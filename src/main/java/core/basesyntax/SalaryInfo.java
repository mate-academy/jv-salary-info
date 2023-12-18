package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        LocalDate startDate = LocalDate.parse(dateFrom, formatDate);
        LocalDate endDate = LocalDate.parse(dateTo, formatDate);

        for (String name : names) {
            int totalEarnings = 0;

            for (String lines : data) {
                String[] parts = lines.split(" ");
                LocalDate infoDate = LocalDate.parse(parts[DATE_INDEX], formatDate);

                if (!infoDate.isBefore(startDate) && !infoDate.isAfter(endDate)
                        && name.equals(parts[NAME_INDEX])) {
                    int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                    int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
                    totalEarnings += hoursWorked * incomePerHour;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalEarnings);
        }

        return report.toString();
    }
}
