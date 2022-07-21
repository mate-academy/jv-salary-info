package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"
        };
        System.out.println(getSalaryInfo(names,data,"24.04.2019","24.04.2019"));
    }

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
