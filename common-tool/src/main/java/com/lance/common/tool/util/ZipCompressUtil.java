package com.lance.common.tool.util;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Utilities of compressing files into .zip.
 *
 * @author Lance
 * @date 2016/11/16 11:38
 */
public class ZipCompressUtil implements ICompressUtils {

    /**
     * Compress files.
     *
     * @param files      files expected to be compressed
     * @param outputPath output file path
     * @throws IOException
     */
    @Override
    public void compress(File[] files, String outputPath) throws IOException {
        BufferedInputStream in = null;
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(outputPath));

            Queue<Pair<File, String>> queue = new LinkedList<>();
            addFiles2Queue(queue, files, "");
            while (!queue.isEmpty()) {
                Pair<File, String> pair = queue.poll();
                File file = pair.getKey();

                if (!file.exists()) continue;

                if (file.isFile()) {
                    in = new BufferedInputStream(new FileInputStream(file));
                    if (pair.getValue().isEmpty()) {
                        out.putNextEntry(new ZipEntry(file.getName()));
                    } else {
                        out.putNextEntry(new ZipEntry(pair.getValue() + File.separator + file.getName()));
                    }

                    int temp = -1;
                    while ((temp = in.read()) != -1) {
                        out.write(temp);
                    }
                    FileUtils.close(in);
                } else if (file.isDirectory()) {
                    if (pair.getValue().isEmpty()) {
                        addFiles2Queue(queue, file.listFiles(), file.getName());
                    } else {
                        addFiles2Queue(queue, file.listFiles(), pair.getValue() + File.separator + file.getName());
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to compress files", e);
        } finally {
            FileUtils.closeQuietly(in);
            FileUtils.closeQuietly(out);
        }
    }

    private static void addFiles2Queue(Queue<Pair<File, String>> queue, File[] files, String outputPath) {
        for (File file : files) {
            queue.add(new Pair<File, String>(file, outputPath));
        }
    }


    /**
     * Uncompress files.
     *
     * @param zipFilename compressed filename
     * @param outputPath  directory where  files will stay
     * @throws IOException
     */
    @Override
    public void uncompress(File zipFilename, String outputPath) throws IOException {
        mkdir(outputPath);

        ZipInputStream in = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipFilename);
            in = new ZipInputStream(new FileInputStream(zipFilename));

            ZipEntry entry = null;
            while ((entry = in.getNextEntry()) != null) {
                String newFilename = outputPath + File.separator + entry.getName();
                mkParentDir(newFilename);
                FileUtils.copyAndClose(zipFile.getInputStream(entry), new FileOutputStream(newFilename));
            }
        } catch (IOException e) {
            throw new IOException("Failed to uncompress file", e);
        } finally {
            FileUtils.closeQuietly(in);
            FileUtils.closeQuietly(zipFile);
        }
    }

    private static boolean mkdir(String dirName) {
        if (null == dirName || dirName.isEmpty()) return false;

        File dir = new File(dirName);
        return !dir.exists() && dir.mkdirs();
    }

    private static boolean mkParentDir(String filename) {
        File parentDir = new File(filename).getParentFile();
        return !parentDir.exists() && parentDir.mkdirs();
    }
}
