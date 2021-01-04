package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> posisiPlayer = new ArrayList<Integer>();
    static ArrayList<Integer> posisiCpu = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] papan = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        cetakPapan(papan);

        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Pilih posisi anda [1-9] : ");
            int posPlayer = sc.nextInt();
            while (posisiPlayer.contains(posPlayer) || posisiCpu.contains(posPlayer)) {                
                System.out.println("Posisi sudah terisi");
                System.out.println("Pilih ulang posisi");
                posPlayer = sc.nextInt();
            }
            String pemain = "player";
            System.out.println("Kamu memilih posisi " + posPlayer);
            giliran(papan, posPlayer, pemain);
            String hasil = kondisiMenang();
            if (hasil.length() > 0 ){
                cetakPapan(papan);
                System.out.println(hasil);
                break;
            }

            Random random = new Random();
            pemain = "cpu";
            int posCpu = random.nextInt(9) + 1;
            while (posisiPlayer.contains(posCpu) || posisiCpu.contains(posCpu)) {                
                posCpu = random.nextInt(9) + 1;
            }
            System.out.println("Koputer memilih posisi " + posCpu);
            giliran(papan, posCpu, pemain);
            hasil = kondisiMenang();
            if (hasil.length() > 0 ){
                cetakPapan(papan);
                System.out.println(hasil);
                break;
            }

            cetakPapan(papan);
            System.out.println("");
        }

    }

    public static void cetakPapan(char[][] papan) {
        for (char[] x : papan) {
            for (char y : x) {
                System.out.print(y);
            }
            System.out.println("");
        }

    }

    public static void giliran(char[][] papan, int pos, String pemain) {
        char simbol = ' ';
        if (pemain.equalsIgnoreCase("player")) {
            simbol = 'X';
            posisiPlayer.add(pos);
        } else if (pemain.equalsIgnoreCase("cpu")) {
            simbol = 'O';
            posisiCpu.add(pos);
        }

        switch (pos) {
            case 1:
                papan[0][0] = simbol;
                break;
            case 2:
                papan[0][2] = simbol;
                break;
            case 3:
                papan[0][4] = simbol;
                break;
            case 4:
                papan[2][0] = simbol;
                break;
            case 5:
                papan[2][2] = simbol;
                break;
            case 6:
                papan[2][4] = simbol;
                break;
            case 7:
                papan[4][0] = simbol;
                break;
            case 8:
                papan[4][2] = simbol;
                break;
            case 9:
                papan[4][4] = simbol;
                break;
            default:
                break;
        }
    }
    
    public static String kondisiMenang(){
        
        List barisAtas = Arrays.asList(1,2,3);
        List barisTengah = Arrays.asList(4,5,6);
        List barisBawah = Arrays.asList(7,8,9);
        List kolKiri = Arrays.asList(1,4,7);
        List kolTengah = Arrays.asList(2,5,8);
        List kolKanan = Arrays.asList(3,6,9);
        List diag1 = Arrays.asList(1,5,9);
        List diag2 = Arrays.asList(3,5,7);
        
        List<List> menang = new ArrayList<>();
        menang.add(barisAtas);
        menang.add(barisTengah);
        menang.add(barisBawah);
        menang.add(kolKiri);
        menang.add(kolTengah);
        menang.add(kolKanan);
        menang.add(diag1);
        menang.add(diag2);
        
        for(List z : menang){
            if (posisiPlayer.containsAll(z)){
                return "Selamat kamu MENANG !!";
            } else if (posisiCpu.containsAll(z)){
                return "Maaf kamu KALAH T.T";
            } else if (posisiPlayer.size()+ posisiCpu.size() == 9){
                return "Hasil pertandingan SERI";
            }
        }
        
        return "";
    }

}
