package com.rudgjs8080.library.service;

import com.rudgjs8080.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit을 사용하여 API 접속을 할 때
 * 최종 end point, header값, method type, parameter 등을
 * 설정하는 interface
 */
public interface NaverRetrofit {

    /**
     * method의 return type을 void 형식이 아닌
     * Call Class type으로 설정을 한다
     */
    // 1. 가장 기본 타입, 아무일도 할 수 없는 상태임
    public Call getBook();

    /**
     * 2. naver openAPI를 사용하기 위해서는
     * 반드시 header에 Client ID와 Client Secret값을
     * 전달해 줘야 한다
     * header에 Client ID와 Client Secret을 전달하기 위하여
     *      - method의 매개변수에 해당 속성을 지정해 준다
     *      - @Header() annotation을 부착한다
     *      - @Header() annotation에 Header의 이름을 지정한다
     *
     * 3. method에 end point와 요청 method type을 지정한다
     *      요청 method type : @GET, @POST, @PUT, @DELETE
     *
     * 4. OpenAPI를 통해 데이터를 요청할 때
     *      값, 변수 등을 보내고 싶을 때
     *      queryString을 만드는데
     *      여기에서는 @Query() 를 사용하여 지정한다
     *      
     * 5. return type인 Call 클래스에
     *      Data를 mapping할 DTO(VO) 클래스를
     *      Generic 으로 설정해 준다
     *
     */
    @GET("book.json")
    public Call<NaverParent> getBook(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int end
    );

}
