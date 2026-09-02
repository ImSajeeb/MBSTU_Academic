---
title: "Chapter 2 — Discrete Convolution and Correlation"
subtitle: "Exam Master Notes — A. Anand Kumar"
author: "Prepared from Chapter 2, printed pages 90–178"
geometry: margin=0.75in
---

> **Formatting convention**
>
> All multi-step mathematical derivations are intentionally written with aligned display equations.  
> Definitions and final results are boxed. Sequence samples are kept in braces, with the time origin stated explicitly whenever indexing matters.
>
> **Reading rule:** do not skip a transition in a calculation. Each line either applies a formula, changes the summation limits, substitutes values, or simplifies the previous line.


> **Scope note — source-faithful theory:** The added theory sections below are based on Chapter 2 of *Digital Signal Processing — A. Anand Kumar*, printed pages 90–178. They preserve the book’s terminology and methods; where the extracted PDF text is unclear, no new numerical result has been invented. The examples remain in their worked form, while these sections supply the missing definitions, reasoning, rules, and exam interpretations.

# 1. Chapter roadmap

Chapter 2 covers:

1. Introduction to convolution and correlation
2. Impulse response and convolution sum
3. Analytical evaluation of discrete convolution
4. Convolution of finite sequences
5. Six practical methods for linear convolution
6. Deconvolution
7. Interconnection of LTI systems
8. Circular shift and circular symmetry
9. Periodic/circular convolution
10. Linear convolution from periodic convolution
11. Periodic convolution from linear convolution
12. Periodic extension of non-periodic signals
13. System response to periodic inputs
14. Cross correlation
15. Autocorrelation
16. Correlation computation and periodic correlation

---


## Theory: discrete-time system, excitation, response, and impulse response

A discrete-time system accepts an input sequence and produces an output sequence according to a specified operation. In the book's notation, the input sequence is the **system excitation** `x(n)` and the output sequence is the **system response** `y(n)`. For a system represented by an operator `T{·}`,

$$
\boxed{y(n)=T[x(n)]}
$$

The system is assumed to be initially relaxed (zero initial conditions) when the impulse-response method is used. If the excitation is a unit impulse, then the resulting output is the impulse response. This impulse response completely characterizes a relaxed LTI system for the convolution operation.

## Why the impulse response is sufficient for an LTI system

The book builds the convolution relation from three ideas:

1. **Impulse decomposition:** any arbitrary sequence can be written as a weighted sum of shifted impulses.
2. **Linearity:** the response to a weighted sum is the same weighted sum of the individual responses.
3. **Shift invariance:** the response to `δ(n-k)` is a shifted copy of the response to `δ(n)`.

Thus the complete response can be reconstructed from `h(n)`. This is the central theoretical reason convolution is used to analyse LTI systems.

### Physical interpretation of the convolution sum

For each integer `k`, the sample `x(k)` scales a shifted impulse response `h(n-k)`. The output at time `n` is the sum of all those weighted shifted responses:

$$
\boxed{y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k)}
$$

So convolution can be remembered as:

> **decompose → weight → shift → add**

This interpretation is especially useful when drawing the graphical/flip-shift-multiply-sum method.

## Important frequency-domain interpretation

The chapter emphasizes that convolution in the time domain corresponds to multiplication in the frequency domain. This is one reason convolution is fundamental in digital filtering.

# 2. Impulse response and convolution sum

## 2.1 — Impulse response

For an LTI system, if the input is a unit impulse,

$$
x(n)=\delta(n),
$$

the output is called the **impulse response**:

$$
\boxed{h(n)=T[\delta(n)]}
$$

The key idea is that **any sequence can be decomposed into weighted shifted impulses**:

$$
\boxed{ x(n)=\sum_{k=-\infty}^{\infty}x(k)\delta(n-k) }
$$

For a linear system,

$$
\begin{aligned} y(n) &=T[x(n)]\\ &=\sum_{k=-\infty}^{\infty}x(k)\,T[\delta(n-k)]. \end{aligned}
$$

For a shift-invariant system,

$$
T[\delta(n-k)]=h(n-k).
$$

**Therefore,**
$$
\boxed{ y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k) }
$$

This is the **convolution sum**.

Equivalently,

$$
\boxed{ y(n)=x(n)\ast h(n)=h(n)\ast x(n) }
$$

and also

$$
\boxed{ y(n)=\sum_{k=-\infty}^{\infty}h(k)x(n-k) }
$$

### Why this matters

The convolution sum gives the response of a **relaxed LTI system** when the input $x(n)$ and impulse response $h(n)$ are known. The book emphasizes that convolution is the mathematical operation behind FIR filtering and that time-domain convolution corresponds to multiplication in the frequency domain. 

---


## Theory: how to choose the convolution limits

The general convolution sum is always the starting point. The only reason the limits become finite is that one or both sequences are zero outside specified index ranges. For analytical problems, write the support conditions first and derive the allowed range of `k`; do not guess the limits. The book explicitly recommends using the step functions to determine where `x(k)` and `h(n-k)` are nonzero.

### Support-overlap rule

At a fixed output index `n`, a term contributes only when **both** factors are nonzero:

$$
x(k)\ne0 \quad\text{and}\quad h(n-k)\ne0.
$$

Therefore, an analytical convolution is fundamentally an **overlap problem in the summation index `k`**. This is why the limits change when a problem switches from causal to non-causal signals.

### Four cases to memorize

| System | Input | Useful convolution limits |
|---|---|---|
| Non-causal | Non-causal | `k=-∞` to `∞` |
| Non-causal | Causal | `k=0` to `∞` |
| Causal | Non-causal | `k=-∞` to `n` |
| Causal | Causal | `k=0` to `n` |

These are the exact four cases summarized again in the book's short-answer section.

# 3. Limits of the convolution sum

The general expression is

$$
y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k).
$$

The limits can be reduced by using the support of $x(k)$ and $h(n-k)$.

## 3.1 — Both input and system are causal

For a causal input,

$$
x(k)=0,\qquad k<0
$$

and for a causal system,

$$
h(n-k)=0,\qquad n-k<0.
$$

The second condition gives

$$
k\le n.
$$

Thus

$$
\boxed{ y(n)=\sum_{k=0}^{n}x(k)h(n-k) }
$$

for a causal input and causal system.

## 3.2 — Non-causal system + causal input

Only the causal input limits the lower bound:

$$
\boxed{ y(n)=\sum_{k=0}^{\infty}x(k)h(n-k) }
$$

## 3.3 — Causal system + non-causal input

Only the causal system gives the upper limit:

$$
\boxed{ y(n)=\sum_{k=-\infty}^{n}x(k)h(n-k) }
$$

## 3.4 — Both non-causal

No finite support restriction is available in general:

$$
\boxed{ y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k) }
$$

---


## Theory: what the properties mean

The algebraic properties are not merely formulas to memorize; they explain why block diagrams of LTI systems can be rearranged. In particular, commutativity allows the two convolved sequences to be interchanged, associativity permits cascaded systems to be grouped in either order, and distributivity allows a common input to be split across parallel branches and recombined.

### Useful interpretation of the shifting property

If the two input sequences are shifted before convolution, the output is shifted by the **sum** of their shifts. Hence when indexing finite sequences, the starting indices and ending indices add. This becomes a powerful consistency check later in the chapter.

# 4. Properties of discrete convolution

## 4.1 — Commutative property

$$
\boxed{x(n)\ast h(n)=h(n)\ast x(n)}
$$

## 4.2 — Associative property

$$
\boxed{ [x(n)\ast h_1(n)]\ast h_2(n) = x(n)\ast [h_1(n)\ast h_2(n)] }
$$

## 4.3 — Distributive property

$$
\boxed{ x(n)\ast [h_1(n)+h_2(n)] = x(n)\ast h_1(n)+x(n)\ast h_2(n) }
$$

## 4.4 — Shifting property

If

$$
x(n)\ast h(n)=y(n),
$$

then

$$
\boxed{ x(n-k)\ast h(n-m)=y(n-k-m) }
$$

## 4.5 — Convolution with an impulse

$$
\boxed{ x(n)\ast \delta(n)=x(n) }
$$

More generally,

$$
\boxed{ x(n)\ast \delta(n-k)=x(n-k) }
$$

---

# 5. Example 2.1 — Convolution of two causal exponentials

### Given

$$
h(n)=a^n u(n), \qquad x(n)=b^n u(n)
$$

Find $y(n)=x(n)\ast h(n)$.

## Step 1 — Use the causal convolution limit

Both sequences are causal, so

$$
y(n)=\sum_{k=0}^{n}x(k)h(n-k).
$$

## Step 2 — Substitute

$$
y(n) = \sum_{k=0}^{n} b^k a^{n-k}.
$$

Take $a^n$ outside:

$$
y(n) = a^n \sum_{k=0}^{n} \left(\frac{b}{a}\right)^k.
$$

## Step 3 — Apply the geometric-series formula

For $a\ne b$,

$$
\sum_{k=0}^{n}r^k = \frac{1-r^{n+1}}{1-r}.
$$

Hence,

$$
y(n) = a^n \frac{1-(b/a)^{n+1}} {1-b/a}.
$$

After simplification,

$$
\boxed{ y(n)= \frac{a^{n+1}-b^{n+1}}{a-b}\,u(n), \qquad a\ne b }
$$

### Special case: $a=b$

Now each term is

$$
a^k a^{n-k}=a^n.
$$

There are $n+1$ terms, so

$$
y(n)=a^n(n+1).
$$

**Therefore,**
$$
\boxed{ y(n)=(n+1)a^n u(n) }
$$

---

# 6. Example 2.2 — Finite-duration input with exponential impulse response

### Given

$$
x(n)=n+3,\qquad 0\le n\le2
$$

and

$$
h(n)=a^n u(n).
$$

## Step 1 — Determine the nonzero samples of $x(n)$

$$
x(0)=3,\qquad x(1)=4,\qquad x(2)=5.
$$

Thus

$$
x(n)=\{3,4,5\}.
$$

## Step 2 — Write the convolution sum

Since $x(k)=0$ outside $0\le k\le2$,

$$
y(n)=\sum_{k=0}^{2}x(k)h(n-k).
$$

## Step 3 — Substitute

$$
y(n) = 3h(n)+4h(n-1)+5h(n-2).
$$

Using $h(n)=a^nu(n)$,

$$
\boxed{ y(n)= 3a^n u(n) + 4a^{n-1}u(n-1) + 5a^{n-2}u(n-2) }
$$

The reason for the shifted steps is that the finite input contains three impulses at $n=0,1,2$, and each impulse generates a shifted copy of $h(n)$.

---

# 7. Example 2.3 — $3^n u(n)$ with $(1/3)^n u(n)$

### Given

$$
x(n)=3^n u(n), \qquad h(n)=\left(\frac13\right)^n u(n).
$$

## Step 1 — Causal convolution

$$
\begin{aligned} y(n) &=\sum_{k=0}^{n}3^k\left(\frac13\right)^{n-k}\\ &=3^{-n}\sum_{k=0}^{n}9^k. \end{aligned}
$$

## Step 2 — Simplify the product

$$
3^k \left(\frac13\right)^{n-k} = 3^k3^{-n+k} = 3^{2k-n}.
$$

**Therefore,**
$$
y(n) = 3^{-n} \sum_{k=0}^{n}9^k.
$$

## Step 3 — Use geometric series

$$
\sum_{k=0}^{n}9^k = \frac{9^{n+1}-1}{9-1} = \frac{9^{n+1}-1}{8}.
$$

Thus,

$$
\boxed{ y(n)= \frac{3^{-n}(9^{n+1}-1)}{8}u(n) }
$$

Equivalent forms may be obtained by distributing $3^{-n}$.

---

# 8. Example 2.4 — $3^n u(n)$ with $2^n u(n)$

### Given

$$
x(n)=3^n u(n), \qquad h(n)=2^n u(n).
$$

Because both sequences are causal,

$$
y(n) = \sum_{k=0}^{n} 3^k2^{n-k}.
$$

Factor out $2^n$:

$$
y(n) = 2^n \sum_{k=0}^{n} \left(\frac32\right)^k.
$$

Using the geometric-series formula,

$$
y(n) = 2^n \frac{(3/2)^{n+1}-1}{(3/2)-1}.
$$

Since

$$
(3/2)-1=\frac12,
$$

we get

$$
y(n) = 2^{n+1} \left[ \left(\frac32\right)^{n+1}-1 \right].
$$

This simplifies to

$$
\boxed{ y(n)=2(3^{n+1}-2^{n+1})u(n) }
$$

---

# 9. Example 2.5 — Cosine sequence and exponential impulse response

### Given

$$
x(n)=\cos\left(\frac n2\right)u(n), \qquad h(n)=\left(\frac12\right)^n u(n).
$$

Write cosine using exponentials:

$$
\cos\left(\frac k2\right) = \operatorname{Re}\left\{e^{jk/2}\right\}.
$$

Therefore the convolution can be evaluated through a geometric complex-exponential sum:

$$
y(n) = \sum_{k=0}^{n} \cos\left(\frac k2\right) \left(\frac12\right)^{n-k}.
$$

The textbook develops this by replacing the cosine with the real part of the complex exponential and then applying the finite geometric-series formula. The final answer can equivalently be written in a real trigonometric form after taking the real part.

