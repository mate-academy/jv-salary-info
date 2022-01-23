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
        StringBuilder report = new StringBuilder();
        int[] salary = new int[names.length];

        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            for (String currentData : data) {
                String[] splitData = currentData.split(" ");

                if (names[i].equals(splitData[INDEX_OF_NAME])
                        && parseDate(splitData[INDEX_OF_DATE]).isAfter(localDateFrom)
                        && (parseDate(splitData[INDEX_OF_DATE]).isBefore(localDateTo)
                        || parseDate(splitData[INDEX_OF_DATE]).isEqual(localDateTo))) {
                    salary[i] += Integer.parseInt(splitData[INDEX_OF_WORKING_HOUR])
                            * Integer.parseInt(splitData[INDEX_OF_INCOME]);
                }
            }
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return report.toString();
    }

    private LocalDate parseDate(String date) {
        String[] splitDate = date.split("\\.");
        return LocalDate.of(
                Integer.parseInt(splitDate[INDEX_OF_YEAR]),
                Integer.parseInt(splitDate[INDEX_OF_MONTH]),
                Integer.parseInt(splitDate[INDEX_OF_DAY])
        );
    }
}
