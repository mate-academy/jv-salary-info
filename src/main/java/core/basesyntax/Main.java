package core.basesyntax;

public class Main {
    static final String[] DATA = new String[]{
            "26.04.2019 John 4 50",
            "05.04.2019 Andrew 3 200",
            "10.04.2019 John 7 100",
            "22.04.2019 Kate 9 100",
            "25.06.2019 John 11 50",
            "26.04.2019 Andrew 3 150",
            "13.02.2019 John 7 100",
            "26.04.2019 Kate 9 100"
    };

    static final String[] NAMES = new String[]{"John", "Andrew", "Kate"};
    static final String DATE_FROM = "01.04.2019";
    static final String DATE_TO = "30.04.2019";

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(NAMES, DATA, DATE_FROM, DATE_TO));
    }
}
