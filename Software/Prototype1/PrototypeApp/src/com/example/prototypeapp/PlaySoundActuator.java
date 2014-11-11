package com.example.prototypeapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;

public class PlaySoundActuator extends Thread {
	
	private final int S1 = R.raw.wopwop;
	private SoundPool soundPool;
	final int sound;
	private int sensorID;
	
	public PlaySoundActuator(Activity activity, int sensorID) {	
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sound = soundPool.load(activity, S1, 1);
		this.sensorID = sensorID;
	}
	
	@Override
	public void run() {
		Event event = null;
		while (true) {
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(sensorID, EventType.PLAY_SOUND);
			} catch (Exception e) {

			}
			if (event != null) {
				soundPool.play(sound, 0, 100, 1, 0, 1f);
				event = null;
			}
		}
	}
	
	public void playSound() {
		soundPool.play(sound, 0, 100, 1, 0, 1f);
	}
	
	public void terminate() {
		soundPool.release();
		this.interrupt();
	}

}
