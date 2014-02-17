package com.luiges90.sq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

public class Progress {

    public static final int MODE_STANDARD = 0;
    public static final int MODE_SURVIVAL = 1;

    private int currentLevel;
    private Context context;
    
    private int mode;

    private File getFile() {
        return new File(context.getFilesDir(), 
                mode == MODE_STANDARD ? "sq_std_progress" : "sq_srv_progress");
    }

    private static Progress standard_instance;
    private static Progress survival_instance;

    public static Progress instance(Context context, int mode) {
        if (mode == MODE_STANDARD) {
            if (standard_instance == null) {
                standard_instance = new Progress(context, mode);
            }
            return standard_instance;
        } else if (mode == MODE_SURVIVAL) {
            if (survival_instance == null) {
                survival_instance = new Progress(context, mode);
            }
            return survival_instance;
        } else {
            throw new IllegalArgumentException("Unexpected mode: " + mode);
        }
    }
    
    private String getEventTag() {
        return this.mode == MODE_STANDARD ? "standard progress" : "survival progress";
    }

    private Progress(Context context, int mode) {
        this.context = context;
        this.mode = mode;
        File file = getFile();

        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new FileReader(file));
                currentLevel = Integer.parseInt(br.readLine());
                br.close();
            } catch (IOException ex) {
                currentLevel = 0;
                saveProgress();
                if (br != null) {
                    br.close();
                }
            } catch (NumberFormatException ex) {
                currentLevel = 0;
                saveProgress();
                if (br != null) {
                    br.close();
                }
            }
        } catch (IOException ex) {
            Log.e("progress", "Unable to close file", ex);
        }
    }

    private void saveProgress() {
        BufferedWriter bw = null;
        try {
            File file = getFile();
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(Integer.toString(currentLevel));
            bw.close();
        } catch (IOException e) {
            Log.e("progress", "Unable to save progress", e);
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                Log.e("progress", "Unable to close file", ex);
            }
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void advanceLevel(int newLevel) {
        if (newLevel > currentLevel) {
            EasyTracker.getInstance(context).send(
                    MapBuilder.createEvent(getEventTag(), "progress", "reached level " + newLevel,
                            null)
                            .build());
            currentLevel = newLevel;
            saveProgress();
        }
    }


}
