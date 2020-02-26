import java.awt.*;
import java.util.*;
import java.util.List;

public class Robot {
    Point posicion;
    Point siguienteposicion;
    Direccion direccion;
    boolean invertido = false;
    boolean teleportado = false;
    int movimiento = 0;
    String path = "";
    List<Point> camino;
    Robot(Mapa map, Point p){
        this.posicion = p;
        this.direccion = Direccion.SOUTH;
        this.siguienteposicion = siguientePosicion();
        camino = new ArrayList<>();
    }


    public boolean bucle() {
        for (int i = 0; i < camino.size(); i++) {
            for (int j = 0; j < camino.size(); j++) {
                if (i < j)
                    if (camino.get(i).equals(camino.get(j))) {
                        List<Point> sublist = camino.subList(i, j);
                        List<Point> subsublist1 = sublist.subList(0, sublist.size() / 2);
                        List<Point> subsublist2 = sublist.subList(sublist.size() / 2, sublist.size());
                        if (subsublist1.equals(subsublist2))
                            return true;
                    }
            }
        }
        return false;
    }

    public boolean moverse(Mapa map){
        if (!(map.map[siguienteposicion.x][siguienteposicion.y] instanceof Pared)) {
            this.camino.add(this.posicion);
            this.posicion = this.siguienteposicion;
            this.siguienteposicion = siguientePosicion();
            if (!this.teleportado) {
                this.path += this.direccion.getChar();

            }
            teleportado = false;
            return true;
        }
        else {
            return false;
        }

    }
    //ordre: S (South), E (East), N (North), W (West).
    //direccions: N (North), W(West), S (South), E (East).
    public void cambiarDireccion(Mapa map) {

        if (this.invertido) {
            this.movimiento = 2;
        }
        else {
            this.movimiento = 0;
        }

        while (map.map[siguienteposicion.x][siguienteposicion.y] instanceof Pared) {
            this.direccion = direccion.getDireccion(this.movimiento);
            this.siguienteposicion = siguientePosicion();
            this.movimiento++;
        }

    }


    public boolean pisarTeleport (Mapa map){
        for (int i = 0; i < map.teletransportadores.length ; i++) {
            if (this.posicion.equals(map.teletransportadores[i].point)){
                this.siguienteposicion = getTeleportadorObjetivo(map,map.teletransportadores[i]);
                this.teleportado = true;
                return true;
            }
        }
        return false;
    }

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

    public Point getTeleportadorObjetivo(Mapa map, Teleportador tel){
        Map<Integer, Map<Integer,Teleportador>> distancias = new HashMap<>();
        for (int i = 0; i < map.teletransportadores.length; i++) {
            if (!map.teletransportadores[i].equals(tel)) {
                Map<Integer, Teleportador> lista = new HashMap<>();
                System.out.println("Aqui no se repiten los keys. KEYS: " + getDistancia(map.teletransportadores[i], tel));
                lista.put(calculateAngle(map.teletransportadores[i].point, tel.point), map.teletransportadores[i]);
                if (distancias.isEmpty())
                    distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
                else {
                    if (distancias.containsKey(getDistancia(map.teletransportadores[i], tel))) {
                        System.out.println("Ahora se repiten los keys. KEYS: " + getDistancia(map.teletransportadores[i], tel));
                        distancias.get(getDistancia(map.teletransportadores[i], tel)).put(calculateAngle(map.teletransportadores[i].point, tel.point), map.teletransportadores[i]);
                    } else
                        distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
                }
            }
        }
        for (int i = 1; i < 1000 ; i++) {
            if (distancias.containsKey(i))
                for (int j = 0; j < 1000; j++) {
                    if (distancias.get(i).containsKey(j))
                        return distancias.get(i).get(j).point;
                }
        }

        System.out.println("error");
        return distancias.get(0).get(0).point;
    }

    /*
    * int dotp = (point1.x * point2.x) + (point1.y * point2.y);
        double lenghtP1 = Math.sqrt(Math.pow(point1.x,2) + Math.sqrt(Math.pow(point1.y,2)));
        double lenghtP2 = Math.sqrt(Math.pow(point2.x,2) + Math.sqrt(Math.pow(point2.y,2)));

        return (int) (Math.pow(Math.cos(Math.floorDiv(dotp, (int) (lenghtP1 * lenghtP2))), -1));*/

    public int calculateAngle(Point point1, Point point2){
        Point vector = new Point(point1.x - point2.x, point1.y - point2.y);
        System.out.println("point1: " + point1.x + " " + point1.y);
        System.out.println("point2: " + point2.x + " " + point2.y);
        System.out.println("point: " + vector.x + " " + vector.y);



        if (vector.x == 0 && vector.y > 0)
            return 180;
        if (vector.x == 0 && vector.y < 0)
            return 360;
        if (vector.y == 0 && vector.x > 0)
            return 90;
        if (vector.y == 0 && vector.x < 0)
            return 270;

        double hip = Math.sqrt(vector.x * vector.x + vector.y * vector.y);
        double anguloT = Math.toDegrees(Math.acos(vector.y / hip));

        System.out.println("Hipotenusa: " + hip);
        System.out.println("Angulo: " + anguloT);
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

    public Point siguientePosicion(){


        switch (this.direccion){
            case NORTH: return new Point(this.posicion.x - 1,this.posicion.y);
            case SOUTH: return new Point(this.posicion.x + 1,this.posicion.y);
            case WEST: return new Point(this.posicion.x,this.posicion.y - 1);
            case EAST: return new Point(this.posicion.x,this.posicion.y + 1);
            default: return null;
        }
    }

}
enum Direccion{
    SOUTH(0),
    EAST(1),
    NORTH(2),
    WEST(3),
    ERROR(4);
    int num;
    Direccion(int num){
        this.num = num;
    }

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