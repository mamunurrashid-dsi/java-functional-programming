package com.java.functional;

import com.java.functional.dataHandler.DataHandler;
import com.java.functional.dataHandler.FinanceAccountDataHandler;
import com.java.functional.dto.DataSet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Initializing data manually.
        //This data will come from database.
        DataSet dataSet = new DataSet();
        Map<String, String> map = new HashMap<>();
        map.put("ACCT_NUM", "123");
        map.put("CHECK_NUM", "456");
        map.put("CHECK_AMT", "500.00");
        map.put("CHECK_DATE","2024-01-01");
        dataSet.addRow(map);

        map = new HashMap<>();
        map.put("ACCT_NUM", "123");
        map.put("CHECK_NUM", "456");
        map.put("CHECK_AMT", "50000");
        map.put("CHECK_DATE","2024-01-01");
        dataSet.addRow(map);

        map = new HashMap<>();
        map.put("ACCT_NUM", "1234");
        map.put("CHECK_NUM", "789");
        map.put("CHECK_AMT", "500.00");
        map.put("CHECK_DATE","2024-01-02");
        dataSet.addRow(map);
        System.out.println("-------------imperative-------------");
        //Printing dataset in imperative way starts.
        //Printing Header of table.
        Map<String, String> row = dataSet.getRows().getFirst();
        int count = 0;
        for (String columName : row.keySet()) {
            System.out.print(columName);
            count++;
            if (count != row.keySet().size()) {
                System.out.print(",");
            }
        }
        System.out.println();
        //Printing content of table.
        count = 0;
        List<Map<String, String>> rows = dataSet.getRows();
        for (Map<String, String> tableRow : rows) {
            for (String columnValue : tableRow.values()) {
                System.out.print(columnValue);
                count++;
                if (count != row.values().size()) {
                    System.out.print(",");
                } else {
                    count = 0;
                    System.out.println();
                }
            }
        }
        //Printing dataset in imperative way ends.
        System.out.println("-------------declarative-------------");
        //Printing dataset in declarative way using streams and lambdas starts.
        //Printing header of table.
        rows.stream()
                .limit(1)
                .map(Map::keySet)
                .map(set -> String.join(",", set))
                .forEach(System.out::println);
        //Printing content of table
        rows.stream()
                .map(Map::values)
                .map(strings -> String.join(",",strings))
                .forEach(System.out::println);
        System.out.println("--------------------------");
        //Printing dataset in declarative way using streams and lambdas ends.
        /*
        Using declarative way for above code is more concise and readable.
        In declarative way we do not need to write code for how to do it, we just need to write code for what to do.
         */

        //Now calling handleData method to map data from dataset to specific DTO in declarative way.
        Properties properties = new Properties();
        try(FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(inputStream);
            DataHandler handler = (DataHandler) Class.forName(properties.getProperty("handler.classpath")).getDeclaredConstructor().newInstance();
            handler.handleData(dataSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}