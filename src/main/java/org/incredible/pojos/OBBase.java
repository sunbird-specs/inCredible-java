package org.incredible.pojos;

/**
 * Common properties that are applicable to several classes of the OpenBadges
 */
// TODO: Check hierarchy
public class OBBase {
    /**
     * Identifies a related version of the entity.
     */
    private String[] related;

    /**
     * The version identifier for the present edition of the entity.
     */
    private String version;

    /**
     * A claim made about this entity.
     */
    private Endorsement endorsement;

    public String[] getRelated() {
        return related;
    }

    public void setRelated(String[] related) {
        this.related = related;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Endorsement getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(Endorsement endorsement) {
        this.endorsement = endorsement;
    }
}
