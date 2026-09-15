package com.djulia.tests.testObjects;

import com.djulia.tests.BaseTest;
import com.djulia.tileRegistry.TileRegistry;
import com.djulia.tileRegistry.VanillaTiles;
import com.djulia.tileRegistry.tileDefinition.TileDefinition;

public class testTileRegistry extends BaseTest {

    public testTileRegistry() {
        super("TileRegistry");
    }

    @Override
    protected void runTest() {
        TileRegistry registry = TileRegistry.getInstance();
        registry.clear();

        // Регистрация базовых тайлов
        VanillaTiles.register(registry);

        check(registry.has("grass"), "Grass зарегистрирован");
        check(registry.has("dirt"), "Dirt зарегистрирован");
        check(registry.has("stone"), "Stone зарегистрирован");

        // Получение определения
        TileDefinition grass = registry.get("grass");

        check(grass != null, "Grass можно получить");
        check(
                grass.getId().equals("grass"),
                "ID grass корректный"
        );

        // Неизвестный ID
        check(
                registry.get("does_not_exist") == null,
                "Неизвестный тайл возвращает null"
        );

        // Количество зарегистрированных тайлов
        check(
                registry.getAll().size() > 0,
                "В реестре есть тайлы"
        );

        // Повторная регистрация должна быть запрещена
        boolean duplicateThrown = false;

        try {
            registry.register(grass);
        } catch (IllegalArgumentException e) {
            duplicateThrown = true;
        }

        check(
                duplicateThrown,
                "Повторная регистрация ID запрещена"
        );

        // unregister
        registry.unregister("grass");

        check(
                !registry.has("grass"),
                "Grass удалён из Registry"
        );

        // registerOrReplace
        registry.registerOrReplace(grass);

        check(
                registry.has("grass"),
                "Grass можно зарегистрировать обратно"
        );
    }
}
