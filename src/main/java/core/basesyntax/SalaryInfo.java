package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_PAY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                if (datum.contains(name)) {
                    String[] employeeData = datum.split(" ");
                    LocalDate currentDate = LocalDate.parse(employeeData[DATE_INDEX], FORMATTER);
                    if (currentDate.compareTo(dateFromFormatted) >= 0
                            && dateToFormatted.compareTo(currentDate) >= 0) {
                        salary += Integer.parseInt(employeeData[HOURS_INDEX])
                                * Integer.parseInt(employeeData[HOURLY_PAY_INDEX]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
