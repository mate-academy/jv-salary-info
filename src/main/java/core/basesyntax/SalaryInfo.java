package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToLD = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordDetails = record.split(" ");
                LocalDate currentDate = LocalDate.parse(recordDetails[0], DATE_FORMATTER);
                String employeeName = recordDetails[1];
                if (name.equals(employeeName) && !currentDate.isBefore(dateFromLD)
                        && !currentDate.isAfter(dateToLD)) {
                    totalSalary += Integer.parseInt(recordDetails[2])
                            * Integer.parseInt(recordDetails[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(totalSalary);
        }
        return builder.toString();
    }
}
