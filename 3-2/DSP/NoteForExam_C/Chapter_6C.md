---
title: "Chapter 6 — Discrete Fourier Series (DFS) and Discrete Fourier Transform (DFT)"
source: "Digital Signal Processing — A. Anand Kumar"
printed_pages: "412–478"
style: "Full theory + derivations + worked examples + exam questions + MATLAB"
---

# Chapter 6 — Discrete Fourier Series (DFS) and Discrete Fourier Transform (DFT)

> **Source boundary:** These notes are based on Chapter 6, printed pages **412–478**, of *Digital Signal Processing* by A. Anand Kumar.
>
> **Goal:** exam-ready theory, derivations, formulas, book-style worked examples, convolution methods, FFT preparation, review questions, objective questions, problems, and MATLAB.
>
> **Formatting rule:** multi-line derivations are written with aligned LaTeX so that each equality remains visually connected.

---

# 1. CHAPTER ROADMAP

Chapter 6 covers:

1. Introduction to DFS and DFT
2. Discrete Fourier Series
   - Exponential form
   - Trigonometric form
   - Relationship between the two forms
3. Properties of DFS
   - Linearity
   - Time shifting
   - Symmetry
   - Periodic convolution
4. Relation between DFT and Z-transform
5. Comparison between DTFT and DFT
6. A faster method for computing DFT values
7. Matrix formulation of DFT and IDFT
8. IDFT from the matrix form
9. Using DFT to find IDFT
10. Properties of DFT
11. Linear convolution using DFT
12. Circular convolution using DFT
13. Sectioned convolution
   - Overlap-add
   - Overlap-save
14. End-of-chapter questions and MATLAB programs

---

# 2. WHY DFS AND DFT ARE NEEDED

The DTFT of a discrete-time sequence is a **continuous function of frequency** and is periodic with period $2\pi$.

A digital computer cannot directly process a continuous frequency function as a finite set of numbers. Therefore, the frequency axis is sampled.

The textbook describes the DFT as a sampled version of the DTFT:

$$
\boxed{ X(k)=X(\omega)\bigg|_{\omega=\frac{2\pi k}{N}}, \qquad k=0,1,\ldots,N-1 }
$$

Thus, the DFT converts:

$$
\boxed{ \text{finite-duration discrete-time sequence} \longrightarrow \text{finite-length discrete-frequency sequence} }
$$

The DFT is important mainly for:

- **spectral analysis** — determining the frequency content of a signal;
- **frequency-domain filtering** — performing filtering using multiplication in the frequency domain.

---

# 3. BASIC DFT DEFINITIONS

## 3.1 Sampling frequencies

For an $N$-point DFT, the frequency samples are

$$
\boxed{ \omega_k=\frac{2\pi k}{N}, \qquad k=0,1,\ldots,N-1 }
$$

The value $k=N$ corresponds to $\omega=2\pi$, which is the same frequency point as $\omega=0$. Therefore, $k=N$ is not included.

---

## 3.2 N-point DFT

For a finite-duration sequence $x(n)$ of length $L$, choose

$$
\boxed{N\ge L}
$$

and define

$$
\boxed{ X(k)= \sum_{n=0}^{N-1} x(n)e^{-j\frac{2\pi}{N}nk}, \qquad k=0,1,\ldots,N-1 }
$$

Define the twiddle factor

$$
\boxed{ W_N=e^{-j\frac{2\pi}{N}} }
$$

Then

$$
\boxed{ X(k)= \sum_{n=0}^{N-1}x(n)W_N^{nk} }
$$

---

## 3.3 IDFT

The inverse DFT is

$$
\boxed{ x(n)= \frac{1}{N} \sum_{k=0}^{N-1} X(k)e^{j\frac{2\pi}{N}nk} }
$$

or, using the twiddle factor,

$$
\boxed{ x(n)= \frac{1}{N} \sum_{k=0}^{N-1} X(k)W_N^{-nk}, \qquad n=0,1,\ldots,N-1 }
$$

Therefore the DFT pair is

$$
\boxed{ x(n) \ \xleftrightarrow[\text{IDFT}]{\text{DFT}}\ X(k) }
$$

---

# 4. TWIDDLE FACTOR

The fundamental DFT factor is

$$
\boxed{ W_N=e^{-j2\pi/N} }
$$

Important properties:

$$
\boxed{ W_N^N=1 }
$$

$$
\boxed{ W_N^{k+N}=W_N^k }
$$

$$
\boxed{ W_N^{-k}=W_N^{N-k} }
$$

for the appropriate modulo-$N$ interpretation.

Euler's relation gives

$$
e^{-j\theta}=\cos\theta-j\sin\theta
$$

so

$$
\boxed{ W_N^k = \cos\left(\frac{2\pi k}{N}\right) -j\sin\left(\frac{2\pi k}{N}\right) }
$$

---

# 5. DISCRETE FOURIER SERIES (DFS)

## 5.1 Definition

The Fourier series representation of a **periodic discrete-time sequence** is called the **Discrete Fourier Series (DFS)**.

Let

$$
x(n)=x(n+N)
$$

where $N$ is the period.

The fundamental frequency is

$$
\boxed{ \omega_0=\frac{2\pi}{N} }
$$

A periodic sequence can be represented as a finite sum of complex exponentials whose frequencies are integer multiples of $\omega_0$.

---

# 6. EXPONENTIAL FORM OF DFS

A real periodic sequence $x(n)$ of period $N$ can be represented as

$$
\boxed{ x(n)= \sum_{k=0}^{N-1}X(k)e^{j\frac{2\pi}{N}kn} }
$$

The coefficients are

$$
\boxed{ X(k)= \frac{1}{N} \sum_{n=0}^{N-1} x(n)e^{-j\frac{2\pi}{N}kn} }
$$

Equivalently,

$$
\boxed{ X(k)= \frac{1}{N} \sum_{n=0}^{N-1} x(n)W_N^{nk} }
$$

and the synthesis equation is

$$
\boxed{ x(n)= \sum_{k=0}^{N-1} X(k)W_N^{-nk} }
$$

### Important distinction

For the DFT, the usual forward transform is

$$
X(k)=\sum x(n)W_N^{nk}
$$

while the DFS coefficient convention used in this chapter includes the factor $1/N$ in the coefficient definition.

The periodic sequence is reconstructed by summing its DFS components.

---

# 7. TRIGONOMETRIC FORM OF DFS

The complex-exponential form can be converted into sine/cosine form.

For a real sequence,

$$
\boxed{ x(n)= A_0+ \sum_{k=1}^{N/2} \left[ A(k)\cos(k\omega_0 n) + B(k)\sin(k\omega_0 n) \right] }
$$

with the exact upper limit and special Nyquist term depending on whether $N$ is even or odd.

For an even $N$, the $N/2$ term has special treatment because

$$
\sin(\pi n)=0
$$

for integer $n$.

The chapter obtains the trigonometric coefficients from the exponential DFS coefficients using conjugate pairs.

For the cosine coefficient,

$$
\boxed{ A(k)=X(k)+X^*(k) }
$$

under the chapter's coefficient convention, with the corresponding $1/N$ factors included as defined in the coefficient sequence.

The important exam idea is:

> **Pair positive- and negative-frequency DFS coefficients to obtain cosine and sine components.**

---

# 8. RELATION BETWEEN EXPONENTIAL AND TRIGONOMETRIC FORMS

Euler identities:

$$
e^{j\theta}=\cos\theta+j\sin\theta
$$

$$
e^{-j\theta}=\cos\theta-j\sin\theta
$$

Therefore,

$$
\boxed{ \cos\theta= \frac{e^{j\theta}+e^{-j\theta}}{2} }
$$

and

$$
\boxed{ \sin\theta= \frac{e^{j\theta}-e^{-j\theta}}{2j} }
$$

Thus, a pair of conjugate exponential terms can be combined into cosine and sine terms.

---

# 9. PROPERTIES OF DFS

## 9.1 Linearity

If

$$
x_1(n)\xleftrightarrow{\text{DFS}}X_1(k)
$$

and

$$
x_2(n)\xleftrightarrow{\text{DFS}}X_2(k)
$$

then

$$
\boxed{ a x_1(n)+b x_2(n) \ \xleftrightarrow{\text{DFS}}\ aX_1(k)+bX_2(k) }
$$

---

## 9.2 Time shifting

If

$$
x(n)\xleftrightarrow{\text{DFS}}X(k)
$$

then

$$
\boxed{ x(n-m) \ \xleftrightarrow{\text{DFS}}\ e^{-j\frac{2\pi}{N}mk}X(k) }
$$

or

$$
\boxed{ x(n-m) \ \xleftrightarrow{\text{DFS}}\ W_N^{mk}X(k) }
$$

Similarly,

$$
\boxed{ x(n+m) \ \xleftrightarrow{\text{DFS}}\ W_N^{-mk}X(k) }
$$

---

## 9.3 Frequency shifting

Using the periodic exponential,

$$
\boxed{ W_N^{ln}x(n) \ \xleftrightarrow{\text{DFS}}\ X(k-l) }
$$

