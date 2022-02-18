package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.y");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateTimeFormatter);
        int[] salaries = new int[names.length];
        for (String string : data) {
            String[] parts = string.split(" ");
            LocalDate date = LocalDate.parse(parts[0], dateTimeFormatter);
            if (date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0) {
                continue;
            }
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(parts[1])) {
                    int hours = Integer.parseInt(parts[2]);
                    int incomePerHour = Integer.parseInt(parts[3]);
                    salaries[i] += incomePerHour * hours;
                    break;
                }
            }
        }
        for (int i = 0 ; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
