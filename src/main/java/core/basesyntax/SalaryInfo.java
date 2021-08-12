package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int currentSum;
        int searchInDataResult;
        LocalDate currentDate;
        try {
            this.dateFrom = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
            return "Start date is incorrect!";
        }
        try {
            this.dateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            return "Final date is incorrect!";
        }
        StringBuilder resultBuilder = new StringBuilder("Report for period ");
        resultBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            resultBuilder.append(System.lineSeparator()).append(names[i]).append(" - ");
            currentSum = 0;
            for (int j = 0; j < data.length; j++) {
                searchInDataResult = data[j].indexOf(names[i]); // points to the Name position
                if (searchInDataResult >= 0) { // name found in the data string
                    try {
                        currentDate = LocalDate.parse(data[j]
                                .substring(0, searchInDataResult - 1), formatter);
                    } catch (DateTimeParseException e) {
                        return "Date is incorrect! " + data[j];
                    }
                    if (! this.dateFrom.isAfter(currentDate)
                            && ! this.dateTo.isBefore(currentDate)) {
                        // date is in range
                        searchInDataResult = searchInDataResult + names[i].length() + 1;
                        // searchInDataResult now points to the number of hours in the data string
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
