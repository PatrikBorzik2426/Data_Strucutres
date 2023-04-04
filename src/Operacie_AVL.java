public class Operacie_AVL {
    public static Node_avl vloz(Node_avl prvok, int hodnota,String text)
    {
        if(prvok == null) {
            return new Node_avl(hodnota,text);      //vytovrí nový node, ktorý sa pridá do stromu
        }
        else if (prvok.data==hodnota) {             //vyhodnotí, že ide o duplikát
            //System.out.println("Duplikat");
            prvok.text="duplikat";
            return prvok;
        }
        else if (prvok.data<hodnota) {
            prvok.pravy= vloz(prvok.pravy,hodnota,text); // ak ide o hodnotu, ktorá je väčšia presunia sa na pravý node od predchádzajúceho
        }
        else {
            prvok.lavy=vloz(prvok.lavy,hodnota,text);   // ak ide o hodnotu, ktorá je menšia presunia sa na ľavý node od predchádzajúceho
        }

        return Prebalancovanie(prvok); // rekurzívne sa všetky predošlé nody v strome budú pre-balancovávať, aby strom ostal vyvážený
    }

    public static void aktualizuj_vysku(Node_avl prvok){
        prvok.vyska=1+Math.max(vyska(prvok.lavy),vyska(prvok.pravy));
    }

    public static int vyska(Node_avl prvok){
        if(prvok==null){
            return -1;
        }
        else {
            return prvok.vyska;
        }
    }

    public static Node_avl rotaciaRight(Node_avl prvok){
        Node_avl a = prvok.lavy;
        Node_avl c = a.pravy;

        a.pravy=prvok;
        prvok.lavy=c;

        aktualizuj_vysku(prvok);
        aktualizuj_vysku(a);

        return a;
    }
    public  static Node_avl rotaciaLeft(Node_avl prvok){
        Node_avl a = prvok.pravy;
        Node_avl c = a.lavy;

        a.lavy=prvok;
        prvok.pravy=c;

        aktualizuj_vysku(prvok);
        aktualizuj_vysku(a);

        return a;
    }
    public static int Balancovanie(Node_avl prvok){
        if(prvok == null){
            return 0;
        }else{
            return (vyska(prvok.pravy) - vyska(prvok.lavy));
        }
    }

    public static Node_avl najdi(Node_avl koren, int hladany){
        Node_avl pomocny=new Node_avl(0,null);
        if(koren==null){
            return null;        //v prípade, že strom neexistuje
        }

        int meranie_vysky= koren.vyska; // určenie výšky korena
        pomocny=koren;                  // vytvorenie pomocnej hodnoty

        while(pomocny.data!=hladany && meranie_vysky!=0) {  //cyklus, ktorý prehľadáva strom na základe veľkosti
            if (hladany > pomocny.data) {
                pomocny=pomocny.pravy;
                meranie_vysky--;
                if(pomocny==null){break;}
            } else if (hladany < pomocny.data) {
                pomocny=pomocny.lavy;
                meranie_vysky--;
                if(pomocny==null){break;}
            }
        }
        if(pomocny!= null && pomocny.data!=hladany){
            return null;
        }else{}
        return pomocny;
    }

    public static Node_avl Prebalancovanie(Node_avl prvok){
        aktualizuj_vysku(prvok);            //aktualizuje výšku prvku
        int balanc = Balancovanie(prvok);   //určí balance faktor
        if(balanc > 1){                     //na základe balance faktoru sa rozhodne o aký prípad ide a zvolí správnu rotáciu alebo jej kombináciu
            if(vyska(prvok.pravy.pravy) > vyska(prvok.pravy.lavy)){
                prvok = rotaciaLeft(prvok);
            }else {
                prvok.pravy=rotaciaRight(prvok.pravy);
                prvok=rotaciaLeft(prvok);
            }
        }else if(balanc<-1){
            if(vyska(prvok.lavy.lavy) > vyska(prvok.lavy.pravy)){
                prvok=rotaciaRight(prvok);
            }else {
                prvok.lavy=rotaciaLeft(prvok.lavy);
                prvok=rotaciaRight(prvok);
            }
        }
        return prvok; //vracia prvok po rotáciach
    }
    public static Node_avl zmazanie(Node_avl prvok,int hodnota) {
        Node_avl max = null;
        if (prvok == null) {                                    //lokalizujeme prvok na základe hodnoty prvku
            return prvok;
        } else if (hodnota > prvok.data) {
            prvok.pravy = zmazanie(prvok.pravy, hodnota);
        } else if (hodnota < prvok.data) {
            prvok.lavy = zmazanie(prvok.lavy, hodnota);
        } else if (prvok.data == hodnota) {                     //pri nájdení číselnej zhody následne detekuje štyri prípady
            if (prvok.lavy == null && prvok.pravy == null) {
                prvok = null;
            } else if (prvok.lavy == null && prvok.pravy != null) {
                prvok=prvok.pravy;
            } else if (prvok.lavy != null && prvok.pravy == null) {
                prvok = prvok.lavy;
            } else if (prvok.lavy != null && prvok.pravy != null) {
                max = najhlbsie(prvok.lavy);                    //hľadanie náhradu za seba
                prvok.data = max.data;
                prvok.lavy=zmazanie(prvok.lavy,prvok.data);    //výmena
            }
        }
        if(prvok!=null){
            Prebalancovanie(prvok);
        }
        return prvok;
    }

    public static Node_avl najhlbsie(Node_avl prvok){
        Node_avl max=null;
        int maximum=0;                                  //hľadanie najväčšieho pravého prvku v danom podstrome


        if (maximum < prvok.data) {
            maximum = prvok.data;
            max = prvok;
        }

        while(prvok.pravy!=null || prvok.lavy!=null) {
            if (prvok.pravy != null) {

                if (maximum < prvok.data) {
                    maximum = prvok.data;
                    max = prvok;
                }prvok = prvok.pravy;

            } else if (prvok.lavy != null) {

                if (maximum < prvok.data) {
                    maximum = prvok.data;
                    max = prvok;
                }prvok = prvok.lavy;
            }
        }


        if (maximum < prvok.data) {
            maximum = prvok.data;
            max = prvok;
        }



        return max;
    }
    
}
