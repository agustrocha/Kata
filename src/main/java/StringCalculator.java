import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.Double.*;
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

        String[] numbersString = text.split("[,\n]");

        if (isNegative(numbersString)){
            String negativeNumber = "";
            for (String s : numbersString) {
                if (parseInt(s) < 0) {
                    negativeNumber += s;
                }
            }
            return "Negative not allowed: " + negativeNumber;
        }

        if(isDecimal(numbersString)){
            double[] nums = Arrays.stream(text.substring(0, text.length()).split("[,\n]"))
                    .map(String::trim).mapToDouble(Double::parseDouble).toArray();
    
            double sum = round(DoubleStream.of(nums).sum());
    
            return String.valueOf(sum);

        } else {
            int sum = 0;

            for (String numberString : numbersString) {
                sum += parseInt(numberString);
            }

            return String.valueOf(sum);
        }
    }

    public static boolean isDecimal(String[] s){
        for (String string : s) {
            if (string.contains(".")) {
                return true;
            }
        }
        return false;
    }

    public static double round(Double d){
        return new BigDecimal(d).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static boolean hasTooMuchCommas(String s){
        return s.contains(",,") || (s.charAt(0) == ',') || (s.charAt(s.length() - 1) == ',');
    }

    public static boolean isNegative(String[] s){

        if(isDecimal(s)){
            for (String value : s) {
                double negativeNum = parseDouble(value);
                if (negativeNum < 0) {
                    return true;
                }
            }
        } else {
            for (String value : s) {
                int negativeNum = parseInt(value);
                if (negativeNum < 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
