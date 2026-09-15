package com.djulia.tileRegistry;

import com.djulia.tileRegistry.tileDefinition.GroundTileDefinition;
import com.djulia.tileRegistry.tileDefinition.ObjectTileDefinition;

public final class VanillaTiles {

    private VanillaTiles() {
    }

    public static void register(TileRegistry registry) {
        registry.register(new GroundTileDefinition("grass"));
        registry.register(new GroundTileDefinition("dirt"));
        registry.register(new GroundTileDefinition("stone"));
        registry.register(new GroundTileDefinition("water"));

        registry.register(new ObjectTileDefinition("wall"));
        registry.register(new ObjectTileDefinition("door"));
        registry.register(new ObjectTileDefinition("chest"));
    }
}