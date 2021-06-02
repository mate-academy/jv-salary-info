package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int amountSalary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] array = info.split(" ");
                    LocalDate day = LocalDate.parse(array[0], FORMATTER);
                    if (day.compareTo(from) >= 0 && day.compareTo(to) <= 0) {
                        amountSalary += Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                    }
                }
            }
            result.append(name).append(" - ").append(amountSalary).append("\n");
        }
        return result.toString().trim();
    }
}
