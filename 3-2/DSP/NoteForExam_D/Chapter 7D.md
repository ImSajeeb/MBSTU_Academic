# Chapter 7: Fast Fourier Transform (FFT)

## 7.1 INTRODUCTION

The **Fast Fourier Transform (FFT)** is an algorithm for computing the DFT efficiently with a reduced number of calculations.

### Direct DFT Computation:
- **N² complex multiplications**
- **N(N-1) complex additions**

### FFT Computation (Radix-2):
- **(N/2) log₂ N complex multiplications**
- **N log₂ N complex additions**

### Symmetry Properties exploited by FFT:
1. **Symmetry Property**: $W_N^{k+N/2} = -W_N^k$
2. **Periodicity Property**: $W_N^{k+N} = W_N^k$

### Radix:
The number r in $N = r^m$ is called the **radix** of the FFT algorithm.

### Two Basic FFT Algorithms:
1. **Decimation-in-Time (DIT) FFT**
2. **Decimation-in-Frequency (DIF) FFT**

### Twiddle Factor:
$$W_N = e^{-j2\pi/N}$$

---

## 7.2 DECIMATION-IN-TIME (DIT) RADIX-2 FFT

In the DIT algorithm, the time domain sequence $x(n)$ is decimated into smaller sequences.

### Derivation:
Let $x(n)$ be an N-point sequence where $N = 2^m$.
Split $x(n)$ into even and odd indexed samples:
$$f_1(n) = x(2n) \text{ (even indexed)}$$
$$f_2(n) = x(2n+1) \text{ (odd indexed)}$$

$$X(k) = \sum_{n=0}^{N-1} x(n)W_N^{nk}$$

Breaking into even and odd parts:
$$X(k) = \sum_{n=0}^{N/2-1} x(2n)W_N^{2nk} + \sum_{n=0}^{N/2-1} x(2n+1)W_N^{(2n+1)k}$$

Since $W_N^{2nk} = W_{N/2}^{nk}$:
$$X(k) = \sum_{n=0}^{N/2-1} f_1(n)W_{N/2}^{nk} + W_N^k \sum_{n=0}^{N/2-1} f_2(n)W_{N/2}^{nk}$$
$$X(k) = F_1(k) + W_N^k F_2(k)$$
where $F_1(k)$ and $F_2(k)$ are N/2-point DFTs.

For $k \ge N/2$:
$$X(k+N/2) = F_1(k) - W_N^k F_2(k)$$

### Butterfly Diagram (DIT):
```text
a ──────┬──────► A = a + W_N^k b
        │
b ──────┼──────► B = a - W_N^k b
        │
        └─── W_N^k
```

### Number of Stages:
$$m = \log_2 N$$

### Number of Butterflies per Stage:
$$N/2$$

---

## 7.3 DECIMATION-IN-FREQUENCY (DIF) RADIX-2 FFT

In the DIF algorithm, the frequency domain sequence $X(k)$ is decimated.

### Derivation:
$$X(k) = \sum_{n=0}^{N-1} x(n)W_N^{nk}$$

Split into first and second half:
$$X(k) = \sum_{n=0}^{N/2-1} x(n)W_N^{nk} + \sum_{n=0}^{N/2-1} x(n+N/2)W_N^{(n+N/2)k}$$
$$= \sum_{n=0}^{N/2-1} [x(n) + (-1)^k x(n+N/2)] W_N^{nk}$$

For even k ($k = 2r$):
$$X(2r) = \sum_{n=0}^{N/2-1} [x(n) + x(n+N/2)] W_{N/2}^{nr}$$

For odd k ($k = 2r+1$):
$$X(2r+1) = \sum_{n=0}^{N/2-1} [x(n) - x(n+N/2)] W_N^n W_{N/2}^{nr}$$

### Butterfly Diagram (DIF):
```text
a ──────┬──────► A = a + b
        │
b ──────┼──────► B = (a - b)W_N^n
        │
        └─── W_N^n
```

### Comparison of DIT and DIF:

| Feature | DIT FFT | DIF FFT |
|---------|---------|---------|
| **Input order** | Bit-reversed | Normal |
| **Output order** | Normal | Bit-reversed |
| **Multiplication** | Before add/subtract | After add/subtract |
| **Decimation** | Time domain | Frequency domain |

---

## EXAMPLE 7.1: Butterfly diagram for 8-point FFT (DIT)

**Given**: N = 8 = $2^3$

### Solution:

**Step 1: Bit-reversed input order**
For N = 8, the bit-reversed order is:
$$x_r(n) = \{x(0), x(4), x(2), x(6), x(1), x(5), x(3), x(7)\}$$

**Step 2: Number of stages**
$$m = \log_2 8 = 3 \text{ stages}$$

**Step 3: Number of butterflies per stage**
$$N/2 = 4 \text{ butterflies per stage}$$

**Step 4: Twiddle factors for each stage**
- **Stage 1**: $W_2^0 = 1$ (all butterflies)
- **Stage 2**: $W_4^0 = 1$, $W_4^1 = -j$
- **Stage 3**: $W_8^0, W_8^1, W_8^2, W_8^3$

**Step 5: Output in normal order**
The output $X(k) = \{X(0), X(1), X(2), X(3), X(4), X(5), X(6), X(7)\}$

$$\boxed{\text{3 stages, 4 butterflies per stage}}$$

---

## EXAMPLE 7.2: 8-point DIF FFT

### Solution:

**Step 1: Stage 1 - Convert 8-point to two 4-point sequences**
$$u_1(n) = x(n) + x(n+4), \quad n = 0,1,2,3$$
$$u_2(n) = [x(n) - x(n+4)]W_8^n, \quad n = 0,1,2,3$$

This gives:
$$u_1(0) = x(0)+x(4), \quad u_1(1) = x(1)+x(5)$$
$$u_1(2) = x(2)+x(6), \quad u_1(3) = x(3)+x(7)$$
$$u_2(0) = [x(0)-x(4)]W_8^0, \quad u_2(1) = [x(1)-x(5)]W_8^1$$
$$u_2(2) = [x(2)-x(6)]W_8^2, \quad u_2(3) = [x(3)-x(7)]W_8^3$$

**Step 2: Stage 2 - Convert each 4-point to two 2-point sequences**
$$v_{11}(n) = u_1(n) + u_1(n+2), \quad n = 0,1$$
$$v_{12}(n) = [u_1(n) - u_1(n+2)]W_4^n, \quad n = 0,1$$
$$v_{21}(n) = u_2(n) + u_2(n+2), \quad n = 0,1$$
$$v_{22}(n) = [u_2(n) - u_2(n+2)]W_4^n, \quad n = 0,1$$

