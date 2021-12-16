package core.basesyntax.academy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : data) {
            stringBuilder.append(str).append(" ");
        }
        String strResult = stringBuilder.toString();
        String[] matrixFromStrResult = strResult.split(" ");
        StringBuilder stringBuilderForPeriod = new StringBuilder();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate stop = LocalDate.parse(dateTo, formatter);
        for (int k = 0; k < matrixFromStrResult.length; k += 4) {
            try {
                LocalDate today = LocalDate.parse(matrixFromStrResult[k], formatter);
                boolean intervalContainsToday = ((today.isAfter(start) || start.equals(today))
                        && (today.isBefore(stop) || stop.equals(today)));
                if (intervalContainsToday) {
                    String str1 = matrixFromStrResult[k];
                    String str2 = matrixFromStrResult[k + 1];
                    String str3 = matrixFromStrResult[k + 2];
                    String str4 = matrixFromStrResult[k + 3];
                    stringBuilderForPeriod.append(str1).append(" ").append(str2).append(" ")
                            .append(str3).append(" ").append(str4).append(" ");
                }
            } catch (DateTimeParseException exc) {
                throw new RuntimeException("Wrong  format data", exc);
            }
        }
        String strForPeriod = stringBuilderForPeriod.toString();
        String[] matrixStrForPeriod = strForPeriod.split(" ");
        StringBuilder stringBuilderResult = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 1; i < matrixStrForPeriod.length; i += 4) {
                if (matrixStrForPeriod[i].equals(name)) {
                    salary = salary + (Integer.parseInt(matrixStrForPeriod[i + 1])
                                * Integer.parseInt(matrixStrForPeriod[i + 2]));
                }
            }
            stringBuilderResult.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        System.out.println(stringBuilderResult);
        return stringBuilderResult.toString();
    }
}
