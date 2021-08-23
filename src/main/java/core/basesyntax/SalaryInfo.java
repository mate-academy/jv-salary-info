package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int HOURS_FROM_DATA = 2;
    private static final int SALARY_FROM_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        LocalDate dateInArray;
        StringBuilder salaryInfo = new StringBuilder();
        try {
            startDate = LocalDate.parse(dateFrom, FORMATTER);
            endDate = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeException e) {
            throw new RuntimeException("Cannot parse date. Format of date should be"
                    + "dd.mm.yyyy", e);
        }
        salaryInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] rowInData = data[j].split(" ");
                try {
                    dateInArray = LocalDate.parse(rowInData[DATE_FROM_DATA], FORMATTER);
                } catch (DateTimeException e) {
                    throw new RuntimeException("Cannot parse date from data."
                            + "Format of date should be dd.mm.yyyy", e);
                }
                String nameFromData = rowInData[NAME_FROM_DATA];
                int hours = Integer.parseInt(rowInData[HOURS_FROM_DATA]);
                int salaryPerHour = Integer.parseInt(rowInData[SALARY_FROM_DATA]);
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
