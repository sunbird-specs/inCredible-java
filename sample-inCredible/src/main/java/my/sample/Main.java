package my.sample;

import org.incredible.pojos.CertificateExtension;

import org.incredible.pojos.ob.SignedVerification;
import org.incredible.utils.KeyGenerator;
import org.incredible.utils.SignatureHelper;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * A demonstration of how inCredible specification can be realized
 * in JAVA.
 * Depends on the following jar files:
 *  1. org.incredible.pojos - Plain object with getters and setters
 *  2. org.incredible.utils - Encryption and Signature along with Key utilities
 *  The keys are generally preserved in a database for permanence. In this
 *  demonstration, we store it in local file system as plain files.
 */
public class Main {
    /**
     * The directory where the public and private key files must be stored.
     */
    private static final String WORK_DIR = "./";

    /**
     * The algorithm to use
     */
    private static final String RSA_ALGO = "RSA";

    /**
     * The public key file name
     */
    private static final String PUBLIC_KEY_FILENAME = WORK_DIR + "public.pub";

    /**
     * The private key file name
     */
    private static final String PRIVATE_KEY_FILENAME = WORK_DIR + "private.key";

    /**
     * The public key pair
     */
    private static KeyPair keyPair;

    /**
     * The context json file - for JSON-LD signing and machine-readability.
     */
    private static final String CONTEXT_FILE_NAME = "v1/context.json";

    /**
     * Contents of the CONTEXT_FILE_NAME
     */
    private static String context;

    /**
     * The domain that holds the contexts for public consumption
     */
    private static final String DOMAIN = "http://localhost:8080";

    /**
     * Signature helper
     */
    private static SignatureHelper signatureHelper;

    /**
     * Sample builders
     */
    private static SampleBuilders sampleBuilders;

    private static KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = new KeyGenerator(RSA_ALGO, 2048);
        keyPair = keyGenerator.get();
        return keyPair;
    }

    private String getPublicKey() throws IOException {
        return new KeyWriter().getPublicKey(keyPair.getPublic());
    }

    private static void initializeKeys() {
        try {
            keyPair = KeyLoader.load(RSA_ALGO, PUBLIC_KEY_FILENAME, PRIVATE_KEY_FILENAME);
            if (keyPair.getPrivate() == null ||
                    keyPair.getPublic() == null) {
                // Generate new key pairs
                keyPair = generateKeys();

                // Write it in current directory for next time
                new KeyWriter().write(keyPair, WORK_DIR);
            }
        } catch (NoSuchAlgorithmException algoException) {
            System.out.println("No such algorithm. Refer https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html");
        } catch (IOException ioException) {
            System.out.println(ioException.getCause() + "message : " + ioException.getMessage());
        }
    }

    private static void initSignatureHelper() {
        try {
            signatureHelper = new SignatureHelper("SHA1withRSA", keyPair);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * A quick sample for sign and verification
     */
    private static void sampleSignatureTest() {
        try {
            String signature = signatureHelper.sign(new String("hello").getBytes());
            System.out.println("verification result = " +
                    signatureHelper.verify(new String("hello").getBytes(), signature));
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the JSON-LD context
     * @throws IOException
     */
    private static void initContext() throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource(CONTEXT_FILE_NAME).getFile());

        if (file == null) {
            throw new IOException("Context file not found");
        }

        context = DOMAIN + "/" + CONTEXT_FILE_NAME;
        System.out.println("Context file Found : " + file.exists());
    }

    private static void initBuilders() {
        sampleBuilders = new SampleBuilders(context);
    }

    /**
     * Validates if the certificate input is un-tampered against the given signature
     * @param certificate
     * @param signatureValue
     * @return
     */
    public static boolean verifySignature(String certificate, String signatureValue) {
        boolean isValid = false;
        try {
            isValid = signatureHelper.verify(certificate.getBytes(),
                    signatureValue);
            System.out.println("Certificate isValid : " + isValid);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    /**
     * Sets the dates - issued on and expiry
     * @param certificate
     */
    private static void setDates(CertificateExtension certificate) {
        // Set date limits
        String nowDt = Instant.now().toString();
        certificate.setIssuedOn(Instant.now().toString());

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd");
        certificate.setValidFrom(dateFormatGmt.format(Date.from(Instant.now())));

        // The certificate is set to expire after 2 years.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        certificate.setExpires(dateFormatGmt.format(calendar.getTime()));
    }

    public static void main(String[] args) throws IOException {
        initContext();
        initBuilders();
        initializeKeys();
        initSignatureHelper();

        CertificateExtension certificate = new CertificateExtension(context);
        certificate.setId("tag:msde.gov.in,2015-02-27:dgt.certificate/1800122349");
        certificate.setRecipient(sampleBuilders.buildRecipient());
        certificate.setBadge(sampleBuilders.buildBadge());
        setDates(certificate);
        certificate.setEvidence(sampleBuilders.buildEvidence());

        // Mark that this is signed badge (not hosted verification)
        SignedVerification signedVerification = new SignedVerification();
        certificate.setVerification(signedVerification);
        certificate.setSignatory(new String[]{"https://example.com/dgt/1"});

        // Sign the certificate - whatever that has built so far
        String toSignCertificate = certificate.toString();
        String signatureValue = null;
        try {
            signatureValue = signatureHelper.sign(toSignCertificate.getBytes());
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        certificate.setSignature(sampleBuilders.buildSignature(signatureValue));

        // Prints the certificate in json format
        // System.out.println(certificate.toString());

        // Verify the signature
        verifySignature(toSignCertificate, signatureValue);
    }
}
