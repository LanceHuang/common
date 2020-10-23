package com.lance.common.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Methods for copy stream, rename file and so on.
 *
 * @author Lance
 */
public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    private static final int SIZE_BUFF = 4096;

    private FileUtils() {
    }

    /**
     * Copy input stream into output stream, but not close them.
     *
     * @param in  input stream
     * @param out output stream
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.assertNotNull(in, "InputStream cannot be null");
        Assert.assertNotNull(out, "OutputStream cannot be null");

        try {
            int totalRead = 0;
            byte[] buff = new byte[SIZE_BUFF];
            int count;
            while ((count = in.read(buff)) > 0) {
                out.write(buff, 0, count);
                totalRead += count;
            }
            out.flush();
            return totalRead;
        } catch (IOException e) {
            throw new IOException("Failed to copy stream", e);
        }
    }

    /**
     * Copy input stream into output stream, but not close them, finally close them.
     *
     * @param in  input stream
     * @param out output stream
     */
    public static int copyAndClose(InputStream in, OutputStream out) throws IOException {
        Assert.assertNotNull(in, "InputStream cannot be null");
        Assert.assertNotNull(out, "OutputStream cannot be null");

        try {
            return copy(in, out);
        } catch (IOException e) {
            throw new IOException("Failed to copy stream", e);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
        }
    }

    /**
     * Rename file to new name.
     *
     * @param file    expected file
     * @param newName new name
     * @return {@code true} successfully rename；{@code false} fail
     */
    public static boolean renameTo(File file, String newName) {
        Assert.assertNotNull(file, "File cannot be null");
        Assert.assertEquals(existFile(file), true, "Invalid file");
        Assert.assertNotEmpty(newName, "Invalid new filename");

        return file.renameTo(new File(file.getParent() + File.separator + newName));
    }

    /**
     * Close object extends Closable.
     *
     * @param obj Object extends Closable
     * @throws IOException Fail to close object
     */
    public static void close(Closeable obj) throws IOException {
        if (null != obj) {
            try {
                obj.close();
            } catch (IOException e) {
                throw new IOException("Fail to close object", e);
            }
        }
    }

    /**
     * Close object extends Closable quietly.
     *
     * @param obj Object extends Closable
     */
    public static void closeQuietly(Closeable obj) {
        if (null != obj) {
            try {
                obj.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Delete file if it exists.
     *
     * @param filename expected filename
     * @return {@code true} successfully delete file or {@code false} not
     */
    public static boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile() && file.delete();
    }

    /**
     * Tests whether file exists.
     *
     * @param filename expected filename
     * @return {@code true} exist file or {@code false} not
     */
    public static boolean existFile(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile();
    }

    /**
     * Tests whether file exists.
     *
     * @param file expected file
     * @return {@code true} exist file or {@code false} not
     */
    public static boolean existFile(File file) {
        return null != file && file.exists() && file.isFile();
    }
}
