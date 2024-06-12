package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int NAME_OF_PERSON = 1;
    private static final int DATA = 0;
    private static final int WORKING_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(SEPARATOR).append(dateTo);
        for (String name : names) {
            int earnedMoneyByPerson = getEarnedMoney(data, name, dateFrom, dateTo);
            result.append(System.lineSeparator()).append(name).append(SEPARATOR).append(earnedMoneyByPerson);
        }
        System.out.println(result);
        return result.toString();
    }

    private int getEarnedMoney(String[] data, String nameOfPerson, String dateFrom, String dateTo) {
        int sum = 0;
        for (String elementOfData : data) {
            String[] detailsOfElementOfData = elementOfData.split(" ");
            if (detailsOfElementOfData[NAME_OF_PERSON].equals(nameOfPerson)) {
                LocalDate particularData = null;
                LocalDate fromDate = null;
                LocalDate toDate = null;
                try {
                    particularData = LocalDate.parse(detailsOfElementOfData[DATA], FORMATTER);
                    fromDate = LocalDate.parse(dateFrom, FORMATTER);
                    toDate = LocalDate.parse(dateTo, FORMATTER);
                    fromDate = fromDate.minusDays(1);
                    toDate = toDate.plusDays(1);
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("There was an error parsing data");
                }
                if (particularData.isAfter(fromDate) && particularData.isBefore(toDate)) {
                    sum += Integer.parseInt(detailsOfElementOfData[WORKING_HOUR])
                            * Integer.parseInt(detailsOfElementOfData[INCOME_PER_HOUR]);
                }
            }
        }
        return sum;
    }
}
