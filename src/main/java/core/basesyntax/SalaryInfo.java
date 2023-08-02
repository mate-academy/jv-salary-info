package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_RATE = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            result.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] infoArray = info.split(" ");
                    LocalDate workDate = LocalDate.parse(infoArray[INDEX_OF_DATE], formatter);
                    if (workDate.isEqual(dateStart)
                            || (workDate.isAfter(dateStart) && workDate.isBefore(dateEnd)
                            || workDate.isEqual(dateEnd))) {
                        int hour = Integer.parseInt(infoArray[INDEX_OF_HOURS]);
                        int rate = Integer.parseInt(infoArray[INDEX_OF_RATE]);
                        salary += hour * rate;
                    }
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
