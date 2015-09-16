package com.gym.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.R;
import com.gym.app.Constants;
import com.gym.bean.CityBean;
import com.gym.http.protocol.BaseProtocol;
import com.gym.http.protocol.CityProtocol;
import com.gym.utils.ProgressUtil;
import com.gym.utils.UIUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class CitySelectActivity extends BaseActivity {
    @InjectView(R.id.title_tb)
    TextView titleTb;
    @InjectView(R.id.back_tb)
    ImageView backTb;
    @InjectView(R.id.area_tb)
    TextView areaTb;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv)
    ListView lv;
    private ActionBar mActionBar;
    private boolean loading = false;
    private ArrayList<CityBean> cityBeans;

    @Override
    public void init() {
        super.init();
        setContentView(R.layout.activity_city);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        new CityTask().execute();

    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);

        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        titleTb.setText(UIUtils.getString(R.string.city_search));
    }

    class CityAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cityBeans == null ? 0 : cityBeans.size();
        }

        @Override
        public Object getItem(int i) {
            return cityBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = UIUtils.inflate(R.layout.item_searchcitys);
                ;
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder = (ViewHolder) view.getTag();
            CityBean bean = cityBeans.get(i);
            holder.cityName.setText(bean.getCity());
            return view;
        }


    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_searchcitys.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.cityName)
        TextView cityName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class CityTask extends AsyncTask<String, String, ArrayList<CityBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = true;
            ProgressUtil.startProgressBar(CitySelectActivity.this);
        }

        @Override
        protected ArrayList<CityBean> doInBackground(String... strings) {
            CityProtocol protocol = new CityProtocol();
            cityBeans = protocol.load(UIUtils.getString(R.string.GetCity_URL), BaseProtocol.GET);

            cityBeans.clear();
            CityBean cityBean1 = new CityBean();
            cityBean1.setCity("上海市");
            CityBean cityBean2 = new CityBean();
            cityBean2.setCity("北京市");
            CityBean cityBean3 = new CityBean();
            cityBean3.setCity("南京市");
            cityBeans.add(cityBean1);
            cityBeans.add(cityBean2);
            cityBeans.add(cityBean3);
            return cityBeans;
        }

        @Override
        protected void onPostExecute(ArrayList<CityBean> beans) {
            loading = false;
            ProgressUtil.stopProgressBar();
            CityAdapter adapter = new CityAdapter();
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Constants.city = cityBeans.get(i);
                    finish();
                }
            });
        }
    }
}
