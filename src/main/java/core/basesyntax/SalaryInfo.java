package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate dateFromData;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        int namePosition;
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] tmp = data[i].split(" ");
            dateFromData = LocalDate.parse(tmp[0], formatter);
            if (dateFromData.isAfter(dateStart.minusDays(1))
                    && dateFromData.isBefore(dateEnd.plusDays(1))) {
                namePosition = Arrays.asList(names).indexOf(tmp[1]);
                salary[namePosition] += Integer.parseInt(tmp[2]) * Integer.parseInt(tmp[3]);
            }
        }
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }
}
