package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String paragraph = System.lineSeparator();
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        for (String name: names) {
            int totalSalary = 0;

            for (String employeeData: data) {
                String[] employeeInfo = employeeData.split(" ");
                LocalDate workDay = LocalDate.parse(employeeInfo[0], formatter);

                if (name.equals(employeeInfo[1])
                        && workDay.isAfter(startDate)
                        && (workDay.isEqual(endDate)
                        || workDay.isBefore(endDate))) {
                    totalSalary += Integer.parseInt(employeeInfo[2])
                            * Integer.parseInt(employeeInfo[3]);
                }
            }

            builder.append(paragraph).append(name).append(" - ").append(totalSalary);
        }

        return builder.toString();
    }
}
