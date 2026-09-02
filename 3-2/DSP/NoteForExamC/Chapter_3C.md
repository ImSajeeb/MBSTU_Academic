---
title: "Chapter 3 — Z-Transforms"
source: "Digital Signal Processing — A. Anand Kumar"
printed_pages: "179–276"
style: "Full theory + derivations + worked examples + exam questions"
---

# Chapter 3 — Z-Transforms

> **Source boundary:** These notes follow Chapter 3, printed pages **179–276**, of *Digital Signal Processing — A. Anand Kumar*. The organization follows the textbook: theory first, then worked examples, then short questions/review/fill-in/objective/problem sections.
>
> **Important mathematical-format rule:** Multi-step derivations are written with aligned LaTeX so that every equality stays visually connected.

---

# 1. Chapter Roadmap

According to the chapter contents, Chapter 3 covers:

1. Introduction
2. Advantages of Z-transform
3. Relation between DTFT and Z-transform
4. Z-transform and ROC of finite-duration sequences
   - Right-sided sequence
   - Left-sided sequence
   - Two-sided sequence
5. Properties of ROC
6. Properties of Z-transform
   - Linearity
   - Time shifting
   - Multiplication by exponential sequence
   - Time reversal
   - Time expansion
   - Multiplication by \(n\) / differentiation in \(z\)-domain
   - Convolution
   - Multiplication / complex convolution
   - Correlation
   - Parseval relation
   - Initial value theorem
   - Final value theorem
7. Inverse Z-transform
   - Power-series / long-division method
   - Partial-fraction method
   - Complex-inversion / residue method
   - Convolution method
8. Analysis of discrete-time LTI systems using Z-transform
9. Difference equation, system function and frequency response
10. Stability and causality
11. Free, forced and total response
12. Deconvolution
13. Laplace-transform and Z-transform relation
14. Short questions, review questions, fill-ins, objective questions and problems

The chapter introduces the Z-transform as the discrete-time counterpart of the Laplace transform and emphasizes its usefulness for solving difference equations, analyzing LTI systems, and handling signals for which the DTFT may not exist.

---

# 2. Introduction to Z-Transform

## 2.1 Why Z-transform is needed

A discrete-time LTI system is commonly described by a **difference equation**. Direct solution of higher-order difference equations can become tedious.

The Z-transform provides an indirect but systematic approach:

\[
\boxed{
\text{Difference equation}
\;\xrightarrow{\mathcal Z}\;
\text{algebraic equation in }z
\;\xrightarrow{\text{solve}}\;
Y(z)
\;\xrightarrow{\mathcal Z^{-1}}\;
y(n)
}
\]

The textbook compares this role with the role played by the Laplace transform for continuous-time systems.

### Main idea

Instead of repeatedly solving a difference equation sample-by-sample, transform the equation into the \(z\)-domain, manipulate algebraically, then return to the time domain.

The chapter emphasizes that the Z-transform:

- makes system analysis simpler and systematic;
- converts convolution into multiplication;
- can exist for many signals for which the DTFT does not exist;
- directly gives frequency response when the unit circle belongs to the ROC;
- is especially useful with causal signals and initial conditions.

The chapter states that the unilateral (one-sided) Z-transform is particularly useful for causal sequences and for difference equations involving initial conditions.

---

# 3. Definition of Z-Transform

## 3.1 Bilateral / two-sided Z-transform

For a discrete-time sequence \(x(n)\),

\[
\boxed{
X(z)=\mathcal Z\{x(n)\}
=\sum_{n=-\infty}^{\infty}x(n)z^{-n}
}
\]

where \(z\) is a complex variable.

This is called the **bilateral** or **two-sided Z-transform**.

---

## 3.2 Unilateral / one-sided Z-transform

The one-sided Z-transform is

\[
\boxed{
X(z)=\mathcal Z_u\{x(n)\}
=\sum_{n=0}^{\infty}x(n)z^{-n}
}
\]

The book points out:

\[
x(n)=0,\qquad n<0
\]

implies that the unilateral and bilateral transforms are equivalent.

### Exam distinction

| Bilateral Z-transform | Unilateral Z-transform |
|---|---|
| Sum from \(n=-\infty\) to \(+\infty\) | Sum from \(n=0\) to \(+\infty\) |
| Used for general sequences | Particularly useful for causal sequences |
| Natural for ROC/signals analysis | Very useful for initial-condition difference equations |

---

# 4. Region of Convergence (ROC)

A Z-transform does **not automatically converge for every \(z\)**.

The set of all values of \(z\) for which

\[
\sum_{n=-\infty}^{\infty}x(n)z^{-n}
\]

converges is called the **Region of Convergence**, or **ROC**.

\[
\boxed{
\mathrm{ROC}
=
\left\{z:
\sum_{n=-\infty}^{\infty}x(n)z^{-n}
\text{ converges}
\right\}
}
\]

If there is no value of \(z\) for which the summation converges, the sequence is said to have **no Z-transform**.

---

## 4.1 ROC using \(z=re^{j\omega}\)

Let

\[
z=re^{j\omega}.
\]

Then

\[
z^{-n}=r^{-n}e^{-j\omega n},
\]

so

\[
X(z)
=
\sum_{n=-\infty}^{\infty}
x(n)r^{-n}e^{-j\omega n}.
\]

Thus the Z-transform can be viewed as a DTFT of the weighted sequence

\[
x(n)r^{-n}.
\]

For convergence, the weighted sequence must satisfy the required summability condition.

---

# 5. Relation Between Z-Transform and DTFT

The DTFT is

\[
\boxed{
X(e^{j\omega})
=
\sum_{n=-\infty}^{\infty}
x(n)e^{-j\omega n}
}
\]

while the Z-transform is

\[
\boxed{
X(z)
=
\sum_{n=-\infty}^{\infty}
x(n)z^{-n}.
}
\]

Putting

\[
z=re^{j\omega},
\]

we obtain

\[
\begin{aligned}
X(re^{j\omega})
&=
\sum_{n=-\infty}^{\infty}
x(n)\left(re^{j\omega}\right)^{-n}\\
&=
\sum_{n=-\infty}^{\infty}
x(n)r^{-n}e^{-j\omega n}.
\end{aligned}
\]

Therefore,

\[
\boxed{
X(re^{j\omega})
=
\mathrm{DTFT}\{x(n)r^{-n}\}
}
\]

The **unit circle** corresponds to

\[
r=1,\qquad z=e^{j\omega}.
\]

Hence,

\[
\boxed{
X(e^{j\omega})
=
X(z)\big|_{z=e^{j\omega}}
}
\]

provided the **unit circle lies inside the ROC**.

### Important exam statement

> **DTFT is the Z-transform evaluated on the unit circle.**

---

# 6. Advantages of Z-Transform

The textbook highlights these advantages:

### 6.1 Difference equations become algebraic equations

\[
\boxed{
\text{difference equation}
\longrightarrow
\text{algebraic equation in }z
}
\]

This is simpler to manipulate.

### 6.2 Convolution becomes multiplication

If

\[
y(n)=x(n)*h(n),
\]

then

\[
\boxed{
Y(z)=X(z)H(z).
}
\]

### 6.3 Existence is broader than DTFT

Many signals whose DTFT does not exist can still have a Z-transform because suitable values of \(|z|\) can make the weighted summation converge.

### 6.4 Frequency response

If the unit circle belongs to the ROC,

\[
\boxed{
H(e^{j\omega})=H(z)\big|_{z=e^{j\omega}}.
}
\]

---

# 7. Z-Transform of Common Basic Signals

## 7.1 Unit impulse

\[
x(n)=\delta(n)
\]

gives

\[
\begin{aligned}
X(z)
&=
\sum_{n=-\infty}^{\infty}
\delta(n)z^{-n}\\
&=1.
\end{aligned}
\]

Therefore,

\[
\boxed{\mathcal Z\{\delta(n)\}=1}
\]

and its ROC is the entire \(z\)-plane.

---

## 7.2 Unit step

For the causal unit step,

\[
u(n)=
\begin{cases}
1,&n\ge 0\\
0,&n<0,
\end{cases}
\]

\[
\begin{aligned}
X(z)
&=
\sum_{n=0}^{\infty}z^{-n}\\
&=
1+z^{-1}+z^{-2}+\cdots\\
&=
\frac{1}{1-z^{-1}}
=
\frac{z}{z-1}.
\end{aligned}
\]

The geometric series converges for

\[
|z^{-1}|<1
\quad\Longrightarrow\quad
|z|>1.
\]

Hence,

\[
\boxed{
\mathcal Z\{u(n)\}
=
\frac{z}{z-1},
\qquad
|z|>1.
}
\]

---

# 8. Z-Transform of Infinite-Duration Causal Sequence

Consider

\[
x(n)=a^n u(n).
\]

Then

\[
\begin{aligned}
X(z)
&=
\sum_{n=0}^{\infty}a^n z^{-n}\\
&=
\sum_{n=0}^{\infty}(az^{-1})^n\\
&=
\frac{1}{1-az^{-1}}\\
&=
\frac{z}{z-a}.
\end{aligned}
\]

Convergence requires

\[
|az^{-1}|<1
\]

so

\[
\boxed{|z|>|a|}.
\]

Therefore,

\[
\boxed{
a^n u(n)
\;\xleftrightarrow{\mathcal Z}\;
\frac{z}{z-a},
\quad
|z|>|a|.
}
\]

### Memory rule

**Causal infinite sequence \(\rightarrow\) ROC outside the outermost pole.**

---

# 9. Z-Transform of Infinite-Duration Left-Sided Sequence

Consider

\[
x(n)=-a^n u(-n-1).
\]

The sequence exists only for negative \(n\):

\[
n\le -1.
\]

Then

\[
\begin{aligned}
X(z)
&=
-\sum_{n=-\infty}^{-1}a^n z^{-n}.
\end{aligned}
\]

Let

\[
m=-n,\qquad m=1,2,3,\ldots
\]

Then

\[
\begin{aligned}
X(z)
&=
-\sum_{m=1}^{\infty}\left(\frac{z}{a}\right)^m\\
&=
\frac{z}{z-a}.
\end{aligned}
\]

The same algebraic expression is obtained, but now convergence requires

\[
\boxed{|z|<|a|}.
\]

### Critical lesson

\[
\boxed{
\text{Same }X(z)+\text{different ROC}
\Rightarrow
\text{different time-domain sequence.}
}
\]

Therefore **ROC is part of the Z-transform description**.

---

# 10. Right-Sided, Left-Sided and Two-Sided Sequences

## 10.1 Right-sided sequence

A right-sided sequence is one satisfying

\[
x(n)=0,\qquad n<n_0.
\]

For infinite-duration right-sided sequences, the ROC is generally

\[
\boxed{|z|>\text{outermost pole magnitude}.}
\]

For a finite-duration causal sequence:

\[
\boxed{\mathrm{ROC}=\text{entire }z\text{-plane except }z=0.}
\]

---

## 10.2 Left-sided sequence

A left-sided sequence is one satisfying

\[
x(n)=0,\qquad n>n_0.
\]

For infinite-duration left-sided sequences, the ROC is inside the innermost pole magnitude.

For a finite-duration anticausal sequence,

\[
\boxed{\mathrm{ROC}=\text{entire }z\text{-plane except }z=\infty.}
\]

---

## 10.3 Two-sided sequence

A two-sided infinite sequence extends in both directions.

Its ROC is generally a ring:

\[
\boxed{
R_1<|z|<R_2.
}
\]

For a finite-duration two-sided sequence,

\[
\boxed{
\mathrm{ROC}=\text{entire }z\text{-plane except }z=0,\infty.
}
\]

---

# 11. ROC Properties — High-Yield Theory

The chapter lists the following important ROC rules.

