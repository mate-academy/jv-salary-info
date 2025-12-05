package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_RATE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        for (String datum : data) {
            String[] parts = (datum.split(" "));
            LocalDate curr = LocalDate.parse(parts[INDEX_DATE], FORMATTER);
            if (!curr.isBefore(startDate) && !curr.isAfter(endDate)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(parts[INDEX_NAME])) {
                        salaries[j] += Integer.parseInt(parts[INDEX_HOURS])
                                * Integer.parseInt(parts[INDEX_RATE]);
                        break;
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            res.append(LINE_SEPARATOR)
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        SalaryInfo si = new SalaryInfo();
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {"26.04.2019 John 4 50", "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100", "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50", "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100", "26.04.2019 Kate 9 100"};
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        System.out.println(si.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
