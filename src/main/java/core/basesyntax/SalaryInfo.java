package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        //StringBuilder builder = new StringBuilder();
        /*создать for each о массиву data пройтись по String dateFrom, String dateTo
        рамки для считания зарпалаты dateFrom, dateTo == масив дата и то и то
        split в массив
        */
        LocalDate dateFromLocalTime = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToLocalTime = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate currentEmployeeDate;
        String[] employee = new String[data.length];
        String getName = "";
        int hoursWork = 0;
        int money = 0;
        int getMoney = 0;
        for (String current : data) {
            employee = current.split("");
            //employee[0] =
            employee[1] = getName;
            employee[2] = String.valueOf(hoursWork);
            employee[3] = String.valueOf(money);
        }
        for (int i = 0; i < employee.length; i++) {
            if(getName.contains(employee[i])){
               getMoney = hoursWork * money;
            }
        }



    }
}
