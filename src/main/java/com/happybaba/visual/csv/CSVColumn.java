package com.happybaba.visual.csv;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CSVColumn {
    private String name ;
    private List<Double> data ;
    public CSVColumn(String name, List<Double> data) {
        this.name = name;
        this.data = data;
    }
    public CSVColumn(String name) {
       this(name,new ArrayList<>());
    }

    public CSVColumn() {
    }

    /*default-package */void pushDataToTail(double d){
        data.add(d) ;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Double> getData() {
        return data;
    }
    public void setData(List<Double> data) {
        this.data = data;
    }

    /**
     * 计算指定一列的差分，返回一个包含所有差分的新列。<br/>
     * 例如名为test的序列 1,2,3,4,5,7，将返回一个test_diff的新列，包含序列0,1,1,1,1,2
     * @param column 指定列
     * @return 新的列
     */
    public static CSVColumn difference(CSVColumn column){
        CSVColumn diff = new CSVColumn(column.getName()+"_diff");
        diff.pushDataToTail(0);
        if(column.getData().size() > 0 ){
            double preValue = column.getData().get(0) ;
            for(int i = 1 ; i < column.getData().size() ; ++i){
                double currValue = column.getData().get(i) ;
                diff.pushDataToTail(currValue - preValue);
                preValue = currValue ;
            }
        }
        return diff;
    }

    /**
     * 计算指定列的和
     * @param column 指定的列
     * @return 和
     */
    public static double accumulate(CSVColumn column){
        double accumulate = 0 ;
        for(int i = 0 ; i < column.getData().size() ; ++i){
            accumulate += column.getData().get(i);
        }
        return accumulate ;
    }

    /**
     * 计算指定列的平均值
     * @param column 指定列
     * @return 平均值
     */
    public static double average(CSVColumn column){
        if(column.getData().size() == 0) return 0 ;
        return accumulate(column) / column.getData().size() ;
    }

    /**
     * 找出指定列中的最小值和最大值
     * @param column 指定列
     * @return 最小值(下标为0)和最大值(下标为1)
     * @exception InvalidParameterException 若该列没有任何数据
     */
    public static double[] minAndMax(CSVColumn column){
        if(column.getData().size() == 0) throw new InvalidParameterException("no elements of this column :"+column.getName());
        double max = column.getData().get(0) ;
        double min = column.getData().get(0) ;
        for(int i = 1 ; i < column.getData().size() ; ++i){
            double currValue = column.getData().get(i) ;
            if(currValue > max) max = currValue ;
            if(currValue < min) min = currValue ;
        }
        double[] ret = new double[2] ;
        ret[0] = min ; ret[1] = max;
        return ret ;
    }

    /**
     * 求指定列的极差
     * @param column 指定列
     * @return 极差
     * @exception InvalidParameterException 若该列没有任何数据
     */
    public static double range(CSVColumn column){
        double[] minAndMax = minAndMax(column) ;
        return minAndMax[1] - minAndMax[0] ;
    }

    @Override
    public String toString() {
        return "CSVColumn{" +
                "name='" + name + '\'' +
                ", data=" + data +
                "}\n";
    }

}
