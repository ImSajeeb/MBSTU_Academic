# Chapter 5: Discrete-Time Fourier Transform (DTFT)

## 5.1 INTRODUCTION

The Fourier transform of a discrete-time signal is called the **Discrete-Time Fourier Transform (DTFT)**.

**Why DTFT is important**: Convolution in time domain converts to multiplication in frequency domain.

### Differences between DTFT and CTFT:

| Feature | CTFT (Analog) | DTFT (Discrete) |
|---------|---------------|-----------------|
| Frequency range | -∞ to ∞ | -π to π (periodic) |
| Operation | Integration | Summation |
| Periodicity | Not periodic | Periodic with 2π |

---

## 5.2 DISCRETE-TIME FOURIER TRANSFORM (DTFT)

### Definition:

$$X(\omega) = X(e^{j\omega}) = \sum_{n=-\infty}^{\infty} x(n)e^{-j\omega n}$$

### Inverse DTFT:

$$x(n) = \frac{1}{2\pi} \int_{-\pi}^{\pi} X(\omega)e^{j\omega n} d\omega$$

### DTFT Pair:

$$x(n) \xleftrightarrow{FT} X(\omega)$$

### Signal Spectrum:
$X(\omega)$ represents the frequency content of $x(n)$.

---

## 5.3 EXISTENCE OF DTFT

The DTFT exists if and only if the sequence is **absolutely summable**:

$$\sum_{n=-\infty}^{\infty} |x(n)| < \infty$$

**Important**: DTFT does not exist for growing exponential sequences like $a^n u(n)$ with $a > 1$.

---

## 5.4 RELATION BETWEEN Z-TRANSFORM AND DTFT

$$X(\omega) = X(z)|_{z = e^{j\omega}}$$

**The DTFT is the Z-transform evaluated along the unit circle.**

For $z = re^{j\omega}$:

$$X(z) = \sum_{n=-\infty}^{\infty} [x(n)r^{-n}]e^{-j\omega n} = \text{DTFT of } x(n)r^{-n}$$

**Conclusion**: When $r = 1$, DTFT = Z-transform on unit circle.

---

## EXAMPLE 5.1: DTFT of basic sequences

**(a)** $x(n) = \delta(n)$

### Solution:

$$X(\omega) = \sum_{n=-\infty}^{\infty} \delta(n)e^{-j\omega n} = \delta(0)e^0 = 1$$

$$\boxed{X(\omega) = 1}$$

---

**(b)** $x(n) = u(n)$

### Solution:

$$X(\omega) = \sum_{n=0}^{\infty} e^{-j\omega n} = \frac{1}{1-e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-e^{-j\omega}}}$$

---

**(c)** $x(n) = \delta(n-m)$

### Solution:

$$X(\omega) = \sum_{n=-\infty}^{\infty} \delta(n-m)e^{-j\omega n} = e^{-j\omega m}$$

$$\boxed{X(\omega) = e^{-j\omega m}}$$

---

**(d)** $x(n) = u(n-m)$

### Solution:

$$X(\omega) = \sum_{n=m}^{\infty} e^{-j\omega n} = e^{-j\omega m} \sum_{k=0}^{\infty} e^{-j\omega k}$$

$$= \frac{e^{-j\omega m}}{1-e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{e^{-j\omega m}}{1-e^{-j\omega}}}$$

---

**(e)** $x(n) = a^n u(n)$

### Solution:

$$X(\omega) = \sum_{n=0}^{\infty} a^n e^{-j\omega n} = \sum_{n=0}^{\infty} (ae^{-j\omega})^n = \frac{1}{1-ae^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-ae^{-j\omega}}}$$

---

**(f)** $x(n) = -a^n u(-n-1)$

### Solution:

$$X(\omega) = -\sum_{n=-\infty}^{-1} a^n e^{-j\omega n}$$

Let $m = -n$, then $n = -m$:

$$X(\omega) = -\sum_{m=1}^{\infty} a^{-m} e^{j\omega m} = -\sum_{m=1}^{\infty} (e^{j\omega}/a)^m$$

$$= -\frac{e^{j\omega}/a}{1-e^{j\omega}/a} = \frac{1}{1-ae^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-ae^{-j\omega}}}$$

---

**(g)** $x(n) = \delta(n+3) - \delta(n-3)$

### Solution:

$$X(\omega) = e^{j3\omega} - e^{-j3\omega} = 2j\sin(3\omega)$$

$$\boxed{X(\omega) = 2j\sin(3\omega)}$$

---

**(h)** $x(n) = u(n+3) - u(n-3)$

### Solution:

$x(n) = 1$ for $-3 \le n \le 2$

$$X(\omega) = \sum_{n=-3}^{2} e^{-j\omega n}$$

$$= e^{j3\omega} + e^{j2\omega} + e^{j\omega} + 1 + e^{-j\omega} + e^{-j2\omega}$$

$$= 1 + 2\cos\omega + 2\cos(2\omega) + 2\cos(3\omega)$$

$$\boxed{X(\omega) = 1 + 2\cos\omega + 2\cos 2\omega + 2\cos 3\omega}$$

---

## EXAMPLE 5.2: DTFT of various sequences

**(a)** $x(n) = \{1, -2, 2, 3\}$ with $\uparrow$ at n=0

### Solution:

$$X(\omega) = x(0) + x(1)e^{-j\omega} + x(2)e^{-j2\omega} + x(3)e^{-j3\omega}$$

$$= 1 - 2e^{-j\omega} + 2e^{-j2\omega} + 3e^{-j3\omega}$$

$$\boxed{X(\omega) = 1 - 2e^{-j\omega} + 2e^{-j2\omega} + 3e^{-j3\omega}}$$

---

**(b)** $x(n) = 3^n u(n)$

### Solution:

The sequence is not absolutely summable (since $3^n$ grows with n). Therefore, its DTFT does not exist.

$$\boxed{\text{DTFT does not exist}}$$

---

**(c)** $x(n) = (0.5)^n u(n) + 2^n u(-n-1)$

### Solution:

