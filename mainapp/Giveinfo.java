package com.rana.adarsh.imagetotext.mainapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.speech.RecognizerIntent;

import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import com.rana.adarsh.imagetotext.Model.Config5;
import com.rana.adarsh.imagetotext.R;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Giveinfo extends AppCompatActivity {

    Button savedata,checkdata;
    EditText showresult,etnum;
    ImageView PreviewImage;
    ListView listview;
    String house_number;

    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=400;
    private static final int IMAGE_PICK_GALLERY_CODE=1000;
    private static final int IMAGE_PICK_CAMERA_CODE=1001;

    private static final int RESULT_SPEECH =1;

    public static final String KEY_NUMHOUSE = "House_number";


    String cameraPermission[];
    String storagePermission[];

    Uri image_uri;

    SharedPreferences sharedPreferences;

    public static final String fileName = "login";
    public static final String Username = "username";

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giveinfo);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("เก็บข้อมูลการใช้น้ำ");
        actionBar.setSubtitle("Click image or voice");

        //image processing
        showresult=findViewById(R.id.result);
        PreviewImage=findViewById(R.id.image);

        checkdata = (Button) findViewById(R.id.checkbnt);
        savedata = (Button) findViewById(R.id.savebnt);
        etnum = (EditText) findViewById(R.id.numhouse);
        listview = (ListView)findViewById(R.id.listView);

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Username)){
            user = sharedPreferences.getString(Username,"not found");
        }

        //เช็คข้อมูลลูกบ้าน
        checkdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                house_number = etnum.getText().toString().trim();

                if (house_number.equals("")){
                    Toast.makeText(Giveinfo.this, "Please Enter House Number", Toast.LENGTH_SHORT).show();
                }else {

                    GetMatchData();
                }

            }
        });


        //บันทึกข้อมูล
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });

        cameraPermission=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};



    }

    private void GetMatchData() {

        house_number = etnum.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config5.MATCHDATA_URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) { showJSON(response);
                        } else { showJSON(response); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Giveinfo.this, "error", Toast.LENGTH_LONG).show(); }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_NUMHOUSE, house_number);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config5.JSON_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Name = jo.getString(Config5.KEY_NAMEOWNER);

                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config5.KEY_NAMEOWNER,Name);


                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Giveinfo.this, list, R.layout.list_item,
                new String[]{Config5.KEY_NAMEOWNER},
                new int[]{R.id.tvnameowner});
        listview.setAdapter(adapter);
    }

    public void Update(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://groundwaterbkj.000webhostapp.com/appgw/ubdate.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){
                            Toast.makeText(getApplicationContext(),"บันทึกแล้ว", Toast.LENGTH_SHORT).show();
                            etnum.setText("");
                            showresult.setText("");
                            listview.setAdapter(null);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("House_number",etnum.getText().toString());
                params.put("Water_new",showresult.getText().toString());
                params.put("Formuser",user);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add_image)
        {
            showImageImportDialog();
        }
        else if(id==R.id.add_voice)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "th-TH");
            try{
                startActivityForResult(intent, RESULT_SPEECH);
                showresult.setText("");
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void showImageImportDialog() {

        String []items={"Camera","Gallery"};
        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0)
                {
                    if(!CameraPermission())
                    { requestCameraPermission(); }
                    else
                    { pickCamera(); }
                }
                if(i==1)
                {
                    if(!StoragePermission())
                    { requestStoragePermission(); }
                    else
                    { pickGallery(); }
                }
            }
        });
        dialog.create().show();
    }

    private void pickGallery() {
        Intent intent=new Intent((Intent.ACTION_PICK));
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }

    private void pickCamera() {
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image to Text");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);

    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE);

    }

    private boolean StoragePermission() {
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,cameraPermission,CAMERA_REQUEST_CODE);

    }

    private boolean CameraPermission() {
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0)
                {
                    boolean cameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted)
                    { pickCamera(); }
                    else
                    { Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show(); }
                }
            case STORAGE_REQUEST_CODE:
                if(grantResults.length>0)
                {
                    boolean writestorageAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(writestorageAccepted)
                    { pickGallery(); }
                    else
                    { Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show(); }
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null){
            if(requestCode == RESULT_SPEECH){
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                showresult.setText(text.get(0));
            }
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);

            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(image_uri).setGuidelines(CropImageView.Guidelines.ON).start(this);

            }

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultURI = result.getUri();
                PreviewImage.setImageURI(resultURI);

                BitmapDrawable bitmapDrawable = (BitmapDrawable) PreviewImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                TextRecognizer recognizer = new TextRecognizer.Builder(
                        getApplicationContext()).build();
                if (!recognizer.isOperational()) { Toast.makeText(this, "error", Toast.LENGTH_SHORT).show(); }
                else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                    }
                    showresult.setText(sb.toString());
                }
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error=result.getError();
                Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show();
            }
        }
    }
}