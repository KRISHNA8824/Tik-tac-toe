package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String n1;
    String n2;
    public void play(View view){
        TextView name1 = (TextView) findViewById(R.id.name1);
        TextView name2 = (TextView) findViewById(R.id.name2);
        n1 = name1.getText().toString();
        n2 = name2.getText().toString();
        if(n1.isEmpty() || n2.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter names of both players",Toast.LENGTH_LONG).show();
        }
        else{
            ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main);
            main.setVisibility(view.INVISIBLE);
            ImageView block = (ImageView) findViewById(R.id.block);
            ConstraintLayout cn = (ConstraintLayout) findViewById(R.id.constraintLayout);
            block.setVisibility(view.VISIBLE);
            cn.setVisibility(view.VISIBLE);
            Toast.makeText(getApplicationContext(),"It's "+n1+"'s turn",Toast.LENGTH_LONG).show();
        }
    }
    int activeplayer=0;
    int [] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winning_positions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void fade(View view){
        ImageView image = (ImageView) view;
        //image.view
        int tapped_image = Integer.parseInt(image.getTag().toString());
        //Log.i("tapped image",Integer.toString(tapped_image));
        if(gamestate[tapped_image]==2) {
            gamestate[tapped_image] = activeplayer;
            image.setTranslationY(-1000f);
            if (activeplayer == 0) {
                image.setImageResource(R.drawable.cross);
                image.animate().translationYBy(1000f).rotation(360f).setDuration(800);
                activeplayer = 1;
                //Log.i("cross",Integer.toString(activeplayer));
            } else {
                image.setImageResource(R.drawable.circle);
                image.animate().translationYBy(1000f).rotation(360f).setDuration(800);
                activeplayer = 0;
                //Log.i("circle",Integer.toString(activeplayer));
            }
            boolean check = true;
            for(int[] winning_position : winning_positions)
            {
                if(gamestate[winning_position[0]] == gamestate[winning_position[1]] && gamestate[winning_position[1]] == gamestate[winning_position[2]] && gamestate[winning_position[0]] != 2)
                {
                    String winner = n1;
                    int l=0;
                    if(gamestate[winning_position[0]] == 1)
                    {
                        l=1;
                        winner = n2;
                    }
                    Log.i("winner team is: ",winner);
                    System.out.println(gamestate[winning_position[0]]);
                    for(int i=0;i<9;i++)
                    {
                        if(gamestate[i]==2)
                        {
                            gamestate[i]=l;
                        }
                    }
                    TextView message = (TextView) findViewById(R.id.winner);
                    message.setText(winner + " has won");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                    layout.setVisibility(view.VISIBLE);
                    check = false;
                }
            }
            boolean gameover = true;
            for(int i=0;i<9;i++){
                if(gamestate[i]==2){gameover = false;}
            }
            if(gameover == true && check==true){
                Log.i("K","lp");
                TextView message = (TextView) findViewById(R.id.winner);
                message.setText("Match is draw");
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                layout.setVisibility(view.VISIBLE);
            }

        }
    }
    public void playAgain (View view) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main);
        ConstraintLayout constraint = (ConstraintLayout) findViewById(R.id.constraintLayout);
        ImageView block = (ImageView) findViewById(R.id.block);
        block.setVisibility(view.INVISIBLE);
        main.setVisibility(view.VISIBLE);
        layout.setVisibility(view.INVISIBLE);
        constraint.setVisibility(view.INVISIBLE);
        activeplayer = 0;
        n1="";
        n2="";
        for (int i = 0; i < 9; i++){
            gamestate[i] = 2;
        }
        for(int i=0;i<constraint.getChildCount();i++)
        {
            ((ImageView)constraint.getChildAt(i)).setImageResource(0);
        }
        //constraint.setVisibility(view.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
