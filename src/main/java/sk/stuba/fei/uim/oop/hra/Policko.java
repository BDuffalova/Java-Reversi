package sk.stuba.fei.uim.oop.hra;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Setter @Getter
public class Policko extends JPanel {
    private int majitel;
    final private int poziciaX;
    final private int poziciaY;
    private boolean jeHratelne;
    private int vertikalnySmer1;
    private int vertikalnySmer2;
    private int horizontalnySmer1;
    private int horizontalnySmer2;
    private int diagonalnySmer11;
    private int diagonalnySmer12;
    private int diagonalnySmer21;
    private int diagonalnySmer22;
    public Policko(int majitel,int x, int y) {
        super();

        this.majitel = majitel;
        this.setBackground(Color.GREEN);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.poziciaX=x;
        this.poziciaY=y;
        this.vertikalnySmer1=0;
        this.vertikalnySmer2=0;
        this.horizontalnySmer1=0;
        this.horizontalnySmer2=0;
        this.diagonalnySmer11=0;
        this.diagonalnySmer12=0;
        this.diagonalnySmer21=0;
        this.diagonalnySmer22=0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.majitel==1){
            g.setColor(Color.WHITE);
            g.fillOval(10,10,HraciePole.VELKOST_POLICKA-20,HraciePole.VELKOST_POLICKA-20);
        }
        else if(this.majitel==2){
            g.setColor(Color.BLACK);
            g.fillOval(10,10,HraciePole.VELKOST_POLICKA-20,HraciePole.VELKOST_POLICKA-20);

        }
        else if(this.jeHratelne){
            g.setColor(Color.cyan);
            g.fillOval(10,10,HraciePole.VELKOST_POLICKA-20,HraciePole.VELKOST_POLICKA-20);
        }
        else{
            g.setColor(Color.GREEN);
            g.fillOval(10,10,HraciePole.VELKOST_POLICKA-20,HraciePole.VELKOST_POLICKA-20);
        }
    }
}
