# DSP MATLAB — Lab Final Survival Guide

Built from all 7 chapters of your lab code, merged with your existing codebook, reorganized **by operation** (not chapter), and annotated with **what kind of exam question triggers each block**. Everything is copy-paste-run: change only the lines marked `% <-- CHANGE THIS`.

**How to use this during the exam:**
1. Read the question, spot the keyword (e.g. "circular convolution", "check stability").
2. Ctrl+F that keyword here, or use the **Decision Table** below.
3. Copy the block, swap the input values, run.

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
| "Find/plot the frequency response H(e^jω) of the system" | §7.1 |
| "Find the DTFT of a sequence" (not a system) | §7.2 |
| "Compute DTFT manually / without freqz" | §7.3 |
| "Verify a DTFT property" (shift, modulation, reversal, convolution) | §7.4 |
| "Find the Fourier Transform of a continuous signal" (symbolic) | §8.1 |
| "Find FT of a rectangular pulse" | §8.2 |
| "Find Fourier Series coefficients of a periodic signal" | §9.1 |
| "Demonstrate Gibbs phenomenon" | §9.2 |
| "Compute DFT using the DFT matrix (dftmtx)" | §10.1 |
| "Find FFT / IFFT of a sequence" | §10.2 |
| "Verify Parseval's theorem" | §10.5 |
| "Verify the circular shift property of DFT" | §10.6 |
| "Plot the magnitude spectrum of a signal" | §10.7 |

---

## 0. Universal Header (top of every script, no exceptions)

```matlab
clc; clear; close all;   % Clear command window, workspace variables, and figures
```
**Why the exam cares:** an examiner running your script cold — with leftover variables from your last question still in memory — will get wrong output if you skip this. It's usually worth marks by itself.

---

## 1. Signal Generation

**🎯 Exam pattern:** "Generate and plot the following discrete-time sequences..." This is almost always the warm-up question.

### 1.1 Standard sequences: impulse, step, ramp, parabola
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
```matlab
n = -20:20;
u1 = double(n >= -3);       % u(n+3)  -> step shifted LEFT by 3
u2 = 5*double(n >= 15);     % 5u(n-15) -> shifted RIGHT by 15, scaled by 5
u3 = 4*double(n >= -10);    % 4u(n+10) -> shifted LEFT by 10, scaled by 4
x  = u1 + u2 + u3;          % superposition -> build any staircase-like signal
stem(n, x);
```
**Rule of thumb:** `u(n - k)` shifts **right** by k (delay); `u(n + k)` shifts **left** by k (advance). This is the #1 sign-error spot in the exam — write it on your scratch paper before typing.

### 1.3 Exponential sequence a^n
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

> Compare all four in one figure (common "plot for 4 cases of a" question):
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
```matlab
n = 0:0.1:5;
x = 6 * cos(2*pi*1.2*n);    % A*cos(2*pi*f*n): amplitude A, frequency f
stem(n, x);
```

### 1.5 Chirp / swept-frequency signal
```matlab
n = 0:100;
a = pi/200;                 % <-- CHANGE THIS: controls sweep rate
x = cos(a * (n.^2));        % quadratic phase -> frequency increases with n
stem(n, x); xlabel('Discrete time n'); ylabel('Amplitude');
title('Swept-Frequency Sinusoidal Signal');
```
**If asked "why does the spacing between peaks shrink over time?"** — because the instantaneous frequency is proportional to n (derivative of the quadratic phase), so it keeps increasing.

### 1.6 Complex exponential
```matlab
n = 1:10;
x = (0.9*exp(1j*pi/3)).^n;  % magnitude 0.9 (decaying), angle pi/3/step -> decaying spiral
```

---

## 2. Signal Operations & Manipulation

**🎯 Exam pattern:** "Given x[n] = ..., find and plot x[n−D], x[−n], the even part, and the odd part." This is almost always asked as one combined question.

### 2.1 Time shift / delay
```matlab
D = 10;                     % <-- CHANGE THIS: delay in samples
xd = [zeros(1,D), x];       % prepend D zeros -> delays x by D samples
```

### 2.2 Time reversal
```matlab
y2 = fliplr(y1);            % reverses the vector left-to-right -> y1(-n)
```