$$X(\omega) = \sum_{n=0}^{\infty} (0.5)^n e^{-j\omega n} + \sum_{n=-\infty}^{-1} 2^n e^{-j\omega n}$$

**First term**:
$$\sum_{n=0}^{\infty} (0.5e^{-j\omega})^n = \frac{1}{1-0.5e^{-j\omega}}$$

**Second term**: Let $m = -n$, $n = -m$:

$$\sum_{m=1}^{\infty} 2^{-m} e^{j\omega m} = \sum_{m=1}^{\infty} (e^{j\omega}/2)^m = \frac{e^{j\omega}/2}{1-e^{j\omega}/2}$$

$$X(\omega) = \frac{1}{1-0.5e^{-j\omega}} + \frac{e^{j\omega}/2}{1-e^{j\omega}/2}$$

$$\boxed{X(\omega) = \frac{1}{1-0.5e^{-j\omega}} + \frac{e^{j\omega}}{2-e^{j\omega}}}$$

---

**(d)** $x(n) = \left(\frac{1}{4}\right)^n u(-n-1)$

### Solution:

$$X(\omega) = \sum_{n=-\infty}^{-1} \left(\frac{1}{4}\right)^n e^{-j\omega n}$$

Let $m = -n$, $n = -m$:

$$X(\omega) = \sum_{m=1}^{\infty} \left(\frac{1}{4}\right)^{-m} e^{j\omega m} = \sum_{m=1}^{\infty} (4e^{j\omega})^m$$

$$= \frac{4e^{j\omega}}{1-4e^{j\omega}} = \frac{1}{1-\frac{1}{4}e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-\frac{1}{4}e^{-j\omega}}}$$

---

**(e)** $x(n) = \begin{cases} n & -4 \le n \le 4 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$X(\omega) = \sum_{n=-4}^{4} n e^{-j\omega n}$$

Using symmetry: $n$ is odd, so terms cancel in pairs:

$$X(\omega) = -4e^{j4\omega} - 3e^{j3\omega} - 2e^{j2\omega} - e^{j\omega} + 0 + e^{-j\omega} + 2e^{-j2\omega} + 3e^{-j3\omega} + 4e^{-j4\omega}$$

$$= 2j[\sin\omega + 2\sin 2\omega + 3\sin 3\omega + 4\sin 4\omega]$$

$$\boxed{X(\omega) = 2j(\sin\omega + 2\sin 2\omega + 3\sin 3\omega + 4\sin 4\omega)}$$

---

**(f)** $x(n) = \begin{cases} 1 & 0 \le n \le 3 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$X(\omega) = \sum_{n=0}^{3} e^{-j\omega n} = 1 + e^{-j\omega} + e^{-j2\omega} + e^{-j3\omega}$$

$$= e^{-j3\omega/2}(e^{j3\omega/2} + e^{j\omega/2} + e^{-j\omega/2} + e^{-j3\omega/2})$$

$$= e^{-j3\omega/2} \cdot \frac{\sin(2\omega)}{\sin(\omega/2)}$$

$$\boxed{X(\omega) = e^{-j3\omega/2} \frac{\sin 2\omega}{\sin(\omega/2)}}$$

---

**(g)** $x(n) = a^{|n|}$

### Solution:

$$X(\omega) = \sum_{n=-\infty}^{-1} a^{-n} e^{-j\omega n} + 1 + \sum_{n=1}^{\infty} a^n e^{-j\omega n}$$

$$= \frac{ae^{j\omega}}{1-ae^{j\omega}} + 1 + \frac{ae^{-j\omega}}{1-ae^{-j\omega}}$$

$$= \frac{1-a^2}{1-2a\cos\omega + a^2}$$

$$\boxed{X(\omega) = \frac{1-a^2}{1-2a\cos\omega + a^2}}$$

---

## EXAMPLE 5.3: DTFT of sinusoidal sequences

**(a)** $x(n) = \sin\left(\frac{n\pi}{2}\right)u(n)$

### Solution:

$$\sin\left(\frac{n\pi}{2}\right) = \frac{e^{j\pi n/2} - e^{-j\pi n/2}}{2j}$$

$$X(\omega) = \frac{1}{2j}\left[\frac{1}{1-e^{j\pi/2}e^{-j\omega}} - \frac{1}{1-e^{-j\pi/2}e^{-j\omega}}\right]$$

$$= \frac{1}{2j}\left[\frac{1}{1-je^{-j\omega}} - \frac{1}{1+je^{-j\omega}}\right]$$

$$= \frac{1}{2j}\left[\frac{2je^{-j\omega}}{1+e^{-j2\omega}}\right] = \frac{e^{-j\omega}}{1+e^{-j2\omega}}$$

$$\boxed{X(\omega) = \frac{e^{-j\omega}}{1+e^{-j2\omega}}}$$

---

**(b)** $x(n) = \cos\left(\frac{n\pi}{3}\right)u(n)$

### Solution:

$$\cos\left(\frac{n\pi}{3}\right) = \frac{e^{j\pi n/3} + e^{-j\pi n/3}}{2}$$

$$X(\omega) = \frac{1}{2}\left[\frac{1}{1-e^{j\pi/3}e^{-j\omega}} + \frac{1}{1-e^{-j\pi/3}e^{-j\omega}}\right]$$

$$= \frac{1 - \cos(\pi/3)e^{-j\omega}}{1 - 2\cos(\pi/3)e^{-j\omega} + e^{-j2\omega}}$$

$$= \frac{1 - \frac{1}{2}e^{-j\omega}}{1 - e^{-j\omega} + e^{-j2\omega}}$$

$$\boxed{X(\omega) = \frac{1 - 0.5e^{-j\omega}}{1 - e^{-j\omega} + e^{-j2\omega}}}$$

---

**(c)** $x(n) = \left(\frac{1}{2}\right)^n \sin\left(\frac{n\pi}{4}\right)u(n)$

### Solution:

$$X(\omega) = \frac{1}{2j}\left[\frac{1}{1-\frac{1}{2}e^{j\pi/4}e^{-j\omega}} - \frac{1}{1-\frac{1}{2}e^{-j\pi/4}e^{-j\omega}}\right]$$

