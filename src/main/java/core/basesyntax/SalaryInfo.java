package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int firstIndex = 0;
    private static final int thirdIndex = 2;
    private static final int forthIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int index = 0; index < names.length; ++index) {
            String name = names[index];
            int sum = 0;
            for (String userInfo : data) {
                if (userInfo.contains(name)) {
                    String[] arr = userInfo.split(" ");
                    LocalDate localDate = LocalDate.parse(arr[firstIndex], formatter);
                    if ((localDate.isAfter(localDateFrom)
                            || localDate.getDayOfMonth() == localDateFrom.getDayOfMonth())
                            && (localDate.isBefore(localDateTo)
                            || localDate.getDayOfMonth() == localDateTo.getDayOfMonth())) {
                        int hours = Integer.parseInt(arr[thirdIndex]);
                        int salary = Integer.parseInt(arr[forthIndex]);
                        sum += (hours * salary);
                    }
                }
            }
            if (index == names.length - 1) {
                stringBuilder.append(name).append(" - ").append(sum);
                break;
            }
            stringBuilder.append(name).append(" - ").append(sum).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
