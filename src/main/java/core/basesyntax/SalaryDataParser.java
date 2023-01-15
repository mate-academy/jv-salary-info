package core.basesyntax;

import java.util.ArrayList;
import java.util.Arrays;

public class SalaryDataParser {

    public int[] parseDate(String date) {
        return Arrays.stream(date.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }

    public CalendarDay arrayToCalendarDay(int[] date) {
        return new CalendarDay(date[0], date[1], date[2]);
    }

    public ArrayList<String[]> splitData(String[] data) {
        ArrayList<String[]> splittedData = new ArrayList<>();
        for (String singleData : data) {
            splittedData.add(singleData.split(" "));
        }
        return splittedData;
    }
}
