package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    protected static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate dateT = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        StringBuilder stringBuilder =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        if (dateF.isAfter(dateT)) {
            return null;
        }
        for (String s : names) {
            String name;
            int salary;
            int salaryCounter = 0;
            for (String string : data) {
                LocalDate day = LocalDate.parse(string.split(" ")[0], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (day.isBefore(dateT) || day.isEqual(dateT) && day.isAfter(dateF) || day.isEqual(dateF)) {
                    name = string.split(" ")[1];
                    salary = Integer.parseInt(string.split(" ")[2])
                            * Integer.parseInt(string.split(" ")[3]);
                    if (s.equals(name)) {
                        salaryCounter += salary;
                    }
                }
            }
            stringBuilder.append(s + " - " + salaryCounter + '\n');
        }
        return new String(stringBuilder);
    }
}
