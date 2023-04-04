package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String d : data) {
                String[] split = d.split(" ");
                LocalDate date = LocalDate.parse(split[0], formatter);
                String workerName = split[1];
                int hours = Integer.parseInt(split[2]);
                int hourlySalary = Integer.parseInt(split[3]);
                if (name.equals(workerName)
                        && !date.isBefore(startDate)
                        && !date.isAfter(endDate)) {
                    salary += hours * hourlySalary;
                }
            }
            builder.append("\n").append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
