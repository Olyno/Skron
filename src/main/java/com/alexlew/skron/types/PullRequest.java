package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

public class PullRequest {
    static {
        Classes.registerClass(new ClassInfo<GHPullRequest>(GHPullRequest.class, "pullrequest")
                .user("pull ?request")
                .name("pullrequest")
                .parser(new Parser<GHPullRequest>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHPullRequest parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHPullRequest arg0, int arg1) {
                        return arg0.getTitle();
                    }

                    @Override
                    public String toVariableNameString(GHPullRequest arg0) {
                        return arg0.getTitle();
                    }

                }));


    }

    public PullRequest() {}

    private GHRepository repository;


    public GHRepository getRepository() {
        return repository;
    }

    public void setRepository( GHRepository repository ) {
        this.repository = repository;
    }
}
