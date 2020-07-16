package com.android.framekit.utils

import java.io.Closeable
import java.io.File
import java.text.DecimalFormat


object FileUtil {
    /**
     * Create a dir file.
     * @param fullPath the dir full string path.
     * @param isNew    delete the dir if the dir is exist when true set.
     */
    fun createFileDir(fullPath: String, isNew: Boolean): Boolean {
        if (isPathInvalid(fullPath)) {
            return false
        }
        val fileDir = File(fullPath)
        return if (fileDir.exists()) {
            if (fileDir.isDirectory) {
                if (isNew) {
                    fileDir.delete()
                    fileDir.mkdirs()
                } else {
                    true
                }
            } else {
                false
            }
        } else {
            fileDir.mkdirs()
        }
    }

    /**
     * Create a file.
     * @param fullPath the file full string path.
     * @param isNew    delete the file if the file is exist when true set.
     */
    fun createFile(fullPath: String, isNew: Boolean): Boolean {
        if (isPathInvalid(fullPath)) {
            return false
        }
        val file = File(fullPath)
        return if (file.exists()) {
            if (file.isFile) {
                if (isNew) {
                    file.delete()
                    file.createNewFile()
                } else {
                    true
                }
            } else {
                false
            }
        } else {
            file.createNewFile()
        }

    }

    /**
     * Write a string to file.
     * @param fullPath the file full string path.
     * @param isAppend append data to the end of file when set true.
     * @param data     a string data.
     */
    fun writeFile(fullPath: String, isAppend: Boolean, data: String) {
        FileUtil.writeFile(fullPath, isAppend, data.toByteArray())
    }

    /**
     * Write a string to file.[kotlin.io]
     * @param fullPath the file full string path.
     * @param isAppend append data to the end of file when set true.
     * @param data     a ByteArray data.
     */
    fun writeFile(fullPath: String, isAppend: Boolean, data: ByteArray) {
        val file = File(fullPath)
        if (isAppend) {
            file.appendBytes(data)
        } else {
            file.writeBytes(data)
        }
    }

    /**
     * Get text by path.[kotlin.io]
     */
    fun readFileString(fullPath: String): String {
        val file = File(fullPath)
        return file.readText()
    }

    /**
     * Get ByteArray by path.[kotlin.io]
     */
    fun readFileByte(fullPath: String): ByteArray {
        val file = File(fullPath)
        return file.readBytes()
    }

    /**
     * The '\r', '\n', '\t', 'tab' are invalid as file path. [File.isInvalid]
     */
    fun isPathInvalid(filePath: String): Boolean {
        filePath.forEach { c ->
            if (c.isWhitespace()) {
                return true
            }
        }
        return false
    }

    /**
     * @param io file or net io
     */
    fun close(io: Closeable?) {
        io?.let {
            try {
                it.close()
            } catch (e: Exception) {

            }
        }
    }

    fun isFileExit(fullPath: String): Boolean {
        if (isPathInvalid(fullPath)) {
            return false
        }
        val file = File(fullPath)
        return file.exists()
    }

    fun isFileComparableExit(fullPath: String, fileSize: Long): Boolean {
        if (!isFileExit(fullPath)) {
            return false
        }
        val file = File(fullPath)
        val destSize = file.length()
        return destSize == fileSize
    }

    fun getFileNameByPath(path: String): String {
        if (path.isNullOrEmpty()) {
            return ""
        }
        val index = path.lastIndexOf("/")
        return if (path.length > index) {
            path.substring(index + 1)
        } else {
            ""
        }

    }

    fun getChildFileContent(dirPath: String, extension: String): List<String> {
        val contents: ArrayList<String> = ArrayList<String>()
        val fileTree: FileTreeWalk = File(dirPath).walk()
        fileTree.maxDepth(2)
                .filter { it.isFile }
                .filter { it.extension in listOf(extension) }
                .forEach {
                    contents.add(readFileString(it.absolutePath))
                }
        return contents
    }

    fun getFileFormatSize(size: Float): String {
        val df = DecimalFormat("#.00")
        var fileSizeString = ""
        val wrongSize = "0B"
        if (size == 0f) {
            return wrongSize
        }
        fileSizeString = when {
            size < 1024 -> {
                df.format(size.toDouble()).toString() + "B"
            }
            size < 1048576 -> {
                df.format(size.toDouble() / 1024).toString() + "KB"
            }
            size < 1073741824 -> {
                df.format(size.toDouble() / 1048576).toString() + "MB"
            }
            else -> {
                df.format(size.toDouble() / 1073741824).toString() + "GB"
            }
        }
        return fileSizeString
    }

    enum class FormatEnum(
            var TYPE: String,
            var ICON: Int,
            private vararg val formats: String
    ) {

        //图片格式
        IMG("img", 0, "jpg", "jpeg", "gif", "png", "bmp", "tiff"),

        //文本格式
        TXT("txt", 0, "txt"),

        //文档格式
        WORD("word", 0, "docx", "dotx", "doc", "dot", "pagers"),

        //电子表格
        EXCEL("excel", 0, "xls", "xlsx", "xlt", "xltx"),

        //ppt
        PPT("ppt", 0, "ppt", "pptx"),

        //pdf
        PDF("pdf", 0, "pdf"),

        //音频格式
        MP3("mp3", 0, "mp3", "wav", "wma"),

        //视频格式
        VIDEO(
                "video",
                0,
                "avi",
                "flv",
                "mpg",
                "mpeg",
                "mp4",
                "3gp",
                "mov",
                "rmvb",
                "mkv"
        ),

        //网页格式
        HTML("html", 0, "html"),

        //cad
//        CAD("cad", R.mipmap.icon_fil, "dwg", "dxf", "dwt"),

        //ps
//        PS("ps", R.mipmap.train_pdf_icon, "psd", "pdd"),

        //max
//        MAX3D("3DMax", R.mipmap.train_pdf_icon, "max"),

        //压缩包
        ZIP("zip", 0, "zip", "jar", "rar", "7z"),

        //未知格式
        UNKNOWN("unknown", 0);

        companion object {
            /**
             * 通过文件类型获取对应枚举
             *
             * @param extension 文件扩展名
             * @return 文件对应的枚举信息，如果没有，返回未知
             */
            fun getFormat(extension: String?): FormatEnum {

                for (format in FormatEnum.values()) {
                    for (extend in format.formats) {
                        if (extend.equals(extension, ignoreCase = true)) {
                            return format
                        }
                    }
                }
                return UNKNOWN
            }
        }

    }

    fun getFormatName(fileName: String?): String? {
        //去掉首尾的空格
        return try {
            var fileName = fileName
            fileName = fileName?.trim()
            fileName?.substring(fileName.lastIndexOf(".") + 1)
        } catch (e: java.lang.Exception) {
            ""
        }

    }

}