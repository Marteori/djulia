package com.djulia.dep.dialogue;

import com.djulia.dep.condition.ICondition;
import com.djulia.dep.condition.GameContext;

public class DialogueChoice {
    private final String text;        // текст кнопки ответа
    private final String nextNodeId;  // ID следующей ноды (или null если конец)
    private final ICondition condition;

    public DialogueChoice(String text, String nextNodeId) {
        this(text, nextNodeId, null);
    }

    public DialogueChoice(String text, String nextNodeId, ICondition condition) {
        this.text = text;
        this.nextNodeId = nextNodeId;
        this.condition = condition;
    }

    public boolean isAvailable(GameContext context) {
        return condition == null || condition.check(context);
    }

    public String getText() {
        return text;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }
}