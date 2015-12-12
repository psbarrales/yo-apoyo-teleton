package alquimio.asomao.teleton;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

public class Asomao extends Service {


    private WindowManager windowManager;
    private InputMethodManager imm;
    private static final String BCAST_CONFIGCHANGED = "android.intent.action.CONFIGURATION_CHANGED";
    public BroadcastReceiver mBroadcastReceiver;
    private ImageView chatHead;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        chatHead = new ImageView(this);

        chatHead.setImageResource(R.drawable.floating);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT);
        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.alpha =  0.7f;
        params.x = 0;
        params.y = 0;

        windowManager.addView(chatHead, params);

        this.mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent myIntent) {

                if ( myIntent.getAction().equals( BCAST_CONFIGCHANGED ) ) {
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BCAST_CONFIGCHANGED);
        registerReceiver(this.mBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }

}