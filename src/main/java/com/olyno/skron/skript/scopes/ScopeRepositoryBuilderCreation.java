package com.olyno.skron.skript.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.skron.util.EffectSection;
import com.olyno.skron.util.classes.RepositoryBuilder;
import org.bukkit.event.Event;

import java.util.HashMap;

@Name("Scope Repository Creation")
@Description("Scope for repository creation")
@Examples({
        "on skript load:\n" +
                "    login to github account \"olyno\" with oauth \"my secret oauth\"\n" +
                "\n" +
                "command create:\n" +
                "\ttrigger:\n" +
                "\t\tmake new repository named \"test\":\n" +
                "\t\t\tset download state of repository to true\n" +
                "\t\tcreate repository \"test\"\n" +
                "\t\tsend \"Repository created!\""
})
@Since("1.0.0")

public class ScopeRepositoryBuilderCreation extends EffectSection {

    public static RepositoryBuilder lastRepositoryBuilder;
    public static HashMap<String, RepositoryBuilder> repositoriesBuilder = new HashMap<>();

    static {
        Skript.registerCondition(ScopeRepositoryBuilderCreation.class,
                "(make|do|create) [new] repo[sitory] [builder] ((with|as) name|named) %string%"
        );
    }

    private Expression<String> repositoryName;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (checkIfCondition()) {
            return false;
        }
        if (!hasSection()) {
            Skript.error("An repository creation scope is useless without any content!");
            return false;
        }
        repositoryName = (Expression<String>) expr[0];
        loadSection(true);
        return true;
    }

    @Override
    protected void execute(Event e) {
        String name = repositoryName.getSingle(e);
        RepositoryBuilder repository = new RepositoryBuilder(name);
        repositoriesBuilder.put(name, repository);
        lastRepositoryBuilder = repository;
        runSection(e);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create new repository with name \"" + repositoryName.toString(e, debug) + "\"";
    }

}
