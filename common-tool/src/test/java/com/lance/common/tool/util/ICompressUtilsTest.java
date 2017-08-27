package com.lance.common.tool.util;

import org.junit.Test;

import java.io.File;

/**
 * @author Lance
 * @date 2016/11/16 11:43
 */
public class ICompressUtilsTest {


    private ICompressUtils compressUtils = new ZipCompressUtil();

    @Test
    public void testCompress() throws Exception {
        compressUtils.compress(new File[]{new File("E:\\test\\zip\\dev1.txt")}, "E:\\test\\zip\\singleFile.zip");
        compressUtils.compress(new File[]{new File("E:\\test\\zip\\dev2.txt"), new File("E:\\test\\zip\\dev2.txt")}, "E:\\test\\zip\\multiFile.zip");
        compressUtils.compress(new File[]{new File("E:\\test\\excel")}, "E:\\test\\zip\\dir.zip");
        compressUtils.compress(new File[]{new File("E:\\test\\zip\\dev1.txt"), new File("E:\\test\\excel")}, "E:\\test\\zip\\fileAndDir.zip");
        System.out.println("Finished");
    }

    @Test
    public void testUncompress() throws Exception {
//        compressUtils.uncompress(new File("E:\\test\\zip\\singleFile.zip"), "E:\\test\\zip");
//        compressUtils.uncompress(new File("E:\\test\\zip\\multiFile.zip"), "E:\\test\\zip");
//        compressUtils.uncompress(new File("E:\\test\\zip\\dir.zip"), "E:\\test\\zip");
        compressUtils.uncompress(new File("E:\\test\\zip\\fileAndDir.zip"), "E:\\test\\zip");
        System.out.println("Finished");
    }
}