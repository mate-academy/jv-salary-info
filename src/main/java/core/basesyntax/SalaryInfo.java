package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(
            String[] names,
            String[] data,
            String dateFrom,
            String dateTo
    ) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate date = LocalDate.parse(splittedLine[DATE_INDEX], formatter);
                String nameFromLine = splittedLine[NAME_INDEX];
                int hours = Integer.parseInt(splittedLine[HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                if ((date.isAfter(from)
                        && date.isBefore(to)
                        || date.equals(from)
                        || date.equals(to))
                        && nameFromLine.equals(name)) {
                    salary += hours * salaryPerHour;
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
