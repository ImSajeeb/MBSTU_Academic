# DSP MATLAB — Master Codebook (Type-wise)

Same 35 programs from your `Full.docx`, but grouped **by what they do** instead of by chapter — so all convolution questions sit together, all Z-transform questions sit together, etc. Full code is kept exactly as-is (nothing simplified or removed), and each entry keeps its **question/title** and its **original chapter.number** in brackets, so you can still cross-reference your book or the serial-order file if needed.

---

## Contents

**1. Elementary & Special Signal Generation**
- [1.1] Generation of Elementary Signals in Discrete-time
- [1.2] Generation of a Discrete-time Exponential Sequence
- [1.5] Generation of the Composite Sequence x(n)=u(n+3)+5u(n-15)+4u(n+10)
- [1.6] Generation of Swept-frequency Sinusoidal Signal

**2. Signal Operations (Multiplication, Even/Odd Decomposition)**
- [1.3] Multiplication of Discrete-time Signals
- [1.4] Even and Odd Components of the Sequence y(n)=u(n)-u(n-10)

**3. LTI System Properties (Impulse Response, Linearity, Time-Invariance, Stability)**
- [1.8] Computation of Impulse Response
- [1.7] Checking the Time-invariance Property
- [1.9] Checking the Linearity of a System
- [1.10] Testing the Stability of a System

**4. Linear Convolution (Direct / via Circular Conv / via DFT / via FFT)**
- [2.1] Convolution of Two Sequences
- [2.2] Linear Convolution via Circular Convolution
- [2.3] Linear Convolution Using DFT
- [6.4] Linear Convolution Using DFT
- [7.2] Linear Convolution Using FFT

**5. Circular Convolution (Time-domain / DFT-based / FFT-based)**
- [2.4] Circular Convolution Using DFT Based Approach
- [6.5] Circular Convolution Using DFT
- [7.3] Circular Convolution Using FFT

**6. Correlation**
- [2.5] Computation of Correlation

**7. Z-Transform & Inverse Z-Transform**
- [3.1] Z-transform and Inverse Z-transform of Given Signals
- [3.2] Finding the Residues of Z^3/[(z-0.5)(z-0.75)(z-1)]
- [3.3] Inverse Z-transform by the Polynomial Division Method
- [3.4] Inverse Z-transform for the Cascaded Form Using Polynomial Division Method
- [3.6] Convolution Using Z-transform

**8. Filter Design & Pole-Zero Analysis**
- [3.5] Pole-zero Plot of a Butterworth Band Pass Filter

**9. Continuous-Time / Symbolic Fourier Transform**
- [5.1] Fourier Transform and Inverse Fourier Transform of a Given Sequence
- [5.2] Fourier Transform of a Signal u(t+0.5)-u(t-0.5)

**10. DTFT — Evaluation & Properties**
- [5.3] Evaluation and Plotting of DTFT of the Transfer Function
- [5.4] Time Shifting Property of DTFT
- [5.5] Frequency Shifting Property of DTFT
- [5.6] Time Convolution Property of DTFT
- [5.7] Time Reversal Property of DTFT
- [5.8] Frequency Response of the Given System
- [5.9] Periodicity Property of DTFT

**11. Fourier Series & Gibbs Phenomenon**
- [6.1] Fourier Series Representation of a Train of Pulses
- [6.2] Fourier Series Representation of a Full Wave Rectified Wave
- [6.9] Gibbs Phenomenon

**12. DFT/FFT Fundamentals & Properties**
- [6.3] Direct Computation of Discrete Fourier Transform (Matrix Formulation)
- [7.1] Calculation of the DFT of a Given Sequence Using FFT
- [6.6] Relation Between DFTs of the Periodic Even and Odd Parts of a Real Sequence
- [6.7] Parseval's Relation of DFT
- [6.8] Circular Time Shifting Property of DFT
- [7.4] Plotting of DFT of Sinusoidal Wave

---

# 1. Elementary & Special Signal Generation

## [1.1] Generation of Elementary Signals in Discrete-time

```matlab
clc; clear; close all; % Clear command window, variables, and close existing figures

% --- Unit Impulse & Unit Step ---
n1 = -10:10; % Define time vector 'n1' from -10 to 10
impulse = (n1 == 0); % Generate Unit Impulse: returns 1 only when n1 is exactly 0
subplot(2,2,1); % Select the 1st section (top-left) of a 2x2 plotting grid
stem(n1, impulse); % Plot the discrete impulse signal using stems
title('Unit Impulse Sequence'); % Add a title to the first plot

step = (n1 >= 0); % Generate Unit Step: returns 1 for all n1 greater than or equal to 0
subplot(2,2,2); % Select the 2nd section (top-right) of the 2x2 grid
stem(n1, step); % Plot the discrete step signal
title('Unit Step Sequence'); % Add a title to the second plot

% --- Unit Ramp & Unit Parabolic ---
n2 = 0:10; % Define a new time vector 'n2' strictly from 0 to 10
ramp = n2; % Generate Unit Ramp: amplitude is directly equal to time 'n2'
subplot(2,2,3); % Select the 3rd section (bottom-left) of the 2x2 grid
stem(n2, ramp); % Plot the discrete ramp signal
title('Unit Ramp Sequence'); % Add a title to the third plot

parabola = 0.5 * (n2.^2); % Generate Unit Parabolic: amplitude is 1/2 of 'n2' squared
subplot(2,2,4); % Select the 4th section (bottom-right) of the 2x2 grid
stem(n2, parabola); % Plot the discrete parabolic signal
title('Unit Parabolic Sequence'); % Add a title to the fourth plot
```

## [1.2] Generation of a Discrete-time Exponential Sequence

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

n = -10:10; % Define the discrete time vector 'n' from -10 to 10
a_vals = [0.8, 1.5, -0.8, -1.2]; % Store the four different base 'a' values in an array
titles = {'0 < a < 1', 'a > 1', '-1 < a < 0', 'a < -1'}; % Store the plot titles in a cell array

for i = 1:4 % Start a loop that will run 4 times (once for each 'a' value)
    x = a_vals(i) .^ n; % Calculate the exponential sequence: current 'a' raised to power of 'n'
    subplot(2, 2, i); % Select the current plot position (1, 2, 3, or 4) in a 2x2 grid
    stem(n, x); % Plot the discrete-time signal using stems
    title(['x[n] for ', titles{i}]); % Dynamically generate and set the title for the subplot
    xlabel('Samples n'); % Label the x-axis
    ylabel('Sample Amplitude'); % Label the y-axis
end % End the loop
```

## [1.5] Generation of the Composite Sequence x(n)=u(n+3)+5u(n-15)+4u(n+10)

```matlab
clc; clear; close all; % Clear the command window, workspace memory, and close all figures

n = -20:20; % Define the discrete time vector 'n' from -20 to 20

