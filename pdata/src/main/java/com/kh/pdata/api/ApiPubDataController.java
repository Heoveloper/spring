package com.kh.pdata.api;

import com.kh.pdata.svc.ApiExplorer;
import com.kh.pdata.svc.CompanyReq;
import com.kh.pdata.svc.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/pub")
@RequiredArgsConstructor
public class ApiPubDataController {

    private final ApiExplorer apiExplorer;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getJsonData(@RequestParam("city") String city) {

        CompanyReq companyReq = new CompanyReq();

        companyReq.setPerPage("0");     //페이지 크기 (기본 20)
        companyReq.setPageIndex("1");   //시작 페이지 (기본 1)
        companyReq.setCity(city);       //시군구

        Response res = apiExplorer.apiCall(companyReq);
        log.info("res={}", res);
        return res;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Response getXMLData() {
        Response res = apiExplorer.apiCall();
        log.info("res={}", res);
        return res;
    }
}
