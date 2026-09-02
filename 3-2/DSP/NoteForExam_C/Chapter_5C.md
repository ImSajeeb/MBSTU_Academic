---
title: "Chapter 5 — Discrete-time Fourier Transform (DTFT)"
source: "Digital Signal Processing — A. Anand Kumar"
source_pages: "Printed pages 358–411"
purpose: "Full theory + derivations + worked examples + exam-oriented revision"
---

# CHAPTER 5 — DISCRETE-TIME FOURIER TRANSFORM (DTFT)

> **Source boundary:** These notes are prepared from Chapter 5, *Discrete-time Fourier Transform*, of A. Anand Kumar's *Digital Signal Processing*, printed pages **358–411**. The chapter introduces DTFT, its existence condition, its relation with the Z-transform, inverse DTFT, DTFT properties, transfer function, frequency response, ideal-filter impulse responses, examples, review questions, fill-in-the-blanks, objective questions, problems, and MATLAB programs.

---

# 1. CHAPTER ROADMAP

1. Introduction
2. Discrete-time Fourier Transform
3. Existence of DTFT
4. Relation between Z-transform and Fourier Transform
5. Inverse DTFT
6. Properties of DTFT
   - Linearity
   - Periodicity
   - Time shifting
   - Frequency shifting
   - Time reversal
   - Differentiation in frequency domain
   - Time convolution
   - Frequency convolution
   - Correlation theorem
   - Modulation theorem
   - Parseval's theorem
   - Symmetry properties
7. Worked Examples 5.1–5.23
8. Transfer function
9. Frequency response of discrete-time systems
10. Ideal-filter impulse responses
11. Short questions
12. Fill-in-the-blanks
13. Objective-type questions
14. Problems
15. MATLAB programs
16. Master formula sheet and exam decision tree

---

# 2. INTRODUCTION

A continuous-time signal can be represented in the frequency domain using the **Laplace transform** or **continuous-time Fourier transform (CTFT)**.

Similarly, a discrete-time signal can be represented in the frequency domain using:

- **Z-transform**
- **Discrete-time Fourier transform (DTFT)**

The Fourier transform of a discrete-time signal is called the **discrete-time Fourier transform (DTFT)**.

The major usefulness of DTFT is that a difficult convolution operation in the time domain becomes a simple multiplication in the frequency domain:

$$
x(n)*h(n)
\quad\longleftrightarrow\quad
X(e^{j\omega})H(e^{j\omega})
$$

Thus DTFT is particularly useful in the analysis of discrete-time signals and LTI systems.

---

# 3. DISCRETE-TIME FOURIER TRANSFORM

## 3.1 Definition

If $x(n)$ is a discrete-time sequence, its DTFT is denoted by

$$
X(\omega)
$$

or equivalently

$$
X(e^{j\omega}).
$$

The DTFT is defined as

$$
\boxed{
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n}
}
$$

or

$$
\boxed{
X(\omega)
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n}
}
$$

The sequence $x(n)$ and spectrum $X(e^{j\omega})$ are called a **Fourier-transform pair**:

$$
\boxed{
x(n)\ \xleftrightarrow{\mathcal F}\ X(e^{j\omega})
}
$$

---

# 4. INVERSE DTFT

The inverse DTFT gives the original sequence $x(n)$ from its spectrum.

$$
\boxed{
x(n)
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

Because the DTFT is periodic with period $2\pi$, any interval of length $2\pi$ can be used.

For example,

$$
x(n)
=
\frac{1}{2\pi}
\int_{0}^{2\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega
$$

is also valid.

---

# 5. EXISTENCE OF DTFT

The book gives the absolute-summability condition for existence of the DTFT.

The sequence $x(n)$ must satisfy

$$
\boxed{
\sum_{n=-\infty}^{\infty}|x(n)|<\infty
}
$$

This is the condition of **absolute summability**.

Therefore:

> **A discrete-time sequence has a DTFT when it is absolutely summable.**

### Important exam consequence

A sequence such as

$$
x(n)=a^n u(n),\qquad |a|<1
$$

is absolutely summable and therefore has a DTFT.

But if

$$
x(n)=a^n u(n),\qquad |a|>1,
$$

the sequence grows exponentially and is not absolutely summable; hence its ordinary DTFT does not exist.

The book also states that DTFT-based system analysis is applicable to asymptotically stable systems; for a rational system function this corresponds to poles lying inside the unit circle.

---

# 6. DTFT AS THE SIGNAL SPECTRUM

The Fourier transform $X(e^{j\omega})$ represents the **frequency content** of $x(n)$.

Taking the Fourier transform decomposes the signal into its frequency components. Hence

$$
\boxed{X(e^{j\omega})\text{ is called the signal spectrum.}}
$$

---

# 7. IMPORTANT DIFFERENCES: ANALOG FT vs DISCRETE-TIME FT

| Analog/continuous-time FT | Discrete-time FT |
|---|---|
| Spectrum extends over $-\infty<\omega<\infty$ | Spectrum is periodic |
| Unique frequency range can be taken as all real frequencies | One unique interval is $-\pi\le\omega\le\pi$, or $0\le\omega\le2\pi$ |
| Fourier transform involves integration | DTFT involves summation |
| Continuous-time signal is transformed using an integral | Discrete-time sequence is transformed using a sum |

The periodicity of DTFT is one of the most important differences.

---

# 8. PERIODICITY OF DTFT

Starting from

$$
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}x(n)e^{-j\omega n},
$$

replace $\omega$ by $\omega+2\pi$:

$$
X(e^{j(\omega+2\pi)})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j(\omega+2\pi)n}.
$$

Since $n$ is an integer,

$$
e^{-j2\pi n}=1.
$$

Therefore,

$$
X(e^{j(\omega+2\pi)})
=
X(e^{j\omega}).
$$

Hence

$$
\boxed{
X(\omega+2\pi)=X(\omega)
}
$$

So the DTFT is periodic with period

$$
\boxed{2\pi}.
$$

### Exam point

Only one period is needed for analysis, commonly

$$
-\pi\le\omega\le\pi
$$

or

$$
0\le\omega\le2\pi.
$$

---

# 9. RELATION BETWEEN Z-TRANSFORM AND DTFT

The Z-transform of $x(n)$ is

$$
X(z)=
\sum_{n=-\infty}^{\infty}
x(n)z^{-n}.
$$

Let

$$
z=re^{j\omega}.
$$

Then

$$
X(z)
=
\sum_{n=-\infty}^{\infty}
x(n)(re^{j\omega})^{-n}
$$

$$
=
\sum_{n=-\infty}^{\infty}
x(n)r^{-n}e^{-j\omega n}.
$$

Therefore, the Z-transform is the Fourier transform of the weighted sequence

$$
x(n)r^{-n}.
$$

When

$$
r=1,
$$

we have

$$
z=e^{j\omega}.
$$

Hence

$$
\boxed{
X(e^{j\omega})
=
X(z)\big|_{z=e^{j\omega}}
}
$$

That is:

> **DTFT is the Z-transform evaluated on the unit circle.**

---

## 9.1 Condition for obtaining DTFT from $X(z)$

For the DTFT to exist, the **ROC of $X(z)$** must include the unit circle.

Since the ROC cannot contain poles, the poles of a causal rational system must lie inside the unit circle for the DTFT to exist.

Thus, for the causal stable rational case:

$$
\boxed{|p_k|<1\quad\text{for every pole}}
$$

---

# 10. EXAMPLE 5.1 — BASIC DTFT PAIRS

**Find the DTFT of the following sequences:**

### (a) $\delta(n)$

Given

$$
x(n)=\delta(n)
$$

By definition,

$$
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
\delta(n)e^{-j\omega n}.
$$

Only $n=0$ contributes:

$$
X(e^{j\omega})=1.
$$

Therefore,

$$
\boxed{
\delta(n)\xleftrightarrow{\mathcal F}1
}
$$

---

### (b) $u(n)$

Given

$$
x(n)=u(n)
$$

Therefore

$$
X(e^{j\omega})
=
\sum_{n=0}^{\infty}e^{-j\omega n}.
$$

This is a geometric series:

$$
X(e^{j\omega})
=
1+e^{-j\omega}+e^{-j2\omega}+\cdots
$$

so

$$
\boxed{
X(e^{j\omega})
=
\frac{1}{1-e^{-j\omega}}
}
$$

in the book's ordinary transform treatment.

---

### (c) $\delta(n-m)$

Given

$$
x(n)=\delta(n-m).
$$

Then

$$
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
\delta(n-m)e^{-j\omega n}.
$$

The impulse selects $n=m$:

$$
X(e^{j\omega})
=
e^{-j\omega m}.
$$

Hence

$$
\boxed{
\delta(n-m)
\xleftrightarrow{\mathcal F}
e^{-j\omega m}
}
$$

---

### (d) $u(n-m)$

Given

$$
x(n)=u(n-m).
$$

The non-zero samples start at $n=m$:

$$
X(e^{j\omega})
=
e^{-j\omega m}
+
e^{-j\omega(m+1)}
+
e^{-j\omega(m+2)}
+\cdots
$$

