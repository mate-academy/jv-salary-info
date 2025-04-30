package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_ARRAY_INDEX = 0;
    private static final int NAME_ARRAY_INDEX = 1;
    private static final int WORK_HOURS_ARRAY_INDEX = 2;
    private static final int SALARY_PER_HOUR_ARRAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, dateFormat);
            LocalDate toDate = LocalDate.parse(dateTo, dateFormat);

            report
                    .append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());

            for (String name : names) {
                int totalEarnings = 0;
                for (String entry : data) {
                    String[] entryParts = entry.split(" ");
                    String entryDateStr = entryParts[DATE_ARRAY_INDEX];
                    String employeeName = entryParts[NAME_ARRAY_INDEX];
                    int hoursWorked = Integer.parseInt(entryParts[WORK_HOURS_ARRAY_INDEX]);
                    int hourlyRate = Integer.parseInt(entryParts[SALARY_PER_HOUR_ARRAY_INDEX]);

                    LocalDate entryDate = LocalDate.parse(entryDateStr, dateFormat);

                    if (entryDate.isAfter(fromDate) && entryDate.isBefore(toDate)
                            || entryDate.equals(fromDate)
                            || entryDate.equals(toDate)) {
                        if (employeeName.equals(name)) {
                            totalEarnings += hoursWorked * hourlyRate;
                        }
                    }
                }

                report
                        .append(name)
                        .append(" - ")
                        .append(totalEarnings);

                if (!lastEntry(names, name)) {
                    report.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing dates: " + e.getMessage());
        }

        return report.toString();
    }

    private static boolean lastEntry(String[] names, String name) {
        return name.equals(names[names.length - 1]);
    }
}
