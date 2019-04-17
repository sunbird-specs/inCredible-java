package org.incredible.pojos;

import java.util.ArrayList;

public class SignedBadge extends VerificationObject {
    /**
     * The (HTTP) id of the key used to sign the Assertion. If not present, verifiers will check public key(s) declared
     * in the referenced issuer Profile. If a key is declared here, it must be authorized in the issuer Profile as well.
     * creator is expected to be the dereferencable URI of a document that describes a CryptographicKey
     */
    private CryptographicKey creator;

    public SignedBadge() {
        type = new ArrayList<String>();
        getType().add("SignedBadge");
    }
}
