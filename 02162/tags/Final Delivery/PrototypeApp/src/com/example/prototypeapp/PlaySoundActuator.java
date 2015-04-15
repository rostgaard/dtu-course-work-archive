package com.example.prototypeapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 
 * @author s113444 (Luai)
 *
 */

public class PlaySoundActuator extends Thread {
	
	private final int S1 = R.raw.wopwop;
	private SoundPool soundPool;
	final int sound;
	private String macAddress;
	private boolean run = true;
	
	public PlaySoundActuator(Activity activity, String macAddress) {	
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sound = soundPool.load(activity, S1, 1);
		this.macAddress = macAddress;
	}
	
	@Override
	public void run() {
		Event event = null;
		while (run || !isInterrupted()) {
			try {
				event = WebServiceConnection.invokeAwaitEventWebServer(macAddress, EventType.PLAYSOUND);
			} catch (Exception e) {

			}
			if(!run || isInterrupted()) break;
			
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
		run = false;
		soundPool.release();
		this.interrupt();
	}

}
