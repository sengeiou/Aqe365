package com.likeit.aqe365.activity.login.fragment;


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

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends BaseFragment implements View.OnClickListener {
    ToggleButton tb_re_pwd;
    EditText et_pwd;
    EditText et_pwd_confirm;
    ToggleButton tb_re_pwd_confirm;
    private TextView tv_confirm;

    public static ForgetPwdFragment newInstance() {

        Bundle args = new Bundle();
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    public void initUI() {
        setBackView();
        setTitle("找回密码");
        tb_re_pwd = findView(R.id.tb_re_pwd);
        et_pwd = findView(R.id.register_et_pwd);
        et_pwd_confirm = findView(R.id.register_et_pwd_confirm);
        tb_re_pwd_confirm = findView(R.id.tb_re_pwd_confirm);
        tv_confirm = findView(R.id.tv_confirm);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {
        tv_confirm.setOnClickListener(this);
        tb_re_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });
        tb_re_pwd_confirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //普通文本框
                    et_pwd_confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码框
                    et_pwd_confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                et_pwd_confirm.postInvalidate();//刷新View
                //将光标置于文字末尾
                CharSequence charSequence = et_pwd_confirm.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                getActivity().finish();
                break;
        }
    }
}