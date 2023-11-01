package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    // 26.04.2019 John 4 50
    private static final int dateSubstring = 0;
    private static final int nameSubstring = 1;
    private static final int timesPaidSubstring = 2;
    private static final int sumPaidSubstring = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Creating String builder with starting content
        StringBuilder outputData = new StringBuilder("Report for period ");
        String visualSeparator = " - ";
        outputData.append(dateFrom)
                .append(visualSeparator)
                .append(dateTo);

        for (String currentName : names) {
            int totalIncome = 0;
            for (String dataArrayElement : data) {
                String[] rowOfData = dataArrayElement.split(" ");
                String name = rowOfData[nameSubstring]; // Get name from row of data

                if (currentName.equals(name)) {
                    String dateFromRowOfData = rowOfData[dateSubstring];
                    LocalDate localDateFromRowOfDate = dateFromString(dateFromRowOfData);
                    LocalDate localDateFrom = dateFromString(dateFrom);
                    LocalDate localDateTo = dateFromString(dateTo);

                    if ((localDateFromRowOfDate.isEqual(localDateFrom)
                            || localDateFromRowOfDate.isAfter(localDateFrom))
                            && (localDateFromRowOfDate.isEqual(localDateTo)
                            || localDateFromRowOfDate.isBefore(localDateTo))) {
                        int timesWasPaid = Integer.parseInt(rowOfData[timesPaidSubstring]);
                        int paidSum = Integer.parseInt(rowOfData[sumPaidSubstring]);
                        totalIncome += timesWasPaid * paidSum;
                    }
                }
            }
            outputData.append(System.lineSeparator())
                    .append(currentName)
                    .append(visualSeparator)
                    .append(totalIncome);
        }
        return outputData.toString();
    }

    public LocalDate dateFromString(String date) {
        date = date.strip();
        try {
            // Parse the date string into a LocalDate using the specified format
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing date from string");
        }
    }

}