$$= \frac{\frac{1}{2}\sin(\pi/4)e^{-j\omega}}{1 - \cos(\pi/4)e^{-j\omega} + \frac{1}{4}e^{-j2\omega}}$$

$$= \frac{0.3536e^{-j\omega}}{1 - 0.707e^{-j\omega} + 0.25e^{-j2\omega}}$$

$$\boxed{X(\omega) = \frac{0.3536e^{-j\omega}}{1 - 0.707e^{-j\omega} + 0.25e^{-j2\omega}}}$$

---

**(d)** $x(n) = \left(\frac{1}{2}\right)^{n-2} u(n-2)$

### Solution:

$$x(n) = \left(\frac{1}{2}\right)^{n-2} u(n-2)$$

$$X(\omega) = \sum_{n=2}^{\infty} \left(\frac{1}{2}\right)^{n-2} e^{-j\omega n}$$

Let $k = n-2$, $n = k+2$:

$$X(\omega) = \sum_{k=0}^{\infty} \left(\frac{1}{2}\right)^k e^{-j\omega(k+2)} = e^{-j2\omega} \sum_{k=0}^{\infty} (0.5e^{-j\omega})^k$$

$$= \frac{e^{-j2\omega}}{1-0.5e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{e^{-j2\omega}}{1-0.5e^{-j\omega}}}$$

---

**(e)** $x(n) = \cos(\omega_0 n)u(n)$

### Solution:

$$X(\omega) = \frac{1}{2}\left[\frac{1}{1-e^{j\omega_0}e^{-j\omega}} + \frac{1}{1-e^{-j\omega_0}e^{-j\omega}}\right]$$

$$= \frac{1 - \cos\omega_0 e^{-j\omega}}{1 - 2\cos\omega_0 e^{-j\omega} + e^{-j2\omega}}$$

$$\boxed{X(\omega) = \frac{1 - \cos\omega_0 e^{-j\omega}}{1 - 2\cos\omega_0 e^{-j\omega} + e^{-j2\omega}}}$$

---

**(f)** $x(n) = \sin(\omega_0 n)u(n)$

### Solution:

$$X(\omega) = \frac{\sin\omega_0 e^{-j\omega}}{1 - 2\cos\omega_0 e^{-j\omega} + e^{-j2\omega}}$$

$$\boxed{X(\omega) = \frac{\sin\omega_0 e^{-j\omega}}{1 - 2\cos\omega_0 e^{-j\omega} + e^{-j2\omega}}}$$

---

## EXAMPLE 5.4: DTFT of rectangular pulse

**Given**: $x(n) = \begin{cases} A & -N \le n \le N \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$X(\omega) = A\sum_{n=-N}^{N} e^{-j\omega n} = A \frac{\sin[(N+1/2)\omega]}{\sin(\omega/2)}$$

$$\boxed{X(\omega) = A\frac{\sin[(N+1/2)\omega]}{\sin(\omega/2)}}$$

---

## 5.5 INVERSE DTFT

$$x(n) = \frac{1}{2\pi} \int_{-\pi}^{\pi} X(\omega)e^{j\omega n} d\omega$$

---

## EXAMPLE 5.5: Inverse DTFT

**(a)** $X(\omega) = e^{-j\omega}$ for $-\pi \le \omega \le \pi$

### Solution:

$$x(n) = \frac{1}{2\pi} \int_{-\pi}^{\pi} e^{-j\omega} e^{j\omega n} d\omega = \frac{1}{2\pi} \int_{-\pi}^{\pi} e^{j\omega(n-1)} d\omega$$

$$= \frac{\sin[\pi(n-1)]}{\pi(n-1)} = \delta(n-1)$$

$$\boxed{x(n) = \delta(n-1)}$$

---

**(b)** $X(\omega) = e^{-j\omega}(1+\cos\omega)$

### Solution:

$$X(\omega) = e^{-j\omega} + \frac{1}{2}e^{-j\omega}(e^{j\omega} + e^{-j\omega}) = e^{-j\omega} + \frac{1}{2} + \frac{1}{2}e^{-j2\omega}$$

Taking inverse:

$$x(n) = \delta(n-1) + \frac{1}{2}\delta(n) + \frac{1}{2}\delta(n-2)$$

$$\boxed{x(n) = \left\{\frac{1}{2}, 1, \frac{1}{2}\right\}}$$

---

## EXAMPLE 5.6: Impulse response from frequency response

**Given**: $H(\omega) = \begin{cases} 1 & |\omega| \le \omega_0 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$h(n) = \frac{1}{2\pi} \int_{-\omega_0}^{\omega_0} e^{j\omega n} d\omega = \frac{1}{2\pi} \left[\frac{e^{j\omega n}}{jn}\right]_{-\omega_0}^{\omega_0}$$

$$= \frac{\sin(\omega_0 n)}{\pi n}$$

$$\boxed{h(n) = \frac{\sin(\omega_0 n)}{\pi n}}$$

---

## EXAMPLE 5.7: Inverse Fourier transform

**Given**: $X(\omega) = \begin{cases} 2 & -\frac{\pi}{3} \le \omega \le \frac{\pi}{3} \\ 1 & -\frac{2\pi}{3} \le \omega \le -\frac{\pi}{3} \text{ and } \frac{\pi}{3} \le \omega \le \frac{2\pi}{3} \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$x(n) = \frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{j\omega n}d\omega$$

$$= \frac{1}{2\pi}\left[\int_{-2\pi/3}^{-\pi/3} e^{j\omega n}d\omega + \int_{-\pi/3}^{\pi/3} 2e^{j\omega n}d\omega + \int_{\pi/3}^{2\pi/3} e^{j\omega n}d\omega\right]$$

$$= \frac{1}{2\pi}\left[\frac{e^{j\omega n}}{jn}\right]_{-2\pi/3}^{-\pi/3} + \frac{2}{2\pi}\left[\frac{e^{j\omega n}}{jn}\right]_{-\pi/3}^{\pi/3} + \frac{1}{2\pi}\left[\frac{e^{j\omega n}}{jn}\right]_{\pi/3}^{2\pi/3}$$

