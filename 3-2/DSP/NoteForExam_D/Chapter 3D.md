# Chapter 3: Z-Transforms

## 3.1 INTRODUCTION

The Z-transform plays the same role for discrete-time systems as the Laplace transform plays for continuous-time systems.

### Definition:

**Bilateral (Two-sided) Z-transform**:
$$X(z) = \sum_{n=-\infty}^{\infty} x(n)z^{-n}$$

**Unilateral (One-sided) Z-transform**:
$$X(z) = \sum_{n=0}^{\infty} x(n)z^{-n}$$

where $z$ is a complex variable.

### Advantages of Z-transform:

1. Converts difference equations into algebraic equations
2. Convolution in time domain → multiplication in z-domain
3. Exists for signals where DTFT does not exist
4. Frequency response can be determined (evaluate on unit circle)

### Region of Convergence (ROC):

The set of values of z (or points in z-plane) for which X(z) converges.

---

## 3.2 RELATION BETWEEN DTFT AND Z-TRANSFORM

$$X(z) = \sum_{n=-\infty}^{\infty} x(n)z^{-n} = \sum_{n=-\infty}^{\infty} [x(n)r^{-n}]e^{-j\omega n}$$

**The Z-transform of x(n) is the DTFT of x(n)r⁻ⁿ.**

When $r = 1$ (i.e., $z = e^{j\omega}$):
$$X(e^{j\omega}) = \sum_{n=-\infty}^{\infty} x(n)e^{-j\omega n}$$

**DTFT is the Z-transform evaluated on the unit circle.**

---

## EXAMPLE 3.1: ROC for causal sequences

**Prove**: For causal sequences, the ROC is the exterior of a circle of radius r.

### Solution:

Consider a causal sequence:
$$x(n) = r^n u(n)$$

$$X(z) = \sum_{n=-\infty}^{\infty} r^n u(n)z^{-n} = \sum_{n=0}^{\infty} (rz^{-1})^n$$

This geometric series converges when:
$$|rz^{-1}| < 1 \Rightarrow |z| > r$$

**Conclusion**: For causal sequences, the ROC is the exterior of a circle of radius r.

$$\boxed{\text{Causal: ROC is } |z| > r}$$

---

## EXAMPLE 3.2: Same X(z), different ROC

**Given**:
(a) $x(n) = a^n u(n)$
(b) $x(n) = -a^n u(-n-1)$

### Solution:

**(a) For $x(n) = a^n u(n)$:**

$$X(z) = \sum_{n=0}^{\infty} a^n z^{-n} = \sum_{n=0}^{\infty} (az^{-1})^n = \frac{1}{1-az^{-1}} = \frac{z}{z-a}$$

**ROC**: $|z| > |a|$ (exterior of circle)

$$\boxed{X(z) = \frac{z}{z-a}, \quad |z| > |a|}$$

---

**(b) For $x(n) = -a^n u(-n-1)$:**

$$X(z) = -\sum_{n=-\infty}^{-1} a^n z^{-n}$$

Let $m = -n$, then $n = -m$:

$$X(z) = -\sum_{m=1}^{\infty} a^{-m} z^{m} = -\sum_{m=1}^{\infty} \left(\frac{z}{a}\right)^m$$

$$= -\left[\frac{z/a}{1-z/a}\right] = -\frac{z}{a-z} = \frac{z}{z-a}$$

**ROC**: $|z| < |a|$ (interior of circle)

$$\boxed{X(z) = \frac{z}{z-a}, \quad |z| < |a|}$$

**Conclusion**: Both sequences have the same X(z) but different ROCs. ROC is essential to find the correct inverse Z-transform.

---

## EXAMPLE 3.3: Z-transform of $\left(\frac{1}{4}\right)^n \cos\left(\frac{\pi}{3}n\right)u(n)$

### Solution:

$$x(n) = \left(\frac{1}{4}\right)^n \cos\left(\frac{\pi}{3}n\right)u(n)$$

**Step 1: Use Euler's identity**

$$\cos\left(\frac{\pi}{3}n\right) = \frac{e^{j\pi n/3} + e^{-j\pi n/3}}{2}$$

$$x(n) = \frac{1}{2}\left[\left(\frac{1}{4}e^{j\pi/3}\right)^n + \left(\frac{1}{4}e^{-j\pi/3}\right)^n\right]u(n)$$

**Step 2: Apply Z-transform**

$$X(z) = \frac{1}{2}\left[\frac{1}{1 - \frac{1}{4}e^{j\pi/3}z^{-1}} + \frac{1}{1 - \frac{1}{4}e^{-j\pi/3}z^{-1}}\right]$$

$$= \frac{1}{2}\left[\frac{z}{z - \frac{1}{4}e^{j\pi/3}} + \frac{z}{z - \frac{1}{4}e^{-j\pi/3}}\right]$$

**Step 3: Simplify**

$$X(z) = \frac{z\left[z - \frac{1}{4}(e^{j\pi/3} + e^{-j\pi/3})\right]}{\left(z - \frac{1}{4}e^{j\pi/3}\right)\left(z - \frac{1}{4}e^{-j\pi/3}\right)}$$

$$= \frac{z\left(z - \frac{1}{4}\cos\frac{\pi}{3}\right)}{z^2 - \frac{1}{2}z\cos\frac{\pi}{3} + \frac{1}{16}}$$

$$= \frac{z\left(z - \frac{1}{8}\right)}{z^2 - \frac{1}{4}z + \frac{1}{16}}$$

$$\boxed{X(z) = \frac{z(z - \frac{1}{8})}{z^2 - \frac{1}{4}z + \frac{1}{16}}}$$

**ROC**: $|z| > \frac{1}{4}$

**Poles**: at $z = \frac{1}{4}e^{\pm j\pi/3}$
**Zeros**: at $z = 0$ and $z = \frac{1}{8}$

---

## EXAMPLE 3.4: Z-transform of two-sided sequence

**Given**: $x(n) = \left(\frac{5}{6}\right)^n u(-n-1) + \left(\frac{1}{4}\right)^n u(n)$

### Solution:

$$X(z) = \sum_{n=-\infty}^{-1} \left(\frac{5}{6}\right)^n z^{-n} + \sum_{n=0}^{\infty} \left(\frac{1}{4}\right)^n z^{-n}$$

**First term**: Let $m = -n$, $n = -m$

$$\sum_{n=-\infty}^{-1} \left(\frac{5}{6}\right)^n z^{-n} = \sum_{m=1}^{\infty} \left(\frac{5}{6}\right)^{-m} z^{m} = \sum_{m=1}^{\infty} \left(\frac{6}{5}\right)^m z^{m}$$

$$= \frac{\frac{6}{5}z}{1-\frac{6}{5}z} = -\frac{6z}{5-6z}$$

**Second term**:

$$\sum_{n=0}^{\infty} \left(\frac{1}{4}\right)^n z^{-n} = \frac{1}{1-\frac{1}{4}z^{-1}} = \frac{z}{z-\frac{1}{4}}$$

$$X(z) = -\frac{6z}{5-6z} + \frac{z}{z-\frac{1}{4}}$$

$$\boxed{X(z) = -\frac{6z}{5-6z} + \frac{z}{z-\frac{1}{4}}}$$

**ROC**: $\frac{1}{4} < |z| < \frac{5}{6}$

---

## EXAMPLE 3.5: Z-transform of finite duration sequence

**Given**: $x(n) = \begin{cases} a^n & 0 \le n \le N-1 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$X(z) = \sum_{n=0}^{N-1} a^n z^{-n} = \sum_{n=0}^{N-1} (az^{-1})^n$$

$$= \frac{1-(az^{-1})^N}{1-az^{-1}} = \frac{1-a^N z^{-N}}{1-az^{-1}}$$

$$= \frac{z^N - a^N}{z^{N-1}(z-a)}$$

$$\boxed{X(z) = \frac{z^N - a^N}{z^{N-1}(z-a)}}$$

---

## EXAMPLE 3.6: ROC of causal finite sequence

**Given**: $x(n) = \{1, 0, -2, 3, 5, 4\}$ with $\uparrow$ at n=0

### Solution:

$$X(z) = x(0) + x(1)z^{-1} + x(2)z^{-2} + x(3)z^{-3} + x(4)z^{-4} + x(5)z^{-5}$$

$$= 1 + 0z^{-1} - 2z^{-2} + 3z^{-3} + 5z^{-4} + 4z^{-5}$$

$$= 1 - 2z^{-2} + 3z^{-3} + 5z^{-4} + 4z^{-5}$$

**ROC**: Entire z-plane except z = 0

$$\boxed{X(z) = 1 - 2z^{-2} + 3z^{-3} + 5z^{-4} + 4z^{-5}}$$

---

## EXAMPLE 3.7: Z-transform of anticausal finite sequence

**Given**: $x(n) = \{5, 3, -2, 0, 4, -3\}$ with $\uparrow$ at n=0

### Solution:

$$x(n) = 5\delta(n) + 3\delta(n-1) - 2\delta(n-2) + 0\delta(n-3) + 4\delta(n-4) - 3\delta(n-5)$$

$$X(z) = 5 + 3z^{-1} - 2z^{-2} + 4z^{-4} - 3z^{-5}$$

**ROC**: Entire z-plane except z = 0

$$\boxed{X(z) = 5 + 3z^{-1} - 2z^{-2} + 4z^{-4} - 3z^{-5}}$$

---

## EXAMPLE 3.8: Z-transform of anticausal sequence

**Given**: $x(n) = \{4, 2, -3, 1, -2, 1\}$ with $\uparrow$ at n=0

### Solution:

Values: $x(-5) = 4, x(-4) = 2, x(-3) = -3, x(-2) = 1, x(-1) = -2, x(0) = 1$

$$X(z) = x(-5)z^5 + x(-4)z^4 + x(-3)z^3 + x(-2)z^2 + x(-1)z + x(0)$$

$$= 4z^5 + 2z^4 - 3z^3 + z^2 - 2z + 1$$

**ROC**: Entire z-plane except z = ∞

$$\boxed{X(z) = 4z^5 + 2z^4 - 3z^3 + z^2 - 2z + 1}$$

---

## EXAMPLE 3.9: Z-transform of two-sided finite sequence

**Given**: $x(n) = \{2, 1, -3, 0, 4, 3, -2, 1, 5\}$ with $\uparrow$ at n=0

### Solution:

Values: $x(-4)=2, x(-3)=1, x(-2)=-3, x(-1)=0, x(0)=4, x(1)=3, x(2)=-2, x(3)=1, x(4)=5$

