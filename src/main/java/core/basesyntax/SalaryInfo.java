package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final StringBuilder tempSB = new StringBuilder();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private LocalDate currentDate;
    private String[] userData;
    private int income;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        tempSB.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String info : data) {
                userData = info.split(" ");
                currentDate = LocalDate.parse(userData[0], formatter);
                if (userData[1].equals(name) && dateFrom != null && dateTo != null
                        && (!currentDate.isBefore(LocalDate.parse(dateFrom, formatter))
                        && !currentDate.isAfter(LocalDate.parse(dateTo, formatter)))) {
                    income += Integer.parseInt(userData[2]) * Integer.parseInt(userData[3]);
                }
            }
            tempSB.append(System.lineSeparator()).append(name).append(" - ").append(income);
            income = 0;
        }
        return tempSB.toString();
    }
}
