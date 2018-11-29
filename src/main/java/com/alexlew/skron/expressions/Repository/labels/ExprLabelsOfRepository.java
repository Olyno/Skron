package com.alexlew.skron.expressions.Repository.labels;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.util.stream.StreamSupport;

@Name("Labels of repository")
@Description("Returns labels of a repository")
@Examples({
        "set {_labels::*} to labels of {_repository}"
})
@Since("1.0")

public class ExprLabelsOfRepository extends SimpleExpression<GHLabel> {

    static {
        Skript.registerExpression(ExprLabelsOfRepository.class, GHLabel.class, ExpressionType.SIMPLE,
                "[the] [skron] labels of %repository%",
                          "[the] [skron] %repository%'s labels");
    }

    private Expression<GHRepository> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        repository = (Expression<GHRepository>) expr[0];
        return true;
    }

    @Override
    protected GHLabel[] get( Event e ) {
        PagedIterable<GHLabel> labels = null;
        try {
            labels = repository.getSingle(e).listLabels();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return StreamSupport.stream(labels.spliterator(), false).toArray(GHLabel[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHLabel> getReturnType() {
        return GHLabel.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "labels of repository " + repository.getSingle(e);
    }

}

