# Final Project Proposal
The system that our group wants to consider for the final project is: CLI Stock Trading System
## CLI-Stock-Trading-System
Chosen Programming Language for Development: Java
### Project Overview
This system allows users to stock code(s) and the number of shares. The system will then determine the purchasing and selling price (based on the investment term) and display the total amount of gain or loss.
The system does not rely on any external APIs or real-time stock data. Instead we use java.lang.Math.random() to simulate stock prices at the time of buying and selling, making the application  easy to implement and test. 
### Key Features
● Enter Trade Details: quantity of share and some of the stock codes

● Calculate Profit or Loss: Determine if the trade made a profit or a loss

● Display Result: Show the amount of gain or loss for each trade

● Support Multiple Trades: Allow multiple entries and display a total summary

● Risk Level Indicator: If the investment amount is high, display a warning to remind the user to proceed with caution

● Sort Stock Options: Display different stock codes in alphabetical order before placing a trade
### Classes that might be considered for Class Diagram
● Trade:

  Attributes: buyPrice, sellPrice, share, investmentTerm
  
  Methods: calculateProfitOrLoss()
  
● ProfitLossCalculator:

  Responsible for computing total profit/loss across multiple trades
  
● Main (or App): 

  Handles user input and output
  
**Team Members**

Muyao (Jerry) Kong mk9014@nyu.edu

Yifei (Zora) Zhao yz9704@nyu.edu

Gordon Zou kz2538@nyu.edu


