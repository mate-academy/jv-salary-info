package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_FOUR = 3;
    private static final String SPLIT_REGEX = " ";
    private static final String DASH = " - ";
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salaryPerPeriod = 0;
            for (String lineFromData : data) {
                String[] splitLineFromData = lineFromData.split(SPLIT_REGEX);
                boolean isTheSameName = splitLineFromData[INDEX_OF_NAME].equals(name);
                if (isTheSameName
                        && isDateInRange(splitLineFromData[INDEX_OF_DATE], dateFrom, dateTo)) {
                    int hours = Integer.parseInt(splitLineFromData[INDEX_OF_WORKING_HOURS]);
                    int salaryPerHour =
                            Integer.parseInt(splitLineFromData[INDEX_OF_SALARY_PER_FOUR]);
                    salaryPerPeriod += hours * salaryPerHour;
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(DASH).append(salaryPerPeriod);
        }
        return result.toString();
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        LocalDate dateToCheck = LocalDate.parse(date, DATE_TIME_FORMATTER);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        return dateToCheck.isAfter(startDate) && !dateToCheck.isAfter(endDate);
    }
}