$$= \frac{2\sin(n\pi/3) + \sin(2n\pi/3)}{\pi n}$$

$$\boxed{x(n) = \frac{2\sin(n\pi/3) + \sin(2n\pi/3)}{\pi n}}$$

---

## 5.6 PROPERTIES OF DTFT

### 1. Linearity:
$$FT[ax_1(n) + bx_2(n)] = aX_1(\omega) + bX_2(\omega)$$

### 2. Periodicity:
$$X(\omega + 2\pi) = X(\omega)$$

### 3. Time Shifting:
$$FT[x(n-m)] = e^{-j\omega m}X(\omega)$$

### 4. Frequency Shifting:
$$FT[e^{j\omega_0 n}x(n)] = X(\omega - \omega_0)$$

### 5. Time Reversal:
$$FT[x(-n)] = X(-\omega)$$

### 6. Differentiation in Frequency Domain:
$$FT[nx(n)] = j\frac{dX(\omega)}{d\omega}$$

### 7. Time Convolution:
$$FT[x(n)*h(n)] = X(\omega)H(\omega)$$

### 8. Frequency Convolution:
$$FT[x(n)h(n)] = \frac{1}{2\pi}X(\omega)*H(\omega)$$

### 9. Correlation Theorem:
$$FT[R_{xy}(l)] = X(\omega)Y(-\omega)$$

### 10. Modulation Theorem:
$$FT[x(n)\cos\omega_0 n] = \frac{1}{2}[X(\omega-\omega_0) + X(\omega+\omega_0)]$$

### 11. Parseval's Theorem:
$$\sum_{n=-\infty}^{\infty} |x(n)|^2 = \frac{1}{2\pi}\int_{-\pi}^{\pi} |X(\omega)|^2 d\omega$$

### 12. Symmetry Properties:

| x(n) | X(ω) |
|------|------|
| Real | X(ω) = X*(-ω) (Conjugate symmetry) |
| Even | X(ω) is real |
| Odd | X(ω) is imaginary |
| Real and Even | X(ω) is real and even |
| Real and Odd | X(ω) is imaginary and odd |

---

## EXAMPLE 5.9: Using properties of DTFT

**(a)** $FT\left[\left(\frac{1}{4}\right)^{|n|}\right]$

### Solution:

We know $FT[a^{|n|}] = \frac{1-a^2}{1-2a\cos\omega + a^2}$

For $a = \frac{1}{4}$:

$$X(\omega) = \frac{1-\frac{1}{16}}{1-\frac{1}{2}\cos\omega + \frac{1}{16}} = \frac{15/16}{17/16 - \frac{1}{2}\cos\omega}$$

$$= \frac{15}{17 - 8\cos\omega}$$

$$\boxed{X(\omega) = \frac{15}{17-8\cos\omega}}$$

---

**(b)** $FT\left[\left(\frac{1}{3}\right)^{n-3}u(n-3)\right]$

### Solution:

Using time shifting property:

$$FT\left[\left(\frac{1}{3}\right)^n u(n)\right] = \frac{1}{1-\frac{1}{3}e^{-j\omega}}$$

$$FT\left[\left(\frac{1}{3}\right)^{n-3}u(n-3)\right] = e^{-j3\omega} \cdot \frac{1}{1-\frac{1}{3}e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{e^{-j3\omega}}{1-\frac{1}{3}e^{-j\omega}}}$$

---

**(c)** $FT[\delta(n+2) - \delta(n-2)]$

### Solution:

$$FT[\delta(n+2)] = e^{j2\omega}, \quad FT[\delta(n-2)] = e^{-j2\omega}$$

$$X(\omega) = e^{j2\omega} - e^{-j2\omega} = 2j\sin(2\omega)$$

$$\boxed{X(\omega) = 2j\sin(2\omega)}$$

---

**(d)** $FT[u(n-1) - u(n-2)]$

### Solution:

$x(n) = \begin{cases} 1 & 1 \le n \le 2 \\ 0 & \text{otherwise} \end{cases}$

$$X(\omega) = e^{-j\omega} + e^{-j2\omega}$$

$$\boxed{X(\omega) = e^{-j\omega}(1 + e^{-j\omega})}$$

---

**(e)** $FT[n^2u(n)]$

### Solution:

$$FT[nu(n)] = j\frac{d}{d\omega}\left[\frac{1}{1-e^{-j\omega}}\right]$$

$$FT[n^2u(n)] = j\frac{d}{d\omega}\left[FT[nu(n)]\right]$$

$$\boxed{X(\omega) = \frac{e^{-j\omega}(1+e^{-j\omega})}{(1-e^{-j\omega})^3}}$$

---

**(f)** $FT[u(-n)]$

### Solution:

Using time reversal: $FT[u(-n)] = FT[u(n)]$ with $\omega \to -\omega$

$$X(\omega) = \frac{1}{1-e^{j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-e^{j\omega}}}$$

---

**(g)** $FT[n3^n u(-n)]$

### Solution:

Using differentiation and time reversal:

$$FT[3^n u(-n)] = \frac{1}{1-3e^{j\omega}}$$

$$FT[n3^n u(-n)] = j\frac{d}{d\omega}\left[\frac{1}{1-3e^{j\omega}}\right]$$

$$\boxed{X(\omega) = \frac{3je^{j\omega}}{(1-3e^{j\omega})^2}}$$

---

**(h)** $FT[e^{3n}u(n)]$

### Solution:

$$FT[e^{3n}u(n)] = \frac{1}{1-e^3 e^{-j\omega}}$$

$$\boxed{X(\omega) = \frac{1}{1-e^{3-j\omega}}}$$

---

## EXAMPLE 5.10: Inverse FT of recursive filter

**Given**: $H(\omega) = \frac{1}{1-ae^{-j\omega}}$

### Solution:

$$H(\omega) = \sum_{n=0}^{\infty} a^n e^{-j\omega n}$$

Therefore:

$$\boxed{h(n) = a^n u(n)}$$

---

## EXAMPLE 5.11: Output sequence from output spectrum

