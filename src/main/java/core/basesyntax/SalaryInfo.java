package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATION_WITH_DOT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate date;
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, FORMATION_WITH_DOT);
        LocalDate dateToParsed = LocalDate.parse(dateTo, FORMATION_WITH_DOT);
        int timesGotSalary;
        int salary;
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (String name: names) {
            int employeeSalary = 0;
            for (String datum : data) {
                String[] employeesInformation = datum.split(" ");
                String nameOfEmployee = employeesInformation[1];
                timesGotSalary = Integer.parseInt(employeesInformation[2]);
                salary = Integer.parseInt(employeesInformation[3]);
                String dateWithoutDot = employeesInformation[0];
                date = LocalDate.parse(dateWithoutDot, FORMATION_WITH_DOT);

                if (dateFromParsed.minusDays(1).isBefore(date)
                        && dateToParsed.plusDays(1).isAfter(date)) {
                    if (nameOfEmployee.equals(name)) {
                        employeeSalary += timesGotSalary * salary;
                    } else {
                        employeeSalary += 0;
                    }
                }
            }
            str.append(name).append(" - ").append(employeeSalary).append("\n");
        }
        return str.toString().trim();
    }
}
