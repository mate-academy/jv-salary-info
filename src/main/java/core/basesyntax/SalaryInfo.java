package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_TIME_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder();
        result.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        List<String[]> list = Arrays.stream(data).map(x -> x.split(" ")).toList();

        for (String name : names) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                try {
                    if (sdf.parse(list.get(i)[DATE_INDEX]).compareTo(sdf.parse(dateFrom)) >= 0
                            && sdf.parse(list.get(i)[DATE_INDEX]).compareTo(sdf.parse(dateTo)) <= 0
                            && name.contains(list.get(i)[NAME_INDEX])) {
                        sum += Integer.parseInt(list.get(i)[WORK_TIME_INDEX]) * Integer.parseInt(list.get(i)[SALARY_INDEX]);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException();
                }
            }
            result.append("\n").append(name).append(" - ").append(sum);
        }
        return result.toString();
    }
}
