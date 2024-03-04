package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dateFromParts = dateFrom.split("\\.");
        String[] dateToParts = dateTo.split("\\.");
        int fromDay = Integer.parseInt(dateFromParts[0]);
        int fromMonth = Integer.parseInt(dateFromParts[1]);
        int fromYear = Integer.parseInt(dateFromParts[2]);
        int toDay = Integer.parseInt(dateToParts[0]);
        int toMonth = Integer.parseInt(dateToParts[1]);
        int toYear = Integer.parseInt(dateToParts[2]);
        int startDateValue = fromYear * 10000 + fromMonth * 100 + fromDay;
        int endDateValue = toYear * 10000 + toMonth * 100 + toDay;

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(" ");
                String[] dateParts = parts[0].split("\\.");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                int dateValue = year * 10000 + month * 100 + day;

                if (parts[1].equals(name) && (dateValue >= startDateValue && dateValue <= endDateValue)) {
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int payPerHour = Integer.parseInt(parts[3]);
                    totalSalary += hoursWorked * payPerHour;
                }
            }
            result.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
