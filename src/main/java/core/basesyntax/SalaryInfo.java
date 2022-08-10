package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        int[] money = new int[names.length];
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String newData : data) {
            String[] salaryPerDay = newData.split(" ");
            LocalDate dataDate = LocalDate.parse(salaryPerDay[0], FORMATTER);
            if (!(dataDate.isBefore(firstDate)) && !(dataDate.isAfter(lastDate))) {
                for (int k = 0; k < names.length; k++) {
                    if (names[k].equals(salaryPerDay[1])) {
                        money[k] = money[k] + Integer.parseInt(salaryPerDay[2])
                                * Integer.parseInt(salaryPerDay[3]);
                    }
                }
            }
        }
        for (int r = 0; r < names.length; r++) {
            builder.append(System.lineSeparator()).append(names[r]).append(" - ")
                    .append(money[r]);
        }
        return builder.toString();
    }
}
