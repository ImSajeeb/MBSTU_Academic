# DSP MATLAB — Lab Final Survival Guide

Reorganized **by operation** (not chapter), so all similar code lives in one place instead of being scattered across 7 chapters. Every section below is tagged with **📘 Book ref:** — the exact chapter/problem number and title from your lab manual — so you can jump straight from "which book problem is this again?" to the code, or from a question on the exam to the matching theory and code.

**How to use this during the exam:**
1. Read the question, spot the keyword (e.g. "circular convolution", "check stability").
2. Use the **Decision Table** (by question wording) or the **Book Problem Index** (by chapter/problem number) below to find the section.
3. Read the **Theory** line so you understand *why*, then copy the code and swap the `% <-- CHANGE THIS` values.

⚠️ **One gap to know about:** your uploaded `Full.docx` jumps straight from Chapter 3 to Chapter 5 — **Chapter 4 (Filter Realizations, problems 4.1–4.6) is not in that file**, so I don't have your book's exact code for it. Section 7 below covers those six problems with standard, correct MATLAB implementations, clearly marked as **not verified against your book's exact code** — check them against whatever source you have for that chapter if possible.

---

## 🧭 Decision Table — Question Wording → Section

| If the question says... | Go to |
|---|---|
| "Generate/plot impulse, step, ramp, parabola" | §1.1 |
| "Generate a composite signal using shifted/scaled unit steps" | §1.2 |
| "Plot exponential sequence for different values of a" | §1.3 |
| "Generate a sinusoid / chirp / swept-frequency signal" | §1.4–1.5 |
| "Find x[n−D]" / "delay the signal" | §2.1 |
| "Find x[−n]" / "time-reverse the signal" | §2.2 |
| "Find the even and odd parts of x[n]" | §2.3 |
| "Find y[n] = a·x1[n] + b·x2[n]" | §2.4 |
| "Multiply two signals" (modulation/windowing) | §2.5 |
| "Find the impulse response h[n] of the system" | §3.1 |
| "Find the output y[n] for a given input" | §3.2 |
| "Check whether the system is linear" | §3.3 |
| "Check whether the system is time-invariant" | §3.4 |
| "Check whether the system is stable (BIBO)" | §3.5 |
| "Convolve x1[n] and x2[n]" (plain) | §4.1 |
| "Perform linear convolution using circular convolution / cconv" | §4.2 |
| "Perform linear convolution using DFT/FFT" | §4.3 |
| "Perform N-point circular convolution" | §4.4 |
| "Find auto/cross-correlation" | §4.5 |
| "Find the Z-transform / inverse Z-transform of ..." (symbolic) | §5.1 |
| "Find partial fraction expansion / residues / poles" | §5.2 |
| "Find the inverse Z-transform using long division / power series" | §5.3 |
| "Given cascaded second-order sections, find h[n]" | §5.4 |
| "Multiply two Z-transforms" | §5.5 |
| "Design a Butterworth filter" | §6.1 |
| "Plot pole-zero diagram" | §6.2 |
| "Realize/convert a filter to parallel form" | §7.1, §7.5 |
| "Convert direct form to cascade form" | §7.2 |
| "Realize a cascade-form filter" | §7.3 |
| "Convert cascade form to direct form" | §7.4 |
| "Convert parallel form to direct form" | §7.6 |
| "Find/plot the frequency response H(e^jω) of the system" | §8.1 |
| "Find the DTFT of a sequence" (not a system) | §8.2 |
| "Verify a DTFT property" (shift, modulation, reversal, convolution) | §8.3 |
| "Verify the periodicity property of DTFT" | §8.4 |
| "Find the Fourier Transform of a continuous signal" (symbolic) | §9.1 |
| "Find FT of a rectangular pulse" | §9.2 |
| "Find Fourier Series coefficients of a periodic signal" | §10.1 |
| "Demonstrate Gibbs phenomenon" | §10.2 |
| "Compute DFT using the DFT matrix (dftmtx)" | §11.1 |
| "Find FFT / IFFT of a sequence" | §11.2 |
| "Perform linear/circular convolution using FFT" | §11.3 |
| "Find periodic even/odd parts of a sequence via DFT" | §11.4 |
| "Verify Parseval's theorem" | §11.5 |
| "Verify the circular shift property of DFT" | §11.6 |
| "Plot the magnitude spectrum of a signal" | §11.7 |

---

## 📖 Book Problem Index — Chapter/Problem Number → Guide Section

Same content, indexed the other direction — useful if the exam names a problem by number ("do Program 3.2-style") or if you just want to sanity-check that everything from the book is here.

### Chapter 1 — Signals and Systems
| # | Title | Guide section |
|---|---|---|
| 1.1 | Generation of Elementary Signals in Discrete-time | §1.1 |
| 1.2 | Generation of a Discrete-time Exponential Sequence | §1.3 |
| 1.3 | Multiplication of Discrete-time Signals | §2.5 |
| 1.4 | Even and Odd Components of y(n)=u(n)−u(n−10) | §2.3 |
| 1.5 | Generation of x(n)=u(n+3)+5u(n−15)+4u(n+10) | §1.2 |
| 1.6 | Generation of Swept-frequency Sinusoidal Signal | §1.5 |
| 1.7 | Checking the Time-invariance Property | §3.4 |
| 1.8 | Computation of Impulse Response | §3.1 |
| 1.9 | Checking the Linearity of a System | §3.3 |
| 1.10 | Testing the Stability of a System | §3.5 |

### Chapter 2 — Convolution and Correlation
| # | Title | Guide section |
|---|---|---|
| 2.1 | Convolution of Two Sequences | §4.1 |
| 2.2 | Linear Convolution via Circular Convolution | §4.2 |
| 2.3 | Linear Convolution Using DFT | §4.3 |
| 2.4 | Circular Convolution Using DFT Based Approach | §4.4 |
| 2.5 | Computation of Correlation | §4.5 |

### Chapter 3 — Z-Transform and Related Topics
| # | Title | Guide section |
|---|---|---|
| 3.1 | Z-transform and Inverse Z-transform of Given Signals | §5.1 |
| 3.2 | Finding the Residues of Z³/[(z−0.5)(z−0.75)(z−1)] | §5.2 |
| 3.3 | Inverse Z-transform by the Polynomial Division Method | §5.3 |
| 3.4 | Inverse Z-transform for the Cascaded Form (Polynomial Division) | §5.4 |
| 3.5 | Pole-zero Plot of a Butterworth Band Pass Filter | §6.1 + §6.2 |
| 3.6 | Convolution Using Z-transform | §5.5 |

### Chapter 4 — Filter Realizations ⚠️ *not in your uploaded Full.docx*
| # | Title | Guide section |
|---|---|---|
| 4.1 | Parallel Form Realization of IIR Filters | §7.1 |
| 4.2 | Direct Form to Cascade Form Conversion | §7.2 |
| 4.3 | Cascade Form Realization of FIR & IIR Filters | §7.3 |
| 4.4 | Cascade Form to Direct Form Conversion | §7.4 |
| 4.5 | Direct Form to Parallel Form Conversion | §7.5 |
| 4.6 | Parallel Form to Direct Form Conversion | §7.6 |