### Rule 1

The ROC is a disk, exterior region, or ring centered at the origin.

### Rule 2

\[
\boxed{\text{ROC cannot contain poles.}}
\]

### Rule 3 — Infinite causal sequence

\[
\boxed{|z|>\text{largest pole magnitude}.}
\]

### Rule 4 — Infinite anticausal sequence

\[
\boxed{|z|<\text{smallest pole magnitude}.}
\]

### Rule 5 — Finite causal sequence

Entire \(z\)-plane except \(z=0\).

### Rule 6 — Finite anticausal sequence

Entire \(z\)-plane except \(z=\infty\).

### Rule 7 — Finite two-sided sequence

Entire \(z\)-plane except \(z=0,\infty\).

### Rule 8 — Infinite two-sided sequence

\[
\boxed{R_1<|z|<R_2}
\]

where the boundaries are determined by poles.

### Rule 9 — Stability

For an LTI system,

\[
\boxed{\text{stable} \Longleftrightarrow h(n)\text{ is absolutely summable}}
\]

and consequently the ROC of \(H(z)\) must contain the unit circle.

### Rule 10 — Rational Z-transform

For a rational \(X(z)\), the ROC is bounded by poles or extends to infinity.

### Rule 11 — Entire \(z\)-plane

The chapter identifies

\[
\boxed{\delta(n)}
\]

as the only signal whose ROC is the entire \(z\)-plane.

---

# 12. Pole-Zero Interpretation

For a rational transform,

\[
X(z)=\frac{N(z)}{D(z)},
\]

the **zeros** are values of \(z\) making

\[
N(z)=0,
\]

while the **poles** are values of \(z\) making

\[
D(z)=0.
\]

On a pole-zero plot:

- zero: \( \circ \)
- pole: \( \times \)

The ROC cannot pass through a pole.

---

# 13. Finite-Duration Sequences

Finite-duration sequences contain only a finite number of nonzero samples.

They can be:

1. right-sided;
2. left-sided;
3. two-sided.

For a finite sequence,

\[
X(z)=\sum_{n=n_1}^{n_2}x(n)z^{-n}.
\]

Because the sum contains only finitely many terms, convergence is straightforward except at points caused by positive or negative powers.

---

# 14. Important Z-Transform Properties

The following are the major properties in the textbook.

---

## 14.1 Linearity Property

If

\[
x_1(n)\xleftrightarrow{\mathcal Z}X_1(z),
\qquad
x_2(n)\xleftrightarrow{\mathcal Z}X_2(z),
\]

then

\[
\boxed{
ax_1(n)+bx_2(n)
\xleftrightarrow{\mathcal Z}
aX_1(z)+bX_2(z).
}
\]

ROC is at least the intersection:

\[
\boxed{\mathrm{ROC}\supseteq R_1\cap R_2}
\]

with possible enlargement if pole cancellation occurs.

### Proof

\[
\begin{aligned}
\mathcal Z\{ax_1(n)+bx_2(n)\}
&=
\sum_n
[ax_1(n)+bx_2(n)]z^{-n}\\
&=
a\sum_nx_1(n)z^{-n}
+b\sum_nx_2(n)z^{-n}\\
&=
aX_1(z)+bX_2(z).
\end{aligned}
\]

---

## 14.2 Time-Shifting Property

For zero initial conditions,

\[
\boxed{
x(n-m)
\xleftrightarrow{\mathcal Z}
z^{-m}X(z)
}
\]

and

\[
\boxed{
x(n+m)
\xleftrightarrow{\mathcal Z}
z^mX(z).
}
\]

### Derivation

\[
\begin{aligned}
\mathcal Z\{x(n-m)\}
&=
\sum_nx(n-m)z^{-n}.
\end{aligned}
\]

Let

\[
p=n-m
\quad\Rightarrow\quad
n=p+m.
\]

Then

\[
\begin{aligned}
\mathcal Z\{x(n-m)\}
&=
\sum_p x(p)z^{-(p+m)}\\
&=
z^{-m}\sum_p x(p)z^{-p}\\
&=
z^{-m}X(z).
\end{aligned}
\]

### Initial-condition warning

For unilateral Z-transform, delays and advances involve additional initial-condition terms. The chapter explicitly develops these formulas because this property is essential in solving difference equations.

---

# 15. Multiplication by an Exponential Sequence

If

\[
x(n)\xleftrightarrow{\mathcal Z}X(z),
\]

then

\[
\boxed{
a^n x(n)
\xleftrightarrow{\mathcal Z}
X\left(\frac{z}{a}\right).
}
\]

The ROC scales correspondingly.

### Derivation

\[
\begin{aligned}
\mathcal Z\{a^n x(n)\}
&=
\sum_n a^n x(n)z^{-n}\\
&=
\sum_n x(n)(z/a)^{-n}\\
&=
X(z/a).
\end{aligned}
\]

All pole-zero locations scale by \(a\).

---

# 16. Time-Reversal Property

If

\[
x(n)\xleftrightarrow{\mathcal Z}X(z),
\]

then

\[
\boxed{
x(-n)
\xleftrightarrow{\mathcal Z}
X(z^{-1}).
}
\]

The ROC is correspondingly inverted.

### Proof

\[
\begin{aligned}
\mathcal Z\{x(-n)\}
&=
\sum_nx(-n)z^{-n}.
\end{aligned}
\]

Let

\[
p=-n.
\]

Then

\[
\begin{aligned}
\mathcal Z\{x(-n)\}
&=
\sum_p x(p)z^p\\
&=
\sum_p x(p)(z^{-1})^{-p}\\
&=
X(z^{-1}).
\end{aligned}
\]

---

# 17. Time-Expansion Property

For the expanded sequence

\[
x_e(n)=
\begin{cases}
x(n/k),&n\text{ is a multiple of }k,\\
0,&\text{otherwise},
\end{cases}
\]

we have

\[
\boxed{
\mathcal Z\{x_e(n)\}=X(z^k).
}
\]

The sequence contains \(k-1\) zeros inserted between successive samples.

---

# 18. Multiplication by \(n\) / Differentiation in \(z\)-Domain

If

\[
x(n)\xleftrightarrow{\mathcal Z}X(z),
\]

then

\[
\boxed{
\mathcal Z\{nx(n)\}
=
-z\frac{dX(z)}{dz}.
}
\]

### Derivation

\[
X(z)=\sum_nx(n)z^{-n}.
\]

Differentiate:

\[
\begin{aligned}
\frac{dX(z)}{dz}
&=
\sum_nx(n)(-n)z^{-n-1}\\
&=
-\frac1z
\sum_n n x(n)z^{-n}.
\end{aligned}
\]

Multiplying by \(-z\),

\[
\boxed{
-z\frac{dX(z)}{dz}
=
\sum_n n x(n)z^{-n}.
}
\]

Hence,

