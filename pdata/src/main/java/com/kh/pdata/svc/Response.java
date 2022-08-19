package com.kh.pdata.svc;

import lombok.Data;

@Data
public class Response {
    private Header header;

    @Data
    static class Header {
        private String resultCode;
        private String resultMsg;
    }

    private Body body;

    @Data
    static class Body {
        private String pageIndex;
        private String numOfRows;
        private String pageNo;
        private String totalCount;
        private java.util.List<List> data;

        @Data
        static class List {
            private String company;
            private double lat;
            private double lng;
            private String foodType;
            private String city;
            private String address;
            private String phoneNumber;
            private String mainMenu;
        }
    }
}
