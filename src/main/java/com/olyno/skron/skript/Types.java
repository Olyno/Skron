package com.olyno.skron.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.kohsuke.github.*;

import java.io.IOException;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(GitHub.class, "githubaccount")
                .defaultExpression(new EventValueExpression<>(GitHub.class))
                .user("github(( )?account)?")
                .name("Github")
                .description("Github Account")
                .since("1.0.0")
                .parser(new Parser<GitHub>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GitHub parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GitHub github, int arg1) {
                        try {
                            return github.getMyself().getName();
                        } catch (IOException ex) {
                            Skript.exception(ex, "Can't get the name of the github user.");
                        }
                        return null;
                    }

                    @Override
                    public String toVariableNameString(GitHub github) {
                        try {
                            return github.getMyself().getName();
                        } catch (IOException ex) {
                            Skript.exception(ex, "Can't get the name of the github user.");
                        }
                        return null;
                    }

                }));

        Classes.registerClass(new ClassInfo<>(GHUser.class, "githubuser")
                .defaultExpression(new EventValueExpression<>(GHUser.class))
                .user("(github? ?)?user")
                .name("Github User")
                .description("A github user.")
                .since("1.0.0")
                .parser(new Parser<GHUser>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHUser parse(String name, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHUser user, int arg1) {
                        try {
                            return user.getName();
                        } catch (IOException e) {
                            return null;
                        }
                    }

                    @Override
                    public String toVariableNameString(GHUser user) {
                        try {
                            return user.getName();
                        } catch (IOException e) {
                            return null;
                        }
                    }

                }));

        Classes.registerClass(new ClassInfo<>(GHRepository.class, "repository")
                .defaultExpression(new EventValueExpression<>(GHRepository.class))
                .user("repo(sitory)?")
                .name("Repository")
                .description("Any github repository from the connected account.")
                .since("1.0.0")
                .parser(new Parser<GHRepository>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHRepository parse(String arg0, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHRepository repository, int arg1) {
                        return repository.getName();
                    }

                    @Override
                    public String toVariableNameString(GHRepository repository) {
                        return repository.getName();
                    }

                }));

        Classes.registerClass(new ClassInfo<>(RepositoryBuilder.class, "repositorybuilder")
                .defaultExpression(new EventValueExpression<>(RepositoryBuilder.class))
                .user("repo(sitory)? ?builder")
                .name("Repository Builder")
                .description("A repository builder, to create a repository.")
                .since("1.0.0")
                .parser(new Parser<RepositoryBuilder>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public RepositoryBuilder parse(String name, ParseContext arg1) {
                        return new RepositoryBuilder(name);
                    }

                    @Override
                    public String toString(RepositoryBuilder repository, int arg1) {
                        return repository.getName();
                    }

                    @Override
                    public String toVariableNameString(RepositoryBuilder repository) {
                        return repository.getName();
                    }

                }));

        Classes.registerClass(new ClassInfo<>(GHBranch.class, "branch")
                .defaultExpression(new EventValueExpression<>(GHBranch.class))
                .user("(repo(sitory)? ?)?branch")
                .name("Repository Branch")
                .description("A repository branch.")
                .since("1.0.0")
                .parser(new Parser<GHBranch>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHBranch parse(String name, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHBranch branch, int arg1) {
                        return branch.getName();
                    }

                    @Override
                    public String toVariableNameString(GHBranch branch) {
                        return branch.getName();
                    }

                }));

        Classes.registerClass(new ClassInfo<>(GHLabel.class, "label")
                .defaultExpression(new EventValueExpression<>(GHLabel.class))
                .user("(repo(sitory)? ?)?label")
                .name("Repository Label")
                .description("A repository label.")
                .since("1.0.0")
                .parser(new Parser<GHLabel>() {

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    public GHLabel parse(String name, ParseContext arg1) {
                        return null;
                    }

                    @Override
                    public String toString(GHLabel label, int arg1) {
                        return label.getName();
                    }

                    @Override
                    public String toVariableNameString(GHLabel label) {
                        return label.getName();
                    }

                }));
    }

}
