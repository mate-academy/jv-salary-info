package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        LocalDate workDay;
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            int hours;
            for (String datum : data) {
                String[] values = datum.split(" ");
                if (datum.contains(name)) {
                    workDay = LocalDate.parse(values[0], DATE_FORMAT);
                    if ((workDay.isAfter(startDate) || workDay.isEqual(startDate))
                            && (workDay.isBefore(endDate) || workDay.isEqual(endDate))) {
                        hours = Integer.parseInt(values[values.length - 2]);
                        salary += hours * Integer.parseInt(values[values.length - 1]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
