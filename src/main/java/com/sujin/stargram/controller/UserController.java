package com.sujin.stargram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sujin.stargram.Service.UserService;
import com.sujin.stargram.config.auth.PrincipalDetails;
import com.sujin.stargram.domain.User;
import com.sujin.stargram.dto.KakaoProfile;
import com.sujin.stargram.dto.OAuthToken;
import com.sujin.stargram.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class UserController {

    //private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Value("${tnwls.key}")
    private String key;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable long pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.userProfile(pageUserId,principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //System.out.println("????????????: " + principalDetails.getUser());

        // ?????? ??????
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
        // System.out.println("?????? ?????? ?????? : " + mPrincipalDetails.getUser());

        //model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }

    // ????????? ????????? ????????????
//    @GetMapping("/auth/kakao/callback")
//    public String kakaoCallback(String code) throws JsonProcessingException { //DATA??? ???????????? ???????????? ??????
//
//        //post???????????? key=value ???????????? ??????
//        // HttpsURLConnection url = new HttpsURLConnection
//        //Retrofit2 - ???????????????
//        //OkHttp
//
//        RestTemplate rt = new RestTemplate();
//
//        //HttpHeader ???????????? ?????????
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpBody ???????????? ??????
//        MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
//        params.add("grant_type","authorization_code");
//        params.add("client_id","4a6e119a413511e487cef7801477fcc7");
//        params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
//        params.add("code",code);
//
//        //HttpHeader??? HttpBody??? ????????? ??????????????? ??????
//        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
//                new HttpEntity<>(params,headers);
//
//        // Http ???????????? - Post???????????? - ????????? response ????????? ?????? ??????
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        //Gson, Json simple, ObjectMapper
//        ObjectMapper objectMapper = new ObjectMapper();
//        OAuthToken oAuthToken = null;
//
//        try{
//            oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
//        }catch (JsonMappingException e){
//            e.printStackTrace();
//        }catch (JsonProcessingException e){
//            e.printStackTrace();
//        }
//
//
//
//        RestTemplate rt2 = new RestTemplate();
//
//        //HttpHeader ???????????? ?????????
//        HttpHeaders headers2 = new HttpHeaders();
//        headers2.add("Authorization","Bearer " + oAuthToken.getAccess_token());
//        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//
//        //HttpHeader??? HttpBody??? ????????? ??????????????? ??????
//        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 =
//                new HttpEntity<>(headers2);
//
//        // Http ???????????? - Post???????????? - ????????? response ????????? ?????? ??????
//        ResponseEntity<String> response2 = rt2.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest2,
//                String.class
//        );
//
//
//        //Gson, Json simple, ObjectMapper
//        ObjectMapper objectMapper2 = new ObjectMapper();
//
//
//        KakaoProfile kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
//
//
//
//        //User username, password, email
//        User kakaoUser = User.builder()
//                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
//                .password(key)
//                .email(kakaoProfile.getKakao_account().getEmail())
//                .build();
//
//        //??????????????? ?????????????????? ???????????? ??????
//        User originUser = userService.findByUsername(kakaoUser.getUsername());
//
//        if( originUser.getUsername() == null){
//            System.out.println("?????? ????????? ????????? ????????? ?????? ??????????????? ???????????????.");
//            userService.join(kakaoUser);
//        }
//
//        // ????????? ??????
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),key));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//       return "redirect:/";
//    }
}
