package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int MULTIPLIER_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String dates : data) {
                String[] currentData = dates.split(" ");
                String date = currentData[DATA_INDEX];
                String currentName = currentData[NAME_INDEX];
                int multiplier = Integer.parseInt(currentData[MULTIPLIER_INDEX]);
                int salaryPerHour = Integer.parseInt(currentData[SALARY_INDEX]);
                LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate now = LocalDate.parse(date, FORMATTER);
                LocalDate end = LocalDate.parse(dateTo, FORMATTER);
                if (name.equals(currentName) && now.isAfter(start) && !now.isAfter(end)) {
                    totalSalary += salaryPerHour * multiplier;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(totalSalary);
        }
        return stringBuilder.toString();
    }
}
