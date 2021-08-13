package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateTo;
        LocalDate localDateFrom;
        LocalDate currentDate;
        try {
            localDateFrom = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
            return "Start date is incorrect!";
        }
        try {
            localDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            return "Final date is incorrect!";
        }
        StringBuilder resultBuilder = new StringBuilder("Report for period ");
        resultBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultBuilder.append(System.lineSeparator()).append(names[i]).append(" - ");
            int currentSum = 0;
            for (int j = 0; j < data.length; j++) {
                int searchInDataResult = data[j].indexOf(names[i]);
                if (searchInDataResult >= 0) {
                    try {
                        currentDate = LocalDate.parse(data[j]
                                .substring(0, searchInDataResult - 1), formatter);
                    } catch (DateTimeParseException e) {
                        return "Date is incorrect! " + data[j];
                    }
                    if (! localDateFrom.isAfter(currentDate)
                            && ! localDateTo.isBefore(currentDate)) {
                        searchInDataResult = searchInDataResult + names[i].length() + 1;
                        currentSum += Integer.parseInt(data[j].substring(searchInDataResult,
                                data[j].indexOf(' ', searchInDataResult)))
                                * Integer.parseInt(data[j].substring(data[j].indexOf(' ',
                                searchInDataResult) + 1));
                    }
                }
            }
            resultBuilder.append(currentSum);
        }
        return resultBuilder.toString();
    }
}
