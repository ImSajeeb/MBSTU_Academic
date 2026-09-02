---
title: "Chapter 1 — Discrete-Time Signals and Systems"
source: "Digital Signal Processing — A. Anand Kumar"
printed_pages: "1–89"
style: "Full theory + worked examples + exam preparation + MATLAB"
---

# CHAPTER 1 — DISCRETE-TIME SIGNALS AND SYSTEMS

> **Textbook:** A. Anand Kumar, *Digital Signal Processing*  
> **Chapter:** 1 — *Discrete-Time Signals and Systems*  
> **Printed pages:** 1–89
>
> This guide follows the organization, terminology, definitions, properties, examples, examination questions and MATLAB material of the textbook. The chapter covers discrete-time signals, standard sequences, operations on sequences, signal classification, system classification and representation of arbitrary sequences.

---

# 1. CHAPTER ROADMAP

## 1.1 Introduction

## 1.2 Representation of Discrete-time Signals
- Graphical representation
- Functional representation
- Tabular representation
- Sequence representation

## 1.3 Elementary Discrete-time Signals
- Unit step
- Unit ramp
- Unit parabolic
- Unit impulse / unit sample
- Sinusoidal sequence
- Real exponential
- Complex exponential

## 1.4 Basic Operations on Sequences
- Time shifting
- Time reversal
- Amplitude scaling
- Time scaling
- Signal addition
- Signal multiplication

## 1.5 Classification of Discrete-time Signals
- Deterministic and random
- Periodic and non-periodic
- Energy and power
- Causal and non-causal
- Even and odd

## 1.6 Classification of Discrete-time Systems
- Static and dynamic
- Causal and non-causal
- Linear and non-linear
- Shift-invariant and shift-varying
- Stable and unstable
- FIR and IIR
- Invertible and non-invertible

## 1.7 Representation of an Arbitrary Sequence

Then:
- Short questions with answers
- Review questions
- Fill in the blanks
- Objective questions
- Problems
- MATLAB programs

---

# 2. INTRODUCTION

A **signal** is anything that carries information.

The textbook defines a signal as:

> a single-valued function of one or more independent variables which contains some information.

A signal may also be regarded as a physical quantity that varies with:

- time,
- space,
- temperature,
- position,
- pressure,
- distance,
- or another independent variable.

Examples:

- speech;
- voltage;
- current;
- temperature;
- pressure.

A signal may be represented in:

$$
\boxed{\text{time domain}}
$$

or

$$
\boxed{\text{frequency domain}}.
$$

---

# 3. ONE-DIMENSIONAL AND TWO-DIMENSIONAL SIGNALS

If a signal depends on only one independent variable, it is a

$$
\boxed{\text{one-dimensional signal}}.
$$

If it depends on two independent variables, it is a

$$
\boxed{\text{two-dimensional signal}}.
$$

In this book, the discussion is restricted mainly to:

$$
\boxed{
\text{discrete one-dimensional signals}
}
$$

and

$$
\boxed{
\text{single-input, single-output discrete-time systems}.
}
$$

---

# 4. WHAT IS A SYSTEM?

A **system** is an entity that acts on an input signal and transforms it into an output signal.

The basic relation is

$$
\boxed{
x(n)\longrightarrow
\boxed{\text{SYSTEM}}
\longrightarrow y(n)
}
$$

where:

- $x(n)$ = input;
- $y(n)$ = output.

A system can also be considered a set of connected elements or fundamental blocks that produces an output in response to an input.

Systems may be:

- single-input single-output;
- multi-input multi-output.

---

# 5. WHAT IS SIGNAL PROCESSING?

Signal processing is the process of extracting information from a signal.

It involves:

1. representing signals mathematically;
2. carrying out operations on the signals;
3. extracting useful information.

Digital signal processing performs these operations in the digital domain.

---

# 6. ADVANTAGES OF DIGITAL SIGNAL PROCESSING

The textbook emphasizes the following advantages.

### 6.1 Less sensitivity to component variations

Digital circuits are less sensitive to:

- component-value changes;
- temperature;
- ageing;
- other external parameters.

### 6.2 Adjustable accuracy

Digital signals and coefficients are represented by binary words.

Increasing the number of bits increases the available numerical accuracy.

$$
\boxed{
\text{more bits}\Rightarrow\text{higher possible precision}
}
$$

### 6.3 Time sharing

One digital processor can be shared by several signals using time sharing.

### 6.4 Easy adjustment

Processor characteristics can be changed easily during processing.

### 6.5 Linear phase

The textbook states that linear-phase characteristics can be achieved only with digital filters.

### 6.6 Multirate processing

Multirate processing is possible in the digital domain.

### 6.7 Cascading

Digital circuits can be connected in cascade without the loading problems associated with analog circuits.

### 6.8 Storage

Digital data can be stored easily on storage media without the same deterioration associated with stored analog signals.

### 6.9 Low-frequency signals

Digital processing is particularly suitable for very low-frequency signals such as seismic signals.

---

# 7. LIMITATIONS OF DIGITAL SIGNAL PROCESSING

Digital processing also has disadvantages.

1. A/D and D/A converters may be required.
2. Reconstruction filters may be required.
3. Digital techniques have frequency limitations.
4. Digital systems use active devices and therefore consume power.
5. Active devices are less reliable than passive components.

Even with these limitations, the textbook emphasizes that the advantages of DSP outweigh the disadvantages in many applications.

---

# 8. APPLICATIONS OF DSP

Applications mentioned in the textbook include:

- speech processing;
- communication;
- biomedical applications;
- consumer electronics;
- seismology;
- image processing.

---

# 9. DISCRETE-TIME SIGNALS

A discrete-time signal is defined only at **discrete instants of time**.

The independent variable is an integer

$$
\boxed{n}
$$

and the signal is represented as

$$
\boxed{x(n)}.
$$

Between two sampling instants, the amplitude of a discrete-time signal is not defined.

This is an important distinction from a continuous-time signal.

---

# 10. FOUR REPRESENTATIONS OF DISCRETE-TIME SIGNALS

The textbook gives four representations:

$$
\boxed{
\begin{array}{ll}
1.&\text{Graphical representation}\\
2.&\text{Functional representation}\\
3.&\text{Tabular representation}\\
4.&\text{Sequence representation}
\end{array}
}
$$

---

# 11. GRAPHICAL REPRESENTATION

In graphical representation:

- horizontal axis represents $n$;
- vertical axis represents $x(n)$.

Discrete-time signals are usually represented using stems because the signal exists only at integer sampling instants.

For example:

$$
x(-2)=3,\quad
x(-1)=2,\quad
x(0)=0,\quad
x(1)=3,\quad
x(2)=1,\quad
x(3)=2.
$$

Each value is plotted at its corresponding integer $n$.

---

# 12. FUNCTIONAL REPRESENTATION

A discrete-time signal can be described by a function.

For example,

$$
\boxed{
x(n)=2^n u(n)
}
$$

means

$$
x(n)=
\begin{cases}
2^n, & n\ge0,\\
0, & n<0.
\end{cases}
$$

A piecewise function may also be used:

$$
x(n)=
\begin{cases}
3, & n=-2,\\
2, & n=-1,\\
0, & n=0,\\
3, & n=1,\\
1, & n=2,\\
2, & n=3.
\end{cases}
$$

---

# 13. TABULAR REPRESENTATION

A table explicitly lists $n$ and $x(n)$.

For example:

| $n$ | $-2$ | $-1$ | $0$ | $1$ | $2$ | $3$ |
|---:|---:|---:|---:|---:|---:|---:|
| $x(n)$ | 3 | 2 | 0 | 3 | 1 | 2 |

---

# 14. SEQUENCE REPRESENTATION

A finite sequence can be written directly:

$$
\boxed{
x(n)=\{3,2,0,3,1,2\}.
}
$$

An arrow is used in the textbook to identify the $n=0$ term.

When no arrow is shown, the first term is taken as the $n=0$ term.

---

# 15. BASIC SEQUENCE OPERATIONS

For two sequences

$$
x_1(n),\qquad x_2(n),
$$

### Addition

$$
\boxed{
x(n)=x_1(n)+x_2(n)
}
$$

is obtained by adding corresponding samples.

### Multiplication

$$
\boxed{
x(n)=x_1(n)x_2(n)
}
$$

is obtained by multiplying corresponding samples.

### Constant multiplication

$$
\boxed{
y(n)=kx(n)
}
$$

multiplies every sample by $k$.

---

# 16. ELEMENTARY DISCRETE-TIME SIGNALS

The textbook identifies seven standard signals:

1. Unit step sequence
2. Unit ramp sequence
3. Unit parabolic sequence
4. Unit impulse/unit sample sequence
5. Sinusoidal sequence
6. Real exponential sequence
7. Complex exponential sequence

These signals are building blocks for more complicated signals.

---

# 17. UNIT STEP SEQUENCE

The unit-step sequence is denoted by

$$
\boxed{u(n)}.
$$

Definition:

$$
\boxed{
u(n)=
\begin{cases}
1,&n\ge0,\\
0,&n<0.
\end{cases}
}
$$

It exists from $n=0$ onward.

The step is extremely useful for making a sequence causal or right-sided.

For example,

$$
x(n)=a^n u(n)
$$

is zero for $n<0$.

---

# 18. SHIFTED UNIT STEP

The shifted unit step is

$$
\boxed{u(n-k)}.
$$

It is defined as

$$
\boxed{
u(n-k)=
\begin{cases}
1,&n\ge k,\\
0,&n<k.
\end{cases}
}
$$

Therefore:

- $u(n-3)$ starts at $n=3$;
- $u(n+3)$ starts at $n=-3$.

The easiest way to remember:

$$
\boxed{
u(n-k)\Rightarrow\text{shift right by }k
}
$$

for positive $k$.

---

# 19. UNIT RAMP SEQUENCE

The unit-ramp sequence is

$$
\boxed{r(n)}.
$$

Definition:

