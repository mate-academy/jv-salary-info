package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder("Report for period ");
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMATTER);
        builder.append(dateFrom).append(" - ").append(dateTo);
        int [] sumOfSalary = new int [names.length];

        for (int i = 0; i < names.length; i++) {
            for (String record : data) {
                String [] splitData = record.split(" ");
                LocalDate workDate = LocalDate.parse(splitData[0], FORMATTER);
                String employeeName = splitData[1];
                int workHours = Integer.parseInt(splitData[2]);
                int income = Integer.parseInt(splitData[3]);
                if (employeeName.equals(names[i]) && !workDate.isBefore(startDate)
                        && !workDate.isAfter(finishDate)) {
                    sumOfSalary[i] += workHours * income;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(sumOfSalary[i]);
        }
        return builder.toString();
    }
}
