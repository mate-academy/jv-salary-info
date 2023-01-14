package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Pattern dataEntryPattern =
            Pattern.compile("^(\\d{2}.\\d{2}.\\d{4}) (\\w+) (\\d+) (\\d+)$");
    private static final String DATA_ITEMS_SEPARATOR = " ";
    private static final String REPORT_DATA_SEPARATOR = " - ";
    private static final int DATA_ITEM_DATE = 0;
    private static final int DATA_ITEM_NAME = 1;
    private static final int DATA_ITEM_HOURS = 2;
    private static final int DATA_ITEM_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        LocalDate localDateFrom;
        LocalDate localDateTo;

        if (names == null || data == null || names.length == 0 || data.length == 0) {
            throw new IllegalArgumentException("Data and employees list should not be empty!");
        }

        try {
            localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
            localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Dates should be valid dates in dd.MM.yyyy format!");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Range dates should not be null!");
        }

        reportBuilder.append("Report for period ").append(dateFrom)
                .append(REPORT_DATA_SEPARATOR).append(dateTo);

        for (String name : names) {
            int salary = 0;
            LocalDate date;
            if (name == null || name.isBlank()) {
                continue;
            }
            for (String dataEntry : data) {
                if (!dataEntryPattern.matcher(dataEntry).matches()) {
                    continue;
                }
                String[] dataItems = dataEntry.split(DATA_ITEMS_SEPARATOR);

                try {
                    date = LocalDate.parse(dataItems[DATA_ITEM_DATE], DATE_TIME_FORMATTER);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException(dataItems[DATA_ITEM_DATE] + " - invalid date!");
                }

                if (name.equals(dataItems[DATA_ITEM_NAME]) && date.isAfter(localDateFrom.minusDays(1))
                        && date.isBefore(localDateTo.plusDays(1))) {
                    salary += Integer.parseInt(dataItems[DATA_ITEM_HOURS]) * Integer.parseInt(dataItems[DATA_ITEM_RATE]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(REPORT_DATA_SEPARATOR).append(salary);
        }
        return reportBuilder.toString();
    }
}
