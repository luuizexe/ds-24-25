package e2;

public class KeyPad {
    public static boolean isValidKeyPad(char[][] keyPad) {
        //Si el string es nulo o comienza con un nulo, devolvemos false
        if (keyPad == null || keyPad[0] == null) {
            return false;
        }
        int numCols = keyPad[0].length;

        // Si alguna fila aparte de la primera es nula, devolvemos false
        for (int i = 1; i < keyPad.length; i++) {
            if (keyPad[i] == null || keyPad[i].length != numCols) return false;

        }

        //Si el primer caracter no es 1, devolvemos false
        if (keyPad[0][0] != '1') return false;

        //Creamos un string de referencia con el orden adecuado
        String secuencia = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int cont = 0;
        boolean noEstaEnFilas = false;

        //Bucle para recorrerlo en filas
        for (char[] fila : keyPad) {
            for (char col : fila) {
                if (cont >= secuencia.length() || col != secuencia.charAt(cont)) {
                    noEstaEnFilas = true;
                    cont = 0;
                    break;
                }
                cont++;
            }
        }
        // Si no esta en filas, hacemos otro bucle para recorrerlo por columnas
        if (noEstaEnFilas) {
            for (int j = 0; j < keyPad[0].length; j++) {
                for (char[] fila : keyPad) {
                    //Si no esta ni en filas ni en columnas, devolvemos false
                    if (cont >= secuencia.length() || fila[j] != secuencia.charAt(cont)) return false;
                    cont++;
                }
            }
        }
        return true;
    }

    public static boolean areValidMovements(String[] movs) {
        // Si el array es nulo devolvemos false
        if (movs == null) return false;

        for (String mov : movs) {
            // Si algun elemento del array es nulo devolvemos false
            if (mov == null) return false;

            // Recorremos los movimientos para comprobar si son validos
            for (int j = 0; j < mov.length(); j++) {
                if (mov.charAt(j) != 'D' && mov.charAt(j) != 'L' && mov.charAt(j) != 'U' && mov.charAt(j) != 'R')
                    return false;
            }
        }
        return true;
    }

    public static String obtainCode(char[][] keyPad, String[] movs) {
        // Si los movimientos o el keypad no son validos, devolvemos false
        if (!areValidMovements(movs) || !isValidKeyPad(keyPad)) {
            throw new IllegalArgumentException();
        }

        // Declaramos variables
        StringBuilder code = new StringBuilder();
        int col = 0, fila = 0;

        //Recorremos los movimientos para obtener el codigo
        for (String mov : movs) {
            for (int j = 0; j < mov.length(); j++) {
                if (mov.charAt(j) == 'R' && col < keyPad[0].length - 1) {
                    ++col;
                } else if (mov.charAt(j) == 'L' && col > 0) {
                    --col;
                } else if (mov.charAt(j) == 'U' && fila > 0) {
                    --fila;
                } else if (mov.charAt(j) == 'D' && fila < keyPad.length - 1) {
                    ++fila;
                }
            }
            code.append(keyPad[fila][col]);
        }
        return code.toString();
    }
}