package core.basesyntax;

public class SalaryInfo {
    private static final String REPORT_TITLE = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportText = new StringBuilder(REPORT_TITLE)
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        User[] usersList = new User[names.length];

        for (int i = 0; i < names.length; i++) {
            usersList[i] = new User(names[i]);
        }

        for (String userData : data) {
            Report userReport = new ReportStringParser().parseReportString(userData);
            ReportDateUtil reportDate = new ReportDateUtil(userReport.getDate());
            if (reportDate.checkIsDateInRange(dateFrom, dateTo)) {
                for (User user : usersList) {
                    if (user.getUserName().equals(userReport.getUserName())) {
                        user.addMoneyToSalary(userReport.getSalaryPerHour()
                                        * userReport.getHoursAmount());
                        break;
                    }
                }
            }
        }

        for (User user : usersList) {
            reportText.append(new UserReportSupplier(user).createUserReportTextLine());
        }

        return reportText.toString();
    }
}
