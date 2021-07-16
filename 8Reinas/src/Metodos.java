public class Metodos 
{
    Object[][] vector = new Object[2][8];
    Object[][] tabu = new Object[7][];
    Object[][] soluciones = new Object[28][3];
    char[][] tablero = new char[8][8];
    char []letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    char valor1;
    char valor2;
    int numtabla;
    private int movimientos;
    private char reina1[] = new char[28];
    private char reina2[] = new char[28];
    private int pasos[] = new int[28];
    public int colision;
    public Object []reinasCambiadas = new Object[2];
    
    
    public Metodos()
    {
        this.movimientos = this.colision = 0;
    }
    
    public void configInicialVector(){
        vector[0][0] = 'A';
        vector[0][1] = 'B';
        vector[0][2] = 'C';
        vector[0][3] = 'D';
        vector[0][4] = 'E';
        vector[0][5] = 'F';
        vector[0][6] = 'G';
        vector[0][7] = 'H';
        vector[1][0] = 8;
        vector[1][1] = 4;
        vector[1][2] = 7;
        vector[1][3] = 5;
        vector[1][4] = 3;
        vector[1][5] = 1;
        vector[1][6] = 6;
        vector[1][7] = 2;
    }
    
    public void inicializaSoluciones(){
        for(int i=0; i<28; i++)
            for(int j=0; j<3; j++)
                soluciones[i][j] = 0;
    }
    
    public void inicializaTabu(){
        tabu[0] = new Object[7];
        tabu[1] = new Object[6];
        tabu[2] = new Object[5];
        tabu[3] = new Object[4];
        tabu[4] = new Object[3];
        tabu[5] = new Object[2];
        tabu[6] = new Object[1];
        for(int i=0; i<7; i++)
            for(int j=0; j<tabu[i].length; j++)
                tabu[i][j] = 0;
        showStructTabu();
    }
    
    public char [][]inicializatablero(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++)
                tablero[i][j] = ' ';
        }
        Object[][] vecNum = new Object[2][8];
        for(int i=0; i<8; i++){
            vecNum[0][i]=getPositionFromLetter(vector[0][i].toString());
            vecNum[1][i]=(int)vector[1][i] - 1;
        }
        tablero[(int)vecNum[1][0]][(int)vecNum[0][0]] = 'A';
        tablero[(int)vecNum[1][1]][(int)vecNum[0][1]] = 'B';
        tablero[(int)vecNum[1][2]][(int)vecNum[0][2]] = 'C';
        tablero[(int)vecNum[1][3]][(int)vecNum[0][3]] = 'D';
        tablero[(int)vecNum[1][4]][(int)vecNum[0][4]] = 'E';
        tablero[(int)vecNum[1][5]][(int)vecNum[0][5]] = 'F';
        tablero[(int)vecNum[1][6]][(int)vecNum[0][6]] = 'G';
        tablero[(int)vecNum[1][7]][(int)vecNum[0][7]] = 'H';
        cambiosTablero(tablero);
        System.out.println("\nConfiguración de la solución actual:");
        showVector();
        this.colision = colision();
        System.out.println("No. de colisiones presentes: "+ this.colision);
        return tablero;
    }
    
    public void cambiosTablero(char tablero[][]){
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero.length; j++){
                if(tablero[j][i] != ' '){
                    valor1 = tablero[j][i];
                    siguiente(i,j);
                }
                
            }
        }
        this.movimientos = 0;
    }
    
    public void siguiente(int c, int f){
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero.length; j++){
                if(tablero[j][i] != ' ' && i>c){
                    valor2 = tablero[j][i];
                    cambian(c,f,i,j);
                }
            }
        }
    }
    
    public void cambian(int c1,int f1,int c2,int f2){
        int valor;
        tablero[f1][c1] = ' ';
        tablero[f1][c2] = valor1;
        tablero[f2][c1] = valor2;
        tablero[f2][c2] = ' ';
        this.reina1[this.movimientos]=valor1;
        this.reina2[this.movimientos]=valor2;
        numtabla++;
        valor = colision();
        this.pasos[this.movimientos]=valor;
        tablero[f1][c1] = valor1;
        tablero[f1][c2] = ' ';
        tablero[f2][c1] = ' ';
        tablero[f2][c2] = valor2;
        this.movimientos++;
    }
    
    private void ordena(){
        for(int i=0;i<28;i++){
            for(int j=0;j<28;j++){
                if(this.pasos[i]<this.pasos[j]){
                    int valor = this.pasos[i];char reina1=this.reina1[i];char reina2=this.reina2[i];
                    this.pasos[i]=this.pasos[j];this.reina1[i]=this.reina1[j];this.reina2[i]=this.reina2[j];
                    this.pasos[j]=valor;this.reina1[j]=reina1;this.reina2[j]=reina2;
                }
            }
        }
    }
    
    public void movimientos(){
        ordena();
        System.out.println("Las 10 mejores soluciones plausibles:");
        for(int i=0;i<10;i++){
            soluciones[i][0] = this.reina1[i];
            soluciones[i][1] = this.reina2[i];
            soluciones[i][2] = this.pasos[i] - this.colision;
            System.out.println(
                "["+this.soluciones[i][0]+"]"+
                "["+this.soluciones[i][1]+"]"+
                "["+this.soluciones[i][2]+"]"
            );
        }
    }
    
    public void movimientos2()
    {
    	ordena();
        System.out.println("Las 10 mejores soluciones plausibles:");
        int numSolMostradas = 1;
        for(int i=0;i<28;i++){
            soluciones[i][0] = this.reina1[i];
            soluciones[i][1] = this.reina2[i];
            soluciones[i][2] = this.pasos[i] - this.colision;
            if((int)soluciones[i][2] == 0 )
            	continue;
            System.out.println(
                "["+this.soluciones[i][0]+"]"+
                "["+this.soluciones[i][1]+"]"+
                "["+this.soluciones[i][2]+"]"
            );
            if(numSolMostradas == 10)
            	break;
            numSolMostradas++;
        }
    }
    
    public int colision(){
        int aux1,aux2,aux3,aux4;
        int cont=0,ocho=0,cuenta=0;
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero.length; j++){
                if(tablero[j][i] != ' '){
                    ocho = 0;
                    aux1 = j+1; aux2 = i+1; aux3 = j-1; aux4 = i+1;
                    switch(j){    
                        case 0:{
                            do{
                                if(aux2==8){
                                    break;
                                }
                                if(tablero[aux1][aux2] != ' '){
                                    cont = 1;
                                    cuenta++;
                                }
                                aux1++;
                                aux2++;
                                ocho++;
                            }while(ocho < 8 && cont != 1);
                            break;
                        }
                        case 7:{
                            do{
                                if(aux2==8){
                                    break;
                                }
                                if(tablero[aux3][aux4] != ' '){
                                    cont = 1;
                                    cuenta++;
                                }
                                aux3--;
                                aux4++;
                                ocho++;
                            }while(ocho < 8 && cont != 1 && aux3 != 0 && aux4 != 8);
                            break;
                        }
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:{
                            do{
                                if(aux2==8){
                                    break;
                                }else if(tablero[aux1][aux2] != ' '){
                                    cont = 1;
                                    cuenta++;
                                    aux1 = 8;
                                }
                                aux1++;
                                aux2++;
                                ocho++;
                            }while(cont != 1 && aux1 < 8 && aux2 < 8 && ocho != 8);
                            cont = 0;
                            aux1 = 0;
                            aux2 = 0;
                            ocho = 0;
                            do{
                                if(aux4==8){
                                    break;
                                }
                                if(tablero[aux3][aux4] != ' '){
                                    cont = 1;
                                    cuenta++;
                                    aux4 = 8;
                                }
                                aux3--;
                                aux4++;
                                ocho++;
                            }while(cont != 1 && aux3 > -1 && aux4 < 8 && ocho < 8);
                            cont = 0;
                            aux1 = 0;
                            aux2 = 0;
                            aux3 = 0;
                            aux4 = 0;
                            ocho = 0;
                            break;
                        }
                    }
                    cont=0;
                }
            }
        }
        return cuenta;
    }
    
    public char[][] seleccionarSolucion()
    {
        char [][] tableroTemp = new char[8][8];
        for(int i = 0; i < 28; i++)
            if( (int)this.soluciones[i][2] != 0 )
            {
                tableroTemp = actualizarSolActual(i);
                if(tableroTemp == null)
                    continue;
                break;
            }
        return tableroTemp;
    }
    
    public char[][] actualizarSolActual(int solSeleccionada)
    {
        int firstPos = getPositionFromLetter(this.soluciones[solSeleccionada][0].toString());
        int secondPos = getPositionFromLetter(this.soluciones[solSeleccionada][1].toString());
        secondPos = posicionarEnTabu(firstPos, secondPos);
        if((int)tabu[firstPos][secondPos] > 0)
            return null;
        for(int i = 0; i < 8; i++)
            if(vector[0][i] == soluciones[solSeleccionada][0])
            {
                Object temp;
                temp = vector[1][i];
                int posSegundaReina = 0;
                for(int j = 0; j < 8; j++)
                    if(vector[0][j] == soluciones[solSeleccionada][1])
                    {    
                        posSegundaReina = j;
                        break;
                    }
                vector[1][i] = vector[1][posSegundaReina];
                vector[1][posSegundaReina] = temp;
                this.reinasCambiadas[0] = soluciones[solSeleccionada][0];
                this.reinasCambiadas[1] = soluciones[solSeleccionada][1];
                tablero = inicializatablero();
                actualizarEstructTabu(reinasCambiadas);
                break;
            }
        return tablero;
    }
    
    public void actualizarEstructTabu(Object []reinasCambiadas)
    {
        int pos1 = getPositionFromLetter(reinasCambiadas[0].toString());
        int pos2 = getPositionFromLetter(reinasCambiadas[1].toString());
        pos2 = posicionarEnTabu(pos1, pos2);
        tabu[pos1][pos2] = 4;
        showStructTabu();
    }
    
    public int getPositionFromLetter(String letra)
    {
        switch(letra)
        {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
        }      
        return 8; //No tiene uso alguno xd
    }

    public int posicionarEnTabu(int frstPos, int scndPos)
    {
        switch(frstPos)
        {
            case 1:
                scndPos -= 2;
                return scndPos;
            case 2:
                scndPos -= 3;
                return scndPos;
            case 3:
                scndPos -= 4;
                return scndPos;
            case 4:
                scndPos -= 5;
                return scndPos;
            case 5:
                scndPos -= 6;
                return scndPos;
            case 6:
                scndPos -= 7;
                return scndPos;
            case 7:
                scndPos -= 8;
                return scndPos;
            default:
                return scndPos; //No lo altera
        }
    }
    
    public void showVector()
    {
        for(int i=0; i<2; i++) {
            for(int j=0; j<8; j++) {
                System.out.print("[" + vector[i][j] + "]");;
            }
            System.out.println("");
        }

    }
    
    public void showStructTabu()
    {
        System.out.println("\nEstructura Tabú");
        System.out.println("   B  C  D  E  F  G  H");
        int aux = 3;
        for(int i=0; i<7; i++){
        	System.out.print(letras[i] + " ");
            for(int j=0; j<tabu[i].length; j++){
                System.out.print("[" + tabu[i][j] + "]");
            }
            System.out.println("");
            for(int k=0; k<aux; k++)
                System.out.print(" ");
            aux+=3;
        }
        System.out.println();
    }

    public void quitUnoATabu() //Cada que se realice una iteraciÃ³n se le quitara 1 a todos los campos mayores a 0
    {
        for(int i = 0; i < this.tabu.length; i++)
            for(int j = 0; j < this.tabu[i].length; j++)
                if((int)this.tabu[i][j] > 0)
                    this.tabu[i][j] = (int)this.tabu[i][j] - 1;
    }
}