Factor $e^{-j\omega m}$:

$$
X(e^{j\omega})
=
e^{-j\omega m}
\left[
1+e^{-j\omega}+e^{-j2\omega}+\cdots
\right].
$$

Therefore,

$$
\boxed{
X(e^{j\omega})
=
\frac{e^{-j\omega m}}
{1-e^{-j\omega}}
}
$$

---

### (e) $a^n u(n)$

Given

$$
x(n)=a^n u(n).
$$

Then

$$
X(e^{j\omega})
=
\sum_{n=0}^{\infty}
a^n e^{-j\omega n}
$$

$$
=
\sum_{n=0}^{\infty}
(ae^{-j\omega})^n.
$$

Using the geometric-series result,

$$
\boxed{
X(e^{j\omega})
=
\frac{1}{1-ae^{-j\omega}}
}
$$

---

### (f) $-a^n u(-n-1)$

The sequence is left-sided:

$$
x(n)=-a^n u(-n-1).
$$

The non-zero samples occur for

$$
n\le -1.
$$

Hence

$$
X(e^{j\omega})
=
-\sum_{n=-\infty}^{-1}
a^n e^{-j\omega n}.
$$

Let

$$
k=-n.
$$

Then $k=1,2,\ldots$, giving

$$
X(e^{j\omega})
=
-\sum_{k=1}^{\infty}
a^{-k}e^{j\omega k}.
$$

This geometric series gives

$$
\boxed{
X(e^{j\omega})
=
\frac{1}{1-ae^{-j\omega}}
}
$$

with the corresponding convergence condition for the left-sided sequence.

---

### (g) $\delta(n+3)-\delta(n-3)$

Using the shifted-impulse result,

$$
\delta(n+3)
\longleftrightarrow
e^{j3\omega}
$$

and

$$
\delta(n-3)
\longleftrightarrow
e^{-j3\omega}.
$$

Therefore,

$$
X(e^{j\omega})
=
e^{j3\omega}-e^{-j3\omega}.
$$

Using

$$
e^{j\theta}-e^{-j\theta}=2j\sin\theta,
$$

we obtain

$$
\boxed{
X(e^{j\omega})=2j\sin(3\omega)
}
$$

---

### (h) $u(n+3)-u(n-3)$

This is a finite rectangular sequence. It is non-zero for

$$
-3\le n\le2.
$$

Therefore,

$$
X(e^{j\omega})
=
\sum_{n=-3}^{2}e^{-j\omega n}.
$$

Writing the terms explicitly and simplifying gives the corresponding finite geometric-series expression. The important exam technique is:

1. Identify the finite support.
2. Write every non-zero exponential term.
3. Combine using the finite geometric-series identity.

---

# 11. EXAMPLE 5.2 — DTFT OF DIFFERENT TYPES OF SEQUENCES

### (a) $x(n)=\{1,-2,2,3\}$

Assuming the first sample corresponds to $n=0$,

$$
x(0)=1,\quad x(1)=-2,\quad x(2)=2,\quad x(3)=3.
$$

By definition,

$$
X(e^{j\omega})
=
x(0)+x(1)e^{-j\omega}
+x(2)e^{-j2\omega}
+x(3)e^{-j3\omega}.
$$

Hence

$$
\boxed{
X(e^{j\omega})
=
1-2e^{-j\omega}
+2e^{-j2\omega}
+3e^{-j3\omega}
}
$$

---

### (b) $x(n)=3^n u(n)$

Since

$$
|3|>1,
$$

the sequence grows exponentially and is not absolutely summable.

Therefore,

$$
\boxed{\text{DTFT does not exist.}}
$$

---

### (c) $x(n)=(0.5)^n u(n)+2^n u(-n-1)$

Treat the two parts separately.

For the right-sided part,

$$
(0.5)^n u(n)
$$

the DTFT exists because $0.5<1$.

For the left-sided part,

$$
2^n u(-n-1),
$$

the left-sided sequence decays as $n\to-\infty$.

The two-sided sequence can therefore be handled by calculating the two geometric sums separately and adding the results.

**Exam strategy:** when a sequence contains a sum of right-sided and left-sided terms, transform each part separately and then use linearity.

---

### (d) $\frac{1}{4}(1/4)^n u(n-1)$

Use the time-shift property or start the summation at the first non-zero sample.

The key step is

$$
u(n-1)=1,\qquad n\ge1.
$$

Therefore,

$$
X(e^{j\omega})
=
\frac14
\sum_{n=1}^{\infty}
\left(\frac14\right)^n e^{-j\omega n}.
$$

This is a geometric series.

---

### (e) Finite sequence defined for $-4\le n\le4$

For

$$
x(n)=n,\qquad -4\le n\le4,
$$

and zero otherwise,

$$
X(e^{j\omega})
=
\sum_{n=-4}^{4}ne^{-j\omega n}.
$$

A useful way to simplify it is to pair positive and negative indices:

$$
ne^{-j\omega n}+(-n)e^{j\omega n}
=
n(e^{-j\omega n}-e^{j\omega n})
$$

$$
=
-2jn\sin(n\omega).
$$

Thus the resulting spectrum is purely imaginary, consistent with the odd real time sequence.

---

### (f) Rectangular sequence

If

$$
x(n)=
\begin{cases}
1,&0\le n\le3,\\
0,&\text{otherwise},
\end{cases}
$$

then

$$
X(e^{j\omega})
=
1+e^{-j\omega}+e^{-j2\omega}+e^{-j3\omega}.
$$

Using the finite geometric-series identity,

$$
\boxed{
X(e^{j\omega})
=
e^{-j3\omega/2}
\frac{\sin(2\omega)}
{\sin(\omega/2)}
}
$$

with the value at points where the denominator is zero obtained by taking the appropriate limiting value.

---

### (g) $x(n)=a^{|n|}$

Split the sequence into two sides:

$$
x(n)=
\begin{cases}
a^n,&n\ge0,\\
a^{-n},&n<0.
\end{cases}
$$

Then

$$
X(e^{j\omega})
=
1+\sum_{n=1}^{\infty}a^ne^{-j\omega n}
+\sum_{n=1}^{\infty}a^ne^{j\omega n}.
$$

For $|a|<1$,

$$
X(e^{j\omega})
=
1+
\frac{ae^{-j\omega}}{1-ae^{-j\omega}}
+
\frac{ae^{j\omega}}{1-ae^{j\omega}}.
$$

After simplification,

$$
\boxed{
X(e^{j\omega})
=
\frac{1-a^2}
{1-2a\cos\omega+a^2}
}
$$

---

# 12. EXAMPLE 5.3 — SINUSOIDAL RIGHT-SIDED SEQUENCES

The book considers sequences such as

$$
x(n)=\sin\left(\frac{\pi n}{2}\right)u(n)
$$

and

$$
x(n)=\cos\left(\frac{\pi n}{3}\right)u(n),
$$

as well as related sinusoidal/exponential forms.

## General method

Use Euler identities:

$$
\cos(\omega_0n)
=
\frac12
\left(
e^{j\omega_0n}+e^{-j\omega_0n}
\right)
$$

and

$$
\sin(\omega_0n)
=
\frac{1}{2j}
\left(
e^{j\omega_0n}-e^{-j\omega_0n}
\right).
$$

For example,

$$
\sin(\omega_0n)u(n)
=
\frac{1}{2j}
\left[
e^{j\omega_0n}u(n)
-
e^{-j\omega_0n}u(n)
\right].
$$

Then use

$$
\sum_{n=0}^{\infty}r^n
=
\frac{1}{1-r}
$$

for the convergent geometric series.

### Exam procedure

1. Convert sine/cosine into exponentials.
2. Multiply by $u(n)$.
3. Transform each exponential right-sided term.
4. Combine the two fractions.
5. Simplify the final expression.

---

# 13. EXAMPLE 5.4 — DTFT OF A RECTANGULAR PULSE

Consider

$$
x(n)=
\begin{cases}
A,&-N\le n\le N,\\
0,&\text{otherwise}.
\end{cases}
$$

Then

$$
X(e^{j\omega})
=
A\sum_{n=-N}^{N}e^{-j\omega n}.
$$

This is a finite geometric series. A standard symmetric form is

$$
\boxed{
X(e^{j\omega})
=
A\frac{\sin\left[(2N+1)\omega/2\right]}
{\sin(\omega/2)}
}
$$

The result is real and even because the rectangular sequence is real and even.

### Important interpretation

The rectangular sequence in time produces a **sinc-like periodic spectrum** in frequency.

---

# 14. INVERSE DISCRETE-TIME FOURIER TRANSFORM

The process of obtaining $x(n)$ from $X(e^{j\omega})$ is called the **inverse DTFT**.

The formula is

$$
\boxed{
x(n)=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

Although the integral is useful analytically, the book also emphasizes a direct coefficient method.

From

$$
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n},
$$

if $X(e^{j\omega})$ can be written as a sum of complex exponentials, then the coefficient of

$$
e^{-j\omega n}
$$

