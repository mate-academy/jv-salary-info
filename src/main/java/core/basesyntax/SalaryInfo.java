package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String emloyeeData : data) {
                String[] parts = emloyeeData.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_FORMAT);

                if (date.isAfter(toDate) || date.isBefore(fromDate)) {
                    continue;
                }
                if (parts[NAME_INDEX].equals(name)) {
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    int rate = Integer.parseInt(parts[RATE_INDEX]);
                    totalSalary += hours * rate;
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
