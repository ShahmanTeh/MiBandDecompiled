// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package cn.com.smartdevices.bracelet.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.com.smartdevices.bracelet.BleTask.BleFwUpgradeTask;
import cn.com.smartdevices.bracelet.BraceletApp;
import cn.com.smartdevices.bracelet.upgrade.OtaVersionInfo;
import cn.com.smartdevices.bracelet.upgrade.UpgradeService;
import cn.com.smartdevices.bracelet.webapi.BraceletHttpClient;
import com.loopj.android.http.AsyncHttpClient;
import com.xiaomi.hm.bleservice.BLEService;
import com.xiaomi.hm.bleservice.profile.MiLiProfile;
import java.io.File;
import java.io.IOException;

// Referenced classes of package cn.com.smartdevices.bracelet.ui:
//            bI, bH, bL, bJ, 
//            bK

public class DownloadFirmwareHandler extends Fragment
{

    public static final int MSG_UPDATE_FIRMWARE_INFO = 1;
    public static final String SUFFIX_FIRMWARE = ".fw";
    public static final String SUFFIX_TEMP = ".tmp";
    private static final String a = "Setting.Firmware";
    private static final int b = 4098;
    public static Handler mHandler;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;
    private ProgressDialog g;
    private com.xiaomi.hm.bleservice.profile.etActivity h;
    private OtaVersionInfo i;

    static OtaVersionInfo a(DownloadFirmwareHandler downloadfirmwarehandler)
    {
        return downloadfirmwarehandler.i;
    }

    private void a()
    {
        mHandler = new bI(this);
    }

    private void a(int j)
    {
        if (g == null)
        {
            g = new ProgressDialog(getActivity());
            g.setMax(j);
            g.setProgress(0);
            g.setTitle("\u56FA\u4EF6\u5347\u7EA7");
            g.setMessage("\u5347\u7EA7\u8FDB\u5EA6");
            g.setCancelable(false);
            g.setProgressStyle(1);
            g.setIndeterminate(false);
            g.show();
        }
    }

    private void a(View view)
    {
        c = (TextView)view.findViewById(0x7f0a011b);
        d = (TextView)view.findViewById(0x7f0a011c);
        e = (TextView)view.findViewById(0x7f0a011d);
        f = (TextView)view.findViewById(0x7f0a011e);
        f.setOnClickListener(new bH(this));
        f.setEnabled(false);
    }

    static void a(f f1, int j)
    {
        f1.a(j);
    }

    static void a(a a1, OtaVersionInfo otaversioninfo)
    {
        a1.b(otaversioninfo);
    }

    static void a(b b1, String s, String s1)
    {
        b1.a(s, s1);
    }

    private void a(OtaVersionInfo otaversioninfo)
    {
        d.setText(otaversioninfo.firmwareVersion);
        e.setText(otaversioninfo.firmwareInfo);
        f.setEnabled(UpgradeService.checkFirmwareUpgradeState(otaversioninfo, h));
    }

    private void a(String s, String s1)
    {
        (new BleFwUpgradeTask(s, s1, new bL(this, s1))).work();
    }

    static OtaVersionInfo b(h h1, OtaVersionInfo otaversioninfo)
    {
        h1.i = otaversioninfo;
        return otaversioninfo;
    }

    private void b()
    {
        b(((MiLiProfile)BraceletApp.BLEService.getDefaultPeripheral()).getFirmwareUpdatingProgress().);
        mHandler.sendEmptyMessageDelayed(4098, 1000L);
    }

    private void b(int j)
    {
        if (g != null)
        {
            g.setProgress(j);
        }
    }

    static void b(g g1)
    {
        g1.b();
    }

