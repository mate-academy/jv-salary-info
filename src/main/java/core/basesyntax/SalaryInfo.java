package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        LocalDate dateStart = getDate(dateFrom);
        LocalDate dateEnd = getDate(dateTo);

        for (String name : names) {
            result.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String[] infoArray = info.split(" ");
                    LocalDate workDate = getDate(infoArray[0]);
                    if (workDate.isEqual(dateStart)
                            || (workDate.isAfter(dateStart) && workDate.isBefore(dateEnd)
                            || workDate.isEqual(dateEnd))) {
                        int hour = Integer.parseInt(infoArray[2]);
                        int rate = Integer.parseInt(infoArray[3]);
                        salary += hour * rate;
                    }
                }
            }
            result.append(salary);
        }
        return result.toString();
    }

    private LocalDate getDate(String stringDate) {
        String[] tempDateArray = stringDate.split("\\.");
        int tempDay = Integer.parseInt(tempDateArray[0]);
        int tempMonth = Integer.parseInt(tempDateArray[1]);
        int tempYear = Integer.parseInt(tempDateArray[2]);
        return LocalDate.of(tempYear, tempMonth, tempDay);
    }
}