\[
\boxed{\mathcal Z\{nx(n)\}
=
-zX'(z).}
\]

---

# 19. Convolution Property

If

\[
x_1(n)\xleftrightarrow{\mathcal Z}X_1(z)
\]

and

\[
x_2(n)\xleftrightarrow{\mathcal Z}X_2(z),
\]

then

\[
\boxed{
x_1(n)*x_2(n)
\xleftrightarrow{\mathcal Z}
X_1(z)X_2(z).
}
\]

Thus:

\[
\boxed{
Y(z)=X(z)H(z)
}
\]

for an LTI system.

This is one of the most important properties of the chapter.

---

# 20. Complex-Convolution / Multiplication Property

The chapter also presents the transform-domain multiplication property corresponding to time-domain pointwise multiplication.

For sequences \(x_1(n)\) and \(x_2(n)\),

\[
\boxed{
\mathcal Z\{x_1(n)x_2(n)\}
=
\frac{1}{2\pi j}
\oint
X_1(v)X_2\!\left(\frac{z}{v}\right)
\frac{dv}{v}
}
\]

with the contour selected inside the relevant ROC.

This is the Z-domain counterpart of the convolution relationship associated with multiplication in the time domain.

---

# 21. Correlation Property

If

\[
R_{12}(n)=x_1(n)\star x_2(n),
\]

then the chapter gives the transform relation

\[
\boxed{
\mathcal Z\{R_{12}(n)\}
=
X_1(z)X_2(z^{-1}).
}
\]

This is especially useful when deriving correlation sequences from known transforms.

---

# 22. Parseval Relation

The textbook presents a Parseval-type relation connecting the time-domain product and a contour integral in the Z-domain.

For complex sequences,

\[
\boxed{
\sum_n x_1(n)x_2^*(n)
=
\frac{1}{2\pi j}
\oint
X_1(z)X_2^*(z^*)
\frac{dz}{z}
}
\]

with the contour lying in the common ROC as required.

The practical meaning is that signal-energy/product calculations can be carried between the time and transform domains.

---

# 23. Initial Value Theorem

For a **causal signal**,

\[
\boxed{
x(0)=\lim_{z\to\infty}X(z).
}
\]

### Why it works

For a causal sequence,

\[
X(z)=x(0)+x(1)z^{-1}+x(2)z^{-2}+\cdots.
\]

As

\[
z\to\infty,
\]

all terms containing \(z^{-1},z^{-2},\ldots\) vanish.

Therefore,

\[
\boxed{x(0)=\lim_{z\to\infty}X(z).}
\]

> **Exam condition:** Initial value theorem is used for causal signals.

---

# 24. Final Value Theorem

For a causal signal satisfying the pole conditions stated in the textbook,

\[
\boxed{
x(\infty)
=
\lim_{z\to1}
(z-1)X(z).
}
\]

An equivalent form is

\[
\boxed{
x(\infty)
=
\lim_{z\to1}
(1-z^{-1})X(z).
}
\]

The textbook requires that the relevant poles of

\[
(1-z^{-1})X(z)
\]

satisfy the stated stability/pole-location condition: no poles on or outside the unit circle except the allowed final-value behavior.

> **Exam warning:** Do **not** use the final value theorem blindly. Check the pole condition first.

---

# 25. Common Z-Transform Pairs

The chapter's table includes pairs such as:

\[
\delta(n)
\;\xleftrightarrow{\mathcal Z}\;
1
\]

\[
u(n)
\;\xleftrightarrow{\mathcal Z}\;
\frac{z}{z-1},
\qquad |z|>1
\]

\[
a^n u(n)
\;\xleftrightarrow{\mathcal Z}\;
\frac{z}{z-a},
\qquad |z|>|a|
\]

\[
-a^n u(-n-1)
\;\xleftrightarrow{\mathcal Z}\;
\frac{z}{z-a},
\qquad |z|<|a|
\]

\[
nu(n)
\;\xleftrightarrow{\mathcal Z}\;
\frac{z}{(z-1)^2},
\qquad |z|>1
\]

\[
na^n u(n)
\;\xleftrightarrow{\mathcal Z}\;
\frac{az}{(z-a)^2},
\qquad |z|>|a|
\]

and the corresponding sine, cosine, shifted, and polynomial-weighted pairs.

---

# 26. Worked Examples — Z-Transform, ROC and Properties

The following examples retain the textbook's sequence of reasoning. The most important pattern is always:

\[
\boxed{
\text{Identify support}
\rightarrow
\text{write }X(z)
\rightarrow
\text{test convergence}
\rightarrow
\text{state ROC}
}
\]

---

# Example 3.1 — ROC of a Causal Sequence

Given

\[
x(n)=r^n u(n).
\]

Then

\[
\begin{aligned}
X(z)
&=
\sum_{n=0}^{\infty}r^nz^{-n}\\
&=
\sum_{n=0}^{\infty}(rz^{-1})^n\\
&=
\frac{1}{1-rz^{-1}}.
\end{aligned}
\]

For convergence,

\[
|rz^{-1}|<1.
\]

Hence

\[
|z|>|r|.
\]

Therefore,

\[
\boxed{
\mathrm{ROC}:|z|>|r|.
}
\]

**Concept tested:** causal right-sided sequence \(\Rightarrow\) exterior ROC.

---

# Example 3.2 — Same Algebraic \(X(z)\), Different ROCs

Consider

\[
x_1(n)=a^nu(n)
\]

and

\[
x_2(n)=-a^nu(-n-1).
\]

For \(x_1(n)\),

\[
\boxed{
X_1(z)=\frac{z}{z-a},
\qquad |z|>|a|.
}
\]

For \(x_2(n)\),

\[
\boxed{
X_2(z)=\frac{z}{z-a},
\qquad |z|<|a|.
}
\]

Thus the algebraic expression is identical, but the ROCs differ.

### Exam conclusion

\[
\boxed{
X(z)\text{ alone is insufficient;}
\quad
\text{ROC is essential to identify }x(n).
}
\]

---

# Example 3.3 — \( \cos(n\pi/3) \) Type Sequence

For

\[
x(n)=\left(\frac14\right)^n
\cos\left(\frac{n\pi}{3}\right)u(n),
\]

use

\[
\cos\theta
=
\frac12
\left(e^{j\theta}+e^{-j\theta}\right).
\]

Then

\[
x(n)
=
\frac12
\left[
\left(\frac14e^{j\pi/3}\right)^n
+
\left(\frac14e^{-j\pi/3}\right)^n
\right]u(n).
\]

Therefore,

\[
\boxed{
X(z)
=
\frac12
\left[
\frac{z}{z-\frac14e^{j\pi/3}}
+
\frac{z}{z-\frac14e^{-j\pi/3}}
\right].
}
\]

Both terms are causal, so

\[
\boxed{|z|>\frac14.}
\]

The poles are

\[
\boxed{
z=\frac14e^{\pm j\pi/3}.
}
\]

---

# Example 3.4 — Two-Sided Sequence and Ring ROC

The textbook considers a sequence made from a right-sided exponential term and a left-sided exponential term.

The important procedure is:

1. transform the right-sided part;
2. transform the left-sided part;
3. determine each ROC;
4. intersect the two ROCs.

Suppose the right-sided component requires

\[
|z|>\frac14
\]

and the left-sided component requires

\[
|z|<\frac56.
\]

Then

\[
\boxed{
\frac14<|z|<\frac56.
}
\]

Thus the ROC is a **ring**.

---

# Example 3.5 — Finite-Duration Exponential Sequence

For

\[
x(n)=
\begin{cases}
a^n,&0\le n\le N-1\\
0,&\text{otherwise},
\end{cases}
\]

we have

\[
\begin{aligned}
X(z)
&=
\sum_{n=0}^{N-1}a^nz^{-n}\\
&=
\sum_{n=0}^{N-1}(az^{-1})^n\\
&=
\frac{1-(az^{-1})^N}{1-az^{-1}}.
\end{aligned}
\]

Equivalently,

\[
\boxed{
X(z)
=
\frac{z^N-a^N}{z^{N-1}(z-a)}.
}
\]

Since the sequence is finite and right-sided, the ROC is

\[
\boxed{\text{entire }z\text{-plane except }z=0.}
\]

---

# Example 3.6 — Causal Finite Sequence

Given

\[
x(n)=\{1,0,2,3,5,4\}
\]

starting at \(n=0\),

\[
x(0)=1,\;
x(1)=0,\;
x(2)=2,\;
x(3)=3,\;
x(4)=5,\;
x(5)=4.
\]

Therefore,

\[
\begin{aligned}
X(z)
&=
1+0z^{-1}
+2z^{-2}
+3z^{-3}
+5z^{-4}
+4z^{-5}\\
&=
1+2z^{-2}+3z^{-3}+5z^{-4}+4z^{-5}.
\end{aligned}
\]

Hence,

\[
\boxed{\mathrm{ROC}: z\neq0.}
\]

---

# Example 3.7 — Another Finite Right-Sided Sequence

Given

\[
x(n)=\{5,3,-2,0,4,-3\},
\]

starting at \(n=0\),

\[
\boxed{
X(z)
=
5+3z^{-1}-2z^{-2}
+4z^{-4}-3z^{-5}.
}
\]

ROC:

\[
\boxed{z\neq0.}
\]

---

# Example 3.8 — Anticausal Finite Sequence

Given

\[
x(n)=\{4,2,3,1,2,1\}
\]

with the first value located at \(n=-5\),

\[
x(-5)=4,\quad x(-4)=2,\ldots,x(0)=1.
\]

Then

\[
\boxed{
X(z)
=
4z^5+2z^4+3z^3+z^2+2z+1.
}
\]

Because the sequence is finite and left-sided,

\[
\boxed{
\mathrm{ROC}=\text{entire }z\text{-plane except }z=\infty.
}
\]

---

# Example 3.9 — Finite Two-Sided Sequence

For a sequence extending from negative to positive time,

\[
x(n)=\{2,1,3,0,4,3,2,1,5\},
\]

with samples occupying both sides of \(n=0\), write every term using its actual index.

Then

\[
X(z)
=
\sum_{n=n_1}^{n_2}x(n)z^{-n}.
\]

Because positive and negative powers both occur,

\[
\boxed{
\mathrm{ROC}
=
\text{entire }z\text{-plane except }z=0,\infty.
}
\]

---

# Example 3.10 — Unit-Step Difference Signals

### (a)

\[
x(n)=u(n)-u(n-4)
\]

has values

\[
x(n)=1,\qquad 0\le n\le3.
\]

Hence

\[
\boxed{
X(z)=1+z^{-1}+z^{-2}+z^{-3}.
}
\]

ROC:

\[
\boxed{z\neq0.}
\]

### (b)

\[
x(n)=u(n)-u(n-3)
\]

contains three nonzero samples:

\[
\boxed{
X(z)=1+z^{-1}+z^{-2}.
}
\]

### (c)

\[
x(n)=u(n-2)-u(n+2)
\]

produces a finite two-sided sequence. Therefore its ROC excludes both

\[
z=0,\qquad z=\infty.
\]

---

# 27. Examples Using Z-Transform Properties

# Example 3.11 — Using Time Reversal

For

\[
x(n)=u(n),
\]

we know

\[
X(z)=\frac{z}{z-1}.
\]

For

\[
x(-n)=u(-n),
\]

use

\[
\mathcal Z\{x(-n)\}=X(z^{-1}).
\]

Hence

\[
\begin{aligned}
X(z^{-1})
&=
\frac{z^{-1}}{z^{-1}-1}\\
&=
\frac{1}{1-z}.
\end{aligned}
\]

The ROC becomes

\[
\boxed{|z|<1.}
\]

---

# Example 3.12 — Shifted Unit-Step Sequence

### (a)

For

\[
x(n)=nu(n),
\]

the known transform is

\[
\boxed{
X(z)=\frac{z}{(z-1)^2}.
}
\]

For

\[
x(n-2)=(n-2)u(n-2),
\]

using the time-shift property,

\[
\boxed{
\mathcal Z\{x(n-2)\}=z^{-2}X(z)
}
\]

with the corresponding unilateral correction when initial conditions are included.

### (b)

A rectangular finite sequence

\[
x(n)=
\begin{cases}
1,&0\le n\le N-1\\
0,&\text{otherwise}
\end{cases}
\]

can be written as

\[
x(n)=u(n)-u(n-N).
\]

Therefore,

\[
\begin{aligned}
X(z)
&=
\frac{z}{z-1}
-
z^{-N}\frac{z}{z-1}\\
&=
\frac{z}{z-1}\left(1-z^{-N}\right).
\end{aligned}
\]

---

# Example 3.13 — Multiplication by \(n\) and an Exponential

The chapter uses a sinusoidal sequence multiplied by an exponential sequence.

The systematic approach is:

\[
\boxed{
\text{first use a known sinusoidal pair, then apply}
\quad
\mathcal Z\{a^nx(n)\}=X(z/a).
}
\]

If an additional factor \(n\) appears, use

\[
\boxed{
\mathcal Z\{nx(n)\}
=
-z\frac{dX(z)}{dz}.
}
\]

This two-property combination is highly exam-relevant.

---

# Example 3.14 — Convolution Property

For

\[
x_1(n)=\left(\frac12\right)^n u(n),
\qquad
x_2(n)=\left(\frac14\right)^n u(n),
\]

we have

\[
X_1(z)=\frac{z}{z-\frac12},
\qquad
X_2(z)=\frac{z}{z-\frac14}.
\]

Therefore,

\[
\begin{aligned}
X(z)
&=X_1(z)X_2(z)\\
&=
\frac{z^2}
{\left(z-\frac12\right)
 \left(z-\frac14\right)}.
\end{aligned}
\]

The common causal ROC is

\[
\boxed{|z|>\frac12.}
\]

---

# Example 3.15 — Convolution of Two Exponential Sequences

For

\[
x_1(n)=\left(\frac12\right)^n u(n),
\qquad
x_2(n)=\left(\frac13\right)^n u(n),
\]

use the convolution property:

\[
X(z)=X_1(z)X_2(z).
\]

Then partial fractions give a sum of two exponential terms in time.

The important exam pattern is

\[
\boxed{
\left(a^nu(n)\right)*
\left(b^nu(n)\right)
\Longleftrightarrow
\frac{z}{z-a}
\frac{z}{z-b}.
}
\]

---

# Example 3.16 — Convolution of Finite Sequences Using Z-Transform

Given

\[
x_1(n)=\{2,1,0,1,3\},
\qquad
x_2(n)=\{1,3,2\},
\]

their transforms are

\[
X_1(z)=2+z^{-1}+z^{-3}+3z^{-4},
\]

\[
X_2(z)=1+3z^{-1}+2z^{-2}.
\]

Multiply:

\[
\begin{aligned}
Y(z)
&=
X_1(z)X_2(z)\\
&=
(2+z^{-1}+z^{-3}+3z^{-4})
(1+3z^{-1}+2z^{-2}).
\end{aligned}
\]

Expanding and collecting powers gives

\[
\boxed{
y(n)=\{2,5,1,1,6,11,6\}.
}
\]

Thus multiplication in the Z-domain reproduces linear convolution in time.

---

# Example 3.17 — Compare Z-Domain and Time-Domain Convolution

The chapter explicitly compares:

### Method A — Z-transform

\[
x_1(n)\to X_1(z),
\quad
x_2(n)\to X_2(z)
\]

then

\[
Y(z)=X_1(z)X_2(z),
\]

then inverse Z-transform.

### Method B — Time-domain convolution

\[
\boxed{
y(n)=
\sum_{k=-\infty}^{\infty}
x_1(k)x_2(n-k).
}
\]

Both approaches give the same result.

**Exam point:** The Z-transform does not change the operation; it converts convolution into multiplication, which is algebraically easier.

---

# Example 3.18 — Cross-Correlation Using Z-Transform

For

\[
x_1(n)=\{1,2,3,4\},
\qquad
x_2(n)=\{4,3,2,1\},
\]

the correlation property is used.

First write

\[
R_{x_1x_2}(n)
=
x_1(n)*x_2(-n).
\]

Then

\[
R_{x_1x_2}(z)
=
X_1(z)X_2(z^{-1}).
\]

After multiplication and inverse Z-transform, the textbook obtains

\[
\boxed{
r_{x_1x_2}(l)
=
\{1,4,10,20,25,24,16\}.
}
\]

The second solution in the book computes individual lags by direct correlation and verifies the same sequence.

---

# 28. Initial and Final Value Examples

# Example 3.19 — Final Value Theorem

For a causal sequence with a valid final-value-theorem pole condition,

\[
\boxed{
x(\infty)=
\lim_{z\to1}(z-1)X(z).
}
\]

### (a)

Given

\[
X(z)=\frac{z^2}{z(z-0.6)},
\]

\[
\begin{aligned}
x(\infty)
&=
\lim_{z\to1}
(z-1)\frac{z^2}{z(z-0.6)}.
\end{aligned}
\]

The limit gives the steady-state value given in the textbook.

### (c) Pole-condition warning

If

\[
(z-1)X(z)
\]

has a pole on the unit circle or outside it, the final value theorem cannot be used to claim a finite final value.

---

# Example 3.20 — Initial Value Theorem

For causal \(x(n)\),

\[
x(0)=\lim_{z\to\infty}X(z).
\]

The procedure is simply:

1. simplify \(X(z)\);
2. let \(z\to\infty\);
3. state \(x(0)\).

This is usually much faster than taking the inverse Z-transform.

---

# Example 3.21 — Prove Initial and Final Values

For

\[
X(z)=
\frac{z^2}{(z-1)(z-0.2)},
\]

the final value is

\[
\begin{aligned}
x(\infty)
&=
\lim_{z\to1}
(z-1)
\frac{z^2}{(z-1)(z-0.2)}\\
&=
\frac{1}{1-0.2}\\
&=
\boxed{1.25}.
\end{aligned}
\]

The initial value is

\[
\begin{aligned}
x(0)
&=
\lim_{z\to\infty}
\frac{z^2}{(z-1)(z-0.2)}\\
&=\boxed{1}.
\end{aligned}
\]

---

# 29. Inverse Z-Transform

## 29.1 Definition

The process of obtaining \(x(n)\) from \(X(z)\) is called the **inverse Z-transform**:

\[
\boxed{
x(n)=\mathcal Z^{-1}\{X(z)\}.
}
\]

The textbook gives four common methods:

1. power-series / long-division method;
2. partial-fraction expansion;
3. complex inversion / residue method;
4. convolution method.

---

# 30. Direct Inverse Z-Transform Formula

The direct inversion formula is

\[
\boxed{
x(n)=
\frac{1}{2\pi j}
\oint_C
X(z)z^{\,n-1}\,dz
}
\]

where \(C\) is a contour lying in the ROC and traversed counterclockwise.

Using the residue theorem, \(x(n)\) is obtained from the sum of residues of

\[
X(z)z^{n-1}
\]

at the poles enclosed by the contour.

The textbook notes that this direct method is generally tedious, so indirect methods are usually preferred.

---

# 31. Method 1 — Power-Series / Long-Division Method

Suppose

\[
X(z)=\frac{N(z)}{D(z)}.
\]

The idea is to expand \(X(z)\) as a power series.

### For a causal/right-sided sequence

Write in descending powers of \(z\) or ascending powers of \(z^{-1}\):

\[
\boxed{
X(z)
=
x(0)+x(1)z^{-1}+x(2)z^{-2}+\cdots
}
\]

The coefficient of \(z^{-n}\) gives \(x(n)\).

### For an anticausal/left-sided sequence

Expand in positive powers of \(z\):

\[
\boxed{
X(z)
=
x(0)+x(-1)z+x(-2)z^2+\cdots
}
\]

The ROC determines which expansion is correct.

### Limitation

The textbook notes that long division:

- is simple;
- does not directly give a closed-form expression in \(n\);
- is mainly useful when the sequence is purely right-sided or purely left-sided;
- is useful when only the first few samples are required.

---

# Example 3.22 — Direct Identification from a Power Series

If

\[
X(z)
=
x(-3)z^3+x(-2)z^2+x(-1)z+x(0)
+x(1)z^{-1}+x(2)z^{-2}+\cdots,
\]

then coefficients are read directly.

The chapter's example gives

\[
\boxed{
x(n)=\{1,2,1,1,2,3,4\}
}
\]

with the sequence aligned to the stated indices.

---

# Example 3.23 — Three Simple Inverse Z-Transform Cases

### (a)

\[
X(z)=\frac{1}{z-a},
\qquad |z|>|a|.
\]

Use

\[
\frac{1}{z-a}
=
\frac{z^{-1}}{1-az^{-1}}
=
z^{-1}\sum_{n=0}^{\infty}a^nz^{-n}.
\]

Hence

\[
\boxed{
x(n)=a^{n-1}u(n-1).
}
\]

### (b)

For

\[
X(z)=\frac{1}{1-az^{-1}},
\qquad |z|>|a|,
\]

the standard pair gives

\[
\boxed{x(n)=a^nu(n).}
\]

### (c)

For a periodic impulse-like transform containing only powers separated by \(4\), the inverse sequence is nonzero only when

\[
n=4k.
\]

This illustrates how a power series can identify a sparse sequence directly.

---

# Example 3.24 — Inverse Transform of \( \cos(3z) \) and \( \sin(2z) \)

Because the ROC is

\[
|z|<\infty,
\]

the inverse is a **left-sided** sequence.

Use the Taylor series:

\[
\cos x
=
1-\frac{x^2}{2!}
+\frac{x^4}{4!}
-\cdots
\]

and

\[
\sin x
=
x-\frac{x^3}{3!}
+\frac{x^5}{5!}
-\cdots.
\]

Then match every power of \(z\) to a time index.

**Concept tested:** ROC tells whether the Taylor expansion corresponds to right-sided or left-sided samples.

---

# Example 3.25 — Logarithmic Transform, Base 10

The chapter demonstrates that when a logarithm of base 10 appears, convert using

\[
\log_{10}(A)
=
\frac{\ln A}{\ln 10}.
\]

Then expand

\[
\ln(1+x)
=
x-\frac{x^2}{2}
+\frac{x^3}{3}
-\frac{x^4}{4}
+\cdots
\]

and identify the inverse sequence term-by-term.

---

# Example 3.26 — Power-Series Inversion of \(\ln(1-z^{-1})\)

Use

\[
\ln(1-x)
=
-\sum_{k=1}^{\infty}\frac{x^k}{k},
\qquad |x|<1.
\]

Put

\[
x=z^{-1}.
\]

Then

\[
\ln(1-z^{-1})
=
-\sum_{k=1}^{\infty}
\frac{z^{-k}}{k}.
\]

Therefore,

\[
\boxed{
x(n)=
-\frac1n\,u(n-1)
}
\]

for the corresponding right-sided interpretation.

---

# Example 3.27 — Logarithmic Inverse with Different ROC

The chapter compares two forms that have the same general logarithmic algebra but different ROC restrictions.

### Main exam lesson

For

\[
X(z)=F(z),
\]

the ROC determines whether the expansion is interpreted as

- a right-sided sequence;
- a left-sided sequence.

Thus, always write

\[
\boxed{X(z)\;+\;\mathrm{ROC}}
\]

before attempting the inverse.

---

# Example 3.28 — Another Logarithmic Inverse

Use the logarithmic series

\[
\ln(1+x)
=
\sum_{k=1}^{\infty}
(-1)^{k+1}\frac{x^k}{k}
\]

with the argument chosen from the given transform.

Then identify the coefficients of powers of \(z^{-n}\).

---

# 32. Method 2 — Partial-Fraction Expansion

This is usually the most useful method when \(X(z)\) is rational and factorizable.

The textbook's approach is to expand

\[
\frac{X(z)}{z}
\]

into proper partial fractions because standard transform pairs generally have a \(z\) in the numerator.

---

## 32.1 Distinct poles

If the poles are distinct,

\[
\boxed{
\frac{X(z)}{z}
=
\frac{C_1}{z-p_1}
+
\frac{C_2}{z-p_2}
+\cdots+
\frac{C_N}{z-p_N}.
}
\]

The coefficient for pole \(p_k\) is obtained by

\[
\boxed{
C_k
=
\left[
(z-p_k)\frac{X(z)}{z}
\right]_{z=p_k}.
}
\]

---

## 32.2 Repeated pole

For a repeated pole, differentiation is required.

For multiplicity \(l\),

\[
\boxed{
C_{k,l}
=
\frac{1}{(l-i)!}
\left[
\frac{d^{\,l-i}}{dz^{\,l-i}}
\left(
(z-p_k)^l\frac{X(z)}{z}
\right)
\right]_{z=p_k}
}
\]

with the equivalent derivative form used in the textbook.

---

## 32.3 Complex-conjugate poles

If poles are a complex-conjugate pair,

\[
p,\;p^*,
\]

their partial-fraction coefficients are also conjugates:

\[
\boxed{C,\;C^*.}
\]

This ensures a real-valued sequence when the original transform has real coefficients.

---

# Example 3.29 — Long Division for a Causal ROC

Given a rational transform with

\[
\mathrm{ROC}:|z|>1,
\]

the sequence must be causal.

Therefore, perform long division in descending powers of \(z\), obtaining

\[
X(z)
=
x(0)+x(1)z^{-1}+x(2)z^{-2}+\cdots.
\]

The textbook obtains the beginning of the sequence as

\[
\boxed{
x(n)=\{0,1,5,11,12,13,\ldots\}.
}
\]

Repeating the division in ascending powers of \(z^{-1}\) provides the same result.

---

# Example 3.30 — Long Division for a Non-Causal ROC

If

\[
\mathrm{ROC}:|z|<1,
\]

the sequence must be non-causal.

Therefore, use the expansion containing positive powers of \(z\).

The textbook obtains a left-sided sequence whose terms decay toward more negative indices.

### Memory rule

\[
\boxed{
|z|>\text{pole radius}
\Rightarrow
\text{right-sided expansion}
}
\]

\[
\boxed{
|z|<\text{pole radius}
\Rightarrow
\text{left-sided expansion}
}
\]

---

# Example 3.31 — Partial Fractions with Distinct Poles

For a rational transform whose factored denominator gives distinct poles, write

\[
\frac{X(z)}{z}
=
\frac{A}{z-p_1}
+
\frac{B}{z-p_2}.
\]

Find \(A\) and \(B\) using pole substitution.

Then, for a causal ROC, use

\[
\frac{z}{z-p}
\;\xleftrightarrow{\mathcal Z^{-1}}\;
p^nu(n).
\]

For a left-sided ROC, use

\[
\frac{z}{z-p}
\;\xleftrightarrow{\mathcal Z^{-1}}\;
-p^n u(-n-1).
\]

---

# Example 3.32 — Repeated Poles

When a transform contains repeated poles, write terms such as

\[
\frac{C_1}{z-p}
+
\frac{C_2}{(z-p)^2}
+
\frac{C_3}{(z-p)^3}.
\]

Use the residue/derivative formula or the partial-fraction equations.

### Exam strategy

1. Factor denominator.
2. Identify pole multiplicities.
3. Write complete partial-fraction structure.
4. Solve constants.
5. Determine sidedness from ROC.
6. Take the inverse using standard pairs.

---

# Example 3.33 — All Possible Inverse Z-Transforms

Consider a transform with poles at

\[
z=\frac12,\qquad z=\frac14.
\]

Possible ROCs are:

\[
\boxed{|z|>\frac12}
\]

\[
\boxed{\frac14<|z|<\frac12}
\]

\[
\boxed{|z|<\frac14}.
\]

Therefore there are **three possible time-domain sequences**.

### Case 1: \( |z|>\frac12 \)

Both pole terms are causal.

### Case 2: \( \frac14<|z|<\frac12 \)

The pole at \(1/4\) is represented causally, while the pole at \(1/2\) is represented anticausally.

### Case 3: \( |z|<\frac14 \)

Both are anticausal.

\[
\boxed{\text{Number of valid inverse sequences = number of allowable pole-separated ROCs.}}
\]

---

# Example 3.34 — All Four Possible ROCs

For poles at

\[
z=1,\quad2,\quad3,
\]

the allowable ROCs are

\[
|z|>3,
\]

\[
2<|z|<3,
\]

\[
1<|z|<2,
\]

\[
|z|<1.
\]

Each ROC creates a different sidedness assignment and hence a different inverse sequence.

This is a classic exam question.

---

# Example 3.35 — Find the Causal Signal

Given a rational \(X(z)\) with poles at

\[
z=\frac12,\qquad z=\frac14,
\]

and the requirement that the signal is causal:

\[
\boxed{\mathrm{ROC}:|z|>\frac12.}
\]

Perform partial fractions and use the right-sided inverse pair for each pole.

The textbook obtains a causal sequence of the form

\[
\boxed{
x(n)
=
6\left(\frac12\right)^n u(n)
-
20\left(\frac12\right)^n u(n)
+
20\left(\frac14\right)^n u(n)
}
\]

for the constants resulting from its transform.

The central point is the **ROC-driven choice of causal expansion**.

---

# 33. Method 3 — Complex-Inversion / Residue Method

Use

\[
\boxed{
x(n)=
\frac1{2\pi j}
\oint_C X(z)z^{n-1}\,dz
}
\]

and evaluate using residues.

If \(X(z)z^{n-1}\) has a simple pole at \(z=z_i\),

\[
\boxed{
\operatorname{Res}
=
\lim_{z\to z_i}
(z-z_i)X(z)z^{n-1}.
}
\]

For a repeated pole of order \(k\),

\[
\boxed{
\operatorname{Res}
=
\frac{1}{(k-1)!}
\lim_{z\to z_i}
\frac{d^{k-1}}{dz^{k-1}}
\left[
(z-z_i)^kX(z)z^{n-1}
\right].
}
\]

---

# Example 3.36 — Residue Method

The chapter applies residues at the poles lying inside the contour selected by the ROC.

Procedure:

1. identify the poles;
2. choose contour \(C\) inside the ROC;
3. determine which poles lie inside \(C\);
4. calculate residues;
5. sum the residues;
6. multiply by the appropriate contour factor from the inversion integral.

The resulting sequence is the inverse Z-transform.

---

# Example 3.37 — Repeated-Pole Residue

For a transform with a second-order pole at

\[
z=\frac12,
\]

the residue is calculated using

\[
\operatorname{Res}
=
\left.
\frac{d}{dz}
\left[
(z-\tfrac12)^2X(z)z^{n-1}
\right]
\right|_{z=1/2}.
\]

This produces the characteristic form

\[
\boxed{
(\alpha+\beta n)
\left(\frac12\right)^n
u(n)
}
\]

for a causal repeated-pole contribution.

---

# Example 3.38 — All Possible Inverses by Residues

If the poles are

\[
z=-1\quad\text{(order 2)}
\]

and

\[
z=-2\quad\text{(order 3)},
\]

the possible ROCs are

\[
|z|>2,
\qquad
|z|<1,
\qquad
1<|z|<2.
\]

Therefore:

- for \( |z|>2 \): both contributions are right-sided;
- for \( |z|<1 \): both are left-sided;
- for \(1<|z|<2\): the \(-1\) contribution is right-sided and the \(-2\) contribution is left-sided.

This is the residue-method equivalent of Example 3.33/3.34.

---

# 34. Method 4 — Convolution Method for Inverse Z-Transform

Suppose

\[
X(z)=X_1(z)X_2(z).
\]

Then

\[
x(n)=x_1(n)*x_2(n).
\]

Procedure:

1. factor \(X(z)\);
2. split it into convenient transform factors;
3. inverse-transform each factor separately;
4. convolve the resulting sequences.

\[
\boxed{
X(z)=X_1(z)X_2(z)
\Rightarrow
x(n)=x_1(n)*x_2(n).
}
\]

This method is especially useful when the factors correspond to simple standard transform pairs.

---

# Example 3.39 — LTI System Analysis Using Convolution Property

For an LTI system,

\[
y(n)=x(n)*h(n).
\]

Taking Z-transform:

\[
\begin{aligned}
Y(z)
&=
\mathcal Z\{x(n)*h(n)\}\\
&=
X(z)H(z).
\end{aligned}
\]

Hence,

\[
\boxed{
Y(z)=X(z)H(z).
}
\]

Therefore,

\[
\boxed{
y(n)=\mathcal Z^{-1}\{X(z)H(z)\}.
}
\]

This is the basic Z-domain procedure for LTI-system analysis.

---

# Example 3.40 — Inverse Z-Transform by Convolution

Split

\[
X(z)=X_1(z)X_2(z)
\]

so that

\[
X_1(z)\leftrightarrow x_1(n)
\]

and

\[
X_2(z)\leftrightarrow x_2(n).
\]

Then

\[
\boxed{
x(n)=x_1(n)*x_2(n).
}
\]

The textbook evaluates the resulting convolution using the geometric-series form.

---

# Example 3.41 — Another Convolution-Inversion Problem

The same procedure is applied:

\[
X(z)=X_1(z)X_2(z),
\]

then

\[
x_1(n)=\mathcal Z^{-1}\{X_1(z)\},
\qquad
x_2(n)=\mathcal Z^{-1}\{X_2(z)\},
\]

and finally

\[
\boxed{
x(n)=\sum_kx_1(k)x_2(n-k).
}
\]

The key exam idea is that factorization can turn a difficult inverse into two easy inverses plus one convolution.

---

# 35. Analysis of Discrete-Time LTI Systems

For a discrete-time LTI system,

\[
y(n)=x(n)*h(n).
\]

Taking Z-transform,

\[
Y(z)=X(z)H(z).
\]

Therefore,

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}.
}
\]

