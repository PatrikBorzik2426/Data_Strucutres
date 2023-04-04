import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;
import java.math.*;
import java.util.stream.BaseStream;

public class Operacie_adresovanie {
    public static int hashujem_adresovanie(String text, int velkost_tabulky) {
        int hash = 0;

        hash = (text.hashCode() * 31) % velkost_tabulky;        //vytvorenie indexu
        if (hash < 0) {
            hash = Math.abs(hash);                              //v prípade záporných hodnôt sa nám zmení hodnota indexu na kladnú
        }
        return hash;                                            //vrátenie indexu
    }
    public static Node_hash[] zmensi_adresovanie(Node_hash[] array, int velkost_pola, int index, Node_hash prvok){  //funkcia na zmenšenie tabuľky
        int x=0;
        int nulovy=0;
        Node_hash [] new_array=new Node_hash[(int) (velkost_pola*0.75)];    // vytvoríme novú tabuľku s veľkosťou rovnej 75% pôvodnej

        while(x!=array.length){                        //cyklus, ktorý nám skontroluje koľko indexov je v tabuľke nulových indexov
            if(array[x]==null){
                nulovy++;
            }
            x++;
        }

        if(nulovy>(velkost_pola*0.3)) {               //ak je nulových indexov viac než je 30% celej tabuľky, prehashujeme pôvodnú tabuľku do novej
            for (int l = 0; l < velkost_pola; l++) {
                new_array = prehashuj_adresovanie(new_array, array[l]);     //prehashujeme všetky prvky zo starej do novej tabuľky
            }
            new_array = prehashuj_adresovanie(new_array, prvok);            //pridá najnovší prvok do novej tabuľky
            return new_array;
        }

        return array;


    }
    public static Node_hash[] zvacsi_adresovanie(Node_hash[] array, int velkost_pola, int index,Node_hash prvok){
        int x=0;
        Node_hash [] zvacseny_array=new Node_hash[index+(int)(velkost_pola*0.1)+2]; //vytvoríme tabuľku veľkú ako je nový index (ktorý je väčší než veľkosť tabuľky) s malou rezervou

        while (x<velkost_pola) {
            zvacseny_array = prehashuj_adresovanie(zvacseny_array, array[x]);   //prehashujeme prvky zo starej do novej tabuľky
            x++;
        }
        zvacseny_array=prehashuj_adresovanie(zvacseny_array,prvok);             //pridá najnovší prvok do novej tabuľky

        return zvacseny_array;
    }
    
    public static Node_hash[] prehashuj_adresovanie(Node_hash[] array, Node_hash prvok){ //podobná ako funkcia vlož, len samostatne odelená, bez zväčšovania a zmenšovania
        int index=0;

        Node_hash prvok_pomocny;
        Node_hash povodny;

        if(prvok != null) {

            index = hashujem_adresovanie(prvok.text, array.length);         //vygeneruje index prvku

            while(array[index]!=null){                                      //cyklus, ktorý hľadá miesto pre nový prvok
                if(array[index]!=null){
                    index=index+1;
                }
                if(index==array.length){index=0;}                           //pozerá od začiatku tabuľky v prípade, že nič nenašiel
            }

            array[index]=prvok;                                             //osadenie prvku do tabuľky

            return array;
        }else {
            return array;
        }
    }


    public static Node_hash[] vloz_adresovanie(String text, int hodnota, Node_hash[] array, int iteracia, int pocet_prvkov){
        int index=0; int hash=0;

        Node_hash prvok=new Node_hash(hodnota,text);                // vytvorenie nového prvku

        int velkost_tabulky=array.length;

        index=hashujem_adresovanie(prvok.text,velkost_tabulky);     // vygenerovanie indexu v tabuľke
        hash=index;

        if(index>array.length-1) {
            array = zvacsi_adresovanie(array, array.length, index,prvok);       // zväčšovanie tabuľky v prípade väčšieho indexu
            return array;
        }

        if(iteracia==pocet_prvkov/2 || iteracia==(int)(pocet_prvkov*0.9)){      //zmenšovanie tabuľky
            array=zmensi_adresovanie(array, array.length,index,prvok);          // zmenšenie tabuľky v prípade istej interácie vkladania prvku
            return array;
        }

        while(array[index]!=null){                                              //hľadanie miesta pre nový prvok
            if(array[index]!=null){
                index=index+1;
                if(index==hash){
                    array=zvacsi_adresovanie(array, array.length, array.length, prvok);     //zmena veľkosti pre prípad, že sa vrátime index na pôvodný index (potrebujeme zväčšiť tabuľku)
                    return  array;};
                if(index==array.length){index=0;}
            }
        }

        array[index]=prvok;                                                     //osadíme prvok

        return array;
    }

    public static Node_hash hladaj_adresovanie(Node_hash[] array,String text){
        int hash=0;
        Node_hash hashulo=null;
        int index=hashujem_adresovanie(text,array.length);     //vytvorenie indexu pre hľadanú hodnotu
        hash=index;

        hashulo=array[hash];
        if(hashulo==null){
            System.out.println("Prvok neexistuje v Adresovaní!");         // prípad, keď na danom indexe nie je nič
            return null;
        }
        while(hashulo.text!=text){        //prehľadávanie indexu ak nemá hodnotu null
            hash=hash+1;
            if(hash==array.length){hash=0;}
            if(hashulo==null){break;}
            hashulo=array[hash];


            if(hash==index || hashulo==null){                 //vyhodnotenie ak sa prvok nenájde po prejdení celej tabuľky alebo nájdenia indexu s hodnotou null
                System.out.println("Prvok neexistuje v Adresovaní!");
                return  null;
            }
        }

        return hashulo;
    }

    public static Node_hash[] zmazanie_adresovanie (Node_hash[] array, String text){
        int index=hashujem_adresovanie(text, array.length);             //vytvorenie indexu v tabuľke pre prvok, ktorž chceme vymazať
        int hash=index;
        Node_hash hashulo=array[hash];

        if(hashulo==null){
           // System.out.println("Prvok neexistuje");                 //vyhodnotenie situácie ak na danej hodnote je nulový prvok
            return array;
        }

        if(hashulo.text==text){
            array[hash]=new Node_hash(0,"VYMAZANY");        //ak sa prvok, ktorý chceme vymazať hneď nájde, tak sa nahradí za označenie vymazaného prvku
            return array;
        }else {
            while(hashulo.text!=text){
                hash=hash+1;                                        //prechádzanie tabuľkou
                hashulo=array[hash];
                if(hash>array.length){
                    hash=0;
                }
                if(hashulo==null || hash==index){
                   // System.out.println("Prvok sa v tabuľke nenachádza!");       // ak prejdeme celú tabuľku alebo nájdeme nulový index tak vyhodnotíme, že prvok v tabuľke nie je
                    return array;
                }
            }
            array[hash]=new Node_hash(0,"VYMAZANY");
        }

        return array;
    }
}
