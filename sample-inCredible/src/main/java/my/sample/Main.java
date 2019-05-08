package my.sample;

import org.incredible.pojos.CertificateExtension;
import org.incredible.pojos.CompositeIdentityObject;

import org.incredible.utils.EncryptionHelper;
import org.incredible.utils.KeyGenerator;
import org.incredible.utils.SignatureHelper;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.Instant;

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
     * Signature helper
     */
    private static SignatureHelper signatureHelper;

    private static KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = new KeyGenerator(RSA_ALGO, 2048);
        keyPair = keyGenerator.get();
        return keyPair;
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
            System.out.println(keyPair.getPrivate().getFormat());
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

    private void sampleSignatureTest() {
        try {
            byte[] signature = signatureHelper.sign(new String("hello").getBytes());
            System.out.println("verification result = " +
                    signatureHelper.verify(new String("hello").getBytes(), signature));
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeKeys();
        initSignatureHelper();

        CertificateExtension certificate = new CertificateExtension();
        CompositeIdentityObject recipient = new CompositeIdentityObject();

        certificate.setAwardedThrough("PMKYY");
        String nowDt = Instant.now().toString();
        certificate.setValidFrom(nowDt);
        certificate.setExpires(null);
        certificate.setId("http://mydomain/certs/1");
        certificate.setIssuedOn(nowDt);
        certificate.setNarrative("Certified for level-1 carpentry");

        System.out.println(certificate.toString());
    }
}
