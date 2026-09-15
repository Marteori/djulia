package com.djulia.dep.condition;

import java.util.HashMap;
import java.util.Map;

public class GameContext {
    private final String subject;         // Who init trigger ~ player
    private final String target;          // Who Initializate trigger ~ Nps
    private final String level;           // Game world-class
    private final String server;          // Server class
    // States is a self-written data storage system that was used in Mappet. Essentially a global HashMap
    private final Map<String, String> serverStates;    // ServerStates
    private final Map<String, String> levelStates;     // LevelStates

    public GameContext(String subject, String target) {
        this.subject = subject;
        this.target = target;
        this.level = "Обычный мир";
        this.server = "Типа клас сервера к которому можно обрашяться";
        this.serverStates = new HashMap<>();
        this.levelStates = new HashMap<>();
    }

    public String getSubject() {return subject;}
    public String getTarget() {return target;}
    public String getLevel() {return level;}
    public String getServer() {return server;}
    public Map<String, String> getServerStates() {return serverStates;}
    public Map<String, String> getLevelStates() {return levelStates;}
}
