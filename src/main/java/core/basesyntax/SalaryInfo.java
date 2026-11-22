
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo
                        + System.lineSeparator()
        );

        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        boolean firstEmployee = true;

        for (String employee : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");

                if (parts[NAME_INDEX].equals(employee)) {
                    LocalDate workDate = LocalDate.parse(
                            parts[DATE_INDEX], DATE_FORMATTER
                    );

                    if (!workDate.isBefore(startDate)
                            && !workDate.isAfter(endDate)) {
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int rate = Integer.parseInt(parts[RATE_INDEX]);

                        totalSalary += hours * rate;
                    }
                }
            }

            if (!firstEmployee) {
                report.append(System.lineSeparator());
            }
            firstEmployee = false;

            report.append(employee)
                    .append(" - ")
                    .append(totalSalary);
        }

        return report.toString();
    }
}