**Step 3: Stage 3 - Compute 2-point DFTs**
Each 2-point DFT gives two outputs.

**Step 4: Output in bit-reversed order**
$$X(k) = \{X(0), X(4), X(2), X(6), X(1), X(5), X(3), X(7)\}$$

$$\boxed{\text{Input normal, Output bit-reversed}}$$

---

## EXAMPLE 7.3: 16-point DIT FFT

**(a)** Implement DIT FFT for N = 16

### Solution:

**Step 1: Bit-reversed input order**
For N = 16:
$$x_r(n) = \{x(0), x(8), x(4), x(12), x(2), x(10), x(6), x(14), x(1), x(9), x(5), x(13), x(3), x(11), x(7), x(15)\}$$

**Step 2: Number of stages**
$$m = \log_2 16 = 4 \text{ stages}$$

**Step 3: Butterflies per stage**
$$N/2 = 8 \text{ butterflies per stage}$$

**(b)** Number of non-trivial multiplications:

$$\text{Multiplications} = \frac{N}{2}\log_2 N = \frac{16}{2} \times 4 = 32$$
$$\text{Additions} = N\log_2 N = 16 \times 4 = 64$$

**Direct computation comparison**:
- Multiplications: $N^2 = 256$
- Additions: $N(N-1) = 240$

$$\boxed{\text{FFT: 32 multiplications, 64 additions}}$$
$$\boxed{\text{Direct: 256 multiplications, 240 additions}}$$

---

## EXAMPLE 7.4: Percentage saving in FFT

**Given**: N = 32

### Solution:

**Step 1: Direct DFT**
- Multiplications: $32^2 = 1024$
- Additions: $32 \times 31 = 992$

**Step 2: Radix-2 FFT**
$$m = \log_2 32 = 5 \text{ stages}$$
- Multiplications: $\frac{32}{2} \times 5 = 80$
- Additions: $32 \times 5 = 160$

**Step 3: Percentage saving in multiplications**
$$\text{Saving} = 100 - \frac{80}{1024} \times 100 = 100 - 7.8125 = 92.19\%$$
$$\boxed{92.19\% \text{ saving in multiplications}}$$

**Step 4: Percentage saving in additions**
$$\text{Saving} = 100 - \frac{160}{992} \times 100 = 100 - 16.13 = 83.87\%$$
$$\boxed{83.87\% \text{ saving in additions}}$$

---

## EXAMPLE 7.5: Inverse FFT algorithm

### Solution:

**IDFT formula**:
$$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{-nk}$$

**Procedure to compute IDFT using FFT**:
1. Take conjugate: $X^*(k)$
2. Compute N-point DFT of $X^*(k)$ using FFT
3. Take conjugate of the output
4. Divide by N

$$x(n) = \frac{1}{N}[FFT\{X^*(k)\}]^*$$

**For N = 8 example**:
$$x(n) = \frac{1}{8}[FFT\{X^*(k)\}]^*$$
$$\boxed{x(n) = \frac{1}{N}[FFT\{X^*(k)\}]^*}$$

---

## EXAMPLE 7.6: 4-point DFT using DIT and DIF

**Given**: $x(n) = \{2, 1, 4, 3\}$

### (a) DIT FFT Algorithm

### Solution:

**Step 1: Bit-reversed input**
$$x_r(n) = \{x(0), x(2), x(1), x(3)\} = \{2, 4, 1, 3\}$$

**Step 2: Stage 1 (2-point DFTs)**
**Butterfly 1** (inputs: 2, 4, $W_2^0 = 1$):
$$F_1(0) = 2 + 4 = 6$$
$$F_1(1) = 2 - 4 = -2$$

**Butterfly 2** (inputs: 1, 3, $W_2^0 = 1$):
$$F_2(0) = 1 + 3 = 4$$
$$F_2(1) = 1 - 3 = -2$$

**Step 3: Stage 2 (Combine into 4-point DFT)**
$W_4^0 = 1, W_4^1 = -j$

For k = 0:
$$X(0) = F_1(0) + W_4^0 F_2(0) = 6 + 4 = 10$$
For k = 1:
$$X(1) = F_1(1) + W_4^1 F_2(1) = -2 + (-j)(-2) = -2 + j2$$
For k = 2:
$$X(2) = F_1(0) - W_4^0 F_2(0) = 6 - 4 = 2$$
For k = 3:
$$X(3) = F_1(1) - W_4^1 F_2(1) = -2 - (-j)(-2) = -2 - j2$$

$$\boxed{X(k) = \{10, -2+j2, 2, -2-j2\}}$$

---

### (b) DIF FFT Algorithm

### Solution:

**Step 1: Input in normal order**: $x(n) = \{2, 1, 4, 3\}$

**Step 2: Stage 1**
$$u_1(0) = x(0) + x(2) = 2 + 4 = 6$$
$$u_1(1) = x(1) + x(3) = 1 + 3 = 4$$
$$u_2(0) = [x(0) - x(2)]W_4^0 = (2-4)(1) = -2$$
$$u_2(1) = [x(1) - x(3)]W_4^1 = (1-3)(-j) = j2$$

**Step 3: Stage 2 (2-point DFTs)**
$$X(0) = u_1(0) + u_1(1) = 6 + 4 = 10$$
$$X(2) = u_1(0) - u_1(1) = 6 - 4 = 2$$
$$X(1) = u_2(0) + u_2(1) = -2 + j2$$
$$X(3) = u_2(0) - u_2(1) = -2 - j2$$

$$\boxed{X(k) = \{10, -2+j2, 2, -2-j2\}}$$

### Magnitude and Phase Spectrum:

| k | X(k) | \|X(k)\| | ∠X(k) |
|---|------|--------|--------|
| 0 | 10 | 10 | 0° |
| 1 | -2+j2 | 2.828 | 135° = 2.356 rad |
| 2 | 2 | 2 | 0° |
| 3 | -2-j2 | 2.828 | -135° = -2.356 rad |

$$\boxed{\text{Magnitude: } \{10, 2.828, 2, 2.828\}}$$
$$\boxed{\text{Phase: } \{0°, 135°, 0°, -135°\}}$$

---

## EXAMPLE 7.7: Circular convolution using DFT approach

**Given**: $x_1(n) = \{1, 2, 0, 1\}$, $x_2(n) = \{2, 2, 1, 1\}$

### Solution:

**Step 1: Compute 4-point DFTs using DIT FFT**
**For $x_1(n) = \{1, 2, 0, 1\}$:**
Bit-reversed: $\{x_1(0), x_1(2), x_1(1), x_1(3)\} = \{1, 0, 2, 1\}$
Stage 1:
- Butterfly 1 (1, 0): $A = 1+0=1$, $B = 1-0=1$
- Butterfly 2 (2, 1): $C = 2+1=3$, $D = 2-1=1$

Stage 2:
$$X_1(0) = 1 + 3 = 4$$
$$X_1(1) = 1 + (-j)(1) = 1 - j$$
$$X_1(2) = 1 - 3 = -2$$
$$X_1(3) = 1 - (-j)(1) = 1 + j$$
$$X_1(k) = \{4, 1-j, -2, 1+j\}$$

**For $x_2(n) = \{2, 2, 1, 1\}$:**
Bit-reversed: $\{2, 1, 2, 1\}$
Stage 1:
- Butterfly 1 (2, 2): $A = 2+2=4$, $B = 2-2=0$
- Butterfly 2 (1, 1): $C = 1+1=2$, $D = 1-1=0$

Stage 2:
$$X_2(0) = 4 + 2 = 6$$
$$X_2(1) = 0 + (-j)(0) = 0$$
$$X_2(2) = 4 - 2 = 2$$
$$X_2(3) = 0 - (-j)(0) = 0$$
$$X_2(k) = \{6, 0, 2, 0\}$$

**Step 2: Multiply**
$$Y(k) = X_1(k)X_2(k) = \{24, 0, -4, 0\}$$

**Step 3: IDFT using FFT**
$$Y^*(k) = \{24, 0, -4, 0\}$$
Bit-reversed: $\{24, -4, 0, 0\}$
Stage 1:
- Butterfly 1 (24, 0): $A = 24+0=24$, $B = 24-0=24$
- Butterfly 2 (-4, 0): $C = -4+0=-4$, $D = -4-0=-4$

Stage 2:
$$DFT[Y^*(k)] = \{20, 28, 28, 20\}$$
$$y(n) = \frac{1}{4}\{20, 28, 28, 20\}^* = \{5, 7, 7, 5\}$$

$$\boxed{y(n) = \{5, 7, 7, 5\}}$$

---

## EXAMPLE 7.8: DFT of square wave sequence

**Given**: $x(n) = \begin{cases} 1 & 0 \le n \le N/2-1 \\ -1 & N/2 \le n \le N-1 \end{cases}$, N = 4

### Solution:
$x(n) = \{1, 1, -1, -1\}$
Bit-reversed: $\{x(0), x(2), x(1), x(3)\} = \{1, -1, 1, -1\}$

**Stage 1** ($W_2^0 = 1$):
- Butterfly 1 (1, -1): $A = 1-1=0$, $B = 1-(-1)=2$
- Butterfly 2 (1, -1): $C = 1-1=0$, $D = 1-(-1)=2$

**Stage 2** ($W_4^0 = 1, W_4^1 = -j$):
$$X(0) = 0 + 0 = 0$$
$$X(1) = 2 + (-j)(2) = 2 - j2$$
$$X(2) = 0 - 0 = 0$$
$$X(3) = 2 - (-j)(2) = 2 + j2$$

$$\boxed{X(k) = \{0, 2-j2, 0, 2+j2\}}$$

---

## EXAMPLE 7.9: Response of LTI system using DIT FFT

**Given**: $x(n) = \{2, 2, 2\}$, $h(n) = \{-2, -2\}$

### Solution:

**Step 1: Length of output**
$$L_y = 3 + 2 - 1 = 4$$
Pad to length 4:
$$x(n) = \{2, 2, 2, 0\}$$
$$h(n) = \{-2, -2, 0, 0\}$$

**Step 2: Compute X(k)**
Bit-reversed: $\{2, 2, 2, 0\}$
Stage 1:
- Butterfly 1 (2, 2): $A = 2+2=4$, $B = 2-2=0$
- Butterfly 2 (2, 0): $C = 2+0=2$, $D = 2-0=2$

Stage 2:
$$X(0) = 4 + 2 = 6$$
$$X(1) = 0 + (-j)(2) = -j2$$
$$X(2) = 4 - 2 = 2$$
$$X(3) = 0 - (-j)(2) = j2$$
$$X(k) = \{6, -j2, 2, j2\}$$

**Step 3: Compute H(k)**
Bit-reversed: $\{-2, 0, -2, 0\}$
Stage 1:
- Butterfly 1 (-2, -2): $A = -2-2=-4$, $B = -2-(-2)=0$
- Butterfly 2 (0, 0): $C = 0+0=0$, $D = 0-0=0$

Stage 2:
$$H(0) = -4 + 0 = -4$$
$$H(1) = 0 + (-j)(0) = 0$$
$$H(2) = -4 - 0 = -4$$
$$H(3) = 0 - (-j)(0) = 0$$
$$H(k) = \{-4, 0, -4, 0\}$$

**Step 4: Multiply**
$$Y(k) = X(k)H(k) = \{6, -j2, 2, j2\} \times \{-4, 0, -4, 0\} = \{-24, 0, -8, 0\}$$

**Step 5: IDFT**
$$Y^*(k) = \{-24, 0, -8, 0\}$$
Bit-reversed: $\{-24, -8, 0, 0\}$
Stage 1:
- Butterfly 1 (-24, 0): $A = -24+0=-24$, $B = -24-0=-24$
- Butterfly 2 (-8, 0): $C = -8+0=-8$, $D = -8-0=-8$

Stage 2:
$$DFT[Y^*(k)] = \{-32, -16, -16, -32\}$$
$$y(n) = \frac{1}{4}\{-32, -16, -16, -32\}^* = \{-8, -4, -4, -8\}$$

$$\boxed{y(n) = \{-8, -4, -4, -8\}}$$

---

## EXAMPLE 7.10: Response of LTI system

**Given**: $x(n) = \{1, 0.5, 0\}$, $h(n) = \{0.5, 1\}$

### Solution:

**Step 1: Length of output**
$$L_y = 3 + 2 - 1 = 4$$
Pad to length 4:
$$x(n) = \{1, 0.5, 0, 0\}$$
$$h(n) = \{0.5, 1, 0, 0\}$$

**Step 2: Compute X(k)**
Bit-reversed: $\{1, 0, 0.5, 0\}$
Stage 1:
- Butterfly 1 (1, 0.5): $A = 1+0.5=1.5$, $B = 1-0.5=0.5$
- Butterfly 2 (0, 0): $C = 0+0=0$, $D = 0-0=0$

