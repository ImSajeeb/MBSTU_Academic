---
title: "Chapter 7 — Fast Fourier Transform (FFT)"
source: "Digital Signal Processing — A. Anand Kumar"
printed_pages: "479–547"
style: "Full theory + derivations + worked examples + exam preparation + MATLAB"
---

# CHAPTER 7 — FAST FOURIER TRANSFORM (FFT)

> **Source:** A. Anand Kumar, *Digital Signal Processing*, Chapter 7, printed pages **479–547**.
>
> This chapter is written as a study/exam guide while preserving the textbook's terminology, sequence of topics, formulas, examples, and problem types. Equations are reformatted in aligned LaTeX for readability.

---

# 1. CHAPTER ROADMAP

Chapter 7 develops efficient algorithms for computing the DFT.

Main topics:

1. Introduction to FFT
2. Fast Fourier Transform
3. Decimation-in-Time (DIT) radix-2 FFT
4. 8-point DFT using radix-2 DIT FFT
5. Butterfly diagram
6. Decimation-in-Frequency (DIF) radix-2 FFT
7. IDFT using FFT
8. DIT/DIF comparison
9. Fast convolution using FFT
10. Composite/mixed-radix FFT
11. Radix-3 FFT
12. Radix-4 FFT
13. Worked numerical examples
14. Review questions
15. Fill-in-the-blanks
16. Objective questions
17. Problems
18. MATLAB programs

---

# 2. WHY FFT IS REQUIRED

The $N$-point DFT is

$$
\boxed{
X(k)=
\sum_{n=0}^{N-1}
x(n)W_N^{nk},
\qquad
k=0,1,\ldots,N-1
}
$$

where

$$
\boxed{
W_N=e^{-j2\pi/N}.
}
$$

For every $k$, direct computation requires $N$ complex multiplications and $N-1$ complex additions.

Therefore, for all $N$ values,

$$
\boxed{
\text{complex multiplications}=N^2
}
$$

and

$$
\boxed{
\text{complex additions}=N(N-1).
}
$$

The same type of computational burden occurs in the IDFT.

The FFT was developed to reduce this computational burden.

---

# 3. DEFINITION OF FFT

The textbook defines the **Fast Fourier Transform (FFT)** as an algorithm/method for computing the DFT efficiently, with a reduced number of calculations.

The efficiency comes from a **divide-and-conquer approach**.

The basic idea is:

$$
\boxed{
N\text{-point DFT}
\rightarrow
\text{smaller DFTs}
\rightarrow
\text{still smaller DFTs}
\rightarrow
\text{2-point DFTs}
}
$$

The small transforms are then combined to obtain the complete $N$-point DFT.

The two basic FFT algorithms discussed in this chapter are:

$$
\boxed{
\text{DIT FFT}
}
$$

and

$$
\boxed{
\text{DIF FFT}.
}
$$

---

# 4. TWIDDLE FACTOR

The phase factor used in the DFT is

$$
\boxed{
W_N=e^{-j2\pi/N}.
}
$$

It is an $N$-th root of unity.

Important properties used by FFT are:

## 4.1 Periodicity

$$
\boxed{
W_N^{k+N}=W_N^k.
}
$$

## 4.2 Symmetry

$$
\boxed{
W_N^{k+N/2}=-W_N^k.
}
$$

These properties allow repeated calculations to be avoided.

---

# 5. DIRECT DFT COMPUTATION

For a complex-valued sequence,

$$
x(n)=x_R(n)+jx_I(n)
$$

and

$$
X(k)=X_R(k)+jX_I(k).
$$

Using

$$
e^{-j\theta}=\cos\theta-j\sin\theta,
$$

the DFT can be separated into real and imaginary parts.

The real part is

$$
\boxed{
X_R(k)=
\sum_{n=0}^{N-1}
\left[
x_R(n)\cos\frac{2\pi nk}{N}
+
x_I(n)\sin\frac{2\pi nk}{N}
\right].
}
$$

The imaginary part is

$$
\boxed{
X_I(k)=
\sum_{n=0}^{N-1}
\left[
x_I(n)\cos\frac{2\pi nk}{N}
-
x_R(n)\sin\frac{2\pi nk}{N}
\right].
}
$$

Direct computation also requires a large number of trigonometric evaluations.

FFT avoids much of this repeated work by exploiting twiddle-factor symmetry and periodicity.

---

# 6. COMPUTATIONAL REQUIREMENTS

## Direct DFT

$$
\boxed{
N^2\text{ complex multiplications}
}
$$

$$
\boxed{
N(N-1)\text{ complex additions}
}
$$

The textbook also gives the corresponding real-operation counts as

$$
\boxed{
4N^2\text{ real multiplications}
}
$$

and

$$
\boxed{
4N(N-1)\text{ real additions}
}
$$

in the direct calculation framework.

---

# 7. RADIX CONCEPT

Suppose

$$
N=r^m.
$$

Then:

- $r$ is called the **radix**;
- $m$ is the number of computational stages.

Thus,

$$
\boxed{
N=r^m.
}
$$

For radix-2,

$$
\boxed{
N=2^m
}
$$

and

$$
\boxed{
m=\log_2N.
}
$$

The radix-2 algorithm repeatedly decomposes the transform into two smaller transforms.

---

# 8. DIT RADIX-2 FFT

## 8.1 Meaning

**DIT** means **Decimation in Time**.

In DIT, the **time-domain sequence** $x(n)$ is decimated.

An $N$-point DFT is decomposed into:

$$
2\times(N/2)\text{-point DFTs}
$$

then

$$
4\times(N/4)\text{-point DFTs}
$$

and so on until 2-point DFTs are reached.

---

# 9. DIT RADIX-2 DERIVATION

Start with

$$
X(k)=
\sum_{n=0}^{N-1}
x(n)W_N^{nk}.
$$

Separate the even and odd samples.

For even indices,

$$
n=2r.
$$

For odd indices,

$$
n=2r+1.
$$

Therefore,

$$
\begin{aligned}
X(k)
&=
\sum_{r=0}^{N/2-1}
x(2r)W_N^{2rk}
+
\sum_{r=0}^{N/2-1}
x(2r+1)W_N^{(2r+1)k}.
\end{aligned}
$$

Since

$$
W_N^{2k}=W_{N/2}^{k},
$$

we obtain

$$
\boxed{
X(k)=
F_1(k)+W_N^kF_2(k)
}
$$

where

$$
\boxed{
F_1(k)=
\sum_{r=0}^{N/2-1}
x(2r)W_{N/2}^{rk}
}
$$

and

$$
\boxed{
F_2(k)=
\sum_{r=0}^{N/2-1}
x(2r+1)W_{N/2}^{rk}.
}
$$

Thus $F_1(k)$ and $F_2(k)$ are two $(N/2)$-point DFTs.

---

# 10. DIT SECOND HALF OF OUTPUT

Because the $(N/2)$-point DFTs are periodic with period $N/2$,

$$
F_1(k+N/2)=F_1(k)
$$

and

$$
F_2(k+N/2)=F_2(k).
$$

Also,

$$
W_N^{k+N/2}
=
-W_N^k.
$$

Therefore,

$$
\boxed{
X(k)=
F_1(k)+W_N^kF_2(k),
\qquad
0\le k<N/2
}
$$

and

$$
\boxed{
X(k+N/2)=
F_1(k)-W_N^kF_2(k).
}
$$

This is the basic radix-2 DIT combining equation.

---

# 11. DIT BUTTERFLY

The basic DIT butterfly takes two complex inputs $a$ and $b$.

First multiply $b$ by the twiddle factor:

$$
bW_N^k.
$$

Then form:

$$
\boxed{
A=a+bW_N^k
}
$$

and

$$
\boxed{
B=a-bW_N^k.
}
$$

So the DIT butterfly is

$$
\boxed{
\begin{aligned}
A&=a+W_N^kb,\\
B&=a-W_N^kb.
\end{aligned}
}
$$

### Memory rule

> **DIT: multiply $b$ by the phase factor first, then add/subtract.**

---

# 12. RADIX-2 DIT COMPUTATIONAL REQUIREMENTS

For an $N$-point radix-2 FFT:

$$
\boxed{
\text{stages}=\log_2N
}
$$

