package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORM = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), DATE_FORM);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), DATE_FORM);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(fromDate.format(DATE_FORM))
                .append(" - ")
                .append(toDate.format(DATE_FORM))
                .append("\n");

        Map<String, Integer> totalEarnedMap = new HashMap<>();

        for (String name : names) {
            totalEarnedMap.put(name, 0);
        }

        for (String entry : data) {
            LocalDate entryDate = LocalDate.parse(entry.substring(0, 10), DATE_FORM);
            if (isWithinDateRange(entryDate, fromDate, toDate)) {
                String name = entry.substring(11, entry.indexOf(' ', 11));
                int hours = Integer.parseInt(entry.substring(entry.indexOf(' ', 11) + 1,
                        entry.lastIndexOf(' ')));
                int rate = Integer.parseInt(entry.substring(entry.lastIndexOf(' ') + 1));

                if (totalEarnedMap.containsKey(name)) {
                    int totalEarned = totalEarnedMap.get(name);
                    totalEarned += hours * rate;
                    totalEarnedMap.put(name, totalEarned);
                }
            }
        }

        for (String name : names) {
            report.append(name)
                    .append(" - ")
                    .append(totalEarnedMap.get(name))
                    .append("\n");
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
