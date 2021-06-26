package sk.stuba.fei.uim.upb.utils;

import java.util.concurrent.TimeUnit;

public class Timer {

	private long start;
	private long end;
	
	public void start() {
		this.start = System.nanoTime();
	}
	
	public void stop() {
		this.end = System.nanoTime();
	}
	
	public String durationString() {
		long duration = (end - start);
		String durationString = duration + " ns";
		if(duration >= 1000000000) {
			durationString = TimeUnit.NANOSECONDS.toSeconds(duration) + " s";
		} else if (duration >= 1000000) {
			durationString = TimeUnit.NANOSECONDS.toMillis(duration) + " ms";
		}
	
		return durationString;
	}
}
