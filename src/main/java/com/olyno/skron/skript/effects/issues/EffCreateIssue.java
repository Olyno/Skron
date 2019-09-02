package com.olyno.skron.skript.effects.issues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.olyno.skron.skript.scopes.ScopeIssueBuilderCreation;
import com.olyno.skron.util.AsyncEffect;
import com.olyno.skron.util.classes.IssueBuilder;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Create Issue")
@Description("Create an issue.")
@Examples({
        ""
})
@Since("1.0.0")

public class EffCreateIssue extends AsyncEffect {

    static {
        Skript.registerEffect(EffCreateIssue.class,
                "create issue %string% (in|for) %repository%"
        );
    }

    private Expression<String> issueName;
    private Expression<GHRepository> repository;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        issueName = (Expression<String>) expr[0];
        repository = (Expression<GHRepository>) expr[1];
        return true;
    }

    @Override
    protected void execute(Event e) {
        String name = issueName.getSingle(e);
        GHRepository repo = repository.getSingle(e);
        IssueBuilder builder = ScopeIssueBuilderCreation.issuesBuilder.getOrDefault(name, new IssueBuilder(name));

        executeCode(e, () -> {

            try {
                repo.createIssue(builder.getName())
                        .body(builder.getContent())
                        .label(builder.getName())
                        .assignee(builder.getAssignee())
                        .milestone(builder.getMilestone())
                        .create();
            } catch (IOException ex) {
                Skript.exception(ex);
            }

        });

    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create issue " + issueName.toString(e, debug) + " for repository " + repository.toString(e, debug);
    }

}
