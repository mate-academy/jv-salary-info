package core.basesyntax;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        String[] names = new String[]{"John", "Andrew", "Kate"};
        String[] dates = new String[]{"26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"};
        String datesFrom = "01.04.2019";
        String datesTo = "30.04.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(names, dates, datesFrom, datesTo));
    }
}
