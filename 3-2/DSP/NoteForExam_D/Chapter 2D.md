# Chapter 2: Discrete Convolution and Correlation

## 2.1 INTRODUCTION

**Convolution**: A mathematical operation equivalent to FIR filtering. It is a method of finding the zero-state response of relaxed LTI systems.

**Key Property**: Convolving two sequences in time domain is equivalent to multiplying the sequences in frequency domain.

**Correlation**: A measure of similarity between two signals.

---

## 2.2 IMPULSE RESPONSE AND CONVOLUTION SUM

If input is unit impulse $x(n) = \delta(n)$, output is **impulse response** $h(n)$:

$$h(n) = T[\delta(n)]$$

Any arbitrary sequence can be represented as:

$$x(n) = \sum_{k=-\infty}^{\infty} x(k)\delta(n-k)$$

**Convolution Sum**:

$$y(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k) = \sum_{k=-\infty}^{\infty} h(k)x(n-k)$$

$$y(n) = x(n) * h(n) = h(n) * x(n)$$

### Limits for Different Cases:

| System Type | Input Type | Convolution Limits |
|-------------|------------|-------------------|
| Non-causal | Non-causal | $y(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k)$ |
| Non-causal | Causal | $y(n) = \sum_{k=0}^{\infty} x(k)h(n-k)$ |
| Causal | Non-causal | $y(n) = \sum_{k=-\infty}^{n} x(k)h(n-k)$ |
| Causal | Causal | $y(n) = \sum_{k=0}^{n} x(k)h(n-k)$ |

### Properties of Convolution:

1. **Commutative**: $x(n) * h(n) = h(n) * x(n)$
2. **Associative**: $[x(n) * h_1(n)] * h_2(n) = x(n) * [h_1(n) * h_2(n)]$
3. **Distributive**: $x(n) * [h_1(n) + h_2(n)] = x(n) * h_1(n) + x(n) * h_2(n)$
4. **Shifting**: If $x(n) * h(n) = y(n)$, then $x(n-k) * h(n-m) = y(n-k-m)$
5. **Convolution with impulse**: $x(n) * \delta(n) = x(n)$

---

## EXAMPLE 2.1: Convolution of two finite duration sequences

**Given**: $x(n) = b^n u(n)$, $h(n) = a^n u(n)$

**Find**: (i) When $a \neq b$ (ii) When $a = b$

### Solution:

Both sequences are causal. For causal input and causal system:

$$y(n) = \sum_{k=0}^{n} x(k)h(n-k)$$

**(i) When $a \neq b$:**

$$y(n) = \sum_{k=0}^{n} b^k a^{n-k} = a^n \sum_{k=0}^{n} \left(\frac{b}{a}\right)^k$$

Using geometric series formula $\sum_{k=0}^{n} r^k = \frac{1-r^{n+1}}{1-r}$:

$$y(n) = a^n \left[\frac{1 - (b/a)^{n+1}}{1 - (b/a)}\right]$$

$$\boxed{y(n) = \frac{a^{n+1} - b^{n+1}}{a-b}, \quad n \ge 0}$$

**(ii) When $a = b$:**

$$y(n) = \sum_{k=0}^{n} a^k a^{n-k} = a^n \sum_{k=0}^{n} 1 = a^n(n+1)$$

$$\boxed{y(n) = a^n(n+1), \quad n \ge 0}$$

---

## EXAMPLE 2.2: Convolution with finite duration input

**Given**: $x(n) = n + 3$ for $0 \le n \le 2$, $h(n) = a^n u(n)$

**Find**: $y(n)$

### Solution:

Here $h(n)$ is causal and $x(n)$ is a finite duration sequence (zero for n > 2).

$$y(n) = \sum_{k=0}^{2} x(k)h(n-k)$$

**Step 1: Identify values of x(k)**

For k = 0: x(0) = 0 + 3 = 3
For k = 1: x(1) = 1 + 3 = 4
For k = 2: x(2) = 2 + 3 = 5

**Step 2: Substitute into convolution sum**

$$y(n) = 3 \cdot h(n) + 4 \cdot h(n-1) + 5 \cdot h(n-2)$$

**Step 3: Substitute h(n) = a^n u(n)**

$$y(n) = 3a^n u(n) + 4a^{n-1}u(n-1) + 5a^{n-2}u(n-2)$$

$$\boxed{y(n) = 3a^n u(n) + 4a^{n-1}u(n-1) + 5a^{n-2}u(n-2)}$$

**For explicit values:**
- n = 0: y(0) = 3
- n = 1: y(1) = 3a + 4
- n = 2: y(2) = 3a² + 4a + 5
- n ≥ 3: y(n) = 3a^n + 4a^{n-1} + 5a^{n-2}

---

## EXAMPLE 2.3: Response of system

**Given**: $x(n) = 3^n u(n)$, $h(n) = \left(\frac{1}{3}\right)^n u(n)$

### Solution:

Both are causal, so:

$$y(n) = \sum_{k=0}^{n} x(k)h(n-k)$$

**Step 1: Substitute x(k) and h(n-k)**

$$y(n) = \sum_{k=0}^{n} 3^k \left(\frac{1}{3}\right)^{n-k}$$

**Step 2: Simplify using exponent rules**

$$\left(\frac{1}{3}\right)^{n-k} = 3^{-(n-k)} = 3^{k-n}$$

$$y(n) = \sum_{k=0}^{n} 3^k \cdot 3^{k-n} = 3^{-n} \sum_{k=0}^{n} 3^{2k} = 3^{-n} \sum_{k=0}^{n} 9^k$$

**Step 3: Use geometric series formula**

$$\sum_{k=0}^{n} 9^k = \frac{1-9^{n+1}}{1-9} = \frac{9^{n+1}-1}{8}$$

$$y(n) = 3^{-n} \cdot \frac{9^{n+1}-1}{8}$$

$$\boxed{y(n) = \frac{9^{n+1} - 1}{8 \cdot 3^n}}$$

---

## EXAMPLE 2.4: Response of system

**Given**: $x(n) = 3^n u(n)$, $h(n) = 2^n u(n)$

### Solution:

$$y(n) = \sum_{k=0}^{n} 3^k \cdot 2^{n-k}$$

**Step 1: Factor out $2^n$**

$$y(n) = 2^n \sum_{k=0}^{n} \left(\frac{3}{2}\right)^k$$

**Step 2: Geometric series**

$$\sum_{k=0}^{n} \left(\frac{3}{2}\right)^k = \frac{1 - (3/2)^{n+1}}{1 - 3/2} = 2\left[(3/2)^{n+1} - 1\right]$$

**Step 3: Substitute**

$$y(n) = 2^n \cdot 2\left[(3/2)^{n+1} - 1\right] = 2^{n+1}\left[(3/2)^{n+1} - 1\right]$$

$$\boxed{y(n) = 2^{n+1}\left[\left(\frac{3}{2}\right)^{n+1} - 1\right]}$$

---

## EXAMPLE 2.5: Convolution with cosine

