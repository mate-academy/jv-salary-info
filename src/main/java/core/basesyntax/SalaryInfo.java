package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORK_DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SPLIT_BY_SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        String[] dataArrays;
        LocalDate workDay;
        int salary;
        int workingHours;
        int incomePerHour;

        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int j = 0; j < data.length; j++) {
                dataArrays = data[j].split(SPLIT_BY_SPACE);
                workDay = LocalDate.parse(dataArrays[WORK_DAY_INDEX], DATE_TIME_FORMATTER);
                workingHours = Integer.parseInt(dataArrays[WORKING_HOURS_INDEX]);
                incomePerHour = Integer.parseInt(dataArrays[INCOME_PER_HOUR_INDEX]);

                if (localDateFrom.isBefore(workDay)
                        && (localDateTo.isAfter(workDay)
                        || localDateTo.equals(workDay))
                        && names[i].equals(dataArrays[NAME_INDEX])) {
                    salary += workingHours * incomePerHour;
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
