package com.djulia.tests;

public abstract class BaseTest {
    protected final String testName;

    protected BaseTest(String testName) {
        this.testName = testName;
    }

    public void startTest() {
        System.out.println("=== " + testName + " ===");
        runTest();
        System.out.println();
    }

    protected abstract void runTest();

    public static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(
                    "TEST FAILED: " + description
            );
        }

        System.out.println("OK: " + description);
    }
}
