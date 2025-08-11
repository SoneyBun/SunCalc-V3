<!-- Disclaimer -->
>[!CAUTION]
>Despite the programmed formulas being well known, SunCalc's Health feature should not influence any medical decisions at all.

<!-- Banner -->
![Health Banner](https://github.com/user-attachments/assets/f8d58016-7b14-4f25-a7e0-e57cbc6ff16f)
<div align="center">SunCalc's medical formulas</div>


---

## Overview  
The `Health` class is an interactive console-based health calculator that helps users estimate various body metrics. It supports both **Metric** and **Imperial** measurement systems and includes:  
- 📊 **BMI** (Body Mass Index)  
- 🔥 **BMR** (Basal Metabolic Rate — 3 methods)  
- 📏 **Estimated Adult Height**  

⚠ **Important Disclaimer:**  
All results are **estimates** and are **not** a substitute for professional medical advice, diagnosis, or treatment. Always consult a qualified healthcare provider before making health-related decisions.  

---

## Features  

### 📊 BMI Calculation  
Calculates body mass index and categorizes weight status:  
- **Underweight** — BMI < 18.5  
- **Normal** — 18.5 ≤ BMI < 25  
- **Overweight** — 25 ≤ BMI < 30  
- **Obese** — BMI ≥ 30  

**Formula:**  
\[
\text{BMI} = \frac{\text{weight (kg)}}{\text{height (m)}^2}
\]  

---

### 🔥 BMR Calculation  
Estimates daily calorie requirements at rest using three standard formulas:  

1. **Mifflin–St Jeor**  
   \[
   10w + 6.25h - 5a + s
   \]  
   where \( s = 5 \) for males, \( -161 \) for females.  

2. **Harris–Benedict**  
   Gender-specific coefficients applied to weight, height, and age.  

3. **Katch–McArdle**  
   \[
   370 + (21.6 \times (1 - \text{bf}) \times w)
   \]  

⚠ **Disclaimer:**  
BMR values can vary based on individual health conditions, activity levels, and body composition. These are **approximations** and should not be used as the sole basis for diet or exercise planning.  

---

### 📏 Estimated Adult Height  
Predicts future adult height based on biological parents’ heights:  
- **Male formula adjustment:** +13 cm (Metric) or +5 in (Imperial)  
- **Female formula adjustment:** −13 cm (Metric) or −5 in (Imperial)  

⚠ **Disclaimer:**  
Height predictions are based on population averages and **may differ significantly** from actual adult height due to genetics, health, and environmental factors.  

---

## ⚙ Class Structure  

| Method | Description |
|--------|-------------|
| `start()` | Main loop; presents the menu and routes to selected health tools. |
| `getMeasurementSystem()` | Asks for Metric or Imperial input. |
| `getDouble(prompt)` | Gets and validates decimal input. |
| `getInt(prompt)` | Gets and validates integer input. |
| `getBodyFatPercent()` | Gets and validates body fat percentage (0–100%). |
| `askGender()` | Requests biological gender for calculation purposes. |
| `calculateBMI()` | Calculates BMI and weight classification. |
| `calculateBMR()` | Calculates BMR using three methods. |
| `estimateHeight()` | Estimates adult height from parents’ heights. |
