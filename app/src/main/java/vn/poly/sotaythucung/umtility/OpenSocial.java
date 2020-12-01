package vn.poly.sotaythucung.umtility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class OpenSocial {
//    public Intent openFacebook(Context context) {
//        try {
//            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/quangvucot"));
//        } catch (PackageManager.NameNotFoundException e) {
//            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/quangvucot/"));
//        }
//    }

    public static String FACEBOOK_URL = "https://www.facebook.com/quangvucot";
    public static String FACEBOOK_PAGE_ID = "quangvucot";

    public static Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/quangvucot"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/quangvucot"));
        }
    }
}
