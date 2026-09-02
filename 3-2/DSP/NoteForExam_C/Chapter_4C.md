# Chapter 4 — System Realization
## FULL Exam Notes — Based on *Digital Signal Processing* by A. Anand Kumar

> **Source:** Chapter 4, *System Realization*, printed pages **277–357**.  
> These notes preserve the chapter's organization, terminology, formulas, worked-example approach, comparison tables, exam questions, problems, and MATLAB programs.  
> **Important:** The book's block diagrams/figures are referred to by figure number and described textually; the mathematical realization procedure is written out so the structure can be redrawn in an exam.

---

# 1. Chapter Overview

Discrete-time systems may be:

- **FIR (Finite Impulse Response)** systems
- **IIR (Infinite Impulse Response)** systems

**Realization of a discrete-time system** means obtaining a network corresponding to its **difference equation or transfer function**.

The basic realization elements are:

1. **Adder**
2. **Constant multiplier**
3. **Unit delay element**

The main realization structures discussed in this chapter are:

### IIR structures
1. Direct form-I
2. Direct form-II
3. Transposed form
4. Cascade form
5. Parallel form
6. Lattice structure
7. Ladder structure

### FIR structures
1. Direct form
2. Transposed form
3. Cascade form
4. Lattice structure
5. Linear-phase realization

---

# 2. 4.1 Introduction

A discrete-time system can be represented by a difference equation or a transfer function.

- An **FIR system** has a finite number of impulse-response samples.
- An **IIR system** has an infinite number of impulse-response samples.

The objective of realization is to convert the mathematical description of a system into a practical network of delays, multipliers and adders.

---

# 3. 4.2 Realization of Discrete-Time Systems

## 3.1 Basic realization procedure

To realize a discrete-time system:

1. Start with the **difference equation** in the time domain.
2. Convert it into an **algebraic equation in the z-domain**, if convenient.
3. Represent each term using:
   - a constant multiplier,
   - a delay element $z^{-1}$,
   - and adders.
4. Connect the elements so that the required output is obtained.

The three basic elements are:

### Adder

Adds two or more signals.

$$
y(n)=x_1(n)+x_2(n)+\cdots
$$

### Constant multiplier

Multiplies a signal by a constant $K$.

$$
y(n)=Kx(n)
$$

### Unit delay

Delays a signal by one sampling period:

$$
x(n)\rightarrow x(n-1)
$$

In the z-domain:

$$
X(z)\rightarrow z^{-1}X(z)
$$

---

# 4. Example 4.1 — Constructing Block Diagrams

## (a)

Given:

$$
y(n)=0.7x(n)+0.3x(n-1)
$$

Taking the z-transform:

$$
Y(z)=0.7X(z)+0.3z^{-1}X(z)
$$

### Realization

- One direct path from $x(n)$ through multiplier $0.7$.
- A second path from $x(n)$ through $z^{-1}$, then multiplier $0.3$.
- Add the two paths to obtain $y(n)$.

---

## (b)

Given:

$$
y(n)=0.5y(n-1)+0.8x(n)+0.4x(n-1)
$$

Taking the z-transform:

$$
Y(z)=0.5z^{-1}Y(z)+0.8X(z)+0.4z^{-1}X(z)
$$

### Realization

Three terms enter the final adder:

$$
0.5z^{-1}Y(z),\qquad 0.8X(z),\qquad 0.4z^{-1}X(z)
$$

The first term is a feedback path; the other two are feedforward paths.

---

# 5. IIR System Realization

## 5.1 Definition

An IIR system has an impulse response containing an **infinite number of samples**.

The convolution formula is:

$$
\boxed{ y(n)=\sum_{k=0}^{\infty}h(k)x(n-k) }
$$

Because the sum extends indefinitely, an IIR system has **infinite memory**.

---

## 5.2 Recursive system

A system whose output depends on present/past inputs and past outputs is called a **recursive system**.

In general:

$$
y(n)=F[ y(n-1),y(n-2),\ldots,y(n-N), x(n),x(n-1),\ldots,x(n-M) ]
$$

To compute $y(n_0)$, previous outputs must be available:

$$
y(0),y(1),\ldots,y(n_0-1)
$$

Therefore recursive-system outputs have to be computed in sequence.

---

# 6. IIR Difference Equation and Transfer Function

The general IIR difference equation is:

$$
\boxed{ y(n)= -\sum_{k=1}^{N}a_k y(n-k) + \sum_{k=0}^{M}b_k x(n-k) }
$$

Expanded:

$$
\begin{aligned} y(n)=& -a_1y(n-1)-a_2y(n-2)-\cdots-a_Ny(n-N)\\ &+b_0x(n)+b_1x(n-1)+\cdots+b_Mx(n-M) \end{aligned}
$$

Taking the z-transform and neglecting initial conditions:

$$
Y(z) = -\sum_{k=1}^{N}a_kz^{-k}Y(z) + \sum_{k=0}^{M}b_kz^{-k}X(z)
$$

Hence,

$$
Y(z) \left[ 1+\sum_{k=1}^{N}a_kz^{-k} \right] = X(z) \left[ \sum_{k=0}^{M}b_kz^{-k} \right]
$$

Therefore,

$$
\boxed{ H(z)=\frac{Y(z)}{X(z)} = \frac{ \sum_{k=0}^{M}b_kz^{-k} }{ 1+\sum_{k=1}^{N}a_kz^{-k} } }
$$

or

$$
\boxed{ H(z)= \frac{ b_0+b_1z^{-1}+\cdots+b_Mz^{-M} }{ 1+a_1z^{-1}+\cdots+a_Nz^{-N} } }
$$

---

# 7. Factors Affecting Choice of Realization Structure

The chapter identifies three major factors:

### 7.1 Computational complexity

The number of arithmetic operations required to calculate an output value $y(n)$.

### 7.2 Memory requirements

The number of memory locations required for:

- system parameters,
- past inputs,
- past outputs,
- intermediate computed values.

### 7.3 Finite-word-length effects

Quantization effects caused by finite precision in digital implementation.

Other considerations include:

- suitability for parallel processing,
- suitability for pipelining.

---

# 8. 4.3 Structures for Realization of IIR Systems

The seven IIR structures are:

1. **Direct form-I**
2. **Direct form-II**
3. **Transposed form**
4. **Cascade form**
5. **Parallel form**
6. **Lattice structure**
7. **Ladder structure**

---

# 9. 4.3.1 Direct Form-I Structure

Direct form-I is the direct implementation of the difference equation or transfer function.

Starting from:

$$
y(n)= -\sum_{k=1}^{N}a_ky(n-k) + \sum_{k=0}^{M}b_kx(n-k)
$$

the output can be written as:

$$
\begin{aligned} y(n)=& -a_1y(n-1)-a_2y(n-2)-\cdots-a_Ny(n-N)\\ &+b_0x(n)+b_1x(n-1)+\cdots+b_Mx(n-M) \end{aligned}
$$

In z-domain:

$$
\begin{aligned} Y(z)=& -a_1z^{-1}Y(z)-\cdots-a_Nz^{-N}Y(z)\\ &+b_0X(z)+b_1z^{-1}X(z)+\cdots+b_Mz^{-M}X(z) \end{aligned}
$$

## Structure

Direct form-I has two separate delay chains:

- **input delay chain** for the zeros,
- **output delay chain** for the poles.

Thus:

> **Zeros are realized first and poles are realized second.**

It is therefore a **non-canonical structure** because it uses more delay elements than the order of the difference equation.

## Limitations

- More delay elements are required.
- It is not effective for high-order systems.
- It lacks hardware flexibility.
- Quantization noise can cause instability.

---

# 10. 4.3.2 Direct Form-II Structure

Direct form-II is an alternative to direct form-I and uses fewer delay elements.

The transfer function is split into:

$$
\boxed{ H(z)= \frac{Y(z)}{W(z)} \cdot \frac{W(z)}{X(z)} }
$$

where

$$
\boxed{ \frac{W(z)}{X(z)} = \frac{1}{ 1+a_1z^{-1}+\cdots+a_Nz^{-N} } }
$$

and

$$
\boxed{ \frac{Y(z)}{W(z)} = b_0+b_1z^{-1}+\cdots+b_Mz^{-M} }
$$

Therefore:

$$
W(z)=X(z)-a_1z^{-1}W(z)-\cdots-a_Nz^{-N}W(z)
$$

and

