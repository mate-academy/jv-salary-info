package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final String FORMAT_DATE = "dd.MM.yyyy";
    static final int POSITION_DATE_IN_ARRAY_DATA = 0;
    static final int POSITION_NAME_IN_ARRAY_DATE = 1;
    static final int POSITION_HOURS_IN_ARRAY_DATE = 2;
    static final int POSITION_SALARY_IN_ARRAY_DATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromSalaryInfo = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(FORMAT_DATE));
        LocalDate dateToSalaryInfo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern(FORMAT_DATE));
        LocalDate dateWorkUser;
        StringBuilder finishInformation = new StringBuilder("Report for period ");
        finishInformation.append(dateFrom).append(" - ").append(dateTo);
        String[] tempArray;
        int allSalaryUser;

        for (String nameUser : names) {
            allSalaryUser = 0;
            for (String dataWorkUser : data) {
                tempArray = dataWorkUser.split(" ");
                if (tempArray[POSITION_NAME_IN_ARRAY_DATE].equals(nameUser)) {
                    dateWorkUser = LocalDate.parse(tempArray[POSITION_DATE_IN_ARRAY_DATA],
                            DateTimeFormatter.ofPattern(FORMAT_DATE));
                    if (dateWorkUser.isAfter(dateFromSalaryInfo)
                            && dateWorkUser.isBefore(dateToSalaryInfo)
                            || dateWorkUser.equals(dateFromSalaryInfo)
                            || dateWorkUser.equals(dateToSalaryInfo)) {
                        allSalaryUser += Integer.parseInt(tempArray[POSITION_HOURS_IN_ARRAY_DATE])
                                * Integer.parseInt(tempArray[POSITION_SALARY_IN_ARRAY_DATE]);
                    }
                }
            }
            finishInformation.append("\n").append(nameUser).append(" - ").append(allSalaryUser);
        }
        return finishInformation.toString();
    }
}
