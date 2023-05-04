package basic.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* TreeMap
 * ����Ʈ���� ������� �ϴ� Map Collection
 * TreeSet�� ���� �����Ѵٸ� TreeMap�� key�� value�� ����Ǵ� Map, Entry�� �����ϴ� ��
 * �ڵ����� ���ĵ�.
 * key�� ����� ���ÿ� ������������ ���ĵǰ� ���� Ÿ���� ��� ������
 * ���� Ÿ���� ��� �����ڵ�� ���ĵȴ�.
 * key���� ������ ���� ������ ������
 * HashMap���� ������ ��������.
 * ��� �����ϱ� ������ add�� delete�� HashMap���� ���� �ɸ���.
 * ���ĵ� �����͸� ��ȸ�ؾ� �ϴ� ���� �˻��� ��� TreeMap�� ȿ�����̴�.
 * 
 * */
public class TreeMapExam2 {
	public static void main(String[] args) throws Exception {

		// Ʈ���� ����
		TreeMap<Integer, String> tmap1 = new TreeMap<>();

		tmap1.put(1, "���");
		tmap1.put(2, "��");
		tmap1.put(3, "����");

		System.out.println(tmap1);

		/*
		 * TreeMap�� HassMap�� �⺻������ Map �������̽��� �����ϰ� ��� �����Ƿ� �⺻���� �޼ҵ��� ���� ��ü�� �����ϴ�.
		 * 
		 * �� �߰� : put(key, value) �ԷµǴ� key ���� TreeMap ���ο� �����Ѵٸ� ������ ���� ���� �ԷµǴ� ������ ����ȴ�.
		 */

		// �� ����
		tmap1.remove(1); // key�� 1 ����

		System.out.println(tmap1);

		tmap1.clear(); // ��� �� ����

		System.out.println(tmap1);

		System.out.println();

		tmap1.put(1, "���");
		tmap1.put(2, "��");
		tmap1.put(5, "����");
		tmap1.put(3, "����");

		System.out.println(tmap1); // ��ü ���

		// key 1�� value ��������
		System.out.println(tmap1.get(1));
		// �ּ� Entry ���
		System.out.println(tmap1.firstEntry());
		// �ּ� key ���
		System.out.println(tmap1.firstKey());
		// �ִ� Entry ���
		System.out.println(tmap1.lastEntry());
		// �ִ� key ���
		System.out.println(tmap1.lastKey());

		// ��ü �� ���
		// entrySet() Ȱ��
		System.out.println("��ü �� ���");
		for (Entry<Integer, String> entry : tmap1.entrySet()) {
			System.out.println("[Key]: " + entry.getKey() + " [Value] : " + entry.getValue());
		}

		// KeySet() Ȱ��
		System.out.println("KeySet() Ȱ��");
		for (Integer i : tmap1.keySet()) { // ����� key�� Ȯ��
			System.out.println("[Key]: " + i + " [Value]: " + tmap1.get(i));
		}

		// Iterator ���
		TreeMap<Integer, String> tmap3 = new TreeMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{// �ʱⰪ ����
				put(1, "���");// �� �߰�
				put(2, "������");
				put(3, "����");
			}
		};

		System.out.println();

		// entrySet().iterator()
		Iterator<Entry<Integer, String>> entries = tmap3.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, String> entry = entries.next();
			System.out.println("[Key]:" + entry.getKey() + " [Value]:" + entry.getValue());
		}
		
		System.out.println();
		
		Iterator<Integer> keys = tmap3.keySet().iterator();
		
		while (keys.hasNext()) {
			int key = keys.next();
			System.out.println("[Key] : " + key);
		}
		
	}
}
