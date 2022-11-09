package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(
            String[] names, String[] data,
            String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder("Report for period ");

        stringBuilder.append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateStringFrom;
        LocalDate dateStringTo;

        try {
            dateStringFrom = LocalDate.parse(dateFrom, formatter);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dateFrom);
            throw exc;      // Rethrow the exception.
        }

        try {
            dateStringTo = LocalDate.parse(dateFrom, formatter);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dateTo);
            throw exc;      // Rethrow the exception.
        }

        for (String each: data) {
            String[] dataResult = each.split(" ");
            LocalDate dateFromData;
            try {
                dateFromData = LocalDate.parse(dataResult[0], formatter);
            } catch (DateTimeParseException exc) {
                System.out.printf("%s is not parsable!%n", dataResult[0]);
                throw exc;      // Rethrow the exception.
            }

            if (dateFromData.isAfter(dateStringFrom) && dateFromData.isBefore(dateStringTo)) {
                String tempName = dataResult[1];
                stringBuilder.append(tempName).append(" - ");
                int resultSalary = 0;
                for (String each1 : data){
                    String[] dataEach = each.split(" ");
                    if (dataEach[1].equals(tempName)){
                        int amount = Integer.parseInt(dataEach[2]) * Integer.parseInt(dataEach[3]);
                        resultSalary += amount;
                    }
                }
                stringBuilder.append(resultSalary).append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
