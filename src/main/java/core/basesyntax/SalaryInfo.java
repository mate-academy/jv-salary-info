package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOURS_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate start = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] details = record.split(" ");
            LocalDate workDate = LocalDate.parse(details[DATA_INDEX], DATE_FORMATTER);
            String employee = details[NAME_INDEX];
            int hoursWorked = Integer.parseInt(details[HOURS_INDEX]);
            int hourlyRate = Integer.parseInt(details[INCOME_PER_HOURS_INDEX]);

            if (!workDate.isBefore(start) && !workDate.isAfter(end)) {
                for (int i = 0; i < data.length; i++) {
                    if (names[i].equals(employee)) {
                        salaries[i] += hoursWorked * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append(REPORT_HEADER).append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i])
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
