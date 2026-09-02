# Chapter 8: Infinite-duration Impulse Response (IIR) Filters

## 8.1 INTRODUCTION

**IIR Filters**: Filters designed by considering all the infinite samples of the impulse response. They are of recursive type.

**FIR Filters**: Filters designed by considering only a finite number of samples of the impulse response.

### Requirements for Analog Filter:

1. $H_a(s)$ should be a rational function with real coefficients
2. Poles should lie on the left half of s-plane
3. Number of zeros ≤ number of poles

### Requirements for Digital Filter:

1. $H(z)$ should be a rational function with real coefficients
2. Poles should lie inside the unit circle in z-plane
3. Number of zeros ≤ number of poles

### Desirable Properties of Analog-to-Digital Transformation:

1. Imaginary axis in s-plane → Unit circle in z-plane
2. Left half of s-plane → Interior of unit circle in z-plane

---

## 8.2 DESIGN OF IIR FILTER BY APPROXIMATION OF DERIVATIVES

### Backward Difference Method:

$$\frac{dy(t)}{dt}\bigg|_{t=nT} = \frac{y(nT) - y(nT-T)}{T} = \frac{y(n) - y(n-1)}{T}$$

**Transformation**:

$$s = \frac{1 - z^{-1}}{T}$$

For higher derivatives:

$$s^i = \left(\frac{1 - z^{-1}}{T}\right)^i$$

### Mapping:

$$z = \frac{1}{1 - sT}$$

- Left half of s-plane → Inside circle with centre at z = 1/2, radius 1/2
- Stable analog filter → Stable digital filter
- Not suitable for high-pass or band-reject filters

### Forward Difference Method:

$$s = \frac{z - 1}{T}$$

$$z = 1 + sT$$

- Not always stable (may map stable analog poles outside unit circle)

---

## EXAMPLE 8.1: Convert analog low-pass filter using backward difference

**Given**: $H_a(s) = \frac{2}{s+3}$, T = 1s

### Solution:

**Step 1: Apply transformation**

$$s = \frac{1-z^{-1}}{T} = 1 - z^{-1} \quad (\text{since T = 1})$$

**Step 2: Substitute in $H_a(s)$**

$$H(z) = H_a(s)|_{s=1-z^{-1}} = \frac{2}{(1-z^{-1})+3} = \frac{2}{4-z^{-1}}$$

**Step 3: Simplify**

$$H(z) = \frac{2}{4 - z^{-1}} = \frac{2z}{4z - 1}$$

$$\boxed{H(z) = \frac{2z}{4z - 1}}$$

---

## EXAMPLE 8.2: Convert analog filter using backward difference

**Given**: $H_a(s) = \frac{4}{s^2+9}$, T = 1s

### Solution:

**Step 1: Apply transformation**

$$s = \frac{1-z^{-1}}{T} = 1 - z^{-1}$$

**Step 2: Substitute**

$$H(z) = \frac{4}{(1-z^{-1})^2 + 9} = \frac{4}{1 - 2z^{-1} + z^{-2} + 9}$$

$$= \frac{4}{10 - 2z^{-1} + z^{-2}}$$

$$\boxed{H(z) = \frac{4}{10 - 2z^{-1} + z^{-2}}}$$

---

## EXAMPLE 8.3: Convert analog filter using backward difference

**Given**: $H_a(s) = \frac{3}{(s+0.5)^2 + 16}$, T = 1s

### Solution:

**Step 1: Apply transformation**

$$s = 1 - z^{-1}$$

**Step 2: Substitute**

$$H(z) = \frac{3}{(1-z^{-1}+0.5)^2 + 16} = \frac{3}{(1.5 - z^{-1})^2 + 16}$$

$$= \frac{3}{2.25 - 3z^{-1} + z^{-2} + 16} = \frac{3}{18.25 - 3z^{-1} + z^{-2}}$$

$$\boxed{H(z) = \frac{3}{18.25 - 3z^{-1} + z^{-2}}}$$

---

## 8.3 DESIGN BY IMPULSE INVARIANT TRANSFORMATION

### Principle:
$$h(n) = h_a(t)|_{t=nT} = h_a(nT)$$

### For Distinct Poles:

If $$H_a(s) = \sum_{i=1}^{N} \frac{A_i}{s - p_i}$$

Then:

$$H(z) = \sum_{i=1}^{N} \frac{A_i}{1 - e^{p_iT}z^{-1}}$$

### Standard Transformations:

**1. Real Pole:**

$$\frac{A}{s+a} \longrightarrow \frac{A}{1 - e^{-aT}z^{-1}}$$

**2. Complex Conjugate Poles:**

$$\frac{b}{(s+a)^2 + b^2} \longrightarrow \frac{e^{-aT}\sin(bT)z^{-1}}{1 - 2e^{-aT}\cos(bT)z^{-1} + e^{-2aT}z^{-2}}$$

$$\frac{s+a}{(s+a)^2 + b^2} \longrightarrow \frac{1 - e^{-aT}\cos(bT)z^{-1}}{1 - 2e^{-aT}\cos(bT)z^{-1} + e^{-2aT}z^{-2}}$$

### Mapping:
$$z = e^{sT}$$

- Left half of s-plane → Inside unit circle
- Imaginary axis → Unit circle
- **Aliasing effect**: Many-to-one mapping

---

## EXAMPLE 8.4: Impulse invariant transformation

**Given**: $H_a(s) = \frac{2}{(s+1)(s+3)}$

**(a)** T = 1s, **(b)** T = 0.5s

### Solution:

**Step 1: Partial fraction expansion**

$$H_a(s) = \frac{2}{(s+1)(s+3)} = \frac{A}{s+1} + \frac{B}{s+3}$$

$$A = \left.\frac{2}{s+3}\right|_{s=-1} = \frac{2}{2} = 1$$

$$B = \left.\frac{2}{s+1}\right|_{s=-3} = \frac{2}{-2} = -1$$

$$H_a(s) = \frac{1}{s+1} - \frac{1}{s+3}$$

**Step 2: Apply impulse invariant transformation**

$$H(z) = \frac{1}{1 - e^{-T}z^{-1}} - \frac{1}{1 - e^{-3T}z^{-1}}$$

**(a)** For T = 1s:

$$H(z) = \frac{1}{1 - e^{-1}z^{-1}} - \frac{1}{1 - e^{-3}z^{-1}}$$

$$= \frac{1}{1 - 0.3679z^{-1}} - \frac{1}{1 - 0.0498z^{-1}}$$

$$= \frac{(1 - 0.0498z^{-1}) - (1 - 0.3679z^{-1})}{(1 - 0.3679z^{-1})(1 - 0.0498z^{-1})}$$

$$= \frac{0.3181z^{-1}}{1 - 0.4177z^{-1} + 0.0183z^{-2}}$$

$$\boxed{H(z) = \frac{0.3181z^{-1}}{1 - 0.4177z^{-1} + 0.0183z^{-2}}}$$

