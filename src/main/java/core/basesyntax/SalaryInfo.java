package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DDMMYYYY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DDMMYYYY);
        LocalDate to = LocalDate.parse(dateTo, DDMMYYYY);
        String result = "Report for period " + from.format(DDMMYYYY) + " - " + to.format(DDMMYYYY);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] cols = data[j].split("\\s+");
                LocalDate date = LocalDate.parse(cols[0], DDMMYYYY);
                String name = cols[1];
                int hours = Integer.parseInt(cols[2]);
                int salaryByHour = Integer.parseInt(cols[3]);
                if (names[i].equals(name) && between(date, from, to)) {
                    salary += salaryByHour * hours;
                }
            }
            result += "\n" + names[i] + " - " + String.valueOf(salary);
        }

        return result;
    }

    public static boolean between(LocalDate d, LocalDate from, LocalDate to) {
        return d.isAfter(from) && d.isBefore(to) || d.isEqual(from) || d.isEqual(to);
    }
}
