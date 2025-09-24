package core.basesyntax;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_PAY_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] salaries = new int[names.length];
        String[] localData;
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < data.length; i++) {
            localData = Utilities.parseLine(data[i]);
            if (Utilities.isDateInBound(dateFrom, dateTo, localData[INDEX_OF_DATE])) {
                int indexOfName = Utilities.indexOfName(names, localData[INDEX_OF_NAME]);
                if (indexOfName != -1) {
                    salaries[indexOfName] += Integer.parseInt(localData[INDEX_OF_HOURS])
                            * Integer.parseInt(localData[INDEX_OF_PAY_RATE]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaries[i]);
        }
        return stringBuilder.toString();
    }
}
