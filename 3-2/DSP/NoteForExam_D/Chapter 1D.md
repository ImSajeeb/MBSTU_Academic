# Chapter 1: Discrete-Time Signals and Systems

## 1.1 INTRODUCTION

* **Signal**: A single-valued function of one or more independent variables which contain some information.
* **System**: An entity that acts on an input signal and transforms it into an output signal.
* **Signal Processing**: A method of extracting information from the signal.

### Advantages of Digital Signal Processing

1. Digital circuits do not depend on precise values of digital signals.
2. Less sensitive to changes in component values, temperature, and ageing.
3. Any accuracy can be achieved by increasing the number of bits.
4. A single processor can be shared among multiple signals (time sharing).
5. Easy adjustment of processor characteristics during processing.
6. Linear phase characteristics are possible only with digital filters.
7. Easy storage of digital data.

### Disadvantages of Digital Signal Processing

* Requires A/D (Analog-to-Digital) and D/A (Digital-to-Analog) converters and reconstruction filters.
* Frequency limitations.
* Consumes power (uses active devices).
* Less reliable than passive components.

### Block Diagram of DSP System

`Analog Input` &rarr; `[Anti-aliasing Filter]` &rarr; `[A/D Converter]` &rarr; `[Digital Processor]` &rarr; `[D/A Converter]` &rarr; `[Reconstruction Filter]` &rarr; `Analog Output`


---

## 1.2 REPRESENTATION OF DISCRETE-TIME SIGNALS

### 1.2.1 Graphical Representation
Plot showing samples as vertical lines (stems) at discrete time instants.

### 1.2.2 Functional Representation
Amplitude written against values of n.

**Example**: 
$$x(-2) = -3, x(-1) = 2, x(0) = 0, x(1) = 3, x(2) = 1, x(3) = 2$$

### 1.2.3 Tabular Representation

| n | -2 | -1 | 0 | 1 | 2 | 3 |
|---|---|---|---|---|---|---|
| x(n) | -3 | 2 | 0 | 3 | 1 | 2 |

### 1.2.4 Sequence Representation

$$x(n) = \{-3, 2, 0, 3, 1, 2\} \text{ with } \uparrow \text{ at } n=0$$

### Sum and Product of Sequences:

**Sum**: $\{C_n\} = \{a_n\} + \{b_n\} \Rightarrow C_n = a_n + b_n$

**Product**: $\{C_n\} = \{a_n\}\{b_n\} \Rightarrow C_n = a_n b_n$

**Multiplication by constant**: $\{C_n\} = k\{a_n\} \Rightarrow C_n = ka_n$

---

## 1.3 ELEMENTARY DISCRETE-TIME SIGNALS

### 1.3.1 Unit Step Sequence

$$u(n) = \begin{cases} 1 & n \ge 0 \\ 0 & n < 0 \end{cases}$$

**Shifted version**:
$$u(n-k) = \begin{cases} 1 & n \ge k \\ 0 & n < k \end{cases}$$

### 1.3.2 Unit Ramp Sequence

$$r(n) = \begin{cases} n & n \ge 0 \\ 0 & n < 0 \end{cases} = n u(n)$$

$$r(n-k) = (n-k)u(n-k)$$

### 1.3.3 Unit Parabolic Sequence

$$p(n) = \begin{cases} \frac{n^2}{2} & n \ge 0 \\ 0 & n < 0 \end{cases} = \frac{n^2}{2}u(n)$$

### 1.3.4 Unit Impulse (Unit Sample) Sequence

$$\delta(n) = \begin{cases} 1 & n = 0 \\ 0 & n \neq 0 \end{cases}$$

$$\delta(n-k) = \begin{cases} 1 & n = k \\ 0 & n \neq k \end{cases}$$

**Properties of Unit Impulse**:

1. $$\delta(n) = u(n) - u(n-1)$$
2. $$\delta(n-k) = \begin{cases} 1 & n = k \\ 0 & n \neq k \end{cases}$$
3. $$x(n) = \sum_{k=-\infty}^{\infty} x(k)\delta(n-k)$$
4. $$\sum_{n=-\infty}^{\infty} x(n)\delta(n-n_0) = x(n_0)$$

**Relation between unit impulse and unit step**:

$$u(n) = \sum_{m=0}^{n} \delta(m), \quad \delta(n) = u(n) - u(n-1)$$

### 1.3.5 Sinusoidal Sequence

$$x(n) = A\sin(\omega n + \phi)$$

**Periodicity**: For discrete-time sinusoidal sequence to be periodic, $\frac{\omega}{2\pi}$ must be a rational number.

$$N = \frac{2\pi}{\omega}m$$ where N and m are integers.

### 1.3.6 Real Exponential Sequence

$$x(n) = a^n \text{ for all } n$$

- If a > 1: Sequence grows exponentially
- If 0 < a < 1: Sequence decays exponentially
- If a < 0: Sequence takes alternating signs

### 1.3.7 Complex Exponential Sequence

$$x(n) = a^n e^{j(\omega_0 n + \phi)} = a^n \cos(\omega_0 n + \phi) + ja^n \sin(\omega_0 n + \phi)$$

- For |a| = 1: Sinusoidal
- For |a| > 1: Exponentially grows
- For |a| < 1: Exponentially decays

---

## EXAMPLE 1.1: Find the following summations

**(a)** $$\sum_{n=-\infty}^{\infty} e^{3n}\delta(n-3)$$

### Solution:

We know the sifting property of impulse function:

$$\sum_{n=-\infty}^{\infty} x(n)\delta(n-n_0) = x(n_0)$$

This property states that when an impulse is multiplied by any function x(n) and summed over all n, the result is simply the value of x(n) at the location of the impulse.

Here, the impulse is $\delta(n-3)$, which means it is located at n = 3.

So we evaluate the function $e^{3n}$ at n = 3:

$$\sum_{n=-\infty}^{\infty} e^{3n}\delta(n-3) = [e^{3n}]_{n=3} = e^9$$

$$\boxed{\sum_{n=-\infty}^{\infty} e^{3n}\delta(n-3) = e^9}$$

---

**(b)** $$\sum_{n=-\infty}^{\infty} \delta(n-2)\cos 3n$$

### Solution:

The impulse $\delta(n-2)$ is located at n = 2.

Using the sifting property:

$$\sum_{n=-\infty}^{\infty} \delta(n-2)\cos 3n = [\cos 3n]_{n=2} = \cos 6$$

$$\boxed{\sum_{n=-\infty}^{\infty} \delta(n-2)\cos 3n = \cos 6}$$

---

**(c)** $$\sum_{n=-\infty}^{\infty} n^2\delta(n+4)$$

### Solution:

The impulse $\delta(n+4)$ is located at n = -4 (because n+4 = 0 when n = -4).

Using the sifting property:

$$\sum_{n=-\infty}^{\infty} n^2\delta(n+4) = [n^2]_{n=-4} = (-4)^2 = 16$$

$$\boxed{\sum_{n=-\infty}^{\infty} n^2\delta(n+4) = 16}$$

---

**(d)** $$\sum_{n=-\infty}^{\infty} \delta(n-2)e^{n^2}$$

### Solution:

The impulse $\delta(n-2)$ is located at n = 2.

Using the sifting property:

$$\sum_{n=-\infty}^{\infty} \delta(n-2)e^{n^2} = [e^{n^2}]_{n=2} = e^4$$

$$\boxed{\sum_{n=-\infty}^{\infty} \delta(n-2)e^{n^2} = e^4}$$

---

**(e)** $$\sum_{n=0}^{\infty} \delta(n+1)4^n$$

### Solution:

The impulse $\delta(n+1)$ is located at n = -1.

But the summation limits are from n = 0 to n = ∞.

So the impulse at n = -1 does not fall within the summation range.

$$\delta(n+1) = \begin{cases} 1 & n = -1 \\ 0 & n \neq -1 \end{cases}$$

Since n = -1 is outside the range n = 0 to ∞:

$$\sum_{n=0}^{\infty} \delta(n+1)4^n = 0$$

$$\boxed{\sum_{n=0}^{\infty} \delta(n+1)4^n = 0}$$

---

## 1.4 BASIC OPERATIONS ON SEQUENCES

### 1.4.1 Time Shifting
$$y(n) = x(n-k)$$
- k > 0: Delay (shift right)
- k < 0: Advance (shift left)

### 1.4.2 Time Reversal
$$y(n) = x(-n)$$

### 1.4.3 Amplitude Scaling
$$y(n) = ax(n)$$
- a > 1: Amplification
- a < 1: Attenuation

### 1.4.4 Time Scaling
$$y(n) = x(an)$$
- a > 1: Time compression
- a < 1: Time expansion

### 1.4.5 Signal Addition
$$y(n) = x_1(n) + x_2(n)$$

### 1.4.6 Signal Multiplication
$$y(n) = x_1(n)x_2(n)$$

---

## EXAMPLE 1.2: Sketch the following signals

**(a)** $$u(n+2)u(-n+3)$$

### Solution:

**Step 1: Sketch u(n+2)**

u(n+2) = 1 when n+2 ≥ 0, i.e., n ≥ -2
u(n+2) = 0 when n < -2

So u(n+2) exists from n = -2 to ∞.

**Step 2: Sketch u(-n+3)**

u(-n+3) = 1 when -n+3 ≥ 0, i.e., -n ≥ -3, i.e., n ≤ 3
u(-n+3) = 0 when n > 3

So u(-n+3) exists from n = -∞ to n = 3.

**Step 3: Multiply point by point**

The product is 1 only where both signals are 1, i.e., from n = -2 to n = 3.

$$u(n+2)u(-n+3) = \begin{cases} 1 & -2 \le n \le 3 \\ 0 & \text{otherwise} \end{cases}$$

$$\boxed{u(n+2)u(-n+3) = \begin{cases} 1 & -2 \le n \le 3 \\ 0 & \text{otherwise} \end{cases}}$$

---

**(b)** $$x(n) = u(n+4) - u(n-2)$$

### Solution:

u(n+4) = 1 for n ≥ -4
u(n-2) = 1 for n ≥ 2

When we subtract:
- For n < -4: u(n+4) = 0, u(n-2) = 0 → x(n) = 0
- For -4 ≤ n < 2: u(n+4) = 1, u(n-2) = 0 → x(n) = 1
- For n ≥ 2: u(n+4) = 1, u(n-2) = 1 → x(n) = 0

$$x(n) = \begin{cases} 1 & -4 \le n \le 1 \\ 0 & \text{otherwise} \end{cases}$$

$$\boxed{x(n) = \begin{cases} 1 & -4 \le n \le 1 \\ 0 & \text{otherwise} \end{cases}}$$

---

## EXAMPLE 1.3: Express the signals shown as sum of singular functions

**(a)** Signal exists from n = -2 to n = 1 with amplitude 1

### Solution:

At n = -2, -1, 0, 1, the signal has value 1.

$$x(n) = \delta(n+2) + \delta(n+1) + \delta(n) + \delta(n-1)$$

Alternatively:
$$x(n) = \begin{cases} 1 & -2 \le n \le 1 \\ 0 & \text{otherwise} \end{cases}$$

Using step functions:
$$x(n) = u(n+2) - u(n-2)$$

Because u(n+2) starts at -2 and u(n-2) starts at 2, their difference gives 1 from -2 to 1.

