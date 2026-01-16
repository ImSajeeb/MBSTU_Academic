package com.sajeeb;


public class CustomPRNG {

    private static long seed = System.nanoTime();
    private static final long MOD = 2147483647; // 2^31 - 1 (prime)

    //INT

    public static int randomInt() {
        seed = scramble(seed, 1);
        return (int)(seed % Integer.MAX_VALUE);
    }

    public static int randomInt(int bound) {
        seed = scramble(seed, bound);
        return (int)(Math.abs(seed) % bound);
    }

    //DOUBLE

    public static double randomDouble() {
        seed = scramble(seed, 2);
        return (seed % MOD) / (double) MOD;
    }

    public static double randomDouble(double min, double max) {
        return min + randomDouble() * (max - min);
    }

    //FLOAT

    public static float randomFloat() {
        seed = scramble(seed, 3);
        return (float)((seed % MOD) / (double) MOD);
    }

    // MIXED

    public static Number randomMixed() {
        int choice = randomInt(3);
        return switch (choice) {
            case 0 -> randomInt(100);
            case 1 -> randomDouble();
            default -> randomFloat();
        };
    }

    //CORE SCRAMBLER

    private static long scramble(long x, long n) {
        x ^= (x << 5);
        x ^= (x >> 3);
        x += n * 31;
        return Math.abs(x) % MOD;
    }
}