and each stage has

$$
\boxed{
N/2\text{ butterflies}.
}
$$

Each butterfly requires one complex multiplication and two complex additions in the general arithmetic representation.

Therefore the textbook gives:

$$
\boxed{
\text{complex multiplications}
=
\frac{N}{2}\log_2N
}
$$

and

$$
\boxed{
\text{complex additions}
=
N\log_2N.
}
$$

---

# 13. BIT-REVERSED INPUT IN DIT

For radix-2 DIT FFT, the input is arranged in **bit-reversed order**.

For an 8-point transform:

$$
\begin{array}{c|c|c}
\text{Normal index}&\text{Binary}&\text{Bit-reversed index}\\
\hline
0&000&000\\
1&001&100\\
2&010&010\\
3&011&110\\
4&100&001\\
5&101&101\\
6&110&011\\
7&111&111
\end{array}
$$

Therefore the input order becomes

$$
\boxed{
\{x(0),x(4),x(2),x(6),x(1),x(5),x(3),x(7)\}.
}
$$

The output is in normal order.

---

# 14. 8-POINT DIT FFT

For

$$
N=8=2^3,
$$

there are

$$
\boxed{
3\text{ stages}.
}
$$

The decomposition is:

$$
8\rightarrow4+4
$$

$$
4\rightarrow2+2
$$

and finally the 2-point transforms are combined.

The four 2-point subsequences are

$$
\boxed{
\{x(0),x(4)\}
}
$$

$$
\boxed{
\{x(2),x(6)\}
}
$$

$$
\boxed{
\{x(1),x(5)\}
}
$$

$$
\boxed{
\{x(3),x(7)\}.
}
$$

The three stages contain

$$
\boxed{
4\text{ butterflies per stage}.
}
$$

Hence total butterflies:

$$
\boxed{
4\times3=12.
}
$$

---

# 15. 8-POINT DIT — STAGE STRUCTURE

Define

$$
g_{11}(n)=\{x(0),x(4)\}
$$

$$
g_{12}(n)=\{x(2),x(6)\}
$$

$$
g_{21}(n)=\{x(1),x(5)\}
$$

$$
g_{22}(n)=\{x(3),x(7)\}.
$$

Their 2-point DFTs are computed first.

For example,

$$
G_{11}(0)=x(0)+x(4)
$$

$$
G_{11}(1)=x(0)-x(4).
$$

Similarly,

$$
G_{12}(0)=x(2)+x(6)
$$

$$
G_{12}(1)=x(2)-x(6)
$$

and corresponding equations apply to $G_{21}$ and $G_{22}$.

---

# 16. SECOND DIT STAGE

The 2-point DFTs are combined into 4-point DFTs.

For the first branch,

$$
\boxed{
F_1(k)
=
G_{11}(k)+W_4^kG_{12}(k)
}
$$

and similarly,

$$
\boxed{
F_2(k)
=
G_{21}(k)+W_4^kG_{22}(k).
}
$$

Because

$$
W_4=-j,
$$

the required factors are

$$
W_4^0=1,
\qquad
W_4^1=-j.
$$

---

# 17. THIRD DIT STAGE

The two 4-point transforms are combined into the final 8-point DFT:

$$
\boxed{
X(k)=F_1(k)+W_8^kF_2(k).
}
$$

For

$$
k=0,1,2,3,
$$

these produce the first four outputs.

The remaining outputs follow from

$$
\boxed{
X(k+4)=F_1(k)-W_8^kF_2(k).
}
$$

The textbook's complete 8-point DIT flow graph contains three stages.

---

# 18. DIT BUTTERFLY — WHAT TO REMEMBER

For inputs $a,b$:

$$
\boxed{
A=a+bW_N^k
}
$$

$$
\boxed{
B=a-bW_N^k.
}
$$

The multiplication occurs **before** the add/subtract operation.

---

# 19. DIF RADIX-2 FFT

## 19.1 Meaning

**DIF** means **Decimation in Frequency**.

In DIF, the frequency-domain sequence $X(k)$ is decimated.

The $N$-point transform is converted into two $N/2$-point transforms, then four $N/4$-point transforms, and so on until $N/2$ two-point transforms are obtained.

---

# 20. DIF DERIVATION

Split the input into first and second halves:

$$
x(0),x(1),\ldots,x(N/2-1)
$$

and

$$
x(N/2),x(N/2+1),\ldots,x(N-1).
$$

Starting with

$$
X(k)=
\sum_{n=0}^{N-1}
x(n)W_N^{nk},
$$

we obtain

$$
\boxed{
X(k)=
\sum_{n=0}^{N/2-1}
\left[
x(n)+x(n+N/2)
\right]
W_N^{nk}
}
$$

for the even-frequency outputs.

For the odd-frequency outputs, the difference sequence appears:

$$
\boxed{
X(k+N/2)=
\sum_{n=0}^{N/2-1}
\left[
x(n)-x(n+N/2)
\right]
W_N^{nk}
}
$$

with the appropriate phase-factor arrangement.

The key point is that DIF first forms **sum and difference** terms and then applies the twiddle factor to the difference branch.

---

# 21. DIF BUTTERFLY

For inputs $a$ and $b$:

First compute

$$
\boxed{
A=a+b
}
$$

and

$$
\boxed{
a-b.
}
$$

Then multiply the difference by the phase factor:

$$
\boxed{
B=(a-b)W_N^k.
}
$$

Thus,

$$
\boxed{
\begin{aligned}
A&=a+b,\\
B&=(a-b)W_N^k.
\end{aligned}
}
$$

### Memory rule

> **DIF: add/subtract first, multiply the difference afterward.**

---

# 22. DIT VS DIF

| Radix-2 DIT | Radix-2 DIF |
|---|---|
| Decimation is in time | Decimation is in frequency |
| Time-domain sequence is decomposed | Frequency-domain sequence is decomposed |
| Input is bit-reversed | Input is normal |
| Output is normal | Output is bit-reversed |
| Twiddle multiplication occurs before add/subtract | Twiddle multiplication occurs after add/subtract |
| $N=2^m$ | $N=2^m$ |
| $m=\log_2N$ stages | $m=\log_2N$ stages |
| $N/2$ butterflies per stage | $N/2$ butterflies per stage |
| Same total arithmetic count | Same total arithmetic count |

### Most important distinction

$$
\boxed{
\text{DIT: bit-reversed input, normal output}
}
$$

$$
\boxed{
\text{DIF: normal input, bit-reversed output}
}
$$

---

# 23. DIT AND DIF SIMILARITIES

Both:

1. compute the same DFT;
2. use divide-and-conquer decomposition;
3. require $\log_2N$ stages for radix-2;
4. require $N/2$ butterflies per stage;
5. require

$$
\frac N2\log_2N
$$

complex multiplications;
6. require

$$
N\log_2N
$$

complex additions;
7. use bit reversal at some point in the computation.

---

# 24. EXAMPLE 7.1 — 8-POINT DIT BUTTERFLY DIAGRAM

**Question:** Draw the butterfly line diagram for 8-point FFT calculation and briefly explain using DIT.

For

$$
N=8=2^3,
$$

there are

$$
3
$$

stages.

The input must be supplied in bit-reversed order:

$$
\boxed{
x_r(n)=
\{x(0),x(4),x(2),x(6),x(1),x(5),x(3),x(7)\}.
}
$$

Each stage has

$$
N/2=4
$$

butterflies.

Stage 1:

$$
2\text{-point DFTs}.
$$

Stage 2:

$$
4\text{-point DFTs}.
$$

Stage 3:

$$
8\text{-point DFT}.
$$

Arithmetic requirement:

$$
\boxed{
8\log_28=24
}
$$

complex additions and

$$
\boxed{
\frac82\log_28=12
}
$$

complex multiplications.

---

# 25. EXAMPLE 7.2 — 8-POINT DIF FFT

For $N=8$, the radix-2 DIF FFT has three stages.

Input:

$$
\boxed{
\text{normal order}
}
$$

Output:

$$
\boxed{
\text{bit-reversed order}.
}
$$

At each stage, the input sequence is divided into smaller sequences until 2-point sequences are reached.

The final 2-point DFTs produce all eight DFT samples.

---

