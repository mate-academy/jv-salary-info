package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String information : data) {
                String[] parseData = information.split(" ");
                if (checkDate(dateFrom, dateTo, parseData[0]) && parseData[1].equals(name)) {
                    sum += Integer.parseInt(parseData[2]) * Integer.parseInt(parseData[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(sum);
        }
        return result.toString();
    }

    private boolean checkDate(String dateFrom, String dateTo, String date) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, dtf);
        LocalDate finalDate = LocalDate.parse(dateTo, dtf);
        LocalDate localDate = LocalDate.parse(date, dtf);
        return (localDate.isAfter(startDate) && localDate.isBefore(finalDate))
                || localDate.isEqual(finalDate);
    }
}