is the corresponding sample $x(n)$.

---

# 15. EXAMPLE 5.5 — FIND $x(n)$ FROM $X(e^{j\omega})$

### (a)

If

$$
X(e^{j\omega})=e^{-j\omega}
$$

compare it with

$$
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n}.
$$

The coefficient of $e^{-j\omega}$ is 1.

Therefore,

$$
\boxed{x(n)=\delta(n-1)}
$$

---

### (b)

The book gives a spectrum involving

$$
X(e^{j\omega})
=
e^{-j\omega}(1+\cos\omega).
$$

Use

$$
\cos\omega
=
\frac12(e^{j\omega}+e^{-j\omega}).
$$

Hence

$$
e^{-j\omega}(1+\cos\omega)
=
e^{-j\omega}
+
\frac12
+
\frac12e^{-j2\omega}.
$$

Therefore the coefficients are

$$
x(0)=\frac12,
\qquad
x(1)=1,
\qquad
x(2)=\frac12.
$$

Thus

$$
\boxed{
x(n)=\left\{\frac12,\;1,\;\frac12\right\}
}
$$

with the samples located at $n=0,1,2$.

---

# 16. EXAMPLE 5.6 — IMPULSE RESPONSE FROM FREQUENCY RESPONSE

The book gives a frequency response specified over a frequency interval and asks for the impulse response.

The fundamental relation is

$$
\boxed{
h(n)=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
H(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

The solution is obtained by:

1. inserting the given $H(e^{j\omega})$,
2. evaluating the integral over the specified interval,
3. simplifying the resulting sine terms,
4. expressing the result as $h(n)$.

This is the standard method for obtaining the impulse response from a frequency response.

---

# 17. EXAMPLE 5.7 — INVERSE FOURIER TRANSFORM

Given a piecewise frequency spectrum $X(e^{j\omega})$, use

$$
x(n)
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega.
$$

For piecewise spectra, split the integral into the specified intervals:

$$
x(n)
=
\frac{1}{2\pi}
\left[
\int_{\omega_1}^{\omega_2}
X(e^{j\omega})e^{j\omega n}\,d\omega
+
\int_{\omega_2}^{\omega_3}
X(e^{j\omega})e^{j\omega n}\,d\omega
+\cdots
\right].
$$

The final expression is simplified using exponential and trigonometric identities.

---

# 18. EXAMPLE 5.8 — INVERSE TRANSFORM BY COEFFICIENT COMPARISON

Given

$$
X(e^{j\omega})
=
2-e^{-j\omega}+3e^{-j3\omega}+4e^{-j4\omega},
$$

compare directly with

$$
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n}.
$$

Therefore,

$$
x(0)=2,
\quad
x(1)=-1,
\quad
x(2)=0,
\quad
x(3)=3,
\quad
x(4)=4.
$$

Thus

$$
\boxed{
x(n)=\{2,-1,0,3,4\}
}
$$

---

# 19. PROPERTIES OF DTFT

## 19.1 Linearity Property

If

$$
x_1(n)\xleftrightarrow{\mathcal F}X_1(e^{j\omega})
$$

and

$$
x_2(n)\xleftrightarrow{\mathcal F}X_2(e^{j\omega}),
$$

then

$$
\boxed{
ax_1(n)+bx_2(n)
\xleftrightarrow{\mathcal F}
aX_1(e^{j\omega})+bX_2(e^{j\omega})
}
$$

### Proof

$$
\mathcal F\{ax_1(n)+bx_2(n)\}
=
\sum_n
[ax_1(n)+bx_2(n)]e^{-j\omega n}
$$

$$
=
a\sum_nx_1(n)e^{-j\omega n}
+
b\sum_nx_2(n)e^{-j\omega n}
$$

$$
=
aX_1(e^{j\omega})+bX_2(e^{j\omega}).
$$

---

## 19.2 Periodicity Property

$$
\boxed{
X(e^{j(\omega+2\pi)})
=
X(e^{j\omega})
}
$$

The DTFT repeats every $2\pi$.

---

## 19.3 Time-Shifting Property

If

$$
x(n)\xleftrightarrow{\mathcal F}X(e^{j\omega}),
$$

then

$$
\boxed{
x(n-m)
\xleftrightarrow{\mathcal F}
e^{-j\omega m}X(e^{j\omega})
}
$$

### Proof

$$
\mathcal F\{x(n-m)\}
=
\sum_nx(n-m)e^{-j\omega n}.
$$

Let

$$
p=n-m
\quad\Rightarrow\quad
n=p+m.
$$

Then

$$
\sum_p x(p)e^{-j\omega(p+m)}
=
e^{-j\omega m}
\sum_p x(p)e^{-j\omega p}.
$$

Hence

$$
\boxed{
\mathcal F\{x(n-m)\}
=
e^{-j\omega m}X(e^{j\omega})
}
$$

### Physical meaning

A time shift changes the **phase**, but not the magnitude:

$$
|e^{-j\omega m}X(e^{j\omega})|
=
|X(e^{j\omega})|.
$$

---

## 19.4 Frequency-Shifting Property

If

$$
x(n)\xleftrightarrow{\mathcal F}X(e^{j\omega}),
$$

then

$$
\boxed{
x(n)e^{j\omega_0n}
\xleftrightarrow{\mathcal F}
X(e^{j(\omega-\omega_0)})
}
$$

### Proof

$$
\mathcal F\{x(n)e^{j\omega_0n}\}
=
\sum_nx(n)e^{j\omega_0n}e^{-j\omega n}
$$

$$
=
\sum_nx(n)e^{-j(\omega-\omega_0)n}.
$$

Therefore,

$$
\boxed{
X(e^{j(\omega-\omega_0)})
}
$$

is obtained.

---

## 19.5 Time-Reversal Property

If

$$
x(n)\xleftrightarrow{\mathcal F}X(e^{j\omega}),
$$

then

$$
\boxed{
x(-n)
\xleftrightarrow{\mathcal F}
X(e^{-j\omega})
}
$$

### Meaning

Folding in the time domain corresponds to folding in the frequency domain.

---

## 19.6 Differentiation in the Frequency Domain

If

$$
x(n)\xleftrightarrow{\mathcal F}X(e^{j\omega}),
$$

then

$$
\boxed{
nx(n)
\xleftrightarrow{\mathcal F}
j\frac{dX(e^{j\omega})}{d\omega}
}
$$

### Proof

Starting with

$$
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n},
$$

differentiate:

$$
\frac{dX}{d\omega}
=
\sum_nx(n)(-jn)e^{-j\omega n}.
$$

Therefore,

$$
j\frac{dX}{d\omega}
=
\sum_nnx(n)e^{-j\omega n}.
$$

Hence

$$
\boxed{
\mathcal F\{nx(n)\}
=
j\frac{dX}{d\omega}
}
$$

---

## 19.7 Time-Convolution Property

If

$$
x_1(n)\xleftrightarrow{\mathcal F}X_1(e^{j\omega})
$$

and

$$
x_2(n)\xleftrightarrow{\mathcal F}X_2(e^{j\omega}),
$$

then

$$
\boxed{
x_1(n)*x_2(n)
\xleftrightarrow{\mathcal F}
X_1(e^{j\omega})X_2(e^{j\omega})
}
$$

Thus

$$
\boxed{
\mathcal F\{x_1*x_2\}=X_1X_2
}
$$

This is one of the most important reasons DTFT is useful.

---

## 19.8 Frequency-Convolution Property

If

$$
x_1(n)\xleftrightarrow{\mathcal F}X_1(e^{j\omega})
$$

and

$$
x_2(n)\xleftrightarrow{\mathcal F}X_2(e^{j\omega}),
$$

then

$$
\boxed{
\mathcal F\{x_1(n)x_2(n)\}
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
X_1(e^{j\theta})
X_2(e^{j(\omega-\theta)})
\,d\theta
}
$$

This is **periodic convolution** in the frequency domain.

---

## 19.9 Correlation Theorem

If $R_{x_1x_2}(l)$ is the cross-correlation sequence, then

$$
\boxed{
\mathcal F\{R_{x_1x_2}(l)\}
=
X_1(e^{j\omega})
X_2(e^{-j\omega})
}
$$

The product is called the **cross-energy spectrum** of the two signals.

---

## 19.10 Modulation Theorem

If

$$
x(n)\xleftrightarrow{\mathcal F}X(e^{j\omega}),
$$

then

$$
\boxed{
x(n)\cos(\omega_0n)
\xleftrightarrow{\mathcal F}
\frac12
\left[
X(e^{j(\omega-\omega_0)})
+
X(e^{j(\omega+\omega_0)})
\right]
}
$$

### Derivation

Use

$$
\cos(\omega_0n)
=
\frac12
\left(
e^{j\omega_0n}
+
e^{-j\omega_0n}
\right).
$$

Therefore,

$$
x(n)\cos(\omega_0n)
=
\frac12x(n)e^{j\omega_0n}
+
\frac12x(n)e^{-j\omega_0n}.
$$

Apply frequency shifting to each term.

