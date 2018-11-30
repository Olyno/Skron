package com.alexlew.skron.scopes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alexlew.skron.types.CommentType;
import com.alexlew.skron.types.Issue;
import com.alexlew.skron.util.EffectSection;
import org.bukkit.event.Event;

@Name("Scope Comment Creation")
@Description("Scope for comment creation")
@Examples({
        "make new comment about issue:",
        "\tset the body of comment to \"My body!\"",
        "comment"
})
@Since("1.0")

public class ScopeNewComment extends EffectSection {

    public static CommentType lastCommentBuilder;

    static {
        Skript.registerCondition(ScopeNewComment.class,
                "(make|do|create) [a] [new] [skron] comment %commenttype%");
    }

    @Override
    public boolean init( Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        if (checkIfCondition())
            return false;
        if (!hasSection()) {
            Skript.error("A comment creation scope is useless without any content!");
            return false;
        }
        loadSection(true);
        return true;
    }

    @Override
    protected void execute( Event e ) {
        lastCommentBuilder = new CommentType();
        runSection(e);
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "make new comment";
    }
}