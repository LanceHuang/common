package com.lance.common.tool;

import java.io.File;
import java.io.IOException;

/**
 * Utilities of compressing files
 *
 * @author Lance
 * @see ZipCompressUtil
 */
public interface ICompressUtils {

    /**
     * Compress files
     *
     * @param files      files expected to be compressed
     * @param outputPath output file path
     */
    void compress(File[] files, String outputPath) throws IOException;

    /**
     * uncompress files
     *
     * @param zipFilename compressed filename
     * @param outputPath  directory where  files will stay
     */
    void uncompress(File zipFilename, String outputPath) throws IOException;
}
