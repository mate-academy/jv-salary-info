package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATION_WITH_DOT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String nameOfEmployee = "";
        LocalDate date;
        LocalDate dateFromParsed;
        LocalDate dateToParsed;
        int timesGotSalary;
        int salary;
        int[] arrayOfSalaries = new int[names.length];
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] employeesInformation = data[i].split(" ");
                nameOfEmployee = employeesInformation[1];
                timesGotSalary = Integer.parseInt(employeesInformation[2]);
                salary = Integer.parseInt(employeesInformation[3]);
                String dateWithoutDot = employeesInformation[0];
                date = LocalDate.parse(dateWithoutDot, FORMATION_WITH_DOT);
                dateFromParsed = LocalDate.parse(dateFrom, FORMATION_WITH_DOT);
                dateToParsed = LocalDate.parse(dateTo, FORMATION_WITH_DOT);

                if (dateFromParsed.minusDays(1).isBefore(date)
                        && dateToParsed.plusDays(1).isAfter(date)) {
                    if (nameOfEmployee.equals(names[j])) {
                        arrayOfSalaries[j] += timesGotSalary * salary;
                    } else {
                        arrayOfSalaries[j] += 0;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; ) {
            for (int employeeSalary : arrayOfSalaries) {
                str.append(names[i]).append(" - ").append(employeeSalary).append("\n");
                i++;
            }
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}
