package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate dateAfter = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate dateBegin = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String day : data) {
                String[] singleDay = day.split(" ");
                if ((LocalDate.parse(singleDay[DATE_INDEX], DATE_TIME_FORMATTER).equals(dateBegin)
                        || LocalDate.parse(singleDay[DATE_INDEX], DATE_TIME_FORMATTER)
                        .isAfter(dateBegin))
                        && (LocalDate.parse(singleDay[DATE_INDEX], DATE_TIME_FORMATTER)
                        .equals(dateAfter)
                        || LocalDate.parse(singleDay[DATE_INDEX], DATE_TIME_FORMATTER)
                        .isBefore(dateAfter))
                        && name.equals(singleDay[NAME_INDEX])) {
                    int hours = Integer.parseInt(singleDay[HOURS_INDEX]);
                    int salary = Integer.parseInt(singleDay[SALARY_INDEX]);
                    totalSalary += hours * salary;
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }

        return reportBuilder.toString();
    }
}