$$\boxed{x(n) = u(n+2) - u(n-2)}$$

---

**(b)** Signal exists from n = 2 to n = 5 with amplitude 1

### Solution:

At n = 2, 3, 4, 5, the signal has value 1.

$$x(n) = \delta(n-2) + \delta(n-3) + \delta(n-4) + \delta(n-5)$$

Alternatively:
$$x(n) = \begin{cases} 1 & 2 \le n \le 5 \\ 0 & \text{otherwise} \end{cases}$$

Using step functions:
$$x(n) = u(n-2) - u(n-6)$$

$$\boxed{x(n) = u(n-2) - u(n-6)}$$

---

## 1.5 CLASSIFICATION OF DISCRETE-TIME SIGNALS

### 1.5.1 Deterministic and Random Signals

**Deterministic**: No uncertainty, can be represented by mathematical equation.
- Example: $x(n) = \cos \omega n$

**Random**: Uncertainty, cannot be represented by mathematical equation.
- Example: Thermal noise

### 1.5.2 Periodic and Non-periodic Sequences

**Periodic**: $x(n) = x(n+N)$ for all n

**Fundamental period**: Smallest N satisfying above condition.

$$\omega = \frac{2\pi}{N}, \quad N = \frac{2\pi}{\omega}$$

---

## EXAMPLE 1.4: Show that $x(n) = e^{j\omega_0 n}$ is periodic only if $\omega_0/2\pi$ is rational

### Solution:

A signal is periodic if there exists an integer N such that:
$$x(n+N) = x(n) \text{ for all } n$$

**Step 1: Write the condition for periodicity**

$$e^{j\omega_0(n+N)} = e^{j\omega_0 n}$$

**Step 2: Simplify using exponential properties**

$$e^{j\omega_0 n}e^{j\omega_0 N} = e^{j\omega_0 n}$$

**Step 3: Cancel $e^{j\omega_0 n}$ from both sides**

$$e^{j\omega_0 N} = 1$$

**Step 4: Recall that $e^{j2\pi k} = 1$ for any integer k**

So $e^{j\omega_0 N} = 1$ implies:
$$\omega_0 N = 2\pi k$$

**Step 5: Rearrange**

$$\frac{\omega_0}{2\pi} = \frac{k}{N}$$

Since k and N are integers, $\frac{k}{N}$ is a rational number.

Therefore, $x(n)$ is periodic if and only if $\omega_0/2\pi$ is a rational number.

$$\boxed{x(n) \text{ is periodic if } \frac{\omega_0}{2\pi} \text{ is rational}}$$

---

## EXAMPLE 1.5: Periodicity of sampled complex exponential

**Given**: $x(t) = e^{j\omega_0 t}$ with fundamental period $T = 2\pi/\omega_0$
$x(n) = x(nT_s) = e^{jn\omega_0 T_s}$

**Show**: $x(n)$ is periodic if $T_s/T$ is rational.

### Solution:

**Step 1: Periodicity condition**

$$x(n+N) = x(n)$$
$$e^{j(n+N)\omega_0 T_s} = e^{jn\omega_0 T_s}$$

**Step 2: Simplify**

$$e^{jN\omega_0 T_s} = 1$$
$$N\omega_0 T_s = 2\pi m$$

**Step 3: Substitute $\omega_0 = 2\pi/T$**

$$N\left(\frac{2\pi}{T}\right)T_s = 2\pi m$$

**Step 4: Cancel $2\pi$ from both sides**

$$N\frac{T_s}{T} = m$$
$$\frac{T_s}{T} = \frac{m}{N}$$

Since m and N are integers, $\frac{m}{N}$ is rational.

Therefore, $x(n)$ is periodic if $T_s/T$ is a rational number.

$$\boxed{x(n) \text{ is periodic if } \frac{T_s}{T} \text{ is rational}}$$

---

## EXAMPLE 1.6: Condition for discrete-time sinusoidal signal to be periodic

**Given**: $x(n) = A\sin(\omega_0 n + \theta)$

### Solution:

**Step 1: Periodicity condition**

$$x(n+N) = x(n)$$
$$A\sin[\omega_0(n+N) + \theta] = A\sin(\omega_0 n + \theta)$$

**Step 2: Simplify the left side**

$$A\sin(\omega_0 n + \theta + \omega_0 N) = A\sin(\omega_0 n + \theta)$$

**Step 3: For sine to be equal at all n**

$$\omega_0 N = 2\pi m$$

**Step 4: Solve for N**

$$N = \frac{2\pi m}{\omega_0}$$

**Condition**: For the discrete-time signal to be periodic, $\omega_0$ must be a rational multiple of $2\pi$.

$$\boxed{\text{Periodic if } \frac{\omega_0}{2\pi} \text{ is rational}}$$

---

## EXAMPLE 1.7: Determine periodicity of signals

**(a)** $$\sin(0.02\pi n)$$

### Solution:

Compare with $\sin(2\pi fn)$:
$$0.02\pi = 2\pi f$$
$$f = \frac{0.02\pi}{2\pi} = 0.01 = \frac{1}{100}$$

Since f is rational, the signal is periodic.

Fundamental period: $N = 100$ (when k = 1)

$$\boxed{\text{Periodic with } N = 100}$$

---

**(b)** $$\sin(5\pi n)$$

### Solution:

$$2\pi f = 5\pi$$
$$f = \frac{5}{2}$$

Here f is rational with k = 5 and N = 2.

Therefore, periodic with fundamental period $N = 2$.

$$\boxed{\text{Periodic with } N = 2}$$

---

**(c)** $$\cos 4n$$

### Solution:

$$2\pi f = 4$$
$$f = \frac{2}{\pi}$$

Since $\frac{2}{\pi}$ is not rational (π is irrational), the signal is **not periodic**.

$$\boxed{\text{Not periodic}}$$

---

**(d)** $$\sin\frac{2\pi n}{3} + \cos\frac{2\pi n}{5}$$

### Solution:

For $\sin\frac{2\pi n}{3}$:
$$2\pi f_1 = \frac{2\pi}{3} \Rightarrow f_1 = \frac{1}{3}$$
$$N_1 = 3$$

For $\cos\frac{2\pi n}{5}$:
$$2\pi f_2 = \frac{2\pi}{5} \Rightarrow f_2 = \frac{1}{5}$$
$$N_2 = 5$$

$$\frac{N_1}{N_2} = \frac{3}{5} \text{ is rational}$$

LCM of 3 and 5 is 15.

Therefore, periodic with fundamental period $N = 15$.

$$\boxed{\text{Periodic with } N = 15}$$

---

**(e)** $$\cos\left(\frac{n}{6}\right)\cos\left(\frac{n\pi}{6}\right)$$

### Solution:

For $\cos\frac{n}{6}$:
$$2\pi f_1 = \frac{1}{6} \Rightarrow f_1 = \frac{1}{12\pi} \text{ (not rational)}$$

For $\cos\frac{n\pi}{6}$:
$$2\pi f_2 = \frac{\pi}{6} \Rightarrow f_2 = \frac{1}{12} \text{ (rational)}$$

Since one component is non-periodic and the other is periodic, the product is **non-periodic**.

$$\boxed{\text{Not periodic}}$$

---

**(f)** $$\cos\left(\frac{\pi}{2} + 0.3n\right)$$

### Solution:

$$2\pi f = 0.3$$
$$f = \frac{0.3}{2\pi} = \frac{3}{20\pi} \text{ (not rational)}$$

The signal is **non-periodic**.

$$\boxed{\text{Not periodic}}$$

---

**(g)** $$e^{j(\pi/2)n}$$

### Solution:

$$2\pi f = \frac{\pi}{2}$$
$$f = \frac{1}{4} \text{ (rational)}$$

Periodic with fundamental period $N = 4$.

$$\boxed{\text{Periodic with } N = 4}$$

---

**(h)** $$1 + e^{j2\pi n/3} - e^{j4\pi n/7}$$

### Solution:

Let $x(n) = x_1(n) + x_2(n) + x_3(n)$

where:
- $x_1(n) = 1$: $N_1 = 1$ (DC signal has arbitrary period)
- $x_2(n) = e^{j2\pi n/3}$: $f_2 = \frac{1}{3}$, $N_2 = 3$
- $x_3(n) = e^{j4\pi n/7}$: $f_3 = \frac{2}{7}$, $N_3 = \frac{7}{2}$

For the sum to be periodic, the ratio of any two periods must be rational:
$$\frac{N_1}{N_2} = \frac{1}{3} \text{ (rational)}$$
$$\frac{N_1}{N_3} = \frac{1}{7/2} = \frac{2}{7} \text{ (rational)}$$

LCM of $N_1, N_2, N_3 = \frac{7}{2} \times 3 = \frac{21}{2} = 10.5$

Therefore, periodic with fundamental period $N = 10.5$.

$$\boxed{\text{Periodic with } N = 10.5}$$

---

### 1.5.3 Energy and Power Signals

**Total Energy**:
$$E = \sum_{n=-\infty}^{\infty} |x(n)|^2$$

**Average Power**:
$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} |x(n)|^2$$

**Energy Signal**: 0 < E < ∞, P = 0
**Power Signal**: 0 < P < ∞, E = ∞
**Neither**: Both E and P are infinite

---

## EXAMPLE 1.8: Find energy/power signals

**(a)** $$x(n) = \left(\frac{1}{2}\right)^n u(n)$$

### Solution:

**Step 1: Calculate Energy**

$$E = \sum_{n=-\infty}^{\infty} |x(n)|^2 = \sum_{n=-\infty}^{\infty} \left[\left(\frac{1}{2}\right)^n u(n)\right]^2$$

Since u(n) = 0 for n < 0:
$$E = \sum_{n=0}^{\infty} \left(\frac{1}{4}\right)^n$$

This is a geometric series with ratio $r = \frac{1}{4}$:
$$E = \frac{1}{1 - \frac{1}{4}} = \frac{1}{\frac{3}{4}} = \frac{4}{3} \text{ joules}$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=0}^{N} \left(\frac{1}{4}\right)^n$$

Since the sum is finite and we divide by (2N+1) which goes to infinity:
$$P = 0$$

$$\boxed{\text{Energy signal: } E = \frac{4}{3}, P = 0}$$

---

**(b)** $$x(n) = e^{j(\pi n/3 + \pi/2)}$$

### Solution:

Note that $|e^{j\theta}| = 1$ for any $\theta$.

$$|x(n)|^2 = \left|e^{j(\pi n/3 + \pi/2)}\right|^2 = 1$$

**Step 1: Calculate Energy**

$$E = \lim_{N \to \infty} \sum_{n=-N}^{N} 1 = \lim_{N \to \infty} [2N+1] = \infty$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} 1 = \lim_{N \to \infty} \frac{2N+1}{2N+1} = 1 \text{ watt}$$

$$\boxed{\text{Power signal: } E = \infty, P = 1}$$

---

**(c)** $$x(n) = \sin\left(\frac{\pi}{3}n\right)$$

### Solution:

**Step 1: Calculate Energy**

$$E = \lim_{N \to \infty} \sum_{n=-N}^{N} \sin^2\left(\frac{\pi}{3}n\right)$$

Using $\sin^2\theta = \frac{1-\cos 2\theta}{2}$:

