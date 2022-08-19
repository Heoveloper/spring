package com.kh.pdata.svc;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class ApiExplorerImpl implements ApiExplorer {
    private static final String serviceKey = "rWGHLB92x6jWBuF2Vi7vGCyIOqWUR5A7otp6POH1Nh9ZrU5Z%2FPg0ebD8OFZz2%2Fvx5XFDgH7o%2BKaOoIG9IVDYNw%3D%3D";

    @Override
    public Response apiCall() {
        String xmlStr = "";
        Response res = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6310000/ulsanrestaurant/getulsanrestaurantList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 크기(기본20)*/
            urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*시작 페이지(기본1)*/
            urlBuilder.append("&" + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode("중구", "UTF-8")); /*시군구*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
            xmlStr = sb.toString();

            //xml to java Object
            XmlMapper xmlMapper = new XmlMapper();
            res = xmlMapper.readValue(xmlStr, Response.class);
            System.out.println(res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Response apiCall(CompanyReq companyReq) {
        String xmlStr = "";
        Response res = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6310000/ulsanrestaurant/getulsanrestaurantList"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);                                               /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode(companyReq.getPerPage(), "UTF-8"));     /*페이지 크기(기본20)*/
            urlBuilder.append("&" + URLEncoder.encode("pageIndex", "UTF-8") + "=" + URLEncoder.encode(companyReq.getPageIndex(), "UTF-8")); /*시작 페이지(기본1)*/
            urlBuilder.append("&" + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(companyReq.getCity(), "UTF-8"));           /*시군구*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
            xmlStr = sb.toString();

            //xml to java Object
            XmlMapper xmlMapper = new XmlMapper();
            res = xmlMapper.readValue(xmlStr, Response.class);
            System.out.println(res);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}