**Given**: $Y(\omega) = \frac{1-e^{-j2\omega}}{4(1-ae^{-j\omega})}$

### Solution:

$$Y(\omega) = \frac{1}{4}\left[\frac{1}{1-ae^{-j\omega}} - \frac{e^{-j2\omega}}{1-ae^{-j\omega}}\right]$$

$$y(n) = \frac{1}{4}[a^n u(n) - a^{n-2}u(n-2)]$$

$$\boxed{y(n) = \frac{1}{4}[a^n u(n) - a^{n-2}u(n-2)]}$$

---

## EXAMPLE 5.12: Response using convolution property

**Given**: $h(n) = \{1, 2, 1, -2\}$, $x(n) = \{1, 3, 2, 1\}$

### Solution:

$$X(\omega) = 1 + 3e^{-j\omega} + 2e^{-j2\omega} + e^{-j3\omega}$$

$$H(\omega) = 1 + 2e^{-j\omega} + e^{-j2\omega} - 2e^{-j3\omega}$$

$$Y(\omega) = X(\omega)H(\omega) = 1 + 5e^{-j\omega} + 9e^{-j2\omega} + 6e^{-j3\omega} - 2e^{-j4\omega} - 3e^{-j5\omega} - 2e^{-j6\omega}$$

$$y(n) = \{1, 5, 9, 6, -2, -3, -2\}$$

$$\boxed{y(n) = \{1, 5, 9, 6, -2, -3, -2\}}$$

---

## EXAMPLE 5.13: Convolution using FT

**Given**: $x_1(n) = \left(\frac{1}{2}\right)^n u(n)$, $x_2(n) = \left(\frac{1}{3}\right)^n u(n)$

### Solution:

$$X_1(\omega) = \frac{1}{1-\frac{1}{2}e^{-j\omega}}$$

$$X_2(\omega) = \frac{1}{1-\frac{1}{3}e^{-j\omega}}$$

$$X(\omega) = X_1(\omega)X_2(\omega) = \frac{1}{(1-\frac{1}{2}e^{-j\omega})(1-\frac{1}{3}e^{-j\omega})}$$

Using partial fractions:

$$X(\omega) = \frac{3}{1-\frac{1}{2}e^{-j\omega}} - \frac{2}{1-\frac{1}{3}e^{-j\omega}}$$

$$x(n) = 3\left(\frac{1}{2}\right)^n u(n) - 2\left(\frac{1}{3}\right)^n u(n)$$

$$\boxed{x(n) = 3\left(\frac{1}{2}\right)^n u(n) - 2\left(\frac{1}{3}\right)^n u(n)}$$

---

## EXAMPLE 5.14: Response of LTI system

**Given**: $h(n) = \left(\frac{1}{2}\right)^n u(n)$, $x(n) = \left(\frac{3}{4}\right)^n u(n)$

### Solution:

$$H(\omega) = \frac{1}{1-\frac{1}{2}e^{-j\omega}}$$

$$X(\omega) = \frac{1}{1-\frac{3}{4}e^{-j\omega}}$$

$$Y(\omega) = \frac{1}{(1-\frac{1}{2}e^{-j\omega})(1-\frac{3}{4}e^{-j\omega})}$$

Using partial fractions:

$$Y(\omega) = \frac{3}{1-\frac{1}{2}e^{-j\omega}} - \frac{2}{1-\frac{3}{4}e^{-j\omega}}$$

$$y(n) = 3\left(\frac{1}{2}\right)^n u(n) - 2\left(\frac{3}{4}\right)^n u(n)$$

$$\boxed{y(n) = 3\left(\frac{1}{2}\right)^n u(n) - 2\left(\frac{3}{4}\right)^n u(n)}$$

---

## 5.7 TRANSFER FUNCTION

$$H(\omega) = \frac{Y(\omega)}{X(\omega)}$$

**The transfer function is the Fourier transform of the impulse response:**

$$H(\omega) = FT[h(n)]$$

---

## 5.8 FREQUENCY RESPONSE OF DISCRETE-TIME SYSTEMS

For input $x(n) = e^{j\omega n}$:

$$y(n) = H(\omega)e^{j\omega n}$$

**Frequency response**:
- $|H(\omega)|$: Magnitude response
- $\angle H(\omega)$: Phase response

### Properties of Frequency Response:
1. Periodic in $\omega$ with period $2\pi$
2. $|H(\omega)|$ is even (for real h(n))
3. $\angle H(\omega)$ is odd (for real h(n))

---

## EXAMPLE 5.15: Difference equation from frequency response

**Given**: $H(\omega) = \frac{1 - 3e^{-j\omega} + e^{-j2\omega}}{1 - \frac{1}{3}e^{-j\omega} + \frac{1}{6}e^{-j2\omega}}$

### Solution:

Cross multiplying:

$$Y(\omega)\left(1 - \frac{1}{3}e^{-j\omega} + \frac{1}{6}e^{-j2\omega}\right) = X(\omega)(1 - 3e^{-j\omega} + e^{-j2\omega})$$

Taking inverse FT:

$$y(n) - \frac{1}{3}y(n-1) + \frac{1}{6}y(n-2) = x(n) - 3x(n-1) + x(n-2)$$

$$\boxed{y(n) - \frac{1}{3}y(n-1) + \frac{1}{6}y(n-2) = x(n) - 3x(n-1) + x(n-2)}$$

---

## EXAMPLE 5.16: Frequency response of causal systems

**(a)** $y(n) - \frac{3}{16}y(n-1) + \frac{1}{2}y(n-2) = x(n) - x(n-1)$

### Solution:

$$H(\omega) = \frac{1 - e^{-j\omega}}{1 - \frac{3}{16}e^{-j\omega} + \frac{1}{2}e^{-j2\omega}}$$

$$\boxed{H(\omega) = \frac{1 - e^{-j\omega}}{1 - \frac{3}{16}e^{-j\omega} + \frac{1}{2}e^{-j2\omega}}}$$

---

**(b)** $y(n) - \frac{1}{4}y(n-1) + \frac{3}{8}y(n-2) = x(n) - x(n-1)$

### Solution:

