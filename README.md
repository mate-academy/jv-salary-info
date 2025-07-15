# jv-salary-info

Implement method `getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)`. 
It should calculate the salary for employees. As input, you receive two arrays and two dates in String format.
 - Date represents limits that you should meet while calculating salary for employees (inclusively).
 - The first array (`names`) contains the names of employees you should calculate salary for.
 - The second array (`data`) contains info about their working hour during the particular day and income per hour. 

The method should return info in the following format:
```
   Report for period #date_1# - #date_2#
   #Name of employee# - #money earned#
```
Here is a sample of input data.
Dates format:
```
   date from = `01.04.2019 `
   date to = `30.04.2019` 
``` 

names:
```
   John
   Andrew
   Kate
```

data:
```
  26.04.2019 John 4 50
  05.04.2019 Andrew 3 200
  10.04.2019 John 7 100
  22.04.2019 Kate 9 100
  25.06.2019 John 11 50
  26.04.2019 Andrew 3 150
  13.02.2019 John 7 100
  26.04.2019 Kate 9 100
```

Method execution result:
```
   Report for period 01.04.2019  - 30.04.2019  
   John - 900
   Andrew - 1050
   Kate - 1800
```
### [Try to avoid these common mistakes while solving task](./checklist.md)