# 26. EXAMPLE 7.3 — 16-POINT DIT FFT

For

$$
N=16=2^4,
$$

the DIT FFT has

$$
\boxed{
4\text{ stages}.
}
$$

The bit-reversed input order is

$$
\boxed{
\begin{aligned}
\{&
x(0),x(8),x(4),x(12),\\
&x(2),x(10),x(6),x(14),\\
&x(1),x(9),x(5),x(13),\\
&x(3),x(11),x(7),x(15)
\}.
\end{aligned}
}
$$

Number of complex multiplications:

$$
\boxed{
\frac{16}{2}\log_2 16
=
8(4)
=
32.
}
$$

Number of complex additions:

$$
\boxed{
16\log_216
=
64.
}
$$

Direct DFT:

$$
16^2=256
$$

complex multiplications and

$$
16(15)=240
$$

complex additions.

---

# 27. EXAMPLE 7.4 — 32-POINT FFT

For

$$
N=32=2^5,
$$

the number of stages is

$$
\boxed{
5.
}
$$

Complex multiplications:

$$
\boxed{
\frac{32}{2}\log_232
=
16(5)
=
80.
}
$$

Complex additions:

$$
\boxed{
32\log_232
=
160.
}
$$

The direct DFT would require

$$
32^2=1024
$$

complex multiplications and

$$
32(31)=992
$$

complex additions.

---

# 28. EXAMPLE 7.5 — IDFT USING FFT

The IDFT is

$$
x(n)=
\frac1N
\sum_{k=0}^{N-1}
X(k)W_N^{-nk}.
$$

The textbook shows that it can be obtained from a forward DFT/FFT.

### Procedure

1. Take the conjugate of $X(k)$:

$$
X^*(k).
$$

2. Compute the $N$-point DFT of $X^*(k)$ using FFT.

3. Take the conjugate of the FFT output.

4. Divide by $N$.

Thus,

$$
\boxed{
x(n)
=
\frac1N
\left[
\operatorname{DFT}\{X^*(k)\}
\right]^*.
}
$$

Therefore a single FFT algorithm can be used for both DFT and IDFT.

---

# 29. EXAMPLE 7.6 — 4-POINT DFT BY DIT AND DIF

Given

$$
\boxed{
x(n)=\{2,1,4,3\}.
}
$$

## (a) DIT

Bit-reversed input:

$$
\boxed{
x_r(n)=\{x(0),x(2),x(1),x(3)\}
}
$$

so

$$
\boxed{
x_r(n)=\{2,4,1,3\}.
}
$$

The 4-point DFT is

$$
\boxed{
X(k)=
\{10,-2+j2,2,-2-j2\}.
}
$$

## (b) DIF

Input remains in normal order.

The output is generated in bit-reversed order and then rearranged.

Final normal-order result:

$$
\boxed{
X(k)=
\{10,-2+j2,2,-2-j2\}.
}
$$

### Magnitude

$$
\boxed{
|X(k)|=
\{10,\;2.828,\;2,\;2.828\}.
}
$$

### Phase

$$
\boxed{
\angle X(k)=
\{0,\;135^\circ,\;0,\;-135^\circ\}
}
$$

under the usual principal-angle interpretation.

---

# 30. EXAMPLE 7.7 — CIRCULAR CONVOLUTION USING FFT

Given

$$
x_1(n)=\{1,2,0,1\}
$$

and

$$
x_2(n)=\{2,2,1,1\}.
$$

The DFTs are

$$
\boxed{
X_1(k)=\{4,-1-j,2,-1+j\}
}
$$

and

$$
\boxed{
X_2(k)=\{6,-1-j,0,-1+j\}.
}
$$

Multiply:

$$
Y(k)=X_1(k)X_2(k).
$$

Then use the IDFT.

The result is

$$
\boxed{
x_1(n)\circledast x_2(n)
=
\{6,7,6,5\}.
}
$$

Thus FFT performs circular convolution efficiently through

$$
\boxed{
Y(k)=X_1(k)X_2(k).
}
$$

---

# 31. EXAMPLE 7.8 — DFT OF A SQUARE-WAVE SEQUENCE

For the even-$N$ square-wave sequence

$$
x(n)=
\begin{cases}
1,&0\le n<N/2,\\
-1,&N/2\le n<N,
\end{cases}
$$

take

$$
N=4.
$$

Then

$$
\boxed{
x(n)=\{1,1,-1,-1\}.
}
$$

Using 4-point radix-2 DIT FFT,

$$
\boxed{
X(k)=\{0,2-j2,0,2+j2\}.
}
$$

This example is useful for recognizing the spectrum of an alternating/square-like finite sequence.

---

# 32. EXAMPLE 7.9 — LTI RESPONSE USING DIT FFT

Given

$$
x(n)=\{2,2,2\}
$$

and

$$
h(n)=\{-2,-2\}.
$$

The output is

$$
y(n)=x(n)*h(n).
$$

Since

$$
L_x=3,\qquad L_h=2,
$$

the linear-convolution length is

$$
\boxed{
L_y=3+2-1=4.
}
$$

Therefore convert to 4-point sequences:

$$
x(n)=\{2,2,2,0\}
$$

$$
h(n)=\{-2,-2,0,0\}.
$$

Compute

$$
X(k)=\operatorname{DFT}\{x(n)\}
$$

and

$$
H(k)=\operatorname{DFT}\{h(n)\}.
$$

Then

$$
\boxed{
Y(k)=X(k)H(k).
}
$$

Finally,

$$
\boxed{
y(n)=\operatorname{IDFT}\{Y(k)\}.
}
$$

### Four-step FFT convolution procedure

1. Find $X(k)$ using FFT.
2. Find $H(k)$ using FFT.
3. Multiply $X(k)H(k)$.
4. Find the IDFT using FFT.

---

# 33. EXAMPLE 7.10 — LTI RESPONSE USING DIT FFT

Given

$$
h(n)=\{0.5,1\}
$$

and

$$
x(n)=\{1,0.5,0\}.
$$

Required length:

$$
N=3+2-1=4.
$$

Zero-pad:

$$
x(n)=\{1,0.5,0,0\}
$$

$$
h(n)=\{0.5,1,0,0\}.
$$

The textbook obtains

$$
X(k)=
\{1.5,1-j0.5,0.5,1+j0.5\}
$$

and

$$
H(k)=
\{1.5,0.5-j1,-0.5,0.5+j1\}.
$$

Therefore,

$$
Y(k)=X(k)H(k)
$$

and after the FFT-based IDFT,

$$
\boxed{
y(n)=\{0.5,1.25,0.5,0\}.
}
$$

This is the linear convolution.

---

# 34. EXAMPLE 7.11 — DFT OF AN IMPULSE SEQUENCE

Given

$$
x(n)=\{1,0,0,0,0,0,0,0\}.
$$

Directly,

$$
X(k)=
\sum_{n=0}^{7}x(n)W_8^{nk}.
$$

Only $n=0$ contributes:

$$
\boxed{
X(k)=1
}
$$

for every $k$.

Therefore,

$$
\boxed{
X(k)=
\{1,1,1,1,1,1,1,1\}.
}
$$

FFT gives the same result.

---

# 35. EXAMPLE 7.12 — 8-POINT DFT USING DIT AND DIF

Given

$$
x(n)=
\{2,2,2,2,1,1,1,1\}.
$$

For DIT, bit-reversed input is

$$
\boxed{
\{2,1,2,1,2,1,2,1\}.
}
$$

The final DFT is

$$
\boxed{
\begin{aligned}
X(k)=\{&
12,\,
-1-j2.414,\,
0,\,
-1-j0.414,\\
&0,\,
-1+j0.414,\,
0,\,
-1+j2.414
\}.
\end{aligned}
}
$$

DIF gives the same DFT after rearranging its bit-reversed output.

Magnitude values are approximately

$$
\boxed{
\{12,\;2.61,\;0,\;1.08,\;0,\;1.08,\;0,\;2.61\}.
}
$$

The corresponding phase values follow from the rectangular coordinates.

---

# 36. MAGNITUDE AND PHASE SPECTRUM

If

$$
X(k)=X_R(k)+jX_I(k),
$$

then

