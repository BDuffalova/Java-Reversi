package sk.stuba.fei.uim.oop.hra;

import javax.swing.*;
import java.awt.*;

public class Hra{
    public Hra(){
        //super();
        JFrame okno = new JFrame("Reversi");
        JPanel menu=new JPanel();
        LogikaHry logika = new LogikaHry(okno,menu);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //okno.setLayout(new GridLayout(1,2));
        okno.setLayout(new FlowLayout());
        okno.setMinimumSize(new Dimension(logika.getPole().getVelkost()*HraciePole.VELKOST_POLICKA+150,logika.getPole().getVelkost()*HraciePole.VELKOST_POLICKA+230));
        okno.addKeyListener(logika);
        okno.setResizable(false);
        menu.setBackground(Color.PINK);
        menu.setLayout(new GridLayout(3,1));
        menu.add(logika.getInfo());
        JButton reset = new JButton("Reset");
        reset.addActionListener(logika);
        reset.setFocusable(false);
        menu.add(reset);
        JSlider velkost = new JSlider(6,12,6);
        velkost.setMajorTickSpacing(2);
        velkost.setPaintTicks(true);
        velkost.setPaintLabels(true);
        velkost.setSnapToTicks(true);
        velkost.setFocusable(false);
        velkost.addChangeListener(logika);
        menu.add(velkost);
        menu.setPreferredSize(new Dimension(logika.getPole().getVelkost()*HraciePole.VELKOST_POLICKA+130,150));
        menu.setFocusable(false);
        okno.add(menu);
        okno.add(logika.getPole());
        okno.setVisible(true);
    }
}
