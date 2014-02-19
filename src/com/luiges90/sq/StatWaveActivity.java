package com.luiges90.sq;

import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StatWaveActivity extends Fragment {

    private void showStats() {
        Stat stat = Stat.getInstance(this.getActivity());
        TableLayout table = (TableLayout) getView().findViewById(R.id.tblStat);

        int i = 1;
        for (Map.Entry<Integer, Integer> kv : stat.getClearedWaveCounts().entrySet()) {
            TableRow row = new TableRow(this.getActivity());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView txWave = new TextView(this.getActivity());
            TableRow.LayoutParams w1p = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            w1p.weight = 1;
            txWave.setLayoutParams(w1p);
            txWave.setGravity(Gravity.CENTER_HORIZONTAL);
            txWave.setText(Integer.toString(kv.getKey()));
            txWave.setTextAppearance(this.getActivity(), android.R.style.TextAppearance_Medium);

            TextView txCleared = new TextView(this.getActivity());
            txCleared.setLayoutParams(w1p);
            txCleared.setText(Integer.toString(kv.getValue()));
            txCleared.setGravity(Gravity.CENTER_HORIZONTAL);
            txCleared.setTextAppearance(this.getActivity(), android.R.style.TextAppearance_Medium);

            TextView txBeKilled = new TextView(this.getActivity());
            txBeKilled.setLayoutParams(w1p);
            txBeKilled.setText(Integer.toString(stat.getDieInWave(kv.getKey())));
            txBeKilled.setGravity(Gravity.CENTER_HORIZONTAL);
            txBeKilled.setTextAppearance(this.getActivity(), android.R.style.TextAppearance_Medium);

            row.addView(txWave);
            row.addView(txCleared);
            row.addView(txBeKilled);

            table.addView(row, i);

            i++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_wave_stat, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showStats();
    }

}
