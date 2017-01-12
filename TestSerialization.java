import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ������Դ���ڿ�arrayListԴ��ʱ�������б���elementData�Ǳ�transient���ε�
 * 		     ����arrayList��ʵ����Serializable�ӿڵģ�elementData�Ǵ洢arrayList��ֵ��
 * 		     ��ôarrayList�����ʵ�����л��뷴���л����أ�
 * ����������������֪�����л��뷴���л���ʹ��ObjectInputStream��ObjectOutputStream�е�
 * 		  readObject��writeObject����ʵ�ֵģ���ô��ʹarrayList�е�elementData������
 * 		     ��transient�ģ�Ҳ������arrayList�ж����������������������е���ObjectInputStream��
 * 		  ObjectOutputStream�е������������������arrayListԴ����ȷʵ�ж�����������������������
 * 		   �Ƿ����������������ͻᱻ�����أ��������������������������л��뷴���л��Ƿ��ɹ�.
 * @author Administrator
 *
 */
public class TestSerialization implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient int    num;

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException
    {
        s.defaultWriteObject();
        s.writeObject(num);
        System.out.println("writeObject of "+this.getClass().getName());
    }

    /**
     * @param s
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        num = (Integer) s.readObject();
        System.out.println("readObject of "+this.getClass().getName());
    }

    public static void main(String[] args)
    {
        TestSerialization test = new TestSerialization();
        test.setNum(10);
        System.out.println("���л�֮ǰ��ֵ��"+test.getNum());
        // д��
        try
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream("D:\\test.tmp"));
            outputStream.writeObject(test);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // ��ȡ
        try
        {
            ObjectInputStream oInputStream = new ObjectInputStream(
                    new FileInputStream("D:\\test.tmp"));
            try
            {
                TestSerialization aTest = (TestSerialization) oInputStream.readObject();
                System.out.println("��ȡ���л����ֵ��"+aTest.getNum());
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}