% --- Generate the Individual Signals ---
u1 = double(n >= -3); % u(n+3): Generates 1s when n is greater than or equal to -3, 0s otherwise
u2 = 5 * double(n >= 15); % 5u(n-15): Generates 1s when n >= 15, then scales amplitude by 5
u3 = 4 * double(n >= -10); % 4u(n+10): Generates 1s when n >= -10, then scales amplitude by 4

% --- Generate the Composite Signal ---
x = u1 + u2 + u3; % Add the three individual signals together element-by-element

% --- Plotting ---
subplot(4, 1, 1); % Select the 1st position in a 4-row, 1-column grid
stem(n, u1); % Plot the first signal
title('u(n+3)'); % Title for the first plot

subplot(4, 1, 2); % Select the 2nd position
stem(n, u2); % Plot the second scaled/shifted signal
title('5u(n-15)'); % Title for the second plot

subplot(4, 1, 3); % Select the 3rd position
stem(n, u3); % Plot the third scaled/shifted signal
title('4u(n+10)'); % Title for the third plot

subplot(4, 1, 4); % Select the 4th position
stem(n, x); % Plot the final composite signal
title('Composite Signal x(n)'); % Title for the final combined plot
```

## [1.6] Generation of Swept-frequency Sinusoidal Signal

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

n = 0:100; % Define discrete time vector 'n' from 0 to 100
a = pi / 200; % Define the frequency sweep rate (simplified from pi/2/100)
x = cos(a * (n.^2)); % Generate the swept-frequency signal (cosine with a quadratic phase)

stem(n, x); % Plot the sequence as discrete stems
xlabel('Discrete time n'); % Label the x-axis
ylabel('Amplitude'); % Label the y-axis
title('Swept-Frequency Sinusoidal Signal'); % Add a title to the plot
```

---

# 2. Signal Operations (Multiplication, Even/Odd Decomposition)

## [1.3] Multiplication of Discrete-time Signals

```matlab
clc; clear; close all; % Clear the command window, empty workspace memory, and close figures

n = 0:0.1:5; % Define time vector 'n' from 0 to 5 with a step size of 0.1

% --- First Signal: Growing Exponential ---
x1 = 6 * (2 .^ n); % Calculate x1[n] = 6 * 2^n (using .^ for element-wise power)
subplot(3, 1, 1); % Select the 1st position in a 3-row, 1-column grid
stem(n, x1); % Plot x1 as a discrete sequence
title('Signal x_1[n] = 6(2^n)'); % Title for the first plot

% --- Second Signal: Cosine Wave ---
x2 = 2 * cos(2 * pi * 1.2 * n); % Calculate x2[n] = 2 * cos(2*pi*f*n) with frequency f = 1.2
subplot(3, 1, 2); % Select the 2nd position in the grid
stem(n, x2); % Plot x2 as a discrete sequence
title('Signal x_2[n] = 2cos(\omega n)'); % Title for the second plot

% --- Product of the Two Signals ---
y = x1 .* x2; % Multiply x1 and x2 element-by-element using the '.*' operator
subplot(3, 1, 3); % Select the 3rd position in the grid
stem(n, y); % Plot the final multiplied signal
title('Result y[n] = x_1[n] \times x_2[n]'); % Title for the bottom plot
xlabel('Time n'); % Label the x-axis for the final graph
ylabel('Amplitude'); % Label the y-axis for the final graph
```

## [1.4] Even and Odd Components of the Sequence y(n)=u(n)-u(n-10)

```matlab
clc; clear; close all; % Clear the command window, empty workspace memory, and close figures

n = -15:15; % Define the discrete time vector 'n' from -15 to 15
y1 = double(n >= 0 & n < 10); % Generate y(n) = u(n) - u(n-10). Gives 1s for n=0 to 9, 0s elsewhere.
y2 = fliplr(y1); % Generate the time-reversed sequence y(-n) by flipping the array left-to-right

ye = 0.5 * (y1 + y2); % Calculate the Even component using the standard formula
yo = 0.5 * (y1 - y2); % Calculate the Odd component using the standard formula

subplot(2, 2, 1); % Select the top-left quadrant of a 2x2 grid
stem(n, y1); % Plot the original signal
title('Original Signal y(n)'); % Title for the first plot

subplot(2, 2, 2); % Select the top-right quadrant
stem(n, y2); % Plot the time-reversed signal
title('Reversed Signal y(-n)'); % Title for the second plot

subplot(2, 2, 3); % Select the bottom-left quadrant
stem(n, ye); % Plot the even component
title('Even Component y_e(n)'); % Title for the third plot

subplot(2, 2, 4); % Select the bottom-right quadrant
stem(n, yo); % Plot the odd component
title('Odd Component y_o(n)'); % Title for the fourth plot
```

---

# 3. LTI System Properties (Impulse Response, Linearity, Time-Invariance, Stability)

## [1.8] Computation of Impulse Response

```matlab
clc; clear; close all; % Clear command window, empty workspace memory, and close figures

N = 40; % Define the number of samples to compute (first 40 steps)

% --- Filter Coefficients ---
num = [2.2403, 2.4908, 2.2403]; % Numerator coefficients of the system's transfer function
den = [1, -0.4, 0.75]; % Denominator coefficients of the system's transfer function

% --- Compute and Plot ---
y = impz(num, den, N); % Built-in function to calculate the impulse response sequence
stem(y); % Plot the resulting sequence using stems
xlabel('Discrete time n'); % Label the x-axis
ylabel('Amplitude'); % Label the y-axis
title('Impulse Response of the Filter'); % Add a title to the plot
```

## [1.7] Checking the Time-invariance Property

```matlab
clc; clear; close all; % Clear the command window, empty workspace memory, and close figures

n = 0:40; % Define discrete time vector 'n' from 0 to 40 (total 41 samples)
D = 10; % Set the time delay amount to 10 samples

% --- 1. Define Input Signals ---
x = 3*cos(2*pi*0.1*n) - 2*cos(2*pi*0.4*n); % Original input signal x[n]
xd = [zeros(1, D), x]; % Delayed input signal x[n-D], created by prepending 10 zeros

% --- 2. Define the System (Digital Filter) ---
num = [2.2403, 2.4908, 2.2403]; % Numerator coefficients of the system's transfer function
den = [1, -0.4, 0.75]; % Denominator coefficients of the system's transfer function
ic = [0, 0]; % Set initial conditions to zero (assuming a relaxed system)

% --- 3. Compute Outputs ---
y = filter(num, den, x, ic); % Compute output y[n] from original input x[n]
yd = filter(num, den, xd, ic); % Compute output yd[n] from delayed input xd[n]

% --- 4. Check Time-Invariance ---
% Subtract original y[n] from the shifted version of yd[n]. 
% (1+D to 41+D extracts exactly 41 samples starting after the delay)
d = y - yd(1+D : 41+D); 

% --- 5. Plotting ---
subplot(3, 1, 1); stem(n, y); title('Original Output y[n]'); % Plot standard output
subplot(3, 1, 2); stem(n, yd(1:41)); title('Output due to Delayed Input'); % Plot delayed output
subplot(3, 1, 3); stem(n, d); title('Difference d[n]'); % Plot the difference
xlabel('Discrete time'); % Label the x-axis (only needed on the bottom plot for neatness)
ylabel('Amplitude'); % Label the y-axis
```