Stage 2:
$$X(0) = 1.5 + 0 = 1.5$$
$$X(1) = 0.5 + (-j)(0) = 0.5$$
$$X(2) = 1.5 - 0 = 1.5$$
$$X(3) = 0.5 - (-j)(0) = 0.5$$
$$X(k) = \{1.5, 0.5, 1.5, 0.5\}$$

**Step 3: Compute H(k)**
Bit-reversed: $\{0.5, 0, 1, 0\}$
Stage 1:
- Butterfly 1 (0.5, 1): $A = 0.5+1=1.5$, $B = 0.5-1=-0.5$
- Butterfly 2 (0, 0): $C = 0+0=0$, $D = 0-0=0$

Stage 2:
$$H(0) = 1.5 + 0 = 1.5$$
$$H(1) = -0.5 + (-j)(0) = -0.5$$
$$H(2) = 1.5 - 0 = 1.5$$
$$H(3) = -0.5 - (-j)(0) = -0.5$$
$$H(k) = \{1.5, -0.5, 1.5, -0.5\}$$

**Step 4: Multiply**
$$Y(k) = X(k)H(k) = \{2.25, -0.25, 2.25, -0.25\}$$

**Step 5: IDFT**
$$Y^*(k) = \{2.25, -0.25, 2.25, -0.25\}$$
Bit-reversed: $\{2.25, 2.25, -0.25, -0.25\}$
Stage 1:
- Butterfly 1 (2.25, -0.25): $A = 2.25-0.25=2$, $B = 2.25-(-0.25)=2.5$
- Butterfly 2 (2.25, -0.25): $C = 2.25-0.25=2$, $D = 2.25-(-0.25)=2.5$

Stage 2:
$$DFT[Y^*(k)] = \{4, 5, 0, 0\}$$
$$y(n) = \frac{1}{4}\{4, 5, 0, 0\}^* = \{1, 1.25, 0, 0\}$$

$$\boxed{y(n) = \{1, 1.25, 0, 0\}}$$

---

## EXAMPLE 7.11: DFT of impulse sequence using FFT

**Given**: $x(n) = \{1, 0, 0, 0, 0, 0, 0, 0\}$

### (a) Direct computation
$$X(k) = \sum_{n=0}^{7} x(n)e^{-j2\pi nk/8} = 1 \text{ for all } k$$
$$\boxed{X(k) = \{1, 1, 1, 1, 1, 1, 1, 1\}}$$

### (b) Using FFT
Bit-reversed: $\{1, 0, 0, 0, 0, 0, 0, 0\}$
After all butterfly stages, all outputs remain 1.
$$\boxed{X(k) = \{1, 1, 1, 1, 1, 1, 1, 1\}}$$

---

## EXAMPLE 7.12: 8-point DFT using DIT and DIF

**Given**: $x(n) = \{2, 2, 2, 2, 1, 1, 1, 1\}$

### (a) DIT FFT Algorithm

### Solution:

**Step 1: Bit-reversed input**
$$x_r(n) = \{x(0), x(4), x(2), x(6), x(1), x(5), x(3), x(7)\}$$
$$= \{2, 1, 2, 1, 2, 1, 2, 1\}$$

**Step 2: Stage 1** (4 butterflies, $W_2^0 = 1$)
Butterfly 1: (2,1) → (3, 1)
Butterfly 2: (2,1) → (3, 1)
Butterfly 3: (2,1) → (3, 1)
Butterfly 4: (2,1) → (3, 1)

**Step 3: Stage 2** (4 butterflies, $W_4^0 = 1, W_4^1 = -j$)
Butterfly 1: (3,3) with $W_4^0$ → (6, 0)
Butterfly 2: (1,1) with $W_4^1$ → (1+j, 1-j)
Butterfly 3: (3,3) with $W_4^0$ → (6, 0)
Butterfly 4: (1,1) with $W_4^1$ → (1+j, 1-j)

**Step 4: Stage 3** ($W_8^0 = 1, W_8^1 = 0.707-j0.707, W_8^2 = -j, W_8^3 = -0.707-j0.707$)
$$X(0) = 6 + 6 = 12$$
$$X(1) = (1+j) + W_8^1(1-j) = 1 + j + (-j1.414) = 1 - j0.414$$
$$X(2) = 0 + W_8^2(0) = 0$$
$$X(3) = (1-j) + W_8^3(1+j) = 1 - j - j1.414 = 1 - j2.414$$
$$X(4) = 6 - 6 = 0$$
$$X(5) = (1+j) - W_8^1(1-j) = 1 + j + j1.414 = 1 + j2.414$$
$$X(6) = 0 - 0 = 0$$
$$X(7) = (1-j) - W_8^3(1+j) = 1 - j + j1.414 = 1 + j0.414$$

$$\boxed{X(k) = \{12, 1-j0.414, 0, 1-j2.414, 0, 1+j2.414, 0, 1+j0.414\}}$$

---

### (b) DIF FFT Algorithm

### Solution:

**Step 1: Input in normal order**: $\{2, 2, 2, 2, 1, 1, 1, 1\}$

**Step 2: Stage 1**
$$u_1(0) = x(0)+x(4) = 2+1=3$$
$$u_1(1) = x(1)+x(5) = 2+1=3$$
$$u_1(2) = x(2)+x(6) = 2+1=3$$
$$u_1(3) = x(3)+x(7) = 2+1=3$$
$$u_2(0) = [x(0)-x(4)]W_8^0 = (2-1)(1)=1$$
$$u_2(1) = [x(1)-x(5)]W_8^1 = (2-1)(0.707-j0.707) = 0.707-j0.707$$
$$u_2(2) = [x(2)-x(6)]W_8^2 = (2-1)(-j) = -j$$
$$u_2(3) = [x(3)-x(7)]W_8^3 = (2-1)(-0.707-j0.707) = -0.707-j0.707$$

**Step 3: Stage 2 and Stage 3**
Following the DIF butterfly structure gives the same result in bit-reversed order.

$$\boxed{X(k) = \{12, 0, 0, 0, 1-j0.414, 1+j2.414, 1-j2.414, 1+j0.414\}}$$

### Magnitude and Phase Spectrum:

| k | X(k) | \|X(k)\| | ∠X(k) |
|---|------|--------|--------|
| 0 | 12 | 12 | 0° |
| 1 | 1-j0.414 | 1.082 | -22.5° |
| 2 | 0 | 0 | 0 |
| 3 | 1-j2.414 | 2.613 | -67.5° |
| 4 | 0 | 0 | 0 |
| 5 | 1+j2.414 | 2.613 | 67.5° |
| 6 | 0 | 0 | 0 |
| 7 | 1+j0.414 | 1.082 | 22.5° |

