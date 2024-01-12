package core.basesyntax;

import static java.lang.Integer.parseInt;
import static java.lang.System.lineSeparator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_WAGE_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        validateInput(names, data, dateFrom, dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataLine = data[i].split(" ");
                if (dataLine[NAME_INDEX].equals(name)
                        && isSuitableDate(dataLine[DATE_INDEX], dateFrom, dateTo)) {
                    sum += parseInt(dataLine[HOURS_INDEX])
                            * parseInt(dataLine[HOURLY_WAGE_INDEX]);
                }
            }
            result.append(lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sum);
        }
        return result.toString();
    }

    private void validateInput(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0 || dateFrom == null || dateTo == null) {
            throw new DataFormatException("Data is empty");
        }
    }

    private boolean isSuitableDate(String date, String dateFrom, String dateTo) {
        return !parseDate(date).isBefore(parseDate(dateFrom))
                && !parseDate(date).isAfter(parseDate(dateTo));
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
