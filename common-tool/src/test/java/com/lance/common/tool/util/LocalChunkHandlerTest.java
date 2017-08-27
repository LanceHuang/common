package com.lance.common.tool.util;

import org.junit.Test;

import java.io.*;

/**
 * @author Lance
 * @date 2016/10/26 20:22
 */
public class LocalChunkHandlerTest {

    private String filename = "octopus.txt";
    private int chunks = 2;
    private int chunk = 2;

    private IChunkHandler chunkHandler = new LocalChunkHandler();

    @Test
    public void testUploadChunk() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\文档.txt");
            System.out.println(chunkHandler.uploadChunk(filename, chunks, chunk, inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void testMergeChunks() throws Exception {
        System.out.println(chunkHandler.mergeChunks(filename, chunks));
    }

    @Test
    public void testClearChunks() {
        System.out.println(chunkHandler.clearChunks(filename, chunks));
    }

    @Test
    public void testClearChunk() {
        System.out.println(chunkHandler.clearChunk(filename, chunks, chunk));
    }

    @Test
    public void testClearMerge() throws Exception {
        System.out.println(chunkHandler.clearMerge(filename, chunks));
    }

    @Test
    public void testClearAll() throws Exception {
        System.out.println(chunkHandler.clearAll(filename, chunks));
    }

    @Test
    public void testExistChunk() {
        System.out.println(chunkHandler.existChunk(filename, chunks, chunk));
    }

    @Test
    public void testExistMerge() throws Exception {
        System.out.println(chunkHandler.existMerge(filename, chunks));
    }

    @Test
    public void testValidateChunks() {
        System.out.println(chunkHandler.validateChunks(filename, chunks));
    }

    @Test
    public void testDownloadChunk() {
        String outputPath = "E:\\test\\newFile.txt";

        try {
            FileUtils.copyAndClose(chunkHandler.downloadChunk(filename, chunks, chunk),
                    new BufferedOutputStream(new FileOutputStream(outputPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownloadMerge() {
        String outputPath = "E:\\test\\newFile.txt";

        try {
            FileUtils.copyAndClose(chunkHandler.downloadMerge(filename, chunks),
                    new BufferedOutputStream(new FileOutputStream(outputPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetChunkName() {
        System.out.println(chunkHandler.getChunkName(filename, chunks, chunk));
    }

    @Test
    public void testGetMergeName() {
        System.out.println(chunkHandler.getMergeName(filename, chunks));
    }

    @Test
    public void testGetAbsoluteChunkName() {
        System.out.println(chunkHandler.getAbsoluteChunkName(filename, chunks, chunk));
    }

    @Test
    public void testGetAbsoluteMergeName() {
        System.out.println(chunkHandler.getAbsoluteMergeName(filename, chunks));
    }


    @Test
    public void test() {
        System.out.println(File.separator);
    }
}