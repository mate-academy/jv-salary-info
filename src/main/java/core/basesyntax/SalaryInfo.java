package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 0;
    private static final int HOURS_OF_WORK = 0;
    private static final int SALARY_PER_HOUR = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd_MM_yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period")
                .append(dateFrom).append("-")
                .append(dateTo)
                .append(System.lineSeparator());
        int moneyEarned = 0;
        for (String nameOfEmployee : names) {
            for (String checkData : data) {
                String[] newData = checkData.split(" ");
                String date = newData[DATE];
                String name = newData[NAME];
                LocalDate begin = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate ourDate = LocalDate.parse(date, FORMATTER);
                LocalDate finish = LocalDate.parse(dateTo, FORMATTER);
                int hours = Integer.parseInt(newData[HOURS_OF_WORK]);
                int salary = Integer.parseInt(newData[SALARY_PER_HOUR]);
                if (name.equals(nameOfEmployee) && ourDate.isBefore(finish)
                        && ourDate.isAfter(begin)) {
                    moneyEarned += hours * salary;
                }
                builder.append(nameOfEmployee).append(moneyEarned);
            }
        }
        return builder.toString();
    }
}
