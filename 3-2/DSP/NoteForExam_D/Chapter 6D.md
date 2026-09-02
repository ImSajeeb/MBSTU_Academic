# Chapter 6: Discrete Fourier Series (DFS) and Discrete Fourier Transform (DFT)

## 6.1 INTRODUCTION

**DFT**: The Discrete Fourier Transform is obtained by sampling one period of the Fourier transform X(ω) at a finite number of frequency points.

### Why DFT is Important:
1. Allows spectral analysis (frequency content determination)
2. Performs filtering operations in frequency domain
3. Can be processed by digital systems (unlike DTFT which is continuous)

### Difference between DTFT and DFT:

| Feature | DTFT | DFT |
|---------|------|-----|
| Frequency | Continuous | Discrete |
| Summation | Infinite | Finite |
| Periodicity | Periodic with 2π | Periodic with N |
| Processing | Cannot be processed by digital system | Can be processed by digital system |
| X(ω) | Continuous function | Discrete sequence |

---

## 6.2 DISCRETE FOURIER SERIES (DFS)

The Fourier series representation of a periodic discrete-time sequence.

### Exponential Form:

$$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)e^{j2\pi nk/N}$$

$$X(k) = \sum_{n=0}^{N-1} x(n)e^{-j2\pi nk/N}$$

### Using Twiddle Factor:

$$W_N = e^{-j2\pi/N}$$

$$X(k) = \sum_{n=0}^{N-1} x(n)W_N^{nk}$$

$$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{-nk}$$

### Trigonometric Form of DFS:

**For even N:**

$$x(n) = \frac{1}{N}A(0) + \sum_{k=1}^{N/2-1} A(k)\cos\left(\frac{2\pi kn}{N}\right) + \sum_{k=1}^{N/2-1} B(k)\sin\left(\frac{2\pi kn}{N}\right) + \frac{1}{N}A(N/2)\cos(\pi n)$$

**For odd N:**

$$x(n) = \frac{1}{N}A(0) + \sum_{k=1}^{(N-1)/2} A(k)\cos\left(\frac{2\pi kn}{N}\right) + \sum_{k=1}^{(N-1)/2} B(k)\sin\left(\frac{2\pi kn}{N}\right)$$

**Relations between Exponential and Trigonometric Forms:**

- $A(0) = X(0)$
- $A(k) = X(k) + X(N-k)$ for $k = 1, 2, \dots, N/2-1$
- $B(k) = j[X(k) - X(N-k)]$ for $k = 1, 2, \dots, N/2-1$
- $A(N/2) = X(N/2)$ (for even N)

---

## EXAMPLE 6.1: DFS of a periodic sequence

**Given**: x(n) shown in Figure 6.1 with N = 4, where x(0)=0, x(1)=1, x(2)=2, x(3)=3

### Solution:

**Step 1: Identify twiddle factors**

$$W_4 = e^{-j2\pi/4} = e^{-j\pi/2} = -j$$

$$W_4^0 = 1, \quad W_4^1 = -j, \quad W_4^2 = -1, \quad W_4^3 = j$$

**Step 2: Find X(k)**

$$X(0) = \sum_{n=0}^{3} x(n) = 0 + 1 + 2 + 3 = 6$$

$$X(1) = \sum_{n=0}^{3} x(n)W_4^n = 0 + 1(-j) + 2(-1) + 3(j) = -2 + 2j$$

$$X(2) = \sum_{n=0}^{3} x(n)W_4^{2n} = 0 + 1(-1) + 2(1) + 3(-1) = -2$$

$$X(3) = \sum_{n=0}^{3} x(n)W_4^{3n} = 0 + 1(j) + 2(-1) + 3(-j) = -2 - 2j$$

**Step 3: Exponential form**

$$x(n) = \frac{1}{4}\sum_{k=0}^{3} X(k)W_4^{-nk}$$

$$x(n) = \frac{1}{4}[6 - (2 - 2j)W_4^{-n} - 2W_4^{-2n} - (2 + 2j)W_4^{-3n}]$$

$$\boxed{x(n) = \frac{1}{4}[6 - (2 - 2j)W_4^{-n} - 2W_4^{-2n} - (2 + 2j)W_4^{-3n}]}$$

**Step 4: Trigonometric form**

$$A(0) = X(0)/N = 6/4 = 3/2$$

$$A(1) = [X(1) + X(3)]/2 = [(-2+2j) + (-2-2j)]/2 = -2$$

$$B(1) = j[X(1) - X(3)]/2 = j[(-2+2j) - (-2-2j)]/2 = j[4j]/2 = -2$$

$$A(2) = X(2)/2 = -1$$

$$x(n) = \frac{3}{2} - 2\cos\left(\frac{\pi n}{2}\right) - 2\sin\left(\frac{\pi n}{2}\right) - \cos(\pi n)$$

$$\boxed{x(n) = \frac{3}{2} - 2\cos\left(\frac{\pi n}{2}\right) - 2\sin\left(\frac{\pi n}{2}\right) - \cos(\pi n)}$$

---

## 6.3 PROPERTIES OF DFS

### 1. Linearity:
$$DFS[ax_1(n) + bx_2(n)] = aX_1(k) + bX_2(k)$$

### 2. Time Shifting:
$$DFS[x(n-m)] = W_N^{km}X(k)$$

### 3. Frequency Shifting:
$$DFS[W_N^{-ln}x(n)] = X(k+l)$$

### 4. Periodic Convolution:
$$x_1(n) \circledast x_2(n) \leftrightarrow X_1(k)X_2(k)$$

### 5. Symmetry Properties:
- If x(n) is real, then $X(k) = X^*(-k)$
- If x(n) is real and even, then X(k) is real and even
- If x(n) is real and odd, then X(k) is imaginary and odd

---

## 6.4 RELATION BETWEEN DFT AND Z-TRANSFORM

$$X(k) = X(z)|_{z = e^{j2\pi k/N}}$$

**The DFT is the Z-transform evaluated at N equally spaced points on the unit circle.**

---

## EXAMPLE 6.2: DFT of finite sequences

**(a)** $x(n) = \delta(n)$

### Solution:

$$X(k) = \sum_{n=0}^{N-1} \delta(n)e^{-j2\pi nk/N} = 1$$

$$\boxed{X(k) = 1 \text{ for all } k}$$

---

**(b)** $x(n) = \delta(n-n_0)$, $0 < n_0 < N$

### Solution:

$$X(k) = \sum_{n=0}^{N-1} \delta(n-n_0)e^{-j2\pi nk/N} = e^{-j2\pi n_0 k/N}$$

$$\boxed{X(k) = e^{-j2\pi n_0 k/N}}$$

---

**(c)** $x(n) = a^n$, $0 \le n \le N-1$

### Solution:

$$X(k) = \sum_{n=0}^{N-1} a^n e^{-j2\pi nk/N} = \sum_{n=0}^{N-1} (ae^{-j2\pi k/N})^n$$

$$= \frac{1 - a^N e^{-j2\pi k}}{1 - ae^{-j2\pi k/N}} = \frac{1-a^N}{1-ae^{-j2\pi k/N}}$$

$$\boxed{X(k) = \frac{1-a^N}{1-ae^{-j2\pi k/N}}}$$

---

**(d)** $x(n) = \begin{cases} 1 & n \text{ even} \\ 0 & n \text{ odd} \end{cases}$

### Solution:

For N even:

$$X(k) = \sum_{n \text{ even}} e^{-j2\pi nk/N} = \sum_{m=0}^{N/2-1} e^{-j2\pi(2m)k/N} = \sum_{m=0}^{N/2-1} (e^{-j4\pi k/N})^m$$

