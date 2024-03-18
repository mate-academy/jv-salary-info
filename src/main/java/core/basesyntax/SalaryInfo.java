package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final HashMap<String, Integer> HASH_MAP = new HashMap<>();
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS_WORK = 2;
    private static final int INDEX_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        HASH_MAP.clear();
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = DATE_FORMAT.parse(dateFrom);
            toDate = DATE_FORMAT.parse(dateTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String itemNames : names) {
            for (String itemDate : data) {
                String[] arrayElements = itemDate.split(" ");
                String employeeName = arrayElements[INDEX_NAME];
                Date dateWork = null;
                try {
                    dateWork = DATE_FORMAT.parse(arrayElements[INDEX_DATE]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (arrayElements[INDEX_NAME].equals(itemNames)
                        && dateWork.getTime() <= toDate.getTime()
                        && dateWork.getTime() >= fromDate.getTime()) {
                    int salary = Integer.parseInt(arrayElements[INDEX_SALARY]);
                    int day = Integer.parseInt(arrayElements[INDEX_HOURS_WORK]);
                    if (HASH_MAP.containsKey(employeeName)) {
                        HASH_MAP.put(employeeName, HASH_MAP.get(employeeName) + salary * day);
                    } else {
                        HASH_MAP.put(employeeName, salary * day);
                    }
                }
            }
        }

        if (HASH_MAP.isEmpty()) {
            for (String item : names) {
                HASH_MAP.put(item, 0);
            }
        }

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\r\n");
        for (int i = 0; i < names.length; i++) {
            Integer salary = HASH_MAP.get(names[i]);
            resultBuilder.append(names[i]).append(" - ").append(salary);
            if (i < names.length - 1) {
                resultBuilder.append("\r\n");
            }
        }
        return resultBuilder.toString();
    }
}
