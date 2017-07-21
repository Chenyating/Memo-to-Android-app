package edu.tjpu.note.biz.impl;

import java.util.List;

import edu.tjpu.dao.impl.INoteDaoImpl;
import edu.tjpu.dao.impl.MessageDaoImpl;
import edu.tjpu.note.biz.IMessageBiz;
import edu.tjpu.note.biz.INoteBiz;
import edu.tjpu.note.dao.IMessageDao;
import edu.tjpu.note.dao.INoteDao;
import edu.tjpu.note.datasource.ConnectionManager;
import edu.tjpu.note.datasource.TransactionManager;
import edu.tjpu.note.po.Message;
import edu.tjpu.note.po.Note;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteBizImpl implements INoteBiz {

	private INoteDao notedao;

	public NoteBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.notedao = new INoteDaoImpl();
	}

	@Override
	public boolean add(Context mContext, Note note) {
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
			this.notedao.insert(note, sqLiteDatabase);
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
	public boolean alter(Context mContext, Note note) {
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
			this.notedao.update(note, sqLiteDatabase);
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
	public List<Note> find1(Context mContext) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
		ConnectionManager connectionManager = new ConnectionManager();
		SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
				mContext, "read");

		// 步骤2：调用Dao层方法完成数据库操作
		List<Note> lstNote = this.notedao.select1(sqLiteDatabase);

		// 步骤3：关闭数据库连接
		connectionManager.closeConnection(sqLiteDatabase);

		// 返回结果
		return lstNote;
	}

	@Override
	public boolean remove(Context mContext, Note note) {
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
			this.notedao.drop(note, sqLiteDatabase);
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
	public boolean alterzhiding(Context mContext, Note note) {
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
					this.notedao.updatezhidingiid(note, sqLiteDatabase);
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


	public boolean alteriid(Context mContext) {
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
					this.notedao.updateiid(sqLiteDatabase);
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
	public List<Note> find2(Context mContext, Note note) {
		// TODO Auto-generated method stub
		// 步骤1：获取数据库连接对象
				ConnectionManager connectionManager = new ConnectionManager();
				SQLiteDatabase sqLiteDatabase = connectionManager.openConnection(
						mContext, "read");

				// 步骤2：调用Dao层方法完成数据库操作
				List<Note> lstNote = this.notedao.select2(sqLiteDatabase,note);

				// 步骤3：关闭数据库连接
				connectionManager.closeConnection(sqLiteDatabase);

				// 返回结果
				return lstNote;
	}

}