with indices interpreted modulo $N$.

---

## 9.4 Symmetry

The textbook gives

$$
\boxed{ x^*(n) \ \xleftrightarrow{\text{DFS}}\ X^*(-k) }
$$

and

$$
\boxed{ x^*(-n) \ \xleftrightarrow{\text{DFS}}\ X^*(k) }
$$

Define the conjugate-even and conjugate-odd parts:

$$
\boxed{ x_e(n)= \frac12\left[x(n)+x^*(-n)\right] }
$$

$$
\boxed{ x_o(n)= \frac12\left[x(n)-x^*(-n)\right] }
$$

Then

$$
\boxed{ \text{DFS}\{x_e(n)\}=\Re\{X(k)\} }
$$

and

$$
\boxed{ \text{DFS}\{x_o(n)\}=j\,\Im\{X(k)\} }
$$

---

## 9.5 Periodic convolution

For two periodic sequences of period $N$,

$$
x_1(n)\xleftrightarrow{\text{DFS}}X_1(k)
$$

$$
x_2(n)\xleftrightarrow{\text{DFS}}X_2(k)
$$

define

$$
x_3(n)= \sum_{m=0}^{N-1} x_1(m)x_2(n-m)
$$

where periodic indexing is understood.

Then

$$
\boxed{ X_3(k)=X_1(k)X_2(k) }
$$

Hence,

$$
\boxed{ \text{periodic convolution in time} \longleftrightarrow \text{multiplication in frequency} }
$$

---

# 10. DFT AS SAMPLES OF DTFT

For a finite-duration sequence,

$$
X(e^{j\omega}) = \sum_{n=0}^{N-1} x(n)e^{-j\omega n}
$$

The DFT samples this DTFT at

$$
\boxed{ \omega_k=\frac{2\pi k}{N} }
$$

Therefore,

$$
\boxed{ X(k)=X(e^{j\omega})\bigg|_{\omega=2\pi k/N} }
$$

The DFT is thus a set of $N$ equally spaced frequency samples.

---

# 11. RELATION BETWEEN DFT AND Z-TRANSFORM

The $Z$-transform of an $N$-point sequence is

$$
X(z)= \sum_{n=0}^{N-1}x(n)z^{-n}
$$

Evaluate it at

$$
\boxed{ z=e^{j\frac{2\pi}{N}k} }
$$

Then

$$
\begin{aligned} X(z)\bigg|_{z=e^{j2\pi k/N}} &= \sum_{n=0}^{N-1} x(n) \left(e^{j2\pi k/N}\right)^{-n} \\ &= \sum_{n=0}^{N-1} x(n)e^{-j2\pi nk/N} \\ &= X(k) \end{aligned}
$$

Therefore,

$$
\boxed{ X(k)= X(z)\bigg|_{z=e^{j2\pi k/N}} }
$$

### Exam statement

> The $N$-point DFT of a finite-duration sequence is obtained by evaluating its $Z$-transform at $N$ equally spaced points on the unit circle.

---

# 12. EXAMPLE 6.1 — DFS OF A 4-POINT SEQUENCE

The textbook considers the 4-point sequence shown in Figure 6.1:

$$
x(n)=\{0,1,2,3\}
$$

with $N=4$.

The twiddle factors are

$$
W_4^0=1
$$

$$
W_4^1=-j
$$

$$
W_4^2=-1
$$

$$
W_4^3=j
$$

and

$$
W_4^4=1.
$$

Using the DFS/DFT coefficient calculation,

$$
X(k)=\frac14\sum_{n=0}^{3}x(n)W_4^{nk}.
$$

### For $k=0$

$$
\begin{aligned} X(0) &=\frac14[0+1+2+3]\\ &=\boxed{\frac32} \end{aligned}
$$

The textbook's unnormalized intermediate DFT values are

$$
\{6,\,-2+j2,\,-2,\,-2-j2\}.
$$

### Trigonometric form

Combining conjugate-frequency terms gives the textbook result

$$
\boxed{ x(n) = \frac32 -\cos\left(\frac{\pi n}{2}\right) -\sin\left(\frac{\pi n}{2}\right) -\frac12\cos(\pi n) }
$$

This is an important model problem for converting exponential DFS coefficients into trigonometric form.

---

# 13. EXAMPLE 6.2 — DFT OF STANDARD SEQUENCES

The textbook asks for the DFT of several standard finite-length sequences.

## (a) Unit impulse

Let

$$
x(n)=\delta(n).
$$

Then

$$
\begin{aligned} X(k) &= \sum_{n=0}^{N-1} \delta(n)e^{-j2\pi nk/N}\\ &=1. \end{aligned}
$$

Therefore,

$$
\boxed{ \delta(n)\xleftrightarrow{\text{DFT}}1 }
$$

for all $k$.

---

## (b) Shifted impulse

Let

$$
x(n)=\delta(n-n_0), \qquad 0<n_0<N.
$$

Then

$$
\begin{aligned} X(k) &= \sum_{n=0}^{N-1} \delta(n-n_0)e^{-j2\pi nk/N}\\ &= \boxed{ e^{-j2\pi n_0k/N} } \end{aligned}
$$

or

$$
\boxed{ X(k)=W_N^{n_0k}. }
$$

---

## (c) Exponential sequence

Let

$$
x(n)=a^n, \qquad 0\le n\le N-1.
$$

Then

$$
\begin{aligned} X(k) &= \sum_{n=0}^{N-1} a^n e^{-j2\pi nk/N}\\ &= \sum_{n=0}^{N-1} \left(ae^{-j2\pi k/N}\right)^n. \end{aligned}
$$

Using the geometric-series result,

$$
\boxed{ X(k)= \frac{ 1-a^N e^{-j2\pi k} }{ 1-ae^{-j2\pi k/N} } }
$$

and since

$$
e^{-j2\pi k}=1,
$$

$$
\boxed{ X(k)= \frac{1-a^N} {1-ae^{-j2\pi k/N}} }
$$

for the non-singular case.

---

## (d) Even-index sequence

For the sequence

$$
x(n)= \begin{cases} 1,&n\text{ even}\\ 0,&n\text{ odd}, \end{cases}
$$

the DFT summation contains only even-index samples:

$$
\boxed{ X(k)= \sum_{m=0}^{N/2-1} e^{-j(4\pi/N)mk} }
$$

for even $N$.

This can be evaluated as a geometric series.

---

# 14. EXAMPLE 6.3 — DFT FROM THE Z-TRANSFORM

The textbook uses

$$
x(n)=u(n)-u(n-6)
$$

so that

$$
x(n)=\{1,1,1,1,1,1\}.
$$

Its $Z$-transform is

$$
\boxed{ X(z)=1+z^{-1}+z^{-2}+z^{-3}+z^{-4}+z^{-5} }
$$

Take a 4-point DFT by evaluating

$$
z=e^{j(2\pi/4)k}, \qquad k=0,1,2,3.
$$

Therefore,

$$
\boxed{ X(k)= X(z)\bigg|_{z=e^{j(2\pi/4)k}} }
$$

The important conclusion is that the 4-point DFT does **not** reproduce the original six-sample sequence directly because

$$
N=4<L=6.
$$

Thus, time-domain aliasing occurs when too few DFT points are used.

### Exam rule

For a finite sequence of length $L$,

$$
\boxed{N\ge L}
$$

is required to represent all samples without truncation.

For linear convolution, the stronger zero-padding condition is

$$
\boxed{ N\ge L_x+L_h-1. }
$$

---

# 15. EXAMPLE 6.4 — DIRECT 4-POINT DFT

Given

$$
x(n)=\{1,-1,2,-2\}.
$$

For $N=4$,

$$
W_4=-j.
$$

The DFT is

$$
X(k)=\sum_{n=0}^{3}x(n)W_4^{nk}.
$$

### $k=0$

$$
X(0)=1-1+2-2=0.
$$

### $k=1$

$$
\begin{aligned} X(1) &=1+(-1)(-j)+2(-1)+(-2)(j)\\ &=-1-j. \end{aligned}
$$

### $k=2$

$$
\begin{aligned} X(2) &=1+(-1)(-1)+2(1)+(-2)(-1)\\ &=6. \end{aligned}
$$

### $k=3$

$$
X(3)=X^*(1)=-1+j.
$$

Therefore,

$$
\boxed{ X(k)=\{0,\,-1-j,\,6,\,-1+j\}. }
$$

---

## Example 6.4(b) — Direct IDFT

Given

$$
X(k)=\{4,2,0,4\}.
$$

Use

$$
x(n)=\frac14\sum_{k=0}^{3}X(k)W_4^{-nk}.
$$

Evaluating the four samples gives the inverse sequence.

This example is useful for remembering the **$1/N$ factor in the IDFT**.

---

# 16. EXAMPLE 6.5 — ANOTHER 4-POINT DFT

Given

$$
x(n)=\{1,-2,3,2\}.
$$

Using

$$
X(k)= \sum_{n=0}^{3} x(n)W_4^{nk},
$$

the textbook obtains