$$E = \frac{1}{2} \lim_{N \to \infty} \sum_{n=-N}^{N} \left[1 - \cos\left(\frac{2\pi}{3}n\right)\right] = \infty$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} \sin^2\left(\frac{\pi}{3}n\right)$$

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} \frac{1-\cos(2\pi n/3)}{2}$$

The average of $\cos(2\pi n/3)$ over many periods is 0.

$$P = \frac{1}{2} \text{ watt}$$

$$\boxed{\text{Power signal: } E = \infty, P = \frac{1}{2}}$$

---

**(d)** $$x(n) = u(n) - u(n-6)$$

### Solution:

This signal is 1 for n = 0, 1, 2, 3, 4, 5 (6 samples).

**Step 1: Calculate Energy**

$$E = \sum_{n=0}^{5} (1)^2 = 6 \text{ joules}$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=0}^{5} 1 = \lim_{N \to \infty} \frac{6}{2N+1} = 0$$

$$\boxed{\text{Energy signal: } E = 6, P = 0}$$

---

**(e)** $$x(n) = nu(n)$$

### Solution:

**Step 1: Calculate Energy**

$$E = \sum_{n=0}^{\infty} n^2 = \infty$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=0}^{N} n^2 = \infty$$

(Since $\sum_{n=0}^{N} n^2 \approx \frac{N^3}{3}$, dividing by N gives $\frac{N^2}{3} \to \infty$)

$$\boxed{\text{Neither: } E = \infty, P = \infty}$$

---

**(f)** $$x(n) = r(n) - r(n-4)$$

### Solution:

This signal is $x(n) = \{0, 1, 2, 3, 4, 4, 4, ...\}$ (ramp up to n=4, then constant at 4)

**Step 1: Calculate Energy**

$$E = \lim_{N \to \infty} \left[\sum_{n=0}^{4} n^2 + \sum_{n=5}^{N} 16\right] = \infty$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \left[\sum_{n=0}^{4} n^2 + \sum_{n=5}^{N} 16\right]$$

As N → ∞, $\sum_{n=5}^{N} 16 \approx 16N$

$$P = \lim_{N \to \infty} \frac{16N}{2N} = 8 \text{ watts}$$

$$\boxed{\text{Power signal: } E = \infty, P = 8}$$

---

## EXAMPLE 1.9: Find energy and power of finite duration signal

**Given**: $$x(n) = \begin{cases} n^2 & 0 \le n \le 3 \\ 10-n & 4 \le n \le 6 \\ n & 7 \le n \le 9 \\ 0 & \text{otherwise} \end{cases}$$

### Solution:

**Step 1: Calculate Energy**

$$E = \sum_{n=0}^{3} (n^2)^2 + \sum_{n=4}^{6} (10-n)^2 + \sum_{n=7}^{9} n^2$$

$$E = \sum_{n=0}^{3} n^4 + \sum_{n=4}^{6} (100 + n^2 - 20n) + \sum_{n=7}^{9} n^2$$

For n = 0 to 3:
$$\sum_{n=0}^{3} n^4 = 0 + 1 + 16 + 81 = 98$$

For n = 4 to 6:
$$(10-4)^2 + (10-5)^2 + (10-6)^2 = 6^2 + 5^2 + 4^2 = 36 + 25 + 16 = 77$$

For n = 7 to 9:
$$7^2 + 8^2 + 9^2 = 49 + 64 + 81 = 194$$

$$E = 98 + 77 + 194 = 369 \text{ joules}$$

**Step 2: Calculate Power**

$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=0}^{9} |x(n)|^2$$

Since the sum is finite (369) and we divide by (2N+1) which goes to infinity:

$$P = \lim_{N \to \infty} \frac{369}{2N+1} = 0$$

$$\boxed{\text{Energy signal: } E = 369, P = 0}$$

---

### 1.5.4 Causal and Non-causal Signals

**Causal**: x(n) = 0 for n < 0
**Non-causal**: x(n) ≠ 0 for n < 0
**Anti-causal**: x(n) = 0 for n > 0

---

## EXAMPLE 1.10: Find which signals are causal or non-causal

**(a)** $$x(n) = u(n+4) - u(n-2)$$

### Solution:

u(n+4) = 1 for n ≥ -4
u(n-2) = 1 for n ≥ 2

The signal exists from n = -4 to n = 1.

Since x(n) ≠ 0 for n < 0, the signal is **non-causal**.

$$\boxed{\text{Non-causal}}$$

---

**(b)** $$x(n) = \left(\frac{1}{4}\right)^n u(n+2) - \left(\frac{1}{2}\right)^n u(n-4)$$

### Solution:

u(n+2) exists for n ≥ -2
u(n-4) exists for n ≥ 4

The signal has non-zero values for n < 0 (specifically at n = -2, -1).

Therefore, the signal is **non-causal**.

$$\boxed{\text{Non-causal}}$$

---

**(c)** $$x(n) = u(-n)$$

### Solution:

u(-n) = 1 for -n ≥ 0, i.e., n ≤ 0
u(-n) = 0 for n > 0

The signal exists only for n ≤ 0.

Therefore, the signal is **anti-causal** (can also be called non-causal).

$$\boxed{\text{Anti-causal}}$$

---

### 1.5.5 Even and Odd Signals

**Even Signal**: $x(-n) = x(n)$

**Odd Signal**: $x(-n) = -x(n)$

**Decomposition**:

$$x_e(n) = \frac{1}{2}[x(n) + x(-n)]$$
$$x_o(n) = \frac{1}{2}[x(n) - x(-n)]$$

**Product Properties**:
- Even × Even = Even
- Odd × Odd = Even
- Even × Odd = Odd

---

## EXAMPLE 1.11: Find even and odd components

**(a)** $$x(n) = \{3, 1, -2, 4, -2\} \text{ with } \uparrow \text{ at } n=0$$

### Solution:

Given:
$$x(n) = \{3, 1, -2, 4, -2\}$$
where values are at n = -2, -1, 0, 1, 2 respectively.

$$x(-n) = \{-2, 4, -2, 1, 3\}$$

**Step 1: Find even component**

$$x_e(n) = \frac{1}{2}[x(n) + x(-n)]$$

$$x_e(n) = \frac{1}{2}[\{3,1,-2,4,-2\} + \{-2,4,-2,1,3\}]$$

$$x_e(n) = \frac{1}{2}\{1, 5, -4, 5, 1\}$$
$$x_e(n) = \{0.5, 2.5, -2, 2.5, 0.5\}$$

**Step 2: Find odd component**

$$x_o(n) = \frac{1}{2}[x(n) - x(-n)]$$

$$x_o(n) = \frac{1}{2}[\{3,1,-2,4,-2\} - \{-2,4,-2,1,3\}]$$

$$x_o(n) = \frac{1}{2}\{5, -3, 0, 3, -5\}$$
$$x_o(n) = \{2.5, -1.5, 0, 1.5, -2.5\}$$

$$\boxed{x_e(n) = \{0.5, 2.5, -2, 2.5, 0.5\}}$$
$$\boxed{x_o(n) = \{2.5, -1.5, 0, 1.5, -2.5\}}$$

**Verification**: $x(n) = x_e(n) + x_o(n)$

---

**(b)** $$x(n) = \{2, 5, 1, -3\} \text{ with } \uparrow \text{ at } n=0$$

### Solution:

$$x(n) = \{2, 5, 1, -3\}$$
Values at n = -1, 0, 1, 2 respectively.

$$x(-n) = \{-3, 1, 5, 2\}$$

**Step 1: Even component**

$$x_e(n) = \frac{1}{2}[\{2,5,1,-3\} + \{-3,1,5,2\}]$$
$$x_e(n) = \frac{1}{2}\{-1, 6, 6, -1\}$$
$$x_e(n) = \{-0.5, 3, 3, -0.5\}$$

**Step 2: Odd component**

$$x_o(n) = \frac{1}{2}[\{2,5,1,-3\} - \{-3,1,5,2\}]$$
$$x_o(n) = \frac{1}{2}\{5, 4, -4, -5\}$$
$$x_o(n) = \{2.5, 2, -2, -2.5\}$$

$$\boxed{x_e(n) = \{-0.5, 3, 3, -0.5\}}$$
$$\boxed{x_o(n) = \{2.5, 2, -2, -2.5\}}$$

---

**(c)** $$x(n) = \{5, 4, 3, 2, 1\} \text{ with } \uparrow \text{ at } n=0$$

### Solution:

$$x(n) = \{5, 4, 3, 2, 1\}$$
Values at n = -2, -1, 0, 1, 2 respectively.

$$x(-n) = \{1, 2, 3, 4, 5\}$$

**Step 1: Even component**

$$x_e(n) = \frac{1}{2}[\{5,4,3,2,1\} + \{1,2,3,4,5\}]$$
$$x_e(n) = \frac{1}{2}\{6, 6, 6, 6, 6\}$$
$$x_e(n) = \{3, 3, 3, 3, 3\}$$

**Step 2: Odd component**

$$x_o(n) = \frac{1}{2}[\{5,4,3,2,1\} - \{1,2,3,4,5\}]$$
$$x_o(n) = \frac{1}{2}\{4, 2, 0, -2, -4\}$$
$$x_o(n) = \{2, 1, 0, -1, -2\}$$

$$\boxed{x_e(n) = \{3, 3, 3, 3, 3\}}$$
$$\boxed{x_o(n) = \{2, 1, 0, -1, -2\}}$$

---

**(d)** $$x(n) = \{5, 4, 3, 2, 1\} \text{ with } \uparrow \text{ at } n=4$$

### Solution:

Here the arrow at n=4 means:
x(0) = 5, x(1) = 4, x(2) = 3, x(3) = 2, x(4) = 1

So:
$$x(n) = \{5, 4, 3, 2, 1\}$$
Values at n = 0, 1, 2, 3, 4.

$$x(-n) = \{1, 2, 3, 4, 5\}$$
Values at n = -4, -3, -2, -1, 0.

To find even and odd parts, we need to align them at n = 0.

x(n): n = 0, 1, 2, 3, 4 → [5, 4, 3, 2, 1]
x(-n): n = 0, -1, -2, -3, -4 → [1, 2, 3, 4, 5]

**Step 1: Even component**

$$x_e(n) = \frac{1}{2}[x(n) + x(-n)]$$
$$x_e(n) = \frac{1}{2}[\{5,4,3,2,1\} + \{1,2,3,4,5\}]$$
$$x_e(n) = \{3, 3, 3, 3, 3\}$$

**Step 2: Odd component**

$$x_o(n) = \frac{1}{2}[x(n) - x(-n)]$$
$$x_o(n) = \frac{1}{2}[\{5,4,3,2,1\} - \{1,2,3,4,5\}]$$
$$x_o(n) = \{2, 1, 0, -1, -2\}$$

$$\boxed{x_e(n) = \{3, 3, 3, 3, 3\}}$$
$$\boxed{x_o(n) = \{2, 1, 0, -1, -2\}}$$

---

## 1.6 CLASSIFICATION OF DISCRETE-TIME SYSTEMS

### 1.6.1 Static and Dynamic Systems

**Static (Memoryless)**: Output depends only on present input.
- Example: $y(n) = x^2(n)$

**Dynamic (Memory)**: Output depends on past or future inputs.
- Example: $y(n) = x(n+2)$

---

## EXAMPLE 1.12: Find whether systems are dynamic or not

**(a)** $$y(n) = x(n+2)$$

### Solution:

