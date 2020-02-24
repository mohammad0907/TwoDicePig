package com.example.cis436p1_twodicepig;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int diceOneVal = 1;
    int diceTwoVal = 1;
    boolean playerOneTurn = true;
    Player p1 = new Player("P1");
    Player p2 = new Player("P2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView currentPlayer = (TextView) findViewById(R.id.currentPlayer);
        final TextView turnTotal = (TextView) findViewById(R.id.turnTotal);
        final TextView p1Score = (TextView)findViewById(R.id.playerOne);
        final TextView p2Score = (TextView) findViewById(R.id.playerTwo);
        Button hold = (Button) findViewById(R.id.hold);
        Button roll = (Button) findViewById(R.id.rollDice);
        Button reset = (Button) findViewById(R.id.reset);
        ImageView diceOnePic = (ImageView) findViewById(R.id.diceOnePic);
        ImageView diceTwoPic = (ImageView) findViewById(R.id.diceTwoPic);
        turnTotal.setText("Turn Total: 0");
        p1Score.setText("Player One : 0");
        p2Score.setText("Player Two: 0");
        currentPlayer.setText("Current Player: " + p1.getPlayerName());



       rollDice(roll, hold, p1Score, p2Score,turnTotal, currentPlayer, diceOnePic, diceTwoPic );
        hold(roll, hold,  p1Score, p2Score,turnTotal, currentPlayer);
        reset(reset, currentPlayer, turnTotal, p1Score, p2Score, diceOnePic, diceTwoPic, roll, hold);
    }

    private void rollDice(Button roll, final Button hold,  final TextView player1, final TextView player2, final TextView turnTotal, final TextView currentPlayer, final ImageView diceOnePic, final ImageView diceTwoPic){

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceOneVal = (int)(Math.random() * (6 - 1 + 1) + 1);
                diceTwoVal = (int)(Math.random() * (6 - 1 + 1) + 1);
                setImage(diceOneVal, diceTwoVal,diceOnePic, diceTwoPic);

                hold.setEnabled(true);
                if(playerOneTurn){
                    if(diceOneVal == 1 && diceTwoVal == 1){
                        p1.setTurnTotal(0);
                        p1.setScore(0);
                        turnTotal.setText("Turn Total : 0");
                        player1.setText("Player One : " + p1.getScore());
                        currentPlayer.setText("Current Player: " + p2.getPlayerName());
                        playerOneTurn = !playerOneTurn;

                    }else if(diceOneVal == 1 || diceTwoVal == 1){
                        p1.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        currentPlayer.setText("Current Player: " + p2.getPlayerName());
                        playerOneTurn = !playerOneTurn;
                    }else if(diceOneVal == diceTwoVal){

                        hold.setEnabled(false);
                        p1.setTurnTotal(p1.getTurnTotal() + (diceOneVal + diceTwoVal));
                        turnTotal.setText("Turn Total : " + p1.getTurnTotal() );
                    }
                    else{
                        p1.setTurnTotal(p1.getTurnTotal() + (diceOneVal + diceTwoVal));
                        turnTotal.setText("Turn Total : " + p1.getTurnTotal() );
                    }

                }else{
                    if(diceOneVal == 1 && diceTwoVal == 1){
                        p2.setTurnTotal(0);
                        p2.setScore(0);
                        turnTotal.setText("Turn Total : 0");
                        player2.setText("Player Two : " + p2.getScore());
                        currentPlayer.setText("Current Player: " + p1.getPlayerName());
                        playerOneTurn = !playerOneTurn;

                    }else if(diceOneVal == 1 || diceTwoVal == 1){
                        p2.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        currentPlayer.setText("Current Player: " + p1.getPlayerName());
                        playerOneTurn = !playerOneTurn;
                    }else if(diceOneVal == diceTwoVal){

                        hold.setEnabled(false);
                        p2.setTurnTotal(p2.getTurnTotal() + (diceOneVal + diceTwoVal));
                        turnTotal.setText("Turn Total : " + p2.getTurnTotal() );
                    }
                    else{
                        p2.setTurnTotal(p2.getTurnTotal() + (diceOneVal + diceTwoVal));
                        turnTotal.setText("Turn Total : " + p2.getTurnTotal() );
                    }

                }


            }
        });
    }

    private void hold(final Button roll, final Button hold,  final TextView player1, final TextView player2, final TextView turnTotal, final TextView currentPlayer ){

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerOneTurn){

                    p1.setScore(p1.getScore() + p1.getTurnTotal());
                    if(p1.getScore() >= 50){
                        p1.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        player1.setText("Player One : " + p1.getScore() +" : WINNER!!!");
                        roll.setEnabled(false);
                        hold.setEnabled(false);
                    }else{
                        p1.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        player1.setText("Player One : " + p1.getScore());
                        currentPlayer.setText("Current Player: " + p2.getPlayerName());
                        playerOneTurn = !playerOneTurn;
                    }


                }else{

                    p2.setScore(p2.getScore() + p2.getTurnTotal());
                    if(p2.getScore() >=50){
                        p2.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        player2.setText("Player Two : " + p2.getScore() + " : WINNER!!!");
                        roll.setEnabled(false);
                        hold.setEnabled(false);
                    }else{
                        p2.setTurnTotal(0);
                        turnTotal.setText("Turn Total : 0");
                        player2.setText("Player Two : " + p2.getScore());
                        currentPlayer.setText("Current Player: " + p1.getPlayerName());
                        playerOneTurn = !playerOneTurn;
                    }

                }
            }
        });
    }

    private void setImage(int diceOneVal, int diceTwoVal, ImageView diceOnePic, ImageView diceTwoPic){
        if(diceOneVal == 1){
            diceOnePic.setImageResource(R.drawable.one);
        }else if(diceOneVal == 2){
            diceOnePic.setImageResource(R.drawable.two);
        }else if(diceOneVal == 3){
            diceOnePic.setImageResource(R.drawable.three);
        }else if(diceOneVal == 4){
            diceOnePic.setImageResource(R.drawable.four);
        }else if(diceOneVal == 5){
            diceOnePic.setImageResource(R.drawable.five);
        }else if(diceOneVal == 6){
            diceOnePic.setImageResource(R.drawable.six);
        }

        if(diceTwoVal == 1){
            diceTwoPic.setImageResource(R.drawable.one);
        }else if(diceTwoVal == 2){
            diceTwoPic.setImageResource(R.drawable.two);
        }else if(diceTwoVal == 3){
            diceTwoPic.setImageResource(R.drawable.three);
        }else if(diceTwoVal == 4){
            diceTwoPic.setImageResource(R.drawable.four);
        }else if(diceTwoVal == 5){
            diceTwoPic.setImageResource(R.drawable.five);
        }else if(diceTwoVal == 6){
            diceTwoPic.setImageResource(R.drawable.six);
        }

    }

    private void reset(Button reset , final TextView currentPlayer, final TextView turnTotal, final TextView p1Score, final TextView p2Score, final ImageView diceOnePic, final ImageView diceTwoPic, final Button roll, final Button hold){
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnTotal.setText("Turn Total: 0");
                p1Score.setText("Player One : 0");
                p2Score.setText("Player Two: 0");
                currentPlayer.setText("Current Player: " + p1.getPlayerName());
                diceOneVal = 1;
                diceOneVal = 1;
                diceOnePic.setImageResource(R.drawable.one);
                diceTwoPic.setImageResource(R.drawable.one);
                p1.setScore(0);
                p2.setScore(0);
                p1.setTurnTotal(0);
                p2.setTurnTotal(0);
                hold.setEnabled(true);
                roll.setEnabled(true);

            }
        });
    }
}
