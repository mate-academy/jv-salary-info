package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        int salary = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateFinish = LocalDate.parse(dateTo, formatter);
        LocalDate workDay;
        StringBuilder builder = new StringBuilder();

        for (String name : names) {
            for (String dayInfo : data) {
                String[] columnsData = dayInfo.split(" ");
                workDay = LocalDate.parse(columnsData[0], formatter);

                if ((name.equals(columnsData[1]))
                        && ((dateStart.isBefore(workDay) || dateStart.isEqual(workDay))
                        && dateFinish.isAfter(workDay) || dateFinish.isEqual(workDay))) {
                    int hours = Integer.parseInt(columnsData[2]);
                    int hourlyPay = Integer.parseInt(columnsData[3]);
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
