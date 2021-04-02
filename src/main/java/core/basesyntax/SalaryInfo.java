package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder sb = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salarySumm = 0;
            for (String value : data) {
                if (value.contains(name)) {
                    String[] valuesFromData = value.split(" ");
                    LocalDate separateDate = LocalDate.parse(valuesFromData[0],
                            DATE_TIME_FORMATTER);
                    if (separateDate.isAfter(startDate) && separateDate
                            .isBefore(finishDate.plusDays(1))) {
                        salarySumm += Integer.valueOf(valuesFromData[2])
                                * Integer.valueOf(valuesFromData[3]);
                    }
                }
            }
            sb.append("\n").append(name).append(" - ").append(salarySumm);
        }
        return sb.toString();
    }
}
