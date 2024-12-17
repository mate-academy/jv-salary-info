package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int[] salary = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            String dateOfWork = parts[0];
            String name = parts[1];
            int hoursOfWork = Integer.parseInt(parts[2]);
            int salaryPerHour = Integer.parseInt(parts[3]);

            if (isDateInRange(dateOfWork, dateFrom, dateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salary[i] += hoursOfWork * salaryPerHour;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(lineSeparator).append(names[i]).append(" - ").append(salary[i]);
        }
        return report.toString();
    }

    private static boolean isDateInRange(String dateOfWork, String dateFrom, String dateTo) {
        String[] dateParts = dateOfWork.split("\\.");
        String[] fromParts = dateFrom.split("\\.");
        String[] toParts = dateTo.split("\\.");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        int fromDay = Integer.parseInt(fromParts[0]);
        int fromMonth = Integer.parseInt(fromParts[1]);
        int fromYear = Integer.parseInt(fromParts[2]);

        int toDay = Integer.parseInt(toParts[0]);
        int toMonth = Integer.parseInt(toParts[1]);
        int toYear = Integer.parseInt(toParts[2]);

        if (year < fromYear || (year == fromYear && (month < fromMonth
                || (month == fromMonth && day < fromDay)))) {
            return false;
        }

        if (year > toYear || (year == toYear && (month > toMonth
                || (month == toMonth && day > toDay)))) {
            return false;
        }
        return true;
    }
}
