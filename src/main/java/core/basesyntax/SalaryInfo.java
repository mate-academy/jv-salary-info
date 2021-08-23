package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salarySum = 0;
            for (String datum : data) {
                String[] splittedDate = datum.split(" ");
                if (isValidDate(name, dateFrom, splittedDate,
                        DATE_INDEX, dateTo, FORMATTER)) {
                    int hours = Integer.parseInt(splittedDate[HOURS_POSITION]);
                    int salary = Integer.parseInt(splittedDate[SALARY_POSITION]);
                    salarySum += (hours * salary);
                }
            }
            report.append(name).append(" - ").append(salarySum)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    public boolean isValidDate(String name, String dateFrom, String[] splittedDate,
                               int dateindex, String dateTo, DateTimeFormatter formatter) {
        return (name.equals(splittedDate[NAME_INDEX])
                && !LocalDate.parse(splittedDate[dateindex], formatter)
                .isBefore(LocalDate.parse(dateFrom, formatter))
                && !LocalDate.parse(splittedDate[dateindex], formatter)
                .isAfter(LocalDate.parse(dateTo, formatter)));
    }
}

