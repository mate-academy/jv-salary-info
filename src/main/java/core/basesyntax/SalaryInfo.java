package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class SalaryInfo {
    private static final int DATE_FRM_ARRAY_INDEX = 0;
    private static final int NAME_FRM_ARRAY_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final String REPORT_PERIOD_MSG = "Report for period ";
    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy").toFormatter();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder().append(REPORT_PERIOD_MSG)
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name:names) {
            int salary = 0;
            for (String array : data) {
                String[] split = array.split(" ");
                LocalDate arrayDate = LocalDate.parse(split[DATE_FRM_ARRAY_INDEX], formatter);
                if (arrayDate.isEqual(startDate) | arrayDate.isAfter(startDate)
                        && arrayDate.isBefore(endDate) | arrayDate.isEqual(endDate)
                        && name.equals(split[NAME_FRM_ARRAY_INDEX])) {
                    salary += Integer.parseInt(split[WORKING_HOURS_INDEX])
                           * Integer.parseInt(split[SALARY_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(name).append(" - ").append(salary)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}

