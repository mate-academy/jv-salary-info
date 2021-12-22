package core.basesyntax;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = parseDate(dateFrom);
        LocalDate dateT = parseDate(dateTo);
        Map<String, Integer> map = new HashMap<>();
        for (String name : names) {
            map.put(name, 0);
        }
        for (String info : data) {
            String[] infoArr = info.split(" ");
            if ((parseDate(infoArr[0]).isAfter(dateF)
                    || parseDate(infoArr[0]).equals(dateF))
                    && (parseDate(infoArr[0]).isBefore(dateT)
                    || parseDate(infoArr[0]).equals(dateF))) {
                if(map.get(infoArr[1]) != null) {
                    Integer newValue = map.get(infoArr[1])
                            + Integer.valueOf(infoArr[2]) * Integer.valueOf(infoArr[3]);
                    map.put(infoArr[1], newValue);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo + "\n");
        for (Map.Entry entry : map.entrySet()) {
            stringBuilder.append(entry.getKey() + " - " + String.valueOf(entry.getValue()) + "\n");
        }
        return stringBuilder.toString();
    }

    private LocalDate parseDate(String date) {
        String[] dates = date.split("\\.");
        return LocalDate.parse(dates[2] + "-" + dates[1] + "-" + dates[0]);
    }
}