$$= \frac{1 - e^{-j2\pi k}}{1 - e^{-j4\pi k/N}}$$

If k is even: $X(k) = N/2$
If k is odd: $X(k) = 0$

$$\boxed{X(k) = \begin{cases} N/2 & k \text{ even} \\ 0 & k \text{ odd} \end{cases}}$$

---

## EXAMPLE 6.3: Z-transform sampling and aliasing

**Given**: $x(n) = u(n) - u(n-6)$, sample X(z) at 4 points on unit circle

### Solution:

**Step 1: Find X(z)**

$$X(z) = 1 + z^{-1} + z^{-2} + z^{-3} + z^{-4} + z^{-5}$$

**Step 2: Sample at N = 4 points**

$$X(k) = X(z)|_{z = e^{j2\pi k/4}} = 1 + e^{-j\pi k/2} + e^{-j\pi k} + e^{-j3\pi k/2} + e^{-j2\pi k} + e^{-j5\pi k/2}$$

For k = 0: X(0) = 6
For k = 1: X(1) = 1 - j - 1 + j + 1 - j = 1 - j
For k = 2: X(2) = 1 - 1 + 1 - 1 + 1 - 1 = 0
For k = 3: X(3) = 1 + j - 1 - j + 1 + j = 1 + j

**Step 3: IDFT**

$$x'(n) = \frac{1}{4}[6 + (1-j)(-j)^n + 0 + (1+j)(j)^n]$$

$$x'(0) = \frac{1}{4}[6 + 1 - j + 1 + j] = 2$$

$$x'(1) = \frac{1}{4}[6 + (1-j)(-j) + (1+j)(j)] = 2$$

$$x'(2) = \frac{1}{4}[6 + (1-j)(-1) + (1+j)(-1)] = 1$$

$$x'(3) = \frac{1}{4}[6 + (1-j)(j) + (1+j)(-j)] = 1$$

