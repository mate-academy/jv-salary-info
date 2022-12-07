package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final int DATE_COLUMN_NUMBER = 0;
    private static final int NAME_COLUMN_NUMBER = 1;
    private static final int HOURS_COLUMN_NUMBER = 2;
    private static final int SALARY_COLUMN_NUMBER = 3;

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
                String[] splitLine = line.split(" ");
                LocalDate date = LocalDate.parse(splitLine[DATE_COLUMN_NUMBER], formatter);
                String nameFromLine = splitLine[NAME_COLUMN_NUMBER];
                int hours = Integer.parseInt(splitLine[HOURS_COLUMN_NUMBER]);
                int salaryPerHour = Integer.parseInt(splitLine[SALARY_COLUMN_NUMBER]);

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
