package words.num.com.numbertowords;

import java.text.DecimalFormat;

import static java.lang.StrictMath.abs;

/**
 * Created by Shiva on 27-03-2017.
 */

public class NumberToWords {

    //Create Static Strings for single digit numbers
    final private  static String[] units = {"Zero","One","Two","Three","Four",
            "Five","Six","Seven","Eight","Nine","Ten",
            "Eleven","Twelve","Thirteen","Fourteen","Fifteen",
            "Sixteen","Seventeen","Eighteen","Nineteen"};

    //Create Static Strings to represent multiple of tens
    final private static String[] tens = {"","","Twenty","Thirty","Forty","Fifty",
            "Sixty","Seventy","Eighty","Ninety"};


    //This method can convert it to thousands and millions (US Format)
    public static String ConvertNumber(Integer i) {
        if( i < 20)  return units[i];
        if( i < 100) return tens[i/10] + ((i % 10 > 0)? " " + ConvertNumber(i % 10):"");
        if( i < 1000) return units[i/100] + " Hundred" + ((i % 100 > 0)?" and " + ConvertNumber(i % 100):"");
        if( i < 1000000) return ConvertNumber(i / 1000) + " Thousand " + ((i % 1000 > 0)? " " + ConvertNumber(i % 1000):"") ;
        return ConvertNumber(i / 1000000) + " Million " + ((i % 1000000 > 0)? " " + ConvertNumber(i % 1000000):"") ;
    }

    public static String Convert(String number) {
        int no = 0;
        if(number.trim().equals("")) {
            return "";
        } else if (!number.matches("[0-9]+")) {
            return "Please Enter Valid Numbers";
        }else {
            no = Integer.valueOf(number);
        }

        return Convert(no);


    }

    //This method can convert it to thousands, Lakhs and Crores (Indian Format) upto 99,99,99,999
    private static String Convert(Integer i) {
        String number = i.toString();
        String numberToConvert = "";
        if(number.length() <= 5) {
            number = ConvertNumber(i);
        }
        else if(number.length() > 5 && number.length() < 8) {
            if (number.length() == 6) {
                numberToConvert = ConvertNumber(Integer.valueOf(number.substring(1, number.length())));
                number = units[Integer.valueOf(number.substring(0, 1))] + " Lakh " + numberToConvert;
            } else {
                numberToConvert = ConvertNumber(Integer.valueOf(number.substring(2, number.length())));
                number = ConvertNumber(Integer.valueOf(number.substring(0, 2))) + " Lakhs " + numberToConvert;
            }
        }

        else if (number.length() > 7 && number.length() < 10) {
            if(number.length() == 8) {
                numberToConvert = Convert(Integer.valueOf(number.substring(1, number.length())));
                number = units[Integer.valueOf(number.substring(0, 1))] + " Crore " + numberToConvert;
            } else {
                numberToConvert = Convert(Integer.valueOf(number.substring(2, number.length())));
                number = ConvertNumber(Integer.valueOf(number.substring(0, 2))) + " Crores " + numberToConvert;
            }
        }

        return number;
    }


}
