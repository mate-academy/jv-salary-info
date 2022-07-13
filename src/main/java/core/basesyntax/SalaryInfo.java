package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String array:names) {
            builder.append(System.lineSeparator()).append(array).append(" - ");
            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                String[] newArray = data[i].split(" ");
                LocalDate date = LocalDate.parse(newArray[0], FORMATTER);
                if (date.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && !date.isAfter(LocalDate.parse(dateTo, FORMATTER))
                        && array.equals(newArray[1])) {
                    sum += Integer.parseInt(newArray[2]) * Integer.parseInt(newArray[3]);
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
