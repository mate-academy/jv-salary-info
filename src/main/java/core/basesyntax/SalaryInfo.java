package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (String name: names) {
            int periodSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splitData = data[i].split(" ");
                if (splitData[1].equals(name)
                        && LocalDate.parse(splitData[0], DATE_TIME_FORMATTER)
                        .isAfter(startDate.minusDays(1))
                        && LocalDate.parse(splitData[0], DATE_TIME_FORMATTER)
                        .isBefore(endDate.plusDays(1))) {
                    periodSalary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            reportBuilder.append(name).append(" - ").append(periodSalary).append("\n");
        }
        return reportBuilder.toString().trim();
    }
}
