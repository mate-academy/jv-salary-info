package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int WAGE_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                              String dateFrom, String dateTo)
            throws DateTimeParseException {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period " + dateStart.format(formatter)
                + " - " + dateEnd.format(formatter));

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                LocalDate workDayDate = LocalDate.parse(record[DATE_INDEX], formatter);
                int workingHours = Integer.parseInt(record[HOURS_INDEX]);
                int wagePerHour = Integer.parseInt(record[WAGE_INDEX]);
                if (workDayDate.isAfter(dateStart.minusDays(1))
                        && workDayDate.isBefore(dateEnd.plusDays(1))
                        && record[NAME_INDEX].equals(name)) {
                    totalSalary = workingHours * wagePerHour + totalSalary;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