## [1.9] Checking the Linearity of a System

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Signals and Constants ---
n = 0:50; % Define discrete time vector 'n' from 0 to 50
a = 2; b = -3; % Define scaling constants (weights)
x1 = cos(2 * pi * 0.1 * n); % Generate first input signal
x2 = cos(2 * pi * 0.4 * n); % Generate second input signal
x = (a * x1) + (b * x2); % Create a weighted sum of the two inputs

% --- 2. Define the System (Filter) ---
num = [2.2403, 2.4908, 2.2403]; % Numerator coefficients of the system
den = [1, -0.4, 0.75]; % Denominator coefficients of the system
ic = [0, 0]; % Set zero initial conditions (assumes the system is at rest)

% --- 3. Compute Outputs ---
y1 = filter(num, den, x1, ic); % Find output y1[n] when only x1[n] is input
y2 = filter(num, den, x2, ic); % Find output y2[n] when only x2[n] is input
y = filter(num, den, x, ic); % Find output y[n] when the weighted sum x[n] is input
yt = (a * y1) + (b * y2); % Calculate the weighted sum of the individual outputs

% --- 4. Check Linearity ---
d = y - yt; % Calculate the difference between the two methods

% --- 5. Plotting ---
subplot(3, 1, 1); stem(n, y); title('Output Due to Weighted Input'); % Plot first method
subplot(3, 1, 2); stem(n, yt); title('Weighted Sum of Outputs'); % Plot second method
subplot(3, 1, 3); stem(n, d); title('Difference Signal'); % Plot the difference
xlabel('Discrete time n'); % Label x-axis (placed at the bottom for cleanliness)
```

## [1.10] Testing the Stability of a System

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the System ---
num = [1, -0.8]; % Numerator coefficients of the system's transfer function
den = [1, 1.5, 0.9]; % Denominator coefficients of the system's transfer function
N = 200; % Number of samples to test
h = impz(num, den, N+1); % Generate 201 samples of the impulse response

% --- 2. Test for Stability ---
parsum = 0; % Initialize a variable to hold the running sum
for k = 1:N+1 % CORRECTED: Loop from the 1st sample to the 201st sample
    parsum = parsum + abs(h(k)); % Add the absolute value of the current sample to the sum
    if abs(h(k)) < 1e-6 % If the amplitude decays to virtually zero (less than 0.000001)
        break; % Stop the loop early, as the system has effectively settled
    end
end

% --- 3. Plotting and Output ---
stem(h); % Plot the impulse response sequence
xlabel('Discrete time n'); % Label the x-axis
ylabel('Amplitude'); % Label the y-axis
title('System Stability Check'); % Add a title to the plot

disp('Absolute sum at decay point:'); % Print text to the console
disp(parsum); % Print the calculated partial sum
```

---

# 4. Linear Convolution (Direct / via Circular Conv / via DFT / via FFT)

## [2.1] Convolution of Two Sequences

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Sequences ---
x1 = [1, 2, 0, 1]; % Define the first discrete input sequence x1[n]
x2 = [2, 2, 1, 1]; % Define the second discrete input sequence x2[n]

% --- 2. Compute Convolution ---
y = conv(x1, x2); % Use MATLAB's built-in function to convolve x1 and x2
disp('The convolution output is:'); % Print descriptive text to the console
disp(y); % Print the resulting array y to the console

% --- 3. Plotting ---
subplot(3, 1, 1); stem(x1); title('First Input Sequence'); % Plot x1 in the top section
subplot(3, 1, 2); stem(x2); title('Second Input Sequence'); % Plot x2 in the middle section
subplot(3, 1, 3); stem(y); title('Convolution Output'); % Plot the result y in the bottom section
xlabel('Discrete time n'); % Label the x-axis for the bottom plot
ylabel('Amplitude'); % Label the y-axis for the bottom plot
```

## [2.2] Linear Convolution via Circular Convolution

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Sequences ---
x1 = [1, 2, 3, 4, 5]; % Define first sequence (length L = 5)
x2 = [2, 2, 0, 1, 1]; % Define second sequence (length M = 5)

% --- 2. Zero-Padding ---
N = length(x1) + length(x2) - 1; % Total length to prevent aliasing (5+5-1 = 9)
x1e = [x1, zeros(1, N - length(x1))]; % Pad x1 to length N
x2e = [x2, zeros(1, N - length(x2))]; % Pad x2 to length N

% --- 3. Compute and Compare ---
ylin = cconv(x1e, x2e, N); % Linear convolution via circular convolution
disp('Linear convolution via circular convolution:');
disp(ylin);

y = conv(x1, x2); % Direct linear convolution
disp('Direct linear convolution:');
disp(y);

% --- 4. Plotting ---
subplot(2, 2, 1); stem(0:length(x1)-1, x1, 'filled'); title('x_1[n]');
subplot(2, 2, 2); stem(0:length(x2)-1, x2, 'filled'); title('x_2[n]');
subplot(2, 2, 3); stem(0:N-1, ylin, 'filled'); title('cconv');
subplot(2, 2, 4); stem(0:N-1, y, 'filled'); title('conv');
```

## [2.3] Linear Convolution Using DFT

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Sequences ---
x = [1, 2]; % Define the first sequence (input signal)
h = [2, 1]; % Define the second sequence (impulse response)

% --- 2. Calculate Required Length ---
N = length(x) + length(h) - 1; % Calculate length N to avoid aliasing (2+2-1 = 3)

% --- 3. Compute DFTs (with automatic zero-padding) ---
X = fft(x, N); % Compute the N-point DFT of x (MATLAB automatically adds zeros)
H = fft(h, N); % Compute the N-point DFT of h (MATLAB automatically adds zeros)

% --- 4. Frequency Domain Multiplication & Inverse DFT ---
Y = X .* H; % Multiply the two signals in the frequency domain element-by-element
y_out = ifft(Y); % Apply Inverse DFT to bring the result back to the time domain

