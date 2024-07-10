package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.FileData;

import java.io.File;

public interface FileService {
    String getOutputFilePath();
    void saveFile(int groupId, File file, String type);
    void saveFile(File file);

    FileData getFileData(String filePath);
}
