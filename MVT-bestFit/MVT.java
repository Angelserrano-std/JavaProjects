public class MVT {

    private int mPrincipal;
    private int mSecundaria;

    private int[] memoriaPrincipal;
    private int[] memoriaSecundaria;

    public MVT(int mPrincipal, int mSecundaria){
        this.mPrincipal = mPrincipal;
        this.mSecundaria = mSecundaria;

        this.memoriaPrincipal = new int[mPrincipal];
        this.memoriaSecundaria = new int[mSecundaria];
    }


    public int getmPrincipal() { return mPrincipal;}
    public int getmSecundaria() { return mSecundaria;}

    public void mostrarMemorias() {

        System.out.println("memoria principal: ");
        for (int j : memoriaPrincipal) {
            System.out.print("[" + j + "]");
        }
        System.out.println();

        System.out.println("memoria secundaria: ");
        for (int j : memoriaSecundaria) {
            System.out.print("[" + j + "]");
        }
        System.out.println();
    }

    private int mejorPocision(int[]memoria, int tamaño) {
        int mejorInicio = -1;
        int mejorTamaño = memoria.length;
        int i = 0;

        while (i < memoria.length) {
            if (memoria[i] == 0) {
                int inicio = i;
                int libres = 0;

                while (i < memoria.length && memoria[i] == 0) {
                    libres++;
                    i++;
                }

                if (libres >= tamaño) {
                    if (mejorInicio == -1 || libres < mejorTamaño) {
                        mejorInicio = inicio;
                        mejorTamaño = libres;
                    }
                }

            } else { i++; }

        }
        return mejorInicio;
    }

    private boolean colocarProceso(int[]memoria, int id, int tamaño){

        int inicio = mejorPocision(memoria, tamaño);

        if (inicio == -1){ return false; }

        for(int i = inicio; i < inicio + tamaño; i++){ memoria[i] = id; }

        return true;

    }

    private boolean existeProceso(int[] memoria, int id) {
        for (int j : memoria) {
            if (j == id) {
                return true;
            }
        }
        return false;
    }

    public void registroProceso(int id, int tamaño){
        if (id <= 0 || tamaño <= 0){
            System.out.println("debe ser mayor a 0");
            return;
        }
        if (existeProceso(memoriaPrincipal, id) || existeProceso(memoriaSecundaria, id)){
            System.out.println(" ya existe");
        }

        if (colocarProceso(memoriaPrincipal, id, tamaño)){
            System.out.println(" registrado en la memoria principal");
        } else if(colocarProceso(memoriaSecundaria, id, tamaño)){
            System.out.println("registrado en la memoria secundaria");
        } else {
            System.out.println(" no alcanza en ningun lado");
        }
    }

    private boolean liberarProceso(int[]memoria, int id){
        boolean encontrado = false;

        for(int i = 0; i < memoria.length; i++){
            if (memoria[i] == id){
                memoria[i] = 0;
                encontrado = true;
            }
        }
        return encontrado;
    }

    public void eliminarProceso(int id) {
        if (id <= 0) {
            System.out.println("debe ser mayor que cero");
            return;
        }

        if (liberarProceso(memoriaPrincipal, id)) {
            System.out.println("proceso eliminado");
        } else if (liberarProceso(memoriaSecundaria, id)) {
            System.out.println("proceso eliminado");
        } else {
            System.out.println("el proceso no existe");
        }
    }

    private void compactar(int[]memoria){
        int destino = 0;

        for ( int i = 0; i < memoria.length; i++){
            if (memoria[i] !=0){
                memoria[destino] = memoria[i];
                destino++;
            }
        }

        for (int i = destino; i < memoria.length; i++){
            memoria[i] = 0;
        }
    }

    public void compactarMemoria(){
        compactar(memoriaPrincipal);
        compactar(memoriaSecundaria);
    }


    public void intercambio(int id){
        if (id <= 0){
            System.out.println("debe ser mayor que cero");
            return;
        }
        int[]origen;
        int[]destino;

        if(existeProceso(memoriaPrincipal, id)){
            origen = memoriaPrincipal;
            destino = memoriaSecundaria;
        } else if (existeProceso(memoriaSecundaria, id)){
            origen = memoriaSecundaria;
            destino = memoriaPrincipal;
        } else {
            System.out.println(" proceso no existe ");
            return;
        }

        int tamaño = 0;

        for(int i = 0; i < origen.length; i++){
            if(origen[i] == id){
                tamaño++;
            }
        }

        if(colocarProceso(destino,id,tamaño)){
            liberarProceso(origen,id);
            System.out.println("listo, se traslado");
        } else {
            System.out.println("no hay espacio, sigue donde mismo");
        }
    }



}
