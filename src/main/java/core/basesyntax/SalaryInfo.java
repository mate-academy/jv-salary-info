package core.basesyntax;

public class SalaryInfo {
    private static final String SAMPLE = "*****";
    private static final int MAX_INDEX = 12;
    private final CalculateSalary salaryInfo = new CalculateSalary();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        BetweenDates betweenDates = new BetweenDates(dateFrom, dateTo);
        String result;
        String[] checkedArray = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            if (betweenDates.isBetweenDates(data[i].substring(0, MAX_INDEX))) {
                checkedArray[i] = data[i].substring(MAX_INDEX - 1);
            } else {
                checkedArray[i] = SAMPLE;
            }
        }
        result = salaryInfo.toGetSalary(names, checkedArray);
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator() + result;
    }
}
