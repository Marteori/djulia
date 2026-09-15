package com.djulia;

import com.djulia.dep.dialogue.DialogueChoice;
import com.djulia.dep.dialogue.DialogueNode;
import com.djulia.dep.dialogue.DialogueTree;

import java.util.List;
import java.io.PrintStream;

public class MainDep {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        DialogueTree DT = new DialogueTree("start");

        DT.addNode(
            new DialogueNode(
                "start",
                "Привет, путник! Что тебе нужно?",
                List.of(
                    new DialogueChoice("Расскажи о задании", "quest"),
                    new DialogueChoice("Пока", "bye")
                )
            )
        );

        DT.addNode(
            new DialogueNode(
                "quest",
                "Найди артефакт в пещере на севере.",
                List.of(
                    new DialogueChoice("Понял, возьмусь", "accept"),
                    new DialogueChoice("Слишком опасно", "bye")
                )
            )
        );

        DT.addNode(
            new DialogueNode(
                "accept",
                "Удачи, путник!",
                List.of()
            )
        );

        DT.addNode(
            new DialogueNode(
                "bye",
                "Удачи тебе.",
                List.of()
            )
        );

        // Выводим текущую ноду
        DialogueNode current = DT.getCurrentNode();
        System.out.println("NPC: " + current.getNpcText());
        System.out.println("Варианты:");
        for (int i = 0; i < current.getChoices().size(); i++) {
            System.out.println(i + ": " + current.getChoices().get(i).getText());
        }
        //TODO da

        // Выбираем вариант 0
        System.out.println("\nВыбираем: " + current.getChoices().get(0).getText());
        DT.choose(0);

        // Выводим следующую ноду
        current = DT.getCurrentNode();
        System.out.println("\nNPC: " + current.getNpcText());
    }
}