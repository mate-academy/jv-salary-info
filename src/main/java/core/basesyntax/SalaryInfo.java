package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            stringBuilder.append(System.lineSeparator());
            for (int j = 0; j < data.length; j++) {
                String[] table = data[j].split(" ");
                if (names[i].equals(table[1])) {
                    try {
                        if (time.parse(dateFrom).equals(time.parse(table[0]))
                                || time.parse(dateTo).equals(time.parse(table[0]))
                                || (time.parse(table[0]).after(time.parse(dateFrom))
                                && time.parse(table[0]).before(time.parse(dateTo)))) {
                            sum += Integer.parseInt(table[2]) * Integer.parseInt(table[3]);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            stringBuilder.append(names[i]).append(" - ").append(sum);
        }
        return stringBuilder.toString();
    }
}