**Given**: $x(n) = \cos n\pi \, u(n) = (-1)^n u(n)$, $h(n) = \left(\frac{1}{2}\right)^n u(n)$

### Solution:

$$y(n) = \sum_{k=0}^{n} (-1)^k \left(\frac{1}{2}\right)^{n-k}$$

**Step 1: Factor out $(1/2)^n$**

$$y(n) = \left(\frac{1}{2}\right)^n \sum_{k=0}^{n} (-1)^k \cdot 2^k = \left(\frac{1}{2}\right)^n \sum_{k=0}^{n} (-2)^k$$

**Step 2: Geometric series with r = -2**

$$\sum_{k=0}^{n} (-2)^k = \frac{1-(-2)^{n+1}}{1-(-2)} = \frac{1+2(-2)^n}{3}$$

$$y(n) = \left(\frac{1}{2}\right)^n \frac{1+2(-2)^n}{3}$$

$$\boxed{y(n) = \frac{1}{3}\left(\frac{1}{2}\right)^n u(n) + \frac{2}{3}(-1)^n u(n)}$$

---

## EXAMPLE 2.6: Convolution of step sequences

**Given**: $x(n) = u(n)$, $h(n) = u(n-3)$

### Solution:

$$y(n) = \sum_{k=-\infty}^{\infty} u(k)u(n-3-k)$$

**Step 1: Determine limits**

- $u(k) = 1$ for $k \ge 0$
- $u(n-3-k) = 1$ for $n-3-k \ge 0$, i.e., $k \le n-3$

So the overlap is from $k = 0$ to $k = n-3$.

**Step 2: Sum**

$$y(n) = \sum_{k=0}^{n-3} 1 = n-2$$

$$\boxed{y(n) = n-2}$$

---

## EXAMPLE 2.7: System with unit sample response

**Given**: $h(n) = a^n u(n)$, $x(n) = u(n) - u(n-N)$

### Solution:

**Step 1: Identify x(k)**

$x(k) = 1$ for $0 \le k \le N-1$, $x(k) = 0$ otherwise.

**For $n < 0$:**

No overlap between x(k) and h(n-k). $y(n) = 0$.

**For $0 \le n < N$:**

$$y(n) = \sum_{k=0}^{n} a^{n-k} = a^n \frac{1-a^{-(n+1)}}{1-a^{-1}} = \frac{1-a^{n+1}}{1-a}$$

**For $n \ge N$:**

$$y(n) = \sum_{k=0}^{N-1} a^{n-k} = a^{n-(N-1)} \frac{1-a^N}{1-a}$$

$$\boxed{y(n) = \begin{cases} 0 & n < 0 \\ \frac{1-a^{n+1}}{1-a} & 0 \le n < N \\ a^{n-(N-1)}\frac{1-a^N}{1-a} & n \ge N \end{cases}}$$

---

## EXAMPLE 2.8: Convolution of exponential and finite step

**Given**: $x(n) = \left(\frac{1}{2}\right)^n u(n)$, $h(n) = u(n) - u(n-10)$

### Solution:

$h(n)$ is 1 for $0 \le n \le 9$, 0 otherwise.

**For $0 \le n \le 9$:**

$$y(n) = \sum_{k=0}^{n} \left(\frac{1}{2}\right)^k = \frac{1 - (1/2)^{n+1}}{1-1/2} = 2\left[1 - \left(\frac{1}{2}\right)^{n+1}\right]$$

$$= 2 - \left(\frac{1}{2}\right)^n$$

**For $n \ge 10$:**

$$y(n) = \sum_{k=n-9}^{n} \left(\frac{1}{2}\right)^k$$

$$= \left(\frac{1}{2}\right)^{n-9} \left[1 + \frac{1}{2} + \left(\frac{1}{2}\right)^2 + \dots + \left(\frac{1}{2}\right)^9\right]$$

$$= \left(\frac{1}{2}\right)^n [2^{10} - 1]$$

$$\boxed{y(n) = \begin{cases} 2 - (1/2)^n & 0 \le n \le 9 \\ [(2^{10} - 1)/2^n] & n \ge 10 \end{cases}}$$

---

## EXAMPLE 2.9: Convolution of non-causal sequences

**Given**: $x(n) = \left(\frac{1}{3}\right)^{-n} u(-n-1)$, $h(n) = u(n-1)$

### Solution:

**Step 1: Analyze x(k)**

$x(k) = \left(\frac{1}{3}\right)^{-k} = 3^k$ for $k \le -1$, 0 otherwise.

**Step 2: Analyze h(n-k)**

$h(n-k) = 1$ for $n-k \ge 1$, i.e., $k \le n-1$.

**For $n < 0$:**

The overlap is from $k = -\infty$ to $k = n-1$.

$$y(n) = \sum_{k=-\infty}^{n-1} 3^k = 3^{n-1} \left[1 + \frac{1}{3} + \left(\frac{1}{3}\right)^2 + \dots\right]$$

$$= 3^{n-1} \cdot \frac{1}{1-1/3} = \frac{3^n}{2} = 0.5(3)^n$$

**For $n \ge 0$:**

The overlap is from $k = -\infty$ to $k = -1$.

$$y(n) = \sum_{k=-\infty}^{-1} 3^k = \sum_{k=1}^{\infty} \left(\frac{1}{3}\right)^k = \frac{1/3}{1-1/3} = \frac{1}{2} = 0.5$$

$$\boxed{y(n) = \begin{cases} 0.5(3)^n & n < 0 \\ 0.5 & n \ge 0 \end{cases}}$$

---

## 2.3 UNIT STEP RESPONSE

The step response is the running sum of impulse response:

$$s(n) = h(n) * u(n) = \sum_{k=-\infty}^{n} h(k)$$

For causal system:
$$s(n) = \sum_{k=0}^{n} h(k)$$

---

## EXAMPLE 2.10: Evaluate step response

**(a)** $h(n) = \delta(n) - \delta(n-2)$

### Solution:

$$s(n) = [\delta(n) - \delta(n-2)] * u(n) = u(n) - u(n-2)$$

$$\boxed{s(n) = u(n) - u(n-2)}$$

---

**(b)** $h(n) = \left(\frac{1}{4}\right)^n u(n)$

### Solution:

$$s(n) = \sum_{k=0}^{n} \left(\frac{1}{4}\right)^{n-k} = \left(\frac{1}{4}\right)^n \sum_{k=0}^{n} 4^k$$

$$= \left(\frac{1}{4}\right)^n \cdot \frac{1-4^{n+1}}{1-4} = \frac{4 - (1/4)^n}{3}$$

$$\boxed{s(n) = \frac{4 - (1/4)^n}{3}}$$

---

**(c)** $h(n) = n u(n)$

### Solution:

$$s(n) = \sum_{k=0}^{n} k = \frac{n(n+1)}{2}$$

$$\boxed{s(n) = \frac{n(n+1)}{2}}$$

---

**(d)** $h(n) = u(n)$

### Solution:

$$s(n) = \sum_{k=0}^{n} 1 = n+1$$

$$\boxed{s(n) = n+1}$$

---

