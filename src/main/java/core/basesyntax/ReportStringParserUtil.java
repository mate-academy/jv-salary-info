package core.basesyntax;

public final class ReportStringParserUtil {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_AMOUNT_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public static Report parseReportString(String stringReport) {
        String[] splitReportString = stringReport.split(" ");
        String date = splitReportString[DATE_INDEX];
        String name = splitReportString[NAME_INDEX];
        int hoursAmount = Integer.parseInt(splitReportString[HOURS_AMOUNT_INDEX]);
        int salaryPerHour = Integer.parseInt(splitReportString[SALARY_PER_HOUR_INDEX]);
        return new Report(date, name, hoursAmount, salaryPerHour);
    }
}
