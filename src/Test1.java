import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        int pocet_prvkov = 10000000;

        String hladany_text = "nic"; int hladane_cislo=0;

        Node_avl koren_avl = null; Node_splay koren_splay = null; Node_hash hladany_hash=null;
        Node_avl pomocny=null;

        String slovicko = "slovo"; int cele_cislo=0;

        Random nahoda = new Random();
        nahoda.setSeed(6969420);

        char abeceda[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        long zaciatok_insert=0,koniec_insert=0, zaciatok_search=0, koniec_search=0, zaciatok_delete=0, koniec_delete=0;
        long cas_search=0, cas_delete=0, cas_insert=0;

        Node_hash[] array_adresovanie=new Node_hash[pocet_prvkov/2];
        Node_hash[] array_chaining=new Node_hash[pocet_prvkov/2];

           for (int i = 0; i < pocet_prvkov; i++) {
               //for (int i = 0; i < pocet_prvkov ; i++) {

               for (int l = 0; l < 6; l++) {
                   cele_cislo = nahoda.nextInt(52);
                   slovicko = abeceda[cele_cislo] + slovicko;
               }

               cele_cislo = nahoda.nextInt(pocet_prvkov);


               if (i == 100000 - 1 || i == 200000 - 1 || i == 300000 - 1 || i == 400000 - 1 || i == 500000 - 1 || i == 600000 - 1 || i == 700000 - 1 || i == 800000 - 1 || i == 900000 - 1 || i == 1000000 - 1 || i == 2000000 - 1 || i == 3000000 - 1 || i == 4000000 - 1 || i == 5000000 - 1 || i == 6000000 - 1 || i == 7000000 - 1 || i == 8000000 - 1 || i == 9000000 - 1 || i == 10000000 - 1) {
                   hladany_text = slovicko;
                   hladane_cislo = cele_cislo;
               }

               //if (i == 10 - 1 | i == 50 - 1 | i == 100 - 1 | i == 500 - 1 | i == 1000 - 1 | i == 5000 - 1 | i == 10000 - 1 | i == 50000 - 1 | i == 100000 - 1 | i == 500000 - 1 | i == 1000000 - 1 | i == 5000000 - 1 | i == 10000000 - 1) {
                   if (i == 100000 - 1 || i == 200000 - 1 || i == 300000 - 1 || i == 400000 - 1 || i == 500000 - 1 || i == 600000 - 1 || i == 700000 - 1 || i == 800000 - 1 || i == 900000 - 1 || i == 1000000 - 1 || i == 2000000 - 1 || i == 3000000 - 1 || i == 4000000 - 1 || i == 5000000 - 1 || i == 6000000 - 1 || i == 7000000 - 1 || i == 8000000 - 1 || i == 9000000 - 1 || i == 10000000 - 1) {

                       zaciatok_insert = System.nanoTime();
                   //zaciatok_delete=System.nanoTime();
                   //array_chaining = Operacie_chaining.vloz_chain(slovicko,cele_cislo,array_chaining,i,pocet_prvkov);
                   //array_adresovanie=Operacie_adresovanie.vloz_adresovanie(slovicko,cele_cislo,array_adresovanie,i,pocet_prvkov);
                   //koren_avl=Operacie_AVL.vloz(koren_avl,cele_cislo,slovicko);
                   koren_splay = Operacie_Splay.vloz(koren_splay, cele_cislo, slovicko);

                   koniec_insert = System.nanoTime();
                   cas_insert = (koniec_insert - zaciatok_insert)+cas_insert;
                   System.out.println(cas_insert);

               } else {
                   //array_chaining = Operacie_chaining.vloz_chain(slovicko,cele_cislo,array_chaining,i,pocet_prvkov);
                   //array_adresovanie=Operacie_adresovanie.vloz_adresovanie(slovicko,cele_cislo,array_adresovanie,i,pocet_prvkov);
                   //koren_avl=Operacie_AVL.vloz(koren_avl,cele_cislo,slovicko);
                   koren_splay = Operacie_Splay.vloz(koren_splay, cele_cislo, slovicko);
               }

               //HLADANIE
/*
               if (i == 100000 - 1 || i == 200000 - 1 || i == 300000 - 1 || i == 400000 - 1 || i == 500000 - 1 || i == 600000 - 1 || i == 700000 - 1 || i == 800000 - 1 || i == 900000 - 1 || i == 1000000 - 1 || i == 2000000 - 1 || i == 3000000 - 1 || i == 4000000 - 1 || i == 5000000 - 1 || i == 6000000 - 1 || i == 7000000 - 1 || i == 8000000 - 1 || i == 9000000 - 1 || i == 10000000 - 1) {
                    //for(int l=0;l<10;l++) {
                        //zaciatok_search = System.nanoTime();
                        pomocny = Operacie_AVL.najdi(koren_avl, hladane_cislo);
                        //koren_splay=Operacie_Splay.najdi(koren_splay,hladane_cislo);
                        //hladany_hash=Operacie_chaining.hladaj_chain(array_chaining,hladany_text);
                        //hladany_hash=Operacie_adresovanie.hladaj_adresovanie(array_adresovanie,hladany_text);
                        //koniec_search = System.nanoTime();
                        //cas_search = (koniec_search - zaciatok_search);
                   // }
                   //System.out.println(cas_search/10);
                   //System.out.println(cas_search);
                   // cas_search=0;
               }

*/

/*            //MAZANIE
               if (i == 100000 - 1 || i == 200000 - 1 || i == 300000 - 1 || i == 400000 - 1 || i == 500000 - 1 || i == 600000 - 1 || i == 700000 - 1 || i == 800000 - 1 || i == 900000 - 1 || i == 1000000 - 1 || i == 2000000 - 1 || i == 3000000 - 1 || i == 4000000 - 1 || i == 5000000 - 1 || i == 6000000 - 1 || i == 7000000 - 1 || i == 8000000 - 1 || i == 9000000 - 1 || i == 10000000 - 1) {

                   //zaciatok_delete = System.nanoTime();
                   koren_avl=Operacie_AVL.zmazanie(koren_avl,hladane_cislo);
                   //koren_splay=Operacie_Splay.zmazanie(koren_splay,hladane_cislo);
                   //array_chaining=Operacie_chaining.zmazanie_chain(array_chaining,hladany_text);
                   //array_adresovanie = Operacie_adresovanie.zmazanie_adresovanie(array_adresovanie, hladany_text);
                   koniec_delete = System.nanoTime();
                   cas_delete = (koniec_delete - zaciatok_delete)+cas_delete;
                   System.out.println(cas_delete);
               }


*/
               slovicko = "";
           }
        }
    }