package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            String nameCheked = name.replaceAll("[^A-Za-z]", "");
            report.append(System.lineSeparator()).append(name.replaceAll("[^A-Za-z]", ""));
            int amount = 0;
            for (String dayData: data) {
                String[] dayDataArray = dayData.split(" ");
                String dateDay = dayDataArray[0];
                String nameDay = dayDataArray[1];
                int hoursDay = Integer.parseInt(dayDataArray[2]);
                int salaryDay = Integer.parseInt(dayDataArray[3]);

                if (checkDate(dateDay, dateTo, dateFrom) && nameCheked.equals(nameDay)) {
                    amount += hoursDay * salaryDay;
                }
            }
            report.append(" - ").append(amount);
        }
        return report.toString();
    }

    public LocalDate toDateFormat(String date) {
        return LocalDate.parse(date, formatter);
    }

    public boolean checkDate(String dateDay, String dateTo, String dateFrom) {
        LocalDate date = toDateFormat(dateDay);
        LocalDate dateToLoc = toDateFormat(dateTo);
        LocalDate dateFromLoc = toDateFormat(dateFrom);

        return (date.isAfter(dateFromLoc) && date.isBefore(dateToLoc)) || date.isEqual(dateFromLoc)
                || date.isEqual(dateToLoc);
    }
}
