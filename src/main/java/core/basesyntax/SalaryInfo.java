package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int MONEY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        LocalDate dateFromData = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToData = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        for (Employee employee : employees) {
            for (String dataString : data) {
                String[] dataArray = dataString.split(" ");
                LocalDate date = LocalDate.parse(dataArray[DATE_POSITION], FORMATTER);
                if (employee.getName().equalsIgnoreCase(dataArray[NAME_POSITION])
                        && (date.isAfter(dateFromData) || date.isEqual(dateFromData))
                        && (date.isBefore(dateToData) || date.isEqual(dateToData))) {
                    employee.addEarnedMoney(Integer.parseInt(dataArray[HOURS_POSITION])
                            * Integer.parseInt(dataArray[MONEY_POSITION]));
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (Employee employee : employees) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(employee.getName()).append(" - ")
                    .append(employee.getEarnedMoney());
        }
        return stringBuilder.toString();
    }
}