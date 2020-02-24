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
        this.bot = new Robot(this.map, this.map.getBot());
    }


    public String run() {
        if (!this.map.isValid()) return null;
        int pasos = 0;
        while (!(this.map.map[bot.posicion.x][bot.posicion.y] instanceof Fi || pasos++ == 100)) {
            System.out.println(bot.direccion.getDireccion(bot.movimiento));
            if (bot.moverse(this.map))
                System.out.println("paso");
            else bot.cambiarDireccion(this.map);

            if (bot.pisarTeleport(this.map)) {
                System.out.println("pisado teleport");
                if (bot.moverse(this.map))
                    System.out.println("paso");
                else bot.cambiarDireccion(this.map);

            }
            if (bot.pisarInvertido(this.map)) {
                System.out.println("pisado invertido");
                if (bot.moverse(this.map))
                    System.out.println("paso");
                else bot.cambiarDireccion(this.map);
            }


            System.out.println("");
            System.out.println("");
            System.out.println("");

            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[i].length; j++) {
                    if (bot.posicion.equals(new Point(i, j)))
                        System.out.print("o");
                    else if (map.map[i][j] instanceof Teleportador)
                        System.out.print("T");
                    else
                        System.out.print(map.map[i][j]);
                }
                System.out.println("");
            }


        }
        return bot.path;
    }


    public int bestRun() {
        return 1;
    }
}