The output at time n depends on x(n+2), which is a future value of input.

For example: y(0) depends on x(2), which hasn't occurred yet.

Therefore, the system is **dynamic**.

$$\boxed{\text{Dynamic}}$$

---

**(b)** $$y(n) = x^2(n)$$

### Solution:

The output at time n depends only on x(n), which is the present input.

Therefore, the system is **static**.

$$\boxed{\text{Static}}$$

---

**(c)** $$y(n) = x(n-2) + x(n)$$

### Solution:

The output depends on x(n) (present) and x(n-2) (past).

Since it depends on past values, it requires memory.

Therefore, the system is **dynamic**.

$$\boxed{\text{Dynamic}}$$

---

### 1.6.2 Causal and Non-causal Systems

**Causal**: Output depends only on present and past inputs.
$$h(n) = 0 \text{ for } n < 0$$

**Non-causal**: Output depends on future inputs.

---

## EXAMPLE 1.13: Check whether systems are causal or not

**(a)** $$y(n) = x(n) + x(n-2)$$

### Solution:

For n = 0: y(0) = x(0) + x(-2) (present and past)
For n = 2: y(2) = x(2) + x(0) (present and past)

For all values of n, output depends only on present and past inputs.

Therefore, the system is **causal**.

$$\boxed{\text{Causal}}$$

---

**(b)** $$y(n) = x(2n)$$

### Solution:

For n = 2: y(2) = x(4) (future input)
For n = 3: y(3) = x(6) (future input)

The output depends on future values of input.

Therefore, the system is **non-causal**.

$$\boxed{\text{Non-causal}}$$

---

**(c)** $$y(n) = \sin[x(n)]$$

### Solution:

The output at any time n depends only on x(n), the present input.

Therefore, the system is **causal**.

$$\boxed{\text{Causal}}$$

---

**(d)** $$y(n) = x(-n)$$

### Solution:

For n = -2: y(-2) = x(2) (future input)
For n = -1: y(-1) = x(1) (future input)

For negative values of n, the output depends on future inputs.

Therefore, the system is **non-causal**.

$$\boxed{\text{Non-causal}}$$

---

### 1.6.3 Linear and Non-linear Systems

**Linearity Test**: A system is linear if:

$$T[ax_1(n) + bx_2(n)] = aT[x_1(n)] + bT[x_2(n)]$$

---

## EXAMPLE 1.14: Check whether systems are linear or not

**(a)** $$y(n) = n^2x(n)$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = n^2x_1(n)$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = n^2x_2(n)$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = a[n^2x_1(n)] + b[n^2x_2(n)]$$
$$= n^2[ax_1(n) + bx_2(n)]$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = n^2[ax_1(n) + bx_2(n)]$$

**Step 3: Compare**

$$y_3(n) = ay_1(n) + by_2(n)$$

Therefore, the system is **linear**.

$$\boxed{\text{Linear}}$$

---

**(b)** $$y(n) = x(n) + \frac{1}{2x(n-2)}$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = x_1(n) + \frac{1}{2x_1(n-2)}$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = x_2(n) + \frac{1}{2x_2(n-2)}$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = a\left[x_1(n) + \frac{1}{2x_1(n-2)}\right] + b\left[x_2(n) + \frac{1}{2x_2(n-2)}\right]$$
$$= [ax_1(n) + bx_2(n)] + \frac{a}{2x_1(n-2)} + \frac{b}{2x_2(n-2)}$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = [ax_1(n) + bx_2(n)] + \frac{1}{2[ax_1(n-2) + bx_2(n-2)]}$$

**Step 3: Compare**

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

$$\boxed{\text{Non-linear}}$$

---

**(c)** $$y(n) = 2x(n) + 4$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = 2x_1(n) + 4$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = 2x_2(n) + 4$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = a[2x_1(n) + 4] + b[2x_2(n) + 4]$$
$$= 2[ax_1(n) + bx_2(n)] + 4(a+b)$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = 2[ax_1(n) + bx_2(n)] + 4$$

**Step 3: Compare**

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

$$\boxed{\text{Non-linear}}$$

---

**(d)** $$y(n) = x(n)\cos\omega n$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = x_1(n)\cos\omega n$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = x_2(n)\cos\omega n$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = a[x_1(n)\cos\omega n] + b[x_2(n)\cos\omega n]$$
$$= [ax_1(n) + bx_2(n)]\cos\omega n$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = [ax_1(n) + bx_2(n)]\cos\omega n$$

**Step 3: Compare**

$$y_3(n) = ay_1(n) + by_2(n)$$

Therefore, the system is **linear**.

$$\boxed{\text{Linear}}$$

---

**(e)** $$y(n) = |x(n)|$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = |x_1(n)|$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = |x_2(n)|$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = a|x_1(n)| + b|x_2(n)|$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = |ax_1(n) + bx_2(n)|$$

**Step 3: Compare**

In general, $|ax_1(n) + bx_2(n)| \neq a|x_1(n)| + b|x_2(n)|$

For example, if a = 1, b = 1, x₁ = 1, x₂ = -1:
LHS = |1-1| = 0, RHS = 1 + 1 = 2

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

$$\boxed{\text{Non-linear}}$$

---

**(f)** $$y(n) = \frac{1}{N}\sum_{k=0}^{N-1} x(n-k)$$

### Solution:

Let input x₁(n) produce output y₁(n):
$$y_1(n) = \frac{1}{N}\sum_{k=0}^{N-1} x_1(n-k)$$

Let input x₂(n) produce output y₂(n):
$$y_2(n) = \frac{1}{N}\sum_{k=0}^{N-1} x_2(n-k)$$

**Step 1: Weighted sum of outputs**

$$ay_1(n) + by_2(n) = \frac{a}{N}\sum_{k=0}^{N-1} x_1(n-k) + \frac{b}{N}\sum_{k=0}^{N-1} x_2(n-k)$$
$$= \frac{1}{N}\sum_{k=0}^{N-1} [ax_1(n-k) + bx_2(n-k)]$$

**Step 2: Output due to weighted sum of inputs**

$$y_3(n) = T[ax_1(n) + bx_2(n)] = \frac{1}{N}\sum_{k=0}^{N-1} [ax_1(n-k) + bx_2(n-k)]$$

**Step 3: Compare**

$$y_3(n) = ay_1(n) + by_2(n)$$

Therefore, the system is **linear**.

$$\boxed{\text{Linear}}$$

---

### 1.6.4 Shift-invariant and Shift-varying Systems

**Shift-invariant**: If $T[x(n)] = y(n)$, then $T[x(n-k)] = y(n-k)$

**Test Procedure**:
1. Find output due to delayed input: $y(n,k) = T[x(n-k)]$
2. Find delayed output: $y(n-k) = y(n)|_{n \to n-k}$
3. If $y(n,k) = y(n-k)$, system is time-invariant

---

## EXAMPLE 1.15: Determine whether systems are time-invariant or not

**(a)** $$y(n) = x(n/2)$$

### Solution:

**Step 1: Output due to delayed input**

$$y(n,k) = T[x(n-k)] = y(n)|_{x(n) = x(n-k)} = x\left(\frac{n}{2} - k\right)$$

**Step 2: Delayed output**

$$y(n-k) = y(n)|_{n = n-k} = x\left(\frac{n-k}{2}\right)$$

**Step 3: Compare**

$$y(n,k) = x\left(\frac{n}{2} - k\right) \neq x\left(\frac{n-k}{2}\right) = y(n-k)$$

Therefore, the system is **time-variant**.

$$\boxed{\text{Time-variant}}$$

---

**(b)** $$y(n) = x(n)$$

### Solution:

**Step 1: Output due to delayed input**

$$y(n,k) = T[x(n-k)] = y(n)|_{x(n) = x(n-k)} = x(n-k)$$

**Step 2: Delayed output**

$$y(n-k) = y(n)|_{n = n-k} = x(n-k)$$

**Step 3: Compare**

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Time-invariant}}$$

---

**(c)** $$y(n) = x^2(n-2)$$

### Solution:

**Step 1: Output due to delayed input**

$$y(n,k) = T[x(n-k)] = y(n)|_{x(n) = x(n-k)} = x^2(n-2-k)$$

**Step 2: Delayed output**

$$y(n-k) = y(n)|_{n = n-k} = x^2(n-2-k)$$

**Step 3: Compare**

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Time-invariant}}$$

---

**(d)** $$y(n) = x(n) + nx(n-2)$$

### Solution:

**Step 1: Output due to delayed input**

$$y(n,k) = T[x(n-k)] = y(n)|_{x(n) = x(n-k)} = x(n-k) + nx(n-2-k)$$

**Step 2: Delayed output**

$$y(n-k) = y(n)|_{n = n-k} = x(n-k) + (n-k)x(n-k-2)$$

**Step 3: Compare**

$$y(n,k) = x(n-k) + nx(n-2-k) \neq x(n-k) + (n-k)x(n-k-2) = y(n-k)$$

Therefore, the system is **time-variant**.

$$\boxed{\text{Time-variant}}$$

---

## EXAMPLE 1.16: Show that the following systems are linear shift-invariant systems

**(a)** $$y(n) = x(n/2)$$

### Solution:

**Step 1: Test linearity**

For inputs x₁(n) and x₂(n):
$$y_1(n) = x_1\left(\frac{n}{2}\right)$$
$$y_2(n) = x_2\left(\frac{n}{2}\right)$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = ax_1\left(\frac{n}{2}\right) + bx_2\left(\frac{n}{2}\right)$$

Output due to weighted sum of inputs:
$$y_3(n) = T[ax_1(n) + bx_2(n)] = ax_1\left(\frac{n}{2}\right) + bx_2\left(\frac{n}{2}\right)$$

$$y_3(n) = ay_1(n) + by_2(n)$$

So the system is **linear**.

**Step 2: Test shift-invariance**

Output due to delayed input:
$$y(n,k) = T[x(n-k)] = x\left(\frac{n}{2} - k\right)$$

Delayed output:
$$y(n-k) = x\left(\frac{n-k}{2}\right)$$

$$y(n,k) \neq y(n-k)$$

So the system is **shift-varying**.

$$\boxed{\text{Linear but shift-varying (not LSI)}}$$

---

**(b)** $$y(n) = \begin{cases} x(n) + x(n-2) & n \ge 0 \\ 0 & n < 0 \end{cases}$$

### Solution:

**Step 1: Test linearity**

For inputs x₁(n) and x₂(n):
$$y_1(n) = x_1(n) + x_1(n-2) \text{ for } n \ge 0$$
$$y_2(n) = x_2(n) + x_2(n-2) \text{ for } n \ge 0$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = a[x_1(n) + x_1(n-2)] + b[x_2(n) + x_2(n-2)]$$
$$= [ax_1(n) + bx_2(n)] + [ax_1(n-2) + bx_2(n-2)]$$

Output due to weighted sum of inputs:
$$y_3(n) = T[ax_1(n) + bx_2(n)] = [ax_1(n) + bx_2(n)] + [ax_1(n-2) + bx_2(n-2)]$$

$$y_3(n) = ay_1(n) + by_2(n)$$

So the system is **linear**.

**Step 2: Test shift-invariance**

Output due to delayed input:
$$y(n,k) = T[x(n-k)] = x(n-k) + x(n-2-k)$$

Delayed output:
$$y(n-k) = x(n-k) + x(n-k-2)$$

