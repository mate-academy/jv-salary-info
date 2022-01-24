package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return calculate(names, data, dateFrom, dateTo);
    }

    public String calculate(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder calculation = new StringBuilder();
        calculation.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int finalSalary = 0;
            calculation.append(System.lineSeparator()).append(name).append(" - ");
            for (int i = 0; i < data.length; i++) {
                String[] period = data[i].split(" ");
                LocalDate today = LocalDate.parse(period[0], FORMATTER);
                if (name.equals(period[1]) && today.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && today.isBefore(LocalDate.parse(dateTo,FORMATTER).plusDays(1))) {
                    finalSalary = finalSalary
                            + (Integer.parseInt(period[2]) * Integer.parseInt(period[3]));
                }
            }
            calculation.append(finalSalary);
        }
        return calculation.toString();
    }
}


