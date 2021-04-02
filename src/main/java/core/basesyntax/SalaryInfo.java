package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int sumUserSalary = 0;

            for (String dataLine : data) {
                if (dataLine.contains(name)) {
                    String[] userData = dataLine.split(" ");
                    if (isDateInRange(userData[0], dateFrom, dateTo)) {
                        sumUserSalary += (Integer.valueOf(userData[2])
                                * Integer.valueOf(userData[3]));
                    }
                }
            }
            builder.append("\n" + name + " - " + sumUserSalary);
        }
        return builder.toString();
    }

    private boolean isDateInRange(String testDate, String firstDate, String lastDate) {
        LocalDate testLocalDate = LocalDate.parse(testDate, formatter);
        LocalDate firstLocalDate = LocalDate.parse(firstDate, formatter);
        LocalDate lastLocalDate = LocalDate.parse(lastDate, formatter);

        if ((testLocalDate.isAfter(firstLocalDate) && testLocalDate.isBefore(lastLocalDate))
                || testLocalDate.isEqual(firstLocalDate)
                || testLocalDate.isEqual(lastLocalDate)) {
            return true;
        }
        return false;
    }
}
