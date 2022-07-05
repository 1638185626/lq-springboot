package com.example.lqmybatiscode;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: SimpleStatementHandler
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/7/5
 **/
public class SimpleStatementHandler {
    public  List  query(Connection connection, MappedStatement statement, Object parameter) {
        String sql = statement.getSql();
        List list = new ArrayList();
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement=null;
            if (sql.contains("#{")){
                sql = sql.replace("#{id}", "?");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, ((Integer) parameter));
            }else {
                preparedStatement = connection.prepareStatement(sql);
            }
             resultSet = preparedStatement.executeQuery();
            Object result=null;
            while (resultSet.next()) {
                Class resultClass = statement.getResultMaps().getType();
                result = resultClass.newInstance();
                for (ResultMapping propertyResultMapping : statement.getResultMaps().getPropertyResultMappings()) {
                    String columnValue = resultSet.getString(propertyResultMapping.getColumn());
                    if (columnValue!=null && !columnValue.equals("")){
                        Field field = resultClass.getDeclaredField(propertyResultMapping.getColumn());
                        Object type = field.getType();
                        field.setAccessible(true);
                        // 反射获取字段类型
                        if ("java.lang.Long".equals(field.getType().getName())){
                            field.set(result,Long.parseLong(columnValue));
                        }else {
                            field.set(result,columnValue);
                        }
                    }
                }
            }
            list.add(result);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (List) list;
    }
}
