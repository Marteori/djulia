package com.djulia.levelRegistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LevelRegistry {

    private final Map<String, Level> levels;

    public LevelRegistry() {
        this.levels = new HashMap<>();
    }

    public Set<String> getLevels() {
        return Collections.unmodifiableSet(levels.keySet());
    }

    public void addLevel(Level level) {
        if (levels.containsKey(level.getId())) {
            throw new IllegalArgumentException(
                    "Level already exists: " + level.getId()
            );
        }

        levels.put(level.getId(), level);
    }

    public void removeLevel(String levelId) {
        levels.remove(levelId);
    }

    public void removeLevel(Level level) {
        levels.remove(level.getId());
    }

    public Level getLevel(String levelId) {
        return levels.get(levelId);
    }
}