$$
\boxed{
r(n)=
\begin{cases}
n,&n\ge0,\\
0,&n<0.
\end{cases}
}
$$

Therefore,

$$
\boxed{
r(n)=nu(n).
}
$$

The shifted ramp is

$$
\boxed{
r(n-k)=(n-k)u(n-k).
}
$$

It starts at $n=k$ and then increases linearly.

---

# 20. UNIT PARABOLIC SEQUENCE

The unit parabolic sequence is

$$
\boxed{p(n)}.
$$

Definition:

$$
\boxed{
p(n)=
\begin{cases}
\dfrac{n^2}{2},&n\ge0,\\
0,&n<0.
\end{cases}
}
$$

Therefore,

$$
\boxed{
p(n)=\frac{n^2}{2}u(n).
}
$$

The shifted form is

$$
\boxed{
p(n-k)=
\frac{(n-k)^2}{2}u(n-k).
}
$$

---

# 21. UNIT IMPULSE / UNIT SAMPLE SEQUENCE

The unit impulse is denoted by

$$
\boxed{\delta(n)}
$$

and is also called the unit sample sequence.

Definition:

$$
\boxed{
\delta(n)=
\begin{cases}
1,&n=0,\\
0,&n\ne0.
\end{cases}
}
$$

It is zero everywhere except at $n=0$.

---

# 22. SHIFTED UNIT IMPULSE

The shifted impulse is

$$
\boxed{\delta(n-k)}.
$$

Definition:

$$
\boxed{
\delta(n-k)=
\begin{cases}
1,&n=k,\\
0,&n\ne k.
\end{cases}
}
$$

Therefore:

$$
\boxed{
\delta(n-k)\text{ occurs at }n=k.
}
$$

---

# 23. IMPORTANT IMPULSE PROPERTIES

### Property 1 — Step relation

$$
\boxed{
\delta(n)=u(n)-u(n-1).
}
$$

### Property 2 — Shifted impulse

$$
\boxed{
\delta(n-k)=
\begin{cases}
1,&n=k,\\
0,&n\ne k.
\end{cases}
}
$$

### Property 3 — Sifting/representation property

Any sequence can be represented using shifted impulses:

$$
\boxed{
x(n)=
\sum_{k=-\infty}^{\infty}
x(k)\delta(n-k).
}
$$

### Property 4 — Sifting property

$$
\boxed{
\sum_{n=-\infty}^{\infty}
x(n)\delta(n-n_0)
=
x(n_0).
}
$$

These properties are fundamental throughout DSP.

---

# 24. RELATION BETWEEN UNIT STEP AND UNIT SAMPLE

The textbook gives

$$
\boxed{
u(n)=
\sum_{m=0}^{n}\delta(m)
}
$$

in the appropriate discrete-time interpretation, and

$$
\boxed{
\delta(n)=u(n)-u(n-1).
}
$$

Thus the impulse can be regarded as the first difference of the step.

---

# 25. SINUSOIDAL SEQUENCE

A discrete-time sinusoidal sequence is

$$
\boxed{
x(n)=A\sin(\omega n+\phi)
}
$$

where:

- $A$ = amplitude;
- $\omega$ = angular frequency in rad/sample;
- $\phi$ = phase angle;
- $n$ = integer.

A cosine sequence may similarly be written as

$$
\boxed{
x(n)=A\cos(\omega n+\phi).
}
$$

---

# 26. PERIOD OF A DISCRETE-TIME SINUSOID

A discrete-time sinusoid is not necessarily periodic.

For periodicity, there must exist a positive integer $N$ such that

$$
\boxed{
x(n+N)=x(n)
}
$$

for every integer $n$.

For

$$
x(n)=A\sin(\omega_0n+\phi),
$$

the condition is

$$
\boxed{
\omega_0N=2\pi m
}
$$

where $m$ and $N$ are integers.

Therefore,

$$
\boxed{
\frac{\omega_0}{2\pi}
=
\frac{m}{N}
}
$$

must be rational.

This is one of the most important facts in Chapter 1.

---

# 27. FUNDAMENTAL PERIOD OF A SINUSOID

If

$$
\frac{\omega_0}{2\pi}
=
\frac{m}{N}
$$

and $m,N$ are relatively prime, then

$$
\boxed{
N_0=N
}
$$

is the fundamental period.

Equivalent form:

$$
\boxed{
N_0=
\frac{2\pi m}{\omega_0}
}
$$

with the smallest positive integer satisfying the periodicity condition.

---

# 28. REAL EXPONENTIAL SEQUENCE

The discrete-time real exponential is

$$
\boxed{
x(n)=a^n.
}
$$

Behavior depends on $a$.

### Case 1: $a>1$

The sequence grows exponentially.

### Case 2: $0<a<1$

The sequence decays exponentially.

### Case 3: $a<-1$

Magnitude grows while signs alternate.

### Case 4: $-1<a<0$

Magnitude decays while signs alternate.

---

# 29. COMPLEX EXPONENTIAL SEQUENCE

The complex exponential is

$$
\boxed{
x(n)=a^n e^{j(\omega_0n+\phi)}.
}
$$

Using Euler's identity,

$$
e^{j\theta}=\cos\theta+j\sin\theta,
$$

we obtain

$$
\boxed{
x(n)
=
a^n\cos(\omega_0n+\phi)
+
j\,a^n\sin(\omega_0n+\phi).
}
$$

For

$$
|a|=1,
$$

the real and imaginary parts are sinusoidal.

For

$$
|a|>1,
$$

the amplitude grows exponentially.

For

$$
|a|<1,
$$

the amplitude decays exponentially.

---

# 30. EXAMPLE 1.1 — SUMMATIONS INVOLVING IMPULSE SEQUENCES

The textbook asks sums such as

$$
\sum_{n=-\infty}^{\infty}
e^{3n}\delta(n-3)
$$

and similar expressions involving

$$
\delta(n-k),\qquad
\cos(3n),\qquad
n^2.
$$

Use the sifting property:

$$
\boxed{
\sum_{n=-\infty}^{\infty}
f(n)\delta(n-k)
=
f(k).
}
$$

Therefore,

$$
\sum_{n=-\infty}^{\infty}
e^{3n}\delta(n-3)
=
e^9.
$$

Similarly,

$$
\sum_{n=-\infty}^{\infty}
\delta(n-2)\cos(3n)
=
\boxed{\cos6}.
$$

The key exam technique is:

> **Find the location of the impulse and substitute that value of $n$ into the remaining expression.**

---

# 31. BASIC OPERATIONS ON SEQUENCES

The textbook gives six operations:

$$
\boxed{
\begin{array}{ll}
1.&\text{Time shifting}\\
2.&\text{Time reversal}\\
3.&\text{Time scaling}\\
4.&\text{Amplitude scaling}\\
5.&\text{Signal addition}\\
6.&\text{Signal multiplication}
\end{array}
}
$$

The first three manipulate the independent variable.

The last three manipulate amplitudes.

---

# 32. TIME SHIFTING

For

$$
\boxed{
y(n)=x(n-k)
}
$$

if

$$
k>0,
$$

the sequence is delayed by $k$ samples.

$$
\boxed{
x(n-k)\Rightarrow\text{right shift}
}
$$

If

$$
k<0,
$$

the sequence is advanced.

For example,

$$
x(n-3)
$$

is a delay of 3 samples.

And

$$
x(n+2)
$$

is an advance of 2 samples.

---

# 33. TIME REVERSAL

Time reversal is also called **time folding**.

Replace

$$
n\rightarrow -n.
$$

Thus,

$$
\boxed{
y(n)=x(-n).
}
$$

The sequence is reflected about

$$
\boxed{n=0}.
$$

Examples:

$$
x(-n+3)
$$

and

$$
x(-n-3)
$$

are shifted versions of the reversed sequence.

### Exam procedure

1. Draw $x(n)$.
2. Fold it about $n=0$ to obtain $x(-n)$.
3. Shift the reversed sequence if required.

---

# 34. EXAMPLE 1.2 — SKETCHING SIGNALS

### (a)

$$
\boxed{
x(n)=u(n+2)u(-n+3)
}
$$

The first condition:

$$
u(n+2)=1
$$

for

$$
n\ge-2.
$$

The second:

$$
u(-n+3)=1
$$

for

$$
n\le3.
$$

Therefore both are 1 over the common interval:

$$
\boxed{
-2\le n\le3.
}
$$

Hence

$$
x(n)=
\begin{cases}
1,&-2\le n\le3,\\
0,&\text{otherwise}.
\end{cases}
$$

### (b)

$$
\boxed{
x(n)=u(n+4)-u(n-2)
}
$$

The first step turns on at $-4$, while the second turns on at $2$.

Therefore:

$$
\boxed{
x(n)=1,\qquad -4\le n\le1.
}
$$

This is a finite-duration rectangular sequence.

---

# 35. AMPLITUDE SCALING

Amplitude scaling is

$$
\boxed{
y(n)=ax(n).
}
$$

If

$$
a>1,
$$

it is amplification.

If

$$
0<a<1,
$$

it is attenuation.

If

$$
a<0,
$$

the sequence also undergoes sign reversal.

---

# 36. TIME SCALING

Time scaling is written as

$$
\boxed{
y(n)=x(an).
}
$$

For integer $a>1$, the signal is compressed.

For example:

$$
\boxed{
x(2n)
}
$$

keeps the even-index samples of $x(n)$ and compresses the sequence by 2.

---

# 37. TIME EXPANSION

For

$$
\boxed{
y(n)=x(n/2)
}
$$

the signal is expanded by 2.

Only integer values of $n$ for which $n/2$ is an integer correspond to original samples.

The textbook places zeros at the intermediate positions.

Thus, for an expansion by 2:

$$
\boxed{
x(n/2)=0
}
$$

at the odd indices when the original sequence is defined only at integer samples.

---

# 38. EXAMPLE OF TIME SCALING

Suppose

$$
x(0)=1,\quad x(1)=2,\quad x(2)=3,\quad x(3)=4.
$$

For

$$
y(n)=x(2n),
$$

