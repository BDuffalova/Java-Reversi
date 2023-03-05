package sk.stuba.fei.uim.oop.hraci;

import sk.stuba.fei.uim.oop.hra.Policko;

import java.util.ArrayList;

public class Hrac extends UcastnikHry {
    public Hrac() {
        super(1,"Hrac");
    }

    @Override
    public void resetujSkore() {
        super.resetujSkore();
    }

    @Override
    public void vymenKamene(ArrayList<ArrayList<Policko>> pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        super.vymenKamene(pole, x, y, ja, protivnik);
    }
}
