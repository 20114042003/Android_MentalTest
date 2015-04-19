package com.cx.project.mentaltest.activity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cx.project.mentaltest.Extra;
import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.TestType;
import com.cx.project.mentaltest.adapter.MenuItemAdapter;
import com.cx.project.mentaltest.adapter.TestItemAdapter;
import com.cx.project.mentaltest.custom.CustomTitle;
import com.cx.project.mentaltest.entity.CeShi;
import com.cx.project.mentaltest.entity.Data;
import com.cx.project.mentaltest.entity.TestItem;
import com.cx.project.mentaltest.entity.User;
import com.cx.project.mentaltest.utils.DataManagerUtil;
import com.cx.project.mentaltest.utils.NetUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class MainActivity extends Activity implements OnItemClickListener{
	private static final String URL="http://bapi.xinli001.com/ceshi/ceshis.json/?category_id=2&rows=10&key=1f7cdc5432ab50dda2bf6331d4a36ec7&offset=0&rmd=-1";
	private static final String URL2="http://news-at.zhihu.com/api/3/stories/latest";
	private static final String TAG ="MainActivity";
	private ListView lvTest;
	private CustomTitle ctTitle;
	private List<TestItem> itemList;
	
	//数据相关
	private CeShi ceshi;
	private TestAdapter adapter;
	
	// 侧滑菜单相关
	private SlidingMenu menu;
	private ListView lvItem;
	private Button  btnLogin;
	
	//UIL 相关
	DisplayImageOptions options;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)  //设置图片Uri为空或是错误的时候显示的图片
		.showImageForEmptyUri(R.drawable.ic_empty)  //设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.ic_error)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)   //设置下载的图片是否缓存在内存中
		.cacheOnDisk(true)    //设置下载的图片是否缓存在SD卡中
		.bitmapConfig(Bitmap.Config.RGB_565)     //设置图片的解码类型
		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(20))
        .build();  
		
		
		intiPareams();
		initView();
	}

	
	
	
	private void intiPareams() {
		itemList = TestItem.getAllItem(new DataManagerUtil(this).openDatabase());
	}




	/**
	 * 初始化界面
	 */
	private void initView() {
		initSlidingMenu();
		initTitle();
		lvTest = (ListView) findViewById(R.id.lv_text);
		lvTest.setAdapter(new TestItemAdapter(this, itemList));
		lvTest.setOnItemClickListener(new homeListOnItemClick());
//		MyAsyncTask task = new MyAsyncTask();
//		task.execute(URL);
		
	}

	/**
	 * 初始化 标题栏
	 */
	private void initTitle() {
		ctTitle = (CustomTitle) findViewById(R.id.custom_title);
		ctTitle.setTitle("首页");
		ctTitle.setImageResource(R.drawable.app_icon);
		ctTitle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				menu.toggle();
			}
		});
	}
	
	/**
	 * 初始化侧滑菜单
	 */
	private void initSlidingMenu() {
		
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setShadowWidth(10);
		menu.setBehindOffset(50);
		menu.setBehindWidth(400);
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		menu.setMenu(R.layout.sliding_menu);
		
		
		lvItem = (ListView) findViewById(R.id.lv_thems);
		btnLogin = (Button) findViewById(R.id.btn_menu_login);
	
		
		lvItem.setAdapter(new MenuItemAdapter(this));
		lvItem.setOnItemClickListener(this);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,RegisterAndLoginActivity.class));
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(User.isLoginState()){
			btnLogin.setText("已登录");
		}else{
			btnLogin.setText("请登录");
		}

	}

	
	/**
	 * 解析Json字符串。
	 * @author CxiaoX
	 *
	 * 2014年12月11日上午9:04:53
	 */
	class MyAsyncTask extends AsyncTask<String, Void, CeShi>{

		@Override
		protected CeShi doInBackground(String... params) {
			try {
				String json=NetUtil.getJson(params[0]);
				if(json!=null){
					ceshi = CeShi.parser(new JSONObject(json));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return ceshi;
		}
		
		@Override
		protected void onPostExecute(CeShi result) {
			adapter = new TestAdapter();
			lvTest.setAdapter(adapter);
			
			
//			adapter.notifyDataSetChanged();
		}
		
	}
	
	/**
	 * 适配器中 每项的控件
	 * @author CxiaoX
	 *
	 * 2014年12月11日上午9:30:13
	 */
	private static class ViewHolder {
		ImageView imgIcon;
		TextView txTitle;
		TextView txViewNum;
		TextView txCommentNum;
	}
	
	/**
	 * 测试列表所用适配器
	 * @author CxiaoX
	 *
	 * 2014年12月10日下午11:35:53
	 */
	class TestAdapter extends BaseAdapter{
		
		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();


		@Override
		public int getCount() {
			return ceshi==null?0:ceshi.getData().size();
		}

		@Override
		public Object getItem(int position) {
			return ceshi.getData().get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			if(convertView==null){
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_test_item, parent,false);
				
				holder = new ViewHolder();
				holder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
				holder.txTitle = (TextView) convertView.findViewById(R.id.tx_title);
				holder.txViewNum = (TextView) convertView.findViewById(R.id.tx_view);
				holder.txCommentNum = (TextView) convertView.findViewById(R.id.tx_comment);
			
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			Data data = ceshi.getData().get(position);
			
			holder.txTitle.setText(data.getTitle());
			holder.txCommentNum.setText(String.format("%s评论", data.getCommentnum()));
			holder.txViewNum.setText(String.format("%s人测过", data.getViewnum()));
			
			//使用Universal　加载图片
			ImageLoader.getInstance().displayImage(data.getCover(), holder.imgIcon, options, animateFirstListener);

			
			return convertView;
		}
		
	}
	
	
	/**
	 * 使用Universal　加载图片时使用
	 * @author CxiaoX
	 *
	 * 2014年12月11日上午9:33:02
	 */
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}


	/*-----------------menu项 点击监听-----------------*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0:
			menu.toggle();
			break;
//		case 1:
//			// 每日心理学
//			break;
		case 1:
			startActivity(new Intent(this, DispositionListActivity.class));
			//性格测试
			break;
		case 2:
			//职业测试
			startActivity(new Intent(this, VocationalListActivity.class));
			break;
		case 3:
			Intent intent = new Intent(this, TestListActivity.class);
			intent.putExtra(Extra.TYPE_ID, 2);
			startActivity(intent);
			break;
		case 4:
			//心理咨询
			startActivity(new Intent(this, CounselingActivity.class));
			break;
		case 5:
			//关于我们
			startActivity(new Intent(this, AboutAppActivity.class));
			break;

		default:
			break;
		}
	}
	/*-----------------menu项 点击监听-----------------*/

	private class homeListOnItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			 TestItem item = itemList.get(position);
			 Intent intent=null;
			switch (item.getTestType()) {
			case TestType.SKIP:
				intent = new Intent(MainActivity.this, SkipTestActivity.class);
				break;
			case TestType.ONE_SELECTE:
				intent = new Intent(MainActivity.this, OneSelectTestActivity.class);
				break;
			case TestType.SELECT_VALUE:
				intent = new Intent(MainActivity.this, 	SelectedValueTestActivity.class);
				break;
			case TestType.VOCATIONL_TEST:
				intent = new Intent(MainActivity.this, VocationalTestActivity.class);
				break;
			default:
				break;
			}
			
			intent.putExtra(Extra.TEST_ID, item.getTestId());
			intent.putExtra(Extra.TYPE_ID, item.getTypeId());
			startActivity(intent);
			
			 
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if(keyCode == KeyEvent.KEYCODE_BACK)  
	       {    
	           exitBy2Click();      //调用双击退出函数  
	       }  
	    return false;  
	}
	
	/** 
	 * 双击退出函数 
	 */  
	private static Boolean isExit = false;  
	  
	private void exitBy2Click() {  
	    Timer tExit = null;  
	    if (isExit == false) {  
	        isExit = true; // 准备退出  
	        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
	        tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                isExit = false; // 取消退出  
	            }  
	        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
	  
	    } else {  
	        finish();  
	        System.exit(0);  
	    }  
	}  
	
	
	
}
