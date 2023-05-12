package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static String recordSeparator;
    private static final String FIELD_SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int WAGE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATA_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATA_FORMATTER);

        StringBuilder reportBuilder = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String line : data) {
                String[] splitData = line.split(FIELD_SEPARATOR);
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX], DATA_FORMATTER);
                String dataName = splitData[NAME_INDEX];
                int hour = Integer.parseInt(splitData[HOUR_INDEX]);
                int wage = Integer.parseInt(splitData[WAGE_INDEX]);

                if (dataName.equals(names[i])) {
                    if (date.equals(startDate) || date.equals(endDate)
                            || (date.isAfter(startDate) && date.isBefore(endDate))) {
                        salary += wage * hour;
                    }
                }
            }

            recordSeparator = " - ";
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(recordSeparator)
                    .append(salary);
        }
        return reportBuilder.toString();
    }
}
