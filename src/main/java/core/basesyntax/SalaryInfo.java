package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate till = LocalDate.parse(dateTo, formatter).plusDays(1);
        String[][] splittedData = new String[data.length][];
        String[] result = new String[names.length];
        for (int j = 0; j < names.length; j++) {
            StringBuilder stringBuilder = new StringBuilder();
            int totalInRange = 0;
            stringBuilder.append(names[j]).append(" - ");
            for (int i = 0; i < data.length; i++) {
                splittedData[i] = data[i].split(" ");
                LocalDate recordDate = LocalDate.parse(splittedData[i][0], formatter);
                if (recordDate.isAfter(from)
                        && recordDate.isBefore(till)
                        && splittedData[i][1].equals(names[j])) {
                    totalInRange += Integer.valueOf(splittedData[i][2])
                            * Integer.valueOf(splittedData[i][3]);
                }
            }
            stringBuilder.append(totalInRange);
            result[j] = stringBuilder.toString();
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String s : result) {
            report.append(System.lineSeparator()).append(s);
        }
        return report.toString();
    }
}
