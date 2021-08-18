package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMAT);
            LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMAT);
            for (int i = 0; i < names.length; i++) {
                int salary = 0;
                for (int j = 0; j < data.length; j++) {
                    String[] dataOfDay = data[j].split("\\s");
                    LocalDate localDate = LocalDate.parse(dataOfDay[0], DATE_FORMAT);
                    if (names[i].equals(dataOfDay[1])
                            && !localDate.isBefore(dateFromFormatted)
                            && !localDate.isAfter(dateToFormatted)) {
                        salary += Integer.parseInt(dataOfDay[2]) * Integer.parseInt(dataOfDay[3]);
                    }
                }
                stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            }
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParseException! Some date cannot be formatted");
        }
        return stringBuilder.toString();
    }
}
