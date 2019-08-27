package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    protected static String getSalaryInfo(String[] names,
                                          String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate firstDate = LocalDate.parse(dateFrom, dtf);
        LocalDate secondDate = LocalDate.parse(dateTo, dtf);
        StringBuilder stringBuilder =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        if (firstDate.isAfter(secondDate)) {
            return null;
        }
        for (String nameInfo : names) {
            int salaryCounter = 0;
            for (String string : data) {
                LocalDate day = LocalDate.parse(string.split(" ")[0], dtf);
                if (day.isBefore(secondDate) || day.isEqual(secondDate)
                        && day.isAfter(firstDate) || day.isEqual(firstDate)) {
                    String name = string.split(" ")[1];
                    int salary = Integer.parseInt(string.split(" ")[2])
                            * Integer.parseInt(string.split(" ")[3]);
                    if (nameInfo.equals(name)) {
                        salaryCounter += salary;
                    }
                }
            }
            stringBuilder.append(nameInfo + " - " + salaryCounter + '\n');
        }
        return new String(stringBuilder);
    }
}
