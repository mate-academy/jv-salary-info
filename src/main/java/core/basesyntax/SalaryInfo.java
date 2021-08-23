package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int zeroConstant = 0;
    private final int oneConstant = 1;
    private final int twoConstant = 2;
    private final int threeConstant = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromConvert = LocalDate.parse(dateFrom, dataFormat);
        LocalDate dateToConvert = LocalDate.parse(dateTo, dataFormat);

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                LocalDate checkDate = LocalDate.parse(data[j].split(" ")[zeroConstant], dataFormat);
                if (!checkDate.isBefore(dateFromConvert) && !checkDate.isAfter(dateToConvert)) {
                    if (data[j].split(" ")[oneConstant].equals(names[i])) {
                        salary[i] += Integer.parseInt(data[j].split(" ")[twoConstant])
                                * Integer.parseInt(data[j].split(" ")[threeConstant]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }

        return result.toString();
    }
}
