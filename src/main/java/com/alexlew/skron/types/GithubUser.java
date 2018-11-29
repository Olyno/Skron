package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.GHUser;

import java.io.IOException;

public class GithubUser {
    static {
        Classes.registerClass(new ClassInfo<GHUser>(GHUser.class, "githubuser")
                .user("githubuser")
                .name("githubuser")
                .parser(new Parser<GHUser>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHUser parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHUser arg0, int arg1) {
                        try {
                            return arg0.getName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public String toVariableNameString(GHUser arg0) {
                        try {
                            return arg0.getName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                }));

    }
}
