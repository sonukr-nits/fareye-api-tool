package com.sonu.fareyeapi.module;

import com.sonu.fareyeapi.module.dto.APIKeyDTO;
import com.sonu.fareyeapi.module.dto.GETRequestDTO;
import com.sonu.fareyeapi.module.dto.POSTRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/apiKey")
    public ResponseEntity<String> getApiKey(@RequestBody APIKeyDTO apiKeyDTO){
        try {
            String apiKeyBody = apiService.getAPIKEY(apiKeyDTO);
            return new ResponseEntity<>(apiKeyBody,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> handlePostRequest(@RequestBody POSTRequestDTO postRequestDTO){
        try {
            String responseString = "";
            return new ResponseEntity<>(responseString,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity<String> handleGetRequest(@RequestBody GETRequestDTO getRequestDTO){
        try {

            String responseString=apiService.handleGETRequest(getRequestDTO);
            return new ResponseEntity<>(responseString,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
