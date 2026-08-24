# DSP MATLAB Master Codebook

Built from your 7 chapters of lab code. Instead of chapter order, everything is grouped **by operation**, so when you need "convolution" or "Z-transform" you go straight to that section instead of hunting through chapters. Every block is a generalized, reusable template — swap the variables marked `% <-- CHANGE THIS` and run.

**How to use this doc:** Ctrl+F the operation name (e.g. "residue", "freqz", "cconv") to jump straight to it. Section 12 has a one-line function index if you just need to remember *which command* does what.

---

## 0. Universal Header (put this at the top of every script)

```matlab
clc; clear; close all;   % Clear command window, workspace variables, and figures
```

---

## 1. Signal Generation

### 1.1 Standard discrete sequences (impulse, step, ramp, parabola)
```matlab
n = -10:10;                    % <-- CHANGE THIS: time range

impulse  = (n == 0);           % Unit impulse: 1 only at n=0
step     = (n >= 0);           % Unit step: 1 for n>=0
ramp     = n .* (n >= 0);      % Unit ramp: equals n for n>=0, else 0
parabola = 0.5*(n.^2) .* (n>=0); % Unit parabolic sequence

subplot(2,2,1); stem(n, impulse);  title('Unit Impulse');
subplot(2,2,2); stem(n, step);     title('Unit Step');
subplot(2,2,3); stem(n, ramp);     title('Unit Ramp');
subplot(2,2,4); stem(n, parabola); title('Unit Parabolic');
```

### 1.2 Shifted / scaled step combinations (building composite signals)
```matlab
n = -20:20;
u1 = double(n >= -3);      % u(n+3)  -> shifted left
u2 = 5*double(n >= 15);    % 5u(n-15) -> shifted right + scaled
x  = u1 + u2;               % add signals together to build a composite waveform
stem(n, x);
```

### 1.3 Exponential sequence  a^n
```matlab
n = -10:10;
a = 0.8;                    % <-- CHANGE THIS: 0<a<1 decaying, a>1 growing, negative a alternates sign
x = a .^ n;
stem(n, x);
```
> Loop version for comparing multiple `a` values at once:
```matlab
a_vals = [0.8, 1.5, -0.8, -1.2];
titles = {'0<a<1','a>1','-1<a<0','a<-1'};
for i = 1:4
    x = a_vals(i) .^ n;
    subplot(2,2,i); stem(n,x); title(titles{i});
end
```

### 1.4 Sinusoid / cosine signal
```matlab
n = 0:0.1:5;
x = 6 * cos(2*pi*1.2*n);    % A*cos(2*pi*f*n): amplitude A, frequency f
stem(n, x);
```

### 1.5 Chirp / swept-frequency signal
```matlab
n = 0:100;
a = pi/200;                 % <-- CHANGE THIS: sweep rate
x = cos(a * (n.^2));        % quadratic phase => frequency increases with n
stem(n, x);
```

### 1.6 Complex exponential
```matlab
n = 1:10;
x = (0.9*exp(1j*pi/3)).^n;  % magnitude 0.9, angle pi/3 per step -> decaying spiral
```

---

## 2. Signal Operations & Manipulation

### 2.1 Time shift / delay
```matlab
D = 10;                     % <-- CHANGE THIS: delay in samples
xd = [zeros(1,D), x];       % prepend D zeros to delay by D samples
```

### 2.2 Time reversal
```matlab
y2 = fliplr(y1);            % reverse a vector left-to-right (y1(-n))
```

### 2.3 Even & odd decomposition
```matlab
ye = 0.5*(x + fliplr(x));   % even part: 0.5*(x[n] + x[-n])
yo = 0.5*(x - fliplr(x));   % odd part:  0.5*(x[n] - x[-n])
```
> Note: `fliplr` only gives true `x[-n]` if your vector is symmetric about n=0. Otherwise construct the reversed index vector manually.

### 2.4 Weighted sum / linear combination
```matlab
a = 2; b = -3;               % <-- CHANGE THIS: scaling constants
x = a*x1 + b*x2;
```