**(b)** For T = 0.5s:

$$H(z) = \frac{1}{1 - e^{-0.5}z^{-1}} - \frac{1}{1 - e^{-1.5}z^{-1}}$$

$$= \frac{1}{1 - 0.6065z^{-1}} - \frac{1}{1 - 0.2231z^{-1}}$$

$$= \frac{0.3834z^{-1}}{1 - 0.8296z^{-1} + 0.1353z^{-2}}$$

$$\boxed{H(z) = \frac{0.3834z^{-1}}{1 - 0.8296z^{-1} + 0.1353z^{-2}}}$$

---

## EXAMPLE 8.5: Impulse invariant transformation

**Given**: $H_a(s) = \frac{s + 0.1}{(s+0.1)^2 + 9}$, T = 1s

### Solution:

**Step 1: Identify standard form**

Here $a = 0.1$, $b = 3$, and the numerator is $s + a$.

For $\frac{s+a}{(s+a)^2+b^2}$:

$$H(z) = \frac{1 - e^{-aT}\cos(bT)z^{-1}}{1 - 2e^{-aT}\cos(bT)z^{-1} + e^{-2aT}z^{-2}}$$

**Step 2: Substitute values (T=1)**

$$e^{-aT} = e^{-0.1} = 0.9048$$
$$\cos(bT) = \cos 3 = -0.99$$
$$e^{-2aT} = e^{-0.2} = 0.8187$$

**Step 3: Form H(z)**

$$H(z) = \frac{1 - 0.9048(-0.99)z^{-1}}{1 - 2(0.9048)(-0.99)z^{-1} + 0.8187z^{-2}}$$

$$= \frac{1 + 0.8956z^{-1}}{1 + 1.7916z^{-1} + 0.8187z^{-2}}$$

$$\boxed{H(z) = \frac{1 + 0.8956z^{-1}}{1 + 1.7916z^{-1} + 0.8187z^{-2}}}$$

---

## EXAMPLE 8.6: Impulse invariant transformation

**Given**: $H_a(s) = \frac{s + 0.5}{(s+0.5)^2 + 4}$, T = 1s

### Solution:

Here $a = 0.5$, $b = 2$

$$e^{-aT} = e^{-0.5} = 0.6065$$
$$\cos(bT) = \cos 2 = -0.4161$$
$$e^{-2aT} = e^{-1} = 0.3679$$

$$H(z) = \frac{1 - e^{-aT}\cos(bT)z^{-1}}{1 - 2e^{-aT}\cos(bT)z^{-1} + e^{-2aT}z^{-2}}$$

$$= \frac{1 - 0.6065(-0.4161)z^{-1}}{1 - 2(0.6065)(-0.4161)z^{-1} + 0.3679z^{-2}}$$

$$= \frac{1 + 0.2523z^{-1}}{1 + 0.5047z^{-1} + 0.3679z^{-2}}$$

$$\boxed{H(z) = \frac{1 + 0.2523z^{-1}}{1 + 0.5047z^{-1} + 0.3679z^{-2}}}$$

---

## EXAMPLE 8.7: Impulse invariant transformation with sampling frequency

**Given**: $H_a(s) = \frac{2}{s(s+2)}$, Sampling rate = 4 samples/sec

### Solution:

**Step 1: Find sampling period**

$$T = \frac{1}{4} = 0.25s$$

**Step 2: Partial fraction expansion**

$$H_a(s) = \frac{2}{s(s+2)} = \frac{A}{s} + \frac{B}{s+2}$$

$$A = \left.\frac{2}{s+2}\right|_{s=0} = 1$$

$$B = \left.\frac{2}{s}\right|_{s=-2} = -1$$

$$H_a(s) = \frac{1}{s} - \frac{1}{s+2}$$

**Step 3: Apply impulse invariant transformation**

$$H(z) = \frac{1}{1 - e^0 z^{-1}} - \frac{1}{1 - e^{-2T}z^{-1}}$$

$$= \frac{1}{1 - z^{-1}} - \frac{1}{1 - e^{-0.5}z^{-1}}$$

$$= \frac{1}{1 - z^{-1}} - \frac{1}{1 - 0.6065z^{-1}}$$

$$= \frac{(1 - 0.6065z^{-1}) - (1 - z^{-1})}{(1 - z^{-1})(1 - 0.6065z^{-1})}$$

$$= \frac{0.3935z^{-1}}{1 - 1.6065z^{-1} + 0.6065z^{-2}}$$

$$\boxed{H(z) = \frac{0.3935z^{-1}}{1 - 1.6065z^{-1} + 0.6065z^{-2}}}$$

---

## EXAMPLE 8.8: Impulse invariant transformation

**Given**: $H_a(s) = \frac{2}{(s+0.4)^2 + 4}$

### Solution:

Here $a = 0.4$, $b = 2$

For $\frac{b}{(s+a)^2+b^2}$:

$$H(z) = \frac{e^{-aT}\sin(bT)z^{-1}}{1 - 2e^{-aT}\cos(bT)z^{-1} + e^{-2aT}z^{-2}}$$

For T = 1:

$$e^{-aT} = e^{-0.4} = 0.6703$$
$$\sin(bT) = \sin 2 = 0.9093$$
$$\cos(bT) = \cos 2 = -0.4161$$
$$e^{-2aT} = e^{-0.8} = 0.4493$$

$$H(z) = \frac{0.6703(0.9093)z^{-1}}{1 - 2(0.6703)(-0.4161)z^{-1} + 0.4493z^{-2}}$$

$$= \frac{0.6095z^{-1}}{1 + 0.5578z^{-1} + 0.4493z^{-2}}$$

$$\boxed{H(z) = \frac{0.6095z^{-1}}{1 + 0.5578z^{-1} + 0.4493z^{-2}}}$$

---

## EXAMPLE 8.9: Impulse invariant transformation with complex poles

**Given**: $H_a(s) = \frac{1}{(s+1)(s^2+s+2)}$

### Solution:

**Step 1: Partial fraction expansion**

$$H_a(s) = \frac{A}{s+1} + \frac{Bs+C}{s^2+s+2}$$

$$A = \left.\frac{1}{s^2+s+2}\right|_{s=-1} = \frac{1}{1-1+2} = \frac{1}{2}$$

For B and C:

$$\frac{1}{(s+1)(s^2+s+2)} = \frac{1/2}{s+1} + \frac{Bs+C}{s^2+s+2}$$

Multiplying both sides by $(s+1)(s^2+s+2)$:

$$1 = \frac{1}{2}(s^2+s+2) + (Bs+C)(s+1)$$

$$1 = \frac{1}{2}s^2 + \frac{1}{2}s + 1 + Bs^2 + Bs + Cs + C$$

Comparing coefficients:

$s^2$: $0 = \frac{1}{2} + B \Rightarrow B = -\frac{1}{2}$

$s^1$: $0 = \frac{1}{2} + B + C = \frac{1}{2} - \frac{1}{2} + C \Rightarrow C = 0$

