package com.pomzwj;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.pomzwj.constant.TemplateFileConstants;
import com.pomzwj.domain.SegmentData;
import com.pomzwj.domain.TableData;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class DatabaseExportApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        List<TableData> subData = new ArrayList<>();
        List<Map<String, Object>> subDataMap = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            Map<String, Object> table = new HashMap<>(1);
            List<RowRenderData> rows = new ArrayList<>();
            //获取表头
            List<String> headerList = new ArrayList<>();
            headerList.add("姓名");
            headerList.add("年龄");
            headerList.add("性别");
            headerList.add("住址");
            headerList.add("电话");
            RowRenderData raw0 = Rows.of(headerList.toArray(new String[headerList.size()])).textColor("FFFFFF").bgColor("4471c4").center().create();
            rows.add(raw0);
            for (int i = 0; i < 10; i++) {
                List<String> dataBody = new ArrayList<>();
                dataBody.add("张三" + i + " " + j);
                dataBody.add("21" + i);
                dataBody.add("男" + i);
                dataBody.add("中国北京" + i);
                dataBody.add("11231_" + i);
                rows.add(Rows.of(dataBody.toArray(new String[dataBody.size()])).center().create());
            }
//            TableData segmentData = new TableData();
//            segmentData.setTable(Tables.create(rows.toArray(new RowRenderData[rows.size()])));
//            subData.add(segmentData);

            Map<String, Object> tableMap = new HashMap<>(1);
            tableMap.put("table",Tables.create(rows.toArray(new RowRenderData[rows.size()])));
            subDataMap.add(tableMap);
        }

        Map<String, Object> table = new HashMap<>(1);
//        table.put("tables", Includes.ofLocal("D:\\test\\sub_model.docx").setRenderModel(subData).create());
        table.put("tables", Includes.ofLocal("D:\\test\\sub_model.docx").setRenderModel(subDataMap).create());

//        XWPFTemplate template = XWPFTemplate.compile("D:\\test\\import.docx").render(
//                new HashMap<String, Object>(){{
//                    put("table",  Tables.create(rows.toArray(new RowRenderData[rows.size()])));
//                }});
        XWPFTemplate template = XWPFTemplate.compile(new File("D:\\test\\import.docx")).render(table);
        template.writeAndClose(new FileOutputStream("D:\\test\\output.docx"));
    }

}
