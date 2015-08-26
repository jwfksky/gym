/**
 * Tool.java[v 1.0.0]
 *classes: com.eFan.SuperGroundAttack.utils.Tool
 *zhangyx Create at 2015年3月27日 下午1:42:50
 */

package com.gym.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * com.eFan.SuperGroundAttack.utils.Tool
 * 
 * @author zhangyx <br/>
 *         create at 2015年3月27日 下午1:42:50
 *
 */
@SuppressLint("SimpleDateFormat")
public class Tool {

	/**
	 * 
	 * <p>
	 * Description: <／p>
	 * <p>
	 * Company: eFan<／p>
	 * 
	 * @author zhangyx
	 * @date 2015年3月27日
	 */
	public static String getImgeHexString(String path) {
		// File file = new File(
		// android.os.Environment.getExternalStorageDirectory()
		// + "/Photo/Screenshots/S40306-083401.jpg");
		FileInputStream inputFile = null;
		BufferedInputStream bis = null;
		try {
			File file = new File(path);
			inputFile = new FileInputStream(file);
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			return android.util.Base64.encodeToString(buffer,
					android.util.Base64.DEFAULT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputFile != null) {
				try {
					inputFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public static String getImgeHexBase64String(String filePath) {
		return android.util.Base64.encodeToString(
				bitmapTobyte(getSmallBitmap(filePath)),
				android.util.Base64.DEFAULT);
	}

	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示
	 * 
	 * @param filePath
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 500, 500);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	/***
	 * 测试参数写入SDCard
	 * 
	 * @param str
	 */
	public static void w(String str) {
		String strPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + "TestLog.txt";
		byte[] byteStr = str.getBytes();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(strPath));
			out.write(byteStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static String dataFormate(String d1) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf.parse(d1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdf.format(d);
	}

	/**
	 * Android UI 图片处理：质量压缩方法
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
																// 0---100
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/***
	 * 讲bitmap格式转换为 byte格式
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] bitmapTobyte(Bitmap bm) {
		byte[] buffer = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (bm != null) {
			bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
			buffer = baos.toByteArray();
		}

		return buffer;
	}
}
