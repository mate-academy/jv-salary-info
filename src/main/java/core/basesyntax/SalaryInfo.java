package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        final int[] employeesSalaryCount = new int[names.length];
        int countOfNames = 0;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            for (String datum : data) {
                LocalDate currentDate = LocalDate.parse(
                        datum.substring(0, datum.indexOf(" ")), formatter);
                if (dateCheck(dateFromFormatted, dateToFormatted, currentDate)) {
                    if (datum.contains(name)) {
                        employeesSalaryCount[countOfNames] += dailySalaryCalculation(datum);
                    }
                }
            }
            reportBuilder.append(
                    System.lineSeparator() + name + " - " + employeesSalaryCount[countOfNames]);
            countOfNames++;
        }
        return reportBuilder.toString();
    }

    private boolean dateCheck(LocalDate dateFrom, LocalDate dateTo, LocalDate date) {
        return date.compareTo(dateFrom) >= 0 && dateTo.compareTo(date) >= 0;
    }

    private int dailySalaryCalculation(String employeeData) {
        int hours;
        int hourlyPay;
        final int firstIndex = employeeData.indexOf(" ") + 1;
        int indexData = employeeData.indexOf(" ", firstIndex);
        hours = Integer.parseInt(employeeData.substring(++indexData,
                employeeData.indexOf(" ", indexData)));
        indexData = employeeData.indexOf(" ", indexData);
        hourlyPay = Integer.parseInt(employeeData.substring(++indexData));
        return hours * hourlyPay;
    }
}
