package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate start = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String name:names) {
            int userSalary = 0;
            for (String date: data) {
                String [] userInfo = date.split(" ");
                LocalDate currentDate
                        = LocalDate.parse(userInfo[0], DATE_TIME_FORMATTER);
                if (userInfo[1].equals(name)
                        && (currentDate.isAfter(start) || currentDate.isEqual(start))
                        && (currentDate.isBefore(end) || currentDate.isEqual(end))) {
                    userSalary = userSalary + Integer.parseInt(userInfo[2])
                                * Integer.parseInt(userInfo[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(userSalary);
        }
        return result.toString();
    }
}
