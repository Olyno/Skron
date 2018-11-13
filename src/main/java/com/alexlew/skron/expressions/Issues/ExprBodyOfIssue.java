package com.alexlew.skron.expressions.Issues;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;

import java.io.IOException;

@Name("Body of Issue")
@Description("Returns the body of an issue")
@Examples({
        "set {_body} to body of {_issue}"
})
@Since("1.0")

public class ExprBodyOfIssue extends SimplePropertyExpression<GHIssue, String> {

    static {
        register(ExprBodyOfIssue.class, String.class,
                "body", "issue");
    }

    @Override
    public String convert(GHIssue issue) {
        return issue.getBody();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (GHIssue issue : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    try {
                        issue.setBody((String) delta[0]);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "body";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
