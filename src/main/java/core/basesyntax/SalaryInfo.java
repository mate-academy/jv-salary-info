package core.basesyntax;

public class SalaryInfo {
    private VerifyData verifyData = new VerifyData();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDate startDate = new SimpleDate(dateFrom);
        SimpleDate endDate = new SimpleDate(dateTo);
        int[] salaries = new int[names.length];

        for (String entry : data) {

            try {
                verifyData.verification(entry);
            } catch (FormatDataException e) {
                System.out.println("[" + entry + "] - bad format");
                continue;
            }

            String[] parts = entry.split(" ");
            SimpleDate workDate = new SimpleDate(parts[0]);
            String name = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (workDate.isWithin(startDate, endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hoursWorked * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return report.toString();
    }
}