### Chapter 5 — Fourier Transform and DTFT
| # | Title | Guide section |
|---|---|---|
| 5.1 | Fourier Transform and Inverse Fourier Transform of a Sequence | §9.1 |
| 5.2 | Fourier Transform of u(t+0.5)−u(t−0.5) | §9.2 |
| 5.3 | Evaluation and Plotting of DTFT of the Transfer Function | §8.1 |
| 5.4 | Time Shifting Property of DTFT | §8.3 |
| 5.5 | Frequency Shifting Property of DTFT | §8.3 |
| 5.6 | Time Convolution Property of DTFT | §8.3 |
| 5.7 | Time Reversal Property of DTFT | §8.3 |
| 5.8 | Frequency Response of the Given System | §8.1 |
| 5.9 | Periodicity Property of DTFT | §8.4 |

### Chapter 6 — Fourier Series and DFT
| # | Title | Guide section |
|---|---|---|
| 6.1 | Fourier Series Representation of a Train of Pulses | §10.1 |
| 6.2 | Fourier Series Representation of a Full Wave Rectified Wave | §10.1 |
| 6.3 | Direct Computation of DFT (Matrix Formulation) | §11.1 |
| 6.4 | Linear Convolution Using DFT | §11.3 |
| 6.5 | Circular Convolution Using DFT | §11.3 |
| 6.6 | Relation Between DFTs of Periodic Even and Odd Parts | §11.4 |
| 6.7 | Parseval's Relation of DFT | §11.5 |
| 6.8 | Circular Time Shifting Property of DFT | §11.6 |
| 6.9 | Gibbs Phenomenon | §10.2 |

### Chapter 7 — FFT
| # | Title | Guide section |
|---|---|---|
| 7.1 | Calculation of the DFT of a Sequence Using FFT | §11.2 |
| 7.2 | Linear Convolution Using FFT | §11.3 |
| 7.3 | Circular Convolution Using FFT | §11.3 |
| 7.4 | Plotting of DFT of Sinusoidal Wave | §11.7 |

---

## 0. Universal Header (top of every script, no exceptions)

```matlab
clc; clear; close all;   % Clear command window, workspace variables, and figures
```
**Why the exam cares:** an examiner running your script cold — with leftover variables from your last question still in memory — will get wrong output if you skip this. It's usually worth marks by itself.

---

## 1. Signal Generation

**Theory:** discrete-time "elementary" signals are the building blocks everything else in the course is built from — impulse δ[n], step u[n], ramp, and exponential a^n. Most later systems questions describe their input as a combination of these.

**🎯 Exam pattern:** "Generate and plot the following discrete-time sequences..." This is almost always the warm-up question.

### 1.1 Standard sequences: impulse, step, ramp, parabola
📘 **Book ref: 1.1 — Generation of Elementary Signals in Discrete-time**
```matlab
n = -10:10;                      % <-- CHANGE THIS: time range

impulse  = (n == 0);             % Unit impulse δ[n]: 1 only at n = 0
step     = (n >= 0);             % Unit step u[n]: 1 for n >= 0, else 0
ramp     = n .* (n >= 0);        % Unit ramp: equals n for n >= 0, else 0
parabola = 0.5*(n.^2) .* (n>=0); % Unit parabola: 0.5*n^2 for n >= 0

subplot(2,2,1); stem(n, impulse);  title('Unit Impulse');
subplot(2,2,2); stem(n, step);     title('Unit Step');
subplot(2,2,3); stem(n, ramp);     title('Unit Ramp');
subplot(2,2,4); stem(n, parabola); title('Unit Parabolic');
```
⚠️ **Common mistake:** using `n.^2` without the `(n>=0)` mask — that plots a parabola for negative n too, which is wrong for the *unit* (one-sided) parabola.

### 1.2 Composite signals from shifted/scaled steps
📘 **Book ref: 1.5 — Generation of x(n)=u(n+3)+5u(n−15)+4u(n+10)**
```matlab
n = -20:20;
u1 = double(n >= -3);       % u(n+3)  -> step shifted LEFT by 3
u2 = 5*double(n >= 15);     % 5u(n-15) -> shifted RIGHT by 15, scaled by 5
u3 = 4*double(n >= -10);    % 4u(n+10) -> shifted LEFT by 10, scaled by 4
x  = u1 + u2 + u3;          % superposition -> build any staircase-like signal
stem(n, x);
```
**Rule of thumb:** `u(n − k)` shifts **right** by k (delay); `u(n + k)` shifts **left** by k (advance). This is the #1 sign-error spot in the exam — write it on your scratch paper before typing.

### 1.3 Exponential sequence a^n
📘 **Book ref: 1.2 — Generation of a Discrete-time Exponential Sequence**
```matlab
n = -10:10;
a = 0.8;                    % <-- CHANGE THIS
x = a .^ n;
stem(n, x);
```
| Value of a | Shape |
|---|---|
| 0 < a < 1 | decaying |
| a > 1 | growing |
| -1 < a < 0 | decaying, alternating sign |
| a < -1 | growing, alternating sign |

> Your book's version of this problem plots all four cases at once (common "plot for 4 cases of a" question):
```matlab
a_vals = [0.8, 1.5, -0.8, -1.2];
titles = {'0<a<1','a>1','-1<a<0','a<-1'};
for i = 1:4
    x = a_vals(i) .^ n;
    subplot(2,2,i); stem(n,x); title(['x[n] for ', titles{i}]);
    xlabel('Samples n'); ylabel('Amplitude');
end
```

### 1.4 Sinusoid / cosine
This appears as a supporting example inside other problems (e.g. as one of the two signals multiplied in §2.5), not as a standalone book problem — but it's common enough to know on its own.
```matlab
n = 0:0.1:5;
x = 6 * cos(2*pi*1.2*n);    % A*cos(2*pi*f*n): amplitude A, frequency f
stem(n, x);
```

### 1.5 Chirp / swept-frequency signal
📘 **Book ref: 1.6 — Generation of Swept-frequency Sinusoidal Signal**
```matlab
n = 0:100;
a = pi/200;                 % <-- CHANGE THIS: controls sweep rate
x = cos(a * (n.^2));        % quadratic phase -> frequency increases with n
stem(n, x); xlabel('Discrete time n'); ylabel('Amplitude');
title('Swept-Frequency Sinusoidal Signal');
```
**If asked "why does the spacing between peaks shrink over time?"** — because the instantaneous frequency is proportional to n (derivative of the quadratic phase), so it keeps increasing.

