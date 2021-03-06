package team.house.cn.HuoseApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import team.house.cn.HuoseApp.R;
import team.house.cn.HuoseApp.activity.ReservationServiceActivity;
import team.house.cn.HuoseApp.bean.ServiceContentBean;

/**
 * Created by kenan on 15/11/8.
 */
public class ServiceContentAdaper extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ServiceContentBean> mServiceContentBeans;
    private ReservationServiceActivity reservationServiceActivity;

    public ServiceContentAdaper(List<ServiceContentBean> mServiceContentBeans, ReservationServiceActivity context) {
        reservationServiceActivity = context;
        this.mServiceContentBeans = mServiceContentBeans;
        inflater = LayoutInflater.from(context);

    }
    public void addItems(List<ServiceContentBean> _mServiceContentBeans) {
        mServiceContentBeans = _mServiceContentBeans;
    }

    @Override
    public int getCount() {
        return mServiceContentBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mServiceContentBeans.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ToggleButton_ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_reservationservice, null);
            viewHolder = new ToggleButton_ViewHolder();
            viewHolder.mToggleButton = (ToggleButton) convertView.findViewById(R.id.btn_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ToggleButton_ViewHolder) convertView.getTag();
        }
        ServiceContentBean  serviceContentBean = mServiceContentBeans.get(i);
        viewHolder.mToggleButton.setText(serviceContentBean.getIndus_name());
        viewHolder.mToggleButton.setTextOff(serviceContentBean.getIndus_name());
        viewHolder.mToggleButton.setChecked(serviceContentBean.isChecked());
        viewHolder.mToggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updateServiceContent(i);
                    reservationServiceActivity.serviceContentGridView_ItemChecked(mServiceContentBeans.get(i));
                }


            }
        });
        viewHolder.mToggleButton.setTextOn(serviceContentBean.getIndus_name());
        return convertView;
    }
    private void updateServiceContent (int mPosition){

        for (int j = 0; j < mServiceContentBeans.size(); j++){
            if (j != mPosition) {
                mServiceContentBeans.get(j).setIsChecked(false);
            } else {
                mServiceContentBeans.get(j).setIsChecked(true);
            }

        }
        this.notifyDataSetChanged();
    }
//    class ViewHolder {
//        public ToggleButton mToggleButton;
//
//
////        mToggleButton.setOnCheckedChangeListener(new View.OnCheckedChangeListener()
////        {
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
////            {
////                toggleButton.setChecked(isChecked);
////                imageView.setImageResource(isChecked ? R.drawable.bulb_on : R.drawable.bulb_off);
////
////            }
////
////        });
//    }
}
