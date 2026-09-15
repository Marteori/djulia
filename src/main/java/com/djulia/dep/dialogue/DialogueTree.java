package com.djulia.dep.dialogue;

import java.util.HashMap;
import java.util.Map;

public class DialogueTree {
    private final Map<String, DialogueNode> nodes;  // хранилище всех нод по ID
    private String currentNodeId;                    // ID текущей ноды

    public DialogueTree(String currentNodeId) {
        this.nodes = new HashMap<>();
        this.currentNodeId = currentNodeId;
    }

    public void addNode(DialogueNode node) {
        this.nodes.put(node.getId(), node);
    }

    public DialogueNode getCurrentNode() {
        return this.nodes.get(this.currentNodeId);
    }

    public void choose(int choiceIndex) {
        DialogueNode currentNode = this.nodes.get(this.currentNodeId);
        this.currentNodeId = currentNode.getChoices().get(choiceIndex).getNextNodeId();
    }
}
