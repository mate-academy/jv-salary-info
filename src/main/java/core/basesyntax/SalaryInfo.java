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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        for (String line : data) {
            String[] splitLine = line.split(DATA_SEPARATOR);
            int index = getNameIndex(names, splitLine[NAME_INDEX]);
            if (index != -1 && isDateInRange(splitLine[DATE_INDEX], dateFrom, dateTo)) {
                int workingHours = Integer.parseInt(splitLine[WORKING_HOURS_INDEX]);
                int incomePerHour = Integer.parseInt(splitLine[INCOME_PER_HOUR_INDEX]);
                salaries[index] += workingHours * incomePerHour;
            }
        }
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < salaries.length; i++) {
            reportBuilder.append(System.lineSeparator());
            reportBuilder.append(names[i]).append(" - ").append(salaries[i]);
        }
        return reportBuilder.toString();
    }

    private int getNameIndex(String[] names, String nameToCheck) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(nameToCheck)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isDateInRange(String dateToCheck, String dateFromString, String dateToString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        LocalDate date = LocalDate.parse(dateToCheck, formatter);
        return (date.isEqual(dateFrom) || date.isAfter(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo));
    }
}
