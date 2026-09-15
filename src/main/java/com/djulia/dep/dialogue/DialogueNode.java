package com.djulia.dep.dialogue;

import java.util.List;

public class DialogueNode {
    private final String id;
    private final String npcText;
    private final List<DialogueChoice> choices;

    public DialogueNode(String id, String npcText, List<DialogueChoice> choices) {
        this.id = id;
        this.npcText = npcText;
        this.choices = choices;
    }

    public String getId() {
        return id;
    }

    public String getNpcText() {
        return npcText;
    }

    public List<DialogueChoice> getChoices() {
        return choices;
    }
}