---

## EXAMPLE 7.13: 8-point DFT

**Given**: $x(n) = \{2, 1, 2, 1, 2, 1, 2, 1\}$

### Solution:
Bit-reversed: $\{2, 2, 2, 2, 1, 1, 1, 1\}$
Following the same steps as Example 7.12:
$$X(0) = 12$$
$$X(1) = 0$$
$$X(2) = 0$$
$$X(3) = 0$$
$$X(4) = 4$$
$$X(5) = 0$$
$$X(6) = 0$$
$$X(7) = 0$$

$$\boxed{X(k) = \{12, 0, 0, 0, 4, 0, 0, 0\}}$$

---

## EXAMPLE 7.14: DFT of constant sequence

**Given**: $x(n) = \{1, 1, 1, 1, 1, 1, 1, 1\}$

### Solution:
Bit-reversed: $\{1, 1, 1, 1, 1, 1, 1, 1\}$
After all stages:
$$\boxed{X(k) = \{8, 0, 0, 0, 0, 0, 0, 0\}}$$

---

## EXAMPLE 7.15: 8-point DFT using DIT

**Given**: $x(n) = \{1, 2, 3, 4, 4, 3, 2, 1\}$

### Solution:
Bit-reversed: $\{x(0), x(4), x(2), x(6), x(1), x(5), x(3), x(7)\}$
$$= \{1, 4, 3, 2, 2, 3, 4, 1\}$$

**Stage 1** ($W_2^0 = 1$):
Butterfly 1: (1,4) → (5, -3)
Butterfly 2: (3,2) → (5, 1)
Butterfly 3: (2,3) → (5, -1)
Butterfly 4: (4,1) → (5, 3)

**Stage 2** ($W_4^0 = 1, W_4^1 = -j$):
Butterfly 1: (5,5) with $W_4^0$ → (10, 0)
Butterfly 2: (-3,1) with $W_4^1$ → (-3 + j, -3 - j)
Butterfly 3: (5,5) with $W_4^0$ → (10, 0)
Butterfly 4: (-1,3) with $W_4^1$ → (-1 - j3, -1 + j3)

**Stage 3**:
$$X(0) = 10 + 10 = 20$$
$$X(1) = (-3+j) + W_8^1(-3-j) = -5.828 - j2.414$$
$$X(2) = 0 + W_8^2(0) = 0$$
$$X(3) = (-3-j) + W_8^3(-1+j3) = -0.172 - j0.414$$
$$X(4) = 10 - 10 = 0$$
$$X(5) = (-3+j) - W_8^1(-3-j) = -0.172 + j0.414$$
$$X(6) = 0 - 0 = 0$$
$$X(7) = (-3-j) - W_8^3(-1+j3) = -5.828 + j2.414$$

$$\boxed{X(k) = \{20, -5.828-j2.414, 0, -0.172-j0.414, 0, -0.172+j0.414, 0, -5.828+j2.414\}}$$

---

## EXAMPLE 7.16: 8-point DFT

**Given**: $x(n) = \{0, 1, 2, 3, 4, 5, 6, 7\}$

### Solution:
Bit-reversed: $\{x(0), x(4), x(2), x(6), x(1), x(5), x(3), x(7)\}$
$$= \{0, 4, 2, 6, 1, 5, 3, 7\}$$

Following the DIT algorithm:
$$\boxed{X(k) = \{28, -4+j9.656, -4+j4, -4+j1.656, -4, -4-j1.656, -4-j4, -4-j9.656\}}$$

---

## EXAMPLE 7.17: Response of LTI system

**Given**: $x(n) = \{-1, 1, 2, 1, -1\}$, $h(n) = \{-1, 1, -1, 1\}$

### Solution:

**Step 1: Length of output**
$$L_y = 5 + 4 - 1 = 8$$
Pad to length 8:
$$x(n) = \{-1, 1, 2, 1, -1, 0, 0, 0\}$$
$$h(n) = \{-1, 1, -1, 1, 0, 0, 0, 0\}$$

**Step 2: Compute X(k) and H(k) using DIT FFT**
After computation:
$$X(k) = \{2, -(2+j2), -4, (2-j2), -2, (-2+j2), -4, (2+j2)\}$$
$$H(k) = \{0, -1+(1-j2), 0, -1-(1+j2), -4, -1+(1+j2), 0, -1+(-1+j2)\}$$

**Step 3: Multiply**
$$Y(k) = X(k)H(k)$$

**Step 4: IDFT**
$$\boxed{y(n) = \{1, -2, 0, -1, 1, 0, 2, -1\}}$$

---

## EXAMPLE 7.18: IDFT using DIF algorithm

**Given**: $X(k) = \{4, 1-j2.414, 0, 1-j0.414, 0, 1+j0.414, 0, 1+j2.414\}$

### Solution:

**Step 1: Find X*(k)**
$$X^*(k) = \{4, 1+j2.414, 0, 1+j0.414, 0, 1-j0.414, 0, 1-j2.414\}$$

**Step 2: DFT of X*(k) using DIF**
After DIF FFT:
$$8x^*(n) = \{8, 0, 8, 0, 8, 0, 8, 0\}$$

**Step 3: Take conjugate and divide by 8**
$$x(n) = \frac{1}{8}\{8, 0, 8, 0, 8, 0, 8, 0\}^* = \{1, 0, 1, 0, 1, 0, 1, 0\}$$
$$\boxed{x(n) = \{1, 0, 1, 0, 1, 0, 1, 0\}}$$

---

## EXAMPLE 7.19: IDFT using DIT algorithm

**Given**: $X(k) = \{7, -0.707-j0.707, -j, 0.707-j0.707, 1, 0.707+j0.707, j, -0.707+j0.707\}$

### Solution:

**Step 1: Find X*(k)**
$$X^*(k) = \{7, -0.707+j0.707, j, 0.707+j0.707, 1, 0.707-j0.707, -j, -0.707-j0.707\}$$

**Step 2: Bit-reverse X*(k)**
$$\{7, 1, j, -j, -0.707+j0.707, 0.707-j0.707, 0.707+j0.707, -0.707-j0.707\}$$

**Step 3: DIT FFT**
$$8x^*(n) = \{8, 8, 8, 8, 8, 8, 8, 0\}$$

