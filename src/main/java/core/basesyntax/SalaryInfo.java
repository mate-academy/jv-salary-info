package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ");
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[DATE_INDEX], FORMATTER);
                if (workDay.isAfter(startDate) && !workDay.isAfter(endDate)
                        && name.equals(splittedLine[NAME_INDEX])) {
                    salary += Integer.parseInt(splittedLine[HOUR_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_INDEX]);
                }
            }
            reportBuilder.append(salary);
        }
        return reportBuilder.toString();
    }
}