### 2.5 Element-wise multiplication (modulation, windowing, etc.)
```matlab
y = x1 .* x2;                % use .* not * for sample-by-sample multiply
```

---

## 3. LTI System Analysis

All of these use a transfer function defined by numerator/denominator coefficients:
```matlab
num = [2.2403, 2.4908, 2.2403];   % <-- CHANGE THIS
den = [1, -0.4, 0.75];            % <-- CHANGE THIS
```

### 3.1 Impulse response
```matlab
N = 40;                      % number of samples to compute
h = impz(num, den, N);
stem(h); title('Impulse Response');
```

### 3.2 System response to any input
```matlab
ic = [0, 0];                              % zero initial conditions (relaxed system)
y  = filter(num, den, x, ic);             % x = any input sequence
```

### 3.3 Test Linearity
```matlab
x1 = cos(2*pi*0.1*n); x2 = cos(2*pi*0.4*n);
a = 2; b = -3;
x  = a*x1 + b*x2;

y1 = filter(num, den, x1, ic);
y2 = filter(num, den, x2, ic);
y  = filter(num, den, x,  ic);

yt = a*y1 + b*y2;
d  = y - yt;                              % should be ~0 if system is linear
```

### 3.4 Test Time-Invariance
```matlab
D  = 10;
xd = [zeros(1,D), x];
y  = filter(num, den, x,  ic);
yd = filter(num, den, xd, ic);
d  = y - yd(1+D : length(n)+D);           % should be ~0 if time-invariant
```

### 3.5 Test Stability (BIBO, via decaying impulse response)
```matlab
N = 200;
h = impz(num, den, N+1);
parsum = 0;
for k = 1:N+1
    parsum = parsum + abs(h(k));
    if abs(h(k)) < 1e-6, break; end       % stop once response has decayed to ~0
end
disp(parsum);                             % finite value => stable
```

---

## 4. Convolution & Correlation

### 4.1 Linear convolution (direct)
```matlab
x1 = [1,2,0,1]; x2 = [2,2,1,1];   % <-- CHANGE THIS
y = conv(x1, x2);
```

### 4.2 Linear convolution via circular convolution (must zero-pad!)
```matlab
N = length(x1) + length(x2) - 1;          % required length to avoid aliasing
x1e = [x1, zeros(1, N-length(x1))];
x2e = [x2, zeros(1, N-length(x2))];
ylin = cconv(x1e, x2e, N);
```

### 4.3 Linear convolution via DFT/FFT (frequency-domain multiplication)
```matlab
N = length(x1) + length(x2) - 1;
X = fft(x1, N);           % fft auto zero-pads to length N
H = fft(x2, N);
y_out = ifft(X .* H);     % multiply in freq domain, then inverse transform
```

### 4.4 Circular convolution (N-point, no extra padding)
```matlab
N = 4;                                    % <-- CHANGE THIS: circular length
x_dft = ifft(fft(x1) .* fft(x2));         % method 1: via DFT
x_direct = cconv(x1, x2, N);              % method 2: built-in (should match)
```

### 4.5 Autocorrelation / cross-correlation
```matlab
y = xcorr(x1);             % autocorrelation of x1 with itself
% y = xcorr(x1, x2);       % cross-correlation between two sequences
stem(y); title('Autocorrelation'); xlabel('Lag');
```

---

## 5. Z-Transform

### 5.1 Symbolic Z-transform & Inverse Z-transform
```matlab
syms n wo
a = n + 1;                  % <-- CHANGE THIS: any time-domain expression
b = ztrans(a);               % forward Z-transform
c = iztrans(b);               % inverse Z-transform (recovers a)
disp(b); disp(c);
```

### 5.2 Partial fraction expansion (residues, poles, direct terms)
```matlab
num = [1, 0, 0, 0];                 % <-- CHANGE THIS
den = poly([0.5, 0.75, 1]);         % build denominator from known pole locations
[r, p, k] = residue(num, den);      % r = residues, p = poles, k = direct terms
```

