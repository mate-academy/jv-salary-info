package core.basesyntax;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String DATA_DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int startDate = parseDate(dateFrom);
        int endDate = parseDate(dateTo);
        StringBuilder sb = new StringBuilder();
        sb
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String entry : data) {
                String[] values = entry.split(DATA_DELIMITER);
                int date = parseDate(values[DATE_INDEX]);

                if (name.equals(values[NAME_INDEX]) && date >= startDate && date <= endDate) {
                    int workingHours = Integer.parseInt(values[WORKING_HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(values[INCOME_PER_HOUR_INDEX]);
                    salary += workingHours * incomePerHour;
                }
            }

            sb
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return sb.toString();
    }

    private int parseDate(String date) {
        String[] parts = date.split("\\.");

        return Integer.parseInt(parts[2]) * 10000 // year
                + Integer.parseInt(parts[1]) * 100 // month
                + Integer.parseInt(parts[0]);
    }
}