The quantity \(H(z)\) is called the:

- **system function**;
- **transfer function**.

The textbook defines \(H(z)\) as the ratio of the Z-transform of output to that of input when initial conditions are neglected.

---

# 36. Why \(H(z)\) Is Also the Transform of the Impulse Response

If

\[
x(n)=\delta(n),
\]

then

\[
X(z)=1.
\]

Since

\[
Y(z)=X(z)H(z),
\]

we get

\[
Y(z)=H(z).
\]

But the output to an impulse is the impulse response:

\[
y(n)=h(n).
\]

Thus,

\[
\boxed{
H(z)=\mathcal Z\{h(n)\}.
}
\]

---

# 37. Difference Equation to System Function

A general LTI difference equation is written as

\[
\boxed{
\sum_{k=0}^{N}a_k y(n-k)
=
\sum_{k=0}^{M}b_k x(n-k).
}
\]

Taking Z-transform and assuming zero initial conditions:

\[
\left(
\sum_{k=0}^{N}a_kz^{-k}
\right)Y(z)
=
\left(
\sum_{k=0}^{M}b_kz^{-k}
\right)X(z).
\]

Therefore,

\[
\boxed{
H(z)
=
\frac{Y(z)}{X(z)}
=
\frac{
\sum_{k=0}^{M}b_kz^{-k}
}{
\sum_{k=0}^{N}a_kz^{-k}
}.
}
\]

