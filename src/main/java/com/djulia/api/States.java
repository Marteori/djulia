package com.djulia.api;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * States allow to store values of the world that can be
 * used in dialogues, crafting tables, events and etc.
 * to control logic and store arbitrary numerical values
 */
public class States {
    public static final String QUEST_PREFIX = "quests.";
    public static final String DIALOGUE_PREFIX = "dialogue.";
    public static final String FACTIONS_PREFIX = "factions.";

    public Map<String, Object> values = new HashMap<String, Object>();

    private File file;

    public States()
    {}

    public States(File file)
    {
        this.file = file;
    }

    /* CRUD */

    public void add(String id, double value)
    {
        Object previous = this.values.get(id);

        if (previous == null || previous instanceof Number)
        {
            this.values.put(id, (previous == null ? 0 : ((Number) previous).doubleValue()) + value);
        }
    }

    public void setNumber(String id, double value)
    {
        if (Double.isNaN(value)) return;

        Object previous = this.values.get(id);

        this.values.put(id, value);
    }

    public void setString(String id, String value)
    {
        Object previous = this.values.get(id);

        this.values.put(id, value);
    }

    public double getNumber(String id)
    {
        Object object = this.values.get(id);

        return object instanceof Number ? ((Number) object).doubleValue() : 0;
    }

    public boolean isNumber(String id)
    {
        Object object = this.values.get(id);

        return object instanceof Number;
    }

    public String getString(String id)
    {
        Object object = this.values.get(id);

        return object instanceof String ? (String) object : "";
    }

    public boolean isString(String id)
    {
        Object object = this.values.get(id);

        return object instanceof String;
    }

    public boolean reset(String id)
    {
        Object previous = this.values.remove(id);

        return previous != null;
    }

    public boolean resetMasked(String id)
    {
        if (id.trim().equals("*"))
        {
            boolean wasEmpty = this.values.isEmpty();

            if (!wasEmpty)
            {
                this.clear();
            }

            return !wasEmpty;
        }

        if (id.contains("*"))
        {
            id = id.replaceAll("\\*", ".*");

            Pattern pattern = Pattern.compile("^" + id + "$");
            int size = this.values.size();

            this.values.keySet().removeIf(key -> pattern.matcher(key).matches());

            if (this.values.size() != size) {return true;}

            return false;
        }

        return this.reset(id);
    }

    public void clear()
    {
        this.values.clear();
    }

    public void copy(States states)
    {
        this.values.clear();
        this.values.putAll(states.values);
    }

    /* Quest convenience methods */

    public void completeQuest(String id)
    {
        this.add(QUEST_PREFIX + id, 1);
    }

    public int getQuestCompletedTimes(String id)
    {
        return (int) this.getNumber(QUEST_PREFIX + id);
    }

    public boolean wasQuestCompleted(String id)
    {
        return this.getQuestCompletedTimes(id) > 0;
    }

    /* Faction convenience methods */

    public void addFactionScore(String id, int score, int defaultScore)
    {
        if (this.hasFaction(id))
        {
            this.add(FACTIONS_PREFIX + id, score);
        }
        else
        {
            this.setNumber(FACTIONS_PREFIX + id, defaultScore + score);
        }
    }

    public void setFactionScore(String id, int score)
    {
        this.setNumber(FACTIONS_PREFIX + id, score);
    }

    public int getFactionScore(String id)
    {
        return (int) this.getNumber(FACTIONS_PREFIX + id);
    }

    public boolean clearFactionScore(String id)
    {
        return this.reset(FACTIONS_PREFIX + id);
    }

    public void clearAllFactionScores()
    {
        this.values.keySet().removeIf((key) -> key.startsWith(FACTIONS_PREFIX));
    }

    public boolean hasFaction(String id)
    {
        return this.values.containsKey(FACTIONS_PREFIX + id);
    }

    public Set<String> getFactionNames()
    {
        Set<String> factionNames = new HashSet<>();
        for (String key : this.values.keySet())
        {
            if (key.startsWith(FACTIONS_PREFIX))
            {
                factionNames.add(key.replace(FACTIONS_PREFIX, ""));
            }
        }
        return factionNames;
    }

    /* Dialogues convenience methods */

    public void readDialogue(String id, String marker)
    {
        this.add(this.getDialogueId(id, marker), 1);
    }

    public boolean hasReadDialogue(String id, String marker)
    {
        return this.getReadDialogueTimes(id, marker) > 0;
    }

    public int getReadDialogueTimes(String id, String marker)
    {
        return (int) this.getNumber(this.getDialogueId(id, marker));
    }

    private String getDialogueId(String id, String marker)
    {
        id = DIALOGUE_PREFIX + id;

        if (marker != null && !marker.isEmpty())
        {
            id += ":" + marker;
        }

        return id;
    }

    /* Deserialization and serialization */

    public void load() {
    }

    public void save() {
    }
}