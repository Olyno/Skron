package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.*;

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
                        return arg0.getTitle();
                    }

                    @Override
                    public String toVariableNameString(GHIssue arg0) {
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<Issue>(Issue.class, "issuebuilder")
                .user("issuebuilder")
                .name("issuebuilder")
                .parser(new Parser<Issue>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public Issue parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(Issue arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString(Issue arg0) {
                        return null;
                    }

                }));
    }

    public Issue() {}

    private String body;
    private String label;
    private GHMilestone milestone;
    private GHUser assignee;
    private GHRepository repository;
    private String title;


    public String getBody() {
        return body;
    }

    public void setBody( String body ) {
        this.body = body;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }

    public GHMilestone getMilestone() {
        return milestone;
    }

    public void setMilestone( GHMilestone milestone ) {
        this.milestone = milestone;
    }

    public GHRepository getRepository() {
        return repository;
    }

    public void setRepository( GHRepository repository ) {
        this.repository = repository;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public GHUser getAssignee() {
        return assignee;
    }

    public void setAssignee( GHUser assignee ) {
        this.assignee = assignee;
    }
}
