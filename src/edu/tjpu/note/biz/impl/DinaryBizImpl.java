package edu.tjpu.note.biz.impl;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import edu.tjpu.dao.impl.DinaryDaoImpl;
import edu.tjpu.note.biz.DinaryBiz;
import edu.tjpu.note.dao.DinaryDao;
import edu.tjpu.note.datasource.ConnectionManager;
import edu.tjpu.note.datasource.TransactionManager;
import edu.tjpu.note.po.Dinary;


public class DinaryBizImpl implements DinaryBiz {

	private DinaryDao dinarydao;
	
	public DinaryBizImpl() {
		// TODO Auto-generated constructor stub
		this.dinarydao=new DinaryDaoImpl();
	}
	@Override
	public boolean add(Context mContext, Dinary dinary) {
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
					this.dinarydao.insert(dinary, sqLiteDatabase);
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
	public boolean alter(Context mContext, Dinary dinary) {
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
					this.dinarydao.update(dinary, sqLiteDatabase);
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
	public boolean remove(Context mContext, Dinary dinary) {
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
			this.dinarydao.drop(dinary, sqLiteDatabase);
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
	public List<Dinary> find1(Context mContext, Dinary dinary) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "read");

		// 步骤2：调用Dao层方法完成数据库操作
		List<Dinary> lstDinary = this.dinarydao.select1(dinary,sqLiteDatabase);

		// 步骤3：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		// 返回结果
		return lstDinary;
	}

}
