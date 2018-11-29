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
import com.alexlew.skron.scopes.ScopeNewRepo;
import com.alexlew.skron.types.Issue;
import com.alexlew.skron.types.Repository;
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Issue builder expression")
@Description("If it isn't inside an issue creation scope, this expression returns a new issue builder. " +
        "If it is inside of an issue creation scope, it returns the issue builder that belongs to that scope.")
@Examples({
        "# outside a scope",
        "",
        "set {_e} to a new issue builder",
        "",
        "# or in a scope",
        "",
        "make new issue:",
        "\tset the repository of the issue builder to repository \"My Repository\"",
        "\tset the body of the issue builder to \"Look my issue, how my issue is beautiful?\"",
        "\tset the label of the issue builder to \"Test\"",
        "\tset the assignee user of the issue builder to github user \"AlexLew95\"",
        "\tcreate the issue builder"
})
@Since("1.0")

public class ExprIssueBuilder extends SimpleExpression<Issue> {

    static {
        Skript.registerExpression(ExprIssueBuilder.class, Issue.class, ExpressionType.SIMPLE,
                "[(the|an|[a] new)] [skron] issue build[er]");
    }

    private Boolean scope = false;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        scope = EffectSection.isCurrentSection(ScopeNewIssue.class);
        return scope;
    }

    @Override
    protected Issue[] get( Event e ) {
        return new Issue[]{
                scope ? ScopeNewIssue.lastIssue : new Issue()
        };
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
        return "the issue builder";
    }

}
