---
title: "Chapter 8 — Infinite-duration Impulse Response (IIR) Filters"
source: "Digital Signal Processing — A. Anand Kumar"
printed_pages: "548–650"
style: "Full theory + derivations + worked examples + exam preparation + MATLAB"
---

# CHAPTER 8 — INFINITE-DURATION IMPULSE RESPONSE (IIR) FILTERS

> **Source:** A. Anand Kumar, *Digital Signal Processing*, Chapter 8, printed pages **548–650**.
>
> This guide follows the chapter's organization and terminology and reformats the mathematics for readable Markdown. The chapter covers IIR/FIR fundamentals, analog-to-digital transformation, approximation of derivatives, impulse invariant transformation, bilinear transformation, low-pass filter specifications, Butterworth, Chebyshev, inverse Chebyshev, elliptic filters, analog/digital frequency transformations, textbook examples, short questions, review questions, fill-ins, objective questions, problems and MATLAB material.

---

# 1. CHAPTER ROADMAP

1. Introduction
2. Requirements for transformation
3. IIR filter design by approximation of derivatives
4. IIR filter design by impulse invariant transformation
5. IIR filter design by bilinear transformation
6. Specifications of low-pass filters
7. Low-pass digital Butterworth filter
8. Low-pass Chebyshev filter
9. Inverse Chebyshev filters
10. Elliptic filters
11. Frequency transformation
    - Analog frequency transformation
    - Digital frequency transformation
12. Short questions with answers
13. Review questions
14. Fill-in-the-blanks
15. Objective questions
16. Problems
17. MATLAB programs

---

# 2. INTRODUCTION

A filter is a **frequency-selective network**.

The book divides digital filters into two basic types:

$$
\boxed{\text{FIR — Finite-duration Impulse Response}}
$$

and

$$
\boxed{\text{IIR — Infinite-duration Impulse Response}}.
$$

## IIR filters

IIR filters use all the infinite samples of the impulse response.

They are generally of **recursive type** and employ feedback.

## FIR filters

FIR filters use only a finite number of impulse-response samples.

They are generally **non-recursive** and do not employ feedback.

The book's main IIR-design approach is:

$$
\boxed{
\text{Digital specifications}
\rightarrow
\text{analog prototype}
\rightarrow
\text{analog-to-digital transformation}
\rightarrow
H(z)
}
$$

The reason is that analog-filter design techniques are well developed.

---

# 3. IIR FILTER VS FIR FILTER

| IIR | FIR |
|---|---|
| Uses all infinite impulse-response samples | Uses finite impulse-response samples |
| Usually recursive | Usually non-recursive |
| Feedback is used | No feedback |
| Digital filter is generally obtained from an analog prototype | Can be designed directly |
| Specifications emphasize magnitude response | Magnitude and phase can both be specified |
| Physically realizable stable IIR filters cannot have linear phase | Linear phase can be achieved |

The book emphasizes that a causal and stable IIR filter cannot have linear phase.

---

# 4. ANALOG FILTER CHARACTERIZATION

An analog filter can be represented by its rational system function:

$$
\boxed{
H_a(s)
=
\frac{Y(s)}{X(s)}
=
\frac{\displaystyle\sum_{k=0}^{M}b_k s^k}
{\displaystyle\sum_{k=0}^{N}a_k s^k}.
}
$$

Its impulse response is related to the Laplace transform by

$$
\boxed{
H_a(s)=
\int_{-\infty}^{\infty}
h_a(t)e^{-st}\,dt.
}
$$

The same analog filter can also be described by a linear constant-coefficient differential equation:

$$
\boxed{
\sum_{k=0}^{N}
a_k
\frac{d^k y(t)}{dt^k}
=
\sum_{k=0}^{M}
b_k
\frac{d^k x(t)}{dt^k}.
}
$$

Therefore three equivalent descriptions are available:

1. system function $H_a(s)$;
2. impulse response $h_a(t)$;
3. differential equation.

These lead to different analog-to-digital design methods.

---

# 5. REQUIREMENTS FOR A STABLE AND CAUSAL ANALOG FILTER

The book gives these requirements:

1. $H_a(s)$ should be a rational function of $s$, with real coefficients.
2. All poles should lie in the **left half of the $s$-plane**.
3. The number of zeros should be less than or equal to the number of poles.

Thus,

$$
\boxed{
\text{stable analog filter}
\Rightarrow
\text{poles in LHP}.
}
$$

---

# 6. REQUIREMENTS FOR A STABLE AND CAUSAL DIGITAL FILTER

For the digital transfer function $H(z)$:

1. $H(z)$ should be a rational function of $z$, with real coefficients.
2. All poles should lie **inside the unit circle**.
3. The number of zeros should be less than or equal to the number of poles.

Therefore,

$$
\boxed{
\text{stable digital filter}
\Rightarrow
|p_i|<1.
}
$$

---

# 7. DESIRABLE PROPERTIES OF AN ANALOG-TO-DIGITAL TRANSFORMATION

For an analog-to-digital transformation to be useful:

### Property 1 — Frequency-axis correspondence

The imaginary axis of the $s$-plane should map into the unit circle of the $z$-plane.

### Property 2 — Stability preservation

The left half of the $s$-plane should map into the interior of the unit circle.

Therefore,

$$
\boxed{
\text{LHP}\rightarrow\text{inside unit circle}
}
$$

should hold.

---

# 8. WHY IIR FILTERS ARE DESIGNED FROM ANALOG PROTOTYPES

Analog filter-design methods are well established.

Therefore the usual procedure is:

$$
\boxed{
\text{Given digital specifications}
}
$$

$$
\downarrow
$$

$$
\boxed{
\text{Design equivalent analog filter }H_a(s)
}
$$

$$
\downarrow
$$

$$
\boxed{
H_a(s)\rightarrow H(z)
}
$$

using one of the transformation techniques.

The chapter discusses:

1. approximation of derivatives;
2. impulse invariant transformation;
3. bilinear transformation.

---

# 9. IMPORTANT FACT ABOUT IIR PHASE

A physically realizable stable IIR filter cannot have linear phase.

For linear phase, the impulse response must have a symmetry such as

$$
h(n)=h(N-1-n).
$$

The corresponding pole structure would require mirror-image poles outside the unit circle for poles inside the unit circle, causing instability.

Therefore:

$$
\boxed{
\text{causal + stable IIR}
\not\Rightarrow
\text{linear phase}.
}
$$

In IIR design, the desired **magnitude response** is specified and the phase response resulting from the design is accepted.

---

# 10. ANALOG FILTER VS DIGITAL FILTER

| Analog filter | Digital filter |
|---|---|
| Operates on analog signals | Operates on digital samples |
| Described by differential equations | Described by difference equations |
| Uses $R,L,C$ components | Uses adders, multipliers and delay elements |
| Approximation is used to satisfy desired frequency response | Coefficients are selected to satisfy desired frequency response |

---

# 11. ADVANTAGES OF DIGITAL FILTERS

The book lists important advantages:

1. Higher thermal stability because there are no resistors, capacitors and inductors whose values change with temperature.
2. Precision can be increased by increasing register bit length.
3. Filter coefficients are programmable.
4. Adaptive characteristics can be implemented.
5. One filter can process multiple signals using multiplexing.
6. Linear phase characteristics can be achieved with FIR filters.
7. Multirate processing is possible in the digital domain.
8. Digital data can be stored easily without the degradation associated with analog storage.

---

# 12. LIMITATIONS OF DIGITAL FILTERS

The chapter notes:

1. A/D and D/A converters are needed in practical systems.
2. Reconstruction filters may be required.
3. Digital techniques have frequency limitations.
4. Digital systems may require active hardware and hence consume power.
5. Performance depends on hardware precision and register length.

---

# 13. APPROXIMATION OF DERIVATIVES

The analog differential equation can be converted into an equivalent digital difference equation by approximating derivatives.

The book first uses the **backward difference**.

For

$$
y(t)
$$

at

$$
t=nT,
$$

the first derivative is approximated by

$$
\boxed{
\left.
\frac{dy(t)}{dt}
\right|_{t=nT}
\approx
\frac{y(n)-y(n-1)}{T}.
}
$$

---

# 14. FIRST-DERIVATIVE MAPPING

An analog differentiator has

$$
\boxed{
H_a(s)=s.
}
$$

The corresponding digital differentiator is

$$
\frac{y(n)-y(n-1)}{T}
$$

with transfer function

$$
\boxed{
H(z)=\frac{1-z^{-1}}{T}.
}
$$

Hence the mapping is

$$
\boxed{
s
\longrightarrow
\frac{1-z^{-1}}{T}.
}
$$

---

# 15. SECOND-DERIVATIVE APPROXIMATION

Applying the backward difference twice gives

$$
\boxed{
\frac{d^2y(t)}{dt^2}
\approx
\frac{
y(n)-2y(n-1)+y(n-2)
}{T^2}.
}
$$

Therefore,

$$
\boxed{
s^2
\longrightarrow
\left(
\frac{1-z^{-1}}{T}
\right)^2.
}
$$

---

# 16. $i$-TH DERIVATIVE

The general mapping given in the chapter is

$$
\boxed{
s^i
\longrightarrow
\left(
\frac{1-z^{-1}}{T}
\right)^i.
}
$$

Therefore the analog transfer function is transformed as

$$
\boxed{
H(z)
=
H_a(s)
\bigg|_{\displaystyle
s=(1-z^{-1})/T
}.
}
$$

---

# 17. MAPPING OF THE $s$-PLANE USING BACKWARD DIFFERENCE

From

$$
s=\frac{1-z^{-1}}{T},
$$

we get

$$
z=\frac{1}{1-sT}.
$$

For

$$
s=j\Omega,
$$

the locus in the $z$-plane is a circle with radius $1/2$ and center at

$$
\boxed{
z=\frac12.
}
$$

