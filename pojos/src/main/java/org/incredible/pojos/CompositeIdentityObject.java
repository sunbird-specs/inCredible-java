package org.incredible.pojos;

import org.incredible.pojos.ob.IdentityObject;

import java.util.List;

public class CompositeIdentityObject extends IdentityObject {
    private String[] type = new String[]{"Extension", "IdentityObject", "extensions:CompositeIdentityObject"};
    private List<CompositeIdentityObject> components;

    private String name;
    /**
     * A HTTP URL to a printable version of this certificate.
     * The URL points to a base64 encoded data, like data:application/pdf;base64
     * or data:image/jpeg;base64
     */
    private String photo;

    /**
     * Date of birth in YYYY-MM-DD format.
     */
    private String dob;

    /**
     * Gender
     */
    private Gender gender;

    /**
     * Tag URI, if URN is used
     */
    private String tag;

    /**
     *  Uniform resource name
     *  Required in the absence of URL
     */
    private String urn;

    /**
     *  Uniform resource locator
     *  Required in the absence of URN
     */
    private String url;
}