$$X(z) = 2z^4 + z^3 - 3z^2 + 0z + 4 + 3z^{-1} - 2z^{-2} + z^{-3} + 5z^{-4}$$

$$= 2z^4 + z^3 - 3z^2 + 4 + 3z^{-1} - 2z^{-2} + z^{-3} + 5z^{-4}$$

**ROC**: Entire z-plane except z = 0 and z = ∞

$$\boxed{X(z) = 2z^4 + z^3 - 3z^2 + 4 + 3z^{-1} - 2z^{-2} + z^{-3} + 5z^{-4}}$$

---

## EXAMPLE 3.10: Z-transform of rectangular pulse

**(a)** $x(n) = u(n) - u(n-4)$

### Solution:

$x(n) = 1$ for $0 \le n \le 3$

$$X(z) = 1 + z^{-1} + z^{-2} + z^{-3}$$

**ROC**: Entire z-plane except z = 0

$$\boxed{X(z) = 1 + z^{-1} + z^{-2} + z^{-3}}$$

---

**(b)** $x(n) = u(-n) - u(-n-3)$

### Solution:

$x(n) = 1$ for $-2 \le n \le 0$

$$X(z) = z^2 + z + 1$$

**ROC**: Entire z-plane except z = ∞

$$\boxed{X(z) = z^2 + z + 1}$$

---

**(c)** $x(n) = u(2-n) - u(-n-2)$

### Solution:

$x(n) = 1$ for $-1 \le n \le 2$

$$X(z) = z + 1 + z^{-1} + z^{-2}$$

**ROC**: Entire z-plane except z = 0 and z = ∞

$$\boxed{X(z) = z + 1 + z^{-1} + z^{-2}}$$

---

## 3.4 PROPERTIES OF ROC

1. ROC is a ring or disk in z-plane centred at origin
2. ROC cannot contain any poles
3. Causal sequence → ROC is $|z| > \alpha$ (exterior of circle)
4. Anticausal sequence → ROC is $|z| < \alpha$ (interior of circle)
5. Two-sided sequence → ROC is a ring $\alpha < |z| < \beta$
6. Stable LTI system → ROC contains unit circle
7. Finite duration causal → ROC entire z-plane except z = 0
8. Finite duration anticausal → ROC entire z-plane except z = ∞
9. Only $\delta(n)$ has ROC as entire z-plane
10. ROC must be a connected region

---

## 3.5 PROPERTIES OF Z-TRANSFORM

### 1. Linearity:
$$Z[ax_1(n) + bx_2(n)] = aX_1(z) + bX_2(z)$$

### 2. Time Shifting:
$$Z[x(n-m)] = z^{-m}X(z)$$

### 3. Multiplication by Exponential:
$$Z[a^n x(n)] = X(z/a)$$

### 4. Time Reversal:
$$Z[x(-n)] = X(z^{-1})$$

### 5. Time Expansion:
$$Z[x_k(n)] = X(z^k)$$

where $x_k(n) = \begin{cases} x(n/k) & n \text{ multiple of } k \\ 0 & \text{otherwise} \end{cases}$

### 6. Differentiation in z-domain:
$$Z[nx(n)] = -z\frac{dX(z)}{dz}$$

### 7. Convolution:
$$Z[x(n) * h(n)] = X(z)H(z)$$

### 8. Initial Value Theorem:
$$x(0) = \lim_{z \to \infty} X(z)$$

### 9. Final Value Theorem:
$$x(\infty) = \lim_{z \to 1} (z-1)X(z)$$

### 10. Parseval's Theorem:
$$\sum_{n=-\infty}^{\infty} |x(n)|^2 = \frac{1}{2\pi j}\oint X(z)X^*(1/z^*)z^{-1}dz$$

---

## EXAMPLE 3.11: Using properties of Z-transform

**(a)** $x(n) = u(-n)$

### Solution:

We know $Z[u(n)] = \frac{z}{z-1}$, ROC: $|z| > 1$

Using time reversal: $Z[u(-n)] = Z[u(n)]$ with $z \to z^{-1}$

$$Z[u(-n)] = \frac{z^{-1}}{z^{-1}-1} = \frac{1}{1-z}$$

**ROC**: $|z| < 1$

$$\boxed{Z[u(-n)] = \frac{1}{1-z}, \quad |z| < 1}$$

---

**(b)** $x(n) = u(-n-1)$

### Solution:

$Z[u(-n-1)] = Z[u(-(n+1))]$

Using time shifting: $Z[u(-n-1)] = z \cdot Z[u(-n)]$

$$= z \cdot \frac{1}{1-z} = \frac{z}{1-z}$$

$$\boxed{Z[u(-n-1)] = \frac{z}{1-z}}$$

---

**(c)** $x(n) = u(-n-2)$

### Solution:

$$Z[u(-n-2)] = z^2 \cdot Z[u(-n)] = \frac{z^2}{1-z}$$

$$\boxed{Z[u(-n-2)] = \frac{z^2}{1-z}}$$

---

**(d)** $x(n) = 2^n u(n-2)$

### Solution:

$$Z[2^n u(n)] = \frac{z}{z-2}$$

Using time shifting:
$$Z[2^n u(n-2)] = z^{-2} \cdot \frac{z}{z-2} = \frac{1}{z(z-2)}$$

$$\boxed{Z[2^n u(n-2)] = \frac{1}{z(z-2)}}$$

---

## EXAMPLE 3.12: Using properties

**(a)** $x(n) = \alpha^n u(n-2)$

### Solution:

$$Z[\alpha^n u(n)] = \frac{z}{z-\alpha}$$

$$Z[\alpha^n u(n-2)] = z^{-2} \cdot \frac{z}{z-\alpha} = \frac{1}{z(z-\alpha)}$$

$$\boxed{Z[\alpha^n u(n-2)] = \frac{1}{z(z-\alpha)}}$$

---

**(b)** $x(n) = \begin{cases} 1 & 0 \le n \le N-1 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$x(n) = u(n) - u(n-N)$

$$Z[u(n)] = \frac{z}{z-1}$$

$$Z[u(n-N)] = z^{-N} \cdot \frac{z}{z-1} = \frac{z^{1-N}}{z-1}$$

$$X(z) = \frac{z}{z-1} - \frac{z^{1-N}}{z-1} = \frac{z(1-z^{-N})}{z-1}$$

$$\boxed{X(z) = \frac{z(1-z^{-N})}{z-1}}$$

---

## EXAMPLE 3.13: Using differentiation in z-domain

**Given**: $x(n) = 2^n n\sin\left(\frac{\pi}{2}n\right)u(n)$

### Solution:

**Step 1: Find Z-transform of $\sin\left(\frac{\pi}{2}n\right)u(n)$**

$$Z\left[\sin\left(\frac{\pi}{2}n\right)u(n)\right] = \frac{z\sin(\pi/2)}{z^2 - 2z\cos(\pi/2) + 1} = \frac{z}{z^2 + 1}$$

**Step 2: Multiplication by exponential**

$$Z\left[2^n \sin\left(\frac{\pi}{2}n\right)u(n)\right] = \frac{(z/2)}{(z/2)^2 + 1} = \frac{2z}{z^2 + 4}$$

**Step 3: Differentiation property**

$$Z\left[n \cdot 2^n \sin\left(\frac{\pi}{2}n\right)u(n)\right] = -z\frac{d}{dz}\left[\frac{2z}{z^2+4}\right]$$

$$= -z\left[\frac{2(z^2+4) - 2z(2z)}{(z^2+4)^2}\right] = -z\left[\frac{2z^2+8-4z^2}{(z^2+4)^2}\right]$$

$$= -z\left[\frac{8-2z^2}{(z^2+4)^2}\right] = \frac{2z(z^2-4)}{(z^2+4)^2}$$

$$\boxed{X(z) = \frac{2z(z^2-4)}{(z^2+4)^2}}$$

---

## EXAMPLE 3.14: Using convolution property

**Given**: $x(n) = \left(\frac{1}{2}\right)^n u(n) * \left(\frac{1}{4}\right)^n u(n)$

### Solution:

$$X_1(z) = Z\left[\left(\frac{1}{2}\right)^n u(n)\right] = \frac{z}{z-\frac{1}{2}}$$

$$X_2(z) = Z\left[\left(\frac{1}{4}\right)^n u(n)\right] = \frac{z}{z-\frac{1}{4}}$$

Using convolution property:
$$X(z) = X_1(z)X_2(z) = \frac{z^2}{(z-\frac{1}{2})(z-\frac{1}{4})}$$

$$\boxed{X(z) = \frac{z^2}{(z-\frac{1}{2})(z-\frac{1}{4})}}$$

---

## EXAMPLE 3.15: Using differentiation and convolution

**Given**: $x(n) = n\left[\left(\frac{1}{2}\right)^n u(n) * \left(\frac{1}{3}\right)^n u(n)\right]$

### Solution:

**Step 1: Find Y(z)**

$$Y(z) = Z\left[\left(\frac{1}{2}\right)^n u(n) * \left(\frac{1}{3}\right)^n u(n)\right]$$

$$= \frac{z}{z-\frac{1}{2}} \cdot \frac{z}{z-\frac{1}{3}} = \frac{z^2}{(z-\frac{1}{2})(z-\frac{1}{3})}$$

**Step 2: Apply differentiation property**

$$X(z) = -z\frac{dY(z)}{dz}$$

$$= -z \cdot \frac{d}{dz}\left[\frac{z^2}{(z-\frac{1}{2})(z-\frac{1}{3})}\right]$$

After differentiation and simplification:

$$\boxed{X(z) = \frac{z^2(z^2 - \frac{5}{6}z + \frac{1}{6})}{(z-\frac{1}{2})^2(z-\frac{1}{3})^2}}$$

---

## EXAMPLE 3.16: Convolution using Z-transform

**Given**: $x_1(n) = \{2, 1, 0, -1, 3\}$, $x_2(n) = \{1, -3, 2\}$

### Solution:

$$X_1(z) = 2 + z^{-1} - z^{-3} + 3z^{-4}$$

$$X_2(z) = 1 - 3z^{-1} + 2z^{-2}$$

Using convolution property:
$$X(z) = X_1(z)X_2(z)$$

$$= (2 + z^{-1} - z^{-3} + 3z^{-4})(1 - 3z^{-1} + 2z^{-2})$$

$$= 2 - 5z^{-1} + z^{-2} + z^{-3} + 6z^{-4} - 11z^{-5} + 6z^{-6}$$

$$x(n) = \{2, -5, 1, 1, 6, -11, 6\}$$

