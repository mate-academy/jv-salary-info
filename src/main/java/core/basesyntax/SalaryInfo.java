package core.basesyntax;

public class SalaryInfo {
    private static final int FIRST_POINT_INDEX = 2;
    private static final int SECOND_POINT_INDEX = 6;
    private static final int HOURS_OF_WORK = 2;
    private static final int SALARY_INDEX = 3;
    private static final int PERSON_NAME_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ");
        salaryInfo.append(dateFrom).append(" - ").append(dateTo);
        int[] salaryCount = new int[names.length];
        double dateFromInMonthDay = dateToMonthDayFormat(dateFrom);
        double dateToInMonthDay = dateToMonthDayFormat(dateTo);
        for (String personData: data) {
            double personDateInMonthDay =
                    dateToMonthDayFormat(personData.substring(0,SECOND_POINT_INDEX));
            if (dateFromInMonthDay <= personDateInMonthDay
                    && personDateInMonthDay <= dateToInMonthDay) {
                String[] arrayPersonData = personData.split(" ");
                for (int j = 0; j < names.length; j++) {
                    if (arrayPersonData[PERSON_NAME_INDEX].equals(names[j])) {
                        salaryCount[j] += Integer.parseInt(arrayPersonData[HOURS_OF_WORK])
                                * Integer.parseInt(arrayPersonData[SALARY_INDEX]);
                        j = names.length;
                    }
                }
            }
        }
        for (int j = 0; j < names.length; j++) {
            salaryInfo.append(System.lineSeparator()).append(names[j])
                    .append(" - ").append(salaryCount[j]);
        }
        return salaryInfo.toString();
    }

    private double dateToMonthDayFormat(String date) {
        return Double.parseDouble(date.substring(FIRST_POINT_INDEX + 1, SECOND_POINT_INDEX)
                + date.substring(0, FIRST_POINT_INDEX));
    }
}
