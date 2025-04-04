package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToParsed = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);


        int[] salaries = new int[names.length];


        for (String record : data) {
            String[] parts = record.split(" ");
            String dateStr = parts[0];
            String employeeName = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            LocalDate workDate = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);

            // Check if the work date is within the specified range
            if (!workDate.isBefore(dateFromParsed) && !workDate.isAfter(dateToParsed)) {
                // Find the index of the employee name in the names array
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        int earned = hours * rate;
                        salaries[i] += earned;
                        break;
                    }
                }
            }
        }


        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());


        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}



