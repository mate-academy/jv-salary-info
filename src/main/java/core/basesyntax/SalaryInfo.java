package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int WORK_HOURS_POSITION = 2;
    private static final int SALARY_PER_HOUR_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] salaryCounter = new int[names.length];
        StringBuilder resultString = new StringBuilder();
        for (int k = 0; k < names.length; k++) {
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[DAY_POSITION],
                        DATE_FORMATTER);
                if (workDay.isBefore(toDate) && workDay.isAfter(fromDate)
                        || workDay.equals(toDate) || workDay.equals(fromDate)) {
                    if (splittedLine[NAME_POSITION].equals(names[k])) {
                        salaryCounter[k] += Integer.parseInt(splittedLine[WORK_HOURS_POSITION])
                                * Integer.parseInt(splittedLine[SALARY_PER_HOUR_POSITION]);
                    }
                }
            }
            resultString.append(System.lineSeparator())
                    .append(names[k])
                    .append(" - ")
                    .append(salaryCounter[k]);
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + resultString;
    }
}
