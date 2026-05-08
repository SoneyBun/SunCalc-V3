<!-- Banner -->
![Math Banner](https://github.com/user-attachments/assets/6c01c1a9-31ee-427b-9f27-cc9afbb9292c)
<div align="center">Math available within SunCalc</div>

&nbsp;<div align="center">
  <a href="https://en.wikipedia.org/wiki/Point_(geometry)" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Point-SunCalc?style=for-the-badge&logo=wikipedia&logoColor=%23000000&color=%23ffffff"></a>
  <a href="SunList.md" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/SunList-SunCalc?style=for-the-badge&logo=github&logoColor=%23ffffff&color=%23fa6900"></a>
</div>

---

## 📌 Overview

The **Math Module** provides arithmetic, comparison, trigonometric, hyperbolic, combinatoric, logarithmic, and graphing operations through a unified interface. Calculations are entered in the configured notation (Prefix, Infix, or Postfix) and executed by the `Operation` class. Results are formatted and printed directly to the console.

---

## ⚠️ Disclaimers

- This module performs calculations exactly as requested. Ensure all inputs are valid before execution.
- Incorrect or out-of-domain values return a descriptive error code rather than performing the calculation.

---

## 🔹 Core Class: `Operation`

Handles execution of a single mathematical operation based on a specified operator and operands.

### Key Responsibilities
- Parse and store operands and the operation type.
- Execute supported calculations with full error handling.
- Format output with clear operator–operand–result descriptions.

---

## 🧾 Supported Operation Categories

### ➕ Basic Arithmetic
| Operators | Description |
|-----------|-------------|
| `+` `add` `plus` | Addition |
| `-` `subtract` `minus` | Subtraction |
| `*` `multiply` `times` | Multiplication |
| `/` `divide` `over` | Division — protected against zero |
| `%` `mod` `modulus` | Modulus (remainder) |
| `\|\|` `abval` `absval` `abs` | Absolute value |

---

### 🔍 Comparisons
| Operators | Description |
|-----------|-------------|
| `>` | Greater than |
| `<` | Less than |
| `>=` | Greater than or equal to |
| `<=` | Less than or equal to |
| `=` `==` | Equality |
| `!=` | Inequality |
| `max` `maximum` | Larger of two values |
| `min` `minimum` | Smaller of two values |

---

### 🔢 Number Manipulation
| Operators | Description |
|-----------|-------------|
| `rnd` `round` | Round to nearest integer |
| `trunc` `truncate` | Drop decimal portion |
| `floor` | Round down to nearest integer |
| `ceil` `ceiling` | Round up to nearest integer |
| `sgn` `sign` | Sign of a number (−1, 0, or 1) |
| `sq` `square` | Square (x²) |
| `cube` | Cube (x³) |
| `sqrt` `squareroot` | Square root — domain: x ≥ 0 |
| `cbrt` `cuberoot` | Cube root |
| `inv` `reciprocal` | Reciprocal (1 / x) — protected against zero |
| `avg` `average` `mean` | Arithmetic mean of two values |
| `dist` | Absolute distance between two values |
| `hyp` `hypot` | Hypotenuse — √(a² + b²) |
| `deg` `todegrees` | Convert radians → degrees |
| `rad` `toradians` | Convert degrees → radians |

---

### 📐 Trigonometric Functions
| Operators | Description |
|-----------|-------------|
| `sin` `sine` | Sine |
| `cos` `cosine` | Cosine |
| `tan` `tangent` | Tangent |

> Angular mode is configurable under **Configure → Angular Mode** (Degrees / Radians).

---

### 🔄 Inverse Trigonometry
| Operators | Description |
|-----------|-------------|
| `asin` `arcsin` `arcsine` | Arcsine |
| `acos` `arccos` `arccosine` | Arccosine |
| `atan` `arctan` `arctangent` | Arctangent |
| `atan2` | Two-argument arctangent — atan2(y, x) |

> Output is returned in the currently configured angular mode.

---

### 〰️ Hyperbolic Functions
| Operators | Description |
|-----------|-------------|
| `sinh` | Hyperbolic sine |
| `cosh` | Hyperbolic cosine |
| `tanh` | Hyperbolic tangent |

---

### 🔃 Inverse Hyperbolic Functions
| Operators | Description |
|-----------|-------------|
| `asinh` `arcsinh` | Inverse hyperbolic sine |
| `acosh` `arccosh` | Inverse hyperbolic cosine — domain: x ≥ 1 |
| `atanh` `arctanh` | Inverse hyperbolic tangent — domain: −1 < x < 1 |

---

### ❗ Factorial
| Operators | Description |
|-----------|-------------|
| `!` `factorial` | Factorial — non-negative integers only; uses `BigInteger` for large values |

---

### 📈 Exponents & Logarithms
| Operators | Description |
|-----------|-------------|
| `^` `exp` `exponent` `pwr` `pow` `power` | Exponentiation (aᵇ) |
| `rt` `root` | Nth root — a^(1/b) |
| `log` `logarithm` | Logarithm — base 10 by default; pass a second operand for a custom base |
| `ln` | Natural logarithm (base e) |
| `log2` | Logarithm base 2 |
| `log10` | Logarithm base 10 (explicit) |

---

### 🔣 Combinatorics
| Operators | Description |
|-----------|-------------|
| `perm` `npr` `permutation` | Permutations — P(n, r) = n! / (n−r)! |
| `comb` `ncr` `combination` `choose` | Combinations — C(n, r) = n! / (r! · (n−r)!) |
| `gcd` | Greatest common divisor |
| `lcm` | Least common multiple |

> All combinatoric operators require non-negative integer inputs. Results use `BigInteger` for large values.

---

## 🚨 Error Handling

| Code | Message |
|------|---------|
| `Error 61` | Unknown Operator |
| `Error 62` | Divide By Zero |
| `Error 63` | Logarithm With A Negative Base |
| `Error 64` | Factorial input must be a non-negative integer |
| `Error 65` | Input is outside the valid domain for this operation |
| `Error 66` | Operation requires non-negative integer input(s) |

---

## 🛠️ Helper Functions

- **Angle Conversion** — `toRadians` / `toDegrees` convert values when angular mode requires it.
- **Factorial Calculation** — `factorialOf(int n)` uses `BigInteger` multiplication for arbitrarily large results.
- **GCD** — Recursive Euclidean algorithm used internally by both `gcd` and `lcm`.
