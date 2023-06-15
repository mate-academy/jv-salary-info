package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                              String dateFrom, String dateTo)
            throws DateTimeParseException {
        int wagePerHour;
        int workingHours;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        LocalDate workDayDate;
        StringBuilder report = new StringBuilder("Report for period " + dateStart.format(formatter)
                + " - " + dateEnd.format(formatter));
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] record = datum.split(" ");
                workDayDate = LocalDate.parse(record[0], formatter);
                workingHours = Integer.parseInt(record[2]);
                wagePerHour = Integer.parseInt(record[3]);

                if (workDayDate.isAfter(dateStart.minusDays(1))
                        && workDayDate.isBefore(dateEnd.plusDays(1))
                        && record[1].equals(name)) {
                    totalSalary = workingHours * wagePerHour + totalSalary;
                }
            }
            report.append(System.lineSeparator());
            report.append(name);
            report.append(" - ");
            report.append(totalSalary);
        }
        return report.toString();
    }
}
