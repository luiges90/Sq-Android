package com.luiges90.sq;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GameStandardActivity extends GameActivity {
    
    public static final int START_WAVE_REQUEST_CODE = 1;
    public static final String INTENT_SELECTED_WAVE_CODE = "selected_wave";
    
    protected String getStateFileName() {
        return "sq_state_standard";
    }
    
    protected String getName() {
        return "Standard";
    }
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GameView gv = (GameView) findViewById(R.id.gameView);
        
        gv.getField().setGameOverListener(new GameEnd());
        
        if (isNewGame()) {
            gv.getField().setPlayerLives(1);
        }
        
        gv.getField().setDrawDetails(false);
    }
    
    public class GameEnd implements GameOverListener {
        public void onGameOver(GameField field) {
            GameView gv = (GameView) findViewById(R.id.gameView);
            gv.getField().resetWave(1);
            gv.getField().setGameOverListener(new GameEnd());
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.standard_game_action, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_select_wave:
                Intent intent = new Intent(GameStandardActivity.this, WaveSelectionActivity.class);
                startActivityForResult(intent, START_WAVE_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == START_WAVE_REQUEST_CODE) {
            if (data.hasExtra(INTENT_SELECTED_WAVE_CODE)) {
                int wave = data.getExtras().getInt(INTENT_SELECTED_WAVE_CODE);
                GameView gv = (GameView) findViewById(R.id.gameView);
                gv.getField().setWave(wave);
            }
        }
    }
    
}
