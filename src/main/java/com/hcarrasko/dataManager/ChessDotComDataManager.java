package com.hcarrasko.dataManager;

import com.google.gson.Gson;
import com.hcarrasko.hgemovs.SetupApp;
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
        
        if(SetupApp.firstSetup) {
        		SetupApp.popup.getItem(0).setLabel("Update...");
        		SetupApp.popup.insertSeparator(1);
        		SetupApp.popup.insert("DAILY ELO: "+stats.getChess_daily().getLast().getRating()+
        				" [ "+stats.getChess_daily().getRecord().getWin()+"-"
        				    +stats.getChess_daily().getRecord().getLoss()+"-"
        				    +stats.getChess_daily().getRecord().getDraw()
        				    +" ]", 
        				    2);
        		SetupApp.popup.insert("BULLET ELO: "+stats.getChess_bullet().getLast().getRating()+
        				" [ "+stats.getChess_bullet().getRecord().getWin()+"-"
        				    +stats.getChess_bullet().getRecord().getLoss()+"-"
        				    +stats.getChess_bullet().getRecord().getDraw()
        				    +" ]", 
        				    3);
        		SetupApp.popup.insert("BLITZ ELO: "+stats.getChess_blitz().getLast().getRating()+
        				" [ "+stats.getChess_blitz().getRecord().getWin()+"-"
        				    +stats.getChess_blitz().getRecord().getLoss()+"-"
        				    +stats.getChess_blitz().getRecord().getDraw()
        				    +" ]", 
        				    4);
        		SetupApp.popup.insertSeparator(5);
        		
        		SetupApp.popup.insert("Go to chess.com",6);
        		SetupApp.popup.insert("Go to chess.com/live",7);
        		SetupApp.popup.insert("Go to chess.com/clubs/home",8);
        		
        		SetupApp.popup.insertSeparator(9);
        		
        		SetupApp.popup.insert("Go to chess-results.com",10);
        		SetupApp.popup.insert("Go to torneos ENF",11);
        		//https://www.ajefech.cl/ENF/torneosENF.php
        		
        		SetupApp.firstSetup = false;	
        }
        else {
	        	SetupApp.popup.getItem(2).setLabel("DAILY ELO: "+stats.getChess_daily().getLast().getRating()+
	    				" [ "+stats.getChess_daily().getRecord().getWin()+"-"
	    				    +stats.getChess_daily().getRecord().getLoss()+"-"
	    				    +stats.getChess_daily().getRecord().getDraw()
	    				    +" ]");
	    		SetupApp.popup.getItem(3).setLabel("BULLET ELO: "+stats.getChess_bullet().getLast().getRating()+
	    				" [ "+stats.getChess_bullet().getRecord().getWin()+"-"
	    				    +stats.getChess_bullet().getRecord().getLoss()+"-"
	    				    +stats.getChess_bullet().getRecord().getDraw()
	    				    +" ]");
	    		SetupApp.popup.getItem(4).setLabel("BLITZ ELO: "+stats.getChess_blitz().getLast().getRating()+
	    				" [ "+stats.getChess_blitz().getRecord().getWin()+"-"
	    				    +stats.getChess_blitz().getRecord().getLoss()+"-"
	    				    +stats.getChess_blitz().getRecord().getDraw()
	    				    +" ]");
        }
       
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