% --- 5. Output Result ---
disp('The linear convolution of the given sequence is:'); % Print label to console
disp(y_out); % Print the final convolved sequence to the console
subplot(3, 1, 1); stem(0:length(x)-1, x, 'filled'); title('Input x[n]');
subplot(3, 1, 2); stem(0:length(h)-1, h, 'filled'); title('Impulse Response h[n]');
subplot(3, 1, 3); stem(0:N-1, y_out, 'filled'); title('Linear Convolution y[n]');
```

## [6.4] Linear Convolution Using DFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Define input sequences x[n] and h[n]
x = [1 2];
h = [2 1];

% Zero-padding to length (N1 + N2 - 1) to avoid circular alias
x1 = [x zeros(1, length(h)-1)];
h1 = [h zeros(1, length(x)-1)];

% Compute Fast Fourier Transform (FFT) of padded sequences
X = fft(x1);
H = fft(h1);

% Frequency-domain multiplication
y = X .* H;

% Inverse FFT to obtain linear convolution result
y1 = ifft(y);

disp('the linear convolution of the given sequence')
disp(y1)
```

## [7.2] Linear Convolution Using FFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Define input sequences x[n] and h[n]
x = [1 2];
h = [2 1];

% Zero-padding to length (N1 + N2 - 1)
x1 = [x zeros(1, length(h)-1)];
h1 = [h zeros(1, length(x)-1)];

% FFT computation and multiplication
X = fft(x1);
H = fft(h1);
y = X .* H;
y1 = real(ifft(y)); % Linear convolution result

disp('the linear convolution of the given sequence')
disp(y1)

% --- Plotting the sequences and convolution result ---
subplot(3, 1, 1); stem(x, 'filled'); title('Sequence x[n]');
subplot(3, 1, 2); stem(h, 'filled'); title('Sequence h[n]');
subplot(3, 1, 3); stem(y1, 'filled'); title('Linear Convolution y[n]');
```

---

# 5. Circular Convolution (Time-domain / DFT-based / FFT-based)

## [2.4] Circular Convolution Using DFT Based Approach

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close figures

% --- 1. Define Sequences ---
x1 = [1, 2, 0, 1]; % Define the first sequence
x2 = [2, 2, 1, 1]; % Define the second sequence
N = 4; % Define the N-point length for the circular convolution

% --- 2. DFT Method ---
X = fft(x1) .* fft(x2); % Compute DFTs of both signals and multiply them element-wise
x_dft = ifft(X); % Compute the Inverse DFT to bring the result back to the time domain

disp('Circular convolution by using DFT method:'); % Print label
disp(x_dft); % Display the result of the frequency-domain approach

% --- 3. Direct Time-Domain Method ---
x_direct = cconv(x1, x2, N); % Use standard built-in circular convolution function

disp('Circular convolution by using time domain method:'); % Print label
disp(x_direct); % Display the direct result to prove both outputs are identical

n = 0:N-1;
subplot(2, 2, 1); stem(n, x1, 'filled'); title('x1[n]');
subplot(2, 2, 2); stem(n, x2, 'filled'); title('x2[n]');
subplot(2, 2, 3); stem(n, real(x_dft), 'filled'); title('Via DFT (ifft)');
subplot(2, 2, 4); stem(n, x_direct, 'filled'); title('Via Time Domain (cconv)');
```

## [6.5] Circular Convolution Using DFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Define equal-length input sequences
x = [1 2 1 2];
h = [4 3 2 1];

% Compute N-point Fast Fourier Transform (FFT) of both sequences
X = fft(x);
H = fft(h);

% Frequency-domain element-wise multiplication
y = X .* H;

% Compute Inverse FFT and take real part to get circular convolution result
y1 = real(ifft(y));

disp('the circular convolution of the given sequence')
disp(y1)
```

## [7.3] Circular Convolution Using FFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Define input sequences of equal length
x = [1 2 1 2];
h = [4 3 2 1];

% Fast Fourier Transform (FFT) of both sequences
X = fft(x);
H = fft(h);

% Frequency domain multiplication and Inverse FFT
y = X .* H;
y1 = real(ifft(y)); % Circular convolution result

disp('the circular convolution of the given sequence')
disp(y1)

% --- Plotting the sequences and circular convolution result ---
subplot(3, 1, 1); stem(x, 'filled'); title('Sequence x[n]');
subplot(3, 1, 2); stem(h, 'filled'); title('Sequence h[n]');
subplot(3, 1, 3); stem(y1, 'filled'); title('Circular Convolution y[n]');
```

---

# 6. Correlation

## [2.5] Computation of Correlation

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Sequence ---
x1 = [1, 3, 0, 4]; % Define the discrete input sequence

% --- 2. Compute Correlation ---
y = xcorr(x1); % Compute the autocorrelation (correlating the signal with itself)

% --- 3. Plotting ---
subplot(2, 1, 1); stem(x1); % Plot the original sequence in the top half
title('Input Sequence'); % Title for the top plot
xlabel('Discrete time'); ylabel('Amplitude'); % Label axes

subplot(2, 1, 2); stem(y); % Plot the autocorrelation result in the bottom half
title('Autocorrelation of the Input Sequence'); % Title for the bottom plot
xlabel('Time Lag'); ylabel('Amplitude'); % Label axes (x-axis represents 'lag' or shift)
```

---

# 7. Z-Transform & Inverse Z-Transform

## [3.1] Z-transform and Inverse Z-transform of Given Signals

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close all figures

% Declare symbolic variables for discrete-time index (n) and angular frequency (wo)
syms n wo 

% ========================================================
% First Signal: Linear Ramp Signal (n + 1)
% ========================================================
a = n + 1;                      % Define the input time-domain expression
disp('The input equation is')   % Display header label for the input equation
disp(a)                         % Print the symbolic expression for 'a'

b = ztrans(a);                  % Compute the forward Z-transform of 'a'
disp('The z-transform is')      % Display header label for the Z-transform result
disp(b)                         % Print the resulting Z-domain expression 'b'

c = iztrans(b);                 % Compute the Inverse Z-transform of 'b' to recover the signal
disp('The inverse z-transform is') % Display header label for the inverse Z-transform result
disp(c)                         % Print the recovered time-domain expression 'c'

% ========================================================
% Second Signal: Discrete Cosine Wave (cos(wo*n))
% ========================================================
a1 = cos(wo*n);                 % Define the second input time-domain expression
disp('The input equation is')   % Display header label for the input equation
disp(a1)                        % Print the symbolic expression for 'a1'

b1 = ztrans(a1);                % Compute the forward Z-transform of 'a1'
disp('The z-transform is')      % Display header label for the Z-transform result
disp(b1)                        % Print the resulting Z-domain expression 'b1'

c1 = iztrans(b1);               % Compute the Inverse Z-transform of 'b1' to recover the signal
disp('The inverse z-transform is') % Display header label for the inverse Z-transform result
disp(c1)                        % Print the recovered time-domain expression 'c1'
```

