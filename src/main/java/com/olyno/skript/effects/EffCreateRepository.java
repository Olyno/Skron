package com.olyno.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.Skron;
import com.olyno.skript.scopes.ScopeRepositoryBuilderCreation;
import com.olyno.util.AsyncEffect;
import com.olyno.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;
import org.kohsuke.github.GitHub;

import java.io.IOException;

@Name("Create Repository")
@Description("Create a repository.")
@Examples({
        ""
})
@Since("1.0.0")

public class EffCreateRepository extends AsyncEffect {

    static {
        Skript.registerEffect(EffCreateRepository.class,
                "create repository [builder] %string% [with %-githubaccount%]"
        );
    }

    private Expression<String> repositoryName;
    private Expression<GitHub> account;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repositoryName = (Expression<String>) expr[0];
        account = (Expression<GitHub>) expr[1];
        return true;
    }

    @Override
    protected void execute(Event e) {
        String name = repositoryName.getSingle(e);
        GitHub user = account != null ? account.getSingle(e) : EffLogin.accounts.values().iterator().next();
        RepositoryBuilder builder = ScopeRepositoryBuilderCreation.repositoriesBuilder.getOrDefault(name, new RepositoryBuilder(name));

        try {

            user.createRepository(builder.getName())
                    .description(builder.getDescription())
                    .autoInit(builder.autoInit())
                    .allowMergeCommit(builder.allowMergeCommit())
                    .allowRebaseMerge(builder.allowRebaseMerge())
                    .allowSquashMerge(builder.allowSquashMerge())
                    .downloads(builder.hasDownloads())
                    .issues(builder.hasIssues())
                    .private_(builder.isPrivate())
                    .gitignoreTemplate(builder.getGitignoreTemplate())
                    .wiki(builder.hasWiki())
                    .create();

        } catch (IOException ex) {
            try {
                Skron.error("A repository with name \"" + name + "\" already exists on the account \"" + user.getMyself().getName() + "\". Can't create this repository.");
            } catch (IOException exc) {
                Skript.exception(exc);
            }
        }

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create repository " + repositoryName.toString(e, debug) + " with github account " + (account != null ? account.toString(e, debug) : EffLogin.accounts.values().iterator().next().toString());
    }

}