$$y(n,k) = y(n-k)$$

So the system is **shift-invariant**.

$$\boxed{\text{Linear Shift-Invariant (LSI)}}$$

---

## EXAMPLE 1.17: Check properties of systems

**(a)** $$y(n) = ev\{x(n)\} = \frac{1}{2}[x(n) + x(-n)]$$

### Solution:

**Step 1: Static or Dynamic?**

For n > 0, output depends on past values.
For n < 0, output depends on future values.

Therefore, the system is **dynamic**.

**Step 2: Linear or Non-linear?**

For inputs x₁(n) and x₂(n):
$$y_1(n) = \frac{1}{2}[x_1(n) + x_1(-n)]$$
$$y_2(n) = \frac{1}{2}[x_2(n) + x_2(-n)]$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = \frac{1}{2}[(ax_1(n) + bx_2(n)) + (ax_1(-n) + bx_2(-n))]$$

Output due to weighted input:
$$y_3(n) = \frac{1}{2}[(ax_1(n) + bx_2(n)) + (ax_1(-n) + bx_2(-n))]$$

$$y_3(n) = ay_1(n) + by_2(n)$$

Therefore, the system is **linear**.

**Step 3: Causal or Non-causal?**

For n = -2: y(-2) = ½[x(-2) + x(2)] depends on future input x(2).

Therefore, the system is **non-causal**.

**Step 4: Time-invariant or Time-variant?**

Output due to delayed input:
$$y(n,k) = \frac{1}{2}[x(n-k) + x(-n-k)]$$

Delayed output:
$$y(n-k) = \frac{1}{2}[x(n-k) + x(-n+k)]$$

$$y(n,k) \neq y(n-k)$$

Therefore, the system is **time-variant**.

$$\boxed{\text{Dynamic, Linear, Non-causal, Time-variant}}$$

---

**(b)** $$y(n) = x(n)x(n-2)$$

### Solution:

**Step 1: Static or Dynamic?**

Output depends on x(n-2), which is a past input.

Therefore, the system is **dynamic**.

**Step 2: Linear or Non-linear?**

For inputs x₁(n) and x₂(n):
$$y_1(n) = x_1(n)x_1(n-2)$$
$$y_2(n) = x_2(n)x_2(n-2)$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = ax_1(n)x_1(n-2) + bx_2(n)x_2(n-2)$$

Output due to weighted input:
$$y_3(n) = T[ax_1(n) + bx_2(n)] = [ax_1(n) + bx_2(n)][ax_1(n-2) + bx_2(n-2)]$$
$$= a^2x_1(n)x_1(n-2) + abx_1(n)x_2(n-2) + abx_2(n)x_1(n-2) + b^2x_2(n)x_2(n-2)$$

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

**Step 3: Causal or Non-causal?**

Output depends only on present and past inputs.

Therefore, the system is **causal**.

**Step 4: Time-invariant or Time-variant?**

Output due to delayed input:
$$y(n,k) = T[x(n-k)] = x(n-k)x(n-2-k)$$

Delayed output:
$$y(n-k) = x(n-k)x(n-k-2)$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Dynamic, Non-linear, Causal, Time-invariant}}$$

---

**(c)** $$y(n) = \log_{10}|x(n)|$$

### Solution:

**Step 1: Static or Dynamic?**

Output depends only on present input.

Therefore, the system is **static**.

**Step 2: Linear or Non-linear?**

For inputs x₁(n) and x₂(n):
$$y_1(n) = \log_{10}|x_1(n)|$$
$$y_2(n) = \log_{10}|x_2(n)|$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = a\log_{10}|x_1(n)| + b\log_{10}|x_2(n)|$$

Output due to weighted input:
$$y_3(n) = \log_{10}|ax_1(n) + bx_2(n)|$$

In general, $a\log|A| + b\log|B| \neq \log|aA + bB|$

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

**Step 3: Causal or Non-causal?**

Output depends only on present input.

Therefore, the system is **causal**.

**Step 4: Time-invariant or Time-variant?**

Output due to delayed input:
$$y(n,k) = \log_{10}|x(n-k)|$$

Delayed output:
$$y(n-k) = \log_{10}|x(n-k)|$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Static, Non-linear, Causal, Time-invariant}}$$

---

**(d)** $$y(n) = a^n x(n)$$

### Solution:

**Step 1: Static or Dynamic?**

Output depends only on present input.

Therefore, the system is **static**.

**Step 2: Linear or Non-linear?**

For inputs x₁(n) and x₂(n):
$$y_1(n) = a^n x_1(n)$$
$$y_2(n) = a^n x_2(n)$$

Weighted sum of outputs:
$$py_1(n) + qy_2(n) = a^n[px_1(n) + qx_2(n)]$$

Output due to weighted input:
$$y_3(n) = T[px_1(n) + qx_2(n)] = a^n[px_1(n) + qx_2(n)]$$

$$y_3(n) = py_1(n) + qy_2(n)$$

Therefore, the system is **linear**.

**Step 3: Causal or Non-causal?**

Output depends only on present input.

Therefore, the system is **causal**.

**Step 4: Time-invariant or Time-variant?**

Output due to delayed input:
$$y(n,k) = T[x(n-k)] = a^n x(n-k)$$

Delayed output:
$$y(n-k) = a^{n-k}x(n-k)$$

$$y(n,k) \neq y(n-k)$$

Therefore, the system is **time-variant**.

$$\boxed{\text{Static, Linear, Causal, Time-variant}}$$

---

**(e)** $$y(n) = x^2(n) + \frac{1}{x^2(n-1)}$$

### Solution:

**Step 1: Static or Dynamic?**

Output depends on x(n-1), a past input.

Therefore, the system is **dynamic**.

**Step 2: Linear or Non-linear?**

For inputs x₁(n) and x₂(n):
$$y_1(n) = x_1^2(n) + \frac{1}{x_1^2(n-1)}$$
$$y_2(n) = x_2^2(n) + \frac{1}{x_2^2(n-1)}$$

Weighted sum of outputs:
$$ay_1(n) + by_2(n) = a x_1^2(n) + \frac{a}{x_1^2(n-1)} + b x_2^2(n) + \frac{b}{x_2^2(n-1)}$$

Output due to weighted input:
$$y_3(n) = [ax_1(n) + bx_2(n)]^2 + \frac{1}{[ax_1(n-1) + bx_2(n-1)]^2}$$

$$y_3(n) \neq ay_1(n) + by_2(n)$$

Therefore, the system is **non-linear**.

**Step 3: Causal or Non-causal?**

Output depends only on present and past inputs.

Therefore, the system is **causal**.

**Step 4: Time-invariant or Time-variant?**

Output due to delayed input:
$$y(n,k) = T[x(n-k)] = x^2(n-k) + \frac{1}{x^2(n-1-k)}$$

Delayed output:
$$y(n-k) = x^2(n-k) + \frac{1}{x^2(n-k-1)}$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Dynamic, Non-linear, Causal, Time-invariant}}$$

---

### 1.6.5 Stable and Unstable Systems

**BIBO Stability**: A system is stable if every bounded input produces a bounded output.

**Condition**: The impulse response must be absolutely summable:
$$\sum_{n=-\infty}^{\infty} |h(n)| < \infty$$

**For poles in z-plane**:
1. Poles must lie inside unit circle
2. If pole on unit circle, it must be single order

---

## EXAMPLE 1.18: Check stability of systems

**(a)** $$y(n) = ax(n-7)$$

### Solution:

Let input x(n) = δ(n) (unit impulse)

Then output y(n) = h(n) (impulse response)

$$h(n) = a\delta(n-7)$$

$$h(n) = \begin{cases} a & n = 7 \\ 0 & n \neq 7 \end{cases}$$

**Stability condition**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = |a| < \infty$$

Therefore, the system is **stable if a is finite**.

$$\boxed{\text{Stable if } |a| < \infty}$$

---

**(b)** $$y(n) = x(n) + \frac{1}{2}x(n-1) + \frac{1}{4}x(n-2)$$

### Solution:

Let x(n) = δ(n):

$$h(n) = \delta(n) + \frac{1}{2}\delta(n-1) + \frac{1}{4}\delta(n-2)$$

**Step 1: Find h(n) values**

At n = 0: h(0) = δ(0) + ½δ(-1) + ¼δ(-2) = 1 + 0 + 0 = 1

At n = 1: h(1) = δ(1) + ½δ(0) + ¼δ(-1) = 0 + ½ + 0 = ½

At n = 2: h(2) = δ(2) + ½δ(1) + ¼δ(0) = 0 + 0 + ¼ = ¼

For all other n: h(n) = 0

**Step 2: Check absolute summability**

$$\sum_{n=-\infty}^{\infty} |h(n)| = 1 + \frac{1}{2} + \frac{1}{4} = \frac{7}{4} < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Stable}}$$

---

**(c)** $$h(n) = a^n \text{ for } 0 < n < 11$$

### Solution:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=0}^{11} |a^n| = \frac{1-a^{12}}{1-a}$$

This value is finite for finite a.

Therefore, the system is **stable if a is finite**.

$$\boxed{\text{Stable if } |a| < \infty}$$

---

**(d)** $$h(n) = 2^n u(n)$$

### Solution:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=0}^{\infty} 2^n = \infty$$

The impulse response is not absolutely summable.

Therefore, the system is **unstable**.

$$\boxed{\text{Unstable}}$$

---

**(e)** $$h(n) = u(n)$$

### Solution:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=0}^{\infty} 1 = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Unstable}}$$

---

## EXAMPLE 1.19: Check BIBO stability

**(a)** $$y(n) = ax(n+1) + bx(n-1)$$

### Solution:

Let x(n) = δ(n):

$$h(n) = a\delta(n+1) + b\delta(n-1)$$

**Step 1: Find h(n) values**

At n = -1: h(-1) = aδ(0) + bδ(-2) = a

At n = 1: h(1) = aδ(2) + bδ(0) = b

For all other n: h(n) = 0

**Step 2: Check absolute summability**

$$\sum_{n=-\infty}^{\infty} |h(n)| = |a| + |b|$$

Therefore, the system is **stable if |a| + |b| < ∞**.

$$\boxed{\text{Stable if } |a| + |b| < \infty}$$

---

**(b)** $$y(n) = \text{maximum of } [x(n), x(n-1), x(n-2)]$$

### Solution:

Let x(n) = δ(n):

h(n) = maximum of [δ(n), δ(n-1), δ(n-2)]

At n = 0: h(0) = 1
At n = 1: h(1) = 1
At n = 2: h(2) = 1
At n ≥ 3: h(n) = 0
At n < 0: h(n) = 0

$$\sum_{n=-\infty}^{\infty} |h(n)| = 1 + 1 + 1 = 3 < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Stable}}$$

---

**(c)** $$y(n) = ax(n) + b$$

### Solution:

Let x(n) = δ(n):

$$h(n) = a\delta(n) + b$$

At n = 0: h(0) = a + b
At n ≠ 0: h(n) = b

$$\sum_{n=-\infty}^{\infty} |h(n)| = |a+b| + \sum_{n=-\infty, n\neq 0}^{\infty} |b|$$

This sum diverges.

Therefore, the system is **unstable**.

$$\boxed{\text{Unstable}}$$

---

**(d)** $$y(n) = e^{-x(n)}$$

### Solution:

Let x(n) = δ(n):

