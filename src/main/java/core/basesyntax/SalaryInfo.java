package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String pattern = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate beginDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate currentDate;

        for (String name: names) {
            int sum = 0;
            for (String dateLine: data) {
                if (dateLine.contains(name)) {
                    String[] record = dateLine.split(" ");
                    currentDate = LocalDate.parse(record[0], formatter);

                    if (currentDate.compareTo(beginDate) == 0 || currentDate.compareTo(endDate) == 0
                            || (currentDate.isAfter(beginDate) && currentDate.isBefore(endDate))) {
                        sum += Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                    }
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return sb.toString();
    }
}
