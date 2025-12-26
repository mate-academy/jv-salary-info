package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int sum = 0;
            for (String oneData: data) {
                String[] parts = oneData.split(" ");
                String employeeName = parts[INDEX_ONE];
                if (employeeName.equals(name)) {
                    String employeeStringDate = parts[INDEX_ZERO];
                    LocalDate employeeDate =
                            LocalDate.parse(employeeStringDate, DATE_TIME_FORMATTER);
                    if (!employeeDate.isAfter(to) && !employeeDate.isBefore(from)) {
                        int hours = Integer.parseInt(parts[INDEX_TWO]);
                        int salary = Integer.parseInt(parts[INDEX_THREE]);
                        sum += hours * salary;
                    }
                }

            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }

        return builder.toString();
    }
}
