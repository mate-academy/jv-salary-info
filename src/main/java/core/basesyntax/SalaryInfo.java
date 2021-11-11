package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        int parsedDateFrom = parseDate(dateFrom);
        int parsedDateTo = parseDate(dateTo);
        int[] salary = new int[names.length];
        for (String dataLine : data) {
            String[] parsedLine = dataLine.split("\\s+");
            int parsedDate = parseDate(parsedLine[0]);
            if (parsedDate <= parsedDateTo && parsedDate >= parsedDateFrom) {
                int employeeIndex = findEmployeeIndex(names, parsedLine[1]);
                int moneyEarned = Integer.parseInt(parsedLine[2])
                        * Integer.parseInt(parsedLine[3]);
                salary[employeeIndex] += moneyEarned;
            }
        }
        return generateReport(names, salary, dateFrom, dateTo);
    }

    private int parseDate(String date) {
        String[] parsedDate = date.split("\\.");
        String dateString = parsedDate[2]
                + parsedDate[1]
                + parsedDate[0];
        return Integer.parseInt(dateString);
    }

    private int findEmployeeIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return -1;
    }

    private String generateReport(String[] names,
                                  int[] salary,
                                  String dateFrom,
                                  String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return builder.toString();
    }
}
