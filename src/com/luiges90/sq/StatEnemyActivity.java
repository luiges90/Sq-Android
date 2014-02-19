package com.luiges90.sq;

import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.luiges90.sq.enemy.BaseEnemy;

public class StatEnemyActivity extends Fragment {

    private void showStats() {
        Stat stat = Stat.getInstance(this.getActivity());
        TableLayout table = (TableLayout) getView().findViewById(R.id.tblStat);

        int i = 1;
        for (Map.Entry<Class<? extends BaseEnemy>, Integer> kv : stat.getKilled().entrySet()) {
            int enemyColor = Color.BLACK;
            try {
                enemyColor = (Integer) kv.getKey().getField("COLOR").get(null);
            } catch (IllegalAccessException e) {
                Log.e("stat", kv.getKey().getSimpleName() + " COLOR was not accessible.", e);
            } catch (NoSuchFieldException e) {
                Log.e("stat", kv.getKey().getSimpleName() + " has no COLOR.", e);
            }

            TableRow row = new TableRow(this.getActivity());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView txEnemy = new TextView(this.getActivity());
            TableRow.LayoutParams w1p = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            w1p.weight = 1;
            txEnemy.setLayoutParams(w1p);
            txEnemy.setGravity(Gravity.CENTER_HORIZONTAL);
            txEnemy.setBackgroundColor(enemyColor);

            TextView txKilled = new TextView(this.getActivity());
            TableRow.LayoutParams w3p = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            w3p.weight = 3;
            txKilled.setLayoutParams(w3p);
            txKilled.setText(Integer.toString(kv.getValue()));
            txKilled.setGravity(Gravity.CENTER_HORIZONTAL);
            txKilled.setTextAppearance(this.getActivity(), android.R.style.TextAppearance_Medium);

            TextView txBeKilled = new TextView(this.getActivity());
            txBeKilled.setLayoutParams(w3p);
            txBeKilled.setText(Integer.toString(stat.getBeKilled(kv.getKey())));
            txBeKilled.setGravity(Gravity.CENTER_HORIZONTAL);
            txBeKilled.setTextAppearance(this.getActivity(), android.R.style.TextAppearance_Medium);

            row.addView(txEnemy);
            row.addView(txKilled);
            row.addView(txBeKilled);

            table.addView(row, i);

            i++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_enemy_stat, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showStats();
    }

}
