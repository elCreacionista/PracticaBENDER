import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfeis extends JFrame {


    JLabel[][] labels;
    Container panel;
    JButton boton;
    JButton boton2;
    Interfeis(Bender bender){
        setLocationRelativeTo(null);
        panel = new Container();
        boton = new JButton();
        boton.setText("Run");
        add(boton);
        boton.setBounds(350,400,100,30);
        boton.addActionListener(actionEvent -> {
            if (!(bender.map.map[bender.bot.posicion.x][bender.bot.posicion.y] instanceof Fi )) {
                bender.unPaso();
                actualizarVista(bender);
            }
        });
        boton2 = new JButton();
        boton2.setText("BestRun");
        add(boton2);
        boton2.setBounds(350,350,100,30);
        boton2.addActionListener(actionEvent -> {
            if (!(bender.map.map[bender.bot.posicion.x][bender.bot.posicion.y] instanceof Fi )) {
                bender.PasoInteligente();
                actualizarVista(bender);
            }
        });
        panel.setLocation(20,20);
        labels = new JLabel[bender.map.map.length][];
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        for (int i = 0; i <bender.map.map.length ; i++) {
            labels[i] = new JLabel[bender.map.map[i].length];
            for (int j = 0; j <labels[i].length ; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                labels[i][j].setBounds(j*20,i*20,20,20);
                if (bender.bot.posicion.equals(new Point(i,j))) {
                    labels[i][j].setBackground(new Color(150, 150, 150));
                    labels[i][j].setText("X");
                }
                    else
                switch (bender.map.map[i][j].toString()){
                    case "#": labels[i][j].setBackground(new Color(0,0,0)); break;
                    case " ": labels[i][j].setBackground(new Color(200,200,200));break;
                    case "I": labels[i][j].setBackground(new Color(100,200,200));labels[i][j].setText("I");break;
                    case "T": labels[i][j].setBackground(new Color(250,100,50));labels[i][j].setText("T");break;
                    case "$": labels[i][j].setBackground(new Color(200,250,0));labels[i][j].setText("$");break;
                    default: labels[i][j].setBackground(new Color(255,255,255));labels[i][j].setText("Error");break;
                }
                    labels[i][j].setText(bender.map.map[i][j].distancia + "");
                    panel.add(labels[i][j]);
            }
        }
        setVisible(true);
    }
    public void actualizarVista(Bender bender) {
        for (int i = 0; i < bender.map.map.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {

                if (bender.bot.posicion.equals(new Point(i, j))) {
                    labels[i][j].setBackground(new Color(150, 150, 150));
                    labels[i][j].setText("X");
                } else
                    switch (bender.map.map[i][j].toString()) {
                        case "#":
                            labels[i][j].setBackground(new Color(0, 0, 0));
                            break;
                        case " ":
                            labels[i][j].setBackground(new Color(200, 200, 200));
                            break;
                        case "I":
                            labels[i][j].setBackground(new Color(100, 200, 200));
                            labels[i][j].setText("I");
                            break;
                        case "T":
                            labels[i][j].setBackground(new Color(250, 100, 50));
                            labels[i][j].setText("T");
                            break;
                        case "$":
                            labels[i][j].setBackground(new Color(200, 250, 0));
                            labels[i][j].setText("$");
                            break;
                        default:
                            labels[i][j].setBackground(new Color(255, 255, 255));
                            labels[i][j].setText("Error");
                            break;
                    }
                if (bender.map.map[i][j].getDistancia() > 100)
                    labels[i][j].setText("-");
                else
                    labels[i][j].setText(bender.map.map[i][j].distancia + "");
            }
        }
    }
}
