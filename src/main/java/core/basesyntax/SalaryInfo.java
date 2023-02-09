package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] resultArray = new int[names.length];
        String[] dates;
        for (int i = 0; i < names.length; i++) {
            for (String infoPerDay : data) {
                dates = infoPerDay.split(" ");
                if (names[i].equals(dates[1]) && isTrueDate(dates[0], dateFrom, dateTo)) {
                    resultArray[i] += (Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]));
                }
            }
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(resultArray[i]);
        }
        return result.toString();
    }

    private boolean isTrueDate(String dateNow, String dateFrom, String dateTo) {
        LocalDate localDateTemp = LocalDate.parse(dateNow, format);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, format).minusDays(1);
        LocalDate localDateTo = LocalDate.parse(dateTo, format).plusDays(1);
        return localDateTemp.isAfter(localDateFrom) && localDateTemp.isBefore(localDateTo);
    }
}