we obtain

$$
y(0)=x(0)=1
$$

$$
y(1)=x(2)=3
$$

$$
y(2)=x(4)
$$

and so on.

Thus odd-indexed original samples are skipped.

For

$$
y(n)=x(n/2),
$$

the samples are stretched and zeros occur between the retained samples.

---

# 39. SIGNAL ADDITION

For

$$
x_1(n)=\{1,2,3,1,5\}
$$

and

$$
x_2(n)=\{2,3,4,1,-2\},
$$

the sum is

$$
\boxed{
x_1(n)+x_2(n)
=
\{3,5,7,2,3\}.
}
$$

The subtraction is

$$
\boxed{
x_1(n)-x_2(n)
=
\{-1,-1,-1,0,7\}.
}
$$

---

# 40. SIGNAL MULTIPLICATION

For

$$
x_1(n)=\{1,-3,2,4,1.5\}
$$

and

$$
x_2(n)=\{2,-1,3,1.5,2\},
$$

multiply corresponding samples:

$$
\boxed{
x_1(n)x_2(n)
=
\{2,3,6,6,3\}.
}
$$

---

# 41. EXAMPLE 1.3 — REPRESENTING SIGNALS USING SINGULAR FUNCTIONS

A finite rectangular sequence can be represented using shifted impulses.

For a signal equal to 1 over

$$
-2\le n\le1,
$$

the textbook writes

$$
\boxed{
x(n)=
\delta(n+2)+\delta(n+1)+\delta(n)+\delta(n-1).
}
$$

The same signal can be represented using steps:

$$
\boxed{
x(n)=u(n+2)-u(n-2).
}
$$

Similarly, a signal equal to 1 from

$$
n=2
$$

through

$$
n=5
$$

is

$$
\boxed{
x(n)=
\delta(n-2)+\delta(n-3)+\delta(n-4)+\delta(n-5)
}
$$

or

$$
\boxed{
x(n)=u(n-2)-u(n-6).
}
$$

---

# 42. CLASSIFICATION OF DISCRETE-TIME SIGNALS

The textbook classifies signals as:

1. deterministic / random;
2. periodic / non-periodic;
3. energy / power;
4. causal / non-causal;
5. even / odd.

---

# 43. DETERMINISTIC SIGNAL

A deterministic signal is completely specified for every value of the independent variable.

There is

$$
\boxed{\text{no uncertainty}}
$$

about its values.

Example:

$$
x(n)=\cos(0.2\pi n).
$$

---

# 44. RANDOM SIGNAL

A random signal contains uncertainty.

Its exact value cannot be predicted deterministically for every instant.

Examples include noise-like signals.

Therefore:

$$
\boxed{
\text{deterministic}\Rightarrow\text{no uncertainty}
}
$$

$$
\boxed{
\text{random}\Rightarrow\text{uncertainty}
}
$$

---

# 45. PERIODIC SIGNAL

A sequence is periodic if

$$
\boxed{
x(n+N)=x(n)
}
$$

for every integer $n$, where $N$ is a positive integer.

The smallest positive value of $N$ satisfying this condition is the

$$
\boxed{
\text{fundamental period}.
}
$$

---

# 46. PERIODICITY OF A COMPLEX EXPONENTIAL

For

$$
\boxed{
x(n)=e^{j\omega_0n},
}
$$

periodicity requires

$$
e^{j\omega_0(n+N)}
=
e^{j\omega_0n}.
$$

Hence

$$
e^{j\omega_0N}=1.
$$

Therefore,

$$
\boxed{
\omega_0N=2\pi m.
}
$$

Thus

$$
\boxed{
\frac{\omega_0}{2\pi}
=
\frac{m}{N}
}
$$

must be rational.

---

# 47. EXAMPLE 1.4 — PERIODICITY OF COMPLEX EXPONENTIAL

For

$$
x(n)=e^{j\omega_0n},
$$

require

$$
x(n+N)=x(n).
$$

Then

$$
e^{j\omega_0(n+N)}
=
e^{j\omega_0n}
$$

and therefore

$$
e^{j\omega_0N}=1.
$$

Hence

$$
\boxed{
\omega_0N=2\pi m.
}
$$

So the complex exponential is periodic only when

$$
\boxed{
\frac{\omega_0}{2\pi}
}
$$

is rational.

---

# 48. EXAMPLE 1.5 — CONTINUOUS VS DISCRETE COMPLEX EXPONENTIAL

For the continuous-time signal

$$
x(t)=e^{j\omega_0t},
$$

a period always exists:

$$
\boxed{
T_0=\frac{2\pi}{\omega_0}.
}
$$

For the discrete-time signal

$$
x(n)=e^{j\omega_0n},
$$

a positive integer period exists only if

$$
\boxed{
\frac{\omega_0}{2\pi}
}
$$

is rational.

This is a fundamental difference between continuous-time and discrete-time sinusoids.

---

# 49. EXAMPLE 1.6 — CONDITION FOR A DISCRETE-TIME SINUSOID TO BE PERIODIC

Given

$$
x(n)=A\sin(\omega_0n+\phi),
$$

periodicity requires

$$
A\sin[\omega_0(n+N)+\phi]
=
A\sin(\omega_0n+\phi).
$$

Hence

$$
\boxed{
\omega_0N=2\pi m.
}
$$

Therefore:

$$
\boxed{
\frac{\omega_0}{2\pi}
\text{ must be rational}.
}
$$

---

# 50. EXAMPLE 1.7 — TESTING PERIODICITY

### Example: $x(n)=\cos(4n)$

We require

$$
\frac{4}{2\pi}
=
\frac2\pi.
$$

Since this is irrational,

$$
\boxed{
x(n)=\cos(4n)
\text{ is non-periodic}.
}
$$

### Example: $x(n)=\cos(\pi n/2)$

$$
\omega_0=\frac{\pi}{2}.
$$

Thus

$$
\frac{\omega_0}{2\pi}
=
\frac14
$$

which is rational.

Hence

$$
\boxed{
N_0=4.
}
$$

### Example: $x(n)=\sin(2\pi n/3)+\cos(2\pi n/5)$

Individual periods:

$$
N_1=3,
\qquad
N_2=5.
$$

Therefore the sum is periodic with

$$
\boxed{
N_0=\operatorname{LCM}(3,5)=15.
}
$$

### Important rule

For a sum of periodic sequences,

$$
\boxed{
N_0=\operatorname{LCM}(N_1,N_2,\ldots)
}
$$

provided the component sequences are periodic.

---

# 51. PRODUCT OF PERIODIC AND NON-PERIODIC SIGNALS

If one component is non-periodic, the product is generally non-periodic.

For example,

$$
x(n)=
\cos(n/6)\cos(\pi n/6)
$$

contains a non-periodic component

$$
\cos(n/6),
$$

so the overall signal is non-periodic.

---

# 52. ENERGY OF A DISCRETE-TIME SIGNAL

The total energy is

$$
\boxed{
E=
\sum_{n=-\infty}^{\infty}
|x(n)|^2.
}
$$

For a real signal,

$$
\boxed{
E=
\sum_{n=-\infty}^{\infty}
x^2(n).
}
$$

A signal is an energy signal if

$$
\boxed{
0<E<\infty.
}
$$

For an energy signal,

$$
\boxed{
P=0.
}
$$

---

# 53. AVERAGE POWER

The average power is

$$
\boxed{
P=
\lim_{N\to\infty}
\frac1{2N+1}
\sum_{n=-N}^{N}
|x(n)|^2.
}
$$

For a right-sided signal beginning at $n=0$, an equivalent one-sided average can be used.

A signal is a power signal if

$$
\boxed{
0<P<\infty.
}
$$

For a power signal,

$$
\boxed{
E=\infty.
}
$$

---

# 54. ENERGY-SIGNAL CONDITIONS

A signal is an energy signal when

$$
\boxed{
0<E<\infty.
}
$$

Typical examples:

- finite-duration sequences;
- decaying exponentials with suitable decay.

A periodic nonzero signal cannot be an energy signal because its energy over infinite time is infinite.

---

# 55. POWER-SIGNAL CONDITIONS

A signal is a power signal when

$$
\boxed{
0<P<\infty.
}
$$

Typical examples:

- nonzero periodic signals;
- bounded periodic signals.

For a nonzero periodic signal:

$$
\boxed{
E=\infty.
}
$$

---

# 56. ENERGY AND POWER SIGNALS ARE MUTUALLY EXCLUSIVE

A signal cannot simultaneously be both an energy signal and a power signal in the textbook's classification.

$$
\boxed{
\text{Energy signal}\Rightarrow P=0
}
$$

$$
\boxed{
\text{Power signal}\Rightarrow E=\infty
}
$$

Signals such as

$$
u(n),\qquad
nu(n),\qquad
n^2u(n)
$$

are neither energy nor power signals because both relevant quantities fail to be finite in the required way.

---

# 57. EXAMPLE 1.8 — CLASSIFYING ENERGY AND POWER SIGNALS

The textbook considers signals such as

$$
\left(\frac12\right)^n u(n),
$$

$$
e^{j[(\pi/3)n+\pi/2]},
$$

$$
\sin\left(\frac{\pi}{3}n\right),
$$

$$
u(n)-u(n-6),
$$

$$
nu(n),
$$

and

$$
r(n)-r(n-4).
$$

### Key classifications

#### (a)

$$
x(n)=\left(\frac12\right)^n u(n)
$$

is an energy signal because

$$
\sum_{n=0}^{\infty}
\left(\frac12\right)^{2n}
$$

converges.

#### (b)

$$
x(n)=e^{j[(\pi/3)n+\pi/2]}
$$

has constant magnitude 1 and is periodic, so it is a power signal.

#### (c)

$$
x(n)=\sin(\pi n/3)
$$

is periodic and therefore a power signal.

#### (d)

$$
x(n)=u(n)-u(n-6)
$$

is finite duration, so it is an energy signal.

#### (e)

$$
x(n)=nu(n)
$$

has unbounded/infinite energy and is neither energy nor power.

