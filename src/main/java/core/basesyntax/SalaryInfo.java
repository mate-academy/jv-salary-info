package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT_OF_DATE =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int HOURS_WORKED_INDEX = 2;
    public static final int SALARY_PH_INDEX = 3;
    public static final int INDEX_OF_DATE = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate periodFrom = LocalDate.parse(dateFrom, FORMAT_OF_DATE);
        LocalDate periodTo = LocalDate.parse(dateTo, FORMAT_OF_DATE);
        StringBuilder salaryInfo = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));

        for (String name : names) {
            int salaryForPeriod = 0;
            for (String information : data) {
                if (information.contains(name)) {
                    LocalDate localDate = LocalDate.parse(information.split(" ")[INDEX_OF_DATE], FORMAT_OF_DATE);
                    if ((periodTo.isAfter(localDate) || periodTo.isEqual(localDate))
                            && periodFrom.isBefore(localDate) || periodFrom.isEqual(localDate)) {
                        salaryForPeriod +=
                                Integer.parseInt(information.split(" ")[HOURS_WORKED_INDEX])
                                        * Integer.parseInt(information.split(" ")[SALARY_PH_INDEX]);
                    }
                }

            }
            salaryInfo.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(salaryForPeriod);
        }
        return salaryInfo.toString();
    }
}
