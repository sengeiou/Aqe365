package com.likeit.aqe365.activity.login.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.likeit.aqe365.R;
import com.likeit.aqe365.activity.FrameActivity;
import com.likeit.aqe365.activity.login.activity.LoginActivity;
import com.likeit.aqe365.activity.main.MainActivity;
import com.likeit.aqe365.activity.web.jsinterface.JsInterfaceActivity;
import com.likeit.aqe365.base.BaseFragment;
import com.likeit.aqe365.constants.Constants;
import com.likeit.aqe365.listener.IEditTextChangeListener;
import com.likeit.aqe365.network.model.BaseResponse;
import com.likeit.aqe365.network.model.LoginRegisterModel;
import com.likeit.aqe365.network.util.RetrofitUtil;
import com.likeit.aqe365.utils.AppManager;
import com.likeit.aqe365.utils.EditTextSizeCheckUtil;
import com.likeit.aqe365.utils.LogUtils;
import com.likeit.aqe365.utils.SharedPreferencesUtils;

import rx.Subscriber;

import static com.likeit.aqe365.Interface.BaseInterface.KEY_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class RelevanceUserFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private TextView tv_relevance_forget_pwd;
    private TextView tv_relevance;
    private ToggleButton tb_re_pwd;
    private EditText et_pwd, et_phone;
    private String type;
    private String openid;
    private String isWeb;

    public static RelevanceUserFragment newInstance() {
        Bundle bundle = new Bundle();
        RelevanceUserFragment fragment = new RelevanceUserFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        type = SharedPreferencesUtils.getString(getActivity(), "type");
        openid = SharedPreferencesUtils.getString(getActivity(), "openid");
        isWeb=SharedPreferencesUtils.getString(getActivity(),"isWeb");
    }

    public void initUI() {
        setBackView();
        setTitle("关联帐号");
        tv_relevance_forget_pwd = findView(R.id.tv_relevance_forget_pwd);
        tv_relevance = findView(R.id.tv_relevance);
        tb_re_pwd = findView(R.id.tb_re_pwd);
        et_pwd = findView(R.id.RelevanceUser_et_pwd);
        et_phone = findView(R.id.RelevanceUser_et_phone);
        EditTextSizeCheckUtil.textChangeListener textChangeListener = new EditTextSizeCheckUtil.textChangeListener(tv_relevance);
        textChangeListener.addAllEditText(et_pwd, et_phone);
        EditTextSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    tv_relevance.setBackgroundResource(R.drawable.shape_round_blue_bg_5);
                    tv_relevance.setOnClickListener(RelevanceUserFragment.this);
                } else {
                    tv_relevance.setBackgroundResource(R.drawable.shape_round_grey_bg_5);
                }
            }
        });
    }


    public void addListeners() {
        tv_relevance_forget_pwd.setOnClickListener(this);
        tb_re_pwd.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_relevance_forget_pwd:
                startFrameActivity(Constants.FRAGMENT_FORGET_PWD);
                break;
            case R.id.tv_relevance:
                //startMainActivity();
                snsBind();
               // startMainActivity();
                break;
        }
    }

    private void snsBind() {
        LogUtils.d("openid-->" + openid);
        LogUtils.d("type-->" + type);
        final String mobile = et_phone.getText().toString().trim();
        final String pwd = et_pwd.getText().toString().trim();
        RetrofitUtil.getInstance().snsBind(openid, type, mobile, pwd, new Subscriber<BaseResponse<LoginRegisterModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("错误：" + e);
            }

            @Override
            public void onNext(BaseResponse<LoginRegisterModel> baseResponse) {
                if (baseResponse.code == 200) {
                    SharedPreferencesUtils.put(getActivity(), "phone", mobile);
                    SharedPreferencesUtils.put(getActivity(), "pwd", pwd);
                    SharedPreferencesUtils.put(getActivity(), "token", baseResponse.getData().getToken());
                    LogUtils.d(baseResponse.getData().getMember().getNickname());
                    if ("1".equals(isWeb)) {
                        startMainActivity();
                    } else {
                        startWebActivity();
                    }
                } else {
                    showProgress(baseResponse.getMsg());
                }

            }
        });
    }
    private void startWebActivity() {
        /**
         * 跳转网页
         */
        SharedPreferencesUtils.put(getActivity(), "login", "2");
        Intent intent = new Intent(getActivity(), JsInterfaceActivity.class);
        startActivity(intent);
    }
    private void startMainActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("flag", "0");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        AppManager.getAppManager().finishAllActivity();
    }

    private void startFrameActivity(int keyFragment) {
        Intent intent = new Intent(getActivity(), FrameActivity.class);
        intent.putExtra(KEY_FRAGMENT, keyFragment);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            //普通文本框
            et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //密码框
            et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        et_pwd.postInvalidate();//刷新View
        //将光标置于文字末尾
        CharSequence charSequence = et_pwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_relevance_user;
    }

    @Override
    protected void lazyLoad() {
        initUI();
        addListeners();
    }
}