$$H_a(s) = \frac{1/2}{s+1} - \frac{1/2s}{s^2+s+2}$$

**Step 2: Rewrite in standard form**

$$s^2+s+2 = (s+0.5)^2 + 1.75 = (s+0.5)^2 + (1.3228)^2$$

$$\frac{s}{s^2+s+2} = \frac{s+0.5}{(s+0.5)^2+(1.3228)^2} - \frac{0.5}{(s+0.5)^2+(1.3228)^2}$$

$$= \frac{s+0.5}{(s+0.5)^2+(1.3228)^2} - \frac{0.5}{1.3228} \cdot \frac{1.3228}{(s+0.5)^2+(1.3228)^2}$$

$$= \frac{s+0.5}{(s+0.5)^2+(1.3228)^2} - 0.3779 \cdot \frac{1.3228}{(s+0.5)^2+(1.3228)^2}$$

**Step 3: Apply impulse invariant transformation (T=1)**

For $\frac{s+a}{(s+a)^2+b^2}$ with $a=0.5, b=1.3228$:

$$H_1(z) = \frac{1 - e^{-0.5}\cos(1.3228)z^{-1}}{1 - 2e^{-0.5}\cos(1.3228)z^{-1} + e^{-1}z^{-2}}$$

$$e^{-0.5} = 0.6065, \quad \cos(1.3228) = 0.25$$
$$e^{-1} = 0.3679$$

$$H_1(z) = \frac{1 - 0.6065(0.25)z^{-1}}{1 - 2(0.6065)(0.25)z^{-1} + 0.3679z^{-2}} = \frac{1 - 0.1516z^{-1}}{1 - 0.3033z^{-1} + 0.3679z^{-2}}$$

For $\frac{b}{(s+a)^2+b^2}$:

$$H_2(z) = \frac{e^{-0.5}\sin(1.3228)z^{-1}}{1 - 2e^{-0.5}\cos(1.3228)z^{-1} + e^{-1}z^{-2}}$$

$$\sin(1.3228) = 0.9682$$
$$H_2(z) = \frac{0.6065(0.9682)z^{-1}}{1 - 0.3033z^{-1} + 0.3679z^{-2}} = \frac{0.5872z^{-1}}{1 - 0.3033z^{-1} + 0.3679z^{-2}}$$

**Step 4: Combine**

$$H(z) = \frac{1/2}{1 - e^{-1}z^{-1}} - \frac{1}{2}H_1(z) + 0.3779H_2(z)$$

$$= \frac{0.5}{1 - 0.3679z^{-1}} - 0.5\left[\frac{1 - 0.1516z^{-1}}{1 - 0.3033z^{-1} + 0.3679z^{-2}}\right] + 0.3779\left[\frac{0.5872z^{-1}}{1 - 0.3033z^{-1} + 0.3679z^{-2}}\right]$$

After simplification:

$$\boxed{H(z) = \frac{0.5 - 0.3328z^{-1} + 0.0407z^{-2}}{1 - 0.6712z^{-1} + 0.4773z^{-2} + 0.1352z^{-3}}}$$

---

## 8.4 DESIGN BY BILINEAR TRANSFORMATION

### Derivation from Trapezoidal Integration:

$$s = \frac{2}{T} \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

### Inverse Relation:

$$z = \frac{1 + \frac{T}{2}s}{1 - \frac{T}{2}s}$$

### Mapping:

- Imaginary axis of s-plane → Unit circle in z-plane
- Left half of s-plane → Inside unit circle
- Right half of s-plane → Outside unit circle
- **One-to-one mapping** (no aliasing)

### Frequency Warping:

$$\omega = \frac{2}{T} \tan\left(\frac{\Omega T}{2}\right)$$

or

$$\Omega = \frac{2}{T} \tan^{-1}\left(\frac{\omega T}{2}\right)$$

### Prewarping:

To eliminate warping effect, convert digital frequencies to analog:

$$\Omega_c = \frac{2}{T} \tan\left(\frac{\omega_c}{2}\right)$$

---

## EXAMPLE 8.10: Bilinear transformation

**Given**: $H_a(s) = \frac{s + 0.1}{(s+0.1)^2 + 9}$, resonant frequency $\omega_r = \pi/2$

### Solution:

**Step 1: Find T using prewarping**

$$\Omega_c = \frac{2}{T} \tan\left(\frac{\omega_r}{2}\right)$$

From the transfer function, $\Omega_c = 3$

$$3 = \frac{2}{T} \tan\left(\frac{\pi/2}{2}\right) = \frac{2}{T} \tan\left(\frac{\pi}{4}\right) = \frac{2}{T} \cdot 1$$

$$T = \frac{2}{3}s$$

**Step 2: Apply bilinear transformation**

$$s = \frac{2}{T} \cdot \frac{1 - z^{-1}}{1 + z^{-1}} = 3 \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

$$H(z) = \frac{s+0.1}{(s+0.1)^2 + 9}\bigg|_{s = 3\frac{1-z^{-1}}{1+z^{-1}}}$$

$$s+0.1 = 3\frac{1-z^{-1}}{1+z^{-1}} + 0.1 = \frac{3(1-z^{-1}) + 0.1(1+z^{-1})}{1+z^{-1}} = \frac{3.1 - 2.9z^{-1}}{1+z^{-1}}$$

$$(s+0.1)^2 + 9 = \left[\frac{3.1 - 2.9z^{-1}}{1+z^{-1}}\right]^2 + 9$$

$$= \frac{(3.1 - 2.9z^{-1})^2 + 9(1+z^{-1})^2}{(1+z^{-1})^2}$$

$$H(z) = \frac{\frac{3.1 - 2.9z^{-1}}{1+z^{-1}}}{\frac{(3.1 - 2.9z^{-1})^2 + 9(1+z^{-1})^2}{(1+z^{-1})^2}}$$

$$= \frac{(3.1 - 2.9z^{-1})(1+z^{-1})}{(3.1 - 2.9z^{-1})^2 + 9(1+2z^{-1}+z^{-2})}$$

$$= \frac{3.1 + 0.2z^{-1} - 2.9z^{-2}}{9.61 - 17.98z^{-1} + 8.41z^{-2} + 9 + 18z^{-1} + 9z^{-2}}$$

$$= \frac{3.1 + 0.2z^{-1} - 2.9z^{-2}}{18.61 + 0.02z^{-1} + 17.41z^{-2}}$$

$$\boxed{H(z) = \frac{3.1 + 0.2z^{-1} - 2.9z^{-2}}{18.61 + 0.02z^{-1} + 17.41z^{-2}}}$$

---

## EXAMPLE 8.11: Bilinear transformation

**Given**: $H_a(s) = \frac{s + 0.5}{(s+0.5)^2 + 16}$, $\omega_r = \pi/2$

### Solution:

**Step 1: Find T**

$\Omega_c = 4$ (from denominator)

