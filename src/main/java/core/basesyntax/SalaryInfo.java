package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate currentDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        this.dateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        this.dateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        String[] details;
        int[] salaries = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            details = data[i].split(" ");
            currentDate = LocalDate.parse(details[0], DATE_FORMATTER);
            if (currentDate.isBefore(this.dateFrom) || currentDate.isAfter(this.dateTo)) {
                continue;
            }
            for (int n = 0; n < names.length; n++) {
                if (details[1].equals(names[n])) {
                    salaries[n] += Integer.parseInt(details[2]) * Integer.parseInt(details[3]);
                }
            }
        }
        for (int j = 0; j < names.length; j++) {
            builder.append(System.lineSeparator()).append(names[j]).append(" - ")
                    .append(salaries[j]);
        }
        return builder.toString();
    }
}
