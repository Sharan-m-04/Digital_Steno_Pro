package com.digital.steno.pro;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
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

public class PinActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String max = "";
	private String Black_Color = "";
	private String White_Color = "";
	
	private LinearLayout main;
	private LinearLayout dev_pic_lin;
	private TextView enter_title;
	private LinearLayout dots_lin;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView textview1;
	private ImageView dev_pic;
	private LinearLayout dot_1;
	private LinearLayout dot_2;
	private LinearLayout dot_3;
	private LinearLayout dot_4;
	private EditText pin_edittext;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private TextView one;
	private TextView two;
	private TextView three;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private TextView four;
	private TextView five;
	private TextView six;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private TextView seven;
	private TextView eight;
	private TextView nine;
	private LinearLayout linear20;
	private LinearLayout linear21;
	private LinearLayout linear22;
	private ImageView imageview2;
	private TextView zero;
	
	private SharedPreferences security;
	private Intent in = new Intent();
	private SharedPreferences dm;
	private TimerTask time;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.pin);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		dev_pic_lin = findViewById(R.id.dev_pic_lin);
		enter_title = findViewById(R.id.enter_title);
		dots_lin = findViewById(R.id.dots_lin);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		textview1 = findViewById(R.id.textview1);
		dev_pic = findViewById(R.id.dev_pic);
		dot_1 = findViewById(R.id.dot_1);
		dot_2 = findViewById(R.id.dot_2);
		dot_3 = findViewById(R.id.dot_3);
		dot_4 = findViewById(R.id.dot_4);
		pin_edittext = findViewById(R.id.pin_edittext);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear13 = findViewById(R.id.linear13);
		one = findViewById(R.id.one);
		two = findViewById(R.id.two);
		three = findViewById(R.id.three);
		linear14 = findViewById(R.id.linear14);
		linear15 = findViewById(R.id.linear15);
		linear16 = findViewById(R.id.linear16);
		four = findViewById(R.id.four);
		five = findViewById(R.id.five);
		six = findViewById(R.id.six);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		seven = findViewById(R.id.seven);
		eight = findViewById(R.id.eight);
		nine = findViewById(R.id.nine);
		linear20 = findViewById(R.id.linear20);
		linear21 = findViewById(R.id.linear21);
		linear22 = findViewById(R.id.linear22);
		imageview2 = findViewById(R.id.imageview2);
		zero = findViewById(R.id.zero);
		security = getSharedPreferences("security", Activity.MODE_PRIVATE);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textview1.setTextColor(0xFF607D8B);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								textview1.setTextColor(0xFFF44336);
								in.setClass(getApplicationContext(), ResetActivity.class);
								Animatoo.animateZoom(PinActivity.this);
								startActivity(in);
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		pin_edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					if (_charSeq.length() == 1) {
						if (dm.getString("dark", "").equals("1")) {
							_RoundAndBorder(dot_1, White_Color, 6, White_Color, 100);
							_RoundAndBorder(dot_2, Black_Color, 6, White_Color, 100);
							_RoundAndBorder(dot_3, Black_Color, 6, White_Color, 100);
							_RoundAndBorder(dot_4, Black_Color, 6, White_Color, 100);
						}
						else {
							_RoundAndBorder(dot_1, Black_Color, 6, Black_Color, 100);
							_RoundAndBorder(dot_2, White_Color, 6, Black_Color, 100);
							_RoundAndBorder(dot_3, White_Color, 6, Black_Color, 100);
							_RoundAndBorder(dot_4, White_Color, 6, Black_Color, 100);
						}
					}
					else {
						if (_charSeq.length() == 2) {
							if (dm.getString("dark", "").equals("1")) {
								_RoundAndBorder(dot_1, White_Color, 6, White_Color, 100);
								_RoundAndBorder(dot_2, White_Color, 6, White_Color, 100);
								_RoundAndBorder(dot_3, Black_Color, 6, White_Color, 100);
								_RoundAndBorder(dot_4, Black_Color, 6, White_Color, 100);
							}
							else {
								_RoundAndBorder(dot_1, Black_Color, 6, Black_Color, 100);
								_RoundAndBorder(dot_2, Black_Color, 6, Black_Color, 100);
								_RoundAndBorder(dot_3, White_Color, 6, Black_Color, 100);
								_RoundAndBorder(dot_4, White_Color, 6, Black_Color, 100);
							}
						}
						else {
							if (_charSeq.length() == 3) {
								if (dm.getString("dark", "").equals("1")) {
									_RoundAndBorder(dot_1, White_Color, 6, White_Color, 100);
									_RoundAndBorder(dot_2, White_Color, 6, White_Color, 100);
									_RoundAndBorder(dot_3, White_Color, 6, White_Color, 100);
									_RoundAndBorder(dot_4, Black_Color, 6, White_Color, 100);
								}
								else {
									_RoundAndBorder(dot_1, Black_Color, 6, Black_Color, 100);
									_RoundAndBorder(dot_2, Black_Color, 6, Black_Color, 100);
									_RoundAndBorder(dot_3, Black_Color, 6, Black_Color, 100);
									_RoundAndBorder(dot_4, White_Color, 6, Black_Color, 100);
								}
							}
							else {
								if (_charSeq.length() == 4) {
									if (dm.getString("dark", "").equals("1")) {
										_RoundAndBorder(dot_1, White_Color, 6, White_Color, 100);
										_RoundAndBorder(dot_2, White_Color, 6, White_Color, 100);
										_RoundAndBorder(dot_3, White_Color, 6, White_Color, 100);
										_RoundAndBorder(dot_4, White_Color, 6, White_Color, 100);
										if (security.getString("pin", "").equals(pin_edittext.getText().toString())) {
											in.setClass(getApplicationContext(), HomeActivity.class);
											startActivity(in);
											Animatoo.animateZoom(PinActivity.this);
											finish();
										}
										else {
											SketchwareUtil.showMessage(getApplicationContext(), "Please enter correct password");
											textview1.setVisibility(View.VISIBLE);
										}
										pin_edittext.setText("");
									}
									else {
										_RoundAndBorder(dot_1, Black_Color, 6, Black_Color, 100);
										_RoundAndBorder(dot_2, Black_Color, 6, Black_Color, 100);
										_RoundAndBorder(dot_3, Black_Color, 6, Black_Color, 100);
										_RoundAndBorder(dot_4, Black_Color, 6, Black_Color, 100);
										if (security.getString("pin", "").equals(pin_edittext.getText().toString())) {
											in.setClass(getApplicationContext(), HomeActivity.class);
											startActivity(in);
											Animatoo.animateZoom(PinActivity.this);
											finish();
										}
										else {
											SketchwareUtil.showMessage(getApplicationContext(), "Please enter correct password");
											textview1.setVisibility(View.VISIBLE);
										}
										pin_edittext.setText("");
									}
								}
								else {
									if (_charSeq.length() > 4) {
										if (dm.getString("dark", "").equals("1")) {
											pin_edittext.setText(_charSeq.substring((int)(0), (int)(4)));
											_RoundAndBorder(dot_1, White_Color, 6, White_Color, 100);
											_RoundAndBorder(dot_2, White_Color, 6, White_Color, 100);
											_RoundAndBorder(dot_3, White_Color, 6, White_Color, 100);
											_RoundAndBorder(dot_4, White_Color, 6, White_Color, 100);
											if (security.getString("pin", "").equals(pin_edittext.getText().toString())) {
												in.setClass(getApplicationContext(), HomeActivity.class);
												startActivity(in);
												Animatoo.animateZoom(PinActivity.this);
												finish();
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Please enter correct password");
												textview1.setVisibility(View.VISIBLE);
											}
											pin_edittext.setText("");
										}
										else {
											pin_edittext.setText(_charSeq.substring((int)(0), (int)(4)));
											_RoundAndBorder(dot_1, Black_Color, 6, Black_Color, 100);
											_RoundAndBorder(dot_2, Black_Color, 6, Black_Color, 100);
											_RoundAndBorder(dot_3, Black_Color, 6, Black_Color, 100);
											_RoundAndBorder(dot_4, Black_Color, 6, Black_Color, 100);
											if (security.getString("pin", "").equals(pin_edittext.getText().toString())) {
												in.setClass(getApplicationContext(), HomeActivity.class);
												startActivity(in);
												Animatoo.animateZoom(PinActivity.this);
												finish();
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Please enter correct password");
												textview1.setVisibility(View.VISIBLE);
											}
											pin_edittext.setText("");
										}
									}
									else {
										
									}
								}
							}
						}
					}
					linear20.setVisibility(View.VISIBLE);
				}
				else {
					if (dm.getString("dark", "").equals("1")) {
						_RoundAndBorder(dot_1, Black_Color, 6, White_Color, 100);
						_RoundAndBorder(dot_2, Black_Color, 6, White_Color, 100);
						_RoundAndBorder(dot_3, Black_Color, 6, White_Color, 100);
						_RoundAndBorder(dot_4, Black_Color, 6, White_Color, 100);
						linear20.setVisibility(View.INVISIBLE);
					}
					else {
						_RoundAndBorder(dot_1, White_Color, 6, Black_Color, 100);
						_RoundAndBorder(dot_2, White_Color, 6, Black_Color, 100);
						_RoundAndBorder(dot_3, White_Color, 6, Black_Color, 100);
						_RoundAndBorder(dot_4, White_Color, 6, Black_Color, 100);
						linear20.setVisibility(View.INVISIBLE);
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		linear11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("1"));
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("2"));
			}
		});
		
		linear13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("3"));
			}
		});
		
		linear14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("4"));
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("5"));
			}
		});
		
		linear16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("6"));
			}
		});
		
		linear17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("7"));
			}
		});
		
		linear18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("8"));
			}
		});
		
		linear19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("9"));
			}
		});
		
		linear20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(pin_edittext.getText().toString().length() == 0)) {
					pin_edittext.setText(pin_edittext.getText().toString().substring((int)(0), (int)(pin_edittext.getText().toString().length() - 1)));
				}
			}
		});
		
		linear21.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pin_edittext.setText(pin_edittext.getText().toString().concat("0"));
			}
		});
		
		linear22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		White_Color = "#FFFFFF";
		Black_Color = "#000000";
		pin_edittext.setText("");
		linear20.setVisibility(View.INVISIBLE);
		textview1.setVisibility(View.GONE);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			main.setBackgroundColor(0xFF000000);
			imageview2.setImageResource(R.drawable.round_close);
			one.setTextColor(0xFFFFFFFF);
			two.setTextColor(0xFFFFFFFF);
			three.setTextColor(0xFFFFFFFF);
			four.setTextColor(0xFFFFFFFF);
			five.setTextColor(0xFFFFFFFF);
			six.setTextColor(0xFFFFFFFF);
			seven.setTextColor(0xFFFFFFFF);
			eight.setTextColor(0xFFFFFFFF);
			nine.setTextColor(0xFFFFFFFF);
			zero.setTextColor(0xFFFFFFFF);
			enter_title.setTextColor(0xFFFFFFFF);
			_RoundAndBorder(dot_1, Black_Color, 6, White_Color, 100);
			_RoundAndBorder(dot_2, Black_Color, 6, White_Color, 100);
			_RoundAndBorder(dot_3, Black_Color, 6, White_Color, 100);
			_RoundAndBorder(dot_4, Black_Color, 6, White_Color, 100);
		}
		else {
			main.setBackgroundColor(0xFFFFFFFF);
			imageview2.setImageResource(R.drawable.ic_close_black);
			one.setTextColor(0xFF000000);
			two.setTextColor(0xFF000000);
			three.setTextColor(0xFF000000);
			four.setTextColor(0xFF000000);
			five.setTextColor(0xFF000000);
			six.setTextColor(0xFF000000);
			seven.setTextColor(0xFF000000);
			eight.setTextColor(0xFF000000);
			nine.setTextColor(0xFF000000);
			zero.setTextColor(0xFF000000);
			enter_title.setTextColor(0xFF000000);
			_RoundAndBorder(dot_1, White_Color, 6, Black_Color, 100);
			_RoundAndBorder(dot_2, White_Color, 6, Black_Color, 100);
			_RoundAndBorder(dot_3, White_Color, 6, Black_Color, 100);
			_RoundAndBorder(dot_4, White_Color, 6, Black_Color, 100);
		}
	}
	public void _Animator(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
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