$$\boxed{x'(n) = \{2, 2, 1, 1\}}$$

**Comment**: Time domain aliasing occurs because N < L (4 < 6). Last 2 samples (L-N = 2) are added to first two samples.

---

## EXAMPLE 6.4: 4-point DFT and IDFT

**(a)** Find 4-point DFT of $x(n) = \{1, -1, 2, -2\}$

### Solution:

$$X(k) = \sum_{n=0}^{3} x(n)e^{-j2\pi nk/4} = \sum_{n=0}^{3} x(n)(-j)^{nk}$$

**k = 0:**
$$X(0) = 1 + (-1) + 2 + (-2) = 0$$

**k = 1:**
$$X(1) = 1 + (-1)(-j) + 2(-1) + (-2)(j) = 1 + j - 2 - 2j = -1 - j$$

**k = 2:**
$$X(2) = 1 + (-1)(-1) + 2(1) + (-2)(-1) = 1 + 1 + 2 + 2 = 6$$

**k = 3:**
$$X(3) = 1 + (-1)(j) + 2(-1) + (-2)(-j) = 1 - j - 2 + 2j = -1 + j$$

$$\boxed{X(k) = \{0, -1-j, 6, -1+j\}}$$

---

**(b)** Find IDFT of $X(k) = \{4, 2, 0, 4\}$

### Solution:

$$x(n) = \frac{1}{4}\sum_{k=0}^{3} X(k)e^{j2\pi nk/4} = \frac{1}{4}\sum_{k=0}^{3} X(k)(j)^{nk}$$

**n = 0:**
$$x(0) = \frac{1}{4}[4 + 2 + 0 + 4] = \frac{10}{4} = 2.5$$

**n = 1:**
$$x(1) = \frac{1}{4}[4 + 2j + 0 + 4(-j)] = \frac{1}{4}[4 + 2j - 4j] = 1 - 0.5j$$

**n = 2:**
$$x(2) = \frac{1}{4}[4 + 2(-1) + 0 + 4(1)] = \frac{1}{4}[4 - 2 + 4] = 1.5$$

**n = 3:**
$$x(3) = \frac{1}{4}[4 + 2(-j) + 0 + 4(j)] = \frac{1}{4}[4 - 2j + 4j] = 1 + 0.5j$$

$$\boxed{x(n) = \{2.5, 1 - 0.5j, 1.5, 1 + 0.5j\}}$$

---

## EXAMPLE 6.5: IDFT of X(k) = {1, 0, 1, 0}

**Given**: $X(k) = \{1, 0, 1, 0\}$

### Solution:

$$x(n) = \frac{1}{4}\sum_{k=0}^{3} X(k)e^{j2\pi nk/4} = \frac{1}{4}\sum_{k=0}^{3} X(k)(j)^{nk}$$

**n = 0:**
$$x(0) = \frac{1}{4}[1 + 0 + 1 + 0] = \frac{2}{4} = 0.5$$

**n = 1:**
$$x(1) = \frac{1}{4}[1 + 0 + 1(j^2) + 0] = \frac{1}{4}[1 + (-1)] = 0$$

**n = 2:**
$$x(2) = \frac{1}{4}[1 + 0 + 1(j^4) + 0] = \frac{1}{4}[1 + 1] = 0.5$$

**n = 3:**
$$x(3) = \frac{1}{4}[1 + 0 + 1(j^6) + 0] = \frac{1}{4}[1 + (-1)] = 0$$

$$\boxed{x(n) = \{0.5, 0, 0.5, 0\}}$$

---

## EXAMPLE 6.6: 3-point and 6-point DFT comparison

**Given**: $x(n) = \{2, 1, 2\}$

### 3-point DFT (N = 3):

$$X(k) = \sum_{n=0}^{2} x(n)e^{-j2\pi nk/3}$$

$$X(0) = 2 + 1 + 2 = 5$$

$$X(1) = 2 + e^{-j2\pi/3} + 2e^{-j4\pi/3} = 0.5 + j0.866$$

$$X(2) = 2 + e^{-j4\pi/3} + 2e^{-j8\pi/3} = 0.5 - j0.866$$

$$\boxed{X_{3pt}(k) = \{5, 0.5 + j0.866, 0.5 - j0.866\}}$$

### 6-point DFT (N = 6, pad with zeros):

$$x(n) = \{2, 1, 2, 0, 0, 0\}$$

$$X(k) = \sum_{n=0}^{5} x(n)e^{-j2\pi nk/6}$$

**k = 0:** X(0) = 5

**k = 1:** X(1) = 2 + e^{-j\pi/3} + 2e^{-j2\pi/3} = 1.5 - j2.598

**k = 2:** X(2) = 2 + e^{-j2\pi/3} + 2e^{-j4\pi/3} = 0.5 + j0.866

**k = 3:** X(3) = 2 + e^{-j\pi} + 2e^{-j2\pi} = 3

**k = 4:** X(4) = 2 + e^{-j4\pi/3} + 2e^{-j8\pi/3} = 0.5 - j0.866

**k = 5:** X(5) = 2 + e^{-j5\pi/3} + 2e^{-j10\pi/3} = 1.5 + j0.866

$$\boxed{X_{6pt}(k) = \{5, 1.5 - j2.598, 0.5 + j0.866, 3, 0.5 - j0.866, 1.5 + j0.866\}}$$

**Observation**: 3-point DFT = X(2k) of 6-point DFT

---

## EXAMPLE 6.7: Faster method using twiddle factors

**Given**: $x(n) = \{1, 0, 1, 2\}$

### Solution:

Using twiddle factors for N = 4:

$$W_4^0 = 1, \quad W_4^1 = -j, \quad W_4^2 = -1, \quad W_4^3 = j$$

$$X(k) = \sum_{n=0}^{3} x(n)W_4^{nk}$$

**k = 0:**
$$X(0) = x(0) + x(1) + x(2) + x(3) = 1 + 0 + 1 + 2 = 4$$

**k = 1:**
$$X(1) = x(0) + x(1)W_4^1 + x(2)W_4^2 + x(3)W_4^3$$
$$= 1 + 0(-j) + 1(-1) + 2(j) = 1 - 1 + 2j = 2j$$

**k = 2:**
$$X(2) = x(0) + x(1)W_4^2 + x(2)W_4^4 + x(3)W_4^6$$
$$= 1 + 0(-1) + 1(1) + 2(-1) = 1 + 1 - 2 = 0$$

**k = 3:**
$$X(3) = x(0) + x(1)W_4^3 + x(2)W_4^6 + x(3)W_4^9$$
$$= 1 + 0(j) + 1(-1) + 2(-j) = 1 - 1 - 2j = -2j$$

$$\boxed{X(k) = \{4, 2j, 0, -2j\}}$$

---

## 6.5 MATRIX FORMULATION OF DFT

$$X = W_N \cdot x$$

Where:

$$W_N = \begin{bmatrix}
1 & 1 & 1 & \cdots & 1 \\
1 & W_N & W_N^2 & \cdots & W_N^{N-1} \\
1 & W_N^2 & W_N^4 & \cdots & W_N^{2(N-1)} \\
\vdots & \vdots & \vdots & \ddots & \vdots \\
1 & W_N^{N-1} & W_N^{2(N-1)} & \cdots & W_N^{(N-1)^2}
\end{bmatrix}$$

---

## 6.6 IDFT FROM MATRIX FORM

$$x = W_N^{-1}X = \frac{1}{N}W_N^*X$$

---

## 6.7 USING DFT TO FIND IDFT

$$x(n) = \frac{1}{N}[DFT\{X^*(k)\}]^*$$

---

## EXAMPLE 6.8: DFT using matrix method

**Given**: $x(n) = \{1, 2, 1, 0\}$

### Solution:

For N = 4, $W_4 = -j$:

$$\begin{bmatrix} X(0) \\ X(1) \\ X(2) \\ X(3) \end{bmatrix} = \begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & -j & -1 & j \\
1 & -1 & 1 & -1 \\
1 & j & -1 & -j
\end{bmatrix} \begin{bmatrix} 1 \\ 2 \\ 1 \\ 0 \end{bmatrix}$$

$$X(0) = 1 + 2 + 1 + 0 = 4$$

$$X(1) = 1 - 2j - 1 + 0 = -2j$$

$$X(2) = 1 - 2 + 1 + 0 = 0$$

$$X(3) = 1 + 2j - 1 + 0 = 2j$$

$$\boxed{X(k) = \{4, -2j, 0, 2j\}}$$

---

## EXAMPLE 6.9: DFT using matrix

**Given**: $x(n) = \{1, -1, 2, -2\}$

### Solution:

$$\begin{bmatrix} X(0) \\ X(1) \\ X(2) \\ X(3) \end{bmatrix} = \begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & -j & -1 & j \\
1 & -1 & 1 & -1 \\
1 & j & -1 & -j
\end{bmatrix} \begin{bmatrix} 1 \\ -1 \\ 2 \\ -2 \end{bmatrix}$$

$$\boxed{X(k) = \{0, -1-j, 6, -1+j\}}$$

---

## EXAMPLE 6.10: 4-point DFT

**Given**: $x(n) = \{1, -2, 3, 2\}$

$$\boxed{X(k) = \{4, -2+4j, 4, -2-4j\}}$$

---

## EXAMPLE 6.11: 8-point DFT using conjugate symmetry

**Given**: $x(n) = \{1, 1, 0, 0, 0, 0, 0, 0\}$

### Solution:

$$X(k) = \sum_{n=0}^{7} x(n)e^{-j2\pi nk/8} = 1 + e^{-j\pi k/4}$$

**Step 1: Compute first 4 values**

$$X(0) = 1 + 1 = 2$$

$$X(1) = 1 + e^{-j\pi/4} = 1 + 0.707 - j0.707 = 1.707 - j0.707$$

$$X(2) = 1 + e^{-j\pi/2} = 1 - j$$

$$X(3) = 1 + e^{-j3\pi/4} = 1 - 0.707 - j0.707 = 0.293 - j0.707$$

$$X(4) = 1 + e^{-j\pi} = 1 - 1 = 0$$

**Step 2: Use conjugate symmetry**

$$X(5) = X^*(3) = 0.293 + j0.707$$

$$X(6) = X^*(2) = 1 + j$$

$$X(7) = X^*(1) = 1.707 + j0.707$$

$$\boxed{X(k) = \{2, 1.707-j0.707, 1-j, 0.293-j0.707, 0, 0.293+j0.707, 1+j, 1.707+j0.707\}}$$

---

## EXAMPLE 6.12: IDFT using DFT

**Given**: $X(k) = \{4, -j2, 0, j2\}$

### Solution:

$$X^*(k) = \{4, j2, 0, -j2\}$$

$$DFT[X^*(k)] = \begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & -j & -1 & j \\
1 & -1 & 1 & -1 \\
1 & j & -1 & -j
\end{bmatrix} \begin{bmatrix} 4 \\ j2 \\ 0 \\ -j2 \end{bmatrix} = \begin{bmatrix} 4 \\ 8 \\ 4 \\ 0 \end{bmatrix}$$

$$x(n) = \frac{1}{4}DFT[X^*(k)] = \{1, 2, 1, 0\}$$

$$\boxed{x(n) = \{1, 2, 1, 0\}}$$

---

## EXAMPLE 6.13: IDFT of X(k) = {1, 0, 1, 0}

**Given**: $X(k) = \{1, 0, 1, 0\}$

### Solution:

Using IDFT formula:

$$x(n) = \frac{1}{4}\sum_{k=0}^{3} X(k)W_4^{-nk}$$

Since $W_4^{-nk} = e^{j2\pi nk/4}$

$$x(0) = \frac{1}{4}[1 + 0 + 1 + 0] = 0.5$$

$$x(1) = \frac{1}{4}[1 + 0 + (-1) + 0] = 0$$

$$x(2) = \frac{1}{4}[1 + 0 + 1 + 0] = 0.5$$

$$x(3) = \frac{1}{4}[1 + 0 + (-1) + 0] = 0$$

$$\boxed{x(n) = \{0.5, 0, 0.5, 0\}}$$

---

## EXAMPLE 6.14: IDFT using matrix method

**Given**: $X(k) = \{1, 0, 1, 0\}$

### Solution:

Using IDFT matrix:

$$x = \frac{1}{4}W_4^*X$$

$$W_4^* = \begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & j & -1 & -j \\
1 & -1 & 1 & -1 \\
1 & -j & -1 & j
\end{bmatrix}$$

$$\begin{bmatrix} x(0) \\ x(1) \\ x(2) \\ x(3) \end{bmatrix} = \frac{1}{4}
\begin{bmatrix}
1 & 1 & 1 & 1 \\
1 & j & -1 & -j \\
1 & -1 & 1 & -1 \\
1 & -j & -1 & j
\end{bmatrix}
\begin{bmatrix} 1 \\ 0 \\ 1 \\ 0 \end{bmatrix}$$

$$x(0) = \frac{1}{4}[1 + 0 + 1 + 0] = 0.5$$
$$x(1) = \frac{1}{4}[1 + 0 - 1 + 0] = 0$$
$$x(2) = \frac{1}{4}[1 + 0 + 1 + 0] = 0.5$$
$$x(3) = \frac{1}{4}[1 + 0 - 1 + 0] = 0$$

$$\boxed{x(n) = \{0.5, 0, 0.5, 0\}}$$

---

## 6.8 PROPERTIES OF DFT

### 1. Periodicity:
$$X(k+N) = X(k), \quad x(n+N) = x(n)$$

### 2. Linearity:
$$DFT[ax_1(n) + bx_2(n)] = aX_1(k) + bX_2(k)$$

### 3. DFT of Even/Odd Sequences:
- Even x(n) → X(k) is real
- Odd x(n) → X(k) is imaginary

### 4. Time Reversal:
$$DFT[x((-n) \text{ mod } N)] = X((-k) \text{ mod } N)$$

### 5. Circular Frequency Shift:
$$DFT[e^{j2\pi ln/N}x(n)] = X((k-l) \text{ mod } N)$$

### 6. Complex Conjugate:
$$DFT[x^*(n)] = X^*(N-k)$$

### 7. Circular Time Shift:
$$DFT[x((n-n_0) \text{ mod } N)] = X(k)e^{-j2\pi n_0k/N}$$

### 8. DFT of Real Sequence:
- $X(k) = X^*(N-k)$
- $X_R(k) = X_R(N-k)$
- $X_I(k) = -X_I(N-k)$

### 9. Multiplication of Two Sequences:
$$DFT[x_1(n)x_2(n)] = \frac{1}{N}[X_1(k) \circledast X_2(k)]$$

### 10. Circular Convolution:
$$DFT[x_1(n) \circledast x_2(n)] = X_1(k)X_2(k)$$

### 11. Parseval's Theorem:
$$\sum_{n=0}^{N-1} |x(n)|^2 = \frac{1}{N}\sum_{k=0}^{N-1} |X(k)|^2$$

### 12. Circular Correlation:
$$DFT[r_{xy}(l)] = X(k)Y^*(k)$$

### 13. Central Ordinate Theorem:
$$X(0) = \sum_{n=0}^{N-1} x(n)$$
$$X(N/2) = \sum_{n=0}^{N-1} (-1)^n x(n)$$

---

## EXAMPLE 6.15: Using conjugate symmetry

**Given**: 12-point DFT of real sequence. First 7 samples:
X(0)=8, X(1)=-1+j2, X(2)=2+j3, X(3)=1-j4, X(4)=-2+j2, X(5)=3+j1, X(6)=-1-j3

### Solution:

Using conjugate symmetry: $X(k) = X^*(N-k) = X^*(12-k)$

$$X(7) = X^*(5) = 3 - j1$$

$$X(8) = X^*(4) = -2 - j2$$

$$X(9) = X^*(3) = 1 + j4$$

$$X(10) = X^*(2) = 2 - j3$$

$$X(11) = X^*(1) = -1 - j2$$

$$\boxed{\text{Remaining samples: } 3-j1, -2-j2, 1+j4, 2-j3, -1-j2}$$

---

## EXAMPLE 6.16: Finding unknown DFT values

**Given**: $X(k) = \{1, A, -1, B, 0, -j2, C, -1+j\}$

### Solution:

Using conjugate symmetry: $X(k) = X^*(8-k)$

$$X(1) = X^*(7) = -1 - j \Rightarrow A = -1 - j$$

$$X(3) = X^*(5) = j2 \Rightarrow B = j2$$

$$X(6) = X^*(2) = -1 \Rightarrow C = -1$$

$$\boxed{A = -1-j, \quad B = j2, \quad C = -1}$$

---

## EXAMPLE 6.17: Using central ordinates

**Given**: $x(n) = \{A, 2, 3, 4, 5, 6, 7, B\}$, X(0)=20, X(4)=0

### Solution:

**Central ordinate theorem**:

$$X(0) = \sum_{n=0}^{7} x(n) = A + 2 + 3 + 4 + 5 + 6 + 7 + B = 20$$

$$A + B + 27 = 20 \Rightarrow A + B = -7$$

**For X(4)**:

$$X(4) = \sum_{n=0}^{7} (-1)^n x(n) = A - 2 + 3 - 4 + 5 - 6 + 7 - B = 0$$

$$A - B + 3 = 0 \Rightarrow A - B = -3$$

Solving:
$$A + B = -7$$
$$A - B = -3$$

Adding: $2A = -10 \Rightarrow A = -5$
Then: $-5 + B = -7 \Rightarrow B = -2$

$$\boxed{A = -5, \quad B = -2}$$

---

## EXAMPLE 6.18: Signal energy from DFT

**Given**: $X(k) = \{1, A, -1, B, -7, -j2, C, -1+j\}$

### Solution:

Using conjugate symmetry:
$A = -1-j, \quad B = j2, \quad C = -1$

Using Parseval's theorem:

$$E = \sum_{n=0}^{7} |x(n)|^2 = \frac{1}{8}\sum_{k=0}^{7} |X(k)|^2$$

$$|X(0)|^2 = 1$$
$$|X(1)|^2 = 1+1 = 2$$
$$|X(2)|^2 = 1$$
$$|X(3)|^2 = 4$$
$$|X(4)|^2 = 49$$
$$|X(5)|^2 = 4$$
$$|X(6)|^2 = 1$$
$$|X(7)|^2 = 1+1 = 2$$

$$E = \frac{1}{8}[1+2+1+4+49+4+1+2] = \frac{64}{8} = 8$$

$$\boxed{E = 8}$$

---

## EXAMPLE 6.19: Real sequence DFT symmetry

**Proof**: If x(n) is real, $X_R(k) = X_R(N-k)$ and $X_I(k) = -X_I(N-k)$

### Solution:

For real x(n): $x^*(n) = x(n)$

$$X(k) = \sum_{n=0}^{N-1} x(n)W_N^{nk}$$

Taking conjugate:
$$X^*(k) = \sum_{n=0}^{N-1} x(n)W_N^{-nk} = X(-k) = X(N-k)$$

Therefore:
$$X(k) = X^*(N-k)$$

So:
$$X_R(k) + jX_I(k) = X_R(N-k) - jX_I(N-k)$$

$$\boxed{X_R(k) = X_R(N-k), \quad X_I(k) = -X_I(N-k)}$$

---

## EXAMPLE 6.20: Parseval's theorem proof

**Statement**: $\sum_{n=0}^{N-1} |x(n)|^2 = \frac{1}{N}\sum_{k=0}^{N-1} |X(k)|^2$

### Proof:

$$\sum_{n=0}^{N-1} |x(n)|^2 = \sum_{n=0}^{N-1} x(n)x^*(n)$$

Using IDFT:

$$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{-nk}$$

$$x^*(n) = \frac{1}{N}\sum_{m=0}^{N-1} X^*(m)W_N^{nm}$$

$$\sum_{n=0}^{N-1} |x(n)|^2 = \frac{1}{N^2}\sum_{n=0}^{N-1}\sum_{k=0}^{N-1}\sum_{m=0}^{N-1} X(k)X^*(m)W_N^{-n(k-m)}$$

Using orthogonality: $\sum_{n=0}^{N-1} W_N^{-n(k-m)} = \begin{cases} N & k = m \\ 0 & k \neq m \end{cases}$

$$\sum_{n=0}^{N-1} |x(n)|^2 = \frac{1}{N^2}\sum_{k=0}^{N-1} X(k)X^*(k)N = \frac{1}{N}\sum_{k=0}^{N-1} |X(k)|^2$$

$$\boxed{\sum_{n=0}^{N-1} |x(n)|^2 = \frac{1}{N}\sum_{k=0}^{N-1} |X(k)|^2}$$

---

## EXAMPLE 6.21: DFT properties without computation

**Given**: $x(n) = \{1, -2, 3, 0, -1, 1\}$, 8-point DFT

**(a)** $X(0)$

$$X(0) = \sum_{n=0}^{5} x(n) = 1 - 2 + 3 + 0 - 1 + 1 = 2$$

$$\boxed{X(0) = 2}$$

---

**(b)** $X(4)$

$$X(4) = \sum_{n=0}^{5} (-1)^n x(n) = 1 + 2 + 3 + 0 - 1 - 1 = 4$$

$$\boxed{X(4) = 4}$$

---

**(c)** $\sum_{k=0}^{5} X(k)$

$$\sum_{k=0}^{5} X(k) = 6x(0) = 6 \cdot 1 = 6$$

$$\boxed{\sum X(k) = 6}$$

---

**(d)** $\sum_{k=0}^{5} |X(k)|^2$

$$\sum_{k=0}^{5} |X(k)|^2 = N\sum_{n=0}^{5} |x(n)|^2 = 6[1 + 4 + 9 + 0 + 1 + 1] = 6 \times 16 = 96$$

$$\boxed{\sum |X(k)|^2 = 96}$$

---

## EXAMPLE 6.22: DFT properties

**Given**: $X(k) = \{4, -j2, 0, j2\}$

**(a)** DFT of x(n-2)

$$DFT[x(n-2)] = e^{-j4\pi k/4}X(k) = e^{-j\pi k}X(k)$$

$$= \{4, 2, 0, -2\}$$

$$\boxed{DFT[x(n-2)] = \{4, 2, 0, -2\}}$$

---

**(b)** DFT of x(-n)

$$DFT[x(-n)] = X(-k) = \{4, -j2, 0, j2\}$$

$$\boxed{DFT[x(-n)] = \{4, -j2, 0, j2\}}$$

---

**(c)** DFT of x*(n)

$$DFT[x^*(n)] = X^*(-k) = \{4, j2, 0, -j2\}$$

---

**(d)** DFT of x²(n)

$$DFT[x^2(n)] = \frac{1}{4}[X(k) \circledast X(k)]$$

---

**(e)** DFT of x(n) � x(n)

$$DFT[x(n) \circledast x(n)] = X(k)X(k) = \{16, -4, 0, -4\}$$

---

**(f)** Signal energy

$$E = \frac{1}{4}\sum_{k=0}^{3} |X(k)|^2 = \frac{1}{4}[16 + 4 + 0 + 4] = 6$$

$$\boxed{E = 6}$$

---

## EXAMPLE 6.23: IDFT properties

**Given**: $IDFT[X(k)] = x(n) = \{1, 2, 1, 0\}$

**(a)** $IDFT[X(k-1)]$

$$IDFT[X(k-1)] = x(n)e^{j2\pi n/4} = x(n)(j)^n$$

$$= \{1(1), 2(j), 1(-1), 0(-j)\} = \{1, 2j, -1, 0\}$$

$$\boxed{IDFT[X(k-1)] = \{1, 2j, -1, 0\}}$$

---

**(b)** $IDFT[X(k) \circledast X(k)]$

$$IDFT[X(k) \circledast X(k)] = N \cdot x(n)x(n)$$

$$= 4\{1, 2, 1, 0\}\{1, 2, 1, 0\} = \{4, 16, 4, 0\}$$

$$\boxed{IDFT[X(k) \circledast X(k)] = \{4, 16, 4, 0\}}$$

---

**(c)** $IDFT[X(k)X(k)]$

$$IDFT[X(k)X(k)] = x(n) \circledast x(n)$$

For $x(n) = \{1, 2, 1, 0\}$:

$$y(0) = 1 + 0 + 1 + 0 = 2$$
$$y(1) = 2 + 2 + 0 + 0 = 4$$
$$y(2) = 1 + 4 + 1 + 0 = 6$$
$$y(3) = 0 + 2 + 2 + 0 = 4$$

$$\boxed{IDFT[X(k)X(k)] = \{2, 4, 6, 4\}}$$

---

**(d)** Signal energy

$$E = \sum_{n=0}^{3} |x(n)|^2 = 1^2 + 2^2 + 1^2 + 0^2 = 6$$

$$\boxed{E = 6}$$

---

## 6.9 LINEAR CONVOLUTION USING DFT

### Procedure:

1. Pad sequences to length $L = N_1 + N_2 - 1$
2. Compute L-point DFTs: X(k) and H(k)
3. Multiply: Y(k) = X(k)H(k)
4. Take IDFT: y(n) = IDFT[Y(k)]

---

## EXAMPLE 6.24: Linear convolution using DFT

**Given**: $x(n) = \{1, 2\}$, $h(n) = \{2, 1\}$

### Solution:

**Step 1: Pad to length N = 2+2-1 = 3**

$$x(n) = \{1, 2, 0\}, \quad h(n) = \{2, 1, 0\}$$

**Step 2: 3-point DFT**

$$X(0) = 1+2+0 = 3$$
$$X(1) = 1 + 2e^{-j2\pi/3} = -j1.732$$
$$X(2) = 1 + 2e^{-j4\pi/3} = j1.732$$

$$H(0) = 2+1+0 = 3$$
$$H(1) = 2 + e^{-j2\pi/3} = 1.5 - j0.866$$
$$H(2) = 2 + e^{-j4\pi/3} = 1.5 + j0.866$$

**Step 3: Multiply**

$$Y(0) = 3 \times 3 = 9$$
$$Y(1) = (-j1.732)(1.5 - j0.866) = -1.5 - j2.598$$
$$Y(2) = (j1.732)(1.5 + j0.866) = -1.5 + j2.598$$

**Step 4: IDFT**

$$y(0) = \frac{1}{3}[9 + (-1.5 - j2.598) + (-1.5 + j2.598)] = 2$$

$$y(1) = \frac{1}{3}[9 + (-1.5 - j2.598)e^{j2\pi/3} + (-1.5 + j2.598)e^{j4\pi/3}] = 5$$

$$y(2) = \frac{1}{3}[9 + (-1.5 - j2.598)e^{j4\pi/3} + (-1.5 + j2.598)e^{j8\pi/3}] = 2$$

$$\boxed{y(n) = \{2, 5, 2\}}$$

---

## EXAMPLE 6.25: Linear convolution using DFT

**Given**: $x(n) = \{1, 0, 2\}$, $h(n) = \{1, 1\}$

### Solution:

**Step 1: Pad to length N = 3+2-1 = 4**

$$x(n) = \{1, 0, 2, 0\}, \quad h(n) = \{1, 1, 0, 0\}$$

**Step 2: 4-point DFT**

$$X(0) = 1+0+2+0 = 3$$
$$X(1) = 1 + 0(-j) + 2(-1) + 0(j) = -1$$
$$X(2) = 1 + 0(-1) + 2(1) + 0(-1) = 3$$
$$X(3) = 1 + 0(j) + 2(-1) + 0(-j) = -1$$

$$H(0) = 1+1+0+0 = 2$$
$$H(1) = 1 + 1(-j) + 0 + 0 = 1 - j$$
$$H(2) = 1 + 1(-1) + 0 + 0 = 0$$
$$H(3) = 1 + 1(j) + 0 + 0 = 1 + j$$

**Step 3: Multiply**

$$Y(0) = 3 \times 2 = 6$$
$$Y(1) = (-1)(1-j) = -1 + j$$
$$Y(2) = 3 \times 0 = 0$$
$$Y(3) = (-1)(1+j) = -1 - j$$

**Step 4: IDFT**

$$y(0) = \frac{1}{4}[6 + (-1+j) + 0 + (-1-j)] = \frac{1}{4}[4] = 1$$

$$y(1) = \frac{1}{4}[6 + (-1+j)(j) + (-1-j)(-j)]$$

$(-1+j)(j) = -j + j^2 = -j - 1 = -1 - j$
$(-1-j)(-j) = j + j^2 = j - 1 = -1 + j$

$$y(1) = \frac{1}{4}[6 - 1 - j - 1 + j] = \frac{1}{4}[4] = 1$$

$$y(2) = \frac{1}{4}[6 + (-1+j)(-1) + (-1-j)(-1)] = \frac{1}{4}[6 + 1 - j + 1 + j] = 2$$

$$y(3) = \frac{1}{4}[6 + (-1+j)(-j) + (-1-j)(j)]$$

$(-1+j)(-j) = j - j^2 = j + 1 = 1 + j$
$(-1-j)(j) = -j - j^2 = -j + 1 = 1 - j$

$$y(3) = \frac{1}{4}[6 + 1 + j + 1 - j] = \frac{1}{4}[8] = 2$$

$$\boxed{y(n) = \{1, 1, 2, 2\}}$$

---

## 6.10 CIRCULAR CONVOLUTION USING DFT

$$y(n) = x_1(n) \circledast x_2(n) = IDFT[X_1(k)X_2(k)]$$

---

## EXAMPLE 6.26: Circular convolution using DFT

**Given**: $x_1(n) = \{1, 2, 1, 2\}$, $x_2(n) = \{4, 3, 2, 1\}$

### Solution:

**Step 1: 4-point DFT**

$$X_1(0) = 6, \quad X_1(1) = 0, \quad X_1(2) = -2, \quad X_1(3) = 0$$

$$X_2(0) = 10, \quad X_2(1) = 2 - 2j, \quad X_2(2) = -2, \quad X_2(3) = 2 + 2j$$

**Step 2: Multiply**

$$Y(0) = 60, \quad Y(1) = 0, \quad Y(2) = 4, \quad Y(3) = 0$$

**Step 3: IDFT**

$$y(0) = \frac{1}{4}[60 + 4] = 16$$
$$y(1) = \frac{1}{4}[60 + 4e^{j\pi}] = \frac{1}{4}[60 - 4] = 14$$
$$y(2) = \frac{1}{4}[60 + 4e^{j2\pi}] = \frac{1}{4}[60 + 4] = 16$$
$$y(3) = \frac{1}{4}[60 + 4e^{j3\pi}] = \frac{1}{4}[60 - 4] = 14$$

$$\boxed{y(n) = \{16, 14, 16, 14\}}$$

---

## EXAMPLE 6.27: Circular convolution using DFT and IDFT

**Given**: $x(n) = \{1, 0.5\}$, $h(n) = \{0.5, 1\}$

### Solution:

**Step 1: 2-point DFT**

$$X(0) = 1 + 0.5 = 1.5$$
$$X(1) = 1 + 0.5(-1) = 0.5$$

$$H(0) = 0.5 + 1 = 1.5$$
$$H(1) = 0.5 + 1(-1) = -0.5$$

**Step 2: Multiply**

$$Y(0) = 1.5 \times 1.5 = 2.25$$
$$Y(1) = 0.5 \times (-0.5) = -0.25$$

**Step 3: IDFT**

$$y(0) = \frac{1}{2}[2.25 + (-0.25)] = 1$$
$$y(1) = \frac{1}{2}[2.25 + (-0.25)(-1)] = \frac{1}{2}[2.25 + 0.25] = 1.25$$

$$\boxed{y(n) = \{1, 1.25\}}$$

---

## 6.11 CONVOLUTION OF LONG SEQUENCES (SECTIONED CONVOLUTION)

### Overlap-Add Method:

1. Split long sequence into blocks of length N
2. Pad each block with N-1 zeros
3. Compute convolution of each block with h(n)
4. Add overlapping samples

### Overlap-Save Method:

1. Split long sequence into overlapping blocks
2. Discard first N-1 samples of each convolution
3. Concatenate remaining samples

---

## EXAMPLE 6.28: Overlap-Add and Overlap-Save

**Given**: $x(n) = \{1, -2, 2, -1, 3, -4, 4, -3\}$, $h(n) = \{1, -1\}$

### Overlap-Add Method:

**Step 1: Split x(n) into blocks of length 2**

$$x_1(n) = \{1, -2\}, \quad x_2(n) = \{2, -1\}, \quad x_3(n) = \{3, -4\}, \quad x_4(n) = \{4, -3\}$$

**Step 2: Convolve each block with h(n)**

$$y_1(n) = \{1, -3, 2\}$$
$$y_2(n) = \{2, -3, 1\}$$
$$y_3(n) = \{3, -7, 4\}$$
$$y_4(n) = \{4, -7, 3\}$$

**Step 3: Add overlapping samples (overlap = N-1 = 1)**

| n | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---|---|---|---|---|---|---|---|---|
| y1 | 1 | -3 | 2 | | | | | | |
| y2 | | 2 | -3 | 1 | | | | | |
| y3 | | | 3 | -7 | 4 | | | | |
| y4 | | | | 4 | -7 | 3 | | | |
| y(n) | 1 | -3 | 4 | -3 | 4 | -7 | 8 | -7 | 3 |

$$\boxed{y(n) = \{1, -3, 4, -3, 4, -7, 8, -7, 3\}}$$

---

### Overlap-Save Method:

**Step 1: Add N-1 = 1 leading zero**

$$x(n) = \{0, 1, -2, 2, -1, 3, -4, 4, -3\}$$

**Step 2: Overlapping blocks of length M = 2N = 4**

$$x_1(n) = \{0, 1, -2, 2\}, \quad x_2(n) = \{2, -1, 3, -4\}, \quad x_3(n) = \{-4, 4, -3, 0\}$$

**Step 3: Periodic convolution of each block with h(n)**

$$y_1(n) = \{-2, 1, -3, 4\}$$
$$y_2(n) = \{6, -3, 4, -7\}$$
$$y_3(n) = \{-4, 8, -7, 3\}$$

**Step 4: Discard first N-1 = 1 sample from each**

$$y(n) = \{1, -3, 4, -3, 4, -7, 8, -7, 3\}$$

---

## EXAMPLE 6.29: Overlap-Add and Overlap-Save

**Given**: $x(n) = \{1, -2, 3, 2, -3, 4, 3, -4\}$, $h(n) = \{1, 2, -1\}$

### Overlap-Add Method:

**Step 1: Split x(n) into blocks of length 3**

$$x_1(n) = \{1, -2, 3\}, \quad x_2(n) = \{2, -3, 4\}, \quad x_3(n) = \{3, -4, 0\}$$

**Step 2: Convolve each block with h(n)**

$$y_1(n) = \{1, 0, -2, 8, -3\}$$
$$y_2(n) = \{2, 1, -4, 11, -4\}$$
$$y_3(n) = \{3, 2, -11, 4, 0\}$$

**Step 3: Add overlapping samples (overlap = N-1 = 2)**

| n | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| y1 | 1 | 0 | -2 | 8 | -3 | | | | | | |
| y2 | | | 2 | 1 | -4 | 11 | -4 | | | | |
| y3 | | | | | 3 | 2 | -11 | 4 | 0 | | |
| y(n) | 1 | 0 | -2 | 10 | -2 | -4 | 14 | -2 | -11 | 4 | 0 |

$$\boxed{y(n) = \{1, 0, -2, 10, -2, -4, 14, -2, -11, 4\}}$$

---

### Overlap-Save Method:

**Step 1: Add N-1 = 2 leading zeros**

$$x(n) = \{0, 0, 1, -2, 3, 2, -3, 4, 3, -4\}$$

**Step 2: Overlapping blocks of length M = 2N = 6**

$$x_1(n) = \{0, 0, 1, -2, 3, 2\}$$
$$x_2(n) = \{3, 2, -3, 4, 3, -4\}$$
$$x_3(n) = \{3, -4, 0, 0, 0, 0\}$$

**Step 3: Periodic convolution of each block with h(n)**

$$y_1(n) = \{1, 0, -2, 8, -3, 0\}$$
$$y_2(n) = \{-8, 12, -2, -4, 14, -2\}$$
$$y_3(n) = \{3, 2, -11, 4, 0, 0\}$$

**Step 4: Discard first N-1 = 2 samples from each**

$$y(n) = \{1, 0, -2, 10, -2, -4, 14, -2, -11, 4\}$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. Define DFT.**

$$X(k) = \sum_{n=0}^{N-1} x(n)e^{-j2\pi nk/N}$$

---

**2. Define IDFT.**

$$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)e^{j2\pi nk/N}$$

