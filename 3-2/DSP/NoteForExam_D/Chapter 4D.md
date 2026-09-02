# Chapter 4: System Realization

## 4.1 INTRODUCTION

**Realization**: Obtaining a network corresponding to the difference equation or transfer function of the system.

### Basic Elements for Block Diagram:

1. **Adder**: Adds two or more signals.
2. **Constant Multiplier**: Multiplies a signal by a constant.
3. **Unit Delay Element**: Delays a signal by one sampling time.

### Symbols:

```text
Adder: 
       x1(n) ──┐
               ├──► x1(n) + x2(n)
       x2(n) ──┘

Multiplier: 
       x(n) ───[a]───► a·x(n)

Delay: 
       x(n) ───[z⁻¹]───► x(n-1)
```

---

## 4.2 REALIZATION OF DISCRETE-TIME SYSTEMS

### IIR Systems (Infinite Impulse Response):
* Impulse response has an infinite number of samples.
* Output depends on present and past inputs AND past outputs.
* Recursive type.
* Has both poles and zeros.

### FIR Systems (Finite Impulse Response):
* Impulse response has a finite number of samples.
* Output depends only on present and past inputs.
* Non-recursive type.
* Has only zeros.

---

## 4.3 STRUCTURES FOR REALIZATION OF IIR SYSTEMS

### General IIR Difference Equation:
$$y(n) = \sum_{k=1}^{N} a_k y(n-k) + \sum_{k=0}^{M} b_k x(n-k)$$

### General IIR Transfer Function:
$$H(z) = \frac{Y(z)}{X(z)} = \frac{b_0 + b_1z^{-1} + b_2z^{-2} + \dots + b_Mz^{-M}}{1 + a_1z^{-1} + a_2z^{-2} + \dots + a_Nz^{-N}}$$

### Factors Influencing Choice of Structure:
1. Computational complexity
2. Memory requirements
3. Finite word-length effects (quantization noise)
4. Parallel processing capability
5. Pipelining capability

---

## EXAMPLE 4.1: Construct block diagram

**(a)** $y(n) = 0.7x(n) + 0.3x(n-1)$

### Solution:
**Step 1: Identify terms**
* Term 1: $0.7x(n)$ (present input scaled by 0.7)
* Term 2: $0.3x(n-1)$ (delayed input scaled by 0.3)

**Step 2: Draw block diagram**
```text
x(n) ──┬──[0.7]──┐
       │         ├──[+]──► y(n)
       └──[z⁻¹]──[0.3]──┘
```

**Alternatively using Z-transform:**
$$Y(z) = 0.7X(z) + 0.3z^{-1}X(z)$$

---

**(b)** $y(n) = 0.5y(n-1) + 0.8x(n) + 0.4x(n-1)$

### Solution:
**Step 1: Identify terms**
* Term 1: $0.5y(n-1)$ (delayed output scaled by 0.5)
* Term 2: $0.8x(n)$ (present input scaled by 0.8)
* Term 3: $0.4x(n-1)$ (delayed input scaled by 0.4)

**Step 2: Draw block diagram**
```text
x(n) ──┬──[0.8]─────────┐
       │                │
       └──[z⁻¹]──[0.4]──┤
                        │
                  ┌────[+]────► y(n)
                  │
y(n) ──[z⁻¹]──[0.5]─────┘
```

---

## 4.3.1 Direct Form-I Structure

### Derivation:
$$Y(z) = \sum_{k=1}^{N} a_k z^{-k}Y(z) + \sum_{k=0}^{M} b_k z^{-k}X(z)$$

**Properties:**
* Separate delays for input and output.
* Non-canonical (uses more delay elements than the order of the system).
* Direct relation between time and z-domain.

**Disadvantages:**
* More memory required.
* Lacks hardware flexibility.
* Chances of instability due to quantization noise.

---

## 4.3.2 Direct Form-II Structure

### Derivation:
Let $W(z)$ be an intermediate variable such that:
$$W(z) = \frac{X(z)}{1 + \sum_{k=1}^{N} a_k z^{-k}}$$
and
$$Y(z) = \sum_{k=0}^{M} b_k z^{-k}W(z)$$

**Properties:**
* Single delay line for both input and output.
* Canonical (uses the minimum number of delays).
* More efficient memory-wise.

### Comparison of Direct Form-I and Direct Form-II:

| Feature | Direct Form-I | Direct Form-II |
|---------|---------------|----------------|
| **Delays** | Separate for input/output | Single delay line |
| **Memory** | More | Less |
| **Type** | Non-canonical | Canonical |
| **Efficiency** | Less efficient | More efficient |

