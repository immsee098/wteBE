package com.wte.be.wte.application;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.wte.be.wte.dtos.*;
import com.wte.be.wte.util.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;


@Service
@Slf4j
public class GetLocationService {

    @Value("${naver.api.client.id}")
    private String apiId;

    @Value("${naver.api.client.password}")
    private String apipwd;

    public ResponseEntity<Message> getLocationInfo(String query, String sort) throws JsonProcessingException {
        ArrayList itemList = new ArrayList();

        // TODO 비동기로 3회를 불러서 랜덤 택1은 너무 비효율적이겠죠..?
        // TODO 제외건은 어떻게 할지..
        // TODO while이나 stream 방식으로 바꿔도 OK..일단 개수가 적어서

        // 상암 DMC 검색
        itemList = usePlaceApi("상암 DMC " + query, sort);
        // DMC 검색
        if (itemList.size() < 1) {
            itemList = usePlaceApi("DMC " + query, sort);
        }
        // 상암 검색
        if (itemList.size() < 1) {
            itemList = usePlaceApi("상암 " + query, sort);
        }
        // 그래도 없으면 없음return

        return randomSelect(itemList);
    }

    public ArrayList usePlaceApi(String query, String sort) {
        String nApiUrl = "https://openapi.naver.com/v1/search/local.json?query=" +
                query + "&display=5&start=1&sort=" + sort;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        header.set("X-Naver-Client-Id", apiId);
        header.set("X-Naver-Client-Secret", apipwd);

        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(nApiUrl).build();
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

        ObjectMapper mapper = new ObjectMapper();
        Map map = resultMap.getBody();
        ArrayList itemList = (ArrayList) map.get("items");

        return itemList;
    }

    public ResponseEntity<Message> randomSelect(ArrayList placeList) {
        Message message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (placeList.size() < 1) {
            HashMap failMap = new HashMap();
            failMap.put("rsn", "검색 결과 없음");
            message = CreateMsg.makeMsg(StatusEnum.BAD_REQUEST, "실패", failMap);
        } else {
            double random=Math.random();
            int num = (int)Math.round(random * (placeList.size()-1));

            ObjectMapper mapper = new ObjectMapper();
            ApiResponseDTO adto = mapper.convertValue(placeList.get(num), ApiResponseDTO.class);
            message = CreateMsg.makeMsg(StatusEnum.OK, "성공", adto);
        }

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

    }
}
