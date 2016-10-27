package com.example.administrator.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    //public class calculator extends AppCompatActivity {
    private Button[] btnNum = new Button[11];//数值按钮
    private Button[] btnCommand = new Button[5];//符号按钮
    private EditText editText = null;//显示区域
    private Button btnClear = null;//clear按钮
    private String lastCommand;//用于保存运算符
    private boolean clearFlag;//判断是否显示清空区域的值
    private boolean firstFlag;//判断是否是首次输入
    private double result=0;//计算结果
    private double x;
    private double y;
    private int i=0;
    private boolean isClickButton = false;//判断是否按了=按钮

    public MainActivity() {
        result = 0;//y的值
        firstFlag = true;
        clearFlag = false;
        //lastCommand = "=";//运算符
    }
    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);
        //获取运算符
        btnCommand[0] = (Button) findViewById(R.id.add);
        btnCommand[1] = (Button) findViewById(R.id.subtract);
        btnCommand[2] = (Button) findViewById(R.id.multiply);
        btnCommand[3] = (Button) findViewById(R.id.divide);
        btnCommand[4] = (Button) findViewById(R.id.equal);
        //获取数字
        btnNum[0] = (Button) findViewById(R.id.num0);
        btnNum[1] = (Button) findViewById(R.id.num1);
        btnNum[2] = (Button) findViewById(R.id.num2);
        btnNum[3] = (Button) findViewById(R.id.num3);
        btnNum[4] = (Button) findViewById(R.id.num4);
        btnNum[5] = (Button) findViewById(R.id.num5);
        btnNum[6] = (Button) findViewById(R.id.num6);
        btnNum[7] = (Button) findViewById(R.id.num7);
        btnNum[8] = (Button) findViewById(R.id.num8);
        btnNum[9] = (Button) findViewById(R.id.num9);
        btnNum[10] = (Button) findViewById(R.id.point);
        //初始化显示结果区域
        editText = (EditText) findViewById(R.id.result);
        editText.setText(null);
        for (Button bc: btnNum)
        {
            bc.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = editText.getText().toString();
                    switch (v.getId())
                    {
                        case R.id.num0:
                            str+="0";
                            break;
                        case R.id.num1:
                            str+="1";
                            break;
                        case R.id.num2:
                            str+="2";
                            break;
                        case R.id.num3:
                            str+="3";
                            break;
                        case R.id.num4:
                            str+="4";
                            break;
                        case R.id.num5:
                            str+="5";
                            break;
                        case R.id.num6:
                            str+="6";
                            break;
                        case R.id.num7:
                            str+="7";
                            break;
                        case R.id.num8:
                            str+="8";
                            break;
                        case R.id.num9:
                            str+="9";
                            break;
                        case R.id.point:
                            str+=".";
                            break;
                        default:
                            break;
                    }
                    if (isClickButton) {
                        editText.setText(null);
                        isClickButton = false;
                    }
                    if (firstFlag)
                    {
                        if (str.equals("."))
                            return;
                        firstFlag = false;
                    }
                    else
                    {
                        Button btn=(Button)v;
                        String mystr=btn.getText().toString();
                        //判断是否已经有point
                        if (mystr.indexOf(".") == -1 && str.equals("."))
                            return;
                        if (mystr.equals(".")&&str.equals("."))
                            return ;
                    }
                    //如果点击运算符后再输入数字,就清空显示区域的值
                    if (clearFlag) {
                        editText.setText(null);
                        clearFlag = false;
                    }
                    editText.setText(str);
                }
            });
        }
        for (Button bc:btnCommand)
        {
            bc.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(v.getId())
                    {
                        case R.id.add :
                            String stradd = editText.getText().toString();
                            if (stradd.equals(null)) return;
                            y = Double.valueOf(stradd);
                            editText.setText(null);
                            i=1;
                            isClickButton = false;
                            break;
                        case R.id.subtract :
                            String strsub = editText.getText().toString();
                            if (strsub.equals(null)) return;
                            y = Double.valueOf(strsub);
                            editText.setText(null);
                            i=2;
                            isClickButton = false;
                            break;
                        case R.id.multiply :
                            String strmul = editText.getText().toString();
                            if (strmul.equals(null)) return;
                            y = Double.valueOf(strmul);
                            editText.setText(null);
                            i=3;
                            isClickButton = false;
                            break;
                        case R.id.divide :
                            String strdiv = editText.getText().toString();
                            if (strdiv.equals(null)) return;
                            y = Double.valueOf(strdiv);
                            editText.setText(null);
                            i=4;
                            isClickButton = false;
                            break;
                        case R.id.equal :
                            String str = editText.getText().toString();
                            if (str.equals(null)) return;
                            x = Double.valueOf(str);
                            editText.setText(null);
                            switch (i) {
                                case 0:
                                    result = y;
                                    break;
                                case 1:
                                    result = y + x;
                                    break;
                                case 2:
                                    result = y - x;
                                    break;
                                case 3:
                                    result = y * x;
                                    break;
                                case 4:
                                    result = y / x;
                                    break;
                                default:
                                    result = 0;
                                    break;
                            }
                            editText.setText(String.valueOf(result));
                            isClickButton=true;
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        //clear按钮的动作
        btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
                result = 0;
                firstFlag = true;
                clearFlag = false;
            }
        });
    }
}



