package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalEarnings = 0;
            for (String currentData : data) {
                String[] parts = currentData.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int workedHours = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                int salaryPerHour = Integer.parseInt(parts[SALARY_PER_HOUR_INDEX]);

                if (!localDateTo.isBefore(date)
                        && !localDateFrom.isAfter(date)
                        && employeeName.equals(name)) {
                    totalEarnings += workedHours * salaryPerHour;
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalEarnings);
        }
        return report.toString();
    }
}