**Exam point:** whenever a sinusoid is convolved with a geometric sequence, converting

$$
\cos(\omega k)
$$

to

$$
\operatorname{Re}\{e^{j\omega k}\}
$$

turns the convolution into a geometric series.

---

# 10. Example 2.6 — $u(n)\ast u(n-3)$

### Given

$$
x(n)=u(n),\qquad h(n)=u(n-3).
$$

## Step 1 — Write the convolution

$$
y(n) = \sum_{k=-\infty}^{\infty} u(k)u(n-k-3).
$$

## Step 2 — Find the overlap

For $u(k)$,

$$
k\ge0.
$$

For $u(n-k-3)$,

$$
n-k-3\ge0 \quad\Rightarrow\quad k\le n-3.
$$

Therefore, when $n\ge3$,

$$
0\le k\le n-3.
$$

## Step 3 — Count the nonzero terms

Every product is 1, and the number of terms is

$$
(n-3)-0+1=n-2.
$$

Thus

$$
\boxed{ y(n)=(n-2)u(n-3) }
$$

---

# 11. Example 2.7 — Finite rectangular input through exponential system

### Given

$$
h(n)=a^n u(n),
$$

and

$$
x(n)=u(n)-u(n-N).
$$

The input is 1 for

$$
0\le n\le N-1.
$$

Hence

$$
y(n) = \sum_{k=0}^{N-1} a^{n-k}u(n-k).
$$

## Case 1 — $0\le n<N$

The upper limit becomes $k=n$:

$$
y(n)=\sum_{k=0}^{n}a^{n-k}.
$$

Set $r=n-k$. Then $r=0,1,\ldots,n$, so

$$
y(n)=\sum_{r=0}^{n}a^r.
$$

**Therefore,**
$$
\boxed{ y(n)=\frac{1-a^{n+1}}{1-a}, \qquad 0\le n<N,\ a\ne1 }
$$

## Case 2 — $n\ge N$

All $N$ input samples overlap the impulse response:

$$
y(n) = a^n+a^{n-1}+\cdots+a^{n-N+1}.
$$

Factor $a^{n-N+1}$:

$$
y(n) = a^{n-N+1} (1+a+\cdots+a^{N-1}).
$$

Thus,

$$
\boxed{ y(n)= a^{n-N+1} \frac{1-a^N}{1-a}, \qquad n\ge N }
$$

This is a classic **piecewise convolution** problem.

---

# 12. Example 2.8 — $u(n)$ convolved with a delayed finite geometric sequence

### Given

$$
x(n)=u(n),
$$

$$
h(n)=\left(\frac12\right)^n \left[u(n)-u(n-10)\right].
$$

Thus $h(n)$ is nonzero only for

$$
0\le n\le9.
$$

For $n\le9$,

$$
y(n) = \sum_{k=0}^{n} \left(\frac12\right)^{n-k}.
$$

Let $r=n-k$. Then

$$
y(n)=\sum_{r=0}^{n}\left(\frac12\right)^r.
$$

Hence

$$
\boxed{ y(n)=2\left[1-\left(\frac12\right)^{n+1}\right], \qquad 0\le n\le9 }
$$

For $n\ge10$,

$$
y(n) = \sum_{k=0}^{9} \left(\frac12\right)^{n-k}.
$$

This gives

$$
\boxed{ y(n)=2^{-n}\sum_{k=0}^{9}2^k = 2^{-n}(2^{10}-1) }
$$

The important exam skill is determining the overlap interval before summing.

---

# 13. Example 2.9 — Two-sided/non-causal convolution

### Given

$$
x(n)=\left(\frac13\right)^{-n}u(-n-1), \qquad h(n)=u(n).
$$

The first sequence is nonzero for negative indices, while $u(n)$ is nonzero for nonnegative indices.

The convolution is

$$
y(n)= \sum_{k=-\infty}^{\infty} x(k)h(n-k).
$$

Because

$$
x(k)\ne0 \quad\text{for }k\le-1
$$

and

$$
h(n-k)\ne0 \quad\text{when}\quad k\le n,
$$

the summation limits depend on whether $n<0$ or $n>0$.

The result is therefore naturally obtained in two cases. The textbook uses the overlap of the two shifted sequences to reduce the infinite sum to a geometric series.

**Exam lesson:** non-causal convolutions often require **piecewise summation limits**.

---


## Theory: step response

The **step response** is the response of an LTI system when the input is the unit-step sequence `u(n)`. If `h(n)` is the impulse response, then

$$
\begin{aligned} s(n) &=h(n)\ast u(n)\\ &=\sum_{k=-\infty}^{\infty}h(k)u(n-k). \end{aligned}
$$

Because `u(n-k)=1` for `k\le n` and zero for `k>n`,

$$
\boxed{s(n)=\sum_{k=-\infty}^{n}h(k)}
$$

For a causal system, this reduces to

$$
\boxed{s(n)=\sum_{k=0}^{n}h(k)}
$$

Hence the step response is the **running sum (cumulative sum)** of the impulse response.

### Exam shortcut

If the question says “find the step response from `h(n)`,” do **not** start a new system analysis. Directly use:

$$
s(n)=h(n)\ast u(n).
$$

For a causal `h(n)`, simply accumulate the samples from the beginning up to the current index.

# 14. Step response

The response of an LTI system to

$$
x(n)=u(n)
$$

is called the **step response** $s(n)$.

Since

$$
s(n)=h(n)\ast u(n),
$$

we have

$$
s(n) = \sum_{k=-\infty}^{\infty}h(k)u(n-k).
$$

Because

$$
u(n-k)=1 \quad\text{for }k\le n,
$$

we obtain

$$
\boxed{ s(n)=\sum_{k=-\infty}^{n}h(k) }
$$

For a causal system,

$$
\boxed{ s(n)=\sum_{k=0}^{n}h(k) }
$$

So the step response is the **running sum of the impulse response**.

---

# 15. Example 2.10 — Step response

## (a)

### Given

$$
h(n)=\delta(n)-\delta(n-2).
$$

Then

$$
s(n) = h(n)\ast u(n).
$$

Using

$$
\delta(n)\ast u(n)=u(n)
$$

and

$$
\delta(n-2)\ast u(n)=u(n-2),
$$

we get

$$
\boxed{ s(n)=u(n)-u(n-2) }
$$

This is a rectangular sequence that is 1 at $n=0,1$.

---

## (b)

### Given

$$
h(n)=\left(\frac14\right)^n u(n).
$$

For a causal system,

$$
s(n)=\sum_{k=0}^{n}\left(\frac14\right)^k.
$$

Using the finite geometric-series formula,

$$
s(n) = \frac{1-(1/4)^{n+1}}{1-1/4}.
$$

**Therefore,**
$$
\boxed{ s(n)= \frac43 \left[1-\left(\frac14\right)^{n+1}\right]u(n) }
$$

---

## (c)

### Given

$$
h(n)=nu(n).
$$

Then

$$
s(n) = \sum_{k=0}^{n}k.
$$

We know

$$
\sum_{k=0}^{n}k = \frac{n(n+1)}2.
$$

Thus,

$$
\boxed{ s(n)=\frac{n(n+1)}2u(n) }
$$

---

## (d)

### Given

$$
h(n)=u(n).
$$

Then

$$
s(n) = \sum_{k=0}^{n}1 = n+1.
$$

**Therefore,**
$$
\boxed{ s(n)=(n+1)u(n) }
$$

---


## Theory: convolution of finite sequences

When two finite-duration sequences are convolved, the output is also finite-duration. Three indexing rules are especially important and are repeatedly used in the book:

$$
\boxed{n_{y,\text{start}}=n_{x,\text{start}}+n_{h,\text{start}}}
$$

$$
\boxed{n_{y,\text{end}}=n_{x,\text{end}}+n_{h,\text{end}}}
$$

$$
\boxed{L_y=L_x+L_h-1}
$$

These rules are explicitly stated by the book as consistency checks.

### Sum-of-samples check

A second useful check is

$$
\boxed{\left(\sum_n x(n)\right)\left(\sum_n h(n)\right)=\sum_n y(n)}
$$

The book demonstrates this check after finite-sequence convolution examples.

### Zero insertion versus zero padding

The two operations must not be confused:

**Zero insertion:** zeros are inserted **between adjacent samples**. The book notes that the convolution then contains the corresponding zeros between adjacent output samples.

**Zero padding:** zeros are appended at the beginning or end. Leading or trailing zeros appear correspondingly in the convolution result.

# 16. Finite-sequence convolution: checks you must know

If

- $x(n)$ has length $L_x$,
- $h(n)$ has length $L_h$,

then

$$
\boxed{ L_y=L_x+L_h-1 }
$$

If $x(n)$ starts at $n=n_1$ and $h(n)$ starts at $n=n_2$, then

$$
\boxed{ n_{\text{start},y}=n_1+n_2 }
$$

Likewise, if the ending indices are $n_3$ and $n_4$,

$$
\boxed{ n_{\text{end},y}=n_3+n_4 }
$$

These are excellent consistency checks.

---


## Theory: why the book gives six linear-convolution methods

All six methods evaluate the same convolution sum; they differ only in how the arithmetic is organized and visualized. The chapter lists:

1. Graphical method
2. Tabular-array method
3. Tabular method
4. Matrix method
5. Sum-by-column method
6. Flip–shift–multiply–and–sum method

The book explicitly lists all six methods in its short-answer section.

### Which idea is common to all methods?

At the output index `n`, every method is evaluating

$$
y(n)=\sum_k x(k)h(n-k).
$$

The graphical and sliding methods make **overlap** visually obvious; tabular and matrix methods organize the same products into an arithmetic structure; the sum-by-column method emphasizes shifted impulse responses.

# 17. Six methods of linear convolution

The chapter presents six practical methods:

1. Graphical / flip-shift-multiply-sum method
2. Tabular array method
3. Tabular method
4. Matrix method
5. Sum-by-column method
6. Flip, shift, multiply and sum method

---

## Method 1 — Linear convolution by graphical / flip–shift–multiply–sum method

The graphical method is a direct visual interpretation of the convolution sum. For each output index `n`, one sequence is time-reversed and shifted; the samples that overlap are multiplied, and the products are added. The chapter's practical procedure is: choose the starting index, express both sequences with the same dummy index `k`, fold one sequence, shift it to form `h(n-k)`, multiply the overlapping samples, sum them, and repeat for the next value of `n`.

### Method 2 — Tabular-array method

The tabular-array method places `x(k)` and successive shifted versions of `h(k)` in rows. Each row represents one value of the shifted sequence. Adding the products column-by-column produces the output samples. The chapter illustrates this procedure for `x_3(q)=\sum_k x_1(k)x_2(q-k)`.

### Method 3 — Tabular method

In the ordinary tabular method, the samples are organized so that each input sample multiplies the entire impulse-response row. Diagonal groupings correspond to equal output-index values. The elements on each diagonal are added to form a sample of `y(n)`. This is essentially a structured way of collecting all products having the same `n=k+(n-k)`.

### Method 4 — Matrix method

If `x(n)` contains `N_1` samples and `h(n)` contains `N_2` samples, the chapter forms a convolution matrix with `N_1+N_2-1` rows and `N_1` columns, multiplies it by the `N_1\times1` sample vector of `x(n)`, and obtains the `N_1+N_2-1` output samples.

Conceptually, each column of the matrix is a shifted copy of `h(n)`, so matrix multiplication performs the same sum of shifted products as the convolution sum.

### Method 5 — Sum-by-column method

This method interprets `x(n)` as a collection of weighted shifted impulses. Every sample `x(k)` generates a shifted and scaled copy of the impulse response `h(n-k)`. The shifted copies are placed under the appropriate time indices and then **summed by columns** to form `y(n)`. Importantly, the sequences are not flipped in this presentation.

### Method 6 — Sliding-strip method

The sliding-strip method is another visual form of the same operation. Flip one sequence, place its last element against the appropriate first element of the other sequence, and slide it one sample at a time. At every position, calculate the sum of the pointwise products in the overlap region. The book describes this explicitly as sliding the flipped sequence past the stationary sequence.

### Practical exam choice

For a hand calculation, use the method that makes the indexing least error-prone:

- use **graphical/sliding** when the question emphasizes shifting or overlap;
- use **tabular/sum-by-column** when the sequences are short and integer-valued;
- use **matrix** when a compact systematic calculation is required;
- use **polynomial multiplication** when the coefficient lists are especially convenient.

All of them must give the same `y(n)`.

# 18. Graphical / flip-shift-multiply-sum method

The central four operations are:

$$
\boxed{\text{Flip} \rightarrow \text{Shift} \rightarrow \text{Multiply} \rightarrow \text{Sum}}
$$

Given

$$
y(n)=x(n)\ast h(n) = \sum_k x(k)h(n-k),
$$

perform:

### Step 1

Rewrite both sequences using index $k$.

### Step 2

Flip one sequence:

$$
h(k)\rightarrow h(-k).
$$

### Step 3

Shift it by $n$:

$$
h(-k)\rightarrow h(n-k).
$$

### Step 4

Multiply point-by-point:

$$
x(k)h(n-k).
$$

### Step 5

Add all overlapping products:

$$
y(n)=\sum_k x(k)h(n-k).
$$

### Step 6

Move one sample and repeat.

---

# 19. Example 2.11 — Linear convolution by direct sample calculation

