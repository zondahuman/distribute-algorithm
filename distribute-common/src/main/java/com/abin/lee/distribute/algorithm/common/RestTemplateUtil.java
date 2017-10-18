package com.abin.lee.distribute.algorithm.common;

import com.google.common.collect.Lists;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by abin on 2017/4/25 10:41.
 * circular-reference
 * com.abin.lee.circular.common.util
 */
public class RestTemplateUtil {

    private static RestTemplate restTemplate = null;

    static {
        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(createRequestFactory());
        restTemplate.setMessageConverters(createMessageConverters());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }
    private RestTemplateUtil(){}

    private static class RestTemplateUtilHolder{
        private static RestTemplateUtil instance = new RestTemplateUtil();
    }

    public static RestTemplateUtil getInstance(){
        return RestTemplateUtilHolder.instance;
    }


    private static SimpleClientHttpRequestFactory createRequestFactory(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(60000);
        requestFactory.setConnectTimeout(20000);
        return requestFactory;
    }

    private static List<HttpMessageConverter<?>> createMessageConverters(){
        // 添加转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        return messageConverters;
    }



    public static String httpPost(String httpUrl, Map<String, String> request, Map<String, String> headers){
        String result = "";
        HttpHeaders headersParams = new HttpHeaders();
        for(Iterator<Map.Entry<String, String>> iterator=headers.entrySet().iterator();iterator.hasNext();){
            Map.Entry<String, String> entry = iterator.next();
            headersParams.put(entry.getKey(), Lists.newArrayList(entry.getValue()));
        }
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>();
        for(Iterator<Map.Entry<String, String>> iterator=request.entrySet().iterator();iterator.hasNext();){
            Map.Entry<String, String> entry = iterator.next();
            requestParams.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(requestParams, headersParams);
        ResponseEntity<String> response = restTemplate.postForEntity(httpUrl, param, String.class);
        result = response.getBody();
        return result;
    }


    public static String httpPost(String httpUrl, Map<String, String> request){
        String result = "";
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>();
        for(Iterator<Map.Entry<String, String>> iterator=request.entrySet().iterator();iterator.hasNext();){
            Map.Entry<String, String> entry = iterator.next();
            requestParams.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<MultiValueMap<String, String>>(requestParams, null);
        ResponseEntity<String> response = restTemplate.postForEntity(httpUrl, param, String.class);
        result = response.getBody();
        return result;
    }


    public static String httpPost(String httpUrl, String json, Map<String, String> headers){
        HttpHeaders headersParams = new HttpHeaders();
        headersParams.setContentType(MediaType.APPLICATION_JSON);
        for(Iterator<Map.Entry<String, String>> iterator=headers.entrySet().iterator();iterator.hasNext();){
            Map.Entry<String, String> entry = iterator.next();
            headersParams.put(entry.getKey(), Lists.newArrayList(entry.getValue()));
        }
        HttpEntity<String> formEntity = new HttpEntity<String>(json, headersParams);
        String result = restTemplate.postForObject(httpUrl, formEntity, String.class);
        return result;
    }


    public static String httpPost(String httpUrl, String json){
        HttpHeaders headersParams = new HttpHeaders();
        headersParams.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> formEntity = new HttpEntity<String>(json, headersParams);
        String result = restTemplate.postForObject(httpUrl, formEntity, String.class);
        return result;
    }



    public static String httpGet(String httpUrl) {
        return restTemplate.getForObject(httpUrl, String.class, new String[]{});
    }

    public static String httpGetById(String httpUrl,String id) {
        return restTemplate.getForObject(httpUrl+"?id={id}", String.class, id);
    }







}
