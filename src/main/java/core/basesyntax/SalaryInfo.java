package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate workDayDate;
        LocalDate dateFrom1 = getDateFromString(dateFrom);
        LocalDate dateTo1 = getDateFromString(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String s : data) {
                String[] splitDataUnit = s.split(" ");
                if (name.equals(splitDataUnit[1])) {
                    workDayDate = getDateFromString(splitDataUnit[0]);
                    if (workDayDate.compareTo(dateFrom1) >= 0
                            && workDayDate.compareTo(dateTo1) <= 0) {
                        salary += (Integer.parseInt(splitDataUnit[2])
                                * Integer.parseInt(splitDataUnit[3]));
                    }
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private static LocalDate getDateFromString(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.from(formatter.parse(value));
    }
}
