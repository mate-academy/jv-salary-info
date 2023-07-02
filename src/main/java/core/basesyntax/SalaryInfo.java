package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_HOUR_WAGE = 3;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] separatedData = datum.split(" ");
                if (datum.contains(name) && checkDates(dateFrom, dateTo, separatedData)) {
                    int workedHours = Integer.parseInt(separatedData[INDEX_OF_WORKING_HOURS]);
                    int hourWage = Integer.parseInt(separatedData[INDEX_OF_HOUR_WAGE]);
                    salary += workedHours * hourWage;
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }

    private boolean checkDates(String dateFrom, String dateTo, String[] data) {
        LocalDate startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate workingDate = LocalDate.parse(data[INDEX_OF_DATE], dateTimeFormatter);
        return (startDate.isBefore(workingDate) && endDate.isAfter(workingDate))
                || startDate.equals(workingDate)
                || endDate.equals(workingDate);
    }
}
