# CryptoBot
CryptoBot is a Java project designed to retrieve cryptocurrency values from Binance API, read these values, display them to the user as a chart, and execute buy/sell orders based on market price fluctuations. CryptoBot includes user registration and login features to enhance security and personalization. The project employs several technologies such as API integration, Java Swing, Javax, MySQL and Multithreading.

## Table of Contents
- [Features](#features)
- [Getting Started](#GettingStarted)
- [Screenshots](#Screenshots)


## Features
- Retrieve cryptocurrency values from Binance API in real time.
- Display cryptocurrency values as a chart.
- Automatically executes buy and sell orders in response to market price fluctuations.
- Multithreading for real-time data updates.
- User Registration and Login:
  - Allows users to register for an account.
  - Requires email confirmation for account activation.
  - Provides secure login functionality for registered users.
## Getting Started
To get started with CryptoBot, follow these steps:

Clone the repository to your local machine:

Copy code
"git clone https://github.com/bodyakyryliuk/CryptoBot.git"
Set up your Binance API credentials. You'll need to create an API key from your Binance account and add the key and secret to the configuration file.

Install any necessary dependencies.

Build and run the project.

# Screenshots
In this project, I didn't leverage any styles for a better view. Actually, only Java Swing provides a GUI for this application, so the focus has primarily been on functionality rather than visual aesthetics.

Authorization window provides two input fields for email and password. It has button log in to submit the form and registration to redirect to registration form.

![Login window](/cryptobot-img/login.PNG)

Registration window provides 5 input fields for name, surname, email, password and confirm password. Every field is checked with regular expressions. Also, when user clicks button "Send confirmation code" there appears one more input field for typing the code. If the code is the same as sent, user is redirected to authorization field. If user clicks button "Cancel" he is redirected to authorization window as well.

![Registration window](/cryptobot-img/register.PNG)

After successfull auhtorization main window with bitcoin chart is opened. Chart is zoomable and real-time. On the Y-axis there are prices, and on the X-axis there is time. On the right side of a chart user is able to see current price of currency, its amount and his wallet at that time. There are also fields "Buy price" and "Change" that represent data when user has bought crypto. The same is with other panels.            

![Bitcoin chart](/cryptobot-img/1.PNG)

![Ethereum chart](/cryptobot-img/2.PNG)

![TWT chart](/cryptobot-img/3.PNG)

![XRP chart](/cryptobot-img/4.PNG)

