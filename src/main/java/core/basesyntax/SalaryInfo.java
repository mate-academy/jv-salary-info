package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate finishDate = LocalDate.parse(dateTo, formatter).plusDays(1);

        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int daySalary = 0;

        for (String name : names) {
            for (String employeeWorkingDayDatum : data) {
                String[] employeeInfo = employeeWorkingDayDatum.split(" ");
                LocalDate workDate = LocalDate.parse(employeeInfo[0], formatter);
                if (employeeWorkingDayDatum.contains(name)
                        && workDate.isAfter(startDate)
                        && workDate.isBefore(finishDate)) {
                    daySalary += Integer.parseInt(employeeInfo[2])
                            * Integer.parseInt(employeeInfo[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(daySalary);
            daySalary = 0;
        }

        return result.toString();
    }
}
