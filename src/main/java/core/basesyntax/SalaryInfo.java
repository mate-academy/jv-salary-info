package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int resultOfOnePerson = 0;
        String[] dates;
        for (String name : names) {
            for (String infoPerDay : data) {
                dates = infoPerDay.split(" ");
                if (name.equals(dates[1]) && isDateTrue(dates[0], dateFrom, dateTo)) {
                    resultOfOnePerson += (Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]));
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(resultOfOnePerson);
            resultOfOnePerson = 0;
        }
        return result.toString();
    }

    private LocalDate dateFromString(String stringDate) {
        return LocalDate.parse(stringDate, format);
    }

    private boolean isDateTrue(String dateNow, String dateFrom, String dateTo) {
        return dateFromString(dateNow).isAfter(dateFromString(dateFrom).minusDays(1))
                && dateFromString(dateNow).isBefore(dateFromString(dateTo).plusDays(1));
    }
}
