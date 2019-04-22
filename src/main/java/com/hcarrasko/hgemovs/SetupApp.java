package com.hcarrasko.hgemovs;

import javax.swing.*;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class SetupApp {
	
	ActionsManager actions = new ActionsManager();
	static public PopupMenu popup = new PopupMenu();
	static public TrayIcon trayIcon = null;
	static public boolean firstSetup = true; 
	
	public boolean initSystemTray() {

        String selection = JOptionPane.showInputDialog( "Who are you in chess.com?");
        actions.setChessDotComUserName(selection);
		
		//TimerUpdater timer = new TimerUpdater();
		//timer.updateDataRobot(selection);
				
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(SetupApp.class.getResource("/WhiteKnight.png"));

		MenuItem getUserDataItem = new MenuItem("Update ...");
		getUserDataItem.addActionListener(actions.getUserDataListener);
		popup.add(getUserDataItem);
		
		popup.insertSeparator(1);
		popup.insert("Go to chess.com",2);
		popup.insert("Go to chess.com/live",3);
		popup.insert("Go to chess.com/clubs/home",4);
		popup.insertSeparator(5);
		popup.insert("Go to chess-results.com",6);
		popup.insert("Go to torneos ENF",7);
		popup.insertSeparator(8);
		popup.insert("About this app",9);
		
		popup.getItem(2).addActionListener(actions.goToChessListener); 
		popup.getItem(3).addActionListener(actions.gotToChessLiveListener);
		popup.getItem(4).addActionListener(actions.gotToChessClubsListener);
		popup.getItem(6).addActionListener(actions.gotToChessResultsListener);
		popup.getItem(7).addActionListener(actions.gotToChessTournamentsListener);
		popup.getItem(9).addActionListener(actions.goToAboutAppListener);

		MenuItem configure = new MenuItem("Set user");
        configure.addActionListener(actions.configureListener);
		popup.add(configure);
		
		popup.addSeparator();
		MenuItem quitItem = new MenuItem("Quit");
		quitItem.addActionListener(actions.quitListener);
		popup.add(quitItem);
		
		trayIcon = new TrayIcon(image.getScaledInstance(20, 17, 10), "Chess", popup);
		trayIcon.setToolTip("with chess forget it");

		try {
		    tray.add(trayIcon);
		} catch (AWTException e) {
		    System.err.println(e);
		}

		return true;
	}

}
