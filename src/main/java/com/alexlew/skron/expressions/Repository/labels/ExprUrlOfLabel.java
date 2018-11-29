package com.alexlew.skron.expressions.Repository.labels;

import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.kohsuke.github.GHLabel;

@Name("URL of Label")
@Description("Returns the url of a label")
@Examples({
        "broadcast url of {_label}"
})
@Since("1.0")

public class ExprUrlOfLabel extends SimplePropertyExpression<GHLabel, String> {

    static {
        register(ExprUrlOfLabel.class, String.class,
                "[the] [skron] url", "label");
    }

    @Override
    public String convert( GHLabel label) {
        return label.getUrl();
    }

    @Override
    protected String getPropertyName() {
        return "url";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
