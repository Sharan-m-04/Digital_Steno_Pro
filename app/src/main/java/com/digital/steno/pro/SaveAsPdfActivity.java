package com.digital.steno.pro;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.facebook.shimmer.*;
import com.google.android.gms.*;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class SaveAsPdfActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double position = 0;
	private String path = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear4;
	private LinearLayout linear1;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview2;
	private ImageView imageview3;
	private LinearLayout linear2;
	private ScrollView vscroll2;
	private EditText edittext1;
	private LinearLayout linear3;
	private TextView textview1;
	
	private Intent in = new Intent();
	private SharedPreferences sp;
	private SharedPreferences dm;
	private TimerTask time;
	private SharedPreferences bold;
	private SharedPreferences italics;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.save_as_pdf);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear4 = findViewById(R.id.linear4);
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		linear5 = findViewById(R.id.linear5);
		textview2 = findViewById(R.id.textview2);
		imageview3 = findViewById(R.id.imageview3);
		linear2 = findViewById(R.id.linear2);
		vscroll2 = findViewById(R.id.vscroll2);
		edittext1 = findViewById(R.id.edittext1);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		bold = getSharedPreferences("bold", Activity.MODE_PRIVATE);
		italics = getSharedPreferences("italics", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview1, "scaleX", 1.10d, 200);
				_Animator(imageview1, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview1, "scaleX", 1, 200);
								_Animator(imageview1, "scaleY", 1, 200);
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								Animatoo.animateZoom(SaveAsPdfActivity.this);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview3, "scaleX", 1.10d, 200);
				_Animator(imageview3, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview3, "scaleX", 1, 200);
								_Animator(imageview3, "scaleY", 1, 200);
								try{ android.graphics.pdf.PdfDocument document
									= new android.graphics.pdf.PdfDocument();
									android.graphics.pdf.PdfDocument.PageInfo
									pageInfo = new
									android.graphics.pdf.PdfDocument.PageInfo.Builder
									(linear3.getWidth(), linear3.getHeight(), 1).create();
									android.graphics.pdf.PdfDocument.Page page =
									document.startPage(pageInfo); Canvas canvas =
									page.getCanvas(); Paint paint = new Paint();
									canvas.drawPaint(paint); linear3.draw(canvas);
									document.finishPage(page);
									path = FileUtil.getExternalStorageDir().concat("/Digital Steno Pro/Saved PDF Files/".concat(edittext1.getText().toString().concat(".pdf")));
									FileUtil.writeFile(path, "");
									java.io.File myFile = new
									
									java.io.File(path); java.io.FileOutputStream fOut =
									new java.io.FileOutputStream(myFile);
									java.io.OutputStreamWriter myOutWriter = new
									java.io.OutputStreamWriter(fOut);
									document.writeTo(fOut); document.close();
									myOutWriter.close(); fOut.close();
									Toast.makeText(getBaseContext(),
									
									"File Saved"
									,
									Toast.LENGTH_LONG).show(); }
								catch (Exception e)
								{ Toast.makeText(getBaseContext(),
									e.getMessage(), Toast.LENGTH_LONG).show();}
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								Animatoo.animateZoom(SaveAsPdfActivity.this);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
	}
	
	private void initializeLogic() {
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFEEEEEE));
		_card_style(linear4, 20, 0, "#304FFE");
		if (!sp.getString("allnotes", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("allnotes", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		if (!sp.getString("pos", "").equals("")) {
			position = Double.parseDouble(sp.getString("pos", ""));
			textview1.setText(listmap.get((int)position).get("note").toString());
		}
		else {
			position = -1;
		}
		textview1.setText(getIntent().getStringExtra("pdf"));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear1.setBackgroundColor(0xFF212121);
			textview1.setBackgroundColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFF000000);
		}
		else {
			linear1.setBackgroundColor(0xFFFFFFFF);
			textview1.setBackgroundColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFF000000);
		}
	}
	
	@Override
	public void onBackPressed() {
		in.setClass(getApplicationContext(), AddNoteActivity.class);
		startActivity(in);
		Animatoo.animateZoom(SaveAsPdfActivity.this);
		finish();
	}
	public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _Animator(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
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
			if (_arr.valueAt(_iIdx)) {
				_result.add((double)_arr.keyAt(_iIdx));
			}
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
