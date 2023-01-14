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
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.facebook.shimmer.*;
import com.google.android.gms.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class SettingsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double repeat = 0;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private ImageView imageview1;
	private Button button1;
	private LinearLayout linear6;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button9;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button13;
	private ImageView imageview3;
	private Switch switch1;
	private ImageView imageview2;
	private Switch switch2;
	private ImageView imageview4;
	private Button button14;
	
	private Intent in = new Intent();
	private SharedPreferences dm;
	private SharedPreferences theme;
	private SharedPreferences link;
	private TimerTask time;
	private SharedPreferences r;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		hscroll1 = findViewById(R.id.hscroll1);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear7 = findViewById(R.id.linear7);
		imageview1 = findViewById(R.id.imageview1);
		button1 = findViewById(R.id.button1);
		linear6 = findViewById(R.id.linear6);
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		button5 = findViewById(R.id.button5);
		button6 = findViewById(R.id.button6);
		button7 = findViewById(R.id.button7);
		button9 = findViewById(R.id.button9);
		button10 = findViewById(R.id.button10);
		button11 = findViewById(R.id.button11);
		button12 = findViewById(R.id.button12);
		button13 = findViewById(R.id.button13);
		imageview3 = findViewById(R.id.imageview3);
		switch1 = findViewById(R.id.switch1);
		imageview2 = findViewById(R.id.imageview2);
		switch2 = findViewById(R.id.switch2);
		imageview4 = findViewById(R.id.imageview4);
		button14 = findViewById(R.id.button14);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		theme = getSharedPreferences("theme", Activity.MODE_PRIVATE);
		link = getSharedPreferences("link", Activity.MODE_PRIVATE);
		r = getSharedPreferences("r", Activity.MODE_PRIVATE);
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (switch2.isChecked()) {
					switch2.setChecked(false);
					link.edit().putString("Detect Link", "False").commit();
				}
				else {
					switch2.setChecked(true);
					link.edit().putString("Detect Link", "True").commit();
				}
			}
		});
		
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
								repeat++;
								if (repeat == 1) {
									hscroll1.setVisibility(View.VISIBLE);
								}
								if (repeat == 2) {
									repeat = 0;
									hscroll1.setVisibility(View.GONE);
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button1, "scaleX", 1.10d, 200);
				_Animator(button1, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button1, "scaleX", 1, 200);
								_Animator(button1, "scaleY", 1, 200);
								repeat++;
								if (repeat == 1) {
									hscroll1.setVisibility(View.VISIBLE);
								}
								if (repeat == 2) {
									repeat = 0;
									hscroll1.setVisibility(View.GONE);
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button3, "scaleX", 1.10d, 200);
				_Animator(button3, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button3, "scaleX", 1, 200);
								_Animator(button3, "scaleY", 1, 200);
								theme.edit().putString("themes", "0").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button4, "scaleX", 1.10d, 200);
				_Animator(button4, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button4, "scaleX", 1, 200);
								_Animator(button4, "scaleY", 1, 200);
								theme.edit().putString("themes", "1").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button5, "scaleX", 1.10d, 200);
				_Animator(button5, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button5, "scaleX", 1, 200);
								_Animator(button5, "scaleY", 1, 200);
								theme.edit().putString("themes", "2").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button6, "scaleX", 1.10d, 200);
				_Animator(button6, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button6, "scaleX", 1, 200);
								_Animator(button6, "scaleY", 1, 200);
								theme.edit().putString("themes", "3").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button7, "scaleX", 1.10d, 200);
				_Animator(button7, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button7, "scaleX", 1, 200);
								_Animator(button7, "scaleY", 1, 200);
								theme.edit().putString("themes", "4").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button9, "scaleX", 1.10d, 200);
				_Animator(button9, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button9, "scaleX", 1, 200);
								_Animator(button9, "scaleY", 1, 200);
								theme.edit().putString("themes", "5").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button10, "scaleX", 1.10d, 200);
				_Animator(button10, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button10, "scaleX", 1, 200);
								_Animator(button10, "scaleY", 1, 200);
								theme.edit().putString("themes", "6").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button11, "scaleX", 1.10d, 200);
				_Animator(button11, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button11, "scaleX", 1, 200);
								_Animator(button11, "scaleY", 1, 200);
								theme.edit().putString("themes", "7").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button12, "scaleX", 1.10d, 200);
				_Animator(button12, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button12, "scaleX", 1, 200);
								_Animator(button12, "scaleY", 1, 200);
								theme.edit().putString("themes", "8").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button13, "scaleX", 1.10d, 200);
				_Animator(button13, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button13, "scaleX", 1, 200);
								_Animator(button13, "scaleY", 1, 200);
								theme.edit().putString("themes", "9").commit();
								in.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(in);
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
				
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					dm.edit().putString("dark", "1").commit();
					linear1.setBackgroundColor(0xFF212121);
					imageview1.setImageResource(R.drawable.theme_2);
					imageview2.setImageResource(R.drawable.ic_link_white);
					imageview3.setImageResource(R.drawable.dark_theme_2);
					switch1.setTextColor(0xFFFFFFFF);
					button1.setTextColor(0xFFFFFFFF);
					switch2.setTextColor(0xFFFFFFFF);
					button14.setTextColor(0xFFFFFFFF);
					imageview4.setImageResource(R.drawable.security_logo_white);
				}
				else {
					dm.edit().putString("dark", "0").commit();
					linear1.setBackgroundColor(0xFFFFFFFF);
					imageview1.setImageResource(R.drawable.theme_1);
					imageview2.setImageResource(R.drawable.ic_link_black);
					imageview3.setImageResource(R.drawable.dark_theme_1);
					switch1.setTextColor(0xFF000000);
					button1.setTextColor(0xFF000000);
					switch2.setTextColor(0xFF000000);
					button14.setTextColor(0xFF000000);
					imageview4.setImageResource(R.drawable.security_logo_1);
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (switch2.isChecked()) {
					switch2.setChecked(false);
					link.edit().putString("Detect Link", "False").commit();
				}
				else {
					switch2.setChecked(true);
					link.edit().putString("Detect Link", "True").commit();
				}
			}
		});
		
		switch2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (switch2.isChecked()) {
					switch2.setChecked(false);
					link.edit().putString("Detect Link", "False").commit();
				}
				else {
					switch2.setChecked(true);
					link.edit().putString("Detect Link", "True").commit();
				}
			}
		});
		
		switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				
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
								in.setClass(getApplicationContext(), SecurityActivity.class);
								startActivity(in);
								Animatoo.animateZoom(SettingsActivity.this);
								finish();
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(button14, "scaleX", 1.10d, 200);
				_Animator(button14, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(button14, "scaleX", 1, 200);
								_Animator(button14, "scaleY", 1, 200);
								in.setClass(getApplicationContext(), SecurityActivity.class);
								startActivity(in);
								Animatoo.animateZoom(SettingsActivity.this);
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
		setTitle("Settings");
		hscroll1.setVisibility(View.GONE);
		switch2.setChecked(true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear1.setBackgroundColor(0xFF212121);
			imageview1.setImageResource(R.drawable.theme_2);
			imageview2.setImageResource(R.drawable.ic_link_white);
			imageview3.setImageResource(R.drawable.dark_theme_2);
			switch1.setTextColor(0xFFFFFFFF);
			button1.setTextColor(0xFFFFFFFF);
			switch2.setTextColor(0xFFFFFFFF);
			button14.setTextColor(0xFFFFFFFF);
			imageview4.setImageResource(R.drawable.security_logo_white);
			switch1.setChecked(true);
		}
		else {
			linear1.setBackgroundColor(0xFFFFFFFF);
			imageview1.setImageResource(R.drawable.theme_1);
			imageview2.setImageResource(R.drawable.ic_link_black);
			imageview3.setImageResource(R.drawable.dark_theme_1);
			switch1.setTextColor(0xFF000000);
			button1.setTextColor(0xFF000000);
			switch2.setTextColor(0xFF000000);
			button14.setTextColor(0xFF000000);
			imageview4.setImageResource(R.drawable.security_logo_1);
		}
		if (link.getString("Detect Link", "").equals("True")) {
			switch2.setChecked(true);
		}
		else {
			switch2.setChecked(false);
		}
	}
	
	@Override
	public void onBackPressed() {
		in.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(in);
		Animatoo.animateZoom(SettingsActivity.this);
		finish();
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
