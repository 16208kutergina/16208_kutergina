package com.company;

import com.company.LogicGame.implementation.Level;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Menu extends JFrame implements ActionListener {
    private JButton easy = new JButton("Easy");
    private JButton norm = new JButton("Norm");
    private JButton hard = new JButton("Hard");
    private Window game;
    private Level complexity;
    private JFrame f;
    private Vector<Pair<String, Double>> easyTable;
    private Vector<Pair<String, Double>> normTable;
    private Vector<Pair<String, Double>> hardTable;


    public Menu(){
        f = new JFrame("Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(750, 480);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(10,36,54));
        easy.setForeground(Color.BLACK);
        easy.setActionCommand("easy");
        norm.setForeground(Color.BLACK);
        norm.setActionCommand("norm");
        hard.setForeground(Color.BLACK);

        easy.setBackground(Color.LIGHT_GRAY);
        norm.setBackground(Color.GRAY);
        hard.setBackground(Color.DARK_GRAY);
        hard.setActionCommand("hard");

        easy.setBounds(50,50,100,30);
        norm.setBounds(150,80, 100,30);
        hard.setBounds(250, 110, 100, 30);

        easy.addActionListener(this);
        norm.addActionListener(this);
        hard.addActionListener(this);



        f.add(easy);
        f.add(norm);
        f.add(hard);

        f.setVisible(true);
    }

    private void createGame(){
        if(game == null) {
            game = new Yetysport(complexity, this);
        }
        f.setVisible(false);
    }

    public void killFrame(JFrame frame){
        frame.dispose();
        game = null;
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("norm")){
            complexity=Level.norm;
        }
        if(e.getActionCommand().contains("easy")){
            complexity=Level.easy;
        }
        if(e.getActionCommand().contains("hard")){
            complexity=Level.hard;
        }
        createGame();
    }
}
