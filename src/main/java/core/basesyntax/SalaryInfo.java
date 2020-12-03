package core.basesyntax;

import static core.basesyntax.DateUtils.isInRange;
import static core.basesyntax.DateUtils.parseDate;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            result.append("\n").append(calculateSalary(name, data, dateFrom, dateTo));
        }
        return result.toString();
    }

    private String calculateSalary(String name, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseDate(dateFrom);
        LocalDate to = parseDate(dateTo);
        int salary = 0;

        for (String row : data) {
            if (row.contains(name)) {
                DataRecord record = new DataRecord(row);
                if (isInRange(record.getDate(), from, to)) {
                    salary += record.getHours() * record.getRate();
                }
            }
        }
        return name + " - " + salary;
    }
}
