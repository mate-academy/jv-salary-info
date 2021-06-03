package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilsData {
    public LocalDate getDateFromDate(String data, DateTimeFormatter formatter) {
        return LocalDate.parse(data.split(" ")[0], formatter);
    }

    public String getNameFromDate(String data) {
        return data.split(" ")[1];
    }

    public int getWorkingHourFromDate(String data) {
        return Integer.parseInt(data.split(" ")[2]);
    }

    public int getIncomePerHourFromDate(String data) {
        return Integer.parseInt(data.split(" ")[3]);
    }
}
