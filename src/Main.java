import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int getRow, getCol;
        System.out.println("-----Mayin Tarlasi Oyunu-----");
        // DEGERLENDIRME FORMU 7
        do {
            System.out.print("Oyun alaninin buyuklugunu belirlemek icin satir sayisini giriniz : ");
            getRow = input.nextInt();
            System.out.print("Oyun alaninin buyuklugunu belirlemek icin sutun sayisini giriniz : ");
            getCol = input.nextInt();

            if (getRow < 2 || getCol < 2) {
                System.out.println("Satir veya sutun sayisi 2 den kucuk olamaz tekrar giriniz.");
            }
        } while (getRow < 2 || getCol < 2);

        MineSweeper mineSweeper = new MineSweeper(getRow, getCol);
        mineSweeper.run();
    }
}