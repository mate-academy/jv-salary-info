package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, DATEFORMAT);
        LocalDate finish = LocalDate.parse(dateTo, DATEFORMAT);
        StringBuilder str = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String info : data) {
                String[] parts = info.split(" ");
                LocalDate recordData = LocalDate.parse(parts[0], DATEFORMAT);
                String recordName = parts[1];
                if (recordName.equals(name) && !recordData.isAfter(finish)
                        && !recordData.isBefore(start)) {
                    totalSalary += Integer.valueOf(parts[3]) * Integer.valueOf(parts[2]);
                }
            }
            str.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return str.toString();
    }
}