This is one of the most important formulas in the chapter.

---

# 38. Frequency Response

For an LTI system,

\[
\boxed{
H(e^{j\omega})
=
H(z)\big|_{z=e^{j\omega}}.
}
\]

The frequency response exists as the boundary value on the unit circle when that unit circle belongs to the ROC.

---

# Example 3.42 — Difference Equation and Stability

Given

\[
H(z)=\frac{1}{1-\frac12z^{-1}},
\]

write

\[
Y(z)
=
\frac{1}{1-\frac12z^{-1}}X(z).
\]

Thus,

\[
\left(1-\frac12z^{-1}\right)Y(z)=X(z).
\]

Taking inverse Z-transform,

\[
\boxed{
y(n)-\frac12y(n-1)=x(n).
}
\]

The pole is

\[
z=\frac12.
\]

Since

\[
\left|\frac12\right|<1,
\]

a causal system has a stable pole location.

\[
\boxed{\text{Stable}}
\]

---

# Example 3.43 — Frequency Response from System Function

For a system function obtained in negative powers of \(z\),

\[
H(z)
=
\frac{N(z^{-1})}{D(z^{-1})},
\]

substitute

\[
z=e^{j\omega}.
\]

Then

\[
z^{-1}=e^{-j\omega},
\qquad
z^{-2}=e^{-j2\omega},
\]

and therefore

\[
\boxed{
H(e^{j\omega})
=
\frac{N(e^{-j\omega})}
{D(e^{-j\omega})}.
}
\]

Expand

\[
e^{-j\omega}=\cos\omega-j\sin\omega
\]

when magnitude and phase are needed.

---

# Example 3.44 — Find System Function from Difference Equation

Given

\[
y(n)-y(n-1)
=
2x(n)-3x(n-1),
\]

taking the Z-transform:

\[
\begin{aligned}
Y(z)-z^{-1}Y(z)
&=
2X(z)-3z^{-1}X(z).
\end{aligned}
\]

Therefore,

\[
\begin{aligned}
Y(z)(1-z^{-1})
&=
X(z)(2-3z^{-1}),
\end{aligned}
\]

and

\[
\boxed{
H(z)
=
\frac{2-3z^{-1}}
{1-z^{-1}}.
}
\]

---

# 39. Causality and Stability

For a causal LTI system:

\[
\boxed{
h(n)=0,\qquad n<0.
}
\]

Thus the ROC of \(H(z)\) is outside the outermost pole.

For BIBO stability:

\[
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty.
}
\]

Therefore the ROC must include the unit circle.

For a **causal rational LTI system**, this becomes the familiar pole condition:

\[
\boxed{
\text{All poles must lie strictly inside the unit circle.}
}
\]

---

# 40. Pole-Based Stability Decision

For a causal rational system:

### All poles inside unit circle

\[
|p_i|<1
\quad\forall i
\]

\[
\boxed{\text{Stable}}
\]

### Pole on unit circle

\[
|p_i|=1
\]

\[
\boxed{\text{Not BIBO stable}}
\]

### Pole outside unit circle

\[
|p_i|>1
\]

\[
\boxed{\text{Unstable}}
\]

---

# Example 3.45 — Stable vs. Unstable Systems

### (a)

Suppose poles are

\[
0.5\pm j0.74.
\]

Magnitude:

\[
|p|
=
\sqrt{0.5^2+0.74^2}
<1.
\]

Therefore a causal system is stable.

### (b)

Suppose poles are

\[
1.445,\qquad0.555.
\]

Since

\[
1.445>1,
\]

one pole is outside the unit circle.

\[
\boxed{\text{System is unstable}.}
\]

---

# Example 3.46 — System Function, Frequency Response, Impulse Response and Stability

The textbook gives both input and output:

\[
X(z)=\mathcal Z\{x(n)\},
\qquad
Y(z)=\mathcal Z\{y(n)\}.
\]

### Step 1 — System function

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}.
}
\]

### Step 2 — Frequency response

\[
\boxed{
H(e^{j\omega})=H(z)|_{z=e^{j\omega}}.
}
\]

### Step 3 — Impulse response

Factor \(H(z)\), perform partial fractions of \(H(z)/z\), then inverse-transform.

### Step 4 — Stability

Locate poles of \(H(z)\).

If both poles are inside the unit circle,

\[
\boxed{\text{stable}.}
\]

---

# Example 3.47 — Design a Causal LTI System from Input/Output

Given \(x(n)\) and desired \(y(n)\):

\[
X(z)=\mathcal Z\{x(n)\},
\qquad
Y(z)=\mathcal Z\{y(n)\}.
\]

Then

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}.
}
\]

Because the problem explicitly requires a causal system, choose the causal ROC:

\[
\boxed{\mathrm{ROC}:
|z|>\text{outermost pole}.}
\]

Then obtain

\[
h(n)=\mathcal Z^{-1}\{H(z)\}.
\]

Finally,

