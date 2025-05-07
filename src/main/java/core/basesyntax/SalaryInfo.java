package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String REPORT_TITLE_PATTERN = "Report for period %s - %s";
    public static final String PERSON_DESCRIPTION_PATTERN = "%s - %s";
    public static final String DATA_SEPARATOR = " ";
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOURS_POSITION = 2;
    public static final int INCOME_POSITION = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        final LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        final String title = String.format(REPORT_TITLE_PATTERN, dateFrom, dateTo);
        final StringBuilder builder = new StringBuilder(title);
        int salary = 0;
        for (String name : names) {
            for (String dataElement : data) {
                String[] splitLine = dataElement.split(DATA_SEPARATOR);
                try {
                    if (splitLine[NAME_POSITION].equals(name)
                            && isBetween(from, to, splitLine[DATE_POSITION])) {
                        salary += getSalary(splitLine[INCOME_POSITION], splitLine[HOURS_POSITION]);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(
                            "Invalid input data format. Check if they match the template.", e);
                }
            }
            String formattedString = String.format(PERSON_DESCRIPTION_PATTERN, name, salary);
            builder.append(System.lineSeparator()).append(formattedString);
            salary = 0;
        }
        return builder.toString();
    }

    private boolean isBetween(LocalDate from, LocalDate to, String splitDate)
            throws ParseException {
        LocalDate date = LocalDate.parse(splitDate, DATE_FORMATTER);
        return date.isAfter(from)
                && date.isBefore(to)
                || date.isEqual(from)
                || date.isEqual(to);
    }

    private int getSalary(String income, String hours)
            throws ParseException {
        return Integer.parseInt(income) * Integer.parseInt(hours);
    }
}