#### (f)

$$
x(n)=r(n)-r(n-4)
$$

is finite duration and hence is an energy signal.

---

# 58. CAUSAL SIGNAL

A discrete-time signal is causal if

$$
\boxed{
x(n)=0,\qquad n<0.
}
$$

Thus a causal signal does not exist before $n=0$.

Example:

$$
\boxed{
u(n)
}
$$

is causal.

---

# 59. ANTI-CAUSAL SIGNAL

A signal is anti-causal if

$$
\boxed{
x(n)=0,\qquad n>0.
}
$$

Example:

$$
\boxed{
u(-n)
}
$$

is anti-causal.

---

# 60. NON-CAUSAL SIGNAL

A signal that exists at both positive and negative indices is non-causal.

For example,

$$
x(n)=1,\qquad -2\le n\le3
$$

is non-causal.

---

# 61. EXAMPLE 1.10 — CAUSALITY OF SIGNALS

Consider

$$
x(n)=u(n+4)-u(n-2).
$$

This sequence exists from

$$
n=-4
$$

to

$$
n=1.
$$

Because it has nonzero samples for negative $n$,

$$
\boxed{
x(n)\text{ is non-causal}.
}
$$

For

$$
x(n)=u(-n),
$$

the signal exists only for

$$
n\le0,
$$

so it is

$$
\boxed{\text{anti-causal}}.
$$

---

# 62. EVEN SIGNAL

A discrete-time signal is even if

$$
\boxed{
x(n)=x(-n).
}
$$

Its waveform is symmetric about

$$
\boxed{n=0}.
$$

---

# 63. ODD SIGNAL

A discrete-time signal is odd if

$$
\boxed{
x(n)=-x(-n).
}
$$

For an odd signal,

$$
\boxed{
x(0)=0.
}
$$

---

# 64. EVEN AND ODD COMPONENTS

Any signal can be decomposed into even and odd parts:

$$
\boxed{
x(n)=x_e(n)+x_o(n).
}
$$

The even component is

$$
\boxed{
x_e(n)=
\frac12[x(n)+x(-n)].
}
$$

The odd component is

$$
\boxed{
x_o(n)=
\frac12[x(n)-x(-n)].
}
$$

These formulas are extremely important for numerical problems.

---

# 65. PROPERTIES OF EVEN AND ODD COMPONENTS

### Even component

$$
x_e(-n)=x_e(n).
$$

### Odd component

$$
x_o(-n)=-x_o(n).
$$

### At the origin

$$
\boxed{
x_o(0)=0.
}
$$

### Reconstruction

$$
\boxed{
x(n)=x_e(n)+x_o(n).
}
$$

---

# 66. EXAMPLE 1.11 — EVEN AND ODD COMPONENTS

For a sequence $x(n)$, form

$$
x(-n)
$$

by reversing the sequence.

Then calculate

$$
\boxed{
x_e(n)=\frac{x(n)+x(-n)}2
}
$$

and

$$
\boxed{
x_o(n)=\frac{x(n)-x(-n)}2.
}
$$

For a numerical sequence, align the $n=0$ sample first, reverse the remaining samples around zero, and then add/subtract corresponding samples.

---

# 67. CLASSIFICATION OF DISCRETE-TIME SYSTEMS

The textbook classifies systems as:

1. static / dynamic;
2. causal / non-causal;
3. linear / non-linear;
4. shift-invariant / shift-varying;
5. stable / unstable;
6. FIR / IIR;
7. invertible / non-invertible.

---

# 68. STATIC SYSTEM

A system is static if the output at the current instant depends only on the current input.

For

$$
\boxed{
y(n)=T[x(n)],
}
$$

if $y(n)$ depends only on $x(n)$, the system is static.

Example:

$$
\boxed{
y(n)=3x(n)
}
$$

is static.

---

# 69. DYNAMIC SYSTEM

A system is dynamic if the output depends on past or future input samples.

Examples:

$$
\boxed{
y(n)=x(n-1)
}
$$

and

$$
\boxed{
y(n)=x(n+2).
}
$$

Both require information other than the present input sample.

### Important shortcut

$$
\boxed{
\text{past/future sample present}
\Rightarrow
\text{dynamic}
}
$$

---

# 70. CAUSAL SYSTEM

A system is causal if the output at $n=n_0$ depends only on:

- present input;
- past inputs.

It must not depend on future inputs.

Thus,

$$
\boxed{
y(n)=F[x(n),x(n-1),x(n-2),\ldots]
}
$$

is causal.

---

# 71. NON-CAUSAL SYSTEM

A system is non-causal if the output depends on future input samples.

For example,

$$
\boxed{
y(n)=x(n+2)
}
$$

is non-causal.

Another example:

$$
\boxed{
y(n)=x(-n)
}
$$

is non-causal because for negative $n$, the output uses positive-index input samples.

---

# 72. LINEAR SYSTEM

A system satisfies the principle of superposition if it is linear.

For inputs

$$
x_1(n)\rightarrow y_1(n)
$$

and

$$
x_2(n)\rightarrow y_2(n),
$$

the system is linear if

$$
\boxed{
ax_1(n)+bx_2(n)
\rightarrow
ay_1(n)+by_2(n)
}
$$

for arbitrary constants $a,b$.

Equivalently:

### Additivity

$$
T[x_1+x_2]=T[x_1]+T[x_2].
$$

### Homogeneity

$$
T[ax]=aT[x].
$$

---

# 73. NON-LINEAR SYSTEM

A system is nonlinear if it violates superposition.

Common signs of nonlinearity:

- powers such as $x^2(n)$;
- products such as $x(n)x(n-1)$;
- logarithms;
- exponentials of $x(n)$;
- constants added to the output.

Examples:

$$
\boxed{
y(n)=x^2(n)
}
$$

$$
\boxed{
y(n)=x(n)x(n-2)
}
$$

$$
\boxed{
y(n)=\log_{10}x(n)
}
$$

are nonlinear.

---

# 74. SHIFT-INVARIANT SYSTEM

A system is shift-invariant if shifting the input causes an identical shift in the output.

If

$$
x(n)\rightarrow y(n),
$$

then for a shift $k$,

$$
x(n-k)\rightarrow y(n-k).
$$

The mathematical test is:

### Step 1

Apply shifted input:

$$
x_s(n)=x(n-k).
$$

Find

$$
y_s(n)=T[x(n-k)].
$$

### Step 2

Shift original output:

$$
y(n-k).
$$

### Step 3

Compare.

If

$$
\boxed{
y_s(n)=y(n-k)
}
$$

the system is shift-invariant.

Otherwise it is shift-varying.

---

# 75. SHIFT-VARYING SYSTEM

A system is shift-varying when

$$
\boxed{
T[x(n-k)]\ne y(n-k).
}
$$

A common cause is an explicit $n$ dependence in the system equation.

Example:

$$
\boxed{
y(n)=n\,x(n)
}
$$

is shift-varying.

---

# 76. EXAMPLE 1.12 — STATIC OR DYNAMIC

### (a)

$$
y(n)=nx^2(n)
$$

depends only on the present sample $x(n)$.

Therefore:

$$
\boxed{\text{static}}.
$$

### (b)

$$
y(n)=x(n)+x(n+2)
$$

uses a future sample.

Therefore:

$$
\boxed{\text{dynamic}}.
$$

### (c)

$$
y(n)=\frac12[x(n)+x(-n)]
$$

requires $x(-n)$.

Therefore:

$$
\boxed{\text{dynamic}}
$$

because for some $n$, it requires a sample other than the present sample.

---

# 77. EXAMPLE 1.13 — CAUSAL OR NON-CAUSAL SYSTEM

### Example

$$
\boxed{
y(n)=x(n)+\frac12x(n-2)
}
$$

uses present and past samples only.

Therefore:

$$
\boxed{\text{causal}}.
$$

### Example

$$
\boxed{
y(n)=x(-2n)
}
$$

for negative $n$, $x(-2n)$ is a future input sample.

Therefore:

$$
\boxed{\text{non-causal}}.
$$

---

# 78. EXAMPLE 1.14 — LINEARITY TEST

Suppose

$$
y(n)=x^2(n).
$$

For two inputs,

$$
x_1(n),\quad x_2(n),
$$

the output for

$$
ax_1(n)+bx_2(n)
$$

is

$$
[ax_1(n)+bx_2(n)]^2.
$$

Expanding,

$$
a^2x_1^2(n)
+
2abx_1(n)x_2(n)
+
b^2x_2^2(n).
$$

This is not generally equal to

$$
ax_1^2(n)+bx_2^2(n).
$$

Therefore:

$$
\boxed{
y(n)=x^2(n)\text{ is nonlinear}.
}
$$

---

# 79. EXAMPLE 1.15 — TIME-INVARIANCE TEST

For

$$
\boxed{
y(n)=x(n)+nx(n-3),
}
$$

the explicit factor $n$ changes under a time shift.

Therefore the system is

$$
\boxed{\text{time-varying / shift-varying}}.
$$

A useful exam shortcut:

> **An explicit $n$ in the system equation is a warning sign for time variance.**

But always perform the formal test when required.

---

# 80. EXAMPLE 1.16 — LINEAR SHIFT-INVARIANT SYSTEMS

Systems such as

$$
\boxed{
y(n)=x(n)+x(n-2)
}
$$

are linear because they contain only linear combinations of input samples.

They are shift-invariant because delaying the input by $k$ delays every corresponding output term by $k$.

They are also causal because they use only present and past samples.

They are dynamic because $x(n-2)$ is present.

---

# 81. EXAMPLE 1.17 — COMPLETE SYSTEM CLASSIFICATION

For a system such as

$$
\boxed{
y(n)=\frac12[x(n)+x(-n)],
}
$$

classify using the four tests.

### Dynamic

It uses $x(-n)$, so memory is required.

$$
\boxed{\text{dynamic}}
$$

### Linear

$$
T[ax_1+bx_2]
=
aT[x_1]+bT[x_2].
$$

Thus:

