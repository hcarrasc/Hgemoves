package com.hcarrasko.dataManager;

import com.google.gson.Gson;
import com.hcarrasko.httpManager.HTTPConnection;
import com.hcarrasko.model.GamesDTO;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ChessDotComDataManager {
	
	final static Logger logger = Logger.getLogger(ChessDotComDataManager.class);

	public String getUserData(String user) {

		HTTPConnection connectionManager = new HTTPConnection();
		String host = "https://api.chess.com/pub/player/"+user+"/games/to-move";
		String jsonData="/json";
		try {
			jsonData = connectionManager.doHTTPRequest(host);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NO CONNECTION");
		}
		return jsonData;
	}
	
	public void getData(String user) {

		logger.info("Getting info of user: "+user);
		String jsonData = getUserData(user);
        GamesDTO games = new Gson().fromJson(jsonData, GamesDTO.class);
        int availableMoves = games.getGames().size();
		logger.info("total pending games:"+ availableMoves);
		createNotification(availableMoves);

	}

	private void createNotification(int availableMoves){

		if(availableMoves>0){
			String header = "Hgemovs";
			String title = "Daily moves from chess.com";
			String subtitle = "It's you turn in "+availableMoves+" games :D";
			String[] cmd = {"osascript", "-e", "display notification \""+title+"\" with title \""+header+"\" subtitle \""+subtitle+"\" sound name \"Glass\""};
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String header = "Hgemovs";
			String title = "Daily moves from chess.com";
			String subtitle = "No pending moves";

			String[] cmd = {"osascript", "-e", "display notification \""+title+"\" with title \""+header+"\" subtitle \""+subtitle+"\" sound name \"Glass\""};
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
