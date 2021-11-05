package repchain.inter.cooperation.middleware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author lhc
 * @version 1.0
 * @className FileUploadTets
 * @date 2021年11月04日 9:51 上午
 * @description 描述
 */
public class FileUploadTest {

    @Test
    public void upload() {
        //文件URL，此处取豆瓣上的一个图片
        String fileUrl = "https://img1.doubanio.com/view/photo/l/public/p2537149328.webp";
        try {
            //提取到文件名
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            //转换成文件流
            InputStream is = new URL(fileUrl).openStream();

            //接收文件的服务器地址
            String uploadURL = "http://localhost:8003/fileupload";

            //创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uploadURL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            /*绑定文件参数，传入文件流和contenttype，此处也可以继续添加其他formdata参数*/
            builder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA, fileName);
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            //执行提交
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                //将响应的内容转换成字符串
                String result = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                //此处根据服务器返回的参数转换，这里返回的是JSON格式
                JSONObject output = JSON.parseObject(result);
                JSONArray body = output.getJSONArray("body");
                String resUrl = body.get(0) + "";
                System.out.println(resUrl);
            }
            if (is != null) {
                is.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void grpcFileTransform(){

    }
}
