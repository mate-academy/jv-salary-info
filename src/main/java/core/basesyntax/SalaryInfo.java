package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String REPORT_TITLE = "Report for period ";
    private static final String HYPHEN_WITH_SPACES = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportText = new StringBuilder(REPORT_TITLE)
                .append(dateFrom)
                .append(HYPHEN_WITH_SPACES)
                .append(dateTo);
        for (String userName : names) {
            UserWithSalary userWithSalary = new UserWithSalary(userName);
            for (String dataPerUser : data) {
               Report report = ReportStringParserUtil.parseReportString(dataPerUser);
               if (userWithSalary.getUserName().equals(report.getUserName())
                       && checkIsDateInRange(report.getDate(), dateFrom, dateTo)) {
                   userWithSalary.addMoneyToSalary(
                           report.getSalaryPerHour() * report.getHoursAmount()
                   );
               }
           }
           reportText.append(new UserReportSupplier(userWithSalary).createUserReportTextLine());
       }
        return reportText.toString();
    }

    private boolean checkIsDateInRange(String dateToCheck, String rangeStart, String rangeEnd) {
        LocalDate date = ReportDateUtil.parseReportStringDate(dateToCheck);
        LocalDate rangeStartDate = ReportDateUtil.parseReportStringDate(rangeStart);
        LocalDate rangeEndDate = ReportDateUtil.parseReportStringDate(rangeEnd);
        return (date.isAfter(rangeStartDate) || date.isEqual(rangeStartDate))
                && (date.isBefore(rangeEndDate) || date.isEqual(rangeEndDate));
    }
}
