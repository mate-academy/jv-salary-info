package core.basesyntax;

public class SalaryInfo {
    private static final Integer HOURS_OF_WORK_INDEX = 2;
    private static final Integer SALARY_PER_HOUR_INDEX = 3;
    private static final Integer POSITION_OF_DATE_IN_STRING = 0;
    private static final Integer START_DAY_INDEX = 0;
    private static final Integer START_MONTH_INDEX = 2;
    private static final Integer END_DATE_INDEX = 3;
    private static final Integer END_MONTH_INDEX = 5;
    private static final Integer DAYS_IN_MONTH = 30;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int startDay = calculateDays(dateFrom);
        int endDay = calculateDays(dateTo);
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int sumForName = 0;
            for (String string : data) {
                String[] dataString = string.split(" ");
                if (string.contains(name)) {
                    int sum = Integer.parseInt(dataString[HOURS_OF_WORK_INDEX])
                            * Integer.parseInt(dataString[SALARY_PER_HOUR_INDEX]);
                    if (calculateDays(dataString[POSITION_OF_DATE_IN_STRING]) >= startDay
                            && calculateDays(dataString[POSITION_OF_DATE_IN_STRING]) <= endDay) {
                        sumForName += sum;
                    }
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(sumForName);
        }
        return sb.toString();
    }

    private static Integer calculateDays(String string) {
        return Integer.parseInt(string.substring(END_DATE_INDEX, END_MONTH_INDEX))
                * DAYS_IN_MONTH
                + Integer.parseInt(string.substring(START_DAY_INDEX, START_MONTH_INDEX));
    }
}