## 2.4 CONVOLUTION OF FINITE SEQUENCES

**Rules**:
1. Starting index of y(n) = sum of starting indices of x(n) and h(n)
2. Ending index of y(n) = sum of ending indices of x(n) and h(n)
3. Length: $L_y = L_x + L_h - 1$

---

## 2.5 METHODS TO COMPUTE CONVOLUTION SUM

### Method 1: Graphical Method

1. Choose starting time $n = n_1 + n_2$
2. Express sequences in terms of index $k$
3. Fold $h(k)$ to get $h(-k)$ and shift by $n$ to get $h(n-k)$
4. Multiply $x(k)$ and $h(n-k)$ element by element and sum products
5. Increment $n$, shift and repeat

### Method 2: Tabular Array

Tabulate $x(k)$ and shifted versions of $h(k)$. Sum products at each shift.

### Method 3: Tabular Method

Multiply each sample of $h(n)$ with samples of $x(n)$, tabulate, group diagonally, sum.

### Method 4: Matrices Method

Form matrix using $h(n)$ and column matrix using $x(n)$, multiply.

---

## EXAMPLE 2.11: Determine convolution sum

**Given**: $x(n) = \{4, 2, 1, 3\}$, $h(n) = \{1, 2, 2, 1\}$ with $\uparrow$ at $n=0$ for x(n), and $n=-1$ for h(n)

### Solution:

$x(n)$ starts at $n_1 = 0$, $h(n)$ starts at $n_2 = -1$.

Starting sample of $y(n)$: $n = 0 + (-1) = -1$

Length: $L_y = 4 + 4 - 1 = 7$

$y(n)$ exists from $n = -1$ to $n = 5$.

### Method 1: Graphical Method

$$y(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k)$$

For $n = -1$: $y(-1) = 4 \cdot 1 = 4$

For $n = 0$: $y(0) = 4 \cdot 2 + 2 \cdot 1 = 10$

For $n = 1$: $y(1) = 4 \cdot 2 + 2 \cdot 2 + 1 \cdot 1 = 13$

For $n = 2$: $y(2) = 4 \cdot 1 + 2 \cdot 2 + 1 \cdot 2 + 3 \cdot 1 = 13$

For $n = 3$: $y(3) = 2 \cdot 1 + 1 \cdot 2 + 3 \cdot 2 = 10$

For $n = 4$: $y(4) = 1 \cdot 1 + 3 \cdot 2 = 7$

For $n = 5$: $y(5) = 3 \cdot 1 = 3$

$$\boxed{y(n) = \{4, 10, 13, 13, 10, 7, 3\}}$$

**Check**: $\sum x(n) \cdot \sum h(n) = \sum y(n)$

$\sum x(n) = 4+2+1+3 = 10$, $\sum h(n) = 1+2+2+1 = 6$, $\sum y(n) = 60$

$10 \times 6 = 60$ ✓

### Method 3: Tabular Method

| | x(0)=4 | x(1)=2 | x(2)=1 | x(3)=3 |
|---|---|---|---|---|
| h(0)=1 | 4 | 2 | 1 | 3 |
| h(1)=2 | 8 | 4 | 2 | 6 |
| h(2)=2 | 8 | 4 | 2 | 6 |
| h(3)=1 | 4 | 2 | 1 | 3 |

Sum diagonally:
$$y(n) = 4, 8+2, 8+4+1, 4+4+2+3, 2+2+6, 1+6, 3$$
$$= \{4, 10, 13, 13, 10, 7, 3\}$$

---

## EXAMPLE 2.12: Convolution of sequences

**Given**: $x(n) = \{2, 3, 2, 2\}$ with $\uparrow$ at $n=-2$, $h(n) = \{1, -2, 3, -1\}$ with $\uparrow$ at $n=0$

### Solution:

$x(n)$ starts at $n_1 = -2$, $h(n)$ starts at $n_2 = 0$.

Starting sample: $n = -2 + 0 = -2$

Length: $L_y = 4 + 4 - 1 = 7$

$y(n)$ exists from $n = -2$ to $n = 4$.

### Graphical Method:

$y(-2) = 2 \cdot 1 = 2$

$y(-1) = 2(-2) + 3 \cdot 1 = -1$

$y(0) = 2(3) + 3(-2) + 2 \cdot 1 = 2$

$y(1) = 2(-1) + 3(3) + 2(-2) + 2 \cdot 1 = 5$

$y(2) = 3(-1) + 2(3) + 2(-2) = -1$

$y(3) = 2(-1) + 2(3) = 4$

$y(4) = 2(-1) = -2$

$$\boxed{y(n) = \{2, -1, 2, 5, -1, 4, -2\}}$$

---

## EXAMPLE 2.13: Convolution using tabular method

**Given**: $x(n) = 3\delta(n+1) - 2\delta(n) + \delta(n-1) + 4\delta(n-2)$

$h(n) = 2\delta(n-1) + 5\delta(n-2) + 3\delta(n-3)$

### Solution:

$x(n) = \{3, -2, 1, 4\}$ starts at $n_1 = -1$

$h(n) = \{2, 5, 3\}$ starts at $n_2 = 1$

$y(n)$ starts at $n = -1 + 1 = 0$

Tabular method:

| | 3 | -2 | 1 | 4 |
|---|---|---|---|---|
| 2 | 6 | -4 | 2 | 8 |
| 5 | 15 | -10 | 5 | 20 |
| 3 | 9 | -6 | 3 | 12 |

$$y(n) = \{6, 11, 1, 7, 23, 12\}$$

---

## EXAMPLE 2.14: Convolution of $u(n) * u(n-2)$

### Solution:

$$y(n) = \sum_{k=-\infty}^{\infty} u(k)u[(n-2)-k] = \sum_{k=0}^{n-2} 1 = n-1$$

or $$y(n) = \sum_{k=2}^{n} 1 = n-1$$

$$\boxed{y(n) = n-1}$$

---

## EXAMPLE 2.15: Convolution

**(a)** $y(n) = \sin\left(\frac{n\pi}{2}\right)u(n) * u(n-2)$

### Solution:

$x(n) = \sin\left(\frac{n\pi}{2}\right)u(n)$, $h(n) = u(n-2)$

$$y(n) = \sum_{k=0}^{n-2} \sin\left(\frac{k\pi}{2}\right)$$

**Values of $\sin(k\pi/2)$:**
k = 0: 0, k = 1: 1, k = 2: 0, k = 3: -1, k = 4: 0, k = 5: 1

**Calculate y(n):**

n = 0: y(0) = 0
n = 1: y(1) = 0
n = 2: y(2) = 0
n = 3: y(3) = 1
n = 4: y(4) = 1
n = 5: y(5) = 0
n = 6: y(6) = 0

$$\boxed{y(n) = \{0, 0, 0, 1, 1, 0, 0, 1, 1, \dots\}}$$

---

**(b)** $y(n) = 3^n u(-n+3) * u(n-2)$

### Solution:

**For $n \le 5$:**

