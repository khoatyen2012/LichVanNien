package tana.daithanh.thaotac;

/**
 * Created by Manh on 3/21/2018.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckConection {
    public CheckConection(Context _context) {

        this._context = _context;
    }

    private Context _context;

    public boolean checkMobileInternetConn() {
        //Tạo đối tương ConnectivityManager để trả về thông tin mạng
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //Nếu đối tượng khác null
        if (connectivity != null) {
            //Nhận thông tin mạng
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo infowifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null || infowifi!=null) {
                //Tìm kiếm thiết bị đã kết nối được internet chưa
                if (info.isConnected()||infowifi.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

}
