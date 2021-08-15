package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            String nameCheked = name.replaceAll("[^A-Za-z]", "");
            report.append(System.lineSeparator()).append(nameCheked);
            int amount = 0;
            for (String dayData: data) {
                int spaceSecond = (dayData.substring(11)).indexOf(' ') + 11;
                int spacelast = dayData.lastIndexOf(' ');

                if (checkDate(dayData, dateTo, dateFrom) && nameCheked.equals(dayData
                        .substring(11, spaceSecond))) {
                    amount += Integer.parseInt(dayData.substring(spaceSecond + 1, spacelast)
                            .replaceAll("[^0-9]", ""))
                            * Integer.parseInt(dayData.substring(spacelast + 1)
                            .replaceAll("[^0-9]",""));
                }
            }
            report.append(" - ").append(amount);
        }
        return report.toString();
    }

    public LocalDate toDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean checkDate(String dayData, String dateTo, String dateFrom) {
        LocalDate date = toDateFormat(dayData.substring(0, 10));
        LocalDate dateToLoc = toDateFormat(dateTo);
        LocalDate dateFromLoc = toDateFormat(dateFrom);

        return (date.isAfter(dateFromLoc) && date.isBefore(dateToLoc)) || date.isEqual(dateFromLoc)
                || date.isEqual(dateToLoc);

    }
}
