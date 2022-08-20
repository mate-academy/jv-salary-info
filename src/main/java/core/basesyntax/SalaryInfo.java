package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salarySum = 0;
            for (String datas : data) {
                String[] datasSplitted = datas.split(" ");
                LocalDate dateSalary = LocalDate.parse(datasSplitted[DATE_INDEX], DATE_FORMATTER);
                String dataName = datasSplitted[NAME_INDEX];
                int datasHours = Integer.parseInt(datasSplitted[HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(datasSplitted[SALARY_INDEX]);
                if (name.equals(dataName)
                        && isDateBetween(dateSalary, dateFromFormatted, dateToFormatted)) {
                    salarySum += datasHours * salaryPerHour;
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salarySum);
        }
        return salaryInfo.toString();
    }

    private boolean isDateBetween(LocalDate dateSalary, LocalDate dateFrom, LocalDate dateTo) {
        return dateSalary.isAfter(dateFrom) && dateSalary.isBefore(dateTo)
                || (dateSalary.isEqual(dateFrom) || dateSalary.isEqual(dateTo));
    }
}
