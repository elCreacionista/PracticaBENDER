import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        String mapa = "" +
                "###########\n" +
                "######    #\n" +
                "#$   #    #\n" +
                "#### #    #\n" +
                "#  # #    #\n" +
                "#  #X#    #\n" +
                "#  #I#    #\n" +
                "#  ###    #\n" +
                "#         #\n" +
                "###########";
        System.out.println(mapa);


        Mapa xd = new Mapa(mapa);


        Bender bot = new Bender(mapa);
        bot.run();
        System.out.println(bot.bot.path);
    }
}