---

**3. What is the relation between DFT and Z-transform?**

$$X(k) = X(z)|_{z=e^{j2\pi k/N}}$$

---

**4. What is the twiddle factor?**

$$W_N = e^{-j2\pi/N}$$

---

**5. What are the properties of DFT?**

Periodicity, linearity, time reversal, circular shift, circular convolution, Parseval's theorem.

---

**6. What is circular convolution?**

Convolution performed using modulo-N indexing.

---

**7. How do you find IDFT using DFT?**

$$x(n) = \frac{1}{N}[DFT(X^*(k))]^*$$

---

**8. What is sectioned convolution?**

Method for convolving long sequences by splitting into blocks.

---

**9. What is zero padding?**

Appending zeros to a sequence to increase its length.

---

**10. What are the two methods of sectioned convolution?**

1. Overlap-add method
2. Overlap-save method

---

# FILL IN THE BLANKS

1. The DTFT is a periodic **continuous** function of ω with a period of **2π**.

2. **DFT** allows us to perform frequency analysis on a digital computer.

3. The **DFT** is obtained by sampling one period of the Fourier transform X(ω).

4. The relation between DTFT X(ω) and DFT X(k) is **X(k) = X(ω)|_{ω = 2πk/N}**.

5. The DTFT is nothing but the Z-transform evaluated along the **unit circle** centred at the origin of z-plane.

