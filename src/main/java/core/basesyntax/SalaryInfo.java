package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");

        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int sum = 0;

            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");

                LocalDate thisDate = LocalDate.parse(splitData[0], formatter);
                boolean hasDate = (thisDate.isAfter(startDate) && thisDate.isBefore(endDate.plusDays(1)));
                boolean hasName = splitData[1].equals(names[i]);

                if (hasDate && hasName) {
                    int salary = Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                    sum += salary;
                }
            }

            stringBuilder.append("\n").append(names[i]).append(" - ").append(sum);
        }

        return stringBuilder.toString();
    }
}
