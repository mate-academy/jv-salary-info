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

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ");
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                dateTmp = data[j].substring(0,10);
                if (data[j].contains(names[i])
                        && compareDate(dateTmp, dateFrom, dateTo)) {
                    salary += calculateSalaryFromData(data[j]);
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }

    private int getDayFromDate(String date) {
        return Integer.parseInt(date.substring(0,2));
    }

    private int getMonthFromDate(String date) {
        return Integer.parseInt(date.substring(3,5));
    }

    private int getYearFromDate(String date) {
        return Integer.parseInt(date.substring(6,10));
    }

    private boolean compareDate(String date, String startDate, String endDate) {
        LocalDate dateToCheck = LocalDate.parse(date, formatter);
        LocalDate startDateObj = LocalDate.parse(startDate, formatter);
        LocalDate endDateObj = LocalDate.parse(endDate, formatter);
        return (dateToCheck.isEqual(startDateObj) || dateToCheck.isAfter(startDateObj))
                && (dateToCheck.isEqual(endDateObj) || dateToCheck.isBefore(endDateObj));
    }

    public int calculateSalaryFromData(String data) {
        String[] parts = data.split("\\s+");
        String countOfSalary = null;
        String oneSalary = null;
        int result = 0;
        if (parts.length >= 2) {
            countOfSalary = parts[parts.length - 2];
            oneSalary = parts[parts.length - 1];
        }
        result = Integer.parseInt(countOfSalary) * Integer.parseInt(oneSalary);
        return result;
    }
}
