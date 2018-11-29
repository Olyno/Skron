package com.alexlew.skron.expressions.Repository.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHLabel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Name("Labels of issue")
@Description("Returns labels of an issue. We can add labels.")
@Examples({
        "set {_labels::*} to labels of {_issue}",
        "",
        "# or:",
        "",
        "add label \"Test\" to labels of {_issue}"
})
@Since("1.0")

public class ExprLabelsOfIssue extends SimpleExpression<GHLabel> {

    static {
        Skript.registerExpression(ExprLabelsOfIssue.class, GHLabel.class, ExpressionType.SIMPLE,
                "[the] [skron] labels of %issue%",
                          "[the] [skron] %issue%'s labels");
    }

    private Expression<GHIssue> issue;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        issue = (Expression<GHIssue>) expr[0];
        return true;
    }

    @Override
    protected GHLabel[] get( Event e ) {
        Collection<GHLabel> l;
        List<GHLabel> labels = new ArrayList<GHLabel>();
        try {
            l = issue.getSingle(e)
                    .getLabels();
            labels = new ArrayList(l);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return labels.toArray(new GHLabel[labels.size()]);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return new Class[]{GHIssue.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case ADD:
                try {
                    for (Object label : delta) {
                        if (label instanceof String) {
                            issue.getSingle(e).addLabels((String) label);
                        } else if (label instanceof GHLabel) {
                            issue.getSingle(e).addLabels((GHLabel) label);
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case REMOVE:
                try {
                    for (Object label : delta) {
                        if (label instanceof String) {
                            issue.getSingle(e).removeLabels((String) label);
                        } else if (label instanceof GHLabel) {
                            issue.getSingle(e).removeLabels((GHLabel) label);
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case REMOVE_ALL:
                try {
                    Collection<GHLabel> l;
                    List<GHLabel> labels = new ArrayList<GHLabel>();
                    l = issue.getSingle(e)
                             .getLabels();
                    labels = new ArrayList(l);
                    for (GHLabel label : labels) {
                        issue.getSingle(e).removeLabels(label);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case DELETE:
                try {
                    Collection<GHLabel> l;
                    List<GHLabel> labels = new ArrayList<GHLabel>();
                    l = issue.getSingle(e)
                            .getLabels();
                    labels = new ArrayList(l);
                    for (GHLabel label : labels) {
                        issue.getSingle(e).removeLabels(label);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends GHLabel> getReturnType() {
        return GHLabel.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "labels";
    }

}

