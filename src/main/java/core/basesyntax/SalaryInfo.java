package core.basesyntax;

public class SalaryInfo {
    static final int intDateStr = 0;
    static final int intEmployeeName = 1;
    static final int intHoursWorked = 2;
    static final int intIncomePerHour = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String dateFromComparable = convertDateToComparable(dateFrom);
        String dateToComparable = convertDateToComparable(dateTo);
        int[] totalEarnings = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateStr = parts[intDateStr];
            String employeeName = parts[intEmployeeName];
            int hoursWorked = Integer.parseInt(parts[intHoursWorked]);
            int incomePerHour = Integer.parseInt(parts[intIncomePerHour]);

            String entryDateComparable = convertDateToComparable(dateStr);

            if (entryDateComparable.compareTo(dateFromComparable) >= 0
                    && entryDateComparable.compareTo(dateToComparable) <= 0) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        int earnings = hoursWorked * incomePerHour;
                        totalEarnings[i] += earnings;
                        break;
                    }
                }
            }
        }

        return buildReport(names, totalEarnings, dateFrom, dateTo);
    }

    private static String buildReport(String[] names, int[] totalEarnings,
                                      String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(totalEarnings[i]);
        }
        return report.toString();
    }

    private static String convertDateToComparable(String date) {
        String[] parts = date.split("\\.");
        return parts[2] + parts[1] + parts[0];
    }
}
