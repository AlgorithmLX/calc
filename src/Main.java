import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Main {
    static CalcUtils utils = CalcUtils.instance();

    public static void main(String[] args) throws IOException {
        int num, num0, res;

        var buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите число и знак операнда ('+' или '-', или '*', или '/): ");
        var o = utils.operandAndRes(buf.readLine().toCharArray());
        var charString = String.valueOf(o.getKey());
        var oper = charString.split("[+-/*]");
        var first = oper[0];
        var second = oper[1];
        var trim = second.trim();
        //roman calc
        try {
            num = utils.roman2Int(first); num0 = utils.roman2Int(trim);
            if (num < 0 && num0 < 0) System.out.print("Результат для римских цифр: 0 + 0 = 0");
            else {
                res = utils.calculated(num, num0, o.getValue());
                System.out.print("Результат для римских цифр: ");
                System.out.println(first + " " + o.getValue() + " " + trim + " = " + res);
            }
        } catch (Exception ignored) {}
        //integer calc
        try {
            num = Integer.parseInt(first); num0 = Integer.parseInt(trim);
            res = utils.calculated(num, num0, o.getValue());
            System.out.print("Результат для арабских символов: ");
            System.out.println(num + " " + o.getValue() + " " + num0 + " = " + res);
        } catch (Exception ignored) {}
    }

    public static class CalcUtils {
        private CalcUtils() {}

        public static CalcUtils instance() {
            return new CalcUtils();
        }

        public int calculated(int first, int second, char operand) {
            int result = 0;

            switch (operand) {
                case '+' -> result = first + second;
                case '-' -> result = first - second;
                case '*' -> result = first * second;
                case '/' -> {
                    try {
                        result = first / second;
                    } catch (ArithmeticException | InputMismatchException e) {
                        System.out.println("Allowed only non-zero integer params." + e);
                    }
                }
                default -> throw new IllegalArgumentException("Unsupported operand.");
            }

            return result;
        }

        public int roman2Int(String roman) {
            switch (roman) {
                case "I" -> { return 1; }
                case "II" -> { return 2; }
                case "III" -> { return 3; }
                case "IV" -> { return 4; }
                case "V" -> { return 5; }
                case "VI" -> { return 6; }
                case "VII" -> { return 7; }
                case "VIII" -> { return 8; }
                case "IX" -> { return 9; }
                case "X" -> { return 10; }
                default -> throw new InputMismatchException("Invalid data. Provided value " + roman);
            }
        }

        public Pair<char[], Character> operandAndRes(char[] input) {
            var charArr = new char[10];
            var operator = ' ';

            // for (i in 0 until input.size) on kotlin
            for (var i = 0; i < input.length; i++) {
                charArr[i] = input[i];
                if (charArr[i] == '+') operator = '+';
                else if (charArr[i] == '-') operator = '-';
                else if (charArr[i] == '*') operator = '*';
                else if (charArr[i] == '/') operator = '/';
            }

            return Pair.of(charArr, operator);
        }
    }
}
