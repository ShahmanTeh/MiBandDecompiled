// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.xiaomi.hm.bleservice.profile;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.hm.bleservice.BLEService;
import com.xiaomi.hm.bleservice.util.Debug;

// Referenced classes of package com.xiaomi.hm.bleservice.profile:
//            MiLiProfile

class this._cls0
    implements com.xiaomi.hm.bleservice.gatt.INotifyCallback
{

    final MiLiProfile this$0;

    public void notify(byte abyte0[])
    {
        boolean flag = true;
        Intent intent;
        if (abyte0.length != flag)
        {
            flag = false;
        }
        Debug.ASSERT_TRUE(flag);
        Debug.INFO("=================================================");
        Debug.INFO("========== NOTIFICATION STATUS CHANGED ==========");
        Debug.INFO("=================================================");
        intent = new Intent(MiLiProfile.INTENT_ACTION_DEVICE_STATUS_CHANGED);
        intent.putExtra(BLEService.INTENT_EXTRA_DEVICE, getDevice());
        intent.putExtra(BLEService.INTENT_EXTRA_PARAM, abyte0[0]);
        BLEService.getBroadcastManager().sendBroadcast(intent);
    }

    r()
    {
        this$0 = MiLiProfile.this;
        super();
    }
}
