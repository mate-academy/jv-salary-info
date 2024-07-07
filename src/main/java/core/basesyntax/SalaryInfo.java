package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0 || dateFrom.isEmpty() || dateTo.isEmpty()) {
            throw new IllegalArgumentException("No information provided");
        }
        StringBuilder results = new StringBuilder("Report for period ");
        int firstIndex = dateFrom.indexOf('=') + 3;
        results.append(dateFrom.substring(firstIndex, dateFrom.lastIndexOf(dateFrom)))
                .append(" - ")
                .append(dateTo.substring(firstIndex, dateTo.lastIndexOf(dateTo)))
                .append(System.lineSeparator());
        for (String dates: data) {
            for (String name: names) {
                String[] composite = dates.split(" ");
                int month = Integer.parseInt(dateTo.substring(firstIndex + 3, firstIndex + 5));
                int userMonth = Integer.parseInt(composite[0].substring(3, 5));
                if (month == userMonth) {
                    if (name.equals(composite[1])) {
                        int totalSum = 0;
                        int daySum = Integer.parseInt(composite[2]) * Integer.parseInt(composite[3]);
                        totalSum += daySum;
                        results.append(composite[1])
                                .append(" - ")
                                .append(totalSum)
                                .append(System.lineSeparator());
                    }
                }

            }
        }
        return results.toString();
    }
}