$$y(n) = \sum_{k=-\infty}^{n-2} 3^k = 3^{n-2} \left[1 + \frac{1}{3} + \left(\frac{1}{3}\right)^2 + \dots\right]$$

$$= 3^{n-2} \cdot \frac{1}{1-1/3} = \frac{3^{n-1}}{2}$$

**For $n > 5$:**

$$y(n) = \sum_{k=-\infty}^{3} 3^k = 27 \left[1 + \frac{1}{3} + \frac{1}{9} + \dots\right] = 27 \cdot \frac{3}{2} = 40.5$$

$$\boxed{y(n) = \begin{cases} \frac{3^{n-1}}{2} & n \le 5 \\ 40.5 & n > 5 \end{cases}}$$

---

## EXAMPLE 2.16: FIR filter response

**(a)** $h(n) = \{2, 1, 2, 1\}$, $x(n) = \{1, -2, 4\}$ (both start at n=0)

### Solution:

$L_y = 3 + 4 - 1 = 6$

| n | 0 | 1 | 2 | 3 | 4 | 5 |
|---|---|---|---|---|---|---|
| x(0)=1 | 2 | 1 | 2 | 1 | | |
| x(1)=-2 | | -4 | -2 | -4 | -2 | |
| x(2)=4 | | | 8 | 4 | 8 | 4 |
| y(n) | 2 | -3 | 8 | 1 | 6 | 4 |

$$\boxed{y(n) = \{2, -3, 8, 1, 6, 4\}}$$

---

**(b)** $h(n) = \{3, 7, 0, 5\}$, $x(n) = \{2, 3, 4\}$

### Solution:

Using polynomial multiplication:

$H(z) = 3z^3 + 7z^2 + 5$

$X(z) = 2z^2 + 3z + 4$

$Y(z) = (3z^3 + 7z^2 + 5)(2z^2 + 3z + 4)$

$= 6z^5 + 23z^4 + 33z^3 + 38z^2 + 15z + 20$

$$\boxed{y(n) = \{6, 23, 33, 38, 15, 20\}}$$

---

## EXAMPLE 2.18: Zero insertion and zero padding

**Given**: $h(n) = \{3, 7, 0, 5\}$, $x(n) = \{2, 3, 4\}$

**(a)** $y(n) = \{6, 23, 33, 38, 15, 20\}$

**(b)** Insert zero between each sample:

$h_1(n) = \{3, 0, 7, 0, 0, 0, 5\}$

$x_1(n) = \{2, 0, 3, 0, 4\}$

$y_1(n) = \{6, 0, 23, 0, 33, 0, 38, 0, 15, 0, 20\}$

**(c)** Zero padding:

$h_2(n) = \{3, 7, 0, 5, 0, 0\}$

$x_2(n) = \{2, 3, 4, 0\}$

$y_2(n) = \{6, 23, 33, 38, 15, 20, 0, 0, 0\}$

---

## EXAMPLE 2.19: Convolution by polynomial multiplication

**Given**: $y(n) = \{1, 2, 1\} * \{2, 0, 1\}$

### Solution:

$(z^2 + 2z + 1)(2z^2 + 1) = 2z^4 + 4z^3 + 3z^2 + 2z + 1$

$$\boxed{y(n) = \{2, 4, 3, 2, 1\}}$$

---

## 2.6 DECONVOLUTION

Deconvolution is the process of finding input $x(n)$ or impulse response $h(n)$ from output $y(n)$.

### Using Z-transform:

$$X(z) = \frac{Y(z)}{H(z)}$$

### Using Recursion:

$$x(n) = \frac{y(n) - \sum_{k=0}^{n-1} x(k)h(n-k)}{h(0)}$$

---

## EXAMPLE 2.22: Deconvolution using Z-transform

**Given**: $h(n) = \{2, 1, 0, -1, 3\}$, $y(n) = \{2, -5, 1, 1, 6, -11, 6\}$

### Solution:

$$H(z) = 2 + z^{-1} - z^{-3} + 3z^{-4}$$

$$Y(z) = 2 - 5z^{-1} + z^{-2} + z^{-3} + 6z^{-4} - 11z^{-5} + 6z^{-6}$$

$$X(z) = \frac{Y(z)}{H(z)} = 1 - 3z^{-1} + 2z^{-2}$$

$$x(n) = \{1, -3, 2\}$$

---

## EXAMPLE 2.23: Deconvolution by recursion

**Given**: $y(n) = \{1, 1, 2, 0, 2, 1\}$, $h(n) = \{1, -1, 1\}$

### Solution:

$N_1 = 6 - 3 + 1 = 4$ (length of x(n))

$$x(0) = \frac{y(0)}{h(0)} = \frac{1}{1} = 1$$

$$x(1) = \frac{y(1) - x(0)h(1)}{h(0)} = \frac{1 - 1(-1)}{1} = 2$$

$$x(2) = \frac{y(2) - x(0)h(2) - x(1)h(1)}{h(0)} = \frac{2 - 1(1) - 2(-1)}{1} = 3$$

$$x(3) = \frac{y(3) - x(0)h(3) - x(1)h(2) - x(2)h(1)}{h(0)} = \frac{0 - 1(0) - 2(1) - 3(-1)}{1} = 1$$

$$\boxed{x(n) = \{1, 2, 3, 1\}}$$

---

## EXAMPLE 2.24: Deconvolution using tabular method

**Given**: $h(n) = \{1, -1, 1\}$, $y(n) = \{1, 1, 2, 0, 2, 1\}$

**Find**: $x(n)$

### Solution:

Let $x(n) = \{a, b, c, d\}$

| | a | b | c | d |
|---|---|---|---|---|
| 1 | a | b | c | d |
| -1 | -a | -b | -c | -d |
| 1 | | a | b | c |

$y(0) = a = 1$ → $a = 1$

$y(1) = b - a = 1$ → $b = 1 + 1 = 2$

$y(2) = c - b + a = 2$ → $c = 2 + 2 - 1 = 3$

$y(3) = d - c + b = 0$ → $d = 0 + 3 - 2 = 1$

$$\boxed{x(n) = \{1, 2, 3, 1\}}$$

---

## 2.7 INTERCONNECTION OF LTI SYSTEMS

### Parallel Connection:

$$h(n) = h_1(n) + h_2(n)$$

### Cascade Connection:

$$h(n) = h_1(n) * h_2(n)$$

---

## EXAMPLE 2.26: Interconnection of LTI systems

**Given**: $h_1(n) = \left(\frac{1}{2}\right)^n [u(n) - u(n-4)]$, $h_2(n) = \delta(n)$, $h_3(n) = u(n-2)$

### Solution:

Systems with $h_2(n)$ and $h_3(n)$ are in parallel:

$$h(n) = h_1(n) * [h_2(n) + h_3(n)] = h_1(n) * h_2(n) + h_1(n) * h_3(n)$$

$$h_1(n) * h_2(n) = h_1(n) = \left(\frac{1}{2}\right)^n [u(n) - u(n-4)]$$

$$h_1(n) * h_3(n) = h_1(n) * u(n-2)$$

Let $y_1(n) = \left(\frac{1}{2}\right)^n u(n) * u(n-2)$

