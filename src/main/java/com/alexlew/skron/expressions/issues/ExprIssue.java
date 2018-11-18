package com.alexlew.skron.expressions.issues;


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
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("Issue expression")
@Description("If it isn't inside an issue creation scope, this expression returns a new issue. " +
        "If it is inside of an issue creation scope, it returns the issue that belongs to that scope.")
@Examples({
        "# outside a scope",
        "",
        "set {_e} to a new issue",
        "",
        "# or in a scope",
        "",
        "make a new issue:",
        "\tset the title of the issue to \"My Issue\"",
        "\tset the body of the issue to \"Look my issue, it's beautiful!\"",
        "\tset the assignee user of the issue to github user \"AlexLew95\"",
        "\tadd the label \"test\" to labels of the issue",
        "create last issue"
})
@Since("1.0")

public class ExprIssue extends SimpleExpression<GHIssue> {

    static {
        Skript.registerExpression(ExprIssue.class, GHIssue.class, ExpressionType.SIMPLE,
                "[(the|an|[a] new)] issue");
    }

    private Boolean scope = false;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        scope = EffectSection.isCurrentSection(ScopeNewIssue.class);
        return scope;
    }

    @Override
    protected GHIssue[] get( Event e ) {
        return new GHIssue[] {
                scope ? ScopeNewIssue.lastIssue : new GHIssue()
        };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends GHIssue> getReturnType() {
        return GHIssue.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "the issue";
    }

}
