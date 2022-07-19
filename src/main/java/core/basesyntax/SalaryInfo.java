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
        int salary = 0;
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDay = LocalDate.parse(splittedLine[DATE_INDEX], FORMATTER);
                if (((startDate.isBefore(workDay)) || startDate.equals(workDay))
                        && ((endDate.isAfter(workDay)) || endDate.equals(workDay))
                        && (splittedLine[NAME_INDEX].equals(name))) {
                    salary += Integer.parseInt(splittedLine[HOUR_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return reportBuilder.toString();
    }
}