6. The DFT is nothing but the Z-transform evaluated at a **finite number** of equally spaced points on the **unit circle** centred at the origin of z-plane.

7. The DFT X(k) of a discrete-time sequence x(n) is defined as **$X(k) = \sum_{n=0}^{N-1} x(n)W_N^{nk}$**.

8. The IDFT x(n) of the sequence X(k) is defined as **$x(n) = \frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{-nk}$**.

9. The relation between Z-transform X(z) and DFT X(k) is **$X(k) = X(z)|_{z = e^{j2\pi k/N}}$**.

10. **$W_N = e^{-j2\pi/N}$** is known as the twiddle factor.

11. The DFT supports only **circular** convolution.

12. The technique of convolving two finite duration sequences using DFT techniques is called **fast convolution**.

13. The convolution of two sequences by convolution sum formula is called direct convolution or **slow convolution**.

14. Convolution of long sequences can be done using **sectioned** convolutions.

15. The two methods of sectioned convolution are **overlap-add** method and **overlap-save** method.

16. The central ordinate theorem says that X(0) = **$\sum_{n=0}^{N-1} x(n)$**.

17. The central ordinate theorem says that X(N/2) = **$\sum_{n=0}^{N-1} (-1)^n x(n)$**.

18. In overlap-add method, the last **N-1** samples of each output sequence overlaps with the first **N-1** samples of next section.