$$
\boxed{\text{linear}}
$$

### Causality

For negative $n$, $x(-n)$ is a future input.

Thus:

$$
\boxed{\text{non-causal}}
$$

### Time invariance

The textbook's shifted-input test gives

$$
T[x(n-k)]
=
\frac12[x(n-k)+x(-n-k)]
$$

whereas

$$
y(n-k)
=
\frac12[x(n-k)+x(-n+k)].
$$

They are not equal in general.

Therefore:

$$
\boxed{\text{time-varying}}.
$$

---

# 82. BIBO STABILITY

A signal is bounded if its magnitude is always finite:

$$
\boxed{
|x(n)|\le M_x<\infty.
}
$$

A system is **bounded-input bounded-output (BIBO) stable** if every bounded input produces a bounded output.

Thus:

$$
\boxed{
|x(n)|<\infty
\Rightarrow
|y(n)|<\infty.
}
$$

---

# 83. BIBO STABILITY CRITERION FOR LTI SYSTEMS

For an LTI discrete-time system, the necessary and sufficient condition is

$$
\boxed{
\sum_{n=-\infty}^{\infty}
|h(n)|<\infty.
}
$$

This is one of the most important formulas in Chapter 1.

### Memory rule

$$
\boxed{
\text{Absolutely summable }h(n)
\Rightarrow
\text{BIBO stable}.
}
$$

---

# 84. WHY ABSOLUTE SUMMABILITY GIVES STABILITY

For an LTI system,

$$
y(n)=
\sum_{k=-\infty}^{\infty}
x(k)h(n-k).
$$

If

$$
|x(k)|\le M_x,
$$

then

$$
|y(n)|
\le
\sum_{k=-\infty}^{\infty}
|x(k)|\,|h(n-k)|.
$$

Therefore,

$$
|y(n)|
\le
M_x
\sum_{k=-\infty}^{\infty}
|h(n-k)|.
$$

Changing variables,

$$
m=n-k,
$$

gives

$$
|y(n)|
\le
M_x
\sum_{m=-\infty}^{\infty}
|h(m)|.
$$

If

$$
\sum_{m=-\infty}^{\infty}|h(m)|<\infty,
$$

then $y(n)$ is bounded.

Hence the criterion.

---

# 85. STABILITY CONDITIONS FROM TRANSFER FUNCTION

For a rational discrete-time system, the textbook gives:

1. Degree of numerator should not be greater than degree of denominator.
2. Poles must lie inside the unit circle.
3. If a pole lies on the unit circle, it must be a single-order pole; repeated poles on the unit circle are not stable.

The standard stable-pole condition is

$$
\boxed{
|p_i|<1.
}
$$

---

# 86. EXAMPLE 1.18 — STABILITY

### (a)

$$
y(n)=a\,x(n-7).
$$

For impulse input,

$$
h(n)=a\delta(n-7).
$$

Therefore,

$$
\sum |h(n)|=|a|.
$$

Hence the system is stable for finite $a$.

$$
\boxed{\text{stable}}
$$

### (b)

$$
y(n)=x(n)+\frac12x(n-1)+\frac14x(n-2).
$$

Then

$$
h(n)=
\delta(n)
+\frac12\delta(n-1)
+\frac14\delta(n-2).
$$

Therefore,

$$
\sum|h(n)|
=
1+\frac12+\frac14
=
\frac74<\infty.
$$

Hence:

$$
\boxed{\text{stable}}.
$$

### (c)

$$
h(n)=a^n,\qquad0\le n\le11.
$$

The sum is finite because the sequence has finite duration.

Hence the system is stable for finite $a$.

### (d)

$$
h(n)=2^n u(n).
$$

Then

$$
\sum_{n=0}^{\infty}2^n
$$

diverges.

Hence:

$$
\boxed{\text{unstable}}.
$$

### (e)

$$
h(n)=u(n).
$$

Then

$$
\sum_{n=0}^{\infty}1=\infty.
$$

Therefore:

$$
\boxed{\text{unstable}}.
$$

---

# 87. EXAMPLE 1.19 — BIBO STABILITY

The textbook tests systems by examining whether a bounded input can produce an unbounded output.

### Important strategy

If the system is LTI:

$$
\boxed{
\text{find }h(n)
\rightarrow
\sum|h(n)|
\rightarrow
\text{finite?}
}
$$

If finite:

$$
\boxed{\text{stable}}
$$

otherwise:

$$
\boxed{\text{unstable}}.
$$

---

# 88. EXAMPLE 1.20 — IMPULSE RESPONSE TEST

For each given system, obtain the output for

$$
x(n)=\delta(n).
$$

Then

$$
\boxed{
y(n)=h(n).
}
$$

After obtaining $h(n)$, apply

$$
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty.
}
$$

This is the standard textbook procedure.

---

# 89. EXAMPLE 1.21 — COMPLETE SYSTEM COMMENT

For a system, determine:

1. linearity;
2. stability;
3. time invariance;
4. causality.

Do not assume that one property implies another.

For example, a system can be:

$$
\boxed{
\text{linear + stable + non-causal + time-varying}
}
$$

or any other combination allowed by its equation.

Each property must be tested independently.

---

# 90. EXAMPLE 1.22 — FOUR-PROPERTY TEST

For

$$
y(n)=x(n)+x(n-2),
$$

we obtain:

- present/past input only $\Rightarrow$ causal;
- delayed input present $\Rightarrow$ dynamic;
- only first powers and sums $\Rightarrow$ linear;
- delay operation does not depend explicitly on $n$ $\Rightarrow$ shift-invariant;
- impulse response is finite duration $\Rightarrow$ stable.

Therefore:

$$
\boxed{
\text{dynamic, linear, causal, shift-invariant, stable}.
}
$$

---

# 91. EXAMPLE 1.23 — COMPLETE CLASSIFICATION

For a system involving shifted samples, perform the four tests systematically.

### Dynamic

Look for

$$
x(n-k),\quad k\ne0
$$

or future terms.

### Linear

Check whether

$$
T[ax_1+bx_2]
=
aT[x_1]+bT[x_2].
$$

### Causal

Check whether any term has

$$
x(n+k),\quad k>0.
$$

### Time invariant

Compare

$$
T[x(n-k)]
$$

with

$$
y(n-k).
$$

---

# 92. EXAMPLE 1.24 — CLASSIFICATION OF SYSTEMS

For each system, use the four-property checklist.

A system such as

$$
\boxed{
y(n)=x(n)+nx(n-1)
}
$$

is:

- dynamic;
- linear;
- causal;
- shift-varying.

The explicit $n$ causes time variance.

---

# 93. EXAMPLE 1.25 — CAUSALITY AND STABILITY

For a system given by its impulse response:

1. Check whether

$$
h(n)=0,\quad n<0.
$$

If yes, an LTI system is causal.

2. Check

$$
\sum|h(n)|.
$$

If finite, it is BIBO stable.

Thus:

$$
\boxed{
\text{causal LTI}
\iff
h(n)=0\text{ for }n<0
}
$$

and

$$
\boxed{
\text{stable LTI}
\iff
\sum|h(n)|<\infty.
}
$$

---

# 94. EXAMPLE 1.26 — SYSTEM GIVEN BY A DIFFERENCE EQUATION

A system represented by a difference equation should first be rewritten so that the output is isolated.

For example, if

$$
y(n)+ay(n-1)=bx(n),
$$

then

$$
\boxed{
y(n)=bx(n)-ay(n-1)
}
$$

More generally, determine the dependence of $y(n)$ on:

- present input;
- past inputs;
- future inputs;
- past outputs.

Then classify the system.

---

# 95. DIFFERENCE EQUATION — CLASSIFICATION RULE

For a system represented by

$$
y(n)
=
\sum_k b_kx(n-k)
-
\sum_r a_ry(n-r),
$$

the presence of past outputs/inputs generally indicates a dynamic system.

For causality:

$$
\boxed{
\text{present/past terms only}\Rightarrow\text{causal}
}
$$

provided the system is specified in causal form.

---

# 96. EXAMPLE 1.27 — MULTIPLE-PROPERTY TEST

For each system, test in this order:

$$
\boxed{
\begin{array}{c}
\text{Static/Dynamic}\\
\downarrow\\
\text{Linear/Nonlinear}\\
\downarrow\\
\text{Causal/Noncausal}\\
\downarrow\\
\text{Time-invariant/Time-varying}\\
\downarrow\\
\text{Stable/Unstable}
\end{array}
}
$$

This ordering makes lengthy classification problems much easier.

---

# 97. FIR SYSTEM

An FIR system has a finite-duration impulse response.

If

$$
h(n)=0
$$

outside a finite interval, the system is FIR.

For example,

$$
\boxed{
h(n)=
\delta(n)+
2\delta(n-1)+
\delta(n-2)
}
$$

is FIR.

Its response terminates after a finite number of samples.

---

# 98. IIR SYSTEM

An IIR system has an impulse response that theoretically continues indefinitely.

Example:

$$
\boxed{
h(n)=a^n u(n)
}
$$

for

$$
|a|<1.
$$

Even though its amplitude decays, the sequence continues for infinitely many $n$.

Therefore it is IIR.

---

# 99. FIR VS IIR

| FIR | IIR |
|---|---|
| Finite impulse response | Infinite impulse response |
| Usually non-recursive | Usually recursive |
| No feedback required in standard form | Feedback generally used |
| Linear phase can be achieved | Causal stable IIR cannot have exact linear phase |
| Always stable for finite coefficients in the standard finite-length form | Stability must be checked |

---

# 100. INVERTIBLE SYSTEM

A system is invertible if different inputs produce different outputs and an inverse system can recover the original input.

If

$$
\boxed{
y(n)=T[x(n)],
}
$$

and an inverse system $T^{-1}$ exists such that

$$
\boxed{
T^{-1}[y(n)]=x(n),
}
$$

then the system is invertible.

The cascade of the system and its inverse gives the identity system.

$$
\boxed{
T^{-1}\{T[x(n)]\}=x(n).
}
$$