$$h(n) = e^{-\delta(n)}$$

At n = 0: h(0) = e^{-1}
At n ≠ 0: h(n) = e^0 = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = e^{-1} + \sum_{n=-\infty, n\neq 0}^{\infty} 1 = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Unstable}}$$

---

**(e)** $$y(n) = ax(n) + bx^2(n-1)$$

### Solution:

Let x(n) = δ(n):

$$h(n) = a\delta(n) + b\delta^2(n-1)$$

Since δ²(n-1) = δ(n-1):

$$h(n) = a\delta(n) + b\delta(n-1)$$

At n = 0: h(0) = a
At n = 1: h(1) = b
At n ≠ 0,1: h(n) = 0

$$\sum_{n=-\infty}^{\infty} |h(n)| = |a| + |b|$$

Therefore, the system is **stable if |a| + |b| < ∞**.

$$\boxed{\text{Stable if } |a| + |b| < \infty}$$

---

## EXAMPLE 1.20: Determine causality and stability

**(a)** $$h(n) = 3^n u(-n)$$

### Solution:

**Causality**: u(-n) exists for -∞ < n ≤ 0. Since h(n) ≠ 0 for n < 0, the system is **non-causal**.

**Stability**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=-\infty}^{0} 3^n = \sum_{n=0}^{\infty} 3^{-n} = \sum_{n=0}^{\infty} \left(\frac{1}{3}\right)^n = \frac{1}{1-1/3} = \frac{3}{2} < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Non-causal, Stable}}$$

---

**(b)** $$h(n) = \delta(n) + \cos n\pi$$

### Solution:

**Causality**: cos(nπ) exists for all n, including n < 0. So the system is **non-causal**.

**Stability**:

For odd n: cos(nπ) = -1
For even n: cos(nπ) = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=-\infty}^{\infty} |\delta(n) + \cos n\pi|$$

For n = 0: |1 + 1| = 2
For n ≠ 0: |cos nπ| = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = 2 + \sum_{n=-\infty, n\neq 0}^{\infty} 1 = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Non-causal, Unstable}}$$

---

**(c)** $$h(n) = e^{-3n}u(n-2)$$

### Solution:

**Causality**: u(n-2) exists only for n ≥ 2. Since h(n) = 0 for n < 0, the system is **causal**.

**Stability**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=2}^{\infty} e^{-3n} = e^{-6} + e^{-9} + e^{-12} + ...$$

This is a geometric series with ratio e^{-3} < 1.

$$\sum_{n=-\infty}^{\infty} |h(n)| = \frac{e^{-6}}{1-e^{-3}} < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Causal, Stable}}$$

---

**(d)** $$y(n) = \cos x(n)$$

### Solution:

**Causality**: Output depends only on present input. Therefore, the system is **causal**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = \cos \delta(n)$$

At n = 0: h(0) = cos 1 = 0.54
At n ≠ 0: h(n) = cos 0 = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = 0.54 + \sum_{n=-\infty, n\neq 0}^{\infty} 1 = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Causal, Unstable}}$$

---

**(e)** $$y(n) = \sum_{k=-\infty}^{n+5} x(k)$$

### Solution:

**Causality**: The system depends on future values (n+5). Therefore, it is **non-causal**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = \sum_{k=-\infty}^{n+5} \delta(k)$$

At n < -5: h(n) = 0
At n ≥ -5: h(n) = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Non-causal, Unstable}}$$

---

**(f)** $$y(n) = \log_{10}|x(n)|$$

### Solution:

**Causality**: Output depends only on present input. Therefore, the system is **causal**.

**Stability**:

$$h(n) = \log_{10}|\delta(n)|$$

At n = 0: h(0) = log₁₀|1| = 0
At n ≠ 0: h(n) = log₁₀|0| = -∞ (not defined)

The impulse response is not well-defined. But if we consider bounded inputs with |x(n)| > 0, any bounded input gives bounded output.

The system is **stable** (BIBO) for |x(n)| > 0.

$$\boxed{\text{Causal, Stable (for |x(n)| > 0)}}$$

---

**(g)** $$h(n) = [u(n) - u(n-15)]2^n$$

### Solution:

**Causality**: h(n) = 0 for n < 0. Therefore, the system is **causal**.

**Stability**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=0}^{14} 2^n = 1 + 2 + 4 + ... + 2^{14} = 2^{15} - 1 < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Causal, Stable}}$$

---

**(h)** $$h(n) = 4^n u(2-n)$$

### Solution:

**Causality**: h(n) ≠ 0 for n < 0 (since u(2-n) exists for n ≤ 2). Therefore, the system is **non-causal**.

**Stability**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=-\infty}^{2} 4^n = \sum_{n=-\infty}^{0} 4^n + \sum_{n=1}^{2} 4^n$$
$$= \sum_{n=0}^{\infty} 4^{-n} + 4 + 16 = \frac{1}{1-1/4} + 20 = \frac{4}{3} + 20 = 21.33 < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Non-causal, Stable}}$$

---

**(i)** $$h(n) = e^{-5|n|}$$

### Solution:

**Causality**: h(n) ≠ 0 for n < 0. Therefore, the system is **non-causal**.

**Stability**:

$$\sum_{n=-\infty}^{\infty} |h(n)| = \sum_{n=-\infty}^{\infty} e^{-5|n|}$$
$$= \sum_{n=-\infty}^{-1} e^{5n} + \sum_{n=0}^{\infty} e^{-5n}$$
$$= \sum_{n=1}^{\infty} e^{-5n} + \sum_{n=0}^{\infty} e^{-5n}$$
$$= \frac{e^{-5}}{1-e^{-5}} + \frac{1}{1-e^{-5}} = \frac{1+e^{-5}}{1-e^{-5}} < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Non-causal, Stable}}$$

---

## EXAMPLE 1.21: Comment on linearity, stability, time-invariance, causality

$$y(n) = 2x(n+1) + [x(n-1)]^2$$

### Solution:

**Linearity**: There is a square term of delayed input [x(n-1)]². Therefore, the system is **non-linear**.

**Causality**: The output depends on x(n+1), which is a future input. Therefore, the system is **non-causal**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = 2\delta(n+1) + [\delta(n-1)]^2 = 2\delta(n+1) + \delta(n-1)$$

At n = -1: h(-1) = 2
At n = 1: h(1) = 1
For all other n: h(n) = 0

$$\sum_{n=-\infty}^{\infty} |h(n)| = 2 + 1 = 3 < \infty$$

Therefore, the system is **stable**.

**Time-invariance**:

Output due to delayed input:
$$y(n,k) = 2x(n+1-k) + [x(n-1-k)]^2$$

Delayed output:
$$y(n-k) = 2x(n-k+1) + [x(n-k-1)]^2$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Non-linear, Stable, Time-invariant, Non-causal}}$$

---

## EXAMPLE 1.22: State whether system is linear, causal, time-invariant, stable

$$y(n) + y(n-1) = x(n) + x(n-2)$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) + y_1(n-1) = x_1(n) + x_1(n-2)$$
$$y_2(n) + y_2(n-1) = x_2(n) + x_2(n-2)$$

Weighted sum:
$$[ay_1(n)+by_2(n)] + [ay_1(n-1)+by_2(n-1)] = [ax_1(n)+bx_2(n)] + [ax_1(n-2)+bx_2(n-2)]$$

The system is **linear**.

**Causality**: Output depends only on present and past inputs and past outputs. Therefore, the system is **causal**.

**Time-invariance**: All coefficients are constants. Therefore, the system is **time-invariant**.

**Stability**:

Let x(n) = δ(n):
$$h(n) + h(n-1) = \delta(n) + \delta(n-2)$$

For n = 0: h(0) + h(-1) = 1 + 0 → h(0) = 1
For n = 1: h(1) + h(0) = 0 + 0 → h(1) = -1
For n = 2: h(2) + h(1) = 0 + 1 → h(2) = 2
For n = 3: h(3) + h(2) = 0 + 0 → h(3) = -2
For n = 4: h(4) + h(3) = 0 + 0 → h(4) = 2

$$\sum_{n=-\infty}^{\infty} |h(n)| = 1 + 1 + 2 + 2 + 2 + ... = \infty$$

Therefore, the system is **unstable**.

$$\boxed{\text{Linear, Causal, Time-invariant, Unstable}}$$

---

## EXAMPLE 1.23: Determine system properties

$$y(n) = nx(n) + x(n+2) + y(n-2)$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) = nx_1(n) + x_1(n+2) + y_1(n-2)$$
$$y_2(n) = nx_2(n) + x_2(n+2) + y_2(n-2)$$

Weighted sum:
$$ay_1(n)+by_2(n) = n[ax_1(n)+bx_2(n)] + [ax_1(n+2)+bx_2(n+2)] + [ay_1(n-2)+by_2(n-2)]$$

The system is **linear**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = n\delta(n) + \delta(n+2) + h(n-2)$$

For n = 0: h(0) = 0 + 0 + h(-2) = 0
For n = -2: h(-2) = (-2)δ(-2) + δ(0) + h(-4) = 0 + 1 + 0 = 1
For n = 2: h(2) = 2δ(2) + δ(4) + h(0) = 0 + 0 + 0 = 0

The impulse response does not decay to zero.

$$\sum_{n=-\infty}^{\infty} |h(n)| = \infty$$

Therefore, the system is **unstable**.

**Causality**: Output depends on x(n+2), a future input. Therefore, the system is **non-causal**.

**Time-invariance**: Coefficient of x(n) is n (function of time). Therefore, the system is **time-varying**.

$$\boxed{\text{Linear, Unstable, Non-causal, Time-varying}}$$

---

## EXAMPLE 1.24: Find linearity, invariance, causality

**(a)** $$y(n) = \frac{ax(n-1)}{x(n)}$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) = \frac{ax_1(n-1)}{x_1(n)}$$
$$y_2(n) = \frac{ax_2(n-1)}{x_2(n)}$$

Weighted sum of outputs:
$$py_1(n)+qy_2(n) = \frac{apx_1(n-1)}{x_1(n)} + \frac{bqx_2(n-1)}{x_2(n)}$$

Output due to weighted input:
$$y_3(n) = \frac{a[px_1(n-1)+qx_2(n-1)]}{px_1(n)+qx_2(n)}$$

$$y_3(n) \neq py_1(n)+qy_2(n)$$

Therefore, the system is **non-linear**.

**Causality**: Output depends on present and past inputs. Therefore, the system is **causal**.

**Time-invariance**:

Output due to delayed input:
$$y(n,k) = \frac{ax(n-1-k)}{x(n-k)}$$

Delayed output:
$$y(n-k) = \frac{ax(n-k-1)}{x(n-k)}$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **time-invariant**.

$$\boxed{\text{Non-linear, Causal, Time-invariant}}$$

---

**(b)** $$y(n) = x(n^2) + x(-n)$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) = x_1(n^2) + x_1(-n)$$
$$y_2(n) = x_2(n^2) + x_2(-n)$$

Weighted sum of outputs:
$$ay_1(n)+by_2(n) = [ax_1(n^2)+bx_2(n^2)] + [ax_1(-n)+bx_2(-n)]$$

Output due to weighted input:
$$y_3(n) = [ax_1(n^2)+bx_2(n^2)] + [ax_1(-n)+bx_2(-n)]$$

$$y_3(n) = ay_1(n)+by_2(n)$$

