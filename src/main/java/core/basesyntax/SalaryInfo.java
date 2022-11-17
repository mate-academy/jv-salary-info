package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder salaryResult = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int nameSalary = 0;
            for (String dateData : data) {
                String[] dataNameHoursSalary = dateData.split(" ");
                LocalDate day = LocalDate.parse(dataNameHoursSalary[0], DATE_FORMATTER);
                if (dataNameHoursSalary[1].equals(name)
                        && ((day.isAfter(from) || day.isEqual(from))
                        && ((day.isBefore(to)) || day.isEqual(to)))) {
                    nameSalary += Integer.parseInt(dataNameHoursSalary[2])
                            * Integer.parseInt(dataNameHoursSalary[3]);
                }
            }
            salaryResult.append("\n").append(name).append(" - ").append(nameSalary);
        }
        return salaryResult.toString();
    }
}
