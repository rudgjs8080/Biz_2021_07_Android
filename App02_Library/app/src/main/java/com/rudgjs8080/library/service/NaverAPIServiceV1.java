package com.rudgjs8080.library.service;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rudgjs8080.library.adapter.BookAdapter;
import com.rudgjs8080.library.config.Naver;
import com.rudgjs8080.library.databinding.FragmentFirstBinding;
import com.rudgjs8080.library.model.BookDTO;
import com.rudgjs8080.library.model.NaverParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Ctrl + Alt + O = import 정리
public class NaverAPIServiceV1 extends Thread{

    // private NaverParent naverParent =  null;
    private BookAdapter bookAdapter = null;
    FragmentFirstBinding binding = null;

    public NaverAPIServiceV1(BookAdapter bookAdapter, FragmentFirstBinding binding) {
        this.bookAdapter = bookAdapter;
        this.binding = binding;
    }

    public NaverAPIServiceV1(BookAdapter bookAdapter) {
        this.bookAdapter = bookAdapter;
    }

    // 3. 생성된 Connection을 통하여 데이터를 가져오고
    // 필요한 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search)  {

        // 아래의 코드를 실행하면 Retrofit 설정된 값을 기준으로
        // naver에 요청을 수행한다
        // 이때 이 코드는 비동기 방식으로 작동이 된다
        Call<NaverParent> naverCall = RetrofitClient.getClient()
                .getNaverBook(Naver.CLIENT_ID,Naver.CLIENT_SECRET,search,50,1);

        /**
         * Retrofit은 API요청을 비동기 방식으로 수행을 한다
         * 일반적으로 Network나 외부 다른 장치와 데이터를 주고 받을 때는
         * 대부분의 코드를 비동기, 또는 thread 방식으로 사용한다
         *
         * 동기방식
         * 요청수행 -> 결과가 return 되어 올 때까지 대기
         *
         * 비동기방식
         * 요청수행 -> 결과가 return 되든 말든 다른일 수행하기
         * 결과 return 되면 그 결과를 수신하여 처리할 event handler를
         * 작성해야 한다
         */
        // Retrofit event 핸들러 작성

        naverCall.enqueue(new Callback<NaverParent>() {

            private NaverParent naverParent;
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {

                // return된 response 객체 확인
                Log.d("Naver Res Return",response.toString());
                int resCode = response.code();
                if(resCode < 300){
                    // return된 response 객체에서 데이터만 추출
                    naverParent = response.body();
                    Log.d("Naver Return",naverParent.toString());

                    List<BookDTO> bookDTOList = naverParent.items;
                    bookAdapter = new BookAdapter(bookDTOList);
                    binding.bookListView.setAdapter(bookAdapter);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(binding.getRoot().getContext());
                    binding.bookListView.setLayoutManager(layoutManager);

                }

            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

                Log.d("오류가 발생했습니다",t.toString());
            }
        });
    }



}
