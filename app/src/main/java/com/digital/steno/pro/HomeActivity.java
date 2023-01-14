package com.digital.steno.pro;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.*;
import com.google.android.gms.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private double exit = 0;
	private double search_n1 = 0;
	private double search_n = 0;
	private String version = "";
	private String update_link = "";
	private String link = "";
	private String package_name = "";
	private String latest_version = "";
	private String app_version = "";
	private String your_version = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> note = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private ListView listview1;
	private LinearLayout linear12;
	private LinearLayout linear2;
	private ImageView imageview1;
	private EditText edittext1;
	private ImageView imageview2;
	private ImageView imageview4;
	private TextView textview4;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear4;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear7;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear8;
	private ImageView _drawer_imageview1;
	private ImageView _drawer_settings;
	private TextView _drawer_settings_text;
	private ImageView _drawer_abt_img;
	private TextView _drawer_abt;
	private ImageView _drawer_ocr_imageview;
	private TextView _drawer_ocr_textview;
	private ImageView _drawer_announcements_imageview;
	private TextView _drawer_announcements_textview;
	private ImageView _drawer_web_iv;
	private TextView _drawer_web_tv;
	private ImageView _drawer_pp_iv;
	private TextView _drawer_pp_tv;
	
	private Intent in = new Intent();
	private SharedPreferences sp;
	private AlertDialog.Builder del;
	private SharedPreferences dm;
	private SharedPreferences theme;
	private TimerTask exit_timer;
	private TimerTask timer;
	private DatabaseReference db = _firebase.getReference("users/data");
	private ChildEventListener _db_child_listener;
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
	
	private DatabaseReference proUpdate = _firebase.getReference("app/pro");
	private ChildEventListener _proUpdate_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
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
		_fab = findViewById(R.id._fab);
		
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		listview1 = findViewById(R.id.listview1);
		linear12 = findViewById(R.id.linear12);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		edittext1 = findViewById(R.id.edittext1);
		imageview2 = findViewById(R.id.imageview2);
		imageview4 = findViewById(R.id.imageview4);
		textview4 = findViewById(R.id.textview4);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_linear2 = _nav_view.findViewById(R.id.linear2);
		_drawer_linear3 = _nav_view.findViewById(R.id.linear3);
		_drawer_linear4 = _nav_view.findViewById(R.id.linear4);
		_drawer_linear6 = _nav_view.findViewById(R.id.linear6);
		_drawer_linear7 = _nav_view.findViewById(R.id.linear7);
		_drawer_linear5 = _nav_view.findViewById(R.id.linear5);
		_drawer_linear8 = _nav_view.findViewById(R.id.linear8);
		_drawer_imageview1 = _nav_view.findViewById(R.id.imageview1);
		_drawer_settings = _nav_view.findViewById(R.id.settings);
		_drawer_settings_text = _nav_view.findViewById(R.id.settings_text);
		_drawer_abt_img = _nav_view.findViewById(R.id.abt_img);
		_drawer_abt = _nav_view.findViewById(R.id.abt);
		_drawer_ocr_imageview = _nav_view.findViewById(R.id.ocr_imageview);
		_drawer_ocr_textview = _nav_view.findViewById(R.id.ocr_textview);
		_drawer_announcements_imageview = _nav_view.findViewById(R.id.announcements_imageview);
		_drawer_announcements_textview = _nav_view.findViewById(R.id.announcements_textview);
		_drawer_web_iv = _nav_view.findViewById(R.id.web_iv);
		_drawer_web_tv = _nav_view.findViewById(R.id.web_tv);
		_drawer_pp_iv = _nav_view.findViewById(R.id.pp_iv);
		_drawer_pp_tv = _nav_view.findViewById(R.id.pp_tv);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		del = new AlertDialog.Builder(this);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);
		theme = getSharedPreferences("theme", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				/*
sp.edit().putString("pos", String.valueOf((long)(_position))).commit();
in.setClass(getApplicationContext(), ViewNoteActivity.class);
startActivity(in);
Animatoo.animateZoom(HomeActivity.this);
*/
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				/*
del.setTitle("Delete this note?");
del.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {
listmap.remove((int)(_position));
sp.edit().putString("allnotes", new Gson().toJson(listmap)).commit();
((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
}
});
del.setNegativeButton("No", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface _dialog, int _which) {

}
});
del.create().show();
*/
				return true;
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
				if (_drawer.isDrawerOpen(GravityCompat.START)) {
					_drawer.closeDrawer(GravityCompat.START);
				}
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				_saerch_list(_charSeq);
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Animator(imageview2, "scaleX", 1.10d, 200);
				_Animator(imageview2, "scaleY", 1.10d, 200);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Animator(imageview2, "scaleX", 1, 200);
								_Animator(imageview2, "scaleY", 1, 200);
								in.setClass(getApplicationContext(), ProfileActivity.class);
								startActivity(in);
								Animatoo.animateZoom(HomeActivity.this);
								timer.cancel();
							}
						});
					}
				};
				_timer.schedule(timer, (int)(200));
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), AddNoteActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
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
		
		_proUpdate_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("version update")) {
					version = _childValue.get("version").toString();
					update_link = _childValue.get("link").toString();
					if (version.equals(app_version)) {
						
					}
					else {
						if (!version.equals(app_version)) {
							final AlertDialog sucess = new AlertDialog.Builder(HomeActivity.this).create();
							LayoutInflater inflater = getLayoutInflater();
							
							View convertView = (View) inflater.inflate(R.layout.update_costom, null);
							sucess.setView(convertView);
							
							sucess.requestWindowFeature(Window.FEATURE_NO_TITLE);  sucess.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
							
							LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.linear1);
							
							TextView txt3 = (TextView) convertView.findViewById(R.id.textview3);
							
							ImageView b_img = (ImageView) convertView.findViewById(R.id.imageview1);
							
							android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
							a.setColor(Color.parseColor("#000000"));
							a.setCornerRadius(50);
							lin1.setBackground(a);
							
							b_img.setElevation(5);
							sucess.setCancelable(false);
							sucess.show();
							
							txt3.setOnClickListener(new View.OnClickListener(){
								    public void onClick(View v){
									
									in.setAction(Intent.ACTION_VIEW);
									in.setData(Uri.parse(update_link));
									startActivity(in);
								}});
							
						}
					}
				}
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
		proUpdate.addChildEventListener(_proUpdate_child_listener);
		
		_drawer_settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), SettingsActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_settings_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), SettingsActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_abt_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), InfoActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_abt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), InfoActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_ocr_imageview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), OcrActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
			}
		});
		
		_drawer_ocr_textview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), OcrActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
			}
		});
		
		_drawer_announcements_imageview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), AnnouncementsActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_announcements_textview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), AnnouncementsActivity.class);
				startActivity(in);
				Animatoo.animateZoom(HomeActivity.this);
				finish();
			}
		});
		
		_drawer_web_iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setAction(Intent.ACTION_VIEW);
				in.setData(Uri.parse("https://digitalsteno.github.io/"));
				startActivity(in);
			}
		});
		
		_drawer_web_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setAction(Intent.ACTION_VIEW);
				in.setData(Uri.parse("https://digitalsteno.github.io/"));
				startActivity(in);
			}
		});
		
		_drawer_pp_iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), PrivacyPolicyActivity.class);
				Animatoo.animateZoom(HomeActivity.this);
				startActivity(in);
				finish();
			}
		});
		
		_drawer_pp_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setClass(getApplicationContext(), PrivacyPolicyActivity.class);
				Animatoo.animateZoom(HomeActivity.this);
				startActivity(in);
				finish();
			}
		});
		
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
		setTitle("Home");
		StrictMode.VmPolicy.Builder builder = 
		    new StrictMode.VmPolicy.Builder(); 
		    StrictMode.setVmPolicy(builder.build());
		    if(Build.VERSION.SDK_INT>=24){ 
			         try{
				        java.lang.reflect.Method m = 
				              StrictMode.class.getMethod(
				        "disableDeathOnFileUriExposure"); 
				              m.invoke(null); 
				       }
			      catch(Exception e){ 
				        showMessage(e.toString()); 
				       } 
			    }
		this.getSupportActionBar().hide();
		if (dm.getString("dark", "").equals("1")) {
			{
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(0xFF424242);
				SketchUi.setCornerRadius(d*8);
				SketchUi.setStroke(d*1,0xFFFFFFFF);
				linear2.setElevation(d*4);
				android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
				linear2.setBackground(SketchUiRD);
			}
		}
		else {
			{
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(0xFFF5F5F5);
				SketchUi.setCornerRadius(d*8);
				SketchUi.setStroke(d*1,0xFF9E9E9E);
				linear2.setElevation(d*4);
				android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
				linear2.setBackground(SketchUiRD);
			}
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
		}
		if (listmap.size() > 0) {
			listview1.setVisibility(View.VISIBLE);
			linear12.setVisibility(View.GONE);
		}
		else {
			listview1.setVisibility(View.GONE);
			linear12.setVisibility(View.VISIBLE);
		}
		String urc = "com.digital.steno.pro";
		
		android.content.pm.PackageManager pm = getPackageManager(); 
		try { android.content.pm.PackageInfo pInfo = pm.getPackageInfo(urc, android.content.pm.PackageManager.GET_ACTIVITIES); 
			String version = pInfo.versionName;
			app_version = version;
		} catch (android.content.pm.PackageManager.NameNotFoundException e) { }
		proUpdate.addChildEventListener(_proUpdate_child_listener);
		FileUtil.makeDir("/storage/emulated/0/Digital Steno Pro");
		//also enable single line to show it on keyboard
		edittext1.setOnEditorActionListener(new TextView.OnEditorActionListener() { 
			@Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { if(actionId ==3){ 
					SketchwareUtil.hideKeyboard(getApplicationContext());
				} return false; } }); //GO 2 SEARCH 3 SEND 4 NEXT 5 DONE 6
	}
	
	@Override
	public void onStart() {
		super.onStart();
		sp.edit().putString("pos", "").commit();
		if (!sp.getString("allnotes", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("allnotes", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(listmap));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
		if (dm.getString("dark", "").equals("1")) {
			listview1.setBackgroundColor(0xFF212121);
			linear1.setBackgroundColor(0xFF212121);
			linear3.setBackgroundColor(0xFF212121);
			imageview1.setImageResource(R.drawable.ic_menu_grey);
			imageview2.setImageResource(R.drawable.ic_account_circle_grey);
			Glide.with(getApplicationContext()).load(Uri.parse("file:///android_asset/dark.gif")).into(imageview4);
			textview4.setTextColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFFFFFFFF);
		}
		else {
			listview1.setBackgroundColor(0xFFFFFFFF);
			linear1.setBackgroundColor(0xFFFFFFFF);
			linear3.setBackgroundColor(0xFFFFFFFF);
			edittext1.setTextColor(0xFF000000);
			imageview1.setImageResource(R.drawable.ic_menu_black);
			imageview2.setImageResource(R.drawable.ic_account_circle_black);
			Glide.with(getApplicationContext()).load(Uri.parse("file:///android_asset/light.gif")).into(imageview4);
			textview4.setTextColor(0xFF000000);
		}
		if (listmap.size() > 0) {
			listview1.setVisibility(View.VISIBLE);
			linear12.setVisibility(View.GONE);
		}
		else {
			listview1.setVisibility(View.GONE);
			linear12.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onBackPressed() {
		if (exit == 0) {
			SketchwareUtil.showMessage(getApplicationContext(), "Press again to exit");
			exit = 1;
			exit_timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (!(exit == 0)) {
								exit = 0;
							}
						}
					});
				}
			};
			_timer.schedule(exit_timer, (int)(2000));
		}
		else {
			exit = 1;
			finishAffinity();
		}
	}
	public void _card_style(final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _share() {
	}
	private void shareApplication() { 
		      android.content.pm.ApplicationInfo app = 
		      getApplicationContext().getApplicationInfo(); 
		      String filePath = app.sourceDir;
		      Intent intent = new Intent(Intent.ACTION_SEND); 
		      intent.setType("*/*"); 
		      java.io.File originalApk = new java.io.File(filePath); 
		      try {  
			        java.io.File tempFile = new java.io.File(getExternalCacheDir() + "/ExtractedApk"); 
			         if (!tempFile.isDirectory()) 
			         if (!tempFile.mkdirs()) 
			         return; 
			         tempFile = new java.io.File(tempFile.getPath() + "/" + 
			         "Digital Steno.apk");
			         if (!tempFile.exists()) 
			          {
				           try{
					             if (!tempFile.createNewFile()) { 
						               return; }
					            }
				           catch (java.io.IOException e){} 
				          } 
			         java.io.InputStream in = new java.io.FileInputStream (originalApk);
			         java.io.OutputStream out = new java.io.FileOutputStream(tempFile);
			         byte[] buf = new byte[1024];
			         int len; 
			         while ((len = in.read(buf)) > 0) { 
				            out.write(buf, 0, len); 
				         } 
			         in.close(); 
			         out.close(); 
			         intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
			         startActivity(Intent.createChooser(intent, "Share app via"));
			      } 
		      catch (java.io.IOException e) 
		      { showMessage(e.toString()); 
			      } 
		   }
	{
	}
	
	
	public void _saerch_list(final String _charSeq) {
		if (!sp.getString("allnotes", "").equals("")) {
			listmap = new Gson().fromJson(sp.getString("allnotes", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			if (_charSeq.length() > 0) {
				search_n1 = listmap.size() - 1;
				search_n = listmap.size();
				for(int _repeat91 = 0; _repeat91 < (int)(search_n); _repeat91++) {
					if (listmap.get((int)search_n1).get("title").toString().toLowerCase().contains(_charSeq.toLowerCase())) {
						
					}
					else {
						listmap.remove((int)(search_n1));
					}
					search_n1--;
				}
			}
			listview1.setAdapter(new Listview1Adapter(listmap));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.note, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			{
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(0xFFFFC107);
				SketchUi.setCornerRadius(d*10);
				SketchUi.setStroke(d*1,0xFF000000);
				android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
				linear1.setBackground(SketchUiRD);
				linear1.setClickable(true);
			}
			textview1.setText(listmap.get((int)_position).get("title").toString());
			textview2.setText(listmap.get((int)_position).get("note").toString());
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					sp.edit().putString("pos", String.valueOf((long)(_position))).commit();
					in.setClass(getApplicationContext(), ViewNoteActivity.class);
					startActivity(in);
					Animatoo.animateZoom(HomeActivity.this);
				}
			});
			linear1.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					del.setTitle("Delete this note?");
					del.setMessage("This Note Will Be Permanently Deleted And Cannot Be Recovered.");
					del.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							listmap.remove((int)(_position));
							sp.edit().putString("allnotes", new Gson().toJson(listmap)).commit();
							((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						}
					});
					del.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					del.create().show();
					return true;
				}
			});
			if (theme.getString("themes", "").equals("0")) {
				{
					android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
					int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
					SketchUi.setColor(0xFFF44336);
					SketchUi.setCornerRadius(d*10);
					SketchUi.setStroke(d*1,0xFF000000);
					android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
					linear1.setBackground(SketchUiRD);
					linear1.setClickable(true);
				}
			}
			else {
				if (theme.getString("themes", "").equals("1")) {
					{
						android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
						int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
						SketchUi.setColor(0xFFF5F5F5);
						SketchUi.setCornerRadius(d*10);
						SketchUi.setStroke(d*1,0xFF737373);
						android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
						linear1.setBackground(SketchUiRD);
						linear1.setClickable(true);
					}
				}
				else {
					if (theme.getString("themes", "").equals("2")) {
						{
							android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
							int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
							SketchUi.setColor(0xFF03A9F4);
							SketchUi.setCornerRadius(d*10);
							SketchUi.setStroke(d*1,0xFF000000);
							android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
							linear1.setBackground(SketchUiRD);
							linear1.setClickable(true);
						}
					}
					else {
						if (theme.getString("themes", "").equals("3")) {
							{
								android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
								int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
								SketchUi.setColor(0xFF00BCD4);
								SketchUi.setCornerRadius(d*10);
								SketchUi.setStroke(d*1,0xFF000000);
								android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
								linear1.setBackground(SketchUiRD);
								linear1.setClickable(true);
							}
						}
						else {
							if (theme.getString("themes", "").equals("4")) {
								{
									android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
									int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
									SketchUi.setColor(0xFF4CAF50);
									SketchUi.setCornerRadius(d*10);
									SketchUi.setStroke(d*1,0xFF000000);
									android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
									linear1.setBackground(SketchUiRD);
									linear1.setClickable(true);
								}
							}
							else {
								if (theme.getString("themes", "").equals("5")) {
									{
										android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
										int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
										SketchUi.setColor(0xFF8BC34A);
										SketchUi.setCornerRadius(d*10);
										SketchUi.setStroke(d*1,0xFF000000);
										android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
										linear1.setBackground(SketchUiRD);
										linear1.setClickable(true);
									}
								}
								else {
									if (theme.getString("themes", "").equals("6")) {
										{
											android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
											int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
											SketchUi.setColor(0xFFCDDC39);
											SketchUi.setCornerRadius(d*10);
											SketchUi.setStroke(d*1,0xFF000000);
											android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
											linear1.setBackground(SketchUiRD);
											linear1.setClickable(true);
										}
									}
									else {
										if (theme.getString("themes", "").equals("7")) {
											{
												android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
												int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
												SketchUi.setColor(0xFFFFEB3B);
												SketchUi.setCornerRadius(d*10);
												SketchUi.setStroke(d*1,0xFF000000);
												android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
												linear1.setBackground(SketchUiRD);
												linear1.setClickable(true);
											}
										}
										else {
											if (theme.getString("themes", "").equals("8")) {
												{
													android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
													int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
													SketchUi.setColor(0xFFFFC107);
													SketchUi.setCornerRadius(d*10);
													SketchUi.setStroke(d*1,0xFF000000);
													android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
													linear1.setBackground(SketchUiRD);
													linear1.setClickable(true);
												}
											}
											else {
												if (theme.getString("themes", "").equals("9")) {
													{
														android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
														int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
														SketchUi.setColor(0xFFFF9800);
														SketchUi.setCornerRadius(d*10);
														SketchUi.setStroke(d*1,0xFF000000);
														android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
														linear1.setBackground(SketchUiRD);
														linear1.setClickable(true);
													}
												}
												else {
													{
														android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
														int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
														SketchUi.setColor(0xFFF5F5F5);
														SketchUi.setCornerRadius(d*10);
														SketchUi.setStroke(d*1,0xFF737373);
														android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
														linear1.setBackground(SketchUiRD);
														linear1.setClickable(true);
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
			
			return _view;
		}
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
			if (!_arr.valueAt(_iIdx)) {
				continue;
			}
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