---

## EXAMPLE 4.2: Transposed form realization

**Given**: $y(n) + 2y(n-1) + 3y(n-2) = 4x(n) + 5x(n-1) + 6x(n-2)$

### Solution:
**Step 1: Find H(z)**
$$H(z) = \frac{4 + 5z^{-1} + 6z^{-2}}{1 + 2z^{-1} + 3z^{-2}}$$

**Step 2: Direct Form-II Structure**
The direct form-II structure uses:
* $a_1 = 2, a_2 = 3$ (denominator coefficients, fed back with opposite signs usually)
* $b_0 = 4, b_1 = 5, b_2 = 6$ (numerator coefficients)

**Step 3: Transposed Structure**
The transposed structure is obtained by:
1. Reversing the direction of all branches.
2. Interchanging input and output.
3. Replacing adders with pickoff points (nodes) and vice versa.

```text
x(n) ──┬──[4]──────────┐
       │               │
       └──[z⁻¹]──[5]───┼──┐
       │               │  │
       └──[z⁻¹]──[6]───┼──┼──┐
                       │  │  │
y(n) ──[+2]────────────┼──┘  │
       │               │     │
       └──[+3]─────────┘     │
```
*(Note: Diagram is simplified to show concept of reversal)*

---

## 4.3.3 Cascade Form Realization

In cascade form, $H(z)$ is expressed as the product of second-order (or first-order) sections:
$$H(z) = \prod_{i=1}^{k} H_i(z)$$

Where:
* $H_i(z) = \frac{C_{i0} + C_{i1}z^{-1} + C_{i2}z^{-2}}{D_{i0} + D_{i1}z^{-1} + D_{i2}z^{-2}}$ (second-order)
* $H_i(z) = \frac{C_{i0} + C_{i1}z^{-1}}{D_{i0} + D_{i1}z^{-1}}$ (first-order)

**Advantage**: Less sensitivity to coefficient quantization.
**Difficulties**:
1. Pairing poles and zeros.
2. Ordering sections.
3. Scaling multipliers needed.

---

## 4.3.4 Parallel Form Realization

In parallel form, $H(z)$ is expressed as the sum of first-order and second-order sections using partial fractions:
$$H(z) = C + \sum_{i=1}^{k} H_i(z)$$

**Advantage**: High-speed processing (due to parallel operation).
**Difficulty**: Partial fraction expansion for higher orders is complex.

---

## 4.3.5 Lattice Structure for IIR Systems

The lattice structure consists of two paths through which the input is processed.

### Single-Stage Lattice:
```text
x_p(n) ──┬─────────────► y_p(n)
         │
         └──[K1]──┐
                  │
         ┌────────┘
         │
         └──[z⁻¹]──────► x'_p(n)
```

**Output equations:**
$$y(n) = x_p(n) + K_1 y(n-1)$$
$$x'_p(n) = K_1 y(n) + y(n-1)$$

### Two-Stage Lattice:
For second order:
$$y(n) = x(n) + K_1(1+K_2)x(n-1) + K_2x(n-2)$$

### Reflection Coefficients:
$$K_1 = \frac{a_1}{1+a_2}$$
$$K_2 = a_2$$

---

## 4.3.6 Ladder Structure for IIR Systems

Used when numerator order is greater than denominator order.

### Case I: Numerator order > Denominator order
$$H(z) = \frac{b_0 + b_1z^{-1} + \dots + b_{N}z^{-N}}{a_0 + a_1z^{-1} + \dots + a_Nz^{-N}}$$

Expressed as continuous fractions:
$$H(z) = \alpha_0 + \frac{1}{\beta_1 + \frac{1}{\alpha_1 + \frac{1}{\beta_2 + \dots}}}$$

### Case II: Numerator order = Denominator order
$$H(z) = \alpha_0 + \frac{\beta_0}{\alpha_1 + \frac{1}{\beta_1 + \frac{1}{\alpha_2 + \dots}}}$$

---

## EXAMPLE 4.6: Direct Form-I, Direct Form-II, Cascade, Parallel Realizations

**Given**: $y(n) = \frac{13}{12}y(n-1) - \frac{9}{24}y(n-2) + \frac{1}{24}y(n-3) + x(n) + 4x(n-1) + 3x(n-2)$

### Solution:

