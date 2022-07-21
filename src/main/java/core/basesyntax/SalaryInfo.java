package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder reportForPeriod = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int totalSumForPeriod = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] splittedDataArray = data[j].split(" ");
                    LocalDate workDay = LocalDate.parse(splittedDataArray[0], formatter);
                    if (workDay.isAfter(from) && workDay.isBefore(to)
                            || workDay.isEqual(from) || workDay.isEqual(to)) {
                        int[] paymentPerHour = new int[]{
                                Integer.parseInt(splittedDataArray[2]),
                                Integer.parseInt(splittedDataArray[3])
                        };
                        totalSumForPeriod = paymentPerHour[0] * paymentPerHour[1]
                                + totalSumForPeriod;
                    }
                }
            }
            reportForPeriod
                    .append(names[i])
                    .append(" - ")
                    .append(totalSumForPeriod)
                    .append(System.lineSeparator());
        }
        return reportForPeriod
                .delete(reportForPeriod.length() - 1, reportForPeriod.length())
                .toString();

    }

}
