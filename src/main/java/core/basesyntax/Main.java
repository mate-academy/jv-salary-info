package core.basesyntax;

public class Main {
    private static final String[] sampleNames = {"John", "Andrew", "Kate"};
    private static final String[] scriptArray = {
            "25.04.2019 John 60 50",
            "25.04.2019 Andrew 3 200",
            "25.04.2019 Kate 10 100",

            "26.04.2019 Andrew 3 200",
            "26.04.2019 Kate 9 100",

            "27.04.2019 John 7 100",
            "27.04.2019 Kate 3 80",
            "27.04.2019 Andrew 8 100"
    };
    private static final String[] dates = {
            "24.04.2019",
            "25.04.2019",
            "26.04.2019",
            "27.04.2019"
    };
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        //salaryInfo.getSalaryInfo(null, null, "01.04.2019", "30.04.2019");
        salaryInfo.getSalaryInfo(sampleNames, scriptArray,"01.04.2019", "30.04.2019" );
    }
}