$$\boxed{x(n) = \{2, -5, 1, 1, 6, -11, 6\}}$$

---

## EXAMPLE 3.17: Convolution using Z-transform vs Time domain

**Given**: $x_1(n) = \left(\frac{1}{2}\right)^n u(n)$, $x_2(n) = \left(\frac{1}{3}\right)^{n-2} u(n-2)$

### (a) Using Convolution Property:

### Solution:

$$X_1(z) = \frac{z}{z-\frac{1}{2}}$$

$$X_2(z) = Z\left[\left(\frac{1}{3}\right)^{n-2} u(n-2)\right] = z^{-2} \cdot \frac{z}{z-\frac{1}{3}} = \frac{1}{z(z-\frac{1}{3})}$$

$$X(z) = X_1(z)X_2(z) = \frac{z}{z-\frac{1}{2}} \cdot \frac{1}{z(z-\frac{1}{3})} = \frac{1}{(z-\frac{1}{2})(z-\frac{1}{3})}$$

$$= \frac{6}{z-\frac{1}{2}} - \frac{6}{z-\frac{1}{3}}$$

$$x(n) = 6\left(\frac{1}{2}\right)^{n-1}u(n-1) - 6\left(\frac{1}{3}\right)^{n-1}u(n-1)$$

$$\boxed{x(n) = 6\left[\left(\frac{1}{2}\right)^{n-1} - \left(\frac{1}{3}\right)^{n-1}\right]u(n-1)}$$

---

### (b) Time domain method:

$$x(n) = x_1(n) * x_2(n) = \sum_{k=-\infty}^{\infty} x_1(k)x_2(n-k)$$

$$= \sum_{k=0}^{n-2} \left(\frac{1}{2}\right)^k \left(\frac{1}{3}\right)^{n-k-2}$$

$$= \frac{1}{9}\sum_{k=0}^{n-2} \left(\frac{1}{2}\right)^k \left(\frac{1}{3}\right)^{n-k}$$

$$= \frac{1}{9}\left(\frac{1}{3}\right)^n \sum_{k=0}^{n-2} \left(\frac{3}{2}\right)^k$$

$$= \frac{1}{9}\left(\frac{1}{3}\right)^n \cdot \frac{1 - (3/2)^{n-1}}{1 - 3/2}$$

$$= \frac{1}{9}\left(\frac{1}{3}\right)^n \cdot 2\left[(3/2)^{n-1} - 1\right]$$

$$= 2\left[\left(\frac{1}{2}\right)^{n-1} - \left(\frac{1}{3}\right)^{n-1}\right]u(n-2)$$

$$\boxed{x(n) = 2\left[\left(\frac{1}{2}\right)^{n-1} - \left(\frac{1}{3}\right)^{n-1}\right]u(n-2)}$$

---

## EXAMPLE 3.18: Cross correlation using Z-transform

**Given**: $x_1(n) = \{1, 2, 3, 4\}$, $x_2(n) = \{4, 3, 2, 1\}$

### Solution:

$$X_1(z) = 1 + 2z^{-1} + 3z^{-2} + 4z^{-3}$$

$$X_2(z^{-1}) = 4 + 3z + 2z^2 + z^3$$

Using correlation property:
$$R_{x_1x_2}(z) = X_1(z)X_2(z^{-1})$$

$$= (1 + 2z^{-1} + 3z^{-2} + 4z^{-3})(4 + 3z + 2z^2 + z^3)$$

$$= z^3 + 4z^2 + 10z + 20 + 25z^{-1} + 24z^{-2} + 16z^{-3}$$

$$R_{x_1x_2}(n) = \{1, 4, 10, 20, 25, 24, 16\}$$

$$\boxed{R_{x_1x_2}(n) = \{1, 4, 10, 20, 25, 24, 16\}}$$

---

## EXAMPLE 3.19: Final value theorem

**(a)** $X(z) = \frac{z}{(z-0.6)^2}$

### Solution:

$$x(\infty) = \lim_{z \to 1} (z-1)X(z) = \lim_{z \to 1} (z-1)\frac{z}{(z-0.6)^2} = 0$$

$$\boxed{x(\infty) = 0}$$

---

**(b)** $X(z) = \frac{z}{4(z-1)(z-0.7)}$

### Solution:

$$x(\infty) = \lim_{z \to 1} (z-1)\frac{z}{4(z-1)(z-0.7)} = \lim_{z \to 1} \frac{z}{4(z-0.7)} = \frac{1}{4(0.3)} = \frac{5}{6}$$

$$\boxed{x(\infty) = \frac{5}{6}}$$

---

**(c)** $X(z) = \frac{z^3}{(z-1)(z-3)(z+1)}$

### Solution:

$(z-1)X(z)$ has pole at z=3 (outside unit circle), so $x(\infty) = \infty$

$$\boxed{x(\infty) = \infty \text{ (unstable)}}$$

---

## EXAMPLE 3.20: Initial value theorem

**(a)** $X(z) = \frac{z^2}{(z-1)(z-0.5)}$

### Solution:

$$x(0) = \lim_{z \to \infty} X(z) = \lim_{z \to \infty} \frac{z^2}{(z-1)(z-0.5)} = 1$$

$$\boxed{x(0) = 1}$$

---

**(b)** $X(z) = \frac{z}{(z-1)(z-2)}$

### Solution:

$$x(0) = \lim_{z \to \infty} \frac{z}{(z-1)(z-2)} = \lim_{z \to \infty} \frac{1/z^2}{(1-1/z)(1-2/z)} = 0$$

$$\boxed{x(0) = 0}$$

---

## EXAMPLE 3.21: Initial and final value

**Given**: $X(z) = \frac{z^2}{(z-1)(z-0.2)}$

### Solution:

**Initial value**:

$$x(0) = \lim_{z \to \infty} \frac{z^2}{(z-1)(z-0.2)} = 1$$

$$\boxed{x(0) = 1}$$

**Final value**:

$$x(\infty) = \lim_{z \to 1} (z-1)\frac{z^2}{(z-1)(z-0.2)} = \lim_{z \to 1} \frac{z^2}{z-0.2} = \frac{1}{0.8} = 1.25$$

$$\boxed{x(\infty) = 1.25}$$

---

## 3.6 INVERSE Z-TRANSFORM

### Methods:
1. Long Division (Power Series)
2. Partial Fraction Expansion
3. Residue Method
4. Convolution Method

---

## EXAMPLE 3.22: Inverse Z-transform by inspection

**Given**: $X(z) = z^3 + 2z^2 + z + 1 - 2z^{-1} - 3z^{-2} - 4z^{-3}$

### Solution:

$$x(n) = \delta(n+3) + 2\delta(n+2) + \delta(n+1) + \delta(n) - 2\delta(n-1) - 3\delta(n-2) - 4\delta(n-3)$$

$$\boxed{x(n) = \{1, 2, 1, 1, -2, -3, -4\} \text{ with } \uparrow \text{ at } n=0}$$

---

## EXAMPLE 3.23: Power series method

**(a)** $X(z) = \frac{1}{z-a}$, ROC: $|z| > |a|$

### Solution:

$$X(z) = \frac{1}{z} \cdot \frac{1}{1-az^{-1}} = \frac{1}{z}(1 + az^{-1} + a^2z^{-2} + \dots)$$

$$= z^{-1} + az^{-2} + a^2z^{-3} + \dots = \sum_{n=1}^{\infty} a^{n-1}z^{-n}$$

$$x(n) = a^{n-1}u(n-1)$$

$$\boxed{x(n) = a^{n-1}u(n-1)}$$

---

**(b)** $X(z) = \frac{1}{1-az^{-1}}$, ROC: $|z| > |a|$

### Solution:

$$X(z) = 1 + az^{-1} + a^2z^{-2} + \dots = \sum_{n=0}^{\infty} a^n z^{-n}$$

$$x(n) = a^n u(n)$$

$$\boxed{x(n) = a^n u(n)}$$

---

**(c)** $X(z) = \frac{1}{1-z^{-4}}$, ROC: $|z| > 1$

### Solution:

$$X(z) = 1 + z^{-4} + z^{-8} + z^{-12} + \dots$$

$$x(n) = \sum_{k=0}^{\infty} \delta(n-4k)$$

$$\boxed{x(n) = \begin{cases} 1 & n \text{ multiple of } 4 \\ 0 & \text{otherwise} \end{cases}}$$

---

## EXAMPLE 3.24: Inverse Z-transform of trigonometric functions

**(a)** $X(z) = \cos(3z)$, ROC: $|z| < \infty$

### Solution:

Using Taylor series:
$$\cos(3z) = 1 - \frac{(3z)^2}{2!} + \frac{(3z)^4}{4!} - \frac{(3z)^6}{6!} + \dots$$

$$= 1 - \frac{9}{2}z^2 + \frac{81}{24}z^4 - \frac{729}{720}z^6 + \dots$$

$$x(n) = \left\{1, 0, -\frac{9}{2}, 0, \frac{81}{24}, 0, -\frac{729}{720}, \dots\right\}$$

$$\boxed{x(n) = \begin{cases} (-1)^{n/2}\frac{3^n}{n!} & n \text{ even} \\ 0 & n \text{ odd} \end{cases}}$$

---

**(b)** $X(z) = \sin(2z)$, ROC: $|z| < \infty$

### Solution:

$$\sin(2z) = 2z - \frac{(2z)^3}{3!} + \frac{(2z)^5}{5!} - \frac{(2z)^7}{7!} + \dots$$

$$x(n) = \{0, 2, 0, -\frac{8}{6}, 0, \frac{32}{120}, 0, -\frac{128}{5040}, \dots\}$$

$$\boxed{x(n) = \begin{cases} 0 & n \text{ even} \\ (-1)^{(n-1)/2}\frac{2^n}{n!} & n \text{ odd} \end{cases}}$$

---

## EXAMPLE 3.25: Inverse Z-transform of log

**Given**: $X(z) = \log_{10}(1 + az^{-1})$, ROC: $|z| > |a|$

### Solution:

$$\log_{10}(1 + az^{-1}) = \frac{1}{\ln 10}\ln(1 + az^{-1})$$

$$= \frac{1}{\ln 10}\sum_{n=1}^{\infty} (-1)^{n+1}\frac{a^n z^{-n}}{n}$$

$$x(n) = \frac{(-1)^{n+1}a^n}{n\ln 10}u(n-1)$$

$$\boxed{x(n) = \frac{(-1)^{n+1}a^n}{n\ln 10}u(n-1)}$$

---

## EXAMPLE 3.26: Inverse Z-transform of ln