$$H(\omega) = \frac{1 - e^{-j\omega}}{1 - \frac{1}{4}e^{-j\omega} + \frac{3}{8}e^{-j2\omega}}$$

$$\boxed{H(\omega) = \frac{1 - e^{-j\omega}}{1 - \frac{1}{4}e^{-j\omega} + \frac{3}{8}e^{-j2\omega}}}$$

---

## EXAMPLE 5.17: Magnitude and phase response

**Given**: $y(n) - 5y(n-1) = x(n) - 4x(n-1)$

### Solution:

$$H(\omega) = \frac{1 - 4e^{-j\omega}}{1 - 5e^{-j\omega}} = \frac{(4-\cos\omega) - j\sin\omega}{(5-\cos\omega) - j\sin\omega}$$

**Magnitude response**:

$$|H(\omega)| = \sqrt{\frac{(4-\cos\omega)^2 + \sin^2\omega}{(5-\cos\omega)^2 + \sin^2\omega}} = \sqrt{\frac{17 - 8\cos\omega}{26 - 10\cos\omega}}$$

$$\boxed{|H(\omega)| = \sqrt{\frac{17 - 8\cos\omega}{26 - 10\cos\omega}}}$$

**Phase response**:

$$\angle H(\omega) = \tan^{-1}\left(\frac{-\sin\omega}{4-\cos\omega}\right) - \tan^{-1}\left(\frac{-\sin\omega}{5-\cos\omega}\right)$$

$$\boxed{\angle H(\omega) = \tan^{-1}\left(\frac{-\sin\omega}{4-\cos\omega}\right) - \tan^{-1}\left(\frac{-\sin\omega}{5-\cos\omega}\right)}$$

---

## EXAMPLE 5.18: Magnitude and phase response

**Given**: $y(n) = x(n) + 2x(n-1) + x(n-2)$

### Solution:

$$H(\omega) = 1 + 2e^{-j\omega} + e^{-j2\omega}$$

$$= e^{-j\omega}(2 + 2\cos\omega) = 2e^{-j\omega}(1 + \cos\omega)$$

$$|H(\omega)| = 2|1 + \cos\omega|$$

$$\boxed{|H(\omega)| = 2|1 + \cos\omega|}$$

$$\angle H(\omega) = -\omega$$

$$\boxed{\angle H(\omega) = -\omega}$$

---

## EXAMPLE 5.19: Transfer function of rectangular impulse response

**Given**: $h(n) = \begin{cases} 1 & 0 \le n \le N-1 \\ 0 & \text{otherwise} \end{cases}$

### Solution:

$$H(\omega) = \sum_{n=0}^{N-1} e^{-j\omega n} = \frac{1-e^{-j\omega N}}{1-e^{-j\omega}}$$

$$= e^{-j\omega(N-1)/2} \frac{\sin(\omega N/2)}{\sin(\omega/2)}$$

$$|H(\omega)| = \left|\frac{\sin(\omega N/2)}{\sin(\omega/2)}\right|$$

$$\boxed{|H(\omega)| = \left|\frac{\sin(\omega N/2)}{\sin(\omega/2)}\right|}$$

$$\angle H(\omega) = -\frac{\omega(N-1)}{2}$$

$$\boxed{\angle H(\omega) = -\frac{\omega(N-1)}{2}}$$

---

## EXAMPLE 5.20: Frequency response of exponential impulse response

**Given**: $h(n) = 0.6^n u(n)$

### Solution:

$$H(\omega) = \frac{1}{1-0.6e^{-j\omega}}$$

$$= \frac{1}{1-0.6\cos\omega + j0.6\sin\omega}$$

$$= \frac{1-0.6\cos\omega - j0.6\sin\omega}{(1-0.6\cos\omega)^2 + (0.6\sin\omega)^2}$$

$$= \frac{1-0.6\cos\omega - j0.6\sin\omega}{1.36 - 1.2\cos\omega}$$

$$|H(\omega)| = \frac{1}{\sqrt{1.36 - 1.2\cos\omega}}$$

$$\boxed{|H(\omega)| = \frac{1}{\sqrt{1.36 - 1.2\cos\omega}}}$$

$$\angle H(\omega) = \tan^{-1}\left(\frac{-0.6\sin\omega}{1-0.6\cos\omega}\right)$$

$$\boxed{\angle H(\omega) = \tan^{-1}\left(\frac{-0.6\sin\omega}{1-0.6\cos\omega}\right)}$$

---

## EXAMPLE 5.21: All-pass system

**Given**: $y(n) - ay(n-1) = bx(n) + x(n-1)$, $|a| < 1$

**Find**: $b$ such that $|H(\omega)| = 1$ for all $\omega$

### Solution:

$$H(\omega) = \frac{b + e^{-j\omega}}{1 - ae^{-j\omega}}$$

$$|H(\omega)|^2 = \frac{b^2 + 1 + 2b\cos\omega}{1 + a^2 - 2a\cos\omega}$$

For $|H(\omega)| = 1$ for all $\omega$:

$$b^2 + 1 + 2b\cos\omega = 1 + a^2 - 2a\cos\omega$$

Comparing coefficients:

$$b^2 + 1 = 1 + a^2 \Rightarrow b^2 = a^2$$
$$2b = -2a \Rightarrow b = -a$$

$$\boxed{b = -a}$$

---

## EXAMPLE 5.22: Frequency response from input-output relation

**Given**: $x(n) = \left(\frac{4}{5}\right)^n u(n)$, $y(n) = n\left(\frac{4}{5}\right)^n u(n)$

### Solution:

$$X(\omega) = \frac{1}{1-\frac{4}{5}e^{-j\omega}}$$

$$Y(\omega) = FT[nx(n)] = j\frac{dX(\omega)}{d\omega}$$

$$= \frac{\frac{4}{5}e^{-j\omega}}{(1-\frac{4}{5}e^{-j\omega})^2}$$

$$H(\omega) = \frac{Y(\omega)}{X(\omega)} = \frac{\frac{4}{5}e^{-j\omega}}{1-\frac{4}{5}e^{-j\omega}}$$

