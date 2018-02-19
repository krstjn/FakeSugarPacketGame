import edu.princeton.cs.algs4.StdIn;

/**
 * SugarCubes
 * Höfundur: Kristján Pétur Þórarinsson
 * 
 * Keyrsla: java SugarCubes [heiltala sem segir stærð leikjarins]
 */
public class SugarCubes {
    // breytur sem segja til um hvort leikurinn sé unninn
    // og þá hvor vann
    boolean winnerH = false;
    boolean winnerT = false;

    /**
     * Skilar true ef leikur hefur verið unninn, false annars
     * player = true er leikmaður
     * player = false er tölva
     */
    public boolean gameOver(int[][] x, boolean player) {
        int n = x.length;
        if (!winnerT && !winnerH && player) {
            for (int i = 1; i < n - 1; i++) {
                if (x[n - 1][i] != 1)
                    return false;
            }
            display(x);
            System.out.println("Leikmaðurinn vann");
            winnerH = true;
            return true;
        } else if (!winnerT && !winnerH) {
            for (int i = 1; i < n - 1; i++) {
                if (x[i][n - 1] != 2)
                    return false;
            }
            display(x);
            System.out.println("Tölvan vann");
            winnerT = true;
            return true;
        }
        if (player)
            return winnerH;
        else
            return winnerT;
    }

    /**
     * Skilar true ef staðan er góð fyrir þann sem er að gera 
     * false ef hun er slæm
     */
    public boolean play(int[][] x, boolean player) {
        if (gameOver(x, player))
            return true;
        if (gameOver(x, !player))
            return false;

        int [][] y = new int[x.length][];
        for(int t = 0; t < x.length; t++)
            y[t] = x[t].clone();
        // Lykkja sem skoðar allar mögulegar færslur fyrir þann sem er að gera
        for (int j = 0; j < x.length - 1; j++) {
            for (int i = 0; i < x.length - 1; i++) {
                if (player) {
                    if (x[i][j] == 1 && x[i + 1][j] == 2 && x[i + 2][j] != 2) {
                        y[i][j] = 0;
                        y[i + 2][j] = 1;
                        if (!play(y, !player)) return true;
                        else{ // backtrack
                            y[i][j] = 1;
                            y[i + 2][j] = 0;
                        }
                    } else if (x[i][j] == 1 && x[i + 1][j] == 0) {
                        y[i][j] = 0;
                        y[i + 1][j] = 1;
                        if (!play(y, !player)) return true;
                        else{ // backtrack
                            y[i][j] = 0;
                            y[i + 1][j] = 1;
                        }
                    }
                } else {
                    if (x[j][i] == 2 && x[j][i + 1] == 1 && x[j][i + 2] != 1) {
                        y[j][i] = 0;
                        y[j][i + 2] = 2;
                        if (!play(y, !player)) return true;
                        else { // backtrack
                            y[j][i] = 2;
                            y[j][i + 2] = 0;
                        }
                    } else if (x[j][i] == 2 && x[j][i + 1] == 0) {
                        y[j][i] = 0;
                        y[j][i + 1] = 2;
                        if (!play(y, !player)) return true;
                        else { // backtrack
                            y[j][i] = 2;
                            y[j][i + 1] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Birtir stöðuna á leiknum.
     */
    public void display(int[][] x) {

        int n = x.length;

        String a = " ";
        String b = "";
        for(int i = 1; i < n-1; i++){
            a+=i;
            b+="|";
        }
        System.out.println(a);
        System.out.println("]" +b + "[");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (x[i][j] == 1)
                    System.out.print("X");
                else if (x[i][j] == 2)
                    System.out.print("O");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    public static void main(String[] args) {
        SugarCubes x = new SugarCubes();
        int n = Integer.parseInt(args[0]);
        int[][] game = new int[n + 2][n + 2];

        for (int i = 1; i < game.length - 1; i++)
            game[0][i] = 1;
        for (int i = 1; i < game.length - 1; i++)
            game[i][0] = 2;

        x.display(game);
        int move = -1;
        while (move <= 0 || move >n) {
            System.out.print("Skrifaðu tölu 1-" + n +" til að hreyfa þann sem þú vilt: ");
            move = StdIn.readInt();
        }

        game[0][move] = 0;
        game[1][move] = 1;
        x.display(game);

        // Spilar restina af leiknum, false þýðir að tölvan á að gera
        x.play(game, false);
        System.out.println();

    }
}