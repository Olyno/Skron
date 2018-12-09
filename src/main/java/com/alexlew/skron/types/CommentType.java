package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.alexlew.skron.Skron;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;

public class CommentType {
    static {
        Classes.registerClass(new ClassInfo<CommentType>(CommentType.class, "commenttype")
                .user("commenttype")
                .name("commenttype")
                .parser(new Parser<CommentType>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public CommentType parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(CommentType arg0, int arg1) {
                        if (arg0.getType() instanceof GHIssue) {
                            GHIssue type = (GHIssue) arg0.getType();
                            return type.getTitle();
                        } else if (arg0.getType() instanceof GHCommit) {
                            GHCommit type = (GHCommit) arg0.getType();
                            return type.getOwner().getName();
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public String toVariableNameString(CommentType arg0) {
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<GHIssueComment>(GHIssueComment.class, "issuecomment")
                .user("issue ?comment")
                .name("issuecomment")
                .parser(new Parser<GHIssueComment>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHIssueComment parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHIssueComment arg0, int arg1) {
                        return arg0.getParent().getTitle();
                    }

                    @Override
                    public String toVariableNameString(GHIssueComment arg0) {
                        return arg0.getParent().getTitle();
                    }

                }));

        Classes.registerClass(new ClassInfo<GHCommitComment>(GHCommitComment.class, "commitcomment")
                .user("commit ?comment")
                .name("commitcomment")
                .parser(new Parser<GHCommitComment>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHCommitComment parse( String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHCommitComment arg0, int arg1) {
                        return arg0.getPath();
                    }

                    @Override
                    public String toVariableNameString(GHCommitComment arg0) {
                        return arg0.getPath();
                    }

                }));
    }

    public CommentType() {}

    private Object type;
    private String body;
    private String path;
    private Integer line;
    private Integer position;

    public String getBody() {
        return body;
    }

    public void setBody( String body ) {
        if (body.replaceAll(" ", "") != "") {this.body = body;}
        else {Skron.error("The body can't be set empty.");}
    }

    public Integer getLine() {
        return line;
    }

    public void setLine( Integer line ) {
        this.line = line;
    }

    public String getPath() {
        return path;
    }

    public void setPath( String path ) {
        this.path = path;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition( Integer position ) {
        this.position = position;
    }

    public Object getType() {
        return type;
    }

    public void setType( Object type ) {
        if (type instanceof GHCommit || type instanceof GHIssue) {
            this.type = type;
        } else {
            Skron.error("The type of a comment can only be set as commit or issue, and not " + type.getClass());
        }
    }
}