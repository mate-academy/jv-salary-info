package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(
            String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo).append("\n");

        for (String name : names) {
            int salary = 0;
            for (String dataInfo : data) {
                if (dataInfo.contains(name)) {
                    String[] array = dataInfo.split(" ");
                    LocalDate currentDate = LocalDate.parse(array[0], FORMATTER);

                    if (currentDate.isAfter(localDateFrom)
                            && currentDate.isBefore(localDateTo.plusDays(1))) {
                        salary += Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                    }
                }
            }
            stringBuilder
                    .append(name)
                    .append(" - ")
                    .append(salary).append("\n");
        }
        System.out.println(stringBuilder.toString().trim());
        return stringBuilder.toString().trim();
    }
}
