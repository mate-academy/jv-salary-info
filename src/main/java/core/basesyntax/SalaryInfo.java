package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name: names) {
            int salary = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            LocalDate toDate = LocalDate.parse(dateTo, formatter);

            for (String databaseRecord : data) {
                String[] dataRecord = databaseRecord.split(" ");
                String employeeName = dataRecord[1];
                LocalDate recordDate = LocalDate.parse(dataRecord[0], formatter);

                if (employeeName.equals(name)
                        && !recordDate.isBefore(fromDate)
                        && !recordDate.isAfter(toDate)) {
                    salary += Integer.parseInt(dataRecord[2]) * Integer.parseInt(dataRecord[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