$$
\boxed{
|X(k)|=
\sqrt{X_R^2(k)+X_I^2(k)}.
}
$$

Phase is

$$
\boxed{
\angle X(k)
=
\tan^{-1}
\left(
\frac{X_I(k)}{X_R(k)}
\right)
}
$$

with the appropriate quadrant interpretation.

The **magnitude spectrum** is the sequence

$$
\boxed{
|X(k)|.
}
$$

The **phase spectrum** is

$$
\boxed{
\angle X(k).
}
$$

For an $N$-point DFT,

$$
\boxed{
X(k+N)=X(k).
}
$$

Therefore magnitude and phase spectra are also periodic with period $N$.

---

# 37. EXAMPLE 7.13 — 8-POINT DFT

Given

$$
x(n)=
\{2,1,2,1,2,1,2,1\}.
$$

Bit-reversed input for DIT:

$$
\boxed{
\{2,2,2,2,1,1,1,1\}.
}
$$

The DFT is

$$
\boxed{
X(k)=
\{12,0,0,0,4,0,0,0\}.
}
$$

---

# 38. EXAMPLE 7.14 — 8-POINT DFT OF ALL ONES

Given

$$
x(n)=
\{1,1,1,1,1,1,1,1\}.
$$

The DFT is

$$
\boxed{
X(k)=
\{8,0,0,0,0,0,0,0\}.
}
$$

This follows because the nonzero samples cancel at all nonzero DFT frequencies.

---

# 39. EXAMPLE 7.15 — DIT FFT OF A SYMMETRIC SEQUENCE

Given

$$
x(n)=
\{1,2,3,4,4,3,2,1\}.
$$

Bit-reversed input:

$$
\boxed{
\{1,4,3,2,2,3,4,1\}.
}
$$

The textbook obtains

$$
\boxed{
\begin{aligned}
X(k)=\{&
20,\,
5.828-j2.414,\,
0,\,
0.172-j0.414,\\
&0,\,
0.172+j0.414,\,
0,\,
5.828+j2.414
\}.
\end{aligned}
}
$$

---

# 40. EXAMPLE 7.16 — DIT FFT OF $x(n)=n$

Given

$$
x(n)=\{0,1,2,3,4,5,6,7\}.
$$

Bit-reversed input:

$$
\boxed{
\{0,4,2,6,1,5,3,7\}.
}
$$

The textbook obtains

$$
\boxed{
\begin{aligned}
X(k)=\{&
28,\,
-4+j9.656,\,
-4+j4,\,
-4+j1.656,\\
&-4,\,
-4-j1.656,\,
-4-j4,\,
-4-j9.656
\}.
\end{aligned}
}
$$

---

# 41. EXAMPLE 7.17 — LTI RESPONSE BY DIT FFT

Given

$$
x(n)=\{-1,1,2,1,-1\}
$$

and

$$
h(n)=\{-1,1,-1,1\}.
$$

Output length:

$$
\boxed{
5+4-1=8.
}
$$

Zero-pad:

$$
x(n)=
\{-1,1,2,1,-1,0,0,0\}
$$

$$
h(n)=
\{-1,1,-1,1,0,0,0,0\}.
$$

Compute

$$
X(k)
$$

and

$$
H(k)
$$

using 8-point DIT FFT.

Then

$$
Y(k)=X(k)H(k)
$$

and use FFT-based IDFT.

The resulting output is the linear convolution of $x(n)$ and $h(n)$.

---

# 42. EXAMPLE 7.18 — IDFT USING DIF FFT

Given

$$
\boxed{
X(k)=
\{4,-1-j2.414,0,-1-j0.414,0,-1+j0.414,0,-1+j2.414\}.
}
$$

Procedure:

1. Find $X^*(k)$.
2. Apply 8-point DIF FFT to $X^*(k)$.
3. Obtain $8x^*(n)$ in the DIF output order.
4. Take the conjugate.
5. Divide by 8.

The textbook obtains

$$
\boxed{
x(n)=\{1,1,1,1,0,0,0,0\}.
}
$$

---

# 43. EXAMPLE 7.19 — IDFT USING DIT FFT

Given the 8-point frequency sequence

$$
\boxed{
\begin{aligned}
X(k)=\{&
7,\,
-0.707-j0.707,\,
0,\,
-0.707+j0.707,\\
&-1,\,
-0.707-j0.707,\,
0,\,
-0.707+j0.707
\}.
\end{aligned}
}
$$

Take the conjugate:

$$
X^*(k).
$$

Arrange $X^*(k)$ in bit-reversed order for DIT.

Compute the DFT using radix-2 DIT FFT, conjugate the result, and divide by 8.

The textbook obtains

$$
\boxed{
x(n)=\{1,1,1,1,1,1,1,0\}.
}
$$

---

# 44. EXAMPLE 7.20 — IDFT OF A SQUARE-WAVE SPECTRUM

Given

$$
X(k)=\{12,0,0,0,4,0,0,0\}.
$$

Using the IDFT-through-FFT method:

$$
X^*(k)=X(k).
$$

Apply 8-point DIF FFT.

The textbook obtains

$$
\boxed{
x(n)=\{2,1,2,1,2,1,2,1\}.
}
$$

---

# 45. EXAMPLE 7.21 — IDFT EXAMPLES

## (a)

Given

$$
X(k)=
\{1,1-j2,-1,1+j2\}.
$$

The textbook obtains

$$
\boxed{
x(n)=
\{0.5,1.5,0.5,0.5\}.
}
$$

## (b)

Given

$$
X(k)=\{1,0,1,0\},
$$

the textbook obtains

$$
\boxed{
x(n)=
\{0.5,0,0.5,0\}.
}
$$

## (c)

Given

$$
X(k)=\{3,2+j,1,2-j\},
$$

the textbook obtains

$$
\boxed{
x(n)=\{2,0,0,1\}.
}
$$

---

# 46. COMPOSITE / MIXED-RADIX FFT

Radix-2 requires

$$
N=2^m.
$$

However, sometimes $N$ is not a power of 2.

If $N$ is composite, it may be factorized as

$$
\boxed{
N=p_1p_2\cdots p_m.
}
$$

A **composite or mixed-radix FFT** is used when $N$ has more than one prime factor.

Examples given in the textbook include:

$$
\boxed{
N=6,\;10,\;12.
}
$$

The sequence is decomposed according to the factors.

---

# 47. RADIX-3 FFT

When

$$
\boxed{
N=3^m,
}
$$

a radix-3 FFT can be developed.

For example,

$$
N=9=3^2.
$$

The sequence is decomposed into three 3-point subsequences:

$$
\boxed{
\{x(0),x(3),x(6)\}
}
$$

$$
\boxed{
\{x(1),x(4),x(7)\}
}
$$

$$
\boxed{
\{x(2),x(5),x(8)\}.
}
$$

---

# 48. EXAMPLE 7.22 — RADIX-3 DIT FFT FOR $N=9$

For

$$
N=9=3\times3,
$$

define three 3-point subsequences.

The textbook obtains the decomposition

$$
\boxed{
X(k)
=
X_1(k)
+
W_9^kX_2(k)
+
W_9^{2k}X_3(k).
}
$$

The three subsequences are formed from samples separated by three indices:

$$
X_1(k):
\quad
x(0),x(3),x(6)
$$

$$
X_2(k):
\quad
x(1),x(4),x(7)
$$

$$
X_3(k):
\quad
x(2),x(5),x(8).
$$

The final 9-point DFT is obtained by combining their 3-point DFTs with the appropriate radix-3 phase factors.

---

# 49. RADIX-3 DIF FFT

For radix-3 DIF, the input is decomposed so that three smaller frequency-domain transforms are obtained.

For $N=9$, form combinations of:

$$
x(n),\quad x(n+3),\quad x(n+6).
$$

The sum and weighted differences are then transformed by 3-point DFTs.

The final outputs are obtained by combining the three smaller transforms.

---

# 50. EXAMPLE 7.23 — RADIX-3 DIF FFT FOR $N=9$

For

$$
N=9=3\times3,
$$

the DIF decomposition forms three 3-point branches.

The first branch uses

$$
\boxed{
f(n)=x(n)+x(n+3)+x(n+6)
}
$$

and corresponding phase-weighted combinations are formed for the other branches.

