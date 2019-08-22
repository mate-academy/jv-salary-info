package core.basesyntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {

    static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        List<String[]> nameAndMoney = new ArrayList<>();
        int dateF = Integer.parseInt(dateFrom.replace(".", ""));
        int dateT = Integer.parseInt(dateTo.replace(".", ""));
        if (dateT < dateF) {
            return null;
        }
        for (int i = 0; i < data.length; i++) {
            String[] splitData = data[i].split(" ");
            if (dateF <= Integer.parseInt(splitData[0].replace(".", ""))
                    && dateT >= Integer.parseInt(splitData[0].replace(".", ""))) {
                splitData[0] = splitData[1];
                splitData[1] = String.valueOf(Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]));
                splitData = Arrays.copyOf(splitData, splitData.length - 2);
                nameAndMoney.add(splitData);
            }
        }
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String[] s : nameAndMoney) {
                if (names[i].equals(s[0])) {
                    salaries[i] += Integer.parseInt(s[1]);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i] + " - " + salaries[i] + '\n');
        }
        return new String(stringBuilder);
    }
}