---

# 101. NON-INVERTIBLE SYSTEM

If two or more different inputs can produce the same output, the system is non-invertible.

For example,

$$
\boxed{
y(n)=x^2(n)
}
$$

is generally non-invertible because

$$
x(n)
$$

and

$$
-x(n)
$$

produce the same output.

Thus the input cannot be uniquely recovered.

---

# 102. INVERTIBILITY EXAMPLE

For

$$
\boxed{
y(n)=2x(n),
}
$$

the inverse is

$$
\boxed{
x(n)=\frac{y(n)}2.
}
$$

Therefore the system is invertible.

For

$$
\boxed{
y(n)=x^2(n),
}
$$

the sign of $x(n)$ is lost.

Therefore it is non-invertible.

---

# 103. REPRESENTATION OF AN ARBITRARY SEQUENCE

The unit sample sequence provides the most important general representation.

Any discrete-time sequence can be represented as

$$
\boxed{
x(n)=
\sum_{k=-\infty}^{\infty}
x(k)\delta(n-k).
}
$$

For a finite-duration sequence, the sum reduces to a finite number of terms.

For example,

$$
x(n)=
\{3,5,2,1,4,7\}
$$

with $n=0$ corresponding to the first sample can be written as

$$
\boxed{
x(n)
=
3\delta(n)
+5\delta(n-1)
+2\delta(n-2)
+\delta(n-3)
+4\delta(n-4)
+7\delta(n-5).
}
$$

---

# 104. EXAMPLE 1.28 — REPRESENTING A SEQUENCE USING SHIFTED IMPULSES

Suppose a sequence has nonzero samples

$$
x(n_1)=A_1,\quad
x(n_2)=A_2,\quad
\ldots
$$

Then write

$$
\boxed{
x(n)=
A_1\delta(n-n_1)
+
A_2\delta(n-n_2)
+\cdots
}
$$

For a finite sequence, every sample is represented by one shifted impulse.

### Exam method

1. Identify every nonzero sample.
2. Identify its $n$-location.
3. Multiply that amplitude by $\delta(n-n_0)$.
4. Add all terms.

---

# 105. MASTER TABLE — ELEMENTARY SIGNALS

| Signal | Definition |
|---|---|
| Unit step | $\displaystyle u(n)=\begin{cases}1,&n\ge0\\0,&n<0\end{cases}$ |
| Unit ramp | $\displaystyle r(n)=nu(n)$ |
| Unit parabolic | $\displaystyle p(n)=\frac{n^2}{2}u(n)$ |
| Unit impulse | $\displaystyle \delta(n)=u(n)-u(n-1)$ |
| Sinusoid | $\displaystyle A\sin(\omega n+\phi)$ |
| Real exponential | $\displaystyle a^n$ |
| Complex exponential | $\displaystyle a^ne^{j(\omega n+\phi)}$ |

---

# 106. MASTER TABLE — SIGNAL CLASSIFICATION

| Type | Condition |
|---|---|
| Deterministic | No uncertainty |
| Random | Contains uncertainty |
| Periodic | $x(n+N)=x(n)$ |
| Energy | $0<E<\infty$ |
| Power | $0<P<\infty$ |
| Causal | $x(n)=0$ for $n<0$ |
| Anti-causal | $x(n)=0$ for $n>0$ |
| Even | $x(n)=x(-n)$ |
| Odd | $x(n)=-x(-n)$ |

---

# 107. MASTER TABLE — SYSTEM CLASSIFICATION

| Property | Test |
|---|---|
| Static | Output depends only on present input |
| Dynamic | Output depends on past/future input |
| Causal | No future input required |
| Non-causal | Future input required |
| Linear | Superposition holds |
| Nonlinear | Superposition fails |
| Shift-invariant | $T[x(n-k)]=y(n-k)$ |
| Shift-varying | Above equality fails |
| Stable | Every bounded input gives bounded output |
| FIR | Finite impulse response |
| IIR | Infinite impulse response |
| Invertible | Unique input can be recovered |

---

# 108. FOUR MOST IMPORTANT SYSTEM TESTS

For

$$
\boxed{
y(n)=T[x(n)]
}
$$

always test:

## Test 1 — Static/Dynamic

Does $y(n)$ depend only on $x(n)$?

## Test 2 — Linear/Nonlinear

Check:

$$
\boxed{
T[ax_1+bx_2]
\stackrel{?}{=}
aT[x_1]+bT[x_2].
}
$$

## Test 3 — Causal/Noncausal

Does $y(n)$ require $x(n+k)$, $k>0$?

## Test 4 — Time invariance

Compare:

$$
\boxed{
T[x(n-k)]
}
$$

with

$$
\boxed{
y(n-k).
}
$$

---

# 109. STABILITY MASTER TEST

For LTI systems:

$$
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty
}
$$

is necessary and sufficient for BIBO stability.

For rational systems, remember:

$$
\boxed{
\text{stable poles lie inside the unit circle}.
}
$$

---

# 110. QUICK EXAM SHORTCUTS

### Signal periodicity

$$
\boxed{
\omega_0/(2\pi)\in\mathbb{Q}
\Rightarrow\text{periodic}
}
$$

### Energy

$$
\boxed{
E=\sum|x(n)|^2
}
$$

### Power

$$
\boxed{
P=\lim_{N\to\infty}
\frac1{2N+1}
\sum_{n=-N}^{N}|x(n)|^2
}
$$

### Even part

$$
\boxed{
x_e(n)=\frac{x(n)+x(-n)}2
}
$$

### Odd part

$$
\boxed{
x_o(n)=\frac{x(n)-x(-n)}2
}
$$

### BIBO stability

$$
\boxed{
\sum|h(n)|<\infty
}
$$

---

# 111. COMMON EXAM MISTAKES

1. Writing $u(n)=1$ only for $n>0$.  
   The textbook uses $n\ge0$.

2. Confusing

$$
u(n-k)
$$

with

$$
u(n+k).
$$

3. Forgetting that

$$
\delta(n)
=
u(n)-u(n-1).
$$

4. Assuming every discrete-time sinusoid is periodic.

5. Forgetting the rational-frequency condition:

$$
\frac{\omega_0}{2\pi}\in\mathbb{Q}.
$$

6. Confusing energy and power.

7. Calling every bounded signal an energy signal.

8. Forgetting that a nonzero periodic signal has infinite total energy.

9. Calling a system causal just because it is linear.

10. Calling a system static when it contains $x(n-1)$.

11. Forgetting the formal shift-invariance test.

12. Assuming an explicit $n$ always proves time variance without checking; use the formal test when necessary.

13. Confusing stability with causality.

14. For LTI stability, checking $h(n)$ rather than checking absolute summability.

15. Forgetting that an invertible system must allow unique recovery of the input.

---

# 112. SHORT QUESTIONS — EXAM READY

## 1. What is a signal?

A signal is a single-valued function of one or more independent variables containing information.

## 2. What is a system?

A system acts on an input signal and transforms it into an output signal.

## 3. What is a discrete-time signal?

A signal defined only at discrete instants of time.

## 4. Name four representations of discrete-time signals.

- graphical;
- functional;
- tabular;
- sequence.

## 5. Define unit step.

$$
u(n)=1,\ n\ge0;\qquad u(n)=0,\ n<0.
$$

## 6. Define unit impulse.

$$
\delta(n)=1\text{ at }n=0,\quad0\text{ elsewhere}.
$$

## 7. Give the relation between impulse and step.

$$
\boxed{
\delta(n)=u(n)-u(n-1).
}
$$

## 8. Define periodic sequence.

$$
\boxed{
x(n+N)=x(n)
}
$$

for a positive integer $N$.

## 9. Condition for a discrete sinusoid to be periodic?

$$
\boxed{
\omega_0/(2\pi)\text{ must be rational}.
}
$$

## 10. Define energy signal.

$$
\boxed{
0<E<\infty.
}
$$

## 11. Define power signal.

$$
\boxed{
0<P<\infty.
}
$$

## 12. Define causal signal.

$$
\boxed{
x(n)=0,\ n<0.
}
$$

## 13. Define even signal.

$$
\boxed{
x(n)=x(-n).
}
$$

## 14. Define odd signal.

$$
\boxed{
x(n)=-x(-n).
}
$$

## 15. Define static system.

Output depends only on present input.

## 16. Define dynamic system.

Output depends on past or future input.

## 17. Define linear system.

A system satisfying superposition.

## 18. Define shift-invariant system.

A shift in input produces the same shift in output.

## 19. Define BIBO stability.

Every bounded input produces a bounded output.

## 20. State the LTI stability criterion.

$$
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty.
}
$$

## 21. Define FIR system.

A system having finite-duration impulse response.

## 22. Define IIR system.

A system having infinite-duration impulse response.

## 23. Define invertible system.

A system for which the input can be uniquely recovered from the output.

---

# 113. REVIEW QUESTIONS — CHAPTER 1

1. Define signal and system.
2. Explain signal processing.
3. State the advantages and limitations of digital signal processing.
4. Explain the different representations of discrete-time signals.
5. Define unit-step, unit-ramp, unit-parabolic and unit-impulse sequences.
6. State the properties of the unit sample sequence.
7. Explain sinusoidal and exponential sequences.
8. Explain time shifting.
9. Explain time reversal.
10. Explain amplitude scaling.
11. Explain time scaling.
12. Explain addition and multiplication of sequences.
13. Classify discrete-time signals.
14. Define deterministic and random signals.
15. Explain periodic and non-periodic sequences.
16. Derive the condition for periodicity of a discrete-time sinusoid.
17. Explain energy and power signals.
18. Explain causal and non-causal signals.
19. Explain even and odd signals.
20. Derive even and odd components of a signal.
21. Classify discrete-time systems.
22. Explain static and dynamic systems.
23. Explain causal and non-causal systems.
24. Explain linear and nonlinear systems.
25. Explain shift-invariant and shift-varying systems.
26. Explain BIBO stability.
27. Derive the stability criterion for an LTI system.
28. Explain FIR and IIR systems.
29. Explain invertible and non-invertible systems.
30. Represent an arbitrary sequence using shifted impulses.

