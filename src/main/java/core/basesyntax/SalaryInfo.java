package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder();

        builder.append("Report ").append("for ").append("period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info: data) {
                String[] splitData = info.split(" ");
                LocalDate localDate = LocalDate.parse(splitData[0], DATE_FORMAT);

                if (splitData[1].equals(name)
                        && (localDate.isAfter(localDateFrom) || localDate.isEqual(localDateFrom))
                        && (localDate.isBefore(localDateTo) || localDate.isEqual(localDateTo))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            builder.append("\n").append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
