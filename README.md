# jv-salary-info

Implement method `getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)`. It should calculate 
salary for employees. As input you receive two arrays and two dates in String format. 
 - Date represents limits that you should meet while calculation salary for employees (inclusively).
 - First array (`names`) contains names of employees you should calculate salary for.
 - Second array (`data`) contains info about their working hour during particular day and income per hour. 
Method should return info in the following format:
```
   Report for period #date_1# - #date_2#
   #Name of employee# - #money earned#
```
Here is sample of input data.
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
### [Try to avoid these common mistakes while solving task](https://mate-academy.github.io/jv-program-common-mistakes/java-core/exceptions/salary-info)

Реализуйте метод `getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)`. Он должен рассчитать

Заработная плата для сотрудников. В качестве ввода вы получаете два массива и две даты в формате String.

- Дата представляет собой пределы, которые вы должны соблюдать при расчете заработной платы для сотрудников (включительно).

- Первый массив ("имена") содержит имена сотрудников, для которых вы должны рассчитать заработную плату.

- Второй массив ("данные") содержит информацию об их рабочем часе в течение определенного дня и доходах в час.

Метод должен возвращать информацию в следующем формате:

```

Отчет за период #date_1# - #date_2#

#Имя сотрудника# - #заработанные деньги#

```

Вот образец входных данных.

Формат дат:

```

Дата от = `01.04.2019 `

Дата до = `30.04.2019`

```

Имена:

```

Сортир

Эндрю

Кейт

```

Данные:

```

26.04.2019 Джон 4 50

05.04.2019 Андрей 3 200

10.04.2019 Джон 7 100

22.04.2019 Кейт 9 100

25.06.2019 Джон 11 50

26.04.2019 Андрей 3 150

13.02.2019 Джон 7 100

26.04.2019 Кейт 9 100

```

Результат выполнения метода:

```

Отчет за период 01.04.2019 - 30.04.2019

Джон - 900

Андрей - 1050

Кейт - 1800

```