package com.alexlew.skron.expressions;


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
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Comment expression")
@Description("If it isn't inside an comment creation scope, this expression returns a new comment. " +
        "If it is inside of an comment creation scope, it returns the comment that belongs to that scope.")
@Examples({
        "# outside a scope",
        "",
        "set {_e} to a new comment",
        "",
        "# or in a scope",
        "",
        "make a new comment about issue {_issue}:",
        "\tset body of comment to \"My issue!\"",
        "comment"
})
@Since("1.0")

public class ExprComment extends SimpleExpression<CommentType> {

    static {
        Skript.registerExpression(ExprComment.class, CommentType.class, ExpressionType.SIMPLE,
                "[(the|an|[a] new)] comment");
    }

    private Boolean scope = false;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        scope = EffectSection.isCurrentSection(ScopeNewComment.class);
        return scope;
    }

    @Override
    protected CommentType[] get( Event e ) {
        return new CommentType[]{
                scope ? ScopeNewComment.lastCommentBuilder : new CommentType()
        };
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
        return "the comment";
    }

}
