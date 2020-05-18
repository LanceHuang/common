package com.lance.common.tool.util;

import java.io.InputStream;

/**
 * Handling fileupload by chunk, it can be used for upload file chunk into local path or hdfs. Also it can separately save file chunk or make them one.
 *
 * @author Lance
 * @see LocalChunkHandler
 */
public interface IChunkHandler {

    /**
     * Upload file chunk.
     *
     * @param filename    Filename string
     * @param chunks      Count of chunk
     * @param chunk       Chunk number
     * @param inputStream Input stream of file
     * @return {@code true} succeed, or {@code false}
     */
    boolean uploadChunk(String filename, int chunks, int chunk, InputStream inputStream) throws Exception;

    /**
     * Merge all file chunk.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return {@code true} succeed, or {@code false}
     */
    boolean mergeChunks(String filename, int chunks) throws Exception;

    /**
     * Delete all file chunk.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return number of deleted file chunks
     */
    int clearChunks(String filename, int chunks);

    /**
     * Delete file chunk.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @param chunk    Chunk number
     * @return {@code true} succeed, or {@code false}
     */
    boolean clearChunk(String filename, int chunks, int chunk);

    /**
     * Delete merge file.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return {@code true} succeed, or {@code false}
     */
    boolean clearMerge(String filename, int chunks);

    /**
     * Delete file chunks and merge file.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return {@code true} succeed, or {@code false}
     */
    boolean clearAll(String filename, int chunks);

    /**
     * Test whether file chunk exists.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @param chunk    Chunk number
     * @return {@code true} exist file chunk, or {@code false}
     */
    boolean existChunk(String filename, int chunks, int chunk);

    /**
     * Test whether merge file exists.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return {@code true} exist merge file, or {@code false}
     */
    boolean existMerge(String filename, int chunks);


    /**
     * Valid that file chunks and merge file are all valid.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return {@code true} file chunks and merge file are valid, or {@code false}
     */
    boolean validateChunks(String filename, int chunks);

    /**
     * Download file chunk.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @param chunk    Chunk number
     * @return Input stream of file chunk
     */
    InputStream downloadChunk(String filename, int chunks, int chunk) throws Exception;

    /**
     * Download merge file.
     *
     * @param filename Filename string
     * @param chunks   Count of chunk
     * @return input stream of merge file
     */
    InputStream downloadMerge(String filename, int chunks) throws Exception;

    /**
     * Get name of file chunk.
     *
     * @param name   Filename string
     * @param chunks Count of chunk
     * @param chunk  Chunk number
     * @return name of file chunk
     */
    String getChunkName(String name, int chunks, int chunk);

    /**
     * Get name of merge file.
     *
     * @param name   Filename string
     * @param chunks Count of chunk
     * @return name of merge file
     */
    String getMergeName(String name, int chunks);

    /**
     * Get path of file chunk.
     *
     * @param name   Filename string
     * @param chunks Count of chunk
     * @param chunk  Chunk number
     * @return path of file chunk
     */
    String getAbsoluteChunkName(String name, int chunks, int chunk);

    /**
     * Get path of merge file.
     *
     * @param name   Filename string
     * @param chunks Count of chunk
     * @return path of merge file
     */
    String getAbsoluteMergeName(String name, int chunks);

}
