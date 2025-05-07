package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate givenDateFrom = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate givenDateTo = LocalDate.parse(dateTo, formatter).plusDays(1);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int sum = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] employeeInfo = info.split(" ");
                    LocalDate arrayDate = LocalDate.parse(employeeInfo[0], formatter);
                    if (arrayDate.isAfter(givenDateFrom) && arrayDate.isBefore(givenDateTo)) {
                        sum += Integer.parseInt(employeeInfo[2])
                                * Integer.parseInt(employeeInfo[3]);
                    }
                }
            }
            builder.append(name).append(" - ").append(sum).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
