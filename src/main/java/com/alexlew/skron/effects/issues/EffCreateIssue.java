package com.alexlew.skron.effects.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.alexlew.skron.effects.repositories.EffCreateRepository;
import com.alexlew.skron.scopes.ScopeNewIssue;
import com.alexlew.skron.types.Issue;
import org.bukkit.event.Event;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Create issue")
@Description("Create an issue from a issue builder")
@Examples({
        "command create:",
        "\ttrigger:",
        "\t\tcreate last issue builder"
})
@Since("1.0")

public class EffCreateIssue extends Effect {

    static {
        Skript.registerEffect(EffCreateIssue.class,
                "create %issuebuilder%");
    }

    public static GHIssue lastIssue;

    private Expression<Issue> issuebuilder;

    @Override
    public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
        issuebuilder = (Expression<Issue>) expr[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        Issue issue = issuebuilder.getSingle(e);
        try {
            ScopeNewIssue.lastIssue = issue;
            GHRepository repository = issue.getRepository() != null ? issue.getRepository()
                    : EffCreateRepository.lastRepository;
            lastIssue = repository
                    .createIssue(issue.getTitle())
                    .body(issue.getBody())
                    .label(issue.getLabel())
                    .milestone(issue.getMilestone())
                    .assignee(issue.getAssignee())
                    .create();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create " + issuebuilder.getSingle(e);
    }
}
