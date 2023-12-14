package cn.crazyurus.plantuml;

import org.springframework.util.DigestUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;

import java.io.ByteArrayInputStream;

public class Utilities {
    private static String getObjectName(String content) {
        return DigestUtils.md5DigestAsHex(content.getBytes()) + ".svg";
    }

    public static String UploadFile(String content) throws ClientException {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String bucketName = "plantuml-plugin-image";
        String objectName = getObjectName(content);

        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        ossClient.shutdown();

        return "https://plantuml-plugin-image.oss-cn-hangzhou.aliyuncs.com/" + objectName;
    }
}