**Given**: $X(z) = \ln(1-z^{-1})$, ROC: $|z| > 0$

### Solution:

$$\ln(1-z^{-1}) = -\sum_{k=1}^{\infty} \frac{z^{-k}}{k}$$

$$X(z) = -\sum_{k=1}^{\infty} \frac{z^{-k}}{k}$$

$$x(n) = -\frac{1}{n}u(n-1)$$

$$\boxed{x(n) = -\frac{1}{n}u(n-1)}$$

---

## EXAMPLE 3.27: Inverse Z-transform of log

**(a)** $X(z) = \log_e\left(\frac{1}{1 - a z^{-1}}\right)$, ROC: $|z| > |a|$

### Solution:

$$\log_e\left(\frac{1}{1 - a z^{-1}}\right) = -\log_e(1 - a z^{-1}) = \sum_{n=1}^{\infty} \frac{a^n z^{-n}}{n}$$

$$x(n) = \frac{a^n}{n}u(n-1)$$

$$\boxed{x(n) = \frac{a^n}{n}u(n-1)}$$

---

**(b)** $X(z) = \log_e\left(\frac{1}{1 - a z^{-1}}\right)$, ROC: $|z| < |a|$

### Solution:

$$\log_e\left(\frac{1}{1 - a z^{-1}}\right) = \log_e\left(\frac{-z^{-1}}{a^{-1} - z^{-1}}\right)$$

This gives non-causal sequence:

$$x(n) = -\frac{a^n}{n}u(-n-1)$$

$$\boxed{x(n) = -\frac{a^n}{n}u(-n-1)}$$

---

## EXAMPLE 3.29: Long division method

**Given**: $X(z) = \frac{z^2 + 2z}{z^3 - 3z^2 + 4z + 1}$, ROC: $|z| > 1$

### Solution:

Perform long division (dividing in descending powers of z):

$$z^3 - 3z^2 + 4z + 1 \enclose{longdiv}{z^2 + 2z}$$

**Step-by-step:**

$z^3 - 3z^2 + 4z + 1) \overline{z^2 + 2z}$

$z^{-1}$: $z^2 - 3z + 4 + z^{-1}$

Subtract: $(z^2 + 2z) - (z^2 - 3z + 4 + z^{-1}) = 5z - 4 - z^{-1}$

$5z^{-2}$: $5z - 15 + 20z^{-1} + 5z^{-2}$

Subtract: $11 - 21z^{-1} - 5z^{-2}$

$11z^{-3}$: $11 - 33z^{-1} + 44z^{-2} + 11z^{-3}$

Subtract: $12z^{-1} - 49z^{-2} - 11z^{-3}$

$12z^{-4}$: $12z^{-1} - 36z^{-2} + 48z^{-3} + 12z^{-4}$

Subtract: $-13z^{-2} - 59z^{-3} - 12z^{-4}$

$-13z^{-5}$: ...

$$X(z) = z^{-1} + 5z^{-2} + 11z^{-3} + 12z^{-4} - 13z^{-5} + \dots$$

$$x(n) = \{0, 1, 5, 11, 12, -13, \dots\}$$

$$\boxed{x(n) = \{0, 1, 5, 11, 12, -13, \dots\}}$$

---

## EXAMPLE 3.30: Long division for non-causal sequence

**Given**: $X(z) = \frac{z^2 + z}{z^3 - 2z^2 + 3z - 4}$, ROC: $|z| < 1$

### Solution:

For non-causal sequence, write in ascending powers of z:

$$X(z) = \frac{z^2 + z}{-4 + 3z - 2z^2 + z^3}$$

Long division (ascending powers):

$$\frac{z^2 + z}{-4 + 3z - 2z^2 + z^3}$$

$-\frac{1}{4}z^2$: $-\frac{1}{4}z^2 + \frac{3}{16}z^3 - \frac{1}{8}z^4 + \dots$

$-\frac{1}{4}z$: $-\frac{1}{4}z + \frac{3}{16}z^2 - \frac{1}{8}z^3 + \dots$

$$X(z) = \frac{1}{4} + \frac{1}{8}z + \frac{19}{32}z^2 + \frac{81}{128}z^3 + \frac{411}{512}z^4 + \dots$$

$$x(n) = \left\{\frac{1}{4}, \frac{1}{8}, \frac{19}{32}, \frac{81}{128}, \frac{411}{512}, \dots\right\} \text{ for } n \ge 0$$

$$\boxed{x(n) = \left\{\frac{1}{4}, \frac{1}{8}, \frac{19}{32}, \frac{81}{128}, \frac{411}{512}, \dots\right\}}$$

---

## EXAMPLE 3.31: Partial fraction method

**Given**: $X(z) = \frac{z^{-1}}{1 - 3z^{-1} + 4z^{-2}}$, ROC: $|z| > 1$

### Solution:

**Step 1: Express in positive powers of z**

$$X(z) = \frac{z}{z^2 - 3z + 4} = \frac{z}{(z-1)(z-3)}$$

**Step 2: Partial fraction of $X(z)/z$**

$$\frac{X(z)}{z} = \frac{1}{(z-1)(z-3)} = \frac{A}{z-1} + \frac{B}{z-3}$$

$$A = \left.\frac{1}{z-3}\right|_{z=1} = \frac{1}{1-3} = -\frac{1}{2}$$

$$B = \left.\frac{1}{z-1}\right|_{z=3} = \frac{1}{3-1} = \frac{1}{2}$$

$$\frac{X(z)}{z} = -\frac{1/2}{z-1} + \frac{1/2}{z-3}$$

**Step 3: Multiply by z**

$$X(z) = -\frac{1}{2}\frac{z}{z-1} + \frac{1}{2}\frac{z}{z-3}$$

**Step 4: Inverse Z-transform**

$$x(n) = -\frac{1}{2}u(n) + \frac{1}{2}3^n u(n)$$

$$\boxed{x(n) = -\frac{1}{2}u(n) + \frac{1}{2}3^n u(n)}$$

---

## EXAMPLE 3.32: Partial fraction with repeated poles

**Given**: $X(z) = \frac{z(z+1)}{(z-1)^2(z-2)}$, ROC: $|z| > 2$

### Solution:

**Step 1: Partial fraction of $X(z)/z$**

$$\frac{X(z)}{z} = \frac{z+1}{(z-1)^2(z-2)} = \frac{A}{z-1} + \frac{B}{(z-1)^2} + \frac{C}{z-2}$$

$$B = \left.\frac{z+1}{z-2}\right|_{z=1} = \frac{2}{-1} = -2$$

$$C = \left.\frac{z+1}{(z-1)^2}\right|_{z=2} = \frac{3}{1} = 3$$

$$A = \left.\frac{d}{dz}\left[\frac{z+1}{z-2}\right]\right|_{z=1} = \left.\frac{(z-2)-(z+1)}{(z-2)^2}\right|_{z=1} = -3$$

**Step 2: Form X(z)**

$$X(z) = -3\frac{z}{z-1} - 2\frac{z}{(z-1)^2} + 3\frac{z}{z-2}$$

**Step 3: Inverse Z-transform**

$$x(n) = -3u(n) - 2n u(n) + 3(2)^n u(n)$$

$$\boxed{x(n) = [-3 - 2n + 3(2)^n]u(n)}$$

---

## EXAMPLE 3.33: All possible inverse Z-transforms

**Given**: $X(z) = \frac{1 - \frac{1}{4}z^{-1}}{(1-\frac{1}{2}z^{-1})(1-\frac{1}{4}z^{-1})}$

### Solution:

**Step 1: Express in positive powers**

$$X(z) = \frac{z}{(z-\frac{1}{2})(z-\frac{1}{4})}$$

**Step 2: Partial fraction**

$$\frac{X(z)}{z} = \frac{1}{(z-\frac{1}{2})(z-\frac{1}{4})} = \frac{2}{z-\frac{1}{2}} - \frac{4}{z-\frac{1}{4}}$$

$$X(z) = 2\frac{z}{z-\frac{1}{2}} - 4\frac{z}{z-\frac{1}{4}}$$

**Possible ROCs and corresponding x(n):**

**(a) ROC: $|z| > \frac{1}{2}$ (causal)**

$$x(n) = 2\left(\frac{1}{2}\right)^n u(n) - 4\left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{x(n) = \left[2\left(\frac{1}{2}\right)^n - 4\left(\frac{1}{4}\right)^n\right]u(n)}$$

---

**(b) ROC: $|z| < \frac{1}{4}$ (anticausal)**

$$x(n) = -2\left(\frac{1}{2}\right)^n u(-n-1) + 4\left(\frac{1}{4}\right)^n u(-n-1)$$

$$\boxed{x(n) = \left[-2\left(\frac{1}{2}\right)^n + 4\left(\frac{1}{4}\right)^n\right]u(-n-1)}$$

---

**(c) ROC: $\frac{1}{4} < |z| < \frac{1}{2}$ (two-sided)**

$$x(n) = 2\left(\frac{1}{2}\right)^n u(-n-1) - 4\left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{x(n) = 2\left(\frac{1}{2}\right)^n u(-n-1) - 4\left(\frac{1}{4}\right)^n u(n)}$$

---

## EXAMPLE 3.34: All possible inverse Z-transforms

**Given**: $X(z) = \frac{z^2 - 4z + 5}{z^3 - 6z^2 + 11z - 6}$

### Solution:

**Step 1: Factor denominator**

$$z^3 - 6z^2 + 11z - 6 = (z-1)(z-2)(z-3)$$

$$X(z) = \frac{z^2 - 4z + 5}{(z-1)(z-2)(z-3)}$$

**Step 2: Partial fraction**

$$\frac{X(z)}{z} = \frac{z^2 - 4z + 5}{z(z-1)(z-2)(z-3)} = \frac{A}{z-1} + \frac{B}{z-2} + \frac{C}{z-3}$$

After solving: $A = 1, B = -1, C = 1$

$$X(z) = \frac{z}{z-1} - \frac{z}{z-2} + \frac{z}{z-3}$$

**Possible ROCs:**

**(a) $|z| > 3$**: $x(n) = [1 - 2^n + 3^n]u(n)$

**(b) $|z| < 1$**: $x(n) = [-1 + 2^n - 3^n]u(-n-1)$

**(c) $1 < |z| < 2$**: $x(n) = u(n) - 2^n u(-n-1) + 3^n u(-n-1)$

**(d) $2 < |z| < 3$**: $x(n) = u(n) - 2^n u(n) + 3^n u(-n-1)$

$$\boxed{\text{See above for each ROC}}$$

