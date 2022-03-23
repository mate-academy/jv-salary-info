package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataSplit = data[j].split(" ");
                LocalDate date = LocalDate.parse(dataSplit[0], formatter);
                if (((date.isBefore(endDate) && date.isAfter(startDate) || date.equals(endDate)))
                        && names[i].equals(dataSplit[1])) {
                    salary += Integer.valueOf(dataSplit[2]) * Integer.valueOf(dataSplit[3]);
                }
            }
            builder.append(names[i]).append(" - ").append(salary).append(System.lineSeparator());
            salary = 0;
        }
        return builder.toString().trim();
    }
}
