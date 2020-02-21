import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    Casella[][] map;
    Teleportador[] teletransportadores;
    Invers[] inversores;

    Mapa(String mapa){
        constructMap(createMap(mapa));
        teletransportadores = getTeleporters();
        inversores = getInversors();
    }


    public char[][] createMap(String mapa) {

        char[][] map;
        List<List<Character>> listas = new ArrayList<>();
        List<Character> linea = new ArrayList<>();

        for (int i = 0; i < mapa.length() ; i++) {


            if (mapa.charAt(i) != '\n') {
                linea.add(mapa.charAt(i));
            }
            else {
                listas.add(linea);
                linea = new ArrayList<>();
            }
            if (i == mapa.length() - 1){
                listas.add(linea);
            }
        }


        map = new char[listas.size()][];

        for (int i = 0; i < map.length ; i++) {
            map[i] = new char[listas.get(i).size()];
            for (int j = 0; j < map[i].length ; j++) {
                map[i][j] = listas.get(i).get(j);
            }
        }

        return map;
    }


    public void constructMap(char[][] mapa){

        map = new Casella[mapa.length][];
        for (int i = 0; i < map.length; i++) {
            map[i] = new Casella[mapa[i].length];
            for (int j = 0; j < map[i].length ; j++) {

                switch (mapa[i][j]){
                    case '#': this.map[i][j] = new Pared(); break;
                    case ' ': this.map[i][j] = new Buid(); break;
                    case 'X': this.map[i][j] = new Bot(); break;
                    case '$': this.map[i][j] = new Fi(); break;
                    case 'T': this.map[i][j] = new Teleportador(); break;
                    case 'I': this.map[i][j] = new Invers(); break;
                    default:
                        System.out.println("smth went wrng");
                }

            }

        }
    }

    public Invers[] getInversors(){
        List<Point> lista = new ArrayList<>();
        for (int i = 0; i < this.map.length ; i++) {
            for (int j = 0; j < this.map[i].length ; j++) {
                if (map[i][j] instanceof Invers)
                    lista.add(new Point(i,j));
            }
        }
        Invers[] t = new Invers[lista.size()];
        for (int i = 0; i < t.length ; i++) {
            t[i] = new Invers(lista.get(i));
        }

        return t;
    }
    public Teleportador[] getTeleporters(){
        List<Point> lista = new ArrayList<>();
        for (int i = 0; i < this.map.length ; i++) {
            for (int j = 0; j < this.map[i].length ; j++) {
                if (map[i][j] instanceof Teleportador) {
                    lista.add(new Point(i, j));
                    map[i][j] = new Buid();
                }
            }
        }
        Teleportador[] t = new Teleportador[lista.size()];
        for (int i = 0; i < t.length ; i++) {
            t[i] = new Teleportador(lista.get(i));
        }

        return t;
    }

    public Point getBot(){
        for (int i = 0; i < this.map.length ; i++) {
            for (int j = 0; j < this.map[i].length ; j++) {
                if (this.map[i][j] instanceof Bot) {
                    this.map[i][j] = new Buid();
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    public Point getEnd(){
        for (int i = 0; i < this.map.length ; i++) {
            for (int j = 0; j < this.map[i].length ; j++) {
                if (map[i][j] instanceof Fi)
                    return new Point(i,j);
            }
        }
        return null;
    }

}
class Casella{
    Point point;
    @Override
    public String toString(){
        return "hola";
    }
}
class Pared extends Casella{
    @Override
    public String toString(){
        return "#";
    }
}
class Buid extends  Casella{
    @Override
    public String toString(){
        return " ";
    }
}
class Invers extends Casella{
    Invers(){}
    Invers(Point t){
        this.point = t;
    }

    @Override
    public String toString(){
        return "1";
    }
}
class Teleportador extends Casella{
    Teleportador(){}
    Teleportador(Point t){
        this.point = t;
    }

    @Override
    public String toString(){
        return "T";
    }
}
class Bot extends Casella{
    @Override
    public String toString(){
        return "o";
    }
}
class Fi extends Casella{
    @Override
    public String toString(){
        return "x";
    }
}
