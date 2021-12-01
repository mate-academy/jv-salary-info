package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String recordData = data[j].split(" ")[0];
                if (data[j].contains(names[i])
                        && (LocalDate.parse(recordData, formatter).isBefore(endDate)
                        || LocalDate.parse(recordData, formatter).equals(endDate))
                        && (LocalDate.parse(recordData, formatter).isAfter(startDate)
                        || LocalDate.parse(recordData, formatter).equals(startDate))) {
                    int incomePH = Integer.parseInt(
                            data[j].substring(data[j].lastIndexOf(" ") + 1));
                    String hoursSplit = data[j].substring(0,data[j].lastIndexOf(" "));
                    int hours = Integer.parseInt(
                            hoursSplit.substring(hoursSplit.lastIndexOf(" ") + 1));
                    salary[i] = salary[i] + incomePH * hours;
                }
            }
            finalResult = finalResult.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(salary[i]);
        }
        return finalResult.toString();
    }
}
