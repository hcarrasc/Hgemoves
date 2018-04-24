package com.hcarrasko.hgemovs;

import java.awt.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        System.setProperty("apple.awt.UIElement", "true");

        if (SystemTray.isSupported()) {
            SetupApp initApp = new SetupApp();
            initApp.initSystemTray();
        }

    }
}