---

## EXAMPLE 3.35: Causal signal with repeated pole

**Given**: $X(z) = \frac{z^2 - z}{(z-\frac{1}{2})^2(z-\frac{1}{4})}$

### Solution:

**Step 1: Partial fraction**

$$\frac{X(z)}{z} = \frac{z-1}{(z-\frac{1}{2})^2(z-\frac{1}{4})}$$

$$= \frac{A}{z-\frac{1}{2}} + \frac{B}{(z-\frac{1}{2})^2} + \frac{C}{z-\frac{1}{4}}$$

After solving: $A = -6, B = 2, C = 6$

$$X(z) = -6\frac{z}{z-\frac{1}{2}} + 2\frac{z}{(z-\frac{1}{2})^2} + 6\frac{z}{z-\frac{1}{4}}$$

**Step 2: Inverse Z-transform (causal)**

$$x(n) = -6\left(\frac{1}{2}\right)^n u(n) + 2(n+1)\left(\frac{1}{2}\right)^n u(n) + 6\left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{x(n) = \left[-6 + 2(n+1)\right]\left(\frac{1}{2}\right)^n u(n) + 6\left(\frac{1}{4}\right)^n u(n)}$$

---

## EXAMPLE 3.36: Residue method

**Given**: $X(z) = \frac{z(z-2)}{(z-1)(z-3)}$, ROC: $|z| > 3$

### Solution:

$$x(n) = \sum \text{Residues of } X(z)z^{n-1} \text{ at poles inside contour}$$

$$X(z)z^{n-1} = \frac{z^n(z-2)}{(z-1)(z-3)}$$

**Residue at z = 1:**
$$R_1 = \left.\frac{z^n(z-2)}{z-3}\right|_{z=1} = \frac{1(1-2)}{1-3} = \frac{-1}{-2} = \frac{1}{2}$$

**Residue at z = 3:**
$$R_2 = \left.\frac{z^n(z-2)}{z-1}\right|_{z=3} = \frac{3^n(3-2)}{3-1} = \frac{3^n}{2}$$

$$x(n) = \frac{1}{2}u(n) + \frac{3^n}{2}u(n)$$

$$\boxed{x(n) = \frac{1 + 3^n}{2}u(n)}$$

---

## EXAMPLE 3.37: Residue method with repeated pole

**Given**: $X(z) = \frac{3z}{(z-\frac{1}{2})^2}$, ROC: $|z| > \frac{1}{2}$

### Solution:

$$X(z)z^{n-1} = \frac{3z^n}{(z-\frac{1}{2})^2}$$

**Residue at z = 1/2 (order 2):**

$$R = \left.\frac{d}{dz}\left[3z^n\right]\right|_{z=1/2} = 3n\left(\frac{1}{2}\right)^{n-1}$$

$$x(n) = 3n\left(\frac{1}{2}\right)^{n-1}u(n)$$

$$\boxed{x(n) = 3n\left(\frac{1}{2}\right)^{n-1}u(n)}$$

---

## EXAMPLE 3.38: All possible inverse using residues

**Given**: $X(z) = \frac{z}{(z+1)^2(z+2)^3}$

### Solution:

For ROC $|z| > 2$:

**Residue at z = -1 (order 2):**
$$R_{-1} = \left.\frac{d}{dz}\left[\frac{z^n}{(z+2)^3}\right]\right|_{z=-1}$$

$$= \left.\frac{nz^{n-1}(z+2)^3 - 3z^n(z+2)^2}{(z+2)^6}\right|_{z=-1}$$

$$= \left.\frac{nz^{n-1}(z+2) - 3z^n}{(z+2)^4}\right|_{z=-1}$$

$$= \frac{n(-1)^{n-1}(1) - 3(-1)^n}{1} = n(-1)^{n-1} - 3(-1)^n$$

**Residue at z = -2 (order 3):**

$$R_{-2} = \left.\frac{1}{2!}\frac{d^2}{dz^2}\left[\frac{z^n}{(z+1)^2}\right]\right|_{z=-2}$$

After calculation:

$$R_{-2} = \frac{(-2)^n}{2}\left[(n^2 - n) + 4n - 6\right]$$

$$x(n) = [n(-1)^{n-1} - 3(-1)^n]u(n) + \frac{(-2)^n}{2}(n^2 + 3n - 6)u(n)$$

$$\boxed{x(n) = [n(-1)^{n-1} - 3(-1)^n]u(n) + \frac{(-2)^n}{2}(n^2 + 3n - 6)u(n)}$$

---

## 3.7 TRANSFORM ANALYSIS OF LTI SYSTEMS

### System Function:

$$H(z) = \frac{Y(z)}{X(z)} = \frac{\sum_{k=0}^{M} b_k z^{-k}}{\sum_{k=0}^{N} a_k z^{-k}}$$

### Stability and Causality:

- **Causal**: $h(n) = 0$ for $n < 0$ → ROC outside outermost pole
- **Stable**: ROC contains unit circle
- **Causal and Stable**: All poles inside unit circle

### Frequency Response:

$$H(e^{j\omega}) = H(z)|_{z=e^{j\omega}}$$

---

## EXAMPLE 3.42: System function and stability

**Given**: $H(z) = \frac{1}{1-\frac{1}{2}z^{-1}}$

### Solution:

$$Y(z) - \frac{1}{2}z^{-1}Y(z) = X(z)$$
$$y(n) = x(n) + \frac{1}{2}y(n-1)$$

Pole at $z = \frac{1}{2}$ (inside unit circle), so system is stable.

$$\boxed{\text{Stable, Causal}}$$

---

## EXAMPLE 3.43: Difference equation and frequency response

**Given**: $H(z) = \frac{z^2}{2z^2 - 3z + 4}$

### Solution:

**Step 1: Express in negative powers**

$$H(z) = \frac{1}{2 - 3z^{-1} + 4z^{-2}}$$

**Step 2: Find difference equation**

$$Y(z)(2 - 3z^{-1} + 4z^{-2}) = X(z)$$

$$2y(n) - 3y(n-1) + 4y(n-2) = x(n)$$

$$\boxed{2y(n) - 3y(n-1) + 4y(n-2) = x(n)}$$

**Step 3: Frequency response**

$$H(e^{j\omega}) = \frac{1}{2 - 3e^{-j\omega} + 4e^{-j2\omega}}$$

$$\boxed{H(e^{j\omega}) = \frac{1}{2 - 3e^{-j\omega} + 4e^{-j2\omega}}}$$

---

## EXAMPLE 3.44: System function from difference equation

**Given**: $y(n) - \frac{1}{3}y(n-1) + \frac{1}{5}y(n-2) = x(n) - 2x(n-1)$

### Solution:

$$Y(z) = \frac{1}{3}z^{-1}Y(z) - \frac{1}{5}z^{-2}Y(z) + X(z) - 2z^{-1}X(z)$$

$$H(z) = \frac{1-2z^{-1}}{1-\frac{1}{3}z^{-1}+\frac{1}{5}z^{-2}}$$

$$= \frac{z(z-2)}{z^2-\frac{1}{3}z+\frac{1}{5}}$$

$$\boxed{H(z) = \frac{z(z-2)}{z^2-\frac{1}{3}z+\frac{1}{5}}}$$

---

## EXAMPLE 3.45: Pole-zero plot and stability

**(a)** $y(n) - y(n-1) + 0.8y(n-2) = x(n) + x(n-2)$

### Solution:

$$H(z) = \frac{1+z^{-2}}{1-z^{-1}+0.8z^{-2}} = \frac{z^2+1}{z^2-z+0.8}$$

$$z^2-z+0.8 = (z-0.5-j0.74)(z-0.5+j0.74)$$

Both poles have magnitude $|z| = \sqrt{0.25+0.5476} = 0.893 < 1$

**All poles inside unit circle → Stable**

$$\boxed{\text{Stable}}$$

---

**(b)** $y(n) + 2y(n-1) + 0.8y(n-2) = x(n) - 0.8x(n-1)$

### Solution:

$$H(z) = \frac{1-0.8z^{-1}}{1+2z^{-1}+0.8z^{-2}} = \frac{z(z-0.8)}{z^2+2z+0.8}$$

$$z^2+2z+0.8 = (z+1.445)(z+0.555)$$

One pole at z = -1.445 (outside unit circle) → **Unstable**

$$\boxed{\text{Unstable}}$$

---

## EXAMPLE 3.46: System function, frequency response, impulse response

**Given**: $x(n) = \delta(n) + \frac{1}{6}\delta(n-1) - \frac{1}{6}\delta(n-2)$

$y(n) = \delta(n) - \frac{2}{3}\delta(n-1)$

### Solution:

**Step 1: System function**

$$X(z) = 1 + \frac{1}{6}z^{-1} - \frac{1}{6}z^{-2}$$

$$Y(z) = 1 - \frac{2}{3}z^{-1}$$

$$H(z) = \frac{Y(z)}{X(z)} = \frac{1-\frac{2}{3}z^{-1}}{1+\frac{1}{6}z^{-1}-\frac{1}{6}z^{-2}}$$

$$= \frac{z(z-\frac{2}{3})}{(z-\frac{1}{2})(z+\frac{1}{3})}$$

$$\boxed{H(z) = \frac{z(z-\frac{2}{3})}{(z-\frac{1}{2})(z+\frac{1}{3})}}$$

**Step 2: Frequency response**

$$H(e^{j\omega}) = \frac{e^{j\omega}(e^{j\omega}-\frac{2}{3})}{(e^{j\omega}-\frac{1}{2})(e^{j\omega}+\frac{1}{3})}$$

**Step 3: Impulse response**

$$H(z) = \frac{2}{5}\frac{z}{z-\frac{1}{2}} + \frac{7}{5}\frac{z}{z+\frac{1}{3}}$$

$$h(n) = \frac{2}{5}\left(\frac{1}{2}\right)^n u(n) + \frac{7}{5}\left(-\frac{1}{3}\right)^n u(n)$$

$$\boxed{h(n) = \left[\frac{2}{5}\left(\frac{1}{2}\right)^n + \frac{7}{5}\left(-\frac{1}{3}\right)^n\right]u(n)}$$

**Stability**: Both poles inside unit circle → **Stable**

---

## EXAMPLE 3.47: Design causal LTI system

**Given**: $x(n) = \left(\frac{1}{3}\right)^n u(n) + \frac{1}{5}\left(\frac{1}{3}\right)^n u(n-1)$

$y(n) = \left(\frac{1}{2}\right)^n u(n)$

### Solution:

**Step 1: Find X(z)**

