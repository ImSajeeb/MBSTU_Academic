# Custom PRNG in Java

This project implements a novel pseudo-random number generator
without using Java's built-in Random or Math.random().

## Algorithm

Seed-based scrambling using:
- Bitwise left & right shifts
- XOR operations
- Modular folding

Mathematical model:

f(x, n) = ((x << 5) + (x >> 3) + n) mod (2^31 - 1)

## Features

- Generates int, double, float
- Method overloading
- Static methods (ClassName.methodName())
- Mixed-type generation

## Usage

```java
int x = CustomPRNG.randomInt(100);
double d = CustomPRNG.randomDouble(0,1);
float f = CustomPRNG.randomFloat();
