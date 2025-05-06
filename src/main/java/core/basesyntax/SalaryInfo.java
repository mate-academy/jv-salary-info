package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DATE_POSITION = 0;
    static final int NAME_POSITION = 1;
    static final int WORK_HOURS_POSITION = 2;
    static final int INCOME_POSITION = 3;

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int employeeSalary = 0;
            for (String dataLine: data) {
                String[] splitData = dataLine.split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[DATE_POSITION], FORMATTER);
                if (!currentDate.isBefore(formattedDateFrom)
                        && !currentDate.isAfter(formattedDateTo)
                        && splitData[NAME_POSITION].equals(name)) {
                    employeeSalary += Integer.parseInt(splitData[WORK_HOURS_POSITION])
                            * Integer.parseInt(splitData[INCOME_POSITION]);
                }
            }
            builder.append(System.lineSeparator()).append(name)
                    .append(" - "). append(employeeSalary);

        }
        return builder.toString();
    }
}
