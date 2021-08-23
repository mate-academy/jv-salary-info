package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS_OF_WORK = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String nameOfEmployee : names) {
            int moneyEarned = 0;
            for (String checkData : data) {
                String[] record = checkData.split(" ");
                LocalDate date = LocalDate.parse(record[DATE], FORMATTER);
                String name = record[NAME];
                LocalDate begin = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate finish = LocalDate.parse(dateTo, FORMATTER);

                int hours = Integer.parseInt(record[HOURS_OF_WORK]);
                int salary = Integer.parseInt(record[SALARY_PER_HOUR]);
                if (nameOfEmployee.equals(name) && !date.isBefore(begin) && !date.isAfter(finish)) {
                    moneyEarned += hours * salary;
                }
            }
            builder.append(System.lineSeparator()).append(nameOfEmployee)
                    .append(" - ").append(moneyEarned);
        }
        return builder.toString();
    }
}
