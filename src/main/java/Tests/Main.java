package Tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String[] data = new String[]{"26.04.2019 John 4 50", "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100", "22.04.2019 Kate 9 100", "25.06.2019 John 11 50"
                , "26.04.2019 Andrew 3 150", "13.02.2019 John 7 100", "26.04.2019 Kate 9 100"};

        String[] names = new String[]{"john", "andrew", "alice", "bob", "tania", "tolia"};
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builderOfValidRange = new StringBuilder();
//        for (String dat : data ) {
//            String[] parts = dat.split(" ");
//            stringBuilder.append(parts[0]).append(" ").toString();
//        }
//        System.out.println(stringBuilder);
//     // у цьому методі ми отримали всі дати із масива дат
        int result = 0;
        for (String dataItem : data) {
            String[] parts = dataItem.split(" ");
            String dateString = parts[0];
            try {
                Date date = dateFormat.parse(dateString);
                Date fromDate = dateFormat.parse(dateFrom);
                Date toDate = dateFormat.parse(dateTo);
                if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                    builderOfValidRange.append(parts[1] + " ")
                            .append(Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]) + ",").toString();
                }
            } catch (ParseException e) {
                System.out.println("date format exseption");
            }
        }
        String[] arr = builderOfValidRange.toString().split(",");
        StringBuilder builderForResult = new StringBuilder();
        int index = 0;

        for (String res : arr) {
            String[] parts = res.split(" ");
            if  (names[index].equals(parts[0])) {
                result += Integer.parseInt(parts[1]);
                builderForResult.append(names[index]).append(result).toString();
                index++;
                System.out.println(builderForResult);
            }
        }

    }
}