---

# 114. FILL-IN-THE-BLANK ANSWERS — HIGH-YIELD

| Concept | Answer |
|---|---|
| Signal may be represented in | time or frequency domain |
| Discrete-time independent variable | $n$ |
| Unit impulse | $\delta(n)$ |
| Unit step | $u(n)$ |
| Unit ramp | $r(n)=nu(n)$ |
| Unit parabolic | $p(n)=n^2u(n)/2$ |
| $\delta(n)$ relation | $u(n)-u(n-1)$ |
| Periodic sequence condition | $x(n+N)=x(n)$ |
| DT sinusoid periodicity | $\omega_0/2\pi$ rational |
| Energy signal | finite nonzero energy |
| Power signal | finite nonzero average power |
| Causal signal | zero for $n<0$ |
| Anti-causal signal | zero for $n>0$ |
| Even signal | $x(n)=x(-n)$ |
| Odd signal | $x(n)=-x(-n)$ |
| Linear system | obeys superposition |
| Stable LTI system | absolutely summable impulse response |
| FIR | finite impulse response |
| IIR | infinite impulse response |
| Unique input-output recovery | invertible system |

---

# 115. OBJECTIVE-TYPE ANSWERS — IMPORTANT

### 1. A signal can be represented in

$$
\boxed{\text{both time and frequency domains}}
$$

### 2.

$$
\delta(n)=
\boxed{u(n)-u(n-1)}
$$

### 3. A deterministic signal has

$$
\boxed{\text{no uncertainty}}
$$

### 4. A random signal has

$$
\boxed{\text{uncertainty}}
$$

### 5. A discrete-time sinusoid is periodic when

$$
\boxed{\omega_0/(2\pi)\text{ is rational}}
$$

### 6. Energy signal

$$
\boxed{E<\infty,\quad P=0}
$$

### 7. Power signal

$$
\boxed{P<\infty,\quad E=\infty}
$$

### 8. $a^n u(n)$ is an energy signal when

$$
\boxed{|a|<1}
$$

### 9. $a^n u(n)$ is a power signal when

$$
\boxed{|a|=1}
$$

for the nonzero bounded periodic case.

### 10. A system depending on future input is

$$
\boxed{\text{non-causal}}
$$

### 11. A non-anticipative system is

$$
\boxed{\text{causal}}
$$

### 12. A system with $x(n-1)$ is generally

$$
\boxed{\text{dynamic}}
$$

### 13. A system $y(n)=x(2n)$ is

$$
\boxed{\text{linear, time-varying, dynamic}}
$$

### 14. A system $y(n)=x(-n)$ is

$$
\boxed{\text{linear, non-causal, time-invariant}}
$$

### 15. A system is invertible when

$$
\boxed{\text{the input can be uniquely recovered}}
$$

---

# 116. NUMERICAL PROBLEM TYPES TO PRACTICE

The chapter's problem section asks students to practice:

## Type 1 — Summations

Examples involving:

$$
\sum f(n)\delta(n-k).
$$

## Type 2 — Signal sketching

Examples involving:

$$
u(n+k),\quad
u(-n+k),\quad
u(n-k).
$$

## Type 3 — Periodicity

Examples involving:

$$
\sin(\omega n),\quad
\cos(\omega n),\quad
e^{j\omega n}.
$$

## Type 4 — Energy and power

Calculate:

$$
E,\qquad P.
$$

## Type 5 — Causality

Determine whether

$$
x(n)=0,\quad n<0.
$$

## Type 6 — Even/odd decomposition

Calculate:

$$
x_e(n),\quad x_o(n).
$$

## Type 7 — Static/dynamic

Check whether memory is required.

## Type 8 — Causal/non-causal systems

Check future input dependence.

## Type 9 — Linearity

Use superposition.

## Type 10 — Time invariance

Use the shifted-input test.

## Type 11 — Stability

Use

$$
\sum|h(n)|.
$$

## Type 12 — Complete classification

Classify:

- static/dynamic;
- linear/nonlinear;
- causal/non-causal;
- time-invariant/time-varying.

---

# 117. TEXTBOOK PROBLEM CHECKLIST

Before solving a Chapter 1 numerical:

### Signal problem

- [ ] Identify $n$-range.
- [ ] Identify amplitudes.
- [ ] Draw if required.
- [ ] Convert to step/impulse form if required.

### Periodicity problem

- [ ] Identify $\omega_0$.
- [ ] Calculate $\omega_0/(2\pi)$.
- [ ] Check whether rational.
- [ ] Find smallest integer period.

### Energy/power problem

- [ ] Calculate $E$.
- [ ] Calculate $P$.
- [ ] Classify.

### Even/odd problem

- [ ] Find $x(-n)$.
- [ ] Calculate $x_e(n)$.
- [ ] Calculate $x_o(n)$.

### System problem

- [ ] Static/dynamic.
- [ ] Linear/nonlinear.
- [ ] Causal/non-causal.
- [ ] Time-invariant/time-varying.
- [ ] Stable/unstable if asked.

---

# 118. MATLAB PROGRAM 1.1 — ELEMENTARY SIGNALS

The textbook includes MATLAB material for generation of elementary discrete-time signals.

A clean equivalent implementation is:

```matlab
clc;
close all;
clear;

% Unit impulse
n = -10:10;
impulse = [zeros(1,10), 1, zeros(1,10)];

figure;
stem(n, impulse);
xlabel('Discrete time n');
ylabel('Amplitude');
title('Unit Impulse Sequence');
grid on;

% Unit step
step = [zeros(1,10), ones(1,11)];

figure;
stem(n, step);
xlabel('Discrete time n');
ylabel('Amplitude');
title('Unit Step Sequence');
grid on;

% Unit ramp
n = 0:10;
ramp = n;

figure;
stem(n, ramp);
xlabel('Discrete time n');
ylabel('Amplitude');
title('Unit Ramp Sequence');
grid on;

% Unit parabolic
parabola = 0.5*n.^2;

figure;
stem(n, parabola);
xlabel('Discrete time n');
ylabel('Amplitude');
title('Unit Parabolic Sequence');
grid on;
```

---

# 119. MATLAB — SINUSOIDAL SEQUENCE

```matlab
clc;
clear;
close all;

n = -20:20;

A = 1;
w = pi/4;
phi = 0;

x = A*sin(w*n + phi);

stem(n,x);
xlabel('n');
ylabel('x(n)');
title('Discrete-Time Sinusoidal Sequence');
grid on;
```

---

# 120. MATLAB — TIME SHIFTING

```matlab
clc;
clear;
close all;

n = -10:10;

x = double(n >= 0);

k = 3;

y = double(n-k >= 0);

figure;
stem(n,x);
xlabel('n');
ylabel('x(n)');
title('Original Signal');
grid on;

figure;
stem(n,y);
xlabel('n');
ylabel('x(n-3)');
title('Delayed Signal');
grid on;
```

---

# 121. MATLAB — TIME REVERSAL

```matlab
clc;
clear;
close all;

n = -10:10;

x = double(n >= 0);

% Time reversal
y = fliplr(x);

figure;
stem(n,x);
xlabel('n');
ylabel('x(n)');
title('Original Signal');
grid on;

figure;
stem(n,y);
xlabel('n');
ylabel('x(-n)');
title('Time-Reversed Signal');
grid on;
```

---

# 122. MATLAB — EVEN AND ODD COMPONENTS

```matlab
clc;
clear;
close all;

n = -10:10;

x = sin(pi*n/4) + cos(pi*n/3);

x_reverse = fliplr(x);

xe = 0.5*(x + x_reverse);
xo = 0.5*(x - x_reverse);

figure;
stem(n,xe);
xlabel('n');
ylabel('x_e(n)');
title('Even Component');
grid on;

figure;
stem(n,xo);
xlabel('n');
ylabel('x_o(n)');
title('Odd Component');
grid on;
```

---

# 123. MATLAB — SYSTEM CLASSIFICATION EXAMPLE

For

$$
y(n)=x(n)+x(n-2),
$$

a numerical test can be performed using an arbitrary sequence:

```matlab
clc;
clear;
close all;

n = 0:10;

x = rand(size(n));

y = x;

y(3:end) = y(3:end) + x(1:end-2);

stem(n,y);
xlabel('n');
ylabel('y(n)');
title('Output of y(n)=x(n)+x(n-2)');
grid on;
```

---

# 124. MATLAB — ENERGY CALCULATION

For a finite sequence:

```matlab
clc;
clear;
close all;

x = [1 2 3 4 5];

E = sum(abs(x).^2);

fprintf('Energy = %g\n', E);
```

---

# 125. MATLAB — AVERAGE POWER

For a finite observed sequence:

```matlab
clc;
clear;
close all;

x = [1 2 3 4 5];

P = mean(abs(x).^2);

fprintf('Average power over the observed samples = %g\n', P);
```

For an infinite-time theoretical signal, use the limiting definition rather than interpreting a finite sample average as the exact theoretical power.

---

# 126. MASTER FORMULA SHEET

## Elementary signals

$$
\boxed{
u(n)=
\begin{cases}
1,&n\ge0\\
0,&n<0
\end{cases}
}
$$

$$
\boxed{
r(n)=nu(n)
}
$$

$$
\boxed{
p(n)=\frac{n^2}{2}u(n)
}
$$

$$
\boxed{
\delta(n)=u(n)-u(n-1)
}
$$

$$
\boxed{
x(n)=A\sin(\omega n+\phi)
}
$$

$$
\boxed{
x(n)=a^n
}
$$

$$
\boxed{
x(n)=a^ne^{j(\omega n+\phi)}
}
$$

---

# 127. TIME-OPERATION FORMULA SHEET

### Delay

$$
\boxed{
x(n-k)
}
$$

### Advance

$$
\boxed{
x(n+k)
}
$$

### Reversal

$$
\boxed{
x(-n)
}
$$

