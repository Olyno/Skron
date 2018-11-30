package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.alexlew.skron.Skron;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

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
                        return null;
                    }

                    @Override
                    public String toVariableNameString(CommentType arg0) {
                        return null;
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