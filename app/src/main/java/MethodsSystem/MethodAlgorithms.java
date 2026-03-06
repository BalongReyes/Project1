
package MethodsSystem;

import DatabaseSystem.DataTable.DataTableType;
import java.util.Objects;
import DatabaseSystem.DataTable.DataTableOrder;
import DatabaseSystem.DataTable.DataTable;

public class MethodAlgorithms{

    public static int initBinarySearch(DataTable[] array, int dataIndex, Object target){
        if(array == null || array.length == 0) return -1;
        DataTableType type = array[0].getDataType(dataIndex);
        if(type == DataTableType.TYPE_NULL) return -1;
        switch(type){
            case TYPE_STRING -> {if(!(target instanceof String)) return -1;}
            case TYPE_INTEGER -> {if(!(target instanceof Integer)) return -1;}
        }
        quickSort(array, 0, array.length - 1, dataIndex, DataTableOrder.Desc, type);
        return binarySearch(array, dataIndex, type, 0, array.length - 1, target);
    }
    
    public static int binarySearch(DataTable[] array, int dataIndex, DataTableType type, int low, int high, Object target){
        if(low > high) return -1;
        int mid = (int)Math.floor((low + high) / 2);
        switch(type){
            case TYPE_STRING -> {
                String value = (String)array[mid].getData(dataIndex);
                if(value == null) value = "";
                if(value.equals((String)target)) return mid;
                if(((String)target).compareTo(value) < 0){
                    return binarySearch(array, dataIndex, type, low, mid - 1, target);
                }
            }
            case TYPE_INTEGER -> {
                Integer value = (Integer)array[mid].getData(dataIndex);
                if(value == null) value = -1;
                if(Objects.equals(value, (Integer)target)) return mid;
                if((Integer)target > value){
                    return binarySearch(array, dataIndex, type, low, mid - 1, target);
                }
            }
        }
        return binarySearch(array, dataIndex, type, mid + 1, high, target);
    }
    
// ===========================================================================================================
    
    public static void initQuickSort(DataTable[] array, int dataIndex, DataTableOrder order){
        if(array == null || array.length == 0) return;
        DataTableType type = array[0].getDataType(dataIndex);
        if(type == DataTableType.TYPE_NULL) return;
        quickSort(array, 0, array.length - 1, dataIndex, order, type);
    }
    
    public static void quickSort(DataTable[] array, int start, int end, int dataIndex, DataTableOrder order, DataTableType type){
        if(start >= end) return;
        int index = start;
        switch(type){
            case TYPE_STRING -> {
                String pivotValueString = (String)array[end].getData(dataIndex);
                if(pivotValueString == null) pivotValueString = "";
                for(int i = start; i < end; i++){
                    String value = (String)array[i].getData(dataIndex);
                    if(value == null) value = "";
                    switch(order){
                        case Desc -> {if(value.compareTo(pivotValueString) >= 0) continue;}
                        case Asc -> {if(value.compareTo(pivotValueString) < 0) continue;}
                    }
                    swap(array, i, index);
                    index++;
                }
            }
            case TYPE_INTEGER -> {
                Integer pivotValueInteger = (Integer)array[end].getData(dataIndex);
                if(pivotValueInteger == null) pivotValueInteger = -1;
                for(int i = start; i < end; i++){
                    Integer value = (Integer)array[i].getData(dataIndex);
                    if(value == null) value = -1;
                    switch(order){
                        case Desc -> {if(value < pivotValueInteger) continue;}
                        case Asc -> {if(value >= pivotValueInteger) continue;}
                    }
                    swap(array, i, index);
                    index++;
                }
            }
        }
        swap(array, index, end);
        quickSort(array, start, index - 1, dataIndex, order, type);
        quickSort(array, index + 1, end, dataIndex, order, type);
    }
    
    private static void swap(DataTable[] array, int a, int b){
        DataTable temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
}
