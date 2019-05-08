package org.incredible.pojos.ob;

public class AlignmentObject extends OBBase {
    /**
     * Name of the alignment
     */
    private String targetName;

    /**
     * URL linking to the official description of the alignment target
     */
    private String targetURL;

    /**
     * Short description of the alignment target
     */
    private String targetDescription;

    /**
     * Name of the framework the alignment target
     */
    private String targetFramework;

    /**
     * If applicable, a locally unique string identifier that identifies the
     * alignment target within its framework and/or targetUrl
     */
    private String targetCode;
}
