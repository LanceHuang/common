package com.lance.common.tool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Management of the chunks of local file, index from 0.
 * <ul>
 * <li>chunk format: {@code localPath}filename.ckN</li>
 * <li>merge format: {@code localPath}filename.merge</li>
 * </ul>
 * ckN can be 0, 1, 2, ...
 *
 * @author Lance
 * @date 2016/10/26 20:19
 */
public class LocalChunkHandler implements IChunkHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String localPath = this.getTmpPath();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean uploadChunk(String filename, int chunks, int chunk, InputStream inputStream) throws Exception {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        Assert.assertGe(chunk, 0, "chunk must be greater than -1: %d", chunk);
        Assert.assertGt(chunks, chunk, "chunks must be greater than chunk: chunks=%d, chunk=%d", chunks, chunk);
        this.checkInputStream(inputStream);

        BufferedOutputStream buffOut = null;
        try {
            String chunkPath = this.getAbsoluteChunkName(filename, chunks, chunk);
            buffOut = new BufferedOutputStream(new FileOutputStream(chunkPath));
            if (-1 != FileUtils.copy(inputStream, buffOut)) {
                logger.info("Success upload chunk to " + chunkPath);
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            FileUtils.closeQuietly(inputStream);
            FileUtils.closeQuietly(buffOut);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mergeChunks(String filename, int chunks) throws Exception {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);

        if (!this.validateChunks(filename, chunks)) return false;

        String mergeFile = this.getAbsoluteMergeName(filename, chunks);
        BufferedOutputStream buffOut = null;
        try {
            buffOut = new BufferedOutputStream(new FileOutputStream(mergeFile));
            for (int chunk = 0; chunk < chunks; chunk++) {
                InputStream in = this.downloadChunk(filename, chunks, chunk);
                FileUtils.copy(in, buffOut);
                FileUtils.close(in);
            }

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            FileUtils.closeQuietly(buffOut);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int clearChunks(String filename, int chunks) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);

        int totalDel = 0;
        for (int chunk = 0; chunk < chunks; chunk++) {
            if (this.clearChunk(filename, chunks, chunk)) totalDel++;
        }
        return totalDel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clearChunk(String filename, int chunks, int chunk) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        Assert.assertGe(chunk, 0, "chunk must be greater than -1: %d", chunk);
        Assert.assertGt(chunks, chunk, "chunks must be greater than chunk: chunks=%d, chunk=%d", chunks, chunk);
        return FileUtils.deleteFile(this.getAbsoluteChunkName(filename, chunks, chunk));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clearMerge(String filename, int chunks) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        return FileUtils.deleteFile(this.getAbsoluteMergeName(filename, chunks));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clearAll(String filename, int chunks) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);

        this.clearChunks(filename, chunks);
        return this.clearMerge(filename, chunks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existChunk(String filename, int chunks, int chunk) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        Assert.assertGe(chunk, 0, "chunk must be greater than -1: %d", chunk);
        Assert.assertGt(chunks, chunk, "chunks must be greater than chunk: chunks=%d, chunk=%d", chunks, chunk);
        return FileUtils.existFile(this.getAbsoluteChunkName(filename, chunks, chunk));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existMerge(String filename, int chunks) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        return FileUtils.existFile(this.getAbsoluteMergeName(filename, chunks));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateChunks(String filename, int chunks) {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);

        for (int chunk = 0; chunk < chunks; chunk++) {
            if (!this.existChunk(filename, chunks, chunk)) return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream downloadChunk(String filename, int chunks, int chunk) throws Exception {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        Assert.assertGe(chunk, 0, "chunk must be greater than -1: %d", chunk);
        Assert.assertGt(chunks, chunk, "chunks must be greater than chunk: chunks=%d, chunk=%d", chunks, chunk);

        if (!this.existChunk(filename, chunks, chunk))
            throw new Exception("Not exist chunk NO." + chunk + " of file:" + filename);

        String chunkPath = this.getAbsoluteChunkName(filename, chunks, chunk);
        return new FileInputStream(chunkPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream downloadMerge(String filename, int chunks) throws Exception {
        Assert.assertNotNullOrEmpty(filename, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);

        if (!this.existMerge(filename, chunks))
            throw new Exception("Not exist chunk of file:" + filename);

        String mergePath = this.getAbsoluteMergeName(filename, chunks);
        return new FileInputStream(mergePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChunkName(String name, int chunks, int chunk) {
        return name + ".ck" + chunk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMergeName(String name, int chunks) {
        return name + ".merge";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAbsoluteChunkName(String name, int chunks, int chunk) {
        Assert.assertNotNullOrEmpty(name, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        Assert.assertGe(chunk, 0, "chunk must be greater than -1: %d", chunk);
        Assert.assertGt(chunks, chunk, "chunks must be greater than chunk: chunks=%d, chunk=%d", chunks, chunk);
        return localPath + File.separator + this.getChunkName(name, chunks, chunk);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAbsoluteMergeName(String name, int chunks) {
        Assert.assertNotNullOrEmpty(name, "Filename cannot be null or empty");
        Assert.assertGt(chunks, 0, "chunks must be greater than zero: %d", chunks);
        return localPath + File.separator + this.getMergeName(name, chunks);
    }

    protected String getTmpPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void resetLocalPath() {
        this.localPath = this.getTmpPath();
    }

    protected void checkInputStream(InputStream inputStream) throws IOException {
        if (null == inputStream || inputStream.available() < 1)
            throw new IllegalArgumentException("inputStream cannot be null or unavailable");
    }

}