## [3.2] Finding the Residues of Z^3/[(z-0.5)(z-0.75)(z-1)]

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Polynomials ---
num = [1, 0, 0, 0]; % Coefficients for the numerator Z^3 (1*z^3 + 0*z^2 + 0*z + 0)
den = poly([0.5, 0.75, 1]); % Instantly generate denominator coefficients from its roots

% --- 2. Calculate Partial Fraction Expansion ---
[r, p, k] = residue(num, den); % Calculate residues (r), poles (p), and direct terms (k)

% --- 3. Display Results ---
disp('Residues (r):'); % Print label
disp(r); % Display the calculated residues for each fraction
disp('Poles (p):'); % Print label
disp(p); % Display the poles (should match the 0.5, 0.75, 1 we started with)
disp('Direct Term (k):'); % Print label
disp(k); % Display any direct polynomial terms (happens because num and den are same degree)
```

## [3.3] Inverse Z-transform by the Polynomial Division Method

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Transfer Function ---
num = [1, 2, 1]; % Numerator coefficients: 1 + 2z^(-1) + 1z^(-2)
den = [1, -1, 0.3561]; % Denominator coefficients: 1 - 1z^(-1) + 0.3561z^(-2)
N = 5; % Define how many terms of the sequence you want to calculate

% --- 2. Prepare for Division ---
% Pad the numerator with zeros. This acts like adding ".0000" to a decimal 
% so you can carry out long division to a specific number of places.
num_padded = [num, zeros(1, N-1)]; 

% --- 3. Polynomial Division ---
% deconv performs polynomial long division (deconvolution).
% 'h' stores the quotient (our answer), 'rem' stores the remainder.
[h, rem] = deconv(num_padded, den); 

% --- 4. Display Result ---
disp('The first few terms of the inverse Z-transform are:'); % Print label
disp(h); % Display the resulting time-domain sequence
```

## [3.4] Inverse Z-transform for the Cascaded Form Using Polynomial Division Method

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

N = 5; % Define the number of power series points to calculate (first 5 terms)

% --- 1. Define Cascaded Sections (Second-Order Sections) ---
% Each row is a section, columns are coefficients for [z^0, z^-1, z^-2]
nums = [1, -0.2234600, 1.000000;   % Numerator 1 (N1)
        1, -0.4377883, 1.000000;   % Numerator 2 (N2)
        1,  1.0000000, 0.000000];  % Numerator 3 (N3)

dens = [1, -1.4335090, 0.858110;   % Denominator 1 (D1)
        1, -1.2936010, 0.556929;   % Denominator 2 (D2)
        1, -0.6121590, 0.000000];  % Denominator 3 (D3)

% --- 2. Convert to Single Transfer Function ---
% sos2tf takes the combined [nums, dens] matrix and multiplies everything 
% out to create one single numerator array and one single denominator array.
[num, den] = sos2tf([nums, dens]); 

% --- 3. Polynomial Division ---
num_padded = [num, zeros(1, N-1)]; % Pad numerator with zeros to extend the division process
[x, r] = deconv(num_padded, den); % Perform polynomial division (deconvolution) to get the sequence

% --- 4. Display Results ---
disp('The first five values of the inverse Z-transform are:'); % Print label
disp(x); % Display the resulting time-domain sequence
```

## [3.6] Convolution Using Z-transform

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Sequences ---
% The array indices correspond to coefficients of z^0, z^-1, z^-2, etc.
x1 = [2,  1, 0, -1, 3]; % x1(z) = 2 + 1z^(-1) + 0z^(-2) - 1z^(-3) + 3z^(-4)
x2 = [1, -3, 2];        % x2(z) = 1 - 3z^(-1) + 2z^(-2)

% --- 2. Convolution (Polynomial Multiplication) ---
x3 = conv(x1, x2) % The output sequence x3 corresponds to the product X1(z) * X2(z)
```

---

# 8. Filter Design & Pole-Zero Analysis

## [3.5] Pole-zero Plot of a Butterworth Band Pass Filter

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Filter Specifications ---
% Frequencies must be normalized to the Nyquist frequency (fs/2). 
% If fs = 1000, Nyquist is 500. So we divide all target frequencies by 500.
wp = [200/500, 300/500]; % Define normalized passband frequencies [lower, upper]
ws = [50/500, 450/500]; % Define normalized stopband frequencies [lower, upper]

% --- 2. Filter Design ---
% buttord calculates the minimum filter order (n) needed to meet the specs:
% 3 dB maximum ripple in the passband, 20 dB minimum attenuation in the stopband.
[n, wc] = buttord(wp, ws, 3, 20); 

% butter calculates the zeros (z), poles (p), and gain (k) of the filter
[z, p, k] = butter(n, wp); 

% --- 3. Plotting ---
zplane(z, p); % Automatically plot the poles (x) and zeros (o) on the complex plane
title('Pole-Zero Plot of Butterworth Bandpass Filter'); % Add a title to the plot
```

---

# 9. Continuous-Time / Symbolic Fourier Transform

## [5.1] Fourier Transform and Inverse Fourier Transform of a Given Sequence

```matlab
clc; clear; close all; % Clear command window, empty workspace memory, and close figures
syms x; % Declare 'x' as a symbolic variable so MATLAB treats it like algebra, not a number

% --- Continuous Fourier Transform ---
disp('--- Symbolic Fourier Transform ---'); % Print a simple header for clarity
f = exp(-x^2) % Define the input equation (a Gaussian function) and print it
F = fourier(f) % Compute and print the Continuous Fourier Transform of the equation
f_inv = ifourier(F) % Compute and print the Inverse Fourier Transform to get the original back
```

## [5.2] Fourier Transform of a Signal u(t+0.5)-u(t-0.5)

```matlab
clc; clear all; close all; % Clear command window, clear workspace variables, and close open figure windows

syms t w % Declare symbolic variables: 't' for continuous time, 'w' for angular frequency

% Define the rectangular pulse function using unit step differences: u(t + 0.5) - u(t - 0.5)
a = heaviside(t + 0.5) - heaviside(t - 0.5); 

subplot(2,1,1); % Create the top subplot in a 2-row, 1-column figure grid
ezplot(a, [-3, 3]); % Plot the symbolic time-domain pulse 'a' across the domain t = -3 to 3

b = fourier(a) % Compute the continuous-time Fourier Transform of 'a' and display the result

