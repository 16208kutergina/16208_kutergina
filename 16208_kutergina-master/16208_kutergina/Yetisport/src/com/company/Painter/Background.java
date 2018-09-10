package com.company.Painter;

import com.company.LogicGame.Constants;
import com.company.LogicGame.Game;
import com.company.LogicGame.implementation.Game_impl;
import com.company.LogicGame.implementation.Level;
import com.company.Menu;
import com.company.Painter.implementanion.Penguin_impl;
import com.company.Painter.implementanion.StartSpeed;
import com.company.Painter.implementanion.Wall;
import com.company.Painter.implementanion.Yeti_impl;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import static java.lang.Math.abs;
import static java.lang.Math.signum;


public class Background extends JPanel  {

    public BGMouseListener getMouseListener() {
        return mouseListener;
    }

    public Background(Level level, Menu menu, JFrame frame) {
        try {
            this.parent = frame;
            this.menu = menu;
            this.level = level;
            mainTimer = new Timer(true);
            fontText = new Font("Bangkok", 1, 25);
            background = ImageIO.read(new File("src/images/background.png"));
            mountain = ImageIO.read(new File("src/images/mountain.png"));
            mouseListener = new BGMouseListener();
            keyListener = new BGKeyListener();
            record = 0;
            clipImpact = AudioSystem.getClip();
            String audioImpact = "src/audio/impact.wav";
            clipImpact.open(AudioSystem.getAudioInputStream(new File(audioImpact)));
            clipStart = AudioSystem.getClip();
            String audioStart = "src/audio/start.wav";
            clipStart.open(AudioSystem.getAudioInputStream(new File(audioStart)));
            restart();
            mainTimer.scheduleAtFixedRate(timerTask, 1, 2);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage subImg = background.getSubimage(displBackground, 104, 750, 450);
        g.drawImage(subImg, 0, 0, 750, 450, null);
        g.drawImage(mountain, 510 + (2250 - displBackground), 80, 200, 400, null);
        g.drawImage(wall.invert_mountain, (int) ((int)game.getObstacleInvertCoord().getX() + (2250 - displBackground - signum(abs(displBackground - 2250))*Constants.widthObstacle)),0,(int)Constants.widthObstacle,(int) game.getObstacleInvertCoord().getY(),null);
        g.drawImage(wall.norm_mountain, (int) (game.getObstacleNormCoord().getX() + (2250 - displBackground - signum(abs(displBackground - 2250))*Constants.widthObstacle)), (int)game.getObstacleNormCoord().getY() + 30,(int) Constants.widthObstacle,480 - (int)game.getObstacleNormCoord().getY()-30,null);
        g.drawImage(yeti.getCurrent(), 540 + (2250 - displBackground), 330, 140, 150, null);
        if(numberClicked == 2) {
            g.drawImage(startSpeed.lightSquare, 510, 300, 50, (int)Constants.maxPower/2, null);
            g.drawImage(startSpeed.darkSquare, 510, (int) (300 + (Constants.maxPower/2 - game.getPower() / 2)), 50, (int) game.getPower() / 2, null);
        }
        g.drawImage(penguin.getImg(), (int) currentPenguinX, (int) game.getCurrentCoord().getY(), 60, 60, null);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(fontText);
        g.drawString("The current distance: " + String.format("%.2f",currentDistance),30,40);
        g.drawString("The record: " + String.format("%.2f",record), 30, 70);
    }

    public void actions(){
        if(numberClicked == 0) {
            game.beforeTime();//горизонтально
        }
        if(numberClicked == 1) {
            game.fistTime(); //вертикально
        }
        if(numberClicked == 2){
            game.secondTime();//установка скорости
        }
        if(numberClicked == 3){
            game.thirdTime(); // пауза
        }
        if(numberClicked == 4) {
            yeti.nextImageOneClick();
            penguin.imgDrop();
            game.fourthTime();
            if(game.getCurSpeed() <= 0 ){
                JOptionPane.showMessageDialog(this, "The result: " +String.format("%.2f", currentDistance), "Result",1);
                if (currentDistance > record) {
                    record = currentDistance;
                }
                restart();
            }
        }
        if(numberClicked == 5){
            clipImpact.start();
            game.fifthTime();
            numberClicked++;
        }
        if(numberClicked > 5) {
            yeti.nextImageTwoClick();
            game.remaneTime();
            penguin.direction(game.getCurrentCoord(), game.getCurSpeed());
            if(game.getCurSpeed() > 0){
                double cur = game.getCurrentCoord().getX();
                currentDistance = (((Constants.startX - cur) / Constants.scale ));
                if(cur > 300){
                    currentPenguinX = cur;
                }
                else{
                    int disp = 750 - 400 - (int)cur;
                    displBackground = 2250 - disp;
                }
            }else{
                clipImpact.stop();
                JOptionPane.showMessageDialog(this, "The result: " +String.format("%.2f", currentDistance), "Result",1);
                if(currentDistance > record){
                    record = currentDistance;
                }
                restart();
            }
        }
        repaint();
    }

    private void restart(){
        startSpeed = new StartSpeed();
        wall = new Wall();
        yeti = new Yeti_impl();
        penguin = new Penguin_impl();
        numberClicked = 0;
        displBackground = 2250;
        game = new Game_impl(level);
        currentPenguinX = (int) game.getCurrentCoord().getX();
        currentDistance = 0;
        clipImpact.setMicrosecondPosition(0);
        clipStart.setMicrosecondPosition(0);
        clipStart.start();
    }

    public BGKeyListener getKeyListener() {
        return keyListener;
    }

    private class BGKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                mainTimer.cancel();
                menu.killFrame(parent);
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                restart();
            }
                numberClicked++;
        }
    }

    private class BGMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            numberClicked++;
        }
    }

    private BGMouseListener mouseListener;
    private BGKeyListener keyListener;
    private BufferedImage background;
    private BufferedImage mountain;
    private Font fontText;
    private double record;
    private Clip clipStart;
    private Clip clipImpact;
    private Level level;
    private Menu menu;
    private JFrame parent;
    private Timer mainTimer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            actions();
        }
    };

    private Yeti yeti;
    private Penguin penguin;
    private Wall wall;
    private StartSpeed startSpeed;
    private Game game;
    private int displBackground;
    private double currentPenguinX;
    private double currentDistance;
    private int numberClicked;
}
