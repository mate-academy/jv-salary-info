package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int PERIOD_START = 0;
    static final int PERIOD_END = 1;
    static final int PERIODSALLARY = 2;
    static final int PERIODLAST = 3;
    static final int PERIODPLUSDAYS = 1;

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
                LocalDate today = LocalDate.parse(period[PERIODSTART], FORMATTER);
                if (name.equals(period[PERIODEND])
                        && today.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && today.isBefore(LocalDate.parse(dateTo,FORMATTER)
                        .plusDays(PERIODPLUSDAYS))) {
                    finalSalary = finalSalary
                            + (Integer.parseInt(period[PERIODSALLARY])
                            * Integer.parseInt(period[PERIODLAST]));
                }
            }
            calculation.append(finalSalary);
        }
        return calculation.toString();
    }
}


