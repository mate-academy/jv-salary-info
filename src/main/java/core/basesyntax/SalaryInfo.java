package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalaryInfo {
    public static final int INDEX_OF_DATE = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_OF_DAYS = 2;
    public static final int INDEX_OF_PRICE = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate date;
        String nameOfEmployee;
        int hours;
        int salaryPerHour;
        String[] personInformation;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] finalSalary = new int[names.length];
        for (String line : data) {
            personInformation = line.split(" ");
            date = LocalDate.parse(personInformation[INDEX_OF_DATE], FORMATTER);
            nameOfEmployee = personInformation[INDEX_OF_NAME];
            hours = Integer.parseInt(personInformation[INDEX_OF_DAYS]);
            salaryPerHour = Integer.parseInt(personInformation[INDEX_OF_PRICE]);
            if (List.of(names).contains(nameOfEmployee)
                    && (localDateFrom.isBefore(date) || localDateFrom.isEqual(date))
                    && (localDateTo.isAfter(date) || localDateTo.isEqual(date))) {
                finalSalary[List.of(names).indexOf(nameOfEmployee)] += hours * salaryPerHour;
            }
        }
        return dataToString(names, finalSalary, dateFrom, dateTo);
    }

    private String dataToString(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format("Report for period %s - %s",dateFrom,dateTo));
        for (int i = 0; i < names.length; i++) {
            resultString
                    .append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return resultString.toString();
    }
}
