package com.alexlew.skron.expressions.Repository.commits.commentbuilder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.CommentType;
import org.bukkit.event.Event;

@Name("Path of comment")
@Description("Returns the path of a comment. Can be set in a comment scope about commit.")
@Examples({
        "# outside a scope",
        "broadcast path of last comment builder",
        "# or in a scope",
        "make new comment about commit:",
        "\tset path of comment to \"./src/myfile.java\""
})
@Since("1.0")

public class ExprPathOfComment extends SimplePropertyExpression<CommentType, String> {

    static {
        register(ExprPathOfComment.class, String.class,
                "[the] [skron] path", "commenttype");
    }

    @Override
    public String convert( CommentType comment ) {
        return comment.getPath();
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change( Event e, Object[] delta, Changer.ChangeMode mode) {
        for (CommentType comment : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    comment.setPath((String) delta[0]);
                    break;
                case DELETE:
                    comment.setPath(null);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "path";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
