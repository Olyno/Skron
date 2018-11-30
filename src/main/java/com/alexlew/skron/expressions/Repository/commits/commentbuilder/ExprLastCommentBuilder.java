package com.alexlew.skron.expressions.Repository.commits.commentbuilder;

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

@Name("Last comment builder")
@Description("Returns the last comment builder. It's set in a comment scope")
@Examples({
        "set {_comment} to last comment builder"
})
@Since("1.0")

public class ExprLastCommentBuilder extends SimpleExpression<CommentType> {

    static {
        Skript.registerExpression(ExprLastCommentBuilder.class, CommentType.class,
                ExpressionType.SIMPLE, "[the] [skron] last[ly] comment build[er]");
    }

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        return true;
    }

    @Override
    protected CommentType[] get( Event e ) {
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
        return "last comment builder";
    }
}
