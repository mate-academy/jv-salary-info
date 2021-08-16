package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int periodSalary = 0;
            for (String dat : data) {
                String[] dataPool = dat.split(" ");
                if (dataPool[1].equals(name)) {
                    if (LocalDate.parse(dataPool[0], formatter).isEqual(from)
                            || LocalDate.parse(dataPool[0], formatter).isEqual(to)
                            || LocalDate.parse(dataPool[0], formatter).isAfter(from)
                            && LocalDate.parse(dataPool[0], formatter).isBefore(to)) {
                        periodSalary += (Integer.parseInt(dataPool[2])
                                * Integer.parseInt(dataPool[3]));
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(periodSalary);
        }
        return result.toString();
    }
}
