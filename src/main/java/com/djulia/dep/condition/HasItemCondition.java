package com.djulia.dep.condition;

public class HasItemCondition implements ICondition {
    private final String itemName;

    public HasItemCondition(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean check(GameContext context) {
        return context.getServerStates().containsKey(itemName);
    }
}
