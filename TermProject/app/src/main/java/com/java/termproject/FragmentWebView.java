package com.java.termproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentWebView extends Fragment {

    WebView webView;
    WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = (WebView) rootView.findViewById(R.id.web_view);

        webView.setWebViewClient(new WebViewClient()); // 웹뷰 실행 새창 X
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); //JS 허용
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 X
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // JS를 이용한 새 창 X
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용
        webSettings.setUseWideViewPort(true); // 화면 맞추기 허용
        webSettings.setSupportZoom(true); // 줌 허용
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(true); // 로컬 저장

        webView.loadUrl("http://dict.naver.com/");

        return rootView;
    }


}
