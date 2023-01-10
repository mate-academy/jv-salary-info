package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dataFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dataFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryResultBuilder = new StringBuilder().append("Report for period ")
                .append(dataFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int nameSalary = 0;
            for (String dateData : data) {
                String[] dataNameHoursSalary = dateData.split(" ");
                LocalDate day = LocalDate.parse(dataNameHoursSalary[DATE_INDEX], DATE_FORMATTER);
                if (dataNameHoursSalary[NAME_INDEX].equals(name)
                        && ((day.isAfter(from) || day.isEqual(from))
                        && ((day.isBefore(to)) || day.isEqual(to)))) {
                    nameSalary += Integer.parseInt(dataNameHoursSalary[HOURS_INDEX])
                            * Integer.parseInt(dataNameHoursSalary[INCOME_PER_HOUR_INDEX]);
                }
            }
            salaryResultBuilder.append(System.lineSeparator()).append(name).append(" - ").append(nameSalary);
        }
        return salaryResultBuilder.toString();
    }
}
