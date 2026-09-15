package com.djulia.tileRegistry;

import com.djulia.tileRegistry.tileDefinition.TileDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TileRegistry {

    private static final TileRegistry INSTANCE = new TileRegistry();

    private final Map<String, TileDefinition> definitions = new HashMap<>();

    private TileRegistry() {
    }

    public static TileRegistry getInstance() {
        return INSTANCE;
    }

    public void register(TileDefinition definition) {
        String id = definition.getId();

        if (definitions.putIfAbsent(id, definition) != null) {
            throw new IllegalArgumentException(
                    "Tile definition already registered: " + id
            );
        }
    }

    public void registerOrReplace(TileDefinition definition) {
        definitions.put(definition.getId(), definition);
    }

    public void unregister(String id) {
        definitions.remove(id);
    }

    public TileDefinition get(String id) {
        return definitions.get(id);
    }

    public boolean has(String id) {
        return definitions.containsKey(id);
    }

    public Collection<TileDefinition> getAll() {
        return Collections.unmodifiableCollection(definitions.values());
    }

    public void clear() {
        definitions.clear();
    }
}
