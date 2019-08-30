package com.olyno.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.olyno.util.classes.RepositoryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public String toVariableNameString(GitHub github) {
                        try {
                            return github.getMyself().getName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
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
    }

}
