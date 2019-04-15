package com.example.calvintey.tictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.animation.Animation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int p1 = 0;
    int[] used={2,2,2,2,2,2,2,2,2};
    int[][] won={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{1,4,7},{2,5,8},{0,3,6},{2,4,6}};
    boolean gameActive=true;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (used[tappedCounter] == 2&&gameActive) {
            counter.setTranslationY(-1500);
            counter.setImageResource((p1 == 0 ? R.drawable.cross : R.drawable.circle));
            counter.animate().translationYBy(1500).rotation(360 * 5).setDuration(1000);
            used[tappedCounter] = p1;
            MediaPlayer sound1= MediaPlayer.create(this,R.raw.sound1);
            sound1.start();
            for (int[] position : won) {
                if (used[position[0]] == used[position[1]] && used[position[1]] == used[position[2]] && used[position[0]] != 2) {
                    Toast.makeText(this, "Player" + (p1 + 1) + " has won!", Toast.LENGTH_LONG).show();
                    gameActive=false;
                }
            }
            p1 = (p1 == 0 ? 1 : 0);
        }
    }

    public void reset(View view){
        Log.i("value","reset button clicked");
        for(int i=0;i<used.length;i++)
        {
            used[i]=2;
        }
        gameActive=true;
        android.support.v7.widget.GridLayout board =  findViewById(R.id.gridLayout);
        board.animate().rotationBy(360).setDuration(500);
        for(int i=0;i<board.getChildCount();i++)
        {
            ImageView counter = (ImageView) board.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Toast.makeText(this,"Game Reset",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
