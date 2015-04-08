package com.news;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class Adapter extends BaseAdapter{

	   private LayoutInflater mInflater;
	    private ArrayList<Article> itens;
	    private ImageLoader mImageLoader;
	    private Context context;
	 
	 
	    public Adapter(Context context, List<Article> itens,ImageLoader mImageLoader)
	    {
	        this.itens =  (ArrayList<Article>) itens;
	        //responsible for take the layout for the item
	        this.mInflater = LayoutInflater.from(context);
	        this.mImageLoader = mImageLoader;
	        this.context = context;
	    }
	 
	    /**
	     * return the amount of itens
	     *
	     * @return
	     */
	    public int getCount()
	    {
	        return itens.size();
	    }
	 
	    /**
	     * Returns the position of item on the screen
	     *
	     * @param position
	     * @return
	     */
	    public Article getItem(int position)
	    {
	        return itens.get(position);
	    }
	 
	
	    public long getItemId(int position)
	    {
	        return position;
	    }
	 
	    public View getView(int position, View view, ViewGroup parent){
	    	
	        //Take item according to position
	    	Article article = itens.get(position);
	        //Inflate the layout to fill the data
	        view = mInflater.inflate(R.layout.item_list, null);
	 
	     
	     
	        ((TextView) view.findViewById(R.id.titleArticle)).setText(article.title);
	        ((TextView) view.findViewById(R.id.dateArticle)).setText(article.date);
	        ((TextView) view.findViewById(R.id.authorArticle)).setText(article.authors);
	        
	        //Take image according URL  
	        mImageLoader.displayImage(article.image,(ImageView) view.findViewById(R.id.imgArticle));
	        
	      	 //back information articles read
	        SharedPreferences prefArticles = context.getSharedPreferences("articles_read", Context.MODE_PRIVATE);
	        
	        String title = prefArticles.getString(article.title, "empty");	
	       
	        if(!title.equalsIgnoreCase("empty")){
	        	
	        	view.setBackgroundColor(context.getResources().getColor(R.color.blue));
	        	((TextView) view.findViewById(R.id.textRead)).setText("Already Read!");
	        }
	        
	 
	        return view;
	    }
	    
	    
	 

}