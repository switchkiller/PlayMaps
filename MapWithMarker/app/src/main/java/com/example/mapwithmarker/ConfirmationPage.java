package com.example.mapwithmarker;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        SeekBar seekBar = findViewById(R.id.seekBar);
        RecordView recordView = (RecordView) findViewById(R.id.record_view);
        RecordButton recordButton = (RecordButton) findViewById(R.id.record_button);
        Button button = findViewById(R.id.button_proceed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationPage.this, ResponseRecorded.class);
                startActivity(intent);
            }
        });

        //IMPORTANT
        recordButton.setRecordView(recordView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                //Start Recording..
                Log.d("RecordView", "onStart");
                findViewById(R.id.record_text).setVisibility(View.GONE);
            }

            @Override
            public void onCancel() {
                //On Swipe To Cancel
                Log.d("RecordView", "onCancel");
                findViewById(R.id.record_text).setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish(long recordTime) {
                //Stop Recording..
                // String time = getHumanTimeText(recordTime);
                Log.d("RecordView", "onFinish");
                TextView t = findViewById(R.id.record_text);
                t.setText("Recorded audio");
                t.setVisibility(View.VISIBLE);
                // Log.d("RecordTime", time);
            }

            @Override
            public void onLessThanSecond() {
                //When the record time is less than One Second
                Log.d("RecordView", "onLessThanSecond");
            }
        });

    }
}
