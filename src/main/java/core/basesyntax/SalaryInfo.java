package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_IN_DATA = 0;
    private static final int NAME_IN_DATA = 1;
    private static final int HOURS_IN_DATA = 2;
    private static final int SALARY_IN_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final StringBuilder salaryInfo =
                new StringBuilder("Report for period " + dateFrom + " - "
                        + dateTo + System.lineSeparator());
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String name : names) {
            int sumOfSalary = 0;
            for (String datum : data) {
                String[] parseDatum = datum.split(" ");
                LocalDate tempDate
                        = LocalDate.parse(parseDatum[DATE_IN_DATA], DATE_TIME_FORMATTER);
                if ((tempDate.isAfter(localDateFrom) && tempDate.isBefore(localDateTo)
                        || tempDate.isEqual(localDateFrom) || tempDate.isEqual(localDateTo))
                        && name.equals(parseDatum[NAME_IN_DATA])) {
                    sumOfSalary += Integer.parseInt(parseDatum[HOURS_IN_DATA])
                            * Integer.parseInt(parseDatum[SALARY_IN_DATA]);
                }
            }
            salaryInfo.append(name).append(" - ").append(sumOfSalary)
                    .append(name.equals(names[names.length - 1]) ? "" : System.lineSeparator());
        }
        return salaryInfo.toString();
    }
}
