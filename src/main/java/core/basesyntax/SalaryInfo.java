package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryCounter = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        for (String record : data) {
            String[] splitRecord = record.split(" ");
            LocalDate date = LocalDate.parse(splitRecord[DATE_INDEX], formatter);

            if (!localDateFrom.isAfter(date) && !localDateTo.isBefore(date)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(splitRecord[NAME_INDEX])) {
                        salaryCounter[i] +=
                          Integer.parseInt(splitRecord[HOURS_INDEX])
                            * Integer.parseInt(splitRecord[INCOME_INDEX]);
                        break;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period ")
                  .append(dateFrom)
                  .append(" - ")
                  .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            builder
              .append(System.lineSeparator())
              .append(names[i])
              .append(" - ").append(salaryCounter[i]);
        }

        return builder.toString();
    }
}
