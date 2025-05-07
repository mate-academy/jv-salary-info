package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOURS_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String result : data) {
                String[] dates = result.split(" ");
                LocalDate day = LocalDate.parse(dates[DATE_INDEX], dateTimeFormatter);
                if (name.equals(dates[NAME_INDEX])) {
                    if ((day.isAfter(fromDate) || day.isEqual(fromDate))
                            && (day.isBefore(toDate) || (day.isEqual(toDate)))) {
                        salary += Integer.parseInt(dates[WORKING_HOURS_INDEX])
                                * Integer.parseInt(dates[SALARY_PER_HOURS_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
