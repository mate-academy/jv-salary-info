package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int WORK_HOURS_POSITION = 2;
    private static final int INCOME_PER_HOUR_POSITION = 3;
    private static final String REPORT_TITLE = "Report for period ";
    private static final String DASH = " - ";
    private static final String SPACE_REGEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        builder.append(REPORT_TITLE)
                .append(dateFrom)
                .append(DASH)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalIncome = 0;
            for (String dataRow : data) {
                String[] splitedRow = dataRow.split(SPACE_REGEX);
                String nameFromData = splitedRow[NAME_POSITION];
                if (name.equals(nameFromData)) {
                    LocalDate date =
                            LocalDate.parse(splitedRow[DATE_POSITION], formatter);
                    int workedHours =
                            Integer.parseInt(splitedRow[WORK_HOURS_POSITION]);
                    int incomePerHour =
                            Integer.parseInt(splitedRow[INCOME_PER_HOUR_POSITION]);
                    int totalIncomePerDay = workedHours * incomePerHour;
                    if (date.isBefore(localDateTo) && date.isAfter(localDateFrom)
                            || date.isEqual(localDateTo) || date.isEqual(localDateFrom)) {
                        totalIncome += totalIncomePerDay;
                    }
                }
            }
            builder.append(name)
                    .append(DASH)
                    .append(totalIncome)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
