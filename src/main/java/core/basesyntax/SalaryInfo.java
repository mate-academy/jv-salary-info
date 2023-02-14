package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int salary;

        result.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String nameFromData : data) {

                if (names[i].equals(nameFromData.split(" ")[1])
                        && validate(dateFrom, dateTo, nameFromData.split(" ")[0])) {
                    salary += Integer.parseInt(nameFromData.split(" ")[2])
                            * Integer.parseInt(nameFromData.split(" ")[3]);
                }
            }
            result.append(String.format("%s - %d", names[i], salary));
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
    private boolean validate(String dateFrom, String dateTo, String dateFromData) {
        return LocalDate.parse(dateFromData, formatter)
                .isAfter(LocalDate.parse(dateFrom, formatter).minusDays(1))
                && LocalDate.parse(dateFromData, formatter)
                .isBefore(LocalDate.parse(dateTo, formatter).plusDays(1));
    }
}