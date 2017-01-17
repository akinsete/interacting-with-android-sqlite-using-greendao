package com.appsng.greendaoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appsng.greendaoapp.db.DaoSession;
import com.appsng.greendaoapp.db.Request;
import com.appsng.greendaoapp.db.RequestDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoSession = ((AppController) getApplication()).getDaoSession();


        RequestDao requestDao = daoSession.getRequestDao();
        WhereCondition whereCondition = RequestDao.Properties.Object_id.eq(2);

        QueryBuilder<Request> queryBuilder = requestDao.queryBuilder();
        queryBuilder.where(whereCondition);

        List<Request> requestList = queryBuilder.list();

    }

}