$$y_1(n) = \sum_{k=0}^{n-2} \left(\frac{1}{2}\right)^k = 2\left[1 - \left(\frac{1}{2}\right)^{n-1}\right]u(n-2)$$

Let $y_2(n) = \left(\frac{1}{2}\right)^n u(n-4) * u(n-2)$

$$y_2(n) = \sum_{k=4}^{n-2} \left(\frac{1}{2}\right)^k = \left[\frac{1}{8} - 2\left(\frac{1}{2}\right)^n\right]u(n-6)$$

$$\boxed{h(n) = \left(\frac{1}{2}\right)^n [u(n) - u(n-4)] + 2\left[1 - \left(\frac{1}{2}\right)^{n-1}\right]u(n-2) + \left[\frac{1}{8} - 2\left(\frac{1}{2}\right)^n\right]u(n-6)}$$

---

## 2.8 CIRCULAR SHIFT AND CIRCULAR SYMMETRY

**Circular Shift**: For an N-point sequence, shifting by k means moving the last k samples to the beginning (for delay) or first k samples to the end (for advance).

$$x'(n) = x(n-k \bmod N)$$

**Circular Flipping**: $x(-n \bmod N)$

**Even Sequence**: $x(n-N) = x(n)$ for $0 \le n \le N-1$

**Odd Sequence**: $x(n-N) = -x(n)$ for $0 \le n \le N-1$

---

## EXAMPLE 2.27: Circular shift and flipping

**Given**: $y(n) = \{2, 3, 4, 5, 6, 0, 0, 7\}$

**(a)** $f(n) = y(n-2)$: Move last 2 samples to beginning

$$f(n) = \{0, 7, 2, 3, 4, 5, 6, 0\}$$

**(b)** $g(n) = y(n+2)$: Move first 2 samples to end

$$g(n) = \{4, 5, 6, 0, 0, 7, 2, 3\}$$

**(c)** $h(n) = y(-n)$: Flip

$$h(n) = \{2, 7, 0, 0, 6, 5, 4, 3\}$$

---

## 2.9 PERIODIC (CIRCULAR) CONVOLUTION

For two periodic sequences with period N:

$$y_p(n) = x_p(n) \oplus h_p(n) = \sum_{k=0}^{N-1} x_p(k)h_p(n-k)$$

---

## 2.10 METHODS OF PERFORMING CIRCULAR CONVOLUTION

### Method 1: Graphical (Concentric Circle Method)

1. Plot x(n) on outer circle anticlockwise
2. Plot h(n) on inner circle clockwise
3. Multiply corresponding samples and sum
4. Rotate inner circle anticlockwise and repeat

### Method 2: Tabular Array

Use modulo-N indexing.

### Method 3: Matrices Method

Form matrix using one sequence as circulant matrix.

---

## EXAMPLE 2.28: Circular convolution by graphical method

**Given**: $x_1(n) = \{1, 2, -1, -2, 3, 1\}$, $x_2(n) = \{3, 2, 1\}$

### Solution:

Make $x_2(n)$ length 6 by padding zeros: $x_2(n) = \{3, 2, 1, 0, 0, 0\}$

From concentric circle method:

$x_3(0) = (1)(3) + (2)(0) + (-1)(0) + (-2)(0) + (3)(1) + (1)(2) = 8$

$x_3(1) = (1)(2) + (2)(3) + (-1)(0) + (-2)(0) + (3)(0) + (1)(1) = 9$

$x_3(2) = (1)(1) + (2)(2) + (-1)(3) + (-2)(0) + (3)(0) + (1)(0) = 2$

$x_3(3) = (1)(0) + (2)(1) + (-1)(2) + (-2)(3) + (3)(0) + (1)(0) = -6$

$x_3(4) = (1)(0) + (2)(0) + (-1)(1) + (-2)(2) + (3)(3) + (1)(0) = 4$

$x_3(5) = (1)(0) + (2)(0) + (-1)(0) + (-2)(1) + (3)(2) + (1)(3) = 7$

$$\boxed{x_3(n) = \{8, 9, 2, -6, 4, 7\}}$$

---

## EXAMPLE 2.29: Circular convolution

**Given**: $x_1(n) = \{1, 2, 1, 2\}$, $x_2(n) = \{4, 3, 2, 1\}$

### Solution:

From concentric circle method:

$x_3(0) = (1)(4) + (2)(1) + (1)(2) + (2)(3) = 14$

$x_3(1) = (1)(3) + (2)(4) + (1)(1) + (2)(2) = 16$

$x_3(2) = (1)(2) + (2)(3) + (1)(4) + (2)(1) = 14$

$x_3(3) = (1)(1) + (2)(2) + (1)(3) + (2)(4) = 16$

$$\boxed{x_3(n) = \{14, 16, 14, 16\}}$$

---

## EXAMPLE 2.30: Circular convolution by matrices method

**Given**: $x_1(n) = \{1, 2, 1, 2\}$, $x_2(n) = \{4, 3, 2, 1\}$

### Solution:

$$\begin{bmatrix} x_3(0) \\ x_3(1) \\ x_3(2) \\ x_3(3) \end{bmatrix} = \begin{bmatrix} 4 & 1 & 2 & 3 \\ 3 & 4 & 1 & 2 \\ 2 & 3 & 4 & 1 \\ 1 & 2 & 3 & 4 \end{bmatrix} \begin{bmatrix} 1 \\ 2 \\ 1 \\ 2 \end{bmatrix} = \begin{bmatrix} 14 \\ 16 \\ 14 \\ 16 \end{bmatrix}$$

---

## EXAMPLE 2.33: Circular convolution by all methods

**Given**: $x(n) = \{1, 0.5\}$, $h(n) = \{0.5, 1\}$

### Graphical Method:

$x_3(0) = (1)(0.5) + (0.5)(1) = 1$

$x_3(1) = (1)(1) + (0.5)(0.5) = 1.25$

$$\boxed{y(n) = \{1, 1.25\}}$$

### Matrix Method:

$$\begin{bmatrix} y(0) \\ y(1) \end{bmatrix} = \begin{bmatrix} 0.5 & 1 \\ 1 & 0.5 \end{bmatrix} \begin{bmatrix} 1 \\ 0.5 \end{bmatrix} = \begin{bmatrix} 1 \\ 1.25 \end{bmatrix}$$

---

## 2.11 LINEAR CONVOLUTION FROM PERIODIC CONVOLUTION

To get linear convolution from circular convolution, pad sequences to length $N_1 + N_2 - 1$ with zeros.

---

## EXAMPLE 2.38: Regular convolution using periodic convolution

**Given**: $x(n) = \{3, -2, 1, 4\}$, $h(n) = \{2, 5, 3\}$

### Solution:

Length of linear convolution = $4 + 3 - 1 = 6$

Pad sequences to length 6:

$x_p(n) = \{3, -2, 1, 4, 0, 0\}$

$h_p(n) = \{2, 5, 3, 0, 0, 0\}$

$$y(n) = x(n) * h(n) = \{6, 11, 1, 7, 23, 12\}$$

