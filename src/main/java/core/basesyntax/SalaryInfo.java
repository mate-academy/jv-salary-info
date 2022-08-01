package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        CompareTwoDates compareTwoDates = new CompareTwoDates();
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split(" ");
            if (compareTwoDates.comparator(dateFrom, temp[0], dateTo)) {
                salary[foundIndex(names, temp[1])] +=
                        Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
            }
        }
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }

    public int foundIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return 0;
    }
}
