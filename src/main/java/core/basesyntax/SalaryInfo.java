package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION_IN_ARRAY = 0;
    private static final int NAME_POSITION_IN_ARRAY = 1;
    private static final int WORKING_HOURS_POSITION_IN_ARRAY = 2;
    private static final int INCOME_PER_HOUR_POSITION_IN_ARRAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFromInDateFormat = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToInDateFormat = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate dateFromData;
        String[] dataSplit;
        StringBuilder resultingString =
                new StringBuilder()
                        .append("Report for period ").append(dateFrom)
                        .append(" - ").append(dateTo);

        for (String employeeName : names) {
            int sumSalaryPerPerson = 0;
            for (String stringFromData : data) {
                dataSplit = stringFromData.split(" ");
                dateFromData =
                        LocalDate.parse(dataSplit[DATE_POSITION_IN_ARRAY], DATE_TIME_FORMATTER);
                if (dataSplit[NAME_POSITION_IN_ARRAY].equals(employeeName)
                        && dateFromData.isAfter(dateFromInDateFormat.minusDays(1))
                        && dateFromData.isBefore(dateToInDateFormat.plusDays(1))) {
                    sumSalaryPerPerson +=
                            Integer.parseInt(dataSplit[WORKING_HOURS_POSITION_IN_ARRAY])
                            * Integer.parseInt(dataSplit[INCOME_PER_HOUR_POSITION_IN_ARRAY]);
                }
            }
            resultingString.append("\n").append(employeeName).append(" - ")
                    .append(sumSalaryPerPerson);
        }
        return resultingString.toString();
    }
}
