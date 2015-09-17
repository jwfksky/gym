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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CoachDetailBean;
import com.gym.bean.CoachPhotoBean;
import com.gym.bean.UserBean;
import com.gym.http.image.ImageLoader;
import com.gym.http.protocol.AddCoachPhotoProtocol;
import com.gym.http.protocol.AddPublishLessonProtocol;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CoachDetailProtocol;
import com.gym.http.protocol.CoachPhotoProtocol;
import com.gym.http.protocol.UpdateCoachProtocol;
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
 * Created by Administrator on 2015/8/31 0031.
 */
public class EditCoachInfoActivity extends BaseActivity implements View.OnClickListener {


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
    @InjectView(R.id.name_tv)
    TextView nameTv;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.gender_tv)
    TextView genderTv;
    @InjectView(R.id.height_tv)
    TextView heightTv;
    @InjectView(R.id.height)
    EditText height;
    @InjectView(R.id.weight_tv)
    TextView weightTv;
    @InjectView(R.id.weight)
    EditText weight;
    @InjectView(R.id.body)
    EditText body;
    @InjectView(R.id.teachTime_tv)
    TextView teachTimeTv;
    @InjectView(R.id.teachTime)
    EditText teachTime;
    @InjectView(R.id.cert_tv)
    TextView certTv;
   /* @InjectView(R.id.cert_add)
    TextView certAdd;*/

    @InjectView(R.id.ward_tv)
    TextView wardTv;
