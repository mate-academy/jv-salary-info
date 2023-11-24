package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int SALARY_MULTIPLIER_INDEX = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] foundData = line.split(" ");
                LocalDate parsedFoundDate = LocalDate.parse(foundData[DATE_INDEX], formatter);
                if (isWithinDateRange(parsedFoundDate, parsedDateFrom, parsedDateTo)
                        && foundData[NAME_INDEX].equals(name)) {
                    salary += Integer.parseInt(foundData[SALARY_INDEX])
                            * Integer.parseInt(foundData[SALARY_MULTIPLIER_INDEX]);
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return report.toString();
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }
}