### 1.6 Complex exponential
Also a supporting example (used later inside §8.4's periodicity demonstration), not its own book problem — worth knowing in isolation.
```matlab
n = 1:10;
x = (0.9*exp(1j*pi/3)).^n;  % magnitude 0.9 (decaying), angle pi/3/step -> decaying spiral
```

---

## 2. Signal Operations & Manipulation

**Theory:** these are the standard "operations on a sequence" — shifting it in time, flipping it, splitting it into symmetric (even) and antisymmetric (odd) parts, or combining two sequences. Almost every later system-analysis question uses one of these as a sub-step.

**🎯 Exam pattern:** "Given x[n] = ..., find and plot x[n−D], x[−n], the even part, and the odd part." This is almost always asked as one combined question.

### 2.1 Time shift / delay
General-purpose operation, used as a sub-step inside several book problems (e.g. §3.4's time-invariance test).
```matlab
D = 10;                     % <-- CHANGE THIS: delay in samples
xd = [zeros(1,D), x];       % prepend D zeros -> delays x by D samples
```

### 2.2 Time reversal
```matlab
y2 = fliplr(y1);            % reverses the vector left-to-right -> y1(-n)
```

### 2.3 Even & odd decomposition
📘 **Book ref: 1.4 — Even and Odd Components of the Sequence y(n) = u(n) − u(n−10)**
```matlab
n  = -10:10;                                          % symmetric range required (see warning below)
y1 = double(n >= 0) - double(n >= 10);                % y(n) = u(n) - u(n-10)
y2 = fliplr(y1);                                       % y(-n)
ye = 0.5*(y1 + fliplr(y1));   % even part: 0.5*(y[n] + y[-n])
yo = 0.5*(y1 - fliplr(y1));   % odd part:  0.5*(y[n] - y[-n])

subplot(2,2,1); stem(n,y1); title('Original Signal y(n)');
subplot(2,2,2); stem(n,y2); title('Reversed Signal y(-n)');
subplot(2,2,3); stem(n,ye); title('Even Component y_e(n)');
subplot(2,2,4); stem(n,yo); title('Odd Component y_o(n)');
```
⚠️ **Common mistake / examiner trap:** `fliplr` only equals true `x[-n]` when the signal's time vector is symmetric about n = 0 (e.g. `n = -10:10`). If your `n` is not symmetric, `fliplr` still runs without error but gives the *wrong* answer — always check your `n` vector first.

### 2.4 Weighted sum / linear combination
General-purpose operation, used as a sub-step inside §3.3's linearity test.
```matlab
a = 2; b = -3;               % <-- CHANGE THIS: scaling constants
x = a*x1 + b*x2;
```

### 2.5 Element-wise multiplication (modulation, windowing)
📘 **Book ref: 1.3 — Multiplication of Discrete-time Signals**
```matlab
n = 0:0.1:5;
x1 = 6*(2.^n);                   % first signal
x2 = 2*cos(2*pi*1.2*n);          % second signal
y  = x1 .* x2;                   % use .* (element-wise), NOT * (matrix multiply)

subplot(3,1,1); stem(n,x1); title('Signal x_1[n]');
subplot(3,1,2); stem(n,x2); title('Signal x_2[n]');
subplot(3,1,3); stem(n,y);  title('Result y[n] = x_1[n] \times x_2[n]');
xlabel('Time n'); ylabel('Amplitude');
```

---

## 3. LTI System Analysis

**Theory:** for a system described by a difference equation (num/den coefficients), you're expected to be able to (a) find its impulse response, (b) find its output for any input, and (c)–(e) test the three defining properties: linearity, time-invariance, and BIBO stability. All five reuse the *same* `num`/`den` setup.

**🎯 Exam pattern:** "For the system described by num/den, (a) find h[n], (b) find y[n] for input x[n], (c) check linearity, (d) check time-invariance, (e) check stability." Often all five parts on one system — treat this whole section as one unit.

**One setup, reused for the whole section** — define this once, then each part below only adds its own extra lines (don't retype num/den/ic each time in the exam):
```matlab
num = [2.2403, 2.4908, 2.2403];   % <-- CHANGE THIS
den = [1, -0.4, 0.75];            % <-- CHANGE THIS
ic  = [0, 0];                     % zero initial conditions (relaxed system)
n   = 0:50;                       % time range used for the tests below
```

### 3.1 Impulse response h[n]
📘 **Book ref: 1.8 — Computation of Impulse Response**
```matlab
N = 40;                      % how many samples of h[n] to compute
h = impz(num, den, N);
stem(h); xlabel('n'); ylabel('Amplitude'); title('Impulse Response of the Filter');
```

### 3.2 Output y[n] for any input x[n]
General-purpose — used as a sub-step wherever a system's response to a specific input is needed.
```matlab
y = filter(num, den, x, ic);   % x = whatever input the question gives you
```

### 3.3 Test Linearity
📘 **Book ref: 1.9 — Checking the Linearity of a System**
**Logic:** apply the system to (a·x1 + b·x2) directly, separately apply it to x1 and x2 and combine with the same weights, then compare. Difference ≈ 0 ⇒ linear.
```matlab
x1 = cos(2*pi*0.1*n); x2 = cos(2*pi*0.4*n);
a = 2; b = -3;
x  = a*x1 + b*x2;

y1 = filter(num, den, x1, ic);
y2 = filter(num, den, x2, ic);
y  = filter(num, den, x,  ic);

yt = a*y1 + b*y2;
d  = y - yt;                              % ~0 everywhere => LINEAR
subplot(3,1,1); stem(n,y);  title('Output Due to Weighted Input');
subplot(3,1,2); stem(n,yt); title('Weighted Sum of Outputs');
subplot(3,1,3); stem(n,d);  title('Difference Signal');
```
**How to answer in the report:** "Since d[n] ≈ 0 for all n, the superposition property holds, so the system is linear."

### 3.4 Test Time-Invariance
📘 **Book ref: 1.7 — Checking the Time-invariance Property**
**Logic:** delay the input first then pass through the system, vs. pass the input through the system then delay the output. Same result ⇒ time-invariant. (Reuses `x` from §3.3, or define your own.)
```matlab
D  = 10;
xd = [zeros(1,D), x];
y  = filter(num, den, x,  ic);
yd = filter(num, den, xd, ic);
d  = y - yd(1+D : length(n)+D);           % ~0 everywhere => TIME-INVARIANT
subplot(3,1,1); stem(n,y);            title('Original Output y[n]');
subplot(3,1,2); stem(n,yd(1:length(n))); title('Output due to Delayed Input');
subplot(3,1,3); stem(n,d);            title('Difference d[n]');
```
⚠️ **Common mistake:** indexing `yd` wrong. `yd` is D samples longer than `y` because of the zero-padding, so you must slice out exactly the aligned portion — `yd(1+D : length(n)+D)`, not `yd(1:length(n))`.

### 3.5 Test Stability (BIBO, via decaying impulse response)
📘 **Book ref: 1.10 — Testing the Stability of a System**
**Logic:** BIBO stability ⇔ h[n] is absolutely summable. Sum |h[n]| until it visibly decays to ~0; if the running sum is finite (doesn't blow up), the system is stable. (Reuses `num`, `den` from the top of this section — just extend N.)
```matlab
N = 200;
h = impz(num, den, N+1);
parsum = 0;
for k = 1:N+1
    parsum = parsum + abs(h(k));
    if abs(h(k)) < 1e-6, break; end       % stop once response has decayed
end
disp('Absolute sum at decay point:'); disp(parsum);   % finite => STABLE
stem(h); title('System Stability Check');
```
**How to answer in the report:** "Since h[n] decays to zero and Σ|h[n]| converges to a finite value, the system is BIBO stable."

---

## 4. Convolution & Correlation

**Theory:** convolution is how you find a system's output from its impulse response and an input; correlation measures how similar two signals are as one is slid past the other. This chapter shows the *same* linear convolution computed three different ways (direct, circular-with-padding, DFT) — the exam likes to ask you to prove they all agree.

**🎯 Exam pattern:** "Convolve x1 and x2 using (a) the direct method, (b) circular convolution with zero-padding, (c) the DFT method — show all three give the same result." A classic 3-in-1 verification question.

### 4.1 Linear convolution (direct)
📘 **Book ref: 2.1 — Convolution of Two Sequences**
```matlab
x1 = [1,2,0,1]; x2 = [2,2,1,1];   % <-- CHANGE THIS
y = conv(x1, x2);
disp('the linear convolution of the given sequence'); disp(y);
```

### 4.2 Linear convolution via circular convolution (MUST zero-pad first)
📘 **Book ref: 2.2 — Linear Convolution via Circular Convolution**
```matlab
N = length(x1) + length(x2) - 1;          % required length to avoid aliasing
x1e = [x1, zeros(1, N-length(x1))];
x2e = [x2, zeros(1, N-length(x2))];
ylin = cconv(x1e, x2e, N);
disp('the circular convolution of the given sequence'); disp(ylin);
```
**Why N = L+M-1:** the true linear convolution has that many nonzero samples. If you use a circular length shorter than that, the tail "wraps around" and corrupts the answer — this is exactly what "aliasing" means here.

### 4.3 Linear convolution via DFT/FFT
📘 **Book ref: 2.3 — Linear Convolution Using DFT**
```matlab
N = length(x1) + length(x2) - 1;
X = fft(x1, N);           % fft auto-pads to length N
H = fft(x2, N);
y_out = ifft(X .* H);     % multiply in frequency domain, then inverse transform
```
**One-line theory answer if asked "why does this work":** convolution in the time domain equals multiplication in the frequency domain (the Convolution Theorem).

### 4.4 Circular convolution (N-point, no extra padding — this is the *actual* circular result, not linear)
📘 **Book ref: 2.4 — Circular Convolution Using DFT Based Approach**
```matlab
N = 4;                                    % <-- CHANGE THIS: circular length
x_dft    = ifft(fft(x1) .* fft(x2));      % method 1: via DFT
x_direct = cconv(x1, x2, N);              % method 2: built-in (should match)
```

### 4.5 Autocorrelation / cross-correlation
📘 **Book ref: 2.5 — Computation of Correlation**
```matlab
y = xcorr(x1);             % autocorrelation of x1 with itself
% y = xcorr(x1, x2);       % cross-correlation between two sequences
stem(y); title('Autocorrelation'); xlabel('Lag');
```
**If asked what the peak means:** the autocorrelation always peaks at zero lag — that's where the signal best matches a copy of itself.

---

## 5. Z-Transform

**Theory:** the Z-transform turns a difference equation into an algebraic expression in z, making system analysis (poles, stability, cascading) much easier than working with the time-domain recursion directly. This section covers going forward and backward between X(z) and x[n], by three different backward methods: partial fractions, long division, and cascaded sections.

**🎯 Exam pattern:** either a symbolic "find X(z) and x[n] back" question, or a numeric "given H(z), find h[n]" via partial fractions or long division.

### 5.1 Symbolic Z-transform & Inverse Z-transform
📘 **Book ref: 3.1 — Z-transform and Inverse Z-transform of Given Signals**
```matlab
syms n wo
a = n + 1;                  % <-- CHANGE THIS: any time-domain expression
b = ztrans(a);               % forward Z-transform
c = iztrans(b);               % inverse Z-transform (should recover a)
disp('The input equation is'); disp(a);
disp('The z-transform is'); disp(b);
disp('The inverse z-transform is'); disp(c);
```
> Also works for expressions with a symbolic frequency, e.g. `a1 = cos(wo*n);`

### 5.2 Partial fraction expansion (residues, poles, direct terms)
📘 **Book ref: 3.2 — Finding the Residues of Z³/[(z−0.5)(z−0.75)(z−1)]**
```matlab
num = [1, 0, 0, 0];                 % <-- CHANGE THIS: coefficients for Z^3
den = poly([0.5, 0.75, 1]);         % build denominator directly from known pole locations
[r, p, k] = residue(num, den);      % r = residues, p = poles, k = direct (polynomial) terms
disp('Residues (r):'); disp(r); disp('Poles (p):'); disp(p); disp('Direct Term (k):'); disp(k);
```
**Note for the report:** `k` is nonempty only when num and den have the same degree (improper fraction) — that's when there's a direct polynomial term in addition to the residue terms.

### 5.3 Inverse Z-transform via power series (long division)
📘 **Book ref: 3.3 — Inverse Z-transform by the Polynomial Division Method**
```matlab
num = [1, 2, 1]; den = [1, -1, 0.3561];   % <-- CHANGE THIS
N = 5;                                    % how many terms of h[n] you want
num_padded = [num, zeros(1, N-1)];        % "extend the decimal" so division can continue
[h, rem] = deconv(num_padded, den);       % h = time-domain sequence terms
disp('The first few terms of the inverse Z-transform are:'); disp(h);
```

### 5.4 Cascaded second-order sections → single transfer function → h[n]
📘 **Book ref: 3.4 — Inverse Z-transform for the Cascaded Form (Polynomial Division Method)**
```matlab
nums = [1, -0.2235, 1.0000;    % each row = one SOS numerator [z^0, z^-1, z^-2]
        1, -0.4378, 1.0000;
        1,  1.0000, 0.0000];
dens = [1, -1.4335, 0.8581;
        1, -1.2936, 0.5569;
        1, -0.6122, 0.0000];
[num, den] = sos2tf([nums, dens]);        % multiply all sections into one num/den
N = 5;
num_padded = [num, zeros(1, N-1)];
[x, r] = deconv(num_padded, den);
disp('The first five values of the inverse Z-transform are:'); disp(x);
```

### 5.5 Z-domain "convolution" = polynomial multiplication
📘 **Book ref: 3.6 — Convolution Using Z-transform**
```matlab
x1 = [2, 1, 0, -1, 3];      % coefficients of X1(z): z^0, z^-1, z^-2, ...
x2 = [1, -3, 2];
x3 = conv(x1, x2);          % multiplying polynomials = multiplying Z-transforms
```
**One-liner if asked why `conv` works here:** multiplying two Z-transforms is polynomial multiplication in z⁻¹, and `conv` implements exactly that — same operation as time-domain convolution, different domain.

---

## 6. Filter Design & Pole-Zero Analysis

**Theory:** filter *design* means picking a transfer function (poles/zeros) that meets given passband/stopband specs; the pole-zero plot is then how you visually confirm the design (and its stability).

**🎯 Exam pattern:** "Design a Butterworth bandpass filter given passband/stopband specs, then plot its pole-zero diagram."

### 6.1 Butterworth filter design
📘 **Book ref: 3.5 — Pole-zero Plot of a Butterworth Band Pass Filter (part 1: design)**
```matlab
fs = 1000;                                % <-- CHANGE THIS: sampling frequency
wp = [200 300]/(fs/2);                    % normalized passband edges (divide by Nyquist = fs/2)
ws = [50 450]/(fs/2);                     % normalized stopband edges
[n, wc] = buttord(wp, ws, 3, 20);         % 3 dB passband ripple, 20 dB stopband attenuation
[z, p, k] = butter(n, wp);                % zeros, poles, gain
```
⚠️ **Common mistake:** forgetting to divide by `fs/2` (Nyquist). MATLAB's filter design functions require normalized frequencies in [0, 1], where 1 corresponds to the Nyquist frequency — plugging in raw Hz values silently gives a nonsense filter or an error.

### 6.2 Pole-zero plot
📘 **Book ref: 3.5 — Pole-zero Plot of a Butterworth Band Pass Filter (part 2: plot)**
```matlab
zplane(z, p);
title('Pole-Zero Plot of Butterworth Bandpass Filter');
```
**If asked "is the filter stable?" from the plot:** stable ⇔ all poles (x) lie strictly inside the unit circle.

---

## 7. Filter Structure Realizations ⚠️ Not present in your uploaded Full.docx

**Theory:** the same transfer function H(z) can be *realized* (implemented) in different structural forms — direct, cascade (series of small sections), or parallel (sum of small sections). These conversions matter in practice because cascade/parallel forms are less sensitive to coefficient rounding than one big direct-form polynomial. The whole chapter is really just three MATLAB functions (`residue`, `tf2sos`, `sos2tf`) used in both directions.

**Important:** these six problems (Chapter 4 in your list) don't appear anywhere in the `Full.docx` you uploaded — the file jumps from Chapter 3 straight to Chapter 5. The code below is a standard, correct implementation of each conversion (built the same way your book builds §5.2/§5.4, using `residue`/`sos2tf` for consistency with its style), but **it is not extracted from your book** — cross-check it against your actual course material if you have another source for this chapter.

### 7.1 Parallel Form Realization of IIR Filters
📘 **Book ref: 4.1 — Parallel Form Realization of IIR Filters** ⚠️ *generic implementation, not from your docx*
```matlab
num = [1, 0, 0, 0]; den = poly([0.5, 0.75, 1]);   % <-- CHANGE THIS: direct-form H(z) = num/den
[r, p, k] = residue(num, den);   % same function as §5.2
% Parallel form: H(z) = k + r(1)/(1-p(1)/z) + r(2)/(1-p(2)/z) + ...  (one branch per pole)
disp('Parallel-form branch gains (residues r):'); disp(r);
disp('Parallel-form branch poles (p):'); disp(p);
disp('Direct (feed-through) term k:'); disp(k);
```
**Theory:** `residue` breaks H(z) into one small term per pole — each term is exactly one "branch" of the parallel realization, all summed together with the direct term k.

### 7.2 Direct Form to Cascade Form Conversion
📘 **Book ref: 4.2 — Direct Form to Cascade Form Conversion** ⚠️ *generic implementation, not from your docx*
```matlab
num = [1, 3, 3, 1]; den = [1, -0.5, 0.06, 0];   % <-- CHANGE THIS: direct-form H(z)
[sos, g] = tf2sos(num, den);   % each row of sos = one 2nd-order section [b0 b1 b2 a0 a1 a2]
disp('Second-order sections (cascade form):'); disp(sos);
disp('Overall gain:'); disp(g);
```
**Theory:** `tf2sos` factors the numerator/denominator into pole-zero pairs and groups them into 2nd-order sections — that grouping *is* the cascade form: H(z) = g · H₁(z)·H₂(z)·...

### 7.3 Cascade Form Realization of FIR & IIR Filters
📘 **Book ref: 4.3 — Cascade Form Realization of FIR & IIR Filters** ⚠️ *generic implementation, not from your docx*
```matlab
sos = [1  0.5  0    1  -0.3  0;    % <-- CHANGE THIS: [b0 b1 b2 a0 a1 a2] per stage
       1 -0.2  0.1  1   0.4  0];
for i = 1:size(sos,1)
    b = sos(i,1:3); a = sos(i,4:6);
    h = impz(b, a, 20);
    subplot(size(sos,1),1,i); stem(h); title(['Stage ', num2str(i), ' impulse response']);
end
[num, den] = sos2tf(sos);          % combine to see the overall cascaded response
h_total = impz(num, den, 20);
figure; stem(h_total); title('Overall Cascaded Response');
```
**Theory:** cascade realization implements a filter as a chain of low-order (usually 2nd-order) stages in series — the overall response is what you get by feeding the output of one stage into the next.

### 7.4 Cascade Form to Direct Form Conversion — same technique as §5.4
📘 **Book ref: 4.4 — Cascade Form to Direct Form Conversion** ⚠️ *generic implementation, not from your docx*
```matlab
[num, den] = sos2tf(sos);   % sos = your matrix of 2nd-order sections [b0 b1 b2 a0 a1 a2] per row
disp('Direct-form numerator:'); disp(num);
disp('Direct-form denominator:'); disp(den);
```
This is the exact same `sos2tf` call already used in §5.4 — don't re-learn it, just recognize it.

### 7.5 Direct Form to Parallel Form Conversion — same technique as §7.1
📘 **Book ref: 4.5 — Direct Form to Parallel Form Conversion** ⚠️ *generic implementation, not from your docx*
Identical code to §7.1 (`[r,p,k] = residue(num,den)`) — "direct to parallel" and "parallel form realization" are the same conversion, just phrased differently by the question.

### 7.6 Parallel Form to Direct Form Conversion
📘 **Book ref: 4.6 — Parallel Form to Direct Form Conversion** ⚠️ *generic implementation, not from your docx*
```matlab
r = [0.5, -0.2]; p = [0.75, 0.5]; k = [];  % <-- CHANGE THIS: parallel-form residues/poles/direct term
[num, den] = residue(r, p, k);    % residue() run "backwards" rebuilds direct-form num/den
disp('Direct-form numerator:'); disp(num);
disp('Direct-form denominator:'); disp(den);
```
**Theory:** `residue` is its own inverse — call it with `(r, p, k)` instead of `(num, den)` and it reconstructs the direct-form transfer function from the parallel-form pieces.

---

## 8. DTFT & Frequency Response

**Theory:** the DTFT is the continuous-frequency spectrum of a discrete sequence; `freqz` computes it for you (either of a system's num/den, or directly of a plain sequence). This section also covers verifying DTFT properties (shift, modulation, reversal, convolution, periodicity) by comparing both sides of the property numerically.

**🎯 Exam pattern:** two flavors — (a) "find/plot H(e^jω) of a *system*" (use num & den), or (b) "verify [some DTFT property] using MATLAB."

### 8.1 Frequency response of a system H(e^jω)
📘 **Book ref: 5.3 — Evaluation and Plotting of DTFT of the Transfer Function, and 5.8 — Frequency Response of the Given System** (same core technique, asked twice in your book)
```matlab
w = -pi : 2*pi/255 : pi;            % frequency axis (or omit w: [h,w]=freqz(num,den))
num = [1, 2]; den = [1, -0.2];      % <-- CHANGE THIS
h = freqz(num, den, w);

w_norm = w/pi;
subplot(2,2,1); plot(w_norm, real(h)); title('Real part'); xlabel('Normalized Frequency (\times\pi)');
subplot(2,2,2); plot(w_norm, imag(h)); title('Imaginary part'); xlabel('Normalized Frequency (\times\pi)');
subplot(2,2,3); plot(w_norm, abs(h));  title('Magnitude spectrum'); xlabel('Normalized Frequency (\times\pi)');
subplot(2,2,4); plot(w_norm, angle(h)); title('Phase'); xlabel('Normalized Frequency (\times\pi)');
```
> Problem 5.8's version is the shorter 2-plot form: `[h, om] = freqz(num, den); subplot(2,1,1); plot(om/pi, abs(h)); title('Magnitude Response'); subplot(2,1,2); plot(om/pi, angle(h)); title('Phase Response');` — same underlying computation, just fewer subplots.
> In dB: `plot(w/pi, 20*log10(abs(h)))`

### 8.2 DTFT of a finite-length sequence directly (not a filter)
General-purpose distinction, used as a building block by §8.3's property checks.
```matlab
x1 = 1:15;                          % <-- CHANGE THIS: any sequence
w  = -pi : 2*pi/255 : pi;
h1 = freqz(x1, 1, w);               % denominator = 1 => this is just the DTFT of x1
```
**Key distinction to remember:** `freqz(num, den, w)` gives a *system's* frequency response; `freqz(x, 1, w)` gives a *sequence's* DTFT. Same function, different meaning depending on what you pass in.

### 8.3 DTFT property checks (verify by comparing plots/values)
📘 **Book ref: 5.4 (Time Shift), 5.5 (Frequency Shift), 5.6 (Time Convolution), 5.7 (Time Reversal) — four separate book problems, one shared pattern**
Every property check follows the same recipe: compute both sides of the property equation with `freqz`, then plot magnitude and phase of each side side-by-side to show they match.
```matlab
w = -pi : 2*pi/255 : pi;

% --- 5.4 Time-shift: DTFT{x[n-d]} = e^{-jwd} * DTFT{x[n]} ---
d  = 10; x2 = [zeros(1,d), x1];
h1 = freqz(x1,1,w); h2 = freqz(x2,1,w);
subplot(2,1,1); plot(w/pi, abs(h1)); title('Magnitude: Original');
subplot(2,1,2); plot(w/pi, abs(h2)); title('Magnitude: Shifted');

% --- 5.5 Frequency-shift (modulation): x[n]*e^{jw0 n} shifts the spectrum by w0 ---
w0 = 0.2*pi; n = 0:length(x1)-1;
x_mod = exp(1j*w0*n) .* x1;
h_mod = freqz(x_mod,1,w);

% --- 5.6 Convolution: DTFT{x1*x2} = X1(w).*X2(w) ---
h_product = freqz(x1,1,w) .* freqz(x2,1,w);
h_conv    = freqz(conv(x1,x2),1,w);

% --- 5.7 Time reversal: DTFT{x[-n]} = X(e^{-jw}), needs a phase-correction if x starts at n=0 ---
L = length(x1)-1;
h_rev = freqz(fliplr(x1),1,w);
h_rev_true = exp(1j*w*L) .* h_rev;   % phase-correction term
```
**How to "verify" for the report:** plot both sides of the property (e.g. `abs(h_product)` vs `abs(h_conv)`) — they should overlap exactly. State: "The two plots coincide, confirming the [shift/modulation/reversal/convolution] property of the DTFT."

### 8.4 Periodicity property of DTFT
📘 **Book ref: 5.9 — Periodicity Property of DTFT**
**Theory:** the DTFT is always periodic in ω with period 2π (because e^{-jωn} is periodic in ω). This problem demonstrates it by computing the DTFT over a frequency range wider than one period (−2π to 2π) and showing the shape repeats — using a matrix-multiplication implementation of the DTFT sum instead of `freqz`, as a bonus technique.
```matlab
n = 1:10; x = (0.9*exp(1j*pi/3)).^n;   % complex exponential (same signal as §1.6)
k = -200:200;                           % frequency index steps
w = (pi/100)*k;                         % frequency vector spanning -2*pi to +2*pi (wider than one period)
X_dtft = x * exp(-1j*pi/100).^(n' * k); % DTFT sum sum(x[n]*exp(-jwn)), done via matrix multiplication

subplot(2,1,1); plot(w/pi, abs(X_dtft));   title('Magnitude Spectrum (Periodicity)'); xlabel('Normalized frequency (\times\pi)');
subplot(2,1,2); plot(w/pi, angle(X_dtft)); title('Phase Spectrum (Periodicity)');     xlabel('Normalized frequency (\times\pi)');
```
**If asked to explain the matrix line:** `n' * k` builds an outer-product matrix of all (n,ω) combinations; raising the base to that matrix and multiplying by row-vector `x` performs the DTFT summation in one shot, without an explicit `for` loop.
**How to answer "what does the plot show":** the magnitude/phase pattern repeats every 2π along the frequency axis — direct visual proof that the DTFT is periodic.

---

## 9. Continuous-Time / Symbolic Fourier Transform

**Theory:** this is the *continuous*-time Fourier Transform (not DTFT), computed symbolically since these signals aren't sampled sequences. Used for signals defined by a formula (like a Gaussian or a rectangular pulse) rather than a numeric vector.

**🎯 Exam pattern:** symbolic FT of a known function, or the FT of a rectangular pulse (sinc function).

### 9.1 Symbolic Fourier Transform & Inverse
📘 **Book ref: 5.1 — Fourier Transform and Inverse Fourier Transform of a Given Sequence**
```matlab
syms x
f = exp(-x^2);         % <-- CHANGE THIS: any continuous-time expression
F = fourier(f);         % Fourier transform
f_inv = ifourier(F);    % should recover f
disp('-- Symbolic Fourier Transform --');
disp(F);
```

### 9.2 Fourier Transform of a rectangular pulse
📘 **Book ref: 5.2 — Fourier Transform of a Signal u(t+0.5)−u(t−0.5)**
```matlab
syms t w
a = heaviside(t + 0.5) - heaviside(t - 0.5);   % rect pulse, width 1, centered at 0
subplot(2,1,1); ezplot(a, [-3, 3]);
b = fourier(a);
subplot(2,1,2); ezplot(b, [-50, 50]);
```
**Theory reminder:** the FT of a rectangular pulse is a **sinc** function — if your plot doesn't look like a sinc, the pulse definition is probably wrong.

---

## 10. Fourier Series (Periodic Signals)

**Theory:** any periodic signal can be written as a sum of harmonically-related complex exponentials (or sines/cosines) — the Fourier Series. The coefficients are computed by an integral over one period. Gibbs phenomenon is what happens to that approximation near a discontinuity (like a square wave's jump).

**🎯 Exam pattern:** "Find the Fourier Series coefficients of the given periodic signal" or "Demonstrate the Gibbs phenomenon for a square wave."

### 10.1 Compute FS coefficients via symbolic integration
📘 **Book ref: 6.1 — Fourier Series of a Train of Pulses, and 6.2 — Fourier Series of a Full Wave Rectified Wave** (same technique, two different input signals)
```matlab
syms t
T0 = 1;                                   % <-- CHANGE THIS: fundamental period
x = 2*(heaviside(t) - heaviside(t - T0/4) + heaviside(t - 3*T0/4)); % periodic pulse, one period
% For 6.2 (full-wave rectified wave), swap x for: x = abs(sin(pi*t/T0));

N = 20;                                   % number of harmonics
X = zeros(1,N); w = zeros(1,N);
for k = 1:N
    w(k) = (k-1)*2*pi/T0;
    X(k) = double((1/T0) * int(x * exp(-1j*w(k)*t), t, 0, T0));   % FS coefficient formula
end

subplot(2,1,1); stem(w, abs(X));   title('Magnitude of Fourier Series');
subplot(2,1,2); stem(w, angle(X)); title('Phase of Fourier Series');
```
**Formula to write in your answer sheet:** X_k = (1/T0) ∫₀^T0 x(t)·e^(−jkω0t) dt, where ω0 = 2π/T0.

### 10.2 Gibbs phenomenon (square-wave synthesis from harmonics)
📘 **Book ref: 6.9 — Gibbs Phenomenon**
```matlab
t2 = 0:0.02:pi;
x = zeros(size(t2));
for k = 1:2:19                 % odd harmonics only (square wave has only odd harmonics)
    x = x + sin(k*t2)/k;
    y5((k+1)/2, :) = x;        % store each partial sum for comparison
end
subplot(2,2,1); plot(t2, y5(1,:)); title('1st Harmonic');
subplot(2,2,2); plot(t2, y5(2,:)); title('Up to 3rd Harmonic');
subplot(2,2,3); plot(t2, y5(3,:)); title('Up to 5th Harmonic');
subplot(2,2,4); plot(t2, y5(4,:)); title('Up to 7th Harmonic');
figure; plot(t2, y5'); grid on; title('The building of a square wave: Gibbs'' effect');
xlabel('Time (t)'); ylabel('Amplitude');
```
**What to say if asked "what is Gibbs phenomenon":** near a discontinuity (the square wave's jump), the partial-sum approximation always overshoots by about 9%, no matter how many harmonics you add — adding more terms narrows the overshoot region but doesn't shrink the overshoot itself.

---

## 11. DFT / FFT

**Theory:** the DFT is the *sampled-frequency* counterpart of the DTFT — it's what you actually compute on a computer. The FFT is just a fast algorithm for computing the exact same DFT. This chapter is the biggest bucket: computing it directly from the definition (matrix method), the fast way (`fft`), using it to do convolution, and verifying its theoretical properties (energy conservation, circular shift, even/odd symmetry).

**🎯 Exam pattern:** direct DFT via matrix, FFT/IFFT, linear vs circular convolution via FFT, Parseval's theorem, circular shift property, spectrum of a real signal.

### 11.1 Direct DFT via matrix multiplication (dftmtx)
📘 **Book ref: 6.3 — Direct Computation of Discrete Fourier Transform (Matrix Formulation)**
```matlab
x = [1,-1,2,-2];               % <-- CHANGE THIS
N = length(x);
Y = x * dftmtx(N);              % forward DFT
x_inv = (Y * conj(dftmtx(N))) / N;   % inverse DFT (should recover x)
disp('-- Forward DFT --'); disp(Y);
disp('-- Inverse DFT --'); disp(x_inv);
```
**If asked to explain:** `dftmtx(N)` builds the N×N matrix of DFT basis exponentials; multiplying `x` by it performs the DFT summation directly from the definition, matching the theory formula exactly (useful if you're asked to "derive" the DFT, not just compute it).

### 11.2 FFT / IFFT basics
📘 **Book ref: 7.1 — Calculation of the DFT of a Given Sequence Using FFT**
```matlab
x = [1,-1,2,-2];               % <-- CHANGE THIS
y  = fft(x);
disp('the fft of the input sequence'); disp(y);
subplot(2,1,1); stem(abs(y));   title('magnitude response');
subplot(2,1,2); stem(angle(y)); title('phase response');
y1 = ifft(y);                   % should recover x (take real() if needed due to rounding)
disp('the inverse fft is'); disp(y1);
```

### 11.3 Linear & Circular convolution via FFT — same technique as §4.3/§4.4
📘 **Book ref: 6.4/6.5 (Linear/Circular Convolution Using DFT), and 7.2/7.3 (same, Using FFT) — four book problems, one technique**
This is the *identical* method as §4.3 (linear) and §4.4 (circular) — your book just re-asks it under three different chapter headings. Don't memorize it three times; just remember the one rule:

**Zero-pad before FFT → linear convolution. Skip the zero-padding → circular convolution.** That single difference is a favorite "spot the error" trap question.
```matlab
x = [1 2]; h = [2 1];                    % <-- CHANGE THIS
% Linear (zero-padded to N = len(x)+len(h)-1):
x1 = [x, zeros(1, length(h)-1)];
h1 = [h, zeros(1, length(x)-1)];
y_linear = real(ifft(fft(x1) .* fft(h1)));
subplot(3,1,1); stem(x,'filled'); title('Sequence x[n]');
subplot(3,1,2); stem(h,'filled'); title('Sequence h[n]');
subplot(3,1,3); stem(y_linear,'filled'); title('Linear Convolution y[n]');

% Circular (equal length, NO padding — wraps around instead of extending):
x2 = [1 2 1 2]; h2 = [4 3 2 1];          % must already be same length
y_circular = real(ifft(fft(x2) .* fft(h2)));
```

### 11.4 Periodic even/odd decomposition (circular)
📘 **Book ref: 6.6 — Relation Between DFTs of the Periodic Even and Odd Parts of a Real Sequence**
```matlab
x_val = [1,2,4,2,6,32,6,4,2];
N = 256;
x = [x_val, zeros(1, N-length(x_val))];
x_rev  = [x(1), x(N:-1:2)];      % circular time reversal (index 1 stays fixed)
x_even = 0.5*(x + x_rev);
X      = fft(x);
X_even = fft(x_even);
k = 0:N-1;
subplot(2,1,1); plot(k/128, real(X));      title('Real Part of Original Signal Spectrum');
subplot(2,1,2); plot(k/128, imag(X));      title('Imaginary Part of Original Signal Spectrum');
figure;
subplot(2,1,1); plot(k/128, real(X_even)); title('Real Part of Even-part Spectrum');
subplot(2,1,2); plot(k/128, imag(X_even)); title('Imaginary Part of Even-part Spectrum');
```

### 11.5 Parseval's theorem (energy: time domain vs frequency domain)
📘 **Book ref: 6.7 — Parseval's Relation of DFT**
```matlab
x = [(1:128), (128:-1:1)];       % <-- CHANGE THIS
N = length(x);
E_time = sum(x .* x);
X = fft(x);
E_freq = sum(abs(X).^2) / N;
err = E_time - E_freq;           % should be ~0
disp('energy in time domain'); disp(E_time);
disp('energy in Frequency domain'); disp(E_freq);
disp('error'); disp(err);
```
**Formula for the report:** Σ|x[n]|² = (1/N) Σ|X[k]|² — energy is conserved between time and frequency domains.

### 11.6 Circular shift property of DFT
📘 **Book ref: 6.8 — Circular Time Shifting Property of DFT**
```matlab
x = [0 2 4 6 8 10 12 14 16];    % <-- CHANGE THIS
M = 5;                           % shift amount
y = circshift(x, -M);            % circular shift => phase-only change in DFT
xf = fft(x); yf = fft(y);
subplot(2,1,1); stem(xf, abs(xf)); title('Magnitude of DFT original');
subplot(2,1,2); stem(yf, abs(yf)); title('Magnitude of DFT Circularly Shifted');
figure;
subplot(2,1,1); stem(xf, angle(xf)); title('Phase of DFT original');
subplot(2,1,2); stem(yf, angle(yf)); title('phase of DFT Circularly Shifted');
% abs(xf) == abs(yf)  (magnitude unchanged)
% angle(xf) ~= angle(yf)  (phase changes with the shift)
```
**One-liner for the report:** a circular shift in time changes only the *phase* of the DFT, not the *magnitude* — confirmed since `abs(xf)` and `abs(yf)` match but the angles differ.

### 11.7 Spectrum analysis of a real signal (magnitude spectrum, normalized)
📘 **Book ref: 7.4 — Plotting of DFT of Sinusoidal Wave**
```matlab
t = 0:0.01:1;
a = sin(2*pi*10*t) + sin(2*pi*100*t);   % two-tone test signal: 10 Hz + 100 Hz
b = fft(a);
e = abs(b) / length(a);                  % normalized amplitude spectrum
subplot(2,1,1); plot(t, a); title('input signal'); xlabel('time'); ylabel('amplitude');
subplot(2,1,2); plot(e);    title('fft of the input signal'); xlabel('frequency');
```
**If asked "what do you expect to see":** two sharp peaks in the magnitude spectrum, at locations corresponding to 10 Hz and 100 Hz.

---

## 12. Plotting & Utility Cheat Sheet

| Task | Command |
|---|---|
| Discrete-time plot | `stem(n, x)` |
| Continuous-time plot | `plot(t, x)` |
| Symbolic function plot | `ezplot(f, [a b])` or `fplot(f, [a b])` |
| Grid of subplots | `subplot(rows, cols, index)` |
| Multiple figure windows | `figure(1); ... figure(2); ...` |
| Print a value/label to console | `disp('label'); disp(value);` |
| Normalized frequency axis | plot against `w/pi` so axis reads in units of π |
| dB magnitude | `20*log10(abs(h))` |
| Zero-pad a vector to length N | `[x, zeros(1, N-length(x))]` |

---

## 13. Quick Function-by-Purpose Index

| MATLAB function | Purpose |
|---|---|
| `stem`, `plot` | discrete / continuous plotting |
| `conv` | linear convolution / polynomial multiplication |
| `cconv` | circular (or, with zero-padding, linear) convolution |
| `xcorr` | auto/cross-correlation |
| `filter` | apply a difference-equation system to an input |
| `impz` | impulse response of a system |
| `fft`, `ifft` | Fast Fourier Transform / inverse |
| `dftmtx` | DFT via explicit matrix (matches theory formula) |
| `freqz` | frequency response of a system OR DTFT of a sequence |
| `ztrans`, `iztrans` | symbolic Z-transform / inverse |
| `fourier`, `ifourier` | symbolic continuous Fourier transform / inverse |
| `residue` | partial fraction expansion (poles/residues); also used both directions for parallel-form filter conversion |
| `deconv` | polynomial long division (inverse Z-transform power series) |
| `tf2sos` | split direct-form num/den into cascade (2nd-order sections) form |
| `sos2tf` | combine cascaded second-order sections into one transfer function |
| `buttord`, `butter` | Butterworth filter order & design |
| `zplane` | pole-zero plot |
| `circshift` | circular shift of a sequence |
| `poly` | build polynomial coefficients from known roots |
| `heaviside` | unit step function (for symbolic/continuous signals) |

---

## 14. If You're Panicking: 5-Minute Pre-Exam Checklist

- [ ] Always start with `clc; clear; close all;`
- [ ] For any shift: `u(n−k)` = right shift, `u(n+k)` = left shift.
- [ ] For circular vs linear convolution via FFT: **zero-pad = linear, no pad = circular.**
- [ ] For linearity/time-invariance/stability tests: the "proof" is always **difference ≈ 0** (or the sum staying finite for stability).
- [ ] `freqz(num, den, w)` = system response; `freqz(x, 1, w)` = DTFT of a plain sequence.
- [ ] Normalize filter frequencies by `fs/2` before `buttord`/`butter`.
- [ ] `dftmtx`, `residue`, `deconv`, `tf2sos`/`sos2tf`, `ztrans/iztrans` — know what each *returns* (matrix, [r,p,k], [quotient,remainder], [num,den], symbolic expr) so you don't fumble the output variables under time pressure.
- [ ] Chapter 4 (filter realizations) is NOT in your uploaded book file — if it's fair game tomorrow, double-check §7 against your actual lecture notes if you can.
- [ ] Every plot needs a `title()` — cheap marks, don't skip it.

---

*This file is organized so you can keep extending it: whenever you learn a new operation, drop it into the matching section instead of leaving it buried in a chapter you'll forget about.*