The chapter observes that the LHP maps into the corresponding stable region, but the frequency range is compressed.

This method is suitable mainly for low-pass and low-resonant-frequency band-pass filters.

It is not suitable for high-pass or band-reject filters.

---

# 18. FORWARD-DIFFERENCE APPROXIMATION

Instead of backward difference,

$$
\frac{dy(t)}{dt}
\approx
\frac{y(n+1)-y(n)}{T}.
$$

The corresponding transformation is

$$
\boxed{
s=\frac{z-1}{T}
}
$$

or

$$
\boxed{
z=1+sT.
}
$$

The book points out that this mapping is worse because stable analog poles do not always map into stable digital poles.

---

# 19. HIGHER-ORDER DIFFERENCE APPROXIMATION

A more general $N$-th-order difference approximation is

$$
\boxed{
\left.
\frac{dy(t)}{dt}
\right|_{t=nT}
=
\frac1T
\sum_{k=1}^{N}
a_k
\left[
y(n-k+1)-y(n-k)
\right].
}
$$

The coefficients $a_k$ are selected to optimize the approximation.

The corresponding $s$-to-$z$ mapping is expressed as a function of the coefficients.

---

# 20. EXAMPLE 8.1 — BACKWARD-DIFFERENCE TRANSFORMATION

Given

$$
\boxed{
H_a(s)=\frac{2}{s+3}
}
$$

and using backward difference,

$$
\boxed{
s=\frac{1-z^{-1}}{T}.
}
$$

Therefore,

$$
H(z)
=
\frac{2}
{\dfrac{1-z^{-1}}{T}+3}.
$$

For

$$
T=1,
$$

$$
H(z)
=
\frac{2}{1-z^{-1}+3}
$$

and hence

$$
\boxed{
H(z)=
\frac{2}{4-z^{-1}}.
}
$$

---

# 21. EXAMPLE 8.2 — SECOND-ORDER FILTER

Given

$$
\boxed{
H_a(s)=\frac{4}{s^2+9}.
}
$$

Using

$$
s=\frac{1-z^{-1}}{T},
$$

we get

$$
H(z)=
\frac{4}
{\left(\dfrac{1-z^{-1}}{T}\right)^2+9}.
$$

For

$$
T=1,
$$

$$
\boxed{
H(z)=
\frac{4}
{(1-z^{-1})^2+9}.
}
$$

Expanding,

$$
\boxed{
H(z)=
\frac{4}
{10-2z^{-1}+z^{-2}}.
}
$$

---

# 22. EXAMPLE 8.3 — SECOND-ORDER BACKWARD DIFFERENCE

Given

$$
\boxed{
H_a(s)=
\frac{3}{(s+0.5)^2+16}.
}
$$

Substitute

$$
s=\frac{1-z^{-1}}{T}.
$$

For

$$
T=1,
$$

$$
H(z)
=
\frac{3}
{(1-z^{-1}+0.5)^2+16}.
$$

Therefore,

$$
\boxed{
H(z)=
\frac{3}
{2.25-3z^{-1}+z^{-2}+16}.
}
$$

So,

$$
\boxed{
H(z)=
\frac{3}
{18.25-3z^{-1}+z^{-2}}.
}
$$

---

# 23. IMPULSE INVARIANT TRANSFORMATION

The **impulse invariant transformation** obtains the digital impulse response by uniformly sampling the impulse response of the equivalent analog filter.

The central relation is

$$
\boxed{
h(n)=h_a(nT).
}
$$

The sampling period $T$ should be sufficiently small, or the sampling frequency sufficiently high, to minimize aliasing.

---

# 24. IMPULSE INVARIANT TRANSFORMATION — BASIC DERIVATION

Suppose

$$
\boxed{
H_a(s)=
\sum_{i=1}^{N}
\frac{A_i}{s-p_i}.
}
$$

The analog impulse response is

$$
\boxed{
h_a(t)=
\sum_{i=1}^{N}
A_i e^{p_i t}u_a(t).
}
$$

Sampling at

$$
t=nT
$$

gives

$$
\boxed{
h(n)=
\sum_{i=1}^{N}
A_i e^{p_i nT}u(n).
}
$$

Taking the Z-transform:

$$
H(z)
=
\sum_{i=1}^{N}
A_i
\sum_{n=0}^{\infty}
\left(e^{p_iT}z^{-1}\right)^n.
$$

Therefore,

$$
\boxed{
H(z)
=
\sum_{i=1}^{N}
\frac{A_i}
{1-e^{p_iT}z^{-1}}.
}
$$

Thus the pole mapping is

$$
\boxed{
p_i
\longrightarrow
z_i=e^{p_iT}.
}
$$

---

# 25. POLE MAPPING IN IMPULSE INVARIANT TRANSFORMATION

The mapping is

$$
\boxed{
z=e^{sT}.
}
$$

Let

$$
s=\sigma+j\Omega.
$$

Then

$$
z=e^{(\sigma+j\Omega)T}
$$

so

$$
\boxed{
|z|=e^{\sigma T}
}
$$

and

$$
\boxed{
\angle z=\Omega T.
}
$$

Therefore:

### Left half $s$-plane

$$
\sigma<0
\Rightarrow
|z|<1.
$$

### Imaginary axis

$$
\sigma=0
\Rightarrow
|z|=1.
$$

### Right half $s$-plane

$$
\sigma>0
\Rightarrow
|z|>1.
$$

Thus stability is preserved.

---

# 26. FREQUENCY RELATION — IMPULSE INVARIANT

From

$$
z=e^{sT},
$$

on the imaginary axis:

$$
s=j\Omega
$$

gives

$$
z=e^{j\Omega T}.
$$

Therefore,

$$
\boxed{
\omega=\Omega T.
}
$$

or

$$
\boxed{
\Omega=\frac{\omega}{T}.
}
$$

The relationship is linear.

---

# 27. ALIASING IN IMPULSE INVARIANT TRANSFORMATION

The imaginary axis is mapped repeatedly because

$$
e^{j(\Omega+2\pi k/T)T}
=
e^{j\Omega T}.
$$

Therefore multiple analog frequencies map to the same digital frequency.

The mapping is

$$
\boxed{
\text{many-to-one}.
}
$$

The chapter identifies this as the source of aliasing.

Thus impulse invariant transformation is not one-to-one.

---

# 28. ALIASING

The book defines aliasing as the phenomenon in which high-frequency components acquire the identity of lower-frequency components after sampling.

In simple words:

$$
\boxed{
\text{higher frequencies impersonate lower frequencies}.
}
$$

To reduce aliasing in impulse invariant design:

$$
\boxed{
T\downarrow
\quad\Longleftrightarrow\quad
f_s\uparrow.
}
$$

The analog response should preferably be sufficiently band-limited.

---

# 29. USEFUL IMPULSE-INVARIANT POLE MAPPINGS

For a real pole with multiplicity, the mapping produces corresponding repeated digital poles.

For complex conjugate poles, the analog pair

$$
s=-a\pm jb
$$

maps to

$$
\boxed{
z=e^{-aT}e^{\pm jbT}.
}
$$

Hence

$$
\boxed{
z=e^{-aT}
\left[
\cos(bT)\pm j\sin(bT)
\right].
}
$$

This is particularly useful for second-order analog sections.

---

# 30. EXAMPLE 8.4 — IMPULSE INVARIANT TRANSFORMATION

Given

$$
\boxed{
H_a(s)=
\frac{2}{(s+1)(s+3)}
}
$$

and find $H(z)$ for:

### (a) $T=1$ s

Partial fractions:

$$
\frac{2}{(s+1)(s+3)}
=
\frac{1}{s+1}
-
\frac{1}{s+3}.
$$

Therefore

$$
H(z)=
\frac{1}{1-e^{-T}z^{-1}}
-
\frac{1}{1-e^{-3T}z^{-1}}.
$$

For $T=1$,

$$
e^{-1}=0.3678,
\qquad
e^{-3}=0.0497.
$$

Hence

$$
\boxed{
H(z)=
\frac{1}{1-0.3678z^{-1}}
-
\frac{1}{1-0.0497z^{-1}}.
}
$$

Combining,

$$
\boxed{
H(z)=
\frac{-0.3181z^{-1}}
{1-0.4175z^{-1}+0.0182z^{-2}}.
}
$$

### (b) $T=0.5$ s

$$
e^{-0.5}=0.606,
\qquad
e^{-1.5}=0.223.
$$

Thus

$$
\boxed{
H(z)=
\frac{1}{1-0.606z^{-1}}
-
\frac{1}{1-0.223z^{-1}}.
}
$$

The book obtains the corresponding combined rational form.

---

# 31. EXAMPLE 8.5 — IMPULSE INVARIANT SECOND-ORDER FILTER

Given

$$
\boxed{
H_a(s)=
\frac{s+0.1}{(s+0.1)^2+9}
}
$$

with

$$
T=1.
$$

The standard second-order impulse-invariant mapping gives

$$
\boxed{
H(z)=
\frac{
e^{-0.1T}
\left[
\cos(3T)
-
\text{appropriate phase-weighted term}
\right]
}
{
1-2e^{-0.1T}\cos(3T)z^{-1}
+e^{-0.2T}z^{-2}
}
}
$$

with the numerator determined from the corresponding cosine/sine decomposition.

The book works the numerical form for $T=1$.

---

# 32. EXAMPLE 8.6

Given

$$
\boxed{
H_a(s)=
\frac{s+0.5}{(s+0.5)^2+4}
}
$$

and

$$
T=1.
$$

Here

$$
a=0.5,
\qquad
b=2.
$$

Use the second-order impulse-invariant mapping:

$$
e^{-aT},
\qquad
\cos(bT),
\qquad
\sin(bT).
$$

The resulting $H(z)$ is the digital second-order system obtained by replacing the analog pole pair by