$$
\boxed{ X(k)= \{4,\,-2+j4,\,4,\,-2-j4\}. }
$$

The conjugate-pair structure follows because the input is real.

---

# 17. EXAMPLE 6.6 — 3-POINT AND 6-POINT DFT

Given

$$
x(n)=\{2,1,2\}.
$$

For the 3-point DFT,

$$
X_3(k)= \sum_{n=0}^{2} x(n)e^{-j2\pi nk/3}.
$$

The 6-point DFT is obtained by zero-padding:

$$
x_6(n)=\{2,1,2,0,0,0\}.
$$

### Main comparison

The 3-point and 6-point transforms sample the underlying DTFT at different frequency spacings.

Increasing $N$:

- increases the number of frequency samples;
- gives a denser frequency grid;
- does **not** add new information to the original finite sequence by itself.

---

# 18. A SLIGHTLY FASTER METHOD FOR COMPUTING DFT VALUES

Direct computation requires many repeated complex multiplications.

The textbook introduces a faster calculation based on symmetry and reuse of the twiddle factors.

For small $N$, use

$$
W_N^{nk}
$$

and exploit

$$
W_N^{n(k+N)}=W_N^{nk}
$$

and conjugate relationships.

The method is useful in hand calculations, while the matrix formulation gives a systematic representation.

---

# 19. MATRIX FORMULATION OF DFT

The DFT can be written as a matrix operation.

Define

$$
\mathbf{x} = \begin{bmatrix} x(0)\\ x(1)\\ \vdots\\ x(N-1) \end{bmatrix}
$$

and

$$
\mathbf{X} = \begin{bmatrix} X(0)\\ X(1)\\ \vdots\\ X(N-1) \end{bmatrix}.
$$

Define the DFT matrix

$$
\boxed{ \mathbf{W}_N= \left[W_N^{nk}\right]_{k,n=0}^{N-1} }
$$

Then

$$
\boxed{ \mathbf{X}=\mathbf{W}_N\mathbf{x}. }
$$

The inverse is

$$
\boxed{ \mathbf{x} = \frac1N \mathbf{W}_N^* \mathbf{X}. }
$$

The textbook emphasizes the important relation

$$
\boxed{ \mathbf{W}_N^{-1} = \frac1N\mathbf{W}_N^* }
$$

where conjugate transpose is involved.

---

# 20. EXAMPLE 6.8 — MATRIX DFT

For

$$
x(n)=\{1,2,1,0\},
$$

the 4-point DFT is

$$
\mathbf{X} = \begin{bmatrix} 1&1&1&1\\ 1&-j&-1&j\\ 1&-1&1&-1\\ 1&j&-1&-j \end{bmatrix} \begin{bmatrix} 1\\2\\1\\0 \end{bmatrix}.
$$

Hence

$$
\boxed{ X(k)=\{4,-j2,0,j2\}. }
$$

This is the same result obtained by direct DFT calculation.

---

# 21. EXAMPLES 6.9 AND 6.10 — MATRIX DFT

For

$$
x(n)=\{1,-1,2,-2\},
$$

matrix multiplication gives

$$
\boxed{ X(k)=\{0,-1-j,6,-1+j\}. }
$$

For

$$
x(n)=\{1,-2,3,2\},
$$

matrix multiplication gives

$$
\boxed{ X(k)=\{4,-2+j4,4,-2-j4\}. }
$$

### Exam shortcut

For $N=4$,

$$
\mathbf W_4= \begin{bmatrix} 1&1&1&1\\ 1&-j&-1&j\\ 1&-1&1&-1\\ 1&j&-1&-j \end{bmatrix}.
$$

Memorizing this matrix is useful for short numerical problems.

---

# 22. EXAMPLE 6.11 — CONJUGATE SYMMETRY

Given

$$
x(n)=\{1,1,0,0,0,0,0,0\}
$$

for $N=8$.

Because $x(n)$ is real,

$$
\boxed{ X(k)=X^*(N-k) }
$$

for

$$
k=1,\ldots,N-1.
$$

Thus only approximately half of the DFT values need to be independently computed.

For a real sequence,

$$
\boxed{ X(N-k)=X^*(k). }
$$

---

# 23. IDFT USING THE DFT

A useful textbook relation is

$$
\boxed{ \text{IDFT}\{X(k)\} = \frac1N \left[ \text{DFT}\{X^*(k)\} \right]^* }
$$

That is:

1. take $X^*(k)$;
2. compute its DFT;
3. take the complex conjugate;
4. divide by $N$.

This allows the IDFT to be obtained using a DFT routine.

---

# 24. EXAMPLE 6.12 — IDFT USING DFT

Given

$$
X(k)=\{4,-j2,0,j2\},
$$

take

$$
X^*(k)=\{4,j2,0,-j2\}.
$$

Compute the DFT of $X^*(k)$, take its conjugate, and divide by $N=4$.

The resulting sequence is

$$
\boxed{ x(n)=\{1,2,1,0\}. }
$$

---

# 25. EXAMPLES 6.13 AND 6.14 — IDFT

### Example 6.13

Given

$$
X(k)=\{4,2,0,4\},
$$

use the DFT-to-IDFT relation above.

### Example 6.14

Given

$$
X(k)=\{1,0,1,0\},
$$

the inverse transform is obtained by

$$
x(n)= \frac14 \sum_{k=0}^{3} X(k)e^{j2\pi nk/4}.
$$

The nonzero terms occur at $k=0$ and $k=2$, so

$$
x(n) = \frac14 \left[ 1+e^{j\pi n} \right].
$$

Hence the sequence alternates between nonzero and zero samples.

---

# 26. COMPARISON BETWEEN DTFT AND DFT

The chapter emphasizes:

| DTFT | DFT |
|---|---|
| Frequency variable is continuous | Frequency variable is sampled |
| Periodic in $\omega$ | Finite sequence of frequency samples |
| Can describe positive and negative frequencies | Usually represented by $k=0,\ldots,N-1$ |
| Infinite/continuous frequency representation | Finite computational representation |
| Useful for theoretical frequency analysis | Convenient for digital computation |

The DFT and DTFT coincide at

$$
\boxed{ \omega=\frac{2\pi k}{N}, \qquad k=0,1,\ldots,N-1. }
$$

Increasing $N$ gives more closely spaced frequency samples, but also increases computational effort for direct DFT calculation.

---

# 27. IMPORTANT DFT PROPERTIES

## 27.1 Periodicity

$$
\boxed{ x(n+N)=x(n) }
$$

in the periodic extension associated with the DFT.

Similarly,

$$
\boxed{ X(k+N)=X(k). }
$$

---

## 27.2 Linearity

If

$$
x_1(n)\xleftrightarrow{\text{DFT}}X_1(k)
$$

and

$$
x_2(n)\xleftrightarrow{\text{DFT}}X_2(k),
$$

then

$$
\boxed{ ax_1(n)+bx_2(n) \ \xleftrightarrow{\text{DFT}}\ aX_1(k)+bX_2(k). }
$$

---

# 28. DFT OF EVEN AND ODD SEQUENCES

For a real even sequence,

$$
x(n)=x(-n)
$$

and

$$
\boxed{ X(k)\text{ is purely real}. }
$$

For a real odd sequence,

$$
x(n)=-x(-n)
$$

and

$$
\boxed{ X(k)\text{ is purely imaginary}. }
$$

This is a common exam shortcut.

---

# 29. TIME REVERSAL

The circular time reversal is

$$
\boxed{ x((-n)\bmod N) }
$$

or, for the usual $0\le n<N$ representation,

$$
x(N-n).
$$

Its DFT is

$$
\boxed{ x((-n)\bmod N) \ \xleftrightarrow{\text{DFT}}\ X((-k)\bmod N) }
$$

or

$$
\boxed{ X(N-k). }
$$

---

# 30. CIRCULAR FREQUENCY SHIFT

If

$$
x(n)\xleftrightarrow{\text{DFT}}X(k),
$$

then

$$
\boxed{ x(n)e^{j2\pi ln/N} \ \xleftrightarrow{\text{DFT}}\ X((k-l)\bmod N). }
$$

This is the DFT version of modulation/frequency shifting.

---

# 31. COMPLEX-CONJUGATE PROPERTY

The DFT of the conjugate sequence is

$$
\boxed{ x^*(n) \ \xleftrightarrow{\text{DFT}}\ X^*(N-k) }
$$

with indices interpreted modulo $N$.

---

# 32. CONJUGATE SYMMETRY OF A REAL SEQUENCE

If $x(n)$ is real,

$$
x^*(n)=x(n).
$$

Therefore,

$$
\boxed{ X(k)=X^*(N-k). }
$$

Consequences:

$$
\boxed{ \Re\{X(k)\}=\Re\{X(N-k)\} }
$$

$$
\boxed{ \Im\{X(k)\}=-\Im\{X(N-k)\} }
$$

and

$$
\boxed{ |X(k)|=|X(N-k)|. }
$$

### Special samples

For $k=0$,

$$
\boxed{ X(0)=\sum_{n=0}^{N-1}x(n). }
$$

