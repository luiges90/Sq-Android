package com.luiges90.sq;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GameSurvivalActivity extends GameActivity {

    public static final int RESET_ON_RETURN = 1;

    protected String getStateFileName() {
        return "sq_state_survival";
    }

    protected String getName() {
        return "Survival";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameView gv = (GameView) findViewById(R.id.gameView);

        gv.getField().setGameOverListener(new GameEnd());

        if (isNewGame()) {
            gv.getField().setPlayerLives(GameField.DEBUG ? 9999 : 5);
            gv.getField().setAddLifeEvery(5);
        }

        gv.getField().setDrawDetails(true);
    }

    public class GameEnd implements GameOverListener {
        public void onGameOver(GameField field) {
            Hiscore hs = Hiscore.getInstance(GameSurvivalActivity.this);
            Hiscore.Entry entry = new Hiscore.Entry(field.getWave(), field.getScore());
            hs.addEntry(entry);

            Intent intent = new Intent(GameSurvivalActivity.this, HiscoreActivity.class);
            intent.putExtra(HiscoreActivity.EXTRA_CURRENT_ENTRY, entry);
            GameSurvivalActivity.this.startActivityForResult(intent, RESET_ON_RETURN);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESET_ON_RETURN) {
            GameView gv = (GameView) findViewById(R.id.gameView);
            gv.getField().reset(5);
            gv.getField().setGameOverListener(new GameEnd());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.survival_game_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final GameView gv = (GameView) findViewById(R.id.gameView);
        switch (item.getItemId()) {
            case R.id.action_hiscore:
                Intent intent = new Intent(this, HiscoreActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_retire:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage(R.string.retire_msg);
                b.setPositiveButton(R.string.retire, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        (new GameEnd()).onGameOver(gv.getField());
                    }
                });
                b.setNegativeButton(R.string.cancel, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
                b.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