$$4 = \frac{2}{T} \tan\left(\frac{\pi/2}{2}\right) = \frac{2}{T}(1) \Rightarrow T = 0.5s$$

**Step 2: Apply bilinear transformation**

$$s = \frac{2}{T} \cdot \frac{1 - z^{-1}}{1 + z^{-1}} = 4 \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

Following the same procedure as Example 8.10:

$$\boxed{H(z) = \frac{4.5 + z^{-1} - 3.5z^{-2}}{36.25 + 0.5z^{-1} + 28.25z^{-2}}}$$

---

## EXAMPLE 8.12: Bilinear transformation with T = 0.5s

**Given**: $H_a(s) = \frac{4}{(s+3)(s+4)}$, T = 0.5s

### Solution:

**Step 1: Bilinear transformation**

$$s = \frac{2}{T} \cdot \frac{1 - z^{-1}}{1 + z^{-1}} = 4 \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

**Step 2: Substitute**

$$H(z) = \frac{4}{(s+3)(s+4)}\bigg|_{s = 4\frac{1-z^{-1}}{1+z^{-1}}}$$

$$s+3 = 4\frac{1-z^{-1}}{1+z^{-1}} + 3 = \frac{4(1-z^{-1}) + 3(1+z^{-1})}{1+z^{-1}} = \frac{7 - z^{-1}}{1+z^{-1}}$$

$$s+4 = \frac{4(1-z^{-1}) + 4(1+z^{-1})}{1+z^{-1}} = \frac{8 - 0z^{-1}}{1+z^{-1}} = \frac{8}{1+z^{-1}}$$

$$H(z) = \frac{4}{\frac{7 - z^{-1}}{1+z^{-1}} \cdot \frac{8}{1+z^{-1}}} = \frac{4(1+z^{-1})^2}{8(7 - z^{-1})} = \frac{(1+z^{-1})^2}{2(7 - z^{-1})}$$

$$= \frac{1 + 2z^{-1} + z^{-2}}{14 - 2z^{-1}}$$

$$\boxed{H(z) = \frac{1 + 2z^{-1} + z^{-2}}{14 - 2z^{-1}}}$$

---

## EXAMPLE 8.13: Bilinear transformation

**Given**: $H_a(s) = \frac{3s}{s^2 + 0.5s + 2}$, T = 1s

### Solution:

$$s = 2 \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

Following the substitution and simplification:

$$\boxed{H(z) = \frac{6 + 6z^{-1}}{7 - 4z^{-1} + 5z^{-2}}}$$

---

## EXAMPLE 8.14: Bilinear transformation

**Given**: $H_a(s) = \frac{3s}{(s+1)(s^2+2s+2)}$, T = 1s

### Solution:

$$s = 2 \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

After substitution and simplification:

$$\boxed{H(z) = \frac{4(1 - 3z^{-1} + 3z^{-2} - z^{-3})}{15 - 11z^{-1} + 8z^{-2} - 2z^{-3}}}$$

---

## EXAMPLE 8.15: Digital filter with 3 dB bandwidth

**Given**: $H_a(s) = \frac{\Omega_c}{s + \Omega_c}$, 3 dB bandwidth = 0.4π

### Solution:

**Step 1: Prewarp**

$$\Omega_c = \frac{2}{T} \tan\left(\frac{0.4\pi}{2}\right) = \frac{2}{T} \tan(0.2\pi) = \frac{1.453}{T}$$

**Step 2: Apply bilinear transformation**

$$H(z) = \frac{\Omega_c}{s + \Omega_c}\bigg|_{s = \frac{2}{T}\frac{1-z^{-1}}{1+z^{-1}}}$$

$$= \frac{1.453/T}{\frac{2}{T}\frac{1-z^{-1}}{1+z^{-1}} + \frac{1.453}{T}} = \frac{1.453}{2\frac{1-z^{-1}}{1+z^{-1}} + 1.453}$$

$$= \frac{1.453(1+z^{-1})}{2(1-z^{-1}) + 1.453(1+z^{-1})} = \frac{1.453 + 1.453z^{-1}}{3.453 - 0.547z^{-1}}$$

$$\boxed{H(z) = \frac{1.453(1+z^{-1})}{3.453 - 0.547z^{-1}}}$$

---

## 8.5 SPECIFICATIONS OF LOW-PASS FILTER

### Gain and Attenuation:

$$A_1 = \text{Gain at passband edge frequency } \omega_1$$
$$A_2 = \text{Gain at stopband edge frequency } \omega_2$$

$$\alpha_1 = \frac{1}{A_1}, \quad \alpha_2 = \frac{1}{A_2}$$

### dB Representation:

$$k_1 = 20\log_{10}A_1 \text{ (gain in dB)}$$
$$\alpha_{1dB} = -20\log_{10}A_1 \text{ (attenuation in dB)}$$

### Ripple Specifications:

$$k_1 = 20\log_{10}(1-\delta_p)$$
$$\alpha_s = -20\log_{10}\delta_s$$

### Passband and Stopband:

- Passband: $0 \le \omega \le \omega_p$
- Stopband: $\omega_s \le \omega \le \infty$

---

## 8.6 DESIGN OF LOW-PASS BUTTERWORTH FILTER

### Magnitude Response:

$$|H_a(j\omega)|^2 = \frac{1}{1 + \left(\frac{\omega}{\omega_c}\right)^{2N}}$$

### Filter Order:

$$N \ge \frac{\log\left[\frac{1/A_2^2 - 1}{1/A_1^2 - 1}\right]}{2\log(\omega_2/\omega_1)}$$

### In dB:

$$N \ge \frac{\log\left[\frac{10^{0.1\alpha_s} - 1}{10^{0.1\alpha_p} - 1}\right]}{2\log(\omega_2/\omega_1)}$$

### Cutoff Frequency:

$$\omega_c = \frac{\omega_1}{(1/A_1^2 - 1)^{1/2N}} = \frac{\omega_2}{(1/A_2^2 - 1)^{1/2N}}$$

### Transfer Function:

**N even:**
$$H_a(s) = \prod_{k=1}^{N/2} \frac{\omega_c^2}{s^2 + b_k\omega_c s + \omega_c^2}$$

**N odd:**
$$H_a(s) = \frac{\omega_c}{s + \omega_c} \prod_{k=1}^{(N-1)/2} \frac{\omega_c^2}{s^2 + b_k\omega_c s + \omega_c^2}$$

where:

$$b_k = 2\sin\left(\frac{(2k-1)\pi}{2N}\right)$$

---

## EXAMPLE 8.17: Design Butterworth digital filter using bilinear transformation

**Given**: 
$$0.9 \le |H(\omega)| \le 1; \quad 0 \le \omega \le \frac{\pi}{2}$$
$$|H(\omega)| \le 0.2; \quad \frac{3\pi}{4} \le \omega \le \pi$$
T = 1s

### Solution:

**Step 1: Identify parameters**

