package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int userSalary = 0;
            for (String currentData : data) {
                String[] record = currentData.split(" ");
                String workDate = record[0];
                String employeeName = record[1];
                String workHours = record[2];
                String hourSalary = record[3];

                if (name.equals(employeeName) && isDateIncluded(dateFrom, dateTo, workDate)) {
                    userSalary += Integer.parseInt(workHours) * Integer.parseInt(hourSalary);
                }
            }
            result.append(name).append(" - ").append(userSalary).append(System.lineSeparator());
        }
        return result.toString().trim();

    }

    public Boolean isDateIncluded(String dateFrom, String dateTo, String searchDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate beginDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        LocalDate targetDate = LocalDate.parse(searchDate, formatter);
        return targetDate.isEqual(beginDate) || targetDate.isEqual(endDate) || targetDate.isAfter(beginDate) && targetDate.isBefore(endDate);

    }

}