---

## 19.11 Parseval's Theorem

For a discrete-time aperiodic signal, the energy can be expressed in either domain.

Time-domain energy:

$$
E
=
\sum_{n=-\infty}^{\infty}|x(n)|^2.
$$

Frequency-domain form:

$$
\boxed{
E
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
|X(e^{j\omega})|^2\,d\omega
}
$$

Thus

$$
\boxed{
\sum_{n=-\infty}^{\infty}|x(n)|^2
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
|X(e^{j\omega})|^2d\omega
}
$$

---

# 20. SYMMETRY PROPERTIES

Write

$$
X(e^{j\omega})
=
X_R(\omega)+jX_I(\omega).
$$

From

$$
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n}
$$

and

$$
e^{-j\omega n}
=
\cos(\omega n)-j\sin(\omega n),
$$

we obtain

$$
X_R(\omega)
=
\sum_nx(n)\cos(\omega n)
$$

and

$$
X_I(\omega)
=
-\sum_nx(n)\sin(\omega n).
$$

For a **real sequence** $x(n)$:

$$
\boxed{
X_R(-\omega)=X_R(\omega)
}
$$

so the real part is even, and

$$
\boxed{
X_I(-\omega)=-X_I(\omega)
}
$$

so the imaginary part is odd.

The magnitude is even:

$$
\boxed{
|X(-\omega)|=|X(\omega)|
}
$$

and the phase has odd symmetry where defined, subject to phase wrapping.

---

## 20.1 Polar Form

The DTFT can be expressed as

$$
\boxed{
X(e^{j\omega})
=
|X(e^{j\omega})|e^{j\theta(\omega)}
}
$$

where

- $|X(e^{j\omega})|$ = magnitude spectrum
- $\theta(\omega)$ = phase spectrum

Also,

$$
|X(e^{j\omega})|
=
\sqrt{X_R^2(\omega)+X_I^2(\omega)}
$$

and

$$
\boxed{
\theta(\omega)
=
\tan^{-1}
\left(
\frac{X_I(\omega)}{X_R(\omega)}
\right)
}
$$

with quadrant considerations when calculating the actual phase.

---

# 21. MASTER DTFT PROPERTY TABLE

| Time-domain sequence | DTFT |
|---|---|
| $x(n)$ | $X(e^{j\omega})$ |
| $ax_1(n)+bx_2(n)$ | $aX_1+bX_2$ |
| $x(n-m)$ | $e^{-j\omega m}X(e^{j\omega})$ |
| $x(-n)$ | $X(e^{-j\omega})$ |
| $x(n)e^{j\omega_0n}$ | $X(e^{j(\omega-\omega_0)})$ |
| $nx(n)$ | $j\,dX/d\omega$ |
| $x_1(n)*x_2(n)$ | $X_1X_2$ |
| $x_1(n)x_2(n)$ | $\frac1{2\pi}X_1*_{\rm periodic}X_2$ |
| $R_{x_1x_2}(l)$ | $X_1(e^{j\omega})X_2(e^{-j\omega})$ |
| $x(n)\cos\omega_0n$ | $\frac12[X(\omega-\omega_0)+X(\omega+\omega_0)]$ |
| $|x(n)|^2$ | energy relation through Parseval |

---

# 22. EXAMPLE 5.9 — USING DTFT PROPERTIES

This example applies the properties instead of repeatedly evaluating the DTFT definition.

## Typical transformations

### Time shift

If

$$
x(n)\leftrightarrow X(e^{j\omega}),
$$

then

$$
x(n-m)
\leftrightarrow
e^{-j\omega m}X(e^{j\omega}).
$$

### Time reversal

$$
x(-n)
\leftrightarrow
X(e^{-j\omega}).
$$

### Differentiation

$$
nx(n)
\leftrightarrow
j\frac{dX}{d\omega}.
$$

### Frequency shift

$$
x(n)e^{j\omega_0n}
\leftrightarrow
X(e^{j(\omega-\omega_0)}).
$$

### Product

Use frequency convolution:

$$
x_1(n)x_2(n)
\leftrightarrow
\frac1{2\pi}
X_1*_{\rm periodic}X_2.
$$

### Exam strategy

For Example 5.9-type questions:

1. Identify the base transform.
2. Identify the operation applied to the sequence.
3. Select exactly one DTFT property.
4. Apply the property.
5. Simplify only after the transform relation is established.

---

# 23. EXAMPLE 5.10 — INVERSE FOURIER TRANSFORM OF A FIRST-ORDER RECURSIVE FILTER

Given

$$
H(e^{j\omega})
=
(1-ae^{-j\omega})^{-1}.
$$

Expand using the geometric-series identity:

$$
\frac1{1-r}
=
1+r+r^2+r^3+\cdots.
$$

Therefore,

$$
H(e^{j\omega})
=
1+ae^{-j\omega}+a^2e^{-j2\omega}
+a^3e^{-j3\omega}+\cdots.
$$

Compare with

$$
H(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}h(n)e^{-j\omega n}.
$$

Thus

$$
h(0)=1,\quad
h(1)=a,\quad
h(2)=a^2,\ldots
$$

Therefore,

$$
\boxed{
h(n)=a^n u(n)
}
$$

This is the impulse response of the causal first-order recursive filter.

---

# 24. EXAMPLE 5.11 — OUTPUT SEQUENCE FROM OUTPUT SPECTRUM

Given an output spectrum $Y(e^{j\omega})$, obtain $y(n)$ by inverse DTFT.

The book uses known transform pairs and the **time-shifting property** to simplify the inverse transform.

The general procedure is:

$$
Y(e^{j\omega})
\longrightarrow
\text{rewrite as known transforms}
$$

then

$$
Y(e^{j\omega})
\longrightarrow
y(n).
$$

If a factor $e^{-j\omega m}$ appears,

$$
e^{-j\omega m}X(e^{j\omega})
$$

corresponds to

$$
x(n-m).
$$

### Exam shortcut

Do **not** immediately integrate if the spectrum can be recognized as shifted versions of standard DTFT pairs.

---

# 25. EXAMPLE 5.12 — RESPONSE OF AN LTI SYSTEM USING DTFT

Given

$$
h(n)=\{1,2,1,-2\}
$$

and

$$
x(n)=\{1,3,2,1\}.
$$

For an LTI system,

$$
y(n)=x(n)*h(n).
$$

Using DTFT,

$$
Y(e^{j\omega})
=
X(e^{j\omega})H(e^{j\omega}).
$$

For the finite input,

$$
X(e^{j\omega})
=
1+3e^{-j\omega}
+2e^{-j2\omega}
+e^{-j3\omega}.
$$

For the impulse response,

$$
H(e^{j\omega})
=
1+2e^{-j\omega}
+e^{-j2\omega}
-2e^{-j3\omega}.
$$

Multiply:

$$
Y(e^{j\omega})
=
(1+3e^{-j\omega}+2e^{-j2\omega}+e^{-j3\omega})
(1+2e^{-j\omega}+e^{-j2\omega}-2e^{-j3\omega}).
$$

Collect equal powers of $e^{-j\omega}$:

$$
Y(e^{j\omega})
=
1+5e^{-j\omega}
+9e^{-j2\omega}
+6e^{-j3\omega}
+2e^{-j4\omega}
+3e^{-j5\omega}
-2e^{-j6\omega}.
$$

Hence

$$
\boxed{
y(n)=
\{1,5,9,6,2,3,-2\}
}
$$

for the sequence indexing used in the example.

---

# 26. EXAMPLE 5.13 — CONVOLUTION USING FOURIER TRANSFORM

For

$$
x_1(n)=\left(\frac12\right)^n u(n)
$$

and

$$
x_2(n)=\left(\frac13\right)^n u(n),
$$

their DTFTs are

$$
X_1(e^{j\omega})
=
\frac1{1-\frac12e^{-j\omega}}
$$

and

$$
X_2(e^{j\omega})
=
\frac1{1-\frac13e^{-j\omega}}.
$$

By the convolution property,

$$
\mathcal F\{x_1*x_2\}
=
X_1X_2.
$$

Therefore,

$$
Y(e^{j\omega})
=
\frac1{
(1-\frac12e^{-j\omega})
(1-\frac13e^{-j\omega})
}.
$$

Use partial fractions:

$$
\frac1{
(1-\frac12z^{-1})(1-\frac13z^{-1})
}
=
\frac{A}{1-\frac12z^{-1}}
+
\frac{B}{1-\frac13z^{-1}}.
$$

Solving,

$$
A=3,\qquad B=-2.
$$

Hence

$$
Y(e^{j\omega})
=
\frac3{1-\frac12e^{-j\omega}}
-
\frac2{1-\frac13e^{-j\omega}}.
$$

Taking inverse DTFT,

$$
\boxed{
y(n)
=
3\left(\frac12\right)^n u(n)
-
2\left(\frac13\right)^n u(n)
}
$$

---

# 27. EXAMPLE 5.14 — LTI RESPONSE USING FOURIER TRANSFORM

For the LTI system

