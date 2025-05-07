package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT_DELIMITER = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] date, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + REPORT_DELIMITER + dateTo);

        for (String name : names) {
            int totalSalary = calculateTotalSalaryForEmployee(name, date, dateStart,
                    dateFinish, FORMATTER);
            builder.append(System.lineSeparator()).append(name)
                    .append(REPORT_DELIMITER).append(totalSalary);
        }

        return builder.toString();
    }

    private int calculateTotalSalaryForEmployee(String name, String[] date, LocalDate dateStart,
                                                LocalDate dateFinish, DateTimeFormatter formatter) {
        int totalSalary = 0;
        for (String record : date) {
            String[] recordParts = record.split(" ");
            LocalDate targetDate = LocalDate.parse(recordParts[0], formatter);

            if ((targetDate.isEqual(dateStart) || targetDate.isAfter(dateStart))
                    && (targetDate.isEqual(dateFinish) || targetDate.isBefore(dateFinish))
                    && name.equals(recordParts[1])) {
                totalSalary += Integer.parseInt(recordParts[2]) * Integer.parseInt(recordParts[3]);
            }
        }
        return totalSalary;
    }
}
