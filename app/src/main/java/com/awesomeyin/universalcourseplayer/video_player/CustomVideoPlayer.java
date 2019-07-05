package com.awesomeyin.universalcourseplayer.video_player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesomeyin.universalcourseplayer.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
* 自定义视频播放器
*
* */
public class CustomVideoPlayer extends StandardGSYVideoPlayer implements View.OnClickListener {
    private TextView btnSkipView, btnReplay, btnToEvaluate;
    private ImageView btnRightClose;
    private LinearLayout layoutCompleteView;
    //默认为答题中视频
    private VideoPlayType mVideoType = VideoPlayType.VIDEO_TYPE_IN_EVALUATING;

    public CustomVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public CustomVideoPlayer(Context context) {
        super(context);
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_customplayer;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        btnSkipView = findViewById(R.id.btn_skip);
        btnReplay = findViewById(R.id.btn_replay);
        btnToEvaluate = findViewById(R.id.btn_toevaluate);
        layoutCompleteView = findViewById(R.id.layout_complete);
        btnRightClose = findViewById(R.id.btn_close);
        btnSkipView.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        btnToEvaluate.setOnClickListener(this);
    }

    public void setmVideoType(VideoPlayType videoType) {
        this.mVideoType = videoType;
    }

    //控制自定义功能按钮显示
    private void changeMyCustomBtnVisible(int viewVisibleState) {
        switch (mVideoType) {
            case VIDEO_TYPE_IN_EVALUATING:
                btnRightClose.setVisibility(viewVisibleState);
                break;
            case VIDEO_TYPE_EVALUATING_BEGAIN:
                getBackButton().setVisibility(viewVisibleState);
                btnSkipView.setVisibility(viewVisibleState);
                break;
        }
    }

    @Override
    protected void hideAllWidget() {
        super.hideAllWidget();
        changeMyCustomBtnVisible(GONE);
    }

    @Override
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        changeMyCustomBtnVisible(VISIBLE);
    }

    @Override
    protected void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
        changeMyCustomBtnVisible(GONE);
    }

    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        changeMyCustomBtnVisible(VISIBLE);
    }

    @Override
    protected void changeUiToPrepareingClear() {
        super.changeUiToPrepareingClear();
        changeMyCustomBtnVisible(GONE);
    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        changeMyCustomBtnVisible(VISIBLE);
    }

    @Override
    protected void changeUiToPauseClear() {
        super.changeUiToPauseClear();
        changeMyCustomBtnVisible(GONE);
    }

    @Override
    protected void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        changeMyCustomBtnVisible(VISIBLE);
    }

    @Override
    protected void changeUiToClear() {
        super.changeUiToClear();
        changeMyCustomBtnVisible(GONE);
    }

    public void showCompleteView() {
        hideAllWidget();
        layoutCompleteView.setVisibility(VISIBLE);
    }

    public void resetNormalView() {
        changeUiToNormal();
        layoutCompleteView.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (skipClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.back:
                skipClickListener.onBtnSkipClick(BtnClickType.BTN_BACK_FINISH);
                break;
            case R.id.btn_skip:
                skipClickListener.onBtnSkipClick(BtnClickType.BTN_SKIP_VIDEO);
                break;
            case R.id.btn_replay:
                skipClickListener.onBtnSkipClick(BtnClickType.BTN_REPLAY_VIDEO);
                break;
            case R.id.btn_toevaluate:
                skipClickListener.onBtnSkipClick(BtnClickType.BTN_TO_EVALUATE);
                break;
            case R.id.btn_close:
                skipClickListener.onBtnSkipClick(BtnClickType.BTN_RIGHT_CLOSE);
                break;
        }
    }

    private CustomBtnClickListener skipClickListener;
    interface CustomBtnClickListener {
        void onBtnSkipClick(BtnClickType btnClickType);
    }

    public void setCustomBtnClickListener(CustomBtnClickListener skipClickListener) {
        this.skipClickListener = skipClickListener;
    }

    enum BtnClickType {
        //返回键
        BTN_BACK_FINISH,
        //跳过
        BTN_SKIP_VIDEO,
        //重新播放
        BTN_REPLAY_VIDEO,
        //答题
        BTN_TO_EVALUATE,
        //右侧关闭
        BTN_RIGHT_CLOSE
    }
}
