<!-- Banner -->
![Math Banner](https://github.com/user-attachments/assets/6c01c1a9-31ee-427b-9f27-cc9afbb9292c)
<div align="center">Math available within SunCalc</div>

<!-- Documentation -->
&nbsp;<div align="center">
  <a href="https://en.wikipedia.org/wiki/Point_(geometry)" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Point-SunCalc?style=for-the-badge&logo=wikipedia&logoColor=%23000000&color=%23ffffff"></a>
  <a href="SunList.md" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/SunList-SunCalc?style=for-the-badge&logo=github&logoColor=%23ffffff&color=%23fa6900"></a>
</div>

---

## 📌 Overview
The Math Module provides a wide range of arithmetic, comparison, trigonometric, factorial, and logarithmic operations, allowing flexible calculation handling through a unified interface.

---

## ⚠️ Disclaimers
- This module performs calculations exactly as requested by the user. Ensure all inputs are valid before execution.
- Incorrect or invalid values will return a descriptive error code rather than performing the calculation.
- Report any issues at: [https://bit.ly/SunCode](https://bit.ly/SunCode)

---

## 🔹 Core Class: `Operation`
Handles execution of a single mathematical operation based on a specified operator and operands.

### **Key Responsibilities**
- Parse and store operands and the operation type.
- Execute supported calculations with error handling.
- Format output with clear descriptions.

---

## 🧾 Supported Operation Categories

### ➕ Basic Arithmetic
- Addition (`+`, `add`, `plus`)
- Subtraction (`-`, `subtract`, `minus`)
- Multiplication (`*`, `multiply`, `times`)
- Division (`/`, `divide`, `over`) — with zero-check.
- Modulus (`%`, `mod`, `modulus`)
- Absolute Value (`||`, `abval`, `absval`)

---

### 🔍 Comparisons
- Greater Than (`>`)
- Less Than (`<`)
- Greater Than or Equal To (`>=`)
- Less Than or Equal To (`<=`)
- Equality (`=`, `==`)
- Inequality (`!=`)
- Maximum (`max`, `maximum`)
- Minimum (`min`, `minimum`)

---

### 🔢 Number Manipulation
- Rounding (`rnd`, `round`)
- Truncating (`trunc`, `truncate`)

---

### 📐 Trigonometric Functions
- Sine (`sin`, `sine`)
- Cosine (`cos`, `cosine`)
- Tangent (`tan`, `tangent`)

Angular mode is configurable through global settings.

---

### ❗ Factorial
- Factorial (`!`, `factorial`) — only for non-negative integers.

---

### 📈 Exponents & Logarithms
- Power (`^`, `exp`, `exponent`, `pwr`, `pow`, `power`)
- Root (`rt`, `root`)
- Logarithm (`log`, `logarithm`) — supports base 10, `e`, or custom bases.
- Natural Logarithm (`ln`)

---

### 📏 Inverse Trigonometry
- Arcsine (`asin`, `arcsin`, `arcsine`)
- Arccosine (`acos`, `arccos`, `arccosine`)
- Arctangent (`atan`, `arctan`, `arctangent`)

---

## 🚨 Error Handling
The following errors may be returned instead of results:
- `Error 61` — Unknown Operator
- `Error 62` — Divide By Zero
- `Error 63` — Logarithm With A Negative Base
- `Error 64` — Factorial input must be a non-negative integer

---

## 🛠️ Helper Functions
- **Angle Conversion** — Convert between degrees and radians when angular mode is disabled.
- **Factorial Calculation** — Efficiently calculates factorial using `BigInteger` for large values.
