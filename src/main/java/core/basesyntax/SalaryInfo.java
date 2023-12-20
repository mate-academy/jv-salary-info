package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        if (names != null && data != null && dateFrom != null && dateTo != null) {
            try {
                LocalDate startDate = parseDate(dateFrom);
                LocalDate endDate = parseDate(dateTo);

                for (String name : names) {
                    int employeeSalary = calculateEmployeeSalary(data, name, startDate, endDate);
                    result.append(System.lineSeparator()).append(name)
                            .append(" - ").append(employeeSalary);
                }
            } catch (NumberFormatException e) {
                result.append("\nError parsing number.");
            }
        }

        return result.toString();
    }

    private LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing date.");
        }
    }

    private int calculateEmployeeSalary(String[] data, String name,
                                        LocalDate startDate, LocalDate endDate) {
        int employeeSalary = 0;

        for (String day : data) {
            String[] dataDay = day.split(" ");
            try {
                LocalDate itemDate = parseDate(dataDay[0]);

                if (name.equals(dataDay[1]) && (itemDate.isEqual(startDate)
                        || (itemDate.isAfter(startDate) && itemDate.isBefore(endDate))
                        || itemDate.isEqual(endDate))) {
                    employeeSalary += Integer.parseInt(dataDay[2]) * Integer.parseInt(dataDay[3]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing number: ");
            }
        }

        return employeeSalary;
    }
}
