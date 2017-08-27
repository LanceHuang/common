package com.lance.common.tool.util;

import java.io.File;
import java.io.IOException;

/**
 * Utilities of compressing files
 *
 * @author Lance
 * @date 2016/11/16 11:35
 * @see ZipCompressUtil
 */
public interface ICompressUtils {

    /**
     * Compress files
     *
     * @param files      files expected to be compressed
     * @param outputPath output file path
     * @throws IOException
     */
    public void compress(File[] files, String outputPath) throws IOException;

    /**
     * uncompress files
     *
     * @param zipFilename compressed filename
     * @param outputPath  directory where  files will stay
     * @throws IOException
     */
    public void uncompress(File zipFilename, String outputPath) throws IOException;
}
