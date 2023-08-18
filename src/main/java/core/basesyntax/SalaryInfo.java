package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private Date date;
    private int workingHour;
    private int pricePerHour;
    private String currentName;
    private int salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String answer = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        try {

            for (String name:names) {
                for (int i = 0; i < data.length; i++) {
                    String[] words = data[i].split(" ");
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(words[0]);
                    currentName = words[1];
                    workingHour = Integer.parseInt(words[2]);
                    pricePerHour = Integer.parseInt(words[3]);
                    if ((new SimpleDateFormat("dd.MM.yyyy").parse(words[0])).compareTo(
                            new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom)) >= 0
                            && ((new SimpleDateFormat("dd.MM.yyyy").parse(words[0])).compareTo(
                            new SimpleDateFormat("dd.MM.yyyy").parse(dateTo)) <= 0
                            && currentName.equals(name))) {
                        salary += workingHour * pricePerHour;
                    }
                }
                answer += name + " - " + salary + System.lineSeparator();
                salary = 0;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
        } catch (ParseException e) {
            System.out.println("Illegal argument");
        }

        return answer.substring(0,answer.length() - 2);

    }
}