$$
\boxed{
z=e^{-0.5}e^{\pm j2}.
}
$$

---

# 33. EXAMPLE 8.7

Given

$$
\boxed{
H_a(s)=\frac{2}{s(s+2)}
}
$$

and sampling frequency

$$
f_s=4\text{ samples/s}.
$$

Therefore,

$$
\boxed{
T=\frac14=0.25\text{ s}.
}
$$

Partial fractions:

$$
\frac{2}{s(s+2)}
=
\frac1s-\frac1{s+2}.
$$

The analog poles are

$$
p_1=0,\qquad p_2=-2.
$$

Hence

$$
z_1=e^0=1
$$

and

$$
z_2=e^{-2(0.25)}=e^{-0.5}\approx0.606.
$$

Thus

$$
\boxed{
H(z)=
\frac1{1-z^{-1}}
-
\frac1{1-0.606z^{-1}}.
}
$$

The combined form is the book's resulting digital transfer function.

---

# 34. EXAMPLE 8.8 — STANDARD SECOND-ORDER IMPULSE INVARIANT FORM

For

$$
\boxed{
H_a(s)=
\frac{b}{(s+a)^2+b^2},
}
$$

the impulse-invariant digital denominator is

$$
\boxed{
1-2e^{-aT}\cos(bT)z^{-1}
+
e^{-2aT}z^{-2}.
}
$$

The numerator contains the corresponding sampled sine term.

This standard form is important for solving second-order examples quickly.

---

# 35. BILINEAR TRANSFORMATION

The book introduces bilinear transformation to overcome the limitations of the previous methods.

It is especially useful for high-pass and band-reject filters.

The bilinear transformation is a **one-to-one conformal mapping** from the $s$-plane to the $z$-plane.

The main relation is

$$
\boxed{
s=
\frac{2}{T}
\frac{1-z^{-1}}{1+z^{-1}}.
}
$$

Equivalently,

$$
\boxed{
z=
\frac{1+sT/2}{1-sT/2}.
}
$$

To design the digital filter:

$$
\boxed{
H(z)=
H_a(s)
\bigg|_{\displaystyle
s=\frac{2}{T}\frac{1-z^{-1}}{1+z^{-1}}
}.
}
$$

---

# 36. DERIVATION OF BILINEAR TRANSFORMATION

Consider

$$
\boxed{
H_a(s)=\frac{b}{s+a}.
}
$$

Then

$$
sY(s)+aY(s)=bX(s).
$$

Taking inverse Laplace transform:

$$
\boxed{
\frac{dy(t)}{dt}+ay(t)=bx(t).
}
$$

Integrate between

$$
(n-1)T
$$

and

$$
nT.
$$

Using the trapezoidal integration rule,

$$
\int_{(n-1)T}^{nT}f(t)\,dt
\approx
\frac{T}{2}
[f(nT)+f((n-1)T)].
$$

This gives

$$
\boxed{
y(n)-y(n-1)
+
\frac{aT}{2}[y(n)+y(n-1)]
=
\frac{bT}{2}[x(n)+x(n-1)].
}
$$

Taking the Z-transform produces

$$
\boxed{
H(z)
=
\frac{
(bT/2)(1+z^{-1})
}{
(1+aT/2)
+
(-1+aT/2)z^{-1}
}.
}
$$

Comparison with $H_a(s)$ gives

$$
\boxed{
s=
\frac{2}{T}
\frac{1-z^{-1}}{1+z^{-1}}.
}
$$

---

# 37. POLE MAPPING — BILINEAR TRANSFORMATION

For

$$
s=\sigma+j\Omega,
$$

the mapping has the important property:

$$
\boxed{
\sigma<0
\Rightarrow
|z|<1
}
$$

$$
\boxed{
\sigma=0
\Rightarrow
|z|=1
}
$$

$$
\boxed{
\sigma>0
\Rightarrow
|z|>1.
}
$$

Therefore:

$$
\boxed{
\text{LHP}\rightarrow\text{inside unit circle}
}
$$

$$
\boxed{
j\Omega\text{-axis}\rightarrow\text{unit circle}
}
$$

$$
\boxed{
\text{RHP}\rightarrow\text{outside unit circle}.
}
$$

Hence a stable analog filter becomes a stable digital filter.

---

# 38. FREQUENCY RELATION IN BILINEAR TRANSFORMATION

On the imaginary axis,

$$
s=j\Omega.
$$

On the unit circle,

$$
z=e^{j\omega}.
$$

The transformation yields

$$
\boxed{
\Omega=
\frac{2}{T}
\tan\left(\frac{\omega}{2}\right).
}
$$

Equivalently,

$$
\boxed{
\omega=
2\tan^{-1}
\left(
\frac{\Omega T}{2}
\right).
}
$$

This relationship is nonlinear.

---

# 39. FREQUENCY WARPING

Because

$$
\Omega=
\frac{2}{T}
\tan\left(\frac{\omega}{2}\right),
$$

the relationship between analog and digital frequency is nonlinear.

This distortion of the frequency axis is called

$$
\boxed{
\text{frequency warping}.
}
$$

Low frequencies are expanded relative to the high-frequency region, while high frequencies are compressed.

---

# 40. EFFECT OF WARPING

### Magnitude response

The digital filter retains the same number of passbands, but their bandwidths become disproportionate.

### Phase response

Even if the analog filter has a linear phase response, the corresponding digital response becomes nonlinear.

Thus:

$$
\boxed{
\text{bilinear transformation does not preserve linear phase}.
}
$$

---

# 41. PREWARPING

The effect of frequency warping on the amplitude response can be eliminated by **prewarping**.

Given a desired digital frequency $\omega$, first calculate the corresponding analog frequency:

$$
\boxed{
\Omega_p=
\frac{2}{T}
\tan\left(\frac{\omega}{2}\right).
}
$$

These analog frequencies are called **prewarp frequencies**.

Then:

1. design the analog filter using the prewarped frequencies;
2. apply the bilinear transformation.

---

# 42. IMPULSE INVARIANT VS BILINEAR TRANSFORMATION

| Impulse invariant | Bilinear |
|---|---|
| Many-to-one mapping | One-to-one mapping |
| $z=e^{sT}$ | $s=\frac{2}{T}\frac{1-z^{-1}}{1+z^{-1}}$ |
| Analog/digital frequency relationship is linear | Frequency relationship is nonlinear |
| Aliasing occurs | No aliasing |
| Analog filter should be band-limited | Analog filter need not be band-limited |
| Magnitude/phase can be preserved approximately with sufficiently high sampling frequency | Magnitude can be preserved by prewarping |
| Not suitable for high-pass/high-frequency band-reject design | Suitable for low-pass, high-pass, band-pass and band-stop design |

---

# 43. EXAMPLE 8.10 — BILINEAR TRANSFORMATION

Given

$$
\boxed{
H_a(s)=
\frac{s+0.1}{(s+0.1)^2+9}
}
$$

and the desired digital resonant frequency

$$
\omega_r=\frac{\pi}{2}.
$$

The analog frequency is obtained from

$$
\Omega_r
=
\frac{2}{T}
\tan\left(\frac{\omega_r}{2}\right).
$$

Since

$$
\omega_r=\frac{\pi}{2},
$$

$$
\boxed{
\Omega_r=
\frac{2}{T}\tan\left(\frac{\pi}{4}\right)
=
\frac{2}{T}.
}
$$

The book then selects $T$ so that the analog resonant frequency corresponds to the desired digital resonant frequency and substitutes

$$
s=
\frac{2}{T}
\frac{1-z^{-1}}{1+z^{-1}}
$$

into $H_a(s)$.

---

# 44. EXAMPLE 8.11 — BILINEAR SECOND-ORDER FILTER

Given

$$
\boxed{
H_a(s)=
\frac{s+0.5}{(s+0.5)^2+16}
}
$$

and

$$
\omega_r=\frac{\pi}{2},
$$

use

$$
\boxed{
\Omega_r=
\frac{2}{T}
\tan\left(\frac{\omega_r}{2}\right).
}
$$

For

$$
\omega_r=\frac{\pi}{2},
$$

$$
\Omega_r=\frac2T.
$$

The book obtains the corresponding $T$, substitutes

$$
s=
\frac{2}{T}
\frac{1-z^{-1}}{1+z^{-1}},
$$

and simplifies the resulting $H(z)$.

---

# 45. LOW-PASS FILTER SPECIFICATIONS

A low-pass filter is generally specified using:

- passband edge frequency $\Omega_1$;
- stopband edge frequency $\Omega_2$;
- passband gain $A_1$;
- stopband gain $A_2$.

The desired magnitude characteristics are of the form

$$
\boxed{
A_1\le |H(\Omega)|\le1,
\qquad
0\le\Omega\le\Omega_1
}
$$

and

$$
\boxed{
|H(\Omega)|\le A_2,
\qquad
\Omega\ge\Omega_2.
}
$$

---

# 46. GAIN AND ATTENUATION

For normalized maximum gain of 1:

$$
A_1<1,\qquad A_2<1.
$$

The corresponding attenuation values are

$$
\boxed{
\alpha_1=\frac1{A_1}
}
$$

and

$$
\boxed{
\alpha_2=\frac1{A_2}.
}
$$

When expressed in dB:

$$
\boxed{
k_1=20\log_{10}A_1
}
$$

$$
\boxed{
k_2=20\log_{10}A_2.
}
$$

Because $A_1,A_2<1$, gain in dB is negative.

Attenuation in dB is positive.

---

# 47. RIPPLE SPECIFICATIONS

If passband ripple is $p$ and stopband ripple is $s$, the book gives the approximate conversions:

$$
\boxed{
k_1=20\log_{10}(1-p)
}
$$

and

$$
\boxed{
k_2=20\log_{10}s.
}
$$

The corresponding attenuation quantities are positive.