$$
h(n)=\left(\frac12\right)^n u(n-1)
$$

and

$$
x(n)=\left(\frac34\right)^n u(n),
$$

the method is:

### Step 1 — Transform the input

$$
X(e^{j\omega})
=
\frac1{1-\frac34e^{-j\omega}}.
$$

### Step 2 — Transform the impulse response

Use the shift of the right-sided exponential:

$$
H(e^{j\omega})
=
\frac{\frac12e^{-j\omega}}
{1-\frac12e^{-j\omega}}.
$$

### Step 3 — Multiply

$$
Y(e^{j\omega})
=
X(e^{j\omega})H(e^{j\omega}).
$$

### Step 4 — Partial fractions

Resolve the product into standard first-order terms.

### Step 5 — Inverse transform

Use

$$
\frac1{1-ae^{-j\omega}}
\leftrightarrow
a^nu(n).
$$

This gives the output sequence.

---

# 28. TRANSFER FUNCTION

For an LTI discrete-time system,

$$
y(n)=x(n)*h(n).
$$

Taking DTFT,

$$
Y(e^{j\omega})
=
X(e^{j\omega})H(e^{j\omega}).
$$

Therefore,

$$
\boxed{
H(e^{j\omega})
=
\frac{Y(e^{j\omega})}
{X(e^{j\omega})}
}
$$

The transfer function in the frequency domain is therefore the ratio of output spectrum to input spectrum.

It is also the Fourier transform of the impulse response:

$$
\boxed{
H(e^{j\omega})=\mathcal F\{h(n)\}
}
$$

---

# 29. COMPLEX-EXPONENTIAL INTERPRETATION OF FREQUENCY RESPONSE

Let the input be

$$
x(n)=e^{j\omega n}.
$$

The output is

$$
y(n)
=
\sum_kh(k)x(n-k).
$$

Substitute the input:

$$
y(n)
=
\sum_kh(k)e^{j\omega(n-k)}
$$

$$
=
e^{j\omega n}
\sum_kh(k)e^{-j\omega k}.
$$

The summation is exactly $H(e^{j\omega})$. Hence

$$
\boxed{
y(n)=H(e^{j\omega})e^{j\omega n}
}
$$

This shows that a complex exponential is an eigenfunction of an LTI system.

---

# 30. FREQUENCY RESPONSE OF A DISCRETE-TIME SYSTEM

The frequency response tells us how an LTI system changes the:

- amplitude
- phase

of each frequency component.

Write

$$
\boxed{
H(e^{j\omega})
=
|H(e^{j\omega})|
e^{j\theta(\omega)}
}
$$

where

$$
|H(e^{j\omega})|
$$

is the **magnitude response**, and

$$
\theta(\omega)
$$

is the **phase response**.

If

$$
x(n)=e^{j\omega n},
$$

then

$$
y(n)
=
|H(e^{j\omega})|
e^{j[\omega n+\theta(\omega)]}.
$$

Therefore:

- amplitude is multiplied by $|H(e^{j\omega})|$,
- phase is shifted by $\theta(\omega)$.

---

# 31. PROPERTIES OF FREQUENCY RESPONSE

For a real impulse response $h(n)$, the book lists the following properties:

1. $H(e^{j\omega})$ is defined over a continuum of frequency values.
2. It is periodic with period $2\pi$.
3. The magnitude response is even:

$$
\boxed{
|H(e^{-j\omega})|=|H(e^{j\omega})|
}
$$

4. The phase response has odd symmetry, subject to phase wrapping:

$$
\boxed{
\theta(-\omega)=-\theta(\omega)
}
$$

---

# 32. EXAMPLE 5.15 — OBTAIN A DIFFERENCE EQUATION FROM $H(e^{j\omega})$

Given a frequency response of the form

$$
H(e^{j\omega})
=
\frac{
1+3e^{-j\omega}+e^{-j2\omega}
}{
1-\frac13e^{-j\omega}
+\frac16e^{-j2\omega}
}.
$$

Since

$$
H(e^{j\omega})
=
\frac{Y(e^{j\omega})}{X(e^{j\omega})},
$$

we have

$$
Y(e^{j\omega})
\left(
1-\frac13e^{-j\omega}
+\frac16e^{-j2\omega}
\right)
=
X(e^{j\omega})
\left(
1+3e^{-j\omega}+e^{-j2\omega}
\right).
$$

Taking inverse DTFT term by term,

$$
\boxed{
y(n)
-\frac13y(n-1)
+\frac16y(n-2)
=
x(n)+3x(n-1)+x(n-2)
}
$$

### Exam method

**Cross multiply first**, then replace

$$
e^{-j\omega k}
$$

by a delay of $k$ samples.

---

# 33. EXAMPLE 5.16 — FREQUENCY RESPONSE OF CAUSAL SYSTEMS

For a difference equation, take the DTFT of both sides.

For example, if

$$
y(n)-ay(n-1)-by(n-2)
=
x(n)+cx(n-1),
$$

then

$$
Y(e^{j\omega})
\left[
1-ae^{-j\omega}-be^{-j2\omega}
\right]
=
X(e^{j\omega})
\left[
1+ce^{-j\omega}
\right].
$$

Therefore,

$$
\boxed{
H(e^{j\omega})
=
\frac{
1+ce^{-j\omega}
}{
1-ae^{-j\omega}-be^{-j2\omega}
}
}
$$

The two systems in Example 5.16 are handled exactly this way.

---

# 34. EXAMPLE 5.17 — MAGNITUDE AND PHASE RESPONSE

Given

$$
y(n)-5y(n-1)
=
x(n)-4x(n-1).
$$

Taking DTFT:

$$
Y(e^{j\omega})
-
5e^{-j\omega}Y(e^{j\omega})
=
X(e^{j\omega})
-
4e^{-j\omega}X(e^{j\omega}).
$$

Therefore,

$$
H(e^{j\omega})
=
\frac{1-4e^{-j\omega}}
{1-5e^{-j\omega}}.
$$

Write

$$
e^{-j\omega}=\cos\omega-j\sin\omega.
$$

Then

$$
H(e^{j\omega})
=
\frac{
(1-4\cos\omega)+j4\sin\omega
}{
(1-5\cos\omega)+j5\sin\omega
}.
$$

The magnitude is

$$
\boxed{
|H(e^{j\omega})|
=
\frac{
\sqrt{(1-4\cos\omega)^2+(4\sin\omega)^2}
}{
\sqrt{(1-5\cos\omega)^2+(5\sin\omega)^2}
}
}
$$

and the phase is obtained from numerator phase minus denominator phase:

$$
\boxed{
\theta(\omega)
=
\tan^{-1}
\frac{4\sin\omega}{1-4\cos\omega}
-
\tan^{-1}
\frac{5\sin\omega}{1-5\cos\omega}
}
$$

with proper quadrant handling.

---

# 35. EXAMPLE 5.18 — MAGNITUDE AND PHASE FROM A DIFFERENCE EQUATION

Given

$$
y(n)=x(n)+2x(n-1)+x(n-2),
$$

take DTFT:

$$
Y(e^{j\omega})
=
X(e^{j\omega})
\left[
1+2e^{-j\omega}+e^{-j2\omega}
\right].
$$

Hence

$$
\boxed{
H(e^{j\omega})
=
1+2e^{-j\omega}+e^{-j2\omega}
}
$$

Factor:

$$
H(e^{j\omega})
=
e^{-j\omega}
\left(
e^{j\omega}+2+e^{-j\omega}
\right).
$$

Since

$$
e^{j\omega}+e^{-j\omega}=2\cos\omega,
$$

$$
H(e^{j\omega})
=
2e^{-j\omega}(1+\cos\omega).
$$

Therefore,

$$
\boxed{
|H(e^{j\omega})|
=
2|1+\cos\omega|
}
$$

and, in the range where $1+\cos\omega\ge0$,

$$
\boxed{
\theta(\omega)=-\omega
}
$$

apart from phase discontinuities at zeros.

---

# 36. EXAMPLE 5.19 — $N$-POINT MOVING-AVERAGE/RECTANGULAR IMPULSE RESPONSE

For

$$
h(n)=
\begin{cases}
1,&0\le n\le N-1,\\
0,&\text{otherwise},
\end{cases}
$$

the frequency response is

$$
H(e^{j\omega})
=
\sum_{n=0}^{N-1}e^{-j\omega n}.
$$

Using the finite geometric-series result,

$$
\boxed{
H(e^{j\omega})
=
\frac{1-e^{-jN\omega}}
{1-e^{-j\omega}}
}
$$

Multiply numerator and denominator by suitable exponential factors:

$$
H(e^{j\omega})
=
e^{-j(N-1)\omega/2}
\frac{
\sin(N\omega/2)
}{
\sin(\omega/2)
}.
$$

Hence

$$
\boxed{
|H(e^{j\omega})|
=
\left|
\frac{\sin(N\omega/2)}
{\sin(\omega/2)}
\right|
}
$$

and the linear phase factor contributes

$$
\boxed{
\theta(\omega)
=
-\frac{(N-1)\omega}{2}
}
$$

