package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String DATA_SEPARATOR = " ";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(DATA_SEPARATOR);
                if (name.equals(splittedLine[NAME_INDEX])
                        && isDateInRange(splittedLine[DATE_INDEX], dateFrom, dateTo)) {
                    int workingHours = Integer.parseInt(splittedLine[WORKING_HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                    salary += workingHours * incomePerHour;
                }
            }
            reportBuilder.append(System.lineSeparator());
            reportBuilder.append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }

    private boolean isDateInRange(String dateToCheck, String dateFromString, String dateToString) {
        LocalDate dateFrom = LocalDate.parse(dateFromString, FORMATTER);
        LocalDate dateTo = LocalDate.parse(dateToString, FORMATTER);
        LocalDate date = LocalDate.parse(dateToCheck, FORMATTER);
        return (date.isEqual(dateFrom) || date.isAfter(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo));
    }
}
