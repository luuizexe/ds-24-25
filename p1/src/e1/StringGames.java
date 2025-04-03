package e1;

public class StringGames {
    public static String bestCharacters(String word1, String word2) {
        // Declaracion de variables
        int pmay1 = 0, pmay2 = 0, pmin1 = 0, pmin2 = 0, pdig1 = 0, pdig2 = 0;
        int puntos1 = 0, puntos2 = 0;
        char c1, c2;

        // Error: los strings no son de la misma longitud
        if (word1.length() != word2.length()) {
            throw new IllegalArgumentException();
        }

        // Recorremos las palabras para saber quien gana cada categoria
        for (int i = 0; i < word1.length(); i++) {
            c1 = word1.charAt(i);
            c2 = word2.charAt(i);

            //  Sumamos +1 a los contadores de mayusculas, minusculas y numeros de cada uno
            if (Character.isDigit(c1)) pdig1++;
            else if (Character.isUpperCase(c1)) pmay1++;
            else if (Character.isLowerCase(c1)) pmin1++;

            if (Character.isDigit(c2)) pdig2++;
            else if (Character.isUpperCase(c2)) pmay2++;
            else if (Character.isLowerCase(c2)) pmin2++;
        }

        // Sumamos puntos a quien gane en cada categoria
        if (pmay1 > pmay2) {
            puntos1++;
        } else if (pmay1 < pmay2) {
            puntos2++;
        }

        if (pmin1 > pmin2) {
            puntos1++;
        } else if (pmin1 < pmin2) {
            puntos2++;
        }

        if (pdig1 > pdig2) {
            puntos1++;
        } else if (pdig1 < pdig2) {
            puntos2++;
        }

        // Declaramos ganador y sacamos datos
        return ((puntos1 >= puntos2) ? word1 : word2);
    }

    // Funcion que determina de cuantas formas se pueden poner 2 palabras en un crucigrama
    public static int crossingWords(String word1, String word2) {
        int num = 0;
        for (int i = 0; i < word1.length(); i++) {
            char l = word1.charAt(i);
            for (int j = 0; j < word2.length(); j++) {
                if (l == word2.charAt(j)) {
                    num++;
                }
            }
        }
        return num;
    }

    // Funcion para reordenar una palabra segun un abecedario en un orden determinado
    public static String wackyAlphabet(String word1, String word2) {
        if (esAbecedarioCorrecto(word2)) {
            // Declaramos un string auxiliar para insertar los caracteres que se encuentren
            StringBuilder aux = new StringBuilder();

            // Reordenamos la primera palabra
            for (int i = 0; i < word2.length(); i++) {
                char l = word2.charAt(i);
                for (int j = 0; j < word1.length(); j++) {
                    if (l == word1.charAt(j)) {
                        aux.append(l);
                    }
                }
            }
            return aux.toString();
        } else {
            // Si no es abecedario correcto, da error

            throw new IllegalArgumentException();
        }

    }

    // Funcion que verifica que no se repitan las letras del abecedario y esten todas
    public static boolean esAbecedarioCorrecto(String word) {
        if (word == null) return false;

        int cont;
        // Abecedario base:
        String abecedario = "abcdefghijklmnopqrstuvwxyz";

        // Doble bucle para comparar ambos abecedarios
        for (int i = 0; i < abecedario.length(); i++) {
            char l = abecedario.charAt(i);
            cont = 0;
            for (int j = 0; j < word.length(); j++) {
                char k = word.charAt(j);
                if (l == k) {
                    cont++;
                }
            }
            if (cont != 1) return false;
        }
        return true;
    }
}