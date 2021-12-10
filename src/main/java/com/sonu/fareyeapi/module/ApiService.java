package com.sonu.fareyeapi.module;

import com.sonu.fareyeapi.module.dto.APIKeyDTO;
import com.sonu.fareyeapi.module.dto.GETRequestDTO;
import com.sonu.fareyeapi.module.dto.POSTRequestDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    private static final String LOGIN_END_POINT = "/app/authentication";
    private static final String API_KEY_END_POINT= "/v1/app/rest/user/api_key";

    private static String COOKIES="";
    private  RestTemplate restTemplate = new RestTemplate();
    /**
     *
     * @param apiKeyDTO
     * @return
     */
    public String getAPIKEY(APIKeyDTO apiKeyDTO){
        if(apiKeyDTO ==null
                || !StringUtils.hasText(apiKeyDTO.getServerURL())
                || !StringUtils.hasText(apiKeyDTO.getUserName())
                || !StringUtils.hasText(apiKeyDTO.getPassword())){
            throw new RuntimeException("Invalid Input");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> maps= new LinkedMultiValueMap<>();
        maps.add("j_username",apiKeyDTO.getUserName());
        maps.add("j_password",apiKeyDTO.getPassword());
        maps.add("remember-me", "false");
        maps.add("submit","Login");
        String cookies= loginAndGetCookies(apiKeyDTO.getServerURL(),maps,headers);

        headers = new HttpHeaders();
        headers.set("Cookie",cookies);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiKeyDTO.getServerURL()+API_KEY_END_POINT, HttpMethod.GET, new HttpEntity<>(headers), String.class);
            return response.getBody();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("couldn't get APIKey");
        }

    }

    private String loginAndGetCookies(String serverlUrl, MultiValueMap<String, String> creds,HttpHeaders headers){
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(creds,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(serverlUrl+LOGIN_END_POINT, request, String.class);
            List<String> cookiesList = response.getHeaders().get(HttpHeaders.SET_COOKIE);
            String cookies="";
            if(cookiesList!=null){
                for(String cook:cookiesList){
                    cookies=cookies+"; "+cook;
                }
            }
            COOKIES=cookies;
            return cookies;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("USERNAME or PASSWORD is not valid");
        }

    }


    /**
     *
     * @param getRequestDTO
     * @return
     */
    public String handleGETRequest(GETRequestDTO getRequestDTO){
        if(getRequestDTO ==null
                || !StringUtils.hasText(getRequestDTO.getUrl())){
            throw new RuntimeException("Invalid Input");
        }
        String url = getRequestDTO.getUrl();
        if(StringUtils.hasText(getRequestDTO.getParams())){
            url = url + getRequestDTO.getParams();
        }



        try {

//            String apikey="kHZvwc7VaqeNRfgMWufOCGCB5CAIgiVv";
//            String fooResourceUrl
//                = "https://qatest.fareye.co/api/v1/search_order?api_key="+apikey+"&referenceNumber=Pu4";
//            ResponseEntity<String> response
//                = restTemplate.getForEntity(fooResourceUrl, String.class);


            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Cookie",COOKIES);
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            return response.getBody();
        }
        catch (Exception e){
            System.out.println(url);
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    /**
     *
     * @param postRequestDTO
     * @return
     */
    public String handlePOSTRequest(POSTRequestDTO postRequestDTO){

        return null;
    }




    public void commentCode(){
//        String apikey="kHZvwc7VaqeNRfgMWufOCGCB5CAIgiVv";
//        RestTemplate restTemplate = new RestTemplate();
////        String fooResourceUrl
////                = "https://qatest.fareye.co/api/v1/search_order?api_key="+apikey+"&referenceNumber=Pu4";
////        ResponseEntity<String> response
////                = restTemplate.getForEntity(fooResourceUrl, String.class);
//
//        String serverlUrl="https://qatest.fareye.co";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        //Map<String,String> maps = new HashMap<>();
//        MultiValueMap<String, String> maps= new LinkedMultiValueMap<>();
//        maps.add("j_username","007_admin");
//        maps.add("j_password","8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
//        maps.add("remember-me", "false");
//        maps.add("submit","Login");
//        HttpEntity<MultiValueMap> request = new HttpEntity<>(maps,headers);
//
//        ResponseEntity<String> response;
//        ResponseEntity<String> response1=null;
//        String str="";
//        try {
//            response = restTemplate.postForEntity(serverlUrl+"/app/authentication", request, String.class);
//            List<String> listHead = response.getHeaders().get(HttpHeaders.SET_COOKIE);
//            headers = new HttpHeaders();
//            String coook="";
//            for(String a:listHead){
//                coook=coook+"; "+a;
//            }
//            headers.set("Cookie",coook);
//            response1 = restTemplate.exchange(serverlUrl+"/v1/app/rest/user/api_key", HttpMethod.GET, new HttpEntity<>(headers), String.class);
//        }catch (Exception e){
//            System.out.println(e);
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(response1==null?"":response1.getBody(), HttpStatus.OK);

    }
}
