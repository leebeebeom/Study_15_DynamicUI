package com.beebeom.a15_dynamicui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArticleFrag extends Fragment {

    public static final String CURRENTPOSITION = "currentposition";
    private TextView mTextView;
    //if 문에서 == null 을 지정할 수 없기 때문에 0이상이 아닌 값을 넣어줌
    //0을 넣으면 0번째 아이템 표시됨
    private int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //최초 생성 아닐시
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(CURRENTPOSITION);
        }
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        mTextView = view.findViewById(R.id.article_text);
        return view;
    }

    //onCreateView 에서 View 생성 후 여기로 넘어옴
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //보낸 번들 객체 받기
        Bundle args = getArguments();
        //최초 생성일시(태블릿에서 args NPE 나서 체크해줌)
        if (args != null) {
            //setArticle 메소드 호출
            //이렇게 안하면 NPE 남.
            setArticle(args.getInt("position"));
        }//아닐시
        else if(mCurrentPosition > -1){
            setArticle(mCurrentPosition);
        }
    }

    public void setArticle(int position) {
        mTextView.setText(Articles.articles[position]);
        //화면 회전 대응 위해서 int 객체 생성
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //화면 회전 대응
        //마지막 포지션 저장
        outState.putInt(CURRENTPOSITION, mCurrentPosition);
    }
}