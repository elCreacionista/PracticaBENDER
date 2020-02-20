import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mapa {
    Casella[][] map;

    
    Mapa(String mapa){
        constructMap(createMap(mapa));
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
    @Override
    public String toString(){
        return "1";
    }
}
class Teleportador extends Casella{
    Teleportador(){}
    Teleportador(Teleportador t){
        this.relacionat = t;
    }

    public void setTeleportador(Teleportador t){
        this.relacionat = t;
    }

    Teleportador relacionat;
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