### 5.3 Inverse Z-transform via power series (long division)
```matlab
num = [1, 2, 1]; den = [1, -1, 0.3561];   % <-- CHANGE THIS
N = 5;                                    % how many terms of h[n] you want
num_padded = [num, zeros(1, N-1)];
[h, rem] = deconv(num_padded, den);       % h = the time-domain sequence terms
```

### 5.4 Cascaded second-order sections -> single transfer function
```matlab
nums = [1, -0.2235, 1.0000;    % each row = one SOS numerator [z0 z^-1 z^-2]
        1, -0.4378, 1.0000;
        1,  1.0000, 0.0000];
dens = [1, -1.4335, 0.8581;
        1, -1.2936, 0.5569;
        1, -0.6122, 0.0000];
[num, den] = sos2tf([nums, dens]);
num_padded = [num, zeros(1, N-1)];
[x, r] = deconv(num_padded, den);
```

### 5.5 Z-domain "convolution" = polynomial multiplication
```matlab
x1 = [2, 1, 0, -1, 3];      % coefficients of X1(z): z^0, z^-1, z^-2, ...
x2 = [1, -3, 2];
x3 = conv(x1, x2);          % multiplying polynomials = multiplying Z-transforms
```

---

## 6. Filter Design & Pole-Zero Analysis

### 6.1 Butterworth filter design (auto order + coefficients)
```matlab
fs = 1000;                                % <-- CHANGE THIS: sampling frequency
wp = [200 300]/(fs/2);                    % normalized passband edges
ws = [50 450]/(fs/2);                     % normalized stopband edges
[n, wc] = buttord(wp, ws, 3, 20);         % 3 dB passband ripple, 20 dB stopband atten.
[z, p, k] = butter(n, wp);                % zeros, poles, gain
```

### 6.2 Pole-zero plot
```matlab
zplane(z, p);
title('Pole-Zero Plot');
```

---

## 7. DTFT & Frequency Response

### 7.1 Frequency response of a system H(e^jw) from num/den
```matlab
w = -pi : 2*pi/255 : pi;            % frequency axis (or leave blank: [h,w]=freqz(num,den))
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
h1 = freqz(x1, 1, w);               % denominator = 1 => just the DTFT of x1
```

### 7.3 Manual DTFT (matrix-multiplication method, no freqz)
```matlab
n = 1:10; x = (0.9*exp(1j*pi/3)).^n;   % <-- CHANGE THIS
k = -200:200;
w = (pi/100)*k;
X_dtft = x * exp(-1j*pi/100).^(n' * k);   % sum(x[n]*exp(-jwn)) done as a matrix product
```

### 7.4 DTFT property checks (verify by comparing plots/values)
```matlab
w = -pi : 2*pi/255 : pi;

% --- Time-shift property: DTFT{x[n-d]} = e^-jwd * DTFT{x[n]} ---
d  = 10; x2 = [zeros(1,d), x1];
h1 = freqz(x1,1,w); h2 = freqz(x2,1,w);

% --- Frequency-shift (modulation): x[n]*e^(jw0 n) shifts spectrum by w0 ---
w0 = 0.2*pi; n = 0:length(x1)-1;
x_mod = exp(1j*w0*n) .* x1;

% --- Time reversal: DTFT{x[-n]} = X(e^-jw), extra phase correction if x starts at n=0 ---
L = length(x1)-1;
h_rev = freqz(fliplr(x1),1,w);
h_rev_true = exp(1j*w*L) .* h_rev;

% --- Convolution property: DTFT{x1*x2} = X1(w).*X2(w) ---
h_product = freqz(x1,1,w) .* freqz(x2,1,w);
h_conv    = freqz(conv(x1,x2),1,w);
```

---

## 8. Continuous-Time / Symbolic Fourier Transform

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

---

## 9. Fourier Series (Periodic Signals)