---

# 48. DIGITAL FREQUENCY NORMALIZATION

For a digital frequency specified in Hz and sampling frequency $f_s$,

$$
\boxed{
\omega=2\pi\frac{f}{f_s}.
}
$$

The book often expresses normalized angular frequency using

$$
\boxed{
\omega=2\pi\frac{f}{f_s}.
}
$$

For bilinear transformation,

$$
\boxed{
\Omega=
\frac{2}{T}
\tan\left(\frac{\omega}{2}\right).
}
$$

For impulse invariant transformation,

$$
\boxed{
\Omega=\frac{\omega}{T}.
}
$$

---

# 49. BUTTERWORTH APPROXIMATION

The Butterworth approximation is selected so that the magnitude response is:

$$
\boxed{
\text{maximally flat at the origin}
}
$$

and

$$
\boxed{
\text{monotonically decreasing with frequency}.
}
$$

The low-pass Butterworth magnitude-squared response is

$$
\boxed{
|H_a(j\Omega)|^2
=
\frac{1}
{1+\left(\dfrac{\Omega}{\Omega_c}\right)^{2N}}.
}
$$

Here:

- $N$ = filter order;
- $\Omega_c$ = 3-dB cutoff frequency.

---

# 50. BUTTERWORTH FILTER PROPERTIES

1. It is an **all-pole** design.
2. Magnitude is maximally flat at the origin.
3. Magnitude decreases monotonically.
4. Increasing $N$ makes the response approach the ideal response.
5. At the cutoff frequency,

$$
\boxed{
|H_a(j\Omega_c)|=\frac1{\sqrt2}.
}
$$

Therefore the cutoff is the

$$
\boxed{
3\text{-dB point}.
}
$$

---

# 51. BUTTERWORTH POLES

The normalized Butterworth poles lie symmetrically on a circle in the $s$-plane.

Number of poles:

$$
\boxed{
2N.
}
$$

Angular spacing:

$$
\boxed{
\frac{360^\circ}{2N}.
}
$$

For a stable filter, only the $N$ poles in the left half-plane are selected.

For a valid pole angle $\theta$,

$$
\boxed{
p_k=
\Omega_c
\left(
\cos\theta_k
+j\sin\theta_k
\right)
}
$$

with the stable LHP poles selected.

The book also expresses the second-order factors using

$$
\boxed{
b_k=
2\sin
\left(
\frac{(2k-1)\pi}{2N}
\right).
}
$$

---

# 52. BUTTERWORTH TRANSFER FUNCTION

For even $N$,

$$
\boxed{
H_a(s)=
\frac{\Omega_c^N}
{\displaystyle
\prod_{k=1}^{N/2}
\left[
s^2+b_k\Omega_c s+\Omega_c^2
\right]
}.
}
$$

For odd $N$,

$$
\boxed{
H_a(s)=
\frac{\Omega_c^N}
{(s+\Omega_c)
\displaystyle
\prod_{k=1}^{(N-1)/2}
\left[
s^2+b_k\Omega_c s+\Omega_c^2
\right]
}.
}
$$

---

# 53. BUTTERWORTH DESIGN — GENERAL STEPS

### Step 1 — Select transformation

Choose:

$$
\boxed{\text{bilinear}}
$$

or

$$
\boxed{\text{impulse invariant}}.
$$

### Step 2 — Convert digital edge frequencies

For bilinear:

$$
\boxed{
\Omega_i=
\frac2T
\tan\left(\frac{\omega_i}{2}\right).
}
$$

For impulse invariant:

$$
\boxed{
\Omega_i=\frac{\omega_i}{T}.
}
$$

### Step 3 — Calculate filter order $N$

Use the Butterworth order relation.

### Step 4 — Calculate analog cutoff $\Omega_c$.

### Step 5 — Form $H_a(s)$.

### Step 6 — Transform $H_a(s)\rightarrow H(z)$.

### Step 7 — Realize the digital filter.

---

# 54. BUTTERWORTH ORDER EQUATION

From

$$
A_1
\le
\frac1{
\sqrt{
1+(\Omega_1/\Omega_c)^{2N}
}
}
$$

and

$$
A_2
\ge
\frac1{
\sqrt{
1+(\Omega_2/\Omega_c)^{2N}
}
},
$$

the book obtains the order condition

$$
\boxed{
N
\ge
\frac{
\log
\left[
\dfrac{1/A_2^2-1}
{1/A_1^2-1}
\right]
}
{
2\log(\Omega_2/\Omega_1)
}.
}
$$

If the result is not an integer, choose the next greater integer.

---

# 55. BUTTERWORTH CUTOFF FREQUENCY

From the passband specification,

$$
\boxed{
\Omega_c
=
\frac{\Omega_1}
{
\left[
1/A_1^2-1
\right]^{1/(2N)}
}.
}
$$

From the stopband specification,

$$
\boxed{
\Omega_c
=
\frac{\Omega_2}
{
\left[
1/A_2^2-1
\right]^{1/(2N)}
}.
}
$$

The appropriate value is selected according to the design requirement.

---

# 56. EXAMPLE 8.17 — BUTTERWORTH DIGITAL FILTER BY BILINEAR TRANSFORMATION

Specifications:

$$
\boxed{
0.9\le |H|\le1,
\qquad
0\le\omega\le2
}
$$

and

$$
\boxed{
|H|\le0.2,
\qquad
\omega\ge4
}
$$

with

$$
T=1\text{ s}.
$$

### Step 1

Bilinear transformation is specified.

### Step 2 — Analog edge frequencies

$$
\Omega_1
=
2\tan(2/2)
=
2\tan1
$$

and

$$
\Omega_2
=
2\tan(4/2)
=
2\tan2.
$$

The textbook obtains the ratio

$$
\boxed{
\frac{\Omega_2}{\Omega_1}\approx2.414.
}
$$

### Step 3 — Order

Using the Butterworth order formula, the book obtains

$$
N\ge2.626.
$$

Therefore,

$$
\boxed{
N=3.
}
$$

### Step 4 — Cutoff

The textbook obtains

$$
\boxed{
\Omega_c\approx2.5467.
}
$$

### Step 5 — Analog transfer function

For $N=3$,

$$
\boxed{
H_a(s)=
\frac{\Omega_c^3}
{(s+\Omega_c)
(s^2+b_1\Omega_c s+\Omega_c^2)}
}
$$

with

$$
b_1=1.
$$

### Step 6

Apply

$$
\boxed{
s=
\frac{2}{T}
\frac{1-z^{-1}}{1+z^{-1}}
}
$$

and simplify to obtain $H(z)$.

---

# 57. EXAMPLE 8.18 — BUTTERWORTH WITH IMPULSE INVARIANT TRANSFORMATION

Specifications:

$$
\boxed{
0.8\le |H|\le1,\qquad0\le\omega\le0.2
}
$$

$$
\boxed{
|H|\le0.2,\qquad\omega\ge0.32
}
$$

with

$$
T=1.
$$

For impulse invariant transformation,

$$
\Omega_1=0.2
$$

and

$$
\Omega_2=0.32.
$$

Thus,

$$
\boxed{
\frac{\Omega_2}{\Omega_1}=1.6.
}
$$

The Butterworth order calculation gives

$$
N\ge3.9931.
$$

Therefore,

$$
\boxed{
N=4.
}
$$

The textbook obtains

$$
\boxed{
\Omega_c\approx0.675\text{ rad/s}.
}
$$

Then the fourth-order analog Butterworth transfer function is formed and converted using impulse invariant transformation.

---

# 58. EXAMPLE 8.19 — BUTTERWORTH, 2 kHz / 4 kHz

Specifications:

- passband: $0$–$2$ kHz;
- stopband: $4$ kHz and above;
- passband attenuation: $3$ dB;
- stopband attenuation: $20$ dB;
- sampling frequency: $10$ kHz.

Convert gains:

$$
\boxed{
A_1=10^{-3/20}=0.707
}
$$

$$
\boxed{
A_2=10^{-20/20}=0.1.
}
$$

Normalize:

$$
\omega_1
=
2\pi\frac{2000}{10000}
=
0.4\pi
$$

and

$$
\omega_2
=
2\pi\frac{4000}{10000}
=
0.8\pi.
$$

Using bilinear transformation:

$$
\boxed{
\Omega_1=2f_s\tan(\omega_1/2)
}
$$

$$
\boxed{
\Omega_2=2f_s\tan(\omega_2/2).
}
$$

The book obtains

$$
\boxed{
\frac{\Omega_2}{\Omega_1}=4.236
}
$$

and

$$
\boxed{
N=2.
}
$$

The analog cutoff is approximately

$$
\boxed{
\Omega_c=14530\text{ rad/s}.
}
$$

The resulting second-order $H_a(s)$ is transformed to $H(z)$ using the bilinear transformation.

---

# 59. EXAMPLE 8.20 — BUTTERWORTH, 400 Hz / 2.1 kHz

Specifications:

- passband: $0$–400 Hz;
- stopband: $2.1$ kHz and above;
- passband ripple/attenuation: $2$ dB;
- stopband attenuation: $20$ dB;
- $f_s=10$ kHz.

Convert:

$$
\boxed{
A_1=10^{-2/20}=0.794
}
$$

$$
\boxed{
A_2=10^{-20/20}=0.1.
}
$$

Normalize the digital frequencies and prewarp them using

$$
\boxed{
\Omega=
2f_s\tan\left(\frac{\omega}{2}\right).
}
$$

The textbook obtains approximately

$$
\boxed{
\Omega_1=2513.102\text{ rad/s}
}
$$

and

$$
\boxed{
\Omega_2=15506.08\text{ rad/s}.
}
$$

Thus

$$
\boxed{
\frac{\Omega_2}{\Omega_1}\approx6.1703.
}
$$

The order calculation gives

$$
\boxed{
N=2.
}
$$

