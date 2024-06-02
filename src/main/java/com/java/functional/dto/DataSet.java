package com.java.functional.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataSet {
    private List<Map<String, String>> rows = new ArrayList<>(); //It will contain row of a database table with column name as map key and column value as map value.

    public DataSet() {
    }

    public DataSet(List<Map<String, String>> row) {
        this.rows = row;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void addRow(Map<String, String> row) {
        this.rows.add(row);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String header = rows.stream()
                .limit(1)
                .map(Map::keySet)
                .map(set -> String.join(",", set))
                .collect(Collectors.joining(","));
        sb.append(header).append("\n");

        String content = rows.stream()
                .map(Map::values)
                .map(strings -> String.join(",",strings))
                .collect(Collectors.joining("\n"));
        sb.append(content);
        return sb.toString();
    }
}