**Step 4: Take conjugate and divide by 8**
$$\boxed{x(n) = \{1, 1, 1, 1, 1, 1, 1, 0\}}$$

---

## EXAMPLE 7.20: IDFT of square wave sequence

**Given**: $X(k) = \{12, 0, 0, 0, 4, 0, 0, 0\}$

### Solution:

**Step 1: X*(k) = X(k)**
$$X^*(k) = \{12, 0, 0, 0, 4, 0, 0, 0\}$$

**Step 2: DIF FFT**
$$8x^*(n) = \{16, 16, 16, 16, 8, 8, 8, 8\}$$

**Step 3: Take conjugate and divide by 8**
$$x(n) = \frac{1}{8}\{16, 8, 16, 8, 16, 8, 16, 8\}$$
$$\boxed{x(n) = \{2, 1, 2, 1, 2, 1, 2, 1\}}$$

---

## EXAMPLE 7.21: IDFT using DIF

**(a)** $X(k) = \{1, 1-j2, -1, 1+j2\}$

### Solution:
**Step 1: X*(k) = {1, 1+j2, -1, 1-j2}**
**Step 2: Bit-reverse: {1, -1, 1+j2, 1-j2}**
**Step 3: DIT FFT**
$$4x^*(n) = \{2, 6, -2, -2\}$$
**Step 4: Divide by 4**
$$x(n) = \{0.5, 1.5, -0.5, -0.5\}$$
$$\boxed{x(n) = \{0.5, 1.5, -0.5, -0.5\}}$$

---

**(b)** $X(k) = \{1, 0, 1, 0\}$

### Solution:
**Step 1: X*(k) = {1, 0, 1, 0}**
**Step 2: DIF FFT**
$$4x^*(n) = \{2, 2, 0, 0\}$$
**Step 3: Divide by 4**
$$\boxed{x(n) = \{0.5, 0, 0.5, 0\}}$$

---

**(c)** $X(k) = \{3, 2+j, 1, 2-j\}$

### Solution:
**Step 1: X*(k) = {3, 2-j, 1, 2+j}**
**Step 2: DIF FFT**
$$4x^*(n) = \{8, 0, 0, 4\}$$
**Step 3: Divide by 4**
$$\boxed{x(n) = \{2, 0, 0, 1\}}$$

---

## 7.4 FFT FOR COMPOSITE N (N = 6, 9, 12, etc.)

When N is not a power of 2, composite radix FFT can be used.

### Radix-3 FFT (N = 3^m):
Split sequence into 3 subsequences:
$$X(k) = X_1(k) + W_N^k X_2(k) + W_N^{2k} X_3(k)$$

### Radix-4 FFT (N = 4^m):
Split sequence into 4 subsequences:
$$X(k) = X_1(k) + W_N^k X_2(k) + W_N^{2k} X_3(k) + W_N^{3k} X_4(k)$$

---

## EXAMPLE 7.22: Radix-3 DIT FFT (N = 9)

### Solution:
Given $N = 9 = 3 \times 3$
Split into 3 sequences of length 3:
$$X_1(k) = x(0) + x(3)W_9^{3k} + x(6)W_9^{6k}$$
$$X_2(k) = x(1) + x(4)W_9^{3k} + x(7)W_9^{6k}$$
$$X_3(k) = x(2) + x(5)W_9^{3k} + x(8)W_9^{6k}$$

$$X(k) = X_1(k) + W_9^k X_2(k) + W_9^{2k} X_3(k)$$

**For $k = 0, 1, 2$:**
$$X(k) = X_1(k) + W_9^k X_2(k) + W_9^{2k} X_3(k)$$

**For $k = 3, 4, 5$** (using periodicity):
$$X(k+3) = X_1(k) + W_9^{k+3} X_2(k) + W_9^{2k+6} X_3(k)$$

**For $k = 6, 7, 8$**:
$$X(k+6) = X_1(k) + W_9^{k+6} X_2(k) + W_9^{2k+12} X_3(k)$$

$$\boxed{\text{Input bit-reversed, Output normal}}$$

---

## EXAMPLE 7.23: Radix-3 DIF FFT (N = 9)

### Solution:
Split into 3 sequences of length 3 in frequency domain:

**For $k = 0, 1, 2$:**
$$X(3k) = \sum_{n=0}^{2} [x(n) + x(n+3) + x(n+6)] W_3^{nk}$$
$$X(3k+1) = \sum_{n=0}^{2} [x(n) + x(n+3)W_9^3 + x(n+6)W_9^6] W_9^n W_3^{nk}$$
$$X(3k+2) = \sum_{n=0}^{2} [x(n) + x(n+3)W_9^6 + x(n+6)W_9^3] W_9^{2n} W_3^{nk}$$

$$\boxed{\text{Input normal, Output bit-reversed}}$$

---

## EXAMPLE 7.24: DIT FFT for N = 6

**(a)** N = 2 × 3

### Solution:
Split into 2 sequences of length 3:
$$X_1(k) = x(0) + x(2)W_6^{2k} + x(4)W_6^{4k}$$
$$X_2(k) = x(1) + x(3)W_6^{2k} + x(5)W_6^{4k}$$
$$X(k) = X_1(k) + W_6^k X_2(k)$$

**For $k = 0, 1, 2$:**
$$X(k) = X_1(k) + W_6^k X_2(k)$$

**For $k = 3, 4, 5$** (using periodicity):
$$X(k+3) = X_1(k) - W_6^k X_2(k)$$

$$\boxed{\text{Input bit-reversed, Output normal}}$$

---

**(b)** N = 3 × 2

### Solution:
Split into 3 sequences of length 2:
$$X_1(k) = x(0) + x(3)W_6^{3k}$$
$$X_2(k) = x(1) + x(4)W_6^{3k}$$
$$X_3(k) = x(2) + x(5)W_6^{3k}$$
$$X(k) = X_1(k) + W_6^k X_2(k) + W_6^{2k} X_3(k)$$

**Example with $x(n) = \{1, 0, 2, 2, 0, 2\}$:**
After computation:
$$\boxed{X(k) = \{7, -1, 1+j3.464, -1, 1-j3.464, -1\}}$$

---

## EXAMPLE 7.25: DIF FFT for N = 6

**(a)** N = 3 × 2

### Solution:
For $k = 0, 1, 2$:
$$X(2k) = \sum_{n=0}^{2} [x(n) + x(n+3)] W_3^{nk}$$
$$X(2k+1) = \sum_{n=0}^{2} [x(n) - x(n+3)] W_6^n W_3^{nk}$$

$$\boxed{\text{Input normal, Output bit-reversed}}$$

