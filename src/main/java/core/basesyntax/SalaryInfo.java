package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORM = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), DATE_FORM);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), DATE_FORM);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalEarned = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORM);

                if (name.equals(parts[1]) && isWithinDateRange(entryDate, fromDate, toDate)) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    totalEarned += hours * rate;
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(totalEarned)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }

    private boolean isWithinDateRange(LocalDate entryDate, LocalDate fromDate, LocalDate toDate) {
        return !entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate);
    }

    public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };

        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        String report = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(report);
    }
}
