package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DaySummary[] daySummaries = new DaySummary[data.length];
        for (int i = 0; i < data.length; i++) {
            daySummaries[i] = new DaySummary(data[i]);
        }

        StringBuilder builder = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        DateSupplier dateSupplier = new DateSupplier();

        int sum;
        for (String name : names) {
            builder.append("\r\n").append(name).append(" - ");
            sum = 0;
            for (DaySummary daySummary : daySummaries) {
                if (dateSupplier.isBetween(daySummary.getDate(), dateFrom, dateTo)
                        && name.equals(daySummary.getEmployee())) {
                    sum += daySummary.getDaySumIncome();
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
