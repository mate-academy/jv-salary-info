package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATA_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalData = LocalDate.parse(dateFrom, DATA_FORMAT);
        LocalDate dateToLocalData = LocalDate.parse(dateTo, DATA_FORMAT);
        LocalDate dateWork;
        String[] dataOfMen = new String[4];
        int salaryMen = 0;

        StringBuilder salaryInfo = new StringBuilder(String.format("Report for period %s - %s",
                                                                                      dateFrom,
                                                                                      dateTo));
        for (String name : names) {
            for (String info : data) {
                dataOfMen = info.split(" ");
                dateWork = LocalDate.parse(dataOfMen[0], DATA_FORMAT);
                if (name.equals(dataOfMen[1])) {
                    if ((dateToLocalData.isAfter(dateWork) || (dateToLocalData.isEqual(dateWork))
                            && dateFromLocalData.isBefore(dateWork)
                            || dateFromLocalData.isEqual(dateWork))) {
                        salaryMen += Integer.parseInt(dataOfMen[2])
                                   * Integer.parseInt(dataOfMen[3]);
                    }
                }else {
                    continue;
                }
            }
            salaryInfo.append("\n").append(name).append(" - ").append(salaryMen);
            salaryMen = 0;
        }
        return salaryInfo.toString();
    }
}