### Given

$$
x(n)=\{4,2,1,3\},
$$

$$
h(n)=\{1,2,2,1\},
$$

with $x(n)$ starting at $n=0$ and $h(n)$ starting at $n=-1$.

## Step 1 — Find the starting index

$$
n_{\text{start}} = 0+(-1) = -1.
$$

## Step 2 — Find the length

$$
L_y=4+4-1=7.
$$

Thus $y(n)$ runs from

$$
n=-1\text{ to }5.
$$

## Step 3 — Calculate each sample

### At $n=-1$

Only one sample overlaps:

$$
y(-1)=4(1)=4.
$$

### At $n=0$

$$
y(0)=4(2)+2(1)=10.
$$

### At $n=1$

$$
y(1)=4(2)+2(2)+1(1)=13.
$$

### At $n=2$

$$
y(2)=4(1)+2(2)+1(2)+3(1) =13.
$$

### At $n=3$

$$
y(3)=2(1)+1(2)+3(2)=10.
$$

### At $n=4$

$$
y(4)=1(1)+3(2)=7.
$$

### At $n=5$

$$
y(5)=3(1)=3.
$$

**Therefore,**
$$
\boxed{ y(n)=\{4,10,13,13,10,7,3\} }
$$

with the first element at $n=-1$.

## Check

For finite convolution,

$$
\left(\sum_nx(n)\right) \left(\sum_nh(n)\right) = \sum_ny(n).
$$

Here,

$$
\sum x(n)=10, \qquad \sum h(n)=6,
$$

so

$$
10\times6=60.
$$

Also,

$$
4+10+13+13+10+7+3=60.
$$

Therefore the result is consistent.

---

# 20. Example 2.12 — Convolution with negative samples

### Given

$$
x(n)=\{2,3,2,-1\},
$$

starting at $n=-2$, and

$$
h(n)=\{1,-2,3,1\},
$$

starting at $n=0$.

The output starts at

$$
n=-2+0=-2
$$

and has

$$
4+4-1=7
$$

samples.

Compute:

$$
y(-2)=2(1)=2
$$

$$
y(-1)=2(-2)+3(1)=-1
$$

$$
y(0)=2(3)+3(-2)+2(1)=2
$$

$$
y(1)=2(-1)+3(3)+2(-2)+(-1)(1)=2
$$

> **Important:** when reading a scanned table, do not memorize the apparent compressed line. Always reconstruct the overlapping samples from
> $$
> y(n)=\sum_kx(k)h(n-k).
> $$
> The printed worked example gives its corresponding sequence and checks the sum-of-samples identity.

---

# 21. Example 2.13 — Sum-by-column method

For

$$
x(n)=\{3,2,1,4\}, \qquad h(n)=\{2,5,3\},
$$

the lengths are 4 and 3.

**Therefore,**
$$
L_y=4+3-1=6.
$$

The output is formed by collecting the diagonal/same-index products:

$$
\begin{aligned} y(0)&=3(2)=6,\\ y(1)&=3(5)+2(2)=19,\\ y(2)&=3(3)+2(5)+1(2),\\ y(3)&=2(3)+1(5)+4(2),\\ y(4)&=1(3)+4(5),\\ y(5)&=4(3). \end{aligned}
$$

The **method**, rather than the particular numerical result, is the main exam point: multiply shifted copies and sum each diagonal group.

---

# 22. Example 2.14 — Infinite sequences

### Given

$$
u(n)\ast u(n-2)
$$

Because

$$
u(n)=1,\quad n\ge0
$$

and

$$
u(n-2)=1,\quad n\ge2,
$$

the first output sample occurs at $n=2$.

At $n=2$, there is one overlapping product:

$$
y(2)=1.
$$

At $n=3$,

$$
y(3)=1+1=2.
$$

At $n=4$,

$$
y(4)=1+1+1=3.
$$

**Therefore,**
$$
\boxed{ y(n)=(n-1)u(n-2) }
$$

---

# 23. Example 2.15 — Two useful analytical cases

## (a)

For

$$
x(n)=\sin\left(\frac{\pi n}{2}\right)u(n), \qquad h(n)=u(n-2),
$$

the overlap starts at $n=2$. The first samples are

$$
y(0)=0,\qquad y(1)=0,\qquad y(2)=1,\qquad y(3)=2,\qquad y(4)=3.
$$

The tabular pattern gives

$$
\boxed{ y(n)=\{0,0,1,2,3,\ldots\} }
$$

with the exact closed form obtained from the finite trigonometric/geometric sum.

## (b)

For

$$
x(n)=3^n u(-n+3), \qquad h(n)=u(n-2),
$$

the overlap limits change with $n$, so the convolution must be evaluated piecewise.

The textbook separates the range

$$
-\infty<n\le5
$$

from

$$
n>5
$$

and uses a finite geometric series in each region.

**Exam lesson:** when one sequence is left-sided and the other right-sided, always determine the overlap before attempting the sum.

---

# 24. Example 2.16 — Sum-by-column / paper-and-pencil method

### (a)

Given

$$
h(n)=\{2,1,2,1\}, \qquad x(n)=\{1,-2,4\}.
$$

Represent the input as

$$
x(n)= \delta(n) -2\delta(n-1) +4\delta(n-2).
$$

Therefore the total response is

$$
y(n) = h(n)-2h(n-1)+4h(n-2).
$$

Writing the shifted rows and adding column-by-column gives

$$
\boxed{ y(n)=\{2,-3,8,1,6,4\} }
$$

starting at $n=0$.

### (b)

For

$$
h(n)=\{3,7,0,5\}, \qquad x(n)=\{2,3,4\},
$$

the sum-by-column table produces

$$
\boxed{ y(n)=\{6,23,33,38,15,20\} }
$$

---

# 25. Example 2.17 — Sliding-strip method

Given

$$
h(n)=\{3,7,0,5\}, \qquad x(n)=\{2,3,4\}.
$$

Flip

$$
x(k)=\{2,3,4\}
$$

to

$$
x(-k)=\{4,3,2\}.
$$

Now slide the flipped sequence across $h(n)$.

$$
y(0)=3(2)=6
$$

$$
y(1)=3(3)+7(2)=9+14=23
$$

$$
y(2)=3(4)+7(3)=12+21=33
$$

$$
y(3)=7(4)+5(2)=28+10=38
$$

$$
y(4)=5(3)=15
$$

$$
y(5)=5(4)=20.
$$

Hence,

$$
\boxed{ y(n)=\{6,23,33,38,15,20\} }
$$

---


## Theory: convolution as polynomial multiplication

For finite sequences, the chapter gives a direct polynomial interpretation:

> The convolution sequence is the coefficient sequence of the product polynomial.

For example, if the sequence coefficients are represented in ascending powers of the polynomial variable,

$$
X(z)=x_0+x_1z+x_2z^2+\cdots,\qquad H(z)=h_0+h_1z+h_2z^2+\cdots,
$$

then

$$
\boxed{Y(z)=X(z)H(z)}
$$

and the coefficients of `Y(z)` give the convolution result. The book also uses this interpretation to explain zero insertion and zero padding.

### Why this is useful in exams

Polynomial multiplication can be much faster than writing every shift when the sequences are short. It is also the conceptual bridge to deconvolution by polynomial division and to Z-domain deconvolution.

# 26. Polynomial interpretation of convolution

For finite sequences, convolution is equivalent to polynomial multiplication.

If

$$
x(n)=\{x_0,x_1,x_2,\ldots\}
$$

associate

$$
X(z)=x_0+x_1z^{-1}+x_2z^{-2}+\cdots
$$

and similarly for $h(n)$.

Then the coefficients of

$$
Y(z)=X(z)H(z)
$$

are the convolution samples.

This gives a very useful algebraic shortcut.

---

# 27. Example 2.18 — Polynomial method and zero insertion

### Given

$$
h(n)=\{3,7,0,5\}, \qquad x(n)=\{2,3,4\}.
$$

The polynomials are

$$
H(z)=3+7z^{-1}+0z^{-2}+5z^{-3},
$$

$$
X(z)=2+3z^{-1}+4z^{-2}.
$$

Multiply:

$$
Y(z)=H(z)X(z).
$$

Collecting coefficients gives

$$
\boxed{ y(n)=\{6,23,33,38,15,20\} }
$$

## Zero insertion

Insert one zero between adjacent samples:

$$
h_1(n)=\{3,0,7,0,0,0,5\},
$$

$$
x_1(n)=\{2,0,3,0,4\}.
$$

The convolution becomes the original convolution with one zero inserted between adjacent output samples:

$$
\boxed{ \{6,0,23,0,33,0,38,0,15,0,20\} }
$$

## Zero padding

Appending zeros adds corresponding trailing zeros to the convolution.

**Remember:**

- zero **insertion** between samples → zeros appear between convolution samples;
- zero **padding** at the end → corresponding trailing zeros appear in the convolution;
- leading zeros shift the location of the resulting sequence.

---

# 28. Example 2.19

$$
\{1,2,1\}\ast \{2,0,1\}.
$$

Polynomial form:

$$
(1+2z^{-1}+z^{-2})(2+z^{-2})
$$

$$
=2+4z^{-1}+3z^{-2}+2z^{-3}+z^{-4}.
$$

**Therefore,**
$$
\boxed{ y(n)=\{2,4,3,2,1\} }
$$

---

# 29. Example 2.20 — Zero insertion shortcut

Given

$$
\{1,0,2,0,1\}\ast \{2,0,0,0,1\}.
$$

These are the sequences of Example 2.19 with zeros inserted between adjacent samples.

Hence insert zeros in the convolution:

$$
\boxed{ \{2,0,4,0,3,0,2,0,1\} }
$$

---

# 30. Example 2.21 — Zero padding shortcut

Padding the corresponding sequences causes matching padding in the convolution.

The textbook result is

$$
\boxed{ \{0,0,2,4,3,2,1,0,0,0\} }
$$

for the stated padded sequences.

---


## Theory: meaning and purpose of deconvolution

Deconvolution is the inverse problem of convolution. If

$$
y(n)=x(n)\ast h(n),
$$

and two of the three sequences are known, deconvolution seeks the third sequence. The book describes both cases: recovering the input from known `y(n)` and `h(n)`, or recovering the impulse response from known `y(n)` and `x(n)`.

### Z-domain viewpoint

The convolution relationship becomes multiplication in the Z-domain:

$$
Y(z)=X(z)H(z).
$$

Therefore,

$$
\boxed{X(z)=\frac{Y(z)}{H(z)}} \qquad\text{or}\qquad \boxed{H(z)=\frac{Y(z)}{X(z)}}.
$$

After the division, take the inverse Z-transform to recover the desired sequence.

### Three methods named by the book

- Polynomial division (Z-domain interpretation)
- Recursion
- Tabular method

The short-answer section lists these three methods explicitly.

### Recursive idea

For one-sided sequences,

$$
y(n)=\sum_{k=0}^{n}x(k)h(n-k).
$$

The first few equations are

$$
\begin{aligned} y(0)&=x(0)h(0),\\ y(1)&=x(0)h(1)+x(1)h(0),\\ y(2)&=x(0)h(2)+x(1)h(1)+x(2)h(0). \end{aligned}
$$

These equations can be solved successively for unknown samples. The book notes that this approach becomes sensitive to noise and round-off error when a remainder is involved.

# 31. Deconvolution

## Definition

If

$$
y(n)=x(n)\ast h(n),
$$

then **deconvolution** means finding $x(n)$ from $y(n)$ and $h(n)$, or finding $h(n)$ from $y(n)$ and $x(n)$.

In the transform domain,

$$
Y(z)=X(z)H(z).
$$

**Therefore,**
$$
\boxed{ X(z)=\frac{Y(z)}{H(z)} }
$$

or

$$
\boxed{ H(z)=\frac{Y(z)}{X(z)} }
$$

The chapter gives three practical deconvolution approaches:

1. Polynomial/division method
2. Recursion method
3. Tabular method

---

# 32. Example 2.22 — Deconvolution using $z$-domain division

### Given

$$
h(n)=\{2,1,0,-1,3\}
$$

and

$$
y(n)=\{2,-5,1,1,-6,11,6\}.
$$

Write

$$
H(z) = 2+z^{-1}-z^{-3}+3z^{-4},
$$

$$
Y(z) = 2-5z^{-1}+z^{-2}+z^{-3} -6z^{-4}+11z^{-5}+6z^{-6}.
$$

Then

$$
X(z)=\frac{Y(z)}{H(z)}.
$$

Polynomial long division yields

$$
X(z)=1-3z^{-1}+2z^{-2}.
$$

Taking the inverse transform,

$$
\boxed{ x(n)=\{1,-3,2\} }
$$

---

# 33. Deconvolution by recursion

Assume both $x(n)$ and $h(n)$ are one-sided.

Then

$$
y(n)=\sum_{k=0}^{n}x(k)h(n-k).
$$

At $n=0$,

$$
y(0)=x(0)h(0),
$$

so

$$
\boxed{ x(0)=\frac{y(0)}{h(0)} }
$$

At $n=1$,

$$
y(1)=x(0)h(1)+x(1)h(0),
$$

hence

$$
\boxed{ x(1)= \frac{y(1)-x(0)h(1)} {h(0)} }
$$

At $n=2$,

$$
y(2)=x(0)h(2)+x(1)h(1)+x(2)h(0),
$$

