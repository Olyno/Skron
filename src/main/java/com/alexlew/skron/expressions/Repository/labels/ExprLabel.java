package com.alexlew.skron.expressions.Repository.labels;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
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

import java.io.IOException;

@Name("Label")
@Description("Returns a label from its name")
@Examples({
        "set {_label} to label \"Test\""
})
@Since("1.0")

public class ExprLabel extends SimpleExpression<GHLabel> {

    static {
        Skript.registerExpression(ExprLabel.class, GHLabel.class, ExpressionType.SIMPLE,
                "[the] [skron] label [(named|with name)] %string% (from|of) %repository%");
    }

    private Expression<String> labelName;
    private Expression<GHRepository> labelRepository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        labelName = (Expression<String>) expr[0];
        labelRepository = (Expression<GHRepository>) expr[1];
        return true;
    }

    @Override
    protected GHLabel[] get( Event e ) {
        try {
            return new GHLabel[] {labelRepository.getSingle(e).getLabel(labelName.getSingle(e))};
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.DELETE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case DELETE:
                try {
                    labelRepository.getSingle(e).getLabel(labelName.getSingle(e)).delete();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                break;
            }
        }


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHLabel> getReturnType() {
        return GHLabel.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "label " + labelName.getSingle(e) + " from repository " + labelRepository.getSingle(e);
    }

}