### Amplitude scaling

$$
\boxed{
ax(n)
}
$$

### Time compression

$$
\boxed{
x(an),\quad |a|>1
}
$$

### Time expansion

$$
\boxed{
x(n/a),\quad |a|>1
}
$$

---

# 128. CLASSIFICATION FORMULA SHEET

### Periodicity

$$
\boxed{
x(n+N)=x(n)
}
$$

### DT sinusoid

$$
\boxed{
\omega_0N=2\pi m
}
$$

### Energy

$$
\boxed{
E=\sum_{n=-\infty}^{\infty}|x(n)|^2
}
$$

### Power

$$
\boxed{
P=
\lim_{N\to\infty}
\frac1{2N+1}
\sum_{n=-N}^{N}|x(n)|^2
}
$$

### Even part

$$
\boxed{
x_e(n)=
\frac{x(n)+x(-n)}2
}
$$

### Odd part

$$
\boxed{
x_o(n)=
\frac{x(n)-x(-n)}2
}
$$

---

# 129. SYSTEM FORMULA SHEET

### Linearity

$$
\boxed{
T[ax_1+bx_2]
=
aT[x_1]+bT[x_2]
}
$$

### Time invariance

$$
\boxed{
T[x(n-k)]
=
y(n-k)
}
$$

### LTI stability

$$
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty
}
$$

### LTI causality

$$
\boxed{
h(n)=0,\qquad n<0.
}
$$

### Invertibility

$$
\boxed{
T^{-1}\{T[x(n)]\}=x(n).
}
$$

---

# 130. MASTER DECISION TREE

## Signal classification

### Is the signal completely predictable?

Yes:

$$
\boxed{\text{deterministic}}
$$

No:

$$
\boxed{\text{random}}
$$

### Is there an integer $N>0$ such that

$$
x(n+N)=x(n)?
$$

Yes:

$$
\boxed{\text{periodic}}
$$

No:

$$
\boxed{\text{non-periodic}}
$$

### Is

$$
0<E<\infty?
$$

Yes:

$$
\boxed{\text{energy signal}}
$$

### Is

$$
0<P<\infty?
$$

Yes:

$$
\boxed{\text{power signal}}
$$

### Is

$$
x(n)=0,\ n<0?
$$

Yes:

$$
\boxed{\text{causal}}
$$

### Is

$$
x(n)=x(-n)?
$$

Yes:

$$
\boxed{\text{even}}
$$

### Is

$$
x(n)=-x(-n)?
$$

Yes:

$$
\boxed{\text{odd}}
$$

---

# 131. SYSTEM DECISION TREE

Given

$$
y(n)=T[x(n)]:
$$

### 1. Static or dynamic?

Only $x(n)$?

$$
\boxed{\text{static}}
$$

Past/future samples?

$$
\boxed{\text{dynamic}}
$$

### 2. Linear?

Check superposition.

### 3. Causal?

Any future input?

If yes:

$$
\boxed{\text{non-causal}}
$$

### 4. Time invariant?

Compare:

$$
T[x(n-k)]
$$

and

$$
y(n-k).
$$

### 5. Stable?

For LTI:

$$
\boxed{
\sum|h(n)|<\infty.
}
$$

### 6. FIR or IIR?

Finite impulse response:

$$
\boxed{\text{FIR}}
$$

Infinite impulse response:

$$
\boxed{\text{IIR}}
$$

### 7. Invertible?

Can the original input be uniquely recovered?

---

# 132. ULTRA-SHORT EXAM MEMORY SHEET

$$
\boxed{
\delta(n)=u(n)-u(n-1)
}
$$

$$
\boxed{
r(n)=nu(n)
}
$$

$$
\boxed{
p(n)=\frac{n^2}{2}u(n)
}
$$

$$
\boxed{
x(n-k)=\text{delay}
}
$$

$$
\boxed{
x(-n)=\text{time reversal}
}
$$

$$
\boxed{
x_e=\frac{x+x(-n)}2
}
$$

$$
\boxed{
x_o=\frac{x-x(-n)}2
}
$$

$$
\boxed{
\omega_0N=2\pi m
}
$$

$$
\boxed{
\omega_0/(2\pi)\text{ rational}
}
$$

$$
\boxed{
E=\sum|x(n)|^2
}
$$

$$
\boxed{
P=\lim_{N\to\infty}
\frac1{2N+1}\sum_{-N}^{N}|x(n)|^2
}
$$

$$
\boxed{
\text{causal signal}:x(n)=0,\ n<0
}
$$

$$
\boxed{
\text{linear}:T[ax_1+bx_2]=aT[x_1]+bT[x_2]
}
$$

$$
\boxed{
\text{time invariant}:T[x(n-k)]=y(n-k)
}
$$

$$
\boxed{
\text{stable LTI}:\sum|h(n)|<\infty
}
$$

$$
\boxed{
\text{FIR}=\text{finite }h(n)
}
$$

$$
\boxed{
\text{IIR}=\text{infinite }h(n)
}
$$

---

# 133. FINAL EXAM CHECKLIST

Before the exam, make sure you can:

- [ ] Define signal.
- [ ] Define system.
- [ ] Explain signal processing.
- [ ] State DSP advantages.
- [ ] State DSP limitations.
- [ ] Define discrete-time signal.
- [ ] Draw and interpret a stem plot.
- [ ] Write a signal functionally.
- [ ] Write a signal in tabular form.
- [ ] Write a signal as a sequence.
- [ ] Define unit step.
- [ ] Define shifted unit step.
- [ ] Define unit ramp.
- [ ] Define unit parabolic sequence.
- [ ] Define unit impulse.
- [ ] State impulse properties.
- [ ] Represent a sequence using impulses.
- [ ] Determine whether a DT sinusoid is periodic.
- [ ] Find fundamental period.
- [ ] Explain real exponential behavior.
- [ ] Explain complex exponential behavior.
- [ ] Perform time shifting.
- [ ] Perform time reversal.
- [ ] Perform amplitude scaling.
- [ ] Perform time scaling.
- [ ] Add sequences.
- [ ] Multiply sequences.
- [ ] Classify deterministic/random signals.
- [ ] Classify periodic/non-periodic signals.
- [ ] Calculate energy.
- [ ] Calculate average power.
- [ ] Classify energy/power/neither.
- [ ] Classify causal/non-causal signals.
- [ ] Find even and odd components.
- [ ] Classify static/dynamic systems.
- [ ] Classify causal/non-causal systems.
- [ ] Test linearity.
- [ ] Test time invariance.
- [ ] Test BIBO stability.
- [ ] Distinguish FIR and IIR.
- [ ] Test invertibility.
- [ ] Solve complete system-classification problems.
- [ ] Represent arbitrary sequences using shifted impulses.
- [ ] Write basic MATLAB code for elementary signals.

---

# 134. SOURCE COVERAGE

The textbook contents identify Chapter 1 as:

- **1. Discrete-Time Signals and Systems — printed pages 1–89**
- 1.1 Introduction
- 1.2 Representation of Discrete-time Signals
  - 1.2.1 Graphical Representation
  - 1.2.2 Functional Representation
  - 1.2.3 Tabular Representation
  - 1.2.4 Sequence Representation
- 1.3 Elementary Discrete-time Signals
  - Unit Step
  - Unit Ramp
  - Unit Parabolic
  - Unit Impulse
  - Sinusoidal
  - Real Exponential
  - Complex Exponential
- 1.4 Basic Operations on Sequences
  - Time Shifting
  - Time Reversal
  - Amplitude Scaling
  - Time Scaling
  - Signal Addition
  - Signal Multiplication
- 1.5 Classification of Discrete-time Signals
  - Deterministic and Random
  - Periodic and Non-periodic
  - Energy and Power
  - Causal and Non-causal
  - Even and Odd
- 1.6 Classification of Discrete-time Systems
  - Static and Dynamic
  - Causal and Non-causal
  - Linear and Non-linear
  - Shift-invariant and Shift-varying
  - Stable and Unstable
  - FIR and IIR
  - Invertible and Non-invertible
- 1.7 Representation of an Arbitrary Sequence
- Short Questions
- Review Questions
- Fill in the Blanks
- Objective Questions
- Problems
- MATLAB Programs

The textbook's own preface confirms that Chapter 1 covers standard discrete-time signals, basic signal operations, signal classification, and system classification with numerous examples. It also states that each chapter contains short questions, review questions, fill-ins, objective questions, numerical problems and MATLAB programs. 

---

# 135. FINAL MASTER MAP

$$
\boxed{
\text{Signal}
\rightarrow
\text{Representation}
\rightarrow
\text{Elementary Signals}
\rightarrow
\text{Operations}
\rightarrow
\text{Signal Classification}
\rightarrow
\text{System Classification}
\rightarrow
\text{Arbitrary Sequence}
}
$$

The most important Chapter 1 equations to memorize are:

$$
\boxed{
\delta(n)=u(n)-u(n-1)
}
$$

$$
\boxed{
r(n)=nu(n)
}
$$

$$
\boxed{
p(n)=\frac{n^2}{2}u(n)
}
$$

$$
\boxed{
x(n+N)=x(n)
}
$$

$$
\boxed{
\omega_0N=2\pi m
}
$$

$$
\boxed{
E=\sum|x(n)|^2
}
$$

$$
\boxed{
P=\lim_{N\to\infty}
\frac1{2N+1}
\sum_{n=-N}^{N}|x(n)|^2
}
$$

$$
\boxed{
x_e(n)=\frac{x(n)+x(-n)}2
}
$$

$$
\boxed{
x_o(n)=\frac{x(n)-x(-n)}2
}
$$

$$
\boxed{
T[ax_1+bx_2]
=
aT[x_1]+bT[x_2]
}
$$

$$
\boxed{
T[x(n-k)]=y(n-k)
}
$$

$$
\boxed{
\sum_{n=-\infty}^{\infty}|h(n)|<\infty
}
$$

These are the core formulas from which a large fraction of Chapter 1 exam questions can be solved.
