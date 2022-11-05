package com.kadai.calcpi_aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nkai;
    Button btncalc;
    TextView txtans, txtcalctime;

    ICalcPiInterface mInterface;
    private Object MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nkai=(EditText) findViewById(R.id.nkai);
        btncalc=(Button) findViewById(R.id.btncalc);
        txtans=(TextView) findViewById(R.id.txtans);
        txtcalctime=(TextView) findViewById(R.id.txtcalctime);

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int kaisuu= Integer.parseInt(nkai.getText().toString());

                try {
                    long starttime = System.currentTimeMillis();
                    float result=mInterface.monte(kaisuu);
                    long endtime = System.currentTimeMillis();
                    long calctimes=endtime-starttime;

                    txtans.setText(result+"");
                    txtcalctime.setText(calctimes+"");
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        Intent CalcPiService=new Intent(MainActivity.this, com.kadai.calcpi_aidl.CalcPiService.class);
        bindService(CalcPiService, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mInterface=ICalcPiInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}