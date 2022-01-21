package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int dataFromData = 0;
        final int nameFromData = 1;
        final int hours = 2;
        final int payPerHour = 3;
        long totalSalary = 0;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate theDateFrom = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate theDateTo = LocalDate.parse(dateTo.trim(), formatter);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name:
                names) {
            for (String dataInstance : data) {
                LocalDate currentDate = LocalDate.parse(dataInstance
                        .split(" ")[dataFromData].trim(), formatter);
                if ((dataInstance.split(" ")[nameFromData].equals(name))
                        && ((currentDate.isAfter(theDateFrom))
                        && (currentDate.isBefore(theDateTo)) || currentDate.equals(theDateFrom)
                        || currentDate.isEqual(theDateTo))) {
                    totalSalary += Long.parseLong(dataInstance.split(" ")[hours])
                            * Long.parseLong(dataInstance.split(" ")[payPerHour]);
                }
            }
            stringBuilder.append(name).append(" - ")
                    .append(totalSalary).append(System.lineSeparator());
            totalSalary = 0;
        }
        return stringBuilder.toString();
    }
}
