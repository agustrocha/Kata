import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.Integer.*;
import static java.lang.Integer.parseInt;

public class StringCalculator {

    public StringCalculator() {
    }

    public static String add(String text) {

        if(text.isEmpty()){
            return "0";
        }

        boolean doesntContainComma = !text.contains(",");
        boolean doesntContainNewLine = !text.contains("\n");

        if(doesntContainComma && doesntContainNewLine){
            return text;
        }

        if(hasTooMuchCommas(text)){
            return "Number expected but EOF found.";
        }

        String[] cadenaNumeros = text.split("[,\n]");


        if(isDecimal(cadenaNumeros)){
            double[] nums = Arrays.stream(text.substring(0, text.length()).split("[,\n]"))
                    .map(String::trim).mapToDouble(Double::parseDouble).toArray();
    
            double sum = round(DoubleStream.of(nums).sum());
    
            return String.valueOf(sum);

        } else {
            int suma = 0;

            for (String cadenaNumero : cadenaNumeros) {
                suma += parseInt(cadenaNumero);
            }

            return String.valueOf(suma);
        }
    }

    public static boolean isDecimal(String[] cad){

        for (String cadena : cad) {
            if (cadena.contains(".")) {
                return true;
            }
        }

        return false;
    }

    public static double round(Double d){
        return new BigDecimal(d).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static boolean hasTooMuchCommas(String cad){

        return cad.contains(",,") || (cad.charAt(0) == ',') || (cad.charAt(cad.length() - 1) == ',');

    }
}
