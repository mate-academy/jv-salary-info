package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String europeanDatePattern = "dd.MM.yyyy";

        LocalDate fromDate = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(europeanDatePattern));
        LocalDate toDate = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern(europeanDatePattern));

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                        .append(dateTo).append("\n");

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordString = record.split(" ");
                String recordDate = recordString[0];
                String recordName = recordString[1];
                int hours = Integer.parseInt(recordString[2]);
                int rate = Integer.parseInt(recordString[3]);

                LocalDate entryLocalDate = LocalDate.parse(recordDate,
                        DateTimeFormatter.ofPattern(europeanDatePattern));

                if (recordName.equals(name) && !entryLocalDate.isBefore(fromDate)
                        && !entryLocalDate.isAfter(toDate)) {
                    totalSalary += hours * rate;
                }
            }
            report.append(name).append(" - ").append(totalSalary).append("\n");
        }
        return report.toString();
    }
}
