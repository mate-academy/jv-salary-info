package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String allDate = getAllDataBetweenDate(data, dateFrom, dateTo);
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        String[] employees = allDate.split(";");
        for (String name : names) {
            int salary = 0;
            for (String dataEmployee : employees) {
                String[] employee = dataEmployee.split(" ");
                if (employee[0].equals(name)) {
                    int time = Integer.parseInt(employee[1]);
                    int money = Integer.parseInt(employee[2]);
                    salary += time * money;
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().strip();
    }

    private String getAllDataBetweenDate(String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder allData = new StringBuilder();
        for (String line : data) {
            String[] dates = line.split(" ");
            LocalDate localDateCurrent = LocalDate.parse(dates[0], formatter);
            if (localDateCurrent.isEqual(localDateFrom)
                    || localDateCurrent.isEqual(localDateTo)
                    || localDateCurrent.isAfter(localDateFrom)
                    && localDateCurrent.isBefore(localDateTo)) {
                allData.append(dates[1]).append(" ");
                allData.append(dates[2]).append(" ");
                allData.append(dates[3]).append(";");
            }
        }
        return allData.toString();
    }
}