$$
Y(z)=b_0W(z)+b_1z^{-1}W(z)+\cdots+b_Mz^{-M}W(z)
$$

### Important order

In direct form-II:

> **Poles are realized first and zeros are realized second.**

The same delay line is shared by the two parts.

## Why direct form-II is called canonical

The number of delay elements is the same as the order of the difference equation.

Hence:

$$
\boxed{\text{Direct form-II = canonical structure}}
$$

## Advantages

- Requires fewer delay elements.
- Requires less memory.
- More efficient than direct form-I.

## Limitations

- Lacks hardware flexibility.
- Quantization noise can cause instability.

---

# 11. Direct Form-I vs Direct Form-II

| Feature | Direct Form-I | Direct Form-II |
|---|---|---|
| Delay arrangement | Separate input/output delays | Single shared delay line |
| Number of delays | $M+N-2$ | $\max(M-1,N-1)$ |
| Multipliers | $M+N-1$ | $M+N-1$ |
| Adders | $M+N-2$ | $M+N-2$ |
| Structure | Non-canonical | Canonical |
| Memory | Higher | Lower |
| First part | Non-recursive | Recursive |
| Second part | Recursive | Non-recursive |
| Main advantage | Direct/simple interpretation | Minimum delay storage |

For equal pole and zero orders, direct form-II requires approximately half the delay elements of direct form-I.

---

# 12. Conversion of Direct Form-I to Direct Form-II

Direct form-I can be regarded as two systems in cascade:

$$
H(z)=H_1(z)H_2(z)
$$

By the interchangeability of cascaded LTI systems, their order can be reversed.

After reversal:

- the two systems have the same delay inputs,
- their delay elements can be merged,
- the two delay chains become a single delay chain.

The resulting structure is **direct form-II**.

### Exam procedure

1. Split direct form-I into two cascaded systems.
2. Interchange their order.
3. Observe that the corresponding delay inputs are identical.
4. Merge the delay lines.
5. Obtain direct form-II.

---

# 13. 4.3.3 Transposed Form Structure

The transposed structure is obtained by:

1. Reversing the direction of all branch transmittances.
2. Interchanging input and output.
3. Keeping branch transmittances unchanged.
4. Replacing junctions by adders and adders by junctions.
5. Folding the resulting structure where appropriate.

The system function remains unchanged.

### Conditions

The transposition is valid when:

- branch transmittances remain unchanged,
- all branch directions are reversed,
- input/output roles are interchanged.

## Important point

The transposed structure is particularly advantageous when applied to **direct form-II**.

The book notes that:

- higher-order systems obtain greater advantage,
- there is no advantage when applied to direct form-I.

---

# 14. Example 4.2 — IIR System in Transposed Form

Given:

$$
y(n)+2y(n-1)+3y(n-2) = 4x(n)+5x(n-1)+6x(n-2)
$$

Taking z-transform:

$$
Y(z)+2z^{-1}Y(z)+3z^{-2}Y(z) = 4X(z)+5z^{-1}X(z)+6z^{-2}X(z)
$$

Therefore:

$$
\boxed{ H(z)= \frac{4+5z^{-1}+6z^{-2}} {1+2z^{-1}+3z^{-2}} }
$$

The book obtains:

1. direct form-II realization,
2. recovered realization,
3. transposed realization.

For an exam, first obtain $H(z)$, construct the direct form-II equations, then transpose the structure.

---

# 15. 4.3.4 Cascade Form Realization

Cascade form means a **series interconnection** of several lower-order subsystems.

The transfer function is factored as:

$$
\boxed{ H(z)=\prod_{i=1}^{K}H_i(z) }
$$

Typical sections are first-order or second-order:

### Second-order section

$$
H_i(z)= \frac{C_{0i}+C_{1i}z^{-1}+C_{2i}z^{-2}} {d_{0i}+d_{1i}z^{-1}+d_{2i}z^{-2}}
$$

### First-order section

$$
H_i(z)= \frac{C_{0i}+C_{1i}z^{-1}} {d_{0i}+d_{1i}z^{-1}}
$$

Each section is realized separately, usually using direct form structures, and all sections are cascaded.

## Difficulties

1. Pairing poles and zeros.
2. Choosing the order of first- and second-order sections.
3. Providing scaling between sections to prevent internal variables from becoming too large or too small.

## Main advantage

Cascade realization reduces sensitivity of frequency response to coefficient quantization.

---

# 16. 4.3.5 Parallel Form Realization

Parallel form is obtained by partial-fraction expansion.

The transfer function is written as:

$$
\boxed{ H(z)=C+\sum_{i=1}^{K}H_i(z) }
$$

where each $H_i(z)$ is generally a first- or second-order section.

Each section is realized separately and the outputs are added.

## Advantage

Because operations can be performed simultaneously, parallel form is suitable for:

> **high-speed filtering applications.**

## Difficulty

Partial-fraction expansion becomes difficult for higher-order systems.

---

# 17. Cascade vs Parallel

| Cascade | Parallel |
|---|---|
| Product of sections | Sum of sections |
| Series connection | Parallel connection |
| Factorization required | Partial-fraction expansion required |
| Signal passes through sections successively | Sections process simultaneously |
| Section ordering matters | Section ordering is less central |
| Scaling between sections may be needed | Parallel processing supports high speed |
| Good for modular realization | Good for high-speed filtering |

---

# 18. 4.3.6 Lattice Structure for IIR Systems

An IIR system has both zeros and poles.

The transfer function is separated into:

$$
\boxed{ H(z)=H_z(z)H_p(z) }
$$

where

$$
H_z(z)=\sum_{k=0}^{M}b_kz^{-k}
$$

represents the **zeros**, and

$$
H_p(z)= \frac{1}{ 1+\sum_{k=1}^{N}a_kz^{-k} }
$$

represents the **poles**.

The zero section is realized first, followed by the pole section.

---

## 18.1 Single-stage pole lattice

For one stage:

$$
\boxed{ y(n)=x_{p1}(n)+K_{1p}y(n-1) }
$$

and the feedback response is:

$$
\boxed{ x'_{p1}(n)=K_{1p}y(n)+y(n-1) }
$$

where $K_{1p}$ is a reflection coefficient.

---

## 18.2 Two-stage pole lattice

For a two-stage structure:

$$
x_{p1}(n) = x_{p2}(n)+K_{2p}x'_{p1}(n-1)
$$

and the resulting output is:

$$
\boxed{ y(n)= x_{p2}(n) + K_{1p}(1+K_{2p})y(n-1) + K_{2p}y(n-2) }
$$

---

## 18.3 All-zero-all-pole lattice

The general IIR lattice realization consists of:

- an all-zero lattice section,
- an all-pole lattice section,
- connected in cascade.

## Procedure

1. Find the order of the difference equation.
2. Compare coefficients with the corresponding lattice equations.
3. Determine reflection coefficients:
$$
K_{1p},K_{2p},\ldots
$$
   for poles and
$$
K_{1z},K_{2z},\ldots
$$
   for zeros.
4. Construct each lattice.
5. Cascade zero and pole lattice sections.

---

# 19. Example 4.3 — IIR Lattice Coefficients

Given:

$$
y(n)-\frac25y(n-1)+\frac15y(n-2) = x(n)+\frac14x(n-1)
$$

Rearrange:

$$
y(n)= \frac25y(n-1)-\frac15y(n-2) +x(n)+\frac14x(n-1)
$$

The system has:

- first-order zeros,
- second-order poles.

From the lattice equation:

$$
y(n)=x(n)+K_{1z}x(n-1) +K_{1p}(1+K_{2p})y(n-1) +K_{2p}y(n-2)
$$

Comparison gives:

$$
\boxed{K_{1z}=\frac14}
$$

$$
\boxed{K_{2p}=-\frac15}
$$

and

$$
K_{1p}(1+K_{2p})=\frac25
$$

Therefore:

$$
K_{1p} = \frac{2/5}{1-1/5} = \boxed{\frac12}
$$

Thus:

$$
\boxed{ K_{1z}=\frac14,\qquad K_{1p}=\frac12,\qquad K_{2p}=-\frac15 }
$$

This gives the **all-zero-all-pole lattice realization**.

---

# 20. 4.3.7 Ladder Structure Realization of IIR Systems

Ladder realization is used only for an **IIR system**.

The numerator polynomial is divided by the denominator polynomial **sequentially**, and the resulting quotients are substituted into the ladder structure.

There are two cases.

---

## 20.1 Case-I

If the negative order of the numerator polynomial is greater than that of the denominator polynomial:

$$
H(z)= \frac{ b_{N+1}z^{-(N+1)}+\cdots+b_0 }{ a_Nz^{-N}+\cdots+a_0 }
$$

the ladder representation has the form:

$$
\boxed{ H(z)= B_1z^{-1} + \frac{1}{ C_1+ \frac{1}{ B_2z^{-1} + \frac{1}{ C_2+\cdots } } } }
$$

The exact nested form is represented graphically in the book's Figure 4.19(a).

---

## 20.2 Case-II

If numerator and denominator have equal negative order:

$$
H(z)= \frac{ b_Nz^{-N}+\cdots+b_0 }{ a_Nz^{-N}+\cdots+a_0 }
$$

the ladder form begins with a constant term:

$$
\boxed{ H(z)= B_0+ \frac{1}{ C_1z^{-1} + \frac{1}{ B_1+ \frac{1}{ C_2z^{-1}+\cdots } } } }
$$

---

## 20.3 Ladder realization procedure

1. Express numerator and denominator in descending powers of $z^{-1}$.
2. Determine whether the transfer function belongs to Case-I or Case-II.
3. Perform sequential division.
4. Compare the quotients with the standard ladder expression.
5. Determine the ladder parameters.
6. Draw the ladder structure.

---

# 21. Example 4.4 — IIR Ladder, Case-II

Given:

$$
H(z)= \frac{3z^2+5z+4}{z^2+6z+8}
$$

Write in negative powers:

$$
H(z)= \frac{3+5z^{-1}+4z^{-2}} {1+6z^{-1}+8z^{-2}}
$$

or in the orientation used for ladder division:

$$
H(z)= \frac{4z^{-2}+5z^{-1}+3} {8z^{-2}+6z^{-1}+1}
$$

The numerator and denominator have equal negative order, so this is **Case-II**.

Sequential division gives the ladder parameters:

$$
\boxed{ B_0=\frac12,\quad C_1=4,\quad B_1=-\frac12,\quad C_2=-4,\quad a_2=\frac43 }
$$

The resulting continued-fraction form is then implemented using the Case-II ladder structure.

---

# 22. Example 4.5 — IIR Ladder, Case-I

Given:

$$
H(z)= \frac{5z^3+3z^2+4z+2} {z(2z^2+3z+1)}
$$

Writing in negative powers gives:

$$
H(z)= \frac{ 2z^{-3}+4z^{-2}+3z^{-1}+5 }{ z^{-2}+3z^{-1}+2 }
$$

The numerator has greater negative order than the denominator.

Therefore this is **Case-I**.

Sequential division gives:

$$
\boxed{ B_1=2,\quad C_1=-\frac12,\quad B_2=-\frac45,\quad C_2=\frac{25}{26}, \quad B_3=-\frac{169}{40}, \quad C_3=-\frac{8}{65} }
$$

These values are substituted into the Case-I ladder structure.

---

# 23. Example 4.6 — Complete IIR Realization

Given:

$$
y(n)= -\frac{13}{12}y(n-1) -\frac{9}{24}y(n-2) -\frac1{24}y(n-3) +x(n)+4x(n-1)+3x(n-2)
$$

The transfer function is:

$$
\boxed{ H(z)= \frac{1+4z^{-1}+3z^{-2}} {1+\frac{13}{12}z^{-1} +\frac9{24}z^{-2} +\frac1{24}z^{-3}} }
$$

The chapter obtains all four realizations:

- direct form-I,
- direct form-II,
- cascade,
- parallel.

### Cascade factorization

$$
\boxed{ H(z)= \frac{(1+z^{-1})(1+3z^{-1})} {(1+\frac12z^{-1})(1+\frac13z^{-1})(1+\frac14z^{-1})} }
$$

Hence three first-order cascade sections can be formed.

### Parallel realization

Partial-fraction expansion gives:

$$
\boxed{ H(z)= \frac{30}{1+\frac12z^{-1}} - \frac{128}{1+\frac13z^{-1}} + \frac{99}{1+\frac14z^{-1}} }
$$

Each term is realized separately and the outputs are summed.

---

# 24. Example 4.7 — Direct Form-I and II

Given:

$$
H(z)= \frac{3z^3-5z^2+9z-3} {[z-\frac12][z^2-z+\frac13]}
$$

Expanding the denominator:

$$
H(z)= \frac{ 3-5z^{-1}+9z^{-2}-3z^{-3} }{ 1-\frac32z^{-1} +\frac56z^{-2} -\frac16z^{-3} }
$$

### Direct form-I equation

$$
\boxed{ \begin{aligned} Y(z)=& \frac32z^{-1}Y(z) -\frac56z^{-2}Y(z) +\frac16z^{-3}Y(z)\\ &+3X(z)-5z^{-1}X(z)+9z^{-2}X(z)-3z^{-3}X(z) \end{aligned} }
$$

### Direct form-II equations

Introduce $W(z)$:

$$
\frac{W(z)}{X(z)} = \frac{1}{ 1-\frac32z^{-1} +\frac56z^{-2} -\frac16z^{-3} }
$$

Hence:

$$
\boxed{ W(z)= X(z)+\frac32z^{-1}W(z) -\frac56z^{-2}W(z) +\frac16z^{-3}W(z) }
$$

and

$$
\boxed{ Y(z)= 3W(z)-5z^{-1}W(z)+9z^{-2}W(z)-3z^{-3}W(z) }
$$

---

# 25. Example 4.8 — Direct and Transposed Networks

Given:

$$
\begin{aligned} y(n)=& 2x(n)+0.3x(n-1)+0.5x(n-2)\\ &-0.7y(n-1)-0.9y(n-2) \end{aligned}
$$

Transfer function:

$$
\boxed{ H(z)= \frac{2+0.3z^{-1}+0.5z^{-2}} {1+0.7z^{-1}+0.9z^{-2}} }
$$

For direct form-II:

$$
\boxed{ W(z)= X(z)-0.7z^{-1}W(z)-0.9z^{-2}W(z) }
$$

and

$$
\boxed{ Y(z)= 2W(z)+0.3z^{-1}W(z)+0.5z^{-2}W(z) }
$$

The transposed structure is obtained by the transposition procedure.

---

# 26. Example 4.9 — Cascade and Parallel

Given:

$$
H(z)= \frac{1+\frac13z^{-1}} { \left(1-\frac12z^{-1}+\frac13z^{-2}\right) \left(1-\frac13z^{-1}+\frac12z^{-2}\right) }
$$

### Cascade

Define:

$$
H(z)=H_1(z)H_2(z)
$$

where

$$
H_1(z)= \frac{1+\frac13z^{-1}} {1-\frac12z^{-1}+\frac13z^{-2}}
$$

and

$$
H_2(z)= \frac1{ 1-\frac13z^{-1}+\frac12z^{-2} }
$$

Each section is realized in direct form-II and connected in cascade.

### Parallel

The transfer function can instead be decomposed into partial-fraction sections and each section realized independently.

---

# 27. Example 4.10 — Cascade and Parallel

The chapter also considers:

$$
\boxed{ H(z)= \frac1{1+2z^{-1}-z^{-2}} }
$$

Factor the denominator:

$$
1+2z^{-1}-z^{-2} = (1-0.414z^{-1})(1+2.414z^{-1})
$$

Thus:

$$
H(z)= \frac1{(1-0.414z^{-1})(1+2.414z^{-1})}
$$

### Cascade sections

$$
H_1(z)=\frac1{1-0.414z^{-1}}
$$

$$
H_2(z)=\frac1{1+2.414z^{-1}}
$$

### Parallel form

The chapter obtains:

$$
\boxed{ H(z)= \frac{0.146}{1-0.414z^{-1}} + \frac{0.853}{1+2.414z^{-1}} }
$$

This illustrates how a second-order system can be implemented as either two first-order cascade sections or two first-order parallel sections.

---

# 28. Example 4.11 — Cascade Realization

Given:

$$
H(z)= \frac{3+2z^{-1}+z^{-2}} { (1+\frac13z^{-1}) (1-\frac13z^{-1}) (1+\frac13z^{-1}) }
$$

Combining denominator factors:

$$
H(z)= \frac{ 3+2z^{-1}+z^{-2} }{ (1-\frac13z^{-1}) (1+\frac23z^{-1}+\frac19z^{-2}) }
$$

Thus:

$$
H(z)=H_1(z)H_2(z)
$$

with

$$
H_1(z)=\frac1{1-\frac13z^{-1}}
$$

and

$$
H_2(z)= \frac{3+2z^{-1}+z^{-2}} {1+\frac23z^{-1}+\frac19z^{-2}}
$$

Each section is realized by direct form-II.

---

# 29. Example 4.12 — Cascade and Parallel Realization

Given:

$$
\boxed{ H(z)= \frac{(1+z^{-1})^3} { [1-\frac12z^{-1}] [1+z^{-1}+\frac13z^{-2}] } }
$$

## Cascade

Factor the numerator:

$$
(1+z^{-1})^3 = (1+z^{-1})(1+2z^{-1}+z^{-2})
$$

Therefore:

$$
H(z)= \frac{1+z^{-1}}{1-\frac12z^{-1}} \cdot \frac{1+2z^{-1}+z^{-2}} {1+z^{-1}+\frac13z^{-2}}
$$

So two sections are cascaded.

## Parallel

Partial-fraction expansion gives:

$$
\boxed{ H(z) = 1+ \frac{\frac{81}{26}z^{-1}} {1-\frac12z^{-1}} + \frac{ -\frac8{13}z^{-1}-\frac{10}{39}z^{-2} }{ 1+z^{-1}+\frac13z^{-2} } }
$$

The individual sections are then realized and connected in parallel.

---

# 30. FIR System Realization

## 30.1 Definition

An FIR system has a finite number of impulse-response samples.

The convolution sum is:

$$
\boxed{ y(n)= \sum_{k=0}^{N-1}h(k)x(n-k) }
$$

with:

$$
h(n)=0 \qquad \text{for }n<0\text{ and }n\ge N
$$

Therefore the system uses only the most recent $N$ input samples.

This gives a **finite memory of length $N$**.

---

# 31. FIR Systems Are Non-Recursive

A non-recursive system has output depending only on present and past inputs:

$$
\boxed{ y(n)=F[x(n),x(n-1),\ldots,x(n-M)] }
$$

There is no dependence on previous outputs.

Therefore an FIR system is generally **non-recursive**.

A useful consequence:

$$
y(n_0)
$$

can be calculated immediately without first calculating:

$$
y(n_0-1),y(n_0-2),\ldots
$$

Thus outputs may be computed in any order.

---

# 32. FIR Difference Equation and Transfer Function

General FIR difference equation:

$$
\boxed{ y(n)= \sum_{k=0}^{N-1}b_kx(n-k) }
$$

Taking the z-transform:

$$
Y(z)= \sum_{k=0}^{N-1}b_kz^{-k}X(z)
$$

Hence:

$$
\boxed{ H(z)=\frac{Y(z)}{X(z)} = \sum_{k=0}^{N-1}b_kz^{-k} }
$$

or:

$$
\boxed{ H(z)= b_0+b_1z^{-1}+b_2z^{-2} +\cdots+b_{N-1}z^{-(N-1)} }
$$

Also:

$$
H(z)=Z[h(n)]
$$

so:

$$
\boxed{ b_k=h(k),\qquad k=0,1,\ldots,N-1 }
$$

---

# 33. FIR Realization Structures

The five FIR structures are:

1. Direct form
2. Transposed form
3. Cascade form
4. Lattice structure
5. Linear-phase realization

---

# 34. 4.4.1 Direct Form FIR

Since an FIR system has no denominator/pole part, its direct form contains only:

- delays,
- multipliers,
- adders.

The structure is also called a:

> **transversal or tapped-delay-line filter**

It is a **canonical structure** because the number of delays equals the order of the filter.

---

# 35. 4.4.2 Transposed FIR Structure

The same transposition procedure used for IIR systems applies to FIR systems.

### Procedure

1. Draw the direct form.
2. Reverse signal-flow directions.
3. Interchange input and output.
4. Replace junctions with adders and adders with junctions.
5. Fold the structure.

### Important point

For FIR systems, the transposed form has **no computational advantage** over the direct form.

The numbers of:

- additions,
- multiplications,
- storage elements

remain the same.

---

# 36. Example 4.13 — FIR Transposed Form

Given:

$$
\boxed{ y(n)=2x(n)+4x(n-1)-3x(n-2) }
$$

Taking z-transform:

$$
Y(z)= 2X(z)+4z^{-1}X(z)-3z^{-2}X(z)
$$

Hence:

$$
\boxed{ H(z)=2+4z^{-1}-3z^{-2} }
$$

Draw the direct form, recover the equivalent realization, then transpose it according to the standard four-step procedure.

---

# 37. 4.4.3 Cascade FIR Structure

Cascade FIR realization breaks:

$$
H(z)
$$

into a product:

$$
\boxed{ H(z)=H_1(z)H_2(z)\cdots H_K(z) }
$$

Each factor is realized separately in direct form and the sections are connected in series.

---

## 37.1 When $N$ is odd

The FIR transfer function is an $(N-1)$-order polynomial.

If $N$ is odd, $N-1$ is even, so the polynomial can be represented using second-order factors:

$$
H(z)= \prod_i (C_{0i}+C_{1i}z^{-1}+C_{2i}z^{-2})
$$

Thus there are:

$$
\boxed{\frac{N-1}{2}}
$$

second-order sections.

---

## 37.2 When $N$ is even

Then $N-1$ is odd.

Therefore there is:

- one first-order section,
- $(N-2)/2$ second-order sections.

---

# 38. 4.4.4 FIR Lattice Structure

The FIR lattice structure is extensively used in **digital speech processing**.

It has two paths:

- real output $y(n)$,
- supporting output $y'(n)$.

The coefficient $K$ is called the **reflection coefficient**.

---

## 38.1 First-order lattice

$$
\boxed{ y_1(n)=x(n)+K_1x(n-1) }
$$

and

$$
\boxed{ y'_1(n)=K_1x(n)+x(n-1) }
$$

---

## 38.2 Second-order lattice

$$
y_2(n)=y_1(n)+K_2y'_1(n-1)
$$

$$
y'_2(n)=K_2y_1(n)+y'_1(n-1)
$$

Substitution gives:

$$
\boxed{ y_2(n)= x(n)+K_1(1+K_2)x(n-1)+K_2x(n-2) }
$$

and

$$
\boxed{ y'_2(n)= K_2x(n)+K_1(1+K_2)x(n-1)+x(n-2) }
$$

Define:

$$
B_2(0)=1
$$

$$
B_2(1)=K_1(1+K_2)
$$

$$
B_2(2)=K_2
$$

Then:

$$
\boxed{ y_2(n)= \sum_{k=0}^{2}B_2(k)x(n-k) }
$$

---

## 38.3 General $m$-th order lattice

$$
\boxed{ y_m(n)= \sum_{k=0}^{m}B_m(k)x(n-k) }
$$

Taking z-transform:

$$
Y_m(z)=B_m(z)X(z)
$$

Hence:

$$
\boxed{ B_m(z)=\frac{Y_m(z)}{X(z)} }
$$

The resulting FIR filter is called a **forward prediction** structure.

---

## 38.4 FIR lattice realization procedure

1. If the coefficient of present input $x(n)$ is not unity, normalize it.
2. Find the order of the difference equation.
3. Compare the coefficients with the standard lattice equation.
4. Determine $K_1,K_2,\ldots$.
5. Construct the lattice.

### Key point

Only the reflection coefficients need to be determined, making the structure easily programmable.

However, the number of components, especially adders and multipliers, increases.

---

# 39. Example 4.14 — First-Order FIR Lattice

Given:

$$
H(z)=5+3z^{-1}
$$

Therefore:

$$
Y(z)=5X(z)+3z^{-1}X(z)
$$

and:

$$
y(n)=5x(n)+3x(n-1)
$$

Factor out 5:

$$
y(n)=5 \left[ x(n)+\frac35x(n-1) \right]
$$

Compare with:

$$
y(n)=x(n)+K_1x(n-1)
$$

Therefore:

$$
\boxed{K_1=\frac35}
$$

The factor 5 is implemented as the overall gain.

---

# 40. Example 4.15 — Second-Order FIR Lattice

Given:

$$
\boxed{ H(z)=1+\frac79z^{-1}+\frac35z^{-2} }
$$

Thus:

$$
y(n)= x(n)+\frac79x(n-1)+\frac35x(n-2)
$$

Compare with:

$$
y(n)= x(n)+K_1(1+K_2)x(n-1)+K_2x(n-2)
$$

Hence:

$$
\boxed{K_2=\frac35}
$$

and:

$$
K_1\left(1+\frac35\right)=\frac79
$$

Therefore:

$$
K_1= \frac{7/9}{8/5} = \boxed{\frac{35}{72}}
$$

So:

$$
\boxed{ K_1=\frac{35}{72}, \qquad K_2=\frac35 }
$$

---

# 41. 4.4.5 Linear-Phase FIR Realization

An FIR system is linear phase if:

$$
\boxed{ h(k)=\pm h(N-1-k) }
$$

For symmetric FIR:

$$
\boxed{ h(k)=h(N-1-k) }
$$

For antisymmetric FIR:

$$
\boxed{ h(k)=-h(N-1-k) }
$$

The chapter discusses symmetric FIR realization.

The symmetry can be exploited to reduce the number of multipliers.

---

# 42. Odd Number of Samples — Symmetric FIR

For odd $N$:

$$
N=11
$$

for example:

$$
\begin{aligned} h(0)&=h(10)\\ h(1)&=h(9)\\ h(2)&=h(8)\\ h(3)&=h(7)\\ h(4)&=h(6) \end{aligned}
$$

The middle sample is:

$$
\boxed{ k=\frac{N-1}{2} }
$$

For $N=11$:

$$
k=5
$$

The paired terms are combined before multiplication.

For example:

$$
h(0) [ X(z)+z^{-(N-1)}X(z) ]
$$

$$
h(1) [ z^{-1}X(z)+z^{-(N-2)}X(z) ]
$$

and so on.

The center sample is unpaired.

---

# 43. Even Number of Samples — Symmetric FIR

For even $N$, e.g. $N=8$:

$$
\begin{aligned} h(0)&=h(7)\\ h(1)&=h(6)\\ h(2)&=h(5)\\ h(3)&=h(4) \end{aligned}
$$

There is no actual central sample.

A virtual center occurs at:

$$
\boxed{ k=\frac{N-1}{2} }
$$

For $N=8$:

$$
k=3.5
$$

Again, symmetric pairs are added before multiplication.

---

# 44. Why Linear-Phase Realization Reduces Multipliers

Without exploiting symmetry, every coefficient requires a multiplication.

With symmetry:

$$
h(k)=h(N-1-k)
$$

two input terms share the same coefficient:

$$
h(k) [ x(n-k)+x(n-(N-1-k)) ]
$$

Therefore one multiplier can replace two.

This is the major exam point:

$$
\boxed{ \text{Linear-phase symmetry reduces the number of multipliers.} }
$$

---

# 45. Example 4.16 — FIR Direct Form

Given:

$$
H(z)= 1+\frac15z^{-1} +\frac34z^{-2} +\frac13z^{-3} +\frac17z^{-4} +\frac16z^{-5}
$$

Therefore:

$$
\begin{aligned} Y(z)=& X(z)+\frac15z^{-1}X(z) +\frac34z^{-2}X(z)\\ &+\frac13z^{-3}X(z) +\frac17z^{-4}X(z) +\frac16z^{-5}X(z) \end{aligned}
$$

The direct-form realization is obtained directly from these tapped-delay terms.

---

# 46. Example 4.17 — FIR Factor Multiplication

Given:

$$
H(z)= (1-z^{-1}) (1+2z^{-1}-3z^{-2})
$$

Multiply:

$$
\begin{aligned} H(z) &= 1+2z^{-1}-3z^{-2} -z^{-1}-2z^{-2}+3z^{-3}\\ &= \boxed{ 1+z^{-1}-5z^{-2}+3z^{-3} } \end{aligned}
$$

Hence:

$$
\boxed{ Y(z)= X(z)+z^{-1}X(z)-5z^{-2}X(z)+3z^{-3}X(z) }
$$

This equation directly gives the direct-form realization.

---

# 47. Example 4.18 — Minimum Number of Multipliers

## (a)

Given:

$$
H(z)= \frac13+\frac15z^{-1} +\frac23z^{-2} +\frac15z^{-3} +\frac13z^{-4}
$$

Impulse response:

$$
\boxed{ h(n)= \left\{ \frac13,\frac15,\frac23,\frac15,\frac13 \right\} }
$$

It is symmetric:

$$
h(0)=h(4),\qquad h(1)=h(3)
$$

Therefore linear-phase realization can be used:

$$
\boxed{ Y(z)= \frac13[X(z)+z^{-4}X(z)] + \frac15[z^{-1}X(z)+z^{-3}X(z)] + \frac23z^{-2}X(z) }
$$

This reduces the number of multipliers.

---

## (b)

Given:

$$
H(z)= \frac12+\frac14z^{-1} +\frac14z^{-2} +\frac12z^{-3}
$$

Symmetry:

$$
h(0)=h(3)
$$

$$
h(1)=h(2)
$$

Hence:

$$
\boxed{ Y(z)= \frac12[X(z)+z^{-3}X(z)] + \frac14[z^{-1}X(z)+z^{-2}X(z)] }
$$

---

## (c)

Given:

$$
H(z)= \left( 1+\frac13z^{-1}+z^{-2} \right) \left( 1+\frac15z^{-1}+z^{-2} \right)
$$

Use cascade realization.

Define:

$$
H(z)=H_1(z)H_2(z)
$$

where:

$$
H_1(z)=1+\frac13z^{-1}+z^{-2}
$$

$$
H_2(z)=1+\frac15z^{-1}+z^{-2}
$$

Each second-order section is symmetric and therefore can use a linear-phase minimum-multiplier realization.

---

# 48. IIR vs FIR — Important Comparison

| IIR System | FIR System |
|---|---|
| Infinite impulse response | Finite impulse response |
| Depends on present/past inputs and past outputs | Depends on present/past inputs |
| Poles and zeros | Zeros only |
| Recursive | Non-recursive |
| Not always stable | Stability guaranteed for finite impulse response |
| Closed-loop | Open-loop |
| More complex | Less complex |
| Reliability not always guaranteed | Reliability is high |
| Design often starts from analog prototype | Designed directly in digital domain |
| Sharp magnitude response with lower order | Higher order may be needed for sharp response |
| Does not generally have linear phase | Linear phase can be obtained |
| Finite-word-length effects can cause instability | No recursive instability mechanism |

---

# 49. High-Value Exam Definitions

## Recursive system

A system whose output depends on past outputs as well as input samples.

## Non-recursive system

A system whose output does not depend on past output values.

## Canonical structure

A realization using the minimum required number of delay elements.

## Non-canonical structure

A realization using more delay elements than required by the system order.

## Direct form-I

Direct implementation of the difference equation with separate input and output delay lines.

## Direct form-II

A realization in which the pole and zero sections share a single delay line.

## Transposed form

A realization obtained by reversing all branch directions and interchanging input and output.

## Cascade realization

A realization in which $H(z)$ is factored into a product of lower-order sections.

## Parallel realization

A realization in which $H(z)$ is decomposed into a sum of lower-order sections.

## Reflection coefficient

The coefficient $K$ used in lattice structures.

## Linear-phase FIR

An FIR system satisfying:

$$
h(k)=\pm h(N-1-k)
$$

---

# 50. Important Formula Sheet

## IIR

$$
\boxed{ y(n)= -\sum_{k=1}^{N}a_ky(n-k) +\sum_{k=0}^{M}b_kx(n-k) }
$$

$$
\boxed{ H(z)= \frac{ b_0+b_1z^{-1}+\cdots+b_Mz^{-M} }{ 1+a_1z^{-1}+\cdots+a_Nz^{-N} } }
$$

### Direct form-II

$$
\boxed{ W(z)= X(z)-\sum_{k=1}^{N}a_kz^{-k}W(z) }
$$

$$
\boxed{ Y(z)= \sum_{k=0}^{M}b_kz^{-k}W(z) }
$$

### Cascade

$$
\boxed{ H(z)=\prod_iH_i(z) }
$$

### Parallel

$$
\boxed{ H(z)=C+\sum_iH_i(z) }
$$

### IIR lattice

$$
\boxed{ y(n)= x_{p2}(n) + K_{1p}(1+K_{2p})y(n-1) + K_{2p}y(n-2) }
$$

---

## FIR

$$
\boxed{ y(n)= \sum_{k=0}^{N-1}h(k)x(n-k) }
$$

$$
\boxed{ H(z)= \sum_{k=0}^{N-1}b_kz^{-k} }
$$

$$
\boxed{ b_k=h(k) }
$$

### First-order FIR lattice

$$
\boxed{ y_1(n)=x(n)+K_1x(n-1) }
$$

### Second-order FIR lattice

$$
\boxed{ y_2(n)= x(n)+K_1(1+K_2)x(n-1)+K_2x(n-2) }
$$

### Linear phase

$$
\boxed{ h(k)=\pm h(N-1-k) }
$$

### Symmetric FIR

$$
\boxed{ h(k)=h(N-1-k) }
$$

---

# 51. Exam Workflow — How to Realize a Given System

## If a difference equation is given

### Step 1 — Identify FIR or IIR

- Past outputs present → **IIR / recursive**
- Past outputs absent → **FIR / non-recursive**

### Step 2 — Take z-transform

Replace:

$$
x(n-k)\rightarrow z^{-k}X(z)
$$

$$
y(n-k)\rightarrow z^{-k}Y(z)
$$

### Step 3 — Obtain $H(z)$

$$
\boxed{H(z)=Y(z)/X(z)}
$$

### Step 4 — Select requested structure

- Direct form-I → write the original $Y(z)$ equation directly.
- Direct form-II → introduce $W(z)$.
- Transposed → first form direct realization, then transpose.
- Cascade → factor numerator and denominator.
- Parallel → partial fractions.
- Lattice → compare coefficients with lattice equations.
- Ladder → sequential polynomial division.
- FIR linear phase → check coefficient symmetry first.

---

# 52. Exam Workflow — Direct Form-I

Given:

$$
H(z)= \frac{ b_0+b_1z^{-1}+\cdots+b_Mz^{-M} }{ 1+a_1z^{-1}+\cdots+a_Nz^{-N} }
$$

Write:

$$
Y(z) = -\sum_{k=1}^{N}a_kz^{-k}Y(z) + \sum_{k=0}^{M}b_kz^{-k}X(z)
$$

Then draw:

- input delay line for $b_k$,
- output feedback delay line for $a_k$,
- multipliers,
- final adder.

---

# 53. Exam Workflow — Direct Form-II

Write:

$$
\frac{W(z)}{X(z)} = \frac1{ 1+a_1z^{-1}+\cdots+a_Nz^{-N} }
$$

Therefore:

$$
W(z)= X(z)-a_1z^{-1}W(z)-\cdots-a_Nz^{-N}W(z)
$$

Then:

$$
Y(z)= b_0W(z)+b_1z^{-1}W(z)+\cdots+b_Mz^{-M}W(z)
$$

Use one common delay chain.

---

# 54. Exam Workflow — Cascade

1. Obtain $H(z)$.
2. Factor numerator.
3. Factor denominator.
4. Form first-/second-order sections.
5. Write:
$$
H(z)=H_1(z)H_2(z)\cdots
$$
6. Realize each section.
7. Connect sections in series.

---

# 55. Exam Workflow — Parallel

1. Obtain $H(z)$.
2. Express in partial fractions:
$$
H(z)=C+\sum_iH_i(z)
$$
3. Realize each section.
4. Connect outputs to a common adder.

---

# 56. Exam Workflow — Lattice

## IIR

1. Separate zero and pole parts.
2. Determine order.
3. Compare with lattice equations.
4. Find $K_{1z},K_{2z},\ldots$ and $K_{1p},K_{2p},\ldots$.
5. Draw zero lattice and pole lattice.
6. Cascade them.

## FIR

1. Normalize the coefficient of $x(n)$ to unity.
2. Determine order.
3. Compare:
$$
y_1(n)=x(n)+K_1x(n-1)
$$
   and
$$
y_2(n)=x(n)+K_1(1+K_2)x(n-1)+K_2x(n-2)
$$
4. Find the reflection coefficients.
5. Draw the lattice.

---

# 57. Exam Workflow — Ladder

1. Express numerator and denominator in descending powers of $z^{-1}$.
2. Compare their negative orders.
3. Select:
   - Case-I, or
   - Case-II.
4. Perform sequential division.
5. Match quotients with ladder parameters.
6. Draw the ladder structure.

---

# 58. Exam Workflow — Minimum Multipliers

Before drawing a normal FIR direct form:

### Check symmetry

If:

$$
h(k)=h(N-1-k)
$$

then use linear-phase realization.

Pair:

$$
x(n-k)+x(n-(N-1-k))
$$

and multiply the pair by only one coefficient.

This is usually the key to problems saying:

> **“Realize with minimum number of multipliers.”**

---

# 59. Short Questions with Answers

## 1. What is cascade form realization?

Cascade form is a realization in which the transfer function is expressed as a product of several transfer functions and each section is realized separately; the sections are then cascaded.

## 2. What is parallel form realization?

Parallel form is a realization in which the transfer function is expressed in partial-fraction form and the resulting sections are realized separately and connected in parallel.

## 3. What are recursive and non-recursive systems?

A recursive system depends on present/past inputs and past outputs.

Example:

$$
y(n)=y(n-1)+0.5x(n)+x(n-1)
$$

A non-recursive system does not depend on past outputs.

Example:

$$
y(n)=x(n)+2x(n-1)-1.5x(n-2)
$$

## 4. What are the basic elements of a discrete-time system block diagram?

- Adder
- Constant multiplier
- Unit delay

## 5. What is an IIR system?

An IIR system is one designed using all the infinite samples of the impulse response.

## 6. IIR convolution formula

$$
\boxed{ y(n)=\sum_{k=0}^{\infty}h(k)x(n-k) }
$$

## 7. General IIR difference equation and transfer function

$$
\boxed{ y(n)= -\sum_{k=1}^{N}a_ky(n-k) + \sum_{k=0}^{M}b_kx(n-k) }
$$

$$
\boxed{ H(z)= \frac{\sum_{k=0}^{M}b_kz^{-k}} {1+\sum_{k=1}^{N}a_kz^{-k}} }
$$

## 8. Factors influencing structure selection

- Computational complexity
- Memory requirements
- Finite-word-length effects
- Parallel processing suitability
- Pipelining suitability

## 9. List IIR structures

1. Direct form-I
2. Direct form-II
3. Transposed form
4. Cascade form
5. Parallel form
6. Lattice
7. Ladder

## 10. Advantage of direct form-II

It requires less memory because it uses fewer delay elements.

## 11. Why direct form-I is non-canonical

Because it uses more delay elements than the order of the difference equation.

## 12. Why direct form-II is canonical

Because the number of delay elements is equal to the order of the difference equation.

## 13. Difficulties in cascade realization

- Pole-zero pairing
- Ordering of sections
- Scaling between sections

## 14. Advantage of cascade and parallel realization

They reduce sensitivity of frequency response characteristics to coefficient quantization.

## 15. What is an FIR system?

An FIR system is one designed by selecting a finite number of impulse-response samples.

## 16. FIR convolution formula

$$
\boxed{ y(n)= \sum_{k=0}^{N-1}h(k)x(n-k) }
$$

## 17. FIR difference equation and transfer function

$$
\boxed{ y(n)= \sum_{k=0}^{N-1}b_kx(n-k) }
$$

$$
\boxed{ H(z)= \sum_{k=0}^{N-1}b_kz^{-k} }
$$

## 18. List FIR realization structures

- Direct
- Transposed
- Cascade
- Lattice
- Linear phase

## 19. Linear-phase condition

The impulse response should satisfy:

$$
\boxed{ h(k)=\pm h(N-1-k) }
$$

## 20. Advantage of linear-phase realization

It reduces the number of multipliers required.

---

# 60. Review Questions

1. Discuss the basic elements used to construct the block diagram of discrete-time systems.
2. Explain the factors that influence the choice of structure for realization of an LTI system.
3. Write the difference equations for FIR and IIR systems and derive their transfer functions.
4. Discuss the different methods of IIR realization and explain conversion from direct form-I to direct form-II.
5. Discuss the different methods of FIR realization.
6. Compare FIR and IIR systems.
7. Compare cascade and parallel form realizations.

---

# 61. Fill in the Blanks — With Answers

1. A system whose output depends only on present and past inputs is a **non-recursive** system.
2. A system whose output depends on past outputs is a **recursive** system.
3. The basic elements are **adder, multiplier and unit delay**.
4. **Computational complexity** refers to the number of arithmetic operations.
5. **Memory requirements** refer to the number of memory locations.
6. **Finite-word-length effects** refer to quantization effects.
7. In **IIR** systems, the impulse response has infinite samples.
8. IIR convolution:
$$
\boxed{y(n)=\sum_{k=0}^{\infty}h(k)x(n-k)}
$$
9. IIR difference equation:
$$
\boxed{ y(n)= -\sum_{k=1}^{N}a_ky(n-k) +\sum_{k=0}^{M}b_kx(n-k) }
$$
10. IIR transfer function:
$$
\boxed{ H(z)= \frac{\sum_{k=0}^{M}b_kz^{-k}} {1+\sum_{k=1}^{N}a_kz^{-k}} }
$$
11. IIR structures include **direct form-I, direct form-II, transposed, cascade, parallel, lattice and ladder**.
12. **Direct form-I** provides a direct relation between time-domain and z-domain equations.
13. Direct form-II uses fewer **delay elements**.
14. In **FIR** systems, the impulse response has finite samples.
15. FIR convolution:
$$
\boxed{ y(n)=\sum_{k=0}^{N-1}h(k)x(n-k) }
$$
16. FIR difference equation:
$$
\boxed{ y(n)=\sum_{k=0}^{N-1}b_kx(n-k) }
$$
17. FIR transfer function:
$$
\boxed{ H(z)=\sum_{k=0}^{N-1}b_kz^{-k} }
$$
18. FIR structures: **direct, transposed, cascade, lattice, linear phase**.
19. For linear phase, the **impulse response** should be symmetrical.
20. Linear phase reduces the number of **multipliers**.

---

# 62. Objective Type Questions — Answers

### 1.
A system whose output depends on past output values is:

**Answer: (a) recursive system**

### 2.
A system whose output depends only on present and past inputs is:

**Answer: (b) non-recursive system**

### 3.
The structure using fewer delay elements is:

**Answer: (b) direct form-II**

### 4.
The realization reducing FIR multipliers is:

**Answer: (d) linear phase realization**

---

# 63. Important Problems from the Chapter

## Problem 1

Construct block diagram and signal-flow graph for:

### (a)

$$
y(n)=0.5x(n)+0.5x(n-1)
$$

### (b)

$$
y(n)=0.25y(n-1)+0.5x(n)+0.75x(n-1)
$$

---

## Problem 2

Find direct and transposed networks for:

$$
y(n)= x(n)-0.3x(n-1)-0.7x(n-2) +0.6y(n-1)+0.8y(n-2)
$$

---

## Problem 3

Determine direct form-I and direct form-II realizations:

### (a)

$$
\begin{aligned} y(n)=& -0.5y(n-1)+0.25y(n-2)+0.125y(n-3)\\ &+x(n)+0.5x(n-1)+0.75x(n-2) \end{aligned}
$$

### (b)

$$
\begin{aligned} y(n)=& -\frac38y(n-1)+\frac3{32}y(n-2)+\frac1{64}y(n-3)\\ &+x(n)+3x(n-1)+2x(n-2) \end{aligned}
$$

---

## Problem 4

Given:

$$
y(n)=a_1y(n-1)+x(n)+b_1x(n-1)
$$

Realize it in direct form-I and convert it to direct form-II.

---

## Problem 5

Obtain direct form-I, direct form-II, cascade and parallel realizations for the given LTI systems in the book.

---

## Problem 6

Realize the given systems in cascade and parallel forms.

---

## Problem 7

Realize the IIR system in cascade and parallel forms:

$$
y(n)+\frac14y(n-1)-\frac18y(n-2) = x(n)-2x(n-1)+x(n-2)
$$

---

## Problem 8

Determine direct form-I, direct form-II, cascade and parallel realizations for the two transfer functions given in the chapter.

---

## Problem 9

Realize the following IIR filters using ladder structure:

$$
H(z)= \frac{2z^2+3z+4}{z^2+5z+7}
$$

and

$$
H(z)= \frac{z^3+3z^2+2z+5}{2z^2+z+4}
$$

---

## Problem 10

Realize the second-order IIR system using transposed form:

$$
y(n)+\frac12y(n-1)+\frac14y(n-2) = 2x(n)+x(n-1)+x(n-2)
$$

---

## Problem 11

Determine lattice coefficients for:

$$
y(n)-\frac12y(n-1)+\frac17y(n-2) = x(n)+\frac15x(n-1)
$$

and realize it.

---

## Problem 12

Draw direct-form structures for the three FIR transfer functions given in the chapter.

---

## Problem 13 — Very Important

Realize the listed FIR systems with the **minimum number of multipliers**.

### Strategy

1. Write the coefficient sequence.
2. Check:
$$
h(k)=h(N-1-k)
$$
3. If symmetric, pair equal coefficients.
4. Use linear-phase realization.
5. Count the remaining multipliers.

---

## Problem 14

Realize the two given FIR systems in cascade form.

---

## Problem 15

Realize:

$$
\boxed{ y(n)=3x(n)+5x(n-1)-2x(n-2) }
$$

using transposed form.

---

## Problem 16

Determine lattice coefficients for:

$$
\boxed{ H(z)=1+\frac5{12}z^{-1}+\frac23z^{-2} }
$$

and realize it.

---

# 64. Fast Comparison Table for Exam Revision

| Topic | Key fact |
|---|---|
| IIR | Infinite impulse response |
| FIR | Finite impulse response |
| IIR type | Recursive |
| FIR type | Non-recursive |
| IIR convolution | Infinite sum |
| FIR convolution | Finite sum |
| IIR transfer function | Numerator / denominator |
| FIR transfer function | Polynomial in $z^{-1}$ |
| Direct-I | Separate input/output delay chains |
| Direct-II | Shared delay chain |
| Direct-I | Non-canonical |
| Direct-II | Canonical |
| Transposed | Reverse branches + swap input/output |
| Cascade | Product of sections |
| Parallel | Sum of sections |
| IIR lattice | Zero lattice + pole lattice |
| Ladder | Sequential polynomial division |
| FIR lattice | Reflection coefficients |
| Linear phase | Symmetric/antisymmetric $h(n)$ |
| Minimum multipliers | Use coefficient symmetry |

---

# 65. Common Exam Mistakes

### Mistake 1 — Forgetting the signs of feedback coefficients

If:

$$
y(n)= -a_1y(n-1)+\cdots
$$

then the feedback multiplier is associated with $-a_1$.

Always derive the actual $Y(z)$ equation before drawing.

### Mistake 2 — Confusing direct-I and direct-II

Remember:

- **Direct-I:** separate delay lines.
- **Direct-II:** one shared delay line.

### Mistake 3 — Wrong order in direct-II

Direct-II realizes:

> poles first → zeros second.

### Mistake 4 — Forgetting normalization

If the non-delay denominator coefficient is not unity, normalize the denominator before applying standard structures.

### Mistake 5 — Using cascade when the question asks parallel

- Cascade → factor/product.
- Parallel → partial fractions/sum.

### Mistake 6 — Missing symmetry in FIR

Before drawing a minimum-multiplier FIR structure, always inspect:

$$
h(k)=h(N-1-k)
$$

### Mistake 7 — Treating FIR and IIR the same

FIR:

$$
H(z)=b_0+b_1z^{-1}+\cdots
$$

IIR:

$$
H(z)= \frac{b_0+b_1z^{-1}+\cdots} {1+a_1z^{-1}+\cdots}
$$

---

# 66. MATLAB Programs from the Chapter

## Program 4.1 — Parallel Form Realization of IIR Filters

```matlab
% Parallel form realization of IIR filters

clc; clear all; close all;

num=[2 10 23 34 31 16 4];
den=[36 78 87 59 26 7 1];

[r1 p1 k1]=residuez(num,den);
[r2 p2 k2]=residue(num,den);

disp('parallel form 1')
disp('residues are')
disp(r1)

disp('poles are at')
disp(p1)

disp('constant value')
disp(k1)

disp('parallel form II')
disp('residues are')
disp(r2)

disp('poles are at')
disp(p2)

disp('constant value')
disp(k2)
```

---

## Program 4.2 — Direct Form to Cascade Form Conversion

```matlab
% Direct form to cascade form conversion

clc; clear all; close all;

b=[4 5 6]; % numerator coefficients
a=[1 2 3]; % denominator coefficients

b0=b(1);
a0=a(1);

b=b/b0;
a=a/a0;

m=length(b);
n=length(a);

if n > m
    b=[b zeros(1,n-m)];
elseif m > n
    a=[a zeros(1,m-n)];
end

k=floor(n/2);

B=zeros(k,3);
A=zeros(k,3);

if k*2==n
    b=[b 0];
    a=[a 0];
end

broots=cplxpair(roots(b));
aroots=cplxpair(roots(a));

for i=1:2:2*k
    brow=broots(i:i+1,:);
    brow=real(poly(brow));
    B(fix((i+1)/2),:)=brow;

    arow=aroots(i:i+1,:);
    arow=real(poly(arow));
    A(fix((i+1)/2),:)=arow;
end

disp('numerator coefficients of cascade form')
disp(brow)

disp('denominator coefficients of cascade form')
disp(arow)
```

---

## Program 4.3 — Cascade Form Realization of FIR and IIR Filters

```matlab
% Cascade form realization of FIR & IIR filters

clc; clear all; close all;

b=[4 5 6];
a=[1 2 3];

b0=b(1);

x=[1 2 3 8 9 4 6 7 10];

[k l]=size(b);
n=length(x);

w=zeros(k+1,n);
w(1,:)=x;

for i=1:k
    w(i+1,:)=filter(b(i,:),a(i,:),w(i,:));
end

y=b0*w(k+1,:);

disp('output of the final filter operation')
disp(y)
```

---

## Program 4.4 — Cascade Form to Direct Form

```matlab
% Cascade form to direct form conversion

clc; clear all; close all;

B=[4 5 6];
A=[1 2 3];

b0=B(1);

[k l]=size(B);

b=[1];
a=[1];

for i=1:k
    b=conv(b,B(i,:));
    a=conv(a,A(i,:));
end

b=b*b0;

disp('numerator coefficients of direct form')
disp(b)

disp('denominator coefficients of direct form')
disp(a)
```

---

## Program 4.5 — Direct Form to Parallel Form Conversion

```matlab
% Direct form to parallel form conversion

clc; clear all; close all;

b=[4 5 6];
a=[1 2 3];

m=length(b);
n=length(a);

[r1 p1 c]=residuez(b,a);

p=cplxpair(p1,10000000*eps);
p2=cplxpair(p1);

I=[];

for j=1:length(p2)
    for i=1:length(p1)
        if(abs(p1(i)-p2(j)) < 0.0001)
            I=[I,i];
        end
    end
end

I=I';
r=r1(I);

K=floor(n/2);

B=zeros(K,2);
A=zeros(K,3);

if K*2==n

    for i=1:2:n-2
        Brow=r(i:i+1,:);
        Arow=p(i:i+1,:);

        [Brow Arow]=residuez(Brow,Arow,[]);

        B(fix((i+1)/2),:)=real(Brow);
        A(fix((i+1)/2),:)=real(Arow);
    end

    [Brow Arow]=residuez(r(n-1),p(n-1),[]);

    B(K,:)=[real(Brow) 0];
    A(K,:)=[real(Arow) 0];

else

    for i=1:2:n-1
        Brow=r(i:i+1,:);
        Arow=p(i:i+1,:);

        [Brow Arow]=residuez(Brow,Arow,[]);

        B(fix((i+1)/2),:)=real(Brow);
        A(fix((i+1)/2),:)=real(Arow);
    end
end

disp('numerator coefficients of parallel form')
disp(B)

disp('denominator coefficients of parallel form')
disp(A)
```

---

## Program 4.6 — Parallel Form to Direct Form Conversion

```matlab
% Parallel form to direct form conversion

clc; clear all; close all;

C=[0];

A=[1 1 0.9;
   1 0.4 -0.4];

B=[2 4;
   3 1];

[K,L]=size(A);

R=[];
P=[];

for i=1:K
    [r p k]=residuez(B(i,:),A(i,:));
    R=[R;r];
    P=[P;p];
end

[b a]=residuez(R,P,C);

b=b(:)';
a=a(:)';

disp('numerator coefficients of direct form')
disp(b)

disp('denominator coefficients of direct form')
disp(a)
```

---

# 67. MATLAB Functions Worth Remembering

From the chapter programs:

| Function | Purpose |
|---|---|
| `residuez` | Partial-fraction/residue representation for digital filters |
| `residue` | Residue calculation |
| `roots` | Find polynomial roots |
| `poly` | Form polynomial from roots |
| `cplxpair` | Arrange complex-conjugate pairs |
| `filter` | Filter an input sequence |
| `conv` | Convolution |

---

# 68. Last-Minute Revision Sheet

## If asked: “What is realization?”

$$
\boxed{ \text{Conversion of a difference equation or transfer function into a network.} }
$$

## If asked: “Basic elements?”

$$
\boxed{ \text{Adder + Constant multiplier + Unit delay} }
$$

## If asked: “Canonical IIR structure?”

$$
\boxed{\text{Direct form-II}}
$$

## If asked: “Non-canonical IIR structure?”

$$
\boxed{\text{Direct form-I}}
$$

## If asked: “Which has fewer delays?”

$$
\boxed{\text{Direct form-II}}
$$

## If asked: “Cascade means?”

$$
\boxed{H(z)=H_1(z)H_2(z)\cdots}
$$

## If asked: “Parallel means?”

$$
\boxed{H(z)=C+\sum_iH_i(z)}
$$

## If asked: “FIR linear-phase condition?”

$$
\boxed{h(k)=\pm h(N-1-k)}
$$

## If asked: “Why linear phase?”

$$
\boxed{\text{Reduces number of multipliers.}}
$$

## If asked: “FIR lattice coefficient?”

For second order:

$$
\boxed{ K_2=b_2,\qquad K_1=\frac{b_1}{1+K_2} }
$$

when the present-input coefficient has first been normalized to unity.

## If asked: “IIR lattice?”

Separate:

$$
\boxed{\text{zero lattice + pole lattice}}
$$

## If asked: “Ladder realization?”

$$
\boxed{\text{Sequential polynomial division}}
$$

---

# 69. Chapter 4 — Most Important Long-Answer Questions

Prepare these especially well:

1. **Explain realization of discrete-time systems and the basic realization elements.**
2. **Derive the general IIR transfer function from the difference equation.**
3. **Explain direct form-I with structure, equations, advantages and limitations.**
4. **Explain direct form-II and compare it with direct form-I.**
5. **Explain conversion of direct form-I into direct form-II.**
6. **Explain transposed form realization and its procedure.**
7. **Explain cascade and parallel IIR realization and compare them.**
8. **Explain IIR lattice realization and determine reflection coefficients.**
9. **Explain ladder realization and the two cases.**
10. **Derive the FIR transfer function.**
11. **Explain direct, transposed, cascade and lattice FIR realizations.**
12. **Explain linear-phase FIR realization and multiplier reduction.**
13. **Compare FIR and IIR systems.**
14. **Solve a complete realization problem using direct-I, direct-II, cascade and parallel forms.**

---

# 70. One-Page Memory Map

```text
SYSTEM REALIZATION
│
├── Basic elements
│   ├── Adder
│   ├── Constant multiplier
│   └── Unit delay z^-1
│
├── IIR
│   ├── Infinite impulse response
│   ├── Recursive
│   ├── Direct Form-I
│   │   └── non-canonical
│   ├── Direct Form-II
│   │   └── canonical
│   ├── Transposed
│   ├── Cascade
│   ├── Parallel
│   ├── Lattice
│   └── Ladder
│
└── FIR
    ├── Finite impulse response
    ├── Non-recursive
    ├── Direct
    ├── Transposed
    ├── Cascade
    ├── Lattice
    └── Linear Phase
        └── symmetry → fewer multipliers
```

---

# 71. Final Exam Strategy

When a realization question appears:

$$
\boxed{ \text{Difference equation} \rightarrow Z\text{-transform} \rightarrow H(z) \rightarrow \text{requested structure} }
$$

Then remember:

$$
\boxed{ \begin{array}{ll} \text{Direct-I} &\rightarrow \text{separate delay lines}\\ \text{Direct-II} &\rightarrow \text{shared delay line}\\ \text{Transposed} &\rightarrow \text{reverse + interchange}\\ \text{Cascade} &\rightarrow \text{factor/product}\\ \text{Parallel} &\rightarrow \text{partial fractions/sum}\\ \text{Lattice} &\rightarrow \text{reflection coefficients}\\ \text{Ladder} &\rightarrow \text{sequential division}\\ \text{Linear phase} &\rightarrow \text{symmetry + fewer multipliers} \end{array} }
$$

**This is the central decision tree for Chapter 4.**