19. In overlap-save method, we discard the first **N-1** samples in each convolution.

---

# OBJECTIVE TYPE QUESTIONS

**1. DTFT is a periodic function with a period of**
(a) π (b) 0 (c) 2π (d) infinity

**Answer: (c) 2π**

---

**2. DFT performs filtering operation in**
(a) time domain (b) frequency domain (c) both (d) none

**Answer: (b) frequency domain**

---

**3. The DFT of x(n), i.e. X(k) is defined as**
(a) $X(2πk/N)$ (b) $X(2πk)$ (c) $X(2πn/N)$ (d) $X(2πn)$

**Answer: (a) $X(2πk/N)$**

---

**4. The DTFT is the Z-transform evaluated along the**
(a) imaginary axis (b) real axis (c) unit circle (d) entire z-plane

**Answer: (c) unit circle**

---

**5. DFT {x(n)} is given by X(k) =**
(a) $\sum_{n=0}^{N-1} x(n)W_N^{nk}$ (b) $\sum_{n=0}^{N-1} x(n)W_N^{-nk}$

**Answer: (a) $\sum_{n=0}^{N-1} x(n)W_N^{nk}$**

---

**6. The IDFT of X(k) is given by x(n) =**
(a) $\frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{nk}$ (b) $\frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{-nk}$

