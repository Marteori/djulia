package com.djulia.tests;

import com.djulia.tests.testObjects.*;

import java.io.PrintStream;

public class MainTester {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        new testTileRegistry().startTest();
        new testLevelRegistry().startTest();
        new testWorldPos().startTest();
        new testChunk().startTest();
        new testTileState().startTest();
        new testLevel().startTest();
        new testTileDefinition().startTest();

        System.out.println("\nВсе тесты пройдены.");
    }
}