package com.digital.steno.pro;

import android.Manifest;
import android.animation.*;
import android.annotation.SuppressLint;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.os.Build;
import android.provider.MediaStore;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.blogspot.atifsoftwares.animatoolib.*;
import com.facebook.shimmer.*;
import com.google.android.gms.*;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.firebase.FirebaseApp;

import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

import org.json.*;

public class OcrActivity extends AppCompatActivity {

	public final int REQ_CD_CAM = 101;

	private HashMap<String, Object> map = new HashMap<>();
	private double noteCount = 0;
	private double position = 0;

	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();

	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private TextView textview1;

	private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_cam;
	private SharedPreferences dm;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.ocr);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		vscroll1 = findViewById(R.id.vscroll1);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		_file_cam = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_cam;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_cam = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_cam);
		} else {
			_uri_cam = Uri.fromFile(_file_cam);
		}
		cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
		cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		dm = getSharedPreferences("dm", Activity.MODE_PRIVATE);

		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (textview1.getText().toString().equals("Click To Copy The Text.....")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Please Scan Text To Copy!");
				} else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString()));
					SketchwareUtil.showMessage(getApplicationContext(), "Copied To Clipboard");
				}
			}
		});
	}

	private void initializeLogic() {
		cameraView = new SurfaceView(this);
		linear2.addView(cameraView);
		_process();
		textview1.setTextIsSelectable(true);
		noteCount = 1;
		if (!"".equals("")) {
			position = Double.parseDouble("0");
		} else {
			position = -1;
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		if (dm.getString("dark", "").equals("1")) {
			linear1.setBackgroundColor(0xFF000000);
			textview1.setTextColor(0xFFFFFFFF);
		} else {
			linear1.setBackgroundColor(0xFFFFFFFF);
			textview1.setTextColor(0xFF000000);
		}
	}

	public void _locate_vrb() {
	}

	com.google.android.gms.vision.CameraSource cameraSource;
	SurfaceView cameraView;

	{
	}


	public void _process() {
		com.google.android.gms.vision.text.TextRecognizer textRecognizer = new com.google.android.gms.vision.text.TextRecognizer.Builder(getApplicationContext()).build();
		if (!textRecognizer.isOperational()) {
			Log.w("OcrActivity", "Detector dependencies are not yet available");
		} else {
			cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
					.setFacing(CameraSource.CAMERA_FACING_BACK)
					.setRequestedPreviewSize(1280, 1024)
					.setRequestedFps(2.0f)
					.setAutoFocusEnabled(true)
					.build();
		}

		cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder surfaceHolder) {
				try {
					if (ActivityCompat.checkSelfPermission(OcrActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
						return;
					}
					cameraSource.start(cameraView.getHolder());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

			@Override
			public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
				cameraSource.stop();
			}
		});

		textRecognizer.setProcessor(new Detector.Processor<com.google.android.gms.vision.text.TextBlock>() {
			@Override
			public void release() {
			}

			@Override
			public void receiveDetections(Detector.Detections<com.google.android.gms.vision.text.TextBlock> detections) {
				final SparseArray<com.google.android.gms.vision.text.TextBlock> items = detections.getDetectedItems();
				if(items.size() != 0)
				{
					textview1.post(new Runnable() {
						@Override
						public void run() {
							StringBuilder stringBuilder = new StringBuilder();
							for(int i =0;i<items.size();++i)
							{
								com.google.android.gms.vision.text.TextBlock item = items.valueAt(i);
								stringBuilder.append(item.getValue());
								stringBuilder.append("\n");
							}
							textview1.setText(stringBuilder.toString());
						}
					});
				}
			}
		});

		//Change textview1 with your TextView Id
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
