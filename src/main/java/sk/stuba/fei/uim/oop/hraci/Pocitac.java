package sk.stuba.fei.uim.oop.hraci;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.hra.Policko;

import java.util.ArrayList;

@Getter
@Setter
public class Pocitac extends UcastnikHry {
    private int najlepsiTahX;
    private int najlepsiTahY;

    public Pocitac() {
        super(2,"Pocitac");
        this.najlepsiTahX=0;
        this.najlepsiTahY=0;
    }
    public void resetujSkore() {
        super.resetujSkore();
    }

    @Override
    public void vymenKamene(ArrayList<ArrayList<Policko>> pole, int x, int y, UcastnikHry ja, UcastnikHry protivnik) {
        super.vymenKamene(pole, x, y, ja, protivnik);
    }

    public void najdiNajlepsiTah(ArrayList<ArrayList<Policko>>pole, int velkostPola){
        int maxPocet = 0;
        int spocitanyPocet;
        for(int i=0;i<velkostPola;i++){
            for(int j = 0; j<velkostPola;j++){
                if(pole.get(i).get(j).isJeHratelne()&&pole.get(i).get(j).getMajitel()==0){
                    spocitanyPocet = spocitajPrebrateKamene(pole,j,i);
                    if(spocitanyPocet>maxPocet){
                        maxPocet=spocitanyPocet;
                        this.setNajlepsiTahX(j);
                        this.setNajlepsiTahY(i);
                    }
                }
            }
        }
    }

    private int spocitajPrebrateKamene(ArrayList<ArrayList<Policko>>pole,int x, int y){
        int pocet = 0;
        int pozX = x;
        int pozY = y;
        if(pole.get(y).get(x).getHorizontalnySmer1()==1){
            pozX--;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozX--;
            }
            pozX=x;
        }
        if(pole.get(y).get(x).getHorizontalnySmer2()==1){
            pozX++;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozX++;
            }
            pozX=x;
        }
        if(pole.get(y).get(x).getVertikalnySmer1()==1){
           pozY--;
           while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY--;
           }
            pozY=y;
        }
        if(pole.get(y).get(x).getVertikalnySmer2()==1){
            pozY++;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY++;
            }
            pozY=y;
        }
        if(pole.get(y).get(x).getDiagonalnySmer11()==1){
            pozY--;
            pozX--;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY--;
                pozX--;
            }
            pozY=y;
            pozX=x;
        }
        if(pole.get(y).get(x).getDiagonalnySmer12()==1){
            pozY++;
            pozX++;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY++;
                pozX++;
            }
            pozY=y;
            pozX=x;
        }
        if(pole.get(y).get(x).getDiagonalnySmer21()==1){
            pozY--;
            pozX++;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY--;
                pozX++;
            }
            pozY=y;
            pozX=x;
        }
        if(pole.get(y).get(x).getDiagonalnySmer22()==1){
            pozY++;
            pozX--;
            while(pole.get(pozY).get(pozX).getMajitel()!=2){
                pocet++;
                pozY++;
                pozX--;
            }
        }
        return pocet;
    }
}