---

## EXAMPLE 2.39: Zero padding for convolution

**Given**: $x(n) = \{1, 3, 0, 2, 1\}$, $h(n) = \{2, 3\}$

**(a)** Length of $y(n) = 5 + 2 - 1 = 6$. Pad 1 zero to x(n), 4 zeros to h(n).

**(b)** $x(n) * h(n) = \{2, 9, 9, 4, 8, 3\}$

**(c)** Circular convolution of zero-padded sequences = same as (b)

**(d)** Regular convolution of zero-padded sequences = $\{2, 9, 9, 4, 8, 3, 0, 0, 0, 0, 0\}$

---

## 2.12 PERIODIC CONVOLUTION FROM LINEAR CONVOLUTION

Find linear convolution (length $2N-1$), extend to length $2N$, wrap-around last N samples and add to first N.

---

## EXAMPLE 2.40: Periodic convolution from linear convolution

**(a)** $x_1(n) = \{1, 2, -1, -2, 3, 1\}$, $x_2(n) = \{3, 2, 1\}$

Linear convolution: $\{3, 8, 2, -6, 4, 7, 5, 1, 0\}$

Wrap-around last 6 samples:

$y_p(n) = \{8, 9, 2, -6, 4, 7\}$

**(b)** $x_1(n) = \{1, 2, 1, 2\}$, $x_2(n) = \{4, 3, 2, 1\}$

Linear convolution: $\{4, 11, 12, 16, 10, 5, 2\}$

$y_p(n) = \{14, 16, 14, 16\}$

**(c)** $x(n) = \{1, -1, 1, -1\}$, $h(n) = \{1, 2, 3, 4\}$

$y_p(n) = \{-2, 2, -2, 2\}$

---

## 2.13 PERIODIC EXTENSION

$$x_p(n) = \sum_{k=-\infty}^{\infty} x(n + kN)$$

---

## EXAMPLE 2.41: Periodic extension

**(a)** $x(n) = \{2, 0, 3, 0, 4, 7, 6, 5\}$, $N = 3$

Wrap-around blocks of 3:

$\{2, 0, 3\} + \{0, 4, 7\} + \{6, 5\} = \{8, 9, 10\}$

$$x_p(n) = \{8, 9, 10\}$$

**(b)** $x(n) = a^n u(n)$, period N:

$$x_p(n) = \sum_{k=0}^{\infty} a^{n+kN} = \frac{a^n}{1-a^N}, \quad 0 \le n \le N-1$$

---

## 2.14 SYSTEM RESPONSE TO PERIODIC INPUTS

Response to periodic input (period N) is also periodic with period N.

**Methods**:
1. Find linear convolution and ignore first period
2. Find output for one period, wrap-around
3. Find periodic extension of impulse response, convolve, wrap-around

---

## EXAMPLE 2.42: Response to periodic input

**Given**: $x(n) = \{2, 3, -4, 2, 3, -4, \dots\}$, $h(n) = \{1, 2\}$, $N = 3$

### Solution:

Linear convolution (sum-by-column):

$y(n) = \{2, 7, 2, -8, 7, 2, -8, \dots\}$

One period: $y(n) = \{-6, 7, 2\}$

---

## 2.15 DISCRETE CORRELATION

### Cross Correlation:

$$R_{xy}(n) = \sum_{k=-\infty}^{\infty} x(k)y(k-n) = \sum_{k=-\infty}^{\infty} x(k+n)y(k)$$

$$R_{xy}(n) = x(n) * y(-n)$$

$$R_{xy}(n) \neq R_{yx}(n), \quad R_{xy}(n) = R_{yx}(-n)$$

### Autocorrelation:

$$R_{xx}(n) = \sum_{k=-\infty}^{\infty} x(k)x(k-n) = x(n) * x(-n)$$

**Properties**:
1. $R_{xx}(0) = \sum x^2(k) = E_x$
2. $|R_{xx}(n)| \le R_{xx}(0)$
3. $R_{xx}(n) = R_{xx}(-n)$

---

## EXAMPLE 2.46: Cross correlation

**Given**: $x(n) = \{2, 3, 1, 4\}$, $y(n) = \{1, 3, 2, 1\}$

### Solution:

$y(-n) = \{1, 2, 3, 1\}$

$R_{xy}(n) = x(n) * y(-n)$

$$R_{xy}(n) = \{2, 7, 13, 17, 14, 13, 4\}$$

---

## EXAMPLE 2.47: Autocorrelation

**Given**: $x(n) = \{2, 3, 1, 4\}$

### Solution:

$x(-n) = \{4, 1, 3, 2\}$

$R_{xx}(n) = x(n) * x(-n)$

$$R_{xx}(n) = \{8, 14, 13, 30, 13, 14, 8\}$$

---

## EXAMPLE 2.48: Cross correlation properties

**Given**: $x(n) = \{3, 5, 1, 2\}$ with $\uparrow$ at $n=0$, $h(n) = \{1, 4, 3\}$ with $\uparrow$ at $n=0$

### Solution:

$R_{xh}(n) = x(n) * h(-n)$

$h(-n) = \{3, 4, 1\}$

$$R_{xh}(n) = \{9, 27, 26, 15, 9, 2\}$$

$R_{hx}(n) = h(n) * x(-n)$

$x(-n) = \{2, 1, 5, 3\}$

$$R_{hx}(n) = \{2, 9, 15, 26, 27, 9\}$$

Thus, $R_{xh}(n) \neq R_{hx}(n)$ and $R_{xh}(n) = R_{hx}(-n)$.

---

## EXAMPLE 2.49: Autocorrelation

**Given**: $x(n) = \{2, 5, -4\}$ with $\uparrow$ at $n=0$

### Solution:

$x(-n) = \{-4, 5, 2\}$

$$R_{xx}(n) = \{-8, -10, 45, -10, -8\}$$

Note: $R_{xx}(n)$ is even symmetric about $n=0$ and $R_{xx}(0) \ge R_{xx}(n)$.

---

## EXAMPLE 2.50: Cross correlation of exponential sequences

**Given**: $x(n) = (0.6)^n u(n)$, $h(n) = (0.3)^n u(n)$

### Solution:

$$R_{xh}(n) = \sum_{k=-\infty}^{\infty} (0.6)^k u(k)(0.3)^{k-n}u(k-n)$$

**For $n < 0$:**

$$R_{xh}(n) = \sum_{k=0}^{\infty} (0.6)^k(0.3)^{k-n} = (0.3)^{-n} \sum_{k=0}^{\infty} (0.18)^k = \frac{(0.3)^{-n}}{1-0.18} = 1.22(0.3)^{-n}u(-n-1)$$

**For $n \ge 0$:**

$$R_{xh}(n) = \sum_{k=n}^{\infty} (0.6)^k(0.3)^{k-n} = (0.6)^n \sum_{m=0}^{\infty} (0.18)^m = 1.22(0.6)^n u(n)$$

$$\boxed{R_{xh}(n) = 1.22(0.3)^{-n}u(-n-1) + 1.22(0.6)^n u(n)}$$

