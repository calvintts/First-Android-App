package com.example.calvintey.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int p1 = 0;
    int[] used={2,2,2,2,2,2,2,2,2};
    int[][] won={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{1,4,7},{2,5,8},{0,3,6},{2,5,8}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        boolean gameActive=true;
        if (used[tappedCounter] == 2&&gameActive) {
            counter.setTranslationY(-1500);
            counter.setImageResource((p1 == 0 ? R.drawable.cross : R.drawable.circle));
            counter.animate().translationYBy(1500).rotation(360 * 5).setDuration(1000);
            used[tappedCounter] = p1;
            for (int[] position : won) {
                if (used[position[0]] == used[position[1]] && used[position[1]] == used[position[2]] && used[position[0]] != 2) {
                    Toast.makeText(this, "Player" + (p1 + 1) + " has won!", Toast.LENGTH_SHORT).show();
                    gameActive=false;
                }
            }
            p1 = (p1 == 0 ? 1 : 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