between phase jumps caused by sign changes of the sine ratio.

---

# 37. EXAMPLE 5.20 — $h(n)=0.6^n u(n)$

Given

$$
h(n)=0.6^n u(n),
$$

the frequency response is

$$
H(e^{j\omega})
=
\frac1{1-0.6e^{-j\omega}}.
$$

Multiply numerator and denominator by the complex conjugate:

$$
H(e^{j\omega})
=
\frac{
1-0.6e^{j\omega}
}{
(1-0.6e^{-j\omega})(1-0.6e^{j\omega})
}.
$$

Since

$$
e^{j\omega}=\cos\omega+j\sin\omega,
$$

the denominator becomes

$$
1-1.2\cos\omega+0.36
=
1.36-1.2\cos\omega.
$$

Therefore,

$$
\boxed{
H(e^{j\omega})
=
\frac{
1-0.6\cos\omega-j0.6\sin\omega
}{
1.36-1.2\cos\omega
}
}
$$

Thus

$$
H_R(\omega)
=
\frac{1-0.6\cos\omega}
{1.36-1.2\cos\omega}
$$

and

$$
H_I(\omega)
=
\frac{-0.6\sin\omega}
{1.36-1.2\cos\omega}.
$$

Magnitude:

$$
\boxed{
|H(e^{j\omega})|
=
\frac1{\sqrt{1.36-1.2\cos\omega}}
}
$$

Phase:

$$
\boxed{
\theta(\omega)
=
\tan^{-1}
\left(
\frac{-0.6\sin\omega}
{1-0.6\cos\omega}
\right)
}
$$

---

# 38. EXAMPLE 5.21 — ALL-PASS CONDITION

Given the causal LTI system

$$
y(n)-ay(n-1)
=
bx(n)+x(n-1),
$$

where $|a|<1$, choose $b$ so that

$$
|H(e^{j\omega})|=1
$$

for all $\omega$.

Taking DTFT:

$$
Y(e^{j\omega})
-
ae^{-j\omega}Y(e^{j\omega})
=
bX(e^{j\omega})
+
e^{-j\omega}X(e^{j\omega}).
$$

Hence

$$
H(e^{j\omega})
=
\frac{b+e^{-j\omega}}
{1-ae^{-j\omega}}.
$$

For the magnitude to be independent of frequency, equate the frequency-dependent terms in

$$
|H(e^{j\omega})|^2.
$$

The book obtains

$$
\boxed{
b=\frac1a
}
$$

under its stated assumptions.

---

# 39. EXAMPLE 5.22 — FIND FREQUENCY RESPONSE AND DIFFERENCE EQUATION

Given the causal and stable system relation involving

$$
x(n)=\left(\frac45\right)^n u(n)
$$

and

$$
y(n)=n\left(\frac45\right)^n u(n),
$$

the method is based on differentiation in the frequency domain.

For the input,

$$
X(e^{j\omega})
=
\frac1{1-\frac45e^{-j\omega}}.
$$

For the output, use

$$
\mathcal F\{nx(n)\}
=
j\frac{dX(e^{j\omega})}{d\omega}.
$$

This produces $Y(e^{j\omega})$. Then

$$
\boxed{
H(e^{j\omega})
=
\frac{Y(e^{j\omega})}
{X(e^{j\omega})}
}
$$

and cross multiplication is used to obtain the corresponding difference equation.

The book's resulting relation is

$$
\boxed{
y(n)-\frac45y(n-1)
=
\frac45x(n)
}
$$

for the system represented in the example.

---

# 40. IDEAL DIGITAL FILTERS

The chapter considers four ideal filters:

1. Ideal low-pass filter
2. Ideal high-pass filter
3. Ideal band-pass filter
4. Ideal band-stop filter

The impulse response is obtained from

