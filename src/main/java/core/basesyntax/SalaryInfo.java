package core.basesyntax;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_INCOME_INDEX = 3;
    private static final int DAY_INDEX = 0; //not to confuse with DATE_INDEX
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        int parsedDateFrom = parseDate(dateFrom);
        int parsedDateTo = parseDate(dateTo);
        int[] salary = new int[names.length];
        for (String dataLine : data) {
            String[] parsedLine = dataLine.split("\\s+");
            int parsedDate = parseDate(parsedLine[DATE_INDEX]);
            if (parsedDate <= parsedDateTo && parsedDate >= parsedDateFrom) {
                int employeeIndex = findEmployeeIndex(names, parsedLine[NAME_INDEX]);
                int moneyEarned = Integer.parseInt(parsedLine[HOURS_INDEX])
                        * Integer.parseInt(parsedLine[HOUR_INCOME_INDEX]);
                salary[employeeIndex] += moneyEarned;
            }
        }
        return generateReport(names, salary, dateFrom, dateTo);
    }

    private int parseDate(String date) {
        String[] parsedDate = date.split("\\.");
        String dateString = parsedDate[YEAR_INDEX]
                + parsedDate[MONTH_INDEX]
                + parsedDate[DAY_INDEX];
        return Integer.parseInt(dateString);
    }

    private int findEmployeeIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return -1;
    }

    private String generateReport(String[] names,
                                  int[] salary,
                                  String dateFrom,
                                  String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return builder.toString();
    }
}
