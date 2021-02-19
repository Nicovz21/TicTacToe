//Author : Nico Vazquez
package com.hfad.tictactoetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity  implements View.OnClickListener {

    //username from MainActivity
   private TextView userNameOutput;
    private String userName;

    //setup the array that correlates to the grid for buttons
    private Button[][] buttons = new Button[5][5];

    //determine whose turn it is by checking if it's player 1's turn
    private boolean player1Turn = true;

    //boolean for player 1 winning
    private boolean player1Wins = false;

    //boolean for player 2 winning
    private boolean player2Wins = false;

    //boolean for draw game
    private boolean drawGame = false;

    //keep count of rounds variable
    private int roundCount;

    private String winMessage;

//    //initialize notification textview
   private TextView notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        userNameOutput=findViewById(R.id.userNameOutput);



        notifications = (TextView) findViewById(R.id.notification);

        notifications.setText("");
        //get username from MainActivity
        userName = getIntent().getExtras().getString("Value");
        userNameOutput.setText(userName);
        //search through the buttons by column and row
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //find the button id by assining buttons the id of button_00 through button_44
                String buttonID = "button_" + i + j;
                //replacement to R.id
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                //include the resID in the viewID
                buttons[i][j] = findViewById(resID);
                //make onClickListeners for each button
                buttons[i][j].setOnClickListener(this);
            }
        }
        //create the notification text
        TextView notification = (TextView) findViewById(R.id.notification);

    }




    @Override
    //onClick commands for when a button is clicked
    public void onClick(View v) {
//        //create a random value from 0-4
//        int randomValue = getRandomInteger(5, 0);

        //if no button is clicked
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        //if it is player 1's turn and the button being clicked is not X
        if (player1Turn) {

            ((Button) v).setText("X");

        }else{

            ((Button) v).setText("O");
        }
        //increase the count of the round
        roundCount++;

        //check to see who wins
        if (checkForWin()) {
            //if it is player 1 turn on the win condition, then player 1 wins
            if (player1Turn) {
                player1Wins();
            //check to see if it player 2's turn on win condition
            } else {
                player2Wins();
            }

        //call a draw if 25 spots have been filled with no winner
        } else if (roundCount == 25) {
            draw();
        //if no one wins and there's no draw then return to player turn
        } else {
            player1Turn = !player1Turn;
        }

    }
    //check to see if the Crosses or Noughts lineup to be considered a "win"
    private boolean checkForWin() {
        String[][] field = new String[5][5];
    //itterate throught the buttons to see if it is a "X" or "O"
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            //Check all rows across
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            //Check all rows across Backwards
            if (field[i][4].equals(field[i][3])
                    && field[i][4].equals(field[i][2])
                    && field[i][4].equals(field[i][1])
                    && !field[i][4].equals("")) {
                return true;
            }
        }


        for (int i = 0; i < 5; i++) {
            //Check all columns down
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //Check diagnol going from 00 to 33
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && !field[0][0].equals("")) {
            return true;
        }
        //Check diagnol going from 04 to 31
        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2])
                && field[0][4].equals(field[3][1])
                && !field[0][4].equals("")) {
            return true;
        }
        //Check diagnol going from 44 to 11
        if (field[4][4].equals(field[3][3])
                && field[4][4].equals(field[2][2])
                && field[4][4].equals(field[1][1])
                && !field[4][4].equals("")) {
            return true;
        }
        //Check diagnol going from 40 to 13
        if (field[4][0].equals(field[3][1])
                && field[4][0].equals(field[2][2])
                && field[4][0].equals(field[1][3])
                && !field[4][0].equals("")) {
            return true;
        }
        //Check diagnol from 03 to 30
        if (field[0][3].equals(field[1][2])
                && field[0][3].equals(field[2][1])
                && field[0][3].equals(field[3][0])
                && !field[0][3].equals("")) {
            return true;
        }
        //Check diagnol from 01 to 34
        if (field[0][1].equals(field[1][2])
                && field[0][1].equals(field[2][3])
                && field[0][1].equals(field[3][4])
                && !field[0][1].equals("")) {
            return true;
        }
        //if no win condition is found then return false (no one wins yet so continue game)
        return false;
    }
    //declare player 1 is the winner and reset board
    private void player1Wins() {
        //display who wins
        notifications.setText("Player 1 wins");

        player1Wins = true;
    }
    //declare player 2 is the winner and reset board
    private void player2Wins() {
        //display who wins
        notifications.setText("Player 2 Wins");
        player2Wins = true;
    }
    //declare no one is the winner and reset board
    private void draw() {
        //display who wins
        notifications.setText("Draw Game!");
        drawGame = true;
    }
//set all buttons back to being empty strings (so they do not show "X" or "O")
    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }
        //reset the rounds to 0 and make it player 1 turn again
        roundCount = 0;
        player1Turn = true;
    }
    private void resetGame(){
        resetBoard();
    }
//save all the data to get ready for a screen rotation
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("player1Turn", player1Turn);
    }
//use the previously saved data that was prepared for the screen rotation and return all the data to where it should be
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
//Button for resetting the board
    public void restart(View view) {
        if(player1Wins == true || player2Wins == true || drawGame == true) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    buttons[i][j].setText("");
                }
            }
            //set win message
            notifications.setText(" ");
            //set win condition back to false
            player1Wins = false;
            player2Wins = false;
            drawGame = false;
            //reset the rounds to 0 and make it player 1 turn again
            roundCount = 0;
            player1Turn = true;
        }else{

        }


    }
}