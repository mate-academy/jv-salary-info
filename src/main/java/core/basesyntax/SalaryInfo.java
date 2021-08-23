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
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] rowInData = data[j].split(" ");
                LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
                LocalDate dateInArray = LocalDate.parse(rowInData[DATE], FORMATTER);
                String nameFromData = rowInData[NAME];
                int hours = Integer.parseInt(rowInData[HOURS]);
                int salaryPerHour = Integer.parseInt(rowInData[SALARY]);
                if (nameFromData.equals(names[i])
                        && (dateInArray.isAfter(startDate) || dateInArray.equals(startDate))
                        && (dateInArray.isBefore(endDate) || dateInArray.equals(endDate))) {
                    salary += hours * salaryPerHour;
                }
            }
            salaryInfo.append(names[i])
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }
}
