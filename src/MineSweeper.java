import java.util.Random;
import java.util.Scanner;
public class MineSweeper {
    Scanner tara = new Scanner(System.in);
    private int adetSatir;
    private int adetSutun;
    private String[][] mineMap;
    private String[][] board;
    private int sayac = 0;
    private int satirNo;
    private int sutunNo;
    private boolean win;
    private boolean durum;

    MineSweeper(int adetSatir, int adetSutun) {
        this.adetSatir = adetSatir;
        this.adetSutun = adetSutun;
        this.mineMap = new String[adetSatir][adetSutun];
        this.board = new String[adetSatir][adetSutun];
        fill(this.board);
        fill(this.mineMap);
    }

    public void randomSayi() {
        Random rdn = new Random();
        this.satirNo = rdn.nextInt(this.adetSatir);

        Random rdn2 = new Random();
        this.sutunNo = rdn2.nextInt(this.adetSutun);
    }

    public String[][] fill(String[][] dizi) {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                dizi[i][j] = "-";
            }
        }
        return dizi;
    }

    public boolean isFind(String[][] arr, String value) {
        if (arr[this.satirNo][sutunNo] == "B") {
            return false;
        }
        return true;
    }

    public void run() {
        for (int j = 0; j < this.adetSatir; j++) {
            for (int k = 0; k < this.adetSutun; k++) {
                if (sayac < (this.adetSutun * this.adetSatir / 4)) {
                    randomSayi();
                    sayac++;
                    if (isFind(this.board, "B")) {
                        this.mineMap[satirNo][sutunNo] = "B";
                        this.board[satirNo][sutunNo] = "B";
                    } else sayac--;
                }
            }
        }
        printMineMap();
        fill(this.board);
        printBoard();
        koordinatGir();

    }

    private int durumSayac = 0;

    public void koordinatGir() {
        this.durum = true;
        while (this.durum) {
            int sayac = 0;
            this.durumSayac++;
            boolean kontrol = true;
            int satir = 0;
            int sutun = 0;
            while (kontrol) {
                System.out.println("Satır Giriniz : ");
                satir = tara.nextInt();
                System.out.println("Sütun Giriniz : ");
                sutun = tara.nextInt();
                if ((satir < 0 || satir > (this.adetSatir - 1)) || (sutun < 0 || sutun > (this.adetSutun - 1))) {
                    System.out.println("Hatalı sayı girdiniz lütfen tekrar giriniz ");
                    kontrol = true;
                } else {
                    kontrol = false;
                }
            }
            System.out.println("===========================");
            if (this.mineMap[satir][sutun].equals("B")) {
                System.out.println("Kaybettin ! ");
                printMineMap();
                this.durum = false;
                break;
            }
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((satir + i >= 0 && (satir + i) <= (this.adetSatir - 1)) && (sutun + j >= 0 && (sutun + j) <= (this.adetSutun - 1))) {
                        if (this.mineMap[satir + i][sutun + j].equals("B")) {
                            sayac++;
                            this.board[satir][sutun] = String.valueOf(sayac);
                        }
                        if (sayac == 0) {
                            this.board[satir][sutun] = "0";
                        }
                    }
                }
            }
            if (this.durumSayac == this.adetSatir * this.adetSutun - (this.adetSutun * this.adetSatir / 4)) {
                this.win = true;
            }
            if (this.win == true) {
                this.durum = false;
                System.out.println("KAZANDINIZ !!!");
            } else durum = true;
            printBoard();

        }
    }

    public void printMineMap() {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                System.out.print(" " + this.mineMap[i][j]);
            }
            System.out.println("");
        }
        System.out.println("===========================");
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");
    }
    public void printBoard() {
        for (int i = 0; i < this.adetSatir; i++) {
            for (int j = 0; j < this.adetSutun; j++) {
                System.out.print(" " + this.board[i][j]);
            }
            System.out.println("");
        }
    }
    public void MineCheck(int satirNo, int sutunNo) {
        this.adetSatir = satirNo;
        this.adetSutun = sutunNo;
    }
}