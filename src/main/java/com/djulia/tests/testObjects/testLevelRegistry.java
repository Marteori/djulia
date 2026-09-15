package com.djulia.tests.testObjects;

import com.djulia.levelRegistry.Level;
import com.djulia.levelRegistry.LevelRegistry;
import com.djulia.tests.BaseTest;

public class testLevelRegistry extends BaseTest {
    public testLevelRegistry() {
        super("LevelRegistry");
    }

    @Override
    protected void runTest() {
        LevelRegistry levelRegistry = LevelRegistry.getInstance();
        levelRegistry.clear();

        Level level = new Level(
                "test",
                "Underworld"
        );

        // Добавление
        levelRegistry.addLevel(level);

        check(
                levelRegistry.getLevel("test") == level,
                "Level можно получить по ID"
        );

        // ID и имя
        check(
                level.getId().equals("test"),
                "ID Level корректный"
        );

        check(
                level.getName().equals("Underworld"),
                "Name Level корректный"
        );

        // Неизвестный уровень
        check(
                levelRegistry.getLevel("does_not_exist") == null,
                "Неизвестный Level возвращает null"
        );

        // Проверка списка уровней
        check(
                levelRegistry.getLevels().contains("test"),
                "Level присутствует в списке"
        );

        // Повторное добавление
        boolean duplicateThrown = false;

        try {
            levelRegistry.addLevel(level);
        } catch (IllegalArgumentException e) {
            duplicateThrown = true;
        }

        check(
                duplicateThrown,
                "Повторная регистрация Level запрещена"
        );

        // Удаление
        levelRegistry.removeLevel("test");

        check(
                levelRegistry.getLevel("test") == null,
                "Level удалён"
        );

        check(
                !levelRegistry.getLevels().contains("test"),
                "Удалённого Level нет в списке"
        );
    }
}