$$x(n) = \left(\frac{1}{3}\right)^n u(n) + \frac{1}{5}\left(\frac{1}{3}\right)^n u(n-1)$$

$$X(z) = \frac{z}{z-\frac{1}{3}} + \frac{1}{5}\frac{1}{z-\frac{1}{3}}$$

$$= \frac{z + \frac{1}{5}}{z-\frac{1}{3}}$$

**Step 2: Find Y(z)**

$$Y(z) = \frac{z}{z-\frac{1}{2}}$$

**Step 3: System function**

$$H(z) = \frac{Y(z)}{X(z)} = \frac{\frac{z}{z-\frac{1}{2}}}{\frac{z+\frac{1}{5}}{z-\frac{1}{3}}}$$

$$= \frac{z(z-\frac{1}{3})}{(z-\frac{1}{2})(z+\frac{1}{5})}$$

$$\boxed{H(z) = \frac{z(z-\frac{1}{3})}{(z-\frac{1}{2})(z+\frac{1}{5})}}$$

---

## EXAMPLE 3.48: System function and impulse response

**Given**: $y(n) - y(n-1) - y(n-2) = x(n) - 2x(n-1)$

### Solution:

$$H(z) = \frac{1-2z^{-1}}{1-z^{-1}-z^{-2}} = \frac{z(z-2)}{z^2-z-1}$$

$$z^2-z-1 = (z-1.618)(z+0.618)$$

**Poles**: z = 1.618 (outside unit circle), z = -0.618 (inside)

One pole outside unit circle → **Unstable**

**Impulse response**:

$$H(z) = 1.618\frac{z}{z-1.618} - 0.618\frac{z}{z+0.618}$$

$$h(n) = 1.618(1.618)^n u(n) - 0.618(-0.618)^n u(n)$$

$$\boxed{h(n) = [1.618(1.618)^n - 0.618(-0.618)^n]u(n)}$$

$$\boxed{\text{Unstable}}$$

---

## EXAMPLE 3.49: Causal and stable systems

**(a)** $H(z) = \frac{1+2z^{-1}}{1-\frac{4}{9}z^{-2}}$

### Solution:

$$H(z) = \frac{z^2+2z}{z^2-\frac{4}{9}} = \frac{z(z+2)}{(z-\frac{2}{3})(z+\frac{2}{3})}$$

Poles: $z = \pm\frac{2}{3}$ (both inside unit circle)

**Both causal and stable**

$$\boxed{\text{Causal and Stable}}$$

---

**(b)** $H(z) = \frac{1+2z^{-1}}{1-\frac{6}{5}z^{-1}+\frac{9}{25}z^{-2}}$

### Solution:

$$H(z) = \frac{z^2+2z}{z^2-\frac{6}{5}z+\frac{9}{25}} = \frac{z(z+2)}{(z-\frac{3}{5})^2}$$

Poles: $z = \frac{3}{5}$ (double pole, inside unit circle)

**Both causal and stable**

$$\boxed{\text{Causal and Stable}}$$

---

## EXAMPLE 3.50: ROC and impulse response

**Given**: $y(n) - \frac{9}{4}y(n-1) + \frac{1}{2}y(n-2) = x(n) - 3x(n-1)$

### Solution:

$$H(z) = \frac{1-3z^{-1}}{1-\frac{9}{4}z^{-1}+\frac{1}{2}z^{-2}} = \frac{z(z-3)}{(z-\frac{1}{4})(z-2)}$$

Poles at $z = \frac{1}{4}$ and $z = 2$

**(a) For stable system**: ROC: $\frac{1}{4} < |z| < 2$

$$H(z) = \frac{11}{7}\frac{z}{z-\frac{1}{4}} - \frac{4}{7}\frac{z}{z-2}$$

$$h(n) = \frac{11}{7}\left(\frac{1}{4}\right)^n u(n) + \frac{4}{7}(2)^n u(-n-1)$$

$$\boxed{h(n) = \frac{11}{7}\left(\frac{1}{4}\right)^n u(n) + \frac{4}{7}(2)^n u(-n-1)}$$

**(b) For causal system**: ROC: $|z| > 2$

$$h(n) = \frac{11}{7}\left(\frac{1}{4}\right)^n u(n) - \frac{4}{7}(2)^n u(n)$$

$$\boxed{h(n) = \left[\frac{11}{7}\left(\frac{1}{4}\right)^n - \frac{4}{7}(2)^n\right]u(n)}$$

---

## EXAMPLE 3.54: Unit sample response

**Given**: $y(n) - \frac{3}{4}y(n-1) + \frac{1}{8}y(n-2) = x(n) - \frac{1}{3}x(n-1)$

### Solution:

$$H(z) = \frac{1-\frac{1}{3}z^{-1}}{1-\frac{3}{4}z^{-1}+\frac{1}{8}z^{-2}} = \frac{z(z-\frac{1}{3})}{(z-\frac{1}{2})(z-\frac{1}{4})}$$

$$\frac{H(z)}{z} = \frac{z-\frac{1}{3}}{(z-\frac{1}{2})(z-\frac{1}{4})} = \frac{A}{z-\frac{1}{2}} + \frac{B}{z-\frac{1}{4}}$$

$$A = \left.\frac{z-\frac{1}{3}}{z-\frac{1}{4}}\right|_{z=\frac{1}{2}} = \frac{\frac{1}{2}-\frac{1}{3}}{\frac{1}{2}-\frac{1}{4}} = \frac{\frac{1}{6}}{\frac{1}{4}} = \frac{2}{3}$$

$$B = \left.\frac{z-\frac{1}{3}}{z-\frac{1}{2}}\right|_{z=\frac{1}{4}} = \frac{\frac{1}{4}-\frac{1}{3}}{\frac{1}{4}-\frac{1}{2}} = \frac{-\frac{1}{12}}{-\frac{1}{4}} = \frac{1}{3}$$

$$H(z) = \frac{2}{3}\frac{z}{z-\frac{1}{2}} + \frac{1}{3}\frac{z}{z-\frac{1}{4}}$$

$$h(n) = \frac{2}{3}\left(\frac{1}{2}\right)^n u(n) + \frac{1}{3}\left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{h(n) = \left[\frac{2}{3}\left(\frac{1}{2}\right)^n + \frac{1}{3}\left(\frac{1}{4}\right)^n\right]u(n)}$$

---

## EXAMPLE 3.59: Impulse response of IIR filter

**Given**: $y(n) - 3y(n-1) - 4y(n-2) = x(n) - 2x(n-1)$

### Solution:

$$H(z) = \frac{1-2z^{-1}}{1-3z^{-1}-4z^{-2}} = \frac{z(z-2)}{(z-4)(z+1)}$$

$$\frac{H(z)}{z} = \frac{z-2}{(z-4)(z+1)} = \frac{A}{z-4} + \frac{B}{z+1}$$

$$A = \left.\frac{z-2}{z+1}\right|_{z=4} = \frac{2}{5}$$

$$B = \left.\frac{z-2}{z-4}\right|_{z=-1} = \frac{-3}{-5} = \frac{3}{5}$$

$$H(z) = \frac{2}{5}\frac{z}{z-4} + \frac{3}{5}\frac{z}{z+1}$$

$$h(n) = \frac{2}{5}(4)^n u(n) + \frac{3}{5}(-1)^n u(n)$$

$$\boxed{h(n) = \left[\frac{2}{5}4^n + \frac{3}{5}(-1)^n\right]u(n)}$$

---

## EXAMPLE 3.61: Natural and forced response

**Given**: $y(n) - \frac{3}{4}y(n-1) + \frac{1}{8}y(n-2) = x(n) - x(n-1)$

with $y(-1) = 0$, $y(-2) = -1$

### Solution:

**(a) Natural response**: Set x(n) = 0

$$Y(z) - \frac{3}{4}[z^{-1}Y(z) + y(-1)] + \frac{1}{8}[z^{-2}Y(z) + z^{-1}y(-1) + y(-2)] = 0$$

With y(-1) = 0, y(-2) = -1:

$$Y(z)\left(1 - \frac{3}{4}z^{-1} + \frac{1}{8}z^{-2}\right) - \frac{1}{8} = 0$$

$$Y(z) = \frac{\frac{1}{8}z^2}{z^2-\frac{3}{4}z+\frac{1}{8}} = \frac{\frac{1}{8}z}{(z-\frac{1}{2})(z-\frac{1}{4})}$$

$$\frac{Y(z)}{z} = \frac{\frac{1}{8}}{(z-\frac{1}{2})(z-\frac{1}{4})} = \frac{1/4}{z-\frac{1}{2}} - \frac{1/8}{z-\frac{1}{4}}$$

$$y_n(n) = \frac{1}{4}\left(\frac{1}{2}\right)^n u(n) - \frac{1}{8}\left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{y_n(n) = \left[\frac{1}{4}\left(\frac{1}{2}\right)^n - \frac{1}{8}\left(\frac{1}{4}\right)^n\right]u(n)}$$

---

**(b) Forced response for step input**: x(n) = u(n)

$$X(z) = \frac{z}{z-1}$$

$$Y(z) = H(z)X(z) = \frac{1-z^{-1}}{1-\frac{3}{4}z^{-1}+\frac{1}{8}z^{-2}} \cdot \frac{z}{z-1}$$

$$= \frac{z^2}{(z-\frac{1}{2})(z-\frac{1}{4})}$$

$$\frac{Y(z)}{z} = \frac{z}{(z-\frac{1}{2})(z-\frac{1}{4})} = \frac{2}{z-\frac{1}{2}} - \frac{1}{z-\frac{1}{4}}$$

$$y_f(n) = 2\left(\frac{1}{2}\right)^n u(n) - \left(\frac{1}{4}\right)^n u(n)$$

$$\boxed{y_f(n) = \left[2\left(\frac{1}{2}\right)^n - \left(\frac{1}{4}\right)^n\right]u(n)}$$

---

## EXAMPLE 3.64: Solve difference equation

**Given**: $y(n) - 2y(n-1) = x(n)$ with $x(n) = \left(\frac{1}{3}\right)^n u(n)$, $y(-1) = 1$

### Solution:

Taking Z-transform:

$$Y(z) - 2[z^{-1}Y(z) + y(-1)] = X(z)$$

$$Y(z)(1-2z^{-1}) - 2 = \frac{z}{z-\frac{1}{3}}$$

$$Y(z) = \frac{2}{1-2z^{-1}} + \frac{z}{(z-\frac{1}{3})(1-2z^{-1})}$$

