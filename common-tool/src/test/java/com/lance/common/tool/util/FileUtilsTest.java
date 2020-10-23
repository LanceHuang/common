package com.lance.common.tool.util;

import com.lance.common.tool.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Lance
 * @date 2016/10/27 11:02
 */
public class FileUtilsTest {

    private String inputFile = "C:\\Users\\Administrator\\Desktop\\doc.txt";
    private String outputFile = "E:\\test\\newFile.txt";

    @Test
    public void testCopy() throws Exception {
        System.out.println(FileUtils.copy(new FileInputStream(inputFile), new FileOutputStream(outputFile)));
    }

    @Test
    public void testCopyAndClose() throws Exception {
        System.out.println(FileUtils.copyAndClose(new FileInputStream(inputFile), new FileOutputStream(outputFile)));
    }

    @Test
    public void testRenameTo() throws Exception {
        System.out.println(FileUtils.renameTo(new File("C:\\Users\\Administrator\\Desktop\\MessageEntity.java"), "MessageEntity2.java"));
    }
}