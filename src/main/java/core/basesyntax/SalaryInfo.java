package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String string : data) {
                String[] dataInfo = string.split(" ");
                LocalDate localDate = LocalDate.parse(dataInfo[0], FORMATTER);
                if ((localDate.equals(from) || localDate.equals(to) || localDate.isAfter(from)
                        && localDate.isBefore(to)) && name.equals(dataInfo[1])) {
                    salary += Integer.parseInt(dataInfo[2]) * Integer.parseInt(dataInfo[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
