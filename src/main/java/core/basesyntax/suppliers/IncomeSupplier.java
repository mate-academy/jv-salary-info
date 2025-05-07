package core.basesyntax.suppliers;

public class IncomeSupplier {
    private static final int INCOME_PER_HOURS_INDEX = 3;
    private HoursSupplier hoursSupplier = new HoursSupplier();

    public int getIncomePerHour(String data) {
        return Integer.parseInt(data.split(" ")[INCOME_PER_HOURS_INDEX]);
    }

    public int getDayIncome(String data) {
        return getIncomePerHour(data) * hoursSupplier.getWorkingHours(data);
    }
}
