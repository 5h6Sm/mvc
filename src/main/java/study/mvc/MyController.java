package study.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
// 이제 모든 요청 처리 메소드 주소의 앞에 "/api"를 자동으로 붙여줌
@RequestMapping("/api")
public class MyController {
//    @GetMapping("/Hello") //get메서드
//    public String Hello(){
//        return "Hello"; //응답메세지에 붙음
//    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request,
                      HttpServletResponse response) throws IOException{
        response.setStatus(200);
        response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.setHeader("Content-Length", "5");
        response.getWriter().write("Hello");
    }
    @GetMapping("/echo")
    public void echo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
// 메서드 정보 접근
        String method = request.getMethod();
        System.out.println("Method : " + method);
// 주소 정보 접근
        String uri = request.getRequestURI();
        System.out.println("URI : " + uri);
// 쿼리 스트링 정보 접근
        String query = request.getQueryString();
        System.out.println("Query String : " + query);
// 프로토콜, HTTP 버전 정보 접근
        String protocol = request.getProtocol();
        System.out.println("Protocol : " + protocol);
// 헤더 정보 접근
        System.out.println("Headers");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames != null && headerNames.hasMoreElements()) {
            String h = headerNames.nextElement();
            System.out.println(h + " : " + request.getHeader(h));
        }
// 요청 메시지의 바디 데이터 접근
// 1. 바디 데이터를 추가하기 위해서는 POSTMAN과 같은 클라이언트 프로그램을 사용해야 함
// 2. 일반적으로, GET 요청은 바디 데이터를 추가하지 않는 것이 권장되며, 특정 데이터 보내기 위해서 쿼리스트링을 이용함
        byte[] bytes = request.getInputStream().readAllBytes();
        String bytesToString = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(bytesToString);
// 응답 헤더 설정
        response.setHeader("Content-Type", "text/plain; charset=utf-8");
// 전달받은 body 텍스트를 그대로 응답하도록 설정
        response.getWriter().write(bytesToString);
    }
//        http://localhost:1234/api/echo?hello=world&asdf=1234 <= 쿼리 스트링

//        HashMap<String, String> map = new HashMap<>();
//        String[] parts = query.split("&");
//        for(String p : parts){
//            String[] tmp = p.split("=");
//            map.put(tmp[0], tmp[1]);
//        }
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames != null && headerNames.hasMoreElements()){
//            String h = headerNames.nextElement();
//            System.out.println(h + " : " + request.getHeader(h));
//        }
//
//
//        byte[] bytes = request.getInputStream().readAllBytes();
//    }
@GetMapping("/dog-image-file")
public void dogImageFile(HttpServletResponse response) throws IOException {
    File file = ResourceUtils.getFile("classpath:static/dog.jpg");
    byte[] bytes = Files.readAllBytes(file.toPath());

    response.setStatus(200);
    // 임의의 바이너리 데이터임을 알려주는 MIME 타입 설정
    response.setHeader("Content-Type", "application/octet-stream");
    // Content-Length는 자동으로 파일 크기만큼 설정해주지만 여기서는 그냥 넣었음
    // (Q) 만약 바이트 크기를 제대로 주지 않으면 어떻게 될까?)
    response.setHeader("Content-Length", bytes.length + "");
    // 다운로드 될 파일 이름 설정
    String filename = "dog.jpg";
    response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    response.getOutputStream().write(bytes);
}

}
