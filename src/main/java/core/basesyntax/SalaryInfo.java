package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFromInDateFormat = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToInDateFormat = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate dateFromData;
        String[] dataSplit;
        StringBuilder resultingString =
                new StringBuilder()
                        .append("Report for period ").append(dateFrom)
                        .append(" - ").append(dateTo).append("\n");

        for (String employeeName : names) {
            int sumSalaryPerPerson = 0;
            for (String stringFromData : data) {
                dataSplit = stringFromData.split(" ");
                dateFromData = LocalDate.parse(dataSplit[0], DATE_TIME_FORMATTER);
                if (dataSplit[1].equals(employeeName)
                        && dateFromData.isAfter(dateFromInDateFormat.minusDays(1))
                        && dateFromData.isBefore(dateToInDateFormat.plusDays(1))) {
                    sumSalaryPerPerson += Integer.parseInt(dataSplit[2])
                            * Integer.parseInt(dataSplit[3]);
                }
            }
            resultingString.append(employeeName).append(" - ")
                    .append(sumSalaryPerPerson).append("\n");
        }
        return resultingString.toString().trim();
    }
}
