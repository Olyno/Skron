package com.alexlew.skron.expressions.Repository.labels;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.kohsuke.github.GHLabel;

import java.io.IOException;

@Name("Color of Label")
@Description("Returns the color of a label")
@Examples({
        "broadcast color of {_label}"
})
@Since("1.0")

public class ExprColorOfLabel extends SimplePropertyExpression<GHLabel, String> {

    static {
        register(ExprColorOfLabel.class, String.class,
                "[the] [skron] colo[u]r", "label");
    }

    @Override
    public String convert( GHLabel label) {
        return label.getColor();
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        for (GHLabel label : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    try {
                        label.setColor((String) delta[0]);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case DELETE:
                    try {
                        label.delete();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "color";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
