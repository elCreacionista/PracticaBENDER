import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mapa {

    static public String MetodoPere(int sizex, int sizey){
        List<StringBuilder> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < sizex; i++) {
            s.append('#');
        }
        list.add(s);
        int randx1 = (int)(Math.random()*sizex - 2) + 1;
        int randy1 = (int)(Math.random()*sizey - 2) + 1;
        int randx2 = (int)(Math.random()*sizex - 2) + 1;
        int randy2 = (int)(Math.random()*sizey - 2) + 1;
        for (int i = 0; i < sizey; i++) {
            s = new StringBuilder();
            s.append('#');
            for (int j = 0; j < sizex - 2; j++) {
                if (i == randx1 && j == randy1) {
                    s.append('X');
                    continue;
                }
                if (i == randx2 && j == randy2) {
                    s.append('$');
                    continue;
                }
                switch ((int)(Math.random()*30)){
                    case 0: case 1: s.append('I');break;
                    case 2: case 3: s.append('T');break;
                    case 4: case 5: case 6: case 7: case 8: s.append('#');break;
                    default: s.append(' ');
                }
            }
            s.append('#');
            list.add(s);
        }

        s = new StringBuilder();
        for (int i = 0; i < sizex; i++) {
            s.append('#');
        }
        list.add(s);
        String finalMap = "";
        for (int i = 0; i < list.size()-1; i++) {
            finalMap += list.get(i).toString() + "\n";
        }
        finalMap += list.get(list.size()-1);
        System.out.println(finalMap);
        return finalMap;

    }









    Casella[][] map;
    Teleportador[] teletransportadores;
    Invers[] inversores;

    Mapa(String mapa){
        constructMap(createMap(mapa));
        teletransportadores = getTeleporters();
        inversores = getInversors();
    }

    public boolean isValid(){
        if (getEnd() == null)
            return false;
        if (getError())
            return false;
        if (teletransportadores.length == 1)
            return false;

        return true;
    }


    public boolean comprovarBorder(){
        Point comprovacion = null;
        Point posicioninicial;
        int j = 0;
        while (comprovacion == null){
            for (int i = 0; i < map[j].length; i++) {
                if (map[j][i] instanceof Pared) {
                    comprovacion = new Point(j, i);
                    break;
                }
            }
            j++;
        }
        posicioninicial = new Point( comprovacion.x, comprovacion.y);
        if (map[comprovacion.x][comprovacion.y + 1] instanceof Pared)
        comprovacion.y++;
        while (!comprovacion.equals(posicioninicial)){

            System.out.println("Point1: " + posicioninicial.x + " " + posicioninicial.y);
            System.out.println("Point2: " + comprovacion.x + " " + comprovacion.y);

            while (comprovacion.y < map.length && map[comprovacion.x][comprovacion.y + 1] instanceof Pared) {
                comprovacion.y++;
            }
            while (comprovacion.y > 0 && map[comprovacion.x][comprovacion.y - 1] instanceof Pared) {
                comprovacion.y--;
            }
            while (comprovacion.x + 1 < map[comprovacion.y].length && map[comprovacion.x + 1][comprovacion.y] instanceof Pared) {
                comprovacion.x++;
            }
            while (comprovacion.x > 0 && map[comprovacion.x - 1][comprovacion.y] instanceof Pared) {
                comprovacion.x--;
            }

            if (!comprovacion.equals(posicioninicial))
            return false;
        }
        return true;
    }


    public char[][] createMap(String mapa) {


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


        char[][] map = new char[listas.size()][];

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
                        this.map[i][j] = new Error(); break;
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
                    //map[i][j] = new Buid();
                }
            }
        }
        Teleportador[] t = new Teleportador[lista.size()];
        for (int i = 0; i < t.length ; i++) {
            t[i] = new Teleportador(lista.get(i));
        }

        return t;
    }

    public boolean getError(){
        for (int i = 0; i < this.map.length ; i++) {
            for (int j = 0; j < this.map[i].length ; j++) {
                if (this.map[i][j] instanceof Error) {
                    return true;
                }
            }
        }
        return false;
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
abstract class Casella{
    Point point;
    int distancia = 1000;
    boolean explorado = false;
    public void setDistancia(int distancia){
        this.distancia = distancia;
    }
    public int getDistancia(){
        return this.distancia;
    }
    @Override
    public String toString(){
        return "Casella";
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
        return "I";
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
        return "X";
    }
}
class Fi extends Casella{
    @Override
    public String toString(){
        return "$";
    }
}
class Error extends Casella{
    @Override
    public String toString(){
        return "ERROR";
    }
}
