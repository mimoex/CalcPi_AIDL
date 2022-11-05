package com.kadai.calcpi_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

public class CalcPiService extends Service {
    public CalcPiService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    ICalcPiInterface.Stub mBinder=new ICalcPiInterface.Stub() {
        @Override
        public  float monte(int n) {
            Random rand=new Random(System.currentTimeMillis());
            int cnt = 0;
            float x,y;
            for(int i=0;i<n;i++){
                x=rand.nextFloat();
                y=rand.nextFloat();

                if(x * x + y * y <= 1){
                    cnt++;
                }
            }
            return (float) ((float)cnt / (float)n * 4.0) ;
        }
    };
}