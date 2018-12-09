package com.alexlew.skron.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.Skron;
import com.alexlew.skron.effects.EffLogin;
import com.alexlew.skron.types.CommentType;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;

import java.io.IOException;

@Name("Body of Issue/Comment")
@Description("Returns the body/content of an issue/comment. Can set in a specific scope.")
@Examples({
        "set {_body} to content of {_issue}"
})
@Since("1.0")

public class ExprBody extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprBody.class, String.class,
                "[the] [skron] (body|content)", "object");
    }

    @Override
    public String convert(Object o) {
        if (o instanceof GHIssue) {
            GHIssue issue = (GHIssue) o;
            return issue.getBody();
        } else if (o instanceof Issue) {
            Issue issue = (Issue) o;
            return issue.getBody();
        } else if (o instanceof GHIssueComment) {
            GHIssueComment comment = (GHIssueComment) o;
            return comment.getBody();
        } else if (o instanceof CommentType) {
            CommentType comment = (CommentType) o;
            return comment.getBody();
        } else if (o instanceof GHIssueComment) {
            GHIssueComment comment = (GHIssueComment) o;
            return comment.getBody();
        } else if (o instanceof GHCommitComment) {
            GHCommitComment comment = (GHCommitComment) o;
            return comment.getBody();
        } else {
            return null;
        }

    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
            return new Class[]{String.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        for (Object o : getExpr().getArray(e)) {
            switch (mode) {
                case SET:
                    try {
                        if (o instanceof GHIssue) {
                            GHIssue issue = (GHIssue) o;
                            String author = issue.getUser().getName();
                            if (author == EffLogin.account.getMyself().getName()) {
                                issue.setBody((String) delta[0]);
                            } else {
                                Skron.error("You can set the body of an issue only if you're the owner of this issue, but here the owner is " + author);
                            }
                        } else if (o instanceof Issue) {
                            Issue issue = (Issue) o;
                            issue.setBody((String) delta[0]);
                        } else if (o instanceof CommentType) {
                            CommentType comment = (CommentType) o;
                            comment.setBody((String) delta[0]);
                        } else if (o instanceof GHIssueComment) {
                            GHIssueComment comment = (GHIssueComment) o;
                            comment.update((String) delta[0]);
                        } else if (o instanceof GHCommitComment) {
                            GHCommitComment comment = (GHCommitComment) o;
                            comment.update((String) delta[0]);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case DELETE:
                    if (o instanceof CommentType) {
                        CommentType comment = (CommentType) o;
                        comment.setBody(null);
                    }
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