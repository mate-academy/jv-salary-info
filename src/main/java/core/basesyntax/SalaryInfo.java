package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int SALARY_PER_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String separator = System.lineSeparator();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] record = data[j].split(" ");
                LocalDate period = LocalDate.parse(record[DATE], TIME_FORMATTER);
                if ((name.equals(record[EMPLOYEE_NAME]))
                        && ((period.isAfter(localDateFrom) && period.isBefore(localDateTo))
                        || period.isEqual(localDateFrom)
                        || period.isEqual(localDateTo))) {
                    salary += Integer.valueOf(record[WORK_HOURS]) * Integer.valueOf(record[SALARY_PER_HOURS]);
                }
            }
            stringBuilder.append(separator).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
