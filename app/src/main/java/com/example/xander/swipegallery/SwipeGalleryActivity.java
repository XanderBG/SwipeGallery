package com.example.xander.swipegallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;


public class SwipeGalleryActivity extends FragmentActivity {

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        File pictureDirectory = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);


        List<File> pictures = Arrays.asList(pictureDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return !new File(dir, filename).isDirectory();
            }
        }));
        mPagerAdapter = new SwipeGalleryAdapter(getSupportFragmentManager(), pictures);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.swipe_gallery);
        mPager.setAdapter(mPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SwipeGalleryAdapter extends FragmentStatePagerAdapter {

        private List<File> galleryList;

        public SwipeGalleryAdapter(FragmentManager fm, List<File> gallery) {
            super(fm);
            galleryList = gallery;
        }

        @Override
        public Fragment getItem(int i) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Bitmap currentImage = BitmapFactory.decodeFile(galleryList.get(i).getPath(), options);
            ImageFragment currentImageFragment = new ImageFragment();
            Bundle imageBundle = new Bundle();
            imageBundle.putParcelable("image", currentImage);
            currentImageFragment.setArguments(imageBundle);
            return currentImageFragment;
        }

        @Override
        public int getCount() {
            return galleryList.size();
        }

        public void addFile (File file) {
            galleryList.add(file);
        }
    }
}
