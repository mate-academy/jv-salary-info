package core.basesyntax;

import core.basesyntax.suppliers.DateSupplier;
import core.basesyntax.suppliers.IncomeSupplier;
import core.basesyntax.suppliers.NameSupplier;

public class SalaryInfo {
    private IncomeSupplier incomeSupplier = new IncomeSupplier();
    private DateSupplier dateSupplier = new DateSupplier();
    private NameSupplier nameSupplier = new NameSupplier();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int income = 0;
            for (String datum : data) {
                if (nameSupplier.getName(datum).equals(name)
                        && dateSupplier.isDateInInterval(datum, dateFrom, dateTo)) {
                    income += incomeSupplier.getDayIncome(datum);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(income);
        }
        return stringBuilder.toString();
    }
}