### 9.1 Compute FS coefficients via symbolic integration
```matlab
syms t
T0 = 1;                                   % <-- CHANGE THIS: fundamental period
x = 2*(heaviside(t) - heaviside(t - T0/4) + heaviside(t - 3*T0/4)); % periodic pulse

N = 20;                                   % number of harmonics
X = zeros(1,N); w = zeros(1,N);
for k = 1:N
    w(k) = (k-1)*2*pi/T0;
    X(k) = double((1/T0) * int(x * exp(-1j*w(k)*t), t, 0, T0));
end

subplot(2,1,1); stem(w, abs(X));   title('Magnitude of FS coefficients');
subplot(2,1,2); stem(w, angle(X)); title('Phase of FS coefficients');
```

### 9.2 Gibbs phenomenon (square-wave synthesis from harmonics)
```matlab
t2 = 0:0.02:pi;
x = zeros(size(t2));
for k = 1:2:19                 % odd harmonics only
    x = x + sin(k*t2)/k;
    y5((k+1)/2, :) = x;        % store each partial sum
end
plot(t2, y5(1:2:9,:)'); title('Gibbs Phenomenon');
```

---

## 10. DFT / FFT

### 10.1 Direct DFT via matrix multiplication (dftmtx)
```matlab
x = [1,-1,2,-2];               % <-- CHANGE THIS
N = length(x);
Y = x * dftmtx(N);              % forward DFT
x_inv = (Y * conj(dftmtx(N))) / N;   % inverse DFT
```

### 10.2 FFT / IFFT basics
```matlab
y  = fft(x);
y1 = ifft(y);                   % should recover x (take real() if needed)
```

### 10.3 Linear convolution via FFT (zero-pad first!)
```matlab
x = [1 2]; h = [2 1];           % <-- CHANGE THIS
x1 = [x, zeros(1, length(h)-1)];
h1 = [h, zeros(1, length(x)-1)];
y  = real(ifft(fft(x1) .* fft(h1)));   % linear convolution result
```

### 10.4 Circular convolution via FFT (equal length, no padding)
```matlab
x = [1 2 1 2]; h = [4 3 2 1];   % must be same length
y = real(ifft(fft(x) .* fft(h)));      % circular convolution result
```

### 10.5 Periodic even/odd decomposition (circular)
```matlab
x_val = [1,2,4,2,6,32,6,4,2];
N = 256;
x = [x_val, zeros(1, N-length(x_val))];
x_rev  = [x(1), x(N:-1:2)];      % circular time reversal
x_even = 0.5*(x + x_rev);
X      = fft(x);
X_even = fft(x_even);
```

### 10.6 Parseval's theorem (energy: time domain vs frequency domain)
```matlab
x = [(1:128), (128:-1:1)];       % <-- CHANGE THIS
N = length(x);
E_time = sum(x .* x);
X = fft(x);
E_freq = sum(abs(X).^2) / N;
error = E_time - E_freq;         % should be ~0
```

### 10.7 Circular shift property of DFT
```matlab
x = [0 2 4 6 8 10 12 14 16];    % <-- CHANGE THIS
M = 5;                           % shift amount
y = circshift(x, -M);            % circular shift => phase-only change in DFT
xf = fft(x); yf = fft(y);
% abs(xf) == abs(yf), but angle(xf) ~= angle(yf)
```

### 10.8 Spectrum analysis of a real signal (magnitude spectrum, normalized)
```matlab
t = 0:0.01:1;
a = sin(2*pi*10*t) + sin(2*pi*100*t);   % two-tone test signal, 10 Hz + 100 Hz
b = fft(a);
e = abs(b) / length(a);                  % normalized amplitude spectrum
subplot(2,1,1); plot(t, a); title('Time domain');
subplot(2,1,2); plot(e);    title('Normalized FFT magnitude');
```

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
| `dftmtx` | DFT via explicit matrix (educational, matches theory) |
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

*Tip: this file is organized so you can keep adding to it — whenever you learn a new operation, drop it into the matching section (or add a new one) instead of leaving it buried in a chapter you'll forget about.*