    private void b(OtaVersionInfo otaversioninfo)
    {
        File file = new File((new StringBuilder()).append(((BraceletApp)getActivity().getApplication()).getStoragePath()).append("/bracelet.fw").toString());
        File file1 = new File((new StringBuilder()).append(file.getPath()).append(".tmp").toString());
        class DownloadFirmwareHandler extends FileAsyncHttpResponseHandler
        {

            private static final char h[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                'a', 'b', 'c', 'd', 'e', 'f'
            };
            private SettingFirmwareActivity.SettingFirmwareFragment b;
            private Context c;
            private File d;
            private OtaVersionInfo e;
            private boolean f;
            private ProgressDialog g;

            private void a()
            {
                if (g != null)
                {
                    g.show();
                }
            }

            private void a(int j)
            {
                if (g != null)
                {
                    g.setProgress(j);
                }
            }

            private void a(Context context)
            {
                if (g == null)
                {
                    g = new ProgressDialog(context);
                    g.setMax(100);
                    g.setProgress(0);
                    g.setTitle("\u56FA\u4EF6\u4E0B\u8F7D");
                    g.setMessage("\u4E0B\u8F7D\u8FDB\u5EA6");
                    g.setProgressStyle(1);
                    g.setIndeterminate(false);
                    g.setCancelable(true);
                    g.setOnCancelListener(new bM(this));
                }
            }

            static boolean a(DownloadFirmwareHandler downloadfirmwarehandler, boolean flag)
            {
                downloadfirmwarehandler.f = flag;
                return flag;
            }

            private void b()
            {
                if (g != null)
                {
                    g.dismiss();
                }
            }

            public static String md5sum(File file2)
            {
                byte abyte0[];
                if (!file2.isFile())
                {
                    return null;
                }
                abyte0 = new byte[1024];
                FileInputStream fileinputstream;
                MessageDigest messagedigest;
                fileinputstream = new FileInputStream(file2);
                messagedigest = MessageDigest.getInstance("MD5");
_L1:
                int j = fileinputstream.read(abyte0);
label0:
                {
                    if (j <= 0)
                    {
                        break label0;
                    }
                    String s;
                    try
                    {
                        messagedigest.update(abyte0, 0, j);
                    }
                    catch (Exception exception)
                    {
                        return null;
                    }
                }
                  goto _L1
                fileinputstream.close();
                s = toHexString(messagedigest.digest());
                return s;
            }

            public static String toHexString(byte abyte0[])
            {
                StringBuilder stringbuilder = new StringBuilder(2 * abyte0.length);
                for (int j = 0; j < abyte0.length; j++)
                {
                    stringbuilder.append(h[(0xf0 & abyte0[j]) >>> 4]);
                    stringbuilder.append(h[0xf & abyte0[j]]);
                }

                return stringbuilder.toString();
            }

            public void onFailure(int j, Header aheader[], Throwable throwable, File file2)
            {
                Debug.w("Setting.Firmware", (new StringBuilder()).append("Download On Failure : ").append(d.getPath()).toString());
                Debug.w("Setting.Firmware", throwable.toString());
                if (aheader != null)
                {
                    int k = aheader.length;
                    for (int l = 0; l < k; l++)
                    {
                        Header header = aheader[l];
                        Debug.i("Setting.Firmware", (new StringBuilder()).append(header.getName()).append(" : ").append(header.getValue()).toString());
                    }

                }
                if (f)
                {
                    return;
                } else
                {
                    b();
                    CustomToast.makeText(c, "\u4E0B\u8F7D\u56FA\u4EF6\u5931\u8D25\uFF0C\u8BF7\u91CD\u8BD5", 0).show();
                    return;
                }
            }

            public void onFinish()
            {
                Debug.i("Setting.Firmware", "Download On Finish!!");
                if (d.exists())
                {
                    d.delete();
                }
                b();
            }

            public void onProgress(int j, int k)
            {
                Debug.i("Setting.Firmware", (new StringBuilder()).append("Download On Progress : ").append(j).append(" Total : ").append(k).toString());
                a((int)(100F * ((float)j / (float)k)));
            }

            public void onStart()
            {
                super.onStart();
                Debug.i("Setting.Firmware", "Download On Start!!");
                a();
            }

            public void onSuccess(int j, Header aheader[], File file2)
            {
                Debug.i("Setting.Firmware", "Download On Success!!");
                if (aheader != null)
                {
                    int k = aheader.length;
                    for (int l = 0; l < k; l++)
                    {
                        Header header = aheader[l];
                        Debug.i("Setting.Firmware", (new StringBuilder()).append(header.getName()).append(" : ").append(header.getValue()).toString());
                    }

                }
                if (f)
                {
                    return;
                }
                String s = d.getPath();
                File file3 = new File(s.substring(0, s.length() - ".tmp".length()));
                if (file3.exists())
                {
                    file3.delete();
                }
                d.renameTo(file3);
                Debug.i("Setting.Firmware", (new StringBuilder()).append("FirmwareFile : ").append(file3).append(" , ").append(file3.exists()).append(" , ").append(file3.length()).toString());
                String s1 = md5sum(file3);
                Debug.i("Setting.Firmware", (new StringBuilder()).append("FirmwareMd5 : ").append(s1).toString());
                if (s1.equalsIgnoreCase(e.firmwareMd5))
                {
                    b.confirmFwUpgrade();
                    return;
                } else
                {
                    CustomToast.makeText(c, "\u56FA\u4EF6\u6821\u9A8C\u5931\u8D25\uFF0C\u8BF7\u91CD\u8BD5", 0).show();
                    return;
                }
            }


            public DownloadFirmwareHandler(Context context, File file, OtaVersionInfo otaversioninfo)
            {
                super(file);
                b = SettingFirmwareActivity.SettingFirmwareFragment.this;
                c = context;
                d = file;
                e = otaversioninfo;
                a(context);
            }
        }

        if (!file1.exists())
        {
            try
            {
                file1.getParentFile().mkdirs();
                file1.createNewFile();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        BraceletHttpClient.client.get(otaversioninfo.firmwareUrl, new DownloadFirmwareHandler(getActivity(), file1, otaversioninfo));
    }

    private void c()
    {
        if (g != null)
        {
            g.dismiss();
            g = null;
        }
        mHandler.removeMessages(4098);
    }

    static void c(mHandler mhandler)
    {
        mhandler.c();
    }

    static void c(c c1, OtaVersionInfo otaversioninfo)
    {
        c1.a(otaversioninfo);
    }

    public void confirmFwUpgrade()
    {
        android.app.ingFirmwareFragment ingfirmwarefragment = new android.app.ingFirmwareFragment(getActivity());
        ingfirmwarefragment.getActivity("\u56FA\u4EF6\u5347\u7EA7");
        String s = (new StringBuilder()).append(((BraceletApp)getActivity().getApplication()).getStoragePath()).append("/bracelet.fw").toString();
        File file = new File(s);
        String s1;
        if (file.exists() && file.canRead())
        {
            s1 = (new StringBuilder()).append("\u624B\u73AF\u56FA\u4EF6\u8DEF\u5F84\uFF1A").append(s).append("\n").append("\u786E\u5B9A\u5347\u7EA7\uFF1F").toString();
            ingfirmwarefragment.getActivity("\u786E\u5B9A", new bJ(this, s));
        } else
        {
            s1 = (new StringBuilder()).append("\u624B\u73AF\u56FA\u4EF6\u8DEF\u5F84\uFF1A").append(s).append("\u4E0D\u5B58\u5728,").append("\u8BF7\u628A\u56FA\u4EF6\u590D\u5236\u5230\u76EE\u6807\u5730\u5740\u540E\u91CD\u8BD5\uFF01").toString();
        }
        ingfirmwarefragment.ingFirmwareFragment(s1);
        ingfirmwarefragment.ingFirmwareFragment("\u53D6\u6D88", new bK(this));
        ingfirmwarefragment.ingFirmwareFragment();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f030044, viewgroup, false);
        a(view);
        a();
        h = ((MiLiProfile)BraceletApp.BLEService.getDefaultPeripheral()).getCachedDeviceInfo();
        c.setText(h.());
        UpgradeService.start(getActivity(), 2);
        return view;
    }

    public DownloadFirmwareHandler()
    {
    }
}