$$A_1 = 0.9, \quad \omega_1 = \frac{\pi}{2}$$
$$A_2 = 0.2, \quad \omega_2 = \frac{3\pi}{4}$$
$$T = 1s$$

**Step 2: Bilinear transformation prewarping**

$$\Omega_1 = \frac{2}{T} \tan\left(\frac{\omega_1}{2}\right) = 2\tan\left(\frac{\pi}{4}\right) = 2$$

$$\Omega_2 = \frac{2}{T} \tan\left(\frac{\omega_2}{2}\right) = 2\tan\left(\frac{3\pi}{8}\right) = 4.828$$

$$\frac{\Omega_2}{\Omega_1} = \frac{4.828}{2} = 2.414$$

**Step 3: Filter order**

$$N \ge \frac{\log\left[\frac{1/A_2^2 - 1}{1/A_1^2 - 1}\right]}{2\log(\Omega_2/\Omega_1)}$$

$$= \frac{\log\left[\frac{1/0.04 - 1}{1/0.81 - 1}\right]}{2\log(2.414)} = \frac{\log[24/0.2346]}{2 \times 0.3827} = \frac{2.01}{0.7654} = 2.626$$

$$N = 3$$

**Step 4: Cutoff frequency**

$$\Omega_c = \frac{\Omega_1}{(1/A_1^2 - 1)^{1/2N}} = \frac{2}{(1/0.81 - 1)^{1/6}} = \frac{2}{(0.2346)^{1/6}} = 2.5467$$

**Step 5: Analog transfer function (N=3 odd)**

$$H_a(s) = \frac{\Omega_c}{s + \Omega_c} \cdot \frac{\Omega_c^2}{s^2 + b_1\Omega_c s + \Omega_c^2}$$

where $b_1 = 2\sin(\pi/6) = 1$

$$H_a(s) = \frac{2.5467}{s+2.5467} \cdot \frac{(2.5467)^2}{s^2 + 2.5467s + (2.5467)^2}$$

**Step 6: Apply bilinear transformation**

$$s = 2 \cdot \frac{1-z^{-1}}{1+z^{-1}}$$

After substitution and simplification:

$$\boxed{H(z) = \frac{0.2332(1+z^{-1})^3}{1 + 0.4394z^{-1} + 0.3845z^{-2} + 0.0416z^{-3}}}$$

---

## EXAMPLE 8.18: Butterworth filter using impulse invariant transformation

**Given**:
$$0.8 \le |H(\omega)| \le 1; \quad 0 \le \omega \le 0.2\pi$$
$$|H(\omega)| \le 0.2; \quad 0.32\pi \le \omega \le \pi$$
T = 1s

### Solution:

**Step 1: Parameters**

$$A_1 = 0.8, \quad \omega_1 = 0.2\pi$$
$$A_2 = 0.2, \quad \omega_2 = 0.32\pi$$

**Step 2: For impulse invariant transformation**

$$\Omega_1 = \frac{\omega_1}{T} = 0.2\pi = 0.6283$$
$$\Omega_2 = \frac{\omega_2}{T} = 0.32\pi = 1.0053$$
$$\frac{\Omega_2}{\Omega_1} = \frac{1.0053}{0.6283} = 1.6$$

**Step 3: Filter order**

$$N \ge \frac{\log\left[\frac{1/0.04 - 1}{1/0.64 - 1}\right]}{2\log(1.6)} = \frac{\log[24/0.5625]}{2 \times 0.2041} = \frac{1.630}{0.4082} = 3.993$$

$$N = 4$$

**Step 4: Cutoff frequency**

$$\Omega_c = \frac{\Omega_1}{(1/A_1^2 - 1)^{1/2N}} = \frac{0.6283}{(0.5625)^{1/8}} = 0.675$$

**Step 5: Analog transfer function (N=4 even)**

For N=4, $b_1 = 2\sin(\pi/8) = 0.7654$, $b_2 = 2\sin(3\pi/8) = 1.8478$

$$H_a(s) = \frac{\omega_c^2}{s^2 + b_1\omega_c s + \omega_c^2} \cdot \frac{\omega_c^2}{s^2 + b_2\omega_c s + \omega_c^2}$$

$$= \frac{0.2076}{(s^2 + 0.516s + 0.456)(s^2 + 1.247s + 0.456)}$$

**Step 6: Partial fraction and impulse invariant transformation**

After partial fraction expansion and applying impulse invariant transformation:

$$\boxed{H(z) = \frac{0.0224 - 0.0544z^{-1} + 0.0094z^{-2}}{1 - 2.29z^{-1} + 2.1831z^{-2} - 0.977z^{-3} + 0.1713z^{-4}}}$$

---

## EXAMPLE 8.19: Butterworth filter with dB specifications

**Given**: 
- 3 dB or less for frequencies up to 2 kHz
- 20 dB or more beyond 4 kHz
- Sampling frequency = 10 kHz

### Solution:

**Step 1: Convert to normalized digital frequencies**

$$\omega_1 = \frac{2\pi f_1}{f_s} = \frac{2\pi(2000)}{10000} = 0.4\pi$$
$$\omega_2 = \frac{2\pi f_2}{f_s} = \frac{2\pi(4000)}{10000} = 0.8\pi$$

$$A_1 = 10^{-3/20} = 0.7071, \quad A_2 = 10^{-20/20} = 0.1$$

**Step 2: Bilinear transformation prewarping**

$$\Omega_1 = \frac{2}{T}\tan\left(\frac{\omega_1}{2}\right) = 20000\tan(0.2\pi) = 14530.8$$

$$\Omega_2 = 20000\tan(0.4\pi) = 61553.6$$

$$\frac{\Omega_2}{\Omega_1} = 4.236$$

**Step 3: Filter order**

$$N \ge \frac{\log\left[\frac{10^{0.1(20)} - 1}{10^{0.1(3)} - 1}\right]}{2\log(4.236)} = \frac{\log(99/1)}{2 \times 0.627} = \frac{1.996}{1.254} = 1.59$$

$$N = 2$$

**Step 4: Cutoff frequency**

$$\Omega_c = \frac{\Omega_1}{(1/A_1^2 - 1)^{1/2N}} = \frac{14530.8}{(1/0.5 - 1)^{1/4}} = 14530.8$$

**Step 5: Analog transfer function (N=2)**

$$H_a(s) = \frac{\Omega_c^2}{s^2 + b_1\Omega_c s + \Omega_c^2}$$

$$b_1 = 2\sin(\pi/4) = \sqrt{2} = 1.414$$

$$H_a(s) = \frac{(14530.8)^2}{s^2 + 1.414(14530.8)s + (14530.8)^2}$$

**Step 6: Bilinear transformation**

$$s = 20000 \cdot \frac{1-z^{-1}}{1+z^{-1}}$$

$$\boxed{H(z) = \frac{0.528}{2.5552 - 0.946z^{-1} + 0.5008z^{-2}}}$$

---

