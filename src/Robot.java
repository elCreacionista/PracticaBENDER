import java.awt.*;
import java.util.*;
import java.util.List;

public class Robot {
    Point posicion;
    Point siguienteposicion;
    boolean invertido = false;
    boolean teleportado = false;

    /*Per el moviment empr aquestes dues variables*/
    int movimiento = 0;
    Direccion direccion;

    /*El cami que fa*/
    String path = "";

    /*Una llista amb totes les posicions per on ha pasat el bot*/
    List<Point> camino;

    Robot(Point p){
        this.posicion = p;
        this.direccion = Direccion.SOUTH;
        this.siguienteposicion = siguientePosicion();
        camino = new ArrayList<>();
    }

    public Point siguientePosicion(){

        /*Calcula la seguent posició del bot depenent de on miri*/

        switch (this.direccion){
            case NORTH: return new Point(this.posicion.x - 1,this.posicion.y);
            case SOUTH: return new Point(this.posicion.x + 1,this.posicion.y);
            case WEST: return new Point(this.posicion.x,this.posicion.y - 1);
            case EAST: return new Point(this.posicion.x,this.posicion.y + 1);
            default: return null;
        }
    }

/*Cerca bucles a la llista "camino"*/
    public boolean bucle() {
        for (int i = 0; i < camino.size(); i++) {
            for (int j = 0; j < camino.size(); j++) {
                if (i < j) {
                    /*Recorr la llista i cerca dos punts que coincideixin*/
                    if (camino.get(i).equals(camino.get(j))) {
                        /*Crea una subllista i la divideix en 2, si són iguals és perque el bot ha caigut dins un bucle*/
                        List<Point> sublist = camino.subList(i, j);
                        List<Point> subsublist1 = sublist.subList(0, sublist.size() / 2);
                        List<Point> subsublist2 = sublist.subList(sublist.size() / 2, sublist.size());
                        if (subsublist1.equals(subsublist2))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moverse(Mapa map){
        /* Si el bloc al que está mirant no és pared es mou*/
        if (!(map.map[siguienteposicion.x][siguienteposicion.y] instanceof Pared)) {
            this.camino.add(this.posicion);                /*Si no ve de un teleportador s'afegeix el char a la string*/
            this.posicion = this.siguienteposicion;            /*La seguent posició pasa a ser la posició actual*/
            this.siguienteposicion = siguientePosicion();            /*Es calcula la seguent posició*/
            if (!this.teleportado) {                /*Si no ve de un teleportador s'afegeix el char a la string*/
                this.path += this.direccion.getChar();
            }
            teleportado = false;            /*Com acaba de mourerse no vé de un teleporter*/
            return true;
        }
        else {
            return false;
        }

    }
    //ordre: S (South), E (East), N (North), W (West).
    //direccions: N (North), W(West), S (South), E (East).
    public void cambiarDireccion(Mapa map) {
        /*Aquest nombre es per l'eenum Direccion: 0 = S (quan no està invertit) i 2 = N (Quan està invertit)*/
        if (this.invertido) {
            this.movimiento = 2;
        }
        else {
            this.movimiento = 0;
        }

        /*Gira fins que no hi hagui pared*/
        while (map.map[siguienteposicion.x][siguienteposicion.y] instanceof Pared) {
            this.direccion = direccion.getDireccion(this.movimiento);
            this.siguienteposicion = siguientePosicion();
            this.movimiento++;
            if (this.movimiento == 10)
                return;
        }

    }


        public boolean pisarTeleport (Mapa map){
        for (int i = 0; i < map.teletransportadores.length ; i++) {
            /*Recorr tots els teleporters cercant el que esta a la mateixa posició que el bot*/
            if (this.posicion.equals(map.teletransportadores[i].point)){
                this.siguienteposicion = getTeleportadorObjetivo(map,map.teletransportadores[i]);
                this.teleportado = true;
                return true;
            }
        }
        return false;
    }

    /*Resta les x i les y dels dos punts per treure la distància*/
    public int getDistancia(Teleportador tel, Teleportador objetivo){
        Point inicio = tel.point;
        Point destino = objetivo.point;
        int distancia = 0;
        if (destino.x == inicio.x && destino.y == inicio.y)
            return 0;

        if (destino.x < inicio.x )
            distancia += inicio.x - destino.x;
        else
            distancia += destino.x - inicio.x;

        if (destino.y < inicio.y )
            distancia += inicio.y - destino.y;
        else
            distancia += destino.y - inicio.y;

        return distancia;

    }

    /*Cerca el teleportador mes proper*/
    public Point getTeleportadorObjetivo(Mapa map, Teleportador tel){

        /*En aquest map es guarden la distància que hi ha desde cada teleportador al bot com key i el teleportador com paràmetre,
        * en cas de que hi hagui més d'un teleportador a la mateixa distància el key del segon map es l'angle que forma amb l'eix y*/
        Map<Integer, Map<Integer,Teleportador>> distancias = new HashMap<>();
        for (int i = 0; i < map.teletransportadores.length; i++) {
            if (!map.teletransportadores[i].equals(tel)) {
                Map<Integer, Teleportador> lista = new HashMap<>();
                lista.put(calculateAngle(map.teletransportadores[i].point, tel.point), map.teletransportadores[i]);
                if (distancias.isEmpty()) /*Aquest if es per colocar el primer element*/
                    distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
                else { /*Mira si ja hi ha un teleport a aquesta distància: En cas de que si coloca el nou teletransportador calculant l'angle
                En case de que no, simplement posa la llista*/
                    if (distancias.containsKey(getDistancia(map.teletransportadores[i], tel))) {
                        distancias.get(getDistancia(map.teletransportadores[i], tel)).put(calculateAngle(map.teletransportadores[i].point, tel.point), map.teletransportadores[i]);
                    } else
                        distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
                }
            }
        }
        /*Després es treu el primer teleportador que trobi*/
        for (int i = 1; i < 1000 ; i++) {
            if (distancias.containsKey(i))
                for (int j = 0; j < 1000; j++) {
                    if (distancias.get(i).containsKey(j))
                        return distancias.get(i).get(j).point;
                }
        }

        /*En teoría no arriba fins aquí peró tenia que acabar el mètode*/
        System.out.println("error");
        return distancias.get(0).get(0).point;
    }

    /*Calcula l'angle que formen els teleportadors*/
    public int calculateAngle(Point point1, Point point2){
        /*Per calcular l'angle treim el vector que formen els dos punts*/
        Point vector = new Point(point1.x - point2.x, point1.y - point2.y);

        /*Si cualcuna de les coordenades es 0 simplement torna un dels seguents graus*/
        if (vector.x == 0 && vector.y > 0)
            return 90;
        if (vector.x == 0 && vector.y < 0)
            return 270;
        if (vector.y == 0 && vector.x > 0)
            return 180;
        if (vector.y == 0 && vector.x < 0)
            return 0;

        /*Fa el coseno del vector*/
        double hip = Math.sqrt(vector.x * vector.x + vector.y * vector.y);
        double anguloT = Math.toDegrees(Math.acos(vector.y / hip));

        if (vector.x < 0 && vector.y > 0)
            return (int) anguloT;

        if (vector.x > 0 && vector.y > 0)
            return (int) anguloT + 90;

        if (vector.x > 0 && vector.y < 0)
            return (int) anguloT + 180;

        if (vector.x < 0 && vector.y < 0)
            return (int) anguloT + 270;


        return (int) anguloT;
    }

    public boolean pisarInvertido(Mapa map){
        /*Comprova tots els inversors i cerca si n'hi ha cualcun a la posició del bot*/
        for (int i = 0; i < map.inversores.length ; i++) {
            if (this.posicion.equals(map.inversores[i].point)){
                if (invertido){
                    this.invertido = false;
                    return true;
                }
                else{
                    this.invertido = true;
                    return true;
                }
            }
        }
        return false;
    }



}
enum Direccion{

    /*Les 4 direccions que pot agafar el bot*/
    SOUTH(0),
    EAST(1),
    NORTH(2),
    WEST(3),
    /*Aquesta és per si cualcuna cosa surt malament, encara que en teoría no tendría que pasar res*/
    ERROR(4);
    int num;
    Direccion(int num){
        this.num = num;
    }

    /*Retorna un valor del enum depenent del nombre que hi passem*/
    public Direccion getDireccion(int num){
        num = num % 4;
        switch (num){
            case 0: return Direccion.SOUTH;
            case 1: return Direccion.EAST;
            case 2: return Direccion.NORTH;
            case 3: return Direccion.WEST;
            default: return Direccion.ERROR;
        }
    }
    /*Agafam el char de la direcció que té a cada pas*/
    public char getChar(){
        switch (this.num){
            case 0: return 'S';
            case 1: return 'E';
            case 2: return 'N';
            case 3: return 'W';
            default: return 'X';
        }
    }
}