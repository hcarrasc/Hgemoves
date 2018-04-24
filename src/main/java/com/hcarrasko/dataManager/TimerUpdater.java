package com.hcarrasko.dataManager;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUpdater {
	
	// zero seconds for delay
	private long delay = 0;
	// minutes of interval
	private long interval = 600000;
	ChessDotComDataManager dataManager = new ChessDotComDataManager();

	public void updateDataRobot(final String chessDotComUser) {
		Timer temporizer = new Timer();
		TimerTask task = new TimerTask(){
		    @Override
		    public void run() {
				try {
					dataManager.getData(chessDotComUser);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		temporizer.schedule (task,delay,interval);
	}

}
