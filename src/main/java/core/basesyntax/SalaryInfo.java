package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        String dateTmp = "";

        for (String employeeName : names) {
            salaryInfo.append(System.lineSeparator())
                    .append(employeeName)
                    .append(" - ");

            int totalSalary = calculateTotalSalary(employeeName, data, dateFrom, dateTo);
            salaryInfo.append(totalSalary);
        }
        return salaryInfo.toString();
    }

    private int calculateTotalSalary(String employeeName,
                                     String[] salaryData,
                                     String dateFrom,
                                     String dateTo) {
        int totalSalary = 0;

        for (String data : salaryData) {
            String date = data.substring(0, 10);

            if (data.contains(employeeName) && isDateInRange(date, dateFrom, dateTo)) {
                totalSalary += calculateSalaryFromData(data);
            }
        }
        return totalSalary;
    }

    private boolean isDateInRange(String date, String startDate, String endDate) {
        LocalDate dateToCheck = LocalDate.parse(date, formatter);
        LocalDate startDateObj = LocalDate.parse(startDate, formatter);
        LocalDate endDateObj = LocalDate.parse(endDate, formatter);

        return (dateToCheck.isEqual(startDateObj) || dateToCheck.isAfter(startDateObj))
                && (dateToCheck.isEqual(endDateObj) || dateToCheck.isBefore(endDateObj));
    }

    private int calculateSalaryFromData(String data) {
        String[] parts = data.split("\\s+");
        int result = 0;

        if (parts.length >= 2) {
            int countOfSalary = Integer.parseInt(parts[parts.length - 2]);
            int oneSalary = Integer.parseInt(parts[parts.length - 1]);
            result = countOfSalary * oneSalary;
        }
        return result;
    }
}
