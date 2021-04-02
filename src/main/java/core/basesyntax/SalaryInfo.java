package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || names.length == 0 || data == null || data.length == 0
                || dateFrom == null || dateFrom == "" || dateTo == null || dateTo == "") {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);
        int sumUserSalary = 0;

        for (int i = 0; i < names.length; i++) {
            sumUserSalary = 0;

            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] userData = data[j].split(" ");
                    if (isDateInRange(userData[0], dateFrom, dateTo)) {
                        sumUserSalary += (Integer.valueOf(userData[2])
                                * Integer.valueOf(userData[3]));
                    }
                }
            }
            builder.append("\n" + names[i] + " - " + sumUserSalary);
        }
        return builder.toString();
    }

    private boolean isDateInRange(String testDate, String firstDate, String lastDate) {
        try {
            LocalDate testLocalDate = LocalDate.parse(testDate, formatter);
            LocalDate firstLocalDate = LocalDate.parse(firstDate, formatter);
            LocalDate lastLocalDate = LocalDate.parse(lastDate, formatter);
            if ((testLocalDate.isAfter(firstLocalDate) && testLocalDate.isBefore(lastLocalDate))
                    || testLocalDate.isEqual(firstLocalDate)
                    || testLocalDate.isEqual(lastLocalDate)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error! Can't read date value");
        }
        return false;
    }
}
