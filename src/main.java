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
        for (int i = 0; i <xd.map.length ; i++) {
            for (int j = 0; j <xd.map[i].length ; j++) {
                System.out.print(xd.map[i][j]);
            }
            System.out.println("");
        }

        Bender bot = new Bender(mapa);
        bot.run();
        System.out.println(bot.bot.path);
    }
}
