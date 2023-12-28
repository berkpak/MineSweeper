import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    //Degiskenler
    int row, col, boardSize;
    String[][] mineMap; //Mayinli map
    String[][] playerMap; //kullanicinin gorecegi harita
    boolean game = true;
    Random rand = new Random();
    Scanner input = new Scanner(System.in);

    //Constructor metot
    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.mineMap = new String[row][col];
        this.playerMap = new String[row][col];
        this.boardSize = (row * col);
    }

    // Oyunu Calistirma metodu
    public void run() {
        int getRow, getCol, winCount = 0;
        setMine();
        printMap(mineMap); // Mayinli haritayi gormek icin
        System.out.println("Uyari! satir ve sutun degeleri 0 ile basliyor");
        System.out.println("Ilk noktayi secmek isterseniz : Ornek(0,0)");
        System.out.println("Oyun Basladi");
        while (game) {
            printMap(playerMap);
            // DEGERLENDIRME FORMU 9
            System.out.print("Secmek istediginiz koodinatin satir degeri: ");
            getRow = input.nextInt();
            System.out.print("Secmek istediginiz koodinatin sutun degeri : ");
            getCol = input.nextInt();
            // DEGERLENDIRME FORMU 10
            //Kullanicidan alinan koordinat noktasinin oyun alaninin icinde olma durumunun kontrolu
            if (getRow < 0 || getRow >= row) {
                System.out.println("Yanlis koordinat giridiniz tekrar deneyin.");
                continue;
            }
            if (getCol < 0 || getCol >= col) {
                System.out.println("Yanlis koordinat giridiniz tekrar deneyin.");
                continue;
            }

            /* Kazanma ve Kaybetme durumunun kontrolu
            Secilen noktada mayin varsa oyunu bitir.
            Secilen nokta haricinde en sonda sadece mayinlar kaldiysa oyunu kazan. */
            // DEGERLENDIRME FORMU 13
            if (mineMap[getRow][getCol] == "*") {
                game = false;
                System.out.println("GAME OVER !!!"); //DEGERLENDIRME FORMU 15
            } else {
                check(getRow, getCol);
                winCount++;
                // DEGERLENDIRME FORMU 14
                if (winCount == (boardSize - (boardSize / 4))) {
                    System.out.println("Tebrikler! Oyunu Kazandiniz"); //DEGERLENDIRME FORMU 15
                    break;
                }
            }
        }
    }

    //Kullanicinin gorecegi harita
    public void printMap(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == null) {    // String turundeki bos bir array varsayilan deger olarak
                    System.out.print("-");  // null atadigi icin null ciktilarini "-" ile degistirdim.
                } else {
                    System.out.print(arr[i][j]); // null a esit olmayan koordinatlar mayin "*" ile doldurulacak.
                }
            }
            System.out.println();
        }
    }
    /* DEGERLENDIRME FORMU 8
    Mayinli Harita */
    public void setMine() {
        int mine = 0;
        while (mine != (boardSize / 4)) {
            int randRow = rand.nextInt(row);
            int randCol = rand.nextInt(col);
            if (mineMap[randRow][randCol] != "*") {
                mineMap[randRow][randCol] = "*";
                mine++;
            }
        }
    }
    // DEGERLENDIRME FORMU 12
    // Secilen noktanin etrafindaki 8 nokta icin mayin kontrolu
    public void check(int r, int c) {
        int mineCount = 0;
        if (c < col - 1 && mineMap[r][c + 1] == "*") { //sag
            mineCount++;
        }
        if (r < row - 1 && mineMap[r + 1][c] == "*") { // alt
            mineCount++;
        }
        if (r > 0 && mineMap[r - 1][c] == "*") { //ust
            mineCount++;
        }                                                       // r > 0 ve c > 0 ile ilk ve son, satir ve sutun icin sinir kontrolu
        if (c > 0 && mineMap[r][c - 1] == "*") {  //sol
            mineCount++;
        }
        if (r > 0 && c > 0 && mineMap[r - 1][c - 1] == "*") { // sol ust capraz
            mineCount++;
        }
        if (r < row - 1 && c > 0 && mineMap[r + 1][c - 1] == "*") { // sol alt capraz
            mineCount++;
        }
        if (r > 0 && c < col - 1 && mineMap[r - 1][c + 1] == "*") {  //sag ust capraz
            mineCount++;
        }
        if (r < row - 1 && c < col - 1 && mineMap[r + 1][c + 1] == "*") { // sag alt capraz
            mineCount++;
        }
        playerMap[r][c] = String.valueOf(mineCount);
    }
}