---

## EXAMPLE 2.51: Autocorrelation of exponential

**Given**: $x(n) = a^n u(n)$

### Solution:

For $n \ge 0$:

$$R_{xx}(n) = \sum_{k=n}^{\infty} a^k a^{k-n} = a^n \sum_{k=0}^{\infty} a^{2k} = \frac{a^n}{1-a^2}u(n)$$

Even extension:

$$R_{xx}(n) = \frac{a^{|n|}}{1-a^2}$$

---

## 2.16 PERIODIC DISCRETE CORRELATION

For periodic sequences with period N:

$$R_{xyp}(n) = x(n) \otimes y(n) = \sum_{k=0}^{N-1} x(k)y(k-n)$$

$$R_{xyp}(n) = R_{yxp}(-n)$$

---

## EXAMPLE 2.52: Periodic cross correlation

**Given**: $x(n) = \{1, 3, 0, 4\}$, $y(n) = \{4, 2, 1, 3\}$, $N = 4$

### Solution:

Linear cross correlation:

$R_{xy}(n) = \{4, 14, 7, 20, 9, 18, 16\}$

Wrap-around:

$R_{xyp}(n) = \{12, 23, 16, 29\}$

One period: $R_{xyp}(n) = \{12, 23, 16, 29\}$

$R_{yxp}(n) = \{12, 29, 16, 23\}$

Note: $R_{yxp}(n) = R_{xyp}(-n)$

---

## EXAMPLE 2.53: Periodic autocorrelation

**(a)** $x(n) = \{1, 3, 0, 4\}$

### Solution:

Linear autocorrelation: $\{4, 12, 1, 26, 0, 12, 16\}$

$R_{xxp}(n) = \{20, 28, 1, 30\}$

One period: $\{20, 28, 1, 30\}$

**(b)** $y(n) = \{2, 1, -2, 1\}$

$R_{yyp}(n) = \{10, -1, -2, -1\}$

---

# SHORT QUESTIONS WITH ANSWERS

**1. What is discrete convolution?**

Discrete convolution is a method of finding the zero-state response of relaxed linear time invariant systems.

$$y(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k)$$

---

**2. Write the expression for discrete convolution.**

$$y(n) = x(n) * h(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k) = \sum_{k=-\infty}^{\infty} h(k)x(n-k)$$

---

**3. If y(n) = x(n) * h(n), how are parameters related?**

1. Starting index of y(n) = sum of starting indices of x(n) and h(n)
2. Ending index of y(n) = sum of ending indices of x(n) and h(n)
3. Length: $L_y = L_x + L_h - 1$

---

**4. Write the properties of discrete convolution.**

1. Commutative: $x(n) * h(n) = h(n) * x(n)$
2. Associative: $[x(n) * h_1(n)] * h_2(n) = x(n) * [h_1(n) * h_2(n)]$
3. Distributive: $x(n) * [h_1(n) + h_2(n)] = x(n) * h_1(n) + x(n) * h_2(n)$
4. Shifting: $x(n-k) * h(n-m) = y(n-k-m)$
5. $x(n) * \delta(n) = x(n)$

---

**5. What is deconvolution?**

Deconvolution is the process of finding h(n) or x(n) from y(n) for a given x(n) or h(n).

---

**6. What is the basic difference between linear and circular convolution?**

In circular convolution, folding and shifting operations are performed in a circular fashion using modulo-N operation. In linear convolution, there is no modulo-N operation.

---

**7. What are the methods of finding circular convolution?**

1. Concentric circle method (Graphical)
2. Tabular array method
3. Matrices method
4. DFT method

---

**8. What is correlation?**

Correlation is a measure of similarity between two signals. The correlation of two signals is equal to the convolution of one signal with the flipped version of the second signal.

---

**9. What is cross correlation?**

The correlation of two different signals is called cross correlation.

---

**10. What is autocorrelation?**

The correlation of a signal with itself is called autocorrelation.

---

**11. Where does the autocorrelation function attain its maximum value?**

The autocorrelation function attains its maximum value at $n=0$.

---

# FILL IN THE BLANKS

1. Convolution is a mathematical operation equivalent to **FIR** filtering.

2. Convolution is a method of finding the **zero-state** response of relaxed LTI systems.

3. Convolving two sequences in time domain is equivalent to **multiplying** the sequences in frequency domain.

4. **Correlation** is a measure of similarity between two signals.

5. If the input to the system is a unit impulse, the output is known as **impulse response**.

6. The commutative property states: $x(n) * h(n) = h(n) * x(n)$

7. The step response is the **running sum** of the impulse response.

8. If $N_1$ is the length of $x(n)$ and $N_2$ is the length of $h(n)$, the length of $y(n)$ is **$N_1 + N_2 - 1$**.

9. Leading zeros appended to a sequence will appear as **leading** zeros in the convolution result.

10. **Deconvolution** is the process of finding the impulse response from y(n).

11. The regular convolution of two signals, both of which are **periodic**, does not exist.

12. The circular convolution of two sequences, each of length N, yields a sequence of length **N**.

13. **Correlation** is used to compare two signals.

14. The correlation between x(n) and y(n) is given by **$R_{xy}(n) = \sum_{k=-\infty}^{\infty} x(k)y(k-n)$**.

15. The periodic autocorrelation attains a maximum at **n = 0**.

---

# OBJECTIVE TYPE QUESTIONS

**1. The commutative property of convolution states that**
(a) $x(n) * h(n) = h(n) * x(n)$

**Answer: (a)**

---

**2. The associative property of convolution states that**
(b) $[x(n) * h_1(n)] * h_2(n) = x(n) * [h_1(n) * h_2(n)]$

**Answer: (b)**

---

**3. The distributive property of convolution states that**
(c) $x(n) * [h_1(n) + h_2(n)] = x(n) * h_1(n) + x(n) * h_2(n)$

**Answer: (c)**

---

**4. For a non-causal system h(n) excited by a non-causal input x(n):**
(a) $y(n) = \sum_{k=-\infty}^{\infty} x(k)h(n-k)$

**Answer: (a)**

---

**5. For a causal system excited by a causal input:**
(d) $y(n) = \sum_{k=0}^{n} x(k)h(n-k)$

**Answer: (d)**

---

**6. If x(n) = {1, 2, 3, 0} and h(n) = {3, 1, 0, 0, 0}, the length of y(n) = x(n) * h(n) is**
(a) 8 (b) 7 (c) 9 (d) none of these

**Answer: (a) 8** (4 + 5 - 1 = 8)

---

**7. {1, 2, 3} * {3, 2, 1} =**
(a) {3, 8, 1, 4, 8, 3} (b) {3, 8, 8, 3} (c) {3, 8, 12, 8, 3} (d) {2, 3, 8, 14, 8, 3}

**Answer: (a) {3, 8, 1, 4, 8, 3}**

---

**8. The convolution of x(n) = {1, 2, 0, 0, 0} and h(n) = {2, 1, 0} is**
(a) {2, 5, 2, 0, 0, 0} (b) {2, 5, 2, 0, 0, 0, 0} (c) {2, 5, 0, 0, 0, 0, 0} (d) {2, 5, 1, 0, 0, 0, 0}