**Answer: (a) $\frac{1}{N}\sum_{k=0}^{N-1} X(k)W_N^{nk}$**

---

**7. The twiddle factor is WN =**
(a) $e^{j2\pi/N}$ (b) $e^{j\pi/N}$ (c) $e^{-j2\pi/N}$ (d) $e^{-j\pi/N}$

**Answer: (c) $e^{-j2\pi/N}$**

---

**8. DFT {δ(n)} =**
(a) 2π (b) π (c) 1 (d) 0

**Answer: (c) 1**

---

**9. The IDFT of X(k) is given by x(n) =**
(a) $\frac{1}{N}[DFT\{X^*(k)\}]^*$ (b) $[DFT\{X^*(k)\}]^*$
(c) $\frac{1}{N}DFT\{X(k)\}$ (d) $DFT\{X(k)\}$

**Answer: (a) $\frac{1}{N}[DFT\{X^*(k)\}]^*$**

---

**10. DFT [x1(n) � x2(n)] =**
(a) $\frac{1}{N}X_1(k)X_2(k)$ (b) $NX_1(k)X_2(k)$ (c) $X_1(k)X_2(k)$ (d) $X_1(k) \circledast X_2(k)$

**Answer: (c) $X_1(k)X_2(k)$**

---

**11. DFT [x1(n) x2(n)] =**
(a) $\frac{1}{N}X_1(k) \circledast X_2(k)$ (b) $\frac{1}{N}X_1(k)X_2(k)$
(c) $NX_1(k)X_2(k)$ (d) $NX_1(k) \circledast X_2(k)$

**Answer: (a) $\frac{1}{N}X_1(k) \circledast X_2(k)$**

---

**12. Parseval's theorem states that $\sum_{n=0}^{N-1} x(n)x^*(n) =$**
(a) $\frac{1}{N}\sum_{k=0}^{N-1} X(k)X^*(k)$ (b) $\frac{1}{N}\sum_{k=0}^{N-1} X^*(k)X(k)$

