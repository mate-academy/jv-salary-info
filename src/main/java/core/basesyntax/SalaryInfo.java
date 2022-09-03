package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPLITTER = " ";
    private static final String HYPHEN = " - ";
    private static final String HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        StringBuilder reportForPeriod = new StringBuilder(HEADER).append(dateFrom)
                .append(HYPHEN).append(dateTo);
        for (String employee: names) {
            int dailySalary;
            int salary = 0;
            for (String employeeData : data) {
                String[] details = employeeData.split(SPLITTER);
                if (employee.equals(details[1])) {
                    LocalDate date = getDate(details[0]);
                    if (date.compareTo(localDateFrom) >= 0 && date.compareTo(localDateTo) <= 0) {
                        dailySalary = Integer.parseInt(details[2]) * Integer.parseInt(details[3]);
                        salary += dailySalary;
                    }
                }
            }
            reportForPeriod.append(System.lineSeparator()).append(employee)
                        .append(HYPHEN).append(salary);
        }
        return reportForPeriod.toString();
    }

    private LocalDate getDate(String input) {
        return LocalDate.parse(input, FORMATTER);
    }
}
