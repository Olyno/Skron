package com.alexlew.skron.expressions.Repository.issues.issuebuilder;

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
import com.alexlew.skron.scopes.ScopeNewIssue;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;

@Name("Last issue builder")
@Description("Returns the last issue builder. It's set in a issue scope")
@Examples({
        "create last issue builder"
})
@Since("1.0")

public class ExprLastIssueBuilder extends SimpleExpression<Issue> {

    static {
        Skript.registerExpression(ExprLastIssueBuilder.class, Issue.class,
                ExpressionType.SIMPLE, "[the] [skron] last[ly] issue build[er]");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected Issue[] get( Event e ) {
            return new Issue[] {ScopeNewIssue.lastIssue};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Issue> getReturnType() {
        return Issue.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "last issue builder";
    }
}
