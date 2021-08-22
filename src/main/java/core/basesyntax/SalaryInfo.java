package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitedData;
        LocalDate dateFromData;
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom,formatter);
        LocalDate formattedDateTo = LocalDate.parse(dateTo,formatter);
        int[] namesSalary = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder result = stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String datum : data) {
            splitedData = datum.split(" ");
            dateFromData = LocalDate.parse(splitedData[0], formatter);
            if ((dateFromData.isBefore(formattedDateTo)
                    && dateFromData.isAfter(formattedDateFrom))
                    || dateFromData.isEqual(formattedDateTo)
                    || dateFromData.isEqual(formattedDateFrom)) {
                for (int j = 0; j < names.length; j++) {
                    if (splitedData[1].equals(names[j])) {
                        namesSalary[j] += Integer.parseInt(splitedData[2])
                                * Integer.parseInt(splitedData[3]);
                    }
                }
            }
        }
        for (int i = 0; i < namesSalary.length; i++) {
            result.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(namesSalary[i]);
        }
        return result.toString();
    }
}
