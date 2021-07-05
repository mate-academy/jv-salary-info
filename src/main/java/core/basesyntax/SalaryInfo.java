package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.getProperty("Line.separator"));

        for(String name : names) {
            int salarySum = 0;
            for(String date : data) {
                if(name.equals(date.substring(11, 11 + name.length()))
                        && !parseDate(date.substring(0, 10)).isBefore(parseDate(dateFrom))
                        && !parseDate(date.substring(0, 10)).isAfter(parseDate(dateTo))) {
                    int hours = Integer.parseInt(date.split(" ")[2]);
                    int salary = Integer.parseInt(date.split(" ")[3]);
                    salarySum += (hours * salary);
                }
                result.append(name).append(" - ").append(salarySum).append(System.getProperty("Line.separator"));
            }
        }

        return result.toString().trim();
    }

    public final LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(date, formatter);
    }
}
