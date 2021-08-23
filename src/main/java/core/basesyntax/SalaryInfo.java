package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        String separator = System.lineSeparator();
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(separator);
        int[] salaryArr = new int[names.length];
        for (int name = 0; name < names.length; name++) {
            for (int record = 0; record < data.length; record++) {
                if (data[record].contains(names[name])) {
                    LocalDate day = LocalDate.parse((data[record].split(" "))[0], FORMATTER);
                    if (!day.isBefore(fromDate) && !day.isAfter(toDate)) {
                        salaryArr[name] = salaryArr[name]
                                    + Integer.parseInt(data[record].split(" ")[2])
                                    * Integer.parseInt(data[record].split(" ")[3]);
                    }
                }
            }
            if (name == names.length - 1) {
                report.append(names[name]).append(" - ").append(salaryArr[name]);
            } else {
                report.append(names[name]).append(" - ")
                        .append(salaryArr[name]).append(separator);
            }
        }
        return report.toString();
    }
}
