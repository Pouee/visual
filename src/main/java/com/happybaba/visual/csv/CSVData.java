package com.happybaba.visual.csv;


import java.io.*;
import java.util.*;

public class CSVData {

    private List<CSVColumn> columns ;
    private int invalidateCount = 0 ;
    private static final String NEW_LINE = "\r\n" ;

    /**
     * 构造一个CSVData对象
     * @param in    输入流(该流不会自动关闭)
     * @param validateColumn    检验列(数据为0或者1)，传空则表示不检验无效数据
     * @throws IOException      IO错误
     */
    public CSVData(InputStream in , String validateColumn) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in)) ;
        // 处理 header
        String line = br.readLine();
        int validateIndex = -1 ;
        columns = new ArrayList<>() ;
        String[] heads = line.split(",") ;
        for(int i = 0 ; i < heads.length ; ++i){
            if(validateColumn != null && validateColumn.trim().equals(heads[i].trim())) validateIndex = i ;
            columns.add(new CSVColumn(heads[i].trim()));
        }
        // 处理数据
        while((line = br.readLine()) != null){
            if( "".equals(line) ) continue; // 空行
            String[] data = line.split(",") ;
            // 无效数据
            if(validateIndex != -1 && Integer.valueOf(data[validateIndex].trim()) == 0)  {
                ++invalidateCount;
                continue;
            }
            for(int i = 0 ; i < heads.length ;++i){
                columns.get(i).pushDataToTail(Double.valueOf(data[i].trim()));
            }
        }
    }

    /**
     * 构造一个CSVData对象,不过滤无效数据
     * @param in    输入流(该流不会自动关闭)
     * @throws IOException  IO错误
     */
    public CSVData(InputStream in) throws IOException {
        this(in,null);
    }

    /**
     * 保存该CSVData对象到指定定的输出流
     * @param out   输出流
     * @throws IOException  IO错误
     */
    public void save(OutputStream out) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out)) ;
        StringBuilder sb = new StringBuilder() ;
        // 处理标签头
        for(int i = 0 ; i < columns.size() ;++i){
            sb.append(columns.get(i).getName());
            if(i != columns.size() - 1) sb.append(", ");
        }
        sb.append(NEW_LINE);
        bw.write(sb.toString());

        CSVColumn column = columns.get(0) ;
        // 处理数据
        for(int i = 0 ; i < column.getData().size() ;++i){
            sb = new StringBuilder() ;
            for(int j = 0 ; j < columns.size() ; ++j){
                sb.append(columns.get(j).getData()
                        .get(i)) ;
                if(j != columns.size() - 1) sb.append(",  ");
            }
            sb.append(NEW_LINE);
            bw.write(sb.toString());
        }
        bw.flush();
    }

    /**
     * 根据名称找指定列
     * @param name 名称
     * @return 指定列
     * @exception NoSuchElementException 若该名称所指定的列不存在
     */
    public CSVColumn findByName(String name){
        for(int i = 0 ; i < columns.size() ; ++i){
            if(name.trim().equals(columns.get(i).getName().trim())) return columns.get(i) ;
        }
        throw new NoSuchElementException("no such column of this csv : " + name);
    }

    public List<CSVColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CSVColumn> columns) {
        this.columns = columns;
    }

    public int getInvalidateCount() {
        return invalidateCount;
    }

    public void setInvalidateCount(int invalidateCount) {
        this.invalidateCount = invalidateCount;
    }

    @Override
    public String toString() {
        return "CSVData{" +
                "columns=" + columns +
                ", invalidateCount=" + invalidateCount +
                '}';
    }

    /**
     * 添加新的列
     * @param column 新列
     */
    public void addColumn(CSVColumn column){
        columns.add(column);
    }
    public CSVData() {
        this.columns = new ArrayList<>();
    }
}

