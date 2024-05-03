package core.basesyntax;

public class SalaryInfo {
    private static final String SAMPLE = "*****";
    private static final int MAX_INDEX = 10;
    private final SalaryCalculator salaryInfo = new SalaryCalculator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        RangeOfDates rangeOfDates = new RangeOfDates(dateFrom, dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        String[] checkedArray = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            if (rangeOfDates.isInDates(data[i].substring(0, MAX_INDEX))) {
                checkedArray[i] = data[i].substring(MAX_INDEX + 1);
            } else {
                checkedArray[i] = SAMPLE;
            }
        }
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(salaryInfo.toCalculateSalary(names, checkedArray));
        return result.toString();
    }
}