subplot(2,1,2); % Create the bottom subplot in the 2-row, 1-column figure grid
ezplot(b, [-50, 50]); % Plot the symbolic frequency-domain expression 'b' from w = -50 to 50
axis([-50, 50, -1, 2]); % Set fixed axis limits: x-range [-50, 50] and y-range [-1, 2]
```

---

# 10. DTFT — Evaluation & Properties

## [5.3] Evaluation and Plotting of DTFT of the Transfer Function

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close all figures

% Define frequency range from -2*pi to 2*pi with 512 discrete evaluation points
w = -2*pi : 8*pi/511 : 2*pi; 
% Define numerator [1 + 2*e^(-jw)] and denominator [1 - 0.2*e^(-jw)] coefficients
num = [1, 2]; 
den = [1, -0.2];
% Compute Discrete-Time Fourier Transform (DTFT) frequency response values
h = freqz(num, den, w); 
% Normalized frequency axis (divided by pi for display)
w_norm = w / pi;
% --- Figure 1: Real and Imaginary Components ---
figure(1);
subplot(2, 1, 1); plot(w_norm, real(h)); % Plot real component vs normalized frequency
title('Real part of the transfer function'); xlabel('Normalized frequency (\times\pi rad/sample)'); ylabel('Amplitude');
subplot(2, 1, 2); plot(w_norm, imag(h)); % Plot imaginary component vs normalized frequency
title('Imaginary part of the transfer function'); xlabel('Normalized frequency (\times\pi rad/sample)'); ylabel('Amplitude');
% --- Figure 2: Magnitude and Phase Spectra ---
figure(2);
subplot(2, 1, 1); plot(w_norm, abs(h)); % Plot magnitude spectrum |H(e^jw)|
title('Magnitude spectrum of the transfer function'); xlabel('Normalized frequency (\times\pi rad/sample)'); ylabel('Magnitude');
subplot(2, 1, 2); plot(w_norm, angle(h)); % Plot phase angle spectrum in radians
title('Phase of the transfer function'); xlabel('Normalized frequency (\times\pi rad/sample)'); ylabel('Phase (radians)');
```

## [5.4] Time Shifting Property of DTFT

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Sequences and Frequency Range ---
w = -pi : 2*pi/255 : pi; % Define normalized angular frequency vector from -pi to pi
x1 = 1:15; % Define the original time-domain sequence (a simple ramp from 1 to 15)
d = 10; % Define a time delay (shift) of 10 samples
x2 = [zeros(1, d), x1]; % Create the delayed sequence by padding 10 zeros at the start

% --- 2. Compute DTFTs ---
h1 = freqz(x1, 1, w); % Compute the DTFT of the original sequence
h2 = freqz(x2, 1, w); % Compute the DTFT of the time-shifted sequence

% --- 3. Plot Magnitude Spectra ---
figure(1); % Open the first figure window for Magnitude
subplot(2, 1, 1); plot(w/pi, abs(h1)); title('Magnitude: Original Sequence'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, abs(h2)); title('Magnitude: Shifted Sequence'); xlabel('\omega / \pi');

% --- 4. Plot Phase Spectra ---
figure(2); % Open the second figure window for Phase
subplot(2, 1, 1); plot(w/pi, angle(h1)); title('Phase: Original Sequence'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, angle(h2)); title('Phase: Shifted Sequence'); xlabel('\omega / \pi');
```

## [5.5] Frequency Shifting Property of DTFT

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Sequences and Frequencies ---
w = -pi : 2*pi/255 : pi; % Define normalized angular frequency vector
w0 = 0.2 * pi; % Define the specific frequency shift amount (omega_0)

x1 = [1, 3, 5, 7, 5, 11, 13, 17, 18, 21, 12]; % Define the original time-domain sequence
n = 0 : length(x1)-1; % Create a time index vector matching the length of x1

% --- 2. Frequency Shifting (Modulation) ---
% Multiply the original signal by a complex exponential to shift its frequency
x2 = exp(1j * w0 * n) .* x1; 

% --- 3. Compute DTFTs ---
h1 = freqz(x1, 1, w); % Compute the DTFT of the original sequence
h2 = freqz(x2, 1, w); % Compute the DTFT of the frequency-shifted sequence

% --- 4. Plot Magnitude Spectra ---
figure(1); 
subplot(2, 1, 1); plot(w/pi, abs(h1)); title('Magnitude: Original'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, abs(h2)); title('Magnitude: Frequency Shifted'); xlabel('\omega / \pi');

% --- 5. Plot Phase Spectra ---
figure(2); 
subplot(2, 1, 1); plot(w/pi, angle(h1)); title('Phase: Original'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, angle(h2)); title('Phase: Frequency Shifted'); xlabel('\omega / \pi');
```

## [5.6] Time Convolution Property of DTFT

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures
% --- 1. Define Sequences and Frequency Range ---
w = -2*pi : 2*pi/255 : 2*pi; % Define normalized angular frequency vector
x1 = [1, 3, 5, 7, 5, 11, 13, 17, 18, 21, 12]; % Define first time-domain sequence
x2 = [1, -2, 3, -2, 1]; % Define second time-domain sequence
% --- 2. Time-Domain Convolution ---
y = conv(x1, x2); % Convolve the two sequences directly in the time domain

% --- 3. Frequency-Domain Multiplication ---
h1 = freqz(x1, 1, w); % Compute DTFT of first sequence
h2 = freqz(x2, 1, w); % Compute DTFT of second sequence
h_product = h1 .* h2; % Multiply the two DTFTs together element-by-element

% --- 4. DTFT of the Convolved Sequence ---
h_conv = freqz(y, 1, w); % Compute the DTFT of the time-domain convolved result

% --- 5. Plot and Compare Magnitude Spectra ---
figure(1); 
subplot(2, 1, 1); plot(w/pi, abs(h_product)); title('Magnitude: Product of DTFTs'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, abs(h_conv)); title('Magnitude: DTFT of Convolved Sequence'); xlabel('\omega / \pi');
% --- 6. Plot and Compare Phase Spectra ---
figure(2);
subplot(2, 1, 1); plot(w/pi, angle(h_product)); title('Phase: Product of DTFTs'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, angle(h_conv)); title('Phase: DTFT of Convolved Sequence'); xlabel('\omega / \pi');
```

## [5.7] Time Reversal Property of DTFT

```matlab
% --- 1. Define Sequences and Frequency Range ---
w = -2*pi : 2*pi/255 : 2*pi; % Define normalized angular frequency vector
x1 = [1, 2, 3, 4, 5, 6]; % Define the original time-domain sequence
L = length(x1) - 1; % Calculate the index of the last element (for shifting later)
% --- 2. Compute DTFT of Original Sequence ---
h1 = freqz(x1, 1, w); 
% --- 3. Time Reversal and DTFT ---
% fliplr() reverses the array from [1 2 3...] to [...3 2 1]
x_reversed = fliplr(x1); 
h2 = freqz(x_reversed, 1, w); % Compute DTFT of the flipped sequence
% The flipped sequence in MATLAB starts at n=0, but true time reversal 
% folds around the y-axis (n < 0). We must shift it back by L to match theory.
h_reversed_true = exp(1j * w * L) .* h2; 
% --- 4. Plot Magnitude Spectra ---
figure(1);
subplot(2, 1, 1); plot(w/pi, abs(h1)); title('Magnitude: Original'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, abs(h_reversed_true)); title('Magnitude: Time-Reversed'); xlabel('\omega / \pi');
% --- 5. Plot Phase Spectra ---
figure(2);
subplot(2, 1, 1); plot(w/pi, angle(h1)); title('Phase: Original'); xlabel('\omega / \pi');
subplot(2, 1, 2); plot(w/pi, angle(h_reversed_true)); title('Phase: Time-Reversed'); xlabel('\omega / \pi');
```

## [5.8] Frequency Response of the Given System

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close figures
% --- 1. Define the System Coefficients ---
num = [1, -1, 3]; % Numerator coefficients of the transfer function
den = [1, 1/3, 1/6]; % Denominator coefficients of the transfer function

% --- 2. Compute Frequency Response ---
% By not providing a specific frequency array, MATLAB automatically 
% calculates 512 points between 0 and pi (the Nyquist limit).
[h, om] = freqz(num, den); 

% --- 3. Plot Magnitude Response (in Decibels) ---
subplot(2, 1, 1); 
plot(om/pi, 20*log10(abs(h))); % Convert absolute magnitude to decibels (dB)
title('Magnitude Response'); xlabel('Normalized Frequency (\times \pi)'); ylabel('Gain (dB)');

% --- 4. Plot Phase Response ---
subplot(2, 1, 2); 
plot(om/pi, angle(h)); % Extract and plot the phase angle
title('Phase Response'); xlabel('Normalized Frequency (\times \pi)'); ylabel('Phase (radians)');
```

