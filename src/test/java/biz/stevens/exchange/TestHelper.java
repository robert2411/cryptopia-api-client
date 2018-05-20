package biz.stevens.exchange;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.net.URL;

public class TestHelper {
    @SneakyThrows
    public static String getResourceAsString(@NonNull String resource){
        if(!resource.startsWith("/")){
            resource = "/" + resource;
        }
        return IOUtils.toString(TestHelper.class.getResourceAsStream(resource), "UTF-8");
    }
}