The three resulting 3-point DFTs produce the nine output samples after recombination.

The textbook's Figure 7.49 shows the radix-3 DIF flow diagram.

---

# 51. EXAMPLE 7.24 — DIT FFT FOR $N=6$

The textbook develops two factorizations:

### (a)

$$
\boxed{
N=2\times3
}
$$

The sequence is split into two 3-sample subsequences:

$$
\{x(0),x(2),x(4)\}
$$

and

$$
\{x(1),x(3),x(5)\}.
$$

### (b)

$$
\boxed{
N=3\times2
}
$$

The sequence is split into three 2-sample subsequences.

For

$$
x(n)=\{1,1,1,2,2,2\},
$$

the textbook obtains

$$
\boxed{
X(k)=
\{9,\,-1+j1.732,\,0,\,-1,\,0,\,-1-j1.732\}.
}
$$

The two factorizations provide alternative mixed-radix decompositions of the same DFT.

---

# 52. EXAMPLE 7.25 — DIF FFT FOR $N=6$

The textbook develops DIF decompositions for:

### (a)

$$
N=3\times2
$$

Two 3-sample sequences are formed.

### (b)

$$
N=2\times3
$$

Three 2-sample sequences are formed.

For DIF, the input is in normal order and the final output is in bit-reversed/shuffled order appropriate to the decomposition.

The basic principle remains:

$$
\boxed{
\text{DIF = decimation of frequency components}.
}
$$

---

# 53. RADIX-4 FFT

When

$$
\boxed{
N=4^m,
}
$$

a radix-4 FFT can be used.

For example,

$$
16=4^2.
$$

The input sequence can be decimated into four sequences of length $N/4$:

$$
\boxed{
\{x(0),x(4),x(8),x(12)\}
}
$$

$$
\boxed{
\{x(1),x(5),x(9),x(13)\}
}
$$

$$
\boxed{
\{x(2),x(6),x(10),x(14)\}
}
$$

$$
\boxed{
\{x(3),x(7),x(11),x(15)\}.
}
$$

The textbook notes that radix-4 DIT and DIF algorithms can be developed from the DFT fundamentals.

---

# 54. WHY RADIX-2 IS IMPORTANT

When

$$
N=2^m,
$$

the decomposition reaches 2-point DFTs.

A 2-point DFT is simply

$$
\boxed{
X(0)=x(0)+x(1)
}
$$

$$
\boxed{
X(1)=x(0)-x(1).
}
$$

Thus every final computation becomes a simple butterfly.

This makes radix-2 FFT particularly simple to implement.

---

# 55. FFT COMPLEXITY — MASTER TABLE

| Method | Complex multiplications | Complex additions |
|---|---:|---:|
| Direct DFT | $N^2$ | $N(N-1)$ |
| Radix-2 FFT | $\frac N2\log_2N$ | $N\log_2N$ |

The asymptotic computational behavior changes from approximately

$$
\boxed{
O(N^2)
}
$$

to

$$
\boxed{
O(N\log_2N).
}
$$

---

# 56. EXAMPLE — 64-POINT DFT

For

$$
N=64,
$$

direct computation requires

$$
64^2=4096
$$

complex multiplications.

FFT requires

$$
\frac{64}{2}\log_264
=
32(6)
=
\boxed{192}
$$

complex multiplications.

This is one of the textbook's direct comparisons.

---

# 57. EXAMPLE — 256-POINT FFT SAVING

For

$$
N=256,
$$

direct DFT multiplications:

$$
256^2=65536.
$$

Radix-2 FFT multiplications:

$$
\frac{256}{2}\log_2256
=
128(8)
=
1024.
$$

Percentage saving:

$$
\begin{aligned}
\%S_M
&=
\frac{65536-1024}{65536}\times100\\
&=\boxed{98.43\%}.
\end{aligned}
$$

Direct additions:

$$
256(255)=65280.
$$

FFT additions:

$$
256(8)=2048.
$$

Percentage saving:

$$
\begin{aligned}
\%S_A
&=
\frac{65280-2048}{65280}\times100\\
&=\boxed{96.86\%}.
\end{aligned}
$$

---

# 58. FAST CONVOLUTION

The response of an LTI system is

$$
\boxed{
y(n)=x(n)*h(n).
}
$$

Direct evaluation of convolution may require a large number of calculations.

Using DFT/FFT:

$$
X(k)=\operatorname{DFT}\{x(n)\}
$$

$$
H(k)=\operatorname{DFT}\{h(n)\}
$$

then

$$
\boxed{
Y(k)=X(k)H(k)
}
$$

and

$$
\boxed{
y(n)=\operatorname{IDFT}\{Y(k)\}.
}
$$

The textbook calls convolution performed using FFT **fast convolution**.

---

# 59. LINEAR CONVOLUTION USING FFT

For

$$
L_x=\text{length of }x(n)
$$

and

$$
L_h=\text{length of }h(n),
$$

the required linear-convolution length is

$$
\boxed{
L_y=L_x+L_h-1.
}
$$

Since DFT multiplication naturally gives circular convolution, zero-pad to

$$
\boxed{
N\ge L_x+L_h-1.
}
$$

Then:

$$
\boxed{
Y(k)=X(k)H(k)
}
$$

and

$$
\boxed{
y(n)=\operatorname{IDFT}\{Y(k)\}.
}
$$

---

# 60. EXAMPLE — FAST LTI CONVOLUTION

Suppose

$$
x(n)=\{1,2\}
$$

and

$$
h(n)=\{2,1\}.
$$

Then

$$
L_y=2+2-1=3.
$$

Use a 3-point transform if convenient.

Zero-pad:

$$
x=\{1,2,0\}
$$

$$
h=\{2,1,0\}.
$$

Then

$$
Y=XH
$$

and IDFT gives

$$
\boxed{
y=\{2,5,2\}.
}
$$

The FFT method is the computational version of the same DFT convolution theorem.

---

# 61. DIRECT / SLOW CONVOLUTION VS FAST CONVOLUTION

### Slow convolution

Convolution is evaluated directly using the convolution sum.

$$
\boxed{
y(n)=
\sum_kx(k)h(n-k).
}
$$

### Fast convolution

Use FFT:

$$
\boxed{
x
\rightarrow
\text{FFT}
\rightarrow
X
}
$$

$$
\boxed{
h
\rightarrow
\text{FFT}
\rightarrow
H
}
$$

$$
\boxed{
Y=XH
}
$$

$$
\boxed{
y=\text{IFFT}(Y).
}
$$

The FFT reduces the transform-computation cost.

---

# 62. IDFT USING FFT — ALGORITHM

Given $X(k)$:

### Step 1

$$
X^*(k)
$$

### Step 2

Compute

$$
\operatorname{DFT}\{X^*(k)\}
$$

using DIT or DIF FFT.

### Step 3

Take the conjugate.

### Step 4

Divide by $N$.

Therefore,

$$
\boxed{
x(n)=
\frac1N
\left[
\operatorname{FFT}\{X^*(k)\}
\right]^*.
}
$$

---

# 63. BASIC DIT BUTTERFLY — EXAM DIAGRAM IN WORDS

For two inputs $a,b$:

$$
b
\rightarrow
bW_N^k
$$

then

$$
a+bW_N^k
$$

and

$$
a-bW_N^k.
$$

Therefore:

$$
\boxed{
A=a+bW_N^k
}
$$

$$
\boxed{
B=a-bW_N^k.
}
$$

---

# 64. BASIC DIF BUTTERFLY — EXAM DIAGRAM IN WORDS

For two inputs $a,b$:

First:

$$
A=a+b
$$

and

$$
D=a-b.
$$

Then:

$$
\boxed{
B=DW_N^k=(a-b)W_N^k.
}
$$

So:

$$
\boxed{
A=a+b
}
$$

$$
\boxed{
B=(a-b)W_N^k.
}
$$

---

# 65. BIT REVERSAL — HOW TO DO IT

Suppose $N=8$.

Write each index using three binary bits:

$$
0=000
$$

$$
1=001
$$

$$
2=010
$$

$$
3=011
$$

$$
4=100
$$

$$
5=101
$$

$$
6=110
$$

$$
7=111.
$$

Reverse the bits:

$$
000\to000
$$

$$
001\to100
$$

$$
010\to010
$$

$$
011\to110
$$

etc.

Therefore:

$$
\boxed{
0,4,2,6,1,5,3,7.
}
$$

This is the standard 8-point DIT input ordering.

---

# 66. DIT/DIF EXAM MEMORY TRICK

### DIT

Think:

$$
\boxed{
\text{Time is Decimated}
}
$$

and

$$
\boxed{
\text{Input Bit-reversed}.
}
$$

### DIF

Think:

$$
\boxed{
\text{Frequency is Decimated}
}
$$

and

$$
\boxed{
\text{Output Bit-reversed}.
}
$$

---

# 67. IMPORTANT PHASE FACTORS FOR 8-POINT FFT

For the third stage of an 8-point DIT FFT, the relevant factors are

$$
\boxed{
W_8^0,\;
W_8^1,\;
W_8^2,\;
W_8^3.
}
$$

Numerically,

$$
W_8^0=1
$$

$$
W_8^1=e^{-j\pi/4}
=
\frac{1}{\sqrt2}(1-j)
$$

$$
W_8^2=-j
$$

$$
W_8^3=
-\frac{1}{\sqrt2}(1+j).
$$

Useful identities:

$$
W_8^4=-1.
$$

---

# 68. EXAM FORMULA SHEET

## DFT

$$
\boxed{
X(k)=
\sum_{n=0}^{N-1}
x(n)W_N^{nk}
}
$$

## IDFT

$$
\boxed{
x(n)=
\frac1N
\sum_{k=0}^{N-1}
X(k)W_N^{-nk}
}
$$

## Twiddle factor

$$
\boxed{
W_N=e^{-j2\pi/N}
}
$$

## DIT decomposition

$$
\boxed{
X(k)=F_1(k)+W_N^kF_2(k)
}
$$

$$
\boxed{
X(k+N/2)=F_1(k)-W_N^kF_2(k)
}
$$

## DIT butterfly

$$
\boxed{
A=a+W_N^kb
}
$$

$$
\boxed{
B=a-W_N^kb
}
$$

## DIF butterfly

$$
\boxed{
A=a+b
}
$$

$$
\boxed{
B=(a-b)W_N^k
}
$$

## Radix-2 stages

$$
\boxed{
\log_2N
}
$$

## Butterflies per stage

$$
\boxed{
N/2
}
$$

## FFT multiplications

$$
\boxed{
\frac N2\log_2N
}
$$

## FFT additions

$$
\boxed{
N\log_2N
}
$$

## IDFT using FFT

$$
\boxed{
x(n)=
\frac1N
\left[
\operatorname{DFT}\{X^*(k)\}
\right]^*
}
$$

## Circular convolution

$$
\boxed{
Y(k)=X(k)H(k)
}
$$

## Linear convolution using FFT

$$
\boxed{
N\ge L_x+L_h-1
}
$$

## Fast convolution

$$
\boxed{
y=\operatorname{IFFT}
\left[
\operatorname{FFT}(x)\operatorname{FFT}(h)
\right]
}
$$

---

# 69. COMPUTATION-COUNT FORMULA SHEET

### Direct DFT

$$
\boxed{
M_D=N^2
}
$$

$$
\boxed{
A_D=N(N-1)
}
$$

### Radix-2 FFT

$$
\boxed{
M_F=\frac N2\log_2N
}
$$

$$
\boxed{
A_F=N\log_2N
}
$$

### Number of stages

$$
\boxed{
S=\log_2N.
}
$$

### Butterflies

$$
\boxed{
B_{\text{stage}}=\frac N2.
}
$$

### Total radix-2 butterflies

$$
\boxed{
B_{\text{total}}
=
\frac N2\log_2N.
}
$$

---

# 70. SAMPLE COMPUTATION TABLE

| $N$ | Stages | FFT multiplications | FFT additions |
|---:|---:|---:|---:|
| 4 | 2 | 4 | 8 |
| 8 | 3 | 12 | 24 |
| 16 | 4 | 32 | 64 |
| 32 | 5 | 80 | 160 |
| 64 | 6 | 192 | 384 |
| 128 | 7 | 448 | 896 |
| 256 | 8 | 1024 | 2048 |
| 512 | 9 | 2304 | 4608 |
| 1024 | 10 | 5120 | 10240 |

---

# 71. REVIEW QUESTIONS — CHAPTER 7

1. What is the importance of DFT?
2. Define FFT.
3. Why is FFT required?
4. How does FFT improve computational speed?
5. What is the twiddle factor?
6. State the symmetry property of the twiddle factor.
7. State the periodicity property of the twiddle factor.
8. Explain the fundamental principle of FFT.
9. What are the two basic classes of FFT algorithms?
10. What is radix-2 FFT?
11. Why is radix-2 particularly useful?
12. What is DIT FFT?
13. What is DIF FFT?
14. Why is DIT called decimation in time?
15. Why is DIF called decimation in frequency?
16. What is the number of stages in an $N$-point radix-2 FFT?
17. How many butterflies occur in each stage?
18. State the computational requirements of radix-2 FFT.
19. Explain the DIT butterfly.
20. Explain the DIF butterfly.
21. What is the input/output order in DIT?
22. What is the input/output order in DIF?
23. Compare DIT and DIF FFT.
24. Explain the 8-point DIT FFT.
25. Explain the 8-point DIF FFT.
26. Explain bit reversal.
27. Explain IDFT using FFT.
28. What is fast convolution?
29. Explain linear convolution using FFT.
30. Why is zero-padding required before FFT convolution?
31. What is composite-radix FFT?
32. Explain radix-3 FFT.
33. Explain radix-4 FFT.
34. Compare direct DFT and FFT computational requirements.
35. Explain magnitude and phase spectra.

---

# 72. FILL-IN-THE-BLANKS

| No. | Answer |
|---:|---|
| 1 | DFT of a single number $A$ is $A$ |
| 2 | DFT of $\{A,B\}$ is $\{A+B,A-B\}$ |
| 3 | Direct DFT multiplications: $N^2$ |
| 4 | Direct DFT additions: $N(N-1)$ |
| 5 | Radix-2 FFT multiplications: $(N/2)\log_2N$ |
| 6 | FFT is an algorithm for computing DFT |
| 7 | Divide-and-conquer |
| 8 | DIT and DIF |
| 9 | Symmetry and periodicity |
| 10 | $N=2^m$ |
| 11 | radix; stages |
| 12 | bit-reversed; normal |
| 13 | normal; bit-reversed |
| 14 | $x(n)=\frac1N[\operatorname{DFT}\{X^*(k)\}]^*$ |
| 15 | 5 stages for $N=32$ |
| 16 | 6 stages for $N=64$ |
| 17 | butterfly |
| 18 | $N/2$ butterflies per stage |
| 19 | slow/direct convolution |
| 20 | fast convolution |
| 21 | $\log_2N$ stages and $N/2$ butterflies per stage |
| 22 | time; frequency |

---

# 73. OBJECTIVE-TYPE ANSWERS

### 1. Direct 8-point DFT multiplications

$$
8^2=\boxed{64}.
$$

### 2. Direct 8-point DFT additions

$$
8(8-1)=\boxed{56}.
$$

### 3. DFT of $\{4,2\}$

$$
\boxed{\{6,2\}}.
$$

### 4. Stages for 1024-point radix-2 FFT

$$
\log_2(1024)=\boxed{10}.
$$

### 5. Butterflies per stage for 64-point radix-2 FFT

$$
64/2=\boxed{32}.
$$

### 6. 256-point FFT multiplications

$$
\frac{256}{2}\log_2256
=
\boxed{1024}.
$$

### 7. 256-point FFT additions

$$
256\log_2256
=
\boxed{2048}.
$$

### 8. Eight stages imply

$$
N=2^8=\boxed{256}.
$$

### 9. Radix-2 requires $N$ to be a power of

$$
\boxed{2}.
$$

---

# 74. IMPORTANT TEXTBOOK OBJECTIVE CONCEPTS

### DFT of $\delta(n)$

$$
\boxed{
1.
}
$$

### DIT input

$$
\boxed{
\text{bit reversed}.
}
$$

### DIT output