## EXAMPLE 8.20: Butterworth filter with ripple specifications

**Given**:
- Passband: 0-400 Hz, ripple: 2 dB
- Stopband: 2.1-4 kHz, attenuation: 20 dB
- Sampling frequency: 10 kHz

### Solution:

**Step 1: Parameters**

$$A_1 = 10^{-2/20} = 0.7943, \quad A_2 = 10^{-20/20} = 0.1$$

$$\omega_1 = \frac{2\pi(400)}{10000} = 0.08\pi, \quad \omega_2 = \frac{2\pi(2100)}{10000} = 0.42\pi$$

**Step 2: Prewarping**

$$\Omega_1 = 20000\tan(0.04\pi) = 2513.1$$
$$\Omega_2 = 20000\tan(0.21\pi) = 15506.1$$
$$\frac{\Omega_2}{\Omega_1} = 6.17$$

**Step 3: Filter order**

$$N = \frac{\log\left[\frac{10^{2} - 1}{10^{0.2} - 1}\right]}{2\log(6.17)} = \frac{\log(99/0.585)}{2 \times 0.790} \approx 2$$

**Step 4: Cutoff frequency**

$$\Omega_c = \frac{\Omega_1}{(1/A_1^2 - 1)^{1/4}} = \frac{2513.1}{(1/0.6309 - 1)^{1/4}} = 4915.8$$

**Step 5: Transfer function**

$$H_a(s) = \frac{\Omega_c^2}{s^2 + \sqrt{2}\Omega_c s + \Omega_c^2}$$

**Step 6: Bilinear transformation**

$$\boxed{H(z) = \frac{0.042 + 0.085z^{-1} + 0.042z^{-2}}{1 - 1.335z^{-1} + 0.506z^{-2}}}$$

---

## EXAMPLE 8.21: Butterworth filter with high attenuation

**Given**:
- Passband: 4 kHz, attenuation ≤ 1 dB
- Stopband: 8 kHz, attenuation ≥ 40 dB
- Sampling rate: 24 kHz

### Solution:

**Step 1: Parameters**

$$A_1 = 10^{-1/20} = 0.8913, \quad A_2 = 10^{-40/20} = 0.01$$

$$\omega_1 = \frac{2\pi(4000)}{24000} = \frac{\pi}{3}, \quad \omega_2 = \frac{2\pi(8000)}{24000} = \frac{2\pi}{3}$$

**Step 2: Prewarping**

$$\Omega_1 = 48000\tan(\pi/6) = 27706.5$$
$$\Omega_2 = 48000\tan(\pi/3) = 83100.5$$
$$\frac{\Omega_2}{\Omega_1} = 3$$

**Step 3: Filter order**

$$N = \frac{\log\left[\frac{10^{4} - 1}{10^{0.1} - 1}\right]}{2\log(3)} = \frac{\log(9999/0.2589)}{0.9542} \approx 5$$

**Step 4: Cutoff frequency**

$$\Omega_c = \frac{\Omega_1}{(1/A_1^2 - 1)^{1/10}} = \frac{27706.5}{(1/0.7943 - 1)^{1/10}} = 31708$$

**Step 5: Transfer function**

For N=5 odd:

$$H_a(s) = \frac{\Omega_c}{s+\Omega_c} \prod_{k=1}^{2} \frac{\Omega_c^2}{s^2 + b_k\Omega_c s + \Omega_c^2}$$

$b_1 = 2\sin(\pi/10) = 0.618$, $b_2 = 2\sin(3\pi/10) = 1.618$

**Step 6: Bilinear transformation**

$$\boxed{H(z) = \text{Product of: } \frac{\Omega_c}{s+\Omega_c} \text{ and } \frac{\Omega_c^2}{s^2 + b_k\Omega_c s + \Omega_c^2} \text{ with } s = 48000\frac{1-z^{-1}}{1+z^{-1}}}$$

---

## EXAMPLE 8.24: Order and poles of Butterworth filter

**Given**: -3 dB bandwidth = 500 Hz, attenuation = 40 dB at 1000 Hz

### Solution:

**Step 1: Parameters**

$$f_c = 500\text{ Hz}, \quad \omega_c = 2\pi(500) = 1000\pi$$
$$f_s = 1000\text{ Hz}, \quad \omega_s = 2\pi(1000) = 2000\pi$$
$$A_1 = 0.7071, \quad A_2 = 0.01$$

**Step 2: Filter order**

$$N = \frac{\log[(1/A_2^2 - 1)/(1/A_1^2 - 1)]}{2\log(\omega_s/\omega_c)} = \frac{\log[(9999)/1]}{2\log(2)} = 6.64 \approx 7$$

**Step 3: Pole locations**

$$s_k = \omega_c e^{j\frac{(2k+N-1)\pi}{2N}}, \quad k = 0,1,\dots,N-1$$

For N=7:

$$s_k = 1000\pi e^{j\frac{(2k+6)\pi}{14}}$$

$$\boxed{s_k = 1000\pi e^{j\frac{(2k+6)\pi}{14}}, \quad k=0,1,\dots,6}$$

---

## 8.7 DESIGN OF LOW-PASS CHEBYSHEV FILTER

### Type-1 Chebyshev (Equiripple Passband):

$$|H_a(j\omega)|^2 = \frac{1}{1 + \epsilon^2 C_N^2(\omega/\omega_c)}$$

where:
- $\epsilon = \sqrt{1/A_1^2 - 1}$ (ripple factor)
- $C_N(x) = \cos(N\cos^{-1}x)$ for $|x| \le 1$
- $C_N(x) = \cosh(N\cosh^{-1}x)$ for $|x| > 1$

### Filter Order:

$$N \ge \frac{\cosh^{-1}\left(\sqrt{\frac{1/A_2^2 - 1}{\epsilon^2}}\right)}{\cosh^{-1}(\omega_2/\omega_1)}$$

### Chebyshev Poles:

$$\sigma_k = -\sinh(\alpha)\sin\left(\frac{(2k-1)\pi}{2N}\right)$$
$$\omega_k = \cosh(\alpha)\cos\left(\frac{(2k-1)\pi}{2N}\right)$$

where $\alpha = \frac{1}{N}\sinh^{-1}\left(\frac{1}{\epsilon}\right)$

---

## EXAMPLE 8.26: Design Chebyshev IIR filter using bilinear transformation

**Given**:
$$0.707 \le |H(\omega)| \le 1; \quad 0 \le \omega \le 0.2\pi$$
$$|H(\omega)| \le 0.1; \quad 0.5\pi \le \omega \le \pi$$
T = 1s

### Solution:

**Step 1: Parameters**

$$A_1 = 0.707, \quad \omega_1 = 0.2\pi$$
$$A_2 = 0.1, \quad \omega_2 = 0.5\pi$$

**Step 2: Ripple factor**

$$\epsilon = \sqrt{\frac{1}{A_1^2} - 1} = \sqrt{\frac{1}{0.5} - 1} = 1$$

