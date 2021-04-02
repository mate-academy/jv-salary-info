package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                if (record.contains(name)) {
                    String[] userRecords = record.split(" ");
                    LocalDate userDate = LocalDate.parse(userRecords[0], formatter);
                    if (userDate.isAfter(parsedDateFrom) && userDate.isBefore(parsedDateTo)
                            || userDate.isEqual(parsedDateFrom) || userDate.isEqual(parsedDateTo)) {
                        totalSalary += Integer.parseInt(userRecords[2])
                                * Integer.parseInt(userRecords[3]);
                    }
                }
            }
            builder.append(name).append(" - ").append(totalSalary).append("\n");
        }
        return builder.toString().trim();
    }
}
