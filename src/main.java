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
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        /*String mapa = "" +
                "###############\n" +
                "#             #\n" +
                "#      T      #\n" +
                "#     T T     #\n" +
                "#    T X T    #\n" +
                "#   T  T  T   #\n" +
                "#    T   T    #\n" +
                "#     T T     #\n" +
                "#      T      #\n" +
                "#             #\n" +
                "#             #\n" +
                "###############";*/

        System.out.println(mapa);

        Bender bender = new Bender(mapa);
        //System.out.println(bender.bestRun());
        PantallaGrafica inte= new PantallaGrafica(bender);

        System.out.println(bender.run());

        //bender.MetodoParaLaInterfaz();
        PantallaGrafica in = new PantallaGrafica(bender);

    }
}
