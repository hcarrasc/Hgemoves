package com.hcarrasko.dataManager;

import com.google.gson.Gson;
import com.hcarrasko.httpManager.HTTPConnection;
import com.hcarrasko.model.GamesDTO;
import com.hcarrasko.model.StatsDTO;

import org.apache.log4j.Logger;

import java.io.IOException;

public class ChessDotComDataManager {
	
	final static Logger logger = Logger.getLogger(ChessDotComDataManager.class);

	public String requestData(String user, String requestType) {

		HTTPConnection connectionManager = new HTTPConnection();
		String jsonData="/json";

		if (requestType.equals("GAMES")){

			String host = "https://api.chess.com/pub/player/"+user+"/games/to-move";
			try {
				jsonData = connectionManager.doHTTPRequest(host);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("NO CONNECTION");
			}
		} else if (requestType.equals("STATS")){
			
			String host = "https://api.chess.com/pub/player/"+user+"/stats";
			logger.info("Trying call :"+host);
			try {
				jsonData = connectionManager.doHTTPRequest(host);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("NO CONNECTION");
			}
		}

		return jsonData;
	}
	
	public void getGamesData(String user) {

		logger.info("Getting info of user: "+user);
		String requestType = "GAMES";
		String jsonData = requestData(user,requestType);
        GamesDTO games = new Gson().fromJson(jsonData, GamesDTO.class);
        int availableMoves = games.getGames().size();
		logger.info("total pending games:"+ availableMoves);
		createNotification(availableMoves);

	}
	
	public void getPlayerData(String user) {

		logger.info("Getting info of user: "+user);
		String requestType = "STATS";
		String jsonData = requestData(user,requestType);
        StatsDTO stats = new Gson().fromJson(jsonData, StatsDTO.class);
        
		logger.info("Daily ELO:"+ stats.getChess_daily().getLast().getRating());
		logger.info("Win Record:"+ stats.getChess_daily().getRecord().getWin());
		logger.info("Loss Record:"+ stats.getChess_daily().getRecord().getLoss());
		logger.info("Draw Record:"+ stats.getChess_daily().getRecord().getDraw());
		
		logger.info("Bullet ELO:"+ stats.getChess_bullet().getLast().getRating());
		logger.info("Win Record:"+ stats.getChess_bullet().getRecord().getWin());
		logger.info("Loss Record:"+ stats.getChess_bullet().getRecord().getLoss());
		logger.info("Draw Record:"+ stats.getChess_bullet().getRecord().getDraw());
		
		logger.info("Blitz ELO:"+ stats.getChess_blitz().getLast().getRating());
		logger.info("Win Record:"+ stats.getChess_blitz().getRecord().getWin());
		logger.info("Loss Record:"+ stats.getChess_blitz().getRecord().getLoss());
		logger.info("Draw Record:"+ stats.getChess_blitz().getRecord().getDraw());

	}

	private void createNotification(int availableMoves){

		if(availableMoves>0){
			String header = "Hgemovs";
			String title = "Daily moves from chess.com";
			String subtitle = "It's you turn in "+availableMoves+" games :D";
			String[] cmd = {"osascript", "-e", "display notification \""+title+"\" with title \""+header+"\" subtitle \""+subtitle+"\" sound name \"Glass\""};
			try {
				Thread.sleep(500);
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
