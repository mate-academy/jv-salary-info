package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int PAYMENT_INDEX = 3;
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeSalaryInfo = new StringBuilder();
        for (String name : names) {
            int salaryForDay = 0;
            employeeSalaryInfo.append(System.lineSeparator()).append(name).append(" - ");
            for (String record : data) {
                int paymentForHour = Integer.parseInt(record.split(" ")[PAYMENT_INDEX]);
                int hoursWorked = Integer.parseInt(record.split(" ")[HOURS_INDEX]);
                LocalDate date = LocalDate.parse(record.split(" ")[DATE_INDEX], DATE_FORMATTER);
                if (date.isEqual(LocalDate.parse(dateFrom, DATE_FORMATTER))
                        || date.isAfter(LocalDate.parse(dateFrom, DATE_FORMATTER))
                        && !date.isAfter(LocalDate.parse(dateTo, DATE_FORMATTER))) {
                    if (name.equals(record.split(" ")[NAME_INDEX])) {
                        salaryForDay += hoursWorked * paymentForHour;
                    }
                }
            }
            employeeSalaryInfo.append(salaryForDay);
        }
        return "Report for period " + dateFrom + " - " + dateTo + employeeSalaryInfo;
    }
}
