package org.incredible.pojos;

public class BadgeClass extends OBBase {
    /**
     * Unique IRI for the badge - HTTP URL or URN
     */
    private String id;

    /**
     * In most cases, this will simply be the string BadgeClass. An array including BadgeClass and other string
     * elements that are either URLs or compact IRIs within the current context are allowed.
     */
    private String[] type = new String[]{"BadgeClass"};

    /**
     * Name of the achievement represented by this class
     * Example: "Carpentry know-how level 1"
     */
    private String name;

    /**
     * Description of the badge
     */
    private String description;


    private String version;

    /**
     * HTTP URL to the image of this credential
     */
    private String image;

    private Criteria criteria;

    private Profile issuer;

    private AlignmentObject alignment;
}
