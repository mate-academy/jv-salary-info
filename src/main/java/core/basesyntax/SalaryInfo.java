
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY = 1;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int PAY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER).minusDays(DAY);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER).plusDays(DAY);
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataSplit = datum.split(" ");
                LocalDate workDay = LocalDate.parse(dataSplit[DATE_INDEX], DATE_FORMATTER);
                if (dataSplit[NAME_INDEX].equals(name) && (startDate.isBefore(workDay)
                        && workDay.isBefore(endDate))) {
                    salary += Integer.parseInt(dataSplit[WORK_HOURS_INDEX])
                            * Integer.parseInt(dataSplit[PAY_PER_HOUR]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
