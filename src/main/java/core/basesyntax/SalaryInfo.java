package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String employeeName;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            employeeName = name;
            if (name != null) {
                builder.append(System.lineSeparator())
                        .append(name)
                        .append(" - ")
                        .append(salaryCalculator(data, dateFrom, dateTo));
            }
        }
        return builder.toString();
    }

    private int salaryCalculator(String[] data, String dateFrom, String dateTo) {
        String[] dataToParts;
        int salary = 0;
        for (String datum : data) {
            dataToParts = datum.split(" ");
            String workingDate = dataToParts[0];
            String name = dataToParts[1];
            int workingHours = Integer.parseInt(dataToParts[2]);
            int hourPayment = Integer.parseInt(dataToParts[3]);
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
        return (workingDates.isEqual(dateFromStr) || workingDates.isAfter(dateFromStr))
                && (workingDates.isEqual(dateToStr) || workingDates.isBefore(dateToStr));
    }
}
