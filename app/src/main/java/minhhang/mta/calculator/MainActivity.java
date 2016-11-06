package minhhang.mta.calculator;

import android.content.DialogInterface;
import android.media.MediaCodec;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Currency;
import java.util.Vector;
import java.util.regex.Pattern;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    //implements  View.OnClickListener vi co qua nhieu tool nen de ko phai goi lai cai Onclick... nhieu lan
    Balan bl = new Balan();
    String display = "";
    String currentOperator = "";
    private TextView tv_result;
    private EditText edt_input;

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;

    private Button btn_sum;
    private Button btn_div;
    private Button btn_equal;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_comma;
    private Button btn_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viet code
        initWidget();
        setEventClickViews();
        setOnLongClick();
        hideKeyboard();
    }

    //ham an ban phim trong editText
    public void hideKeyboard() {
        edt_input.setInputType(InputType.TYPE_NULL);
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            edt_input.setRawInputType(InputType.TYPE_CLASS_TEXT);
            edt_input.setTextIsSelectable(true);
        }
    }

    //gan cac tool cho cac bien
    public void initWidget() {
        tv_result = (TextView) findViewById(R.id.tv_result);
        edt_input = (EditText) findViewById(R.id.edt_input);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);

        btn_sum = (Button) findViewById(R.id.btn_sum);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_comma = (Button) findViewById(R.id.btn_comma);
        btn_C = (Button) findViewById(R.id.btn_C);
    }

    public void setEventClickViews() {
        //set cho cac widget bat su kien. Lang nghe su kien

        /* Ko can set cai nay vi no chi de hien thi. ko Click de lang nghe gi ca
        tv_result.setOnClickListener(this);
        edt_input.setOnClickListener(this);
        */
        btn_comma.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_sum.setOnClickListener(this);
        btn_C.setOnClickListener(this);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        currentOperator = btn.getText().toString();
        String s = edt_input.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
            /*   edt_input.setText(); cai nay de set gia tri cho edt_input.
                neu dung cai nay thi khi click vao so khac. gia tri ban dau se bi xoa bo.
                trong khi ta can nhap dc nhieu so 1 luc =))*/
                edt_input.append("0");
                break;
            case R.id.btn_1:
                edt_input.append("1");
                break;
            case R.id.btn_2:
                edt_input.append("2");
                break;
            case R.id.btn_3:
                edt_input.append("3");
                break;
            case R.id.btn_4:
                edt_input.append("4");
                break;
            case R.id.btn_5:
                edt_input.append("5");
                break;
            case R.id.btn_6:
                edt_input.append("6");
                break;
            case R.id.btn_7:
                edt_input.append("7");
                break;
            case R.id.btn_8:
                edt_input.append("8");
                break;
            case R.id.btn_9:
                edt_input.append("9");
                break;
            case R.id.btn_C:
                //xoa 1 ki tu truoc no. code mau
                BaseInputConnection textFileInputConnection = new BaseInputConnection(edt_input, true);
                textFileInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                //xoa ca phan textview ket qua ben duoi
                tv_result.setText("");
                break;
            case R.id.btn_comma: {
                edt_input.append(".");
                ///////////
            }
            break;
            case R.id.btn_div: {
                if (isOperator(String.valueOf(s.charAt(s.length() - 1))))
                    edt_input.append("");
                else
                    edt_input.append("/");
            }
            break;
            case R.id.btn_mul: {
                if (isOperator(String.valueOf(s.charAt(s.length() - 1))))
                    edt_input.append("");
                else
                    edt_input.append("*");
            }
            break;
            case R.id.btn_sub: {
                if (isOperator(String.valueOf(s.charAt(s.length() - 1))))
                    edt_input.append("");
                else
                    edt_input.append("-");
            }
            break;
            case R.id.btn_sum: {
                if (isOperator(String.valueOf(s.charAt(s.length() - 1))))
                    edt_input.append("");
                else
                    edt_input.append("+");
            }
            break;
            case R.id.btn_equal: {
                tv_result.setText(bl.valueMath(edt_input.getText().toString()));
            }
            break;
        }
    }

    public void setOnLongClick() {
        btn_C.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.btn_C) {
            edt_input.setText("");
            tv_result.setText("");
        }
        return false;
    }

    private boolean isOperator(String s) {
        String[] operator = new String[]{"+", "-", "*", "/"};
        Arrays.sort(operator);
        return Arrays.binarySearch(operator, s) > -1;
    }
/*
    private boolean checkComma(String s){
        String str = edt_input.getText().toString();
        String[] operator = new String[]{"+", "-", "*", "/"};
        for(int i=0;i<str.length();i++)
            if (isOperator(String.valueOf(str[i])))
                for(int j=0;j<i;j++)


        return true;
    }*/
}

