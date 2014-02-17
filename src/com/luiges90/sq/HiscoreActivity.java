package com.luiges90.sq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class HiscoreActivity extends Activity {

    public static final String EXTRA_CURRENT_ENTRY = "current_entry";

    // private Menu actionBar;

    private void showHiscore() {
        Hiscore hs = Hiscore.getInstance(this);
        TableLayout table = (TableLayout) this.findViewById(R.id.tblStat);

        boolean highlighted = false;
        int i = 1;
        for (Hiscore.Entry entry : hs.getEntries()) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            
            int textColor;
            if (entry.equals(this.getIntent().getSerializableExtra(EXTRA_CURRENT_ENTRY))
                    && !highlighted) {
                highlighted = true;
                textColor = Color.RED;
            } else {
                textColor = Color.WHITE;
            }

            TextView txRank = new TextView(this);
            TableRow.LayoutParams w1p = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            w1p.weight = 1;
            txRank.setLayoutParams(w1p);
            txRank.setText(Integer.toString(i));
            txRank.setGravity(Gravity.CENTER_HORIZONTAL);
            txRank.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            txRank.setTextColor(textColor);
            
            TextView txWave = new TextView(this);
            TableRow.LayoutParams w3p = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            w3p.weight = 3;
            txWave.setLayoutParams(w3p);
            txWave.setText(Integer.toString(entry.wave));
            txWave.setGravity(Gravity.CENTER_HORIZONTAL);
            txWave.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            txWave.setTextColor(textColor);
            
            TextView txScore = new TextView(this);
            txScore.setLayoutParams(w3p);
            txScore.setText(Integer.toString(entry.score));
            txScore.setGravity(Gravity.CENTER_HORIZONTAL);
            txScore.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            txScore.setTextColor(textColor);
            
            row.addView(txRank);
            row.addView(txWave);
            row.addView(txScore);
            
            table.addView(row, i);
            
            i++;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);

        showHiscore();
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
