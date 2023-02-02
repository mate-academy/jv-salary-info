package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO = 0;
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

        for (int i = ZERO; i < names.length; i++) {
            salary = ZERO;
            for (int j = ZERO; j < data.length; j++) {
                dataArrays = data[j].split(SPLIT_BY_SPACE);
                workDay = LocalDate.parse(dataArrays[0], DATE_TIME_FORMATTER);
                workingHours = Integer.parseInt(dataArrays[2]);
                incomePerHour = Integer.parseInt(dataArrays[3]);

                if (localDateFrom.isBefore(workDay)
                        && (localDateTo.isAfter(workDay)
                        || localDateTo.equals(workDay))
                        && names[i].equals(dataArrays[1])) {
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
