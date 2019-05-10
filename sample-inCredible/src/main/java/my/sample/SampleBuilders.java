package my.sample;

import org.incredible.pojos.AssessedEvidence;
import org.incredible.pojos.CompositeIdentityObject;
import org.incredible.pojos.RankAssessment;
import org.incredible.pojos.Signature;
import org.incredible.pojos.ob.BadgeClass;
import org.incredible.pojos.ob.Criteria;
import org.incredible.pojos.ob.Evidence;

import java.time.Instant;

public class Builders {
    private String context;

    public Builders(String context) {
        this.context = context;
    }

    public CompositeIdentityObject buildRecipient() {
        CompositeIdentityObject recipient = new CompositeIdentityObject();
        recipient.setType(new String[]{"urn"});
        recipient.setHashed(false);
        recipient.setIdentity("urn:in.gov.gstn.id:Z000000000000001");
        recipient.setName("Government Industrial Training Institute, Salboni");
        return recipient;
    }

    public BadgeClass buildBadge() {
        BadgeClass badgeClass = new BadgeClass(context);
        badgeClass.setId("https://dgt.example.gov.in/certs/iti/grading/appreciate");
        badgeClass.setName("Certificate of Appreciation");
        badgeClass.setDescription("Certificate of Appreciation in National Level ITI Grading");
        badgeClass.setImage("data:image/png;base64,<base64-encoded-png-data>");

        Criteria criteria = new Criteria();
        criteria.setNarrative("For exhibiting outstanding performance");

        badgeClass.setCriteria(criteria);
        badgeClass.setIssuer("https://certs.example.gov/o/dgt/HJ5327VB1247G");
        return badgeClass;
    }

    public Evidence buildEvidence() {
        AssessedEvidence evidence = new AssessedEvidence(context);
        evidence.setId("urn:uuid:02644c88-d2b7-41ef-a78c-6adf7fbdb268");
        evidence.setDescription("Rank in National ITI Grading");

        RankAssessment rankAssessment = new RankAssessment();
        rankAssessment.setValue(8);
        rankAssessment.setMaxValue(1);

        evidence.setAssessment(rankAssessment);
        evidence.setAssessedBy("https://dgt.example.gov.in/iti-assessor.json");
        evidence.setAssessedOn(Instant.now().toString());
        return evidence;
    }

    public Signature buildSignature(String signatureValue) {
        Signature signature = new Signature();
        signature.setCreated(Instant.now().toString());
        signature.setSignatureValue(signatureValue);
        // Note that this key is part of issuer.
        signature.setCreator("https://dgt.example.gov.in/keys/awarding_body.json");
        return signature;
    }
}
