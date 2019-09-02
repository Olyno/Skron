package com.olyno.skron.util.classes;

public class ReleaseBuilder {

    private String name;
    private String content;
    private String commit;
    private Boolean isDraft;
    private Boolean isPrelease;

    public ReleaseBuilder(String name, String content) {
        this.name = name;
        this.content = content;
        this.commit = null;
        this.isDraft = true;
        this.isPrelease = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Boolean isDraft() {
        return isDraft;
    }

    public void isDraft(Boolean draft) {
        isDraft = draft;
    }

    public Boolean isPrelease() {
        return isPrelease;
    }

    public void isPrelease(Boolean prelease) {
        isPrelease = prelease;
    }
}
