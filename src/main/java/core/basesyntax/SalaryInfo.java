package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String RECORD_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordString = record.split(RECORD_SEPARATOR);
                String recordDate = recordString[DATE_INDEX];
                String recordName = recordString[NAME_INDEX];
                int hours = Integer.parseInt(recordString[HOURS_INDEX]);
                int rate = Integer.parseInt(recordString[RATE_INDEX]);

                LocalDate entryLocalDate = LocalDate.parse(recordDate, formatter);

                if (recordName.equals(name) && !entryLocalDate.isBefore(fromDate)
                        && !entryLocalDate.isAfter(toDate)) {
                    totalSalary += hours * rate;
                }
            }
            report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
