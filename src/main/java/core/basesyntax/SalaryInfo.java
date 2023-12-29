package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_SUBSTRING_START = 0;
    private static final int DATE_SUBSTRING_END = 10;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    try {
                        LocalDate localDateCurrent = LocalDate.parse(data[j]
                                .substring(DATE_SUBSTRING_START,
                                           DATE_SUBSTRING_END),
                                           DATE_FORMATTER);
                        if ((localDateFrom.isBefore(localDateCurrent)
                                || localDateFrom.isEqual(localDateCurrent))
                                && (localDateTo.isAfter(localDateCurrent)
                                || localDateTo.isEqual(localDateCurrent))) {
                            String[] currentData = data[j].split(" ");
                            salary[i] += Integer.parseInt(currentData[HOURS_INDEX])
                                       * Integer.parseInt(currentData[RATE_INDEX]);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date value");
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder(String.format("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator()));
        for (int i = 0; i < names.length; i++) {
            builder.append(String.format(names[i] + " - " + salary[i]) + System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
