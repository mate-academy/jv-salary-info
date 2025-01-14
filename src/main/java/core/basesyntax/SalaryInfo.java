package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateFormatter);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");

                String employeeName = recordParts[1];
                LocalDate workDate = LocalDate.parse(recordParts[0], dateFormatter);
                int workHours = Integer.parseInt(recordParts[2]);
                int incomePerHour = Integer.parseInt(recordParts[3]);
                if (employeeName.equals(name) && (workDate.equals(startDate)
                        || workDate.equals(endDate) || (workDate.isAfter(startDate)
                        && workDate.isBefore(endDate)))) {
                    totalSalary += workHours * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }

        return result.toString();

    }
}
