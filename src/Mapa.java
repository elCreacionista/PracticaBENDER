import java.awt.*;

public class Mapa {
    Casella[][] map;

    
    Mapa(String mapa){
        constructMap(createMap(mapa));
    }


    public char[][] createMap(String mapa) {

        char[] MAP = mapa.toCharArray();
        int  height = 0;
        for (int i = 0; i < MAP.length; i++) {
            if (MAP[i] == '\n'){
                height++;
            }
        }
        height++;
        int lugarenmap = 0;
        char[][] map = new char[height][];
        for (int i = 0; i < height; i++) {
            int cont = 0;
            int sizefila = 0;
            while (MAP[cont++] != '\n'){
                sizefila++;8
            }

            char[] m = new char[sizefila];
            for (int j = 0; j < m.length ; j++) {
                m[j] = MAP[lugarenmap++];
            }
            map[i] = m;
            lugarenmap++;
        }



        return map;
    }


    public void constructMap(char[][] mapa){

        map = new Casella[mapa.length][];
        for (int i = 0; i < mapa.length; i++) {
            map[i] = new Casella[mapa[i].length];
            for (int j = 0; j < mapa[i].length ; j++) {

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

    private Point SearchSize(String mapa){
        Point size = new Point(0,0);
        int contador = 0;
        while (mapa.charAt(contador) != '\n'){
            contador++;
        }
        size.y = contador + 1;
        contador = 0;


        for (int i = 0; i < mapa.length() ; i++) {
            if (mapa.charAt(i) == '\n'){
                contador++;
            }
        }
        size.x = contador + 1;


        return size;
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