**(a) Direct Form-I:**
**Step 1: Take Z-transform**
$$Y(z) = \frac{13}{12}z^{-1}Y(z) - \frac{9}{24}z^{-2}Y(z) + \frac{1}{24}z^{-3}Y(z) + X(z) + 4z^{-1}X(z) + 3z^{-2}X(z)$$

**Step 2: Draw Direct Form-I**
```text
x(n) ──┬──[1]─────────┬────┐
       │              │    │
       └──[z⁻¹]──[4]──┤    │
       │              │    │
       └──[z⁻¹]──[3]──┤    │
                      │    │
                 ┌───[+]───┴► y(n)
                 │
y(n) ──[z⁻¹]──[13/12]─┐
       │              │
       ├──[z⁻¹]──[-9/24]
       │              │
       └──[z⁻¹]──[1/24]
```

**(b) Direct Form-II:**
**Step 1: Transfer function**
$$H(z) = \frac{1 + 4z^{-1} + 3z^{-2}}{1 + \frac{13}{12}z^{-1} - \frac{9}{24}z^{-2} + \frac{1}{24}z^{-3}}$$

**Step 2: Let W(z) = X(z)/D(z) and Y(z) = N(z)W(z)**
$$W(z) = X(z) - \frac{13}{12}z^{-1}W(z) + \frac{9}{24}z^{-2}W(z) - \frac{1}{24}z^{-3}W(z)$$
$$Y(z) = W(z) + 4z^{-1}W(z) + 3z^{-2}W(z)$$

**(c) Cascade Form:**
$$H(z) = \frac{(1+z^{-1})(1+3z^{-1})}{(1+\frac{1}{2}z^{-1})(1+\frac{1}{3}z^{-1})(1+\frac{1}{4}z^{-1})}$$

Thus, it can be broken down into:
$$H_1(z) = \frac{1+z^{-1}}{1+\frac{1}{2}z^{-1}}$$
$$H_2(z) = \frac{1+3z^{-1}}{1+\frac{1}{3}z^{-1}}$$
$$H_3(z) = \frac{1}{1+\frac{1}{4}z^{-1}}$$

**(d) Parallel Form:**
Using partial fractions:
$$H(z) = \frac{30}{1+\frac{1}{2}z^{-1}} - \frac{128}{1+\frac{1}{3}z^{-1}} + \frac{99}{1+\frac{1}{4}z^{-1}}$$

---

## 4.4 STRUCTURES FOR REALIZATION OF FIR SYSTEMS

### General FIR Difference Equation:
$$y(n) = \sum_{k=0}^{N-1} b_k x(n-k)$$

### General FIR Transfer Function:
$$H(z) = \sum_{k=0}^{N-1} b_k z^{-k} = b_0 + b_1z^{-1} + \dots + b_{N-1}z^{-(N-1)}$$

### Types of Structures:
1. Direct Form
2. Transposed Form
3. Cascade Form
4. Lattice Structure
5. Linear Phase Realization

---

## 4.4.1 Direct Form Realization
$$Y(z) = b_0X(z) + b_1z^{-1}X(z) + \dots + b_{N-1}z^{-(N-1)}X(z)$$

```text
x(n) ──┬──[b₀]──┬──► y(n)
       │        │
       └──[z⁻¹]──[b₁]──┐
       │        │
       └──[z⁻¹]──[b₂]──┘
       │
       └─── ...
```
**Also called**: Tapped-delay-line filter or Transversal filter.
**Canonical structure**: Number of delays equals the order of the filter.

---

## 4.4.2 Transposed Form for FIR

```text
x(n) ──┬──[b₀]─────────┐
       │               │
       └──[z⁻¹]──[b₁]──┼──┐
       │               │  │
       └──[z⁻¹]──[b₂]──┼──┼──┐
                       │  │  │
                       └──[+]──► y(n)
```

---

## 4.4.3 Cascade Form for FIR
$$H(z) = \prod_{i=1}^{k} H_i(z)$$
Each $H_i(z)$ is of the form:
* $H_i(z) = C_{i0} + C_{i1}z^{-1} + C_{i2}z^{-2}$ (second order)
* $H_i(z) = C_{i0} + C_{i1}z^{-1}$ (first order)

---

## 4.4.4 Lattice Structure for FIR Systems

### Single Stage:
$$y(n) = x(n) + K_1x(n-1)$$
$$y'(n) = K_1x(n) + x(n-1)$$

### Two Stages:
$$y(n) = x(n) + K_1(1+K_2)x(n-1) + K_2x(n-2)$$
$$y'(n) = K_2x(n) + K_1(1+K_2)x(n-1) + x(n-2)$$

