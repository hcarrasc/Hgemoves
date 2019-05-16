package com.hcarrasko.dataManager;

import com.google.gson.Gson;
import com.hcarrasko.hgemovs.ActionsManager;
import com.hcarrasko.hgemovs.SetupApp;
import com.hcarrasko.httpManager.HTTPConnection;
import com.hcarrasko.model.GamesDTO;
import com.hcarrasko.model.StatsDTO;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
        
        requestType = "GAMES";
        jsonData = requestData(user,requestType);
        GamesDTO games = new Gson().fromJson(jsonData, GamesDTO.class);
        int availableMoves = games.getGames().size();
        
        
        if(SetupApp.firstSetup) {
        		SetupApp.popup.getItem(0).setLabel("Update...");
        		SetupApp.popup.insertSeparator(1);
        		SetupApp.popup.insert("DAILY ELO:     "+stats.getChess_daily().getLast().getRating()+
        				" [ "+stats.getChess_daily().getRecord().getWin()+"-"
        				    +stats.getChess_daily().getRecord().getLoss()+"-"
        				    +stats.getChess_daily().getRecord().getDraw()
        				    +" ]", 
        				    2);
        		SetupApp.popup.insert("BULLET ELO:  "+stats.getChess_bullet().getLast().getRating()+
        				" [ "+stats.getChess_bullet().getRecord().getWin()+"-"
        				    +stats.getChess_bullet().getRecord().getLoss()+"-"
        				    +stats.getChess_bullet().getRecord().getDraw()
        				    +" ]", 
        				    3);
        		SetupApp.popup.insert("BLITZ ELO:     "+stats.getChess_blitz().getLast().getRating()+
        				" [ "+stats.getChess_blitz().getRecord().getWin()+"-"
        				    +stats.getChess_blitz().getRecord().getLoss()+"-"
        				    +stats.getChess_blitz().getRecord().getDraw()
        				    +" ]", 
        				    4); 
        		SetupApp.popup.insert("BLITZ RAPID: "+stats.getChess_rapid().getLast().getRating()+
        				" [ "+stats.getChess_rapid().getRecord().getWin()+"-"
        				    +stats.getChess_rapid().getRecord().getLoss()+"-"
        				    +stats.getChess_rapid().getRecord().getDraw()
        				    +" ]", 
        				    5);  
        		int victories = stats.getChess_daily().getRecord().getWin() +
        				        stats.getChess_bullet().getRecord().getWin() +
        				        stats.getChess_blitz().getRecord().getWin() +
        				        stats.getChess_rapid().getRecord().getWin();
        		
        		int loses = stats.getChess_daily().getRecord().getLoss() +
				        stats.getChess_bullet().getRecord().getLoss() +
				        stats.getChess_blitz().getRecord().getLoss() +
				        stats.getChess_rapid().getRecord().getLoss();
        		
        		int draws = stats.getChess_daily().getRecord().getDraw()+
				        stats.getChess_bullet().getRecord().getDraw() +
				        stats.getChess_blitz().getRecord().getDraw() +
				        stats.getChess_rapid().getRecord().getDraw();
        		
        		int totalGamesPlayed = victories + loses + draws;
        		
        		float percentWin =  (float) (victories * 100) / totalGamesPlayed;
        		percentWin = Math.round(percentWin * 100) / 100f;
        		float percentLose = (float) (loses * 100) / totalGamesPlayed;
        		percentLose = Math.round(percentLose * 100) / 100f;
        		float percentDraw = (float) (draws * 100) / totalGamesPlayed;
        		percentDraw = Math.round(percentDraw * 100) / 100f;
        		
        		logger.info(percentWin);
        		logger.info(percentLose);
        		logger.info(percentDraw);
        		
        		SetupApp.popup.insert("               ",6);
        		SetupApp.popup.insert("DAILY GAMES WAITING: "+availableMoves,7);
        		SetupApp.popup.insert("               ",8);
        		SetupApp.popup.insert("TOTAL GAMES:  "+ totalGamesPlayed+" [ "+victories+"-"+loses+"-"+draws+" ]",9);
        		SetupApp.popup.insert("W : "+percentWin+"%  -  L : "+percentLose+"%  -  D : "+percentDraw+"%",10);
        		
        		SetupApp.firstSetup = false;
        		SetupApp.popup.getItem(7).addActionListener(goToDailyChessListener);
        }
        else {
	        	SetupApp.popup.getItem(2).setLabel("DAILY ELO:        "+stats.getChess_daily().getLast().getRating()+
	    				" [ "+stats.getChess_daily().getRecord().getWin()+"-"
	    				    +stats.getChess_daily().getRecord().getLoss()+"-"
	    				    +stats.getChess_daily().getRecord().getDraw()
	    				    +" ]");
	    		SetupApp.popup.getItem(3).setLabel("BULLET ELO:     "+stats.getChess_bullet().getLast().getRating()+
	    				" [ "+stats.getChess_bullet().getRecord().getWin()+"-"
	    				    +stats.getChess_bullet().getRecord().getLoss()+"-"
	    				    +stats.getChess_bullet().getRecord().getDraw()
	    				    +" ]");
	    		SetupApp.popup.getItem(4).setLabel("BLITZ ELO:        "+stats.getChess_blitz().getLast().getRating()+
	    				" [ "+stats.getChess_blitz().getRecord().getWin()+"-"
	    				    +stats.getChess_blitz().getRecord().getLoss()+"-"
	    				    +stats.getChess_blitz().getRecord().getDraw()
	    				    +" ]");
	    		SetupApp.popup.getItem(5).setLabel("BLITZ RAPID ELO: "+stats.getChess_rapid().getLast().getRating()+
        				" [ "+stats.getChess_rapid().getRecord().getWin()+"-"
        				    +stats.getChess_rapid().getRecord().getLoss()+"-"
        				    +stats.getChess_rapid().getRecord().getDraw()
        				    +" ]"); 
        		int victories = stats.getChess_daily().getRecord().getWin() +
				        stats.getChess_bullet().getRecord().getWin() +
				        stats.getChess_blitz().getRecord().getWin() +
				        stats.getChess_rapid().getRecord().getWin();
		
			int loses = stats.getChess_daily().getRecord().getLoss() +
			        stats.getChess_bullet().getRecord().getLoss() +
			        stats.getChess_blitz().getRecord().getLoss() +
			        stats.getChess_rapid().getRecord().getLoss();
			
			int draws = stats.getChess_daily().getRecord().getDraw()+
			        stats.getChess_bullet().getRecord().getDraw() +
			        stats.getChess_blitz().getRecord().getDraw() +
			        stats.getChess_rapid().getRecord().getDraw();
			
			int totalGamesPlayed = victories + loses + draws;
			
			float percentWin =  (float) (victories * 100) / totalGamesPlayed;
			percentWin = Math.round(percentWin * 100) / 100f;
			float percentLose = (float) (loses * 100) / totalGamesPlayed;
			percentLose = Math.round(percentLose * 100) / 100f;
			float percentDraw = (float) (draws * 100) / totalGamesPlayed;
			percentDraw = Math.round(percentDraw * 100) / 100f;
			
			logger.info(percentWin);
			logger.info(percentLose);
			logger.info(percentDraw);
    		SetupApp.popup.getItem(7).setLabel("DAILY GAMES WAITING: "+availableMoves);
			SetupApp.popup.getItem(9).setLabel("TOTAL GAMES:  "+ totalGamesPlayed+" [ "+victories+"-"+loses+"-"+draws+" ]");
			SetupApp.popup.getItem(10).setLabel("W : "+percentWin+"%  -  L : "+percentLose+"%  -  D : "+percentDraw+"%");
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
	
	ActionListener goToDailyChessListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				java.awt.Desktop.getDesktop().browse(new URI("https://www.chess.com/daily"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
        }
	};

}
