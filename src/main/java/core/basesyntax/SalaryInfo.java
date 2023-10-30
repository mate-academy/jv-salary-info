package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder outputData = new StringBuilder("Report for period ");
        String visualSeparator = " - ";
        outputData.append(dateFrom).append(visualSeparator).append(dateTo).append(System.lineSeparator());

        for (String currentName : names) {
            int totalIncome = 0;
            for (String dataArrayElement : data) {
                String[] rowOfData = dataArrayElement.split(" ");
                String name = rowOfData[1]; // Get name from row of data

                if (currentName.equals(name)) {
                    String dateFromRowOfDate = rowOfData[0];
                    LocalDate localDateFromRowOfDate = dateFromString(dateFromRowOfDate);
                    LocalDate localDateFrom = dateFromString(dateFrom);
                    LocalDate localDateTo = dateFromString(dateTo);

                    if (localDateFromRowOfDate.isAfter(localDateFrom)
                            && localDateFromRowOfDate.isBefore(localDateTo)) {
                        int timesWasPaid = Integer.parseInt(rowOfData[2]);
                        int paidSum = Integer.parseInt(rowOfData[3]);
                        totalIncome += timesWasPaid * paidSum;
                    }
                }
            }
            outputData.append(currentName).append(visualSeparator).append(totalIncome).append(System.lineSeparator());
        }
        return outputData.toString();
    }

    public LocalDate dateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date = date.strip();
        try {
            // Parse the date string into a LocalDate using the specified format
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
