package com.hcarrasko.hgemovs;

import com.hcarrasko.dataManager.TimerUpdater;

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

		MenuItem getUserDataItem = new MenuItem("Update...");
		getUserDataItem.addActionListener(actions.getUserDataListener);
		popup.add(getUserDataItem);
		
		MenuItem aboutItem = new MenuItem("About this app");
		popup.add(aboutItem);

		MenuItem configure = new MenuItem("Configure");
        configure.addActionListener(actions.configure);
		popup.add(configure);
		
		popup.addSeparator();
		MenuItem quitItem = new MenuItem("Quit");
		quitItem.addActionListener(actions.quitListener);
		popup.add(quitItem);
		
		trayIcon = new TrayIcon(image.getScaledInstance(20, 17, 10), "Chess.com it's your turn?", popup);
		trayIcon.setToolTip("Stack Overflow Stats");
		// set the TrayIcon properties

		try {
		    tray.add(trayIcon);
		} catch (AWTException e) {
		    System.err.println(e);
		}

		return true;
	}

}
