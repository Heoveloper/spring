package com.kh.pdata.svc;

import lombok.Data;

@Data
public class CompanyReq {
    private String perPage;     //페이지 크기 (기본 20)
    private String pageIndex;   //시작 페이지 (기본 1)
    private String serviceKey;  //인증키
    private String city;        //시군구
}
