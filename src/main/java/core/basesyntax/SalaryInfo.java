package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int totalSalary = 0;
            for (String currentData : data) {
                String[] dataSplit = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(dataSplit[DATE_INDEX], DATE_FORMATTER);
                if (dataSplit[NAME_INDEX].equals(name)
                        && !currentDate.isAfter(to)
                        && !currentDate.isBefore(from)) {
                    totalSalary += Integer.parseInt(dataSplit[HOURS_INDEX])
                            * Integer.parseInt(dataSplit[SALARY_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name).append(" - ").append(totalSalary);
        }

        return builder.toString();
    }
}
