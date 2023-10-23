package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT_HEADER = "Report for period %s - %s"
            + System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        int[] salaryInfo = new int[names.length];
        for (String datum : data) {
            String[] info = datum.split(" ");
            LocalDate date = LocalDate.parse(info[0], DATE_FORMATTER);
            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                String name = info[1];
                int hoursWorked = Integer.parseInt(info[2]);
                int incomePerHour = Integer.parseInt(info[3]);
                int currentSalary = (hoursWorked * incomePerHour);
                int index = -1;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    salaryInfo[index] += currentSalary;
                }
            }
        }
        StringBuilder report = new StringBuilder(String.format(REPORT_HEADER, dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaryInfo[i])
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
