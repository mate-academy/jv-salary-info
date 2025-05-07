package core.basesyntax;

public class ReportBuilder {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String build(IncomePerUser incomePerUser, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String userName : incomePerUser.getNames()) {
            reportBuilder.append(LINE_SEPARATOR)
                    .append(userName)
                    .append(" - ")
                    .append(incomePerUser.getIncome(userName));
        }

        return reportBuilder.toString();
    }
}
