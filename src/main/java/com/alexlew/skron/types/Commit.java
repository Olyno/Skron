package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitStatus;
import org.kohsuke.github.GHRepository;

public class Commit {
    static {
        Classes.registerClass(new ClassInfo<GHCommit>(GHCommit.class, "commit")
                .user("commit")
                .name("commit")
                .parser(new Parser<GHCommit>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHCommit parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHCommit arg0, int arg1) {
                        return arg0.getSHA1();
                    }

                    @Override
                    public String toVariableNameString(GHCommit arg0) {
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<Commit>(Commit.class, "commitbuilder")
                .user("commitbuilder")
                .name("commitbuilder")
                .parser(new Parser<Commit>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public Commit parse( String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString( Commit arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString( Commit arg0) {
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<GHCommitStatus>(GHCommitStatus.class, "commitinfo")
                .user("commitinfo")
                .name("commitinfo")
                .parser(new Parser<GHCommitStatus>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHCommitStatus parse( String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString( GHCommitStatus arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString( GHCommitStatus arg0) {
                        return null;
                    }

                }));
    }

    public Commit() {}

    private GHRepository repository;


    public GHRepository getRepository() {
        return repository;
    }

    public void setRepository( GHRepository repository ) {
        this.repository = repository;
    }
}