For even $N$,

$$
\boxed{ X(N/2)= \sum_{n=0}^{N-1} (-1)^n x(n). }
$$

Both are real when $x(n)$ is real.

---

# 33. DFT OF A DELAYED SEQUENCE

For circular delay,

$$
x((n-l)\bmod N)
$$

the DFT is

$$
\boxed{ X(k)e^{-j2\pi kl/N}. }
$$

Equivalently,

$$
\boxed{ x((n-l)\bmod N) \ \xleftrightarrow{\text{DFT}}\ W_N^{kl}X(k). }
$$

---

# 34. MULTIPLICATION OF TWO SEQUENCES

If

$$
x_1(n)\xleftrightarrow{\text{DFT}}X_1(k)
$$

and

$$
x_2(n)\xleftrightarrow{\text{DFT}}X_2(k),
$$

then

$$
\boxed{ \text{DFT}\{x_1(n)x_2(n)\} = \frac1N \left[ X_1(k)\circledast X_2(k) \right] }
$$

where $\circledast$ denotes circular convolution in frequency.

Thus:

$$
\boxed{ x_1(n)x_2(n) \longleftrightarrow \frac1N (X_1\circledast X_2)(k). }
$$

---

# 35. CIRCULAR CONVOLUTION PROPERTY

Let

$$
X_1(k)=\text{DFT}\{x_1(n)\}
$$

and

$$
X_2(k)=\text{DFT}\{x_2(n)\}.
$$

Then

$$
\boxed{ X_1(k)X_2(k) = \text{DFT} \left\{ x_1(n)\circledast x_2(n) \right\}. }
$$

The $N$-point circular convolution is

$$
\boxed{ x_3(n)= \sum_{m=0}^{N-1} x_1(m)x_2((n-m)\bmod N). }
$$

Therefore,

$$
\boxed{ \text{circular convolution in time} \longleftrightarrow \text{multiplication in frequency}. }
$$

This is one of the most important DFT results.

---

# 36. PROOF OF THE CIRCULAR-CONVOLUTION PROPERTY

Start with

$$
X_1(k)= \sum_{n=0}^{N-1} x_1(n)e^{-j2\pi nk/N}
$$

and

$$
X_2(k)= \sum_{l=0}^{N-1} x_2(l)e^{-j2\pi lk/N}.
$$

Multiply:

$$
X_1(k)X_2(k) = \sum_{n=0}^{N-1} \sum_{l=0}^{N-1} x_1(n)x_2(l) e^{-j2\pi(n+l)k/N}.
$$

Taking the IDFT gives

$$
x_3(m) = \frac1N \sum_{k=0}^{N-1} X_1(k)X_2(k)e^{j2\pi mk/N}.
$$

Substitution gives

$$
x_3(m) = \sum_{n=0}^{N-1} x_1(n) x_2((m-n)\bmod N).
$$

Therefore,

$$
\boxed{ x_3(m)=x_1(m)\circledast x_2(m) }
$$

and

$$
\boxed{ X_1(k)X_2(k) = \text{DFT}\{x_1(n)\circledast x_2(n)\}. }
$$

---

# 37. PARSEVAL'S THEOREM FOR DFT

If

$$
X_1(k)=\text{DFT}\{x_1(n)\}
$$

and

$$
X_2(k)=\text{DFT}\{x_2(n)\},
$$

then

$$
\boxed{ \sum_{n=0}^{N-1} x_1(n)x_2^*(n) = \frac1N \sum_{k=0}^{N-1} X_1(k)X_2^*(k). }
$$

For $x_1=x_2=x$,

$$
\boxed{ \sum_{n=0}^{N-1}|x(n)|^2 = \frac1N \sum_{k=0}^{N-1}|X(k)|^2. }
$$

Thus the DFT is an energy-conserving transformation with the $1/N$ scaling shown above.

---

# 38. CIRCULAR CORRELATION

For complex-valued sequences,

$$
\boxed{ r_{xy}(l) = \sum_{n=0}^{N-1} x(n)y^*((n-l)\bmod N) }
$$

and

$$
\boxed{ \text{DFT}\{r_{xy}(l)\} = X(k)Y^*(k). }
$$

This is the correlation counterpart of the circular-convolution theorem.

---

# 39. DFT PROPERTY TABLE — EXAM SHEET

| Time domain | Frequency domain |
|---|---|
| $x(n)$ | $X(k)$ |
| $x(n+N)$ | $X(k+N)$ |
| $ax_1(n)+bx_2(n)$ | $aX_1(k)+bX_2(k)$ |
| $x((-n)\bmod N)$ | $X((-k)\bmod N)$ |
| $x((n-l)\bmod N)$ | $X(k)e^{-j2\pi kl/N}$ |
| $x(n)e^{j2\pi ln/N}$ | $X((k-l)\bmod N)$ |
| $x^*(n)$ | $X^*(N-k)$ |
| $x_1(n)\circledast x_2(n)$ | $X_1(k)X_2(k)$ |
| $x_1(n)x_2(n)$ | $\frac1N[X_1\circledast X_2](k)$ |
| circular correlation | $X(k)Y^*(k)$ |
| $\sum x(n)y^*(n)$ | $\frac1N\sum X(k)Y^*(k)$ |

---

# 40. CENTRAL ORDINATES — VERY IMPORTANT

For any $N$-point DFT,

$$
\boxed{ X(0)=\sum_{n=0}^{N-1}x(n). }
$$

For even $N$,

$$
\boxed{ X(N/2)= \sum_{n=0}^{N-1} (-1)^n x(n). }
$$

These are very useful when a problem gives $X(0)$ or $X(N/2)$ and asks for unknown signal samples.

---

# 41. EXAMPLE 6.15 — CONJUGATE SYMMETRY OF A 12-POINT REAL SEQUENCE

Given the first seven samples of a 12-point DFT:

$$
\begin{aligned} X(0)&=8\\ X(1)&=-1+j2\\ X(2)&=2+j3\\ X(3)&=1-j4\\ X(4)&=-2+j2\\ X(5)&=3+j1\\ X(6)&=-1-j3. \end{aligned}
$$

Because the time sequence is real,

$$
X(k)=X^*(12-k).
$$

Therefore,

$$
\boxed{ X(7)=3-j1 }
$$

$$
\boxed{ X(8)=-2-j2 }
$$

$$
\boxed{ X(9)=1+j4 }
$$

$$
\boxed{ X(10)=2-j3 }
$$

$$
\boxed{ X(11)=-1-j2. }
$$

This is a direct conjugate-symmetry question.

---

# 42. EXAMPLE 6.16 — FIND UNKNOWN DFT VALUES

Given a real-signal DFT

$$
X(k)= \{1,A,-1,B,0,-j2,C,-1+j\}.
$$

Since the input is real,

$$
X(k)=X^*(8-k).
$$

Thus,

$$
\boxed{ A=-1-j }
$$

$$
\boxed{ B=j2 }
$$

and

$$
\boxed{ C=-1. }
$$

---

# 43. EXAMPLE 6.17 — CENTRAL ORDINATES

Given

$$
x(n)=\{A,2,3,4,5,6,7,B\}
$$

with

$$
X(0)=20
$$

and

$$
X(4)=0.
$$

Since

$$
X(0)=\sum_{n=0}^{7}x(n),
$$

$$
A+2+3+4+5+6+7+B=20
$$

so

$$
\boxed{ A+B=-7. }
$$

Since $N=8$,

$$
X(4)= \sum_{n=0}^{7} (-1)^n x(n).
$$

Thus

$$
A-2+3-4+5-6+7-B=0
$$

which gives

$$
\boxed{ A-B=-3. }
$$

Solving,

$$
\boxed{ A=-5,\qquad B=-2. }
$$

---

# 44. EXAMPLE 6.18 — SIGNAL ENERGY FROM THE DFT

For the real-signal DFT

$$
X(k)= \{1,A,-1,B,-7,-j2,C,-1+j\},
$$

first use conjugate symmetry to obtain the missing values:

$$
\boxed{ X(k)= \{1,-1-j,-1,j2,-7,-j2,-1,-1+j\}. }
$$

Then apply Parseval:

$$
E= \sum_{n=0}^{7}|x(n)|^2 = \frac18 \sum_{k=0}^{7}|X(k)|^2.
$$

This example demonstrates the preferred method:

> **If the DFT is given and energy is requested, use Parseval instead of computing IDFT first.**

---

# 45. EXAMPLE 6.19 — REAL SEQUENCE SYMMETRY PROOF

Let

$$
X(k)=X_R(k)+jX_I(k).
$$

For real $x(n)$,

$$
X(k)=X^*(N-k).
$$

Therefore,

$$
X_R(k)+jX_I(k) = X_R(N-k)-jX_I(N-k).
$$

Equating real and imaginary parts,

$$
\boxed{ X_R(k)=X_R(N-k) }
$$

and

$$
\boxed{ X_I(k)=-X_I(N-k). }
$$

---

# 46. EXAMPLE 6.20 — PARSEVAL PROOF

For an $N$-point sequence,

