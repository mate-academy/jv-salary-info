package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String DASH_WITH_SPACES = " - ";
    private static final String SPLIT = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DataValidator dataValidator = new DataValidator();
        dataValidator.validate(names, data, dateFrom, dateTo);

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder = new StringBuilder("Report for period "
                + FORMATTER.format(localDateFrom) + DASH_WITH_SPACES
                + FORMATTER.format(localDateTo));

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] splitData = datum.split(SPLIT);
                try {
                    LocalDate date = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                    Integer hours = Integer.parseInt(splitData[HOURS_INDEX]);
                    Integer income = Integer.parseInt(splitData[INCOME_INDEX]);
                    if (name.equals(splitData[NAME_INDEX])
                            && date.compareTo(localDateFrom) >= 0
                            && date.compareTo(localDateTo) <= 0) {
                        salary += hours * income;
                    }
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("Format of date is invalid "
                            + "(should be dd.MM.yyyy): " + datum);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Format of working hours and income "
                            + "per hour is invalid (should be number): " + datum);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(DASH_WITH_SPACES)
                    .append(salary);
        }
        return builder.toString();
    }
}
