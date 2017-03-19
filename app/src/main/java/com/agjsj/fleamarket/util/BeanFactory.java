package com.agjsj.fleamarket.util;

import com.agjsj.fleamarket.view.base.BaseApplication;
import com.orhanobut.logger.Logger;

import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 工厂类
 * @author yh
 *
 */
public class BeanFactory {

	private static WeakHashMap<String,Object> beanCache = new WeakHashMap<>(16);

	private static Lock lock = new ReentrantLock();


	/**
	 * 反射
	 * 加载需要的实现类
	 * @param clazz
	 * @return
	 */
	public static<T> T getImpl(Class<T> clazz){
 		String key = clazz.getSimpleName();
		String className = BaseApplication.INSTANCE().getProperties().getProperty(key);
		if(beanCache.get(className) != null){
			return (T) beanCache.get(className);
		}
		try {
			lock.lock();
			Object o = Class.forName(className).newInstance();
			if(o != null){
				beanCache.put(className,o);
				return (T) o;
			}
		} catch (Exception e) {
			Logger.e("初始化"+className+"实例时出错",e);
		}finally {
			lock.unlock();
		}
		return null;
	}
}
