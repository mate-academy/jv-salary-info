package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate finishDate = LocalDate.parse(dateTo, DATE_FORMAT);

        int[] salaries = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                LocalDate checkDate = LocalDate.parse(data[j].split(" ")[DATE_INDEX], DATE_FORMAT);
                if (!checkDate.isBefore(startDate) && !checkDate.isAfter(finishDate)) {
                    if (data[j].split(" ")[NAME_INDEX].equals(names[i])) {
                        salaries[i] += Integer.parseInt(data[j].split(" ")[WORKING_HOURS_INDEX])
                                * Integer.parseInt(data[j].split(" ")[PAYMENT_INDEX]);
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
                    .append(salaries[i]);
        }

        return result.toString();
    }
}
