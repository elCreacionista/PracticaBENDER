
import java.awt.*;

public class Caminos {

    Casella[][] casellas;
    Point robot;
    Point inicio;
    Point end;

    Caminos(Bender bender) {
        this.casellas = bender.map.map;
        inicio = bender.bot.posicion;
        end = bender.map.getEnd();
        this.robot = bender.bot.posicion;
    }


    public void CalcularDistancias() {
        while (this.casellas[end.x][end.y].toString().equals("$")) {
            this.casellas[robot.x][robot.y].setDistancia(getDistancia(this.inicio, this.robot) + getDistancia(this.robot, this.end));

        }
    }


    public static int getDistancia(Point inicio, Point destino) {

        int distancia = 0;
        if (destino.x == inicio.x && destino.y == inicio.y)
            return 0;

        if (destino.x < inicio.x)
            distancia += inicio.x - destino.x;
        else
            distancia += destino.x - inicio.x;

        if (destino.y < inicio.y)
            distancia += inicio.y - destino.y;
        else
            distancia += destino.y - inicio.y;

        return distancia;

    }
}