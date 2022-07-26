package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate datesFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate datesTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String dataLine : data) {
                String[] split = dataLine.split(" ");
                LocalDate currentDate = LocalDate.parse(split[DATE_INDEX], FORMATTER);
                if ((currentDate.equals(datesFrom) || currentDate.isAfter(datesFrom))
                        && (currentDate.equals(datesTo) || currentDate.isBefore(datesTo))
                        && split[NAME_INDEX].equals(name)) {
                    totalSalary += Integer.parseInt(split[HOUR_INDEX])
                            * Integer.parseInt(split[SALARY_PER_HOUR_INDEX]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return builder.toString();
    }
}
