package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int dateIndex = 0;
        final int nameIndex = 1;
        final int hoursIndex = 2;
        final int paymentIndex = 3;
        StringBuilder employeeSalaryInfo = new StringBuilder();
        int paymentForHour;
        int salaryForDay = 0;
        int hoursWorked;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;

        for (String name : names) {
            employeeSalaryInfo.append(System.lineSeparator()).append(name).append(" - ");
            for (String record : data) {
                paymentForHour = Integer.parseInt(record.split(" ")[paymentIndex]);
                hoursWorked = Integer.parseInt(record.split(" ")[hoursIndex]);
                date = LocalDate.parse(record.split(" ")[dateIndex], formatter);
                if (date.isEqual(LocalDate.parse(dateFrom, formatter))
                        || date.isAfter(LocalDate.parse(dateFrom, formatter))
                        && !date.isAfter(LocalDate.parse(dateTo, formatter))) {
                    if (name.equals(record.split(" ")[nameIndex])) {
                        salaryForDay += hoursWorked * paymentForHour;
                    }
                }
            }
            employeeSalaryInfo.append(salaryForDay);
            salaryForDay = 0;
        }
        return "Report for period " + dateFrom + " - " + dateTo + employeeSalaryInfo;
    }
}
