import java.util.ArrayList;

public class Operacie_chaining {
    public static int hashujem_chain(String text, int velkost_tabulky){ //hashovacia funkcia
        int hash = 0;

        hash = (text.hashCode() * 31) % velkost_tabulky;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


    public static Node_hash[] vloz_chain(String text, int hodnota, Node_hash[] array, int iteracia, int pocet_prvkov){
        int index=0;
        Node_hash prvok_pomocny;
        Node_hash povodny;

        Node_hash prvok=new Node_hash(hodnota,text);        // vytvorenie nového prvku


        int velkost_tabulky=array.length;       //veľkosť tabuľky

        index=hashujem_chain(prvok.text,velkost_tabulky);       //vygenerovanie indexu

        if(index>array.length-1) {
            array = zvacsi(array, array.length, index,prvok); //v prípade potreby zväčšenia sa zväčší tabuľka
            return array;
        }

        if(iteracia==pocet_prvkov/2 || iteracia==(int)(pocet_prvkov*0.9)){
            array= zmensi(array, array.length, index, prvok);           //v polovici pridávania a v 90% pridávania prvkov sa skontroluje či nie je potrebné zmenšenie tabuľky
            return array;
        }


        prvok_pomocny=array[index];
        povodny=prvok_pomocny;

        if(prvok_pomocny!=null){                                //overenie voľného indexu
            while (prvok_pomocny.kamos!=null){
                prvok_pomocny=prvok_pomocny.kamos;              //presun po spájanom zozname
            }
            prvok_pomocny.kamos=prvok;                          //osadnie nového prvku na koniec zoznamu
            array[index]=povodny;                               //vloženie zoznamu na index
        }else {
            array[index]=prvok;
        }

        return array;
    }

    public static Node_hash[] zmensi(Node_hash[] array, int velkost_pola, int index, Node_hash prvok){
        int x=0;
        int nulovy=0;
        Node_hash [] new_array=new Node_hash[(int) (velkost_pola*0.75)];

        while(x!=array.length){
            if(array[x]==null){
                nulovy++;
            }
            x++;
        }

        if(nulovy>0.3* array.length) {
            for (int l = 0; l < array.length; l++) {
                new_array = prehashuj(new_array, array[l]);
            }
            new_array = prehashuj(new_array, prvok);
            return new_array;
        }

        return array;


    }
    public static Node_hash[] zvacsi(Node_hash[] array, int velkost_pola, int index,Node_hash prvok){
        int x=0;
        Node_hash [] zvacseny_array=new Node_hash[index+(int)(velkost_pola*0.1)+2];

        while (x!=velkost_pola) {
            zvacseny_array = prehashuj(zvacseny_array, array[x]);
            x++;
        }
        zvacseny_array=prehashuj(zvacseny_array,prvok);

        return zvacseny_array;
    }

    public static Node_hash[] prehashuj(Node_hash[] array, Node_hash prvok){
        int index=0;                                            //prehashovanie prvkov zo starej do novej tabuľky

        Node_hash prvok_pomocny;
        Node_hash povodny;

        if(prvok != null) {

            index = hashujem_chain(prvok.text, array.length);

            prvok_pomocny = array[index];
            povodny = prvok_pomocny;

            if (prvok_pomocny != null) {
                while (prvok_pomocny.kamos != null) {
                    prvok_pomocny = prvok_pomocny.kamos;
                }
                prvok_pomocny.kamos = prvok;
                array[index] = povodny;
            } else {
                array[index] = prvok;
            }

            return array;
        }else {
            return array;
        }
    }


    public static Node_hash hladaj_chain(Node_hash[] array,String text){
        int hash=0;
        Node_hash hashulo=null;
        hash=hashujem_chain(text, array.length); //vytvorenie indexu hľadaného prvku
        hashulo=array[hash];

        if(hashulo==null){
            return null;            //pre prípad indexu s null hodnotou
        }
        while(hashulo.text!=text){
            hashulo=hashulo.kamos;  //posun po spájanom zozname
            if(hashulo==null){;
                return null;
            }
        }
        return hashulo;
    }

    public static Node_hash[] zmazanie_chain (Node_hash[] array, String text){
        int hash=hashujem_chain(text, array.length); //vytvorenie indexu prvku na vymazanie
        Node_hash hashulo=array[hash];
        Node_hash povodny=hashulo;

        if(hashulo==null){
            return null;            //pre prípad indexu s null hodnotou
        }

        if(hashulo.text==text){
            array[hash]=hashulo.kamos;  //pre prípad, že prvok na zmazanie bude hlavička spájaného zoznamu
        }else {

            while (hashulo.kamos.text != text) {
                hashulo = hashulo.kamos;    //posun po spájanom zozname
                if(hashulo.kamos==null){;
                    return array;
                }
            }
            hashulo.kamos = hashulo.kamos.kamos;

            array[hash]=povodny;            //osadenie nové spájaného zoznamu
        }

        return array;
    }}