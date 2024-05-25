import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение:");
        String exp = scn.nextLine();
        try {
            String result = calc(exp, actions, regexActions, converter);
            System.out.println("Результат:" + result);
        } catch (Exception e) {
            System.out.println("A mistake is " + e.getMessage());
        }
    }

    public static String calc(String exp, String[] actions, String[] regexActions, Converter converter) throws ScannerException {
        int actionIndex = -1;
        int actionCount = 0;

        for (int i = 0; i < actions.length; i++) {
            int count = exp.length() - exp.replace(actions[i], "").length();
            if (count == 1) {
                actionIndex = i;
                actionCount++;
            }
        }

        if (actionIndex == -1 || actionCount != 1) {
            System.out.println("Некорректное выражение");
        }

        String[] data = exp.split(regexActions[actionIndex]);

        if (data.length != 2) {
            System.out.println("Некорректное выражение");
        }

        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (Math.abs(a) > 10 || Math.abs(b) > 10) {
                throw new ScannerException("input is restricted 1 to 10.");
            }

            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new ScannerException("an incorrect operation");
            }

            if (isRoman) {
                return converter.toRoman(result);
            } else {
                return String.valueOf(result);
            }
        } else {
            throw new ScannerException("numbers must be typed in the same format.");
        }
    }
}