so

$$
\boxed{ x(2)= \frac{y(2)-x(0)h(2)-x(1)h(1)} {h(0)} }
$$

The same pattern continues.

---

# 34. Example 2.23 — Recursive deconvolution

### Given

$$
y(n)=\{1,1,2,0,2,1\},
$$

$$
h(n)=\{1,-1,1\}.
$$

Since

$$
L_y=6,\qquad L_h=3,
$$

the required input length is

$$
L_x=6-3+1=4.
$$

Let

$$
x(n)=\{x(0),x(1),x(2),x(3)\}.
$$

### Step 1

$$
y(0)=x(0)h(0)
$$

$$
1=x(0)(1)
$$

so

$$
x(0)=1.
$$

### Step 2

$$
y(1)=x(0)h(1)+x(1)h(0)
$$

$$
1=1(-1)+x(1)
$$

so

$$
x(1)=2.
$$

### Step 3

$$
y(2)=x(0)h(2)+x(1)h(1)+x(2)h(0)
$$

$$
2=1(1)+2(-1)+x(2)
$$

so

$$
x(2)=3.
$$

### Step 4

$$
y(3)=x(0)h(3)+x(1)h(2)+x(2)h(1)+x(3)h(0)
$$

Since $h(3)=0$,

$$
0=0+2(1)+3(-1)+x(3).
$$

**Therefore,**
$$
x(3)=1.
$$

Hence,

$$
\boxed{ x(n)=\{1,2,3,1\} }
$$

---

# 35. Example 2.24 — Tabular deconvolution

Assume

$$
x(n)=\{a,b,c,d\},
$$

$$
h(n)=\{1,-1,1\}.
$$

Then

$$
y(n)= \{a,\ b-a,\ c-b+a,\ d-c+b,\ -d+c,\ d\}.
$$

Compare with

$$
y(n)=\{1,1,2,0,2,1\}.
$$

Thus,

$$
a=1,
$$

$$
b-a=1 \quad\Rightarrow\quad b=2,
$$

$$
c-b+a=2 \quad\Rightarrow\quad c=3,
$$

$$
d-c+b=0 \quad\Rightarrow\quad d=1.
$$

**Therefore,**
$$
\boxed{ x(n)=\{1,2,3,1\} }
$$

---

# 36. Example 2.25 — Find the impulse response

Given

$$
x(n)=\{1,2,3,1\},
$$

$$
y(n)=\{1,1,2,0,2,1\}.
$$

Let

$$
h(n)=\{a,b,c\}.
$$

The convolution is

$$
\begin{aligned} y(0)&=a,\\ y(1)&=2a+b,\\ y(2)&=3a+2b+c,\\ y(3)&=a+3b+2c,\\ y(4)&=b+3c,\\ y(5)&=c. \end{aligned}
$$

From the first three equations,

$$
a=1,
$$

$$
2(1)+b=1 \Rightarrow b=-1,
$$

$$
3(1)+2(-1)+c=2 \Rightarrow c=1.
$$

**Therefore,**
$$
\boxed{ h(n)=\{1,-1,1\} }
$$

---


## Theory: interconnection of LTI systems

The convolution properties directly determine the equivalent impulse response of interconnected LTI systems.

### Parallel connection

If the same input excites two systems and their outputs are added,

$$
y(n)=y_1(n)+y_2(n),
$$

with

$$
y_1=x\ast h_1,\qquad y_2=x\ast h_2,
$$

then

$$
begin{aligned} y(n)&=x\ast h_1+x\ast h_2\\&=x\ast (h_1+h_2). \end{aligned}
$$

Hence the equivalent impulse response is

$$
\boxed{h_{eq}(n)=h_1(n)+h_2(n)}.
$$

The book derives this directly from the distributive property.

### Cascade connection

For cascaded systems, the first system produces `y_1`, which becomes the input to the second. If their impulse responses are `h_1` and `h_2`,

$$
y=x\ast h_1\ast h_2.
$$

Therefore,

$$
\boxed{h_{eq}=h_1\ast h_2}.
$$

By associativity, the order of grouping does not affect the overall LTI impulse response.

### Exam rule for mixed block diagrams

**Parallel branches → add their impulse responses.**

**Cascade blocks → convolve their impulse responses.**

Then simplify the whole block diagram before doing numerical convolution.

# 37. Interconnection of LTI systems

## 37.1 — Parallel connection

For two LTI systems,

$$
y_1(n)=x(n)\ast h_1(n),
$$

$$
y_2(n)=x(n)\ast h_2(n).
$$

If the outputs are added,

$$
y(n)=y_1(n)+y_2(n),
$$

then

$$
y(n) = x(n)\ast h_1(n)+x(n)\ast h_2(n).
$$

Using distributive property,

$$
y(n)=x(n)\ast [h_1(n)+h_2(n)].
$$

Hence,

$$
\boxed{ h(n)=h_1(n)+h_2(n) }
$$

for a parallel connection.

---

# 38. Cascade connection

First system:

$$
y_1(n)=x(n)\ast h_1(n).
$$

Second system:

$$
y(n)=y_1(n)\ast h_2(n).
$$

**Therefore,**
$$
y(n) = [x(n)\ast h_1(n)]\ast h_2(n).
$$

By associativity,

$$
y(n) = x(n)\ast [h_1(n)\ast h_2(n)].
$$

Thus,

$$
\boxed{ h(n)=h_1(n)\ast h_2(n) }
$$

for a cascade connection.

---

# 39. Example 2.26 — Mixed parallel/cascade interconnection

Suppose $h_2(n)$ and $h_3(n)$ are in parallel and this block is in cascade with $h_1(n)$.

Then first combine the parallel section:

$$
h_p(n)=h_2(n)+h_3(n).
$$

Then cascade with $h_1(n)$:

$$
\boxed{ h(n)=h_1(n)\ast [h_2(n)+h_3(n)] }
$$

and by distributive property,

$$
\boxed{ h(n)=h_1(n)\ast h_2(n)+h_1(n)\ast h_3(n) }
$$

The textbook evaluates the two resulting convolutions separately and adds them.

---


## Theory: why circular shift appears in DFT-based problems

The DFT works with `N` samples, which are interpreted as one period of a periodic sequence. Because the samples repeat every `N`, shifting beyond the ends causes the samples to **wrap around**. This is circular shifting.

For an `N`-point sequence, a circular shift by `k` samples is expressed with modulo-`N` indexing:

$$
\boxed{x_c(n)=x((n-k)\bmod N)}
$$

For a right shift by `k`, move the last `k` samples to the beginning. For a left shift by `k`, move the first `k` samples to the end. A circular flip is obtained by reversing the periodic sequence with modulo-`N` indexing.

### Circular symmetry

When a finite `N`-point sequence is represented around a circle, even and odd symmetry are defined with respect to the circular origin. The book gives:

$$
\text{circular even: }x(n-N)=x(n),
$$

$$
\text{circular odd: }x(n-N)=-x(n),
$$

for the stated `N`-point indexing range.

# 40. Circular shift and circular symmetry

For an $N$-point sequence, circular shifting is based on modulo-$N$ indexing.

For a right shift of $k$,