$$
\boxed{
h(n)
=
\frac{1}{2\pi}
\int_{-\pi}^{\pi}
H(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

---

# 41. EXAMPLE 5.23 — IDEAL LOW-PASS FILTER

For an ideal low-pass filter,

$$
H(e^{j\omega})
=
\begin{cases}
1,&|\omega|\le\omega_c,\\
0,&\text{otherwise}.
\end{cases}
$$

Then

$$
h(n)
=
\frac1{2\pi}
\int_{-\omega_c}^{\omega_c}
e^{j\omega n}\,d\omega.
$$

For $n\ne0$,

$$
h(n)
=
\frac1{2\pi}
\left[
\frac{e^{j\omega n}}{jn}
\right]_{-\omega_c}^{\omega_c}
$$

$$
=
\frac{\sin(\omega_c n)}{\pi n}.
$$

Thus

$$
\boxed{
h(n)=\frac{\sin(\omega_c n)}{\pi n},
\qquad n\ne0
}
$$

At $n=0$, use the limiting value:

$$
\boxed{
h(0)=\frac{\omega_c}{\pi}
}
$$

So the complete form is

$$
\boxed{
h(n)=
\begin{cases}
\dfrac{\sin(\omega_c n)}{\pi n},
&n\ne0,\\[6pt]
\dfrac{\omega_c}{\pi},
&n=0.
\end{cases}
}
$$

---

# 42. IDEAL HIGH-PASS FILTER

For an ideal high-pass response,

$$
H_{HP}(e^{j\omega})
=
1-H_{LP}(e^{j\omega})
$$

for the complementary cutoff specification.

Therefore,

$$
\boxed{
h_{HP}(n)
=
\delta(n)-h_{LP}(n)
}
$$

This is the quickest exam method.

---

# 43. IDEAL BAND-PASS FILTER

For an ideal band-pass filter with passband

$$
\omega_{c1}\le|\omega|\le\omega_{c2},
$$

the inverse DTFT gives

$$
h(n)
=
\frac{1}{2\pi}
\left[
\int_{\omega_{c1}}^{\omega_{c2}}
e^{j\omega n}\,d\omega
+
\int_{-\omega_{c2}}^{-\omega_{c1}}
e^{j\omega n}\,d\omega
\right].
$$

For $n\ne0$,

$$
\boxed{
h(n)
=
\frac{
\sin(\omega_{c2}n)
-
\sin(\omega_{c1}n)
}{
\pi n
}
}
$$

The value at $n=0$ is

$$
\boxed{
h(0)
=
\frac{\omega_{c2}-\omega_{c1}}{\pi}
}
$$

---

# 44. IDEAL BAND-STOP FILTER

A band-stop filter is the complement of the corresponding band-pass filter:

$$
H_{BS}(e^{j\omega})
=
1-H_{BP}(e^{j\omega}).
$$

Therefore,

$$
\boxed{
h_{BS}(n)
=
\delta(n)-h_{BP}(n)
}
$$

This complementary-filter relationship is an important shortcut.

---

# 45. SHORT QUESTIONS — EXAM READY

## Q1. Define DTFT.

$$
\boxed{
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n}
}
$$

---

## Q2. Define inverse DTFT.

$$
\boxed{
x(n)
=
\frac1{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

---

## Q3. When does DTFT exist?

The sufficient/standard condition stated in the chapter is absolute summability:

$$
\boxed{
\sum_n|x(n)|<\infty
}
$$

---

## Q4. Why is DTFT called a spectrum?

Because the Fourier transform decomposes $x(n)$ into its frequency components.

---

## Q5. What is the relation between DTFT and Z-transform?

$$
\boxed{
X(e^{j\omega})
=
X(z)\big|_{z=e^{j\omega}}
}
$$

DTFT is the Z-transform evaluated along the unit circle.

---

## Q6. What is the period of DTFT?

$$
\boxed{2\pi}
$$

---

## Q7. What is the frequency response of an LTI system?

The Fourier transform of the impulse response:

$$
\boxed{
H(e^{j\omega})=\mathcal F\{h(n)\}
}
$$

---

## Q8. What is the transfer function?

$$
\boxed{
H(e^{j\omega})
=
\frac{Y(e^{j\omega})}{X(e^{j\omega})}
}
$$

---

## Q9. What are the two components of frequency response?

1. Magnitude response $|H(e^{j\omega})|$
2. Phase response $\theta(\omega)$

---

## Q10. State Parseval's theorem.

$$
\boxed{
\sum_n|x(n)|^2
=
\frac1{2\pi}
\int_{-\pi}^{\pi}
|X(e^{j\omega})|^2\,d\omega
}
$$

---

# 46. FILL-IN-THE-BLANK ANSWERS

1. The DTFT of $x(n)$ is defined as

$$
\boxed{\sum_{n=-\infty}^{\infty}x(n)e^{-j\omega n}}
$$

2. DTFT exists when

$$
\boxed{\sum_n|x(n)|<\infty}
$$

3. The FT of a discrete-time signal is called

$$
\boxed{\text{DTFT}}
$$

4. The FT of a discrete-time signal is periodic with period

$$
\boxed{2\pi}
$$

5. The analog FT involves

$$
\boxed{\text{integration}}
$$

while DTFT involves

$$
\boxed{\text{summation}}
$$

6. The analog FT extends over

$$
\boxed{-\infty\text{ to }\infty}
$$

while DTFT is unique over

$$
\boxed{-\pi\text{ to }\pi}
$$

or equivalently $0$ to $2\pi$.

7. Inverse Fourier transform:

$$
\boxed{
x(n)=\frac1{2\pi}\int_{-\pi}^{\pi}X(e^{j\omega})e^{j\omega n}d\omega
}
$$

8. The FT of $x(n)$ is the Z-transform evaluated along the

$$
\boxed{\text{unit circle}}
$$

9. Relation:

$$
\boxed{
X(e^{j\omega})=X(z)|_{z=e^{j\omega}}
}
$$

10. The FT of a discrete and aperiodic sequence is

$$
\boxed{\text{periodic}}
$$

11. Frequency response is the FT of the

$$
\boxed{\text{impulse response}}
$$

12. The impulse response is the inverse Fourier transform of the

$$
\boxed{\text{frequency response}}
$$

13. The ratio of output FT to input FT is called the

$$
\boxed{\text{transfer function}}
$$

or frequency-domain system function.

14. Frequency response is a

$$
\boxed{\text{complex}}
$$

function of $\omega$.

15. Its two components are

$$
\boxed{\text{magnitude and phase}}
$$

16. If $h(n)$ is real, magnitude is

$$
\boxed{\text{even/symmetric}}
$$

and phase is

$$
\boxed{\text{odd/antisymmetric}}
$$

17. For a complex frequency response, the

$$
\boxed{\text{real}}
$$

part is symmetric and the

$$
\boxed{\text{imaginary}}
$$

part is antisymmetric for a real sequence.

18. The Fourier transform of convolution is equal to the

$$
\boxed{\text{product}}
$$

$X(e^{j\omega})H(e^{j\omega})$.

---

# 47. OBJECTIVE-TYPE QUESTIONS — ANSWERS

### 1. DTFT definition

Correct choice:

$$
\boxed{
\sum_{n=-\infty}^{\infty}x(n)e^{-j\omega n}
}
$$

### 2. Inverse DTFT

Correct expression:

$$
\boxed{
\frac1{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}\,d\omega
}
$$

### 3. Period of DTFT

$$
\boxed{2\pi}
$$

### 4. DTFT–Z-transform relation

$$
\boxed{
X(e^{j\omega})=X(z)|_{z=e^{j\omega}}
}
$$

### 5. Frequency response

FT of the

$$
\boxed{\text{impulse response}}
$$

### 6. FT of convolution

$$
\boxed{X(e^{j\omega})H(e^{j\omega})}
$$

### 7. Analog FT frequency range

$$
\boxed{-\infty\text{ to }\infty}
$$

### 8. Unique DTFT range

$$
\boxed{-\pi\text{ to }\pi}
$$

### 9. FT of $\delta(n)$

$$
\boxed{1}
$$

### 10. FT of $u(n)$

Using the geometric-series expression used in the chapter:

$$
\boxed{
\frac1{1-e^{-j\omega}}
}
$$

### 11. FT of $a^nu(n)$

$$
\boxed{
\frac1{1-ae^{-j\omega}}
}
$$

### 12. FT of $-a^nu(-n-1)$

$$
\boxed{
\frac1{1-ae^{-j\omega}}
}
$$

with the corresponding left-sided convergence condition.

### 13. Shifted exponential sequence

Use the time-shifting rule carefully:

$$
\boxed{
x(n-m)\leftrightarrow e^{-j\omega m}X(e^{j\omega})
}
$$

### 14. FT of $2^nu(n)$

Since $2>1$, the sequence is not absolutely summable.

$$
\boxed{\text{DTFT does not exist}}
$$

### 15. FT of $\delta(n+2)-\delta(n-2)$

$$
e^{j2\omega}-e^{-j2\omega}
=
\boxed{2j\sin(2\omega)}
$$

---

# 48. CHAPTER PROBLEMS — WHAT TO PRACTICE

The chapter's problem set includes the following important patterns.

## Problem Type 1 — Direct DTFT

Practice:

- finite sequences
- shifted unit steps
- sums of right/left-sided exponentials
- $a^n\cos(\omega_0n)$
- $a^{|n|}$

### Core method

$$
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n}
$$

or use known pairs.

---

## Problem Type 2 — Property-based DTFT

Practice:

- $(1/2)^{|n-3|}$
- shifted exponentials
- $nu(-n)$
- $e^{j2n}u(n)$
- polynomial/exponential sequences

### Core method

Recognize the transformation and apply one property.

---

## Problem Type 3 — LTI convolution

Given $x(n)$ and $h(n)$:

$$
\boxed{
y(n)=x(n)*h(n)
}
$$

For DTFT method:

$$
\boxed{
Y= XH
}
$$

then inverse transform.

---

## Problem Type 4 — Frequency response

Given a sequence $x(n)$ interpreted as an impulse response,

$$
\boxed{
H(e^{j\omega})
=
\sum_nh(n)e^{-j\omega n}
}
$$

Then obtain:

$$
|H(e^{j\omega})|
$$

and

$$
\angle H(e^{j\omega}).
$$

---

## Problem Type 5 — Difference equation

Given a difference equation:

1. Take DTFT.
2. Replace delays by $e^{-j\omega k}$.
3. Collect $Y(e^{j\omega})$.
4. Collect $X(e^{j\omega})$.
5. Form

$$
\boxed{H=Y/X}.
$$

Then calculate magnitude and phase.

---

# 49. HIGH-VALUE EXAM DERIVATIONS

The following are especially important to prepare as full derivations.

## Derivation 1 — Time shifting

$$
x(n-m)
\leftrightarrow
e^{-j\omega m}X(e^{j\omega})
$$

## Derivation 2 — Frequency shifting

$$
x(n)e^{j\omega_0n}
\leftrightarrow
X(e^{j(\omega-\omega_0)})
$$

## Derivation 3 — Time reversal

$$
x(-n)
\leftrightarrow
X(e^{-j\omega})
$$

## Derivation 4 — Differentiation

$$
nx(n)
\leftrightarrow
j\frac{dX}{d\omega}
$$

## Derivation 5 — Time convolution

$$
x_1*x_2
\leftrightarrow
X_1X_2
$$

## Derivation 6 — Frequency convolution

$$
x_1x_2
\leftrightarrow
\frac1{2\pi}X_1*_{\rm periodic}X_2
$$

## Derivation 7 — Modulation

$$
x(n)\cos\omega_0n
\leftrightarrow
\frac12[X(\omega-\omega_0)+X(\omega+\omega_0)]
$$

## Derivation 8 — Parseval

$$
\sum_n|x(n)|^2
=
\frac1{2\pi}
\int_{-\pi}^{\pi}|X(e^{j\omega})|^2d\omega
$$

---

# 50. MASTER EXAM DECISION TREE

## If the question says “Find DTFT”

Start with

$$
\boxed{
X(e^{j\omega})
=
\sum_nx(n)e^{-j\omega n}
}
$$

Then ask:

### Is the sequence finite?

Yes → write the finite sum directly.

### Is it a standard exponential?

Use

$$
a^nu(n)
\leftrightarrow
\frac1{1-ae^{-j\omega}}.
$$

### Is it shifted?

Use

$$
x(n-m)
\leftrightarrow
e^{-j\omega m}X(e^{j\omega}).
$$

### Is it sinusoidal?

Convert sine/cosine to exponentials.

### Is it $a^{|n|}$?

Split into right-sided and left-sided parts.

---

# 51. IF THE QUESTION SAYS “FIND INVERSE DTFT”

Try these in order:

1. **Coefficient comparison**
2. **Known transform pair**
3. **Time-shifting property**
4. **Frequency-shifting property**
5. **Partial-fraction-type decomposition**
6. **Direct inverse integral**

Use the integral only when the spectrum is not easily recognized.

---

# 52. IF THE QUESTION GIVES A DIFFERENCE EQUATION

Use

$$
\boxed{
e^{-j\omega k}
\longleftrightarrow
\text{delay by }k
}
$$

Then:

$$
Y(e^{j\omega})=\cdots X(e^{j\omega})
$$

and finally

$$
\boxed{
H(e^{j\omega})=\frac{Y(e^{j\omega})}{X(e^{j\omega})}
}
$$

---

# 53. IF THE QUESTION ASKS MAGNITUDE AND PHASE

First find

$$
H(e^{j\omega}).
$$

Then write

$$
H=H_R+jH_I.
$$

Magnitude:

$$
\boxed{
|H|=\sqrt{H_R^2+H_I^2}
}
$$

Phase:

$$
\boxed{
\angle H
=
\tan^{-1}\left(\frac{H_I}{H_R}\right)
}
$$

For an actual exam solution, pay attention to the quadrant of $H_R+jH_I$.

---

# 54. MASTER FORMULA SHEET

## DTFT

$$
\boxed{
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n}
}
$$

## Inverse DTFT

$$
\boxed{
x(n)
=
\frac1{2\pi}
\int_{-\pi}^{\pi}
X(e^{j\omega})e^{j\omega n}d\omega
}
$$

## Existence

$$
\boxed{
\sum_n|x(n)|<\infty
}
$$

## Periodicity

$$
\boxed{
X(\omega+2\pi)=X(\omega)
}
$$

## Z-transform relation

$$
\boxed{
X(e^{j\omega})=X(z)|_{z=e^{j\omega}}
}
$$

## Shift

$$
\boxed{
x(n-m)\leftrightarrow e^{-j\omega m}X(e^{j\omega})
}
$$

## Frequency shift

$$
\boxed{
x(n)e^{j\omega_0n}
\leftrightarrow
X(e^{j(\omega-\omega_0)})
}
$$

## Reversal

$$
\boxed{
x(-n)\leftrightarrow X(e^{-j\omega})
}
$$

## Differentiation

$$
\boxed{
nx(n)\leftrightarrow j\frac{dX}{d\omega}
}
$$

## Convolution

$$
\boxed{
x_1*x_2\leftrightarrow X_1X_2
}
$$

## Multiplication

$$
\boxed{
x_1x_2
\leftrightarrow
\frac1{2\pi}X_1*_{\rm periodic}X_2
}
$$

## Correlation

$$
\boxed{
R_{x_1x_2}
\leftrightarrow
X_1(e^{j\omega})X_2(e^{-j\omega})
}
$$

## Modulation

$$
\boxed{
x(n)\cos\omega_0n
\leftrightarrow
\frac12[X(\omega-\omega_0)+X(\omega+\omega_0)]
}
$$

## Parseval

$$
\boxed{
\sum_n|x(n)|^2
=
\frac1{2\pi}
\int_{-\pi}^{\pi}|X(e^{j\omega})|^2d\omega
}
$$

## LTI transfer function

$$
\boxed{
H(e^{j\omega})=\frac{Y(e^{j\omega})}{X(e^{j\omega})}
}
$$

## Frequency response

$$
\boxed{
H(e^{j\omega})=\mathcal F\{h(n)\}
}
$$

## Polar form

$$
\boxed{
H(e^{j\omega})
=
|H(e^{j\omega})|e^{j\theta(\omega)}
}
$$

## Magnitude

$$
\boxed{
|H|=\sqrt{H_R^2+H_I^2}
}
$$

## Phase

$$
\boxed{
\theta=\tan^{-1}(H_I/H_R)
}
$$

---

# 55. MUST-MEMORIZE STANDARD PAIRS

$$
\boxed{
\delta(n)\leftrightarrow1
}
$$

$$
\boxed{
\delta(n-m)\leftrightarrow e^{-j\omega m}
}
$$

$$
\boxed{
a^nu(n)
\leftrightarrow
\frac1{1-ae^{-j\omega}}
}
$$

$$
\boxed{
a^{|n|}
\leftrightarrow
\frac{1-a^2}
{1-2a\cos\omega+a^2},
\qquad |a|<1
}
$$

$$
\boxed{
\left(\frac12\right)^nu(n)
\leftrightarrow
\frac1{1-\frac12e^{-j\omega}}
}
$$

$$
\boxed{
\left(\frac13\right)^nu(n)
\leftrightarrow
\frac1{1-\frac13e^{-j\omega}}
}
$$

---

# 56. COMMON EXAM MISTAKES

### Mistake 1 — Forgetting the minus sign in the DTFT exponent

Correct:

$$
e^{-j\omega n}
$$

not $e^{+j\omega n}$.

---

### Mistake 2 — Forgetting the $1/(2\pi)$ in inverse DTFT

Correct:

$$
\frac1{2\pi}\int_{-\pi}^{\pi}\cdots d\omega.
$$

---

### Mistake 3 — Confusing time shift and frequency shift

Time shift:

$$
x(n-m)
\rightarrow
e^{-j\omega m}X(e^{j\omega}).
$$

Frequency shift:

$$
x(n)e^{j\omega_0n}
\rightarrow
X(e^{j(\omega-\omega_0)}).
$$

---

### Mistake 4 — Using ordinary geometric-series formulas without checking convergence

Always check whether the sequence is right-sided or left-sided and whether the required magnitude condition is satisfied.

---

### Mistake 5 — Confusing convolution and multiplication

$$
\boxed{
\text{time convolution}\rightarrow\text{frequency multiplication}
}
$$

but

$$
\boxed{
\text{time multiplication}\rightarrow\text{frequency convolution}
}
$$

---

### Mistake 6 — Forgetting DTFT periodicity

$$
\boxed{
X(\omega+2\pi)=X(\omega)
}
$$

---

### Mistake 7 — Treating $H(e^{j\omega})$ as only a magnitude

It is complex:

$$
H=|H|e^{j\theta}.
$$

---

# 57. LAST-MINUTE REVISION — 15 LINES

1. DTFT converts a discrete-time sequence to frequency domain.
2. DTFT is a summation.
3. Inverse DTFT is an integral.
4. DTFT is periodic with period $2\pi$.
5. One unique frequency interval is $[-\pi,\pi]$.
6. Absolute summability is the stated existence condition.
7. DTFT equals the Z-transform on the unit circle.
8. Time delay produces a phase factor $e^{-j\omega m}$.
9. Frequency shifting moves the spectrum.
10. Time convolution becomes multiplication.
11. Time multiplication becomes periodic frequency convolution.
12. Correlation becomes a spectral product involving $X_2(e^{-j\omega})$.
13. Parseval connects time-domain and frequency-domain energy.
14. $H=Y/X$ for an LTI system.
15. $H$ gives both magnitude and phase response.

---

# 58. SOURCE-BASED REVIEW QUESTIONS

The chapter asks students to prepare:

1. State and prove the time-shifting and frequency-shifting properties of DTFT.
2. State and prove the time-reversal and differentiation-in-frequency-domain properties.
3. State and prove the time-convolution and frequency-convolution properties.
4. State and prove the modulation theorem and Parseval's theorem.
5. Compare the Fourier transforms of discrete-time and analog signals.
6. State the applications of DTFT.

These should be prepared as **full derivations**, not only as formulas.

---

# 59. MATLAB PROGRAMS IN THE CHAPTER

The chapter includes MATLAB programs for:

- Fourier transform and inverse Fourier transform of a given expression.
- Fourier transform of a signal.
- Evaluation and plotting of the DTFT of a transfer function.
- Plotting real and imaginary parts.
- Plotting magnitude and phase spectra.
- Demonstrating time shifting, frequency shifting, multiplication, and convolution.

A representative frequency-response workflow uses `freqz`:

```matlab
clc; clear all; close all;

w = -2*pi:8*pi/511:2*pi;

num = [1 2];
den = [1 -0.2];

h = freqz(num, den, w);

subplot(2,1,1);
plot(w/pi, real(h));
xlabel('Normalized frequency');
ylabel('Amplitude');
title('Real part of the transfer function');

subplot(2,1,2);
plot(w/pi, imag(h));
xlabel('Normalized frequency');
ylabel('Amplitude');
title('Imaginary part of the transfer function');
```

For the chapter's MATLAB questions, remember:

$$
\boxed{
H(e^{j\omega})
\text{ is evaluated over a frequency vector}
}
$$

and then the real part, imaginary part, magnitude, and phase can be plotted separately.

---

# 60. FINAL EXAM CHECKLIST

Before the exam, make sure you can do all of these without notes:

- [ ] Write the DTFT definition.
- [ ] Write the inverse DTFT.
- [ ] State the existence condition.
- [ ] Explain why DTFT is periodic.
- [ ] Explain the DTFT–Z-transform relation.
- [ ] Find DTFT of finite sequences.
- [ ] Find DTFT of shifted impulses.
- [ ] Find DTFT of right-sided exponentials.
- [ ] Handle left-sided exponentials.
- [ ] Handle $a^{|n|}$.
- [ ] Use sine/cosine exponential forms.
- [ ] Find inverse DTFT by coefficient comparison.
- [ ] Use time shifting.
- [ ] Use frequency shifting.
- [ ] Use time reversal.
- [ ] Use differentiation.
- [ ] Use convolution theorem.
- [ ] Use frequency convolution.
- [ ] Use correlation theorem.
- [ ] Use modulation theorem.
- [ ] Use Parseval.
- [ ] Derive $H(e^{j\omega})$ from a difference equation.
- [ ] Find magnitude response.
- [ ] Find phase response.
- [ ] Derive ideal-filter impulse responses.
- [ ] Solve LTI convolution problems using DTFT.
- [ ] Recognize which standard transform pair makes an inverse problem easiest.

---

# END OF CHAPTER 5

**Core idea to remember:**

$$
\boxed{
\text{DTFT}
\quad\Longrightarrow\quad
\text{time-domain convolution becomes frequency-domain multiplication}
}
$$

and

$$
\boxed{
X(e^{j\omega})
=
X(z)\big|_{z=e^{j\omega}}
}
$$

and for an LTI system,

$$
\boxed{
Y(e^{j\omega})
=
X(e^{j\omega})H(e^{j\omega})
}
$$

These three relations form the backbone of Chapter 5.
