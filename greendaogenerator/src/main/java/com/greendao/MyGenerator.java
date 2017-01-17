package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.appsng.greendaoapp.db");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);

        Entity requests = addRequestTable(schema);
        Entity assets = addAssetTable(schema);

        Property assetsObject = assets.addLongProperty("request_id").notNull().getProperty();
        requests.addToMany(assets, assetsObject, "assets");

    }

    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addIntProperty("user_id").notNull();
        user.addStringProperty("last_name");
        user.addStringProperty("first_name");
        user.addStringProperty("email");
        return user;
    }



    private static Entity addRequestTable(final Schema schema) {
        Entity repo = schema.addEntity("Request");
        repo.addIdProperty().primaryKey().autoincrement();
        repo.addIntProperty("object_id").notNull();
        return repo;
    }


    private static Entity addAssetTable(final Schema schema) {
        Entity repo = schema.addEntity("Asset");
        repo.addIdProperty().primaryKey().autoincrement();
        repo.addIntProperty("name").notNull();
        repo.addStringProperty("location");
        repo.addStringProperty("address");
        return repo;
    }


//    private static Entity addPhonesEntities(final Schema schema) {
//        Entity phone = schema.addEntity("Phone");
//        phone.addIdProperty().primaryKey().autoincrement();
//        phone.addIntProperty("user_id").notNull();
//        phone.addStringProperty("number");
//        return phone;
//    }
}
