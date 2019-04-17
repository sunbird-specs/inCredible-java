package org.incredible.pojos;

public class SignatoryExtension extends IdentityObject {
    /**
     * Designation or capacity of the signatory
     */
    private String designation;

    /**
     * HTTP URL or a data URI for an image associated with the signatory
     */
    private String image;

    private CryptographicKey publicKey;
}
