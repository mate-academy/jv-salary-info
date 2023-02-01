package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int dateFromOfYear = localDateFrom.getDayOfYear();
        int dateToOfYear = localDateTo.getDayOfYear();
        String[] dataArrays;
        LocalDate workDay;

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                dataArrays = data[j].split(" ");
                workDay = LocalDate.parse(dataArrays[0], DATE_TIME_FORMATTER);
                int workDayOfYear = workDay.getDayOfYear();
                int workingHours = Integer.parseInt(dataArrays[2]);
                int incomePerHour = Integer.parseInt(dataArrays[3]);
                if (dateFromOfYear <= workDayOfYear
                        && dateToOfYear >= workDayOfYear
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
