package com.snack.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
/**
 * 用于创建.xlsx
 */
@Component
public class ExcelUtil2 {

    public void createSheet(SXSSFWorkbook workbook, List list, List<JSONObject> columns, String sheetName) {
        // 产生工作表对象
    	Sheet sheet = workbook.createSheet(sheetName);
        createHead(workbook, sheet, columns);
        try {
            createData(workbook, sheet, columns, list);
            //列宽度自适应-影响性能
            /*for (int columnNum = 0; columnNum <= list.size(); columnNum++) {
                int columnWidth = sheet.getColumnWidth(columnNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    Row currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }

                    if (currentRow.getCell(columnNum) != null) {
                        Cell currentCell = currentRow.getCell(columnNum);
                        if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                sheet.setColumnWidth(columnNum, columnWidth * 256);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Row createHead(SXSSFWorkbook workbook, Sheet sheet, List<JSONObject> columns) {
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
//		//设置单元格显示颜色
//		cs.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//		//样式的填充类型
//		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //居中对齐
        style.setAlignment(CellStyle.ALIGN_CENTER);
        //设置边框
//		style.setBorderTop((short) 1);
//		style.setBorderBottom((short) 1);
//		style.setBorderLeft((short) 1);
//		style.setBorderRight((short) 1);
        style.setFont(font);

        Row row = sheet.createRow(0);
        int index = 0;
        for (JSONObject json : columns) {
            sheet.setColumnWidth(index, json.getInteger("columnWidth"));
            Cell cell = row.createCell(index++);
            cell.setCellStyle(style);
            cell.setCellValue(json.getString("title"));
        }
        return row;
    }

    private void createData(SXSSFWorkbook workbook, Sheet sheet, List<JSONObject> columns, List list) throws Exception {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        int rowIndex = 1;

        List<Method> methods = new ArrayList<>();
        if (list.size() > 0) {
            Object obj = list.get(0);
            methods = columns.stream().map(column -> getGetter(column, obj)).collect(Collectors.toList());
        }

        int mergerValueIndex = getMergeValueIndex(columns);

        Row mergeStartRow = null;

        for (Object value : list) {

            Row row = sheet.createRow(rowIndex++);
            int index = 0;

            for (Method method : methods) {
                String defaultValue = "";
                try {
                    defaultValue = columns.get(index).getString("defaultValue");
                } catch (Exception e) {

                }

                Cell cell = row.createCell(index++);

                Object cellValue = method.invoke(value);
                if (cellValue != null) {
                    if (cellValue instanceof Date) {
                        cell.setCellValue((Date) cellValue);
                    } else {
                        cell.setCellValue(cellValue.toString());
                    }
                } else {
                    //值为空时设置初始值为0
                    cell.setCellValue(defaultValue);
                }
                cell.setCellStyle(style);
            }

            if (mergerValueIndex != -1) {
                mergerRow(sheet, mergeStartRow, row, mergerValueIndex, columns.size());
                if (isNewRow(mergeStartRow, row, mergerValueIndex)) {
                    mergeStartRow = row;
                }
            }
        }
        Row mergeEndRow = sheet.createRow(rowIndex++);
        if (mergerValueIndex != -1) {
            mergerRow(sheet, mergeStartRow, mergeEndRow, mergerValueIndex, columns.size());
        }
    }

    private void mergerRow(Sheet sheet, Row mergeStartRow, Row mergeEndRow, Integer mergerValueIndex, Integer columnsSize) {
        if (needMerge(mergeStartRow, mergeEndRow, mergerValueIndex)) {
            for (int mergeColumnIndex = 0; mergeColumnIndex < columnsSize; mergeColumnIndex++) {
                Row last = sheet.getRow(mergeEndRow.getRowNum() - 1);
                if (!isNewRow(mergeStartRow, last, mergeColumnIndex)) {
                    sheet.addMergedRegion(
                            new CellRangeAddress(mergeStartRow.getRowNum(), mergeEndRow.getRowNum() - 1, (short) mergeColumnIndex, (short) mergeColumnIndex));
                } else if (isNewRow(mergeStartRow, last, mergeColumnIndex) && (mergeColumnIndex == 9 || mergeColumnIndex == 12)) {
                    mergeColumnIndex += 2;
                }
            }
        }
    }

    // 判断合并
    private boolean needMerge(Row mergeStartRow, Row currentRow, int mergeValueIndex) {
        boolean newRow = isNewRow(mergeStartRow, currentRow, mergeValueIndex);
        if (newRow) {
            if (mergeStartRow != null && currentRow.getRowNum() - mergeStartRow.getRowNum() > 1) {
                return true;
            }
        }
        return false;
    }

    // 把需要合并的列取出
    private List<Integer> getMergeColumnIndex(List<JSONObject> columns) {
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            boolean isMergeColumn = columns.get(i).getBoolean("mergeColumn");
            if (isMergeColumn) {
                indexs.add(i);
            }
        }
        return indexs;
    }

    // 申明哪一列值合并
    private int getMergeValueIndex(List<JSONObject> columns) {
        int mergeColumn = -1;
        for (int i = 0; i < columns.size(); i++) {
            boolean isMergeColumn = columns.get(i).getBoolean("mergeValue");
            if (isMergeColumn) {
                return i;
            }
        }
        return mergeColumn;
    }

    private boolean isNewRow(Row preRow, Row currentRow, int mergeValueIndex) {
        if (preRow == null) {
            return true;
        }
        Cell cell = currentRow.getCell(mergeValueIndex);
        return cell == null || !cell.getStringCellValue().equals(preRow.getCell(mergeValueIndex).getStringCellValue());
    }

    private Method getGetter(JSONObject column, Object obj) {
        String columnName = column.getString("name");
        columnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
        try {
            return obj.getClass().getMethod("get" + columnName);
        } catch (NoSuchMethodException e) {
           System.out.println(e);
        }
        return null;
    }

    public JSONObject defaultGetColumn(String title, String name, int columnWidth,  boolean mergeColumn, boolean mergeValue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("name", name);
        jsonObject.put("columnWidth", columnWidth);
        jsonObject.put("mergeValue", mergeValue);
        jsonObject.put("mergeColumn", mergeColumn);
        return jsonObject;
    }
}
