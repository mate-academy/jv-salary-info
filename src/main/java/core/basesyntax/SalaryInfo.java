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
        String[] usedNames = new String[data.length];
        int[] userSum = new int[data.length];
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            String[] composite = data[i].split(" ");
            int month = Integer.parseInt(dateTo.substring(firstIndex + 3, firstIndex + 5));
            int userMonth = Integer.parseInt(composite[0].substring(3, 5));
            if (month == userMonth) {
                usedNames[i] = composite[1];
                int daySum = Integer.parseInt(composite[2]) * Integer.parseInt(composite[3]);
                userSum[i] = daySum;
            }
        }
        for (int i = 0; i < usedNames.length; i++) {
            for (String name : names) {
                if (usedNames[i].equals(name)) {
                    sum += userSum[i];
                }
            }
            results.append(usedNames[i])
                    .append(" - ")
                    .append(sum)
                    .append(System.lineSeparator());
        }
        return results.toString();
    }
}