---

**(b)** N = 2 × 3

### Solution:
For $k = 0, 1$:
$$X(3k) = \sum_{n=0}^{1} [x(n) + x(n+2) + x(n+4)] W_2^{nk}$$
$$X(3k+1) = \sum_{n=0}^{1} [x(n) + x(n+2)W_6^2 + x(n+4)W_6^4] W_6^n W_2^{nk}$$
$$X(3k+2) = \sum_{n=0}^{1} [x(n) + x(n+2)W_6^4 + x(n+4)W_6^2] W_6^{2n} W_2^{nk}$$

**Example with $x(n) = \{2, 0, -2, 1, 0, -1\}$:**
After computation:
$$\boxed{X(k) = \{0, 1.5+0.866j, 4.5-2.598j, 0, 4.5+2.598j, 1.5-0.866j\}}$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. What is FFT?**
FFT is an algorithm for computing DFT efficiently with a reduced number of calculations.

**2. How many complex multiplications and additions are required for direct DFT?**
- Multiplications: $N^2$
- Additions: $N(N-1)$

**3. How many complex multiplications and additions are required for radix-2 FFT?**
- Multiplications: $\frac{N}{2}\log_2 N$
- Additions: $N\log_2 N$

**4. What are the two basic classes of FFT algorithms?**
1. Decimation-in-Time (DIT) FFT
2. Decimation-in-Frequency (DIF) FFT

**5. What is radix-2?**
When $N = 2^m$, the FFT is called radix-2 FFT.

**6. What is the order of input and output in DIT FFT?**
- Input: Bit-reversed order
- Output: Normal order

**7. What is the order of input and output in DIF FFT?**
- Input: Normal order
- Output: Bit-reversed order

**8. How is IDFT computed using FFT?**
$$x(n) = \frac{1}{N}[FFT\{X^*(k)\}]^*$$

**9. What is a butterfly diagram?**
A signal flow graph resembling a butterfly used to compute 2-point DFTs.

**10. How many butterflies per stage in radix-2 FFT?**
$N/2$ butterflies per stage.

**11. How many stages in radix-2 FFT?**
$\log_2 N$ stages.

**12. What is composite radix FFT?**
FFT used when N is a composite number with more than one prime factor.

**13. What is the twiddle factor?**
$$W_N = e^{-j2\pi/N}$$

**14. What is bit-reversed order?**
The binary pattern of the index is reversed. For example, for N=8:
Normal: 0(000), 1(001), 2(010), 3(011), 4(100), 5(101), 6(110), 7(111)
Bit-reversed: 0(000), 4(100), 2(010), 6(110), 1(001), 5(101), 3(011), 7(111)

---

# FILL IN THE BLANKS

1. The DFT of a single number x(n) = {A} is **{A}**.
2. The DFT of a two sample sequence x(n) = {A, B} is X(k) = **{A+B, A-B}**.
3. The direct computation of an N-point DFT requires **N²** complex multiplications and **N(N-1)** complex additions.
4. The direct computation of DFT requires **4N²** real multiplications and **4N(N-1)** real additions.
5. The computation of DFT by radix-2 FFT requires **(N/2)log₂N** complex multiplications and **Nlog₂N** complex additions.
6. The FFT may be defined as an **algorithm (or method)** for computing DFT.
7. In FFT, the computational efficiency is achieved by adopting a **divide and conquer** approach.
8. The basic FFT algorithms are **DIT FFT** and **DIF FFT**.
9. FFT is a faster method of computation because it exploits the **symmetry** and **periodicity** properties of the phase factor WN.
10. In DFT computation using radix-2 FFT, the value of N should be such that **N = 2^m**.
11. When N = r^m, r is the **radix** and m indicates the number of **stages** of computation.
12. For DIT FFT, the input is in **bit-reversed** order and the output is in **normal** order.
13. For DIF FFT, the input is in **normal** order and the output is in **bit-reversed** order.
14. The IDFT is computed through FFT using the formula **$x(n) = \frac{1}{N}[DFT\{X^*(k)\}]^*$**.
15. The computation of 32-point DFT by radix-2 DIT FFT involves **5** stages of computation.
16. The computation of 64-point DFT by radix-2 DIF FFT involves **6** stages of computation.
17. The signal flow graph for computing DFT by radix-2 FFT is also called the **butterfly** diagram.
18. In radix-2 FFT, **N/2** butterflies per stage are required.
19. The convolution by convolution sum formula is called **slow** convolution.
20. The convolution by FFT is called **fast** convolution.
21. In 16-point DFT by radix-2 FFT, there are **4** stages with **8** butterflies per stage.
22. In DIT algorithm, the sequence **x(n)** is decimated and in DIF algorithm, the sequence **X(k)** is decimated.
23. The N-point DFT can be realized from two **N/2**-point DFTs.
24. In DIT FFT, the phase factors are multiplied **before** the add/subtract operations.
25. In DIF FFT, the phase factors are multiplied **after** the add/subtract operations.

---

# OBJECTIVE TYPE QUESTIONS

**1. The number of complex multiplications involved in the direct computation of 8-point DFT is**
(a) 8 (b) 64 (c) 16 (d) 56
**Answer: (b) 64**

**2. The number of complex additions involved in the direct computation of 8-point DFT is**
(a) 8 (b) 64 (c) 16 (d) 56
**Answer: (d) 56**

**3. The DFT X(k) of a 2 sample sequence x(n) = {4, 2} is**
(a) {6, 2} (b) {4, 2} (c) {8, 4} (d) {2, 1}
**Answer: (a) {6, 2}**

**4. The number of stages in the computation of 1024-point DFT by radix-2 FFT is**
(a) 1024 (b) 32 (c) 8 (d) 10
**Answer: (d) 10** (since $2^{10} = 1024$)

**5. The number of butterflies in each stage of computation of a 64-point radix-2 FFT is**
(a) 64 (b) 32 (c) 16 (d) 8
**Answer: (b) 32** ($N/2 = 32$)

**6. The number of complex multiplications involved in the computation of 256-point DFT by radix-2 FFT is**
(a) 256 (b) 1024 (c) 512 (d) 128
**Answer: (b) 1024** ($(256/2) \times 8 = 1024$)

**7. The number of complex additions involved in the computation of 256-point DFT by radix-2 FFT is**
(a) 256 (b) 2048 (c) 1024 (d) 128
**Answer: (b) 2048** ($256 \times 8 = 2048$)

