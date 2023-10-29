package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder outputData = new StringBuilder("Report for period ");

        for (int i = 0; i < data.length; i++) {
            String visualSeparator = " - ";
            outputData.append(dateFrom).append(visualSeparator).append(dateTo);

            String[] rowOfData = data[i].split(" ");
            String name = rowOfData[1]; // Get name from row of data
            int totalIncome = 0;

            for (int j = 0; j < names.length; j++) {
                if (names[i].equals(name)) {
                    String dateFromRowOfDate = rowOfData[0];
                    LocalDate localDateFromRowOfDate = dateFromString(dateFromRowOfDate);
                    LocalDate localDateFrom = dateFromString(dateFrom);
                    LocalDate localDateTo = dateFromString(dateFrom);

                    if (localDateFromRowOfDate.isAfter(localDateFrom)
                            && localDateFromRowOfDate.isBefore(localDateTo)) {
                        int timesWasPaid = Integer.parseInt(rowOfData[3]);
                        int paidSum = Integer.parseInt(rowOfData[4]);
                        totalIncome+=timesWasPaid*paidSum;
                    }
                    outputData.append(name).append(visualSeparator).append(totalIncome);
                }
            }
        }
        return outputData.toString();
    }

    public LocalDate dateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            // Parse the date string into a LocalDate using the specified format
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
