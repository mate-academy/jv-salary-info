package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int HOUR_PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String employeeName;
        StringBuilder salaryReport = new StringBuilder();
        salaryReport.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            employeeName = name;
            if (name != null) {
                salaryReport.append(System.lineSeparator())
                        .append(name)
                        .append(" - ")
                        .append(salaryCalculator(data, dateFrom, dateTo, employeeName));
            }
        }
        return salaryReport.toString();
    }

    private int salaryCalculator(String[] data, String dateFrom, String dateTo, String employeeName) {
        String[] dataToParts;
        int salary = 0;
        for (String datum : data) {
            dataToParts = datum.split(" ");
            String workingDate = dataToParts[WORKING_DATE_INDEX];
            String name = dataToParts[NAME_INDEX];
            int workingHours = Integer.parseInt(dataToParts[WORKING_HOURS_INDEX]);
            int hourPayment = Integer.parseInt(dataToParts[HOUR_PAYMENT_INDEX]);
            if (employeeName.equals(name) && dateFilter(workingDate, dateFrom, dateTo)) {
                salary = salary + (workingHours * hourPayment);
            }
        }
        return salary;
    }

    public boolean dateFilter(String workingDate, String dateFrom, String dateTo) {
        LocalDate workingDates = LocalDate.parse(workingDate, FORMATTER);
        LocalDate dateFromStr = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToStr = LocalDate.parse(dateTo, FORMATTER);
        return !workingDates.isBefore(dateFromStr) && !workingDates.isAfter(dateToStr);
    }
}
