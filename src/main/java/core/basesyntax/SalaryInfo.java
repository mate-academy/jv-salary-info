package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportText = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        User[] usersList = new User[names.length];

        for (int i = 0; i < names.length; i++) {
            usersList[i] = new User(names[i]);
        }

        for (String dataPerUser : data) {
            Report userReport = new Report(dataPerUser);
            ReportDateUtil reportDate = new ReportDateUtil(userReport.getDate());
            if (reportDate.checkIsDateInRange(dateFrom, dateTo)) {
                for (User user : usersList) {
                    if (user.getName().equals(userReport.getName())) {
                        user.addMoneyToSalary(userReport.getSalaryPerHour()
                                        * userReport.getHoursCount());
                        break;
                    }
                }
            }
        }

        for (User user : usersList) {
            reportText.append("\n");
            reportText.append(user.getName());
            reportText.append(" - ");
            reportText.append(user.getSalaryAmount());
        }

        return reportText.toString();
    }
}
