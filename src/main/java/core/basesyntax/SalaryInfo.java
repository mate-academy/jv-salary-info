package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DaySummary[] daySummaries = new DaySummary[data.length];
        for (int i = 0; i < data.length; i++) {
            daySummaries[i] = splitData(data[i]);
        }

        StringBuilder builder = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        DateService dateService = new DateService();

        int sum;
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            sum = 0;
            for (DaySummary daySummary : daySummaries) {
                if (dateService.isBetween(daySummary.getDate(), dateFrom, dateTo)
                        && name.equals(daySummary.getEmployee())) {
                    sum += daySummary.getDaySumIncome();
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }

    private DaySummary splitData(String data) {
        String[] string = data.split(" ");
        return new DaySummary(string[0], string[1], Integer.parseInt(string[2]),
                Integer.parseInt(string[3]));
    }
}
