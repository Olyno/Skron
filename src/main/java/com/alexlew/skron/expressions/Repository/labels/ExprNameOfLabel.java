package com.alexlew.skron.expressions.Repository.labels;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHLabel;

@Name("Name of Label")
@Description("Returns the name of a label")
@Examples({
        "broadcast name of {_label}"
})
@Since("1.0")

public class ExprNameOfLabel extends SimplePropertyExpression<GHLabel, String> {

    static {
        register(ExprNameOfLabel.class, String.class,
                "[the] [skron] name", "label");
    }

    @Override
    public String convert( GHLabel label) {
        return label.getName();
    }

    @Override
    protected String getPropertyName() {
        return "name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
