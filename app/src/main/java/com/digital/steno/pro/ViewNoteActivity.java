package com.digital.steno.pro;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.speech.tts.TextToSpeech;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
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

public class ViewNoteActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private double position = 0;
	private String share = "";
	private String dataText = "";
	private String text = "";
	private double length = 0;
	private double counts = 0;
	private double repeat = 0;
	private double y = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear11;
	private LinearLayout linear7;
	private ImageView imageview1;
	private LinearLayout linear4;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear5;
	private ImageView imageview3;
	private LinearLayout linear6;
	private ImageView imageview4;
	private LinearLayout linear9;
	private ImageView imageview5;
	private LinearLayout linear10;
	private TextView textview3;
	private ScrollView vscroll1;
	private LinearLayout linear8;
	private TextView textview2;
	
	private Intent in = new Intent();
	private SharedPreferences sp;
	private TextToSpeech tts;
	private SharedPreferences dm;
	private SharedPreferences link;
	private TimerTask time;
	private SharedPreferences back;
	private DatabaseReference db = _firebase.getReference("users/data");
	private ChildEventListener _db_child_listener;
	private SharedPreferences bold;
	private SharedPreferences italics;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_note);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear2 = findViewById(R.id.linear2);
		linear11 = findViewById(R.id.linear11);
		linear7 = findViewById(R.id.linear7);
		imageview1 = findViewById(R.id.imageview1);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		linear5 = findViewById(R.id.linear5);
		imageview3 = findViewById(R.id.imageview3);
		linear6 = findViewById(R.id.linear6);
		imageview4 = findViewById(R.id.imageview4);
		linear9 = findViewById(R.id.linear9);
		imageview5 = findViewById(R.id.imageview5);
		linear10 = findViewById(R.id.linear10);
		textview3 = findViewById(R.id.textview3);
		vscroll1 = findViewById(R.id.vscroll1);
		linear8 = findViewById(R.id.linear8);
		textview2 = findViewById(R.id.textview2);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		tts = new TextToSpeech(getApplicationContext(), null);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		link = getSharedPreferences("link", Activity.MODE_PRIVATE);
		back = getSharedPreferences("back", Activity.MODE_PRIVATE);
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
								if (tts.isSpeaking()) {
									tts.stop();
									in.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(in);
									Animatoo.animateZoom(ViewNoteActivity.this);
									finish();
								}
								else {
									in.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(in);
									Animatoo.animateZoom(ViewNoteActivity.this);
									finish();
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview2, "scaleX", 1.10d, 200);
				_Animator(imageview2, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview2, "scaleX", 1, 200);
								_Animator(imageview2, "scaleY", 1, 200);
								tts.setPitch((float)1);
								tts.setSpeechRate((float)-1000);
								tts.speak(textview2.getText().toString(), TextToSpeech.QUEUE_ADD, null);
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
								tts.stop();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview4, "scaleX", 1.10d, 200);
				_Animator(imageview4, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview4, "scaleX", 1, 200);
								_Animator(imageview4, "scaleY", 1, 200);
								((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview2.getText().toString()));
								SketchwareUtil.showMessage(getApplicationContext(), "Copied to clipboard");
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview5, "scaleX", 1.10d, 200);
				_Animator(imageview5, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview5, "scaleX", 1, 200);
								_Animator(imageview5, "scaleY", 1, 200);
								share = textview2.getText().toString();
								Intent i = new Intent(android.content.Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Share using"));
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), AddNoteActivity.class);
				startActivity(in);
				Animatoo.animateZoom(ViewNoteActivity.this);
				back.edit().putString("view", "true").commit();
				finish();
			}
		});
		
		_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db.addChildEventListener(_db_child_listener);
	}
	
	private void initializeLogic() {
		_card_style(linear2, 20, 0, "#304FFE");
		if (!sp.getString("allnotes", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("allnotes", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		if (!sp.getString("pos", "").equals("")) {
			position = Double.parseDouble(sp.getString("pos", ""));
			textview2.setText(listmap.get((int)position).get("note").toString());
			textview3.setText(listmap.get((int)position).get("title").toString());
		}
		else {
			position = -1;
		}
		if (link.getString("Detect Link", "").equals("True")) {
			_detectLinks(textview2);
		}
		else {
			
		}
		textview2.setTextIsSelectable(true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear7.setBackgroundColor(0xFF212121);
			linear11.setBackgroundColor(0xFF212121);
			textview2.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
		}
		else {
			linear7.setBackgroundColor(0xFFFFFFFF);
			linear11.setBackgroundColor(0xFFFFFFFF);
			textview2.setTextColor(0xFF000000);
			textview3.setTextColor(0xFF000000);
		}
	}
	
	@Override
	public void onBackPressed() {
		if (tts.isSpeaking()) {
			tts.stop();
			in.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(in);
			Animatoo.animateZoom(ViewNoteActivity.this);
			finish();
		}
		else {
			in.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(in);
			Animatoo.animateZoom(ViewNoteActivity.this);
			finish();
		}
	}
	public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _detectLinks(final TextView _txt_linkify) {
		_txt_linkify.setClickable(true);
		android.text.util.Linkify.addLinks(_txt_linkify, android.text.util.Linkify.ALL);
		_txt_linkify.setLinkTextColor(Color.parseColor("#FF3770FD"));
		_txt_linkify.setLinksClickable(true);
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