$$
\boxed{ \sum_{n=0}^{N-1}|x(n)|^2 = \frac1N \sum_{k=0}^{N-1}|X(k)|^2. }
$$

This follows directly by substituting the DFT expression into the energy sum and using DFT orthogonality.

---

# 47. EXAMPLE 6.21 — USING CENTRAL ORDINATES WITHOUT DFT

Given

$$
x(n)=\{1,-2,3,0,-1,1\}
$$

for $0\le n<6$, with an 8-point DFT.

### (a) $X(0)$

$$
X(0)= 1-2+3+0-1+1 = \boxed{2}.
$$

The two padded samples are zero.

### (b) $X(3)$

Use

$$
X(3)= \sum_n x(n)e^{-j2\pi(3)n/8}
$$

and substitute only the nonzero samples.

### (c), (d)

Use DFT periodicity and the given sequence directly rather than calculating all DFT values.

### Exam lesson

Whenever a question gives a few DFT samples and asks for a simple value, first check:

- $X(0)$;
- $X(N/2)$ for even $N$;
- conjugate symmetry;
- periodicity;
- linearity.

---

# 48. EXAMPLE 6.22 — PROPERTY-BASED DFT QUESTIONS

Given

$$
X(k)=\{4,-j2,0,j2\}
$$

for

$$
x(n)=\{1,2,1,0\},
$$

the textbook asks for transforms of modified sequences.

### (a) $x(n-2)$

Use circular time shifting:

$$
\boxed{ X_{\text{new}}(k) = X(k)e^{-j2\pi k(2)/4}. }
$$

### (b) $x(-n)$

Use time reversal:

$$
\boxed{ X_{\text{new}}(k)=X(4-k). }
$$

### (c) $x^*(n)$

Use

$$
\boxed{ X_{\text{new}}(k)=X^*(4-k). }
$$

### (d) $x^2(n)$

Use frequency-domain circular convolution:

$$
\boxed{ \text{DFT}\{x^2(n)\} = \frac14[X\circledast X](k). }
$$

### (e) circular convolution $x(n)\circledast x(n)$

Use

$$
\boxed{ \text{DFT}\{x\circledast x\}=X^2(k). }
$$

### (f) energy

Use Parseval:

$$
\boxed{ E=\frac14\sum_{k=0}^{3}|X(k)|^2. }
$$

---

# 49. EXAMPLE 6.23 — IDFT PROPERTY QUESTIONS

Given

$$
x(n)=\{1,2,1,0\}
$$

as the IDFT of $X(k)$, use DFT properties in reverse.

For example, a circular shift in frequency corresponds to multiplication/modulation in time.

General exam principle:

$$
\boxed{ \text{DFT property} \Longleftrightarrow \text{corresponding IDFT property}. }
$$

---

# 50. LINEAR CONVOLUTION USING DFT

## 50.1 Why zero-padding is required

Suppose

$$
x(n)\text{ has length }L_x
$$

and

$$
h(n)\text{ has length }L_h.
$$

Their linear convolution has length

$$
\boxed{ L_y=L_x+L_h-1. }
$$

To obtain linear convolution using an $N$-point DFT without time aliasing, choose

$$
\boxed{ N\ge L_x+L_h-1. }
$$

Zero-pad both sequences to length $N$.

Then

$$
X(k)=\text{DFT}\{x(n)\}
$$

$$
H(k)=\text{DFT}\{h(n)\}
$$

and

$$
Y(k)=X(k)H(k).
$$

Finally,

$$
\boxed{ y(n)=\text{IDFT}\{Y(k)\}. }
$$

Thus,

$$
\boxed{ y(n)=x(n)*h(n). }
$$

---

# 51. LINEAR CONVOLUTION ALGORITHM USING DFT

### Step 1
Find the required linear-convolution length:

$$
N=L_x+L_h-1.
$$

### Step 2
Zero-pad:

$$
x(n)\rightarrow N\text{ samples}
$$

$$
h(n)\rightarrow N\text{ samples}.
$$

### Step 3
Compute DFTs:

$$
X(k)=\text{DFT}\{x(n)\}
$$

$$
H(k)=\text{DFT}\{h(n)\}.
$$

### Step 4
Multiply:

$$
\boxed{ Y(k)=X(k)H(k). }
$$

### Step 5
Take IDFT:

$$
\boxed{ y(n)=\text{IDFT}\{Y(k)\}. }
$$

### Critical warning

If

$$
N<L_x+L_h-1,
$$

the circular convolution aliases into the output and is **not** the desired linear convolution.

---

# 52. EXAMPLE 6.24 — LINEAR CONVOLUTION USING DFT

Given

$$
x(n)=\{1,2\}
$$

and

$$
h(n)=\{2,1\}.
$$

Required output length:

$$
N=2+2-1=3.
$$

Zero-pad:

$$
x(n)=\{1,2,0\}
$$

$$
h(n)=\{2,1,0\}.
$$

Compute 3-point DFTs:

$$
X(k)=\text{DFT}\{1,2,0\}
$$

$$
H(k)=\text{DFT}\{2,1,0\}.
$$

Multiply:

$$
Y(k)=X(k)H(k).
$$

Then

$$
y(n)=\text{IDFT}\{Y(k)\}.
$$

The result is

$$
\boxed{ y(n)=\{2,5,2\}. }
$$

Direct verification:

$$
\{1,2\}*\{2,1\} = \boxed{\{2,5,2\}}.
$$

---

# 53. EXAMPLE 6.25 — LINEAR CONVOLUTION USING DFT

Given

$$
x(n)=\{1,0,2\}
$$

and

$$
h(n)=\{1,1\}.
$$

Required length:

$$
N=3+2-1=4.
$$

Zero-pad:

$$
x(n)=\{1,0,2,0\}
$$

$$
h(n)=\{1,1,0,0\}.
$$

Then

$$
Y(k)=X(k)H(k)
$$

and

$$
y(n)=\text{IDFT}\{Y(k)\}.
$$

Direct linear-convolution verification gives

$$
\boxed{ y(n)=\{1,1,2,2\}. }
$$

---

# 54. CIRCULAR CONVOLUTION

The $N$-point circular convolution is

$$
\boxed{ y(n)= \sum_{m=0}^{N-1} x(m) h((n-m)\bmod N). }
$$

Unlike linear convolution, the output always has $N$ samples.

### Key difference

| Linear convolution | Circular convolution |
|---|---|
| Length $L_x+L_h-1$ | Length $N$ |
| No wrap-around | Wrap-around occurs |
| Uses ordinary index addition | Uses modulo-$N$ indexing |
| DFT implementation needs zero-padding | DFT multiplication directly gives circular convolution |

---

# 55. CIRCULAR CONVOLUTION USING DFT AND IDFT

Given $x(n)$ and $h(n)$ of length $N$:

$$
X(k)=\text{DFT}\{x(n)\}
$$

$$
H(k)=\text{DFT}\{h(n)\}
$$

Multiply:

$$
\boxed{ Y(k)=X(k)H(k). }
$$

Then

$$
\boxed{ y(n)=\text{IDFT}\{Y(k)\}. }
$$

The result is

$$
\boxed{ y(n)=x(n)\circledast h(n). }
$$

---

# 56. EXAMPLE 6.26 — CIRCULAR CONVOLUTION

Given

$$
x_1(n)=\{1,2,1,2\}
$$

and

$$
x_2(n)=\{4,3,2,1\}.
$$

Both are 4-point sequences.

Compute

$$
X_1(k)=\text{DFT}\{x_1(n)\}
$$

and

$$
X_2(k)=\text{DFT}\{x_2(n)\}.
$$

Then

$$
Y(k)=X_1(k)X_2(k).
$$

Taking the 4-point IDFT gives

$$
\boxed{ y(n)=\{14,16,14,16\}. }
$$

This is the 4-point circular convolution.

---

# 57. EXAMPLE 6.27 — 2-POINT CIRCULAR CONVOLUTION

Given

$$
x(n)=\{1,0.5\}
$$

and

$$
h(n)=\{0.5,1\}.
$$

For $N=2$,

$$
W_2=e^{-j\pi}=-1.
$$

DFTs:

$$
X(0)=1+0.5=1.5
$$

$$
X(1)=1-0.5=0.5
$$

and

$$
H(0)=0.5+1=1.5
$$

$$
H(1)=0.5-1=-0.5.
$$

Therefore,

$$
Y(0)=1.5(1.5)=2.25
$$

$$
Y(1)=0.5(-0.5)=-0.25.
$$

IDFT gives

$$
y(0)=\frac12(2.25-0.25)=1
$$

$$
y(1)=\frac12(2.25+0.25)=1.25.
$$

Thus,

$$
\boxed{ y(n)=\{1,1.25\}. }
$$

---

# 58. SECTIONED CONVOLUTION

For long sequences, directly taking one huge DFT can be computationally inconvenient.

The textbook therefore discusses **sectioned convolution**.

Two methods are:

1. **Overlap-add method**
2. **Overlap-save method**

The common idea is to divide the long input sequence into manageable blocks and use short DFTs.

