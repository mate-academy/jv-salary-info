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
        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] userRecords = data[j].split(" ");
                    LocalDate userDate = LocalDate.parse(userRecords[0], formatter);
                    if (userDate.isAfter(parsedDateFrom) && userDate.isBefore(parsedDateTo)
                            || userDate.isEqual(parsedDateFrom) || userDate.isEqual(parsedDateTo)) {
                        totalSalary += Integer.parseInt(userRecords[2])
                                * Integer.parseInt(userRecords[3]);
                    }
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalary).append("\n");
        }
        return builder.toString().trim();
    }
}

