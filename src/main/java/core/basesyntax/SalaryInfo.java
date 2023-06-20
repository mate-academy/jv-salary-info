package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);

        int[] salaries = new int[names.length];
        for (String rec : data) {
            String[] parts = rec.split(" ");

            LocalDate date = parseDate(parts[INDEX_OF_DATE]);
            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                continue;
            }

            int index = 0;
            for (int i = 0; i < names.length; i++) {
                if (parts[INDEX_OF_NAME].equals(names[i])) {
                    index = i;
                    break;
                }
            }

            salaries[index] += Integer.parseInt(parts[INDEX_OF_WORKING_HOURS])
                    * Integer.parseInt(parts[INDEX_OF_INCOME_PER_HOUR]);
        }

        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return report.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(
                new String[]{"John", "Andrew", "Kate"},
                new String[]{
                        "26.04.2019 John 4 50",
                        "05.04.2019 Andrew 3 200",
                        "10.04.2019 John 7 100",
                        "22.04.2019 Kate 9 100",
                        "25.06.2019 John 11 50",
                        "26.04.2019 Andrew 3 150",
                        "13.02.2019 John 7 100",
                        "26.04.2019 Kate 9 100"
                },
                "01.04.2019",
                "30.04.2019")
        );
    }
}