---

# 59. OVERLAP-ADD METHOD

Suppose

$$
x(n)
$$

is long and

$$
h(n)
$$

has length $N$.

Choose a block length $M$.

Divide $x(n)$ into blocks:

$$
x_1(n),x_2(n),x_3(n),\ldots
$$

Each block is convolved with $h(n)$.

Each block convolution has length

$$
M+N-1.
$$

Adjacent output blocks overlap by

$$
\boxed{ N-1 }
$$

samples.

The overlapping samples are **added**.

### Procedure

1. Divide $x(n)$ into blocks of length $M$.
2. Zero-pad each block appropriately.
3. Compute each block convolution using DFT/IDFT.
4. Shift each block output to its correct location.
5. Add the $N-1$ overlapping samples.
6. Concatenate the result.

---

# 60. OVERLAP-SAVE METHOD

Overlap-save uses a different block organization.

For filter length $N$, each block contains

$$
M
$$

samples and adjacent input blocks overlap by

$$
\boxed{ N-1 }
$$

samples.

Each block is circularly convolved with the zero-padded impulse response.

The first

$$
\boxed{ N-1 }
$$

samples of each circular-convolution output are discarded because they contain circular-aliasing terms.

The remaining samples are retained and concatenated.

### Key memory rule

> **Overlap-add: add the overlapping OUTPUT samples.**

> **Overlap-save: discard the corrupted beginning of each OUTPUT block and save the rest.**

---

# 61. OVERLAP-ADD VS OVERLAP-SAVE

| Feature | Overlap-add | Overlap-save |
|---|---|---|
| Input blocks | Non-overlapping | Overlapping |
| Output blocks | Overlap | Same-size valid portions |
| Main operation | Add overlap | Discard first $N-1$ samples |
| Reason | Join block convolutions | Remove circular aliasing |
| Filter length | $N$ | $N$ |
| Overlap amount | $N-1$ output samples | $N-1$ input samples |

---

# 62. EXAMPLE 6.28 — SECTIONED CONVOLUTION

Given

$$
x(n)= \{1,-2,2,-1,3,-4,4,-3\}
$$

and

$$
h(n)=\{1,-1\}.
$$

The filter length is

$$
N=2.
$$

Therefore the overlap is

$$
N-1=1.
$$

## (a) Overlap-add

Divide $x(n)$ into 2-sample blocks:

$$
x_1=\{1,-2\}
$$

$$
x_2=\{2,-1\}
$$

$$
x_3=\{3,-4\}
$$

$$
x_4=\{4,-3\}.
$$

Convolve each block with

$$
h=\{1,-1\}.
$$

Each produces a 3-sample result.

Shift the blocks by 2 samples and add the overlapping samples.

The final result is the ordinary linear convolution.

## (b) Overlap-save

Add one leading zero:

$$
x(n)=\{0,1,-2,2,-1,3,-4,4,-3\}.
$$

Form overlapping blocks with overlap of one sample.

Circularly convolve each block with the zero-padded filter and discard the first output sample from every block.

The saved portions are concatenated to produce

$$
\boxed{ y(n)= \{1,-3,4,-3,4,-7,8,-7,3\}. }
$$

---

# 63. EXAMPLE 6.29 — OVERLAP-ADD AND OVERLAP-SAVE

Given

$$
x(n)= \{1,-2,3,2,-3,4,3,-4\}
$$

and

$$
h(n)=\{1,2,-1\}.
$$

Here

$$
N=3,
$$

so

$$
N-1=2.
$$

## (a) Overlap-add

Pad the input with one zero:

$$
\boxed{ x(n)= \{1,-2,3,2,-3,4,3,-4,0\}. }
$$

Divide into blocks of length 3:

$$
x_1=\{1,-2,3\}
$$

$$
x_2=\{2,-3,4\}
$$

$$
x_3=\{3,-4,0\}.
$$

Convolve each block with

$$
h=\{1,2,-1\}.
$$

The textbook obtains

$$
y_1=\{1,0,-2,8,-3\}
$$

$$
y_2=\{2,1,-4,11,-4\}
$$

$$
y_3=\{3,2,-11,4,0\}.
$$

Add the overlapping output samples.

The final result is

$$
\boxed{ y(n)= \{1,0,-2,10,-2,-4,14,-2,-11,4\}. }
$$

The final padded zero is discarded.

---

## (b) Overlap-save

For $N=3$, add

$$
N-1=2
$$

leading zeros:

$$
\boxed{ x(n)= \{0,0,1,-2,3,2,-3,4,3,-4\}. }
$$

Choose the block organization so that adjacent sections overlap by two samples.

Zero-pad

$$
h(n)=\{1,2,-1\}
$$

to the selected DFT length.

Perform circular convolution for each section.

Discard the first two output samples from each section and retain the remaining valid samples.

The textbook obtains

$$
\boxed{ y(n)= \{1,0,-2,10,-2,-4,14,-2,-11,4\}. }
$$

This agrees with the overlap-add and direct linear-convolution results.

---

# 64. LINEAR VS CIRCULAR CONVOLUTION — EXAM TRAP

### Linear convolution

$$
\boxed{ y(n)=\sum_k x(k)h(n-k) }
$$

Output length:

$$
\boxed{ L_x+L_h-1. }
$$

### Circular convolution

$$
\boxed{ y(n)= \sum_{k=0}^{N-1} x(k)h((n-k)\bmod N). }
$$

Output length:

$$
\boxed{ N. }
$$

### DFT multiplication

Directly gives

$$
\boxed{ \text{circular convolution}. }
$$

To obtain **linear convolution** using DFT multiplication, zero-pad to at least

$$
\boxed{ N\ge L_x+L_h-1. }
$$

---

# 65. ZERO-PADDING — WHY IT WORKS

Suppose

$$
x(n)*h(n)
$$

has length

$$
L_x+L_h-1.
$$

An $N$-point IDFT always produces a circularly wrapped result of length $N$.

If

$$
N\ge L_x+L_h-1,
$$

the linear-convolution samples fit entirely within the $N$-sample interval and no samples wrap around.

Thus,

$$
\boxed{ \text{zero-padding prevents time aliasing.} }
$$

---

# 66. FFT CONNECTION

The chapter introduces FFT as an efficient algorithm for computing the DFT.

The FFT is based on a **divide-and-conquer** approach.

Instead of directly evaluating all $N^2$ DFT terms, the algorithm decomposes an $N$-point DFT into smaller DFTs.

The chapter discusses:

- **DIT FFT** — decimation-in-time;
- **DIF FFT** — decimation-in-frequency.

The key motivation is computational efficiency.

### Direct DFT complexity

Approximately

$$
\boxed{ O(N^2) }
$$

for direct computation.

### FFT complexity

Approximately

$$
\boxed{ O(N\log_2N) }
$$

for the standard radix-2 structure.

---

# 67. DIT VS DIF — QUICK PREVIEW

| DIT FFT | DIF FFT |
|---|---|
| Decimation in time | Decimation in frequency |
| Splits/decomposes the input sequence | Splits/decomposes frequency outputs |
| Butterfly operations are arranged differently | Butterfly operations are arranged differently |
| Both compute the same DFT | Both compute the same DFT |
| Main benefit: reduced computation | Main benefit: reduced computation |

The detailed FFT derivations follow the DFT structure and should be learned together with the butterfly equations in the relevant FFT section.

---

# 68. ONE-PAGE FORMULA SHEET

## DFT

$$
\boxed{ X(k)= \sum_{n=0}^{N-1} x(n)e^{-j2\pi nk/N} }
$$

## IDFT

$$
\boxed{ x(n)= \frac1N \sum_{k=0}^{N-1} X(k)e^{j2\pi nk/N} }
$$

## Twiddle factor

$$
\boxed{ W_N=e^{-j2\pi/N} }
$$

## DFT using twiddle factor

$$
\boxed{ X(k)=\sum_{n=0}^{N-1}x(n)W_N^{nk} }
$$

## IDFT using twiddle factor

$$
\boxed{ x(n)=\frac1N\sum_{k=0}^{N-1}X(k)W_N^{-nk} }
$$

## Frequency samples

$$
\boxed{ \omega_k=\frac{2\pi k}{N} }
$$

## DFT–Z relation

$$
\boxed{ X(k)=X(z)\big|_{z=e^{j2\pi k/N}} }
$$

## Delay

$$
\boxed{ x((n-l)\bmod N) \leftrightarrow X(k)e^{-j2\pi kl/N} }
$$

## Modulation

$$
\boxed{ x(n)e^{j2\pi ln/N} \leftrightarrow X((k-l)\bmod N) }
$$

## Reversal

$$
\boxed{ x((-n)\bmod N) \leftrightarrow X((-k)\bmod N) }
$$

## Conjugation

$$
\boxed{ x^*(n) \leftrightarrow X^*(N-k) }
$$

## Circular convolution

$$
\boxed{ x_1\circledast x_2 \leftrightarrow X_1X_2 }
$$

## Multiplication

$$
\boxed{ x_1x_2 \leftrightarrow \frac1N(X_1\circledast X_2) }
$$

