package demo.ht.com.toolbartext.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;
import demo.ht.com.toolbartext.R;

/**
 * 创建时间:2021/3/5
 * 创建者:正在蜕变的工程师
 * 博客:https://blog.csdn.net/weixin_44819566
 */
public class MyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv,item);
    }
}
