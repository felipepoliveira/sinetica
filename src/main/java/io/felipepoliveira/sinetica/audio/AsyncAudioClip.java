package io.felipepoliveira.sinetica.audio;

import io.felipepoliveira.sinetica.SoundPlayer;

/**
 * Represents an asynchronous audio clip
 * @author Felipe Oliveira
 */
public abstract class AsyncAudioClip implements AudioClip {
	
	private boolean running = false;
	
	protected boolean loop;
	
	protected Thread thread;
	
	protected SoundPlayer soundPlayer;
	
	public AsyncAudioClip(SoundPlayer soundPlayer) {
		this.soundPlayer = soundPlayer;
		this.thread = new Thread(new Runnable() {
			
			public void run() {
				while(AsyncAudioClip.this.running) AsyncAudioClip.this.play(AsyncAudioClip.this.soundPlayer);
			}
		});
	}
	
	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}

	/**
	 * Interrupt the audio clip for playing 
	 */
	abstract void interrupt();
	
	public void stop() {
		running = false;
	}
	
	public void start() {
		System.out.println("Started");
		running = true;
		this.thread.start();
	}

}
