package com.alexlew.skron.effects.repositories;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.alexlew.skron.Skron;
import com.alexlew.skron.effects.EffLogin;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Create Repository")
@Description("Create a Repository")
@Examples({
        "create last Repository"
})
@Since("1.0")

public class EffCreateRepository extends Effect {

    static {
        Skript.registerEffect(EffCreateRepository.class,
                "create %repositorybuilder% [(with|in) [organization] %-string%]");
    }

    public static GHRepository lastRepository;

    private Expression<Repository> repository;
    private Expression<String> organization;

    private GHCreateRepositoryBuilder builder;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        repository = (Expression<Repository>) expr[0];
        organization = (Expression<String>) expr[1];
        return true;
    }

    @Override
    protected void execute(Event e)  {
        String name = repository.getSingle(e).getName().replaceAll(" ", "") == "" ? null : repository.getSingle(e).getName();
        String description = repository.getSingle(e).getDescription() == null ? "" : repository.getSingle(e).getDescription();
        Object homepage = repository.getSingle(e).getHomepage() != null ? repository.getSingle(e).getHomepage() : "";
        Boolean withIssues = repository.getSingle(e).getIssueTrackerState() != null ? repository.getSingle(e).getIssueTrackerState() : false;
        Boolean withWiki = repository.getSingle(e).getWikiState() != null ? repository.getSingle(e).getWikiState() : false;
        Boolean isDownloadable = repository.getSingle(e).getDownloadableState() != null ? repository.getSingle(e).getDownloadableState() : true;
        Boolean isPrivate = repository.getSingle(e).getPrivateState() != null ? repository.getSingle(e).getPrivateState() : false;
        Boolean isInit = repository.getSingle(e).getInitState() != null ? repository.getSingle(e).getInitState() : true;
        if (name == null) {Skron.error("Your new Repository need a name."); return;}

        if (organization != null) {
            if (organization.getSingle(e).replaceAll(" ", "") == "" || organization.getSingle(e) != EffLogin.lastGithubConnection.getUsername()) {
                try {
                    builder = EffLogin.account.getOrganization(organization.getSingle(e)).createRepository(name);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        } else {
            if (name.replaceAll(" ", "") != "") {
                if (EffLogin.account != null) {
                    builder = EffLogin.account.createRepository(name);
                } else {
                    Skron.error("You must to be login before do something.");
                }

            }
        }

        builder.private_(isPrivate)
                .autoInit(isInit)
                .homepage((String) homepage)
                .issues(withIssues)
                .downloads(isDownloadable)
                .wiki(withWiki);

        if (description.replaceAll(" ", "") != "") {
            builder.description(description);
        }
        try {
            lastRepository = builder.create();
        } catch (IOException e1) {
            //e1.printStackTrace();
            Skron.error("A repository with the name \"" + name + "\" already exist, try another name.");
        }

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "Repository " + repository.getSingle(e);
    }
}
