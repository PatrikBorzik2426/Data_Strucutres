import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        int pocet_prvkov = 10000000;

        int n=0; int k=0;

        Node_splay kontrolny=null;

        String hladany_text = "nic"; int hladane_cislo=0;

        String slovicko = "slovo"; int cele_cislo=0;

        Random nahoda = new Random();
        nahoda.setSeed(6969420);

        char abeceda[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        long cas_avl=0,cas_splay=0,cas_adresovanie=0,cas_chaining=0;
        int avl_count=0, splay_count=0, chaining_count=0, address_count=0;


        for(int m=1; m<=pocet_prvkov; m=m+m*9) {
            Node_avl koren_avl = null; Node_splay koren_splay = null;
            Node_hash hladany_hash=null; Node_avl pomocny=null;

            Node_hash[] array_adresovanie=new Node_hash[pocet_prvkov/2];
            Node_hash[] array_chaining=new Node_hash[pocet_prvkov/2];
            for (int i=0; i<m;i++) {

                for (int l = 0; l < 6; l++) {
                    cele_cislo = nahoda.nextInt(52);
                    slovicko = abeceda[cele_cislo] + slovicko;
                }

                cele_cislo = nahoda.nextInt(pocet_prvkov);

                if(i==m/2){
                    hladany_text=slovicko;
                    hladane_cislo=cele_cislo;
                }

                koren_splay=Operacie_Splay.vloz(koren_splay,cele_cislo,slovicko); //NA OVERENIE ZAKOMENTOVANÝ DUPLIKÁT
                if(koren_splay.text==slovicko){
                    splay_count++;
                }

                koren_avl=Operacie_AVL.vloz(koren_avl,cele_cislo,slovicko);
                if(Operacie_AVL.najdi(koren_avl,cele_cislo).text!="duplikat"){
                    avl_count++;
                }

                array_chaining=Operacie_chaining.vloz_chain(slovicko,cele_cislo,array_chaining,i,m);

                array_adresovanie=Operacie_adresovanie.vloz_adresovanie(slovicko,cele_cislo,array_adresovanie,i,m);


                slovicko = "";
            }
            while(n<array_adresovanie.length){
                if(array_adresovanie[n]!=null){
                    address_count++;
                }
                n++;
            }

            while(k<array_chaining.length){
                if(array_chaining[k]!=null){
                    chaining_count++;
                    Node_hash pomocny_node=array_chaining[k];
                    while(pomocny_node.kamos!=null){
                        chaining_count++;
                        pomocny_node=pomocny_node.kamos;
                    }
                }
                k++;
            }

            System.out.println("AVL: " + avl_count + "\n" + "Splay: " + splay_count + "\n" + "Addressing: " + address_count + "\n" + "Chaining: " + chaining_count +"\n");

            hladany_hash=Operacie_chaining.hladaj_chain(array_chaining,hladany_text);
            if(hladany_hash!=null) {
                System.out.println("Nájdený prvok chain: " + hladany_hash.text);
            }
            hladany_hash=Operacie_adresovanie.hladaj_adresovanie(array_adresovanie,hladany_text);
            if(hladany_hash!=null) {
                System.out.println("Nájdený prvok adresovanie: " + hladany_hash.text);
            }
            pomocny=Operacie_AVL.najdi(koren_avl,hladane_cislo);
            kontrolny=Operacie_Splay.najdi(koren_splay,hladane_cislo);
            if(kontrolny!=null){koren_splay=kontrolny;}
            System.out.println("Nájdený prvok AVL: " + pomocny.text);
            if(koren_splay.text==hladany_text) {
                System.out.println("Nájdený prvok Splay: " + koren_splay.text + "\n");
            }



            address_count=0; chaining_count=0; n=0; k=0;

            System.out.println("MAŽEM O DUŠU HĽDANAÉ!\n ");

            array_chaining=Operacie_chaining.zmazanie_chain(array_chaining,hladany_text);
            array_adresovanie=Operacie_adresovanie.zmazanie_adresovanie(array_adresovanie,hladany_text);
            koren_avl=Operacie_AVL.zmazanie(koren_avl,hladane_cislo);
            koren_splay=Operacie_Splay.zmazanie(koren_splay,hladane_cislo);

            hladany_hash=Operacie_chaining.hladaj_chain(array_chaining,hladany_text);
            if(hladany_hash!=null){
                System.out.println("Nájdený prvok chain: " + hladany_hash.text);
            }else{System.out.println("Prvok neexistuje v Chainingu!");}

            hladany_hash=Operacie_adresovanie.hladaj_adresovanie(array_adresovanie,hladany_text);
            if(hladany_hash!=null){
            System.out.println("Nájdený prvok adresovanie: " + hladany_hash.text);
            }
            pomocny=Operacie_AVL.najdi(koren_avl,hladane_cislo);
            kontrolny=Operacie_Splay.najdi(koren_splay,hladane_cislo);
            if(pomocny!=null){System.out.println("Nájdený prvok AVL: " + pomocny.text);}
            else {System.out.println("Prvok v AVL neexistuje!");}
            if(kontrolny!=null){
                koren_splay=kontrolny;
                if(koren_splay.text==hladany_text) {
                    System.out.println("Nájdený prvok Splay: " + koren_splay.text + "\n");
                }
            }


            System.out.println("\n");

            while(n<array_adresovanie.length){
                if(array_adresovanie[n]!=null && array_adresovanie[n].text!="VYMAZANY"){
                    address_count++;
                }
                n++;
            }

            while(k<array_chaining.length){
                if(array_chaining[k]!=null){
                    chaining_count++;
                    Node_hash pomocny_node=array_chaining[k];
                    while(pomocny_node.kamos!=null){
                        chaining_count++;
                        pomocny_node=pomocny_node.kamos;
                    }
                }
                k++;
            }

            System.out.println("AVL: " + avl_count + "\n" + "Splay: " + splay_count + "\n" + "Addressing: " + address_count + "\n" + "Chaining: " + chaining_count +"\n");
            System.out.println("--------------------------------------------------------------------------------");

            n=0; k=0;
            avl_count=0; splay_count=0; chaining_count=0; address_count=0;

            if(m==1000000){System.exit(0);}
        }
    }
}