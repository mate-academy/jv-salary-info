package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATA_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int INDEX_DATE = 0;
    public static final int INDEX_NAME = 1;
    public static final int INDEX_PRICE = 2;
    public static final int INDEX_HOURS = 3;
    public static final int SIZE_DATA_ARRAY = 4;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate dateFromLocal = LocalDate.parse(dateFrom, DATA_FORMAT);
        final LocalDate dateToLocal = LocalDate.parse(dateTo, DATA_FORMAT);
        LocalDate dateWork;
        String[] dataOfMen = new String[SIZE_DATA_ARRAY];
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salaryMen = 0;
            for (String info : data) {
                dataOfMen = info.split(" ");
                dateWork = LocalDate.parse(dataOfMen[INDEX_DATE], DATA_FORMAT);
                if (name.equals(dataOfMen[INDEX_NAME])) {
                    if (!dateWork.isBefore(dateFromLocal) && !dateWork.isAfter(dateToLocal)) {
                        salaryMen += Integer.parseInt(dataOfMen[INDEX_HOURS])
                                   * Integer.parseInt(dataOfMen[INDEX_PRICE]);
                    }
                }
            }
            salaryInfo.append("\n").append(name).append(" - ").append(salaryMen);
        }
        return salaryInfo.toString();
    }
}