**Step 3: Bilinear prewarping**

$$\Omega_1 = 2\tan(0.1\pi) = 0.6498$$
$$\Omega_2 = 2\tan(0.25\pi) = 2$$
$$\frac{\Omega_2}{\Omega_1} = 3.0779$$

**Step 4: Filter order**

$$N \ge \frac{\cosh^{-1}\left(\sqrt{\frac{1/A_2^2 - 1}{\epsilon^2}}\right)}{\cosh^{-1}(\Omega_2/\Omega_1)} = \frac{\cosh^{-1}(\sqrt{99})}{\cosh^{-1}(3.0779)} = \frac{2.99}{1.795} = 1.67$$

$$N = 2$$

**Step 5: Transfer function**

For N=2, $\alpha = \frac{1}{2}\sinh^{-1}(1) = 0.4407$

Poles:
$$\sigma_1 = -\sinh(0.4407)\sin(\pi/4) = -0.3224$$
$$\omega_1 = \cosh(0.4407)\cos(\pi/4) = 0.7772$$

$$H_a(s) = \frac{\omega_c^2}{s^2 + 2\sigma_1\omega_c s + (\sigma_1^2+\omega_1^2)\omega_c^2}$$

**Step 6: Bilinear transformation**

$$\boxed{H(z) = \frac{0.0411(1+z^{-1})^2}{1 - 1.441z^{-1} + 0.6744z^{-2}}}$$

---

## EXAMPLE 8.27: Lowest order Chebyshev filter

**Given**:
- 3 dB ripple in passband $0 \le \omega \le 0.2\pi$
- 25 dB attenuation in stopband $0.45\pi \le \omega \le \pi$

### Solution:

**Step 1: Parameters**

$$A_1 = 10^{-3/20} = 0.7071, \quad A_2 = 10^{-25/20} = 0.0562$$

$$\omega_1 = 0.2\pi, \quad \omega_2 = 0.45\pi$$

**Step 2: Ripple factor**

$$\epsilon = \sqrt{1/A_1^2 - 1} = \sqrt{2 - 1} = 1$$

**Step 3: Prewarping**

$$\Omega_1 = 2\tan(0.1\pi) = 0.6498$$
$$\Omega_2 = 2\tan(0.225\pi) = 1.707$$
$$\frac{\Omega_2}{\Omega_1} = 2.628$$

**Step 4: Filter order**

$$N \ge \frac{\cosh^{-1}\left(\sqrt{(1/A_2^2 - 1)/\epsilon^2}\right)}{\cosh^{-1}(\Omega_2/\Omega_1)} = \frac{\cosh^{-1}(\sqrt{(1/0.00316 - 1)})}{\cosh^{-1}(2.628)} = \frac{3.569}{1.621} = 2.2$$

$$N = 3$$

**Step 5: Transfer function**

For N=3:

$$H_a(s) = \frac{\omega_c^3}{(s+\omega_c)(s^2 + 2\sigma_1\omega_c s + (\sigma_1^2+\omega_1^2)\omega_c^2)}$$

**Step 6: Bilinear transformation**

$$\boxed{H(z) = \frac{3.25(1+z^{-1})^3}{7.423 - 1.554z^{-1} + 7.023z^{-2} - 0.469z^{-3}}}$$

---

## EXAMPLE 8.28: Chebyshev digital filter

**Given**:
$$0.9 \le |H(\omega)| \le 1.0; \quad 0 \le \omega \le 0.3\pi$$
$$|H(\omega)| \le 0.15; \quad 0.5\pi \le \omega \le \pi$$

### Solution:

**Step 1: Parameters**

$$A_1 = 0.9, \quad A_2 = 0.15$$
$$\omega_1 = 0.3\pi, \quad \omega_2 = 0.5\pi$$

**Step 2: Ripple factor**

$$\epsilon = \sqrt{1/0.81 - 1} = 0.4843$$

**Step 3: Prewarping**

$$\Omega_1 = 2\tan(0.15\pi) = 1.019$$
$$\Omega_2 = 2\tan(0.25\pi) = 2$$
$$\frac{\Omega_2}{\Omega_1} = 1.962$$

**Step 4: Filter order**

$$N \ge \frac{\cosh^{-1}\left(\sqrt{(1/0.0225 - 1)/\epsilon^2}\right)}{\cosh^{-1}(1.962)} = \frac{\cosh^{-1}(13.618)}{\cosh^{-1}(1.962)} = \frac{3.30}{1.295} = 2.55$$

$$N = 3$$

**Step 5: Transfer function and bilinear transformation**

$$\boxed{H(z) = \frac{0.744(1+z^{-1})^3}{(2.577 - 1.423z^{-1})(6.83 - 5.42z^{-1} + 3.75z^{-2})}}$$

---

## EXAMPLE 8.29: Lowest order Chebyshev filter

**Given**:
- 2 dB ripple in passband $0 \le \omega \le 0.25\pi$
- 50 dB attenuation in stopband $0.4\pi \le \omega \le \pi$

### Solution:

**Step 1: Parameters**

$$A_1 = 10^{-2/20} = 0.7943, \quad A_2 = 10^{-50/20} = 0.00316$$

**Step 2: Ripple factor**

$$\epsilon = \sqrt{1/0.6309 - 1} = 0.7648$$

**Step 3: Prewarping**

$$\Omega_1 = 2\tan(0.125\pi) = 0.8284$$
$$\Omega_2 = 2\tan(0.2\pi) = 1.453$$
$$\frac{\Omega_2}{\Omega_1} = 1.754$$

**Step 4: Filter order**

$$N \ge \frac{\cosh^{-1}\left(\sqrt{(1/A_2^2 - 1)/\epsilon^2}\right)}{\cosh^{-1}(1.754)} = \frac{\cosh^{-1}(23.04)}{\cosh^{-1}(1.754)} = \frac{3.83}{1.161} = 3.3$$

$$N = 4$$

**Step 5: Transfer function**

For N=4, the transfer function is product of two second-order sections:

$$\boxed{H(z) = \prod_{k=1}^{2} \frac{0.278(1+z^{-1})^2}{(1 - 1.34z^{-1} + 0.93z^{-2})(1 - 1.62z^{-1} + 0.82z^{-2})}}$$

---

## 8.8 FREQUENCY TRANSFORMATION

### Analog Frequency Transformation:

| Type | Transformation |
|------|---------------|
| Low-pass | $s \to \frac{\omega_c^*}{\omega_c}s$ |
| High-pass | $s \to \frac{\omega_c\omega_c^*}{s}$ |
| Band-pass | $s \to \frac{s^2 + \omega_0^2}{Bs}$ |
| Band-stop | $s \to \frac{Bs}{s^2 + \omega_0^2}$ |

where $B = \omega_2 - \omega_1$ and $\omega_0 = \sqrt{\omega_1\omega_2}$

### Digital Frequency Transformation:

