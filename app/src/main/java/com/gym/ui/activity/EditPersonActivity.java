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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.AddCoachPhotoProtocol;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.UpdateUserProtocol;
import com.gym.ui.widget.RoundImageView;
import com.gym.utils.ImageUtils;
import com.gym.utils.ProgressUtil;
import com.gym.utils.StringUtils;
import com.gym.utils.Tool;
import com.gym.utils.UIUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/8/30 0030.
 */
public class EditPersonActivity extends BaseActivity implements View.OnClickListener {
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
    @InjectView(R.id.userName)
    EditText userName;
    @InjectView(R.id.neckName)
    EditText neckName;
    @InjectView(R.id.gender)
    EditText gender;
    @InjectView(R.id.phone)
    EditText phone;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.birth)
    EditText birth;
    @InjectView(R.id.address)
    EditText address;
    @InjectView(R.id.sign)
    EditText sign;
    @InjectView(R.id.user_picture)
    RoundImageView userPicture;
    @InjectView(R.id.editPerson_submit)
    Button editPersonSubmit;
    @InjectView(R.id.headerImg)
    RelativeLayout headerImg;
    private ActionBar mActionBar;
    private ArrayList<String> toolbarItem;
    private boolean loading = false;
    private HashMap<String, String> params;

    private View dialogView;
    private int TAKE_PICTURE = 100, PHOTO_ALBUM = 200;
    private int takeType = 1;
    private String imgPath1;
    private static final int CROP_PICTURE = 0;
    private String protraitPath1;
    String filePath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + Constants.APP_TMEP_FILE_PATH + "/";
    private Button takingPictures;
    private Button selectPphotoAlbum;
    private Button btnClose;
    private Dialog dialog;
    private String selectItem;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_editperson);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        toolbarItem = getIntent().getStringArrayListExtra(Constants.TOOLBAR_ITEM);
        editPersonSubmit.setOnClickListener(this);
        headerImg.setOnClickListener(this);
        initOperationDialog();
        initInfo();//预加载用户信息
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        if (toolbarItem != null && toolbarItem.size() > 0) {
            titleTb.setText(toolbarItem.get(0));
            if (toolbarItem.size() >= 3) {
                rightTv.setText(toolbarItem.get(2));
                rightTv.setVisibility(View.VISIBLE);
            }
        }
        operateToolbar();
    }

    private void operateToolbar() {
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initInfo() {
        userName.setText(Constants.user.getUsr_UserName());
        neckName.setText(Constants.user.getUsr_User_ActualName());
        gender.setText(Constants.user.getUsr_Sex());
        phone.setText(Constants.user.getUsr_MobilePhone());
        email.setText(Constants.user.getUsr_EmailAddress());
        birth.setText(Constants.user.getUsr_Birthday());
        address.setText(Constants.user.getUsr_address());
        userPicture.setRectAdius(UIUtils.dip2px(45));
        if (!TextUtils.isEmpty(Constants.user.getUsr_Photo())) {
            ImageLoader.load(userPicture, Constants.user.getUsr_Photo());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == editPersonSubmit) {
            new UpdateUserTask().execute();
        } else if (view == headerImg) {
            showDialog();
        } else if (view == selectPphotoAlbum) {// 从相册取
            openAlbum();
            dialog.dismiss();// 操作框 取消
        } else if (view == takingPictures) {// 拍照
            takePicture();
            dialog.dismiss();// 操作框 取消
        } else if (view == btnClose) {
            dialog.dismiss();// 操作框 取消s
        }
    }

    public HashMap<String, String> getParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", Constants.user.getUsr_UserID() + "");
        hashMap.put("UserName", userName.getText() + "");
        hashMap.put("Usr_UserAlia", neckName.getText() + "");
        hashMap.put("Sex", gender.getText() + "");
        hashMap.put("MobilePhone", phone.getText() + "");
        hashMap.put("EmailAddress", email.getText() + "");
        hashMap.put("Birthday", birth.getText() + "");
        hashMap.put("Location", address.getText() + "");
        hashMap.put("Signature", sign.getText() + "");
        return hashMap;
    }

    class UpdateUserTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(EditPersonActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = getParams();
            UpdateUserProtocol protocol = new UpdateUserProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.UpdateUsers_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(s);
            }
        }
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
    } // 打开本地相册

    public void openAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        this.startActivityForResult(intent, PHOTO_ALBUM);
    } // 拍照

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
                }
                if (!loading) {
                    new UpdateImageTask().execute();
                }
                setImageView(realPath, userPicture);

            }
        }
    }

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
        }
        Uri cropUri = Uri.fromFile(protraitFile);
        return cropUri;
    }

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

    private void setImageView(final String realPath, RoundImageView addImage) {

        Bitmap bmp = BitmapFactory.decodeFile(realPath);
        int degree = readPictureDegree(realPath);
        if (takeType == 1) {
            if (degree <= 0) {

                addImage.destroyDrawingCache();
                addImage.setImageBitmap(bmp);
                addImage.setRectAdius(UIUtils.dip2px(45));
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage.setImageBitmap(resizedBitmap);
                addImage.setRectAdius(UIUtils.dip2px(45));
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

    class UpdateImageTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(EditPersonActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userId", String.valueOf(Constants.user.getUsr_UserID()));
            String topicContent = Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath1)), Base64.DEFAULT).replace("\n", "");

            hashMap.put("pictureinfo", topicContent);

            AddCoachPhotoProtocol protocol = new AddCoachPhotoProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.UploadPicture_URL), BaseProtocol.POST);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(EditPersonActivity.this, UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(EditPersonActivity.this, s);
            }

        }
    }
}
