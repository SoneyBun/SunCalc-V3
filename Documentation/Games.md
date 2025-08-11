<!-- Banner -->
![Games Banner](https://github.com/user-attachments/assets/67dc9286-6800-4b93-aca2-c7f541f4ccbb)
<div align="center">Games supported by SunCalc</div>

<!-- Documentation -->
&nbsp;<div align="center">
  <a href="Currency.md" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Currency-SunCalc?style=for-the-badge&logo=github&logoColor=%23ffffff&color=%23fa6900"></a>
</div>


---

## Overview
The **Games Module** provides a collection of interactive betting-style mini-games, all accessed through the **SunGames** hub.  
Players start with a fixed amount of virtual currency, managed through the `Currency` class, and can place bets on various games.  
The `Strike` system ensures fair play by tracking invalid betting attempts.

⚠ **Important Disclaimer:**  
All games are for entertainment purposes only. The virtual currency used in these games has **no real-world value** and should not be confused with gambling involving actual money.

---

## 🛠 Hub & Management Components

### 🗂 SunGames (Hub)
- Acts as the **central menu** for selecting and launching games.
- Tracks the player's balance and selected currency.
- Handles bet validation before each game begins.
- Automatically resets balance if the player loses all funds.

### 💱 Currency (Virtual Money)
- Stores the currency name, symbol, and plural form.
- Supports multiple world currencies and symbols.
- Normalizes currency names for consistent formatting.

### 🚫 Strike (Anti-Cheat)
- Records the number of invalid betting attempts.
- Prevents bets that are zero, negative, or exceed the current balance.
- Issues warning messages with strike counts.

---

## 🎲 Dice Game
**Objective:**  
Guess the outcome of a six-sided dice roll.

**Gameplay:**
- Player guesses a number from **1 to 6**.
- If the guess matches the dice roll, the player wins **3× the bet**.
- Any other result results in the loss of the bet.

⚠ **Disclaimer:**  
Outcomes are determined by random number generation. There is no way to influence or predict results.

---

## 🪙 Coin Flip Game
**Objective:**  
Predict the result of a coin flip — **Heads** or **Tails**.

**Gameplay:**
- Player chooses **Heads (H)** or **Tails (T)**.
- If the guess matches the coin flip, the player wins an amount equal to their bet.
- If the guess is wrong, the bet is lost.

⚠ **Disclaimer:**  
Results are purely random and cannot be manipulated.

---

## 🎯 Chance Game
**Objective:**  
Test your luck with a simple high-risk, high-reward system.

**Gameplay:**
- 3/7 chance to win **3× the bet**.
- 3/7 chance to lose the bet entirely.
- 1/7 chance to **push** (no gain or loss).

⚠ **Disclaimer:**  
Probability distribution is fixed and based on random number generation. No strategies alter the odds.