Therefore, the system is **linear**.

**Causality**:

For n = -2: y(-2) = x(4) + x(2) (depends on future input)
For n = 2: y(2) = x(4) + x(-2)

Therefore, the system is **non-causal**.

**Time-invariance**:

Output due to delayed input:
$$y(n,k) = x(n^2-k) + x(-n-k)$$

Delayed output:
$$y(n-k) = x((n-k)^2) + x(-(n-k))$$

$$y(n,k) \neq y(n-k)$$

Therefore, the system is **time-variant**.

$$\boxed{\text{Linear, Non-causal, Time-variant}}$$

---

## EXAMPLE 1.25: Test causality and stability

$$y(n) = x(n) - x(-n-1) + x(n-1)$$

### Solution:

**Causality**:

For n = -2: y(-2) = x(-2) - x(1) + x(-3)

For negative values of n, output depends on future inputs.

Therefore, the system is **non-causal**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = \delta(n) - \delta(-n-1) + \delta(n-1)$$

At n = -1: h(-1) = 0 - δ(0) + 0 = -1
At n = 0: h(0) = δ(0) - δ(-1) + δ(-1) = 1
At n = 1: h(1) = 0 - δ(-2) + δ(0) = 1
For all other n: h(n) = 0

$$\sum_{n=-\infty}^{\infty} |h(n)| = 1 + 1 + 1 = 3 < \infty$$

Therefore, the system is **stable**.

$$\boxed{\text{Non-causal, Stable}}$$

---

## EXAMPLE 1.26: Linear, shift-invariant, causal?

$$y(n) = 3y^2(n-1) - nx(n) + 4x(n-1) - x(n+1), \quad n \ge 0$$

### Solution:

**(a) Linearity**: There is a square term of delayed output [3y²(n-1)]. Therefore, the system is **non-linear**.

**(b) Shift-invariance**: The coefficient of x(n) is n (function of time). Therefore, the system is **shift-variant**.

**(c) Causality**: The output depends on x(n+1), a future input. Therefore, the system is **non-causal**.

$$\boxed{\text{Non-linear, Shift-variant, Non-causal}}$$

---

## EXAMPLE 1.27: Test for linearity, time-invariance, stability, causality

**(a)** $$y(n) = a^{[x(n)]}$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) = a^{x_1(n)}$$
$$y_2(n) = a^{x_2(n)}$$

Weighted sum of outputs:
$$py_1(n)+qy_2(n) = p a^{x_1(n)} + q a^{x_2(n)}$$

Output due to weighted input:
$$y_3(n) = a^{[px_1(n)+qx_2(n)]}$$

$$y_3(n) \neq py_1(n)+qy_2(n)$$

Therefore, the system is **non-linear**.

**Time-invariance**:

Output due to delayed input:
$$y(n,k) = a^{x(n-k)}$$

Delayed output:
$$y(n-k) = a^{x(n-k)}$$

$$y(n,k) = y(n-k)$$

Therefore, the system is **shift-invariant**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = a^{\delta(n)}$$

At n = 0: h(0) = a
At n ≠ 0: h(n) = a^0 = 1

$$\sum_{n=-\infty}^{\infty} |h(n)| = \infty$$

Therefore, the system is **unstable**.

**Causality**: Output depends only on present input. Therefore, the system is **causal**.

$$\boxed{\text{Non-linear, Shift-invariant, Unstable, Causal}}$$

---

**(b)** $$y(n) = \sin\left(\frac{2\pi bfn}{F}\right)x(n)$$

### Solution:

**Linearity**:

For inputs x₁(n) and x₂(n):
$$y_1(n) = \sin\left(\frac{2\pi bfn}{F}\right)x_1(n)$$
$$y_2(n) = \sin\left(\frac{2\pi bfn}{F}\right)x_2(n)$$

Weighted sum of outputs:
$$py_1(n)+qy_2(n) = \sin\left(\frac{2\pi bfn}{F}\right)[px_1(n)+qx_2(n)]$$

Output due to weighted input:
$$y_3(n) = \sin\left(\frac{2\pi bfn}{F}\right)[px_1(n)+qx_2(n)]$$

$$y_3(n) = py_1(n)+qy_2(n)$$

Therefore, the system is **linear**.

**Time-invariance**:

Output due to delayed input:
$$y(n,k) = \sin\left(\frac{2\pi bfn}{F}\right)x(n-k)$$

Delayed output:
$$y(n-k) = \sin\left(\frac{2\pi bf(n-k)}{F}\right)x(n-k)$$

$$y(n,k) \neq y(n-k)$$

Therefore, the system is **shift-variant**.

**Stability**:

Let x(n) = δ(n):
$$h(n) = \sin\left(\frac{2\pi bfn}{F}\right)\delta(n)$$

At n = 0: h(0) = sin(0)·δ(0) = 0
At n ≠ 0: h(n) = 0

$$\sum_{n=-\infty}^{\infty} |h(n)| = 0$$

Therefore, the system is **stable**.

**Causality**: Output depends only on present input. Therefore, the system is **causal**.

$$\boxed{\text{Linear, Shift-variant, Stable, Causal}}$$

---

## 1.7 REPRESENTATION OF AN ARBITRARY SEQUENCE

Any sequence can be represented as sum of shifted and weighted impulses:

$$x(n) = \sum_{k=-\infty}^{\infty} x(k)\delta(n-k)$$

---

## EXAMPLE 1.28: Represent sequence as sum of shifted impulses

**Given**: $$x(n) = \{3, 1, -2, 1, 4, 2, 5, 1\} \text{ with } \uparrow \text{ at } n=0$$

### Solution:

Values at n = -3, -2, -1, 0, 1, 2, 3, 4 respectively.

$$x(n) = x(-3)\delta(n+3) + x(-2)\delta(n+2) + x(-1)\delta(n+1) + x(0)\delta(n) + x(1)\delta(n-1) + x(2)\delta(n-2) + x(3)\delta(n-3) + x(4)\delta(n-4)$$

$$= 3\delta(n+3) + \delta(n+2) - 2\delta(n+1) + \delta(n) + 4\delta(n-1) + 2\delta(n-2) + 5\delta(n-3) + \delta(n-4)$$

$$\boxed{x(n) = 3\delta(n+3) + \delta(n+2) - 2\delta(n+1) + \delta(n) + 4\delta(n-1) + 2\delta(n-2) + 5\delta(n-3) + \delta(n-4)}$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. Define a signal.**

A signal is defined as a single-valued function of one or more independent variables which contain some information.

---

**2. What is one-dimensional signal?**

A signal which depends on only one independent variable is called a one-dimensional signal.

---

**3. What is signal modelling?**

The representation of a signal by the mathematical expression is known as signal modelling.

---

**4. What are the different types of representing discrete-time signals?**

1. Graphical representation
2. Functional representation
3. Tabular representation
4. Sequence representation

---

**5. Define unit step sequence.**

$$u(n) = \begin{cases} 1 & n \ge 0 \\ 0 & n < 0 \end{cases}$$

---

**6. Define unit ramp sequence.**

$$r(n) = \begin{cases} n & n \ge 0 \\ 0 & n < 0 \end{cases} = nu(n)$$

---

**7. Define unit parabolic sequence.**

$$p(n) = \begin{cases} \frac{n^2}{2} & n \ge 0 \\ 0 & n < 0 \end{cases} = \frac{n^2}{2}u(n)$$

---

**8. Define unit impulse sequence.**

$$\delta(n) = \begin{cases} 1 & n = 0 \\ 0 & n \neq 0 \end{cases}$$

---

**9. Write the properties of unit impulse function.**

1. $$\delta(n) = u(n) - u(n-1)$$
2. $$\delta(n-k) = \begin{cases} 1 & n = k \\ 0 & n \neq k \end{cases}$$
3. $$x(n) = \sum_{k=-\infty}^{\infty} x(k)\delta(n-k)$$
4. $$\sum_{n=-\infty}^{\infty} x(n)\delta(n-n_0) = x(n_0)$$

---

**10. Define a sinusoidal signal.**

$$x(n) = A\sin(\omega_0 n + \phi)$$

---

**11. Define a real exponential signal.**

$$x(n) = a^n u(n)$$

---

**12. Define complex exponential signal.**

$$x(n) = a^n e^{j(\omega_0 n + \phi)}$$

---

**13. What are the basic operations on discrete-time signals?**

1. Time shifting
2. Time reversal
3. Time scaling
4. Amplitude scaling
5. Signal addition
6. Signal multiplication

---

**14. How are discrete-time signals classified?**

1. Deterministic and random signals
2. Periodic and aperiodic signals
3. Energy and power signals
4. Even and odd signals
5. Causal and non-causal signals

---

**15. Distinguish between deterministic and random signals.**

A deterministic signal has no uncertainty and can be represented by a mathematical equation. A random signal has uncertainty and cannot be represented by a mathematical equation.

---

**16. Distinguish between periodic and aperiodic signals.**

A periodic signal satisfies $x(n) = x(n+N)$ for all n. An aperiodic signal does not satisfy this condition.

---

**17. What is the condition for a discrete-time sinusoidal sequence to be periodic?**

The fundamental frequency $\omega_0$ must be a rational multiple of $2\pi$.

---

**18. Distinguish between energy and power signals.**

An energy signal has finite energy and zero power. A power signal has finite power and infinite energy.

---

**19. Write the expressions for total energy E and average power P.**

$$E = \sum_{n=-\infty}^{\infty} |x(n)|^2$$
$$P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} |x(n)|^2$$

---

**20. Distinguish between even and odd signals.**

Even signal: $x(-n) = x(n)$ for all n.
Odd signal: $x(-n) = -x(n)$ for all n.

---

**21. Write the expressions for even and odd parts of a signal.**

$$x_e(n) = \frac{1}{2}[x(n) + x(-n)]$$
$$x_o(n) = \frac{1}{2}[x(n) - x(-n)]$$

---

**22. Distinguish between causal and non-causal signals.**

Causal signal: $x(n) = 0$ for n < 0.
Non-causal signal: $x(n) \neq 0$ for n < 0.

---

**23. Define a system.**

A system is defined as an entity that acts on an input signal and transforms it into an output signal.

---

**24. Define a discrete-time system.**

A discrete-time system is a system which transforms discrete-time input signals into discrete-time output signals.

---

**25. Define a static system.**

A static system is one in which the output at any instant depends only on the input applied at that instant.

---

**26. Define a dynamic system.**

A dynamic system is one in which the output at any instant depends on past or future inputs.

---

**27. Define a causal system.**

A causal system is one whose output at any time n depends only on the present and past values of the input.

---

**28. Define a linear system.**

A linear system obeys the principle of superposition and homogeneity.

---

**29. What is superposition property?**

A system produces output $y_1(n)+y_2(n)$ for input $x_1(n)+x_2(n)$.

---

**30. Define a shift-invariant system.**

A shift-invariant system is one whose input/output characteristics do not change with time.

---

**31. Define a stable system.**

A stable system produces a bounded output for every bounded input.

---

**32. What is the condition for BIBO stability?**

$$\sum_{n=-\infty}^{\infty} |h(n)| < \infty$$

---

# FILL IN THE BLANKS

1. If a signal depends on only one independent variable, it is called a **one-dimensional** signal.

2. The representation of a signal by mathematical expression is known as **signal modelling**.

