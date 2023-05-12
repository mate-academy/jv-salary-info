package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            builder.append(name).append(" - ");
            for (String dataUnit : data) {
                String[] dataUnitToArray = dataUnit.split(" ");
                if (dataUnit.contains(name)
                        && isValidDate(dataUnitToArray[0], dateFrom, dateTo)) {
                    salary += Integer.parseInt(dataUnitToArray[2])
                            * Integer.parseInt(dataUnitToArray[3]);
                }
            }
            builder.append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private boolean isValidDate(String date, String dateFrom, String dateTo) {
        try {
            LocalDate currentDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate dateEnd = LocalDate.parse(dateTo, DATE_FORMATTER);
            return (currentDate.compareTo(dateStart) >= 0)
                    && (currentDate.compareTo(dateEnd) <= 0);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", date);
            System.out.printf("%s is not parsable!%n", dateFrom);
            System.out.printf("%s is not parsable!%n", dateTo);
        }
        return false;
    }
}




