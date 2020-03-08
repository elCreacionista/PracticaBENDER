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

class index{

    public void ObjectesIMètodes(){
        String map = "" +
                "####_#####\n" +
                "#I X  T  #\n" +
                "#  T  $  #\n" +
                "##########";
        Bender bender = new Bender(map);

        Mapa mapa = bender.map;

        char[][] charmap = mapa.createMap(map);
        mapa.constructMap(charmap);
        boolean valid = mapa.isValid();
        boolean error = mapa.getError();
        Point pointrobot = mapa.getBot();
        Point pointend = mapa.getEnd();
        Invers[] inversores =  mapa.getInversors();
        Teleportador[] teleportadors = mapa.getTeleporters();

        Robot robot = bender.bot;

        boolean puedemoverse = robot.moverse(mapa);
        robot.cambiarDireccion(mapa);
        boolean invertido = robot.pisarInvertido(mapa);
        boolean teleporter = robot.pisarTeleport(mapa);
        Point siguientePaso = robot.siguientePosicion();
        Point siguiente_teleporter = robot.getTeleportadorObjetivo(mapa, teleportadors[0]);
        int distancia_teleporters = robot.getDistancia(teleportadors[0],teleportadors[1]);
        int angulo_teleporters = robot.calculateAngle(teleportadors[0].point, teleportadors[1].point);
        boolean buscar_bucle = robot.bucle();


        String run = bender.run();
        bender.unPaso();

        bender.PasoInteligente();
        int bestRun = bender.bestRun();
        bender.MetodoParaLaInterfaz();
    }

}



public class Bender {


    /* MAPA I ROBOT - Bender ha de saber per on i qui ha de mourerse*/
    Mapa map;
    Robot bot;

    /*Constructor per bender, que assigna les variables*/
    Bender(String mapa){
        this.map = new Mapa(mapa);
        this.bot = new Robot(this.map.getBot());
    }


    /*Mou el robot 1 pas, mira si trepitja un inversor o un teleportador*/
    public void unPaso(){
        /*No sé si els ifs poden estar buids, així que pos un print adins
        * El robot es mou*/
        if (bot.moverse(this.map)) {
            System.out.print("");
        }
        /*En cas de que tengui una pared davant, canvia la direccio i es mou*/
        else {
            bot.cambiarDireccion(this.map);
            bot.moverse(this.map);
        }
        if (bot.pisarTeleport(this.map)) {
            /*Si trepitja un teleport, es teletransporta i torna a mourerse
            * per no teletransportarse un altre vegada*/
            if (bot.moverse(this.map))
                System.out.print("");
            else bot.cambiarDireccion(this.map);

        }
        if (bot.pisarInvertido(this.map)) {
            /*Si trepitja un inversor, s'inverteix i torna a mourerse
            * Per a que no caigi dues vegades*/
            if (bot.moverse(this.map))
                System.out.print("");
            else bot.cambiarDireccion(this.map);
        }
    }

/*Run*/
    public String run() {
     /*Si el mapa és vàlid segueix executant i si no retorna null*/
        System.out.println("És valid: " + map.isValid());
        if (!this.map.isValid()) return null;
        /*Aquesta variable serveix després per comprovar si hi ha bucle */
        int pasos = 0;
        while (!(this.map.map[bot.posicion.x][bot.posicion.y] instanceof Fi || pasos++ == 10000)) {
            /*Es mou el bot*/
            unPaso();
            /*En cas de que hi hagui un bucle es comprova cada 100 pasos*/
            if (pasos%100 == 0)
            if (bot.bucle())
                return null;
        }
        return bot.path;
    }


    Point pointdelrun;
    int distanciaactual;
    public int bestRun() {
        MetodoParaLaInterfaz();
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

        if (!(map.map[pointdelrun.x + 1][pointdelrun.y] instanceof Pared) && !map.map[pointdelrun.x + 1][pointdelrun.y].explorado && map.map[pointdelrun.x + 1][pointdelrun.y].getDistancia() > (map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1))
            map.map[pointdelrun.x + 1][pointdelrun.y].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x - 1][pointdelrun.y] instanceof Pared) && !map.map[pointdelrun.x - 1][pointdelrun.y].explorado && map.map[pointdelrun.x - 1][pointdelrun.y].getDistancia() > (map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1))
            map.map[pointdelrun.x - 1][pointdelrun.y].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x][pointdelrun.y + 1] instanceof Pared) && !map.map[pointdelrun.x][pointdelrun.y + 1].explorado && map.map[pointdelrun.x][pointdelrun.y + 1].getDistancia() > (map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1))
            map.map[pointdelrun.x][pointdelrun.y + 1].setDistancia(map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1);

        if (!(map.map[pointdelrun.x][pointdelrun.y - 1] instanceof Pared) && !map.map[pointdelrun.x][pointdelrun.y - 1].explorado && map.map[pointdelrun.x][pointdelrun.y - 1].getDistancia() > (map.map[pointdelrun.x][pointdelrun.y].getDistancia() + 1))
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
