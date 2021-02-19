//Author : Nico Vazquez
package com.hfad.tictactoetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.tictactoetest.GameActivity;
import com.hfad.tictactoetest.R;

public class MainActivity extends AppCompatActivity {
public EditText userName;
public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
    }

    public void Start(View view) {

        //get the next activity for when the intent is called
        //Intent nameIntent = new Intent(this, GameActivity.class);

        //call the method to open GameActivity
        OpenGame();
    }

    private void OpenGame() {
        //Switch activities to GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        name = userName.getText().toString();
        intent.putExtra("Value", name);
        //Start the GameActivity
        startActivity(intent);
        finish();

    }


}