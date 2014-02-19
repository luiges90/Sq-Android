package com.luiges90.sq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class Hiscore {

    public static final String LOG_TAG = "sq_hiscore";
    public static final int LENGTH = 10;

    public static final String HISCORE_FILE = "sq_hiscore";
    private Context context;

    private List<Entry> entries, storedEntries;

    public static final class Entry implements Comparable<Entry>, Serializable {

        private static final long serialVersionUID = -4318962558402009765L;
        public final int wave;
        public final int score;

        public Entry(int w, int s) {
            wave = w;
            score = s;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + score;
            result = prime * result + wave;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Entry))
                return false;
            Entry other = (Entry) obj;
            if (score != other.score)
                return false;
            if (wave != other.wave)
                return false;
            return true;
        }

        @Override
        public int compareTo(Entry another) {
            if (this.wave == another.wave) {
                return this.score - another.score;
            }
            return this.wave - another.wave;
        }

        @Override
        public String toString() {
            return "Entry [wave=" + wave + ", score=" + score + "]";
        }

    }

    private void initEntries() {
        entries.clear();
        for (int i = 0; i < LENGTH; ++i) {
            entries.add(new Entry(0, 0));
        }
    }

    private void loadHiScore() {
        File file = new File(context.getFilesDir(), HISCORE_FILE);

        entries = new ArrayList<Entry>();
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    int wave = Integer.parseInt(data[0]);
                    int score = Integer.parseInt(data[1]);
                    entries.add(new Entry(wave, score));
                }
                if (entries.size() != LENGTH) {
                    br.close();
                    throw new IOException("There should be 10 entries");
                }
                br.close();
            } catch (IOException ex) {
                initEntries();
                if (br != null) {
                    br.close();
                }
            } catch (NumberFormatException ex) {
                initEntries();
                if (br != null) {
                    br.close();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                initEntries();
                if (br != null) {
                    br.close();
                }
            }
        } catch (IOException ex2) {
            Log.e(LOG_TAG, "Unable to close file", ex2);
        }
    }

    private void saveHiScore() {
        File file = new File(context.getFilesDir(), HISCORE_FILE);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Entry e : entries) {
                bw.write(e.wave + "," + e.score + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Unable to save hiscores", ex);
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex2) {
                    Log.e(LOG_TAG, "Unable to close file", ex2);
                }
            }
        }
    }

    private Hiscore(Context context) {
        this.context = context;
        loadHiScore();
    }

    private static Hiscore instance;

    public static Hiscore getInstance(Context context) {
        if (instance == null) {
            instance = new Hiscore(context);
        }
        return instance;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public Entry getEntry(int p) {
        return entries.get(p);
    }

    public void addEntry(Entry e) {
        entries.add(e);

        Collections.sort(entries);
        Collections.reverse(entries);
        entries.remove(LENGTH);

        saveHiScore();
    }

    public void clear() {
        new File(context.getFilesDir(), HISCORE_FILE).delete();
        storedEntries = new ArrayList<Entry>(entries);
        initEntries();
    }

    public void restore() {
        entries = storedEntries;
        saveHiScore();
        storedEntries = null;
    }

    public boolean isRestorable() {
        return storedEntries != null;
    }

    @Override
    public String toString() {
        return "Hiscore [context=" + context + ", entries=" + entries + "]";
    }
}
