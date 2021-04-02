package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);
        LocalDate day;

        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (String name: names) {
            int income = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String[] info = line.split(" ");
                    day = LocalDate.parse(info[0], DATE_FORMAT);
                    if (day.plusDays(1).isAfter(from) && day.minusDays(1).isBefore(to)) {
                        income += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                    }
                }
            }
            salaryInfo.append(name).append(" - ").append(income).append("\n");
        }
        return salaryInfo.toString().trim();
    }
}
