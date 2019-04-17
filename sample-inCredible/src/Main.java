
import org.incredible.pojos.CertificateExtension;
import org.incredible.pojos.CompositeIdentityObject;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
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
