package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int FIRST_INDEX = 0;
    static final int SECOND_INDEX = 1;
    static final int THIRD_INDEX = 2;
    static final int FOURTH_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);
        int[] money = new int[names.length];
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String newData : data) {
            String[] salaryInfo = newData.split(" ");
            LocalDate workDate = LocalDate.parse(salaryInfo[FIRST_INDEX], FORMATTER);
            if (!(workDate.isBefore(firstDate)) && !(workDate.isAfter(lastDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(salaryInfo[SECOND_INDEX])) {
                        money[i] = money[i] + Integer.parseInt(salaryInfo[THIRD_INDEX])
                                * Integer.parseInt(salaryInfo[FOURTH_INDEX]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(money[i]);
        }
        return builder.toString();
    }
}
