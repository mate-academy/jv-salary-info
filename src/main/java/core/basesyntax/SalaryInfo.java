package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int NAME_INDEX = 1;
    static final int WORKING_HOURS_INDEX = 2;
    static final int INCOME_PER_HOUR_INDEX = 3;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate currentDate;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
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
                if (details[NAME_INDEX].equals(names[n])) {
                    salaries[n] += Integer.parseInt(details[WORKING_HOURS_INDEX])
                            * Integer.parseInt(details[INCOME_PER_HOUR_INDEX]);
                }
            }
        }
        for (int j = 0; j < names.length; j++) {
            result.append(System.lineSeparator()).append(names[j]).append(" - ")
                    .append(salaries[j]);
        }
        return result.toString();
    }
}