$$
\boxed{
\text{normal order}.
}
$$

### DIF input

$$
\boxed{
\text{normal order}.
}
$$

### DIF output

$$
\boxed{
\text{bit reversed}.
}
$$

### DIT butterfly

$$
\boxed{
\text{twiddle multiplication before add/subtract}.
}
$$

### DIF butterfly

$$
\boxed{
\text{twiddle multiplication after add/subtract}.
}
$$

### Radix-2 stages

$$
\boxed{
\log_2N.
}
$$

### Butterflies per stage

$$
\boxed{
N/2.
}
$$

---

# 75. TEXTBOOK PROBLEMS — CHAPTER 7

## Problem 1 — DFT by DIT/DIF

Find the DFT by both DIT and DIF FFT:

### (a)

$$
x(n)=\{0.5,1.5,0.5,0.5\}
$$

### (b)

$$
x(n)=\{0.5,0,0.5,0\}
$$

### (c)

$$
x(n)=\{2,0,0,1\}
$$

### (d)

$$
x(n)=\{1,0,1,0\}.
$$

---

## Problem 2 — IDFT by DIT/DIF

Find the IDFT by both DIT and DIF FFT:

### (a)

$$
X(k)=\{10,-2+j2,2,-2-j2\}
$$

### (b)

$$
X(k)=\{0,2-j2,0,2+j2\}
$$

### (c)

$$
X(k)=\{6,-j2,2,j2\}
$$

### (d)

$$
X(k)=\{-4,2+j2,0,2-j2\}.
$$

---

## Problem 3 — 8-point DIT/DIF

Compute by both methods:

### (a)

$$
x(n)=\{1,1,1,1,1,1,1,1\}
$$

### (b)

$$
x(n)=\{0,1,2,3,0,0,0,0\}
$$

### (c)

$$
x(n)=n+1,\qquad N=8
$$

### (d)

$$
x(n)=\{1,1,1,1,0,0,0,0\}.
$$

---

## Problem 4 — 8-point IDFT

Compute by both DIT and DIF:

### (a)

$$
X(k)=\{1,1,1,1,1,1,1,1\}
$$

### (b)

$$
X(k)=\{12,0,-j2,0,0,0,j2,0\}
$$

and the two longer frequency-domain sequences specified in the textbook.

---

## Problem 5 — Circular convolution using radix-2 DIT FFT

### (a)

$$
x(n)=\{1,0.5\},
\qquad
h(n)=\{0.5,1\}
$$

### (b)

$$
x_1(n)=\{1,2,1,2\},
\qquad
x_2(n)=\{4,3,2,1\}
$$

### (c)

$$
x(n)=\{1,-1,1,-1\},
\qquad
h(n)=\{1,2,3,4\}
$$

### (d)

$$
x(n)=\{1,2,0,1\},
\qquad
h(n)=\{2,2,1,1\}.
$$

---

# 76. TEXTBOOK LINEAR-CONVOLUTION PROBLEMS

Find the linear convolution / LTI-system response using FFT:

### (a)

$$
x(n)=\{1,0,2\},
\qquad
h(n)=\{1,1\}
$$

### (b)

$$
x(n)=\{1,2,3\},
\qquad
h(n)=\{1,-1\}
$$

### (c)

$$
x(n)=\{1,2,1,2,1\},
\qquad
h(n)=\{1,-1,1,1\}
$$

### (d)

$$
x(n)=\{1,2,1,1,1\},
\qquad
h(n)=\{1,0,1,0\}.
$$

---

# 77. TEXTBOOK MIXED-RADIX PROBLEMS

### DIT, $N=6$

Draw the flow diagrams for:

$$
\boxed{
N=2\times3
}
$$

and

$$
\boxed{
N=3\times2
}
$$

and evaluate the DFT of

$$
x(n)=\{1,0,2,2,0,2\}.
$$

### DIF, $N=6$

Draw the flow diagrams for:

$$
\boxed{
N=2\times3
}
$$

and

$$
\boxed{
N=3\times2
}
$$

and evaluate the DFT of

$$
x(n)=\{2,0,-2,1,0,-1\}.
$$

---

# 78. MATLAB — FFT

The basic MATLAB command for the FFT is

```matlab
X = fft(x);
```

The inverse is

```matlab
x = ifft(X);
```

---

# 79. MATLAB — DFT/IDFT THROUGH FFT

```matlab
clc;
clear all;
close all;

x = [2 1 4 3];

X = fft(x);

disp('DFT of x(n) is');
disp(X);

x_rec = ifft(X);

disp('IDFT reconstruction is');
disp(x_rec);
```

---

# 80. MATLAB — DIT-STYLE BIT REVERSAL

For an 8-point sequence:

```matlab
x = [x0 x1 x2 x3 x4 x5 x6 x7];

xr = [x(1) x(5) x(3) x(7) ...
      x(2) x(6) x(4) x(8)];
```

This corresponds to

$$
\boxed{
\{x(0),x(4),x(2),x(6),x(1),x(5),x(3),x(7)\}.
}
$$

---

# 81. MATLAB — FFT-BASED LINEAR CONVOLUTION

```matlab
clc;
clear all;
close all;

x = [1 2];
h = [2 1];

N = length(x) + length(h) - 1;

x1 = [x zeros(1, N-length(x))];
h1 = [h zeros(1, N-length(h))];

X = fft(x1);
H = fft(h1);

Y = X .* H;

y = ifft(Y);

disp('Linear convolution is');
disp(real(y));
```

---

# 82. MATLAB — FFT-BASED CIRCULAR CONVOLUTION

```matlab
clc;
clear all;
close all;

x = [1 2 1 2];
h = [4 3 2 1];

X = fft(x);
H = fft(h);

Y = X .* H;

y = ifft(Y);

disp('Circular convolution is');
disp(real(y));
```

---

# 83. MATLAB — MAGNITUDE AND PHASE

```matlab
clc;
clear all;
close all;

x = [2 1 4 3];

X = fft(x);

magX = abs(X);
phaseX = angle(X);

k = 0:length(x)-1;

figure;
stem(k, magX);
xlabel('k');
ylabel('|X(k)|');
title('Magnitude Spectrum');
grid on;

figure;
stem(k, phaseX);
xlabel('k');
ylabel('Phase');
title('Phase Spectrum');
grid on;
```

---

# 84. MATLAB — IDFT USING CONJUGATE PROPERTY

```matlab
clc;
clear all;
close all;

X = [4 -2+2j 2 -2-2j];

Xc = conj(X);

temp = fft(Xc);

x = conj(temp)/length(X);

disp('IDFT using FFT is');
disp(x);
```

This implements

$$
\boxed{
x(n)=
\frac1N
[\operatorname{DFT}\{X^*(k)\}]^*.
}
$$

---

# 85. EXAM SOLVING STRATEGY — DIT

If asked to perform an $N$-point radix-2 DIT FFT:

### Step 1

Check

$$
N=2^m.
$$

### Step 2

Calculate

$$
m=\log_2N.
$$

### Step 3

Write the input in bit-reversed order.

### Step 4

Perform $N/2$ butterflies in stage 1.

### Step 5

Perform $N/2$ butterflies in each subsequent stage.

### Step 6

Use the correct $W_N^k$ phase factors.

### Step 7

The final output is in normal order.

---

# 86. EXAM SOLVING STRATEGY — DIF

### Step 1

Check

$$
N=2^m.
$$

### Step 2

Keep the input in normal order.

### Step 3

At each butterfly calculate

$$
a+b
$$

and

$$
(a-b)W_N^k.
$$

### Step 4

Continue until 2-point sequences remain.

### Step 5

The final output is in bit-reversed order.

### Step 6

Rearrange to normal frequency order if required.

---

# 87. EXAM SOLVING STRATEGY — IDFT USING FFT

Always remember:

$$
\boxed{
\text{conjugate}
\rightarrow
\text{FFT}
\rightarrow
\text{conjugate}
\rightarrow
\frac1N.
}
$$

In words:

1. $X^*(k)$
2. FFT of $X^*(k)$
3. conjugate result
4. divide by $N$

---

# 88. EXAM SOLVING STRATEGY — FFT CONVOLUTION

### If the question asks for linear convolution:

First find

