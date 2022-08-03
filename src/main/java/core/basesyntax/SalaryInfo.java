package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS_WORKED = 2;
    private static final int RATE_PER_HOUR = 3;
    private final DateConverter dateConverter = new DateConverter();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = dateConverter.convert(dateFrom);
        LocalDate toDate = dateConverter.convert(dateTo);

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataElement : data) {
                String[] dataItems = dataElement.split(" ");
                final String employeeName = dataItems[NAME];
                final int hoursWorked = Integer.parseInt(dataItems[HOURS_WORKED]);
                final int ratePerHour = Integer.parseInt(dataItems[RATE_PER_HOUR]);
                LocalDate dateElement = dateConverter.convert(dataItems[DATE]);
                if (name.equals(employeeName)
                        && (dateElement.isAfter(fromDate)
                        && dateElement.isBefore(toDate)
                        || dateElement.equals(fromDate)
                        || dateElement.equals(toDate))) {
                    salary += hoursWorked * ratePerHour;
                }
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
