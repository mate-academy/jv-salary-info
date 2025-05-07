package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final int DATA = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int price = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat(FORMATTER);
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] dataLine = data[i].split(" ");
            boolean correctDate;
            try {
                correctDate = format.parse(dateFrom).compareTo(format.parse(dataLine[DATA])) <= 0
                        && format.parse(dataLine[DATA]).compareTo(format.parse(dateTo)) <= 0;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (correctDate) {
                int index = -1;
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(dataLine[NAME])) {
                        index = j;
                        break;
                    }
                }
                salary[index] += Integer.parseInt(dataLine[HOURS])
                        * Integer.parseInt(dataLine[price]);
            }
        }
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }
}
