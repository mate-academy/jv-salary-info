package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] totalSalary = new int[names.length];

        for (String line : data) {
            String[] parts = line.split(" ");
            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
            if (date.isAfter(toDate) || date.isBefore(fromDate)) {
                continue;
            }

            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    totalSalary[i] += hours * rate;
                    break;
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(totalSalary[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }

    public static void main(String[]args) {
        SalaryInfo salaryInfo = new SalaryInfo();

        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100",

                "13.07.2019 John 60 50",
                "15.07.2019 Andrew 3 200",
                "15.07.2019 Kate 10 100",

                "16.07.2019 Andrew 3 200",
                "16.07.2019 Kate 9 100",

                "10.08.2019 John 7 100",
                "08.08.2019 Kate 3 80",
                "11.08.2019 Andrew 8 100"
        };

        String dateFrom = "25.04.2019";
        String dateTo = "30.08.2019";

        String report = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(report);
    }
}
