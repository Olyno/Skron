package com.alexlew.skron.expressions.commenttype;

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
import com.alexlew.skron.scopes.ScopeNewComment;
import com.alexlew.skron.types.CommentType;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

@Name("About issue")
@Description("Returns a comment type \"issue\"")
@Examples({
        "make new comment about issue:",
        "\t#code"
})
@Since("1.0")

public class ExprAboutIssue extends SimpleExpression<CommentType> {

    static {
        Skript.registerExpression(ExprAboutIssue.class, CommentType.class, ExpressionType.SIMPLE,
                "about [skron] issue %issue%");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected CommentType[] get( Event e ) {
        ScopeNewComment.lastCommentBuilder.setType(issue.getSingle(e));
        return new CommentType[] {ScopeNewComment.lastCommentBuilder};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends CommentType> getReturnType() {
        return CommentType.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "about issue";
    }

}

