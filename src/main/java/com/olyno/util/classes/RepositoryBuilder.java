package com.olyno.util.classes;

public class RepositoryBuilder {

    private String name;
    private String description;
    private String gitignoreTemplate;
    private Boolean isPrivate;
    private Boolean hasIssues;
    private Boolean hasDownloads;
    private Boolean hasWiki;
    private Boolean autoInit;
    private Boolean allowMergeCommit;
    private Boolean allowRebaseMerge;
    private Boolean allowSquashMerge;

    public RepositoryBuilder(String name) {
        this.name = name;
        this.description = null;
        this.gitignoreTemplate = null;
        this.isPrivate = false;
        this.hasIssues = true;
        this.hasDownloads = true;
        this.hasWiki = false;
        this.autoInit = true;
        this.allowMergeCommit = true;
        this.allowRebaseMerge = true;
        this.allowSquashMerge = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isPrivate() {
        return isPrivate;
    }

    public void isPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean hasIssues() {
        return hasIssues;
    }

    public void hasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public Boolean hasDownloads() {
        return hasDownloads;
    }

    public void hasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public Boolean hasWiki() {
        return hasWiki;
    }

    public void hasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public Boolean autoInit() {
        return autoInit;
    }

    public void autoInit(Boolean autoInit) {
        this.autoInit = autoInit;
    }

    public Boolean allowMergeCommit() {
        return allowMergeCommit;
    }

    public void allowMergeCommit(Boolean allowMergeCommit) {
        this.allowMergeCommit = allowMergeCommit;
    }

    public Boolean allowRebaseMerge() {
        return allowRebaseMerge;
    }

    public void allowRebaseMerge(Boolean allowRebaseMerge) {
        this.allowRebaseMerge = allowRebaseMerge;
    }

    public Boolean allowSquashMerge() {
        return allowSquashMerge;
    }

    public void allowSquashMerge(Boolean allowSquashMerge) {
        this.allowSquashMerge = allowSquashMerge;
    }

    public String getGitignoreTemplate() {
        return gitignoreTemplate;
    }

    public void setGitignoreTemplate(String gitignoreTemplate) {
        this.gitignoreTemplate = gitignoreTemplate;
    }
}
