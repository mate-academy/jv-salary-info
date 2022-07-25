package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static int PAYMENT = 3;

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {

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
                    LocalDate workDay = LocalDate.parse(splittedDataArray[DATE], formatter);
                    if (workDay.isAfter(from) && workDay.isBefore(to)
                            || workDay.isEqual(from) || workDay.isEqual(to)) {
                        int[] paymentPerHour = new int[]{
                                Integer.parseInt(splittedDataArray[WORK_HOURS]),
                                Integer.parseInt(splittedDataArray[PAYMENT])
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
