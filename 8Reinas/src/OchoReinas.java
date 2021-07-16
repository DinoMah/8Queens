/*
 * Hecho en Eclipse
 * Equipo:
 * 	Daniel Chávez Flores
 * 	Juan Carlos Herrera
 * 	Berenice Morones Alba
 * */

public class OchoReinas {
    public static void main(String[] args) {    
        Metodos metodos = new Metodos();
        int numIter = 0;
        System.out.println("No. de iteración: " + numIter);
        metodos.configInicialVector();
        metodos.inicializatablero();
        metodos.inicializaTabu();
        metodos.inicializaSoluciones();
        metodos.movimientos();
        numIter++;
        while(true)
        {
        	System.out.println("\nNo. de iteración: " + numIter);
        	metodos.seleccionarSolucion();
            metodos.movimientos(); //Muestra la tabla de sol. plausibles
            System.out.println("Intercambio seleccionado: " + metodos.reinasCambiadas[0] + " con " + metodos.reinasCambiadas[1]);
            metodos.quitUnoATabu();
            if(metodos.colision == 0)
                break;
            else
            	numIter++;
        }
    }
}
