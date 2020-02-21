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


public class Bender {


    Mapa map;
    Robot bot;

    Bender(String mapa){
        this.map = new Mapa(mapa);
        this.bot = new Robot(this.map, this.map.getBot());
    }


    public String run(){
        String movimiento = "";
        while (!(this.map.map[bot.posicion.x][bot.posicion.y] instanceof Fi)){
            System.out.println(bot.direccion.getDireccion(bot.movimiento));
            if (bot.moverse(this.map))
                System.out.println("paso");
                else bot.cambiarDireccion(this.map);

                if (bot.pisarTeleport(this.map))
                    System.out.println("pisado teleport");
                if (bot.pisarInvertido(this.map))
                    System.out.println("pisado invertido");

        }
        return bot.path;
    }


    public int bestRun() {
        return 1;
    }
}
