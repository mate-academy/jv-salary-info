package core.basesyntax;

public class SalaryInfo {
    private final IncomeCalculator incomeCalculator = new IncomeCalculator();
    private final ReportBuilder reportBuilder = new ReportBuilder();

    public String getSalaryInfo(String[] names, String[] dataRows, String dateFrom, String dateTo) {
        IncomePerUser incomePerUser = incomeCalculator.calculate(names, dataRows, dateFrom, dateTo);

        return reportBuilder.build(incomePerUser, dateFrom, dateTo);
    }
}