$$= \frac{2z}{z-2} + \frac{z^2}{(z-\frac{1}{3})(z-2)}$$

$$\frac{Y(z)}{z} = \frac{2}{z-2} + \frac{z}{(z-\frac{1}{3})(z-2)}$$

$$= \frac{2}{z-2} + \frac{3}{7}\frac{1}{z-\frac{1}{3}} - \frac{3}{7}\frac{1}{z-2}$$

$$Y(z) = \frac{11}{7}\frac{z}{z-2} + \frac{3}{7}\frac{z}{z-\frac{1}{3}}$$

$$y(n) = \frac{11}{7}(2)^n u(n) + \frac{3}{7}\left(\frac{1}{3}\right)^n u(n)$$

$$\boxed{y(n) = \left[\frac{11}{7}2^n + \frac{3}{7}\left(\frac{1}{3}\right)^n\right]u(n)}$$

---

## EXAMPLE 3.65: Solve difference equation with initial conditions

**Given**: $y(n) - \frac{7}{12}y(n-1) + \frac{1}{12}y(n-2) = x(n)$

with $y(-1) = 2$, $y(-2) = 4$, $x(n) = \left(\frac{1}{5}\right)^n u(n)$

### Solution:

Taking Z-transform:

$$Y(z) - \frac{7}{12}[z^{-1}Y(z) + y(-1)] + \frac{1}{12}[z^{-2}Y(z) + z^{-1}y(-1) + y(-2)] = X(z)$$

$$Y(z)\left(1 - \frac{7}{12}z^{-1} + \frac{1}{12}z^{-2}\right) - \frac{7}{6} + \frac{1}{6}z^{-1} + \frac{1}{3} = \frac{z}{z-\frac{1}{5}}$$

$$Y(z)\left(1 - \frac{7}{12}z^{-1} + \frac{1}{12}z^{-2}\right) - \frac{5}{6} + \frac{1}{6}z^{-1} = \frac{z}{z-\frac{1}{5}}$$

After solving:

$$Y(z) = \frac{6}{5}\frac{z}{z-\frac{1}{5}} + \frac{1}{8}\frac{z}{z-\frac{1}{4}} - \frac{27}{8}\frac{z}{z-\frac{1}{3}}$$

$$y(n) = \frac{6}{5}\left(\frac{1}{5}\right)^n u(n) + \frac{1}{8}\left(\frac{1}{4}\right)^n u(n) - \frac{27}{8}\left(\frac{1}{3}\right)^n u(n)$$

$$\boxed{y(n) = \left[\frac{6}{5}\left(\frac{1}{5}\right)^n + \frac{1}{8}\left(\frac{1}{4}\right)^n - \frac{27}{8}\left(\frac{1}{3}\right)^n\right]u(n)}$$

---

## EXAMPLE 3.68: Frequency response

**Given**: $y(n) - \frac{3}{4}y(n-1) + \frac{1}{8}y(n-2) = x(n) - x(n-1)$

### Solution:

$$H(z) = \frac{1-z^{-1}}{1-\frac{3}{4}z^{-1}+\frac{1}{8}z^{-2}} = \frac{z(z-1)}{z^2-\frac{3}{4}z+\frac{1}{8}}$$

**Frequency response**:

$$H(e^{j\omega}) = \frac{e^{j\omega}(e^{j\omega}-1)}{e^{j2\omega}-\frac{3}{4}e^{j\omega}+\frac{1}{8}}$$

**Magnitude response**:

$$|H(e^{j\omega})| = \sqrt{\frac{(\cos 2\omega-\cos\omega)^2 + (\sin 2\omega-\sin\omega)^2}{(\cos 2\omega-\frac{3}{4}\cos\omega+\frac{1}{8})^2 + (\sin 2\omega-\frac{3}{4}\sin\omega)^2}}$$

**Phase response**:

$$\angle H(e^{j\omega}) = \tan^{-1}\left(\frac{\sin 2\omega-\sin\omega}{\cos 2\omega-\cos\omega}\right) - \tan^{-1}\left(\frac{\sin 2\omega-\frac{3}{4}\sin\omega}{\cos 2\omega-\frac{3}{4}\cos\omega+\frac{1}{8}}\right)$$

$$\boxed{\text{See above for magnitude and phase}}$$

---

## EXAMPLE 3.71: Response using convolution

**Given**: $h(n) = \{1, 2, 1, -1\}$, $x(n) = \{1, 2, 3, 6\}$

### Solution:

$$X(z) = 1 + 2z^{-1} + 3z^{-2} + 6z^{-3}$$

$$H(z) = 1 + 2z^{-1} + z^{-2} - z^{-3}$$

$$Y(z) = X(z)H(z)$$

$$= (1 + 2z^{-1} + 3z^{-2} + 6z^{-3})(1 + 2z^{-1} + z^{-2} - z^{-3})$$

$$= 1 + 4z^{-1} + 8z^{-2} + 8z^{-3} + 3z^{-4} - 2z^{-5} - z^{-6}$$

$$y(n) = \{1, 4, 8, 8, 3, -2, -1\}$$

$$\boxed{y(n) = \{1, 4, 8, 8, 3, -2, -1\}}$$

---

## EXAMPLE 3.72: Step response to find impulse response

**Given**: $s(n) = \left(\frac{1}{3}\right)^{n-2} u(n+2)$

### Solution:

$$S(z) = \sum_{n=-2}^{\infty} \left(\frac{1}{3}\right)^{n-2} z^{-n}$$

$$= 3^2\sum_{n=-2}^{\infty} \left(\frac{1}{3}\right)^n z^{-n} = 9\sum_{n=-2}^{\infty} \left(\frac{1}{3z}\right)^n$$

$$= 9\left[\left(\frac{1}{3z}\right)^{-2} + \left(\frac{1}{3z}\right)^{-1} + \sum_{n=0}^{\infty} \left(\frac{1}{3z}\right)^n\right]$$

$$= 9\left[9z^2 + 3z + \frac{1}{1-\frac{1}{3z}}\right]$$

Since $s(n) = h(n) * u(n)$, $H(z) = (1-z^{-1})S(z)$

$$H(z) = (1-z^{-1}) \cdot 81z^2 = 81z^2 - 81z$$

$$h(n) = 81\delta(n+2) - 81\delta(n+1)$$

$$\boxed{h(n) = 81\delta(n+2) - 81\delta(n+1)}$$

---

## 3.8 RELATION BETWEEN s-PLANE AND z-PLANE

$$z = e^{sT}$$

Let $z = re^{j\omega}$ and $s = \sigma + j\Omega$:

$$re^{j\omega} = e^{(\sigma + j\Omega)T} = e^{\sigma T}e^{j\Omega T}$$

$$r = e^{\sigma T}, \quad \omega = \Omega T$$

### Mapping:
- $\sigma = 0$ (jω-axis) → $|z| = 1$ (unit circle)
- $\sigma < 0$ (left half) → $|z| < 1$ (inside unit circle)
- $\sigma > 0$ (right half) → $|z| > 1$ (outside unit circle)

**Aliasing effect**: Many-to-one mapping due to periodic nature of $e^{j\Omega T}$

---

# SHORT QUESTIONS WITH ANSWERS

**1. Define Z-transform.**

$$X(z) = \sum_{n=-\infty}^{\infty} x(n)z^{-n}$$

**2. What are the advantages of Z-transform?**

1. Converts difference equations to algebraic equations
2. Convolution → multiplication
3. Exists for signals where DTFT doesn't exist
4. Frequency response from unit circle evaluation

**3. What is ROC?**

The set of values of z for which X(z) converges.

**4. ROC of causal sequence?**

$|z| > \alpha$ (exterior of circle)

**5. ROC of anticausal sequence?**

$|z| < \alpha$ (interior of circle)

**6. ROC of two-sided sequence?**

Ring: $\alpha < |z| < \beta$

**7. Initial value theorem?**

$$x(0) = \lim_{z \to \infty} X(z)$$

**8. Final value theorem?**

$$x(\infty) = \lim_{z \to 1} (z-1)X(z)$$

---

# FILL IN THE BLANKS

1. The Z-transform converts **difference** equations into **algebraic** equations.

2. The Z-transform plays the same role for **discrete-time** systems as that played by Laplace transform for **continuous-time** systems.

3. The range of values of z for which X(z) converges is called the **ROC**.

4. The Z-transform of x(n) is same as the DTFT of **x(n)r⁻ⁿ**.

5. The DTFT is same as the Z-transform when **r = 1**.

6. The ROC of the sum of two or more sequences is equal to the **intersection** of the ROCs of those sequences.

7. The ROC of a causal sequence is the **exterior** of a circle of radius α.

8. The ROC of a non-causal sequence is the **interior** of a circle of radius α.

9. If X(z) is rational, its ROC is bounded by **poles** or extends up to **infinity**.

10. Initial value theorem states that for a causal signal x(0) = **$\lim_{z \to \infty} X(z)$**.

11. Final value theorem states that for a causal signal x(∞) = **$\lim_{z \to 1} (z-1)X(z)$**.

12. We cannot obtain a **two-sided** sequence by long division.

13. The frequency response of a system is obtained by substituting **$z = e^{j\omega}$** in H(z).

14. For a causal LTI system to be stable, all the poles of H(z) must lie **inside the unit circle** in the z-plane.

15. For a causal LTI system to be stable, the ROC of the system function must include the **unit circle**.

16. The response of the system due to input alone, when initial conditions are neglected, is called the **forced response**.

17. The response of the system due to initial conditions alone, when input is neglected, is called the **natural response**.

18. The response due to input and initial conditions considered simultaneously is called the **total response**.

19. The output due to unit sample sequence is called the **impulse response**.

---

# OBJECTIVE TYPE QUESTIONS

**1. The ROC of Z-transform of a sequence $(\frac{5}{4})^n u(n) + (\frac{4}{5})^n u(-n)$ must be**
(a) $|z| > \frac{5}{4}$ (b) $|z| < \frac{4}{5}$ (c) $\frac{4}{5} < |z| < \frac{5}{4}$ (d) None

**Answer: (c) $\frac{4}{5} < |z| < \frac{5}{4}$**

---

**2. The inverse Z-transform of $2 + 3z^{-1} + 4z$ is**
(a) {2, 3, 4} (b) {3, 4, 2} (c) {4, 2, 3} (d) {3, 2, 4}

**Answer: (c) {4, 2, 3}**

---

**3. Which sequence cannot be the inverse Z-transform of $\frac{1 - \frac{1}{3}z^{-1}}{1 - \frac{1}{4}z^{-1}}$?**

