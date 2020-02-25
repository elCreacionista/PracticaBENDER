import java.util.Arrays;


/* String mapa = "" +
                "#############\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#           #\n" +
                "#############";
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
                */

public class main {
    public static void main(String[] args) {

        String mapa = "" +
                "   #######\n" +
                "   # XTI #\n" +
                "   #    $#\n" +
                "#### J  #####\n" +
                "#          T#\n" +
                "####     ####\n" +
                "   #    I#\n" +
                "   #     #\n" +
                "   #######";
        System.out.println(mapa);


        Mapa xd = new Mapa(mapa);


        Bender bot = new Bender(mapa);
        //Interfeis in = new Interfeis(bot.map, bot.bot);
        bot.run();
        System.out.println(bot.bot.bucle());
        System.out.println(bot.bot.path);
    }
}
