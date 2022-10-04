package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DAY = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder tempSB = new StringBuilder();
        LocalDate currentDate;
        String[] userData;
        int income = 0;
        tempSB.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String info : data) {
                userData = info.split(" ");
                currentDate = LocalDate.parse(userData[DAY], FORMATTER);
                if (userData[NAME].equals(name) && dateFrom != null && dateTo != null
                        && (!currentDate.isBefore(LocalDate.parse(dateFrom, FORMATTER))
                        && !currentDate.isAfter(LocalDate.parse(dateTo, FORMATTER)))) {
                    income += Integer.parseInt(userData[WORK_HOURS])
                            * Integer.parseInt(userData[SALARY]);
                }
            }
            tempSB.append(System.lineSeparator()).append(name).append(" - ").append(income);
            income = 0;
        }
        return tempSB.toString();
    }
}
