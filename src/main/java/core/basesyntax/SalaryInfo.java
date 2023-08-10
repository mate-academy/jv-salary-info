package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String INDEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFrom1 = LocalDate.parse(dateFrom, formatter);
        LocalDate dateTo1 = LocalDate.parse(dateTo, formatter);
        int salary = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            for (String dates : data) {
                String[] splitData = dates.split(INDEX);
                LocalDate dataWork = LocalDate.parse(splitData[0], formatter);
                if (name.equals(splitData[1]) && ((dateFrom1.isBefore(dataWork)
                        && dataWork.isBefore(dateTo1))
                        || dataWork.isEqual(dateFrom1)
                        || dataWork.isEqual(dateTo1))) {
                    salary = salary
                            + Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            sb.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return sb.toString();
    }
}
