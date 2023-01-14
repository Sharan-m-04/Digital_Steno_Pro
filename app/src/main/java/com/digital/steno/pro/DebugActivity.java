package com.digital.steno.pro;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.facebook.shimmer.*;
import com.google.android.gms.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class DebugActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private LinearLayout linear1;
    private LinearLayout linear2;
    private ScrollView vscroll1;
    private ImageView imageview1;
    private LinearLayout linear8;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear7;
    private TextView stackTrace;
    private Button button1;
    private Button button2;

    private Intent in = new Intent();
    private AlertDialog.Builder warn;
    private TimerTask tr;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.debug);
        initialize(_savedInstanceState);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        vscroll1 = findViewById(R.id.vscroll1);
        imageview1 = findViewById(R.id.imageview1);
        linear8 = findViewById(R.id.linear8);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear7 = findViewById(R.id.linear7);
        stackTrace = findViewById(R.id.stackTrace);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        warn = new AlertDialog.Builder(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                button1.setBackgroundColor(0xFFFFE082);
                tr = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button1.setBackgroundColor(0xFFFFC107);
                                tr.cancel();
                            }
                        });
                    }
                };
                _timer.schedule(tr, (int)(100));
                ((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", stackTrace.getText().toString()));
                SketchwareUtil.showMessage(getApplicationContext(), "Errors Copied To The Clipboard");
                in.setAction(Intent.ACTION_VIEW);
                in.setData(Uri.parse("mailto:".concat("vasavi.infosolutions@gmail.com")));
                in.putExtra("android.intent.extra.SUBJECT", "Error Found in Digital Steno!");
                in.putExtra("android.intent.extra.TEXT", stackTrace.getText().toString());
                startActivity(in);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                in.setClass(getApplicationContext(), MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void initializeLogic() {
        button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF03A9F4));
        button2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFFF5F5F5));
        stackTrace.setText(getIntent().getStringExtra("error"));
    }

    public void _Send(final Intent _IntentName, final String _to, final String _subject, final String _text) {
        _IntentName.setAction(Intent.ACTION_VIEW);
        _IntentName.setData(Uri.parse("mailto:".concat(_to)));
        _IntentName.putExtra("android.intent.extra.SUBJECT", _subject);
        _IntentName.putExtra("android.intent.extra.TEXT", _text);
        startActivity(_IntentName);
    }


    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}