### For m-th Order:
$$y_m(n) = \sum_{k=0}^{m} h_m(k)x(n-k)$$
where $h_m(k)$ are the impulse response coefficients.

---

## 4.4.5 Linear Phase Realization

**Condition**: $h(n) = \pm h(N-1-n)$

### Odd Symmetry (N odd):
* $h(0) = h(N-1), h(1) = h(N-2), \dots$
* Centre sample at $(N-1)/2$

### Even Symmetry (N even):
* $h(0) = h(N-1), h(1) = h(N-2), \dots$
* Virtual centre at $(N-1)/2$

**Advantage**: Reduces the number of multipliers required by half.

---

## EXAMPLE 4.13: Transposed form for FIR

**Given**: $y(n) = 2x(n) + 4x(n-1) - 3x(n-2)$

### Solution:

**Step 1: Direct Form**
```text
x(n) ──┬──[2]──┬──► y(n)
       │       │
       └──[z⁻¹]──[4]──┤
       │       │
       └──[z⁻¹]──[-3]─┘
```

**Step 2: Transposed Form**
```text
x(n) ──┬──[2]─────────┐
       │              │
       ├──[z⁻¹]──[4]──┼──┐
       │              │  │
       └──[z⁻¹]──[-3]─┼──┼──┐
                      │  │  │
                      └──[+]──► y(n)
```

---

## EXAMPLE 4.14: Lattice realization for FIR

**Given**: $H(z) = 5 + 3z^{-1}$

### Solution:

**Step 1: Identify order (first order)**
$$H(z) = 5\left(1 + \frac{3}{5}z^{-1}\right) = 5(1 + K_1z^{-1})$$

**Step 2: Find reflection coefficient**
$$K_1 = \frac{3}{5}$$

**Step 3: Draw lattice structure**
```text
x(n) ──┬────────────► y(n) = 5x(n) + 3x(n-1)
       │
       └──[K₁=3/5]──┐
       │            │
       ┌────────────┘
       │
       └──[z⁻¹]─────► y'(n)
```
*(Note: A scaling factor of 5 would also be applied at the output)*

---

## EXAMPLE 4.15: Lattice coefficients for FIR

**Given**: $H(z) = 1 + \frac{7}{9}z^{-1} + \frac{3}{5}z^{-2}$

### Solution:

**Step 1: Compare with standard second-order lattice**
$$y(n) = x(n) + K_1(1+K_2)x(n-1) + K_2x(n-2)$$

**Step 2: Identify coefficients**
$$K_2 = \frac{3}{5}$$
$$K_1(1+K_2) = \frac{7}{9}$$
$$K_1 = \frac{7/9}{1+3/5} = \frac{7/9}{8/5} = \frac{35}{72}$$

$$\boxed{K_1 = \frac{35}{72}, \quad K_2 = \frac{3}{5}}$$

**Step 3: Lattice structure**
```text
x(n) ──┬──────────┬──────────► y(n)
       │          │
       └──[K₁]──┐ └──[K₂]──┐
       │        │ │        │
       ┌────────┘ ┌────────┘
       │          │
       └──[z⁻¹]───┴──[z⁻¹]───► y'(n)
```

---

## EXAMPLE 4.16: Direct form FIR structure

**Given**: $H(z) = 1 + \frac{1}{5}z^{-1} + \frac{3}{4}z^{-2} + \frac{1}{3}z^{-3} + \frac{1}{7}z^{-4} + \frac{1}{6}z^{-5}$

### Solution:

**Step 1: Identify coefficients**
$$b_0 = 1, \quad b_1 = \frac{1}{5}, \quad b_2 = \frac{3}{4}, \quad b_3 = \frac{1}{3}, \quad b_4 = \frac{1}{7}, \quad b_5 = \frac{1}{6}$$

**Step 2: Direct form structure**
```text
x(n) ──┬──[1]─────┬──► y(n)
       │          │
       ├──[z⁻¹]──[1/5]
       │          │
       ├──[z⁻¹]──[3/4]
       │          │
       ├──[z⁻¹]──[1/3]
       │          │
       ├──[z⁻¹]──[1/7]
       │          │
       └──[z⁻¹]──[1/6]
```

---

## EXAMPLE 4.17: Direct form for FIR

**Given**: $H(z) = (1-z^{-1})(1+2z^{-1}-3z^{-2})$

### Solution:

**Step 1: Multiply factors**
$$H(z) = (1-z^{-1})(1+2z^{-1}-3z^{-2})$$
$$= 1+2z^{-1}-3z^{-2} - z^{-1} - 2z^{-2} + 3z^{-3}$$
$$= 1 + z^{-1} - 5z^{-2} + 3z^{-3}$$

**Step 2: Difference equation**
$$y(n) = x(n) + x(n-1) - 5x(n-2) + 3x(n-3)$$

**Step 3: Direct form structure**
```text
x(n) ──┬──[1]──┬──► y(n)
       │       │
       ├──[z⁻¹]──[1]
       │       │
       ├──[z⁻¹]──[-5]
       │       │
       └──[z⁻¹]──[3]
```

---

## EXAMPLE 4.18: Linear phase realization

**(a)** $H(z) = \frac{1}{3} + \frac{1}{5}z^{-1} + \frac{2}{3}z^{-2} + \frac{1}{5}z^{-3} + \frac{1}{3}z^{-4}$

### Solution:

**Step 1: Check symmetry**
* $h(0) = h(4) = \frac{1}{3}$ ✓
* $h(1) = h(3) = \frac{1}{5}$ ✓
* $h(2) = \frac{2}{3}$

**Step 2: Linear phase realization**
$$Y(z) = \frac{1}{3}[X(z) + z^{-4}X(z)] + \frac{1}{5}[z^{-1}X(z) + z^{-3}X(z)] + \frac{2}{3}z^{-2}X(z)$$

**Step 3: Draw structure (reduced multipliers from 5 to 3)**
```text
x(n) ──┬──[z⁻¹]──┬──[z⁻¹]──┬──[z⁻¹]──┬──[z⁻¹]──┐
       │         │         │         │         │
       │         │         └──[2/3]──┤         │
       │         │                   │         │
       │         └───────[+]──[1/5]──┤         │
       │                  ^          │         │
       └──────────────────|──────────|─────────┘
                          v          │
                        [+]──[1/3]───┴─────────► y(n)
```

---

**(b)** $H(z) = \frac{1}{2} + \frac{1}{4}z^{-1} + \frac{1}{4}z^{-2} + \frac{1}{2}z^{-3}$

### Solution:

**Step 1: Check symmetry**
* $h(0) = h(3) = \frac{1}{2}$ ✓
* $h(1) = h(2) = \frac{1}{4}$ ✓

**Step 2: Linear phase realization (N=4 even)**
$$Y(z) = \frac{1}{2}[X(z) + z^{-3}X(z)] + \frac{1}{4}[z^{-1}X(z) + z^{-2}X(z)]$$

---

# SHORT QUESTIONS WITH ANSWERS

**1. What is cascade form realization?**
Cascade form realization expresses the transfer function as a product of several smaller transfer functions, each realized in direct form-II and cascaded together.

**2. What is parallel form realization?**
Parallel form realization expresses the transfer function in partial fractions, with each factor realized in direct form and connected in parallel.

**3. What is recursive and non-recursive system?**
* **Recursive**: Output depends on present and past inputs as well as past outputs. (Example: $y(n) = y(n-1) + 0.5x(n)$)
* **Non-recursive**: Output depends only on present and past inputs. (Example: $y(n) = x(n) + 2x(n-1)$)

**4. What are the basic elements used to construct block diagram?**
1. Adder
2. Constant multiplier
3. Unit delay element

**5. What is an IIR system?**
A system (Infinite Impulse Response) designed by selecting all infinite samples of the impulse response.

**6. What is an FIR system?**
A system (Finite Impulse Response) designed by selecting only a finite number of samples of the impulse response.

**7. Why Direct Form-I is called non-canonical?**
Because the number of delay elements used is more than the order of the difference equation.

**8. Why Direct Form-II is called canonical?**
Because the number of delay elements used is exactly equal to the order of the difference equation.

**9. What are the difficulties in cascade realization?**
1. Pairing poles and zeros.
2. Ordering sections.
3. Scaling multipliers needed.

**10. What is the advantage of linear phase realization?**
It reduces the number of multipliers required by half due to coefficient symmetry.

---

# FILL IN THE BLANKS

