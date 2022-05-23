package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator());
            int salary = 0;
            for (String workDay : data) {
                String[] infoFromDay = workDay.split(" ");
                LocalDate currentDay = LocalDate.parse(infoFromDay[DATE],
                        FORMATTER);
                String nameOfEmployee = infoFromDay[NAME];
                if (name.equals(nameOfEmployee) && localDateFrom.compareTo(currentDay) <= 0
                        && localDateTo.compareTo(currentDay) >= 0) {
                    salary += Integer.parseInt(infoFromDay[HOURS])
                            * Integer.parseInt(infoFromDay[SALARY_PER_HOUR]);
                }
            }
            builder.append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
