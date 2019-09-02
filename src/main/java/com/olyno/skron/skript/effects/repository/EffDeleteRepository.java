package com.olyno.skron.skript.effects.repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.skron.util.AsyncEffect;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.concurrent.CompletionException;

@Name("Delete Repository")
@Description("Delete a repository.")
@Examples({
        "command delete:\n" +
                "\ttrigger:\n" +
                "\t\tdelete repository \"test\"\n" +
                "\t\tsend \"The repository test has been deleted!\""
})
@Since("1.0.0")

public class EffDeleteRepository extends AsyncEffect {

    static {
        Skript.registerEffect(EffDeleteRepository.class,
                "delete %repository%"
        );
    }

    private Expression<GHRepository> repository;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        repository = (Expression<GHRepository>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        GHRepository repo = repository.getSingle(e);

        executeCode(e, () -> {

            try {
                repo.delete();
            } catch (IOException ex) {
                throw new CompletionException(ex);
            }

        });

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "delete repository " + repository.toString(e, debug);
    }

}
