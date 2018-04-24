package com.hcarrasko.hgemovs;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.hcarrasko.dataManager.ChessDotComDataManager;
import org.apache.log4j.Logger;

import javax.swing.*;


public class ActionsManager {
	
	final static Logger logger = Logger.getLogger(ActionsManager.class);
    private String chessDotComUserName;
    ChessDotComDataManager dataManager = new ChessDotComDataManager();

	// create a action listener to listen for default action executed on the tray icon
	ActionListener getUserDataListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			logger.info("UPDATING DATA FROM MENU: ...");
            try {
                dataManager.getData(getChessDotComUserName());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	};

	ActionListener quitListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			logger.info(" - Killing app : done");
			quitApp();
		}
	};

	ActionListener configure = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    logger.info(" - Configure app  : started");
            String selection = JOptionPane.showInputDialog( "Who are you in chess.com?");
            logger.info("I'm "+selection);
            setChessDotComUserName(selection);
		}
	};
	
	ActionListener accountingWebsiteListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			try {
				java.awt.Desktop.getDesktop().browse(new URI("http://www.chess.com"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	};

	private void quitApp() {
		System.exit(0);
	}


    public String getChessDotComUserName() {
        return chessDotComUserName;
    }

    public void setChessDotComUserName(String chessDotComUserName) {
        this.chessDotComUserName = chessDotComUserName;
    }
}
