package core.basesyntax;

public class SalaryInfo {

    public int[] getSalaryInfo(String[] names, String[] data) {
        int[] working = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            int lastSpace = data[i].lastIndexOf(" ");
            int firstSpace = data[i].indexOf(" ");
            int secondSpace = data[i].indexOf(" ", firstSpace);
            for (int j = 0; j < names.length; j++) {
                stringBuilder.append(data[i].substring(firstSpace, secondSpace));
                if (stringBuilder.toString().equals(names[j])) {
                    working[i] = Integer.parseInt(data[i].substring(lastSpace, data[i].length())) *
                            Integer.parseInt(data[i].substring(data[i].lastIndexOf(" ", lastSpace)));
                }
            }
        }
        return working;
    }

    public static void printer(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
