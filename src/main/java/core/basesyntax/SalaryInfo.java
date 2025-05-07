package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_COST = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = convertStringToLocalDate(dateFrom);
        LocalDate to = convertStringToLocalDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            result.append(names[i]).append(" - ");
            for (String record: data) {
                String [] dates = record.split(" ");
                try {
                    LocalDate currentDate = convertStringToLocalDate(dates[INDEX_DATE]);
                    if (!currentDate.isBefore(from) && !currentDate.isAfter(to)
                            && dates[INDEX_NAME].equals(names[i])) {
                        salary += Integer.parseInt(dates[INDEX_HOURS])
                                * Integer.parseInt(dates[INDEX_COST]);
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number format hours or cost", e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new RuntimeException("Invalid format record in data", e);
                }
            }
            result.append(salary);
            if (i != names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    private LocalDate convertStringToLocalDate(String date) {
        LocalDate result;
        try {
            result = LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeException e) {
            throw new RuntimeException("Invalid date format - [" + date + "]", e);
        }
        return result;
    }
}
