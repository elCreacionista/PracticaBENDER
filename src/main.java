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
                */

public class main {
    public static void main(String[] args) {
        String mapa = "" +
                "#############\n" +
                "#T X        #\n" +
                "#          I#\n" +
                "#           #\n" +
                "#           #\n" +
                "##          #\n" +
                "#           #\n" +
                "#$          #\n" +
                "#T$         #\n" +
                "#############";
        System.out.println(mapa);


        Mapa xd = new Mapa(mapa);


        Bender bot = new Bender(mapa);
        bot.run();
        System.out.println(bot.bot.path);
    }
}
