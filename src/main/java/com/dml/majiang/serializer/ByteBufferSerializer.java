package com.dml.majiang.serializer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 常用类型的ByteBuffer化工具
 *
 * @author zheng chengdong
 */
public class ByteBufferSerializer {
	/**
	 * 把字符串序列化到ByteBuffer.<br/>
	 * 数据格式：4个字节代表字符串长度n（字节数），后面n个字节为字符串数据。
	 *
	 * @param str
	 * @param bb
	 * @throws Throwable
	 */
	public static void stringToByteBuffer(String str, ByteBuffer bb) throws Throwable {
		if (str != null) {
			bb.put((byte) 1);
			byteArrayToByteBuffer(str.getBytes("UTF-8"), bb);
		} else {
			bb.put((byte) 0);
		}
	}

	/**
	 * 把布尔类型 序列化到ByteBuffer.
	 *
	 * @param value
	 * @param bb
	 * @throws Throwable
	 */
	public static void booleanToByteBuffer(boolean value, ByteBuffer bb) throws Throwable {
		if (value) {
			bb.put((byte) 1);
		} else {
			bb.put((byte) 0);
		}
	}

	/**
	 * ByteBuffer反序列化成布尔
	 *
	 * @param bb
	 * @return
	 * @throws Throwable
	 */
	public static boolean byteBufferToBoolean(ByteBuffer bb) throws Throwable {
		return bb.get() == 1;
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 *
	 * @param bytes
	 * @param bb
	 */
	public static void byteArrayToByteBuffer(byte[] bytes, ByteBuffer bb) {
		if (bytes == null) {
			bb.putInt(0);
		} else {
			bb.putInt(bytes.length);
			bb.put(bytes);
		}
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 *
	 * @param bytes
	 * @param bb
	 */
	public static void intArrayToByteBuffer(int[] intArray, ByteBuffer bb) {
		if (intArray == null) {
			bb.putInt(0);
		} else {
			bb.putInt(intArray.length);
			for (int i : intArray) {
				bb.putInt(i);
			}
		}
	}

	/**
	 * ByteBuffer反序列化成字符串.<br/>
	 * 数据格式：4个字节代表字符串长度n（字节数），后面n个字节为字符串数据。
	 *
	 * @param bb
	 * @return
	 * @throws Throwable
	 */
	public static String byteBufferToString(ByteBuffer bb) throws Throwable {
		byte notNull = bb.get();
		if (notNull == 1) {
			return new String(byteBufferTobyteArray(bb), "UTF-8");
		} else {
			return null;
		}
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 *
	 * @param bb
	 * @return
	 */
	public static byte[] byteBufferTobyteArray(ByteBuffer bb) {
		int length = bb.getInt();
		byte[] bytes = new byte[length];
		bb.get(bytes, 0, length);
		return bytes;
	}

	/**
	 * 数据格式：4个字节代表数组长度n（字节数），后面n个字节数据。
	 *
	 * @param bb
	 * @return
	 */
	public static int[] byteBufferTointArray(ByteBuffer bb) {
		int length = bb.getInt();
		int[] intArray = new int[length];
		for (int i = 0; i < length; i++) {
			intArray[i] = bb.getInt();
		}
		return intArray;
	}

	/**
	 * 把list序列化到ByteBuffer.<br/>
	 * 数据格式：4个字节代表list长度，后面为一个挨着一个的内容。
	 *
	 * @param list
	 * @param bb
	 * @throws Throwable
	 */
	public static void listToByteBuffer(List<Object> list, ByteBuffer bb) throws Throwable {
		if (list == null) {
			bb.putInt(0);
		} else {
			bb.putInt(list.size());
			for (Object obj : list) {
				if (obj instanceof String) {
					bb.put((byte) 1);
					stringToByteBuffer((String) obj, bb);
				} else if (obj instanceof Boolean) {
					bb.put((byte) 2);
					ByteBufferSerializer.booleanToByteBuffer((Boolean) obj, bb);
				} else if (obj instanceof Integer) {
					bb.put((byte) 3);
					bb.putInt((int) obj);
				} else if (obj instanceof Long) {
					bb.put((byte) 4);
					bb.putLong((long) obj);
				} else if (obj instanceof Double) {
					bb.put((byte) 5);
					bb.putDouble((double) obj);
				} else if (obj instanceof ByteBufferAble) {
					bb.put((byte) 6);
					objToByteBuffer((ByteBufferAble) obj, bb);
				} else {
					throw new Exception("unsuported type for:" + obj);
				}

			}
		}
	}

	/**
	 * ByteBuffer list.<br/>
	 * 数据格式：4个字节代表list长度，后面为一个挨着一个的内容。
	 *
	 * @param bb
	 * @return
	 */
	public static List<Object> byteBufferToList(ByteBuffer bb) throws Throwable {
		int size = bb.getInt();
		List<Object> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {

			byte elType = bb.get();
			if (elType == 1) {
				list.add(byteBufferToString(bb));
			} else if (elType == 2) {
				list.add(byteBufferToBoolean(bb));
			} else if (elType == 3) {
				list.add(bb.getInt());
			} else if (elType == 4) {
				list.add(bb.getLong());
			} else if (elType == 5) {
				list.add(bb.getDouble());
			} else if (elType == 6) {
				list.add(byteBufferToObj(bb));
			} else {
				throw new Exception("unsuported type :" + elType);
			}

		}
		return list;
	}

	/**
	 * 反序列化List<String><br/>
	 * 数据格式：4个字节代表list长度，后面为一个挨着一个的内容。
	 *
	 * @param bb
	 * @return
	 */
	public static List<String> byteBufferToStringList(ByteBuffer bb) throws Throwable {
		int size = bb.getInt();
		List<String> list = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			list.add(byteBufferToString(bb));
		}
		return list;
	}

	/**
	 * 把List<String>序列化到ByteBuffer.<br/>
	 *
	 * @param list
	 * @param bb
	 * @throws Throwable
	 */
	public static void stringListToByteBuffer(List<String> list, ByteBuffer bb) throws Throwable {
		if (list == null) {
			bb.putInt(0);
		} else {
			bb.putInt(list.size());
			for (String str : list) {
				stringToByteBuffer(str, bb);
			}
		}

	}

	/**
	 * 把ByteBufferAble对象序列化到ByteBuffer。<br/>
	 * 数据格式：首先序列化ByteBufferAble对象的类型字符串，然后序列化ByteBufferAble对象本身。
	 *
	 * @param obj
	 * @param bb
	 * @throws Throwable
	 */
	public static void objToByteBuffer(ByteBufferAble obj, ByteBuffer bb) throws Throwable {
		if (obj != null) {
			bb.put((byte) 1);
			stringToByteBuffer(obj.getClass().getName(), bb);
			obj.toByteBuffer(bb);
		} else {
			bb.put((byte) 0);
		}
	}

	/**
	 * ByteBufferAble对象反序列化。<br/>
	 * 数据格式：首先反序列化ByteBufferAble对象的类型字符串，然后反射实例化对象， 然后填充对象。
	 *
	 * @param bb
	 * @return
	 */
	public static <E extends ByteBufferAble> E byteBufferToObj(ByteBuffer bb) throws Throwable {
		byte notNull = bb.get();
		if (notNull == 1) {
			String typeStr = byteBufferToString(bb);
			ByteBufferAble obj = (ByteBufferAble) Class.forName(typeStr).newInstance();
			obj.fillByByteBuffer(bb);
			return (E) obj;
		} else {
			return null;
		}
	}

}
