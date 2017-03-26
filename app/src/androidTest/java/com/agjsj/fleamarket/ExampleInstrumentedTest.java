package com.agjsj.fleamarket;

import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.agjsj.fleamarket.dialog.ProgressDialog;
import com.agjsj.fleamarket.util.HttpConnectionUtils;
import com.agjsj.fleamarket.util.LogUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.agjsj.fleamarket", appContext.getPackageName());
    }

    @Test
    public void testFileUpload(){
        List<File> lists = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory(),"av.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory(),"viewphoto.jpg");
        lists.add(file2);
        lists.add(file);
        String result = HttpConnectionUtils.uploadImage(lists);
        System.out.print(result+"");
    }

    @Test
    public void testProgress(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        ProgressDialog progressDialog = new ProgressDialog(appContext);
        progressDialog.show();
    }
}
