package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        String finalResult = "Report for period "
                + dateFrom + " - "
                + dateTo
                + System.lineSeparator();;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])
                        && (LocalDate.parse(data[j].split(" ")[0], formatter).isBefore(endDate)
                        || LocalDate.parse(data[j].split(" ")[0], formatter).equals(endDate))
                        && (LocalDate.parse(data[j].split(" ")[0], formatter).isAfter(startDate)
                        || LocalDate.parse(data[j].split(" ")[0], formatter).equals(startDate))) {
                    int incomePH = Integer.parseInt(
                            data[j].substring(data[j].lastIndexOf(" ") + 1));
                    String hoursSplit = data[j].substring(0,data[j].lastIndexOf(" "));
                    int hours = Integer.parseInt(
                            hoursSplit.substring(hoursSplit.lastIndexOf(" ") + 1));
                    salary[i] = salary[i] + incomePH * hours;
                }
            }
            if (i < names.length - 1) {
                finalResult = finalResult + names[i] + " - " + salary[i] + System.lineSeparator();
            } else {
                finalResult = finalResult + names[i] + " - " + salary[i];
            }
        }
        return finalResult;
    }
}
