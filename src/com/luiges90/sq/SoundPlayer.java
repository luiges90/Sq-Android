package com.luiges90.sq;

import android.content.Context;

public class SoundPlayer {

    // private SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    // private SparseIntArray soundMap = new SparseIntArray();
    //
    // private Context context;

    // private void addSound(int resId) {
    // int soundId = soundPool.load(context, resId, 1);
    // soundMap.put(resId, soundId);
    // }

    public SoundPlayer(Context context) {
        // this.context = context;

        // addSound(R.raw.bullet_destroy);
        // addSound(R.raw.destroy);
        // addSound(R.raw.fire);
        // addSound(R.raw.hit);
        // addSound(R.raw.player_destroy);
        // addSound(R.raw.player_fire);
        // addSound(R.raw.powerup);
        // addSound(R.raw.teleport);
    }

    public void playSound(int resId) {
        // AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // final float volume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC)
        // / am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //
        // int id = soundMap.get(resId);
        // if (id > 0) {
        // soundPool.play(id, volume, volume, 1, 0, 1);
        // }
    }

}
