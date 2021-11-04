package repchain.inter.cooperation.middleware.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author lhc
 * @version 1.0
 * @className MyHttpClient
 * @date 2021年11月04日 9:55 上午
 * @description 描述
 */
public class MyHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(MyHttpClient.class);

    public static String uploadFile(InputStream is, String url, String fileName) {
        String result = null;
        //文件URL，此处取豆瓣上的一个图片
        try {
            //创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            /*绑定文件参数，传入文件流和contenttype，此处也可以继续添加其他formdata参数*/
            builder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA, fileName);
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            //执行提交
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (is != null) {
                is.close();
            }
            if (responseEntity != null) {
                //将响应的内容转换成字符串
                result = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }
}
