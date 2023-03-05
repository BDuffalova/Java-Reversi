package sk.stuba.fei.uim.oop.hraci;

import lombok.Getter;
import sk.stuba.fei.uim.oop.hra.Policko;

import java.util.ArrayList;

@Getter
public class UcastnikHry {
    private int skore;
    private final int indexHraca;
    private final String meno;

    public UcastnikHry(int indexHraca,String meno) {
        this.skore = 2;
        this.indexHraca=indexHraca;
        this.meno=meno;
    }

    public int getSkore() {
        return skore;
    }
    public void resetujSkore() {
        this.skore=2;
    }
    public void uberSkore(int pocet) {
        this.skore -= pocet;
    }
    public void pridajSkore(int pocet) {
        this.skore += pocet;
    }

    protected void vymenKamene(ArrayList<ArrayList<Policko>> pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik){
        if(pole.get(y).get(x).getHorizontalnySmer1()==1){
            this.vymenKameneH1(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getHorizontalnySmer2()==1){
            this.vymenKameneH2(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getVertikalnySmer1()==1){
           this.vymenKameneV1(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getVertikalnySmer2()==1){
           this.vymenKameneV2(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getDiagonalnySmer11()==1){
            this.vymenKameneD11(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getDiagonalnySmer12()==1){
            this.vymenKameneD12(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getDiagonalnySmer21()==1){
            this.vymenKameneD21(pole,x,y,ja,protivnik);
        }
        if(pole.get(y).get(x).getDiagonalnySmer22()==1){
            this.vymenKameneD22(pole,x,y,ja,protivnik);
        }
        pole.get(y).get(x).setJeHratelne(false);
        pole.get(y).get(x).setMajitel(ja.getIndexHraca());
        pole.get(y).get(x).repaint();
    }

    private void vymenKameneH1(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik){
        int pocet=0;
        x--;
        while(pole.get(y).get(x).getMajitel()!=ja.getIndexHraca()){
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            x--;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneH2(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        x++;
        while(pole.get(y).get(x).getMajitel()!=ja.getIndexHraca()){
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            x++;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneV1(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y--;
        while(pole.get(y).get(x).getMajitel()!=ja.getIndexHraca()){
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y--;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneV2(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y++;
        while(pole.get(y).get(x).getMajitel()!=ja.getIndexHraca()){
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y++;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneD11(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y--;
        x--;
        while (pole.get(y).get(x).getMajitel() != ja.getIndexHraca()) {
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y--;
            x--;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneD12(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y++;
        x++;
        while (pole.get(y).get(x).getMajitel() != ja.getIndexHraca()) {
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y++;
            x++;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneD21(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y--;
        x++;
        while (pole.get(y).get(x).getMajitel() != ja.getIndexHraca()) {
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y--;
            x++;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
    private void vymenKameneD22(ArrayList<ArrayList<Policko>>pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        int pocet = 0;
        y++;
        x--;
        while (pole.get(y).get(x).getMajitel() != ja.getIndexHraca()) {
            pole.get(y).get(x).setMajitel(ja.getIndexHraca());
            pole.get(y).get(x).repaint();
            y++;
            x--;
            pocet++;
        }
        protivnik.uberSkore(pocet);
        ja.pridajSkore(pocet+1);
    }
}
