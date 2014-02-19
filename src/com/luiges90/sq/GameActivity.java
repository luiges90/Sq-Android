package com.luiges90.sq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

public abstract class GameActivity extends Activity {

    public static final String STATE_VIEW_STATE = "sq_view_state";
    private boolean newGame;

    protected abstract String getStateFileName();

    protected abstract String getName();

    protected boolean isNewGame() {
        return newGame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameView gv = (GameView) findViewById(R.id.gameView);

        newGame = true;

        if (savedInstanceState != null) {
            gv.restoreState(this, savedInstanceState.getSerializable(STATE_VIEW_STATE));
            newGame = false;
        } else if (!GameField.DEBUG) {
            File file = new File(this.getFilesDir(), getStateFileName());

            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                gv.restoreState(this, (Serializable) ois.readObject());
                newGame = false;
                ois.close();
            } catch (IOException ex) {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e2) {
                        Log.e("Quitsave", "Unable to close state file", e2);
                    }
                }
                // State not found or otherwise corrupt, go ahead and start a new game.
            } catch (ClassNotFoundException e) {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e2) {
                        Log.e("Quitsave", "Unable to close state file", e2);
                    }
                }
                // State not found or otherwise corrupt, go ahead and start a new game.
            }
        }

        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(
                        new SharedPreferences.OnSharedPreferenceChangeListener() {
                            @Override
                            public void onSharedPreferenceChanged(
                                    SharedPreferences sharedPreferences,
                                    String key) {
                                if (key.equals(AboutActivity.TRACK_PREF_KEY)) {
                                    GoogleAnalytics.getInstance(getApplicationContext())
                                            .setAppOptOut(
                                                    sharedPreferences.getBoolean(key, false));
                                }
                            }
                        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Stat.getInstance(this).save();

        GameView gv = (GameView) findViewById(R.id.gameView);
        savedInstanceState.putSerializable(STATE_VIEW_STATE, gv.getStoreState());
    }

    @Override
    public void onBackPressed() {
        Stat.getInstance(this).save();

        File file = new File(this.getFilesDir(), getStateFileName());

        GameView gv = (GameView) findViewById(R.id.gameView);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(gv.getStoreState());
            oos.close();
        } catch (IOException e) {
            Log.e("Quitsave", "Unable to save state file", e);
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e2) {
                    Log.e("Quitsave", "Unable to close state file", e2);
                }
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_unpause:
                // shut down menu will unpause the game
                return true;
            case R.id.action_stat:
                Stat.getInstance(this).save();
                Intent intent = new Intent(this, StatActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_report_problem:
                ProblemReporter.report(this, this.getName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this); // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this); // Add this method.
    }
}