\[
H(e^{j\omega})
=
H(z)\big|_{z=e^{j\omega}}.
\]

---

# Example 3.48 — Complete System Analysis

Given a causal LTI difference equation:

1. Take Z-transform.
2. Collect \(Y(z)\) terms.
3. Collect \(X(z)\) terms.
4. Form \(H(z)=Y(z)/X(z)\).
5. Find poles and zeros.
6. Use causality to select the ROC.
7. Test stability.
8. Obtain \(h(n)\) using partial fractions.
9. Obtain frequency response by \(z=e^{j\omega}\).

This is the **standard complete exam workflow**.

---

# Example 3.49 — Test Causality and Stability

For each system:

### Step 1

Factor the denominator and locate poles.

### Step 2

For causal realization, choose

\[
|z|>\text{outermost pole}.
\]

### Step 3

Check whether this ROC contains

\[
|z|=1.
\]

If yes, the causal realization is stable.

The textbook's first case is not both causal and stable because the outermost pole lies outside the unit circle.

The second case has all poles inside the unit circle and therefore is both causal and stable.

---

# Example 3.50 — Same System, Different ROC: Stable vs Causal

Suppose

\[
H(z)
\]

has poles at

\[
\frac14,\qquad2.
\]

Possible ROCs include

\[
|z|>2
\]

and

\[
\frac14<|z|<2.
\]

### Case (a) Stable

Choose

\[
\boxed{
\frac14<|z|<2
}
\]

because the unit circle is inside this ROC.

The resulting system is stable but non-causal.

### Case (b) Causal

Choose

\[
\boxed{|z|>2}.
\]

This is causal but does not contain the unit circle.

Therefore it is unstable.

### Key exam lesson

\[
\boxed{
\text{Causality and stability are determined jointly by poles + ROC.}
}
\]

---

# Example 3.51 — Pole-Zero Plot and Stability

Given a rational system function, solve

\[
D(z)=0
\]

for poles and

\[
N(z)=0
\]

for zeros.

If all poles satisfy

\[
|p|<1,
\]

the causal system is stable.

The textbook's example has poles near

\[
\pm j0.67,
\]

both inside the unit circle, hence

\[
\boxed{\text{stable}.}
\]

---

# Example 3.52 — First-Order IIR Stability

Given the system equation

\[
y(n)=x(n)+bx(n-1),
\]

the transform gives

\[
Y(z)
=
X(z)+bz^{-1}Y(z)
\]

for the corresponding system relation used in the textbook.

The system function is formed by isolating \(Y(z)\).

The textbook concludes stability from the pole location and notes the resulting first-order system behavior.

---

# 41. Impulse Response and Step Response of a Difference-Equation System

For a system described by a difference equation:

### Impulse response

Set

\[
\boxed{x(n)=\delta(n)}
\]

and solve for \(y(n)\).

Then

\[
\boxed{y(n)=h(n).}
\]

### Step response

Set

\[
\boxed{x(n)=u(n)}
\]

and solve for \(y(n)\).

Then the output is the step response.

---

# Example 3.53 — Impulse and Step Responses

The textbook:

1. obtains \(H(z)\);
2. finds \(h(n)=\mathcal Z^{-1}\{H(z)\}\);
3. for a step input uses

\[
X(z)=\frac{z}{z-1};
\]

4. calculates

\[
Y(z)=H(z)X(z);
\]

5. inverse-transforms to get the step response.

The system is unstable if a pole is on or outside the unit circle.

---

# Example 3.54 — Unit-Sample Response of a Difference Equation

For a relaxed system:

\[
\boxed{
\text{initial conditions }=0.
}
\]

Take the Z-transform:

\[
Y(z)D(z)=X(z)N(z).
\]

For unit impulse,

\[
X(z)=1.
\]

Therefore,

\[
\boxed{
H(z)=\frac{N(z)}{D(z)}.
}
\]

Then inverse-transform \(H(z)\) to obtain \(h(n)\).

---

# Example 3.55 — Impulse Response + Step Response + Stability

### Part (a)

Find

\[
H(z)=\frac{Y(z)}{X(z)}.
\]

Then partial-fraction-expand \(H(z)/z\) to obtain \(h(n)\).

### Part (b)

For a unit step,

\[
X(z)=\frac{z}{z-1},
\]

and

\[
Y(z)=H(z)X(z).
\]

Then inverse-transform.

### Part (c)

Check pole magnitudes.

A pole outside the unit circle implies

\[
\boxed{\text{unstable}.}
\]

---

# Example 3.56 — Stability from Complex Poles

Given poles

\[
z=\frac12\pm j\frac{\sqrt3}{2},
\]

their magnitude is

\[
|z|
=
\sqrt{
\left(\frac12\right)^2+
\left(\frac{\sqrt3}{2}\right)^2
}
=1.
\]

Therefore they lie **on** the unit circle.

\[
\boxed{\text{Not BIBO stable}.}
\]

This is a useful quick check for complex-conjugate poles.

---

# Example 3.57 — Pole at \(z=1\)

If

\[
H(z)=\frac{1+z^{-1}}{1-z^{-1}},
\]

then the pole is at

\[
z=1.
\]

Because the pole lies on the unit circle,

\[
\boxed{\text{unstable}.}
\]

---

# Example 3.58 — Stability Conditions for Two Systems

### (a)

For

\[
y(n)=a^n u(n),
\]

the corresponding pole is at

\[
z=a.
\]

Therefore the stable causal condition is

\[
\boxed{|a|<1.}
\]

### (b)

For a system with pole

\[
z=e^a,
\]

stability requires

\[
\boxed{|e^a|<1.}
\]

For real \(a\),

\[
\boxed{a<0.}
\]

---

# Example 3.59 — Impulse Response from Difference Equation

Procedure:

\[
\boxed{
\text{difference equation}
\to
H(z)
\to
\frac{H(z)}{z}
\to
\text{partial fractions}
\to
h(n).
}
\]

The textbook's result has the standard IIR form

\[
\boxed{
h(n)
=
C_1p_1^n u(n)
+
C_2p_2^n u(n)
+\cdots.
}
\]

The pole magnitudes then determine stability.

---

# Example 3.60 — Impulse and Step Response of a Causal System

First derive

\[
H(z).
\]

For impulse input:

\[
X(z)=1
\]

so

\[
Y(z)=H(z),
\]

hence

\[
\boxed{y(n)=h(n).}
\]

For step input:

\[
X(z)=\frac{z}{z-1},
\]

so

\[
\boxed{
Y(z)=
H(z)\frac{z}{z-1}.
}
\]

The textbook gives the resulting step response as a shifted ramp-type sequence.

---

# 42. Natural, Forced and Total Response

This is one of the most important conceptual sections.

## 42.1 Natural / source-free / transient response

The response caused only by initial conditions, with input set to zero:

\[
\boxed{x(n)=0.}
\]

It is called:

- natural response;
- source-free response;
- transient response.

---

## 42.2 Forced / steady-state response

The response caused by the input, with initial conditions neglected:

\[
\boxed{\text{initial conditions}=0.}
\]

It is called:

- forced response;
- steady-state response.

---

## 42.3 Total response

When both the input and initial conditions are present,

\[
\boxed{
y(n)=y_{\text{natural}}(n)+y_{\text{forced}}(n).
}
\]

The textbook states that for a stable system the natural/transient part decays with time.

---

# Example 3.61 — Natural, Forced and Frequency Responses

Given a difference equation with initial conditions:

### (a) Natural response

Set

\[
x(n)=0.
\]

Keep the initial conditions.

Solve the homogeneous system using the unilateral Z-transform.

### (b) Forced response

Set the specified input, but neglect initial conditions.

### (c) Frequency response

For the relaxed LTI system,

\[
\boxed{
H(e^{j\omega})=H(z)|_{z=e^{j\omega}}.
}
\]

---

# Example 3.62 — Free and Forced Response Separately

### Part (a) Free response

Set

\[
x(n)=0.
\]

Retain

\[
y(-1),\;y(-2),\ldots
\]

in the unilateral transform equations.

### Part (b) Forced response

Use the input \(x(n)\) and set all initial conditions to zero.

### Final understanding

\[
\boxed{
\text{Total response}
=
\text{free response}
+
\text{forced response}.
}
\]

---

# Example 3.63 — Immediate Impulse/Step Identification

If the system equation is directly written as

\[
y(n)=2x(n)+3x(n-1)+\cdots,
\]

then for impulse input

\[
x(n)=\delta(n),
\]

the output is immediately

\[
\boxed{
h(n)=2\delta(n)+3\delta(n-1)+\cdots.
}
\]

For step input,

\[
x(n)=u(n),
\]

replace each input impulse term by the corresponding shifted step.

---

# Example 3.64 — Total Response with Initial Condition

Suppose

\[
y(n)=2y(n-1)+x(n)
\]

with a specified nonzero initial condition.

Using the unilateral Z-transform:

\[
\mathcal Z_u\{y(n-1)\}
=
z^{-1}Y(z)+y(-1).
\]

Hence,

\[
Y(z)
=
2z^{-1}Y(z)+2y(-1)+X(z).
\]

Collect \(Y(z)\):

\[
\boxed{
Y(z)(1-2z^{-1})
=
X(z)+2y(-1).
}
\]

This clearly shows why the unilateral transform is convenient for initial-condition problems.

---

# Example 3.65 — Unilateral Z-Transform with Initial Conditions

For a second-order difference equation, use the unilateral delay formulas:

\[
\mathcal Z_u\{y(n-1)\}
=
z^{-1}Y(z)+y(-1),
\]

\[
\boxed{
\mathcal Z_u\{y(n-2)\}
=
z^{-2}Y(z)
+y(-1)z^{-1}
+y(-2).
}
\]

Substitute the given initial conditions immediately, solve algebraically for \(Y(z)\), then inverse-transform.

This is the textbook's standard workflow for total response with initial conditions.

---

# Example 3.66 — Response to Exponential Input

For

\[
x(n)=a^n u(n),
\]

first take

\[
X(z)=\frac{z}{z-a}.
\]

Find \(H(z)\), then

\[
Y(z)=H(z)X(z).
\]

Partial fractions give an output consisting of exponential terms whose rates come from:

- the input pole;
- the system poles.

This demonstrates how the pole structure determines the response components.

---

# Example 3.67 — Step Response from a Given Impulse Response

Given

\[
h(n)=a^n u(n),
\qquad |a|<1,
\]

we have

\[
H(z)=\frac{z}{z-a}.
\]

For a step input,

\[
X(z)=\frac{z}{z-1}.
\]

Therefore,

\[
\begin{aligned}
Y(z)
&=
H(z)X(z)\\
&=
\frac{z^2}
{(z-a)(z-1)}.
\end{aligned}
\]

Partial fractions or the known running-sum relation gives

\[
\boxed{
y(n)=
\frac{1-a^{n+1}}{1-a}
u(n).
}
\]

---

# 43. Frequency Response, Magnitude and Phase Response

Given

\[
H(z),
\]

set

\[
z=e^{j\omega}.
\]

Then

\[
H(e^{j\omega})
=
H_R(\omega)+jH_I(\omega).
\]

The magnitude response is

\[
\boxed{
|H(e^{j\omega})|
=
\sqrt{H_R^2(\omega)+H_I^2(\omega)}.
}
\]

The phase response is

\[
\boxed{
\angle H(e^{j\omega})
=
\tan^{-1}
\left(
\frac{H_I(\omega)}{H_R(\omega)}
\right)
}
\]

with the quadrant-aware interpretation when required.

---

# Example 3.68 — Frequency, Magnitude and Phase Response

For a second-order denominator,

\[
H(e^{j\omega})
=
\frac{1-e^{-j\omega}}
{1-\frac34e^{-j\omega}+\frac18e^{-j2\omega}},
\]

