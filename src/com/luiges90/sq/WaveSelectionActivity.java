package com.luiges90.sq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class WaveSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_selection);

        ((GridView) this.findViewById(R.id.gridWave)).setAdapter(new WaveAdapter());
    }

    public class WaveAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Progress.instance(WaveSelectionActivity.this, Progress.MODE_STANDARD).getCurrentLevel();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            StaticGameView view;
            if (convertView == null) {
                view = new StaticGameView(WaveSelectionActivity.this);
            } else {
                view = (StaticGameView) convertView;
            }

            GameField gf = new GameField(WaveSelectionActivity.this, Progress.MODE_STANDARD);
            gf.setWave(position + 1);

            view.setField(gf);

            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent data = new Intent();
                    data.putExtra(GameStandardActivity.INTENT_SELECTED_WAVE_CODE, position + 1);
                    setResult(RESULT_OK, data);
                    finish();
                }

            });

            return view;
        }

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
