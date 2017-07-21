package edu.tjpu.note.biz.impl;

import java.util.List;

import edu.tjpu.dao.impl.MessageDaoImpl;
import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.dao.IMessageDao;
import edu.tjpu.note.datasource.ConnectionManager;
import edu.tjpu.note.datasource.TransactionManager;
import edu.tjpu.note.po.Message;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MessageBizImpl implements IMessageBiz {

	private IMessageDao messageDao;

	public MessageBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.messageDao = new MessageDaoImpl();
	}

	@Override
	public boolean add(Context mContext, Message message) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "write");

		// 步骤2：开启事务处理
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.beginTransaction(sqLiteDatabase);

		// 步骤3：调用DAO实现添加操作
		try {
			this.messageDao.insert(message, sqLiteDatabase);
		} catch (SQLException ex) {
			return false;
		} catch (RuntimeException ex) {
			return false;
		}
		// 步骤4：提交事务
		transactionManager.commitTransaction(sqLiteDatabase);

		// 步骤5：关闭事务
		transactionManager.closeTransaction(sqLiteDatabase);

		// 步骤6：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		return true;
	}

	@Override
	public boolean alter(Context mContext, Message message) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "write");

		// 步骤2：开启事务处理
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.beginTransaction(sqLiteDatabase);

		// 步骤3：调用DAO实现添加操作
		try {
			this.messageDao.update(message, sqLiteDatabase);
		} catch (SQLException ex) {
			return false;
		} catch (RuntimeException ex) {
			return false;
		}
		// 步骤4：提交事务
		transactionManager.commitTransaction(sqLiteDatabase);

		// 步骤5：关闭事务
		transactionManager.closeTransaction(sqLiteDatabase);

		// 步骤6：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		return true;

	}

	@Override
	public List<Message> find(Context mContext) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "read");

		// 步骤2：调用Dao层方法完成数据库操作
		List<Message> lstMessage = this.messageDao.select(sqLiteDatabase);

		// 步骤3：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		// 返回结果
		return lstMessage;
	}

	@Override
	public boolean alterphoto(Context mContext, Message message) {
		// TODO Auto-generated method stub
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "write");

		// 步骤2：开启事务处理
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.beginTransaction(sqLiteDatabase);

		// 步骤3：调用DAO实现添加操作
		try {
			this.messageDao.updatephoto(message, sqLiteDatabase);
		} catch (SQLException ex) {
			return false;
		} catch (RuntimeException ex) {
			return false;
		}
		// 步骤4：提交事务
		transactionManager.commitTransaction(sqLiteDatabase);

		// 步骤5：关闭事务
		transactionManager.closeTransaction(sqLiteDatabase);

		// 步骤6：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		return true;

	}

}
