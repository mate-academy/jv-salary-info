package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final int POSITION_DATE_IN_ARRAY_DATA = 0;
    private static final int POSITION_NAME_IN_ARRAY_DATA = 1;
    private static final int POSITION_HOURS_IN_ARRAY_DATA = 2;
    private static final int POSITION_SALARY_IN_ARRAY_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromSalaryInfo = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate dateToSalaryInfo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        StringBuilder resultInformation = new StringBuilder("Report for period ");
        resultInformation.append(dateFrom).append(" - ").append(dateTo);

        for (String nameUser : names) {
            int allSalaryUser = 0;
            for (String dataWorkUser : data) {
                String[] tempArray = dataWorkUser.split(" ");
                if (tempArray[POSITION_NAME_IN_ARRAY_DATA].equals(nameUser)) {
                    LocalDate currentDate = LocalDate.parse(tempArray[POSITION_DATE_IN_ARRAY_DATA],
                            DateTimeFormatter.ofPattern(DATE_FORMAT));

                    if (currentDate.isAfter(dateFromSalaryInfo)
                            && currentDate.isBefore(dateToSalaryInfo)
                            || currentDate.equals(dateFromSalaryInfo)
                            || currentDate.equals(dateToSalaryInfo)) {
                        allSalaryUser += Integer.parseInt(tempArray[POSITION_HOURS_IN_ARRAY_DATA])
                                * Integer.parseInt(tempArray[POSITION_SALARY_IN_ARRAY_DATA]);
                    }
                }
            }
            resultInformation.append("\n").append(nameUser).append(" - ").append(allSalaryUser);
        }
        return resultInformation.toString();
    }
}
