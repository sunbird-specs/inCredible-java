package org.incredible.pojos;

import org.incredible.pojos.ob.Evidence;

import java.util.Date;

public class AssessedEvidence extends Evidence {
    /**
     * Subject of the evidence
     */
    private String subject;

    /**
     * Assessment conducted which elicited the evidence
     */
    private String assessment;

    /**
     * HTTP URL identifier to a JSON-LD object or an embedded profile of the
     * individual(s) or organisation(s) who assessed the competency
     */
    private String assessedBy;

    /**
     * Date when the assessment was conducted
     */
    private Date assessedOn;

    /**
     * Signature value typically a hash generated using private key of the assessedBy
     */
    private String signature;

    public AssessedEvidence() {
        String[] type = new String[]{"Evidence", "Extension", "extensions:AssessedEvidence"};
        setType(type);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getAssessedBy() {
        return assessedBy;
    }

    public void setAssessedBy(String assessedBy) {
        this.assessedBy = assessedBy;
    }

    public Date getAssessedOn() {
        return assessedOn;
    }

    public void setAssessedOn(Date assessedOn) {
        this.assessedOn = assessedOn;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
