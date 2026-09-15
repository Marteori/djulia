package com.djulia.dep.condition;

public class FlagCondition implements ICondition {
    private final String flagName;

    public FlagCondition(String flagName) {
        this.flagName = flagName;
    }

    @Override
    public boolean check(GameContext context) {
        return context.getLevelStates().containsKey(flagName);
    }
}
