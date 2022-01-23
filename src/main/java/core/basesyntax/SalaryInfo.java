package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int INDEX_OF_DAY = 0;
    private static final int INDEX_OF_MONTH = 1;
    private static final int INDEX_OF_YEAR = 2;

    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOUR = 2;
    private static final int INDEX_OF_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseDate(dateFrom);
        LocalDate localDateTo = parseDate(dateTo);
        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");

                if (names[i].equals(splitData[INDEX_OF_NAME])
                        && parseDate(splitData[INDEX_OF_DATE]).isAfter(localDateFrom)
                        && parseDate(splitData[INDEX_OF_DATE]).isBefore(localDateTo.plusDays(1))) {
                    salary[i] += Integer.parseInt(splitData[INDEX_OF_WORKING_HOUR])
                            * Integer.parseInt(splitData[INDEX_OF_INCOME]);
                }
            }
        }
        return getReport(names, salary, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        String[] splitDate = date.split("\\.");
        return LocalDate.of(
                Integer.parseInt(splitDate[INDEX_OF_YEAR]),
                Integer.parseInt(splitDate[INDEX_OF_MONTH]),
                Integer.parseInt(splitDate[INDEX_OF_DAY])
        );
    }

    private String getReport(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return report.toString();
    }
}
