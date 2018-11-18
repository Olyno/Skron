package com.alexlew.skron.expressions.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("Comments count of issue")
@Description("Returns the number of comments of an issue")
@Examples({
        "set {_comments::*} to comments count of {_issue}"
})
@Since("1.0")


public class ExprCommentsCountOfIssue extends SimplePropertyExpression<GHIssue, Integer> {

    static {
        register(ExprCommentsCountOfIssue.class, Integer.class,
                "[the] comments count",
                "issue");
    }

    @Override
    public Integer convert( GHIssue issue ) {
        return issue.getCommentsCount();
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    protected String getPropertyName() {
        return "comments count";
    }


}

