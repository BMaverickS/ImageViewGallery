package com.example.imageviewgallery;

import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<File> Image_array;
    GridView GridImg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridImg= (GridView) findViewById(R.id.gridLayout);
        Image_array= PicView(new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Basic"));
        GridImg.setAdapter(new Adapt());
        GridImg.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent tent = new Intent(MainActivity.this,img_extend.class);
                tent.putExtra("img", Image_array.get(i).toString());
                startActivity(tent);
            }
        });
    }

    public class Adapt extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return Image_array.size();
        }

        @Override
        public Object getItem(int i)
        {
            return Image_array.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View convert_View= null;
            if(convert_View==null)
            {
                convert_View = getLayoutInflater().inflate(R.layout.layout_2,viewGroup,false);
                ImageView image= (ImageView) convert_View.findViewById(R.id.imageLayout);
                image.setImageURI(Uri.parse(Image_array.get(i).toString()));
            }

            return convert_View;
        }
    }

    private ArrayList<File> PicView(File externalStorageDirectory)
    {
        ArrayList<File> read= new ArrayList<>();
        File[] ListImg = externalStorageDirectory.listFiles();

        for(int i = 0; i < ListImg.length; i++)
        {
            if(ListImg[i].isDirectory())
            {
                read.addAll(PicView(ListImg[i]));
            }
            else
            {
                if(ListImg[i].getName().endsWith(".jpg"))
                {
                    read.add(ListImg[i]);
                }
            }
        }
        return read;
    }
}