**Answer: (d) $-3^n u(-n-1) + 4^n u(n)$** (ROC doesn't match)

---

**4. A system is described by $H(z) = \frac{z(z+1)}{(z-2)(z-2)}$. The initial value of the system is**
(a) 1 (b) -1/4 (c) -4 (d) ∞

**Answer: (a) 1**

---

**5. Which one is the ROC of a sequence $x(n) = a^n u(n) - b^n u(-n-1)$, where a > b?**
(a) $|z| > a$ (b) $|z| < b$ (c) $b < |z| < a$ (d) none

**Answer: (c) $b < |z| < a$**

---

**6. The only signal whose ROC is the entire z-plane is**
(a) $\delta(n)$ (b) $u(n)$ (c) $r(n)$ (d) $a^n$

**Answer: (a) $\delta(n)$**

---

**7. The initial value theorem states that x(0) =**
(a) $\lim_{z \to 1} (z-1)X(z)$ (b) $\lim_{z \to 0} X(z)$ (c) $\lim_{z \to \infty} X(z)$ (d) $\lim_{z \to \infty} zX(z)$

**Answer: (c) $\lim_{z \to \infty} X(z)$**

---

**8. The final value theorem states that x(∞) =**
(a) $\lim_{z \to 1} (z-1)X(z)$ (b) $\lim_{z \to 0} X(z)$ (c) $\lim_{z \to \infty} X(z)$ (d) $\lim_{z \to \infty} zX(z)$

**Answer: (a) $\lim_{z \to 1} (z-1)X(z)$**

---

# PROBLEMS

1. Find the Z-transform and ROC of the following sequences:
   (a) $x_1(n) = \{2, 1, 3, -4, 1, 2\}$ with $\uparrow$ at n=0
   (b) $x_2(n) = \{1, 3, -2, 0, 2, 4\}$ with $\uparrow$ at n=0
   (c) $x_3(n) = \{2, 4, 1, 0, 1, 3, 5\}$ with $\uparrow$ at n=0

2. Using properties of Z-transform, find the Z-transform of the following sequences:
   (a) $x_1(n) = n u(n-1)$
   (b) $x_2(n) = n^2 u(n)$
   (c) $x_3(n) = n\left(\frac{1}{2}\right)^n u(n)$
   (d) $x_4(n) = 2^n n \cos 3n u(n)$
   (e) $x_5(n) = \left(\frac{1}{3}\right)^n \sin\left(\frac{n\pi}{4}\right)u(n)$
   (f) $x_6(n) = \begin{cases} 0 & n \le 0 \\ 1 & 0 \le n \le 9 \\ 0 & n > 9 \end{cases}$
   (g) $x_7(n) = \left(\frac{4}{5}\right)^n u(n) - 2\left(\frac{3}{5}\right)^n u(n)$
   (h) $x_8(n) = \left(\frac{1}{3}\right)^n u(-n)$
   (i) $x_9(n) = \left(\frac{1}{2}\right)^n [u(n) - u(n-8)]$
   (j) $x_{10}(n) = 3(2)^n u(-n)$
   (k) $x_{11}(n) = n^2\left(\frac{1}{3}\right)^n u(n-3)$

3. Using power series expansion, find the inverse Z-transform of:
   (a) $X(z) = \frac{1}{1 - 1.5z^{-1} + 0.5z^{-2}}$, ROC: $|z| > 1$
   (b) $X(z) = \frac{1}{1 - 15z^{-1} + 0.5z^{-2}}$, ROC: $|z| > \frac{1}{2}$

4. Find the inverse Z-transform of the following:
   (a) $X(z) = \frac{1}{(1-z^{-1})(1-z^{-1})^2}$, ROC: $|z| > 1$
   (b) $X(z) = \frac{1 - \frac{1}{4}z^{-1}}{1 - \frac{5}{6}z^{-1} + \frac{1}{6}z^{-2}}$, ROC: $|z| > \frac{1}{2}$
   (c) $X(z) = \frac{1 - \frac{1}{3}z^{-1}}{1 - \frac{1}{9}z^{-2}}$, ROC: $|z| > \frac{1}{3}$
   (d) $X(z) = \frac{\frac{3}{2}z^3 - \frac{1}{2}z^2 - z}{z^3 - \frac{3}{2}z^2 + \frac{1}{2}z}$, ROC: $|z| > \frac{1}{2}$
   (e) $X(z) = \frac{1 - \frac{1}{2}z^{-1}}{1 - \frac{3}{4}z^{-1} + \frac{1}{8}z^{-2}}$, ROC: $|z| > \frac{1}{2}$
   (f) $X(z) = \frac{4 - 3z^{-1} + z^{-2}}{(1-2z^{-1})(1-3z^{-1})}$
   (g) $X(z) = \frac{3 - 2z^{-1}}{(1-z^{-1})(1-\frac{1}{3}z^{-1})}$

5. Using partial fraction expansion method, obtain all possible inverse Z-transforms of:
   (a) $X(z) = \frac{1 - \frac{1}{4}z^{-1}}{(1-\frac{1}{2}z^{-1})(1-\frac{1}{4}z^{-1})}$
   (b) $X(z) = \frac{3z^2 - 3z + 1}{z^3 - 2z^2 + z}$
   (c) $X(z) = \frac{1 - 2z^{-1}}{1 - \frac{3}{2}z^{-1} + \frac{1}{2}z^{-2}}$
   (d) $X(z) = \frac{2z^2 - 4z + 3}{(z-2)(z-3)}$

6. Using convolution theorem, find the inverse Z-transform for:
   (a) $X(z) = \frac{z}{(z-1)^2}$
   (b) $X(z) = \frac{z}{(z-1)^3}$

7. Determine the inverse Z-transform of $X(z) = \frac{1}{1-z^{-3}}$

8. A causal discrete-time LTI system is to be designed with the property that if the input is $x(n) = \left(\frac{1}{2}\right)^n u(n) + \frac{1}{4}\left(\frac{1}{2}\right)^n u(n-1)$, then the output is $y(n) = \left(\frac{1}{3}\right)^n u(n)$. Determine the impulse response h(n) and the system function H(z).

9. A system has impulse response $h(n) = \left(\frac{1}{2}\right)^n u(n)$. Determine the input to the system if the output is given by $y(n) = \left(\frac{1}{3}\right)^n u(n) + 2\left(\frac{1}{2}\right)^n u(n)$.

10. Determine whether the system described below is causal and stable:
    (a) $H(z) = \frac{z^2}{z^2 - \frac{5}{16}z + 1}$
    (b) $H(z) = \frac{1 - z^{-1}}{1 - \frac{14}{8}z^{-1} + \frac{49}{64}z^{-2}}$

11. Find the natural response of the system described by the difference equation:
    $y(n) - \frac{1}{4}y(n-1) + \frac{1}{8}y(n-2) = x(n) - x(n-1)$
    with $y(-1) = 0$ and $y(-2) = -1$.

12. Determine the forced response of the system described by the difference equation:
    $y(n) - \frac{5}{6}y(n-1) + \frac{1}{6}y(n-2) = x(n)$
    if input $x(n) = 2^n u(n)$.

13. Solve the following difference equation with x(n) = u(n) and initial condition y(-1) = 1:
    $y(n) - \frac{1}{2}y(n-1) = x(n)$

14. Solve the following difference equation for the given initial conditions and input:
    $y(n) - \frac{1}{9}y(n-2) = x(n-1)$
    with $y(-1) = 0$, $y(-2) = -1$, and $x(n) = 3^n u(n)$.

15. Solve the following difference equation using unilateral Z-transform:
    $y(n) - \frac{3}{2}y(n-1) + \frac{1}{2}y(n-2) = x(n)$ for $n \ge 0$
    with initial conditions $y(-1) = 4$, $y(-2) = 10$ and $x(n) = \left(\frac{1}{4}\right)^n u(n)$.

16. Determine the step response of the system:
    $y(n) = \alpha y(n-1) + x(n)$
    with the initial condition $y(-1) = 1$, $-1 < \alpha < 1$.

17. Find the impulse response and step response of the following system:
    $y(n) - \frac{3}{4}y(n-1) + \frac{1}{8}y(n-2) = x(n)$

18. Find the output y(n) of an LTI discrete-time system specified by the following equation:
    $y(n) - \frac{3}{2}y(n-1) + \frac{3}{2}y(n-2) = 2x(n) - \frac{3}{2}x(n-1)$
    if the initial conditions are $y(-1) = 0$, $y(-2) = 1$ and $x(n) = \left(\frac{1}{4}\right)^n u(n)$.

19. Find the response of:
    $y(n) + y(n-1) - 2y(n-2) = u(n-1) + 2u(n-2)$
    due to $y(-1) = 0.5$, $y(-2) = 0.25$.

20. Solve the following difference equation:
    $y(n) - y(n-1) + \frac{1}{4}y(n-2) = x(n)$
    where $x(n) = 2\left(\frac{1}{8}\right)^n u(n)$, $y(-1) = 2$, $y(-2) = 1$.

21. A causal system is represented by the following difference equation:
    $y(n) + \frac{1}{4}y(n-1) = x(n) + \frac{1}{2}x(n-1)$
    (a) Find the system function H(z) and give the corresponding ROC.
    (b) Find the unit sample response of the system.
    (c) Find the frequency response H(ω) and determine its magnitude and phase.

22. Find the output of the system whose input and output are related by:
    $y(n) = 7y(n-1) - 12y(n-2) + 2x(n) - x(n-2)$
    for the input $x(n) = u(n)$.

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| Z-transform | $X(z) = \sum_{n} x(n)z^{-n}$ |
| Inverse Z-transform | $x(n) = \frac{1}{2\pi j}\oint X(z)z^{n-1}dz$ |
| Time shifting | $Z[x(n-k)] = z^{-k}X(z)$ |
| Multiplication by exponential | $Z[a^nx(n)] = X(z/a)$ |
| Differentiation | $Z[nx(n)] = -z\frac{dX(z)}{dz}$ |
| Convolution | $Z[x(n)*h(n)] = X(z)H(z)$ |
| Initial value | $x(0) = \lim_{z\to\infty} X(z)$ |
| Final value | $x(\infty) = \lim_{z\to1} (z-1)X(z)$ |
| System function | $H(z) = \frac{Y(z)}{X(z)}$ |

---

## How to Save This File

1. Copy all the text from the code block above
2. Open a text editor (Notepad, VS Code, etc.)
3. Paste the content
4. Save the file with the name `Chapter_3_Z_Transforms.md`
5. The file will be properly formatted with headers, tables, equations, and examples