1. A system whose output depends only on present and past inputs is called a **non-recursive** system.
2. A system whose output depends on past outputs is called a **recursive** system.
3. The basic elements used to construct block diagram are **adder**, **constant multiplier**, and **unit delay element**.
4. **Computational complexity** refers to the number of arithmetic operations required to compute output.
5. **Memory requirements** refers to the number of memory locations required to store parameters.
6. **Finite word-length effects** refer to quantization effects inherent in digital implementation.
7. In **IIR** systems, the impulse response consists of an infinite number of samples.
8. Direct form-II realization uses less number of **delay elements** than direct form-I.
9. In **FIR** systems, the impulse response consists of a finite number of samples.
10. In FIR systems, for linear phase response, the **impulse response** should be symmetrical.
11. Linear phase results in the reduction of **multipliers** required for realization.

---

# OBJECTIVE TYPE QUESTIONS

**1. A system whose output depends on any number of past output values is called:**
(a) recursive
(b) non-recursive
(c) causal
(d) non-causal
**Answer: (a)**

**2. A system whose output depends only on present and past input values is called:**
(a) recursive
(b) non-recursive
(c) causal
(d) non-causal
**Answer: (b)**

**3. The structure which uses less number of delay elements is:**
(a) direct form-I
(b) direct form-II
(c) cascade form
(d) parallel form
**Answer: (b)**

**4. The number of multipliers required for FIR systems is reduced if we choose:**
(a) direct form
(b) cascade form
(c) parallel form
(d) linear phase realization
**Answer: (d)**

---

# PROBLEMS

1. Construct block diagram for:
   (a) $y(n) = 0.5x(n) + 0.5x(n-1)$
   (b) $y(n) = 0.25y(n-1) + 0.5x(n) + 0.75x(n-1)$

2. Find direct and transposed form for:
   $y(n) = x(n) - 0.3x(n-1) - 0.7x(n-2) + 0.6y(n-1) + 0.8y(n-2)$

3. Determine direct form-I and direct form-II for:
   (a) $y(n) = -0.5y(n-1) + 0.25y(n-2) + 0.125y(n-3) + x(n) + 0.5x(n-1) + 0.75x(n-2)$

4. Realize in cascade and parallel forms:
   (a) $H(z) = \frac{1 + 3z^{-1}}{(1 - \frac{1}{2}z^{-1} + \frac{1}{3}z^{-2})(1 - \frac{1}{3}z^{-1} + \frac{1}{5}z^{-2})}$

5. Realize IIR filter using ladder structure:
   (a) $H(z) = \frac{z^2 + 3z + 4}{z^2 + 5z + 7}$
   (b) $H(z) = \frac{z^3 + 3z^2 + 2z + 5}{2z^2 + z + 4}$

6. Determine lattice coefficients for FIR:
   $H(z) = 1 + \frac{5}{12}z^{-1} + \frac{2}{3}z^{-2}$

7. Draw direct form for:
   $H(z) = 1 + \frac{1}{3}z^{-1} + \frac{1}{2}z^{-2} + \frac{1}{4}z^{-3} + \frac{1}{5}z^{-4} + \frac{1}{7}z^{-5}$

8. Realize with minimum multipliers:
   (a) $H(z) = 1 + 3z^{-1} + 2z^{-2} + 5z^{-3} + 2z^{-4} + 3z^{-5} + z^{-6}$
   (b) $H(z) = 0.2 + 0.6z^{-1} + 0.7z^{-2} + 0.8z^{-3} + 0.9z^{-4} + 0.8z^{-5} + 0.7z^{-6} + 0.6z^{-7} + 0.2z^{-8}$

9. Realize second order FIR using transposed form:
   $y(n) = 3x(n) + 5x(n-1) - 2x(n-2)$

10. Determine lattice coefficients for:
    $H(z) = 1 + \frac{5}{12}z^{-1} + \frac{2}{3}z^{-2}$

---

# SUMMARY OF KEY FORMULAS

| Concept | Formula |
|---------|---------|
| **IIR Difference Equation** | $y(n) = \sum a_k y(n-k) + \sum b_k x(n-k)$ |
| **IIR Transfer Function** | $H(z) = \frac{\sum b_k z^{-k}}{1 + \sum a_k z^{-k}}$ |
| **FIR Difference Equation** | $y(n) = \sum b_k x(n-k)$ |
| **FIR Transfer Function** | $H(z) = \sum b_k z^{-k}$ |
| **Direct Form-II** | $W(z) = X(z) - \sum a_k z^{-k}W(z)$ |
| **Lattice (FIR 1st order)** | $y(n) = x(n) + K_1x(n-1)$ |
| **Lattice (FIR 2nd order)** | $y(n) = x(n) + K_1(1+K_2)x(n-1) + K_2x(n-2)$ |
| **Linear Phase Condition** | $h(n) = \pm h(N-1-n)$ |