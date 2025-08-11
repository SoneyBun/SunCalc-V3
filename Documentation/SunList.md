<!-- Banner -->
![SunList Banner](https://github.com/user-attachments/assets/b20e03a2-ab36-40fb-9312-340e087d671b)
<div align="center">Lists for bulk data</div>

<!-- Documentation -->
&nbsp;<div align="center">
  <a href="https://en.wikipedia.org/wiki/Point_(geometry)" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Point-SunCalc?style=for-the-badge&logo=wikipedia&logoColor=%23000000&color=%23ffffff"></a>
</div>

---

## Overview
The **SunList** module manages collections of points or numbers, with built-in tools for saving, retrieving, and performing statistical analysis.  
It can operate in two modes:
- **Numbers Mode**: Only X-values are relevant (Y-values fixed at 0).
- **Points Mode**: Full XY coordinates are stored.

In addition to storage, SunList includes methods for calculating statistical measures, correlation, and performing regression analysis.

⚠ **Important Disclaimer:**  
SunList performs mathematical calculations using stored values. Accuracy is dependent on the input data provided.

---

## 🗂 Core Features

### 📦 Data Storage
- Maintains a list of `Point` objects.
- Supports both numerical-only and XY coordinate modes.
- Provides named storage using an internal static registry for saving and retrieving lists by name.

### 💾 List Management
- **Save**: Store the list under a custom name.
- **Retrieve**: Access a saved list by name.
- **Remove**: Delete a saved list.
- **List Names**: Retrieve all saved list identifiers.

### ➕ Adding & Removing Data
- **Add Point**: Append a new point to the list.
- **Remove Point**: Delete a point by index.
- **Get Point**: Access a point by index.
- **Size**: Retrieve the total number of points.

---

## 📈 Statistical Calculations

### 📊 Basic Measures
- **Mean X** and **Mean Y**: Calculates the average of X and Y values.
- **Standard Deviation X** and **Standard Deviation Y**: Measures spread of values.

### 🔗 Correlation
- **Correlation Coefficient (r)**: Determines linear relationship between X and Y values.

---

## 📉 Regression Analysis

### 📏 Linear Regression
- Returns `[slope, intercept]` for the best-fit line using least squares.
- Prediction formula:  
