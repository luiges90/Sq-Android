package com.luiges90.sq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.analytics.tracking.android.EasyTracker;

public class StatActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        String enemyTitle = getResources().getString(R.string.enemy_stat);
        String waveTitle = getResources().getString(R.string.wave_stat);

        tabHost.addTab(tabHost.newTabSpec(enemyTitle).setIndicator(enemyTitle),
                StatEnemyActivity.class,
                null);
        tabHost.addTab(tabHost.newTabSpec(waveTitle).setIndicator(waveTitle),
                StatWaveActivity.class,
                null);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.report_problem_action, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_report_problem:
                ProblemReporter.report(this, "Stat");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}
