package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter
            DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    public static final int EMPLOYEE_NAME = 1;
    public static final int RATE = 2;
    public static final int MONEY = 3;

    public static String getSalaryInfo(String[] names,
                                       String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            long salary = 0;
            for (String line : data) {
                String[] lineInfo = line.split(" ");
                LocalDate workingDate = LocalDate.parse(lineInfo[0], DATE_TIME_FORMATTER);
                String employeeName = lineInfo[EMPLOYEE_NAME];
                int rate = Integer.parseInt(lineInfo[RATE]);
                long money = Integer.parseInt(lineInfo[MONEY]);
                if (name.equals(employeeName)) {
                    if ((workingDate.isAfter(from) || workingDate.equals(from))
                            && (workingDate.isBefore(to) || workingDate.equals(to))) {
                        salary += rate * money;
                    }
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
