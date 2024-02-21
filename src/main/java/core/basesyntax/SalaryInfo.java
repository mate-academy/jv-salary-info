package core.basesyntax;

public class SalaryInfo {
    private static final int RECORD_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_STR_INDEX = 2;
    private static final int RATE_STR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        return generateReport(names, data, dateFrom, dateTo);
    }

    private String generateReport(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();

        initializeReportHeader(reportBuilder, dateFrom, dateTo);
        int[] earnings = calculateEarnings(names, data, dateFrom, dateTo);
        finalizeReport(reportBuilder, names, earnings);

        return reportBuilder.toString();
    }

    private void initializeReportHeader(StringBuilder reportBuilder,
                                        String dateFrom,
                                        String dateTo) {
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
    }

    private int[] calculateEarnings(String[] names, String[] data, String dateFrom, String dateTo) {

        int[] earnings = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            if (parts.length == 4) {
                String recordDate = parts[RECORD_DATE_INDEX];
                String name = parts[NAME_INDEX];
                String hoursStr = parts[HOURS_STR_INDEX];
                String rateStr = parts[RATE_STR_INDEX];

                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i]) && isWithinDateRange(recordDate, dateFrom, dateTo)) {
                        earnings[i] += parseAndCalculateEarnings(hoursStr, rateStr);
                    }
                }
            }
        }
        return earnings;
    }

    private boolean isWithinDateRange(String recordDate, String dateFrom, String dateTo) {
        return compareDates(recordDate, dateFrom) >= 0
                && compareDates(recordDate, dateTo) <= 0;
    }

    private int parseAndCalculateEarnings(String hoursStr, String rateStr) {
        try {
            int hours = Integer.parseInt(hoursStr);
            int rate = Integer.parseInt(rateStr);
            return hours * rate;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void finalizeReport(StringBuilder reportBuilder,
                                String[] names,
                                int[] earnings) {
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i])
                    .append(" - ")
                    .append(earnings[i]);
            if (i != names.length - 1) {
                reportBuilder.append(System.lineSeparator());
            }
        }
    }

    private int compareDates(String date1, String date2) {
        String[] parts1 = date1.split("\\.");
        String[] parts2 = date2.split("\\.");

        for (int i = 2; i >= 0; i--) {
            int val1 = Integer.parseInt(parts1[i]);
            int val2 = Integer.parseInt(parts2[i]);
            if (val1 != val2) {
                return Integer.compare(val1, val2);
            }
        }
        return 0;
    }
}
