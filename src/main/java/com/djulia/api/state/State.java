package com.djulia.api.state;

import java.util.HashMap;
import java.util.Map;

public class State {

    private final Map<String, Object> values = new HashMap<>();

    public void set(String id, Object value) {
        if (value == null) {
            values.remove(id);
            return;
        }

        values.put(id, value);
    }

    public Object get(String id) {
        return values.get(id);
    }

    public boolean has(String id) {
        return values.containsKey(id);
    }

    public void remove(String id) {
        values.remove(id);
    }

    public void clear() {
        values.clear();
    }

    public Map<String, Object> getValues() {
        return values;
    }
}