### 2.3 Even & odd decomposition
```matlab
ye = 0.5*(y1 + fliplr(y1));   % even part: 0.5*(x[n] + x[-n])
yo = 0.5*(y1 - fliplr(y1));   % odd part:  0.5*(x[n] - x[-n])
```
⚠️ **Common mistake / examiner trap:** `fliplr` only equals true `x[-n]` when the signal's time vector is symmetric about n = 0 (e.g. `n = -10:10`). If your `n` is not symmetric, `fliplr` still runs without error but gives the *wrong* answer — always check your `n` vector first.

### 2.4 Weighted sum / linear combination
```matlab
a = 2; b = -3;               % <-- CHANGE THIS: scaling constants
x = a*x1 + b*x2;
```

### 2.5 Element-wise multiplication (modulation, windowing)
```matlab
y = x1 .* x2;                % use .* (element-wise), NOT * (matrix multiply)
```

---

## 3. LTI System Analysis

**🎯 Exam pattern:** "For the system described by num/den, (a) find h[n], (b) find y[n] for input x[n], (c) check linearity, (d) check time-invariance, (e) check stability." Often all five parts on one system — memorize this whole section as one unit.

**One setup, reused for the whole section** — define this once, then each part below only adds its own extra lines (don't retype num/den/ic each time in the exam):
```matlab
num = [2.2403, 2.4908, 2.2403];   % <-- CHANGE THIS
den = [1, -0.4, 0.75];            % <-- CHANGE THIS
ic  = [0, 0];                     % zero initial conditions (relaxed system)
n   = 0:50;                       % time range used for the tests below
```

### 3.1 Impulse response h[n]
```matlab
N = 40;                      % how many samples of h[n] to compute
h = impz(num, den, N);
stem(h); xlabel('n'); ylabel('Amplitude'); title('Impulse Response');
```

### 3.2 Output y[n] for any input x[n]
```matlab
y = filter(num, den, x, ic);   % x = whatever input the question gives you
```

### 3.3 Test Linearity
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
subplot(3,1,1); stem(n,y);  title('Output of Weighted Input');
subplot(3,1,2); stem(n,yt); title('Weighted Sum of Outputs');
subplot(3,1,3); stem(n,d);  title('Difference (should be ~0)');
```
**How to answer in the report:** "Since d[n] ≈ 0 for all n, the superposition property holds, so the system is linear."

### 3.4 Test Time-Invariance
**Logic:** delay the input first then pass through the system, vs. pass the input through the system then delay the output. Same result ⇒ time-invariant. (Reuses `x` from §3.3, or define your own.)
```matlab
D  = 10;
xd = [zeros(1,D), x];
y  = filter(num, den, x,  ic);
yd = filter(num, den, xd, ic);
d  = y - yd(1+D : length(n)+D);           % ~0 everywhere => TIME-INVARIANT
```
⚠️ **Common mistake:** indexing `yd` wrong. `yd` is D samples longer than `y` because of the zero-padding, so you must slice out exactly the aligned portion — `yd(1+D : length(n)+D)`, not `yd(1:length(n))`.

### 3.5 Test Stability (BIBO, via decaying impulse response)
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

**🎯 Exam pattern:** "Convolve x1 and x2 using (a) the direct method, (b) circular convolution with zero-padding, (c) the DFT method — show all three give the same result." A classic 3-in-1 verification question.

### 4.1 Linear convolution (direct)
```matlab
x1 = [1,2,0,1]; x2 = [2,2,1,1];   % <-- CHANGE THIS
y = conv(x1, x2);
disp('Linear convolution:'); disp(y);
```

### 4.2 Linear convolution via circular convolution (MUST zero-pad first)
```matlab
N = length(x1) + length(x2) - 1;          % required length to avoid aliasing
x1e = [x1, zeros(1, N-length(x1))];
x2e = [x2, zeros(1, N-length(x2))];
ylin = cconv(x1e, x2e, N);
```
**Why N = L+M-1:** the true linear convolution has that many nonzero samples. If you use a circular length shorter than that, the tail "wraps around" and corrupts the answer — this is exactly what "aliasing" means here.

### 4.3 Linear convolution via DFT/FFT
```matlab
N = length(x1) + length(x2) - 1;
X = fft(x1, N);           % fft auto-pads to length N
H = fft(x2, N);
y_out = ifft(X .* H);     % multiply in frequency domain, then inverse transform
```
**One-line theory answer if asked "why does this work":** convolution in the time domain equals multiplication in the frequency domain (the Convolution Theorem).

### 4.4 Circular convolution (N-point, no extra padding — this is the *actual* circular result, not linear)
```matlab
N = 4;                                    % <-- CHANGE THIS: circular length
x_dft    = ifft(fft(x1) .* fft(x2));      % method 1: via DFT
x_direct = cconv(x1, x2, N);              % method 2: built-in (should match)
```

### 4.5 Autocorrelation / cross-correlation
```matlab
y = xcorr(x1);             % autocorrelation of x1 with itself
% y = xcorr(x1, x2);       % cross-correlation between two sequences
stem(y); title('Autocorrelation'); xlabel('Lag');
```
**If asked what the peak means:** the autocorrelation always peaks at zero lag — that's where the signal best matches a copy of itself.

---

## 5. Z-Transform

**🎯 Exam pattern:** either a symbolic "find X(z) and x[n] back" question, or a numeric "given H(z), find h[n]" via partial fractions or long division.

### 5.1 Symbolic Z-transform & Inverse Z-transform
```matlab
syms n wo
a = n + 1;                  % <-- CHANGE THIS: any time-domain expression
b = ztrans(a);               % forward Z-transform
c = iztrans(b);               % inverse Z-transform (should recover a)
disp('Z-transform:'); disp(b);
disp('Inverse Z-transform:'); disp(c);
```
> Also works for expressions with a symbolic frequency, e.g. `a1 = cos(wo*n);`

### 5.2 Partial fraction expansion (residues, poles, direct terms)
```matlab
num = [1, 0, 0, 0];                 % <-- CHANGE THIS
den = poly([0.5, 0.75, 1]);         % build denominator directly from known pole locations
[r, p, k] = residue(num, den);      % r = residues, p = poles, k = direct (polynomial) terms
disp('Residues:'); disp(r); disp('Poles:'); disp(p); disp('Direct term:'); disp(k);
```
**Note for the report:** `k` is nonempty only when num and den have the same degree (improper fraction) — that's when there's a direct polynomial term in addition to the residue terms.

### 5.3 Inverse Z-transform via power series (long division)
```matlab
num = [1, 2, 1]; den = [1, -1, 0.3561];   % <-- CHANGE THIS
N = 5;                                    % how many terms of h[n] you want
num_padded = [num, zeros(1, N-1)];        % "extend the decimal" so division can continue
[h, rem] = deconv(num_padded, den);       % h = time-domain sequence terms
disp('First terms of h[n]:'); disp(h);
```

### 5.4 Cascaded second-order sections → single transfer function → h[n]
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
```

### 5.5 Z-domain "convolution" = polynomial multiplication
```matlab
x1 = [2, 1, 0, -1, 3];      % coefficients of X1(z): z^0, z^-1, z^-2, ...
x2 = [1, -3, 2];
x3 = conv(x1, x2);          % multiplying polynomials = multiplying Z-transforms
```
**One-liner if asked why `conv` works here:** multiplying two Z-transforms is polynomial multiplication in z⁻¹, and `conv` implements exactly that — same operation as time-domain convolution, different domain.

---

## 6. Filter Design & Pole-Zero Analysis

**🎯 Exam pattern:** "Design a Butterworth bandpass filter given passband/stopband specs, then plot its pole-zero diagram."

### 6.1 Butterworth filter design
```matlab
fs = 1000;                                % <-- CHANGE THIS: sampling frequency
wp = [200 300]/(fs/2);                    % normalized passband edges (divide by Nyquist = fs/2)
ws = [50 450]/(fs/2);                     % normalized stopband edges
[n, wc] = buttord(wp, ws, 3, 20);         % 3 dB passband ripple, 20 dB stopband attenuation
[z, p, k] = butter(n, wp);                % zeros, poles, gain
```
⚠️ **Common mistake:** forgetting to divide by `fs/2` (Nyquist). MATLAB's filter design functions require normalized frequencies in [0, 1], where 1 corresponds to the Nyquist frequency — plugging in raw Hz values silently gives a nonsense filter or an error.

### 6.2 Pole-zero plot
```matlab
zplane(z, p);
title('Pole-Zero Plot');
```
**If asked "is the filter stable?" from the plot:** stable ⇔ all poles (x) lie strictly inside the unit circle.

---

## 7. DTFT & Frequency Response

**🎯 Exam pattern:** two flavors — (a) "find/plot H(e^jω) of a *system*" (use num & den), or (b) "find the DTFT of a *sequence*" (den = 1). Also common: "verify [some DTFT property] using MATLAB."

### 7.1 Frequency response of a system H(e^jω)
```matlab
w = -pi : 2*pi/255 : pi;            % frequency axis (or omit w: [h,w]=freqz(num,den))
num = [1, 2]; den = [1, -0.2];      % <-- CHANGE THIS
h = freqz(num, den, w);

w_norm = w/pi;
subplot(2,1,1); plot(w_norm, abs(h));   title('Magnitude'); xlabel('\omega/\pi');
subplot(2,1,2); plot(w_norm, angle(h)); title('Phase');     xlabel('\omega/\pi');
```
> In dB: `plot(w/pi, 20*log10(abs(h)))`

### 7.2 DTFT of a finite-length sequence directly (not a filter)
```matlab
x1 = 1:15;                          % <-- CHANGE THIS: any sequence
w  = -pi : 2*pi/255 : pi;
h1 = freqz(x1, 1, w);               % denominator = 1 => this is just the DTFT of x1
```
**Key distinction to remember:** `freqz(num, den, w)` gives a *system's* frequency response; `freqz(x, 1, w)` gives a *sequence's* DTFT. Same function, different meaning depending on what you pass in.

### 7.3 Manual DTFT (matrix-multiplication method, no freqz)
```matlab
n = 1:10; x = (0.9*exp(1j*pi/3)).^n;   % <-- CHANGE THIS
k = -200:200;
w = (pi/100)*k;
X_dtft = x * exp(-1j*pi/100).^(n' * k);   % implements sum(x[n]*exp(-jwn)) as a matrix product
```
**If asked to explain this line:** `n' * k` builds an outer-product matrix of all (n,ω) combinations; raising the base to that matrix and multiplying by row-vector `x` performs the DTFT summation in one shot, without an explicit `for` loop.

### 7.4 DTFT property checks (verify by comparing plots/values)
```matlab
w = -pi : 2*pi/255 : pi;

% --- Time-shift: DTFT{x[n-d]} = e^{-jwd} * DTFT{x[n]} ---
d  = 10; x2 = [zeros(1,d), x1];
h1 = freqz(x1,1,w); h2 = freqz(x2,1,w);

% --- Frequency-shift (modulation): x[n]*e^{jw0 n} shifts the spectrum by w0 ---
w0 = 0.2*pi; n = 0:length(x1)-1;
x_mod = exp(1j*w0*n) .* x1;

% --- Time reversal: DTFT{x[-n]} = X(e^{-jw}), needs a phase-correction if x starts at n=0 ---
L = length(x1)-1;
h_rev = freqz(fliplr(x1),1,w);
h_rev_true = exp(1j*w*L) .* h_rev;

% --- Convolution: DTFT{x1*x2} = X1(w).*X2(w) ---
h_product = freqz(x1,1,w) .* freqz(x2,1,w);
h_conv    = freqz(conv(x1,x2),1,w);
```
**How to "verify" for the report:** plot both sides of the property (e.g. `abs(h_product)` vs `abs(h_conv)`) — they should overlap exactly. State: "The two plots coincide, confirming the [shift/modulation/reversal/convolution] property of the DTFT."

---

## 8. Continuous-Time / Symbolic Fourier Transform

**🎯 Exam pattern:** symbolic FT of a known function, or the FT of a rectangular pulse (sinc function).

### 8.1 Symbolic Fourier Transform & Inverse
```matlab
syms x
f = exp(-x^2);         % <-- CHANGE THIS: any continuous-time expression
F = fourier(f);         % Fourier transform
f_inv = ifourier(F);    % should recover f
```

### 8.2 Fourier Transform of a rectangular pulse
```matlab
syms t w
a = heaviside(t + 0.5) - heaviside(t - 0.5);   % rect pulse, width 1, centered at 0
subplot(2,1,1); ezplot(a, [-3, 3]);
b = fourier(a);
subplot(2,1,2); ezplot(b, [-50, 50]);
```
**Theory reminder:** the FT of a rectangular pulse is a **sinc** function — if your plot doesn't look like a sinc, the pulse definition is probably wrong.

---

## 9. Fourier Series (Periodic Signals)

**🎯 Exam pattern:** "Find the Fourier Series coefficients of the given periodic signal" or "Demonstrate the Gibbs phenomenon for a square wave."

### 9.1 Compute FS coefficients via symbolic integration
```matlab
syms t
T0 = 1;                                   % <-- CHANGE THIS: fundamental period
x = 2*(heaviside(t) - heaviside(t - T0/4) + heaviside(t - 3*T0/4)); % periodic pulse, one period

N = 20;                                   % number of harmonics
X = zeros(1,N); w = zeros(1,N);
for k = 1:N
    w(k) = (k-1)*2*pi/T0;
    X(k) = double((1/T0) * int(x * exp(-1j*w(k)*t), t, 0, T0));   % FS coefficient formula
end

subplot(2,1,1); stem(w, abs(X));   title('Magnitude of FS coefficients');
subplot(2,1,2); stem(w, angle(X)); title('Phase of FS coefficients');
```
**Formula to write in your answer sheet:** X_k = (1/T0) ∫₀^T0 x(t)·e^(−jkω0t) dt, where ω0 = 2π/T0.

### 9.2 Gibbs phenomenon (square-wave synthesis from harmonics)
```matlab
t2 = 0:0.02:pi;
x = zeros(size(t2));
for k = 1:2:19                 % odd harmonics only (square wave has only odd harmonics)
    x = x + sin(k*t2)/k;
    y5((k+1)/2, :) = x;        % store each partial sum for comparison
end
plot(t2, y5(1:2:9,:)'); grid on; title('Gibbs Phenomenon');
xlabel('Time (t)'); ylabel('Amplitude');
```
**What to say if asked "what is Gibbs phenomenon":** near a discontinuity (the square wave's jump), the partial-sum approximation always overshoots by about 9%, no matter how many harmonics you add — adding more terms narrows the overshoot region but doesn't shrink the overshoot itself.

---

## 10. DFT / FFT

**🎯 Exam pattern:** the biggest bucket — direct DFT via matrix, FFT/IFFT, linear vs circular convolution via FFT, Parseval's theorem, circular shift property, spectrum of a real signal.

### 10.1 Direct DFT via matrix multiplication (dftmtx)
```matlab
x = [1,-1,2,-2];               % <-- CHANGE THIS
N = length(x);
Y = x * dftmtx(N);              % forward DFT
x_inv = (Y * conj(dftmtx(N))) / N;   % inverse DFT (should recover x)
```
**If asked to explain:** `dftmtx(N)` builds the N×N matrix of DFT basis exponentials; multiplying `x` by it performs the DFT summation directly from the definition, matching the theory formula exactly (useful if you're asked to "derive" the DFT, not just compute it).

### 10.2 FFT / IFFT basics
```matlab
y  = fft(x);
y1 = ifft(y);                   % should recover x (take real() if needed due to rounding)
```

### 10.3 Linear vs Circular convolution via FFT — same technique as §4.3/§4.4
This is the *identical* method as §4.3 (linear) and §4.4 (circular) — only the variable names differ. Don't memorize it twice; just remember the one rule:

**Zero-pad before FFT → linear convolution. Skip the zero-padding → circular convolution.** That single difference is a favorite "spot the error" trap question.
```matlab
x = [1 2]; h = [2 1];                    % <-- CHANGE THIS
% Linear (zero-padded to N = len(x)+len(h)-1):
x1 = [x, zeros(1, length(h)-1)];
h1 = [h, zeros(1, length(x)-1)];
y_linear = real(ifft(fft(x1) .* fft(h1)));

% Circular (equal length, NO padding — wraps around instead of extending):
x2 = [1 2 1 2]; h2 = [4 3 2 1];          % must already be same length
y_circular = real(ifft(fft(x2) .* fft(h2)));
```

### 10.4 Periodic even/odd decomposition (circular)
```matlab
x_val = [1,2,4,2,6,32,6,4,2];
N = 256;
x = [x_val, zeros(1, N-length(x_val))];
x_rev  = [x(1), x(N:-1:2)];      % circular time reversal (index 1 stays fixed)
x_even = 0.5*(x + x_rev);
X      = fft(x);
X_even = fft(x_even);
```

### 10.5 Parseval's theorem (energy: time domain vs frequency domain)
```matlab
x = [(1:128), (128:-1:1)];       % <-- CHANGE THIS
N = length(x);
E_time = sum(x .* x);
X = fft(x);
E_freq = sum(abs(X).^2) / N;
err = E_time - E_freq;           % should be ~0
disp('Energy (time):'); disp(E_time);
disp('Energy (freq):'); disp(E_freq);
disp('Error:'); disp(err);
```
**Formula for the report:** Σ|x[n]|² = (1/N) Σ|X[k]|² — energy is conserved between time and frequency domains.

### 10.6 Circular shift property of DFT
```matlab
x = [0 2 4 6 8 10 12 14 16];    % <-- CHANGE THIS
M = 5;                           % shift amount
y = circshift(x, -M);            % circular shift => phase-only change in DFT
xf = fft(x); yf = fft(y);
% abs(xf) == abs(yf)  (magnitude unchanged)
% angle(xf) ~= angle(yf)  (phase changes with the shift)
```
**One-liner for the report:** a circular shift in time changes only the *phase* of the DFT, not the *magnitude* — confirmed since `abs(xf)` and `abs(yf)` match but the angles differ.

### 10.7 Spectrum analysis of a real signal (magnitude spectrum, normalized)
```matlab
t = 0:0.01:1;
a = sin(2*pi*10*t) + sin(2*pi*100*t);   % two-tone test signal: 10 Hz + 100 Hz
b = fft(a);
e = abs(b) / length(a);                  % normalized amplitude spectrum
subplot(2,1,1); plot(t, a); title('Time domain');
subplot(2,1,2); plot(e);    title('Normalized FFT magnitude');
```
**If asked "what do you expect to see":** two sharp peaks in the magnitude spectrum, at locations corresponding to 10 Hz and 100 Hz.

---

## 11. Plotting & Utility Cheat Sheet

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

## 12. Quick Function-by-Purpose Index

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
| `residue` | partial fraction expansion (poles/residues) |
| `deconv` | polynomial long division (inverse Z-transform power series) |
| `sos2tf` | combine cascaded second-order sections into one transfer function |
| `buttord`, `butter` | Butterworth filter order & design |
| `zplane` | pole-zero plot |
| `circshift` | circular shift of a sequence |
| `poly` | build polynomial coefficients from known roots |
| `heaviside` | unit step function (for symbolic/continuous signals) |

---

## 13. If You're Panicking: 5-Minute Pre-Exam Checklist

- [ ] Always start with `clc; clear; close all;`
- [ ] For any shift: `u(n−k)` = right shift, `u(n+k)` = left shift.
- [ ] For circular vs linear convolution via FFT: **zero-pad = linear, no pad = circular.**
- [ ] For linearity/time-invariance/stability tests: the "proof" is always **difference ≈ 0** (or the sum staying finite for stability).
- [ ] `freqz(num, den, w)` = system response; `freqz(x, 1, w)` = DTFT of a plain sequence.
- [ ] Normalize filter frequencies by `fs/2` before `buttord`/`butter`.
- [ ] `dftmtx`, `residue`, `deconv`, `sos2tf`, `ztrans/iztrans` — know what each *returns* (matrix, [r,p,k], [quotient,remainder], [num,den], symbolic expr) so you don't fumble the output variables under time pressure.
- [ ] Every plot needs a `title()` — cheap marks, don't skip it.

---

*This file is organized so you can keep extending it: whenever you learn a new operation, drop it into the matching section instead of leaving it buried in a chapter you'll forget about.*
