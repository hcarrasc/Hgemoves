package com.hcarrasko.dataManager;
import java.util.Timer;
import java.util.TimerTask;


public class TimerUpdater {
	
	private long delay = 0;
	private long interval = 600000;
	ChessDotComDataManager dataManager = new ChessDotComDataManager();

	public void updateDataRobot(final String chessDotComUser) {
		Timer temporizer = new Timer();
		TimerTask task = new TimerTask(){
		    @Override
		    public void run() {
			}
		};
		temporizer.schedule (task,delay,interval);
	}

}
