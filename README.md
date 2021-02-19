# TicTacToe
This project is to replicate playing tictactoe, but on mobile. In this, you will be able to enter a username, carry it over to the next screen, play against a friend and see a win, lose or draw message

# Installation 
To view/ run this project, you will need Android Studio version 4.1.2.

# Resources used:
https://www.youtube.com/watch?v=apDL78MFR3o&ab_channel=CodinginFlow 
This is a video series which helped me develop the base of the code

# Known Bugs:
The player name jumps on the screen when the message display pops up

# Code Breakdown

# MainActivity
This .java class is to hold the onClick commands for when the start button is pressed. This correlates directly with the activity_main.xml file which uses a Relative Layout to hold 
the username input, description of the game and start button. When the start button is pushed, it calls the intent created in the MAinActivity.java which then points the game to the 
GameActivity.java. Before doing that, it also uses the intent to store the name entered in the EditBox.

# GameActivity

# Username:
The username is recieved using .getExtra() and called into a TextView made in the activity_game.xml file

# button layouts
The buttons are sorted through with two for loops. This is to avoid having to list out the name of every button. The naming convention for the buttons were as follows: "button_00, button_01"
etc. This was to map out the coordinates using buttons more easily. So, instead of listing every button, the ideal solution was to make a for loop that covered from 00 to 44. This was then added
to "button_" An onClick Listener was also setup here to continue for what to do when the buttons are pressed

# OnClick()
In this method, before the buttons are clicked, there is a conditional to see if it is player 1 or player 2's turn. Depending on whose turn it is, it will be an "X" or "O" that appears on the button clicked
After this, the round is counted by 1 so the game may progress to go back to the first player's turn

# CheckforWin()
This checks every possible win condition within a 5x5 board. If the win condition is met, the code goes to the "player1Wins()" method or the method made for player 2, or a draw game method. 

# player1Wins(), player2Wins(), draw() methods
These are to set the text of the textView to who won and set a boolean to true
the boolean being "player1Wins" or "player2Wins" or "drawwGame" (depending on who wins) 

# resetBoard()
In this, the boolean that determined who wins ("player1Wins") is then checked to see which one is true. If any are ture, then the board is reset as well as the turn count and it goes backt to player 1's turn.
NOTE: this is only called when the reset button is pushed, because the onClick in the activity_game.xml for the reset button is set to resetBoard()
If no boolean is true, then the method does nothing, therefore making the button do nothing

