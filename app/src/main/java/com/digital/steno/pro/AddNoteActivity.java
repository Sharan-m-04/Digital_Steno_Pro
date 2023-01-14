package com.digital.steno.pro;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class AddNoteActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double position = 0;
	private String share = "";
	private String clipdata = "";
	private double textSize = 0;
	private String dataText = "";
	private double counts = 0;
	private double repeat = 0;
	private String texts = "";
	private double length = 0;
	private double counting = 0;
	private double y = 0;
	private String translation = "";
	private String TranslationResult = "";
	private String path = "";
	private HashMap<String, Object> map = new HashMap<>();
	private double repeat_bold = 0;
	private double repeat_italics = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> text = new ArrayList<>();
	private ArrayList<String> spinlist = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear8;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear4;
	private ImageView imageview2;
	private LinearLayout linear16;
	private ImageView imageview15;
	private LinearLayout linear17;
	private ImageView imageview17;
	private LinearLayout linear18;
	private EditText edittext1;
	private EditText edittext3;
	private TextView dummy_textsize;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private ImageView imageview7;
	private ImageView imageview8;
	private ImageView imageview6;
	private ImageView imageview9;
	private ImageView imageview10;
	private ImageView imageview11;
	private ImageView imageview12;
	private ImageView imageview13;
	private LinearLayout textsize_linear;
	private TextView textsize_textview;
	private Button plus_button;
	private Button minus_button;
	private EditText edittext2;
	private TextView textview3;
	private TextView textview4;
	private Spinner spinner2;
	private Button button1;
	
	private SpeechRecognizer stt;
	private Intent in = new Intent();
	private SharedPreferences sp;
	private AlertDialog.Builder save;
	private SharedPreferences dm;
	private TimerTask time;
	private SharedPreferences back;
	private SharedPreferences textSizeSave;
	private RequestNetwork InTeRnEt;
	private RequestNetwork.RequestListener _InTeRnEt_request_listener;
	private AlertDialog.Builder discard;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private DatabaseReference db = _firebase.getReference("users/data");
	private ChildEventListener _db_child_listener;
	private AlertDialog dial;
	private com.google.android.material.bottomsheet.BottomSheetDialog bs;
	private SharedPreferences bold;
	private SharedPreferences italics;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.add_note);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
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
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear8 = findViewById(R.id.linear8);
		linear14 = findViewById(R.id.linear14);
		linear15 = findViewById(R.id.linear15);
		imageview1 = findViewById(R.id.imageview1);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		linear4 = findViewById(R.id.linear4);
		imageview2 = findViewById(R.id.imageview2);
		linear16 = findViewById(R.id.linear16);
		imageview15 = findViewById(R.id.imageview15);
		linear17 = findViewById(R.id.linear17);
		imageview17 = findViewById(R.id.imageview17);
		linear18 = findViewById(R.id.linear18);
		edittext1 = findViewById(R.id.edittext1);
		edittext3 = findViewById(R.id.edittext3);
		dummy_textsize = findViewById(R.id.dummy_textsize);
		hscroll1 = findViewById(R.id.hscroll1);
		linear12 = findViewById(R.id.linear12);
		linear13 = findViewById(R.id.linear13);
		imageview3 = findViewById(R.id.imageview3);
		imageview4 = findViewById(R.id.imageview4);
		imageview5 = findViewById(R.id.imageview5);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		imageview6 = findViewById(R.id.imageview6);
		imageview9 = findViewById(R.id.imageview9);
		imageview10 = findViewById(R.id.imageview10);
		imageview11 = findViewById(R.id.imageview11);
		imageview12 = findViewById(R.id.imageview12);
		imageview13 = findViewById(R.id.imageview13);
		textsize_linear = findViewById(R.id.textsize_linear);
		textsize_textview = findViewById(R.id.textsize_textview);
		plus_button = findViewById(R.id.plus_button);
		minus_button = findViewById(R.id.minus_button);
		edittext2 = findViewById(R.id.edittext2);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		spinner2 = findViewById(R.id.spinner2);
		button1 = findViewById(R.id.button1);
		stt = SpeechRecognizer.createSpeechRecognizer(this);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		save = new AlertDialog.Builder(this);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		back = getSharedPreferences("back", Activity.MODE_PRIVATE);
		textSizeSave = getSharedPreferences("textSizeSave", Activity.MODE_PRIVATE);
		InTeRnEt = new RequestNetwork(this);
		discard = new AlertDialog.Builder(this);
		auth = FirebaseAuth.getInstance();
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
								Animatoo.animateZoom(AddNoteActivity.this);
								finish();
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
								if (edittext1.getText().toString().equals("") || edittext3.getText().toString().equals("")) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please Enter Title And Note");
									in.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(in);
									Animatoo.animateZoom(AddNoteActivity.this);
									finish();
								}
								else {
									map = new HashMap<>();
									map.put("note", edittext1.getText().toString().trim());
									map.put("title", edittext3.getText().toString().trim());
									if (position == -1) {
										listmap.add(map);
									}
									else {
										listmap.set((int)(position), map);
									}
									sp.edit().putString("allnotes", new Gson().toJson(listmap)).commit();
									in.setClass(getApplicationContext(), HomeActivity.class);
									startActivity(in);
									Animatoo.animateZoom(AddNoteActivity.this);
									finish();
								}
							}
						});
					}
				};
				_timer.schedule(time, (int)(200));
			}
		});
		
		imageview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Note Cannot Be Empty");
				}
				else {
					_Animator(imageview15, "scaleX", 1.10d, 200);
					_Animator(imageview15, "scaleY", 1.10d, 200);
					time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_Animator(imageview15, "scaleX", 1, 200);
									_Animator(imageview15, "scaleY", 1, 200);
									in.putExtra("pdf", edittext1.getText().toString());
									in.setClass(getApplicationContext(), SaveAsPdfActivity.class);
									startActivity(in);
									Animatoo.animateZoom(AddNoteActivity.this);
									map = new HashMap<>();
									map.put("note", edittext1.getText().toString().trim());
									map.put("title", edittext3.getText().toString().trim());
									if (position == -1) {
										listmap.add(map);
									}
									else {
										listmap.set((int)(position), map);
									}
									sp.edit().putString("allnotes", new Gson().toJson(listmap)).commit();
									finish();
								}
							});
						}
					};
					_timer.schedule(time, (int)(200));
				}
			}
		});
		
		imageview17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Notes Cannot Be Empty");
				}
				else {
					_Animator(imageview17, "scaleX", 1.10d, 200);
					_Animator(imageview17, "scaleY", 1.10d, 200);
					time = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_Animator(imageview17, "scaleX", 1, 200);
									_Animator(imageview17, "scaleY", 1, 200);
									final AlertDialog dial = new AlertDialog.Builder(AddNoteActivity.this).create();
									LayoutInflater dialLI = getLayoutInflater();
									View dialCV = (View) dialLI.inflate(R.layout.txt_dialog, null);
									dial.setView(dialCV);
									dial.setCancelable(true);
									final LinearLayout l1 = (LinearLayout)
									dialCV.findViewById(R.id.linear1);
									final LinearLayout l2 = (LinearLayout)
									dialCV.findViewById(R.id.linear2);
									final EditText e1 = (EditText)
									dialCV.findViewById(R.id.edittext1);
									final Button b1 = (Button)
									dialCV.findViewById(R.id.button1);
									final Button b2 = (Button)
									dialCV.findViewById(R.id.button2);
									dial.show();
									e1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFE0E0E0));
									b1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF03A9F4));
									b2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF03A9F4));
									b1.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											dial.dismiss();
										}
									});
									b2.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/Digital Steno Pro/Saved TXT Files/".concat(e1.getText().toString().concat(".txt"))), edittext1.getText().toString());
											map = new HashMap<>();
											map.put("note", edittext1.getText().toString().trim());
											map.put("title", edittext3.getText().toString().trim());
											if (position == -1) {
												listmap.add(map);
											}
											else {
												listmap.set((int)(position), map);
											}
											sp.edit().putString("allnotes", new Gson().toJson(listmap)).commit();
											SketchwareUtil.showMessage(getApplicationContext(), "File Saved");
											in.setClass(getApplicationContext(), HomeActivity.class);
											startActivity(in);
											Animatoo.animateZoom(AddNoteActivity.this);
											finish();
										}
									});
								}
							});
						}
					};
					_timer.schedule(time, (int)(200));
				}
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview2.setText("Characters:".concat(String.valueOf((long)(_charSeq.length()))));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
				stt.startListening(_intent);
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				stt.stopListening();
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", edittext1.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied To Clipboard");
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_getClipboardData(edittext1);
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				share = edittext1.getText().toString();
				Intent i = new Intent(android.content.Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Share using"));
			}
		});
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				repeat_bold++;
				if (repeat_bold == 1) {
					if (italics.getString("italics", "").equals("1")) {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.BOLD_ITALIC);
						bold.edit().putString("bold", "1").commit();
					}
					else {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.BOLD);
						bold.edit().putString("bold", "1").commit();
					}
				}
				if (repeat_bold == 2) {
					repeat_bold = 0;
					if (italics.getString("italics", "").equals("1")) {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.ITALIC);
						bold.edit().putString("bold", "0").commit();
					}
					else {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.NORMAL);
						bold.edit().putString("bold", "0").commit();
					}
				}
			}
		});
		
		imageview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				repeat_italics++;
				if (repeat_italics == 1) {
					if (bold.getString("bold", "").equals("1")) {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.BOLD_ITALIC);
						bold.edit().putString("italics", "1").commit();
					}
					else {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.ITALIC);
						bold.edit().putString("italics", "1").commit();
					}
				}
				if (repeat_italics == 2) {
					repeat_italics = 0;
					if (italics.getString("bold", "").equals("1")) {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.BOLD);
						bold.edit().putString("italics", "0").commit();
					}
					else {
						edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular_400.ttf"), Typeface.NORMAL);
						bold.edit().putString("italics", "0").commit();
					}
				}
			}
		});
		
		imageview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				repeat++;
				if (repeat == 1) {
					textsize_linear.setVisibility(View.VISIBLE);
				}
				if (repeat == 2) {
					repeat = 0;
					textsize_linear.setVisibility(View.GONE);
				}
			}
		});
		
		imageview12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				repeat++;
				if (repeat == 1) {
					linear14.setVisibility(View.VISIBLE);
				}
				if (repeat == 2) {
					repeat = 0;
					linear14.setVisibility(View.GONE);
					edittext2.setText("");
				}
			}
		});
		
		imageview13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				repeat++;
				if (repeat == 1) {
					linear15.setVisibility(View.VISIBLE);
				}
				if (repeat == 2) {
					repeat = 0;
					linear15.setVisibility(View.GONE);
				}
			}
		});
		
		plus_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textSize++;
				textsize_textview.setText(String.valueOf((long)(textSize)));
				textsize_textview.setTextSize((int)textSize);
				edittext1.setTextSize((int)textSize);
				/*
textSizeSave.edit().putString("tss", String.valueOf((long)(textSize))).commit();
*/
			}
		});
		
		minus_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textSize--;
				textsize_textview.setText(String.valueOf((long)(textSize)));
				textsize_textview.setTextSize((int)textSize);
				edittext1.setTextSize((int)textSize);
				/*
textSizeSave.edit().putString("tss", String.valueOf((long)(textSize))).commit();
*/
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				texts = edittext1.getText().toString();
				Spannable spannable1 = new SpannableString(texts);
				
				android.text.style.ForegroundColorSpan fgSpan = new android.text.style.ForegroundColorSpan(Color.BLACK);
				
				android.text.style.BackgroundColorSpan bgSpan = new android.text.style.BackgroundColorSpan(Color.YELLOW);
				length = _charSeq.length();
				counting = 0;
				y = 0;
				if (texts.contains(_charSeq) && (length > 0)) {
					for(int _repeat25 = 0; _repeat25 < (int)(((texts.length() - length) + 1)); _repeat25++) {
						if (texts.substring((int)(y), (int)(y + length)).equals(_charSeq)) {
							counting++;
							y++;
						}
						else {
							y++;
						}
					}
					int x = 0;
					for(int _repeat44 = 0; _repeat44 < (int)(counting); _repeat44++) {
						int n = texts.indexOf(_charSeq.toString(), x);
						
						x = n+1;
						
						spannable1.setSpan(android.text.style.CharacterStyle.wrap(fgSpan), n, n + _charSeq.length(), 0);
						
						spannable1.setSpan(android.text.style.CharacterStyle.wrap(bgSpan), n, n + _charSeq.length(), 0);
					}
				}
				edittext1.setText(spannable1);
				textview3.setText("found: ".concat(String.valueOf((long)(counting))));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_translate(edittext1.getText().toString());
				_translateNow(edittext1);
			}
		});
		
		stt.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				edittext1.setText(_result);
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				
			}
		});
		
		_InTeRnEt_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
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
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_card_style(linear1, 20, 0, "#304FFE");
		edittext3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFEEEEEE));
		if (!sp.getString("allnotes", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("allnotes", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		if (!sp.getString("pos", "").equals("")) {
			position = Double.parseDouble(sp.getString("pos", ""));
			edittext3.setText(listmap.get((int)position).get("title").toString());
			edittext1.setText(listmap.get((int)position).get("note").toString());
		}
		else {
			position = -1;
		}
		textSize = 16;
		textsize_linear.setVisibility(View.GONE);
		linear14.setVisibility(View.GONE);
		linear15.setVisibility(View.GONE);
		dataText = edittext1.getText().toString();
		edittext1.setText(dataText);
		/*
if (!textSizeSave.getString("tss", "").equals("")) {
textSize = Double.parseDouble(textSizeSave.getString("tss", ""));

}
else {
textSize = 16;
}
edittext1.setTextSize((int)Double.parseDouble("0"));
*/
		//also enable single line to show it on keyboard
		edittext1.setOnEditorActionListener(new TextView.OnEditorActionListener() { 
			@Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { if(actionId ==3){
					SketchwareUtil.hideKeyboard(getApplicationContext());
				} return false; } }); //GO 2 SEARCH 3 SEND 4 NEXT 5 DONE 6
		spinlist.add("Select Language");
		spinlist.add("AFRIKAANS");
		spinlist.add("ALBANIAN");
		spinlist.add("ARABIC");
		spinlist.add("ARMENIAN");
		spinlist.add("AUTO_DETECT");
		spinlist.add("AZERBAIJANI");
		spinlist.add("BASQUE");
		spinlist.add("BELARUSIAN");
		spinlist.add("BENGALI");
		spinlist.add("BULGARIAN");
		spinlist.add("CATALAN");
		spinlist.add("CHINESE");
		spinlist.add("CHINESE_SIMPLIFIED");
		spinlist.add("CHINESE_TRADITIONAL");
		spinlist.add("CROATIAN");
		spinlist.add("CZECH");
		spinlist.add("DANISH");
		spinlist.add("DUTCH");
		spinlist.add("ENGLISH");
		spinlist.add("ESTONIAN");
		spinlist.add("FILIPINO");
		spinlist.add("FINNISH");
		spinlist.add("FRENCH");
		spinlist.add("GALICIAN");
		spinlist.add("GEORGIAN");
		spinlist.add("GERMAN");
		spinlist.add("GREEK");
		spinlist.add("GUJARATI");
		spinlist.add("HAITIAN_CREOLE");
		spinlist.add("HEBREW");
		spinlist.add("HINDI");
		spinlist.add("HUNGARIAN");
		spinlist.add("ICELANDIC");
		spinlist.add("INDONESIAN");
		spinlist.add("IRISH");
		spinlist.add("ITALIAN");
		spinlist.add("JAPANESE");
		spinlist.add("KANNADA");
		spinlist.add("KOREAN");
		spinlist.add("LATIN");
		spinlist.add("LATVIAN");
		spinlist.add("LITHUANIAN");
		spinlist.add("MACEDONIAN");
		spinlist.add("MALAY");
		spinlist.add("MALTESE");
		spinlist.add("NORWEGIAN");
		spinlist.add("PERSIAN");
		spinlist.add("POLISH");
		spinlist.add("PORTUGUESE");
		spinlist.add("ROMANIAN");
		spinlist.add("RUSSIAN");
		spinlist.add("SERBIAN");
		spinlist.add("SLOVAK");
		spinlist.add("SLOVENIAN");
		spinlist.add("SPANISH");
		spinlist.add("SWAHILI");
		spinlist.add("SWEDISH");
		spinlist.add("TAMIL");
		spinlist.add("TELUGU");
		spinlist.add("THAI");
		spinlist.add("TURKISH");
		spinlist.add("UKRAINIAN");
		spinlist.add("URDU");
		spinlist.add("VIETNAMESE");
		spinlist.add("WELSH");
		spinlist.add("YIDDISH");
		spinner2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, spinlist));
		((ArrayAdapter)spinner2.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear2.setBackgroundColor(0xFF212121);
			linear8.setBackgroundColor(0xFF212121);
			edittext1.setTextColor(0xFFFFFFFF);
			linear3.setBackgroundColor(0xFF212121);
			linear14.setBackgroundColor(0xFF212121);
			linear15.setBackgroundColor(0xFF212121);
			textsize_linear.setBackgroundColor(0xFF212121);
			textview3.setTextColor(0xFFFFFFFF);
			edittext2.setTextColor(0xFFFFFFFF);
			textsize_textview.setTextColor(0xFFFFFFFF);
			plus_button.setBackgroundResource(R.drawable.ic_add_white);
			minus_button.setBackgroundResource(R.drawable.ic_remove_white);
			spinner2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, spinlist) {
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					TextView textView1 = (TextView) super.getView(position, convertView, parent);
					textView1.setTextColor(Color.WHITE);
					//textView1.setTextSize(24);
					return textView1; }
				
				/*
@Override
public View getDropDownView(int position, View convertView, ViewGroup parent) {
TextView textView1 = (TextView) super.getDropDownView(position, convertView, parent); textView1.setTextColor(Color.RED);
textView1.setTextSize(24);
return textView1; }*/
			});
			edittext3.setBackgroundColor(0xFF424242);
			edittext3.setTextColor(0xFFFFFFFF);
			textview4.setTextColor(0xFFFFFFFF);
		}
		else {
			linear2.setBackgroundColor(0xFFFFFFFF);
			linear8.setBackgroundColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFF000000);
			linear3.setBackgroundColor(0xFFFFFFFF);
			linear14.setBackgroundColor(0xFFFFFFFF);
			linear15.setBackgroundColor(0xFFFFFFFF);
			textsize_linear.setBackgroundColor(0xFFFFFFFF);
			textview3.setTextColor(0xFF000000);
			edittext2.setTextColor(0xFF000000);
			textsize_textview.setTextColor(0xFF000000);
			plus_button.setBackgroundResource(R.drawable.ic_add_black);
			minus_button.setBackgroundResource(R.drawable.ic_remove_black);
			spinner2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, spinlist) {
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					TextView textView1 = (TextView) super.getView(position, convertView, parent);
					textView1.setTextColor(Color.BLACK);
					//textView1.setTextSize(24);
					return textView1; }
				
				/*
@Override
public View getDropDownView(int position, View convertView, ViewGroup parent) {
TextView textView1 = (TextView) super.getDropDownView(position, convertView, parent); textView1.setTextColor(Color.RED);
textView1.setTextSize(24);
return textView1; }*/
			});
			edittext3.setBackgroundColor(0xFFEEEEEE);
			edittext3.setTextColor(0xFF000000);
			textview4.setTextColor(0xFF000000);
		}
	}
	
	@Override
	public void onBackPressed() {
		if (edittext1.getText().toString().equals("")) {
			in.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(in);
			Animatoo.animateZoom(AddNoteActivity.this);
			finish();
		}
		else {
			discard.setTitle("Do You Want To Discard This Note?");
			discard.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					if (back.getString("view", "").equals("true")) {
						in.setClass(getApplicationContext(), ViewNoteActivity.class);
						startActivity(in);
						Animatoo.animateZoom(AddNoteActivity.this);
						back.edit().putString("view", "false").commit();
						finish();
					}
					else {
						in.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(in);
						Animatoo.animateZoom(AddNoteActivity.this);
						finish();
					}
				}
			});
			discard.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			discard.create().show();
		}
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
	
	
	public void _getClipboardData(final TextView _text) {
		android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = clipboard.getPrimaryClip();
		if ((clipData != null)) {
			clipdata = clipData.getItemAt(0).getText().toString();;
			_text.setText(clipdata);
		}
		else {
			
		}
	}
	
	
	public void _Library() {
	}
	private TranslateAPI translator;
	public static class TranslateAPI {
		    String langFrom = null;
		    String langTo = null;
		    private TranslateListener listener;
		    String resp = null;
		    String url = null;
		    String word = null;
		
		    class Async extends android.os.AsyncTask<String, String, String> {
			        Async() {
				        }
			
			        protected String doInBackground(String... strings) {
				            try {
					                TranslateAPI.this.url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + TranslateAPI.this.langFrom + "&tl=" + TranslateAPI.this.langTo + "&dt=t&q=" + java.net.URLEncoder.encode(TranslateAPI.this.word, "UTF-8"); java.net.HttpURLConnection con = (java.net.HttpURLConnection) new java.net.URL(TranslateAPI.this.url).openConnection();
					                con.setRequestProperty("User-Agent", "Mozilla/5.0");
					                java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
					                StringBuffer response = new StringBuffer();
					                while (true) {
						                    String inputLine = in.readLine();
						                    if (inputLine == null) {
							                        break;
							                    }
						                    response.append(inputLine);
						                }
					                in.close();
					                TranslateAPI.this.resp = response.toString();
					            } catch (java.io.UnsupportedEncodingException e) {
					                e.printStackTrace();
					            } catch (java.net.MalformedURLException e2) {
					                e2.printStackTrace();
					            } catch (java.io.IOException e3) {
					                e3.printStackTrace();
					            } catch (Exception e4) {
					                e4.printStackTrace();
					            }
				            return null;
				        }
			
			        protected void onPreExecute() {
				            super.onPreExecute();
				        }
			
			        protected void onPostExecute(String s) {
				            String temp = "";
				            if (TranslateAPI.this.resp == null) {
					                TranslateAPI.this.listener.onFailure("Network Error");
					            } else {
					                try {
						                    org.json.JSONArray total = (org.json.JSONArray) new org.json.JSONArray(TranslateAPI.this.resp).get(0);
						                    for (int i = 0; i < total.length(); i++) {
							                        temp = temp + ((org.json.JSONArray) total.get(i)).get(0).toString();
							                    }
						                    android.util.Log.d("ContentValues", "onPostExecute: " + temp);
						                    if (temp.length() > 2) {
							                        TranslateAPI.this.listener.onSuccess(temp);
							                    } else {
							                        TranslateAPI.this.listener.onFailure("Invalid Input String");
							                    }
						                } catch (org.json.JSONException e) {
						                    TranslateAPI.this.listener.onFailure(e.getLocalizedMessage());
						                    e.printStackTrace();
						                }
					            }
				            super.onPostExecute(s);
				        }
			
			        protected void onProgressUpdate(String... values) {
				            super.onProgressUpdate(values);
				        }
			
			        protected void onCancelled(String s) {
				            super.onCancelled(s);
				        }
			    }
		
		    public interface TranslateListener {
			        void onFailure(String str);
			
			        void onSuccess(String str);
			    }
		
		    public TranslateAPI(String langFrom, String langTo, String text) {
			        this.langFrom = langFrom;
			        this.langTo = langTo;
			        this.word = text;
			        new Async().execute(new String[0]);
			    }
		
		    public void setTranslateListener(TranslateListener listener) {
			        this.listener = listener;
			    }
	}
	public static final String AFRIKAANS = "af";
	    public static final String ALBANIAN = "sq";
	    public static final String ARABIC = "ar";
	    public static final String ARMENIAN = "hy";
	    public static final String AUTO_DETECT = "auto";
	    public static final String AZERBAIJANI = "az";
	    public static final String BASQUE = "eu";
	    public static final String BELARUSIAN = "be";
	    public static final String BENGALI = "bn";
	    public static final String BULGARIAN = "bg";
	    public static final String CATALAN = "ca";
	    public static final String CHINESE = "zh-CN";
	    public static final String CHINESE_SIMPLIFIED = "zh-CN";
	    public static final String CHINESE_TRADITIONAL = "zh-TW";
	    public static final String CROATIAN = "hr";
	    public static final String CZECH = "cs";
	    public static final String DANISH = "da";
	    public static final String DUTCH = "nl";
	    public static final String ENGLISH = "en";
	    public static final String ESTONIAN = "et";
	    public static final String FILIPINO = "tl";
	    public static final String FINNISH = "fi";
	    public static final String FRENCH = "fr";
	    public static final String GALICIAN = "gl";
	    public static final String GEORGIAN = "ka";
	    public static final String GERMAN = "de";
	    public static final String GREEK = "el";
	    public static final String GUJARATI = "gu";
	    public static final String HAITIAN_CREOLE = "ht";
	    public static final String HEBREW = "iw";
	    public static final String HINDI = "hi";
	    public static final String HUNGARIAN = "hu";
	    public static final String ICELANDIC = "is";
	    public static final String INDONESIAN = "id";
	    public static final String IRISH = "ga";
	    public static final String ITALIAN = "it";
	    public static final String JAPANESE = "ja";
	    public static final String KANNADA = "kn";
	    public static final String KOREAN = "ko";
	    public static final String LATIN = "la";
	    public static final String LATVIAN = "lv";
	    public static final String LITHUANIAN = "lt";
	    public static final String MACEDONIAN = "mk";
	    public static final String MALAY = "ms";
	    public static final String MALTESE = "mt";
	    public static final String NORWEGIAN = "no";
	    public static final String PERSIAN = "fa";
	    public static final String POLISH = "pl";
	    public static final String PORTUGUESE = "pt";
	    public static final String ROMANIAN = "ro";
	    public static final String RUSSIAN = "ru";
	    public static final String SERBIAN = "sr";
	    public static final String SLOVAK = "sk";
	    public static final String SLOVENIAN = "sl";
	    public static final String SPANISH = "es";
	    public static final String SWAHILI = "sw";
	    public static final String SWEDISH = "sv";
	    public static final String TAMIL = "ta";
	    public static final String TELUGU = "te";
	    public static final String THAI = "th";
	    public static final String TURKISH = "tr";
	    public static final String UKRAINIAN = "uk";
	    public static final String URDU = "ur";
	    public static final String VIETNAMESE = "vi";
	    public static final String WELSH = "cy";
	    public static final String YIDDISH = "yi";
	public void libraryArabWareReMade () {
	}
	
	
	public void _translate(final String _translate) {
		if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("0")) {
			SketchwareUtil.showMessage(getApplicationContext(), "Please Select A Language....");
			spinlist.add("Select Language");
			spinlist.add("AFRIKAANS");
			spinlist.add("ALBANIAN");
			spinlist.add("ARABIC");
			spinlist.add("ARMENIAN");
			spinlist.add("AUTO_DETECT");
			spinlist.add("AZERBAIJANI");
			spinlist.add("BASQUE");
			spinlist.add("BELARUSIAN");
			spinlist.add("BENGALI");
			spinlist.add("BULGARIAN");
			spinlist.add("CATALAN");
			spinlist.add("CHINESE");
			spinlist.add("CHINESE_SIMPLIFIED");
			spinlist.add("CHINESE_TRADITIONAL");
			spinlist.add("CROATIAN");
			spinlist.add("CZECH");
			spinlist.add("DANISH");
			spinlist.add("DUTCH");
			spinlist.add("ENGLISH");
			spinlist.add("ESTONIAN");
			spinlist.add("FILIPINO");
			spinlist.add("FINNISH");
			spinlist.add("FRENCH");
			spinlist.add("GALICIAN");
			spinlist.add("GEORGIAN");
			spinlist.add("GERMAN");
			spinlist.add("GREEK");
			spinlist.add("GUJARATI");
			spinlist.add("HAITIAN_CREOLE");
			spinlist.add("HEBREW");
			spinlist.add("HINDI");
			spinlist.add("HUNGARIAN");
			spinlist.add("ICELANDIC");
			spinlist.add("INDONESIAN");
			spinlist.add("IRISH");
			spinlist.add("ITALIAN");
			spinlist.add("JAPANESE");
			spinlist.add("KANNADA");
			spinlist.add("KOREAN");
			spinlist.add("LATIN");
			spinlist.add("LATVIAN");
			spinlist.add("LITHUANIAN");
			spinlist.add("MACEDONIAN");
			spinlist.add("MALAY");
			spinlist.add("MALTESE");
			spinlist.add("NORWEGIAN");
			spinlist.add("PERSIAN");
			spinlist.add("POLISH");
			spinlist.add("PORTUGUESE");
			spinlist.add("ROMANIAN");
			spinlist.add("RUSSIAN");
			spinlist.add("SERBIAN");
			spinlist.add("SLOVAK");
			spinlist.add("SLOVENIAN");
			spinlist.add("SPANISH");
			spinlist.add("SWAHILI");
			spinlist.add("SWEDISH");
			spinlist.add("TAMIL");
			spinlist.add("TELUGU");
			spinlist.add("THAI");
			spinlist.add("TURKISH");
			spinlist.add("UKRAINIAN");
			spinlist.add("URDU");
			spinlist.add("VIETNAMESE");
			spinlist.add("WELSH");
			spinlist.add("YIDDISH");

		}
		else {
			if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("1")) {
				translator = new TranslateAPI(
								ENGLISH,
								AFRIKAANS,
								_translate);
			}
			else {
				if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("2")) {
					translator = new TranslateAPI(
									ENGLISH,
									ALBANIAN,
									_translate);
				}
				else {
					if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("3")) {
						translator = new TranslateAPI(
										ENGLISH,
										ARABIC,
										_translate);
					}
					else {
						if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("4")) {
							translator = new TranslateAPI(
											ENGLISH,
											ARMENIAN,
											_translate);
						}
						else {
							if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("5")) {
								translator = new TranslateAPI(
												ENGLISH,
												AUTO_DETECT,
												_translate);
							}
							else {
								if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("6")) {
									translator = new TranslateAPI(
													ENGLISH,
													AZERBAIJANI,
													_translate);
								}
								else {
									if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("7")) {
										translator = new TranslateAPI(
														ENGLISH,
														BASQUE,
														_translate);
									}
									else {
										if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("8")) {
											translator = new TranslateAPI(
															ENGLISH,
															BELARUSIAN,
															_translate);
										}
										else {
											if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("9")) {
												translator = new TranslateAPI(
																ENGLISH,
																BENGALI,
																_translate);
											}
											else {
												if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("10")) {
													translator = new TranslateAPI(
																	ENGLISH,
																	BULGARIAN,
																	_translate);
												}
												else {
													if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("11")) {
														translator = new TranslateAPI(
																		ENGLISH,
																		CATALAN,
																		_translate);
													}
													else {
														if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("12")) {
															translator = new TranslateAPI(
																			ENGLISH,
																			CHINESE,
																			_translate);
														}
														else {
															if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("13")) {
																translator = new TranslateAPI(
																				ENGLISH,
																				CHINESE_SIMPLIFIED,
																				_translate);
															}
															else {
																if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("14")) {
																	translator = new TranslateAPI(
																					ENGLISH,
																					CHINESE_TRADITIONAL,
																					_translate);
																}
																else {
																	if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("15")) {
																		translator = new TranslateAPI(
																						ENGLISH,
																						CROATIAN,
																						_translate);
																	}
																	else {
																		if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("16")) {
																			translator = new TranslateAPI(
																							ENGLISH,
																							CZECH,
																							_translate);
																		}
																		else {
																			if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("17")) {
																				translator = new TranslateAPI(
																								ENGLISH,
																								DANISH,
																								_translate);
																			}
																			else {
																				if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("18")) {
																					translator = new TranslateAPI(
																									ENGLISH,
																									DUTCH,
																									_translate);
																				}
																				else {
																					if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("19")) {
																						translator = new TranslateAPI(
																										ENGLISH,
																										ENGLISH,
																										_translate);
																					}
																					else {
																						if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("20")) {
																							translator = new TranslateAPI(
																											ENGLISH,
																											ESTONIAN,
																											_translate);
																						}
																						else {
																							if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("21")) {
																								translator = new TranslateAPI(
																												ENGLISH,
																												FILIPINO,
																												_translate);
																							}
																							else {
																								if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("22")) {
																									translator = new TranslateAPI(
																													ENGLISH,
																													FINNISH,
																													_translate);
																								}
																								else {
																									if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("23")) {
																										translator = new TranslateAPI(
																														ENGLISH,
																														FRENCH,
																														_translate);
																									}
																									else {
																										if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("24")) {
																											translator = new TranslateAPI(
																															ENGLISH,
																															GALICIAN,
																															_translate);
																										}
																										else {
																											if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("25")) {
																												translator = new TranslateAPI(
																																ENGLISH,
																																GEORGIAN,
																																_translate);
																											}
																											else {
																												if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("26")) {
																													translator = new TranslateAPI(
																																	ENGLISH,
																																	GERMAN,
																																	_translate);
																												}
																												else {
																													if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("27")) {
																														translator = new TranslateAPI(
																																		ENGLISH,
																																		GREEK,
																																		_translate);
																													}
																													else {
																														if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("28")) {
																															translator = new TranslateAPI(
																																			ENGLISH,
																																			GUJARATI,
																																			_translate);
																														}
																														else {
																															if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("29")) {
																																translator = new TranslateAPI(
																																				ENGLISH,
																																				HAITIAN_CREOLE,
																																				_translate);
																															}
																															else {
																																if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("30")) {
																																	translator = new TranslateAPI(
																																					ENGLISH,
																																					HEBREW,
																																					_translate);
																																}
																																else {
																																	if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("31")) {
																																		translator = new TranslateAPI(
																																						ENGLISH,
																																						HINDI,
																																						_translate);
																																	}
																																	else {
																																		if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("32")) {
																																			translator = new TranslateAPI(
																																							ENGLISH,
																																							HUNGARIAN,
																																							_translate);
																																		}
																																		else {
																																			if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("33")) {
																																				translator = new TranslateAPI(
																																								ENGLISH,
																																								ICELANDIC,
																																								_translate);
																																			}
																																			else {
																																				if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("34")) {
																																					translator = new TranslateAPI(
																																									ENGLISH,
																																									INDONESIAN,
																																									_translate);
																																				}
																																				else {
																																					if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("35")) {
																																						translator = new TranslateAPI(
																																										ENGLISH,
																																										IRISH,
																																										_translate);
																																					}
																																					else {
																																						if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("36")) {
																																							translator = new TranslateAPI(
																																											ENGLISH,
																																											ITALIAN,
																																											_translate);
																																						}
																																						else {
																																							if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("37")) {
																																								translator = new TranslateAPI(
																																												ENGLISH,
																																												JAPANESE,
																																												_translate);
																																							}
																																							else {
																																								if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("38")) {
																																									translator = new TranslateAPI(
																																													ENGLISH,
																																													KANNADA,
																																													_translate);
																																								}
																																								else {
																																									if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("39")) {
																																										translator = new TranslateAPI(
																																														ENGLISH,
																																														KOREAN,
																																														_translate);
																																									}
																																									else {
																																										if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("40")) {
																																											translator = new TranslateAPI(
																																															ENGLISH,
																																															LATIN,
																																															_translate);
																																										}
																																										else {
																																											if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("41")) {
																																												translator = new TranslateAPI(
																																																ENGLISH,
																																																LATVIAN,
																																																_translate);
																																											}
																																											else {
																																												if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("42")) {
																																													translator = new TranslateAPI(
																																																	ENGLISH,
																																																	LITHUANIAN,
																																																	_translate);
																																												}
																																												else {
																																													if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("43")) {
																																														translator = new TranslateAPI(
																																																		ENGLISH,
																																																		MACEDONIAN,
																																																		_translate);
																																													}
																																													else {
																																														if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("44")) {
																																															translator = new TranslateAPI(
																																																			ENGLISH,
																																																			MALAY,
																																																			_translate);
																																														}
																																														else {
																																															if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("45")) {
																																																translator = new TranslateAPI(
																																																				ENGLISH,
																																																				MALTESE,
																																																				_translate);
																																															}
																																															else {
																																																if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("46")) {
																																																	translator = new TranslateAPI(
																																																					ENGLISH,
																																																					NORWEGIAN,
																																																					_translate);
																																																}
																																																else {
																																																	if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("47")) {
																																																		translator = new TranslateAPI(
																																																						ENGLISH,
																																																						PERSIAN,
																																																						_translate);
																																																	}
																																																	else {
																																																		if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("48")) {
																																																			translator = new TranslateAPI(
																																																							ENGLISH,
																																																							POLISH,
																																																							_translate);
																																																		}
																																																		else {
																																																			if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("49")) {
																																																				translator = new TranslateAPI(
																																																								ENGLISH,
																																																								PORTUGUESE,
																																																								_translate);
																																																			}
																																																			else {
																																																				if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("50")) {
																																																					translator = new TranslateAPI(
																																																									ENGLISH,
																																																									ROMANIAN,
																																																									_translate);
																																																				}
																																																				else {
																																																					if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("51")) {
																																																						translator = new TranslateAPI(
																																																										ENGLISH,
																																																										RUSSIAN,
																																																										_translate);
																																																					}
																																																					else {
																																																						if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("52")) {
																																																							translator = new TranslateAPI(
																																																											ENGLISH,
																																																											SERBIAN,
																																																											_translate);
																																																						}
																																																						else {
																																																							if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("53")) {
																																																								translator = new TranslateAPI(
																																																												ENGLISH,
																																																												SLOVAK,
																																																												_translate);
																																																							}
																																																							else {
																																																								if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("54")) {
																																																									translator = new TranslateAPI(
																																																													ENGLISH,
																																																													SLOVENIAN,
																																																													_translate);
																																																								}
																																																								else {
																																																									if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("55")) {
																																																										translator = new TranslateAPI(
																																																														ENGLISH,
																																																														SPANISH,
																																																														_translate);
																																																									}
																																																									else {
																																																										if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("56")) {
																																																											translator = new TranslateAPI(
																																																															ENGLISH,
																																																															SWAHILI,
																																																															_translate);
																																																										}
																																																										else {
																																																											if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("57")) {
																																																												translator = new TranslateAPI(
																																																																ENGLISH,
																																																																SWEDISH,
																																																																_translate);
																																																											}
																																																											else {
																																																												if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("58")) {
																																																													translator = new TranslateAPI(
																																																																	ENGLISH,
																																																																	TAMIL,
																																																																	_translate);
																																																												}
																																																												else {
																																																													if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("59")) {
																																																														translator = new TranslateAPI(
																																																																		ENGLISH,
																																																																		TELUGU,
																																																																		_translate);
																																																													}
																																																													else {
																																																														if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("60")) {
																																																															translator = new TranslateAPI(
																																																																			ENGLISH,
																																																																			THAI,
																																																																			_translate);
																																																														}
																																																														else {
																																																															if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("61")) {
																																																																translator = new TranslateAPI(
																																																																				ENGLISH,
																																																																				TURKISH,
																																																																				_translate);
																																																															}
																																																															else {
																																																																if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("62")) {
																																																																	translator = new TranslateAPI(
																																																																					ENGLISH,
																																																																					UKRAINIAN,
																																																																					_translate);
																																																																}
																																																																else {
																																																																	if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("63")) {
																																																																		translator = new TranslateAPI(
																																																																						ENGLISH,
																																																																						URDU,
																																																																						_translate);
																																																																	}
																																																																	else {
																																																																		if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("64")) {
																																																																			translator = new TranslateAPI(
																																																																							ENGLISH,
																																																																							VIETNAMESE,
																																																																							_translate);
																																																																		}
																																																																		else {
																																																																			if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("65")) {
																																																																				translator = new TranslateAPI(
																																																																								ENGLISH,
																																																																								WELSH,
																																																																								_translate);
																																																																			}
																																																																			else {
																																																																				if (String.valueOf((long)(spinner2.getSelectedItemPosition())).equals("66")) {
																																																																					translator = new TranslateAPI(
																																																																									ENGLISH,
																																																																									YIDDISH,
																																																																									_translate);
																																																																				}
																																																																				else {
																																																																					SketchwareUtil.showMessage(getApplicationContext(), "Language Not Available For Now....");
																																																																				}
																																																																			}
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _translateNow(final TextView _TextViewTranslate) {
		translator.setTranslateListener(new TranslateAPI.TranslateListener() {
										@Override
										public void onSuccess(String translatorText) {
				TranslationResult = translatorText;
				_TextViewTranslate.setText(TranslationResult);
			}
			 @Override public void onFailure(String translatorError) {
									SketchwareUtil.showMessage(getApplicationContext(), "error");
										}
						});
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
