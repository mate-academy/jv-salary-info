package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
             DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] splitData = record.split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[0], FORMATTER);
                String currentName = splitData[1];
                int hoursWorked = Integer.parseInt(splitData[2]);
                int incomePerHour = Integer.parseInt(splitData[3]);

                if (currentName.equals(name)
                        && (currentDate.isEqual(startDate)
                        || currentDate.isAfter(startDate))
                        && (currentDate.isEqual(endDate)
                        || currentDate.isBefore(endDate))) {
                    totalSalary += hoursWorked * incomePerHour;
                }
            }

            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return report.toString();
    }
}
