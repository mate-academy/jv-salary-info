package core.basesyntax;

public class SalaryInfo {
    private String[] names;
    private String[] data;
    private String dateFrom;
    private String dateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        this.names = names;
        this.data = data;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        return generateReport();
    }

    private String generateReport() {
        int[] earnings = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            if (parts.length == 4) {
                String recordDate = parts[0];
                String name = parts[1];
                String hoursStr = parts[2];
                String rateStr = parts[3];

                for (int i = 0; i < names.length; i++) {
                    if (name.equals(names[i]) && isWithinDateRange(recordDate, dateFrom, dateTo)) {
                        earnings[i] += parseAndCalculateEarnings(hoursStr, rateStr);
                    }
                }
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i])
                    .append(" - ")
                    .append(earnings[i]);
            if (i != names.length - 1) {
                reportBuilder.append(System.lineSeparator());
            }
        }
        return reportBuilder.toString();
    }

    private boolean isWithinDateRange(String recordDate, String dateFrom, String dateTo) {
        return compareDates(recordDate, dateFrom) >= 0 && compareDates(recordDate, dateTo) <= 0;
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