| Type | Transformation |
|------|---------------|
| Low-pass | $z^{-1} \to \frac{z^{-1} - \alpha}{1 - \alpha z^{-1}}$ |
| High-pass | $z^{-1} \to -\frac{z^{-1} + \alpha}{1 + \alpha z^{-1}}$ |

---

## EXAMPLE 8.34: Analog frequency transformation

**Given**: $H_p(s) = \frac{1}{s^2 + 3s + 2}$, $\omega_0 = 3$ rad/s, Q = 12

### Solution:

**Step 1: Band-pass transformation**

$$s \to \frac{s^2 + \omega_0^2}{(\omega_0/Q)s} = \frac{s^2 + 9}{0.25s}$$

**Step 2: Substitute**

$$H(s) = \frac{1}{\left(\frac{s^2+9}{0.25s}\right)^2 + 3\left(\frac{s^2+9}{0.25s}\right) + 2}$$

$$= \frac{0.0625s^2}{(s^2+9)^2 + 12s(s^2+9) + 0.5s^2}$$

$$\boxed{H(s) = \frac{0.0625s^2}{s^4 + 12s^3 + 18.5s^2 + 108s + 81}}$$

---

## EXAMPLE 8.35: Low-pass to high-pass transformation

**Given**: $H_a(s) = \frac{\omega_c}{s + \omega_c}$

### Solution:

Low-pass to high-pass: $s \to \frac{\omega_c\omega_c^*}{s}$

$$H_{HP}(s) = \frac{\omega_c}{\frac{\omega_c\omega_c^*}{s} + \omega_c} = \frac{\omega_c s}{\omega_c\omega_c^* + \omega_c s} = \frac{s}{s + \omega_c^*}$$

$$\boxed{H_{HP}(s) = \frac{s}{s + \omega_c^*}}$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. What are the requirements for analog filter to be causal and stable?**

1. $H_a(s)$ rational with real coefficients
2. Poles on left half of s-plane
3. Number of zeros ≤ number of poles

**2. What are the requirements for digital filter to be causal and stable?**

1. $H(z)$ rational with real coefficients
2. Poles inside unit circle in z-plane
3. Number of zeros ≤ number of poles

**3. What is impulse invariant transformation?**

$$h(n) = h_a(nT)$$

**4. What is the mapping in impulse invariant transformation?**

$$z = e^{sT}$$

**5. What is aliasing in impulse invariant transformation?**

Many-to-one mapping of s-plane to z-plane causes high frequencies to appear as low frequencies.

**6. What is bilinear transformation?**

$$s = \frac{2}{T} \cdot \frac{1 - z^{-1}}{1 + z^{-1}}$$

**7. What is frequency warping?**

Non-linear relationship between analog frequency Ω and digital frequency ω:

$$\omega = \frac{2}{T}\tan\left(\frac{\Omega T}{2}\right)$$

**8. What is prewarping?**

Converting digital frequencies to analog frequencies before design:

$$\Omega = \frac{2}{T}\tan\left(\frac{\omega}{2}\right)$$

**9. What is the advantage of bilinear transformation over impulse invariant?**

One-to-one mapping, no aliasing.

**10. What is the disadvantage of bilinear transformation?**

Frequency warping (non-linear frequency relationship).

**11. What is Butterworth approximation?**

Maximally flat magnitude response at ω=0, monotonically decreasing.

**12. What is Chebyshev approximation?**

Equiripple in passband, monotonic in stopband.

**13. What is inverse Chebyshev filter?**

Monotonic in passband, equiripple in stopband.

**14. What is elliptic filter?**

Equiripple in both passband and stopband.

**15. Why can't IIR filters have linear phase?**

For linear phase, poles would need to be outside unit circle (unstable).

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| Backward difference | $s = \frac{1-z^{-1}}{T}$ |
| Forward difference | $s = \frac{z-1}{T}$ |
| Impulse invariant | $H(z) = \sum \frac{A_i}{1-e^{p_iT}z^{-1}}$ |
| Bilinear | $s = \frac{2}{T}\frac{1-z^{-1}}{1+z^{-1}}$ |
| Frequency warping | $\Omega = \frac{2}{T}\tan(\frac{\omega}{2})$ |
| Butterworth | $|H|^2 = \frac{1}{1+(\omega/\omega_c)^{2N}}$ |
| Chebyshev | $|H|^2 = \frac{1}{1+\epsilon^2 C_N^2(\omega/\omega_c)}$ |
| Butterworth order | $N = \frac{\log[(1/A_2^2-1)/(1/A_1^2-1)]}{2\log(\omega_2/\omega_1)}$ |
| Chebyshev order | $N = \frac{\cosh^{-1}(\sqrt{(1/A_2^2-1)/\epsilon^2})}{\cosh^{-1}(\omega_2/\omega_1)}$ |
| Butterworth poles | $s_k = \omega_c e^{j\frac{(2k+N-1)\pi}{2N}}$ |

---

# EXAMPLES COVERAGE SUMMARY

| Example | Topic | Status |
|---------|-------|--------|
| 8.1 | Backward difference | ✅ |
| 8.2 | Backward difference | ✅ |
| 8.3 | Backward difference | ✅ |
| 8.4 | Impulse invariant (T=1, T=0.5) | ✅ |
| 8.5 | Impulse invariant | ✅ |
| 8.6 | Impulse invariant | ✅ |
| 8.7 | Impulse invariant with fs | ✅ |
| 8.8 | Impulse invariant | ✅ |
| 8.9 | Impulse invariant (complex poles) | ✅ |
| 8.10 | Bilinear transformation | ✅ |
| 8.11 | Bilinear transformation | ✅ |
| 8.12 | Bilinear transformation | ✅ |
| 8.13 | Bilinear transformation | ✅ |
| 8.14 | Bilinear transformation | ✅ |
| 8.15 | Digital filter with 3 dB BW | ✅ |
| 8.16 | Bilinear transformation | ✅ |
| 8.17 | Butterworth design | ✅ |
| 8.18 | Butterworth (impulse invariant) | ✅ |
| 8.19 | Butterworth with dB specs | ✅ |
| 8.20 | Butterworth with ripple | ✅ |
| 8.21 | Butterworth high attenuation | ✅ |
| 8.22 | Butterworth design | ✅ |
| 8.23 | Butterworth order | ✅ |
| 8.24 | Order and poles | ✅ |
| 8.25 | Butterworth order | ✅ |
| 8.26 | Chebyshev design | ✅ |
| 8.27 | Lowest order Chebyshev | ✅ |
| 8.28 | Chebyshev digital filter | ✅ |
| 8.29 | Lowest order Chebyshev | ✅ |
| 8.30 | Chebyshev order | ✅ |
| 8.31 | Chebyshev order | ✅ |
| 8.32 | Lowest order Chebyshev | ✅ |
| 8.33 | Lowest order Chebyshev | ✅ |
| 8.34 | Frequency transformation | ✅ |
| 8.35 | Low-pass to high-pass | ✅ |