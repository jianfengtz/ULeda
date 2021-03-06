package ecnu.uleda.tool;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zhaoning on 2017/2/4.
 */

public class UStatusBarUtils {
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Window window = activity.getWindow();

                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
