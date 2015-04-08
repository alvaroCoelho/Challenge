package com.news;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

//Activity responsible for showing details of Articles

public class DetailsArticle extends Activity {

	TextView title,author,content,website,date;
	ImageView imageArticle;
	
	  private ImageLoader mImageLoader;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_article);
		
		title = (TextView)findViewById(R.id.title);
		author = (TextView)findViewById(R.id.author);
		content = (TextView)findViewById(R.id.content);
		website = (TextView)findViewById(R.id.website);
		date = (TextView)findViewById(R.id.date);
		imageArticle = (ImageView)findViewById(R.id.image);
		
		title.setText(getIntent().getStringExtra("title"));
		author.setText(getIntent().getStringExtra("authors"));
		content.setText(getIntent().getStringExtra("content"));
		website.setText(getIntent().getStringExtra("website"));
		date.setText(getIntent().getStringExtra("date"));
		
		
		//Configuration of image
		
		 DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder()
		   .showImageForEmptyUri(R.drawable.empty)
		   .showImageOnFail(R.drawable.empty)
		   .cacheInMemory(true)
		   .cacheOnDisk(true)
		   .build();
		   
		   ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(DetailsArticle.this)
		   .defaultDisplayImageOptions(mDisplayImageOptions)
		   .memoryCacheSize(50 * 1024 * 1024)//50mb
		   .diskCacheSize(50 * 1024 * 1024) //50mb
		   .threadPoolSize(5)
		   .build();
		   mImageLoader = ImageLoader.getInstance();
		   mImageLoader.init(config);
		
		   String urlImage = getIntent().getStringExtra("image");
		   
		   mImageLoader.displayImage(urlImage, imageArticle);
		   
	}
}
