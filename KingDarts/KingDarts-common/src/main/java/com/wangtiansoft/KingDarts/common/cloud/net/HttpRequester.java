package com.wangtiansoft.KingDarts.common.cloud.net;

import com.wangtiansoft.KingDarts.common.cloud.HttpRespons;
import com.wangtiansoft.KingDarts.common.encrypt.EncryptionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;


/**
 * Created by Tibers on 17/2/16.
 */
public class HttpRequester {
    private String defaultContentEncoding;

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     */
    public HttpRequester() {
        this.defaultContentEncoding = Charset.defaultCharset().name();
    }


    /**
     * @param @param  urlString
     * @param @return
     * @param @throws IOException
     *
     * @return HttpRespons
     *
     * @throws
     * @Title:sendPost
     * @Description:TODO
     */
    public HttpRespons sendPost(String urlString) throws IOException {
        return this.send(urlString, "POST", null, null);
    }

    /**
     * @param @param  urlString
     * @param @param  params
     * @param @return
     * @param @throws IOException
     *
     * @return HttpRespons
     *
     * @throws
     * @Title:sendPost
     * @Description:TODO
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException {
        return this.send(urlString, "POST", params, null);
    }

    /**
     * @param @param  urlString
     * @param @param  params
     * @param @param  propertys
     * @param @return
     * @param @throws IOException
     *
     * @return HttpRespons
     *
     * @throws
     * @Title:sendPost
     * @Description:TODO
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys) throws IOException {
        return this.send(urlString, "POST", params, propertys);
    }

    /**
     * @param @param  urlString
     * @param @param  method
     * @param @param  parameters
     * @param @param  propertys
     * @param @return
     * @param @throws IOException
     *
     * @return HttpRespons
     *
     * @throws
     * @Title:send
     * @Description:TODO
     */

    private HttpRespons send(String urlString, String method, Map<String, String> parameters, Map<String, String> propertys) throws IOException {
        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        if (propertys != null)
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null) {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }

        return this.makeContent(urlString, urlConnection);
    }

    /**
     * @param @param  urlString
     * @param @param  urlConnection
     * @param @return
     * @param @throws IOException
     *
     * @return HttpRespons
     *
     * @throws
     * @Title:makeContent
     * @Description:TODO
     */
    private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
        HttpRespons httpResponser = new HttpRespons();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            httpResponser.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                httpResponser.contentCollection.add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            String ecod = urlConnection.getContentEncoding();
            if (ecod == null)
                ecod = this.defaultContentEncoding;

            httpResponser.urlString = urlString;

            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponser.file = urlConnection.getURL().getFile();
            httpResponser.host = urlConnection.getURL().getHost();
            httpResponser.path = urlConnection.getURL().getPath();
            httpResponser.port = urlConnection.getURL().getPort();
            httpResponser.protocol = urlConnection.getURL().getProtocol();
            httpResponser.query = urlConnection.getURL().getQuery();
            httpResponser.ref = urlConnection.getURL().getRef();
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();

            httpResponser.content = new String(temp.toString().getBytes(), ecod);
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();

            return httpResponser;
        } catch (IOException e) {
            throw e;
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    /**
     * @param @return
     *
     * @return String
     *
     * @throws
     * @Title:getDefaultContentEncoding
     * @Description:TODO
     */
    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }

    /**
     * @param @param defaultContentEncoding
     *
     * @return void
     *
     * @throws
     * @Title:setDefaultContentEncoding
     * @Description:TODO
     */
    public void setDefaultContentEncoding(String defaultContentEncoding) {
        this.defaultContentEncoding = defaultContentEncoding;
    }

    private String getKey(TreeMap<String, String> map, String key) {
        StringBuilder sb = new StringBuilder();
        for (String m : map.keySet()) {
            sb.append(map.get(m)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(key);
        System.out.println(sb.toString());
        return EncryptionUtil.getMD5Str(sb.toString());
    }
}
