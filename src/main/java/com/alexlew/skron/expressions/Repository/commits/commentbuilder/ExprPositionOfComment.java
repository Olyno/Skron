package com.alexlew.skron.expressions.Repository.commits.commentbuilder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.CommentType;
import org.bukkit.event.Event;

@Name("Position of comment")
@Description("Returns the position of a comment. Can be set in a comment scope about commit.")
@Examples({
        "# outside a scope",
        "broadcast position of last comment builder",
        "# or in a scope",
        "make new comment about commit:",
        "\tset position of comment to \"./src/myfile.java\""
})
@Since("1.0")

public class ExprPositionOfComment extends SimplePropertyExpression<CommentType, Integer> {

    static {
        register(ExprPositionOfComment.class, Integer.class,
                "[the] [skron] position", "commenttype");
    }

    @Override
    public Integer convert( CommentType comment ) {
        return comment.getPosition();
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return new Class[]{Integer.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        for (CommentType comment : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    comment.setPosition((Integer) delta[0]);
                    break;
                case DELETE:
                    comment.setPosition(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "position";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}