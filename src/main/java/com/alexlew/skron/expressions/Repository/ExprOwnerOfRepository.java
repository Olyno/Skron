package com.alexlew.skron.expressions.Repository;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.alexlew.skron.effects.EffLogin;
import com.alexlew.skron.types.Repository;
import org.bukkit.event.Event;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

@Name("Owner of Repository")
@Description("Returns the owner of a Repository")
@Examples({
        "set {_owner} to owner of last Repository"
})
@Since("1.0")

public class ExprOwnerOfRepository extends SimplePropertyExpression<Object, String> {

    static {
        register(ExprOwnerOfRepository.class, String.class,
                "owner", "repository");
    }

    private Expression<Object> repository;

    @Override
    public boolean init( Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult ) {
        repository = (Expression<Object>) expr[0];
        return true;
    }

    @Override
    public String convert( Object o ) {
        String owner = null;
        if (o instanceof Repository) {
            try {
                owner = EffLogin.account.getRepository(((Repository) o).getName()).getOwnerName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (o instanceof GHRepository) {
            owner = ((GHRepository) o).getOwnerName();
        }
        return owner;
    }

    @Override
    public Class<String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "owner";
    }

}