package demo.ht.com.toolbartext;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.ht.com.toolbartext.adapters.MyAdapter;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);


        initToolbar();

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView rel = findViewById(R.id.rel);

        rel.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<String> mlist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mlist.add("元素" + (i + 1));
        }

        MyAdapter adapter = new MyAdapter(R.layout.rel_item_layout, mlist);

        rel.setAdapter(adapter);

        /*
         * 单击事件
         * */
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, mlist.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        /*
         * 长按事件注册上下文菜单
         * */
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                //注册上下文菜单
                registerForContextMenu(view);
                return false;
            }
        });


    }

    //上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.toolbar_menu_context, menu);
//        menu.setHeaderTitle("请选择操作:");
//        menu.add("添加").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(MainActivity.this, "添加", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        menu.add("删除");
    }

    //上下文菜单点击事件
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_remove:
                Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private void initToolbar() {

        //配合menu使用,不添加menu不显示
        setSupportActionBar(toolbar);


        //以下三行是修改回退按钮为白色的逻辑
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /*
     * 是否选中
     * */
    private Boolean isSelect = true;

    @Override//普通菜单
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        //menu_group  start
//        getMenuInflater().inflate(R.menu.toolbar_menu_group, menu);
        //menu_group  stop

        //对menu的点击操作  start
        getMenuInflater().inflate(R.menu.toolbar_menu_select, menu);
        MenuItem menu_start = menu.findItem(R.id.menu_start);
        MenuItem menu_stop = menu.findItem(R.id.menu_stop);
        if (isSelect) {
            menu_start.setEnabled(true);
            menu_stop.setEnabled(false);
        } else {
            menu_start.setEnabled(false);
            menu_stop.setEnabled(true);
        }
        //对menu的点击操作  stop
        return true;
    }

    ////menu点击事件监听
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_my:
                Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_genduo:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item1:
                Toast.makeText(this, "年龄", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(this, "班级", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_start:
                Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
                isSelect = !isSelect;
                invalidateOptionsMenu();
                break;
            case R.id.menu_stop:
                isSelect = !isSelect;
                invalidateOptionsMenu();
                Toast.makeText(this, "结束", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //popupMenu菜单
    public void onPopupMenuClick(View view) {
        PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);//1.实例化PopupMenu
        getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());//2.加载Menu资源


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               switch (item.getItemId()){
                   case R.id.menu_my:
                       Toast.makeText(MainActivity.this, "我的", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_setting:
                       Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_genduo:
                       Toast.makeText(MainActivity.this, "更多", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_item1:
                       Toast.makeText(MainActivity.this, "年龄", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.menu_item2:
                       Toast.makeText(MainActivity.this, "班级", Toast.LENGTH_SHORT).show();
                       break;
               }
                return false;
            }
        });
        popupMenu.show();//4.显示弹出菜单
    }
}
