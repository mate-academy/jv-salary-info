package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_COLUMN_INDEX = 0;
    private static final int HOURS_COLUMN_INDEX = 2;
    private static final int RATE_COLUMN_INDEX = 3;
    private static final String COLUMNS_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseAndExtractDate(dateFrom);
        LocalDate localDateTo = parseAndExtractDate(dateTo);
        StringBuilder builder = new StringBuilder(reportTitle(dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] columns = data[j].split(COLUMNS_SEPARATOR);
                if (hasName(names[i], data[j])
                        && checkDate(localDateFrom, localDateTo,
                        parseAndExtractDate(columns[DATA_COLUMN_INDEX]))) {
                    salary += calculateSalaryBasedOnHours(columns[HOURS_COLUMN_INDEX],
                            columns[RATE_COLUMN_INDEX]);
                }
            }
            builder.append(reportLine(names[i], salary));
        }
        return builder.toString().trim();
    }

    private int calculateSalaryBasedOnHours(String hours, String rate) {
        return Integer.parseInt(hours) * Integer.parseInt(rate);
    }

    private boolean checkDate(LocalDate localDateFrom, LocalDate localDateTo,
                               LocalDate localDateCurrent) {
        return ((localDateFrom.isBefore(localDateCurrent)
                || localDateFrom.isEqual(localDateCurrent))
                && (localDateTo.isAfter(localDateCurrent)
                || localDateTo.isEqual(localDateCurrent)));
    }

    private boolean hasName(String name, String line) {
        return line.contains(name);
    }

    private LocalDate parseAndExtractDate(String line) {
        return LocalDate.parse(line, DATE_FORMATTER);
    }

    private String reportTitle(String dateFrom, String dateTo) {
        return String.format("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
    }

    private String reportLine(String name, int salary) {
        return String.format(name + " - " + salary) + System.lineSeparator();
    }
}
