import javax.swing.*;
import java.awt.*;

public class PantallaGrafica extends JFrame {


    JLabel[][] labels;
    JPanel pane;
    Container vista;
    Container botones;
    JButton boton;
    JButton boton2;
    PantallaGrafica(Bender bender){

        pane = new JPanel();
        botones = new Container();
        vista = new Container();

        boton = new JButton();
        boton.setText("Run");

        boton2 = new JButton();
        boton2.setText("BestRun");

        add(pane);
        pane.setLayout(new BorderLayout());
        pane.add(vista, BorderLayout.CENTER);
        pane.add(botones,BorderLayout.NORTH);
        botones.setLayout(new FlowLayout());
        botones.add(boton);
        botones.setPreferredSize(new Dimension (200,100));
        botones.add(boton2);
        boton.addActionListener(actionEvent -> {
            if (!(bender.map.map[bender.bot.posicion.x][bender.bot.posicion.y] instanceof Fi )) {
                bender.unPaso();
                actualizarVista(bender);
            }
        });
        boton2.addActionListener(actionEvent -> {
            bender.PasoInteligente();
            actualizarVista(bender);
        });
        labels = new JLabel[bender.map.map.length][];
        setSize(bender.map.map[0].length * 50, (bender.map.map.length * 50 ) + 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        vista.setLayout(new GridLayout(bender.map.map.length, bender.map.map[0].length));
        for (int i = 0; i <bender.map.map.length ; i++) {
            labels[i] = new JLabel[bender.map.map[i].length];
            for (int j = 0; j <labels[i].length ; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                //labels[i][j].setBounds(j*20,i*20,20,20);
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
                    //labels[i][j].setText(bender.map.map[i][j].distancia + "");
                    vista.add(labels[i][j]);
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
                            labels[i][j].setText("#");
                            break;
                        case " ":
                            labels[i][j].setBackground(new Color(200, 200, 200));
                            labels[i][j].setText(" ");
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
                if (bender.map.map[i][j].getDistancia() < 100)
                    labels[i][j].setText(bender.map.map[i][j].distancia + "");
            }
        }
    }
}