**Difference equation**:
$$y(n) - \frac{4}{5}y(n-1) = \frac{4}{5}x(n-1)$$

$$\boxed{H(\omega) = \frac{\frac{4}{5}e^{-j\omega}}{1-\frac{4}{5}e^{-j\omega}}}$$

---

## EXAMPLE 5.23: Impulse response of ideal filters

### (a) Low-pass filter:

$$H(\omega) = \begin{cases} 1 & |\omega| \le \omega_c \\ 0 & \text{otherwise} \end{cases}$$

$$h(n) = \frac{\sin(\omega_c n)}{\pi n}$$

$$\boxed{h(n) = \frac{\sin(\omega_c n)}{\pi n}}$$

---

### (b) High-pass filter:

$$H(\omega) = \begin{cases} 0 & |\omega| \le \omega_c \\ 1 & \text{otherwise} \end{cases}$$

$$h(n) = \delta(n) - \frac{\sin(\omega_c n)}{\pi n}$$

$$\boxed{h(n) = \delta(n) - \frac{\sin(\omega_c n)}{\pi n}}$$

---

### (c) Band-pass filter:

$$H(\omega) = \begin{cases} 1 & \omega_{c1} \le |\omega| \le \omega_{c2} \\ 0 & \text{otherwise} \end{cases}$$

$$h(n) = \frac{\sin(\omega_{c2}n)}{\pi n} - \frac{\sin(\omega_{c1}n)}{\pi n}$$

$$\boxed{h(n) = \frac{\sin(\omega_{c2}n)}{\pi n} - \frac{\sin(\omega_{c1}n)}{\pi n}}$$

---

### (d) Band-stop filter:

$$H(\omega) = \begin{cases} 0 & \omega_{c1} \le |\omega| \le \omega_{c2} \\ 1 & \text{otherwise} \end{cases}$$

$$h(n) = \delta(n) - \frac{\sin(\omega_{c2}n)}{\pi n} + \frac{\sin(\omega_{c1}n)}{\pi n}$$

$$\boxed{h(n) = \delta(n) - \frac{\sin(\omega_{c2}n)}{\pi n} + \frac{\sin(\omega_{c1}n)}{\pi n}}$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. Define DTFT.**

$$X(\omega) = \sum_{n=-\infty}^{\infty} x(n)e^{-j\omega n}$$

---

**2. Define inverse DTFT.**

$$x(n) = \frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{j\omega n}d\omega$$

---

**3. What is the sufficient condition for DTFT to exist?**

The sequence must be absolutely summable: $\sum |x(n)| < \infty$

---

**4. What is the relation between DTFT and Z-transform?**

$$X(\omega) = X(z)|_{z=e^{j\omega}}$$

---

**5. What is the frequency response of an LTI system?**

The Fourier transform of the impulse response: $H(\omega) = FT[h(n)]$

---

**6. What are the properties of frequency response?**

1. Periodic in $\omega$ with period $2\pi$
2. $|H(\omega)|$ is even (for real h(n))
3. $\angle H(\omega)$ is odd (for real h(n))

---

**7. State Parseval's theorem.**

$$\sum_{n=-\infty}^{\infty} |x(n)|^2 = \frac{1}{2\pi}\int_{-\pi}^{\pi} |X(\omega)|^2 d\omega$$

---

**8. If $FT[x(n)] = X(\omega)$, what is $FT[x(n-m)]$?**

$$FT[x(n-m)] = e^{-j\omega m}X(\omega)$$

---

**9. If $FT[x(n)] = X(\omega)$, what is $FT[x(-n)]$?**

$$FT[x(-n)] = X(-\omega)$$

---

**10. If $FT[x(n)] = X(\omega)$, what is $FT[nx(n)]$?**

$$FT[nx(n)] = j\frac{dX(\omega)}{d\omega}$$

---

# FILL IN THE BLANKS

1. The DTFT of x(n) is defined as **$X(\omega) = \sum_{n=-\infty}^{\infty} x(n)e^{-j\omega n}$**.

2. The DTFT exists only if **$\sum |x(n)| < \infty$**.

3. The FT of a discrete-time signal is called **signal spectrum**.

4. The FT of a discrete-time signal is periodic with period **$2\pi$**.

5. The FT of an analog signal involves **integration**, but the FT of discrete-time signal involves **summation**.

6. The FT of analog signals consists of a spectrum with frequency range **$-\infty$ to $\infty$**, but the FT of a discrete-time signal is unique in the range **$-\pi$ to $\pi$**.

7. The inverse FT of X(ω) is defined as **$x(n) = \frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{j\omega n}d\omega$**.

8. The FT of x(n) is nothing but the Z-transform of x(n) evaluated along the **unit circle** centred at the origin of z-plane.

9. The relation between DTFT X(ω) and Z-transform X(z) is **$X(\omega) = X(z)|_{z=e^{j\omega}}$**.

10. The FT of a discrete and aperiodic sequence is **continuous and periodic**.

11. The frequency response of LTI system is given by the FT of the **impulse response** of the system.

12. The impulse response is the inverse FT of the **frequency response** of the system.

13. The ratio of the FT of the output to the FT of the input is called the **transfer function** or **frequency response** of the system.

14. The frequency response has two components: **magnitude response** and **phase response**.

15. If h(n) is real, then |H(ω)| is **even** and $\angle H(\omega)$ is **odd**.

---

# OBJECTIVE TYPE QUESTIONS

**1. The DTFT of a sequence x(n) is defined as X(ω) =**
(a) $\sum_{n=-\infty}^{\infty} x(n)e^{j\omega n}$ (b) $\sum_{n=-\infty}^{\infty} x(n)e^{-j\omega n}$

**Answer: (b)**

---

**2. The inverse DTFT of X(ω) is defined as x(n) =**
(a) $\frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{j\omega n}d\omega$ (b) $\frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{-j\omega n}d\omega$

**Answer: (a)**

---

**3. The FT of a discrete-time signal is periodic with period**
(a) 2π (b) π (c) 2 (d) finite

**Answer: (a)**

---

