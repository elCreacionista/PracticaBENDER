import javax.swing.*;
import java.awt.*;

public class Interfeis extends JFrame {

    Interfeis(Mapa mapa, Robot bot){
        new JFrame("Test14");
        Container panel = new Container();
        JLabel[][] labels = new JLabel[mapa.map.length][];
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        for (int i = 0; i <mapa.map.length ; i++) {
            labels[i] = new JLabel[mapa.map[i].length];
            for (int j = 0; j <labels[i].length ; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                labels[i][j].setBounds(j*50,i*50,50,50);
                if (bot.posicion.equals(new Point(i,j))) {
                    labels[i][j].setBackground(new Color(150, 150, 150));
                    labels[i][j].setText("BOT");
                }
                    else
                switch (mapa.map[i][j].toString()){
                    case "#": labels[i][j].setBackground(new Color(0,0,0)); break;
                    case " ": labels[i][j].setBackground(new Color(200,200,200));break;
                    case "I": labels[i][j].setBackground(new Color(100,200,200));labels[i][j].setText("Inversor");break;
                    case "T": labels[i][j].setBackground(new Color(250,100,50));labels[i][j].setText("Teleportador");break;
                    case "$": labels[i][j].setBackground(new Color(200,250,0));labels[i][j].setText("Fi");break;
                    default: labels[i][j].setBackground(new Color(255,255,255));labels[i][j].setText("Fi");break;
                }
                panel.add(labels[i][j]);
            }
        }
        setVisible(true);
    }
}
