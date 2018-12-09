package com.alexlew.skron.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

public class ConnectionType {

    static {
        Classes.registerClass(new ClassInfo<ConnectionType>(ConnectionType.class, "connectionmethod")
                .user("connectionmethod")
                .name("connectionmethod")
                .parser(new Parser<ConnectionType>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public ConnectionType parse( String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString( ConnectionType arg0, int arg1) {
                        return null;
                    }

                    @Override
                    public String toVariableNameString( ConnectionType arg0) {
                        return null;
                    }

                }));
    }

    private String username;
    private String password;
    private String type;

    public ConnectionType() { }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }
}