The book then forms $H_a(s)$ and converts it using the bilinear transformation.

---

# 60. EXAMPLE 8.21 — BUTTERWORTH, 4 kHz / 8 kHz

Given:

- passband edge $=4$ kHz;
- stopband edge $=8$ kHz;
- passband attenuation $=1$ dB;
- stopband attenuation $=40$ dB;
- sampling rate $=24$ kHz.

Convert gains:

$$
\boxed{
A_1=10^{-1/20}=0.8912
}
$$

$$
\boxed{
A_2=10^{-40/20}=0.01.
}
$$

Use bilinear transformation and prewarp the edge frequencies.

The textbook obtains

$$
\boxed{
\Omega_1\approx27706.49\text{ rad/s}
}
$$

and

$$
\boxed{
\Omega_2\approx83100.52\text{ rad/s}.
}
$$

Thus,

$$
\boxed{
\frac{\Omega_2}{\Omega_1}\approx2.9957.
}
$$

The order calculation gives approximately

$$
N\ge4.8.
$$

Therefore,

$$
\boxed{
N=5.
}
$$

---

# 61. EXAMPLE 8.22 — BUTTERWORTH, 1000 Hz / 1500 Hz

Given:

- $f_s=5000$ Hz;
- passband edge $=1000$ Hz;
- stopband edge $=1500$ Hz;
- passband attenuation $=0.5$ dB;
- stopband attenuation $=30$ dB.

Gain values:

$$
\boxed{
A_1=10^{-0.5/20}=0.9446
}
$$

$$
\boxed{
A_2=10^{-30/20}=0.0316.
}
$$

After bilinear prewarping, the textbook obtains approximately

$$
\boxed{
\frac{\Omega_2}{\Omega_1}\approx1.8944
}
$$

and

$$
\boxed{
N=8.
}
$$

The analog cutoff is approximately

$$
\boxed{
\Omega_c=8292\text{ rad/s}.
}
$$

The eighth-order analog Butterworth filter is then converted to digital form.

---

# 62. EXAMPLE 8.23 — FIND BUTTERWORTH ORDER

Given

$$
A_1=0.5,
\qquad
A_2=0.2,
$$

and, using impulse invariant transformation,

$$
\frac{\Omega_2}{\Omega_1}=1.5.
$$

Using the Butterworth order formula,

$$
\boxed{
N\approx3.919.
}
$$

Therefore,

$$
\boxed{
N=4.
}
$$

---

# 63. EXAMPLE 8.24 — BUTTERWORTH ORDER AND POLES

Given:

- 3-dB bandwidth $=500$ Hz;
- attenuation $=40$ dB at $1000$ Hz.

The book converts the frequencies to angular frequency and determines

$$
A_1=0.707
$$

and

$$
A_2=0.01.
$$

For impulse invariant transformation,

$$
\frac{\Omega_2}{\Omega_1}=2.
$$

The order calculation gives

$$
\boxed{
N\approx6.64
}
$$

so

$$
\boxed{
N=7.
}
$$

The poles are then obtained from the Butterworth pole-location formula.

---

# 64. EXAMPLE 8.25 — BUTTERWORTH ORDER

Given:

$$
f_p=0.10\text{ Hz},
\qquad
A_p=0.5\text{ dB}
$$

$$
f_s=0.15\text{ Hz},
\qquad
A_s=15\text{ dB}
$$

and sampling frequency

$$
f_s^{(\text{sampling})}=1\text{ Hz}.
$$

Using bilinear transformation, the textbook obtains approximately

$$
\boxed{
\frac{\Omega_2}{\Omega_1}=1.57
}
$$

and

$$
\boxed{
N=7.
}
$$

---

# 65. CHEBYSHEV APPROXIMATION

The chapter considers two types:

### Type-1 Chebyshev

$$
\boxed{
\text{equiripple passband, monotonic stopband}
}
$$

### Type-2 Chebyshev

$$
\boxed{
\text{monotonic passband, equiripple stopband}
}
$$

Type-2 is also called

$$
\boxed{
\text{inverse Chebyshev}.
}
$$

The chapter presents the design of **type-1 Chebyshev** filters in detail.

---

# 66. TYPE-1 CHEBYSHEV MAGNITUDE RESPONSE

The type-1 Chebyshev low-pass magnitude-squared response is

$$
\boxed{
|H_a(j\Omega)|^2
=
\frac1{
1+\epsilon^2
C_N^2
\left(
\frac{\Omega}{\Omega_c}
\right)
}.
}
$$

Here:

- $\epsilon$ = attenuation/ripple constant;
- $C_N(\cdot)$ = Chebyshev polynomial of the first kind;
- $N$ = filter order.

---

# 67. CHEBYSHEV POLYNOMIAL

The first-kind Chebyshev polynomial is

$$
\boxed{
C_N(x)=
\cos(N\cos^{-1}x),
\qquad |x|\le1.
}
$$

For

$$
x\ge1,
$$

$$
\boxed{
C_N(x)=
\cosh(N\cosh^{-1}x).
}
$$

---

# 68. CHEBYSHEV FILTER PROPERTIES

1. Type-1 is an all-pole design.
2. Passband has equiripple magnitude.
3. Stopband is monotonic.
4. Increasing $N$ makes the response approach the ideal response.
5. Poles lie symmetrically on an ellipse in the $s$-plane.
6. The phase response is more nonlinear than the Butterworth response for the same order.

---

# 69. CHEBYSHEV RIPPLE CONSTANT

If $A_1$ is the gain at the passband edge, the chapter gives

$$
\boxed{
\epsilon=
\sqrt{
\frac1{A_1^2}-1
}.
}
$$

This parameter determines the passband ripple.

---

# 70. CHEBYSHEV ORDER

The order is obtained using

$$
\boxed{
N
\ge
\frac{
\cosh^{-1}
\left[
\sqrt{(1/A_2^2-1)/\epsilon^2}
\right]
}
{
\cosh^{-1}(\Omega_2/\Omega_1)
}.
}
$$

Choose the next higher integer.

The analog edge frequencies $\Omega_1,\Omega_2$ are first obtained using impulse invariant or bilinear transformation.

---

# 71. CHEBYSHEV TRANSFER FUNCTION

For even $N$, the transfer function is represented as a product of second-order factors:

$$
\boxed{
H_a(s)=
\frac{B_0}
{\displaystyle
\prod
\left[
s^2+b_k\Omega_c s+c_k\Omega_c^2
\right]
}.
}
$$

For odd $N$, an additional first-order term occurs.

The chapter defines the parameters using quantities involving

$$
\epsilon,
\qquad
\theta_k,
\qquad
\sinh(\cdot),
\qquad
\cosh(\cdot).
$$

The normalized poles lie on an ellipse.

---

# 72. CHEBYSHEV POLES

The normalized poles are expressed as

$$
\boxed{
s_n=
-\sinh(y)\sin(x_n)
+
j\cosh(y)\cos(x_n)
}
$$

with

$$
\boxed{
x_n=
\frac{(2n-1)\pi}{2N}.
}
$$

The parameter $y$ is determined from the ripple constant.

Only the $N$ poles in the left half-plane are selected for the stable analog filter.

For even $N$, poles occur in complex-conjugate pairs.

For odd $N$, one pole is real and the remaining poles occur in conjugate pairs.

---

# 73. EXAMPLE 8.27 — LOWEST-ORDER CHEBYSHEV IIR FILTER

Specifications:

$$
\boxed{
3\text{ dB ripple in passband},
\quad
0\le\omega\le0.2
}
$$

$$
\boxed{
25\text{ dB attenuation in stopband},
\quad
\omega\ge0.45.
}
$$

Given

$$
A_1=10^{-3/20}=0.707
$$

and

$$
A_2=10^{-25/20}=0.0562.
$$

For $T=1$ and bilinear transformation,

$$
\boxed{
\epsilon\approx1.
}
$$

The analog frequency ratio is

$$
\boxed{
\frac{\Omega_2}{\Omega_1}
=
\frac{\tan(0.45/2)}
{\tan(0.2/2)}
\approx2.628.
}
$$

The order calculation gives

$$
N\ge2.20
$$

and therefore

$$
\boxed{
N=3.
}
$$

The textbook then computes the analog cutoff and the third-order Chebyshev transfer function, followed by bilinear transformation.

---

# 74. EXAMPLE 8.28 — CHEBYSHEV DIGITAL FILTER

Given

$$
\boxed{
0.9\le|H|\le1,
\qquad
0\le\omega\le0.3
}
$$

and

$$
\boxed{
|H|\le0.15,
\qquad
\omega\ge0.5.
}
$$

Thus

$$
A_1=0.9,
\qquad
A_2=0.15.
$$

### Step 1 — Bilinear transformation

### Step 2 — Attenuation constant

$$
\boxed{
\epsilon=
\sqrt{\frac1{0.9^2}-1}
\approx0.484.
}
$$

### Step 3 — Analog-frequency ratio

The textbook obtains approximately

$$
\boxed{
\frac{\Omega_2}{\Omega_1}=1.962.
}
$$

### Step 4 — Order

The textbook obtains

$$
N\ge2.55.
$$

Therefore,

$$
\boxed{
N=3.
}
$$

The remaining steps are:

1. determine analog cutoff;
2. construct third-order Chebyshev $H_a(s)$;
3. apply bilinear transformation;
4. simplify to $H(z)$.

---

# 75. BUTTERWORTH VS CHEBYSHEV TYPE-1

| Butterworth | Chebyshev Type-1 |
|---|---|
| All-pole | All-pole |
| Poles on a circle | Poles on an ellipse |
| Maximally flat at origin | Equiripple passband |
| Monotonic passband | Ripple in passband |
| Monotonic stopband | Monotonic stopband |
| At cutoff: $1/\sqrt2$ | At cutoff: $1/\sqrt{1+\epsilon^2}$ |
| Generally needs higher order for sharp transition | Can achieve sharper transition for a given order |
| Phase is nonlinear | Phase is more nonlinear |

