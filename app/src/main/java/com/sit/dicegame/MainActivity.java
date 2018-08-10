package com.sit.dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    TextView player_turn, p1_turn, p2_turn, p1_total, p2_total;
    Button button_roll,button_hold,button_reset;
    ImageView iv;
    int p1TotalScore, p2TotalScore, diceValue;
    int score=0;
    boolean isP1Turn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player_turn=(TextView) findViewById(R.id.tv_player_turn);
        p1_turn=(TextView) findViewById(R.id.p1_turn);
        p2_turn=(TextView) findViewById(R.id.p2_turn);
        p1_total=(TextView) findViewById(R.id.p1_total);
        p2_total=(TextView) findViewById(R.id.p2_total);

        iv=(ImageView) findViewById(R.id.iv);

        if(score==10)
        {gameOver();}
        button_roll=(Button) findViewById(R.id.button_roll);
        button_hold=(Button) findViewById(R.id.button_hold);
        button_reset=(Button) findViewById(R.id.button_reset);


        button_roll.setOnClickListener(this);
        button_hold.setOnClickListener(this);
        button_reset.setOnClickListener(this);

    }

    public void roll()
    {
        Random random= new Random();
        diceValue= 1+ random.nextInt(7);
         if (diceValue==1)
         {
             score=0;
             finalizeScore();
         }
        else
         {
             score+=diceValue;
         }

        updateViews();


    }

    public void finalizeScore()
    {
        if(isP1Turn)
        {
            p1TotalScore+=score;
        }
        else
        {
            p2TotalScore+=score;
        }

        isP1Turn=!isP1Turn;
        score=0;
    }

    public void hold()
    {

        finalizeScore();
    }

    public void reset()
    {

        score=p1TotalScore=p2TotalScore=0;
        isP1Turn=true;
        updateViews();

    }

    private void gameOver()
    {
        if(isP1Turn)
            player_turn.setText("Player 1 wins!!!");
        else
            player_turn.setText("Player 2 wins!!!");

    }

    private void updateViews()
    {
        if(isP1Turn)
            player_turn.setText("Player 1 Turn");
        else
            player_turn.setText("Player 2 Turn");
        switch(diceValue)
        {
            case 1:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                break;
            case 2:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                break;
            case 3:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                break;
            case 4:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                break;
            case 5:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                break;
            case 6:
                iv.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                break;

        }

        if(isP1Turn)
        {
            p1_turn.setText("P1 Turn Score:" +score);
            p2_turn.setText("P2 Turn Score: 0");
        }

        else
        {
            p2_turn.setText("P2 Turn Score:" +score);
            p1_turn.setText("P1 Turn Score: 0");
        }

        p1_total.setText("P1 Total Score:" +p1TotalScore);
        p2_total.setText("P2 Total Score:" +p2TotalScore);

    }



    @Override
    public void onClick(View v)
    {


        switch (v.getId())
        {
            case R.id.button_roll:
                roll();
                break;

            case R.id.button_hold:
                hold();
                break;

            case R.id.button_reset:
                reset();
                break;
        }
    }
}
