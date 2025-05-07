package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int TIMES_PAID_INDEX = 2;
    private static final int SUM_PAID_INDEX = 3;
    private static final String VISUAL_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Creating String builder with starting content
        StringBuilder outputData = new StringBuilder("Report for period ");
        outputData.append(dateFrom)
                .append(VISUAL_SEPARATOR)
                .append(dateTo);

        // Get data from string parameters
        LocalDate localDateFrom = dateFromString(dateFrom);
        LocalDate localDateTo = dateFromString(dateTo);

        for (String currentName : names) {
            int totalIncome = 0;
            for (String dataArrayElement : data) {
                String[] rowOfData = dataArrayElement.split(" ");
                String name = rowOfData[NAME_INDEX]; // Get name from row of data

                if (currentName.equals(name)) {
                    // Getting data from current user's row of data
                    String dateFromRowOfData = rowOfData[DATE_INDEX];
                    LocalDate localDateFromRowOfDate = dateFromString(dateFromRowOfData);

                    // Checking if current data is inside set data period
                    if ((localDateFromRowOfDate.isEqual(localDateFrom)
                            || localDateFromRowOfDate.isAfter(localDateFrom))
                            && (localDateFromRowOfDate.isEqual(localDateTo)
                            || localDateFromRowOfDate.isBefore(localDateTo))) {
                        // If condition of data satisfied, add his income to stat
                        int timesWasPaid = Integer.parseInt(rowOfData[TIMES_PAID_INDEX]);
                        int paidSum = Integer.parseInt(rowOfData[SUM_PAID_INDEX]);
                        totalIncome += timesWasPaid * paidSum;
                    }
                }
            }
            outputData.append(System.lineSeparator())
                    .append(currentName)
                    .append(VISUAL_SEPARATOR)
                    .append(totalIncome);
        }
        return outputData.toString();
    }

    public LocalDate dateFromString(String date) {
        date = date.strip();
        try {
            // Parse the date string into a LocalDate using the specified format
            return LocalDate.parse(date, FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing date from string");
        }
    }

}
