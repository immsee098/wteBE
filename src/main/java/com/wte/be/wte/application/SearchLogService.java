package com.wte.be.wte.application;

import com.wte.be.wte.dtos.*;
import com.wte.be.wte.entity.*;
import com.wte.be.wte.repository.*;
import com.wte.be.wte.util.*;
import jakarta.persistence.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.nio.charset.*;
import java.util.*;

@Service
@Slf4j
public class SearchLogService {
    @Autowired
    private SearchLogEntityRepository searchLogEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<Message> insertLog(SearchLogDTO param) {
        Message message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            SearchLogEntity entity = SearchLogEntity.builder()
                    .search_content(param.getSearch_content())
                    .result(param.getResult())
                    .build();
            searchLogEntityRepository.save(entity);
            message = CreateMsg.makeMsg(StatusEnum.OK, "성공", new HashMap());
        } catch (Exception e) {
            HashMap failMap = new HashMap();
            failMap.put("rsn", e.getMessage());
            message = CreateMsg.makeMsg(StatusEnum.BAD_REQUEST, "실패", failMap);
//             e.printStackTrace();
        }

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
