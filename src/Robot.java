import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Robot {
    Point posicion;
    Point siguienteposicion;
    Direccion direccion;
    boolean invertido = false;
    int movimiento = 0;
    String path = "";
    Robot(Mapa map, Point p){
        this.posicion = p;
        direccion = Direccion.SOUTH;
        siguienteposicion = siguientePosicion();
    }

    public boolean moverse(Mapa map){
        System.out.println(siguienteposicion.x + " " + siguienteposicion.y);
        if (!(map.map[siguienteposicion.x][siguienteposicion.y] instanceof Pared)) {
            this.posicion = this.siguienteposicion;
            siguienteposicion = siguientePosicion();
            this.path += this.direccion.getChar();
            return true;
        }
        else {
            this.movimiento += 1;
        }
        return false;
    }
    //ordre: S (South), E (East), N (North), W (West).
    //direccions: N (North), W(West), S (South), E (East).
    public void cambiarDireccion(Mapa map) {


        this.movimiento++;
        this.direccion = direccion.getDireccion(this.movimiento);
        siguienteposicion = siguientePosicion();

    }

    public boolean pisarTeleport(Mapa map){
        for (int i = 0; i < map.teletransportadores.length ; i++) {
            if (this.posicion.equals(map.teletransportadores[i].point)){
                this.siguienteposicion = getTeleportadorObjetivo(map,map.teletransportadores[i]).point;
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

    public Teleportador getTeleportadorObjetivo(Mapa map, Teleportador tel){
        Map<Integer, Teleportador> distancias = new HashMap<>();
        for (int i = 0; i < map.teletransportadores.length; i++) {
            distancias.put(getDistancia(map.teletransportadores[i], tel), map.teletransportadores[i]);
        }
        return distancias.get(2);

    }

    public boolean pisarInvertido(Mapa map){
        for (int i = 0; i < map.inversores.length ; i++) {
            if (this.posicion.equals(map.inversores[i].point)){
                if (invertido){
                    invertido = false;
                    movimiento += 2;
                    this.siguienteposicion = this.siguientePosicion();
                    return true;
                }
                else{
                    invertido = true;
                    movimiento += 2;
                    this.siguienteposicion = this.siguientePosicion();

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