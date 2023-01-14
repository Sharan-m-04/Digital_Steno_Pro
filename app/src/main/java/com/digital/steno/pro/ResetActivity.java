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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ResetActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String max = "";
	private double number = 0;
	private String phoneNumber = "";
	private String otp = "";
	private String verificationCode = "";
	
	private LinearLayout linear7;
	private LinearLayout mobile_no_linear;
	private LinearLayout otp_linear;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ProgressBar progressbar2;
	private TextView textview1;
	private EditText mobile_no_edittext;
	private Button send_otp;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private ProgressBar progressbar3;
	private LinearLayout linear11;
	private EditText otp_edittext;
	private Button varify;
	private TextView resend_otp;
	private TextView textview3;
	private TextView timer;
	
	private Intent in = new Intent();
	private SharedPreferences security;
	private SharedPreferences dm;
	private RequestNetwork ip;
	private RequestNetwork.RequestListener _ip_request_listener;
	private TimerTask t;
	private TimerTask time;
	private FirebaseAuth phoneAuth;
	private OnCompleteListener<AuthResult> _phoneAuth_create_user_listener;
	private OnCompleteListener<AuthResult> _phoneAuth_sign_in_listener;
	private OnCompleteListener<Void> _phoneAuth_reset_password_listener;
	private OnCompleteListener<Void> phoneAuth_updateEmailListener;
	private OnCompleteListener<Void> phoneAuth_updatePasswordListener;
	private OnCompleteListener<Void> phoneAuth_emailVerificationSentListener;
	private OnCompleteListener<Void> phoneAuth_deleteUserListener;
	private OnCompleteListener<Void> phoneAuth_updateProfileListener;
	private OnCompleteListener<AuthResult> phoneAuth_phoneAuthListener;
	private OnCompleteListener<AuthResult> phoneAuth_googleSignInListener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.reset);
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
		linear7 = findViewById(R.id.linear7);
		mobile_no_linear = findViewById(R.id.mobile_no_linear);
		otp_linear = findViewById(R.id.otp_linear);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		progressbar2 = findViewById(R.id.progressbar2);
		textview1 = findViewById(R.id.textview1);
		mobile_no_edittext = findViewById(R.id.mobile_no_edittext);
		send_otp = findViewById(R.id.send_otp);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		progressbar3 = findViewById(R.id.progressbar3);
		linear11 = findViewById(R.id.linear11);
		otp_edittext = findViewById(R.id.otp_edittext);
		varify = findViewById(R.id.varify);
		resend_otp = findViewById(R.id.resend_otp);
		textview3 = findViewById(R.id.textview3);
		timer = findViewById(R.id.timer);
		security = getSharedPreferences("security", Activity.MODE_PRIVATE);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		ip = new RequestNetwork(this);
		phoneAuth = FirebaseAuth.getInstance();
		
		mobile_no_edittext.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 10) {
					max = _charSeq.substring((int)(0), (int)(10));
					mobile_no_edittext.setText("");
				}
				if (_charSeq.length() == 0) {
					mobile_no_edittext.append(max);
					max = "";
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		send_otp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(send_otp, "scaleX", 1.10d, 200);
				_Animator(send_otp, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(send_otp, "scaleX", 1, 200);
								_Animator(send_otp, "scaleY", 1, 200);
								if (mobile_no_edittext.getText().toString().equals("")) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please enter your mobile number");
								}
								else {
									send_otp.setVisibility(View.GONE);
									progressbar2.setVisibility(View.VISIBLE);
									phoneNumber = textview1.getText().toString().concat(mobile_no_edittext.getText().toString());
									
									 
									com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(
									                phoneNumber,   
									                (long)60, java.util.concurrent.TimeUnit.SECONDS, 
									ResetActivity.this,
									              mCallback
									    
									);
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		varify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(varify, "scaleX", 1.10d, 200);
				_Animator(varify, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(varify, "scaleX", 1, 200);
								_Animator(varify, "scaleY", 1, 200);
								varify.setVisibility(View.GONE);
								progressbar3.setVisibility(View.VISIBLE);
								otp = otp_edittext.getText().toString();
								com.google.firebase.auth.PhoneAuthCredential credential = com.google.firebase.auth.PhoneAuthProvider.getCredential(verificationCode, otp);
								phoneAuth.signInWithCredential(credential).addOnCompleteListener(ResetActivity.this, _phoneAuth_sign_in_listener);
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		resend_otp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(resend_otp, "scaleX", 1.10d, 200);
				_Animator(resend_otp, "scaleY", 1.10d, 200);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(resend_otp, "scaleX", 1, 200);
								_Animator(resend_otp, "scaleY", 1, 200);
								if (!(Double.parseDouble(timer.getText().toString()) == 0)) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please wait...");
								}
								else {
									
									 
									com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(
									                phoneNumber,   
									                (long)60, java.util.concurrent.TimeUnit.SECONDS, 
									ResetActivity.this,
									              mCallback
									    
									);
									timer.setText("60");
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		_ip_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				textview1.setText(_response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		phoneAuth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		phoneAuth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_phoneAuth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					in.setClass(getApplicationContext(), SecurityActivity.class);
					startActivity(in);
					Animatoo.animateZoom(ResetActivity.this);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_phoneAuth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					in.setClass(getApplicationContext(), SecurityActivity.class);
					startActivity(in);
					Animatoo.animateZoom(ResetActivity.this);
					finish();
				}
				else {
					send_otp.setVisibility(View.VISIBLE);
					progressbar2.setVisibility(View.GONE);
					SketchwareUtil.showMessage(getApplicationContext(), "Verification code invalid");
				}
			}
		};
		
		_phoneAuth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		setTitle("Reset Password");
		progressbar2.setVisibility(View.GONE);
		progressbar3.setVisibility(View.GONE);
		ip.startRequestNetwork(RequestNetworkController.GET, "https://ipapi.co/country_calling_code", "Calling_Code", _ip_request_listener);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear7.setBackgroundColor(0xFF212121);
			mobile_no_linear.setBackgroundColor(0xFF000000);
			otp_linear.setBackgroundColor(0xFF000000);
			mobile_no_edittext.setTextColor(0xFFFFFFFF);
			textview1.setTextColor(0xFFFFFFFF);
			send_otp.setTextColor(0xFFFFFFFF);
			varify.setTextColor(0xFFFFFFFF);
			textview3.setTextColor(0xFFFFFFFF);
			timer.setTextColor(0xFFFFFFFF);
			otp_edittext.setTextColor(0xFFFFFFFF);
		}
		else {
			linear7.setBackgroundColor(0xFFFFFFFF);
			mobile_no_linear.setBackgroundColor(0xFFFFFFFF);
			otp_linear.setBackgroundColor(0xFFFFFFFF);
			mobile_no_edittext.setTextColor(0xFF000000);
			textview1.setTextColor(0xFF000000);
			send_otp.setTextColor(0xFFFFFFFF);
			varify.setTextColor(0xFFFFFFFF);
			otp_edittext.setTextColor(0xFF000000);
			timer.setTextColor(0xFF000000);
			textview3.setTextColor(0xFF000000);
		}
	}
	
	@Override
	public void onBackPressed() {
		in.setClass(getApplicationContext(), PinActivity.class);
		startActivity(in);
		Animatoo.animateZoom(ResetActivity.this);
		finish();
	}
	public void _StartFirebaseLogin() {
	}
	com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
		        
		        @Override
		        public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential phoneAuthCredential) {
			            Toast.makeText(ResetActivity.this,"Code Received",Toast.LENGTH_SHORT).show();
			        }
		
		        @Override
		        public void onVerificationFailed(com.google.firebase.FirebaseException e) {
			            Toast.makeText(ResetActivity.this,"verification fialed",Toast.LENGTH_SHORT).show();
			send_otp.setVisibility(View.VISIBLE);
		progressbar2.setVisibility(View.GONE);
			        }
		
		        @Override
		        public void onCodeSent(String s, com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
			            super.onCodeSent(s, forceResendingToken);
			            verificationCode = s;
			            
			mobile_no_linear.setVisibility(View.GONE);
			otp_linear.setVisibility(View.VISIBLE);
			send_otp.setVisibility(View.VISIBLE);
			progressbar2.setVisibility(View.GONE);
			number = Double.parseDouble(timer.getText().toString());
			timer.setText(String.valueOf((long)(number)));
			t = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							number--;
							timer.setText(String.valueOf((long)(number)));
							if (number == 0) {
								timer.setText("00");
								t.cancel();
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(t, (int)(0), (int)(1000));
			Toast.makeText(ResetActivity.this,"Code sent",Toast.LENGTH_SHORT).show();
			        }
		    };
	{
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
