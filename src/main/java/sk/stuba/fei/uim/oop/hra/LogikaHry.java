package sk.stuba.fei.uim.oop.hra;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.hraci.UcastnikHry;
import sk.stuba.fei.uim.oop.UniverzalnyAdapter;
import sk.stuba.fei.uim.oop.hraci.Hrac;
import sk.stuba.fei.uim.oop.hraci.Pocitac;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LogikaHry extends UniverzalnyAdapter {
    private final Hrac hrac;
    private final Pocitac pocitac;
    @Getter
    private HraciePole pole;
    @Getter
    private final JLabel info;
    private final JFrame okno;
    private final JPanel menu;
    @Setter
    private int celkovyPocetPolicok;
    @Getter
    private UcastnikHry aktualnyHrac;
    public LogikaHry(JFrame okno , JPanel menu) {
        this.hrac = new Hrac();
        this.pocitac = new Pocitac();
        this.pole = new HraciePole(this);
        this.aktualnyHrac = hrac;
        this.info = new JLabel("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + "   Vyhral: -" + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "   Na rade: " + this.aktualnyHrac.getMeno(),SwingConstants.CENTER);
        this.celkovyPocetPolicok=this.pole.getVelkost()*this.pole.getVelkost();
        this.okno = okno;
        this.menu = menu;
    }
    private void vyhodnotVyhru(){
        if(this.hrac.getSkore()>this.pocitac.getSkore()){
            this.info.setText("Hrac: " + this.hrac.getSkore() + "   Pocitac: " + this.pocitac.getSkore() + "   Vyhral: " + this.hrac.getMeno() + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost());
        }
        else if(this.hrac.getSkore()<this.pocitac.getSkore()){
            this.info.setText("Hrac: " + this.hrac.getSkore() + "   Pocitac: " + this.pocitac.getSkore() + "   Vyhral: " + this.pocitac.getMeno() + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost());
        }
        else{
            this.info.setText("Hrac: " + this.hrac.getSkore() + "   Pocitac: " + this.pocitac.getSkore() + "   Remiza"+ "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                this.okno.dispose();
                System.exit(0);
                break;
            case KeyEvent.VK_R:
                this.restart();
                break;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.restart();
    }

    private void restart(){
        this.hrac.resetujSkore();
        this.pocitac.resetujSkore();
        this.aktualnyHrac=hrac;
        this.info.setText("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + "   Vyhral: -" + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "   Na rade: " + this.aktualnyHrac.getMeno());
        this.pole.restart();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Policko parent = (Policko) e.getSource();
        if (parent.isJeHratelne()) {
            hrac.vymenKamene(this.pole.getPole(), parent.getPoziciaX(), parent.getPoziciaY(), this.hrac, this.pocitac);
            this.aktualnyHrac=pocitac;
            this.pole.nastavHratelnost(this.pocitac.getIndexHraca(), this.hrac.getIndexHraca());
            if (this.pole.getPocetHratelnych() > 0) {
                System.out.println("Tah pocitaca");
                this.info.setText("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + " Vyhral: -" + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "   Na rade: " + this.aktualnyHrac.getMeno());
                pocitac.najdiNajlepsiTah(this.pole.getPole(), pole.getVelkost());
                pocitac.vymenKamene(this.pole.getPole(), pocitac.getNajlepsiTahX(), pocitac.getNajlepsiTahY(), this.pocitac, this.hrac);
                this.aktualnyHrac=hrac;
                this.info.setText("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + "   Vyhral: -" + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "   Na rade: " + this.aktualnyHrac.getMeno());
                this.pole.nastavHratelnost(this.hrac.getIndexHraca(), this.pocitac.getIndexHraca());
                if (this.pole.getPocetHratelnych() == 0) {
                    this.pole.nastavHratelnost(this.pocitac.getIndexHraca(), this.hrac.getIndexHraca());
                    if (this.pole.getPocetHratelnych() == 0) {
                        this.vyhodnotVyhru();
                    } else {
                        while (this.hrac.getSkore() + this.pocitac.getSkore() < this.celkovyPocetPolicok) {
                            System.out.println("Tah pocitaca");
                            pocitac.najdiNajlepsiTah(this.pole.getPole(), pole.getVelkost());
                            pocitac.vymenKamene(this.pole.getPole(), pocitac.getNajlepsiTahX(), pocitac.getNajlepsiTahY(), this.pocitac, this.hrac);
                            this.pole.nastavHratelnost(this.hrac.getIndexHraca(), this.pocitac.getIndexHraca());
                            if (this.pole.getPocetHratelnych() > 0) {
                                this.aktualnyHrac=hrac;
                                this.info.setText("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + "   Vyhral: -" + "   Velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "   Na rade: " + this.aktualnyHrac.getMeno());
                                break;
                            } else {
                                this.pole.nastavHratelnost(this.pocitac.getIndexHraca(), this.hrac.getIndexHraca());
                                if (this.pole.getPocetHratelnych() == 0) {
                                    break;
                                }
                            }
                        }
                    }
                }

            }

         else{
                this.pole.nastavHratelnost(this.hrac.getIndexHraca(), this.pocitac.getIndexHraca());
                this.aktualnyHrac=hrac;
                this.info.setText("Hrac (biele): " + this.hrac.getSkore() + "   Pocitac (čierne): " + this.pocitac.getSkore() + "   Vyhral: -" + "   velkosť: " + this.pole.getVelkost() + "X" + this.pole.getVelkost() + "  Na rade: " + this.aktualnyHrac.getMeno());
                if (this.pole.getPocetHratelnych() == 0) {
                    this.vyhodnotVyhru();
                }
            }
        }

            if(this.hrac.getSkore()+this.pocitac.getSkore()==this.celkovyPocetPolicok||this.pole.getPocetHratelnych()==0){
                this.vyhodnotVyhru();
            }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        Policko policko = (Policko) e.getSource();
        if(policko.isJeHratelne()){
            policko.setBackground(Color.BLUE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Policko policko = (Policko) e.getSource();
        policko.setBackground(Color.GREEN);
    }
    @Override
    public void stateChanged(ChangeEvent e){
        JSlider nastavitel = (JSlider) e.getSource();
        int velkost = nastavitel.getValue();
        if(velkost==6||velkost==8||velkost==10||velkost==12){
            this.setCelkovyPocetPolicok(velkost*velkost);
            this.okno.remove(this.pole);
            this.pole = new HraciePole(velkost,this);
            this.okno.add(pole);
            this.okno.setSize(velkost*HraciePole.VELKOST_POLICKA+150,velkost*HraciePole.VELKOST_POLICKA+220);
            this.menu.setPreferredSize(new Dimension(velkost*HraciePole.VELKOST_POLICKA+130,150));
            this.okno.setVisible(true);
            this.restart();
        }

    }
}
