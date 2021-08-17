package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int salarySum = 0;
            for (String date : data) {
                String[] splittedDate = date.split(" ");
                if (name.equals(splittedDate[1]) && !LocalDate.parse(splittedDate[0], formatter)
                        .isBefore(LocalDate.parse(dateFrom, formatter))
                        && !LocalDate.parse(splittedDate[0], formatter)
                        .isAfter(LocalDate.parse(dateTo, formatter))) {
                    int hours = Integer.parseInt(splittedDate[2]);
                    int salary = Integer.parseInt(splittedDate[3]);
                    salarySum += (hours * salary);
                }
            }
            stringBuilder.append(name).append(" - ").append(salarySum)
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
