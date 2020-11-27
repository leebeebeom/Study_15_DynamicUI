package com.beebeom.a15_dynamicui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements HeadlineFrag.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //분기 태우기
        //프레임레이아웃이 있을 시에만 프래그먼트 인스턴스 생성
        if (findViewById(R.id.container_frame) != null) {
            //화면이 최초생성일 때만 생성
            //이거 없으면 ArticleFrag 일때 화면을 돌리면
            //HeadlineFrag 랑 겹쳐버림
            //또, 프래그먼트 위에 프래그먼트로 계속 쌓임
            if (savedInstanceState == null) {
                //HeadlineFrag 객체 생성 후 프레임레이아웃에 배치
                HeadlineFrag headlineFrag = new HeadlineFrag();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container_frame, headlineFrag)
                        .commit();
            }
        }
    }

    //HeadlineFrag 에서 리스트 아이템 클릭 시
    @Override
    public void onHeadlineSelected(int position) {
        //테블릿에 보일 ArticleFrag 먼트 객체 생성
        ArticleFrag articleFragLarge = (ArticleFrag) getSupportFragmentManager().findFragmentById(R.id.article_frag);
        //위에서 만든 ArticleFrag 객체(R.id.article_frag)가 없을 시 인스턴스 생성
        if (articleFragLarge == null) {
            //번들 객체 생성
            Bundle args = new Bundle();
            //포지션 값 담기
            args.putInt("position", position);
            //ArticleFrag 객체 생성
            ArticleFrag articleFrag = new ArticleFrag();
            //인스턴스 객체에 번들 보내기 -> ArticleFrag 으로 이어짐
            articleFrag.setArguments(args);
            //만들어진 프래그먼트 프레임 레이아웃에 넣기
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_frame, articleFrag)
                    //백스택 추가로 뒤로가기 버튼시 원래 화면으로.
                    .addToBackStack(null)
                    .commit();
        }//있을 시
        else {
            articleFragLarge.setArticle(position);
        }
    }
}