---

# 76. INVERSE CHEBYSHEV FILTER

The type-2 Chebyshev response is called the **inverse Chebyshev response**.

Its magnitude response has:

$$
\boxed{
\text{maximally flat passband}
}
$$

and

$$
\boxed{
\text{equiripple stopband}.
}
$$

This is the opposite arrangement of type-1 Chebyshev.

The chapter gives

$$
\boxed{
C_N(x)=
\cos(N\cos^{-1}x),\quad |x|\le1
}
$$

and

$$
\boxed{
C_N(x)=
\cosh(N\cosh^{-1}x),\quad |x|>1.
}
$$

The attenuation and order are obtained from the low-pass specifications.

---

# 77. ELLIPTIC FILTER

The elliptic filter is also called the **Cauer filter**.

It has

$$
\boxed{
\text{equiripple passband}
}
$$

and

$$
\boxed{
\text{equiripple stopband}.
}
$$

For a given order, passband deviation and stopband deviation, the elliptic filter has the

$$
\boxed{
\text{minimum transition bandwidth}
}
$$

among the filters discussed.

The magnitude response is expressed using the Jacobian elliptic function.

---

# 78. FOUR BASIC FREQUENCY-SELECTIVE FILTERS

The chapter considers:

1. Low-pass filter
2. High-pass filter
3. Band-pass filter
4. Band-stop filter

The design strategy is to start from a low-pass prototype and transform it.

---

# 79. FREQUENCY TRANSFORMATION

The chapter gives two methods:

$$
\boxed{
\text{Analog frequency transformation}
}
$$

and

$$
\boxed{
\text{Digital frequency transformation}.
}
$$

---

# 80. ANALOG FREQUENCY TRANSFORMATION

Start with a prototype low-pass filter

$$
H_p(s).
$$

Transform it into another analog filter

$$
H(s).
$$

Then transform the analog filter to digital:

$$
\boxed{
H(s)\rightarrow H(z).
}
$$

The analog transformation can create:

- another low-pass filter;
- high-pass filter;
- band-pass filter;
- band-stop filter.

---

# 81. ANALOG LOW-PASS TO LOW-PASS

The prototype cutoff $\Omega_c$ is changed to a new cutoff $\Omega_c^*$.

The transformation is of the form

$$
\boxed{
s\rightarrow
\frac{\Omega_c}{\Omega_c^*}s.
}
$$

---

# 82. ANALOG LOW-PASS TO HIGH-PASS

The transformation is

$$
\boxed{
s\rightarrow
\frac{\Omega_c\Omega_c^*}{s}.
}
$$

Substitute this expression into the prototype:

$$
\boxed{
H_{HP}(s)
=
H_p
\left(
\frac{\Omega_c\Omega_c^*}{s}
\right).
}
$$

---

# 83. ANALOG LOW-PASS TO BAND-PASS

Let

$$
\Omega_1,\Omega_2
$$

be the desired lower and upper band-edge frequencies.

The center frequency is

$$
\boxed{
\Omega_0=
\sqrt{\Omega_1\Omega_2}.
}
$$

The bandwidth is

$$
\boxed{
B=\Omega_2-\Omega_1.
}
$$

The quality factor is

$$
\boxed{
Q=\frac{\Omega_0}{\Omega_2-\Omega_1}.
}
$$

The transformation is

$$
\boxed{
s
\rightarrow
\frac{s^2+\Omega_0^2}{Bs}.
}
$$

Equivalently,

$$
\boxed{
s
\rightarrow
\frac{
s^2+\Omega_1\Omega_2
}{
(\Omega_2-\Omega_1)s
}.
}
$$

---

# 84. ANALOG LOW-PASS TO BAND-STOP

The transformation is

$$
\boxed{
s
\rightarrow
\frac{Bs}{s^2+\Omega_0^2}.
}
$$

Equivalently,

$$
\boxed{
s
\rightarrow
\frac{
(\Omega_2-\Omega_1)s
}{
s^2+\Omega_1\Omega_2
}.
}
$$

---

# 85. EXAMPLE 8.34 — PROTOTYPE LP TO BAND-PASS

Given prototype:

$$
\boxed{
H_p(s)=\frac{1}{s^2+3s+2}.
}
$$

Required:

$$
\Omega_0=3\text{ rad/s}
$$

and

$$
Q=12.
$$

Since

$$
Q=\frac{\Omega_0}{\Omega_2-\Omega_1},
$$

the bandwidth is

$$
\boxed{
B=\frac{3}{12}=0.25.
}
$$

The low-pass-to-band-pass transformation is

$$
\boxed{
s\rightarrow
\frac{s^2+\Omega_0^2}{Bs}
=
\frac{s^2+9}{0.25s}.
}
$$

Substitute into $H_p(s)$ to obtain the band-pass transfer function.

---

# 86. EXAMPLE 8.35 — PROTOTYPE LP TO HP

Given a prototype low-pass system function $H_p(s)$.

For a desired high-pass cutoff $\Omega_c^*$, use

$$
\boxed{
s\rightarrow
\frac{\Omega_c\Omega_c^*}{s}.
}
$$

Therefore

$$
\boxed{
H_{HP}(s)=
H_p
\left(
\frac{\Omega_c\Omega_c^*}{s}
\right).
}
$$

This converts the prototype low-pass into the required high-pass filter.

---

# 87. DIGITAL FREQUENCY TRANSFORMATION

Digital frequency transformation is performed by replacing

$$
\boxed{
z^{-1}
}
$$

with a suitable function of

$$
\boxed{
z^{-1}.
}
$$

The mapping must preserve stability:

$$
\boxed{
\text{unit circle}\rightarrow\text{unit circle}
}
$$

and poles inside the unit circle must remain inside it.

The chapter provides transformations for:

- low-pass;
- high-pass;
- band-pass;
- band-stop.

---

# 88. IMPORTANT DIGITAL FREQUENCY TRANSFORMATION IDEA

A prototype digital low-pass filter can be transformed directly into another digital filter.

This is particularly useful when impulse invariant transformation is used.

The chapter points out an important design strategy:

> If impulse invariant transformation is used, it can be advantageous to transform the analog prototype to digital first and then perform the digital frequency transformation, especially for high-pass or high-resonant-frequency cases, to avoid aliasing.

For bilinear transformation, the order of analog versus digital frequency transformation is not significant; both approaches give the same result.

---

# 89. IMPORTANT DIGITAL LP-TO-HP TRANSFORMATION

The digital low-pass to high-pass mapping uses a substitution involving

$$
\boxed{
z^{-1}
\rightarrow
-\,
z^{-1}
}
$$

in the simplest prototype transformation form, with the exact cutoff-dependent mapping given in the chapter's Table 8.3.

The design parameter is determined from the desired prototype and transformed cutoff frequencies.

---

# 90. DESIGN OF AN IIR FILTER — COMPLETE MASTER PROCEDURE

When the exam asks:

> **Design a digital IIR low-pass filter from given specifications.**

Follow this exact sequence.

### Step 1 — Convert specifications

If given dB:

$$
\boxed{
A=10^{-A_{\rm dB}/20}.
}
$$

### Step 2 — Select transformation

Choose:

$$
\boxed{\text{Impulse invariant}}
$$

or

$$
\boxed{\text{Bilinear}}.
$$

### Step 3 — Convert digital frequencies to analog frequencies

Impulse invariant:

$$
\boxed{
\Omega=\frac{\omega}{T}.
}
$$

Bilinear:

$$
\boxed{
\Omega=
\frac2T\tan(\omega/2).
}
$$

### Step 4 — Determine filter order

For Butterworth use the logarithmic order formula.

For Chebyshev use the hyperbolic-cosine order formula.

### Step 5 — Determine cutoff frequency.

### Step 6 — Design analog prototype.

### Step 7 — Obtain $H_a(s)$.

### Step 8 — Convert to $H(z)$.

Impulse invariant:

$$
\boxed{
z=e^{sT}.
}
$$

Bilinear:

$$
\boxed{
s=
\frac2T
\frac{1-z^{-1}}{1+z^{-1}}.
}
$$

### Step 9 — Simplify $H(z)$.

### Step 10 — Realize the digital filter.

---

# 91. QUICK METHOD SELECTION

## Use impulse invariant when:

- low-pass is required;
- low-resonant-frequency band-pass is required;
- analog response is adequately band-limited;
- aliasing can be made negligible.

## Use bilinear when:

- high-pass is required;
- band-stop/high-frequency response is required;
- aliasing must be avoided;
- frequency warping can be handled by prewarping.

---

# 92. EXAM FORMULA SHEET — TRANSFORMATIONS

## Backward difference

$$
\boxed{
s=
\frac{1-z^{-1}}{T}.
}
$$

## Forward difference

$$
\boxed{
s=
\frac{z-1}{T}.
}
$$

## Impulse invariant

$$
\boxed{
z=e^{sT}.
}
$$

## Bilinear

$$
\boxed{
s=
\frac2T
\frac{1-z^{-1}}{1+z^{-1}}.
}
$$

## Bilinear inverse

$$
\boxed{
z=
\frac{1+sT/2}{1-sT/2}.
}
$$

---

# 93. FREQUENCY FORMULA SHEET

## Impulse invariant

$$
\boxed{
\omega=\Omega T.
}
$$

## Bilinear

$$
\boxed{
\Omega=
\frac2T
\tan(\omega/2).
}
$$

## Inverse bilinear relation

$$
\boxed{
\omega=
2\tan^{-1}(\Omega T/2).
}
$$

---

# 94. BUTTERWORTH FORMULA SHEET

Magnitude:

