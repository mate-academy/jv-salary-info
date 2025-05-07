package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                if (splittedLine[NAME_INDEX].equals(name)
                        && isDateInPeriod(splittedLine[DATE_INDEX], dateFrom, dateTo)) {
                    totalSalary += Integer.parseInt(splittedLine[WORKED_HOURS_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name + " - " + totalSalary);
        }

        return stringBuilder.toString();
    }

    private boolean isDateInPeriod(String date, String dateFrom, String dateTo) {
        LocalDate dateDate = LocalDate.parse(date, TIME_PATTERN);
        LocalDate dateFromDate = LocalDate.parse(dateFrom, TIME_PATTERN);
        LocalDate dateToDate = LocalDate.parse(dateTo, TIME_PATTERN);
        return !dateDate.isBefore(dateFromDate) && !dateDate.isAfter(dateToDate);
    }
}
