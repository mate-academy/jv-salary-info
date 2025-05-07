package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_PAY_INDEX = 3;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        int salary = 0;
        LocalDate dateStart = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateFinish = LocalDate.parse(dateTo, FORMATTER);
        LocalDate workDay;
        StringBuilder builder = new StringBuilder();

        for (String name : names) {
            for (String dayInfo : data) {
                String[] columnsData = dayInfo.split(" ");
                workDay = LocalDate.parse(columnsData[DATE_INDEX], FORMATTER);

                if ((name.equals(columnsData[NAME_INDEX]))
                        && ((dateStart.isBefore(workDay) || dateStart.isEqual(workDay))
                        && dateFinish.isAfter(workDay) || dateFinish.isEqual(workDay))) {
                    int hours = Integer.parseInt(columnsData[HOURS_INDEX]);
                    int hourlyPay = Integer.parseInt(columnsData[HOURLY_PAY_INDEX]);
                    salary += hours * hourlyPay;
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
