package com.example.music.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music.Adapter.PlayNhacViewPaperAdapter;
import com.example.music.Fragment.Fragment_DiaNhac;
import com.example.music.Fragment.Fragment_Play_DanhSach_Cac_BaiHat;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    private BaiHat baiHat;
    private ArrayList<BaiHat> arrayListBaiHat;

    Fragment_DiaNhac fragment_diaNhac;

    private Toolbar tbPlayNhac;
    private ImageButton ibrandom,ibback,ibaction,ibnext,ibcycle;
    private SeekBar sbtime;
    private TextView tvtime,tvtimeaction;
    private ViewPager viewPagerPlayNhac;
    private ImageView ivBackGround;

    private MediaPlayer mediaPlayer;
    int position=0;
    boolean checkCycle=false;
    boolean checkRandom=false;
    boolean checkNext=false;
    boolean checkBack=false;

    public static ArrayList<BaiHat> mangBaiHat= new ArrayList<>();
    public static PlayNhacViewPaperAdapter playNhacViewPaperAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhxa();
        Intent intent= getIntent();
        mangBaiHat.clear();
        if(intent.hasExtra("cakhuc")){
            baiHat=intent.getParcelableExtra("cakhuc");
            mangBaiHat.add(baiHat);
        }
        else if(intent.hasExtra("danhsachcakhuc")){
            arrayListBaiHat=intent.getParcelableArrayListExtra("danhsachcakhuc");
            mangBaiHat=arrayListBaiHat;
        }

        init();
        eventClick();
        updateTime();
    }

    private void eventClick() {
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(playNhacViewPaperAdapter.getItem(0)!=null){
                    if(mangBaiHat.size()>0){
                        Log.d("BBBB",DiaChiIP.ip+mangBaiHat.get(0).getHinhBaiHat());
                        fragment_diaNhac.Playnhac(DiaChiIP.ip+mangBaiHat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        ibaction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ibaction.setImageResource(R.drawable.iconplay);
                    if (fragment_diaNhac.objectAnimator!=null){
                        fragment_diaNhac.objectAnimator.pause();
                    }
                }else{
                    mediaPlayer.start();
                    ibaction.setImageResource(R.drawable.iconpause);
                    if (fragment_diaNhac.objectAnimator!=null){
                        fragment_diaNhac.objectAnimator.resume();
                    }
                }
            }
        });

        ibcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCycle==false){
                    if(checkRandom==true){
                        checkRandom=false;
                        ibrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    checkCycle=true;
                    ibcycle.setImageResource(R.drawable.iconsyned);
                }
                else{
                    checkCycle=false;
                    ibcycle.setImageResource(R.drawable.iconrepeat);
                }
            }
        });
        ibrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRandom ==false){
                    if(checkCycle==true){
                        checkCycle=false;
                        ibcycle.setImageResource(R.drawable.iconrepeat);
                    }
                    checkRandom=true;
                    ibrandom.setImageResource(R.drawable.iconshuffled);
                }
                else{
                    checkRandom=false;
                    ibrandom.setImageResource(R.drawable.iconsuffle);
                }
            }
        });

        sbtime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        ibnext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mangBaiHat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<mangBaiHat.size()){
                        ibaction.setImageResource(R.drawable.iconpause);
                        if(checkCycle==false&&checkRandom==false){
                            if(position==mangBaiHat.size()-1){
                                position=0;
                            }
                            else{
                                position++;
                            }
                        }
                        else if(checkRandom==true){
                            Random random=new Random();
                            position=random.nextInt(mangBaiHat.size()-1);
                        }
                        else if(checkCycle==true){}
                        new PlayMP3().execute(DiaChiIP.ip+mangBaiHat.get(position).getLinkBaiHat());

                        //Log.d("BBB",)
                        fragment_diaNhac.Playnhac(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat());
                        fragment_diaNhac.objectAnimator.start();
                        tbPlayNhac.setTitle(mangBaiHat.get(position).getTenBaiHat());
                        Picasso.with(PlayNhacActivity.this).load(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat()).into(ivBackGround);
                        //updateTime();
                    }
                }
            }
        });
        ibback.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mangBaiHat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<mangBaiHat.size()){
                        ibaction.setImageResource(R.drawable.iconpause);
                        if(checkCycle==false&&checkRandom==false){
                            if(position==0){
                                position=mangBaiHat.size()-1;
                            }
                            else{
                                position--;
                            }
                        }
                        else if(checkRandom==true){
                            Random random=new Random();
                            position=random.nextInt(mangBaiHat.size()-1);
                        }
                        else if(checkCycle==true){}
                        new PlayMP3().execute(DiaChiIP.ip+mangBaiHat.get(position).getLinkBaiHat());

                        //Log.d("BBB",)
                        fragment_diaNhac.Playnhac(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat());
                        fragment_diaNhac.objectAnimator.start();
                        tbPlayNhac.setTitle(mangBaiHat.get(position).getTenBaiHat());
                        Picasso.with(PlayNhacActivity.this).load(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat()).into(ivBackGround);
                        //updateTime();
                    }
                }
            }
        });
    }

    private void anhxa() {
//        tbPlayNhac=findViewById(R.id.tbplaynhac);
        ibrandom=findViewById(R.id.ibrandom);
        ibback=findViewById(R.id.ibback);
        ibaction=findViewById(R.id.ibaction);
        ibnext=findViewById(R.id.ibnext);
        ibcycle=findViewById(R.id.ibcycle);
        tvtime=findViewById(R.id.tvtime);
        tvtimeaction=findViewById(R.id.tvtimeaction);
        viewPagerPlayNhac=findViewById(R.id.viewpaperplaynhac);
        sbtime=findViewById(R.id.seekBar);
        tbPlayNhac=findViewById(R.id.tbplaynhac);
        ivBackGround=findViewById(R.id.ivbackground);
    }
    private void init(){
        playNhacViewPaperAdapter=new PlayNhacViewPaperAdapter(getSupportFragmentManager());
        playNhacViewPaperAdapter.addFragment(new Fragment_DiaNhac());
        playNhacViewPaperAdapter.addFragment(new Fragment_Play_DanhSach_Cac_BaiHat());
        viewPagerPlayNhac.setAdapter(playNhacViewPaperAdapter);

        fragment_diaNhac= (Fragment_DiaNhac) playNhacViewPaperAdapter.getItem(0);
        new PlayMP3().execute(DiaChiIP.ip +mangBaiHat.get(0).getLinkBaiHat());
        ibaction.setImageResource(R.drawable.iconpause);
        tbPlayNhac.setTitle(mangBaiHat.get(0).getTenBaiHat());
        Picasso.with(PlayNhacActivity.this).load(DiaChiIP.ip+mangBaiHat.get(0).getHinhBaiHat()).into(ivBackGround);
    }
    private void updateTime(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    sbtime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                    tvtimeaction.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,500);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(mediaPlayer!=null){
                                checkNext=true;
                            }

                            fragment_diaNhac.objectAnimator.pause();
                            Log.d("BBB13","ketthuc");
                            Log.d("BBB13","ket thúc này"+String.valueOf(checkNext));
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else{
                    handler.postDelayed(this,500);
                }
            }
        }, 500);
        Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(checkNext==true){
                    handler1.postDelayed(this,500);
                    //Log.d("BBBBBB","het bai");
                    if(mangBaiHat.size()>0){
                        if(mediaPlayer.isPlaying()||mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer=null;
                        }
                        if(position<mangBaiHat.size()){
                            ibaction.setImageResource(R.drawable.iconpause);
                            if(checkCycle==false&&checkRandom==false){
                                if(position==mangBaiHat.size()-1){
                                    position=0;
                                }
                                else{
                                    position++;
                                }
                            }
                            else if(checkRandom==true){
                                Random random=new Random();
                                position=random.nextInt(mangBaiHat.size()-1);
                            }
                            else if(checkCycle==true){}
                            new PlayMP3().execute(DiaChiIP.ip+mangBaiHat.get(position).getLinkBaiHat());

                            //Log.d("BBB",)
                            fragment_diaNhac.Playnhac(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat());
                            fragment_diaNhac.objectAnimator.start();
                            tbPlayNhac.setTitle(mangBaiHat.get(position).getTenBaiHat());
                            Picasso.with(PlayNhacActivity.this).load(DiaChiIP.ip+mangBaiHat.get(position).getHinhBaiHat()).into(ivBackGround);
                            //updateTime();
                        }
                    }
                    checkNext=false;
                }else{
                    handler1.postDelayed(this,1500);
                    Log.d("BBBB","handler1");
                }
            }
        }, 500);
    }


    private class PlayMP3 extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mediaPlayer =new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

            try {
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            //updateTime();
        }

        private void timeSong() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            tvtime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
            sbtime.setMax(mediaPlayer.getDuration());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkNext = false;
        mediaPlayer.pause();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
        Log.d("BBBB","da reset");
        finish();
    }

}