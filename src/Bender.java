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

    String mapa;


    Bender(String mapa){
        this.mapa = mapa;
    }


    public String run(){
        return "d";
    }


    public int bestRun() {
        return 1;
    }
}
