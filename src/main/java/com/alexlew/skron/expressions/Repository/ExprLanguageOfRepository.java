package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

@Name("Language of repository")
@Description("Returns the programming language of a repository")
@Examples({
        "broadcast language of last repository"
})
@Since("1.0")

public class ExprLanguageOfRepository extends SimplePropertyExpression<GHRepository, String> {

    static {
        register(ExprLanguageOfRepository.class, String.class,
                "[the] [skron] language", "repository");
    }

    @Override
    public String convert( GHRepository repo) {
        return (String) repo.getLanguage();
    }

    @Override
    protected String getPropertyName() {
        return "language";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
