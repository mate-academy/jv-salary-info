package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        try {
            LocalDate dateFromFormat = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate dateToFormat = LocalDate.parse(dateTo, FORMATTER);
            int sumSalary = 0;
            for (String name : names) {
                for (int i = 0; i < data.length; i++) {
                    String[] parseData = data[i].split(" ");
                    if (parseData[1].equals(name)
                            && LocalDate.parse(parseData[0], FORMATTER)
                            .compareTo(dateFromFormat) >= 0
                            && LocalDate.parse(parseData[0], FORMATTER)
                            .compareTo(dateToFormat) <= 0) {
                        int salary = Integer.parseInt(parseData[2])
                                * Integer.parseInt(parseData[3]);
                        sumSalary += salary;
                    }
                }
                sb.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
                sumSalary = 0;
            }
        } catch (DateTimeException e) {
            System.out.println("Invalid date format. Input: dd.MM.yyyy");
        }
        return sb.toString();
    }
}
