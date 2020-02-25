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
<<<<<<< HEAD
        camino = new ArrayList<>();
    }


    public boolean bucle() {
        System.out.println(camino);
        for (int i = 0; i < camino.size(); i++) {
            for (int j = 0; j < camino.size(); j++) {
                if (i < j)
                    if (camino.get(i).equals(camino.get(j))) {
                        System.out.println("coinsicensia");
                        List<Point> sublist = camino.subList(i, j);
                        System.out.println(sublist);
                        List<Point> subsublist1 = sublist.subList(0, sublist.size() / 2);
                        List<Point> subsublist2 = sublist.subList(sublist.size() / 2, sublist.size());
                        System.out.println(subsublist1);
                        System.out.println(subsublist2);
                        if (subsublist1.equals(subsublist2))
                            return true;
                    }
            }
        }
        return false;
=======
>>>>>>> 86d3da915beb8004d38fde213eb454c237c4a42d
    }

    public boolean moverse(Mapa map){
        System.out.println(siguienteposicion.x + " " + siguienteposicion.y);
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

            System.out.println(this.direccion);
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
        Map<Integer, List<Teleportador>> distancias = new HashMap<>();
        for (int i = 0; i < map.teletransportadores.length; i++) {
            List<Teleportador> lista = new ArrayList<>();
            lista.add(map.teletransportadores[i]);
            if (distancias.isEmpty())
                distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
            else{
                if (distancias.containsKey(getDistancia(map.teletransportadores[i], tel))) {
                    distancias.get(getDistancia(map.teletransportadores[i], tel)).add(map.teletransportadores[i]);
                }
                else
                    distancias.put(getDistancia(map.teletransportadores[i], tel), lista);
            }
            lista = new ArrayList<>();
<<<<<<< HEAD
        }

        for (int i = 1; i < 1000 ; i++) {
            System.out.println(distancias.get(i));
            if (distancias.containsKey(i))
                return distancias.get(i).get(0).point;
        }

=======
        }

        for (int i = 1; i < 1000 ; i++) {
            System.out.println(distancias.get(i));
            if (distancias.containsKey(i))
                return distancias.get(i).get(0).point;
        }

>>>>>>> 86d3da915beb8004d38fde213eb454c237c4a42d

        return distancias.get(0).get(0).point;

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