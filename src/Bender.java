/*El robot comença a la posició del mapa assenyalada amb el caràcter «X».
El robot sempre va inicialment cap al sud del mapa.

Les parets estan representades pel caràcter «#».

Quan el robot troba un obstacle, aleshores intenta canviar la seva direcció, en aquest
ordre: S (South), E (East), N (North), W (West).

5. El robot finalitza el seu camí en arribar al destí, assenyalat amb el caràcter «$».

6. En el mapa hi ha també dispositius «inversors», assenyalats amb el caràcter «I». Quan
el robot troba un inversor, canvia la prioritat de les seves direccions: N (North), W
(West), S (South), E (East).

7. Opcionalment, us podeu trobar dispositius teleportadors, assenyalats amb el caràcter
«T».*/


import java.awt.*;

public class Bender {


    Mapa map;
    Robot bot;

    Bender(String mapa){
        this.map = new Mapa(mapa);
        this.bot = new Robot(this.map.getBot());
    }


    public void unPaso(){
        if (bot.moverse(this.map))
            System.out.print("");
        else {
            bot.cambiarDireccion(this.map);
            bot.moverse(this.map);

        }
        if (bot.pisarTeleport(this.map)) {

            if (bot.moverse(this.map))
                System.out.print("");
            else bot.cambiarDireccion(this.map);

        }
        if (bot.pisarInvertido(this.map)) {

            if (bot.moverse(this.map))
                System.out.print("");
            else bot.cambiarDireccion(this.map);
        }
    }


    public String run() {
        System.out.println("És valid: " + map.isValid());
        if (!this.map.isValid()) return null;
        System.out.println("Is valid: " + map.isValid());
        int pasos = 0;
        while (!(this.map.map[bot.posicion.x][bot.posicion.y] instanceof Fi || pasos++ == 10000)) {
            unPaso();
            if (pasos%100 == 0)
            if (bot.bucle())
                return null;
        }
        return bot.path;
    }


    Point pointdelrun;
    int distanciaactual;
    public int bestRun() {
        pointdelrun = bot.posicion;
        map.map[pointdelrun.x][pointdelrun.y].setDistancia(0);
        while (!pointdelrun.equals(new Point(map.getEnd().x, map.getEnd().y))) {
            PasoInteligente();
        }
        return map.map[map.getEnd().x][map.getEnd().y].getDistancia();
    }


    public void MetodoParaLaInterfaz(){
        pointdelrun = bot.posicion;
        map.map[pointdelrun.x][pointdelrun.y].setDistancia(0);
    }
    public void PasoInteligente(){
        distanciaactual = 1000;
        map.map[pointdelrun.x][pointdelrun.y].explorado = true;

        for (int i = 0; i < map.teletransportadores.length; i++) {
            if (pointdelrun.equals(map.teletransportadores[i].point)) {
                int distancia_ayuda = map.map[pointdelrun.x][pointdelrun.y].getDistancia();
                pointdelrun = this.bot.getTeleportadorObjetivo(map, map.teletransportadores[i]);
                map.map[pointdelrun.x][pointdelrun.y].setDistancia(distancia_ayuda);
                break;
            }
        }

        if (!(map.map[pointdelrun.x + 1][pointdelrun.y] instanceof Pared) && !map.map[pointdelrun.x + 1][pointdelrun.y].explorado)
            map.map[pointdelrun.x + 1][pointdelrun.y].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x - 1][pointdelrun.y] instanceof Pared) && !map.map[pointdelrun.x - 1][pointdelrun.y].explorado)
            map.map[pointdelrun.x - 1][pointdelrun.y].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x][pointdelrun.y + 1] instanceof Pared) && !map.map[pointdelrun.x][pointdelrun.y + 1].explorado)
            map.map[pointdelrun.x][pointdelrun.y + 1].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x][pointdelrun.y - 1] instanceof Pared) && !map.map[pointdelrun.x][pointdelrun.y - 1].explorado)
            map.map[pointdelrun.x][pointdelrun.y - 1].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        for (int i = 0; i < map.map.length; i++)
            for (int j = 0; j < map.map[i].length; j++) {
                if (map.map[i][j].getDistancia() < distanciaactual && !map.map[i][j].explorado) {
                    pointdelrun = new Point(i, j);
                    distanciaactual = map.map[i][j].getDistancia();
                }
            }
    }
}