/*    @InjectView(R.id.ward_add)
    TextView wardAdd;*/
    @InjectView(R.id.cert_image1)
    ImageView certImage1;
    @InjectView(R.id.cert_image2)
    ImageView certImage2;
    @InjectView(R.id.cert_image3)
    ImageView certImage3;
    @InjectView(R.id.cert_image4)
    ImageView certImage4;
    @InjectView(R.id.ward_image1)
    ImageView wardImage1;
    @InjectView(R.id.ward_image2)
    ImageView wardImage2;
    @InjectView(R.id.ward_image3)
    ImageView wardImage3;
    @InjectView(R.id.ward_image4)
    ImageView wardImage4;
    @InjectView(R.id.gender)
    RadioGroup gender;
    @InjectView(R.id.right_rb)
    RadioButton rightRb;
    @InjectView(R.id.cb5)
    CheckBox cb5;
    @InjectView(R.id.cb6)
    CheckBox cb6;
    @InjectView(R.id.cb7)
    CheckBox cb7;
    @InjectView(R.id.cb8)
    CheckBox cb8;
    @InjectView(R.id.cb1)
    CheckBox cb1;
    @InjectView(R.id.cb2)
    CheckBox cb2;
    @InjectView(R.id.cb3)
    CheckBox cb3;
    @InjectView(R.id.cb4)
    CheckBox cb4;
    @InjectView(R.id.cb9)
    CheckBox cb9;
    @InjectView(R.id.man)
    RadioButton man;
    @InjectView(R.id.women)
    RadioButton women;
    private ActionBar mActionBar;
    private boolean loading = false;

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
    private ImageView selectedIv;
    private String selectItem;
    private static final String WARD = "ward";
    private static final String CERT = "cert";
    private String expertise;
    private Integer a = 0;//验证多选项的个数
    private CoachDetailBean mCoachDetailBean1;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_inputcoach);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        UserBean userBean=Constants.user;
        new CoachDetailTask().execute(userBean.getUsr_UserID() + "");
        new GetCoachCertTask().execute(userBean.getUsr_UserID() + "", UIUtils.getString(R.string.GetCoachDetail_Photh_Z_URL));
        new GetCoachCertTask().execute(userBean.getUsr_UserID() + "", UIUtils.getString(R.string.GetCoachDetail_Photh_J_URL));
        certImage1.setOnClickListener(this);
        certImage2.setOnClickListener(this);
        certImage3.setOnClickListener(this);
        certImage4.setOnClickListener(this);

        wardImage1.setOnClickListener(this);
        wardImage2.setOnClickListener(this);
        wardImage3.setOnClickListener(this);
        wardImage4.setOnClickListener(this);




        initOperationDialog();
    }

    @Override
    public void initActionbar() {
        super.initActionbar();
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        areaTb.setVisibility(View.GONE);
        backTb.setVisibility(View.VISIBLE);
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(UIUtils.getString(R.string.submit));
        titleTb.setText(UIUtils.getString(R.string.coachInfo));
        rightTv.setOnClickListener(this);
        backTb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == rightTv) {
            if(dialog!=null){
                dialog.dismiss();
            }
            if (!loading)
                new updateCoachInfoTask().execute();
        } else if (view == certImage1) {
            showDialog();
            selectedIv = certImage1;
            selectItem = CERT;
        } else if (view == certImage2) {
            showDialog();
            selectedIv = certImage2;
            selectItem = CERT;
        } else if (view == certImage3) {
            showDialog();
            selectedIv = certImage3;
            selectItem = CERT;
        } else if (view == certImage4) {
            showDialog();
            selectedIv = certImage4;
            selectItem = CERT;
        } else if (view == wardImage1) {
            showDialog();
            selectedIv = wardImage1;
            selectItem = WARD;
        } else if (view == wardImage2) {
            showDialog();
            selectedIv = wardImage2;
            selectItem = WARD;
        } else if (view == wardImage3) {
            showDialog();
            selectedIv = wardImage3;
            selectItem = WARD;
        } else if (view == wardImage4) {
            showDialog();
            selectedIv = wardImage4;
            selectItem = WARD;
        } else if (view == selectPphotoAlbum) {// 从相册取
            openAlbum();
            dialog.dismiss();// 操作框 取消
        } else if (view == takingPictures) {// 拍照
            takePicture();
            dialog.dismiss();// 操作框 取消
        } else if (view == btnClose) {
            dialog.dismiss();// 操作框 取消s
        } else if (view == backTb) {
            onBackPressed();
        }
    }

    /**
     * 获取提交数据
     *
     * @return
     */
    public HashMap<String, String> getParams() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("userID", String.valueOf(Constants.user.getUsr_UserID()));
        hash.put("Usr_User_ActualName", name.getText().toString());
        hash.put("Usr_Sex", gender.getCheckedRadioButtonId() + "");
        hash.put("Usr_Height", height.getText().toString());
        hash.put("Usr_Weight", weight.getText().toString());
        hash.put("Usr_Expertise", getExpertise());
        hash.put("Usr_shape", body.getText().toString());
        hash.put("TeachingYear", teachTime.getText().toString());
        return hash;
    }

    public String getExpertise() {
        final StringBuilder sb1 = new StringBuilder();

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb1)).append(",");
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb2)).append(",");
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb3)).append(",");
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb4)).append(",");
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb5)).append(",");
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb6)).append(",");
            }
        });
        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb7)).append(",");
            }
        });
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb8)).append(",");
            }
        });
        cb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sb1.append(checkboxValues(cb9)).append(",");
            }
        });
        if(sb1.length()>0)
        sb1.deleteCharAt(sb1.length() - 1);
        return sb1.toString();
    }

    private String checkboxValues(CheckBox cb) {
        String sb1 = "";
        if (cb.isChecked()) {
            if (a > 3) {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, UIUtils.getString(R.string.err_three));
                cb.setChecked(false);
                return "";
            }
            sb1 = cb.getText().toString();
            a++;
        } else {
            sb1 = "";
            a--;
        }
        return sb1;
    }


    /**
     * 提交编辑信息
     */
    class updateCoachInfoTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(EditCoachInfoActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = getParams();
            UpdateCoachProtocol protocol = new UpdateCoachProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.UpdateUserforCoach_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, s);

                finish();
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
                setImageView(realPath, selectedIv);
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

    private void setImageView(final String realPath, ImageView addImage) {

        Bitmap bmp = BitmapFactory.decodeFile(realPath);
        int degree = readPictureDegree(realPath);
        if (takeType == 1) {
            if (degree <= 0) {
                addImage.setImageBitmap(bmp);
            } else {
                Matrix matrix = new Matrix();
                // 旋转图片动作
                matrix.postRotate(degree);
                // 创建新图片
                Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                        bmp.getWidth(), bmp.getHeight(), matrix, true);

                addImage.setImageBitmap(resizedBitmap);
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
            ProgressUtil.startProgressBar(EditCoachInfoActivity.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userID", String.valueOf(Constants.user.getUsr_UserID()));
            String topicContent = Base64.encodeToString(Tool.bitmapTobyte(Tool.getSmallBitmap(protraitPath1)), Base64.DEFAULT).replace("\n", "");
            if (selectItem.equals(CERT)) {
                hashMap.put("path_z", topicContent);
                hashMap.put("path_j", "");
            } else {
                hashMap.put("path_j", topicContent);
                hashMap.put("path_z", "");
            }
            AddCoachPhotoProtocol protocol = new AddCoachPhotoProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.AddCoachPhoto_URL), BaseProtocol.POST);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (TextUtils.isEmpty(s)) {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, UIUtils.getString(R.string.network_error));
            } else {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, s);
            }

        }
    }

    class CoachDetailTask extends AsyncTask<String, String, CoachDetailBean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(EditCoachInfoActivity.this);
        }

        @Override
        protected CoachDetailBean doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userID", strings[0]);
            CoachDetailProtocol protocol = new CoachDetailProtocol(hashMap);
            return protocol.load(UIUtils.getString(R.string.GetCoachDetail_URL), BaseProtocol.POST);
        }

        @Override
        protected void onPostExecute(CoachDetailBean coachDetailBean) {
            super.onPostExecute(coachDetailBean);
            loading = false;
            ProgressUtil.stopProgressBar();
            if (coachDetailBean == null) {
                UIUtils.showToastSafe(EditCoachInfoActivity.this, UIUtils.getString(R.string.network_error));
            } else {
                mCoachDetailBean1 = coachDetailBean;
                name.setText(Constants.user.getUsr_UserName());
                if("男".equals(coachDetailBean.getUsr_Sex())){
                    man.setChecked(true);
                    women.setChecked(false);
                }else {
                    man.setChecked(false);
                    women.setChecked(true);
                }
                height.setText(coachDetailBean.getUsr_Height()+"");
                weight.setText(coachDetailBean.getUsr_Weight()+"");
                teachTime.setText(coachDetailBean.getTeachingYear()+"");
                body.setText(coachDetailBean.getUsr_Shape() + "");
            }
        }
    }
    class GetCoachCertTask extends AsyncTask<String,String,ArrayList<CoachPhotoBean>>{

        @Override
        protected ArrayList<CoachPhotoBean> doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userID", strings[0]);
            CoachPhotoProtocol protocol = new CoachPhotoProtocol(hashMap);
            return (ArrayList<CoachPhotoBean>) protocol.load(strings[1],BaseProtocol.POST);

        }

        @Override
        protected void onPostExecute(ArrayList<CoachPhotoBean> coachPhotoBeans) {
            super.onPostExecute(coachPhotoBeans);
            if(coachPhotoBeans!=null){
                for(int i=0;i<coachPhotoBeans.size();i++){
                    CoachPhotoBean bean=coachPhotoBeans.get(i);
                    if(TextUtils.isEmpty(bean.getPhotopath_j())){
                        //证书
                        if(i==0){
                            ImageLoader.load(certImage1, bean.getPhotopath_z());
                        }else if(i==1){
                            ImageLoader.load(certImage2,bean.getPhotopath_z());
                        }
                        else if(i==2){
                            ImageLoader.load(certImage3,bean.getPhotopath_z());
                        }
                        else if(i==3){
                            ImageLoader.load(certImage4,bean.getPhotopath_z());
                        }
                    }else{
                        if(i==0){
                            ImageLoader.load(wardImage1,bean.getPhotopath_j());
                        }else if(i==1){
                            ImageLoader.load(wardImage2,bean.getPhotopath_j());
                        }
                        else if(i==2){
                            ImageLoader.load(wardImage3,bean.getPhotopath_j());
                        }
                        else if(i==3){
                            ImageLoader.load(wardImage4,bean.getPhotopath_j());
                        }
                    }
                }
            }
        }
    }
}