**Answer: (a) {2, 5, 2, 0, 0, 0}**

---

**9. The circular convolution of x(n) = {1, 2, 1} and h(n) = {2, 1, 2} is**
(a) {7, 7, 6} (b) {6, 7, 6} (c) {6, 7, 6, 0} (d) {0, 7, 7, 6}

**Answer: (a) {7, 7, 6}**

---

**10. The cross correlation of x(n) = {1, 2, 1} and h(n) = {1, 2} is**
(a) {1, 4, 5, 2} (b) {2, 5, 4, 1} (c) {1, 2, 1, 1, 2} (d) {1, 3, 5, 2}

**Answer: (a) {1, 4, 5, 2}**

---

# PROBLEMS

1. Determine the response of the system characterized by the impulse response $h(n) = \left(\frac{1}{2}\right)^n u(n)$ to the input signal $x(n) = 2^n u(n)$.

2. Evaluate the step response for the LTI system represented by the impulse response:
   $h(n) = \left(\frac{1}{5}\right)^n u(n)$

3. Compute the linear convolution $y(n)$ of the following signals by all the methods.

4. Find the periodic extension of the following for $N = 3$.

5. Find the response $y(n)$ of the system for $N = 3$.

6. What is the input signal $x(n)$ that will generate the output sequence $y(n) = \{8, 22, 11, 31, 4, 12\}$ for a system with impulse response $h(n) = \{2, 5, 0, 4\}$?

7. The input $x(n) = \{1, 2\}$ to an LTI system produces an output $y(n) = \{2, 3, 1, 6\}$. Use deconvolution to find the impulse response $h(n)$.

8. Find the circular convolution of the following signals by all the methods.

9. Find the circular convolution of the following sequences and compare it with linear convolution.

10. The input $x(n)$ and the impulse response $h(n)$ of a LTI system are given. Determine the response of the system using (a) Linear convolution (b) Circular convolution.

11. Find the regular convolution of the following sequences using circular convolution.

12. Let $x(n) = \{2, 1, 0, -5, 2\}$ and $h(n) = \{1, 3\}$:
    (a) How many zeros must be appended to x(n) and h(n) to generate their regular convolution from the zero-padded sequences?
    (b) What is the regular convolution of the original sequences?
    (c) What is the circular convolution of the zero-padded sequences?
    (d) What is the regular convolution of the zero-padded sequences?

13. Find the periodic convolution of the following sequences using linear convolution.

14. Find the cross correlation of $x(n) = \{2, 3, 1, 4\}$ and $h(n) = \{1, 2, 1, 2\}$.

15. Given $x(n) = \{1, 2, 3, 4\}$ and $h(n) = \{1, 1, 2, 2\}$, show that $R_{xh}(n) \neq R_{hx}(n)$ and $R_{xh}(n) = R_{hx}(-n)$.

16. Find the autocorrelation of $x(n) = \{4, -1, 3, -2\}$.

17. Find the discrete periodic cross correlation $R_{xy}(n)$ and $R_{yx}(n)$ of the periodic sequences whose first period is given.

18. Find the periodic autocorrelation of the sequences.

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| Convolution | $y(n) = \sum_{k} x(k)h(n-k)$ |
| Length of y | $L_y = L_x + L_h - 1$ |
| Cross Correlation | $R_{xy}(n) = \sum_{k} x(k)y(k-n)$ |
| Autocorrelation | $R_{xx}(n) = \sum_{k} x(k)x(k-n)$ |
| Correlation via convolution | $R_{xy}(n) = x(n) * y(-n)$ |
| Periodic Convolution | $y_p(n) = \sum_{k=0}^{N-1} x(k)h(n-k)$ |
| Step Response | $s(n) = \sum_{k=0}^{n} h(k)$ |
| Deconvolution (recursion) | $x(n) = \frac{y(n) - \sum_{k=0}^{n-1} x(k)h(n-k)}{h(0)}$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 2.1 | Convolution $b^n u(n)$ and $a^n u(n)$ | ✅ |
| 2.2 | Convolution with finite duration input | ✅ |
| 2.3 | Response $3^n u(n)$ and $(1/3)^n u(n)$ | ✅ |
| 2.4 | Response $3^n u(n)$ and $2^n u(n)$ | ✅ |
| 2.5 | Convolution with cosine | ✅ |
| 2.6 | $u(n) * u(n-3)$ | ✅ |
| 2.7 | System with $a^n u(n)$ and $u(n)-u(n-N)$ | ✅ |
| 2.8 | $(1/2)^n u(n)$ and $u(n)-u(n-10)$ | ✅ |
| 2.9 | Non-causal sequences convolution | ✅ |
| 2.10 | Step response (4 parts) | ✅ |
| 2.11 | Convolution $x=\{4,2,1,3\}$, $h=\{1,2,2,1\}$ | ✅ |
| 2.12 | Convolution with negative coefficients | ✅ |
| 2.13 | Tabular method | ✅ |
| 2.14 | $u(n) * u(n-2)$ | ✅ |
| 2.15 | (a) $\sin(n\pi/2)u(n) * u(n-2)$, (b) $3^n u(-n+3) * u(n-2)$ | ✅ |
| 2.16 | FIR filter response | ✅ |
| 2.18 | Zero insertion and zero padding | ✅ |
| 2.19 | Polynomial multiplication | ✅ |
| 2.22 | Deconvolution using Z-transform | ✅ |
| 2.23 | Deconvolution by recursion | ✅ |
| 2.24 | Deconvolution by tabular method | ✅ |
| 2.26 | Interconnection of LTI systems | ✅ |
| 2.27 | Circular shift and flipping | ✅ |
| 2.28 | Circular convolution by graphical method | ✅ |
| 2.29 | Circular convolution | ✅ |
| 2.30 | Circular convolution by matrices | ✅ |
| 2.33 | Circular convolution by all methods | ✅ |
| 2.38 | Regular convolution using periodic convolution | ✅ |
| 2.39 | Zero padding for convolution | ✅ |
| 2.40 | Periodic convolution from linear convolution | ✅ |
| 2.41 | Periodic extension | ✅ |
| 2.42-2.45 | Response to periodic inputs | ✅ |
| 2.46 | Cross correlation | ✅ |
| 2.47 | Autocorrelation | ✅ |
| 2.48 | Cross correlation properties | ✅ |
| 2.49 | Autocorrelation | ✅ |
| 2.50 | Cross correlation of exponential sequences | ✅ |
| 2.51 | Autocorrelation of exponential | ✅ |
| 2.52 | Periodic cross correlation | ✅ |
| 2.53 | Periodic autocorrelation | ✅ |

---

## How to Save This File

1. Copy all the text from the code block above
2. Open a text editor (Notepad, VS Code, etc.)
3. Paste the content
4. Save the file with the name `Chapter_2_Convolution_and_Correlation.md`
5. The file will be properly formatted with headers, tables, equations, and examples