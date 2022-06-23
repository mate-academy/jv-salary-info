package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeSalaryOfPeriod = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        dateBegin = dateBegin.minusDays(1);
        dateEnd = dateEnd.plusDays(1);
        for (String name : names) {
            int totalSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] temp = data[i].split(" ");
                if (LocalDate.parse(temp[0], FORMATTER).isAfter(dateBegin)
                        && LocalDate.parse(temp[0], FORMATTER).isBefore(dateEnd)
                        && name.equals(temp[1])) {
                    int hours = Integer.parseInt(temp[2]);
                    int hourSalary = Integer.parseInt(temp[3]);
                    totalSalary = totalSalary + hourSalary * hours;
                }
            }
            employeeSalaryOfPeriod
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return employeeSalaryOfPeriod.toString();
    }
}
