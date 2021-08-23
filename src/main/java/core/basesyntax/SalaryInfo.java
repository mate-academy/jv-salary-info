package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String row : data) {
                String[] rowArray = row.split(" ");
                LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
                LocalDate dateInArray = LocalDate.parse(rowArray[DATE], FORMATTER);
                String nameFromData = rowArray[NAME];
                int hours = Integer.parseInt(rowArray[HOURS]);
                int salaryPerHour = Integer.parseInt(rowArray[SALARY]);
                if (nameFromData.equals(name)
                        && (dateInArray.isAfter(startDate) || dateInArray.isEqual(startDate))
                        && (dateInArray.isBefore(endDate) || dateInArray.isEqual(endDate))) {
                    salary += hours * salaryPerHour;
                }
            }
            salaryInfo.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }
}