## [5.9] Periodicity Property of DTFT

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define Sequence and Frequency Range ---
n = 1:10; % Define time index vector (n = 1 to 10)
x = (0.9 * exp(1j * pi / 3)).^n; % Define a complex exponential time-domain signal

k = -200:200; % Define frequency index steps
w = (pi / 100) * k; % Create frequency vector w spanning from -2*pi to +2*pi

% --- 2. Manual DTFT Computation (Matrix Multiplication) ---
% Instead of using a 'for' loop or freqz, this elegantly uses matrix multiplication 
% to perform the DTFT summation mathematically: sum( x[n] * exp(-j*w*n) )
X_dtft = x * exp(-1j * pi / 100).^(n' * k); 

% --- 3. Plotting ---
figure(1);
subplot(2, 1, 1); 
plot(w/pi, abs(X_dtft)); % Plot Magnitude
title('Magnitude Spectrum (Periodicity)'); xlabel('Normalized frequency (\times \pi)'); ylabel('Amplitude');

subplot(2, 1, 2); 
plot(w/pi, angle(X_dtft)); % Plot Phase
title('Phase Spectrum (Periodicity)'); xlabel('Normalized frequency (\times \pi)'); ylabel('Phase (radians)');
```

---

# 11. Fourier Series & Gibbs Phenomenon

## [6.1] Fourier Series Representation of a Train of Pulses

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

syms t; % Declare 't' as a symbolic variable to perform calculus

% --- 1. Define the Periodic Signal ---
T0 = 1; % Define the fundamental period of the signal (T = 1 second)

% Create a pulse using step functions. It turns ON at t=0, OFF at t=0.25, and ON at t=0.75.
m = heaviside(t) - heaviside(t - T0/4) + heaviside(t - 3*T0/4); 
x = 2 * m; % Scale the amplitude to 2

% Plot the input signal over one full period [0, T0]
figure(1); fplot(x, [0, T0], 'LineWidth', 2); grid on;
title('Time Domain: Input Pulse Sequence'); xlabel('Time (t)'); ylabel('Amplitude');

% --- 2. Compute Fourier Series Coefficients ---
N = 20; % Define the number of harmonics to calculate
X = zeros(1, N); % Pre-allocate an array to store the complex coefficients
w = zeros(1, N); % Pre-allocate an array to store the harmonic frequencies

for k = 1:N
    % Calculate the fundamental frequency for the current harmonic 'k'
    w(k) = (k - 1) * 2 * pi / T0; 

    % Perform symbolic integration to find the coefficient X_k
    % Formula: X_k = (1/T0) * integral( x(t) * exp(-j*k*w0*t) dt ) from 0 to T0
    X_sym = (1/T0) * int(x * exp(-1j * w(k) * t), t, 0, T0); 

    % Convert the symbolic calculus result into a standard double-precision number
    X(k) = double(X_sym); 
end

% --- 3. Plot Magnitude and Phase Spectra ---
figure(2);
subplot(2, 1, 1); stem(w, abs(X), 'filled'); % Plot the magnitude of the coefficients
title('Magnitude of Fourier Series'); xlabel('Frequency \omega (rad/s)'); ylabel('|X_k|');

subplot(2, 1, 2); stem(w, angle(X), 'filled'); % Plot the phase angle of the coefficients
title('Phase of Fourier Series'); xlabel('Frequency \omega (rad/s)'); ylabel('\angle X_k (rad)');
```

## [6.2] Fourier Series Representation of a Full Wave Rectified Wave

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures
clc; clear all; close all; % Clear command window, workspace variables, and close open figure windows

syms t % Declare symbolic variable for continuous time

% Signal parameters and definition of full-wave rectified cosine wave
T0 = 1;                                         % Fundamental period of the rectified wave
m = heaviside(t) - heaviside(t - T0);           % Rectangular window defining the period [0, T0]
x = abs(cos(pi * t)) * m;                       % Full-wave rectified cosine signal over one period

% Compute 20 Fourier Series harmonic coefficients
N = 20; 
for k = 1:N
    X1(k) = int(x * exp(-1j * 2 * pi * (k-1) * t / T0), t, 0, T0) / T0; % Symbolic integration
    X(k) = double(subs(X1(k)));                                         % Numerical coefficient value
    w(k) = (k - 1) * 2 * pi / T0;                                       % Harmonic frequency values
end

% --- Figure 1: Input Waveform ---
figure(1);
ezplot(x, [0, T0]); grid on;
title('input sequence');

% --- Figure 2: Magnitude and Phase Fourier Series Spectra ---
figure(2);
subplot(2, 1, 1); stem(w, abs(X));
title('magnitude of fourier series'); xlabel('Frequency'); ylabel('Magnitude');

subplot(2, 1, 2); stem(w, angle(X));
title('phase of fourier series'); xlabel('Frequency'); ylabel('Phase');
```

## [6.9] Gibbs Phenomenon

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% --- Figure 1: First 4 Individual Harmonic Sums ---
t1 = 0:0.1:10;
y1 = sin(t1);
y2 = y1 + sin(3*t1)/3;
y3 = y2 + sin(5*t1)/5;
y4 = y3 + sin(7*t1)/7;

figure(1);
subplot(2, 2, 1); plot(t1, y1); title('1st Harmonic');
subplot(2, 2, 2); plot(t1, y2); title('Up to 3rd Harmonic');
subplot(2, 2, 3); plot(t1, y3); title('Up to 5th Harmonic');
subplot(2, 2, 4); plot(t1, y4); title('Up to 7th Harmonic');

% --- Figure 2: Gibbs Phenomenon (Square Wave Convergence) ---
t2 = 0:0.02:pi;
x = zeros(size(t2));

for k = 1:2:19
    x = x + sin(k*t2)/k;         % Add odd harmonics: sin(k*t)/k
    y5((k+1)/2, :) = x;         % Store each approximation
end

figure(2);
plot(t2, y5(1:2:9, :)'); grid on;
title('The building of a square wave: Gibbs'' effect');
xlabel('Time (t)'); ylabel('Amplitude');
```

---

# 12. DFT/FFT Fundamentals & Properties

## [6.3] Direct Computation of Discrete Fourier Transform (Matrix Formulation)

```matlab
clc; clear; close all; % Clear command window, workspace, and close figures

% --- 1. Define Sequence ---
x = [1, -1, 2, -2]; % Define the input time-domain sequence
N = length(x); % Get the length of the sequence (N = 4)

disp('--- Forward DFT ---');
% --- 2. Direct DFT (Matrix Formulation) ---
% dftmtx(N) generates the N x N Twiddle Factor matrix.
% We multiply the input vector 'x' by this matrix to get the frequency domain.
Y = x * dftmtx(N) 

disp('--- Inverse DFT ---');
% --- 3. Direct Inverse DFT (Matrix Formulation) ---
% To reverse the process, multiply the frequency vector 'Y' by the 
% complex conjugate of the Twiddle matrix, and divide by N.
x_inv = (Y * conj(dftmtx(N))) / N 
```

## [7.1] Calculation of the DFT of a Given Sequence Using FFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close figures

% Define input sequence
x = [2 2 2 2 1 1 1 1];

% Compute Fast Fourier Transform (FFT)
y = fft(x);
disp('the fft of the input sequence')
disp(y)

% Magnitude and Phase plots
y1 = abs(y);
subplot(2, 1, 1); stem(y1); title('magnitude response');

y2 = angle(y);
subplot(2, 1, 2); stem(y2); title('phase response');

% Compute Inverse Fast Fourier Transform (IFFT)
y3 = ifft(y);
disp('the inverse fft is')
disp(y3)
```

## [6.6] Relation Between DFTs of the Periodic Even and Odd Parts of a Real Sequence

```matlab
clc; clear; close all; % Clear command window, workspace memory, and close all figures

% --- 1. Define the Sequence ---
% Create a 256-point sequence by padding the initial values with zeros
x_val = [1, 2, 4, 2, 6, 32, 6, 4, 2];
x = [x_val, zeros(1, 256 - length(x_val))]; 

% --- 2. Calculate the Periodic Even Component ---
% Circular time reversal: The first element stays at index 1, the rest are flipped
x_rev = [x(1), x(256:-1:2)]; 
x_even = 0.5 * (x + x_rev); % Formula for the even part of a signal

% --- 3. Compute DFTs ---
X = fft(x); % Compute the DFT of the original sequence
X_even = fft(x_even); % Compute the DFT of the even sequence

% --- 4. Plot Original Sequence DFT ---
k = 0:255; % Frequency index vector
figure(1);
subplot(2, 1, 1); plot(k/128, real(X)); title('Real Part of Original DFT');
subplot(2, 1, 2); plot(k/128, imag(X)); title('Imaginary Part of Original DFT');

% --- 5. Plot Even Sequence DFT ---
figure(2);
subplot(2, 1, 1); plot(k/128, real(X_even)); title('Real Part of Even Sequence DFT');
subplot(2, 1, 2); plot(k/128, imag(X_even)); title('Imaginary Part of Even Sequence DFT');
```

## [6.7] Parseval's Relation of DFT

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Define triangular test sequence: rises 1 to 128, then falls 128 to 1
x = [(1:128), (128:-1:1)];

% Compute Fast Fourier Transform (DFT)
y = fft(x);

% Time-domain energy calculation: sum(|x[n]|^2)
y1 = sum(x .* x);
N = length(x);

% Frequency-domain energy calculation using Parseval's theorem: (1/N) * sum(|X[k]|^2)
y3 = abs(y);
y4 = sum(y3 .* y3) / N;

% Difference/error between both domains (should be near 0)
y5 = y1 - y4;

% Display energy values and error
disp('energy in time domain')
disp(y1)

disp('energy in Frequency domain')
disp(y4)

disp('error')
disp(y5)
```

## [6.8] Circular Time Shifting Property of DFT

```matlab
clc; clear all; close all; % Clear workspace and figures

% Input signal and shift amount
x = [0 2 4 6 8 10 12 14 16];
M = 5;                                % Shift amount
n = 0:length(x)-1;                    % Sample indices

% Circular shift using circshift and compute FFTs
y = circshift(x, -M);                 % Direct circular shift
xf = fft(x);
yf = fft(y);

% --- Figure 1: Magnitude Spectra ---
figure(1);
subplot(2, 1, 1); stem(n, abs(xf)); title('Magnitude of DFT original sequence');
subplot(2, 1, 2); stem(n, abs(yf)); title('Magnitude of DFT Circularly shifted sequence');

% --- Figure 2: Phase Spectra ---
figure(2);
subplot(2, 1, 1); stem(n, angle(xf)); title('Phase of DFT original sequence');
subplot(2, 1, 2); stem(n, angle(yf)); title('phase of DFT Circularly shifted sequence');
```

## [7.4] Plotting of DFT of Sinusoidal Wave

```matlab
clc; clear all; close all; % Clear command window, workspace memory, and close open figure windows

% Generate dual-tone sinusoidal signal (10 Hz and 100 Hz components)
t = 0:0.01:1;
a = sin(2*pi*10*t) + sin(2*pi*100*t);

% Compute normalized FFT magnitude
b = fft(a);
c = abs(b);
e = c / length(a); % Normalized amplitude spectrum

% --- Plotting Time-Domain Signal and FFT Magnitude ---
subplot(2, 1, 1); plot(t, a);
title('input signal'); xlabel('time'); ylabel('amplitude');

subplot(2, 1, 2); plot(e);
title('fft of the input signal'); xlabel('frequency'); ylabel('amplitude');
```

---

*Note: [2.3]/[6.4]/[7.2] are essentially the same linear-convolution-via-DFT technique repeated across three chapters — same for [2.4]/[6.5]/[7.3] (circular convolution) and [6.3]/[7.1] (DFT computation). They're kept as separate full entries here (not merged) since each is its own numbered lab question you may be asked for by that exact number — but now they sit next to each other so you can see they're the same idea at a glance.*
