<!-- Banner -->
![Games Banner](https://github.com/user-attachments/assets/67dc9286-6800-4b93-aca2-c7f541f4ccbb)
<div align="center">Games supported by SunCalc</div>

&nbsp;<div align="center">
  <a href="Currency.md" target="_blank"><img alt="Static Badge" src="https://img.shields.io/badge/Currency-SunCalc?style=for-the-badge&logo=github&logoColor=%23ffffff&color=%23fa6900"></a>
</div>

---

## 📌 Overview

The **Games Module** provides a collection of interactive betting-style mini-games, all accessed through the **SunGames** hub. Players start with a fixed balance of virtual currency, managed through the `Currency` class, and place bets on various games. The `Strike` system tracks invalid betting attempts to enforce fair play.

> ⚠️ **Disclaimer:** All games are for entertainment purposes only. The virtual currency used has **no real-world value** and should not be confused with gambling involving actual money.

---

## 🛠 Hub & Management Components

### 🗂 SunGames (Hub)
- Central menu for selecting and launching all five games.
- Tracks the player's balance and chosen currency type.
- Validates bet amounts before each game round begins.
- Automatically resets balance to **100** if the player reaches zero.

### 💱 Currency
- Stores the currency name, symbol, and plural suffix.
- Supports a wide range of world currencies and their Unicode symbols.
- Normalises input names for consistent display.

### 🚫 Strike
- Records the number of invalid bet attempts per session.
- Prevents bets that are zero, negative, or exceed the current balance.
- Issues a numbered warning message for each strike.

---

## 🎲 Dice Game

**Objective:** Guess the outcome of a six-sided dice roll.

**Gameplay:**
- Player guesses a number from **1 to 6**.
- Correct guess → wins **3× the bet**.
- Incorrect guess → loses the bet.

> ⚠️ Outcomes are determined by random number generation and cannot be predicted or influenced.

---

## 🪙 Coin Flip

**Objective:** Predict the result of a coin flip — Heads or Tails.

**Gameplay:**
- Player enters **H** (Heads) or **T** (Tails).
- Correct guess → wins **1× the bet** (doubles money bet).
- Incorrect guess → loses the bet.

> ⚠️ Results are purely random.

---

## 🎯 Chance Game

**Objective:** Test your luck with a fixed-probability payout system.

| Outcome | Probability | Result |
|---------|-------------|--------|
| Win | 3 / 7 | **+3× bet** |
| Lose | 3 / 7 | **−bet** |
| Push | 1 / 7 | No change |

> ⚠️ Probability distribution is fixed. No strategy alters the odds.

---

## 🃏 Blackjack

**Objective:** Beat the dealer by reaching a hand value closer to 21 without going over.

**Gameplay:**
- Player and dealer are each dealt two cards from a freshly shuffled 52-card deck. One dealer card remains hidden until the player's turn ends.
- Player acts first, then the dealer plays automatically.
- Dealer must hit on 16 or below and stand on 17 or above.

### Actions

| Action | Key | Description |
|--------|-----|-------------|
| Hit | `H` | Draw another card. |
| Stand | `S` | End your turn with your current hand. |
| Double Down | `D` | Double the bet, receive exactly one more card, then stand automatically. Only available on the opening two cards when balance allows. |

### Payouts

| Result | Payout |
|--------|--------|
| Win | +1× bet |
| Natural Blackjack (first two cards = 21) | +1.5× bet (rounded down) |
| Push (tie) | No change |
| Lose / Bust | −bet |

### Card Values

| Card | Value |
|------|-------|
| 2 – 10 | Face value |
| J, Q, K | 10 |
| Ace | 11, reduced to 1 if the hand would exceed 21 |

> ⚠️ A standard 52-card deck is shuffled fresh at the start of every hand.
