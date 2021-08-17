package core.basesyntax;

import java.time.LocalDate;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("");

        for (String name : names) {
            int bufferSum = 0;
            for (String datum : data) {
                String[] personalData = datum.split(" ");
                if (name.equals(personalData[1]) &&)
            }
        }
    }
}
