package com.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

//Main class
public class MainActivity extends ListActivity {

	ArrayAdapter<String> myAdapter;
	
	
	
	private Adapter adapter;
	
	List<Article> itens = new ArrayList<>();
	ArrayAdapter<Article> arrayAdapter;
	ArrayList<Article> arrayArticles;
	
     ListView listView;
     
     private ImageLoader mImageLoader;
    
   
   
    private static String url = "http://www.ckl.io/challenge";
    
  
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		
	//Configuration of image
	   DisplayImageOptions mDisplayImageOptions = new DisplayImageOptions.Builder()
	   .showImageForEmptyUri(R.drawable.empty)
	   .showImageOnFail(R.drawable.empty)
	   .cacheInMemory(true)
	   .cacheOnDisk(true)
	   .build();
	   
	   ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MainActivity.this)
	   .defaultDisplayImageOptions(mDisplayImageOptions)
	   .memoryCacheSize(50 * 1024 * 1024)//50mb
	   .diskCacheSize(50 * 1024 * 1024) //50mb
	   .threadPoolSize(5)
	   .build();
	   mImageLoader = ImageLoader.getInstance();
	   mImageLoader.init(config);
	   
	   new ProgressTask(MainActivity.this).execute(); 
	  
	
	}
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		
		//Menu sort by title
		case R.id.sort_title:
			
			Collections.sort(itens,new Comparator<Article>() {
			
				public int compare(Article article1,Article article2){
					
					return article1.title.compareTo(article2.title);
				}
				
			});
	  
			adapter = new Adapter(MainActivity.this,itens,mImageLoader);
			
   		 setListAdapter(adapter);
			
   		Toast.makeText(this, "List Ordered by Title" , Toast.LENGTH_LONG).show();
			
		break;
		
		//Menu sort by date
		case R.id.sort_date:
			
			   Collections.sort(itens,new Comparator<Article>() {
				
				public int compare(Article article1,Article article2){
					
					return article1.date.compareTo(article2.date);
				}
				
			});
	  
			adapter = new Adapter(MainActivity.this,itens,mImageLoader);
			
   		     setListAdapter(adapter);
			
			Toast.makeText(this, "List Ordered by Date" , Toast.LENGTH_LONG).show();
			
		break;	
			
		//Menu sort by author
		case R.id.sort_author:
			
                 Collections.sort(itens,new Comparator<Article>() {
				
				 public int compare(Article article1,Article article2){
					
					return article1.authors.compareTo(article2.authors);
			    	
				   }
				
		      	});
	  
			adapter = new Adapter(MainActivity.this,itens,mImageLoader);
			
   		    setListAdapter(adapter);
			
			Toast.makeText(this, "List Ordered by Author" , Toast.LENGTH_LONG).show();
			
			
		break;
		
		//Menu sort by website
		case R.id.sort_website:
			
			  Collections.sort(itens,new Comparator<Article>() {
					
					 public int compare(Article article1,Article article2){
						
						return article1.website.compareTo(article2.website);
				    	
					   }
					
			      	});
		  
				adapter = new Adapter(MainActivity.this,itens,mImageLoader);
				
	   		    setListAdapter(adapter);
	   		   
				
				Toast.makeText(this, "List Ordered by Website" , Toast.LENGTH_LONG).show();
				
			
			
		break;
		
		
			
		}
		
		
		return super.onOptionsItemSelected(item);
		
		
	}

	
	//Asynctask for bring the data and showing in the screen
	private class ProgressTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog dialog;
		private ListActivity activity; 
	
		public ProgressTask(ListActivity activity) {
			this.activity = activity; 
			context = activity; 
			dialog = new ProgressDialog(context); 
			} 
		
		private Context context;
		
		protected void onPreExecute() {
			this.dialog.setMessage("Wait...");
			this.dialog.show();
			
		} 
		
		
		protected Boolean doInBackground(final String... args) {
			
			
			
				 JSONParser jparser = new JSONParser();
				 
				 
				 JSONArray json = jparser.getJSONFromUrl(url);
				 
				for (int i = 0; i < json.length(); i++) {
					
					 try{ 
						   
						    JSONObject jsonObject = json.getJSONObject(i);
						    
						  Article temp = new Article();
						    
						  temp.setContent( jsonObject.getString("content"));
						  temp.setTitle(jsonObject.getString("title"));
						  temp.setDate(jsonObject.getString("date"));
						  temp.setImage(jsonObject.getString("image"));
				          temp.setWebsite(jsonObject.getString("website"));
						  temp.setAuthors(jsonObject.getString("authors"));
						
						  itens.add(temp);
						   
						 
				} catch (Exception e) {
					
					Log.e(TEXT_SERVICES_MANAGER_SERVICE,"Erro no método createListView: "+e.getMessage());
				}	
			
			}
				
				
			        
				
			
			
			
			return null;
			
		} 
		
		
		
    @Override 
     protected void onPostExecute(final Boolean success) {
    		if (dialog.isShowing()) { 
				dialog.dismiss();
				}
    		
    		 adapter = new Adapter(MainActivity.this,itens,mImageLoader);
		
    		 setListAdapter(adapter);
    		 
    		
				
		  }
		
	 }
				
	
	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
	
		Article article = adapter.getItem(position);
		Intent detailsArticle = new Intent(this, DetailsArticle.class);
		detailsArticle.putExtra("title", article.getTitle());
		detailsArticle.putExtra("content", article.getContent());
		detailsArticle.putExtra("website", article.getWebsite());
		detailsArticle.putExtra("date", article.getDate());
		detailsArticle.putExtra("authors", article.getAuthors());
		detailsArticle.putExtra("image", article.getImage());
		
		
		startActivity(detailsArticle);
		
		
		//safe information of the articles read
		SharedPreferences prefArticles = getSharedPreferences("articles_read", Context.MODE_PRIVATE);
		
		Editor editor = prefArticles.edit();
		
		editor.putString(article.getTitle(), article.getTitle());
		
		editor.commit();
		
	    
		
		list.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.blue));
		 
		TextView textRead = (TextView)view.findViewById(R.id.textRead);
		
		textRead.setText("Already Read!");
		
		
		
		
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	

		










