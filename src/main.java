import java.util.Arrays;


/* String mapa = "" +
                "##################\n" +
                "#                #\n" +
                "#         T      #\n" +
                "#         $      #\n" +
                "#         X      #\n" +
                "#      T  T  T   #\n" +
                "#      $     $   #\n" +
                "#                #\n" +
                "#         T      #\n" +
                "#         $      #\n" +
                "#                #\n" +
                "##################";
String mapa = "" +
                "##################\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "#                #\n" +
                "##################";
                String mapa = "" +
                "#############\n" +
                "# T    X    #\n" +
                "#           #\n" +
                "#           #\n" +
                "#      I    #\n" +
                "#############\n" +
                "#   #  ###  #\n" +
                "#   ####T   #\n" +
                "#    $      #\n" +
                "#############";
String mapa = "" +
                "#############\n" +
                "#           #\n" +
                "#          X#\n" +
                "# T         #\n" +
                "#############\n" +
                "#     T     #\n" +
                "#      ######\n" +
                "#           #\n" +
                "#      $    #\n" +
                "#############";
                String mapa = "" +
                "##########################\n" +
                "######               I  T#\n" +
                "#T   #                  ##\n" +
                "#### #                   #\n" +
                "#  # #############       #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  # #                   #\n" +
                "#  #X#                   #\n" +
                "#  #I#                   #\n" +
                "# T###T                  #\n" +
                "# $                      #\n" +
                "##########################";
                */

public class main {
    public static void main(String[] args) {



        //String mapa = Mapa.MetodoPere(20,20);

        String mapa = "" +
                "##################\n" +
                "#  T  X  #       #\n" +
                "#        #  #### #\n" +
                "#        #  #    #\n" +
                "#    #####  # ####\n" +
                "#           #    #\n" +
                "##############   #\n" +
                "#                #\n" +
                "#        T    $  #\n" +
                "##################";
        System.out.println(mapa);


        Mapa xd = new Mapa(mapa);

        //System.out.println(xd.comprovarBorder());

        Bender bender = new Bender(mapa);
        System.out.println(bender.bestRun());
        Interfeis in = new Interfeis(bender);
        //bot.run();
        //Interfeis inte = new Interfeis(bot);

        System.out.println("Hay un bucle: " + bender.bot.bucle());
        System.out.println("El camino: " + bender.bot.path);
    }
}
