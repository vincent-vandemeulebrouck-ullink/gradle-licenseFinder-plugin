package com.ullink.gradle.utils

import com.google.common.base.Charsets
import com.google.common.io.CharStreams

import java.nio.charset.Charset


class StreamUtil {
    public static final String PREFIX = "entrypoint"
    public static final String SUFFIX = ".rb"

    public static File streamToFile(InputStream inputStream) throws IOException {
        final File temp = File.createTempFile(PREFIX, SUFFIX)
        String content = CharStreams.toString(new InputStreamReader(
                inputStream, Charsets.UTF_8));
        temp.write(content)
        return temp
    }
}
