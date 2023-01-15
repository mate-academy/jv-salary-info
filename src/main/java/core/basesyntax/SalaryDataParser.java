package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalaryDataParser {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    public ArrayList<String[]> splitData(String[] data) {
        ArrayList<String[]> splittedData = new ArrayList<>();
        for (String singleData : data) {
            splittedData.add(singleData.split(" "));
        }
        return splittedData;
    }
}
