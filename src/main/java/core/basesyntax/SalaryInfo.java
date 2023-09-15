package core.basesyntax;

public class SalaryInfo {
    private static final int CURRENT_DAY = 0;
    private static final int NAME = 1;
    private static final int WORKED_HOURS = 2;
    private static final int WAGE_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateRangeValidator dateRangeValidator = new DateRangeValidator(dateFrom, dateTo);
        StringBuilder finalList = new StringBuilder();
        finalList.append("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String nameInList : data) {
                String [] splitData = nameInList.split(" ");
                if (name.equals(splitData[NAME])
                        && dateRangeValidator.isWithinRange(splitData[CURRENT_DAY])) {
                    salary += Integer.parseInt(splitData[WORKED_HOURS])
                            * Integer.parseInt(splitData[WAGE_PER_HOUR]);
                }
            }
            finalList.append(System.lineSeparator() + name + " - " + salary);
        }
        return finalList.toString();
    }
}