$$
\boxed{
N=L_x+L_h-1.
}
$$

Zero-pad both sequences.

Then:

$$
\boxed{
X=\operatorname{FFT}(x)
}
$$

$$
\boxed{
H=\operatorname{FFT}(h)
}
$$

$$
\boxed{
Y=XH
}
$$

$$
\boxed{
y=\operatorname{IFFT}(Y).
}
$$

---

# 89. COMMON EXAM MISTAKES

1. Writing $W_N=e^{+j2\pi/N}$ for the forward DFT.
2. Forgetting that radix-2 requires $N=2^m$.
3. Forgetting the number of stages:

$$
\log_2N.
$$

4. Forgetting that each stage contains $N/2$ butterflies.
5. Mixing DIT and DIF butterfly equations.
6. Forgetting bit reversal.
7. Saying DIT has normal-order input.
8. Saying DIF has bit-reversed input.
9. Putting the twiddle multiplication on the wrong side of the butterfly.
10. Forgetting the $1/N$ in FFT-based IDFT.
11. Performing DFT multiplication without zero-padding when linear convolution is required.
12. Confusing circular convolution with linear convolution.
13. Forgetting that DIF produces bit-reversed output.
14. Using a radix-2 flow diagram when $N$ is not a power of 2 without explaining zero-padding or mixed radix.
15. Forgetting that DIT and DIF require the same number of arithmetic operations.
16. Confusing magnitude with power spectrum.
17. Forgetting phase wrapping when interpreting phase plots.

---

# 90. VERY IMPORTANT SHORT ANSWERS

### What is FFT?

$$
\boxed{
\text{An efficient algorithm for computing the DFT.}
}
$$

### Fundamental principle?

$$
\boxed{
\text{Decompose a large DFT into smaller DFTs.}
}
$$

### Why faster?

$$
\boxed{
\text{Uses symmetry and periodicity of }W_N.
}
$$

### DIT?

$$
\boxed{
\text{Decimation in time}.
}
$$

### DIF?

$$
\boxed{
\text{Decimation in frequency}.
}
$$

### DIT input/output?

$$
\boxed{
\text{bit reversed}\rightarrow\text{normal}.
}
$$

### DIF input/output?

$$
\boxed{
\text{normal}\rightarrow\text{bit reversed}.
}
$$

### Radix-2 condition?

$$
\boxed{
N=2^m.
}
$$

### Number of stages?

$$
\boxed{
\log_2N.
}
$$

### Butterflies per stage?

$$
\boxed{
N/2.
}
$$

### FFT multiplications?

$$
\boxed{
(N/2)\log_2N.
}
$$

### FFT additions?

$$
\boxed{
N\log_2N.
}
$$

### DIT butterfly?

$$
\boxed{
A=a+bW_N^k,\quad
B=a-bW_N^k.
}
$$

### DIF butterfly?

$$
\boxed{
A=a+b,\quad
B=(a-b)W_N^k.
}
$$

### IDFT through FFT?

$$
\boxed{
x(n)=
\frac1N
[\operatorname{FFT}\{X^*(k)\}]^*.
}
$$

---

# 91. FINAL ONE-PAGE MEMORY SHEET

$$
\boxed{
W_N=e^{-j2\pi/N}
}
$$

$$
\boxed{
W_N^{k+N}=W_N^k
}
$$

$$
\boxed{
W_N^{k+N/2}=-W_N^k
}
$$

$$
\boxed{
N=2^m
}
$$

$$
\boxed{
m=\log_2N
}
$$

$$
\boxed{
\text{butterflies/stage}=N/2
}
$$

$$
\boxed{
M_{\text{FFT}}=(N/2)\log_2N
}
$$

$$
\boxed{
A_{\text{FFT}}=N\log_2N
}
$$

$$
\boxed{
\text{DIT: bit-reversed input}
}
$$

$$
\boxed{
\text{DIT: normal output}
}
$$

$$
\boxed{
\text{DIF: normal input}
}
$$

$$
\boxed{
\text{DIF: bit-reversed output}
}
$$

$$
\boxed{
\text{DIT: multiply then add/subtract}
}
$$

$$
\boxed{
\text{DIF: add/subtract then multiply}
}
$$

$$
\boxed{
A_{\rm DIT}=a+bW_N^k
}
$$

$$
\boxed{
B_{\rm DIT}=a-bW_N^k
}
$$

$$
\boxed{
A_{\rm DIF}=a+b
}
$$

$$
\boxed{
B_{\rm DIF}=(a-b)W_N^k
}
$$

$$
\boxed{
x(n)=
\frac1N[\operatorname{FFT}\{X^*(k)\}]^*
}
$$

$$
\boxed{
Y(k)=X(k)H(k)
}
$$

$$
\boxed{
N_{\rm linear}\ge L_x+L_h-1
}
$$

$$
\boxed{
\text{FFT: }O(N\log_2N)
}
$$

$$
\boxed{
\text{Direct DFT: }O(N^2)
}
$$

---

# 92. FINAL EXAM CHECKLIST

Before the exam, make sure you can:

- [ ] Define FFT.
- [ ] Explain why FFT is needed.
- [ ] Derive radix-2 DIT FFT.
- [ ] Explain even/odd decimation.
- [ ] Draw an 8-point DIT butterfly diagram.
- [ ] Explain bit-reversed ordering.
- [ ] Calculate an 8-point DIT FFT.
- [ ] Explain DIF FFT.
- [ ] Draw an 8-point DIF butterfly.
- [ ] Calculate an 8-point DIF FFT.
- [ ] Compare DIT and DIF.
- [ ] Calculate number of stages.
- [ ] Calculate butterflies per stage.
- [ ] Calculate FFT multiplications/additions.
- [ ] Calculate percentage computational saving.
- [ ] Compute IDFT using FFT.
- [ ] Compute circular convolution using FFT.
- [ ] Compute linear convolution using FFT with zero-padding.
- [ ] Explain fast convolution.
- [ ] Explain magnitude and phase spectrum.
- [ ] Explain composite/mixed-radix FFT.
- [ ] Explain radix-3 FFT.
- [ ] Explain radix-4 FFT.
- [ ] Write MATLAB `fft`/`ifft` code.

---

# 93. FINAL DECISION TREE

### Question: “What is FFT?”

Answer:

$$
\boxed{
\text{Efficient algorithm for DFT computation using divide-and-conquer.}
}
$$

### Question: “How many stages?”

$$
\boxed{
\log_2N.
}
$$

### Question: “How many butterflies per stage?”

$$
\boxed{
N/2.
}
$$

### Question: “DIT or DIF?”

Look for:

$$
\boxed{
\text{DIT}\Rightarrow\text{time decimation}
}
$$

$$
\boxed{
\text{DIF}\Rightarrow\text{frequency decimation}
}
$$

### Question: “What is DIT input/output?”

$$
\boxed{
\text{bit reversed}\rightarrow\text{normal}.
}
$$

### Question: “What is DIF input/output?”

$$
\boxed{
\text{normal}\rightarrow\text{bit reversed}.
}
$$

### Question: “Where is the twiddle multiplication?”

DIT:

$$
\boxed{
bW_N^k\rightarrow a\pm bW_N^k.
}
$$

DIF:

$$
\boxed{
a\pm b\rightarrow(a-b)W_N^k.
}
$$

### Question: “How to calculate IDFT with FFT?”

$$
\boxed{
X^*\rightarrow FFT\rightarrow(\cdot)^*\rightarrow1/N.
}
$$

### Question: “How to calculate linear convolution using FFT?”

$$
\boxed{
N\ge L_x+L_h-1
}
$$

then

$$
\boxed{
FFT(x)\times FFT(h)\rightarrow IFFT.
}
$$

---

## SOURCE BOUNDARY

This guide is based on **Chapter 7, Fast Fourier Transform, printed pages 479–547** of A. Anand Kumar's *Digital Signal Processing*. The source chapter explicitly covers radix-2 DIT, 8-point DIT, butterfly diagrams, radix-2 DIF, IDFT using FFT, DIT/DIF comparison, composite/mixed-radix FFT, radix-3 and radix-4 concepts, worked Examples 7.1–7.25, review questions, fill-ins, objective questions, problems, and MATLAB material.
