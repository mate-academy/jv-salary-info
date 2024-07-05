package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0 || dateFrom.isEmpty() || dateTo.isEmpty()) {
            return null;
        }
        StringBuilder results = new StringBuilder("Report for period ");
        int firstIndex = dateFrom.indexOf('=') + 3;
        results.append(dateFrom.substring(firstIndex, dateFrom.lastIndexOf(dateFrom)))
                .append(" - ")
                .append(dateTo.substring(firstIndex, dateTo.lastIndexOf(dateTo)))
                .append(System.lineSeparator());
        for (int i = 0; i < data.length; i++) {
            int lastData = Integer.parseInt(dateTo.substring(firstIndex, firstIndex + 2));
            int userDay = Integer.parseInt(data[0].substring(0,2));
            int sumOfDay = lastData - userDay + 1;
            int sum = Integer.parseInt(data[2]) * Integer.parseInt(data[3]) * sumOfDay;
            results.append(names[i])
                    .append(" - ")
                    .append(sum)
                    .append(System.lineSeparator());
        }
        return results.toString();
    }
}
