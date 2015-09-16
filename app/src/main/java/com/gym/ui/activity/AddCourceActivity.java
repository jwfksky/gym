package com.gym.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.http.protocol.AddCourceProtocol;
import com.gym.http.protocol.BaseProtocol;
import com.gym.utils.ImageUtils;
import com.gym.utils.LogUtils;
import com.gym.utils.ProgressUtil;
import com.gym.utils.StringUtils;
import com.gym.utils.Tool;
import com.gym.utils.UIUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015-08-26.
 */
public class AddCourceActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.back_tb)
    ImageView backTb;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.gender_tv)
    TextView genderTv;
    @InjectView(R.id.addImage1)
    ImageView addImage1;
    @InjectView(R.id.addImage2)
    ImageView addImage2;
    @InjectView(R.id.addImage3)
    ImageView addImage3;
    @InjectView(R.id.addImage4)
    ImageView addImage4;
    @InjectView(R.id.height_tv)
    TextView heightTv;
    @InjectView(R.id.course_weight)
    EditText courseWeight;
    @InjectView(R.id.period_tv)
    TextView periodTv;
    @InjectView(R.id.course_height)
    EditText courseHeight;
    @InjectView(R.id.time_tv)
    TextView timeTv;
    @InjectView(R.id.course_calorie)
    EditText courseCalorie;
    @InjectView(R.id.during_tv)
    TextView duringTv;
    @InjectView(R.id.course_ibm)
    EditText courseIbm;

    private ActionBar mActionBar;
    private Dialog dialog;
    private View dialogView;
    private int TAKE_PICTURE = 100, PHOTO_ALBUM = 200;
    private int takeType = 1;
    private String imgPath1, imgPath2, imgPath3, imgPath4;
    private static final int CROP_PICTURE = 0;
    private String protraitPath1;
    private String protraitPath2;
    private String protraitPath3;
    private String protraitPath4;
    String filePath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + Constants.APP_TMEP_FILE_PATH + "/";
    private Button takingPictures;
    private Button selectPphotoAlbum;
    private Button btnClose;
    private String peLast;
    private String weight;
    private String calorie;
    private String ibm;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_add_course);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        addImage1.setOnClickListener(this);
        addImage2.setOnClickListener(this);
        addImage3.setOnClickListener(this);
        addImage4.setOnClickListener(this);
        initOperationDialog();
    }

    private void initOperationDialog() {
        dialogView = this.getLayoutInflater().inflate(
                R.layout.activity_edituserinfo_popuoperat, null);
        dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
        takingPictures = (Button) dialogView.findViewById(R.id.takingPictures);
        selectPphotoAlbum = (Button) dialogView.findViewById(R.id.selectPphotoAlbum);
        btnClose = (Button) dialogView.findViewById(R.id.btnClose);
        takingPictures.setOnClickListener(this);
        selectPphotoAlbum.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        rightTv.setVisibility(View.VISIBLE);
        titleTb.setText(UIUtils.getString(R.string.addCourse));
        operateToolbar();
    }

    private void operateToolbar() {
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rightTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == addImage1) {
            showDialog();
            takeType = 1;
        } else if (v == addImage2) {
            showDialog();
            takeType = 2;
        } else if (v == addImage3) {
            showDialog();
            takeType = 3;
        } else if (v == addImage4) {
            showDialog();
            takeType = 4;
        } else if (v == selectPphotoAlbum) {// 从相册取
            openAlbum();
            dialog.dismiss();// 操作框 取消
        } else if (v == takingPictures) {// 拍照
            takePicture();
            dialog.dismiss();// 操作框 取消
        } else if (v == btnClose) {
            dialog.dismiss();// 操作框 取消s
        } else if (v == rightTv) {
            initUploadData();
        }
    }

    private void initUploadData() {
        new AddCourceTask().execute();
    }

    private void showDialog() {

        dialog.setContentView(dialogView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.operatPopMenu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.onWindowAttributesChanged(wl);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    // 打开本地相册
    public void openAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.startActivityForResult(intent, PHOTO_ALBUM);
    }

    // 拍照
    public void takePicture() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
            if (takeType == 1) {
                imgPath1 = outFile.getAbsolutePath();
            } else if (takeType == 2) {
                imgPath2 = outFile.getAbsolutePath();
            } else if (takeType == 3) {
                imgPath3 = outFile.getAbsolutePath();
            } else if (takeType == 4) {
                imgPath4 = outFile.getAbsolutePath();
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, TAKE_PICTURE);
        } else {
            UIUtils.showToastSafe(this, "手机设备无存储SDCard,请确认已经插入SD卡.");
        }
    }

    private void startPhotoZoom(Uri data, int takeType) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data, takeType));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);// 输出图片大小
        intent.putExtra("outputY", 600);

        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent, this.CROP_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                if (takeType == 1) {
                    //setImageView(imgPath1);
                    File file = new File(imgPath1);
                    startPhotoZoom(Uri.fromFile(file), 1);
                } else if (takeType == 2) {
                    //setImageView(imgPath2);
                    File file = new File(imgPath2);
                    startPhotoZoom(Uri.fromFile(file), 2);
                } else if (takeType == 3) {
                    File file = new File(imgPath3);
                    startPhotoZoom(Uri.fromFile(file), 3);
                    //setImageView(imgPath3);
                } else if (takeType == 4) {
                    File file = new File(imgPath4);
                    startPhotoZoom(Uri.fromFile(file), 4);
                    //setImageView(imgPath3);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                UIUtils.showToastSafe(this, "拍照失败");
            }
        } else if (requestCode == PHOTO_ALBUM) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String realPath = getRealPathFromURI(uri);
                    //setImageView(realPath);
                    startPhotoZoom(uri, takeType);
                    if (takeType == 1) {
                        imgPath1 = realPath;
                    } else if (takeType == 2) {
                        imgPath2 = realPath;
                    } else if (takeType == 3) {
                        imgPath3 = realPath;
                    } else if (takeType == 4) {
                        imgPath4 = realPath;
                    }
                } else {
                    UIUtils.showToastSafe(this, "从相册获取图片失败");
                }
            }
        } else if (requestCode == CROP_PICTURE) {
            if (resultCode == RESULT_OK) {
                // uploadNewPhoto();// 上传新照片
                String realPath = null;
                if (takeType == 1) {
                    realPath = protraitPath1;
                } else if (takeType == 2) {
                    realPath = protraitPath2;
                } else if (takeType == 3) {
                    realPath = protraitPath3;
                } else if (takeType == 4) {
                    realPath = protraitPath4;
                }
                setImageView(realPath);
            }
        }
    }    // 裁剪头像的绝对路径

    private Uri getUploadTempFile(Uri uri, int takeType) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(filePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {

            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (StringUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(this, uri);
        }
        String ext = "jpg";
        ext = StringUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "super_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        File protraitFile = null;
        if (takeType == 1) {
            protraitPath1 = filePath + cropFileName;
            protraitFile = new File(protraitPath1);

            Uri cropUri = Uri.fromFile(protraitFile);
        } else if (takeType == 2) {
            protraitPath2 = filePath + cropFileName;
            protraitFile = new File(protraitPath2);


        } else if (takeType == 3) {
            protraitPath3 = filePath + cropFileName;
            protraitFile = new File(protraitPath3);
        } else if (takeType == 4) {
            protraitPath3 = filePath + cropFileName;
            protraitFile = new File(protraitPath4);
        }
        Uri cropUri = Uri.fromFile(protraitFile);
        return cropUri;
    }

    /**
     * This method is used to get real path of file from from uri<br/>
     * captured-from-camera
     *
     * @param contentUri
     * @return String
     */
    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.managedQuery(contentUri, proj, null, null,
                    null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }

    private void setImageView(final String realPath) {

        Bitmap bmp = BitmapFactory.decodeFile(realPath);
        int degree = readPictureDegree(realPath);
        if (takeType == 1) {
            if (degree <= 0) {
                addImage1.setImageBitmap(bmp);
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage1.setImageBitmap(resizedBitmap);
            }
        } else if (takeType == 2) {
            if (degree <= 0) {
                addImage2.setImageBitmap(bmp);
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage2.setImageBitmap(resizedBitmap);
            }
        } else if (takeType == 3) {
            if (degree <= 0) {
                addImage3.setImageBitmap(bmp);
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage3.setImageBitmap(resizedBitmap);
            }
        } else if (takeType == 4) {
            if (degree <= 0) {
                addImage4.setImageBitmap(bmp);
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage4.setImageBitmap(resizedBitmap);
            }
        }
    }

    /**
     * 读取照片exif信息中的旋转角度<br/>
     *
     * @param path 照片路径
     * @return角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public boolean checkParams() {
        if (TextUtils.isEmpty(courseWeight.getText())) return false;
        if (TextUtils.isEmpty(courseHeight.getText())) return false;
        if (TextUtils.isEmpty(courseCalorie.getText())) return false;
        if (TextUtils.isEmpty(courseIbm.getText())) return false;
        return true;
    }
    class AddCourceTask extends AsyncTask<String,String,String>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            peLast = courseHeight.getText().toString();
            ibm = courseIbm.getText().toString();
            weight = courseWeight.getText().toString();
            calorie = courseCalorie.getText().toString();
            ProgressUtil.startProgressBar(AddCourceActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            if (checkParams()) {
                String topicContent1 = "";
                String topicContent2 = "";
                String topicContent3 = "";
                String topicContent4 = "";
                if (!TextUtils.isEmpty(protraitPath1)) {
                  // topicContent1 = Tool.getImgeHexBase64String(protraitPath1).replace("\n","");
                    topicContent1 =  Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath1)),Base64.DEFAULT).replace("\n","");
                }
                if (!TextUtils.isEmpty(protraitPath2)) {
                    topicContent2 =  Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath2)), Base64.DEFAULT).replace("\n","");

                }
                if (!TextUtils.isEmpty(protraitPath3)) {
                    topicContent3 =  Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath3)), Base64.DEFAULT).replace("\n","");

                }
                if (!TextUtils.isEmpty(protraitPath4)) {
                    topicContent4 =  Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath4)), Base64.DEFAULT).replace("\n","");

                }
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("userID",Constants.user.getUsr_UserID()+"");
                hashMap.put("photo1",topicContent1);
                hashMap.put("photo2",topicContent2);
                hashMap.put("photo3",topicContent3);
                hashMap.put("photo4",topicContent4);

                hashMap.put("p_e_last", peLast);

                hashMap.put("weight", weight);

                hashMap.put("calorie", calorie);

                hashMap.put("IBM", ibm);
                AddCourceProtocol protocol=new AddCourceProtocol(hashMap);
                return protocol.load(UIUtils.getString(R.string.AddExerciseRecord_URL), BaseProtocol.POST);
            }
            return "-1";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ProgressUtil.stopProgressBar();
            if("1".equals(s)){
                UIUtils.showToastSafe(AddCourceActivity.this,UIUtils.getString(R.string.submit_ok));
                finish();
            }else{
                UIUtils.showToastSafe(AddCourceActivity.this,UIUtils.getString(R.string.submit_error));
            }
        }
    }
}