**4. The relation between DTFT and Z-transform is X(ω) =**
(a) $X(z)|_{z=e^{j\omega}}$ (b) $X(z)|_{z=e^{-j\omega}}$ (c) $X(z)|_{z=j\omega}$ (d) none

**Answer: (a)**

---

**5. The frequency response of LTI system is given by the FT of the _____ of the system.**
(a) transfer function (b) output (c) impulse response (d) input

**Answer: (c)**

---

**6. The FT of x(n) * h(n) is equal to**
(a) X(ω)H(ω) (b) X(ω)*H(ω) (c) X(ω)H(-ω) (d) X(ω)*H(-ω)

**Answer: (a)**

---

**7. The FT of δ(n) is**
(a) 0 (b) 1 (c) ∞ (d) not defined

**Answer: (b)**

---

**8. The FT of u(n) is**
(a) $\frac{1}{1-e^{-j\omega}}$ (b) $\frac{1}{1+e^{-j\omega}}$ (c) $\frac{1}{1-e^{j\omega}}$ (d) $\frac{1}{1+e^{j\omega}}$

**Answer: (a)**

---

**9. The FT of $a^n u(n)$ is**
(a) $\frac{1}{1-ae^{-j\omega}}$ (b) $\frac{1}{1+ae^{-j\omega}}$ (c) $\frac{1}{1-ae^{j\omega}}$ (d) $\frac{1}{1+ae^{j\omega}}$

**Answer: (a)**

---

**10. The FT of $2^n u(n)$**
(a) exists (b) does not exist (c) is zero (d) is infinite

**Answer: (b)** (not absolutely summable)

---

# PROBLEMS

1. Find the DTFT of:
   (a) $x(n) = \{2, 1, 3, 2\}$ with $\uparrow$ at n=0
   (b) $x(n) = \left(\frac{1}{4}\right)^{n-2}u(n-2)$
   (c) $x(n) = (0.2)^n u(n) + 2^n u(-n-1)$
   (d) $x(n) = a^{|n|}\cos\omega_0 n$

2. Using properties of DTFT, find the FT of:
   (a) $\left(\frac{1}{2}\right)^{|n-3|}$
   (b) $\left(\frac{1}{2}\right)^{n-4}u(n-4)$
   (c) $nu(-n)$
   (d) $e^{j2n}u(n)$
   (e) $n3^{-n}u(-n)$

3. The impulse response of an LTI system is $h(n) = \{1, 2, 1, -1\}$. Find the response for input $x(n) = \{1, 3, 2, 1\}$.

4. Find the convolution of the sequences $x_1(n) = \{1, 1, 1\}$ and $x_2(n) = \{1, 1, 1\}$.

5. Find the frequency response of $x(n) = \{2, 1, 2\}$.

6. Determine the output sequence from the spectrum:
   $Y(\omega) = \frac{1-e^{-j3\omega}}{1-ae^{-j\omega}}$

7. A system has unit sample response $h(n) = \frac{1}{4}\delta(n-1) + \frac{1}{2}\delta(n) + \frac{1}{4}\delta(n+1)$. Find the frequency response.

8. Determine frequency response, magnitude response, and phase response of:
   $y(n) = x(n) + 0.81x(n-1) + 0.81x(n-2) + 0.45y(n-2)$

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| DTFT | $X(\omega) = \sum_n x(n)e^{-j\omega n}$ |
| Inverse DTFT | $x(n) = \frac{1}{2\pi}\int_{-\pi}^{\pi} X(\omega)e^{j\omega n}d\omega$ |
| Time Shifting | $FT[x(n-m)] = e^{-j\omega m}X(\omega)$ |
| Frequency Shifting | $FT[e^{j\omega_0 n}x(n)] = X(\omega-\omega_0)$ |
| Time Reversal | $FT[x(-n)] = X(-\omega)$ |
| Differentiation | $FT[nx(n)] = j\frac{dX(\omega)}{d\omega}$ |
| Convolution | $FT[x(n)*h(n)] = X(\omega)H(\omega)$ |
| Parseval | $\sum |x(n)|^2 = \frac{1}{2\pi}\int |X(\omega)|^2 d\omega$ |
| Transfer Function | $H(\omega) = Y(\omega)/X(\omega)$ |
| Frequency Response | $H(\omega) = FT[h(n)]$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 5.1 | DTFT of δ(n), u(n), δ(n-m), u(n-m), aⁿu(n), -aⁿu(-n-1), δ(n+3)-δ(n-3), u(n+3)-u(n-3) | ✅ |
| 5.2 | DTFT of finite sequences, exponential, two-sided, a^|n| | ✅ |
| 5.3 | DTFT of sinusoidal sequences (6 parts) | ✅ |
| 5.4 | DTFT of rectangular pulse | ✅ |
| 5.5 | Inverse DTFT (2 parts) | ✅ |
| 5.6 | Impulse response from frequency response | ✅ |
| 5.7 | Inverse Fourier transform | ✅ |
| 5.8 | Inverse Fourier transform | ✅ |
| 5.9 | Using properties of DTFT (8 parts) | ✅ |
| 5.10 | Inverse FT of recursive filter | ✅ |
| 5.11 | Output sequence from output spectrum | ✅ |
| 5.12 | Response using convolution property | ✅ |
| 5.13 | Convolution using FT | ✅ |
| 5.14 | Response of LTI system | ✅ |
| 5.15 | Difference equation from frequency response | ✅ |
| 5.16 | Frequency response of causal systems (2 parts) | ✅ |
| 5.17 | Magnitude and phase response | ✅ |
| 5.18 | Magnitude and phase response | ✅ |
| 5.19 | Transfer function of rectangular impulse response | ✅ |
| 5.20 | Frequency response of exponential impulse response | ✅ |
| 5.21 | All-pass system | ✅ |
| 5.22 | Frequency response from input-output | ✅ |
| 5.23 | Impulse response of ideal filters (4 types) | ✅ |

---

## How to Save This File

1. Copy all the text from the code block above
2. Open a text editor (Notepad, VS Code, etc.)
3. Paste the content
4. Save the file with the name `Chapter_5_DTFT.md`
5. The file will be properly formatted with headers, tables, equations, and examples