$$
\boxed{
|H_a(j\Omega)|^2
=
\frac1{
1+(\Omega/\Omega_c)^{2N}
}.
}
$$

Order:

$$
\boxed{
N
\ge
\frac{
\log
\left[
(1/A_2^2-1)/(1/A_1^2-1)
\right]
}{
2\log(\Omega_2/\Omega_1)
}.
}
$$

Cutoff:

$$
\boxed{
\Omega_c
=
\frac{\Omega_1}
{(1/A_1^2-1)^{1/(2N)}}.
}
$$

Pole-factor coefficient:

$$
\boxed{
b_k=
2\sin
\left[
\frac{(2k-1)\pi}{2N}
\right].
}
$$

At cutoff:

$$
\boxed{
|H_a(j\Omega_c)|=\frac1{\sqrt2}.
}
$$

---

# 95. CHEBYSHEV FORMULA SHEET

Ripple constant:

$$
\boxed{
\epsilon=
\sqrt{\frac1{A_1^2}-1}.
}
$$

Magnitude:

$$
\boxed{
|H_a(j\Omega)|^2
=
\frac1{
1+\epsilon^2
C_N^2(\Omega/\Omega_c)
}.
}
$$

Order:

$$
\boxed{
N
\ge
\frac{
\cosh^{-1}
\left[
\sqrt{
(1/A_2^2-1)/\epsilon^2
}
\right]
}{
\cosh^{-1}(\Omega_2/\Omega_1)
}.
}
$$

Chebyshev polynomial:

$$
\boxed{
C_N(x)=\cos(N\cos^{-1}x),\quad |x|\le1
}
$$

$$
\boxed{
C_N(x)=\cosh(N\cosh^{-1}x),\quad |x|>1.
}
$$

---

# 96. FILTER-TYPE MEMORY TABLE

| Approximation | Passband | Stopband | Pole pattern |
|---|---|---|---|
| Butterworth | Maximally flat | Monotonic | Circle |
| Chebyshev Type-1 | Equiripple | Monotonic | Ellipse |
| Chebyshev Type-2 / Inverse | Monotonic | Equiripple | Corresponding inverse-Chebyshev pattern |
| Elliptic | Equiripple | Equiripple | Elliptic/Cauer |

---

# 97. VERY IMPORTANT COMPARISON

### Butterworth

$$
\boxed{
\text{smoothest magnitude response}
}
$$

### Chebyshev-I

$$
\boxed{
\text{ripple in passband}
}
$$

### Inverse Chebyshev

$$
\boxed{
\text{ripple in stopband}
}
$$

### Elliptic

$$
\boxed{
\text{ripple in both}
}
$$

and the narrowest transition region for a specified order and deviations.

---

# 98. SHORT QUESTIONS — EXAM READY

### 1. Define IIR filter.

An IIR filter is a digital filter designed by considering all the infinite samples of its impulse response.

### 2. Define FIR filter.

An FIR filter is designed by considering only a finite number of samples of the impulse response.

### 3. What is impulse invariant transformation?

It is an analog-to-digital transformation in which the digital impulse response is the sampled version of the analog impulse response:

$$
\boxed{
h(n)=h_a(nT).
}
$$

### 4. What is the pole mapping?

$$
\boxed{
z=e^{sT}.
}
$$

### 5. What is bilinear transformation?

$$
\boxed{
s=
\frac2T
\frac{1-z^{-1}}{1+z^{-1}}.
}
$$

### 6. What is aliasing?

High-frequency components acquiring the identity of low-frequency components after sampling.

### 7. What is frequency warping?

Distortion of the frequency axis caused by the nonlinear analog/digital frequency relation of bilinear transformation.

### 8. What is prewarping?

Conversion of specified digital frequencies to equivalent analog frequencies before designing the analog prototype.

### 9. What is Butterworth approximation?

An approximation with maximally flat magnitude at the origin and monotonic decrease with frequency.

### 10. What is Chebyshev approximation?

An approximation in which the error is minimized over a prescribed frequency band.

---

# 99. IMPORTANT SHORT-ANSWER COMPARISONS

## Impulse invariant vs bilinear

$$
\boxed{
\begin{array}{c|c}
\text{Impulse invariant}&\text{Bilinear}\\
\hline
\text{Many-to-one}&\text{One-to-one}\\
\text{Aliasing}&\text{No aliasing}\\
\omega=\Omega T&
\Omega=\frac2T\tan(\omega/2)\\
\text{Linear frequency mapping}&\text{Nonlinear mapping}\\
\text{Needs band-limited analog filter}&\text{No such requirement}
\end{array}
}
$$

## Butterworth vs Chebyshev-I

$$
\boxed{
\begin{array}{c|c}
\text{Butterworth}&\text{Chebyshev-I}\\
\hline
\text{Maximally flat}&\text{Equiripple passband}\\
\text{Monotonic}&\text{Ripple}\\
\text{Poles on circle}&\text{Poles on ellipse}\\
\text{1st/2nd-order smooth response}&\text{Sharper transition}
\end{array}
}
$$

---

# 100. OBJECTIVE-TYPE ANSWERS

The chapter's objective section emphasizes:

1. IIR filters are **recursive**.
2. For the same specifications, IIR generally needs fewer coefficients than FIR.
3. Impulse invariant uses pole mapping

$$
\boxed{
z=e^{p_iT}.
}
$$

4. Bilinear transformation is one-to-one.
5. Bilinear frequency relation is nonlinear.
6. Nonlinearity produces frequency warping.
7. Butterworth has a maximally flat response.
8. Chebyshev-I has passband ripple.
9. Chebyshev-II is the inverse Chebyshev filter.
10. Elliptic filters have equiripple passband and stopband.
11. Butterworth is an all-pole filter.
12. Stable analog poles are in the LHP.
13. Stable digital poles are inside the unit circle.

---

# 101. FILL-IN-THE-BLANK ANSWERS

| No. | Answer |
|---:|---|
| 1 | all the infinite |
| 2 | linear |
| 3 | magnitude |
| 4 | only a finite number |
| 5 | ideal frequency response |
| 6 | sampling frequency |
| 7 | transforming |
| 8 | half |
| 9 | approximation of derivatives, impulse invariant, bilinear |
| 10 | causality, stability |
| 11 | ripples |
| 12 | impulse invariant |
| 13 | interior |
| 14 | exterior |
| 15 | unit circle |
| 16 | $2/T$ |
| 17 | aliasing |
| 18 | aliasing |
| 19 | impulse invariant |
| 20 | many-to-one, one-to-one |
| 21 | distortion in frequency axis |
| 22 | magnitude response, prewarping |
| 23 | bilinear |
| 24 | Butterworth, Chebyshev |
| 25 | Butterworth |
| 26 | $1/\sqrt2$ |
| 27 | type-1 Chebyshev |
| 28 | type-2 Chebyshev |
| 29 | inverse Chebyshev |
| 30 | $2\tan^{-1}(\Omega T/2)$ |

---

# 102. REVIEW QUESTIONS — CHAPTER 8

1. Compare analog and digital filters. State the advantages of digital filters.
2. Define IIR and FIR filters and compare them.
3. Justify why a physically realizable IIR filter cannot have linear phase.
4. Describe IIR-filter characterization in the time domain.
5. Describe IIR-filter characterization in the $z$-domain.
6. Discuss impulse invariant transformation.
7. State the limitations of impulse invariant transformation.
8. Compare impulse invariant and bilinear transformation.
9. Discuss magnitude and phase responses of digital filters.
10. Explain construction of the Butterworth circle in the $z$-plane using bilinear transformation.
11. Compare Butterworth and Chebyshev approximations.
12. Discuss the magnitude characteristics and pole locations of Butterworth and Chebyshev filters.
13. What is frequency warping?
14. Explain warping and its influence on amplitude and phase response.
15. Discuss analog frequency transformation.
16. Discuss digital frequency transformation.
17. Obtain the Butterworth transformation between $s$ and $z$ using bilinear transformation.

---

# 103. TEXTBOOK PROBLEM-SOLVING CHECKLIST

When solving a complete IIR design problem, write:

### Given

$$
A_1,\;A_2,\;\omega_1,\;\omega_2,\;T
$$

### Step 1

Choose transformation.

### Step 2

Convert digital frequencies.

### Step 3

Calculate

$$
\frac{\Omega_2}{\Omega_1}.
$$

### Step 4

Calculate order $N$.

### Step 5

Calculate $\Omega_c$.

### Step 6

Write $H_a(s)$.

### Step 7

Transform $H_a(s)$ to $H(z)$.

### Step 8

Simplify numerator and denominator.

### Step 9

State the final digital filter.

---

# 104. COMMON EXAM MISTAKES

1. Confusing $T$ and $f_s$:

$$
\boxed{
T=\frac1{f_s}.
}
$$

2. Using

$$
\Omega=\omega/T
$$

for bilinear transformation. **Wrong.**

For bilinear:

$$
\boxed{
\Omega=\frac2T\tan(\omega/2).
}
$$

3. Forgetting aliasing in impulse invariant transformation.
4. Forgetting frequency warping in bilinear transformation.
5. Forgetting prewarping.
6. Using the wrong pole-mapping equation.
7. Forgetting the factor $2/T$ in bilinear transformation.
8. Selecting an order below the calculated minimum.
9. Forgetting that Butterworth cutoff is the 3-dB point.
10. Confusing Chebyshev-I and inverse Chebyshev.
11. Confusing analog and digital frequency transformations.
12. Forgetting to select only LHP poles for a stable analog filter.
13. Forgetting that stable digital poles must lie inside the unit circle.
14. Using impulse invariant for a high-frequency high-pass design without considering aliasing.
15. Forgetting to convert dB specifications into linear gain before order calculation.

---

# 105. NUMERICAL DESIGN DECISION TREE

## Given a digital IIR specification:

### Is the transformation specified?

**Yes:** use it.

