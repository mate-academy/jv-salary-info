package core.basesyntax;

import java.util.ArrayList;
import java.util.Comparator;

public class SalaryDataSorter {

    public void sortData(ArrayList<DailySalaryData> data){
        Comparator <DailySalaryData> comparator = Comparator.comparing(DailySalaryData::getYear)
                .thenComparing(DailySalaryData::getMonth)
                .thenComparing(DailySalaryData::getDay);
        data.sort(comparator);
    }
}