$$
\boxed{ x'(n)=x[(n-k)\bmod N] }
$$

For a left shift of $k$,

$$
\boxed{ x'(n)=x[(n+k)\bmod N] }
$$

Circular flipping is

$$
\boxed{ x'(n)=x[(-n)\bmod N] }
$$

## Remember the physical rule

For an $N$-point sequence:

- $x(n-k)$: move the **last $k$ samples to the beginning**.
- $x(n+k)$: move the **first $k$ samples to the end**.
- $x(-n)$: circularly reverse the sequence around the zero index.

---

# 41. Example 2.27 — Circular shifts

### Given

$$
y(n)=\{2,3,4,5,6,0,0,7\}.
$$

## (a) $f(n)=y(n-2)$

A right shift by 2 moves the last two samples to the front:

$$
\boxed{ f(n)=\{0,7,2,3,4,5,6,0\} }
$$

## (b) $g(n)=y(n+2)$

A left shift by 2 moves the first two samples to the end:

$$
\boxed{ g(n)=\{4,5,6,0,0,7,2,3\} }
$$

## (c) $h(n)=y(-n)$

Circular reversal gives

$$
\boxed{ h(n)=\{2,7,0,0,6,5,4,3\} }
$$

---


## Theory: periodic (circular) convolution

For two sequences that are periodic with the same period `N`, ordinary linear convolution is not the appropriate finite operation because the signals extend indefinitely. The chapter therefore defines **periodic/circular convolution** over one period.

For `n=0,1,\ldots,N-1`, the circular convolution is

$$
\boxed{y_p(n)=\sum_{k=0}^{N-1}x(k)h((n-k)\bmod N)}
$$

Equivalently, the periodic indexing may be written in the form used throughout the chapter. An averaging factor `1/N` is sometimes included depending on the convention being used.

### Rules before starting a circular-convolution problem

1. Both operands must be represented with the same number `N` of samples.
2. If the lengths differ, append zeros to the shorter sequence.
3. The output has exactly `N` samples.
4. Folding and shifting are performed **circularly**, using modulo-`N` indexing.
5. Either sequence may be folded; the circular convolution result is unchanged.

These points are stated explicitly in the chapter's method discussion and short-answer section.

### Linear versus circular convolution

If the two original sequences have lengths `N_1` and `N_2`, then linear convolution has length

$$
N_1+N_2-1,
$$

whereas an `N`-point circular convolution always has length `N`. Therefore, using circular convolution directly on short sequences can cause **wrap-around (aliasing) of the linear-convolution tails**. Zero-padding to the required linear-convolution length prevents this wrap-around.

# 42. Periodic/circular convolution

For two periodic sequences of common period $N$, linear convolution is replaced by **periodic convolution**.

The circular convolution is

$$
\boxed{ y_p(n) = \sum_{k=0}^{N-1} x_p(k)h_p(n-k) }
$$

where the index is interpreted modulo $N$.

Equivalently,

$$
\boxed{ y_p(n) = \sum_{k=0}^{N-1} x_p(k) h_p[(n-k)\bmod N] }
$$

Some textbook conventions include an averaging factor $1/N$; follow the convention stated in the problem/text.

## Crucial differences from linear convolution

| Linear convolution | Circular convolution |
|---|---|
| No modulo-$N$ wrap-around | Modulo-$N$ wrap-around |
| Output length $L_x+L_h-1$ | Output length $N$ |
| Ordinary flipping/shifting | Circular flipping/shifting |
| Different sequence lengths are allowed | Inputs must be made equal length |

If the lengths are different, append zeros to the shorter sequence before circular convolution.

---

# 43. Circular convolution by graphical method

### Procedure

1. Place $N$ samples of the first sequence on an outer circle.
2. Place $N$ samples of the second sequence on an inner circle in reverse order.
3. Multiply corresponding samples.
4. Add the products to obtain $y(0)$.
5. Rotate the inner circle by one sample.
6. Repeat to obtain $y(1),y(2),\ldots,y(N-1)$.

---

# 44. Circular convolution by tabular method

Given $x_1(k)$ and $x_2(k)$:

1. Fold $x_2(k)$ to $x_2(-k)$.
2. Periodically extend the folded sequence.
3. Shift it by $q$ samples to obtain $x_2(q-k)$.
4. Multiply by $x_1(k)$.
5. Sum one period.

Thus,

$$
\boxed{ x_3(q)= \sum_{k=0}^{N-1} x_1(k)x_2[(q-k)\bmod N] }
$$

---

# 45. Circular convolution by matrix method

For $N$-point circular convolution, form an $N\times N$ circulant matrix from one sequence and multiply by the other sequence as an $N\times1$ column vector.

Schematically,

$$
\boxed{ \mathbf y=\mathbf H_{\rm circ}\mathbf x }
$$

The important feature is that each row is a circular shift of the previous row.

---

# 46. Example 2.28 — Circular convolution with unequal lengths

### Given

$$
x_1(n)=\{1,2,-1,-2,3,1\}
$$

and

$$
x_2(n)=\{3,2,1\}.
$$

The first sequence has length 6 and the second length 3.

## Step 1 — Equalize lengths

Append three zeros:

$$
x_2(n)=\{3,2,1,0,0,0\}.
$$

Now both sequences have

$$
N=6.
$$

## Step 2 — Compute each circular output sample

$$
y(0) = 1(3)+2(0)+(-1)(0)+(-2)(0)+3(1)+1(2) =8.
$$

$$
y(1) = 1(2)+2(3)+(-1)(0)+(-2)(0)+3(0)+1(1) =9.
$$

$$
y(2) = 1(1)+2(2)+(-1)(3)=2.
$$

$$
y(3) = 2(1)+(-1)(2)+(-2)(3) =-6.
$$

$$
y(4) = (-1)(1)+(-2)(2)+3(3) =4.
$$

$$
y(5) = (-2)(1)+3(2)+1(3) =7.
$$

Hence,

$$
\boxed{ y(n)=\{8,9,2,-6,4,7\} }
$$

---

# 47. Example 2.29 — Four-point circular convolution

### Given

$$
x_1(n)=\{1,2,1,2\}
$$

$$
x_2(n)=\{4,3,2,1\}.
$$

## Compute $y(0)$

$$
y(0) = 1(4)+2(1)+1(2)+2(3) = 14.
$$

## Compute $y(1)$

$$
y(1) = 1(3)+2(4)+1(1)+2(2) = 16.
$$

## Compute $y(2)$

$$
y(2) = 1(2)+2(3)+1(4)+2(1) = 14.
$$

## Compute $y(3)$

$$
y(3) = 1(1)+2(2)+1(3)+2(4) = 16.
$$

**Therefore,**
$$
\boxed{ y(n)=\{14,16,14,16\} }
$$

---

# 48. Example 2.30 — Matrix method

For

$$
x_1(n)=\{1,2,1,2\}, \qquad x_2(n)=\{4,3,2,1\},
$$

construct the circulant matrix

$$
\mathbf H= \begin{bmatrix} 4&1&2&3\\ 3&4&1&2\\ 2&3&4&1\\ 1&2&3&4 \end{bmatrix}
$$

and

$$
\mathbf x= \begin{bmatrix} 1\\2\\1\\2 \end{bmatrix}.
$$

Then

$$
\mathbf y=\mathbf H\mathbf x
$$

gives

$$
\mathbf y= \begin{bmatrix} 14\\16\\14\\16 \end{bmatrix}.
$$

Thus,

$$
\boxed{ y(n)=\{14,16,14,16\} }
$$

---

# 49. Example 2.31 — 8-point matrix method

Given

$$
x_1(n)=\{1.6,1.4,1.2,1.0,0.8,0.6,0.4,0.2\}
$$

and

$$
x_2(n)=\{1.5,1.3,1.1,0.9,0.7,0.5,0.3,0.1\}.
$$

The textbook forms the $8\times8$ circulant matrix and multiplies by the $8\times1$ input vector.

The resulting circular convolution is

$$
\boxed{ \{5.2,\ 6,\ 6.48,\ 6.64,\ 6.48,\ 6,\ 5.2,\ 4.08\} }
$$

---

# 50. Example 2.32 — Circular convolution by tabular method

For

$$
x_1(n)=\{1,2,1,2\}, \qquad x_2(n)=\{4,3,2,1\},
$$

use

$$
y(n)= \sum_{k=0}^{3}x_1(k)x_2[(n-k)\bmod4].
$$

For example,

$$
y(0)= 1(4)+2(1)+1(2)+2(3)=14,
$$

$$
y(1)= 1(3)+2(4)+1(1)+2(2)=16.
$$

Similarly,

$$
y(2)=14, \qquad y(3)=16.
$$

**Therefore,**
$$
\boxed{ y(n)=\{14,16,14,16\} }
$$

---

# 51. Example 2.33 — Same result by all circular-convolution methods

### Given

$$
x(n)=\{1,0.5\}, \qquad h(n)=\{0.5,1\}.
$$

Since $N=2$, only two output samples exist.

$$
y(0)=1(0.5)+0.5(1)=1
$$

$$
y(1)=1(1)+0.5(0.5)=1.25.
$$

Hence all three displayed methods in the chapter give

$$
\boxed{ y(n)=\{1,1.25\} }
$$

This is a very good small numerical example for checking whether graphical, tabular, and matrix methods are being applied correctly.

---

# 52. Example 2.34 — Compare linear and circular convolution

### Given

$$
x(n)=\{1,-1,1,-1\}, \qquad h(n)=\{1,2,3,4\}.
$$

## Linear convolution

Length:

$$
4+4-1=7.
$$

The result is

$$
\boxed{ y_{\rm linear}(n)= \{1,1,2,2,-3,1,-4\} }
$$

## Circular convolution

Because $N=4$,

$$
\boxed{ y_{\rm circular}(n)= \{-2,2,-2,2\} }
$$

### Important observation

Circular convolution contains the **wrapped-around sum** of the linear-convolution samples.

The sums are consistent:

$$
\sum y_{\rm linear} = \sum y_{\rm circular}.
$$

---

# 53. Example 2.35 — Another comparison

### Given

$$
x_1(n)=\{1,2,0,1\}, \qquad x_2(n)=\{2,2,1,1\}.
$$

Linear convolution:

$$
\boxed{ \{2,6,5,5,4,1,1\} }
$$

Circular convolution:

$$
\boxed{ \{6,7,6,5\} }
$$

The difference occurs because the circular operation wraps the final linear-convolution samples into the beginning.

---

# 54. Example 2.36 — LTI response by linear and circular convolution

### Given

$$
x(n)=\{2,-1,1,2\},
$$

$$
h(n)=\{0.25,2,1,1,-0.5\}.
$$

Lengths:

$$
L_x=4,\qquad L_h=5.
$$

**Therefore,**
$$
L_y=4+5-1=8.
$$

The sequence starts at

$$
0+(-1)=-1.
$$

The linear convolution gives

$$
\boxed{ y(n)= \{-0.5,-3.75,0.25,-1.5,1,3.5,2.5,1\} }
$$

with the textbook's indexing convention.

For circular convolution, pad both sequences to length 8 and calculate the 8-point circular convolution. It produces the **same result** because the zero-padding length equals the required linear-convolution length.

---

# 55. Example 2.37 — Linear convolution from circular convolution

For

$$
x(n)=\{1,2,0,1\}, \qquad h(n)=\{2,2,1,1\},
$$

the linear convolution has length

$$
4+4-1=7.
$$

Pad both sequences to length 7 and perform a 7-point circular convolution.

The result is

$$
\boxed{ \{2,6,5,5,4,1,1\} }
$$

which is exactly the linear convolution.

---


## Theory: obtaining linear convolution from circular convolution

Suppose `x(n)` has length `N_1` and `h(n)` has length `N_2`. Their linear convolution has length

$$
L=N_1+N_2-1.
$$

To obtain this linear convolution by circular convolution:

$$
\boxed{	ext{zero-pad both sequences to length }L\text{, then perform an }L	ext{-point circular convolution.}}
$$

The circular-convolution result is then exactly the linear convolution of the original unpadded sequences. The book states this rule explicitly.

### Why zero padding works

The padding creates enough circular slots to hold every sample of the linear convolution, including its tails, so no output sample wraps back onto an earlier output position.

# 56. Linear convolution from circular convolution

If

$$
L_x=N_1,\qquad L_h=N_2,
$$

then compute the linear-convolution length

$$
\boxed{ N=N_1+N_2-1. }
$$

Pad both sequences to length $N$.

Then

$$
\boxed{ x(n)\ast h(n) = x_p(n)\oplus_N h_p(n) }
$$

where the right side is $N$-point circular convolution of the zero-padded sequences.

**This is one of the most important exam rules.**

---

# 57. Example 2.38

### Given

$$
x(n)=\{3,-2,1,4\}, \qquad h(n)=\{2,5,3\}.
$$

Linear-convolution length:

$$
4+3-1=6.
$$

Pad to six samples:

$$
x_p(n)=\{3,-2,1,4,0,0\},
$$

$$
h_p(n)=\{2,5,3,0,0,0\}.
$$

Perform 6-point circular convolution.

The result is

$$
\boxed{ \{6,11,1,7,23,12\} }
$$

which equals the ordinary linear convolution of the original sequences.

---

# 58. Example 2.39 — How many zeros are needed?

Given lengths

$$
L_x=5,\qquad L_h=2.
$$

Required linear-convolution length:

$$
N=5+2-1=6.
$$

Therefore:

- $x(n)$ needs $6-5=1$ zero.
- $h(n)$ needs $6-2=4$ zeros.

Thus,

$$
\boxed{1\text{ zero for }x(n),\quad4\text{ zeros for }h(n)}
$$

The circular convolution of the padded sequences equals the original regular convolution:

$$
\boxed{ \{2,9,9,4,8,3\} }
$$

The regular convolution of the padded sequences themselves contains the original result followed by the additional trailing zeros created by padding.

---


## Theory: obtaining periodic convolution from linear convolution

For equal-length `N`-sample sequences, the linear convolution has `2N-1` samples. The chapter shows how to fold the tail back into the first `N` samples:

1. Compute the `2N-1`-sample linear convolution.
2. Append one zero so the working array has `2N` positions.
3. Divide the result into two blocks of `N` samples.
4. Align the second block with the first block.
5. Add the corresponding samples.

The resulting `N` samples are the periodic/circular convolution.

This is the **wrap-around-sum** interpretation of circular convolution. It is one of the most important conceptual bridges in this chapter.

# 59. Periodic convolution from linear convolution

For two $N$-point periodic sequences:

1. Calculate the linear convolution.
2. It has $2N-1$ samples.
3. Append one zero to make $2N$ samples.
4. Split into two $N$-sample halves.
5. Add the second half to the first half.

This produces the $N$-point periodic convolution.

$$
\boxed{ \text{circular convolution} = \text{first half} + \text{wrapped second half} }
$$

---

# 60. Example 2.40 — Periodic convolution using linear convolution

## (a)

$$
x_1(n)=\{1,2,-1,-2,3,1\},
$$

$$
x_2(n)=\{3,2,1\}.
$$

Pad $x_2$ to six samples:

$$
x_2(n)=\{3,2,1,0,0,0\}.
$$

The linear convolution is

$$
\{3,8,2,-6,4,7,5,1,0,0,0\}.
$$

Split after six samples:

$$
\text{first half}=\{3,8,2,-6,4,7\},
$$

$$
\text{wrapped half}=\{5,1,0,0,0,0\}.
$$

Add:

$$
\boxed{ y_p(n)=\{8,9,2,-6,4,7\} }
$$

## (b)

For

$$
\{1,2,1,2\}
$$

and

$$
\{4,3,2,1\},
$$

the linear convolution is

$$
\{4,11,12,16,10,5,2\}.
$$

Wrap the last four samples:

$$
\{10,5,2,0\}.
$$

Add to the first four:

$$
\boxed{ \{14,16,14,16\} }
$$

## (c)

For

$$
\{1,-1,1,-1\}
$$

and

$$
\{1,2,3,4\},
$$

linear convolution is

$$
\{1,1,2,2,-3,1,-4\}.
$$

Wrap:

$$
\{-3,1,-4,0\}.
$$

Hence,

$$
\boxed{ \{-2,2,-2,2\} }
$$

## (d)

For

$$
\{1,2,0,1\}
$$

and

$$
\{2,2,1,1\},
$$

the periodic convolution is

$$
\boxed{ \{6,7,6,5\} }
$$

## (e)

For

$$
\{1,0.5\}
$$

and

$$
\{0.5,1\},
$$

the linear result is

$$
\{0.5,1.25,0.5\}.
$$

Wrap the last two-sample portion:

$$
\{0.5,0\}.
$$

Then

$$
\boxed{ y_p(n)=\{1,1.25\} }
$$

---


## Theory: periodic extension of a non-periodic sequence

A periodic extension is formed by adding infinitely many shifted replicas of a sequence, separated by the period `N`:

$$
\boxed{x_p(n)=\sum_{k=-\infty}^{\infty}x(n+kN)}
$$

The result satisfies periodicity with period `N`.

### Finite-sequence wrap-around interpretation

For a finite sequence, one period can be found by taking blocks of `N` samples, wrapping blocks that extend beyond the first period back into positions `0,1,\ldots,N-1`, and adding overlapping values. If the sequence contains fewer than `N` samples, zero padding completes the first period.

### Important distinction

**Periodic extension is not the same as ordinary zero padding.** Zero padding merely increases the stored length. Periodic extension folds displaced replicas back into one period and therefore can cause several original samples to add at the same location.

# 61. Periodic extension

If $x(n)$ is an absolutely summable or finite sequence, its periodic extension with period $N$ is obtained by adding shifted replicas:

$$
\boxed{ x_p(n)= \sum_{k=-\infty}^{\infty}x(n+kN) }
$$

For finite sequences, an equivalent method is:

1. Divide into blocks of $N$.
2. Wrap them onto the first $N$ positions.
3. Add the samples occupying the same position.

---

# 62. Example 2.41 — Periodic extension

## (a)

Given

$$
x(n)=\{2,0,3,0,4,7,6,5\}, \qquad N=3.
$$

Group into blocks of 3:

$$
\{2,0,3\}, \quad \{0,4,7\}, \quad \{6,5\}.
$$

Pad the last block:

$$
\{6,5,0\}.
$$

Add:

$$
\begin{aligned} x_p(0)&=2+0+6=8,\\ x_p(1)&=0+4+5=9,\\ x_p(2)&=3+7+0=10. \end{aligned}
$$

**Therefore,**
$$
\boxed{ x_p(n)=\{8,9,10\} }
$$

for one period.

---


## Theory: response of an LTI system to a periodic input

A key result stated by the book is:

$$
\boxed{	ext{A periodic input with period }N\text{ produces a periodic steady-state output with the same period }N.}
$$

One direct method is to form the linear convolution of the periodic input representation with the system impulse response and then ignore the **startup period**; the remaining output is the periodic part.

The chapter also gives two equivalent wrap-around approaches:

- Convolve one input period with the finite impulse response, then periodically superpose/wrap the resulting output.
- First obtain one period of the periodic extension of `h(n)`, convolve it with one input period, and wrap the result into one output period.

### Startup effect

When the periodic input is initially turned on, the system may first show a transient/startup part. The book notes that this startup effect lasts for one period in the examples, after which the repeating output period is obtained.

# 63. System response to periodic inputs

If the input is periodic with period $N$, the output of the discrete-time system considered in the chapter is also periodic with the same period $N$.

Several approaches are given:

1. Linear-convolve one period of the input with $h(n)$ and ignore startup effects.
2. Find one-period output and then periodically extend/wrap it.
3. Periodically extend $h(n)$, convolve one input period with that extension, then wrap the result.

---

# 64. Example 2.42

For a periodic input with period

$$
N=3
$$

and

$$
x(n)=\{2,3,-4,2,3,-4,\ldots\},
$$

with

$$
h(n)=\{1,2\},
$$

the startup transient appears first. After wrapping/settling,

$$
\boxed{ y_p(n)=\{-6,7,2\} }
$$

for one period.

---

# 65. Example 2.43

For

$$
x(n)=\{1,3,-2,1,3,-2,\ldots\},
$$

and

$$
h(n)=\{1,2,1,1\},
$$

the input period is $N=3$.

The resulting steady periodic output is

$$
\boxed{ y_p(n)=\{1,6,3\} }
$$

---

# 66. Example 2.44 — Three ways to find periodic system response

For

$$
x_p(n)=\{2,3,-4\}, \qquad h(n)=\{1,2\}, \qquad N=3,
$$

one-period regular convolution gives

$$
y_1(n)=\{2,7,-2,8\}.
$$

Wrap the last sample(s) back into the first three positions:

$$
\{2,7,-2\}+\{8,0,0\} = \boxed{ \{-6,7,2\} }
$$

under the indexing convention used in the textbook.

The chapter also shows the equivalent method of first forming the periodic extension of $h(n)$:

$$
h_p(n)=\{1,2,0\},
$$

then convolving one input period with $h_p(n)$ and wrapping.

---

# 67. Example 2.45

Given periodic input

$$
x_p(n)=\{1,3,-2\}
$$

and

$$
h(n)=\{2,1,2,1,1\}, \qquad N=3.
$$

One period of the input is convolved with $h(n)$, producing the transient sequence.

After wrap-around, the periodic output is

$$
\boxed{ y_p(n)=\{5,7,2\} }
$$

The same result is obtained by first forming the periodic extension of $h(n)$:

$$
h_p(n)=\{3,2,2\}.
$$

---


## Theory: correlation and its role

Correlation is a mathematical operation similar to convolution, but its purpose is different: it measures the **similarity** between signals as one is shifted relative to the other. The book highlights applications such as radar/sonar target detection, image processing, and control engineering.

There are two types:

- **Cross correlation:** comparison of two different sequences.
- **Autocorrelation:** comparison of a sequence with itself.

The lag/shift parameter tells us how far one sequence has been shifted relative to the reference sequence.

# 68. Correlation

Correlation measures the **similarity** between two signals.

There are two types:

1. Cross correlation
2. Autocorrelation

Correlation is closely related to convolution because it can be performed by **flipping one sequence and then convolving**.

---


## Theory: cross correlation

For sequences `x(n)` and `y(n)`, the book defines cross correlation as

$$
\boxed{R_{xy}(n)=\sum_{k=-\infty}^{\infty}x(k)y(k-n)}
$$

Here `n` is the **lag** or shift parameter. In `R_{xy}`, the first sequence `x(n)` is treated as the reference sequence, while `y(n)` is shifted. If the roles are reversed,

$$
\boxed{R_{yx}(n)=\sum_k y(k)x(k-n)}.
$$

In general, they are not equal at the same lag, but they satisfy the important folded relation

$$
\boxed{R_{xy}(n)=R_{yx}(-n)}.
$$

The book explains that the negative-lag relation corresponds to flipping one correlation sequence about the origin.

### Correlation as convolution

The most useful computational identity is

$$
\boxed{R_{xy}(n)=x(n)\ast y(-n)}
$$

Therefore, to compute cross correlation by the methods of this chapter:

1. Flip `y(n)` to obtain `y(-n)`.
2. Convolve `x(n)` with the flipped sequence.
3. Keep the correct starting index and output length.

# 69. Cross correlation

The book defines

$$
\boxed{ R_{xy}(n) = \sum_{k=-\infty}^{\infty} x(k)y(k-n) }
$$

or equivalently, depending on the adopted lag orientation,

$$
\boxed{ R_{xy}(n) = \sum_{k=-\infty}^{\infty} x(k)y(k+n) }
$$

The important point is to follow the **book's stated shift convention** consistently.

With the convolution interpretation,

$$
\boxed{ R_{xy}(n)=x(n)\ast y(-n) }
$$

Therefore:

$$
\boxed{ \text{Cross correlation} = \text{convolution with one sequence folded} }
$$

The reference sequence is kept fixed while the other is shifted.

---

# 70. Cross-correlation symmetry relation

At zero shift,

$$
R_{xy}(0) = \sum_k x(k)y(k).
$$

Also,

$$
\boxed{ R_{xy}(n)=R_{yx}(-n) }
$$

Therefore, in general,

$$
\boxed{ R_{xy}(n)\ne R_{yx}(n) }
$$

but they are time-reversed versions of one another.

---


## Theory: autocorrelation

Autocorrelation is the correlation of a sequence with itself. It measures how similar `x(n)` is to a shifted version of itself:

$$
\boxed{R_{xx}(n)=\sum_{k=-\infty}^{\infty}x(k)x(k-n)}
$$

The book also gives the equivalent convolution form

$$
\boxed{R_{xx}(n)=x(n)\ast x(-n)}.
$$

At zero lag,

$$
\boxed{R_{xx}(0)=\sum_k |x(k)|^2}
$$

for the real-valued sequences treated in the chapter. Thus the zero-lag value represents the total signal-energy-type sum used in the chapter's discussion.

# 71. Autocorrelation

Autocorrelation correlates a sequence with itself:

$$
\boxed{ R_{xx}(n)= \sum_kx(k)x(k-n) }
$$

and equivalently,

$$
\boxed{ R_{xx}(n)=x(n)\ast x(-n) }
$$

At zero lag,

$$
\boxed{ R_{xx}(0)=\sum_k|x(k)|^2 }
$$

which equals the energy for an energy signal.

---


## Theory: key autocorrelation properties

### 1. Even symmetry

The autocorrelation sequence is even symmetric:

$$
\boxed{R_{xx}(n)=R_{xx}(-n)}.
$$

So you often need to compute only the nonnegative-lag side and reflect it about `n=0`.

### 2. Maximum at zero lag

The chapter states

$$
\boxed{R_{xx}(n)\le R_{xx}(0)}
$$

so the autocorrelation reaches its maximum at zero lag. This is the basis of synchronization and similarity-detection interpretations.

### 3. Noise detection

The book notes that noise is essentially uncorrelated with the desired signal. Consequently, autocorrelation of a noisy observation can emphasize the correlated signal component and produce a peak near zero lag.

### Normalized correlations

The normalized autocorrelation is

$$
\boxed{r_{xx}(n)=\frac{R_{xx}(n)}{R_{xx}(0)}}
$$

and the normalized cross-correlation coefficient is

$$
\boxed{r_{xy}(n)=\frac{R_{xy}(n)}{\sqrt{R_{xx}(0)R_{yy}(0)}}}.
$$

The book states that the normalized cross-correlation coefficient lies between `-1` and `+1`, and zero indicates no correlation.

# 72. Properties of autocorrelation

## Property 1 — Even symmetry

$$
\boxed{ R_{xx}(n)=R_{xx}(-n) }
$$

## Property 2 — Maximum at zero

$$
\boxed{ |R_{xx}(n)|\le R_{xx}(0) }
$$

So the autocorrelation reaches its maximum at zero shift.

## Property 3 — Noise detection

A signal buried in noise can often be identified by correlation because uncorrelated noise tends not to reinforce at the correct delay.

---

# 73. Normalized correlation

Normalized autocorrelation:

$$
\boxed{ \rho_{xx}(n)= \frac{R_{xx}(n)}{R_{xx}(0)} }
$$

Normalized cross correlation:

$$
\boxed{ \rho_{xy}(n)= \frac{R_{xy}(n)} {\sqrt{R_{xx}(0)R_{yy}(0)}} }
$$

The normalized cross-correlation coefficient lies between $-1$ and $+1$.

---

# 74. Example 2.46 — Cross correlation

### Given

$$
x(n)=\{2,3,1,4\},
$$

$$
y(n)=\{1,3,2,1\}.
$$

## Step 1 — Fold $y(n)$

$$
y(-n)=\{1,2,3,1\}.
$$

## Step 2 — Convolve

$$
R_{xy}(n) = x(n)\ast y(-n).
$$

Calculate the overlapping products:

$$
R_{xy}(n) = \{2,\ 7,\ 13,\ 17,\ 14,\ 13,\ 4\}.
$$

**Therefore,**
$$
\boxed{ R_{xy}(n)=\{2,7,13,17,14,13,4\} }
$$

The central value corresponds to the strongest alignment.

---

# 75. Example 2.47 — Autocorrelation

### Given

$$
x(n)=\{2,3,1,4\}.
$$

Fold:

$$
x(-n)=\{4,1,3,2\}.
$$

Then

$$
R_{xx}(n)=x(n)\ast x(-n).
$$

The convolution gives

$$
\boxed{ R_{xx}(n)=\{8,14,13,30,13,14,8\} }
$$

Notice:

$$
8,14,13,\boxed{30},13,14,8
$$

is symmetric around the center.

The maximum is

$$
R_{xx}(0)=30.
$$

Also,

$$
R_{xx}(n)=R_{xx}(-n).
$$

---


## Theory: power-signal and periodic correlation

For power signals, the book uses a time-average definition rather than the finite-energy sum used for finite sequences. For periodic signals of period `N`, one period is sufficient and the correlation is obtained by averaging/summing products over that period.

For two periodic sequences with common period `N`, the chapter writes the periodic cross correlation in the form

$$
\boxed{R_{xy,p}(n)=\sum_{k=0}^{N-1}x(k)y(k-n)}
$$

with an optional factor `1/N` depending on convention. The periodic autocorrelation is obtained by replacing `y` with `x`.

### Periodic-correlation properties

- `R_{yx,p}(n)` is the circularly flipped version of `R_{xy,p}(n)`.
- Periodic autocorrelation has circular even symmetry.
- Periodic autocorrelation attains its maximum at `n=0`.

# 76. Correlation of power and periodic signals

For power signals, the cross-correlation uses a time-average limit:

$$
\boxed{ R_{xy}(n) = \lim_{N\to\infty} \frac{1}{2N+1} \sum_{k=-N}^{N} x(k)y(k-n) }
$$

For periodic sequences of period $N$,

$$
\boxed{ R_{xy,p}(n) = \frac1N \sum_{k=0}^{N-1} x(k)y(k-n) }
$$

Again, the book notes that some conventions place the $1/N$ averaging factor explicitly while others absorb it according to the adopted definition.

---

# 77. Example 2.48 — Cross-correlation symmetry

The textbook demonstrates that

$$
R_{xh}(n)
$$

and

$$
R_{hx}(n)
$$

are not generally identical.

Instead,

$$
\boxed{ R_{xh}(n)=R_{hx}(-n) }
$$

For the stated sequences, the textbook obtains

$$
R_{xh}(n)=\{9,27,26,15,9,2\}
$$

and

$$
R_{hx}(n)=\{2,9,15,26,27,9\}.
$$

The second is the reversed form of the first.

### Exam check

Reverse

$$
\{9,27,26,15,9,2\}
$$

to obtain

$$
\{2,9,15,26,27,9\}.
$$

**Therefore,**
$$
\boxed{R_{xh}(n)=R_{hx}(-n)}
$$

---

# 78. Example 2.49 — Autocorrelation

For

$$
x(n)=\{2,5,-4\},
$$

fold the sequence:

$$
x(-n)=\{-4,5,2\}.
$$

Convolve:

$$
R_{xx}(n)=x(n)\ast x(-n).
$$

The result is

$$
\boxed{ R_{xx}(n)=\{-8,-10,45,-10,-8\} }
$$

or, when written from negative to positive lags in the textbook's indexing convention,

$$
\boxed{ \{8,-10,45,-10,8\} }
$$

The essential checks are:

$$
R_{xx}(n)=R_{xx}(-n)
$$

and

$$
R_{xx}(0)=45.
$$

---

# 79. Example 2.50 — Cross correlation of two exponentials

### Given

$$
x(n)=(0.6)^n u(n),
$$

$$
h(n)=(0.3)^n u(n).
$$

Use

$$
R_{xh}(n) = \sum_kx(k)h(k-n).
$$

The step functions determine the allowable $k$-range.

## Case 1 — $n<0$

Then the lower bound is $k=0$:

$$
R_{xh}(n) = \sum_{k=0}^{\infty} (0.6)^k(0.3)^{k-n}.
$$

Factor the $n$-dependent part:

$$
R_{xh}(n) = (0.3)^{-n} \sum_{k=0}^{\infty}(0.18)^k.
$$

Because

$$
|0.18|<1,
$$

the geometric series converges:

$$
\sum_{k=0}^{\infty}(0.18)^k = \frac1{1-0.18}.
$$

Hence,

$$
\boxed{ R_{xh}(n)= \frac{(0.3)^{-n}}{1-0.18}, \qquad n<0 }
$$

## Case 2 — $n\ge0$

The lower limit becomes $k=n$. Set

$$
m=k-n.
$$

Then

$$
R_{xh}(n) = (0.6)^n \sum_{m=0}^{\infty}(0.18)^m
$$

so

$$
\boxed{ R_{xh}(n)= \frac{(0.6)^n}{1-0.18}, \qquad n\ge0 }
$$

Combining the two ranges gives the piecewise correlation sequence.

---

# 80. Example 2.51 — Autocorrelation of $a^n u(n)$

### Given

$$
x(n)=a^n u(n).
$$

Autocorrelation is even, so evaluate only $n\ge0$.

For $n\ge0$,

$$
R_{xx}(n) = \sum_{k=n}^{\infty}a^k a^{k-n}.
$$

Simplify:

$$
R_{xx}(n) = a^n \sum_{m=0}^{\infty}a^{2m}
$$

or equivalently,

$$
R_{xx}(n) = \frac{a^n}{1-a^2}, \qquad |a|<1.
$$

The even extension is

$$
\boxed{ R_{xx}(n)= \frac{a^{|n|}}{1-a^2}, \qquad |a|<1 }
$$

This is a very useful standard result.

---

# 81. Periodic correlation

For periodic sequences $x(n)$ and $y(n)$ with common period $N$,

$$
\boxed{ R_{xy,p}(n) = \frac1N \sum_{k=0}^{N-1} x(k)y(k-n) }
$$

and

$$
\boxed{ R_{yx,p}(n)=R_{xy,p}(-n) }
$$

The periodic autocorrelation is circularly even:

$$
\boxed{ R_{xx,p}(n)=R_{xx,p}(-n) }
$$

and its maximum occurs at

$$
\boxed{n=0}.
$$

---

# 82. Example 2.52 — Periodic cross correlation

For the stated first-period sequences in the textbook, the method is:

### Step 1

Compute the ordinary linear cross correlation.

### Step 2

Wrap the resulting $2N-1$ sequence around to $N$ samples.

### Step 3

Write the result as one period.

For the textbook example,

$$
\boxed{ R_{xy,p}(n)=\{9,1,5,3\} }
$$

and

$$
\boxed{ R_{yx,p}(n)=\{9,3,5,1\} }
$$

These are circular reversals of each other.

---

# 83. Example 2.53 — Periodic autocorrelation

## (a)

For

$$
x(n)=\{1,3,0,4\},
$$

the ordinary autocorrelation is first computed and then wrapped.

The periodic result is

$$
\boxed{ R_{xx,p}(n)=\{26,7,24,7\} }
$$

## (b)

For

$$
y(n)=\{2,1,-2,1\},
$$

the periodic autocorrelation is

$$
\boxed{ R_{yy,p}(n)=\{10,0,6,0\} }
$$

Both show circular even symmetry.

---

# 84. Linear vs circular convolution — exam table

| Point | Linear convolution | Circular convolution |
|---|---|---|
| Formula | $\sum_{k}x(k)h(n-k)$ | $\sum_{k=0}^{N-1}x(k)h[(n-k)\bmod N]$ |
| Output length | $L_x+L_h-1$ | $N$ |
| Indexing | ordinary | modulo $N$ |
| Wrap-around | No | Yes |
| Equal input lengths required? | No | Yes; pad if necessary |
| Main use | LTI response, FIR filtering | DFT/DFT-based processing, periodic sequences |

---

# 85. Linear convolution from circular convolution — most important rule

If two sequences have lengths $L_x$ and $L_h$, choose

$$
\boxed{ N\ge L_x+L_h-1 }
$$

and zero-pad both to length $N$.

Then the $N$-point circular convolution equals the linear convolution.

The minimum choice is

$$
\boxed{ N=L_x+L_h-1. }
$$

---

# 86. Circular convolution from linear convolution — most important rule

For two $N$-point sequences:

1. Compute $2N-1$-point linear convolution.
2. Append one zero.
3. Divide into two $N$-sample blocks.
4. Add corresponding positions.

$$
\boxed{ y_{\rm circ}(n) = y_{\rm lin}(n) + y_{\rm lin}(n+N) }
$$

with the exact index orientation determined by the chosen sample range.

---

# 87. Fast exam algorithms

## 87.1 — Linear convolution

$$
\boxed{ \text{Flip} \rightarrow \text{Shift} \rightarrow \text{Multiply} \rightarrow \text{Sum} }
$$

### Checklist

- Find starting index.
- Find output length.
- Flip one sequence.
- Shift.
- Multiply overlaps.
- Sum.
- Repeat.

## 87.2 — Circular convolution

$$
\boxed{ \text{Equalize length} \rightarrow \text{Circular flip} \rightarrow \text{Circular shift} \rightarrow \text{Multiply} \rightarrow \text{Sum} }
$$

## 87.3 — Deconvolution

$$
\boxed{ y=x\ast h \rightarrow \text{solve sequentially for unknown samples} }
$$

or

$$
\boxed{ X(z)=Y(z)/H(z) }
$$

## 87.4 — Cross correlation

$$
\boxed{ R_{xy}=x\ast y(-n) }
$$

## 87.5 — Autocorrelation

$$
\boxed{ R_{xx}=x\ast x(-n) }
$$

---

# 88. Common mistakes

### Mistake 1 — Using $L_xL_h$

Wrong:

$$
L_y=L_xL_h.
$$

Correct:

$$
\boxed{L_y=L_x+L_h-1}
$$

### Mistake 2 — Forgetting starting indices

If

$$
x(n)\text{ starts at }n=-2
$$

and

$$
h(n)\text{ starts at }n=1,
$$

then

$$
\boxed{y(n)\text{ starts at }-1}.
$$

### Mistake 3 — Circular convolution without zero padding

For unequal lengths, first make the lengths equal.

### Mistake 4 — Confusing correlation with convolution

Correlation requires a fold:

$$
\boxed{R_{xy}=x\ast y(-n)}
$$

### Mistake 5 — Assuming $R_{xy}=R_{yx}$

Generally,

$$
\boxed{ R_{xy}(n)=R_{yx}(-n) }
$$

not necessarily $R_{xy}(n)=R_{yx}(n)$.

### Mistake 6 — Forgetting the modulo-$N$ rule

Circular convolution uses

$$
(n-k)\bmod N.
$$

### Mistake 7 — Forgetting startup effects for periodic inputs

A causal system driven by a periodic input may show an initial transient before the periodic steady-state portion.

---

# 89. Important identities to memorize

$$
\boxed{ x(n)\ast \delta(n)=x(n) }
$$

$$
\boxed{ x(n)\ast \delta(n-k)=x(n-k) }
$$

$$
\boxed{ x(n)\ast h(n)=h(n)\ast x(n) }
$$

$$
\boxed{ [x\ast h_1]\ast h_2=x\ast [h_1\ast h_2] }
$$

$$
\boxed{ x\ast (h_1+h_2)=x\ast h_1+x\ast h_2 }
$$

$$
\boxed{ L_y=L_x+L_h-1 }
$$

$$
\boxed{ n_{y,\rm start}=n_{x,\rm start}+n_{h,\rm start} }
$$

$$
\boxed{ R_{xy}(n)=R_{yx}(-n) }
$$

$$
\boxed{ R_{xx}(n)=R_{xx}(-n) }
$$

$$
\boxed{ R_{xx}(0)\text{ is the maximum autocorrelation value} }
$$

---

# 90. Short Questions with Answers — Exam Ready

## Q1. What is discrete convolution?

**Answer:** Discrete convolution is the mathematical operation used to find the zero-state response of a relaxed LTI system.

---

## Q2. Write the convolution expression.

$$
\boxed{ y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k) }
$$

or

$$
\boxed{ y(n)=x(n)\ast h(n) }
$$

---

## Q3. How are the output parameters related to input parameters?

For finite sequences:

$$
\boxed{ L_y=L_x+L_h-1 }
$$

Starting indices add, and ending indices add.

---

## Q4. List the methods of linear convolution.

1. Graphical method
2. Tabular array
3. Tabular method
4. Matrix method
5. Sum-by-column method
6. Flip-shift-multiply-sum method

---

## Q5. What is deconvolution?

Finding $x(n)$ or $h(n)$ when $y(n)=x(n)\ast h(n)$ and the other sequence is known.

---

## Q6. Methods of deconvolution?

1. Polynomial division
2. Recursion
3. Tabular method

---

## Q7. Difference between linear and circular convolution?

Circular convolution uses modulo-$N$ indexing and produces $N$ samples; linear convolution does not use wrap-around and gives $L_x+L_h-1$ samples.

---

## Q8. How can linear convolution be obtained from circular convolution?

Zero-pad both sequences to length

$$
\boxed{L_x+L_h-1}
$$

and perform circular convolution.

---

## Q9. How can circular convolution be obtained from linear convolution?

For two $N$-point sequences, compute the $2N-1$-point linear convolution, append a zero to make $2N$, split into two $N$-point blocks, and add the blocks.

---

## Q10. Define correlation.

Correlation is a measure of similarity between two signals.

---

## Q11. Define cross correlation.

Correlation between two different signals.

---

## Q12. Define autocorrelation.

Correlation of a signal with itself.

---

## Q13. What is the relation between correlation and convolution?

$$
\boxed{ R_{xy}(n)=x(n)\ast y(-n) }
$$

Correlation is convolution after reversing one signal.

---

## Q14. Where does autocorrelation attain its maximum?

At

$$
\boxed{n=0}.
$$

---

## Q15. State the symmetry property of cross correlation.

$$
\boxed{ R_{xy}(n)=R_{yx}(-n) }
$$

---

## Q16. State the symmetry property of autocorrelation.

$$
\boxed{ R_{xx}(n)=R_{xx}(-n) }
$$

---

# 91. Review questions — model answers

## 1. Write the properties of discrete convolution.

Use the five boxed properties in Section 4.

## 2. Discuss methods of linear convolution.

Explain graphical, tabular array, tabular, matrix, sum-by-column and flip-shift-multiply-sum methods.

## 3. What is deconvolution? Discuss methods.

Define deconvolution, then explain polynomial division, recursion and tabular method.

## 4. Discuss methods of circular convolution.

Explain concentric-circle, tabular-array, matrix and DFT methods as listed in the chapter.

## 5. Explain conversion between linear and circular convolution.

Use Sections 56 and 60.

---

# 92. Fill-in-the-blanks — answer key

1. filtering  
2. zero-state response  
3. multiplying  
4. correlation  
5. impulse response  
6. $y(n)=\sum x(k)h(n-k)$  
7. $\sum_{k=-\infty}^{\infty}x(k)h(n-k)$  
8. $\sum_{k=0}^{\infty}x(k)h(n-k)$  
9. $\sum_{k=-\infty}^{n}x(k)h(n-k)$  
10. $\sum_{k=0}^{n}x(k)h(n-k)$  
11. $x\ast h=h\ast x$  
12. associative property  
13. distributive property  
14. shifting property  
15. impulse property  
16. running sum  
17. $N_1+N_2-1$  
18. $n_1+n_2$  
19. $n_3+n_4$  
20. corresponding convolution locations  
21. corresponding convolution locations  
22. deconvolution  
23. periodic  
24. $N$  
25. correlation  
26. correlation  
27. $R_{xy}(n)$  
28. $n=0$

---

# 93. Objective questions — answer key

1. **(a)** commutative property  
2. **(b)** associative property  
3. **(c)** distributive property  
4. **(a)** full two-sided convolution sum  
5. **(a)** full two-sided convolution sum  
6. **(c)** $\sum_{k=-\infty}^{n}x(k)h(n-k)$  
7. **(d)** $\sum_{k=0}^{n}x(k)h(n-k)$  
8. **(b)** 7  
9. **(c)** $\{3,8,12,8,3\}$  
10. Use the convolution result obtained from the stated indexed sequences; check indexing and arrow location carefully.  
11. Use the stated convolution of the two step/impulse combinations; verify by direct polynomial or tabular multiplication.  
12. **(a)** under the displayed sequence-length convention  
13. **(a)** circular right shift by 2  
14. **(b)** circular left shift by 2  
15. **(a)** under the textbook's circular-flip indexing  
16. **(b)** $\{6,7,6\}$  
17. **(a)** $\{5,3,2\}$ under the listed wrap convention  
18. **(a)** $\{2,2,0\}$  
19. Compute by folding $h(n)$ and convolving; use the book's lag convention.  
20. Compute $x(n)\ast x(-n)$; the result must be even symmetric.

> **Important:** several scanned objective choices contain duplicated/garbled option formatting. The safest exam approach is to calculate the sequence directly rather than rely only on the letter choice.

---

# 94. Numerical problems — method-first answer sheet

## Problem 1

For

$$
h(n)=\left(\frac12\right)^n u(n), \qquad x(n)=2^n u(n),
$$

use the causal exponential convolution formula.

Start with

$$
y(n)=\sum_{k=0}^{n} 2^k\left(\frac12\right)^{n-k}.
$$

Reduce the sum to a geometric series and simplify.

---

## Problem 2

For

$$
h(n)=\left(\frac15\right)^n u(n),
$$

step response is

$$
s(n)=\sum_{k=0}^{n}\left(\frac15\right)^k.
$$

Hence

$$
\boxed{ s(n)= \frac{1-(1/5)^{n+1}}{1-1/5}u(n) }
$$

---

## Problem 3

For each finite sequence pair, the exam procedure is:

1. Determine starting indices.
2. Determine $L_y=L_x+L_h-1$.
3. Select one method requested by the question.
4. Compute all output samples.
5. Mark the $n=0$ location correctly.
6. Verify using
$$
\sum x(n)\sum h(n)=\sum y(n).
$$

For “all methods,” use the same final result and demonstrate the requested computational method separately.

---

## Problem 4

Periodic extension with $N=3$:

1. Break the sequence into blocks of three.
2. Pad the final incomplete block with zeros.
3. Add corresponding columns.
4. The resulting three samples are one period.

---

## Problem 5

For periodic system response:

1. Determine one input period.
2. Find the ordinary convolution with $h(n)$.
3. Wrap the result to $N$ samples.
4. The wrapped sequence is one period of the steady periodic output.

---

## Problem 6

Given $y(n)$ and $h(n)$:

$$
y(n)=x(n)\ast h(n).
$$

First determine the unknown sequence length from

$$
L_y=L_x+L_h-1.
$$

Then use recursion:

$$
x(0)=\frac{y(0)}{h(0)}
$$

$$
x(1)= \frac{y(1)-x(0)h(1)}{h(0)}
$$

and continue.

---

## Problem 7

Same recursion idea, but now the input $x(n)$ is known and the impulse response is unknown:

$$
h(0)=\frac{y(0)}{x(0)}
$$

$$
h(1)= \frac{y(1)-x(1)h(0)} {x(0)}
$$

and continue.

---

## Problem 8

For circular convolution:

1. Make the lengths equal.
2. Fold one sequence.
3. Periodically extend.
4. Shift circularly.
5. Multiply.
6. Sum.

Cross-check using the matrix or tabular method.

---

## Problem 9

Compute both:

$$
y_{\rm linear}(n)
$$

and

$$
y_{\rm circular}(n),
$$

then compare lengths and wrap-around behavior.

---

## Problem 10

For an LTI response:

$$
y(n)=x(n)\ast h(n).
$$

Linear convolution directly gives the response.

For circular convolution, pad both to

$$
N=L_x+L_h-1.
$$

Then the circular result equals the linear result.

---

## Problem 11

To obtain regular convolution using circular convolution:

$$
\boxed{ N=L_x+L_h-1 }
$$

Pad and perform $N$-point circular convolution.

---

## Problem 12

For zero-padding questions:

$$
N=L_x+L_h-1.
$$

Zeros required:

$$
\boxed{N-L_x}
$$

for $x(n)$, and

$$
\boxed{N-L_h}
$$

for $h(n)$.

---

## Problem 13

To obtain periodic convolution from linear convolution:

1. Compute $2N-1$ samples.
2. Append zero.
3. Wrap the second $N$-sample block onto the first.
4. Add.

---

## Problem 14

Cross correlation:

$$
\boxed{ R_{xh}(n)=x(n)\ast h(-n) }
$$

---

## Problem 15

Calculate both

$$
R_{xh}(n)
$$

and

$$
R_{hx}(n).
$$

Then show

$$
\boxed{ R_{xh}(n)=R_{hx}(-n) }
$$

by reversing the sequence.

---

## Problem 16

Autocorrelation:

$$
\boxed{ R_{xx}(n)=x(n)\ast x(-n) }
$$

Check:

$$
R_{xx}(n)=R_{xx}(-n).
$$

---

## Problem 17

Periodic cross correlation:

1. Calculate linear cross correlation.
2. Wrap the result into $N$ samples.
3. Repeat with reversed roles.
4. Confirm
$$
R_{yx,p}(n)=R_{xy,p}(-n).
$$

---

## Problem 18

Periodic autocorrelation:

1. Calculate ordinary autocorrelation.
2. Wrap to $N$ samples.
3. Check circular even symmetry.
4. Check maximum at zero lag.

---

# 95. One-page formula sheet

## Linear convolution

$$
\boxed{ y(n)=\sum_{k=-\infty}^{\infty}x(k)h(n-k) }
$$

For causal input/system:

$$
\boxed{ y(n)=\sum_{k=0}^{n}x(k)h(n-k) }
$$

## Finite length

$$
\boxed{ L_y=L_x+L_h-1 }
$$

## Starting index

$$
\boxed{ n_{y,\text{start}} = n_{x,\text{start}} + n_{h,\text{start}} }
$$

## Step response

$$
\boxed{ s(n)=h(n)\ast u(n) }
$$

For causal $h(n)$:

$$
\boxed{ s(n)=\sum_{k=0}^{n}h(k) }
$$

## Circular convolution

$$
\boxed{ y_p(n)= \sum_{k=0}^{N-1} x(k)h[(n-k)\bmod N] }
$$

## Linear from circular

$$
\boxed{ N=L_x+L_h-1 }
$$

zero-pad and circularly convolve.

## Circular from linear

Compute $2N-1$-sample linear convolution, append zero, split into $N$+$N$, and add.

## Periodic extension

$$
\boxed{ x_p(n)=\sum_{k=-\infty}^{\infty}x(n+kN) }
$$

## Cross correlation

$$
\boxed{ R_{xy}(n)=x(n)\ast y(-n) }
$$

## Autocorrelation

$$
\boxed{ R_{xx}(n)=x(n)\ast x(-n) }
$$

## Cross-correlation symmetry

$$
\boxed{ R_{xy}(n)=R_{yx}(-n) }
$$

## Autocorrelation symmetry

$$
\boxed{ R_{xx}(n)=R_{xx}(-n) }
$$

## Autocorrelation maximum

$$
\boxed{ R_{xx}(0)\ge |R_{xx}(n)| }
$$

---

# 96. Last-minute exam decision tree

### If the question says “find convolution”

Use

$$
y(n)=\sum_kx(k)h(n-k).
$$

### If the sequences are finite

Immediately calculate:

$$
\boxed{L_y=L_x+L_h-1}.
$$

### If circular convolution is asked

Make lengths equal and use modulo-$N$.

### If linear convolution must be obtained from circular convolution

Use

$$
\boxed{N=L_x+L_h-1}
$$

for zero padding.

### If correlation is asked

Fold one sequence first.

$$
\boxed{ R_{xy}=x\ast y(-n) }
$$

### If autocorrelation is asked

Fold the same sequence.

$$
\boxed{ R_{xx}=x\ast x(-n) }
$$

### If the answer looks suspicious

Check:

$$
\boxed{ \sum y = (\sum x)(\sum h) }
$$

for ordinary finite convolution.

For autocorrelation, check:

- symmetry about zero;
- maximum at zero lag.

---

# 97. Core worked-example list from Chapter 2

| Example | Main concept |
|---|---|
| 2.1 | Causal exponential convolution |
| 2.2 | Finite input with exponential $h(n)$ |
| 2.3 | Exponential convolution using geometric series |
| 2.4 | General unequal exponential bases |
| 2.5 | Sinusoid + exponential convolution |
| 2.6 | Step + delayed step |
| 2.7 | Finite rectangular input + exponential response |
| 2.8 | Truncated geometric impulse response |
| 2.9 | Non-causal/two-sided convolution |
| 2.10 | Step response |
| 2.11 | Finite convolution with arbitrary starting indices |
| 2.12 | Finite convolution with negative-valued samples |
| 2.13 | Tabular convolution |
| 2.14 | Infinite step-sequence convolution |
| 2.15 | Analytical convolution cases |
| 2.16 | Sum-by-column method |
| 2.17 | Sliding-strip method |
| 2.18 | Polynomial multiplication, zero insertion/padding |
| 2.19 | Polynomial convolution |
| 2.20 | Zero insertion shortcut |
| 2.21 | Zero padding shortcut |
| 2.22 | Deconvolution using $z$-domain division |
| 2.23 | Recursive deconvolution |
| 2.24 | Tabular deconvolution |
| 2.25 | Find $h(n)$ by deconvolution |
| 2.26 | Parallel/cascade LTI interconnection |
| 2.27 | Circular shifting and flipping |
| 2.28 | Circular convolution, unequal lengths |
| 2.29 | Circular convolution, graphical |
| 2.30 | Circular convolution, matrix |
| 2.31 | 8-point circular convolution |
| 2.32 | Circular convolution, tabular |
| 2.33 | All circular-convolution methods |
| 2.34 | Linear vs circular convolution |
| 2.35 | Linear vs circular convolution |
| 2.36 | LTI response by linear/circular convolution |
| 2.37 | Same using 7-point circular convolution |
| 2.38 | Linear convolution from circular convolution |
| 2.39 | Zero-padding requirement |
| 2.40 | Periodic convolution from linear convolution |
| 2.41 | Periodic extension |
| 2.42 | Periodic-input system response |
| 2.43 | Periodic-input system response |
| 2.44 | Three approaches for periodic response |
| 2.45 | Periodic response with longer $h(n)$ |
| 2.46 | Cross correlation |
| 2.47 | Autocorrelation |
| 2.48 | $R_{xy}(n)$ and $R_{yx}(n)$ |
| 2.49 | Autocorrelation symmetry |
| 2.50 | Cross correlation of exponentials |
| 2.51 | Autocorrelation of $a^nu(n)$ |
| 2.52 | Periodic cross correlation |
| 2.53 | Periodic autocorrelation |

---

# 98. Final exam checklist

Before submitting a convolution answer, verify:

$$
\boxed{ \begin{aligned} &\text{1. Starting index correct}\\ &\text{2. Output length correct}\\ &\text{3. Arrow / }n=0\text{ location correct}\\ &\text{4. Flip/shift direction correct}\\ &\text{5. Every overlap multiplied correctly}\\ &\text{6. Circular calculations use modulo }N\\ &\text{7. Correlation includes a flip}\\ &\text{8. Final sequence has the correct number of samples}\\ &\text{9. Finite convolution passes the sum check}\\ &\text{10. Autocorrelation is even symmetric} \end{aligned} }
$$

---

## Source boundary

This note is based on **Chapter 2, printed pages 90–178** of *Digital Signal Processing* by A. Anand Kumar. The chapter itself identifies these pages as Chapter 2 and lists its sections, end-of-chapter questions, objective questions, and problems. The source also explicitly presents the convolution-sum derivation, six linear-convolution methods, deconvolution, LTI interconnections, circular convolution, periodic extension, and correlation topics covered above.


---

# 99. Formatting / alignment key for handwritten exams

## A. Use one equality chain per calculation

Prefer

$$
\begin{aligned} y(n) &=\sum_{k=0}^{n}x(k)h(n-k)\\ &=\sum_{k=0}^{n}b^k a^{n-k}\\ &=a^n\sum_{k=0}^{n}\left(\frac{b}{a}\right)^k\\ &=a^n\frac{1-(b/a)^{n+1}}{1-b/a}. \end{aligned}
$$

This keeps the **equal signs vertically aligned** and makes the logic of every step visible.

## B. State the reason before changing limits

For example,

$$
x(k)=0,\ k<0
$$

and

$$
h(n-k)=0,\ n-k<0 \;\Rightarrow\; k\le n.
$$

Therefore,

$$
\boxed{ y(n)=\sum_{k=0}^{n}x(k)h(n-k) }
$$

Do not jump directly from the infinite sum to the finite sum.

## C. For finite sequences always state three things

$$
\boxed{ \begin{aligned} L_y&=L_x+L_h-1,\\[2mm] n_{y,\mathrm{start}}&=n_{x,\mathrm{start}}+n_{h,\mathrm{start}},\\[2mm] n_{y,\mathrm{end}}&=n_{x,\mathrm{end}}+n_{h,\mathrm{end}}. \end{aligned} }
$$

This prevents most indexing mistakes.

## D. For circular convolution always show the modulo operation

$$
\boxed{ y_p(n)= \sum_{k=0}^{N-1} x(k)\, h\!\left[(n-k)\bmod N\right] }
$$

This makes it immediately clear why samples wrap around.

## E. For correlation show the fold explicitly

$$
\boxed{ R_{xy}(n) = x(n)\ast y(-n) }
$$

and for autocorrelation,

$$
\boxed{ R_{xx}(n) = x(n)\ast x(-n) }
$$

---

# 100. Final master-memory page

$$
\boxed{ \begin{aligned} \text{Linear convolution:}\quad &y(n)=\sum_kx(k)h(n-k)\\[1mm] \text{Finite length:}\quad &L_y=L_x+L_h-1\\[1mm] \text{Start index:}\quad &n_{y,0}=n_{x,0}+n_{h,0}\\[1mm] \text{Step response:}\quad &s(n)=h(n)\ast u(n)\\[1mm] \text{Circular convolution:}\quad &y_p(n)=\sum_{k=0}^{N-1}x(k)h[(n-k)\bmod N]\\[1mm] \text{Periodic extension:}\quad &x_p(n)=\sum_{m=-\infty}^{\infty}x(n+mN)\\[1mm] \text{Cross correlation:}\quad &R_{xy}(n)=x(n)\ast y(-n)\\[1mm] \text{Autocorrelation:}\quad &R_{xx}(n)=x(n)\ast x(-n)\\[1mm] \text{Correlation symmetry:}\quad &R_{xy}(n)=R_{yx}(-n)\\[1mm] \text{Autocorrelation symmetry:}\quad &R_{xx}(n)=R_{xx}(-n) \end{aligned} }
$$

> **Exam habit:** write the governing equation first, determine the index/overlap limits second, substitute third, simplify last. This produces clean, checkable solutions.