## Parseval

$$
\boxed{ \sum |x(n)|^2 = \frac1N\sum |X(k)|^2 }
$$

## Cross-correlation

$$
\boxed{ r_{xy}(l) \leftrightarrow X(k)Y^*(k) }
$$

## Central ordinate

$$
\boxed{ X(0)=\sum x(n) }
$$

For even $N$,

$$
\boxed{ X(N/2)=\sum(-1)^nx(n) }
$$

## Linear convolution by DFT

$$
\boxed{ N\ge L_x+L_h-1 }
$$

then

$$
\boxed{ Y(k)=X(k)H(k) }
$$

and

$$
\boxed{ y(n)=\text{IDFT}\{Y(k)\}. }
$$

## Sectioned convolution

Overlap-add:

$$
\boxed{\text{add overlapping output samples}}
$$

Overlap-save:

$$
\boxed{\text{discard first }N-1\text{ samples of each output block}}
$$

---

# 69. MOST IMPORTANT EXAM DIFFERENCES

## DFT vs DFS

**DFS:**

- represents a periodic discrete-time sequence;
- coefficients describe one period;
- synthesis reconstructs the periodic sequence.

**DFT:**

- operates on a finite-length sequence;
- gives a finite set of frequency samples;
- is directly computable on a digital computer.

---

## DFT vs DTFT

$$
\boxed{ \text{DFT}=\text{samples of DTFT at }2\pi k/N. }
$$

---

## Linear vs circular convolution

$$
\boxed{ \text{DFT multiplication}\Rightarrow\text{circular convolution} }
$$

unless sufficient zero-padding is used.

---

## Overlap-add vs overlap-save

$$
\boxed{ \text{OLA: add overlaps} }
$$

$$
\boxed{ \text{OLS: discard corrupted overlaps} }
$$

---

# 70. COMMON EXAM MISTAKES

1. Forgetting the $1/N$ in the IDFT.
2. Using $e^{+j2\pi nk/N}$ in the forward DFT.
3. Using $e^{-j2\pi nk/N}$ in the IDFT.
4. Forgetting that $W_N=e^{-j2\pi/N}$.
5. Confusing DFT with DTFT.
6. Forgetting modulo-$N$ indexing in circular convolution.
7. Calling DFT multiplication linear convolution without zero-padding.
8. Using $N<L_x+L_h-1$ for DFT-based linear convolution.
9. Forgetting conjugate symmetry for real sequences.
10. Forgetting that $X(0)$ is the sum of the time samples.
11. Forgetting the special $X(N/2)$ expression for even $N$.
12. Mixing ordinary reversal with circular reversal.
13. In overlap-add, forgetting to **add** overlapping output samples.
14. In overlap-save, forgetting to **discard** the first $N-1$ samples.
15. Treating zero-padding as adding information rather than increasing the frequency sampling density.

---

# 71. HOW TO SOLVE A DIRECT DFT NUMERICAL

Given $x(n)$ and $N$:

### Step 1
Write

$$
W_N=e^{-j2\pi/N}.
$$

### Step 2
Write the DFT equation:

$$
X(k)=\sum_{n=0}^{N-1}x(n)W_N^{nk}.
$$

### Step 3
Compute

$$
X(0),X(1),\ldots,X(N-1).
$$

### Step 4
Use symmetry if the sequence is real.

### Step 5
Check:

$$
X(0)=\sum x(n).
$$

For real sequences and even $N$, check

$$
X(N/2)=\sum(-1)^nx(n).
$$

---

# 72. HOW TO SOLVE AN IDFT NUMERICAL

Given $X(k)$:

### Step 1

Write

$$
x(n)= \frac1N \sum_{k=0}^{N-1} X(k)e^{j2\pi nk/N}.
$$

### Step 2

Calculate $x(0),x(1),\ldots,x(N-1)$.

### Step 3

If convenient, use the conjugate-DFT relation:

$$
\boxed{ \text{IDFT}\{X\} = \frac1N \left[ \text{DFT}\{X^*\} \right]^*. }
$$

---

# 73. HOW TO SOLVE DFT-BASED LINEAR CONVOLUTION

Given $x(n)$ and $h(n)$:

$$
L=L_x+L_h-1.
$$

Choose

$$
N\ge L.
$$

Zero-pad:

$$
x\rightarrow N
$$

$$
h\rightarrow N.
$$

Then

$$
X=\text{DFT}(x)
$$

$$
H=\text{DFT}(h)
$$

$$
Y=XH
$$

$$
\boxed{ y=\text{IDFT}(Y). }
$$

---

# 74. HOW TO SOLVE CIRCULAR CONVOLUTION

If both sequences are $N$-point:

$$
X=\text{DFT}(x)
$$

$$
H=\text{DFT}(h)
$$

$$
Y=XH
$$

$$
\boxed{ y=\text{IDFT}(Y). }
$$

No extra zero-padding is required because the requested operation is circular convolution.

---

# 75. HOW TO SOLVE OVERLAP-ADD

Remember:

$$
\boxed{ \text{Block length }M }
$$

$$
\boxed{ \text{Filter length }N }
$$

Each block convolution has length

$$
M+N-1.
$$

Adjacent blocks overlap by

$$
N-1.
$$

Add those overlap samples.

---

# 76. HOW TO SOLVE OVERLAP-SAVE

Remember:

1. prepend $N-1$ zeros;
2. form overlapping input blocks;
3. overlap successive blocks by $N-1$;
4. circularly convolve each block;
5. discard the first $N-1$ samples;
6. concatenate the remaining samples.

The word **SAVE** means save only the valid portion.

---

# 77. REVIEW QUESTIONS — CHAPTER 6

1. What is a discrete Fourier series?
2. Define the exponential form of DFS.
3. Define the trigonometric form of DFS.
4. Explain the relationship between exponential and trigonometric DFS.
5. State the properties of DFS.
6. Define the DFT.
7. Define the IDFT.
8. What is the twiddle factor?
9. Derive the relation between DFT and Z-transform.
10. Compare DTFT and DFT.
11. Explain the matrix formulation of DFT.
12. Explain the matrix formulation of IDFT.
13. State the periodicity property of DFT.
14. State the linearity property of DFT.
15. Explain DFT of even and odd sequences.
16. Explain time reversal property of DFT.
17. Explain circular frequency shifting.
18. State the complex-conjugate property.
19. Explain conjugate symmetry for real sequences.
20. State the DFT property for delayed sequences.
21. State the multiplication property of DFT.
22. Prove the circular-convolution property.
23. State and explain Parseval's theorem.
24. Define circular correlation.
25. Explain linear convolution using DFT.
26. Why is zero-padding necessary for DFT-based linear convolution?
27. Explain circular convolution using DFT and IDFT.
28. What is sectioned convolution?
29. Explain overlap-add method.
30. Explain overlap-save method.
31. Compare overlap-add and overlap-save methods.
32. Why is FFT preferred over direct DFT for large $N$?
33. What is the basic idea of DIT FFT?
34. What is the basic idea of DIF FFT?

---

# 78. FILL-IN / ONE-LINE ANSWERS

| Question | Answer |
|---|---|
| DFT frequency samples | $2\pi k/N$ |
| Number of DFT samples | $N$ |
| Twiddle factor | $W_N=e^{-j2\pi/N}$ |
| IDFT scaling factor | $1/N$ |
| $X(0)$ | Sum of time samples |
| Real input DFT property | Conjugate symmetry |
| Even real sequence | Purely real DFT |
| Odd real sequence | Purely imaginary DFT |
| DFT multiplication gives | Circular convolution |
| Linear convolution output length | $L_x+L_h-1$ |
| Zero-padding requirement | $N\ge L_x+L_h-1$ |
| OLA overlap | $N-1$ output samples |
| OLS overlap | $N-1$ input samples |
| OLA operation | Add overlap |
| OLS operation | Discard first $N-1$ samples |
| Parseval | Energy in time = scaled energy in frequency |
| DFT from Z-transform | Sample Z-transform on unit circle |
| Direct DFT complexity | $O(N^2)$ |
| FFT complexity | $O(N\log_2N)$ |

---

# 79. OBJECTIVE-TYPE PREPARATION

### 1. The DFT is

**Answer:** a sampled version of the DTFT.

### 2. The DFT is evaluated at

$$
\boxed{ \omega_k=\frac{2\pi k}{N}. }
$$

### 3. The twiddle factor is

$$
\boxed{ W_N=e^{-j2\pi/N}. }
$$

### 4. The IDFT contains

$$
\boxed{ \frac1N. }
$$

### 5. Multiplication of two DFTs corresponds to

$$
\boxed{ \text{circular convolution in time}. }
$$

### 6. To obtain linear convolution by DFT multiplication

$$
\boxed{ N\ge L_x+L_h-1. }
$$

### 7. For a real sequence

$$
\boxed{ X(k)=X^*(N-k). }
$$

### 8. $X(0)$ equals

$$
\boxed{ \sum x(n). }
$$

### 9. For even $N$,

