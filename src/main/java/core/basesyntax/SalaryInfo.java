package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] temp = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(temp[0], FORMATTER);

                if (temp[1].equals(names[i])
                        && ((currentDate.isEqual(localDateFrom) || currentDate.isEqual(localDateTo))
                        || (currentDate.isAfter(localDateFrom)
                        && currentDate.isBefore(localDateTo)))) {
                    salaries[i] += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < salaries.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return builder.toString();
    }
}
