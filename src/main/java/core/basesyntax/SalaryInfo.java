package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int POSITION_OF_DATE = 0;
    public static final int POSITION_OF_NAME = 1;
    public static final int POSITION_OF_HOURS = 2;
    public static final int POSITION_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedMinDate = LocalDate.parse(dateFrom, DATE);
        LocalDate parsedMaxDate = LocalDate.parse(dateTo, DATE);

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        try {
            for (int i = 0; i < names.length; i++) {
                int salary = 0;
                for (int j = 0; j < data.length; j++) {
                    String[] dataOfDay = data[j].split(" ");
                    LocalDate localDate = LocalDate.parse(dataOfDay[POSITION_OF_DATE], DATE);
                    if (names[i].equals(dataOfDay[POSITION_OF_NAME])
                            && !localDate.isBefore(parsedMinDate)
                            && !localDate.isAfter(parsedMaxDate)) {
                        salary += Integer.parseInt(dataOfDay[POSITION_OF_HOURS])
                                * Integer.parseInt(dataOfDay[POSITION_OF_INCOME_PER_HOUR]);
                    }
                }
                strBuilder.append(System.lineSeparator())
                        .append(names[i])
                        .append(" - ")
                        .append(salary);
            }
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParseException! Some date cannot be formatted");
        }
        return strBuilder.toString();
    }
}
