import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static int number1;
    static int number2;
    static char operation;
    static String result1;

    public Main() {
    }

    public static void main(String[] args) throws OutputException {
        scanner = new Scanner(System.in);
        System.out.println("Введите");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws OutputException {
        char[] all_char = new char[10];

        for(int i = 0; i < input.length(); ++i) {
            all_char[i] = input.charAt(i);
            if (all_char[i] == '+') {
                operation = '+';
            }

            if (all_char[i] == '-') {
                operation = '-';
            }

            if (all_char[i] == '*') {
                operation = '*';
            }

            if (all_char[i] == '/') {
                operation = '/';
            }
        }

        String under_charString = String.valueOf(all_char);
        String[] blacks = under_charString.split("[-+/*]");
        if (blacks.length > 2) {
            throw new OutputException("Калькулятор принимает только два целых числа от 1 до 10 или два римских числа от I до X и знак операции");
        } else {
            String stable00 = blacks[0];
            String stable01 = blacks[1];
            String string01 = stable00.trim();
            String string03 = stable01.trim();
            String[] romNum = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String[] arabNum = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            boolean romCheck1 = false;
            boolean romCheck2 = false;
            boolean arabCheck1 = false;
            boolean arabCheck2 = false;

            int i;
            for(i = 0; i < 10; ++i) {
                if (romNum[i].equals(string01)) {
                    romCheck1 = true;
                }
            }

            for(i = 0; i < 10; ++i) {
                if (romNum[i].equals(string03)) {
                    romCheck2 = true;
                }
            }

            for(i = 0; i < 10; ++i) {
                if (arabNum[i].equals(string01)) {
                    arabCheck1 = true;
                }
            }

            for(i = 0; i < 10; ++i) {
                if (arabNum[i].equals(string03)) {
                    arabCheck2 = true;
                }
            }

            if ((!romCheck1 || !romCheck2) && (!arabCheck1 || !arabCheck1)) {
                if (romCheck2 && arabCheck1 || romCheck1 && arabCheck2) {
                    throw new OutputException("используются одновременно разные системы счисления");
                } else if ((!romCheck2 || !arabCheck1) && (!romCheck1 || !arabCheck2)) {
                    throw new OutputException("Калькулятор принимает только два целых числа от 1 до 10 или два римских числа от I до X и знак операции");
                } else {
                    throw new OutputException("используются одновременно разные системы счисления");
                }
            } else {
                if (romCheck1 && romCheck2 && !arabCheck1 && !arabCheck2) {
                    romCalc(string01, string03);
                } else if (arabCheck1 && arabCheck1 && !romCheck1 && !romCheck2) {
                    arabCalc(string01, string03);
                }

                return String.valueOf(result1);
            }
        }
    }

    static String romCalc(String a, String b) throws OutputException {
        number1 = romanToNumber(a);
        number2 = romanToNumber(b);
        String oper = String.valueOf(operation);
        boolean minus = oper.contains("-");
        if (minus && number1 < number2) {
            throw new OutputException("в римской системе нет отрицательных значений");
        } else {
            int result = calculated(number1, number2, operation);
            if (result == 0) {
                throw new OutputException("в римской системе нет нуля");
            } else {
                String resultRoman = convertNumToRoman(result);
                result1 = resultRoman;
                return result1;
            }
        }
    }

    static String arabCalc(String a, String b) {
        number1 = Integer.parseInt(a);
        number2 = Integer.parseInt(b);
        int result = calculated(number1, number2, operation);
        result1 = String.valueOf(result);
        return result1;
    }

    static String convertNumToRoman(int numArabian) {
        String[] roman = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        String s = roman[numArabian];
        return s;
    }

    static int romanToNumber(String roman) {
        if (roman.equals("I")) {
            return 1;
        } else if (roman.equals("II")) {
            return 2;
        } else if (roman.equals("III")) {
            return 3;
        } else if (roman.equals("IV")) {
            return 4;
        } else if (roman.equals("V")) {
            return 5;
        } else if (roman.equals("VI")) {
            return 6;
        } else if (roman.equals("VII")) {
            return 7;
        } else if (roman.equals("VIII")) {
            return 8;
        } else if (roman.equals("IX")) {
            return 9;
        } else {
            return roman.equals("X") ? 10 : -1;
        }
    }

    static int calculated(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '*':
                result = num1 * num2;
                break;
            case '+':
                result = num1 + num2;
            case ',':
            case '.':
            default:
                break;
            case '-':
                result = num1 - num2;
                break;
            case '/':
                result = num1 / num2;
        }

        return result;
    }
    static class OutputException extends Exception {
        OutputException(String description) {
            super(description);
        }
    }
}