package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

import java.net.URL;

public class Repository {

    static {
        //TODO Refaire le type Repository (à supprimer)
        Classes.registerClass(new ClassInfo<Repository>(Repository.class, "repository")
                .user("repository")
                .name("repository")
                .parser(new Parser<Repository>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public Repository parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(Repository arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString(Repository arg0) {
                        return null;
                    }

                }));
    }

    private String name;
    private String description;
    private Object homepage;
    private Boolean downloadableState;
    private Boolean issueTrackerState;
    private Boolean wikiState;
    private Boolean privateState;
    private Boolean initState;

    public Repository() { }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Boolean getPrivateState() {
        return privateState;
    }

    public void setPrivateState( Boolean privateState ) {
        this.privateState = privateState;
    }

    public Boolean getInitState() {
        return initState;
    }

    public void setInitState( Boolean initState ) {
        this.initState = initState;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage( Object homepage ) {
        if (homepage instanceof URL || homepage instanceof String) {
            this.homepage = homepage;
        }

    }

    public Boolean getDownloadableState() {
        return downloadableState;
    }

    public void setDownloadableState( Boolean downloadableState ) {
        this.downloadableState = downloadableState;
    }

    public Boolean getIssueTrackerState() {
        return issueTrackerState;
    }

    public void setIssueTrackerState( Boolean issueTrackerState ) {
        this.issueTrackerState = issueTrackerState;
    }

    public Boolean getWikiState() {
        return wikiState;
    }

    public void setWikiState( Boolean wikiState ) {
        this.wikiState = wikiState;
    }
}
