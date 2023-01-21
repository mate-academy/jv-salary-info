package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        int salary = 0;
        for (String name : names) {
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            for (String info : data) {
                String[] arrayInfo = info.split(" ");
                LocalDate date = LocalDate.parse(arrayInfo[0], formatter);
                if (name.equals(arrayInfo[1])
                        && date.compareTo(fromDate) >= 0
                        && date.compareTo(toDate) <= 0) {
                    salary += Integer.parseInt(arrayInfo[2])
                            * Integer.parseInt(arrayInfo[3]);
                }
            }
            stringBuilder.append(salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}
