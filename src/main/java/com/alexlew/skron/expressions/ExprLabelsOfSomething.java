package com.alexlew.skron.expressions;

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
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

@Name("Labels of issue/repository")
@Description("Returns labels of an issue/repository. You can add labels.")
@Examples({
        "set {_labels::*} to labels of {_issue}",
        "",
        "# or:",
        "",
        "add label \"Test\" to labels of {_issue}"
})
@Since("1.0")

public class ExprLabelsOfSomething extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprLabelsOfSomething.class, Object.class, ExpressionType.SIMPLE,
                "[the] [skron] labels of %object%",
                "[the] [skron] %object%'s labels");
    }

    private Expression<Object> object;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        object = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    protected Object[] get( Event e ) {
        Object o = object.getSingle(e);
        if (o instanceof GHLabel) {
            GHIssue issue = (GHIssue) o;
            Collection<GHLabel> l;
            List<GHLabel> labels = new ArrayList<GHLabel>();
            try {
                l = issue.getLabels();
                labels = new ArrayList(l);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return labels.toArray(new GHLabel[labels.size()]);
        } else if (o instanceof GHRepository) {

            GHRepository repository = (GHRepository) o;
            PagedIterable<GHLabel> labels = null;
            try {
                labels = repository.listLabels();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return StreamSupport.stream(labels.spliterator(), false).toArray(GHLabel[]::new);
        } else {
            return null;
        }

    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return new Class[]{Object.class};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case ADD:
                if (object.getSingle(e) instanceof GHIssue) {
                    GHIssue issue = (GHIssue) object.getSingle(e);
                    try {
                        for (Object label : delta) {
                            if (label instanceof String) {
                                issue.addLabels((String) label);
                            } else if (label instanceof GHLabel) {
                                issue.addLabels((GHLabel) label);
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }

            case REMOVE:
                if (object.getSingle(e) instanceof GHIssue) {
                    GHIssue issue = (GHIssue) object.getSingle(e);
                    try {
                        for (Object label : delta) {
                            if (label instanceof String) {
                                issue.removeLabels((String) label);
                            } else if (label instanceof GHLabel) {
                                issue.removeLabels((GHLabel) label);
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            case REMOVE_ALL:
                if (object.getSingle(e) instanceof GHIssue) {
                    GHIssue issue = (GHIssue) object.getSingle(e);
                    try {
                        Collection<GHLabel> l;
                        List<GHLabel> labels = new ArrayList<GHLabel>();
                        l = issue.getLabels();
                        labels = new ArrayList(l);
                        for (GHLabel label : labels) {
                            issue.removeLabels(label);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            case DELETE:
                if (object.getSingle(e) instanceof GHIssue) {
                    GHIssue issue = (GHIssue) object.getSingle(e);
                    try {
                        Collection<GHLabel> l;
                        List<GHLabel> labels = new ArrayList<GHLabel>();
                        l = issue.getLabels();
                        labels = new ArrayList(l);
                        for (GHLabel label : labels) {
                            issue.removeLabels(label);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            default:
                break;
        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString( Event e, boolean debug ) {
        return "labels";
    }

}