3. Discrete-time signals are **discrete** in time and **continuous** in amplitude.

4. The signals that are discrete in time and quantized in amplitude are called **digital** signals.

5. The **time reversal** of a signal can be obtained by folding the signal about n = 0.

6. A signal which can be described by a mathematical equation is called a **deterministic** signal.

7. A signal which cannot be represented by a mathematical equation is called a **random** signal.

8. In the case of **discrete-time** signals, not all the sinusoidal signals are periodic.

9. For an even signal, $x(-n) =$ **$x(n)$** for all n.

10. For an odd signal, $x(-n) =$ **$-x(n)$** for all n.

11. For an energy signal, E = **finite** and P = **zero**.

12. For a power signal, P = **finite** and E = **infinity**.

13. For an anti-causal signal, $x(n) = 0$ for **$n > 0$**.

14. For a static system, the output does not depend on the **past and future** values of input.

15. For a dynamic system, the output depends on the **past** and/or **future** values of input.

16. Static systems are also called **memoryless** systems.

17. Dynamic systems are also called **memory** systems.

18. A causal system is one whose output depends on **present and past** values of input.

19. A non-causal system is one whose output depends on **future** values of input.

20. A causal system is also known as a **non-anticipative** system.

21. A non-causal system is also known as an **anticipative** system.

22. A **non-causal** system is definitely a dynamic system.

23. A **linear** system obeys the principle of superposition.

24. A **non-linear** system does not obey the principle of superposition.

25. An LTI system is one which satisfies the properties of **linearity** and **time-invariance**.

26. For a time-invariant system, its **input/output characteristics** do not change with time.

27. For a time-variant system, its **input/output characteristics** change with time.

28. A system is said to be stable if every **bounded** input produces a bounded output.

29. For a discrete-time system to be stable, its impulse response must be **absolutely summable**.

30. A system which has a unique relation between its input and output is called **invertible**.

31. A system which does not have a unique relation between its input and output is called **non-invertible**.

---

# OBJECTIVE TYPE QUESTIONS

**1. A signal can be represented in**
(a) time domain (b) frequency domain (c) both (a) and (b) (d) none of these

**Answer: (c) both (a) and (b)**

---

**2. δ(n) =**
(a) $u(n) + u(n-1)$ (b) $u(n)u(n-1)$ (c) $u(n) - u(n-1)$ (d) $u(n-1) - u(n)$

**Answer: (c) $u(n) - u(n-1)$**

---

**3. A deterministic signal has**
(a) no uncertainty (b) uncertainty (c) partial uncertainty (d) none of these

**Answer: (a) no uncertainty**

---

**4. A random signal has**
(a) no uncertainty (b) uncertainty (c) partial uncertainty (d) none of these

**Answer: (b) uncertainty**

---

**5. The fundamental period of a discrete-time complex exponential sequence is N =**
(a) $2\pi/\omega_0$ (b) $(2\pi/\omega_0)\omega_0$ (c) $(2\pi/\omega_0)m$ (d) $2\pi m\omega_0$

**Answer: (c) $(2\pi/\omega_0)m$**

---

**6. A signal is an energy signal if**
(a) E = 0, P = 0 (b) E = ∞, P = finite (c) E = finite, P = 0 (d) E = finite, P = ∞

**Answer: (c) E = finite, P = 0**

---

**7. A signal is a power signal if**
(a) P = finite, E = 0 (b) P = finite, E = ∞ (c) P = finite, E = finite (d) P = ∞, E = ∞

**Answer: (b) P = finite, E = ∞**

---

**8. A system whose output depends on future inputs is a**
(a) static system (b) dynamic system (c) non-causal system (d) both (b) and (c)

**Answer: (d) both (b) and (c)**

---

**9. $y(n) = x(n+2)$ is a**
(a) linear system (b) dynamic system (c) both linear and dynamic system (d) non-linear system

**Answer: (c) both linear and dynamic system**

---

**10. $y(n) = x(2n)$ is a**
(a) time-invariant system (b) time varying, dynamic system (c) linear, time varying, dynamic system (d) linear, time-invariant, static system

**Answer: (c) linear, time varying, dynamic system**

---

**11. $y(n) = x(-n)$ is a**
(a) non-causal system (b) linear, causal, time-invariant system (c) linear, non-causal, time-invariant system (d) linear, non-causal, time varying, dynamic system

**Answer: (d) linear, non-causal, time varying, dynamic system**

---

**12. $y(n) = x(n) + nx(n-1)$ is a**
(a) dynamic system (b) causal system (c) linear system (d) all of these

**Answer: (d) all of these**

---

**13. A system which has a unique relation between its input and output is called**
(a) linear system (b) causal system (c) time-invariant system (d) invertible system

**Answer: (d) invertible system**

---

# PROBLEMS

1. Evaluate the following:
   (a) $\sum_{n=-\infty}^{\infty} e^{2n}\delta(n-2)$
   (b) $\sum_{n=-\infty}^{\infty} \delta(n)5^n$

2. Sketch the following signals:
   (a) $u(n+2) - u(n)$
   (b) $u(-n+2) - u(-n-2)$

3. Determine whether the following signals are periodic or not. If periodic, determine the fundamental period:
   (a) $\cos(0.04\pi n)$
   (b) $e^{i(\pi/3)n}$

4. Find which of the following signals are energy signals, power signals, neither energy nor power signals. Calculate the power and energy in each case:
   (a) $\left(\frac{1}{3}\right)^n u(n)$
   (b) $u(n) - u(n-4)$

5. Find which of the following signals are causal or non-causal:
   (a) $u(-2n)$
   (b) $u(n+3) - u(n+1)$
   (c) $e^{3n}$

6. Find the even and odd components of the following signals:
   (a) $x(n) = \{2, 3, 4, 5, 6\}$ with $\uparrow$ at n=0
   (b) $x(n) = \{2, 1, -3, 1, 2\}$ with $\uparrow$ at n=0

7. Find whether the following signal is even or odd:
   $$u(-n+2)u(n+2)$$

8. Find whether the following systems are dynamic or not:
   (a) $y(n) = nx^2(n)$
   (b) $y(n) = x(n) + x(n+2)$
   (c) $y(n) = nx(2n)$

9. Check whether the following systems are causal or not:
   (a) $y(n) = x(n) + \frac{1}{2x(n-2)}$
   (b) $y(n) = x(-2n)$
   (c) $y(n) = \sum_{k=-\infty}^{n+2} x(k)$

10. Check whether the following systems are linear or not:
    (a) $y(n) = Ax(n) + B$
    (b) $y(n) = 2x(n) + \frac{1}{x(n-3)}$
    (c) $y(n) = n^2 x(2n)$

11. Determine whether the following systems are time-invariant or not:
    (a) $y(n) = x(n) + nx(n-3)$
    (b) $y(n) = x^2(n-2)$
    (c) $y(n) = \sin[x(n)]$

12. Determine whether the following systems are stable or not:
    (a) $y(n) = 8x(n-4)$
    (b) $y(n) = nu(n) + \delta(n-2)$
    (c) $h(n) = 2^{-n}u(n)$

13. Check whether the following systems are:
    (i) Static or dynamic
    (ii) Linear or non-linear
    (iii) Causal or non-causal
    (iv) Time-invariant or time-variant
    (a) $y(n) = \sum_{k=-\infty}^{n+4} x(k)$
    (b) $y(n) = |x(n)|$
    (c) $y(n) = 2x(n+2) - x(n-2)$

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| Unit Step | $u(n) = \begin{cases} 1 & n \ge 0 \\ 0 & n < 0 \end{cases}$ |
| Unit Ramp | $r(n) = n u(n)$ |
| Unit Parabolic | $p(n) = \frac{n^2}{2}u(n)$ |
| Unit Impulse | $\delta(n) = \begin{cases} 1 & n = 0 \\ 0 & n \neq 0 \end{cases}$ |
| Impulse Property | $\sum_{n=-\infty}^{\infty} x(n)\delta(n-n_0) = x(n_0)$ |
| Impulse-Step Relation | $\delta(n) = u(n) - u(n-1)$ |
| Sequence Representation | $x(n) = \sum_{k=-\infty}^{\infty} x(k)\delta(n-k)$ |
| Periodicity Condition | $x(n) = x(n+N)$ |
| Sinusoid Period | $N = \frac{2\pi}{\omega}m$, $\frac{\omega}{2\pi}$ rational |
| Energy | $E = \sum_{n=-\infty}^{\infty} |x(n)|^2$ |
| Power | $P = \lim_{N \to \infty} \frac{1}{2N+1} \sum_{n=-N}^{N} |x(n)|^2$ |
| Even Part | $x_e(n) = \frac{1}{2}[x(n) + x(-n)]$ |
| Odd Part | $x_o(n) = \frac{1}{2}[x(n) - x(-n)]$ |
| Stability Condition | $\sum_{n=-\infty}^{\infty} |h(n)| < \infty$ |
| Linearity Test | $T[ax_1(n)+bx_2(n)] = aT[x_1(n)] + bT[x_2(n)]$ |
| Shift-Invariance | $T[x(n-k)] = y(n-k)$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 1.1 | Summations using impulse function (5 parts) | ✅ |
| 1.2 | Sketching signals (2 parts) | ✅ |
| 1.3 | Express signals as sum of singular functions (2 parts) | ✅ |
| 1.4 | Complex exponential periodicity proof | ✅ |
| 1.5 | Periodic condition for sampled signal | ✅ |
| 1.6 | Condition for sinusoidal periodicity | ✅ |
| 1.7 | Determine periodicity (8 parts: a-h) | ✅ |
| 1.8 | Find energy/power signals (6 parts: a-f) | ✅ |
| 1.9 | Energy and power of finite duration signal | ✅ |
| 1.10 | Determine causal/non-causal signals (3 parts) | ✅ |
| 1.11 | Find even and odd components (4 parts: a-d) | ✅ |
| 1.12 | Determine if systems are dynamic (3 parts) | ✅ |
| 1.13 | Check if systems are causal (4 parts) | ✅ |
| 1.14 | Check if systems are linear (6 parts: a-f) | ✅ |
| 1.15 | Determine time-invariance (4 parts: a-d) | ✅ |
| 1.16 | Show systems are LSI (2 parts) | ✅ |
| 1.17 | Check system properties (5 parts: a-e) | ✅ |
| 1.18 | Check stability (5 parts: a-e) | ✅ |
| 1.19 | Check BIBO stability (5 parts: a-e) | ✅ |
| 1.20 | Determine causality and stability (10 parts: a-j) | ✅ |
| 1.21 | Comment on linearity, stability, TI, causality | ✅ |
| 1.22 | State system properties | ✅ |
| 1.23 | Determine linear, stable, causal, TI | ✅ |
| 1.24 | Find linearity, invariance, causality (2 parts) | ✅ |
| 1.25 | Test causality and stability | ✅ |
| 1.26 | Linear, shift-invariant, causal? | ✅ |
| 1.27 | Test linearity, TI, stability, causality (2 parts) | ✅ |
| 1.28 | Represent sequence as shifted impulses | ✅ |

---

## How to Save This File

1. Copy all the text from the code block above
2. Open a text editor (Notepad, VS Code, etc.)
3. Paste the content
4. Save the file with the name `Chapter_1_Signals_and_Systems.md`
5. The file will be properly formatted with headers, tables, equations, and examples
