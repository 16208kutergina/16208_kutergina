package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppStarter starter = new AppStarter();
        SwingUtilities.invokeLater( starter );
    }
}

class AppStarter extends Thread{
    public void run(){
        JFrame window = new Menu();
    }
}