use

\[
e^{-j\omega}
=
\cos\omega-j\sin\omega
\]

and

\[
e^{-j2\omega}
=
\cos2\omega-j\sin2\omega.
\]

Separate into real and imaginary parts:

\[
H(e^{j\omega})
=
\frac{A(\omega)+jB(\omega)}
{C(\omega)+jD(\omega)}.
\]

Then:

\[
\boxed{
|H|
=
\frac{\sqrt{A^2+B^2}}
{\sqrt{C^2+D^2}}
}
\]

and

\[
\boxed{
\angle H
=
\tan^{-1}\!\frac{B}{A}
-
\tan^{-1}\!\frac{D}{C}.
}
\]

---

# Example 3.69 — Another Frequency-Response Calculation

For

\[
H(z)
=
\frac{z^{-1}+3z^{-2}+2z^{-3}}
{2+z^{-1}+\cdots},
\]

substitute

\[
z=e^{j\omega}.
\]

Then compute:

\[
H(e^{j\omega}),
\]

followed by

\[
|H(e^{j\omega})|
\]

and

\[
\angle H(e^{j\omega}).
\]

The exam sequence is:

\[
\boxed{
H(z)
\rightarrow
z=e^{j\omega}
\rightarrow
H(e^{j\omega})
\rightarrow
|H|
,\angle H.
}
\]

---

# Example 3.70 — Steady-State Response

For

\[
x(n)=\cos(\omega_0n)u(n)
\]

and a stable LTI system,

\[
y(n)=x(n)*h(n).
\]

The Z-transform route is

\[
X(z)\rightarrow H(z)\rightarrow Y(z)=X(z)H(z)
\rightarrow y(n).
\]

For the long-term sinusoidal behavior, the frequency response at \(\omega_0\) determines amplitude and phase modification.

---

# Example 3.71 — Finite-Sequence LTI Response with Z-Transform

Given

\[
x(n)=\{1,2,3,6\},
\qquad
h(n)=\{1,2,1,-1\},
\]

write

\[
X(z)=1+2z^{-1}+3z^{-2}+6z^{-3}
\]

and

\[
H(z)=1+2z^{-1}+z^{-2}-z^{-3}.
\]

Then

\[
Y(z)=X(z)H(z).
\]

After collecting powers,

\[
\boxed{
y(n)=\{1,4,8,8,3,2,-6\}
}
\]

for the coefficient sequence represented by the source's polynomial expansion.

> **Note:** The printed source OCR can visually merge the last coefficients in some displays; preserve the polynomial multiplication when checking a handwritten answer.

---

# Example 3.72 — Obtain \(h(n)\) from a Given Step Response

The step response satisfies

\[
s(n)=h(n)*u(n).
\]

Hence,

\[
S(z)=H(z)U(z)
\]

and

\[
U(z)=\frac{z}{z-1}.
\]

Therefore,

\[
\boxed{
H(z)=S(z)\frac{z-1}{z}.
}
\]

Then inverse-transform \(H(z)\) to obtain \(h(n)\).

This is the standard relationship:

\[
\boxed{
h(n)=s(n)-s(n-1).
}
\]

---

# Example 3.73 — Stable All-Pass System

The textbook considers a causal system whose system function has a pole at

\[
z=a
\]

and a zero at

\[
z=\frac1a.
\]

For stability of the causal system,

\[
\boxed{|a|<1.}
\]

For the frequency response,

\[
H(e^{j\omega})
=
\frac{e^{j\omega}-1/a}{e^{j\omega}-a}.
\]

The textbook simplifies the magnitude and obtains

\[
\boxed{
|H(e^{j\omega})|=1
}
\]

for all \(\omega\).

Therefore the system is **all-pass**: it changes phase but not magnitude.

---

# 44. Deconvolution in Z-Domain

If

\[
y(n)=x(n)*h(n),
\]

then

\[
Y(z)=X(z)H(z).
\]

Therefore,

\[
\boxed{
X(z)=\frac{Y(z)}{H(z)}
}
\]

or

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}.
}
\]

After division, take the inverse Z-transform.

---

# Example 3.74 — Deconvolution

Given

\[
h(n)=\{2,-1,0,-1,3\}
\]

and

\[
y(n)=\{2,-5,1,-1,-6,11,6\},
\]

form

\[
H(z)=2-z^{-1}-z^{-3}+3z^{-4}
\]

and

\[
Y(z)
=
2-5z^{-1}+z^{-2}-z^{-3}-6z^{-4}
+11z^{-5}+6z^{-6}.
\]

Then

\[
\boxed{
X(z)=\frac{Y(z)}{H(z)}.
}
\]

Perform polynomial division and inverse-transform.

The textbook obtains

\[
\boxed{
x(n)=\{1,-3,2\}.
}
\]

---

# 45. Laplace Transform and Z-Transform Relation

Let a continuous-time signal \(x(t)\) be sampled with period \(T\).

The sampled impulse-train representation is

\[
x^*(t)
=
\sum_{n=-\infty}^{\infty}
x(nT)\delta(t-nT).
\]

Its Laplace transform is

\[
X^*(s)
=
\sum_{n=-\infty}^{\infty}
x(nT)e^{-nTs}.
\]

The Z-transform of the sample sequence is

\[
X(z)
=
\sum_{n=-\infty}^{\infty}
x(nT)z^{-n}.
\]

Comparing,

\[
\boxed{
z=e^{sT}.
}
\]

---

# 46. Mapping Between \(s\)-Plane and \(z\)-Plane

Let

\[
s=\sigma+j\Omega.
\]

Then

\[
\begin{aligned}
z
&=
e^{sT}\\
&=
e^{(\sigma+j\Omega)T}\\
&=
e^{\sigma T}e^{j\Omega T}.
\end{aligned}
\]

Therefore,

\[
\boxed{
|z|=e^{\sigma T}
}
\]

and

\[
\boxed{
\arg z=\Omega T.
}
\]

### Important mapping

If

\[
\sigma=0,
\]

then

\[
|z|=1.
\]

Thus:

\[
\boxed{
j\Omega\text{-axis in }s\text{-plane}
\leftrightarrow
\text{unit circle in }z\text{-plane}.
}
\]

If

\[
\sigma<0,
\]

then

\[
|z|<1.
\]

If

\[
\sigma>0,
\]

then

\[
|z|>1.
\]

---

# 47. Aliasing in the \(s\)-to-\(z\) Mapping

Because

\[
z=e^{sT}
\]

and

\[
e^{j(\Omega+2\pi/T)T}
=
e^{j\Omega T},
\]

frequencies separated by integer multiples of the sampling frequency map to the same angular position in the \(z\)-plane.

Therefore many \(s\)-plane points can map to the same \(z\)-plane point.

The textbook notes this as the mathematical source of the aliasing effect.

---

# 48. Very Important Exam Tables

## 48.1 Sequence type vs ROC

| Sequence | ROC |
|---|---|
| Infinite right-sided / causal | Outside outermost pole |
| Infinite left-sided / anticausal | Inside innermost pole |
| Infinite two-sided | Ring between poles |
| Finite causal | All \(z\) except \(0\) |
| Finite anticausal | All \(z\) except \(\infty\) |
| Finite two-sided | All \(z\) except \(0,\infty\) |
| Stable LTI system | ROC includes unit circle |
| Causal stable rational LTI | All poles strictly inside unit circle |

---

## 48.2 Most important properties

\[
\boxed{
ax_1(n)+bx_2(n)
\leftrightarrow
aX_1(z)+bX_2(z)
}
\]

\[
\boxed{
x(n-m)
\leftrightarrow
z^{-m}X(z)
}
\]

\[
\boxed{
a^nx(n)
\leftrightarrow
X(z/a)
}
\]

\[
\boxed{
x(-n)
\leftrightarrow
X(z^{-1})
}
\]

\[
\boxed{
x_e(n)
\leftrightarrow
X(z^k)
}
\]

\[
\boxed{
nx(n)
\leftrightarrow
-z\frac{dX(z)}{dz}
}
\]

\[
\boxed{
x_1(n)*x_2(n)
\leftrightarrow
X_1(z)X_2(z)
}
\]

\[
\boxed{
R_{12}(n)
\leftrightarrow
X_1(z)X_2(z^{-1})
}
\]

\[
\boxed{
x(0)=\lim_{z\to\infty}X(z)
}
\]

\[
\boxed{
x(\infty)=\lim_{z\to1}(z-1)X(z)
}
\]

---

# 49. Difference-Equation Exam Workflow

When a problem gives a difference equation:

### Step 1 — Write the equation clearly

\[
\sum a_k y(n-k)
=
\sum b_k x(n-k).
\]

### Step 2 — Take Z-transform

For zero initial conditions:

\[
z^{-k}Y(z)
\]

appears for delayed outputs.

### Step 3 — Collect \(Y(z)\)

\[
Y(z)
\left[
a_0+a_1z^{-1}+\cdots
\right].
\]

### Step 4 — Collect \(X(z)\)

\[
X(z)
\left[
b_0+b_1z^{-1}+\cdots
\right].
\]

### Step 5 — Form \(H(z)\)

\[
\boxed{
H(z)=
\frac{Y(z)}{X(z)}.
}
\]

### Step 6 — Find poles and zeros

Solve numerator \(=0\) and denominator \(=0\).

### Step 7 — Determine ROC

Use causality/stability conditions.

### Step 8 — Frequency response

\[
\boxed{z=e^{j\omega}.}
\]

### Step 9 — Impulse response

\[
\boxed{
h(n)=\mathcal Z^{-1}\{H(z)\}.
}
\]

---

# 50. Unilateral Z-Transform for Initial Conditions

For initial-condition problems, remember:

\[
\boxed{
\mathcal Z_u\{x(n-1)\}
=
z^{-1}X(z)+x(-1)
}
\]

and

\[
\boxed{
\mathcal Z_u\{x(n-2)\}
=
z^{-2}X(z)+x(-1)z^{-1}+x(-2).
}
\]

More generally,

\[
\mathcal Z_u\{x(n-m)\}
=
z^{-m}X(z)
+
\text{initial-condition terms}.
\]

### Exam memory

**Bilateral transform + zero initial conditions = clean shift rule.**

**Unilateral transform + nonzero initial conditions = shift rule + initial terms.**

---

# 51. Short Questions with Answers

## 1. How are discrete-time systems analysed using Z-transform?

Difference equations are converted into algebraic equations in the \(z\)-domain; after solving, the inverse Z-transform gives the time-domain result.

## 2. Define Z-transform.

\[
\boxed{
X(z)=\sum_{n=-\infty}^{\infty}x(n)z^{-n}
}
\]

for the bilateral transform.

## 3. What are the advantages of Z-transform?

It simplifies difference equations, converts convolution into multiplication, and exists for many signals whose DTFT does not exist.

## 4. What is the condition for Z-transform to exist?

The defining summation must converge for some set of \(z\)-values.

## 5. Relation between DTFT and Z-transform?

\[
\boxed{
X(e^{j\omega})=X(z)|_{z=e^{j\omega}}
}
\]

when the unit circle is in the ROC.

## 6. When are Z-transform and DTFT the same?

When

\[
\boxed{r=1}
\]

for

\[
z=re^{j\omega}.
\]

## 7. How do you get DTFT from Z-transform?

Substitute

\[
\boxed{z=e^{j\omega}}
\]

into \(X(z)\), provided the unit circle is in the ROC.

## 8. What is ROC?

The range of \(z\)-values for which the Z-transform converges.

## 9. ROC of infinite-duration causal sequence?

Outside the outermost pole.

## 10. ROC of infinite-duration non-causal sequence?

Inside the innermost pole.

## 11. ROC of infinite two-sided sequence?

A ring, if the transform exists.

