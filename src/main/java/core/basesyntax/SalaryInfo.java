package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final String resultSeparator = " - ";
        final int indDate = 0;
        final int indName = 1;
        final int indHours = 2;
        final int indMoney = 3;
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(resultSeparator).append(dateTo);
        LocalDate startWork = LocalDate.parse(dateFrom, formatter);
        LocalDate endWork = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(resultSeparator);
            int sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataSepar = data[j].split(" ");
                LocalDate workingDay = LocalDate.parse(dataSepar[indDate], formatter);
                boolean isPresent = workingDay.isAfter(startWork) && workingDay.isBefore(endWork)
                        || workingDay.isEqual(startWork) || workingDay.isEqual(endWork);
                boolean isNameMatch = names[i].equals(dataSepar[indName]);
                if (isPresent && isNameMatch) {
                    sum += Integer.valueOf(dataSepar[indHours])
                            * Integer.valueOf(dataSepar[indMoney]);
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
