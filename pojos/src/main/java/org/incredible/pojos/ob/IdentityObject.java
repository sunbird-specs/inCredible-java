package org.incredible.pojos.ob;

public class IdentityObject {
    /**
     * Value of the annotation
     * Example: father's or a spouse's name
     */
    private String identity;

    /**
     * IRI of the property by which the recipient of a badge is identified.
     */
    private String type;

    /**
     * Whether or not the identity value is hashed.
     */
    private boolean hashed;

    /**
     * If the recipient is hashed, this should contain the string used to
     * salt the hash.
     */
    private String salt;
}
