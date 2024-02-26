package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String endResult = "";
        StringBuilder result = new StringBuilder();
        String first = "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator();
        result.append(first);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate beginningDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endingDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                String[] dataSplitArray = dataString.split(" ");
                LocalDate date = LocalDate.parse(dataSplitArray[0], formatter);
                if ((date.isAfter(beginningDate) || date.isEqual(beginningDate))
                        && (date.isBefore(endingDate) || date.isEqual(endingDate))
                        && dataSplitArray[1].equals(name)) {
                    int hour = Integer.valueOf(dataSplitArray[2]);
                    int income = Integer.valueOf(dataSplitArray[3]);
                    int sum = hour * income;
                    salary = salary + sum;
                }
            }
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        String resultToString = result.toString();
        int index = resultToString.lastIndexOf(System.lineSeparator());
        endResult = resultToString.substring(0, index);
        return endResult;
    }
}
