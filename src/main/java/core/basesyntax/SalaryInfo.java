package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] salary = new int[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (String datum : data) {
            String[] str = datum.split(" ");
            String name = str[1];
            LocalDate date = LocalDate.parse(str[0], formatter);
            LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
            LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
            int salaryInDay = (Integer.parseInt(str[2]) * Integer.parseInt(str[3]));
            if ((date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                    && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(name)) {
                        salary[j] += salaryInDay;
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return result.toString();
    }
}
