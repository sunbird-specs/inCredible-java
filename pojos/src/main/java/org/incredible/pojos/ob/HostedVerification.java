package org.incredible.pojos.ob;

import java.util.ArrayList;

public class HostedVerification extends VerificationObject {
    public HostedVerification() {
        type = new ArrayList<String>();
        getType().add("HostedBadge");
    }
}
