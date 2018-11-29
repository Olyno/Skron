package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.kohsuke.github.GHLabel;

public class Label {
    static {
        Classes.registerClass(new ClassInfo<GHLabel>(GHLabel.class, "label")
                .user("label")
                .name("label")
                .parser(new Parser<GHLabel>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHLabel parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHLabel arg0, int arg1) {
                        return arg0.getName();
                    }

                    @Override
                    public String toVariableNameString(GHLabel arg0) {
                        return null;
                    }
                }));

    }


}
