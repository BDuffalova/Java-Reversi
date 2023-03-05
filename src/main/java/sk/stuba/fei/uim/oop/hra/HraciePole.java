package sk.stuba.fei.uim.oop.hra;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class HraciePole extends JPanel {
    public static final int VELKOST_POLICKA = 50;
    private final ArrayList<ArrayList <Policko> >pole;
    @Setter
    private int velkost;
    LogikaHry logika;
    private int pocetHratelnych;
    public HraciePole(LogikaHry logika) {
        this.velkost = 6;
        this.logika=logika;
        this.pocetHratelnych=0;
        this.pole = new ArrayList< ArrayList<Policko>>();
        inicializujPole();
        nastavHratelnost(1,2);
    }
    public HraciePole(int velkost,LogikaHry logika) {
        this.velkost = velkost;
        this.pole = new ArrayList< ArrayList<Policko>>();
        this.logika=logika;
        inicializujPole();
        nastavHratelnost(1,2);
    }
    public void inicializujPole(){
        this.addMouseListener(this.logika);
        this.setPreferredSize(new Dimension(VELKOST_POLICKA*this.velkost,VELKOST_POLICKA*this.velkost));
        this.setLayout(new GridLayout(velkost,velkost));
        Policko policko;
        for(int i = 0;i<velkost;i++){
            ArrayList <Policko> radPolicok= new ArrayList<>();
            for(int j = 0; j<velkost;j++){
                if((i==velkost/2-1&&j==velkost/2-1)||(i==velkost/2&&j==velkost/2)){
                    policko = new Policko(1,j,i);
                }
                else if((i==velkost/2-1&&j==velkost/2)||(i==velkost/2&&j==velkost/2-1)){
                    policko = new Policko(2,j,i);
                }
                else{
                    policko = new Policko(0,j,i);
                }
                policko.addMouseListener(logika);
                radPolicok.add(policko);
                this.add(policko);
            }
            this.pole.add(radPolicok);
        }
    }
    public void restart() {
        for (int i = 0; i < velkost; i++) {
            for (int j = 0; j < velkost; j++) {
                if ((i == velkost / 2 - 1 && j == velkost / 2 - 1) || (i == velkost / 2 && j == velkost / 2)) {
                    this.pole.get(i).get(j).setMajitel(1);
                } else if ((i == velkost / 2 - 1 && j == velkost / 2) || (i == velkost / 2 && j == velkost / 2 - 1)) {
                    this.pole.get(i).get(j).setMajitel(2);
                } else {
                    this.pole.get(i).get(j).setMajitel(0);
                }
            }
        }
        nastavHratelnost(1,2);
    }
    private void resetujHratelnost(){
        for(int i = 0;i<this.velkost;i++){
            for(int j = 0;j<this.velkost;j++){
                this.pole.get(i).get(j).setJeHratelne(false);
                this.pole.get(i).get(j).setHorizontalnySmer1(0);
                this.pole.get(i).get(j).setHorizontalnySmer2(0);
                this.pole.get(i).get(j).setVertikalnySmer1(0);
                this.pole.get(i).get(j).setVertikalnySmer2(0);
                this.pole.get(i).get(j).setDiagonalnySmer11(0);
                this.pole.get(i).get(j).setDiagonalnySmer12(0);
                this.pole.get(i).get(j).setDiagonalnySmer21(0);
                this.pole.get(i).get(j).setDiagonalnySmer22(0);
            }
        }
    }

    public void nastavHratelnost(int mojIndex,int superovIndex){
        resetujHratelnost();
        this.pocetHratelnych=0;
        for(int i = 0;i<this.velkost;i++){
            for(int j = 0;j<this.velkost;j++){
                if((skontrolujHratelnost(i,j,mojIndex,superovIndex))&&this.pole.get(i).get(j).getMajitel()==0){
                    this.pole.get(i).get(j).setJeHratelne(true);
                    this.pocetHratelnych++;
                    //this.pole.get(i).get(j).repaint();
                }
            }

        }
        this.repaint();

    }
    private boolean skontrolujHratelnost(int i, int j,int mojIndex,int superovIndex){
        return horizonatlnySmer1(i,j,mojIndex,superovIndex) || horizonatlnySmer2(i,j,mojIndex,superovIndex)
                || vertikalnySmer1(i,j,mojIndex,superovIndex) || vertikalnySmer2(i,j,mojIndex,superovIndex)
                || diagonalnySmer11(i,j,mojIndex,superovIndex) || diagonalnySmer12(i,j,mojIndex,superovIndex)
                ||diagonalnySmer21(i,j,mojIndex,superovIndex) || diagonalnySmer22(i,j,mojIndex,superovIndex);
    }
    private  boolean horizonatlnySmer1(int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int j2= j-1;
        while(j2>=0){
            if (this.pole.get(i).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setHorizontalnySmer1(1);
                }
                break;
            }
            else if (this.pole.get(i).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            j2--;
        }
        return jeHratelne;
    }

    private  boolean horizonatlnySmer2(int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int j2= j+1;
        while(j2<this.velkost){
            if (this.pole.get(i).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setHorizontalnySmer2(1);
                }
                break;
            }
            else if (this.pole.get(i).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            j2++;
        }
        return jeHratelne;
    }

   private  boolean vertikalnySmer1(int i, int j,int mojIndex,int superovIndex){
       boolean zajatieSupera = false;
       boolean jeHratelne = false;
       int i2= i-1;
       while(i2>=0){
           if (this.pole.get(i2).get(j).getMajitel() == mojIndex) {
               if (zajatieSupera) {
                   jeHratelne = true;
                   this.pole.get(i).get(j).setVertikalnySmer1(1);
               }
               break;
           }
           else if (this.pole.get(i2).get(j).getMajitel() == superovIndex) {
               zajatieSupera = true;
           }
           else {
               break;
           }
           i2--;
       }
       return jeHratelne;
   }
    private  boolean vertikalnySmer2(int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int i2= i+1;
        while(i2 < this.velkost){
            if (this.pole.get(i2).get(j).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setVertikalnySmer2(1);
                }
                break;
            }
            else if (this.pole.get(i2).get(j).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            i2++;
        }
        return jeHratelne;
    }

    private boolean diagonalnySmer11 (int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int i2= i-1;
        int j2=j-1;
        while(i2>=0&&j2>=0){
            if (this.pole.get(i2).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setDiagonalnySmer11(1);
                }
                break;
            }
            else if (this.pole.get(i2).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            i2--;
            j2--;
        }
        return jeHratelne;
    }
    private boolean diagonalnySmer12 (int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int i2= i+1;
        int j2=j+1;
        while(i2<this.velkost&&j2<this.velkost){
            if (this.pole.get(i2).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setDiagonalnySmer12(1);
                }
                break;
            }
            else if (this.pole.get(i2).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            i2++;
            j2++;
        }
        return jeHratelne;
    }

    private boolean diagonalnySmer21(int i, int j,int mojIndex,int superovIndex){
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int i2= i-1;
        int j2=j+1;
        while(i2>=0&&j2<this.velkost){
            if (this.pole.get(i2).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setDiagonalnySmer21(1);
                }
                break;
            }
            else if (this.pole.get(i2).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            }
            else {
                break;
            }
            i2--;
            j2++;
        }
        return jeHratelne;
    }
    private boolean diagonalnySmer22(int i, int j,int mojIndex,int superovIndex) {
        boolean zajatieSupera = false;
        boolean jeHratelne = false;
        int i2 = i + 1;
        int j2 = j - 1;
        while (i2 < this.velkost && j2 >= 0) {
            if (this.pole.get(i2).get(j2).getMajitel() == mojIndex) {
                if (zajatieSupera) {
                    jeHratelne = true;
                    this.pole.get(i).get(j).setDiagonalnySmer22(1);
                }
                break;
            } else if (this.pole.get(i2).get(j2).getMajitel() == superovIndex) {
                zajatieSupera = true;
            } else {
                break;
            }
            i2++;
            j2--;
        }
        return jeHratelne;
    }

}
