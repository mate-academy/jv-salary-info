package core.basesyntax;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int userSalary = 0;
            for (String row : data) {
                String[] dataSplit = row.split(" ");
                String foundDate = dataSplit[0];
                String foundName = dataSplit[1];
                String foundHours = dataSplit[2];
                String foundHourSalary = dataSplit[3];
                if (name.equals(foundName) && isDateIncluded(dateFrom, dateTo, foundDate)) {
                    userSalary += Integer.parseInt(foundHours) * Integer.parseInt(foundHourSalary);
                }
            }
            result.append(name).append(" - ").append(userSalary)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public boolean isDateIncluded(String dateFrom, String dateTo, String searchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate beginDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate targetDate = LocalDate.parse(searchDate, formatter);
        return targetDate.isEqual(beginDate) || targetDate.isEqual(endDate)
                || targetDate.isAfter(beginDate) && targetDate.isBefore(endDate);
    }
}