**Answer: (a) $\frac{1}{N}\sum_{k=0}^{N-1} X(k)X^*(k)$**

---

**13. As per central ordinates theorem, if DFT[x(n)] = X(k), then X(0) =**
(a) $\sum_{n=0}^{N-1} x(n)$ (b) $\sum_{n=0}^{N-1} x(0)$ (c) $\sum_{k=0}^{N-1} X(k)$ (d) $\sum_{n=0}^{N-1} x(-n)$

**Answer: (a) $\sum_{n=0}^{N-1} x(n)$**

---

**14. As per central ordinates theorem, X(N/2) =**
(a) $\sum_{n=0}^{N-1} (-1)^n x(n)$ (b) $\sum_{n=0}^{N-1} x(n)$

**Answer: (a) $\sum_{n=0}^{N-1} (-1)^n x(n)$**

---

# PROBLEMS

1. Find the DFT of the sequences:
   (a) $x(n) = \{1, 1, 0, 0\}$
   (b) $x(n) = 1/5, \text{ for } -1 \le n \le 1$, 0 otherwise.

2. Find the DFT of $x(n) = \{1, 1, 2, 2, 3, 3\}$ and determine amplitude and phase spectrum.

3. Find the 4-point DFT of $x(n) = \cos(n\pi/4)$.

4. Find the IDFT of $X(k) = \{1, 2, 3, 4\}$.

5. Find the DFT of $x(n) = \{0.5, 0, 0.5, 0\}$.

6. Find the IDFT of $X(k) = \{3, 2+j, 1, 2-j\}$.

7. Find the DFT of $x(n) = \{2, 0, 0, 1\}$.

8. Find the IDFT of $X(k) = \{2, 1-j, 0, 1+j\}$.

9. Find the DFT of $x(n) = \{2.5, 1-j2, -0.5, 1+j0.5\}$.

10. Find the IDFT of $X(k) = \{0, -1-j, 6, -1+j\}$.

11. Find the IDFT of $X(k) = \{4, -2+j4, 4, -2-j4\}$.

12. Find the IDFT of $X(k) = \{5, 0.5+j0.866, 0.5-j0.866\}$.

13. Let X(k) be a 14-point DFT of a length 14 real sequence x(n). The first 8 samples are given. Determine the remaining samples.

14. The DFT of a real signal is $\{2, 1-j3, A, 2+j1, 0, B, 3-j5, C\}$. Find A, B and C.

15. Let $x(n) = \{2, A, 3, 0, 4, 0, B, 5\}$. If X(0) = 18 and X(4) = 0, find A and B.

16. The DFT of a real signal is $X(k) = \{1, A, -2, B, -5, j3, C, 2-j\}$. What is its signal energy?

17. Consider $x(n) = \{2, -4, 6, 1\}$ with a 4-point DFT X(k). Evaluate:
    (a) X(0) (b) X(2) (c) $\sum_{k=0}^{3} X(k)$ (d) $\sum_{k=0}^{3} |X(k)|^2$

18. If DFT{x(n)} = X(k) = $\{2, -j3, 0, j3\}$, find:
    (a) DFT of x(n-2) (b) DFT of x(-n) (c) DFT of x*(n) (d) DFT of x²(n) (e) DFT of x(n) � x(n) (f) Signal energy

19. If IDFT{X(k)} = x(n) = $\{2, 1, 2, 0\}$, find:
    (a) IDFT{X(k-1)} (b) IDFT{X(k) � X(k)} (c) IDFT{X(k)X(k)} (d) Signal energy

20. Find the linear convolution using DFT:
    (a) $x(n) = \{1, -2, 4\}, h(n) = \{2, 1, 2, 1\}$
    (b) $x(n) = \{2, 3, 4\}, h(n) = \{3, 7, 0, 5\}$
    (c) $x(n) = \{1, 2, 1\}, h(n) = \{2, 0, 1\}$

21. Find the circular convolution using DFT:
    (a) $x(n) = \{1, -1, 1, -1\}, h(n) = \{1, 2, 3, 4\}$
    (b) $x(n) = \{1, 2, 0, 1\}, h(n) = \{2, 2, 1, 1\}$
    (c) $x(n) = \{1, 2, 1, 2\}, h(n) = \{4, 3, 2, 1\}$

22. Find linear convolution using overlap-add and overlap-save:
    (a) $x(n) = \{4, 4, 3, 3, 2, 2, 1, 1\}, h(n) = \{-1, 1\}$
    (b) $x(n) = \{1, 3, 2, 4, 4, 2, 3, 1\}, h(n) = \{1, -1, 1\}$

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| DFT | $X(k) = \sum_n x(n)W_N^{nk}$ |
| IDFT | $x(n) = \frac{1}{N}\sum_k X(k)W_N^{-nk}$ |
| Twiddle factor | $W_N = e^{-j2\pi/N}$ |
| Periodicity | $X(k+N) = X(k)$ |
| Circular shift | $DFT[x(n-n_0)] = X(k)W_N^{kn_0}$ |
| Circular convolution | $y(n) = x_1(n) \circledast x_2(n)$ |
| Parseval | $\sum |x(n)|^2 = \frac{1}{N}\sum |X(k)|^2$ |
| IDFT via DFT | $x(n) = \frac{1}{N}[DFT\{X^*(k)\}]^*$ |
| Conjugate symmetry | $X(k) = X^*(N-k)$ for real x(n) |
| Central ordinate | $X(0) = \sum x(n)$, $X(N/2) = \sum (-1)^n x(n)$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 6.1 | DFS of periodic sequence | ✅ |
| 6.2 | DFT of δ(n), δ(n-n₀), aⁿ, even sequence | ✅ |
| 6.3 | Z-transform sampling and aliasing | ✅ |
| 6.4 | 4-point DFT and IDFT | ✅ |
| 6.5 | IDFT of {1,0,1,0} | ✅ |
| 6.6 | 3-point and 6-point DFT comparison | ✅ |
| 6.7 | Faster method using twiddle factors | ✅ |
| 6.8 | DFT using matrix method | ✅ |
| 6.9 | DFT using matrix | ✅ |
| 6.10 | 4-point DFT | ✅ |
| 6.11 | 8-point DFT using conjugate symmetry | ✅ |
| 6.12 | IDFT using DFT | ✅ |
| 6.13 | IDFT of {1,0,1,0} | ✅ |
| 6.14 | IDFT using matrix method | ✅ |
| 6.15 | Conjugate symmetry | ✅ |
| 6.16 | Finding unknown DFT values | ✅ |
| 6.17 | Central ordinates | ✅ |
| 6.18 | Signal energy from DFT | ✅ |
| 6.19 | Real sequence DFT symmetry proof | ✅ |
| 6.20 | Parseval's theorem proof | ✅ |
| 6.21 | DFT properties without computation | ✅ |
| 6.22 | DFT properties | ✅ |
| 6.23 | IDFT properties | ✅ |
| 6.24 | Linear convolution using DFT | ✅ |
| 6.25 | Linear convolution using DFT | ✅ |
| 6.26 | Circular convolution using DFT | ✅ |
| 6.27 | Circular convolution using DFT and IDFT | ✅ |
| 6.28 | Overlap-Add and Overlap-Save | ✅ |
| 6.29 | Overlap-Add and Overlap-Save | ✅ |

---

## How to Save This File

1. Copy all the text from the code block above
2. Open a text editor (Notepad, VS Code, etc.)
3. Paste the content
4. Save the file with the name `Chapter_6_DFS_and_DFT.md`
5. The file will be properly formatted with headers, tables, equations, and examples