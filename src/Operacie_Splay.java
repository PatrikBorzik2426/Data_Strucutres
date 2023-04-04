public class Operacie_Splay {
    public static Node_splay vloz(Node_splay prvok,int hodnota, String text){
        Node_splay pomocny=prvok;       //uložíme si vstupuný koreň stromu
        Node_splay predosly=null;       //pomocny node pre rodičovský prvol
        while(prvok!=null){
            if(hodnota>prvok.data){     //ak je hodnota väčšia posúvame prvok doprava
                predosly=prvok;
                if(prvok.pravy==null) {
                    prvok.pravy = new Node_splay(hodnota, text); //vytvorenie nového prvku
                    prvok.pravy.rodic = predosly;
                    return splaying(prvok.pravy);               //splaying
                }else {
                    prvok = prvok.pravy;                        //posun doprava
                }
            } else if (hodnota< prvok.data) {       // ak je hodnota nižšia posúvame prvok doľava
                predosly=prvok;
                if(prvok.lavy==null){
                    prvok.lavy=new Node_splay(hodnota,text);        //vytvorenie nového prvku
                    prvok.lavy.rodic=predosly;
                    return splaying(prvok.lavy);                    //splaying
                }else{
                    prvok=prvok.lavy;                               //posun doľava
                }
            }else
            {
                //System.out.println("Duplikat");
                return pomocny;                                     //vráti pôvodný vstupný koreň
            }
        }


        prvok=new Node_splay(hodnota,text);                         //ak ešte nie je koreň vytvorí nový koreň
        prvok.rodic=predosly;
        return splaying(prvok);

    }
    public static Node_splay Zig(Node_splay prvok){
        Node_splay a = prvok.lavy;
        Node_splay c = a.pravy;

        a.pravy=prvok;
        prvok.lavy=c;

        a.rodic = prvok.rodic;
        prvok.rodic=a;
        if(c!=null){
            c.rodic=prvok;
        }

        return a;
    }

    public  static Node_splay Zag(Node_splay prvok){
        Node_splay a = prvok.pravy;
        Node_splay c = a.lavy;

        a.lavy=prvok;
        prvok.pravy=c;

        a.rodic = prvok.rodic;
        prvok.rodic=a;
        if(c!=null){
            c.rodic=prvok;
        }

        return a;
    }

    public static Node_splay splaying(Node_splay prvok){

        while(prvok.rodic!=null){
            if(prvok.rodic.rodic==null) {           //ak sa nachádza rovno pod koreňom, tak spravý najjednoduchšiu rotáciu Zig alebo Zag
                if (prvok.rodic.lavy == prvok ) {
                    prvok = Zig(prvok.rodic);       //rotácia Zig
                } else if (prvok.rodic.pravy == prvok) {
                    prvok = Zag(prvok.rodic);       //rotácia Zag
                }
            }else {
                if (prvok.rodic.rodic.lavy == prvok.rodic && prvok.rodic.lavy == prvok) {  // prípad, kde sa prvok nachádza v ľavej "línií" od svojho rodiča a jeho rodič od svojho rodiča
                    prvok = Zig(prvok.rodic.rodic);
                    prvok = Zig(prvok);

                    if(prvok.rodic!=null) {                     //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                        if(prvok.data>prvok.rodic.data){
                            prvok.rodic.pravy=prvok;
                        }else {
                            prvok.rodic.lavy = prvok;
                        }
                    }
                } else if (prvok.rodic.rodic.pravy == prvok.rodic && prvok.rodic.pravy == prvok) { //prípad, kde sa prvok nachádza v pravej "linií" etc.
                    prvok = Zag(prvok.rodic.rodic);
                    prvok = Zag(prvok);

                    if(prvok.rodic!=null) {
                        if(prvok.data<prvok.rodic.data){        //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                            prvok.rodic.lavy=prvok;
                        }else {
                            prvok.rodic.pravy = prvok;
                        }
                    }
                } else if (prvok.rodic.rodic.pravy == prvok.rodic && prvok.rodic.lavy == prvok) { //prípad, ktorý je variáciou "Right-Left" rotácie v AVL strome"
                    prvok = Zig(prvok.rodic);
                    if(prvok.rodic!=null) {
                        if(prvok.data<prvok.rodic.data){        //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                            prvok.rodic.lavy=prvok;
                        }else {
                            prvok.rodic.pravy = prvok;
                        }
                    }
                    prvok = Zag(prvok.rodic);

                    if(prvok.rodic!=null) {
                        if(prvok.data<prvok.rodic.data){        //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                            prvok.rodic.lavy=prvok;
                        }else {
                            prvok.rodic.pravy = prvok;
                        }
                    }
                } else if (prvok.rodic.rodic.lavy == prvok.rodic && prvok.rodic.pravy == prvok) {   //prípad, ktorý je variáciou "Left-Right" rotácie v AVL strome"
                    prvok = Zag(prvok.rodic);
                    if(prvok.rodic!=null) {
                        if(prvok.data>prvok.rodic.data){        //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                            prvok.rodic.pravy=prvok;
                        }else {
                            prvok.rodic.lavy = prvok;
                        }
                    }
                    prvok = Zig(prvok.rodic);

                    if(prvok.rodic!=null) {
                        if(prvok.data>prvok.rodic.data){        //pre pírpad, kde sa stratí pôvodny prvok umiestni sa po rotácií na miesto určené jeho hodnotou
                            prvok.rodic.pravy=prvok;
                        }else {
                            prvok.rodic.lavy = prvok;
                        }
                    }
                }
            }
        }
        return prvok;
    }


    public static Node_splay najdi(Node_splay koren, int hladany){
        Node_splay pomocny=null;           //uloženie pôvodného koreňa
        if(koren==null ||koren.data==hladany){
            return koren;
        }

        while(koren.data!=hladany) {                    //začiatok cyklu
            if (hladany > koren.data) {                 //v prípade ,že hodnota je väčšia ako má prehľadávaný prvok
                koren=koren.pravy;
                if(koren!=null) {
                    if (koren.data == hladany) {
                        //System.out.println("Našiel som! --> " + hladany);
                        return splaying(koren);
                    }
                }else{
                    System.out.println("Nenašlo sa v Splay!");  //ak má prehľadávaný prvok null hodnotu, tak vypíše hlášku
                    return  pomocny;}
            } else if (hladany < koren.data) {          //v prípade, že hodnota je menšia než prehľadávaný prvok
                koren=koren.lavy;
                if(koren!=null) {
                    if (koren.data == hladany) {
                        //System.out.println("Našiel som! ");
                        return splaying(koren);
                    }
                }else {
                    System.out.println("Nenašlo sa!");
                    return pomocny;}
            }
        }

        return koren;
    }

    public static Node_splay zmazanie (Node_splay koren, int hladany){

        Node_splay na_vymazanie=najdi(koren,hladany);               //vyhľadá prvok na vymazanie
        if(na_vymazanie==null){
            System.out.println("Prvok neexistuje!");
            return koren;
        }

        if(na_vymazanie.lavy!=null ||na_vymazanie.pravy!=null) {
            Node_splay novy_koren = na_vymazanie.lavy;
            Node_splay pravy_od_korena = na_vymazanie.pravy;

            na_vymazanie = null;

            novy_koren = splaying(najhlbsie(novy_koren));           //nájdený prvok, ktorý slúži ako náhradna sa splayne na koreň

            novy_koren.pravy = pravy_od_korena;
            if (pravy_od_korena != null) {
                pravy_od_korena.rodic = novy_koren;
            }

            return novy_koren;
        }else{
            na_vymazanie=null;
            return null;
        }
    }

    public static Node_splay najhlbsie(Node_splay prvok){ //funkcia ktorá nájde najväčší prvok v ľavom podstrome
        Node_splay max=null;
        int maximum=0;


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
