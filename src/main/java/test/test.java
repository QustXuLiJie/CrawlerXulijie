package test;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @description:
 * @author: xulijie
 * @create time: 16:47 2017/3/30
 */
public class test {
    public static void main(String[] args) {
        HttpClient httpCLient = new DefaultHttpClient();
        // 创建get请求实例
        HttpGet httpget = new HttpGet("http://www.baidu.com");

        System.out.println("executing request " + httpget.getURI());

        try {
            // 客户端执行get请求 返回响应实体
            HttpResponse response = httpCLient.execute(httpget);

            // 服务器响应状态行
            System.out.println(response.getStatusLine());

            Header[] heads = response.getAllHeaders();
            // 打印所有响应头
            for (Header h : heads) {
                System.out.println(h.getName() + ":" + h.getValue());
            }

            // 获取响应消息实体
            HttpEntity entity = response.getEntity();

            System.out.println("------------------------------------");

            if (entity != null) {

                //响应内容
                System.out.println(EntityUtils.toString(entity));

                System.out.println("----------------------------------------");
                // 响应内容长度
                System.out.println("响应内容长度：" + entity.getContentLength());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpCLient.getConnectionManager().shutdown();
        }
    }
}
