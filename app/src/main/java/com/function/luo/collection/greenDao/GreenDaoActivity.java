package com.function.luo.collection.greenDao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.function.luo.collection.MainActivity;
import com.function.luo.collection.R;
import com.function.luo.collection.base.BaseActivity;
import com.function.luo.collection.base.DbBean;
import com.function.luo.collection.bean.SkuBean;
import com.function.luo.collection.bean.User;
import com.function.luo.collection.bean.UserDao;
import com.function.luo.collection.greenDao.utils.DaoService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2019/4/22.
 * greenDao 数据库的使用,myApplication 的上下文,一定不能为空
 * <p>
 * QQ 联系:1138191036
 */

public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;

    private User user;
    private String Tag = "LUO";


    @Override
    public void initData() {
        super.initData();
        user = new User();
        complexQuery();
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_green_dao;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_query, R.id.btn_add, R.id.btn_update, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                List<User> dbBeans = (List<User>) DaoService.loadAllDbBean(user.getClass());
                if (dbBeans != null && dbBeans.size() > 0) {
                    User user = dbBeans.get(0);
                    tvName.setText(user.getName());
                    tvAge.setText(user.getAge());
                }else {
                    tvName.setText("无");
                    tvAge.setText("无");
                }

//                List<? extends DbBean> dbBeans = DaoService.loadAllDbBean(SkuBean.class);
//
//                Log.d("LUO","======="+dbBeans.size());
                Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_add:
                user.setId((long) 1000);
                user.setAge("26");
                user.setName("小明");
//                List<SkuBean> list = new ArrayList<>();
//                for (int i=0;i<5;i++){
//
//                    SkuBean skuBean = new SkuBean();
//                    skuBean.setId((long) i);
//                    if (i==0){
//                        skuBean.setNo("00000");
//                    }else if (i==1){
//                        skuBean.setNo("1111");
//                    }else {
//                        skuBean.setNo("333");
//                    }
//
//
//
//                    list.add(skuBean);
//                }
//                DaoService.insertOrReplaceDaoBeanList(list);
                 DaoService.insertOrReplaceDaoBean(user);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                List<User> dbBeans2 = (List<User>) DaoService.loadAllDbBean(user.getClass());
                if (dbBeans2 != null && dbBeans2.size() > 0) {
                    User user2 = dbBeans2.get(0);
                    user2.setName("小刚");
                    user2.setAge("22");
                    DaoService.insertOrReplaceDaoBean(user2);
                }
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:

//                List<? extends DbBean> dbBeans3 = DaoService.loadAllDbBean(SkuBean.class);
//                for (DbBean bean:dbBeans3){
//                    DaoService.deleteDaoBean(bean);
//
//                }
                 DaoService.deleteDaoBean(user);
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }



    /**
     * 复杂查询
     */
    private void complexQuery() {
        //CBA所有的BA,基础数据
        try {
            DaoService.PropertyParam param = new DaoService.PropertyParam(UserDao.Properties.Age, "BA");

            final List<DbBean> baBeanList_ba = (List<DbBean>) DaoService.selectDaoBeanByAndCondition(DbBean.class, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        List<WhereCondition> whereConditions = new ArrayList<>();
//        whereConditions.add(UserDao.Properties.Class_type.notEq("BA"));
//        whereConditions.add(UserDao.Properties.Date.eq(DateTimeHelper.format(new Date(), "yyyyMMdd")));
//        whereConditions.add(UserDao.Properties.Workstatus.eq(true));
//        //所有的BC和BAS
//        List<DbBean> bcBeanList = (List<DbBean>) CommonDaoService.getInstance().loadAllDbBeanWithConditions(CUserBean.class, whereConditions2);




    }

    /**
     * 页面跳转
     * @param mainActivity
     */
    public static void launch(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity, GreenDaoActivity.class);
        mainActivity.startActivity(intent);
    }
}
