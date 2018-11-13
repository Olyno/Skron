package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueState;

public class Issue {
    static {
        Classes.registerClass(new ClassInfo<GHIssueState>(GHIssueState.class, "issuestate")
                .user("issuestate")
                .name("issuestate")
                .parser(new Parser<GHIssueState>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHIssueState parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHIssueState arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString(GHIssueState arg0) {
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<GHIssue>(GHIssue.class, "issue")
                .user("issue")
                .name("issue")
                .parser(new Parser<GHIssue>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHIssue parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHIssue arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString(GHIssue arg0) {
                        return null;
                    }

                }));
    }
}