## 12. ROC of the sum of sequences?

The intersection of their individual ROCs, subject to possible cancellation.

## 13. ROC of finite positive-time sequence?

Entire \(z\)-plane except \(z=0\).

## 14. ROC of finite negative-time sequence?

Entire \(z\)-plane except \(z=\infty\).

## 15. ROC of finite two-sided sequence?

Entire \(z\)-plane except \(0\) and \(\infty\).

## 16. Define transfer function.

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}
}
\]

with initial conditions neglected.

It is also

\[
\boxed{
H(z)=\mathcal Z\{h(n)\}.
}
\]

## 17. Stability condition?

The impulse response must be absolutely summable:

\[
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty.
}
\]

---

# 52. Review Questions

The textbook asks students to be able to:

1. Derive the relation between Z-transform and DTFT.
2. Distinguish one-sided and two-sided Z-transforms and state applications.
3. Derive the relation between Laplace and Z-transforms.
4. Write the properties of ROC.
5. State and prove initial and final value theorems.
6. Define inverse Z-transform and explain its methods.
7. Explain analysis of a discrete-time time-invariant system using convolution properties.
8. Prove that the ROC of a causal sequence is the exterior of a circle of radius \(r\).

---

# 53. Fill-in-the-Blanks — Master Answers

1. Z-transform converts difference equations into **algebraic** equations.
2. Z-transform plays the same role for discrete-time systems as **Laplace transform** does for continuous-time systems.
3. The range of \(z\)-values for convergence is called the **ROC**.
4. Z-transform of \(x(n)\) is related to the DTFT of **\(x(n)r^{-n}\)**.
5. DTFT and Z-transform are the same when **\(r=1\)**.
6. ROC of the sum is the **intersection** of individual ROCs.
7. ROC of a causal signal is the **exterior** of a circle.
8. ROC of a non-causal signal is the **interior** of a circle.
9. For a rational transform, ROC is bounded by **poles** or extends to **infinity**.
10. Initial value:
   \[
   \boxed{x(0)=\lim_{z\to\infty}X(z)}
   \]
11. Final value:
   \[
   \boxed{x(\infty)=\lim_{z\to1}(z-1)X(z)}
   \]
12. A **two-sided** sequence cannot generally be obtained by ordinary one-sided long division.
13. Frequency response is obtained using
   \[
   \boxed{z=e^{j\omega}}.
   \]
14. For a causal stable LTI system, all poles lie **inside the unit circle**.
15. The ROC of a causal stable system includes the **unit circle**.
16. Input-only response is the **forced** response.
17. Initial-condition-only response is the **natural/free** response.
18. Their sum is the **total** response.
19. Output due to unit sample input is the **impulse response**.

---

# 54. Objective-Type Questions — Conceptual Answer Key

The chapter's objective section tests mainly:

- ROC of sums of causal/anticausal exponentials;
- coefficient identification from polynomial \(X(z)\);
- possible inverse sequences for the same \(X(z)\);
- initial-value theorem;
- final-value theorem;
- stability from pole positions;
- the only transform with entire-plane ROC;
- causal/non-causal interpretations of rational transforms.

### Fast solving principle

\[
\boxed{
\text{Do not look at }X(z)\text{ alone.}
}
\]

Always inspect:

\[
\boxed{
\text{poles + ROC + sidedness}.
}
\]

---

# 55. Problems from the Chapter — Organized by Type

## Type A — Find \(X(z)\) and ROC

Problems include finite sequences and several causal/shifted expressions.

**Method:**

\[
X(z)=\sum x(n)z^{-n}
\]

and determine ROC from the support.

---

## Type B — Use Z-transform Properties

Typical expressions contain:

- \(nu(n)\);
- \(n^2u(n)\);
- \(a^nu(n)\);
- \(n a^nu(n)\);
- sinusoidal terms;
- exponential-scaled terms;
- shifted sequences.

**Method:** choose the property that changes the known transform into the target.

---

## Type C — Inverse Z-transform by Power Series

Determine sidedness from ROC first.

Then:

- right-sided \(\rightarrow\) expand in \(z^{-1}\);
- left-sided \(\rightarrow\) expand in positive powers of \(z\).

---

## Type D — Partial Fractions and All Possible Signals

1. Find poles.
2. List all mathematically allowable ROCs.
3. For each ROC, classify each pole term as causal or anticausal.
4. Write inverse sequence.

---

## Type E — Convolution-Theorem Problems

\[
X(z)=X_1(z)X_2(z)
\]

then

\[
x(n)=x_1(n)*x_2(n).
\]

---

## Type F — System Design

Given input/output,

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}.
}
\]

Then find \(h(n)\), frequency response and stability.

---

## Type G — Difference Equation

Use:

\[
\boxed{
\text{difference equation}
\to
Y(z),X(z)
\to
H(z)
\to
\mathcal Z^{-1}
}
\]

with unilateral Z-transform when nonzero initial conditions are provided.

---

## Type H — Stability and Causality

Use poles + ROC.

For causal rational systems:

\[
\boxed{
|p_i|<1\quad\forall i
}
\]

is the fast stability test.

---

## Type I — Free / Forced / Total Response

### Free

\[
x(n)=0
\]

retain initial conditions.

### Forced

neglect initial conditions.

### Total

include both.

\[
\boxed{
y_{\text{total}}
=
y_{\text{free}}
+
y_{\text{forced}}
}
\]

---

# 56. MASTER EXAM DECISION TREE

## Question gives \(x(n)\) and asks \(X(z)\)

\[
\boxed{
\text{Write support}
\rightarrow
\text{write the summation}
\rightarrow
\text{sum it}
\rightarrow
\text{find ROC}.
}
\]

## Question gives \(X(z)\) and asks \(x(n)\)

\[
\boxed{
\text{Check ROC}
\rightarrow
\text{factor}
\rightarrow
\text{choose inverse method}.
}
\]

### If only a few coefficients are needed

Use:

\[
\boxed{\text{long division}.}
\]

### If factored rational function

Use:

\[
\boxed{\text{partial fractions}.}
\]

### If repeated/complex poles and residue is convenient

Use:

\[
\boxed{\text{residue method}.}
\]

### If \(X(z)\) factors into simple known transforms

Use:

\[
\boxed{\text{convolution method}.}
\]

## Question gives a difference equation

\[
\boxed{
\text{Z-transform}
\rightarrow
H(z)
\rightarrow
\text{poles/zeros}
\rightarrow
\text{ROC}
\rightarrow
\text{stability}
}
\]

## Question gives initial conditions

\[
\boxed{\text{Use unilateral Z-transform}.}
\]

---

# 57. Master Memory Page

## Definitions

\[
\boxed{
X(z)=\sum_{n=-\infty}^{\infty}x(n)z^{-n}
}
\]

\[
\boxed{
X_u(z)=\sum_{n=0}^{\infty}x(n)z^{-n}
}
\]

\[
\boxed{
\mathrm{ROC}
=
\text{set of }z\text{ values for which }X(z)\text{ converges}
}
\]

---

## DTFT relation

\[
\boxed{
X(e^{j\omega})=X(z)\big|_{z=e^{j\omega}}
}
\]

---

## Core properties

\[
\boxed{
x_1+x_2
\leftrightarrow
X_1+X_2
}
\]

\[
\boxed{
x(n-m)
\leftrightarrow
z^{-m}X(z)
}
\]

\[
\boxed{
a^nx(n)
\leftrightarrow
X(z/a)
}
\]

\[
\boxed{
x(-n)
\leftrightarrow
X(z^{-1})
}
\]

\[
\boxed{
nx(n)
\leftrightarrow
-zX'(z)
}
\]

\[
\boxed{
x_1*x_2
\leftrightarrow
X_1X_2
}
\]

\[
\boxed{
R_{12}
\leftrightarrow
X_1(z)X_2(z^{-1})
}
\]

---

## Value theorems

\[
\boxed{
x(0)=\lim_{z\to\infty}X(z)
}
\]

\[
\boxed{
x(\infty)=
\lim_{z\to1}(z-1)X(z)
}
\]

---

## System function

\[
\boxed{
H(z)=\frac{Y(z)}{X(z)}
}
\]

\[
\boxed{
H(z)=\mathcal Z\{h(n)\}
}
\]

\[
\boxed{
H(e^{j\omega})=H(z)|_{z=e^{j\omega}}
}
\]

---

## Stability

\[
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty
}
\]

For causal rational systems:

\[
\boxed{
\text{all poles strictly inside }|z|=1.
}
\]

---

## Response decomposition

\[
\boxed{
y_{\text{total}}
=
y_{\text{natural}}
+
y_{\text{forced}}
}
\]

---

## Laplace relation

\[
\boxed{
z=e^{sT}.
}
\]

---

# 58. Final Exam Checklist

Before submitting any Z-transform answer, verify:

- Did I state whether the sequence is causal, anticausal, or two-sided?
- Did I write the ROC?
- Did I identify poles correctly?
- Did I choose the inverse expansion consistent with the ROC?
- Did I distinguish bilateral and unilateral transform?
- If initial conditions exist, did I include them?
- If \(H(z)\) is requested, did I explicitly calculate \(Y(z)/X(z)\)?
- If frequency response is requested, did I substitute \(z=e^{j\omega}\)?
- If stability is requested, did I check the unit circle/pole locations?
- If final value theorem is used, did I check its pole condition?
- Did I keep the time-index origin correct?
- Did I preserve signs in shifted sequences?

---

# 59. High-Risk Mistakes to Avoid

## Mistake 1 — Ignoring ROC

Wrong:

\[
X(z)=\frac{z}{z-a}
\]

and stopping there.

Correct:

\[
\boxed{
X(z)=\frac{z}{z-a},
\quad
\text{ROC }|z|>|a|
}
\]

or another ROC if the sequence is left-sided.

---

## Mistake 2 — Assuming every rational transform is causal

The ROC decides sidedness.

---

## Mistake 3 — Using bilateral shift rules with nonzero initial conditions

For initial-condition problems, use the unilateral formulas.

---

## Mistake 4 — Using final value theorem without checking poles

A pole on/outside the unit circle can invalidate a finite final-value conclusion.

---

## Mistake 5 — Calling a system stable merely because a formula looks bounded

For LTI systems, use the impulse-response/pole-ROC criterion.

---

## Mistake 6 — Forgetting that the unit circle must belong to the ROC for frequency response

\[
\boxed{
H(e^{j\omega})
\text{ is meaningful as the system frequency response when the unit circle is in the ROC.}
}
\]

---

# 60. One-Page Problem-Solving Map

\[
\boxed{
\begin{array}{c}
\textbf{Sequence }x(n)\\[2mm]
\downarrow\\
\text{support / sidedness}\\[2mm]
\downarrow\\
X(z)=\sum x(n)z^{-n}\\[2mm]
\downarrow\\
\text{convergence}\\[2mm]
\downarrow\\
\textbf{ROC}\\
\end{array}
}
\]

For inverse transforms:

\[
\boxed{
X(z)+\mathrm{ROC}
\rightarrow
\text{poles}
\rightarrow
\text{sidedness}
\rightarrow
\text{method}
\rightarrow
x(n)
}
\]

For LTI systems:

\[
\boxed{
\text{difference equation}
\rightarrow
H(z)
\rightarrow
\begin{cases}
h(n)\\
H(e^{j\omega})\\
\text{stability}\\
\text{causality}
\end{cases}
}
\]

For initial conditions:

\[
\boxed{
\text{unilateral Z-transform}
\rightarrow
Y(z)
\rightarrow
y(n)
}
\]

For free/forced response:

\[
\boxed{
y_{\text{total}}
=
y_{\text{free}}
+
y_{\text{forced}}.
}
\]

---

# End of Chapter 3 Notes