**No:** choose according to the required filter.

### Low-pass, low resonant frequency?

Impulse invariant can be appropriate.

### High-pass or high-frequency band-stop?

Prefer bilinear transformation.

### Is bilinear being used?

Prewarp:

$$
\boxed{
\Omega_i=
\frac2T\tan(\omega_i/2).
}
$$

### Is impulse invariant being used?

Use:

$$
\boxed{
\Omega_i=\omega_i/T.
}
$$

### Butterworth?

Use logarithmic order formula.

### Chebyshev-I?

Calculate

$$
\epsilon=
\sqrt{1/A_1^2-1}
$$

then use the hyperbolic order formula.

---

# 106. MATLAB — BASIC IIR FILTER DESIGN

The MATLAB environment can be used to design IIR filters after obtaining the required order and cutoff frequencies.

Example structure:

```matlab
clc;
clear;
close all;

N = 4;
Wn = 0.4;

[b,a] = butter(N,Wn,'low');

freqz(b,a);
```

---

# 107. MATLAB — BUTTERWORTH FILTER

```matlab
clc;
clear;
close all;

N = 4;
Wn = 0.4;

[b,a] = butter(N,Wn,'low');

disp('Numerator coefficients:');
disp(b);

disp('Denominator coefficients:');
disp(a);

figure;
freqz(b,a,1024);
```

---

# 108. MATLAB — CHEBYSHEV TYPE-1

```matlab
clc;
clear;
close all;

N = 4;
Rp = 1;
Wn = 0.4;

[b,a] = cheby1(N,Rp,Wn,'low');

disp('Numerator coefficients:');
disp(b);

disp('Denominator coefficients:');
disp(a);

figure;
freqz(b,a,1024);
```

---

# 109. MATLAB — IMPULSE RESPONSE

```matlab
clc;
clear;
close all;

N = 4;
Wn = 0.4;

[b,a] = butter(N,Wn,'low');

[h,n] = impz(b,a);

stem(n,h);
xlabel('n');
ylabel('h(n)');
title('Impulse Response');
grid on;
```

---

# 110. MATLAB — FREQUENCY RESPONSE

```matlab
clc;
clear;
close all;

N = 4;
Wn = 0.4;

[b,a] = butter(N,Wn,'low');

[H,w] = freqz(b,a,1024);

figure;
plot(w,abs(H));
xlabel('\omega');
ylabel('|H(e^{j\omega})|');
title('Magnitude Response');
grid on;

figure;
plot(w,angle(H));
xlabel('\omega');
ylabel('Phase');
title('Phase Response');
grid on;
```

---

# 111. MATLAB — FILTER A SIGNAL

```matlab
clc;
clear;
close all;

N = 4;
Wn = 0.4;

[b,a] = butter(N,Wn,'low');

x = sin(0.2*pi*(0:99)) + sin(0.8*pi*(0:99));

y = filter(b,a,x);

figure;
plot(x);
xlabel('n');
ylabel('x(n)');
title('Input Signal');
grid on;

figure;
plot(y);
xlabel('n');
ylabel('y(n)');
title('Filtered Signal');
grid on;
```

---

# 112. ONE-PAGE MASTER FORMULA SHEET

## Analog-to-digital mappings

$$
\boxed{
s\rightarrow\frac{1-z^{-1}}{T}
}
$$

Backward difference.

$$
\boxed{
s\rightarrow\frac{z-1}{T}
}
$$

Forward difference.

$$
\boxed{
z=e^{sT}
}
$$

Impulse invariant.

$$
\boxed{
s=
\frac2T
\frac{1-z^{-1}}{1+z^{-1}}
}
$$

Bilinear.

---

## Frequency mappings

$$
\boxed{
\omega=\Omega T
}
$$

Impulse invariant.

$$
\boxed{
\Omega=
\frac2T\tan(\omega/2)
}
$$

Bilinear.

---

## Stability mappings

$$
\boxed{
\text{LHP}\rightarrow|z|<1
}
$$

$$
\boxed{
j\Omega\text{-axis}\rightarrow|z|=1
}
$$

$$
\boxed{
\text{RHP}\rightarrow|z|>1
}
$$

for bilinear transformation.

---

## Butterworth

$$
\boxed{
|H|^2=
\frac1{1+(\Omega/\Omega_c)^{2N}}
}
$$

$$
\boxed{
|H(\Omega_c)|=\frac1{\sqrt2}
}
$$

$$
\boxed{
b_k=
2\sin
\left[
\frac{(2k-1)\pi}{2N}
\right]
}
$$

---

## Chebyshev-I

$$
\boxed{
\epsilon=
\sqrt{1/A_1^2-1}
}
$$

$$
\boxed{
|H|^2=
\frac1{1+\epsilon^2C_N^2(\Omega/\Omega_c)}
}
$$

---

## Frequency transformation

Low-pass to high-pass:

$$
\boxed{
s\rightarrow\frac{\Omega_c\Omega_c^*}{s}
}
$$

Low-pass to band-pass:

$$
\boxed{
s\rightarrow
\frac{s^2+\Omega_1\Omega_2}
{(\Omega_2-\Omega_1)s}
}
$$

Low-pass to band-stop:

$$
\boxed{
s\rightarrow
\frac{(\Omega_2-\Omega_1)s}
{s^2+\Omega_1\Omega_2}
}
$$

---

# 113. FINAL MEMORY MAP

$$
\boxed{
\text{IIR}
=
\text{Infinite response}
=
\text{recursive}
}
$$

$$
\boxed{
\text{FIR}
=
\text{Finite response}
=
\text{non-recursive}
}
$$

$$
\boxed{
\text{Impulse invariant}
=
\text{sample impulse response}
}
$$

$$
\boxed{
z=e^{sT}
}
$$

$$
\boxed{
\text{aliasing}
}
$$

$$
\boxed{
\text{Bilinear}
=
\text{one-to-one}
}
$$

$$
\boxed{
s=
\frac2T
\frac{1-z^{-1}}{1+z^{-1}}
}
$$

$$
\boxed{
\text{no aliasing}
}
$$

$$
\boxed{
\text{frequency warping}
}
$$

$$
\boxed{
\text{prewarping fixes magnitude-frequency placement}
}
$$

$$
\boxed{
\text{Butterworth}
=
\text{maximally flat}
}
$$

$$
\boxed{
\text{Chebyshev-I}
=
\text{passband ripple}
}
$$

$$
\boxed{
\text{Inverse Chebyshev}
=
\text{stopband ripple}
}
$$

$$
\boxed{
\text{Elliptic}
=
\text{ripple in both}
}
$$

---

# 114. FINAL EXAM CHECKLIST

Before the exam, make sure you can:

- [ ] Define IIR and FIR.
- [ ] Compare IIR and FIR.
- [ ] Explain why stable IIR cannot have linear phase.
- [ ] State analog stability conditions.
- [ ] State digital stability conditions.
- [ ] Explain approximation of derivatives.
- [ ] Derive backward-difference mapping.
- [ ] Explain forward-difference mapping.
- [ ] Solve basic derivative-approximation examples.
- [ ] Explain impulse invariant transformation.
- [ ] Derive $z=e^{sT}$.
- [ ] Explain pole mapping.
- [ ] Explain aliasing.
- [ ] Solve impulse-invariant examples.
- [ ] Explain bilinear transformation.
- [ ] Derive the bilinear formula.
- [ ] Explain stability preservation.
- [ ] Derive the analog/digital frequency relation.
- [ ] Explain frequency warping.
- [ ] Explain prewarping.
- [ ] Compare impulse invariant and bilinear methods.
- [ ] Convert filter specifications from dB to gain.
- [ ] Calculate Butterworth order.
- [ ] Calculate Butterworth cutoff.
- [ ] Locate Butterworth poles.
- [ ] Form Butterworth $H_a(s)$.
- [ ] Convert Butterworth $H_a(s)$ to $H(z)$.
- [ ] Calculate Chebyshev ripple constant.
- [ ] Calculate Chebyshev order.
- [ ] Explain Chebyshev pole locations.
- [ ] Compare Butterworth and Chebyshev.
- [ ] Explain inverse Chebyshev.
- [ ] Explain elliptic filters.
- [ ] Perform analog frequency transformations.
- [ ] Explain digital frequency transformations.
- [ ] Write MATLAB code for Butterworth/Chebyshev filters.
- [ ] Use `freqz`, `filter`, and `impz`.

---

# 115. SOURCE COVERAGE NOTE

This guide follows Chapter 8 of A. Anand Kumar's *Digital Signal Processing*, printed pages **548–650**. The book's contents identify the chapter sections as:

- 8.1 Introduction — p. 548
- 8.2 Requirements for Transformation — p. 548
- 8.3 Design by Approximation of Derivatives — p. 551
- 8.4 Design by Impulse Invariant Transformation — p. 556
- 8.5 Design by Bilinear Transformation — p. 565
- 8.6 Specifications of Low-pass Filter — p. 575
- 8.7 Low-pass Digital Butterworth — p. 576
- 8.8 Low-pass Chebyshev — p. 599
- 8.9 Inverse Chebyshev — p. 618
- 8.10 Elliptic Filters — p. 620
- 8.11 Frequency Transformation — p. 620
- 8.11.1 Analog Frequency Transformation — p. 621
- 8.11.2 Digital Frequency Transformation — p. 623
- Short Questions — p. 624
- Review Questions — p. 632
- Fill in the Blanks — p. 633
- Objective Questions — p. 634
- Problems — p. 637
- MATLAB Programs — p. 639

The chapter contains many numerical design examples; the most important exam workflow is always:

$$
\boxed{
\text{Specifications}
\rightarrow
\text{frequency conversion}
\rightarrow
\text{order}
\rightarrow
\text{cutoff}
\rightarrow
H_a(s)
\rightarrow
H(z).
}
$$

