package org.incredible.pojos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class Profile extends OBBase {
    /**
     * IRI to the awarding body, assessor or training body.
     */
    private String id;

    private List<String> type;

    /**
     * Name of the awarding body, assessor or training body.
     */
    private String name;

    private String email;

    /**
     * URL of the awarding body, assessor or training body
     */
    private String url;

    /**
     * Short description
     */
    private String description;

    private CryptographicKey publicKey;

    /**
     * List of HTTP URLs of the signed badges
     */
    private List<String> revocationList;

    // Part of OpenBadges, not mentioned in inCredible
    /**
     * A phone number
     */
    private String telephone;

    private VerificationObject verification;
}
