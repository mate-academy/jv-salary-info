package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] infoToday = data[j].split(" ");
                if (infoToday[1].equals(names[i])) {
                    LocalDate currentDate = LocalDate.parse(infoToday[0], DATE_TIME_FORMATTER);
                    if (LocalDate.parse(dateFrom, DATE_TIME_FORMATTER).isAfter(currentDate)
                            || LocalDate.parse(dateTo, DATE_TIME_FORMATTER).isBefore(currentDate)) {
                        continue;
                    }
                    salary += Integer.parseInt(infoToday[2]) * Integer.parseInt(infoToday[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}