$$
\boxed{ X(N/2)=\sum(-1)^nx(n). }
$$

### 10. Overlap-add requires

$$
\boxed{ \text{adding overlapping output samples}. }
$$

### 11. Overlap-save requires

$$
\boxed{ \text{discarding the first }N-1\text{ samples of each circular-convolution output}. }
$$

---

# 80. PROBLEM TYPES TO PRACTICE

## Type 1 — Direct DFT

Given

$$
x(n)=\{a,b,c,d\}
$$

find

$$
X(0),X(1),X(2),X(3).
$$

---

## Type 2 — Direct IDFT

Given $X(k)$, calculate $x(n)$ using the IDFT formula.

---

## Type 3 — DFT from Z-transform

1. Find $X(z)$.
2. Put

$$
z=e^{j2\pi k/N}.
$$

3. Evaluate for $k=0,\ldots,N-1$.

---

## Type 4 — Conjugate symmetry

Given half the DFT of a real sequence, find the remaining samples.

---

## Type 5 — Central ordinates

Use

$$
X(0)=\sum x(n)
$$

and, for even $N$,

$$
X(N/2)=\sum(-1)^nx(n).
$$

---

## Type 6 — Energy

Use

$$
E=\frac1N\sum|X(k)|^2.
$$

---

## Type 7 — DFT properties

Find the DFT of:

- delayed sequence;
- reversed sequence;
- conjugated sequence;
- modulated sequence;
- product;
- circular convolution.

---

## Type 8 — Linear convolution using DFT

Remember:

$$
\boxed{ N=L_x+L_h-1 }
$$

or any larger convenient value.

---

## Type 9 — Circular convolution

Use

$$
Y(k)=X(k)H(k)
$$

and IDFT.

---

## Type 10 — Overlap-add

Partition, convolve, shift, add overlap.

---

## Type 11 — Overlap-save

Prepend zeros, form overlapping blocks, circularly convolve, discard the first $N-1$, concatenate.

---

# 81. MATLAB PROGRAMS — CHAPTER 6

The chapter includes MATLAB programs for:

- Fourier series representation;
- Fourier series of a full-wave rectified signal;
- direct DFT/IDFT using matrix formulation;
- linear convolution using DFT;
- circular convolution using DFT;
- relation between DFTs of periodic even and odd parts of a real sequence.

---

## MATLAB — Direct DFT and IDFT

```matlab
clc;
clear all;
close all;

x = [1 -1 2 -2];

N = length(x);

W = dftmtx(N);

X = x * W;

disp('The discrete Fourier transform of the input sequence is');
disp(X);

Xi = conj(dftmtx(N));

x_rec = X * Xi / N;

disp('The inverse discrete Fourier transform is');
disp(x_rec);
```

For the textbook example,

$$
x=\{1,-1,2,-2\}
$$

the DFT is

$$
\boxed{ X=\{0,-1-j,6,-1+j\}. }
$$

---

# 82. MATLAB — LINEAR CONVOLUTION USING DFT

```matlab
clc;
clear all;
close all;

x = [1 2];
h = [2 1];

N = length(x) + length(h) - 1;

x1 = [x zeros(1, length(h)-1)];
h1 = [h zeros(1, length(x)-1)];

X = fft(x1);
H = fft(h1);

Y = X .* H;

y = ifft(Y);

disp('The linear convolution of the given sequences is');
disp(y);
```

Expected result:

```text
2     5     2
```

---

# 83. MATLAB — CIRCULAR CONVOLUTION USING DFT

```matlab
clc;
clear all;
close all;

x = [1 2 1 2];
h = [4 3 2 1];

X = fft(x);
H = fft(h);

Y = X .* H;

y = real(ifft(Y));

disp('The circular convolution of the given sequences is');
disp(y);
```

Expected result:

```text
14    16    14    16
```

---

# 84. MATLAB — FOURIER SERIES

The chapter's Fourier-series program uses symbolic integration to calculate Fourier-series coefficients at harmonic frequencies.

Core pattern:

```matlab
clc;
clear all;
close all;

syms t

T0 = 1;
N = 20;

% define one period of x(t)
% x = ...

for k = 1:N
    X1(k) = int(x * exp(-1j*2*pi*(k-1)*t/T0), t, 0, T0) / T0;
    X(k) = subs(X1(k));
    w(k) = (k-1)*2*pi/T0;
end

figure
stem(w, abs(X));
title('Magnitude of Fourier Series');

figure
stem(w, angle(X));
title('Phase of Fourier Series');
```

---

# 85. FINAL EXAM DECISION TREE

### If the question says “find DFT”

Use

$$
\boxed{ X(k)=\sum x(n)W_N^{nk}. }
$$

---

### If it says “find IDFT”

Use

$$
\boxed{ x(n)=\frac1N\sum X(k)W_N^{-nk}. }
$$

---

### If it gives $X(z)$ and asks for DFT

Use

$$
\boxed{ z=e^{j2\pi k/N}. }
$$

---

### If the input is real and only half the DFT is given

Use

$$
\boxed{ X(N-k)=X^*(k). }
$$

---

### If $X(0)$ is given

Use

$$
\boxed{ X(0)=\sum x(n). }
$$

---

### If $X(N/2)$ is given and $N$ is even

Use

$$
\boxed{ X(N/2)=\sum(-1)^nx(n). }
$$

---

### If the question asks for energy from $X(k)$

Use

$$
\boxed{ E=\frac1N\sum|X(k)|^2. }
$$

---

### If it asks for circular convolution

Use

$$
\boxed{ Y=XH,\qquad y=\text{IDFT}(Y). }
$$

---

### If it asks for linear convolution using DFT

First calculate

$$
\boxed{ N\ge L_x+L_h-1. }
$$

Then zero-pad, DFT, multiply, and IDFT.

---

### If it asks overlap-add

$$
\boxed{ \text{partition}\rightarrow \text{convolve}\rightarrow \text{shift}\rightarrow \text{add overlap}. }
$$

---

### If it asks overlap-save

$$
\boxed{ \text{prepend zeros}\rightarrow \text{overlap blocks}\rightarrow \text{circular convolution}\rightarrow \text{discard first }N-1\rightarrow \text{concatenate}. }
$$

---

# 86. LAST-MINUTE MEMORY SHEET

Memorize these:

$$
\boxed{ W_N=e^{-j2\pi/N} }
$$

$$
\boxed{ X(k)=\sum_{n=0}^{N-1}x(n)W_N^{nk} }
$$

$$
\boxed{ x(n)=\frac1N\sum_{k=0}^{N-1}X(k)W_N^{-nk} }
$$

$$
\boxed{ X(k)=X(z)|_{z=e^{j2\pi k/N}} }
$$

$$
\boxed{ X(0)=\sum x(n) }
$$

$$
\boxed{ X(N/2)=\sum(-1)^nx(n) }
$$

$$
\boxed{ X(N-k)=X^*(k) \quad\text{for real }x(n) }
$$

$$
\boxed{ \text{DFT}\{x\circledast h\}=XH }
$$

$$
\boxed{ \text{DFT}\{xh\} = \frac1N(X\circledast H) }
$$

$$
\boxed{ E=\sum|x(n)|^2 = \frac1N\sum|X(k)|^2 }
$$

$$
\boxed{ N_{\text{linear conv}} \ge L_x+L_h-1 }
$$

$$
\boxed{ \text{OLA: add overlap} }
$$

$$
\boxed{ \text{OLS: discard first }N-1 }
$$

$$
\boxed{ \text{DFT direct}\sim O(N^2) }
$$

$$
\boxed{ \text{FFT}\sim O(N\log_2N) }
$$

---

# 87. FINAL CHECKLIST BEFORE THE EXAM

- [ ] Can I write the DFT formula without looking?
- [ ] Can I write the IDFT formula and remember $1/N$?
- [ ] Can I calculate $W_4,W_8$ values?
- [ ] Can I solve a 4-point DFT directly?
- [ ] Can I solve a 4-point IDFT?
- [ ] Can I obtain a DFT from a Z-transform?
- [ ] Can I explain DFS?
- [ ] Can I distinguish DFS, DTFT and DFT?
- [ ] Can I use conjugate symmetry?
- [ ] Can I use $X(0)$ and $X(N/2)$?
- [ ] Can I use time shifting and reversal?
- [ ] Can I use circular convolution?
- [ ] Can I use Parseval?
- [ ] Can I calculate linear convolution using DFT?
- [ ] Do I know why zero-padding is required?
- [ ] Can I explain overlap-add?
- [ ] Can I explain overlap-save?
- [ ] Can I distinguish overlap-add from overlap-save?
- [ ] Can I write MATLAB `fft`, `ifft`, and DFT-matrix code?
- [ ] Do I remember the difference between linear and circular convolution?

---

## SOURCE NOTE

This document follows the Chapter 6 organization and terminology of A. Anand Kumar's *Digital Signal Processing*, covering the printed Chapter 6 range **412–478**. The textbook's numerical examples are used as the basis for the worked-example section; equations have been reformatted into readable LaTeX for study and exam use.
