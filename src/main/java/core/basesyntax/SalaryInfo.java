package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int date = 0;
        final int hours = 2;
        final int hourlyPay = 3;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate dateFromFormatted = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateToFormatted = LocalDate.parse(dateTo, formatter);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                if (datum.contains(name)) {
                    String[] employeeData = datum.split(" ");
                    LocalDate currentDate = LocalDate.parse(employeeData[date], formatter);
                    if (currentDate.compareTo(dateFromFormatted) >= 0
                            && dateToFormatted.compareTo(currentDate) >= 0) {
                        salary += Integer.parseInt(employeeData[hours])
                                * Integer.parseInt(employeeData[hourlyPay]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
