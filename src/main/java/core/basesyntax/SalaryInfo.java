package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            final int dateIndex = 0;
            final int nameIndex = 1;
            final int hoursPosition = 2;
            final int salaryPosition = 3;
            int salarySum = 0;
            for (String datum : data) {
                String[] splittedDate = datum.split(" ");
                if (name.equals(splittedDate[nameIndex])
                        && !LocalDate.parse(splittedDate[dateIndex], formatter)
                        .isBefore(LocalDate.parse(dateFrom, formatter))
                        && !LocalDate.parse(splittedDate[dateIndex], formatter)
                        .isAfter(LocalDate.parse(dateTo, formatter))) {
                    int hours = Integer.parseInt(splittedDate[hoursPosition]);
                    int salary = Integer.parseInt(splittedDate[salaryPosition]);
                    salarySum += (hours * salary);
                }
            }
            stringBuilder.append(name).append(" - ").append(salarySum)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
