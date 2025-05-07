package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int RATE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo);
        final LocalDate beginning = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate end = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int incomeEmployee = calculateIncomeForEmployee(name, data, beginning, end);
            report.append(System.lineSeparator()).append(name).append(" - ").append(incomeEmployee);
        }
        return report.toString();
    }

    private int calculateIncomeForEmployee(String name, String[] data,
                                           LocalDate beginning, LocalDate end) {
        int incomeEmployee = 0;

        for (String datum : data) {
            String[] dataElements = datum.split(" ");
            LocalDate currentDate = LocalDate.parse(dataElements[DATE], FORMATTER);

            if ((currentDate.isAfter(beginning) || currentDate.isEqual(beginning))
                    && (currentDate.isBefore(end) || currentDate.isEqual(end))
                    && name.equals(dataElements[NAME])) {
                incomeEmployee += Integer.parseInt(dataElements[HOURS])
                        * Integer.parseInt(dataElements[RATE]);
            }
        }
        return incomeEmployee;
    }
}