**8. For the number of stages in the computation of DFT by radix-2 FFT to be 8, how many samples must x(n) have?**
(a) 256 (b) 128 (c) 512 (d) 8
**Answer: (a) 256** ($2^8 = 256$)

**9. For radix-2 FFT, N must be a power of**
(a) N (b) 4 (c) 2 (d) N/2
**Answer: (c) 2**

**10. The IDFT of X(k) is computed by the equation x(n) =**
(a) $\frac{1}{N}[DFT\{X^*(k)\}]^*$ (b) $[DFT\{X^*(k)\}]^*$
(c) $\frac{1}{N}DFT\{X(k)\}$ (d) $DFT\{X(k)\}$
**Answer: (a) $\frac{1}{N}[DFT\{X^*(k)\}]^*$**

---

# PROBLEMS

1. Find the DFT of the following sequences by (a) DIT FFT (b) DIF FFT:
   (a) $x(n) = \{0.5, 1.5, -0.5, -0.5\}$
   (b) $x(n) = \{0.5, 0, 0.5, 0\}$
   (c) $x(n) = \{2, 0, 0, 1\}$
   (d) $x(n) = \{1, 0, -1, 0\}$

2. Find the IDFT of the following sequences by (a) DIT FFT (b) DIF FFT:
   (a) $X(k) = \{10, -2+j2, 2, -2-j2\}$
   (b) $X(k) = \{0, 2-j2, 0, 2+j2\}$
   (c) $X(k) = \{6, -j2, 2, j2\}$
   (d) $X(k) = \{-4, -2+j2, 0, -2-j2\}$

3. Compute the DFT of the following sequences by (a) DIT FFT (b) DIF FFT:
   (a) $x(n) = \{1, -1, -1, -1, 1, 1, 1, -1\}$
   (b) $x(n) = \{0, 1, 2, 3, 0, 0, 0, 0\}$
   (c) $x(n) = n + 1$, where N = 8
   (d) $x(n) = \{1, 1, 1, 1, 0, 0, 0, 0\}$

4. Compute the IDFT of the following sequences by (a) DIT FFT (b) DIF FFT:
   (a) $X(k) = \{1, 1, 1, 1, 1, 1, 1, 1\}$
   (b) $X(k) = \{12, 0, 2-j2, 0, 0, 0, -2+j2, 0\}$
   (c) $X(k) = \{20, -5.828-j2.414, 0, -0.172-j0.414, 0, -0.172+j0.414, 0, -5.828+j2.414\}$
   (d) $X(k) = \{28, -4+j9.656, -4+j4, -4+j1.656, -4, -4-j1.656, -4-j4, -4-j9.656\}$

5. Compute the circular convolution of the following sequences using radix-2 DIT FFT:
   (a) $x(n) = \{1, 0.5\}$, $h(n) = \{0.5, 1\}$
   (b) $x_1(n) = \{1, 2, 1, 2\}$, $x_2(n) = \{4, 3, 2, 1\}$
   (c) $x(n) = \{1, -1, 1, -1\}$, $h(n) = \{1, 2, 3, 4\}$
   (d) $x(n) = \{1, 2, 0, 1\}$, $h(n) = \{2, 2, 1, 1\}$

6. Find the linear convolution (response of the systems) using FFT:
   (a) $x(n) = \{1, 0, 2\}$, $h(n) = \{1, 1\}$
   (b) $x(n) = \{1, 2, 3\}$, $h(n) = \{1, -1\}$
   (c) $x(n) = \{1, 2, 1, 2, 1\}$, $h(n) = \{1, -1, 1, -1\}$
   (d) $x(n) = \{1, -2, 1, -1, 1\}$, $h(n) = \{1, 0, 1, 0\}$

7. For DIT FFT for N = 6, draw the flow diagrams for (a) N = 2×3 and (b) N = 3×2. Evaluate DFT for $x(n) = \{1, 0, 2, 2, 0, 2\}$.

8. For DIF FFT for N = 6, draw the flow diagrams for (a) N = 2×3 and (b) N = 3×2. Evaluate DFT for $x(n) = \{2, 0, -2, 1, 0, -1\}$.

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| Direct DFT Multiplications | $N^2$ |
| Direct DFT Additions | $N(N-1)$ |
| FFT Multiplications | $\frac{N}{2}\log_2 N$ |
| FFT Additions | $N\log_2 N$ |
| DIT FFT | $X(k) = F_1(k) + W_N^kF_2(k)$ |
| DIF FFT | $X(2k) = \sum[x(n)+x(n+N/2)]W_{N/2}^{nk}$ |
| IDFT via FFT | $x(n) = \frac{1}{N}[FFT\{X^*(k)\}]^*$ |
| Radix-3 FFT | $X(k) = X_1(k) + W_N^kX_2(k) + W_N^{2k}X_3(k)$ |
| Radix-4 FFT | $X(k) = X_1(k) + W_N^kX_2(k) + W_N^{2k}X_3(k) + W_N^{3k}X_4(k)$ |
| Twiddle Factor | $W_N = e^{-j2\pi/N}$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 7.1 | 8-point DIT butterfly diagram | ✅ |
| 7.2 | 8-point DIF FFT | ✅ |
| 7.3 | 16-point DIT FFT | ✅ |
| 7.4 | Percentage saving in FFT | ✅ |
| 7.5 | Inverse FFT algorithm | ✅ |
| 7.6 | 4-point DFT using DIT and DIF | ✅ |
| 7.7 | Circular convolution using DFT approach | ✅ |
| 7.8 | DFT of square wave sequence | ✅ |
| 7.9 | Response of LTI system using DIT FFT | ✅ |
| 7.10 | Response of LTI system | ✅ |
| 7.11 | DFT of impulse sequence | ✅ |
| 7.12 | 8-point DFT using DIT and DIF | ✅ |
| 7.13 | 8-point DFT | ✅ |
| 7.14 | DFT of constant sequence | ✅ |
| 7.15 | 8-point DFT using DIT | ✅ |
| 7.16 | 8-point DFT | ✅ |
| 7.17 | Response of LTI system | ✅ |
| 7.18 | IDFT using DIF algorithm | ✅ |
| 7.19 | IDFT using DIT algorithm | ✅ |
| 7.20 | IDFT of square wave sequence | ✅ |
| 7.21 | IDFT using DIF (3 parts) | ✅ |
| 7.22 | Radix-3 DIT FFT (N=9) | ✅ |
| 7.23 | Radix-3 DIF FFT (N=9) | ✅ |
| 7.24 | DIT FFT for N=6 | ✅ |
| 7.25 | DIF FFT for N=6 | ✅ |
```